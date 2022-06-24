package com.jingtong.platform.sap.service.impl;

import java.util.Date;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.KunnrsDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Kunnr;
import com.jingtong.platform.sap.pojo.KunnrCompany;
import com.jingtong.platform.sap.service.KunnrsService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

public class KunnrsServiceImpl extends BasicService implements KunnrsService {
    private JCoDestination dest;
    private KunnrsDao kunnrsDao;
    private ILogInfoDao logInfoDao;
    private String customer_code;
    private String sales_org;

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getSales_org() {
        return sales_org;
    }

    public void setSales_org(String sales_org) {
        this.sales_org = sales_org;
    }

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

    public KunnrsDao getKunnrsDao() {
        return kunnrsDao;
    }

    public void setKunnrsDao(KunnrsDao kunnrsDao) {
        this.kunnrsDao = kunnrsDao;
    }

    /**
     * @remark 从SAP获取客户主数据
     */
    public void getKunnrListFromSAP() {
        boolean errorFlag = false;
        try {
            // 创建连接
            this.connect("SAP");

            dest = this.getDest("SAP");

            // 链接接口 接口名称
            JCoFunction function = dest.getRepository().getFunction("ZSDFU001");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("客户主数据/ZSDFU001");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                errorFlag = true;
                throw new Exception("连接SAP失败!");
            }

            function.execute(dest);
            function.getExportParameterList().getStructure("E_RETURN");

            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ABLE");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                int i = 0;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                    Kunnr kunnr = new Kunnr();
                    kunnr.setCustomer_name(resultTbl.getString("CNAME"));
                    kunnr.setCustomer_code(resultTbl.getString("KUNNR"));
                    kunnr.setCountry(resultTbl.getString("LAND1"));
                    kunnr.setDistrict(resultTbl.getString("BZIRK"));
                    kunnr.setSale_office(resultTbl.getString("VKBUR"));
                    kunnr.setSales_org(resultTbl.getString("VKORG"));
                    kunnr.setNielsen(resultTbl.getString("NIELS"));
                    kunnr.setAddress(resultTbl.getString("ADDRESS"));
                    if ("X".equals(resultTbl.getString("STATUS")) || "X".equals(resultTbl.getString("LOEVM"))) {
                        kunnr.setState("1");
                    } else {
                        kunnr.setState("0");
                    }
                    kunnr.setName1(resultTbl.getString("KNAME"));
                    kunnr.setTele1(resultTbl.getString("TELF1"));
                    kunnr.setNameFoemat(resultTbl.getString("ENAME"));
                    kunnr.setCurrency_code(resultTbl.getString("WAERS"));
                    kunnr.setRemark(resultTbl.getString("LTEXT"));
                    kunnr.setCustomer_type(resultTbl.getString("KUKLA"));
                    kunnr.setNielsenRemark(resultTbl.getString("BEZEI"));

                    int count = this.kunnrsDao.getKunnrList(kunnr);
                    try {
                        if (count == 0) {
                            this.kunnrsDao.createKunnr(kunnr);
                        } else {
                            this.kunnrsDao.updateKunnr(kunnr);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("客户主数据/ZSDFU001");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
            }

            // 获取接口结果输出表(伙伴关系)
            JCoTable resultTbl1 = function.getTableParameterList().getTable("T_PARTNER");
            int rows1 = resultTbl1.getNumRows();
            if (rows1 > 0) {
                int n = 0;
                for (; n < rows1; n++) {
                    resultTbl1.setRow(n);
                    KunnrCompany kunnrCompany = new KunnrCompany();
                    kunnrCompany.setCustomer_code(resultTbl1.getString("KUNNR"));
                    kunnrCompany.setSales_org(resultTbl1.getString("VKORG"));
                    kunnrCompany.setDistri_channel(resultTbl1.getString("VTWEG"));
                    kunnrCompany.setProduct_group(resultTbl1.getString("SPART"));
                    kunnrCompany.setPartnerType(resultTbl1.getString("PARVW"));
                    kunnrCompany.setPartner_num(resultTbl1.getString("PARZA"));
                    kunnrCompany.setPartnerId(resultTbl1.getString("KUNN2"));
                    kunnrCompany.setPartnerName(resultTbl1.getString("NAME2"));
                    kunnrCompany.setSalesId(resultTbl1.getString("PERNR"));
                    kunnrCompany.setNameFoemat(resultTbl1.getString("ENAME"));
                    kunnrCompany.setAddress(resultTbl1.getString("ADDRESS"));

                    int count1 = this.kunnrsDao.getKunnrCompanyList(kunnrCompany);
                    try {
                        if (count1 == 0) {
                            this.kunnrsDao.createKunnrCompany(kunnrCompany);
                        } else {
                            this.kunnrsDao.updateKunnrCompany(kunnrCompany);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("客户主数据/ZSDFU001");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
                if (n == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("客户主数据/ZSDFU001");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("成功！");
                    log.setLogInfo("成功");
                    this.logInfoDao.addLogInfo(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getKunnrListFromSAP(String customer_code, String sales_org) {
        boolean errorFlag = false;
        try {
            // 创建连接
            this.connect("SAP");

            dest = this.getDest("SAP");

            // 链接接口 接口名称
            JCoFunction function = dest.getRepository().getFunction("ZSDFU001");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("客户主数据/ZSDFU001");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                errorFlag = true;
                throw new Exception("连接SAP失败!");
            }

            // 传参
            JCoParameterList paramList = function.getImportParameterList();
            if (paramList != null) {
                paramList.setValue("I_KUNNR", customer_code);
                paramList.setValue("I_VKORG", "HK10");
            }

            function.execute(dest);
            function.getExportParameterList().getStructure("E_RETURN");

            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ABLE");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {

                int i = 0;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                    Kunnr kunnr = new Kunnr();
                    kunnr.setCustomer_name(resultTbl.getString("CNAME"));
                    kunnr.setCustomer_code(resultTbl.getString("KUNNR"));
                    kunnr.setCountry(resultTbl.getString("LAND1"));
                    kunnr.setDistrict(resultTbl.getString("BZIRK"));
                    kunnr.setSale_office(resultTbl.getString("VKBUR"));
                    kunnr.setSales_org(resultTbl.getString("VKORG"));
                    kunnr.setNielsen(resultTbl.getString("NIELS"));
                    kunnr.setAddress(resultTbl.getString("ADDRESS"));
                    if ("X".equals(resultTbl.getString("STATUS")) || "X".equals(resultTbl.getString("LOEVM"))) {
                        kunnr.setState("1");
                    } else {
                        kunnr.setState("0");
                    }
                    kunnr.setName1(resultTbl.getString("KNAME"));
                    kunnr.setTele1(resultTbl.getString("TELF1"));
                    kunnr.setNameFoemat(resultTbl.getString("ENAME"));
                    kunnr.setCurrency_code(resultTbl.getString("WAERS"));
                    kunnr.setRemark(resultTbl.getString("LTEXT"));
                    kunnr.setCustomer_type(resultTbl.getString("KUKLA"));
                    kunnr.setNielsenRemark(resultTbl.getString("BEZEI"));

                    int count = this.kunnrsDao.getKunnrList(kunnr);
                    try {
                        if (count == 0) {
                            this.kunnrsDao.createKunnr(kunnr);
                        } else {
                            this.kunnrsDao.updateKunnr(kunnr);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("客户主数据/ZSDFU001");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
            }

            // 获取接口结果输出表(伙伴关系)
            JCoTable resultTbl1 = function.getTableParameterList().getTable("T_PARTNER");
            int rows1 = resultTbl1.getNumRows();
            if (rows1 > 0) {
                int n = 0;
                for (; n < rows1; n++) {
                    resultTbl1.setRow(n);
                    KunnrCompany kunnrCompany = new KunnrCompany();
                    kunnrCompany.setCustomer_code(resultTbl1.getString("KUNNR"));
                    kunnrCompany.setSales_org(resultTbl1.getString("VKORG"));
                    kunnrCompany.setDistri_channel(resultTbl1.getString("VTWEG"));
                    kunnrCompany.setProduct_group(resultTbl1.getString("SPART"));
                    kunnrCompany.setPartnerType(resultTbl1.getString("PARVW"));
                    kunnrCompany.setPartner_num(resultTbl1.getString("PARZA"));
                    kunnrCompany.setPartnerId(resultTbl1.getString("KUNN2"));
                    kunnrCompany.setPartnerName(resultTbl1.getString("NAME2"));
                    kunnrCompany.setSalesId(resultTbl1.getString("PERNR"));
                    kunnrCompany.setNameFoemat(resultTbl1.getString("ENAME"));
                    kunnrCompany.setAddress(resultTbl1.getString("ADDRESS"));

                    int count1 = this.kunnrsDao.getKunnrCompanyList(kunnrCompany);
                    try {
                        if (count1 == 0) {
                            this.kunnrsDao.createKunnrCompany(kunnrCompany);

                        } else {
                            this.kunnrsDao.updateKunnrCompany(kunnrCompany);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("客户主数据/ZSDFU001");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());
                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
                if (n == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("客户主数据/ZSDFU001");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("成功！");
                    log.setLogInfo("成功");
                    this.logInfoDao.addLogInfo(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
