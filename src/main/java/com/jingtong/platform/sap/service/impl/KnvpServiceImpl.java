package com.jingtong.platform.sap.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.KunnrsDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Knvp;
import com.jingtong.platform.sap.service.KnvpService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class KnvpServiceImpl extends BasicService implements KnvpService {
    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private KunnrsDao kunnrsDao;

    public KunnrsDao getKunnrsDao() {
        return kunnrsDao;
    }

    public void setKunnrsDao(KunnrsDao kunnrsDao) {
        this.kunnrsDao = kunnrsDao;
    }

    private List<Knvp> knvps = new ArrayList<Knvp>();

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public List<Knvp> getKnvps() {
        return knvps;
    }

    public void setKnvps(List<Knvp> knvps) {
        this.knvps = knvps;
    }

    @Override
    public void getKnvpFromSAP() {
        // TODO Auto-generated method stub
        Knvp knvp = new Knvp();
        System.out.println("-----开始调用获取开票方、售达方、送达方、付款方主数据接口----------");
        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");
            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("Z_RFC_GET_KNVP");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("开售送付主数据/Z_RFC_GET_KNVP");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }
            // 获取接口传入参数表
            if (knvp.getSkunnr().trim().length() > 0) {
                JCoTable item1 = function.getTableParameterList().getTable("S_KUNNR");
                item1.appendRow();
                // 传参
                item1.setValue("SIGN", "I");
                item1.setValue("OPTION", "EQ");
                item1.setValue("LOW", knvp.getSkunnr());
            }
            if (knvp.getSkunn2().trim().length() > 0) {
                JCoTable item2 = function.getTableParameterList().getTable("S_KUNN2");
                item2.appendRow();
                // 传参
                item2.setValue("SIGN", "I");
                item2.setValue("OPTION", "EQ");
                item2.setValue("LOW", knvp.getSkunn2());
            }
            if (knvp.getSvkorg().trim().length() > 0) {
                JCoTable item3 = function.getTableParameterList().getTable("S_VKORG");
                item3.appendRow();
                // 传参
                item3.setValue("SIGN", "I");
                item3.setValue("OPTION", "EQ");
                item3.setValue("LOW", knvp.getSvkorg());
            }
            if (knvp.getSvtweg().trim().length() > 0) {
                JCoTable item4 = function.getTableParameterList().getTable("S_VTWEG");
                item4.appendRow();
                // 传参
                item4.setValue("SIGN", "I");
                item4.setValue("OPTION", "EQ");
                item4.setValue("LOW", knvp.getSvtweg());
            }
            if (knvp.getsParvw().trim().length() > 0) {
                JCoTable item5 = function.getTableParameterList().getTable("S_PARVW");
                String[] arr = (knvp.getsParvw().trim()).split("/");
                for (int i = 0; i < arr.length; i++) {
                    item5.appendRow();
                    // 传参
                    item5.setValue("SIGN", "I");
                    item5.setValue("OPTION", "EQ");
                    item5.setValue("LOW", arr[i]);
                }
            }
            if (knvp.getSdefpa().trim().length() > 0) {
                JCoTable item6 = function.getTableParameterList().getTable("P_DEFPA");
                item6.appendRow();
                // 传参
                item6.setValue("SIGN", "I");
                item6.setValue("OPTION", "EQ");
                item6.setValue("LOW", knvp.getSdefpa());
            }
            function.execute(dest);
            // 获取接口结果输出表
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_KNVP");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                knvps.clear();
                for (int i = 0; i < rows; i++) {
                    resultTbl.setRow(i);
                    Knvp kp = new Knvp();
                    kp.setKunnr(Long.parseLong(resultTbl.getString("KUNNR")) + "");
                    kp.setVkorg(resultTbl.getString("VKORG"));
                    kp.setVtweg(resultTbl.getString("VTWEG"));
                    kp.setSpart(resultTbl.getString("SPART"));
                    kp.setParvw(resultTbl.getString("PARVW"));
                    kp.setParza(resultTbl.getString("PARZA"));
                    kp.setKunn2(resultTbl.getString("KUNN2"));
                    kp.setLifnr(resultTbl.getString("LIFNR"));
                    kp.setPernr(resultTbl.getString("PERNR"));
                    kp.setParnr(resultTbl.getString("PARNR"));
                    kp.setKnref(resultTbl.getString("KNREF"));
                    kp.setDefpa(resultTbl.getString("DEFPA"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        KnvpServiceImpl kn = new KnvpServiceImpl();
        Knvp knvp = new Knvp();
        knvp.setSkunnr("");
        knvp.setsParvw("");
        kn.getKnvpFromSAP();
    }

    @Override
    public List<Knvp> getKunnrList(String kunnr) {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
