package com.jingtong.platform.sap.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.SAPDao;
import com.jingtong.platform.sap.dao.ShopDeliveryDao;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.pojo.Warehouses;
import com.jingtong.platform.sap.service.SAPService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class SAPServiceImpl extends BasicService implements SAPService {
    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private SAPDao sapDao;
    // private IDictDao dictDao;
    private ShopDeliveryDao shopDeliveryDao;

    public ShopDeliveryDao getShopDeliveryDao() {
        return shopDeliveryDao;
    }

    public void setShopDeliveryDao(ShopDeliveryDao shopDeliveryDao) {
        this.shopDeliveryDao = shopDeliveryDao;
    }

    public SAPDao getSapDao() {
        return sapDao;
    }

    public void setSapDao(SAPDao sapDao) {
        this.sapDao = sapDao;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    /**
     * 《产品库存可用量》
     * 
     * @param pmatnr String 物料编码(必填)
     * @param pwerks String 工厂编码(必填)
     * @param plgort String 库位编码
     * @return
     */
    @Override
    public ProductStock getMaterialInventoryFromSap(String smatnr, String swerks, String slgort) {
        System.out.println("-----开始调用产品库存可用量接口----------");
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("Z_RFC_GET_MATERIAL_INVENTORY");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("产品库存可用量/Z_RFC_GET_MATERIAL_INVENTORY");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            // 获取接口传入参数表
            if (smatnr.trim().length() > 0) {
                JCoTable item = function.getTableParameterList().getTable("S_MATNR");// 读取高地位表
                item.appendRow(); // 放入表内
                item.setValue("SIGN", "I");
                item.setValue("OPTION", "EQ");
                item.setValue("LOW", smatnr);
            }
            if (swerks.trim().length() > 0) {
                JCoTable item = function.getTableParameterList().getTable("S_WERKS");// 读取高地位表
                item.appendRow(); // 放入表内
                item.setValue("SIGN", "I");
                item.setValue("OPTION", "EQ");
                item.setValue("LOW", swerks);
            }
            if (slgort.trim().length() > 0) {
                JCoTable item = function.getTableParameterList().getTable("S_LGORT");// 读取高地位表
                item.appendRow(); // 放入表内
                item.setValue("SIGN", "I");
                item.setValue("OPTION", "EQ");
                item.setValue("LOW", slgort);
            }
            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_TAB");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                resultTbl.setRow(0);
                ProductStock ps = new ProductStock();
                ps.setWerks(resultTbl.getString("WERKS"));
                ps.setLgort(resultTbl.getString("LGORT"));
                ps.setMatnr(resultTbl.getString("MATNR"));
                ps.setUnit(resultTbl.getString("MEINS"));
                ps.setStocknum(resultTbl.getFloat("MENGE"));
                ps.setBigme(resultTbl.getString("BIGME"));
                ps.setBigmg(resultTbl.getFloat("BIGMG"));
                ps.setCreateuser("admin");
                ps.setModifyuser("admin");
                return ps;
            }
        } catch (Exception e) {
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("产品库存可用量/Z_RFC_GET_MATERIAL_INVENTORY");
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
        return null;
    }

    @Override
    public void getMaterialInventory() {
        // 调用sap接口获取库存和物料
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("Z_RFC_GET_MATERIAL_INVENTORY");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("产品库存可用量/Z_RFC_GET_MATERIAL_INVENTORY");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            // 获取所有物料
            List<Material> mats = this.sapDao.getMaterials();
            // 获取所有工厂、库位
            List<Warehouses> whs = this.sapDao.getWarehouses();
            // 获取接口传入参数表
            if (whs != null) {
                for (Warehouses sh : whs) {
                    if (sh != null) {
                        if (sh.getWarehouseCode() != null && sh.getWarehouseCode().length() > 0) {
                            JCoTable item = function.getTableParameterList().getTable("S_WERKS");
                            item.appendRow();
                            item.setValue("SIGN", "I");
                            item.setValue("OPTION", "EQ");
                            item.setValue("LOW", sh.getWarehouseCode());
                        }
                        if (sh.getLocationCode() != null && sh.getLocationCode().length() > 0) {
                            JCoTable item = function.getTableParameterList().getTable("S_LGORT");
                            item.appendRow();
                            item.setValue("SIGN", "I");
                            item.setValue("OPTION", "EQ");
                            item.setValue("LOW", sh.getLocationCode());
                        }
                    }
                }
                if (mats != null && (!mats.isEmpty())) {
                    JCoTable item = function.getTableParameterList().getTable("S_MATNR");// 读取高地位表
                    for (int i = 0; i < mats.size(); i++) {
                        item.appendRow();
                        item.setValue("SIGN", "I");
                        item.setValue("OPTION", "EQ");
                    }
                }
                function.execute(dest);
                // 获取接口结果输出表
                JCoTable resultTbl = function.getTableParameterList().getTable("OT_TAB");
                int rows = resultTbl.getNumRows();
                if (rows > 0) {
                    for (int i = 0; i < rows; i++) {
                        resultTbl.setRow(i);
                        ProductStock ps = new ProductStock();
                        ps.setWerks(resultTbl.getString("WERKS"));
                        ps.setLgort(resultTbl.getString("LGORT"));
                        String matnr = resultTbl.getString("MATNR");
                        if ("PG_001".equals(matnr)) {
                            ps.setMatnr(matnr);
                        } else {
                            ps.setMatnr((Long.parseLong(matnr) + "").trim());
                        }
                        ps.setUnit(resultTbl.getString("MEINS"));
                        ps.setStocknum(resultTbl.getFloat("MENGE"));
                        ps.setBigme(resultTbl.getString("BIGME"));
                        ps.setBigmg(resultTbl.getFloat("BIGMG"));
                        ps.setCreateuser("admin");
                        ps.setModifyuser("admin");
                        int result = this.sapDao.getProductStock(ps);
                        if (result > 0) {
                            this.sapDao.updateProductStock(ps);
                        } else {
                            this.sapDao.insertProductStock(ps);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("产品库存可用量/Z_RFC_GET_MATERIAL_INVENTORY");
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
    }

    @Override
    public boolean autoGetCusPlusFromSap() {
        return this.getCusPlusFromSap(null, null, null);
    }

    /***
     * 自动创建客户回款会计凭证
     */
    @Override
    public CuspayAccdoc getCuspayAccdocFromSAP(CuspayAccdoc doc) {
        // 调用sap接口获取库存和物料
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZBDG_CREATE_CUSPAY_ACCDOC");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("自动创建客户回款会计凭证/ZBDG_CREATE_CUSPAY_ACCDOC");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            // 传入的参数
            if (doc != null) {
                if (doc.getP_bukrs() != null && doc.getP_bukrs().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_BUKRS", doc.getP_bukrs());
                }
                if (doc.getP_kunnr() != null && doc.getP_kunnr().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_KUNNR", doc.getP_kunnr());
                }
                if (doc.getP_dmbtr() >= 0) {
                    function.getImportParameterList().setValue("P_DMBTR", doc.getP_dmbtr());
                }
                if (doc.getP_belnr_hk() != null && doc.getP_belnr_hk().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_BELNR_HK", doc.getP_belnr_hk());
                }
                if (doc.getP_gjahr_hk() != null && doc.getP_gjahr_hk().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_GJAHR_HK", doc.getP_gjahr_hk());
                }
                if (doc.getP_bukrs_hk() != null && doc.getP_bukrs_hk().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_BUKRS_HK", doc.getP_bukrs_hk());
                }
                if (doc.getP_usnam() != null && doc.getP_usnam().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_USNAM", doc.getP_usnam());
                }
                if (doc.getP_xref1() != null && doc.getP_xref1().trim().length() > 0) {
                    function.getImportParameterList().setValue("P_XREF1", doc.getP_xref1());
                }
                function.execute(dest);
                // 获取接口输出值
                String retcode = function.getExportParameterList().getValue("RETCODE").toString();// sap返回信息
                String retmsg = function.getExportParameterList().getValue("RETMSG").toString();// sap返回信息
                String l_belnr = function.getExportParameterList().getValue("L_BELNR").toString();// 回款凭证号
                doc.setRetcode(retcode);
                doc.setRetmsg(retmsg);
                doc.setL_belnr(l_belnr);
                return doc;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("自动创建客户回款会计凭证/ZBDG_CREATE_CUSPAY_ACCDOC");
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
        return null;
    }

    @Override
    public List<NoClearMoney> getweiqingMoneyFromSAP(String p_bukrs, String p_gjahr) {
        List<NoClearMoney> noClearMoneyList = new ArrayList<NoClearMoney>();
        // 调用sap接口获取库存和物料
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZBDG_GET_WEIQING_MONEY");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("获取未清现金凭证清单/ZBDG_GET_WEIQING_MONEY");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (p_bukrs != null && p_bukrs.trim().length() > 0) {
                function.getImportParameterList().setValue("P_BUKRS", p_bukrs);
            }
            if (p_gjahr != null && p_gjahr.trim().length() > 0) {
                function.getImportParameterList().setValue("P_GJAHR", p_gjahr);
            }
            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_BSIS");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                for (int i = 0; i < rows; i++) {
                    resultTbl.setRow(i);
                    NoClearMoney mon = new NoClearMoney();
                    mon.setBukrs(resultTbl.getString("BUKRS"));
                    mon.setGjahr(resultTbl.getString("GJAHR"));
                    mon.setBelnr(resultTbl.getString("BELNR"));
                    mon.setBuzei(resultTbl.getString("BUZEI"));
                    mon.setHkont(resultTbl.getString("HKONT"));
                    mon.setBudat(resultTbl.getDate("BUDAT"));
                    mon.setZuonr(resultTbl.getString("ZUONR"));
                    mon.setDmbtr(resultTbl.getDouble("DMBTR"));
                    mon.setRemark(resultTbl.getString("SGTXT")); // 带扩展

                    int result = this.sapDao.getNoClearMoney(mon);
                    if (result > 0) {
                        this.sapDao.updateNoClearMoney(mon);
                    } else {
                        this.sapDao.insertNoClearMoney(mon);
                    }

                    noClearMoneyList.add(mon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("获取未清现金凭证清单/ZBDG_GET_WEIQING_MONEY");
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

        return noClearMoneyList;
    }

    public static void main(String[] args) {
    }

    @Override
    public NoClearMoney getReceiveCode(String belnr) throws Exception {
        return this.sapDao.getReceiveCode(belnr);
    }

    @Override
    public long checkExistReceivePay(ReceivePay receivePay) {
        return this.sapDao.checkExistReceivePay(receivePay);
    }

    @Override
    public long addReceivePay(ReceivePay receivePay) throws Exception {
        // TODO Auto-generated method stub
        return this.sapDao.addReceivePay(receivePay);
    }

    @Override
    public int updateCreditByReceive(String kunnrId) {
        try {
            return sapDao.updateCreditByReceive(kunnrId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<String> getClearMoneyList() {
        try {
            return sapDao.getClearMoneyList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void getReceivePayFromSap() {
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZBDG_GET_WEIQING_YINGSHOU");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("获取市场费用核销/ZBDG_GET_WEIQING_YINGSHOU");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 获取接口传入参数表
            // 凭证日期范围
            JCoTable item = function.getTableParameterList().getTable("S_BUDAT");// 凭证日期范围
            item.appendRow();
            item.setValue("SIGN", "I");
            item.setValue("OPTION", "EQ");
            item.setValue("LOW", "201509012");

            // 客户范围
            JCoTable kunnrs = function.getTableParameterList().getTable("S_KUNNR");// 交货单号
            kunnrs.appendRow(); // 放入表内
            kunnrs.setValue("SIGN", "I");
            kunnrs.setValue("OPTION", "EQ");
            kunnrs.setValue("LOW", "1000447");
            function.execute(dest);
            // 获取接口结果输出表
            function.getExportParameterList().getValue("OT_BSID");// 未清凭证清单
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_BSIS");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                for (int i = 0; i < rows; i++) {
                    resultTbl.setRow(i);
                    System.out.println(resultTbl.getString("BUKRS"));// 公司
                    System.out.println(resultTbl.getString("KUNNR"));// 客户编号
                    System.out.println(resultTbl.getString("GJAHR"));// 年度
                    System.out.println(resultTbl.getString("BELNR"));// 凭证号
                    System.out.println(resultTbl.getString("BUDAT"));// 凭证日期
                    System.out.println(resultTbl.getString("XBLNR"));// 参考凭证号
                    System.out.println(resultTbl.getString("SGTXT"));// 文本
                    System.out.println(resultTbl.getString("DMBTR"));// 金额
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("获取市场费用核销/ZBDG_GET_WEIQING_YINGSHOU");
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
    }

    @Override
    public void getInitialCredit() {
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("Z_BDC_MODIFY_FD32");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("CRM同步信用初始总额到SAP/Z_BDC_MODIFY_FD32");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            // 传入值
            JCoTable item = function.getTableParameterList().getTable("LT_ZFD32");//
            item.appendRow();
            item.setValue("KUNNR", "1000447");// 客户编码
            item.setValue("KKBER", "6666");// 信用控制范围
            item.setValue("KLIMK", 100);// 信贷总限额
            // 客户范围
            function.execute(dest);
            // 获取接口结果输出表
            String retmsg = function.getExportParameterList().getValue("RETMSG").toString();// sap返回信息
                                                                                            // 输出为空就是成功，如果错误的话会反馈错误信息
            System.out.println("" + " " + retmsg);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("CRM同步信用初始总额到SAP/Z_BDC_MODIFY_FD32");
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

    }

    @Override
    public void getZSVoucher() {
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZBDG_GET_WEIQING_YINGSHOU");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("CRM同步贷方凭/ZBDG_GET_WEIQING_YINGSHOU");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 获取接口传入参数表
            JCoTable item = function.getTableParameterList().getTable("S_BUDAT");// 凭证日期范围
            item.appendRow();
            item.setValue("SIGN", "I");
            item.setValue("OPTION", "EQ");
            item.setValue("LOW", "20140912");

            // 凭证类型：X为贷方凭证（费用、退货、商票），空位借方凭证（惩罚）
            function.getImportParameterList().setValue("P_PLUS", "X");

            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_BSID");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                for (int i = 0; i < rows; i++) {
                    resultTbl.setRow(i);
                    System.out.println(resultTbl.getString("BUKRS"));// 公司
                    System.out.println(resultTbl.getString("KUNNR"));// 客户编号
                    System.out.println(resultTbl.getString("GJAHR"));// 年度
                    System.out.println(resultTbl.getString("BELNR"));// 凭证号
                    System.out.println(resultTbl.getString("BUDAT"));// 凭证日期
                    System.out.println(resultTbl.getString("XBLNR"));// 参考凭证号
                    System.out.println(resultTbl.getString("SGTXT"));// 文本
                    System.out.println(resultTbl.getString("DMBTR"));// 金额
                    System.out.println(resultTbl.getString("BUZEI"));// 凭证行项目
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("CRM同步贷方凭/ZBDG_GET_WEIQING_YINGSHOU");
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
    }

    @Override
    public void getFSVoucher() {
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZBDG_GET_WEIQING_YINGSHOU");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("CRM同步贷方凭/ZBDG_GET_WEIQING_YINGSHOU");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 获取接口传入参数表
            JCoTable item = function.getTableParameterList().getTable("S_BUDAT");
            item.appendRow();
            item.setValue("SIGN", "I");
            item.setValue("OPTION", "EQ");
            item.setValue("LOW", "20140912");
            // 凭证类型：X为贷方凭证（费用、退货、商票），空位借方凭证（惩罚）
            function.getImportParameterList().setValue("P_PLUS", "");
            function.execute(dest);

            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_BSID");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                for (int i = 0; i < rows; i++) {
                    resultTbl.setRow(i);
                    System.out.println(resultTbl.getString("BUKRS"));// 公司
                    System.out.println(resultTbl.getString("KUNNR"));// 客户编号
                    System.out.println(resultTbl.getString("GJAHR"));// 年度
                    System.out.println(resultTbl.getString("BELNR"));// 凭证号
                    System.out.println(resultTbl.getString("BUDAT"));// 凭证日期
                    System.out.println(resultTbl.getString("XBLNR"));// 参考凭证号
                    System.out.println(resultTbl.getString("SGTXT"));// 文本
                    System.out.println(resultTbl.getString("DMBTR"));// 金额
                    System.out.println(resultTbl.getString("BUZEI"));// 凭证行项目
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("CRM同步贷方凭/ZBDG_GET_WEIQING_YINGSHOU");
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

    }

    @Override
    public boolean getCusPlusFromSap(Date begda, Date endda, String kunnr) {
        // TODO Auto-generated method stub
        return false;
    }
}
