package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.SampleOrderToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.SampleOrderDetail;
import com.jingtong.platform.sap.pojo.SampleOrderToSap;
import com.jingtong.platform.sap.service.SampleOrderToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class SampleOrderToSapServiceImpl extends BasicService implements SampleOrderToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private SampleOrderToSapDao sampleOrderToSapDao;
    private TransactionTemplate transactionTemplate;

    public JCoDestination getDest() {
        return dest;
    }

    public void setDest(JCoDestination dest) {
        this.dest = dest;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public SampleOrderToSapDao getSampleOrderToSapDao() {
        return sampleOrderToSapDao;
    }

    public void setSampleOrderToSapDao(SampleOrderToSapDao sampleOrderToSapDao) {
        this.sampleOrderToSapDao = sampleOrderToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String sampleOrderToSap(SampleOrderToSap sampleOrder, List<SampleOrderDetail> sampleOrderDetail,
            StarderOrder sorder) {
        // 调用sap接口获取报价单
        String resultMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU006");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("标准订单传入SAP/ZSDFU006");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            if (sampleOrder != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", sampleOrder.getSale_group());
                head.setValue("VTWEG", sampleOrder.getDistri_channel());
                head.setValue("SPART", sampleOrder.getProduct_group());
                head.setValue("AUART", sampleOrder.getType_id());
                head.setValue("KUNWE", sampleOrder.getShip_to());
                head.setValue("KUNRG", sampleOrder.getPayer_to());
                head.setValue("KUNRE", sampleOrder.getBilling_to());
                head.setValue("KUNAG", sampleOrder.getSale_to());
                head.setValue("WAERK", sampleOrder.getCurrency_code());
                head.setValue("BSTKD", sampleOrder.getOrder_id());
            }
            if (sampleOrderDetail != null) {
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值
                for (SampleOrderDetail it : sampleOrderDetail) {
                    item.appendRow();

                    item.setValue("POSNR", it.getRow_no());
                    item.setValue("MATNR", it.getMaterial_id());
                    item.setValue("KWMENG", it.getOrder_QTY());
                    item.setValue("VRKME", it.getSale_unit());
                    item.setValue("TXTPL", it.getRemark());
                    item.setValue("ETDAT", it.getDelivery_dateStr());
                    item.setValue("POSEX", it.getPo_item());
                }
            }
            function.execute(dest);
            // 获取接口结果输出表

            JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
            int rows = resultTbl.getNumRows();

            if (rows > 0) {
                String flag = resultTbl.getString("ZFLAG");
                String message = resultTbl.getString("ZMESG");
                String vbeln = resultTbl.getString("VBELN");
                if (vbeln != null && !"".equals(vbeln)) {
                    sorder.setSap_order_id(vbeln);
                    sampleOrderToSapDao.updateOrderState(sorder);
                    resultMessage = "Create Order" + vbeln + " Success!";

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("标准订单传入SAP/ZSDFU006");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("单号: " + vbeln + "," + flag);

                    log.setLogInfo(message);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("标准订单传入SAP/ZSDFU006");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！");
                    log.setLogInfo("失败! ");
                    resultMessage = message + "Please contact the administrator";
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        resultMessage = message;
                        e1.printStackTrace();
                    }
                }
            } else {
                String message = resultTbl.getString("ZMESG");

                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("标准订单传入SAP/ZSDFU006");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("失败! ");
                resultMessage = message + "Please contact the administrator";
                try {
                    this.logInfoDao.addLogInfo(log);
                } catch (Exception e1) {
                    resultMessage = message;
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "Failed";
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("标准订单传入SAP/ZSDFU006");
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
        return resultMessage + "";
    }

    @Override
    public List<SampleOrderToSap> getSampleOrderTotal(SampleOrderToSap sampleOrder) {
        try {
            return sampleOrderToSapDao.getSampleOrderTotal(sampleOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SampleOrderDetail> getSampleOrderDetail(SampleOrderToSap sampleOrder) {
        try {
            return sampleOrderToSapDao.getSampleOrderDetail(sampleOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
