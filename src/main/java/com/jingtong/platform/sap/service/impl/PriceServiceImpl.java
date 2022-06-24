package com.jingtong.platform.sap.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.PriceDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Price;
import com.jingtong.platform.sap.service.PriceService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class PriceServiceImpl extends BasicService implements PriceService {

    private JCoDestination dest;
    private PriceDao priceDao;
    private ILogInfoDao logInfoDao;
    private IMessageService messageService;

    private PriceService priceSerivce;
    private String customer_code;
    private String start_dateStr;
    private String end_dateStr;
    private String price_type;
    private String org_id;
    private String material_number;

    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }

    public String getMaterial_number() {
        return material_number;
    }

    public void setMaterial_number(String material_number) {
        this.material_number = material_number;
    }

    public JCoDestination getDest() {
        return dest;
    }

    public void setDest(JCoDestination dest) {
        this.dest = dest;
    }

    public PriceDao getPriceDao() {
        return priceDao;
    }

    public void setPriceDao(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public PriceService getPriceSerivce() {
        return priceSerivce;
    }

    public void setPriceSerivce(PriceService priceSerivce) {
        this.priceSerivce = priceSerivce;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getStart_dateStr() {
        return start_dateStr;
    }

    public void setStart_dateStr(String start_dateStr) {
        this.start_dateStr = start_dateStr;
    }

    public String getEnd_dateStr() {
        return end_dateStr;
    }

    public void setEnd_dateStr(String end_dateStr) {
        this.end_dateStr = end_dateStr;
    }

    public String getPrice_type() {
        return price_type;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    @Override
    public void getPriceListFromSAPForPB() {

        try {
            getPriceListFromSAP("ZPB1", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getPriceListFromSAPForMPP() {

        try {
            getPriceListFromSAP("ZMP1", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getPriceListFromSAP(String price_type, String material_number) {

        try {
            // 创建连接
            this.connect("SAP");

            dest = this.getDest("SAP");

            // 链接接口 接口名称
            JCoFunction function = dest.getRepository().getFunction("ZSDFU005");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("价格主数据/ZSDFU005");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            // 获取接口传入参数表
            JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");
            head.setValue("VKORG", "HK10");// 销售组织
            head.setValue("KSCHL", price_type);// 条件类型
            if (!"".equals(material_number) && material_number != null) {
                head.setValue("MATNR", "000000" + material_number);// 12NC
            }

            function.execute(dest);

            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ABLE");
            int rows = resultTbl.getNumRows();
            System.out.println(rows);
            if (rows > 0) {
                int i = 0;
                for (; i < rows; i++) {
                    System.out.println(i);
                    resultTbl.setRow(i);
                    Price price = new Price();
                    try {
                        price.setType(resultTbl.getString("KSCHL"));
                        price.setRemark(resultTbl.getString("VTEXT"));
                        price.setSalesOrg(resultTbl.getString("BZIRK"));
                        price.setMaterialNumber(resultTbl.getString("MATNR"));
                        price.setCustomerNumber(resultTbl.getString("KUNNR"));
                        price.setPrice(resultTbl.getString("KBETR"));
                        price.setRatioUnit(resultTbl.getString("KONWA"));
                        price.setPriceUnit(resultTbl.getString("KPEIN"));
                        price.setConditionUnit(resultTbl.getString("KMEIN"));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                        System.out.println(resultTbl.getString("MATNR"));
                        System.out.println(resultTbl.getString("KUNNR"));
                        try {
                            Date aa = sdf.parse(resultTbl.getString("DATAB") + " 00:00:00");
                            Date aba = sdf.parse(resultTbl.getString("DATBI") + " 23:59:59");
                            price.setStartDate(aa);
                            price.setEndDate(aba);
                        } catch (Exception e) {
                            ExceptionLog log = new ExceptionLog();
                            log.setInterfaceName("价格主数据/ZSDFU005");
                            log.setOperateUser("SAP");
                            log.setOperateTime(new Date());
                            log.setLogDesc("失败！" + "物料编号：" + resultTbl.getString("MATNR") + "----客户编号："
                                    + resultTbl.getString("KUNNR"));
                            log.setLogInfo(e.getMessage());
                            this.logInfoDao.addLogInfo(log);
                            // 插入邮件数据
                            String userNumber = "<br>&nbsp;&nbsp;同步 " + resultTbl.getString("KSCHL") + "失败 ， 失败原因：  "
                                    + e.getMessage() + " ! 对应的物料编号：" + resultTbl.getString("MATNR") + "和客户编号："
                                    + resultTbl.getString("KUNNR");
                            String contents = "Hi " + "admin" + userNumber + "<br>";
                            Message m = new Message();
                            m.setContent(contents);
                            m.setType("同步物料失败");
                            PropUtil pu = new PropUtil("/sap_error_msg.properties");
                            String email = pu.getProperty("price_sysn_fail_email");
                            m.setSendNumber(email);
                            this.logInfoDao.saveMessage(m);
                            continue;
                        }
                        price.setOfficeId(resultTbl.getString("VKBUR"));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(resultTbl.getString("MATNR"));
                        System.out.println(resultTbl.getString("KUNNR"));
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("价格主数据/ZSDFU005");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！" + "物料编号：" + resultTbl.getString("MATNR") + "----客户编号："
                                + resultTbl.getString("KUNNR"));
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        // 插入邮件数据
                        String userNumber = "<br>&nbsp;&nbsp;同步 " + resultTbl.getString("KSCHL") + "失败 ， 失败原因：  "
                                + e.getMessage() + " ! 对应的物料编号：" + resultTbl.getString("MATNR") + "和客户编号："
                                + resultTbl.getString("KUNNR");
                        String contents = "Hi " + "admin" + userNumber + "<br>";
                        Message m = new Message();
                        m.setContent(contents);
                        m.setType("同步物料失败");
                        PropUtil pu = new PropUtil("/sap_error_msg.properties");
                        String email = pu.getProperty("price_sysn_fail_email");
                        m.setSendNumber(email);
                        this.logInfoDao.saveMessage(m);
                        continue;
                    }

                    int count = this.priceDao.getPriceList(price);
                    try {
                        if (count == 0) {
                            this.priceDao.createPrice(price);
                        } else if (count > 0) {
                            priceDao.deletePrice(price);
                            this.priceDao.createPrice(price);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("价格主数据/ZSDFU005");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        continue;
                    }
                }

                // 执行成功记录成功日志时间（中间有失败也会执行这句话）
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("价格主数据/ZSDFU005");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("成功！");
                log.setLogInfo("成功");
                this.logInfoDao.addLogInfo(log);

                // 删除没有用的价格日期大于当天的10个小时前的数据 单物料同步不删除数据，如果不是单物料同步就删除没有用的数据（sap已经把这些数据删除了）
                if (material_number == null || "".equals(material_number)) {
                    Price price = new Price();
                    price.setType(price_type);
                    price.setCreate_time(DateUtil.addMinutes(new Date(), -1200));
                    priceDao.deletePriceInfoForSap(price);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try {
            Date aa = sdf.parse("20180502" + " 00:00:00");
            System.out.println(aa);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
