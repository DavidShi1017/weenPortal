package com.jingtong.platform.sap.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.MaterialDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.service.MaterialService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class MaterialServiceImpl extends BasicService implements MaterialService {
    private JCoDestination dest;
    private MaterialDao materialsDao;
    private ILogInfoDao logInfoDao;

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public MaterialDao getMaterialsDao() {
        return materialsDao;
    }

    public void setMaterialsDao(MaterialDao materialsDao) {
        this.materialsDao = materialsDao;
    }

    @Override
    public void getMaterialListFromSAP(String material_id) {
        boolean errorFlag = false;
        try {
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU002");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("物料主数据/ZSDFU002");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                errorFlag = true;
                throw new Exception("连接SAP失败!");
            }

            // 获取接口传入参数表
            JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");
            if (!"".equals(material_id) && material_id != null) {
                head.setValue("MATNR", "000000" + material_id);
            }

            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ABLE");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                int i = 0;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                    Material material = new Material();
                    material.setMaterial_id(resultTbl.getString("MATNR"));
                    System.out.println(material.getMaterial_id());
                    material.setMaterial_exp(resultTbl.getString("MAKTX"));
                    material.setMaterial_type(resultTbl.getString("MTBEZ"));
                    material.setRemark(resultTbl.getString("VTEXT"));
                    material.setBase_unit(resultTbl.getString("MEINS"));
                    material.setSale_unit(resultTbl.getString("VRKME"));
                    material.setUnitRatio(resultTbl.getString("UNCHG"));
                    material.setState(resultTbl.getString("MSTAE"));
                    material.setIsLocked(resultTbl.getString("MATFI"));
                    material.setMoq(resultTbl.getString("AUMNG"));
                    material.setMaterial_name(resultTbl.getString("BOOKP"));
                    material.setCost(resultTbl.getString("COSTR"));
                    material.setFactory(resultTbl.getString("WERKS"));
                    material.setWm_Unit(resultTbl.getString("LVSME"));
                    material.setNumerator(resultTbl.getString("WMREZ"));
                    material.setDenominator(resultTbl.getString("WMREN"));
                    material.setIsDeleted(resultTbl.getString("LVORM"));
                    String rfsDateString = resultTbl.getString("MSTDE");
                    if (rfsDateString != null && !"".equals(rfsDateString.trim())
                            && !"00000000".equals(rfsDateString) && rfsDateString.trim().length() == 8) {
                        DateFormat formatRfsDate = new SimpleDateFormat("yyyyMMdd"); 
                        material.setRfs_date(formatRfsDate.parse(rfsDateString));
                    } 

                    int count = this.materialsDao.getMaterialCount(material);
                    // RF/DO/D0/D1/D2/OB
                    if ("RF".equals(material.getState()) || "D0".equals(material.getState())) {
                        material.setState("1");
                    } else {
                        material.setState("2");
                    }

                    try {
                        if (count == 0) {
                            this.materialsDao.creatMaterial(material);
                        } else if (count == 1) {
                            this.materialsDao.updateMaterial(material);
                        }
                    } catch (Exception e) {
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("物料主数据/ZSDFU002");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());

                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
                if (i == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("物料主数据/ZSDFU002");
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

    public static void main(String[] args) {
        MaterialServiceImpl dap = new MaterialServiceImpl();
        dap.getMaterialListFromSAP();

    }

    @Override
    public String getMaterialListFromSAP() {
        boolean errorFlag = false;
        String message = "";
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU002");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("物料主数据/ZSDFU002");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                errorFlag = true;
                throw new Exception("连接SAP失败!");
            }

            // 获取接口传入参数表
            JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");
            head.setValue("VKORG", "HK10");

            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("T_ABLE");
            int rows = resultTbl.getNumRows();            
            if (rows > 0) {
                int i = 0;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                    Material material = new Material();
                    material.setMaterial_id(resultTbl.getString("MATNR"));
                    System.out.println(material.getMaterial_id());
                    if ("934072789127".equals(material.getMaterial_id())) {
                        material.setMaterial_id("934072789127");
                    }
                    material.setMaterial_exp(resultTbl.getString("MAKTX"));
                    material.setMaterial_type(resultTbl.getString("MTBEZ"));
                    material.setRemark(resultTbl.getString("VTEXT"));
                    material.setBase_unit(resultTbl.getString("MEINS"));
                    material.setSale_unit(resultTbl.getString("VRKME"));
                    material.setUnitRatio(resultTbl.getString("UNCHG"));
                    material.setState(resultTbl.getString("MSTAE"));
                    material.setIsLocked(resultTbl.getString("MATFI"));
                    material.setMoq(resultTbl.getString("AUMNG"));
                    material.setMaterial_name(resultTbl.getString("BOOKP"));
                    material.setCost(resultTbl.getString("COSTR"));
                    material.setFactory(resultTbl.getString("WERKS"));
                    material.setWm_Unit(resultTbl.getString("LVSME"));
                    material.setNumerator(resultTbl.getString("WMREZ"));
                    material.setDenominator(resultTbl.getString("WMREN"));
                    material.setIsDeleted(resultTbl.getString("LVORM"));
                    String rfsDateString = resultTbl.getString("MSTDE");
                    if (rfsDateString != null && !"".equals(rfsDateString.trim())
                            && !"00000000".equals(rfsDateString) && rfsDateString.trim().length() == 8) {
                        DateFormat formatRfsDate = new SimpleDateFormat("yyyyMMdd"); 
                        material.setRfs_date(formatRfsDate.parse(rfsDateString));
                    } 
                    int count = this.materialsDao.getMaterialCount(material);
                    if ("RF".equals(material.getState()) || "D0".equals(material.getState())) {
                        material.setState("1");
                    } else {
                        material.setState("2");
                    }

                    try {
                        if (count == 0) {
                            this.materialsDao.creatMaterial(material);
                        } else if (count == 1) {
                            this.materialsDao.updateMaterial(material);
                        }
                    } catch (Exception e) {
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("物料主数据/ZSDFU002");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！");
                        log.setLogInfo(e.getMessage());

                        this.logInfoDao.addLogInfo(log);
                        errorFlag = true;
                        continue;
                    }
                }
                if (i == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("物料主数据/ZSDFU002");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("成功！");
                    log.setLogInfo("成功");
                    message = "Success!";
                    this.logInfoDao.addLogInfo(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed!";

        }
        return message;
    }
}
