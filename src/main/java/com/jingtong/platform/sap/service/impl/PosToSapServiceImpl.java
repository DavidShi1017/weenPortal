package com.jingtong.platform.sap.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.PosToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.PosDetail;
import com.jingtong.platform.sap.pojo.PosToSap;
import com.jingtong.platform.sap.service.PosToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class PosToSapServiceImpl extends BasicService implements PosToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private PosToSapDao posToSapDao;
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

    public PosToSapDao getPosToSapDao() {
        return posToSapDao;
    }

    public void setPosToSapDao(PosToSapDao posToSapDao) {
        this.posToSapDao = posToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * 查出所有检查通过的，再择出表头和明细 同一个Disti作为一单 B
     * 
     * @throws Exception
     */
    @Override
    public String posToSapB(List<Pos> posList) throws Exception {

        // 调用sap接口获取报价单
        String reMessage = "";
        outterLoop: for (Pos pos : posList) {

            try {
                // 创建连接
                this.connect("SAP");
                dest = this.getDest("SAP");

                // 链接接口
                JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
                if (function == null) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！");
                    log.setLogInfo("连接SAP失败!");
                    this.logInfoDao.addLogInfo(log);
                    throw new Exception("连接SAP失败!");
                }
                List<Pos> posship = posToSapDao.getPosShipTo(pos);
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                if (posship.size() > 0) {
                    head.setValue("KUNWE", posship.get(0).getShip_to());// 送达方
                    head.setValue("KUNRG", posship.get(0).getPayer_to());// 付款-----------------问题-
                    head.setValue("KUNRE", posship.get(0).getBilling_to());// 开票----------------问题---
                    head.setValue("KUNAG", posship.get(0).getSold_to());// 售达即Disti编码
                } else {
                    head.setValue("KUNWE", pos.getPayer_to());// 送达方
                    head.setValue("KUNRG", pos.getPayer_to());// 付款-----------------问题-
                    head.setValue("KUNRE", pos.getPayer_to());// 开票----------------问题---
                    head.setValue("KUNAG", pos.getPayer_to());// 售达即Disti编码
                }
                System.out.println("test 5");
                head.setValue("WAERK", pos.getDisti_cost_currency());
                head.setValue("BSTKD", pos.getDebit_number());
                head.setValue("TXTHL", "");
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值

                List<Pos> detailPoslist = pos.getDetailPosList();

                // 抛单之前判断这个sap 单号有没有获得值从数据库里查询一次 ，防止2个人同时对同一条记录抛单
                // 如果状态为3，并且sapnumber为空，并且待处理状态为空或者0
                // 更新这个pos记录为处理中，
                for (int i = 0; i < detailPoslist.size(); i++) {
                    Pos detail = detailPoslist.get(i);
                    Pos pos_deal_mark = posToSapDao.getPosById(detail);
                    if ((pos_deal_mark.getSapClaimNbr() != null && !"".equals(pos_deal_mark.getSapClaimNbr()))
                            || (pos_deal_mark.getClaim_deal_mark() != null
                                    && pos_deal_mark.getClaim_deal_mark() == 1L)) {
                        reMessage = "Create an order repeatedly. Please wait a moment and then recreate the order";
                        break outterLoop;
                    } else {
                        detail.setClaim_deal_mark(1L);
                        posToSapDao.updatePosClaimDealMark(detail);
                    }
                }

                String row_no = "10";
                for (int i = 0; i < detailPoslist.size(); i++) {
                    Pos detail = detailPoslist.get(i);
                    row_no = String.valueOf((i * 1 + 1) * 10);
                    item.appendRow(); // 放入表内
                    item.setValue("POSNR", row_no);// 行号
                    item.setValue("MATNR", detail.getM_12nc());
                    item.setValue("KWMENG", detail.getShip_qty());
                    item.setValue("VRKME", "PC");
                    item.setValue("NETWR", detail.getRebatePrice() * 100);
                    item.setValue("TXTPL", "");
                    item.setValue("KPEIN", "100");
                }

                function.execute(dest);
                // 获取接口结果输出表

                JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                int rows = resultTbl.getNumRows();
                if (rows > 0) {
                    String flag = resultTbl.getString("ZFLAG");
                    String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");
                    if (vbeln != null && !"".equals(vbeln)) {// 同步成功则更新claim的状态，代项凭证号以及quote剩余数量

                        for (int i = 0; i < detailPoslist.size(); i++) {
                            Pos detail = detailPoslist.get(i);

                            detail.setRes_qty(detail.getQuote_totalqty() - Double.valueOf(detail.getShip_qty()));// 剩余数量（原剩余数量-本次实际返利数量）
                            detail.setDisti_claimnbr(vbeln);// 代项凭证号
                            detail.setSapClaimNbr(vbeln);
                            posToSapDao.updatePosState(detail);
                            posToSapDao.updateQuoteResQty(detail);

                            detail.setClaim_deal_mark(0L);
                            posToSapDao.updatePosClaimDealMark(detail);
                        }
                        reMessage = "Create Order Success!";
                    }
                    else {
                        try {
                            for (int i = 0; i < detailPoslist.size(); i++) {
                                Pos detail = detailPoslist.get(i);
                                detail.setClaim_deal_mark(0L);
                                posToSapDao.updatePosClaimDealMark(detail);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("单号: " + vbeln + "," + flag);
                    log.setLogInfo(message);
                    if ("".equals(reMessage))
                        reMessage = message;
                    try {
                        pos.setClaim_deal_mark(0L);
                        posToSapDao.updatePosClaimDealMark(pos);

                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    resultTbl.getString("ZFLAG");
                     String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！" + message);
                    log.setLogInfo("失败! " + vbeln);
                    try {
                        pos.setClaim_deal_mark(0L);
                        posToSapDao.updatePosClaimDealMark(pos);

                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw e1;
                    }
                    dest = null;
                    reMessage = message;
                    return reMessage;
                }

            } catch (Exception e) {
                e.printStackTrace();
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo(e.getMessage());
                pos.setClaim_deal_mark(0L);
                posToSapDao.updatePosClaimDealMark(pos);

                this.logInfoDao.addLogInfo(log);
                dest = null;
                return e.getMessage();
            }

        }

        return reMessage;
    }

    /**
     * 查出所有检查通过的，再择出表头和明细 同一个Disti作为一单
     */
    @Override
    public String posToSap(List<Pos> posList) {
        // 调用sap接口获取报价单
        String reMessage = "";
        List<Pos> headList = new ArrayList<Pos>();
        for (Pos po : posList) {
            boolean exist = false;
            for (Pos head : headList) {
                if (po.getDisti_branch().equals(head.getDisti_branch())) {
                    exist = true;
                    break;
                }
            }
            if (exist == false) {
                headList.add(po);
            }
        }
        for (Pos headPos : headList) {

            try {
                // 创建连接
                this.connect("SAP");
                dest = this.getDest("SAP");

                // 链接接口
                JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
                if (function == null) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！");
                    log.setLogInfo("连接SAP失败!");
                    this.logInfoDao.addLogInfo(log);
                    throw new Exception("连接SAP失败!");
                }

                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                head.setValue("KUNWE", headPos.getShip_to());// 送达方
                head.setValue("KUNRG", headPos.getPayer_to());// 付款-----------------问题-
                head.setValue("KUNRE", headPos.getBilling_to());// 开票----------------问题---
                head.setValue("KUNAG", headPos.getDisti_branch());// 售达即Disti编码
                head.setValue("WAERK", headPos.getDisti_cost_currency());
                head.setValue("BSTKD", headPos.getDebit_number());
                head.setValue("TXTHL", "");
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值
                for (Pos p : posList) {
                    if (p.getDisti_branch().equals(headPos.getDisti_branch())) {
                        item.appendRow(); // 放入表内
                        item.setValue("POSNR", "10");// 行号
                        item.setValue("MATNR", p.getM_12nc());
                        item.setValue("KWMENG", p.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", p.getRebatePrice() * 100);
                        item.setValue("TXTPL", "");
                        item.setValue("KPEIN", "100");
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
                    if (vbeln != null && !"".equals(vbeln)) {// 同步成功则更新claim的状态，代项凭证号以及quote剩余数量
                        for (Pos p : posList) {
                            if (p.getDisti_branch().equals(headPos.getDisti_branch())) {
                                p.setRes_qty(p.getQuote_totalqty() - Double.valueOf(p.getShip_qty()));// 剩余数量（原剩余数量-本次实际返利数量）
                                p.setDisti_claimnbr(vbeln);// 代项凭证号
                                p.setSapClaimNbr(vbeln);
                                // p1.setRebate_qty(String.valueOf(p.getRemainQty()));//更新Rebate_qty的值
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);

                            }
                        }
                        reMessage = "Create Order Success!";
                        // reMessage = "Create Order Success!" + "</br>"+ "Order Number:" + vbeln;
                    }
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
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
                    resultTbl.getString("ZFLAG");
                    String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("失败！" + message);
                    log.setLogInfo("失败! " + vbeln);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    dest = null;
                    reMessage = message;
                    return reMessage;
                }
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo(e.getMessage());
                dest = null;
                return e.getMessage();
            }
        }

        return "Create Order Success!";
    }

    /**
     * 分表头和明细 A
     */
    @Override
    public String posToSapA(PosToSap pos, List<Pos> posList) {
        // 调用sap接口获取报价单
        String reMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (pos != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                head.setValue("KUNWE", pos.getShip_to());// 送达方
                head.setValue("KUNRG", pos.getPayer_to());// 付款
                head.setValue("KUNRE", pos.getBilling_to());// 开票
                head.setValue("KUNAG", pos.getSale_to());// 售达即Disti
                head.setValue("WAERK", pos.getCurrency_code());
                head.setValue("BSTKD", pos.getOrder_id());
                head.setValue("TXTHL", pos.getTxt());

                if (posList != null) {
                    // 传入信息（明细）
                    JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值
                    for (Pos it : posList) {
                        if (Double.valueOf(it.getShip_qty()) <= 0) {
                            continue;
                        }
                        item.appendRow(); // 放入表内

                        System.out.println("行号：" + it.getRow_no());
                        System.out.println("物料：" + it.getM_12nc());
                        System.out.println("数量：" + it.getShip_qty());
                        System.out.println("金额：" + it.getRebatePrice() * 100);
                        item.setValue("POSNR", it.getRow_no());// 行号
                        item.setValue("MATNR", it.getM_12nc());
                        item.setValue("KWMENG", it.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", it.getRebatePrice() * 100);
                        item.setValue("TXTPL", "");
                        item.setValue("KPEIN", "100");
                    }

                    function.execute(dest);
                    // 获取接口结果输出表

                    JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                    int rows = resultTbl.getNumRows();

                    if (rows > 0) {
                        String flag = resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");
                        if (vbeln != null && !"".equals(vbeln)) {// 同步成功则更新claim的状态，代项凭证号以及quote剩余数量
                            for (Pos p : posList) {
                                if (Double.valueOf(p.getShip_qty()) <= 0) {
                                    continue;
                                }
                                p.setRes_qty(p.getQuote_totalqty() - p.getRemainQty());// 剩余数量（原剩余数量-本次实际返利数量）
                                p.setDisti_claimnbr(vbeln);// 代项凭证号
                                p.setSapClaimNbr(vbeln);
                                p.setRebate_qty(String.valueOf(p.getRemainQty()));// 更新Rebate_qty的值
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);
                            }
                            reMessage = "Create Order Success!" + "</br>" + "Order Number:" + vbeln;
                        }
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("返利结算传入SAP/ZSDFU008");
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
                        resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");

                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！" + message);
                        log.setLogInfo("失败! " + vbeln);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        reMessage = message;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("返利结算传入SAP/ZSDFU008");
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
        dest = null;
        return reMessage + "";
    }

    @Override
    public List<Pos> getPosTotal(Pos pos) {
        try {
            return posToSapDao.getPosTotal(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PosToSap> getPosToSapTotal(PosToSap pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PosDetail> getPosDetail(PosToSap pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String posToSap(PosToSap pos, List<Pos> posList) {
        // 调用sap接口获取报价单
        String reMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (pos != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", pos.getType_id());
                head.setValue("KUNWE", pos.getShip_to());// 送达方
                head.setValue("KUNRG", pos.getPayer_to());// 付款
                head.setValue("KUNRE", pos.getBilling_to());// 开票
                head.setValue("KUNAG", pos.getSale_to());// 售达即Disti
                head.setValue("WAERK", pos.getCurrency_code());
                head.setValue("BSTKD", pos.getOrder_id());
                head.setValue("TXTHL", pos.getTxt());
                if (posList != null) {
                    // 传入信息（明细）
                    JCoTable item = function.getTableParameterList().getTable("T_ITEM");// 获取表传值
                    for (Pos it : posList) {
                        item.appendRow(); // 放入表内

                        System.out.println("行号：" + it.getRow_no());
                        System.out.println("物料：" + it.getM_12nc());
                        System.out.println("数量：" + it.getShip_qty());
                        System.out.println("金额：" + it.getRebatePrice());
                        item.setValue("POSNR", it.getRow_no());// 行号
                        item.setValue("MATNR", it.getM_12nc());
                        item.setValue("KWMENG", it.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", it.getRebatePrice() * 100);
                        item.setValue("KPEIN", "100");
                        item.setValue("TXTPL", "");
                    }

                    function.execute(dest);
                    // 获取接口结果输出表

                    JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                    int rows = resultTbl.getNumRows();

                    if (rows > 0) {
                        String flag = resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");
                        if (vbeln != null && !"".equals(vbeln)) {// 同步成功则更新claim的状态，代项凭证号以及quote剩余数量
                            for (Pos p : posList) {
                                p.setRes_qty(p.getQuote_totalqty() - p.getRemainQty());// 剩余数量（原剩余数量-本次实际返利数量）
                                p.setDisti_claimnbr(vbeln);// 代项凭证号
                                p.setSapClaimNbr(vbeln);
                                p.setRebate_qty(String.valueOf(p.getRemainQty()));// 更新Rebate_qty的值
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);
                            }
                            reMessage = "Create Order Success!" + "</br>" + "Order Number:" + vbeln;
                        }
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("返利结算传入SAP/ZSDFU008");
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
                        resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");

                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("返利结算传入SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("失败！" + message);
                        log.setLogInfo("失败! " + vbeln);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        reMessage = message;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("返利结算传入SAP/ZSDFU008");
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
        dest = null;
        return reMessage + "";
    }

    @Override
    public List<Pos> getLockmark(Pos pos) {
        return posToSapDao.getLockmark(pos);
    }

    @Override
    public void updateLockMark(Pos pos1) {
        posToSapDao.updateLockMark(pos1);
    }
}
