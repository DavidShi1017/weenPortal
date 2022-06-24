package com.jingtong.platform.sap.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.CrmOrderToSAPDao;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.Credit;
import com.jingtong.platform.sap.pojo.CreditLog;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.OldProduct;
import com.jingtong.platform.sap.pojo.PaymentDetailRecords;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.service.CrmOrderToSAPService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class CrmOrderToSAPServiceImpl extends BasicService implements CrmOrderToSAPService {
    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private CrmOrderToSAPDao crmOrderDao;
    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public CrmOrderToSAPDao getCrmOrderDao() {
        return crmOrderDao;
    }

    public void setCrmOrderDao(CrmOrderToSAPDao crmOrderDao) {
        this.crmOrderDao = crmOrderDao;
    }

    @Override
    public String crmOderToSap(CrmOrderToSap order, List<CrmOrderItem> items) {
        // 调用sap接口获取库存和物料
        String resultMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZCRM_SO_CREATE");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("产品订单传入SAP/ZCRM_SO_CREATE");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (order != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("HEAD");// 获取结构
                head.setValue("ID", order.getId());
                head.setValue("AUART", order.getAuart());
                head.setValue("VKORG", order.getVkorg());
                head.setValue("VTWEG", order.getVtweg());
                head.setValue("SPART", order.getSpart());
                head.setValue("VKBUR", order.getVkbur());
                head.setValue("BZIRK", order.getBzirk());
                head.setValue("ZTERM", order.getZterm());
                head.setValue("AUDAT", order.getAudat());
                head.setValue("BSTKD", order.getBstkd());
                head.setValue("KUNAG", order.getKunag());
                head.setValue("KUNNR", order.getKunnr());
                head.setValue("KUNRG", order.getKunrg());
                head.setValue("KUNRE", order.getKunre());
                head.setValue("ABRVW", order.getAbrvw());
                head.setValue("PERNR", order.getPernr());
                head.setValue("AUGRU", order.getAugru());
                head.setValue("PRSDT", order.getPrsdt());
            }
            if (items != null) {
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("ITEM");// 获取表传值
                for (CrmOrderItem it : items) {
                    item.appendRow(); // 放入表内
                    item.setValue("MANDT", it.getMandt());
                    // item.setValue("ID",it.getId());
                    item.setValue("POSNR", it.getPosnr());
                    item.setValue("MATNR", it.getMatnr());
                    item.setValue("KWMENG", it.getKwmeng());
                    item.setValue("VRKME", it.getVrkme());
                    item.setValue("CHARG", it.getCharg());
                    item.setValue("LGORT", it.getLgort());
                    item.setValue("WERKS", it.getWerks());

                    item.setValue("FATHER", it.getFather());
                    item.setValue("PSTYV", it.getPstyv());
                    item.setValue("PRICE", it.getPrice());
                    item.setValue("WAERK", it.getWaerk());
                    item.setValue("CREDIT_ID", it.getCreditId());
                    // item.setValue("TEXT",it.getWerks());
                    item.setValue("PROMOTION_ID", it.getPromotionId());
                    item.setValue("PRODETAIL_ID", it.getPromoDetailId());
                    item.setValue("CHARG", it.getBatch());

                }
            }
            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
            String strMessage = function.getExportParameterList().getValue("STR_MSG").toString();

            String sapOrderCode = function.getExportParameterList().getValue("SALESDOCUMENT").toString();// sap订单
            System.out.println(sapOrderCode);
            int rows = resultTbl.getNumRows();

            if ("".equals(sapOrderCode) || sapOrderCode == null || "null".equals(sapOrderCode)) {

                if (rows > 0) {
                    String type = resultTbl.getString("TYPE");
                    String message = resultTbl.getString("MESSAGE");
                    System.out.println("订单同步失败！" + "sapOrderCode:" + sapOrderCode + "--" + type + "--" + message);

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("订单传入SAP/ZCRM_SO_CREATE");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("单号: " + sapOrderCode + "," + type);
                    log.setLogInfo(message);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    resultMessage = "<font color=red>操作失败!" + message + "," + strMessage + "</font>";

                } else {

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("订单传入SAP/ZCRM_SO_CREATE");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败! " + strMessage);
                    log.setLogInfo("失败!" + strMessage);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    resultMessage = "<font color=red>操作失败!" + strMessage + "</font>";

                }

            } else {

                // 订单提交完成后修改订单明细状态，并扣减信用-------事物提交
                updateDbOrder(order, items, sapOrderCode);

                if (rows > 0) {
                    String type = resultTbl.getString("TYPE");
                    String message = resultTbl.getString("MESSAGE");

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("订单传入SAP/ZCRM_SO_CREATE");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("单号: " + sapOrderCode + "," + type);
                    log.setLogInfo(message);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("产品订单传入SAP/ZCRM_SO_CREATE");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("成功！");
                    log.setLogInfo("成功! " + sapOrderCode);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                resultMessage = "<font color=Green>操作成功!SAP单号：" + sapOrderCode + "</font>";
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("产品订单传入SAP/ZCRM_SO_CREATE");
            log.setOperateUser("SAP");
            log.setOperateTime(new Date());
            log.setLogDesc("失败！");
            log.setLogInfo(e.getMessage());
            try {
                this.logInfoDao.addLogInfo(log);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return resultMessage + ";<br/>";
    }

    public BooleanResult updateDbOrder(final CrmOrderToSap order, final List<CrmOrderItem> items,
            final String sapOrderCode) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                booleanResult.setResult(true);
                try {
                    String kunnr = order.getKunag();
                    booleanResult.setResult(false);
                    int num = 0;
                    for (int i = 0; i < items.size(); i++) {
                        CrmOrderItem crm = new CrmOrderItem();
                        crm.setId(order.getId());
                        crm.setSapOrderId(sapOrderCode);
                        crm.setPosnr(items.get(i).getPosnr());
                        num = crmOrderDao.updateOrderDetail(crm);
                        if (num > 0) {
                            crm.setMatnr(items.get(i).getMatnr());
                            crm.setKwmeng(items.get(i).getKwmeng());
                            crm.setLgort(items.get(i).getLgort());
                            crm.setWerks(items.get(i).getWerks());
                            crmOrderDao.updateProductStock(crm);

                            OldProduct old = new OldProduct();
                            old.setKunnr(kunnr);
                            old.setMaterialnumber(items.get(i).getMatnr());
                            old.setLastnum(Long.valueOf(items.get(i).getKwmeng()));
                            crmOrderDao.updateOldProductStock(old);

                        }

                        if (null != items.get(i).getCreditId() && !"".equals(items.get(i).getCreditId())) {

                            Credit credit = new Credit();
                            CreditLog log = new CreditLog();
                            credit.setCreditid(Long.valueOf(items.get(i).getCreditId()));

                            List<Credit> list = crmOrderDao.getCreditList(credit);
                            if ("1020".equals(list.get(0).getCreditRange())) {

                                List<ReceivePay> receiveList = crmOrderDao.getNewReceivePayId(kunnr);
                                if (null != receiveList && receiveList.size() > 0) {
                                    double totalPrice = Double.valueOf(items.get(i).getKwmeng())
                                            * items.get(i).getOutPrice();
                                    double nextPrice = 0;
                                    for (int j = 0; j < receiveList.size(); j++) {
                                        totalPrice = totalPrice - receiveList.get(j).getSyMoney();

                                        if (totalPrice < 0) {
                                            ReceivePay receive = new ReceivePay();
                                            receive.setId(receiveList.get(j).getId());
                                            if (j == 0) {
                                                receive.setSyMoney(Double.valueOf(items.get(i).getKwmeng())
                                                        * items.get(i).getOutPrice());
                                            } else {
                                                receive.setSyMoney(nextPrice);
                                            }
                                            crmOrderDao.updateReceivePay(receive);

                                            PaymentDetailRecords pdr = new PaymentDetailRecords();
                                            pdr.setAccrualId(sapOrderCode + "");
                                            pdr.setKunnr(order.getKunag());
                                            pdr.setReceivableId(receiveList.get(j).getId() + "");
                                            if (j == 0) {
                                                pdr.setPayMoney(Double.valueOf(items.get(i).getKwmeng())
                                                        * items.get(i).getOutPrice());
                                            } else {
                                                pdr.setPayMoney(nextPrice);
                                            }
                                            pdr.setCreateDate(new Date());
                                            pdr.setModifyDate(new Date());
                                            pdr.setStatus("1");
                                            pdr.setAccType("1");
                                            crmOrderDao.saveRepaymentRecords(pdr);// 保存还款明细记录
                                            break;
                                        } else {
                                            ReceivePay receive = new ReceivePay();
                                            receive.setId(receiveList.get(j).getId());
                                            receive.setSyMoney(receiveList.get(j).getSyMoney());
                                            crmOrderDao.updateReceivePay(receive);

                                            PaymentDetailRecords pdr = new PaymentDetailRecords();
                                            pdr.setAccrualId(sapOrderCode + "");
                                            pdr.setKunnr(order.getKunag());
                                            pdr.setReceivableId(receiveList.get(j).getId() + "");
                                            pdr.setPayMoney(receiveList.get(j).getSyMoney());
                                            pdr.setCreateDate(new Date());
                                            pdr.setModifyDate(new Date());
                                            pdr.setStatus("1");
                                            pdr.setAccType("1");
                                            crmOrderDao.saveRepaymentRecords(pdr);// 保存还款明细记录
                                            nextPrice = totalPrice;
                                        }
                                    }
                                }
                            }
                            credit.setXyjy(list.get(0).getXyjy()
                                    - (Double.valueOf(items.get(i).getKwmeng()) * items.get(i).getOutPrice()));
                            credit.setXyTake(list.get(0).getXyTake()
                                    - (Double.valueOf(items.get(i).getKwmeng()) * items.get(i).getOutPrice()));

                            int s = crmOrderDao.updateCredit(credit);
                            if (s > 0) {
                                log.setCreditId(items.get(i).getCreditId());
                                log.setCreditxyjy(credit.getXyjy());
                                log.setKunnr(list.get(0).getKunnr());
                                log.setName1(list.get(0).getName1());
                                log.setCreditdbekr(
                                        (Double.valueOf(items.get(i).getKwmeng()) * items.get(i).getOutPrice()));
                                log.setCreditlogtype("C");
                                crmOrderDao.addCreditLog(log);
                            }
                        }
                    }
                    booleanResult.setResult(true);
                    booleanResult.setCode("销售订单生成成功！");
                } catch (Exception e) {
                    booleanResult.setResult(false);
                    booleanResult.setCode("error");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public String crmOrderSoDelete(CrmOrderItem items) {
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZCRM_SO_DELETE_ROW");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("订单拒绝SAP/ZCRM_SO_DELETE_ROW");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            // 传入信息（头）
            function.getImportParameterList().setValue("SALESDOCUMENT", items.getSapOrderId()); // 销售订单号 必填

            JCoStructure heads = function.getImportParameterList().getStructure("HEAD");// CRM抬头
            heads.setValue("MANDT", "");
            heads.setValue("ID", "1");// SAP不允许为空
            // 传入信息（明细）
            JCoTable item = function.getTableParameterList().getTable("ITEM");
            item.appendRow(); // 放入表内
            item.setValue("POSNR", items.getPosnr());// 项目号 必填

            // 传入参数暂时为空
            function.execute(dest);

            // 接受返回信息
            function.getTableParameterList().getTable("T_RETURN");
            String strMsg = function.getExportParameterList().getValue("STR_MSG").toString();// 参考信息
            String eokFlag = function.getExportParameterList().getValue("E_OK_FLAG").toString();// 修改标志 'X'为成功，其他错误
            System.out.println(strMsg + "  " + eokFlag);
            if ("X".equals(eokFlag)) {
                String price = item.getValue("PRICE").toString();// 金额
                String num = item.getValue("KWMENG").toString();// 数量

                // 返回剩余金额
                List<CrmOrderItem> itemList = crmOrderDao.getUseRepaymentList(items);
                if (null != price && !"".equals(price)) {
                    if (Double.valueOf(price) > 0) {
                        double itemPrice = Double.parseDouble(price);
                        if (null != itemList && itemList.size() > 0) {
                            if ("1020".equals(itemList.get(0).getCreditRange())) {
                                // 现款现货
                                PaymentDetailRecords payment = new PaymentDetailRecords();
                                payment.setAccrualId(items.getSapOrderId());
                                List<PaymentDetailRecords> payList = crmOrderDao.getPayMentList(payment);
                                double syPrice = 0;
                                for (int i = 0; i < payList.size(); i++) {
                                    if (payList.size() == 1) {
                                        if (payList.get(i).getPayMoney() >= itemPrice) {
                                            ReceivePay receive = new ReceivePay();
                                            receive.setSyMoney(-itemPrice);
                                            receive.setId(Long.valueOf(payList.get(i).getReceivableId()));
                                            crmOrderDao.updateReceivePay(receive);

                                            PaymentDetailRecords pdr = new PaymentDetailRecords();
                                            pdr.setAccrualId(items.getSapOrderId() + "");
                                            pdr.setReceivableId(payList.get(i).getReceivableId() + "");
                                            pdr.setKunnr(items.getKunnr());
                                            pdr.setPayMoney(-itemPrice);
                                            pdr.setCreateDate(new Date());
                                            pdr.setModifyDate(new Date());
                                            pdr.setStatus("1");
                                            pdr.setAccType("1");
                                            crmOrderDao.saveRepaymentRecords(pdr);// 保存还款明细记录

                                        }
                                    } else {
                                        syPrice = itemPrice;
                                        if (payList.get(i).getPayMoney() >= syPrice) {
                                            ReceivePay receive = new ReceivePay();
                                            receive.setId(Long.valueOf(payList.get(i).getReceivableId()));

                                            receive.setSyMoney(-syPrice);
                                            crmOrderDao.updateReceivePay(receive);

                                            PaymentDetailRecords pdr = new PaymentDetailRecords();
                                            pdr.setAccrualId(items.getSapOrderId() + "");
                                            pdr.setReceivableId(payList.get(i).getReceivableId() + "");
                                            pdr.setKunnr(items.getKunnr());

                                            pdr.setPayMoney(-syPrice);
                                            pdr.setCreateDate(new Date());
                                            pdr.setModifyDate(new Date());
                                            pdr.setStatus("1");
                                            pdr.setAccType("1");
                                            crmOrderDao.saveRepaymentRecords(pdr);// 保存还款明细记录

                                        } else {
                                            ReceivePay receive = new ReceivePay();
                                            receive.setSyMoney(-payList.get(i).getPayMoney());
                                            receive.setId(Long.valueOf(payList.get(i).getReceivableId()));
                                            crmOrderDao.updateReceivePay(receive);
                                            itemPrice = itemPrice - payList.get(i).getPayMoney();

                                            PaymentDetailRecords pdr = new PaymentDetailRecords();
                                            pdr.setAccrualId(items.getSapOrderId() + "");
                                            pdr.setReceivableId(payList.get(i).getReceivableId() + "");
                                            pdr.setKunnr(items.getKunnr());

                                            pdr.setPayMoney(-payList.get(i).getPayMoney());
                                            pdr.setCreateDate(new Date());
                                            pdr.setModifyDate(new Date());
                                            pdr.setStatus("1");
                                            pdr.setAccType("1");
                                            crmOrderDao.saveRepaymentRecords(pdr);// 保存还款明细记录
                                        }
                                    }
                                }
                                Credit credit = new Credit();
                                credit.setCreditid(Long.valueOf(itemList.get(0).getCreditId()));
                                List<Credit> creList = crmOrderDao.getCreditList(credit);
                                credit.setXyjy(creList.get(0).getXyjy() + itemPrice);
                                crmOrderDao.updateCredit(credit);

                            } else {
                                // 其他信用
                                Credit credit = new Credit();
                                credit.setCreditid(Long.valueOf(itemList.get(0).getCreditId()));
                                List<Credit> creList = crmOrderDao.getCreditList(credit);
                                credit.setXyjy(creList.get(0).getXyjy() + itemPrice);
                                credit.setXyTake(creList.get(0).getXyTake());
                                crmOrderDao.updateCredit(credit);
                            }

                            if ("1".equals(itemList.get(0).getOrderType())) {
                                CrmOrderItem crmitem = new CrmOrderItem();
                                crmitem.setPromotionId(itemList.get(0).getPromotionId());
                                crmitem.setPromoDetailId(itemList.get(0).getPromoDetailId());
                                crmitem.setMatnr(itemList.get(0).getMatnr());

                                String limitNum = crmOrderDao.getLimitedNumByOrder(crmitem);
                                if ("" != limitNum && !"".equals(limitNum)) {
                                    crmitem.setKwmeng(num);
                                    crmOrderDao.updateLimitedNumByOrder(crmitem);
                                }

                            }

                        }

                    }

                }

                crmOrderDao.updateOrderDetailStatus(items);

            }
            return strMsg;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }

    public static void main(String[] args) {
        CrmOrderToSap order = new CrmOrderToSap();
        order.setId("200212");// CRM_ID 200212
        order.setAuart("ZTS1"); // 销售凭证类型
        order.setVkorg("2000"); // 销售组织
        order.setVtweg("10"); // 分销渠道
        order.setSpart("02"); // 产品组
        order.setVkbur("1001"); // 销售部门 中珠市场部
        order.setBzirk("100001"); // 销售地区 华南
        order.setAudat("20150723"); // 凭证日期
        order.setBstkd("HN3424"); // 客户采购订单号 HN3424
        order.setKunag("1000447"); // 售达方
        order.setKunnr("1003886"); // 送达方
        order.setKunrg("1000447"); // 付款方
        order.setKunre("1000447"); // 开票方
        order.setAbrvw("3"); // 使用标示 1增票 2普票 3不开票
        order.setPernr("10004"); // 销售雇员
        order.setPrsdt("20150723"); // 定价日期
        order.setText(""); // 备注

        CrmOrderItem item = new CrmOrderItem();
        item.setPosnr("10"); // 项目号
        item.setMatnr("10900000244"); // 物料号
        item.setKwmeng("2"); // 数量
        item.setVrkme("CAR"); // 单位
        item.setCharg(""); // 批号
        item.setWerks("2000"); // 工厂
        item.setLgort("9012"); // 库存地
        item.setFather(""); // 父项目
        item.setPstyv(""); // 销售凭证项目类型 当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
        item.setPrice(""); // 折扣价 当为促销品是值为负数=出货价-常规价
        item.setWaerk("CNY"); // 货币 默认传入CNY
        item.setCreditId(""); // CRM信用号
        item.setText("需要最新的生产日期"); // CRM行项目文档
        item.setPromotionId(""); // 促销大方案号
        item.setPromoDetailId(""); // 促销子方案号

        CrmOrderItem item2 = new CrmOrderItem();

        item2.setPosnr("20"); // 项目号
        item2.setMatnr("10900000292"); // 物料号
        item2.setKwmeng("10"); // 数量
        item2.setVrkme("CAR"); // 单位
        item2.setCharg(""); // 批号
        item2.setWerks("2000"); // 工厂
        item2.setLgort("9012"); // 库存地
        item2.setFather(""); // 父项目
        item2.setPstyv(""); // 销售凭证项目类型 当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
        item2.setPrice(""); // 折扣价 当为促销品是值为负数=出货价-常规价
        item2.setWaerk("CNY"); // 货币 默认传入CNY
        item2.setCreditId(""); // CRM信用号
        item2.setText(""); // CRM行项目文档
        item2.setPromotionId(""); // 促销大方案号
        item2.setPromoDetailId(""); // 促销子方案号
        List<CrmOrderItem> items = new ArrayList<CrmOrderItem>();
        items.add(item);
        items.add(item2);

        CrmOrderToSAPServiceImpl sap = new CrmOrderToSAPServiceImpl();
        sap.crmOderToSap(order, items);

    }

    @Override
    public List<CrmOrderToSap> getCrmOrderTotal(CrmOrderToSap order) {
        try {
            return crmOrderDao.getCrmOrderTotal(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CrmOrderItem> getCrmOrderItems(CrmOrderToSap order) {
        try {
            return crmOrderDao.getCrmOrderItems(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateOrderStatus(CrmOrderToSap order) {
        try {
            return crmOrderDao.updateOrderStatus(order);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CrmOrderItem> getDeleteCrmOrder(CrmOrderToSap order) {
        try {
            return crmOrderDao.getDeleteCrmOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
