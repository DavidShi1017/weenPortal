package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.OrderToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.OrderDetail;
import com.jingtong.platform.sap.pojo.OrderToSap;
import com.jingtong.platform.sap.service.OrderToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class OrderToSapServiceImpl extends BasicService implements OrderToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private OrderToSapDao orderToSapDao;
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

    public OrderToSapDao getOrderToSapDao() {
        return orderToSapDao;
    }

    public void setOrderToSapDao(OrderToSapDao orderToSapDao) {
        this.orderToSapDao = orderToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    public static void main(String[] args) {
    	OrderToSapServiceImpl orderToSapServiceImpl= new OrderToSapServiceImpl();
    	orderToSapServiceImpl.connect("SAP");
    	orderToSapServiceImpl.dest = orderToSapServiceImpl.getDest("SAP");
    	try {
			JCoFunction function = orderToSapServiceImpl.dest.getRepository().getFunction("ZSDFU007");
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public String orderToSap(OrderToSap order, List<OrderDetail> orderDetail, SampleOrder sorder) {
        // 调用sap接口获取报价单
        String resultMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU007");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("样品订单传入SAP/ZSDFU007");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (order != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", order.getSale_group());
                head.setValue("VTWEG", order.getDistri_channel());
                head.setValue("SPART", order.getProduct_group());
                head.setValue("AUART", order.getType_id());
                System.out.println(order.getType_id());
                head.setValue("KUNWE", "900002");
                head.setValue("KUNRG", order.getPayer_to());
                head.setValue("KUNRE", order.getBilling_to());
                head.setValue("KUNAG", order.getSale_to());
                head.setValue("WAERK", order.getCurrency_code());
                head.setValue("BSTKD", order.getOrder_id());
                head.setValue("TXTHL",
                        order.getContact_name() + ';' + order.getContact_tel() + ';' + order.getStreet() + ';'
                                + order.getCountry() + ';' + order.getCity() + ';' + order.getZip() + ';'
                                + order.getCompany());
            }

            if (orderDetail != null) {
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值
                for (OrderDetail it : orderDetail) {
                    item.appendRow(); // 放入表内

                    item.setValue("POSNR", it.getRow_no());
                    item.setValue("MATNR", it.getMaterial_id());
                    item.setValue("KWMENG", it.getOrder_QTY());
                    item.setValue("VRKME", it.getSale_unit());
                    item.setValue("TXTPL", it.getRemark());
                    item.setValue("ETDAT", it.getDelivery_dateStr());
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
                    orderToSapDao.updateOrderState(sorder);
                    resultMessage = "Create Order" + vbeln + " Success!";

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("样品订单传入SAP/ZSDFU007");
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
                    log.setInterfaceName("样品订单传入SAP/ZSDFU007");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！");
                    log.setLogDesc("失败！" + message);
                    resultMessage = "Failed！";
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        resultMessage = message + "Please contact the administrator";
                        e1.printStackTrace();
                    }
                }
            } else {
                String message = resultTbl.getString("ZMESG");
                String vbeln = resultTbl.getString("VBELN");

                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("样品订单传入SAP/ZSDFU007");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！" + message);
                log.setLogInfo("失败! " + vbeln);
                try {
                    this.logInfoDao.addLogInfo(log);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                resultMessage = message;
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("样品订单传入SAP/ZSDFU007");
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
    public List<OrderToSap> getOrderTotal(OrderToSap order) {
        try {
            return orderToSapDao.getOrderTotal(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetail(OrderToSap order) {
        try {
            return orderToSapDao.getOrderDetail(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSampleStockFromSAP(String material_id) {
        boolean errorFlag = false;
        String message = "";
        try {
            // 创建连接
            this.connect("SAP");
            dest = getDest("SAP");
            // 链接接口
            /*
             * |---------|---------| | PARAMETERS 'TABLES' |---------|---------|
             * |T_ZSDS016|T_ZSDS017| |---------|---------| | | | |---------|---------|
             * |T_ZSDS016|T_ZSDS017| |---------|---------|
             */
            JCoFunction function = dest.getRepository().getFunction("ZSDFU026");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("样品库存接口/ZSDFU026");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                errorFlag = true;
                return "Connect SAP Failed!";
            }

            // 传参
            JCoTable item = function.getTableParameterList().getTable("T_ZSDS016");
            if (item != null) {
                item.appendRow(); // 放入表内
                item.setValue("MATNR", material_id);
                // item.setValue("WERKS", "HK11");
                // CN11
                item.setValue("WERKS", "CN11");
            }

            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ZSDS017");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                int i = 0;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                }
                if (i == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("物料主数据/ZSDFU002");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("成功！");
                    log.setLogInfo("成功");
                    resultTbl.setRow(0);
                    String stock = resultTbl.getString("LABST");
                    message = stock;
                    this.logInfoDao.addLogInfo(log);
                }
            } else {
                // 当返回为空记录时，我们当做库存数为0处理
                message = "0";
            }
        } catch (Error e) {
            message = "Can not connect to SAP !";
        } catch (JCoException e) {
            message = "Can not connect to SAP !";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Can not connect to SAP !";
        }
        return message;
    }
}
