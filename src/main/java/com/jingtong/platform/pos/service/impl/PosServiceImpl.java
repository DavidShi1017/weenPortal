package com.jingtong.platform.pos.service.impl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.pos.dao.IPosDao;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.pos.service.IPosService;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public class PosServiceImpl implements IPosService {

    public IPosDao posDao;

    public IPosDao getPosDao() {
        return posDao;
    }

    public void setPosDao(IPosDao posDao) {
        this.posDao = posDao;
    }

    @Override
    public long createPosInfo(Pos pos) {
        try {
            return posDao.createPosInfo(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosListCount(Pos pos) {
        try {
            return posDao.searchPosListCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosList(Pos pos) {
        try {
            return posDao.searchPosList(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pos getquote(Pos pos) {
        try {
            return posDao.getquote(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> getPosDebitNumber(Pos pos) {
        try {
            return posDao.getPosDebitNumber(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pos getquoListBydebitNumBook(Pos pos) {
        try {
            return posDao.getquoListBydebitNumBook(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getMinPrice(Pos pos) {
        try {
            return posDao.getMinPrice(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosListByPos(Pos pos) {
        try {
            return posDao.searchPosListByPos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchCustomerCount(Pos pos) {
        try {
            return posDao.searchCustomerCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchRelationshipCount(Pos pos) {
        try {
            return posDao.searchRelationshipCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchProductCount(Pos pos) {
        try {
            return posDao.searchProductCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getQuoteInfoCount(Pos pos) {
        try {
            return posDao.getQuoteInfoCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getQuoteDetailCount(Pos pos) {
        try {
            return posDao.getQuoteDetailCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getReQuoteDetailCount(Pos pos) {
        try {
            return posDao.getReQuoteDetailCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchEndCustomerCount(Pos pos) {
        try {
            return posDao.searchEndCustomerCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getDictCount(Pos pos) {
        try {
            return posDao.getDictCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosDetailListCountById(Pos pos) {
        try {
            return posDao.searchPosDetailListCountById(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosDetailListById(Pos pos) {
        try {
            return posDao.searchPosDetailListById(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchPosDetailListByIdForOne(Pos pos) {
        try {
            return posDao.searchPosDetailListByIdForOne(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getFileId() {
        try {
            return posDao.getFileId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosDetailListCount(Pos pos) {
        try {
            return posDao.searchPosDetailListCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosDetailListCountForError(Pos pos) {
        try {
            return posDao.searchPosDetailListCountForError(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updatePos(Pos pos) {
        try {
            return posDao.updatePos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public long getClaimFileId() {
        try {
            return posDao.getClaimFileId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosListCountForAll(Pos pos) {
        try {
            return posDao.searchPosListCountForAll(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> searchQuoteList(Pos pos) {
        try {
            return posDao.searchQuoteList(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getPassedQty(Pos pos) {
        try {
            return posDao.getPassedQty(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public List<Pos> getPassedQtys() {
        try {
            return posDao.getPassedQtys();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchPosListForAll(Pos pos) {
        try {
            return posDao.searchPosListForAll(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchPosListForPosCheckInvoice(Pos pos) {
        try {
            return posDao.searchPosListForPosCheckInvoice(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchDRCount(Pos pos) {
        try {
            Pos q = new Pos();
            q.setOppreg_nbr(pos.getOppreg_nbr());
            q.setDisti_name(pos.getDisti_name());
            
            int drNum = posDao.searchDRCount(q);
            Disti_branch disti = new Disti_branch();
            disti.setDisti_name(pos.getDisti_name());
            Disti_branch alias = getDistAlias(disti);
            
            if (alias != null && StringUtils.isNotEmpty(alias.getDisti_alias())) {
                q.setDisti_name(alias.getDisti_alias());
                drNum = drNum + posDao.searchDRCount(q);
            }
            return drNum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosListCountForEDI(Pos pos) {
        try {
            return posDao.searchPosListCountForEDI(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosListForEDI(Pos pos) {
        try {
            return posDao.searchPosListForEDI(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updatePosTips(Pos pos) {
        try {
            return posDao.updatePosTips(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public List<Integer> searchPosFileId(Pos pos) {
        try {
            return posDao.searchPosFileId(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchPosFileIdCount(Pos pos) {
        try {
            return posDao.searchPosFileIdCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // @Override
    // public ECAlias searchEcList(Pos pos) {
    // try{
    // return posDao.searchEcList(pos);
    // }catch(Exception e){
    // e.printStackTrace();
    // return null;
    // }
    // }

    @Override
    public List<ECAlias> searchEcList(Pos pos) {
        try {
            return posDao.searchEcList(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ECAlias> searchEcMasterName(Pos pos) {
        try {
            return posDao.searchEcMasterName(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<EndCustomer> searchEcMasterNameByEcId(Pos pos) {
        try {
            return posDao.searchEcMasterNameByEcId(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchDistiBranch(Pos pos) {
        try {
            return posDao.searchDistiBranch(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int searchPosListCountByIds(Pos pos) {
        try {
            return posDao.searchPosListCountByIds(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosListByIds(Pos pos) {
        try {
            return posDao.searchPosListByIds(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchPosListCountForBb(Pos pos) {
        try {
            return posDao.searchPosListCountForBb(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosListForBb(Pos pos) {
        try {
            return posDao.searchPosListForBb(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchClaimListCountForBb(Pos pos) {
        try {
            return posDao.searchClaimListCountForBb(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchClaimListForBb(Pos pos) {
        try {
            return posDao.searchClaimListForBb(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchPosListForBbAll(Pos pos) {
        try {
            return posDao.searchPosListForBbAll(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchClaimListForBbAll(Pos pos) {
        try {
            return posDao.searchClaimListForBbAll(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pos getDistiName(Pos pos) {
        try {
            return posDao.getDistiName(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pos getClaimDistiName(Pos pos) {
        try {
            return posDao.getClaimDistiName(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 导出报表
     */
    @Override
    public int searchPosTrackingDetailCount(Pos pos) {
        try {
            return posDao.searchPosTrackingDetailCount(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Pos> searchPosTrackingDetail(Pos pos) {
        try {
            return posDao.searchPosTrackingDetail(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> searchPosTrackingDetailNoPage(Pos pos) {
        try {
            return posDao.searchPosTrackingDetailNoPage(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int resetPos(Pos pos) {
        try {
            return posDao.resetPos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void checkEDI(Long id) throws Exception {
        Pos pos1 = new Pos();
        pos1.setType("1");
        pos1.setId(id);
        int n = searchPosListCountByIds(pos1);
        if (n >= 1) {
            List<Pos> poList = searchPosListByIds(pos1);
            for (Pos pos : poList) {
                String reMessage = "";
                String message0 = "";
                String message1 = "";
                String message2 = "";
                String message3 = "";
                String message4 = "";
                String message5 = "";
                String message6 = "";
                String message11 = "";
                String message15 = "";
                String message21 = "";
                String message22 = "";
                String message23 = "";
                String message24 = "";
                String message25 = "";
                String message26 = "";
                String message27 = "";
                String message28 = "";
                String message29 = "";
                String message30 = "";
                String message31 = "";

                // 处理disti_name
                if ("9".equals(pos.getStatus()) && "2".equals(pos.getData_from())) {
                    Pos p = getDistiName(pos);
                    if (p != null) {
                        pos.setDisti_name(p.getDisti_name());
                    }
                }

                if (pos.getTransaction_code() != null && !"".equals(pos.getTransaction_code().trim())) {
                    if (!"S".equals(pos.getTransaction_code().toUpperCase())
                            && !"R".equals(pos.getTransaction_code().toUpperCase())) {
                        message0 = "Invalid transaction code! ";
                    }
                } else {
                    message0 = "Transaction Code is empty! Invalid transaction code!";
                }

                if (pos.getDisti_name() != null && !"".equals(pos.getDisti_name().trim())) {
                    try {
                        Pos qpos = new Pos();
                        qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                        int total = searchCustomerCount(qpos);
                        if (total <= 0) {
                            message1 = "Bad Disti! ";
                        }
                    } catch (Exception e) {
                        message1 = "Bad Disti! ";
                    }

                } else {
                    message1 = "Disti is empty! Bad Disti! ";
                }

                if (pos.getDisti_branch() != null && !"".equals(pos.getDisti_branch().trim())) {
                    message2 = "";
                } else {
                    message2 = "Disti Branch is empty! ";
                }

                if (pos.getDisti_city() != null && !"".equals(pos.getDisti_city().trim())) {
                    message3 = "";
                } else {
                    message3 = "Disti City is empty! ";
                }

                if (pos.getBook_part() != null && !"".equals(pos.getBook_part().trim())) {
                    Pos qpos = new Pos();
                    qpos.setBook_part(pos.getBook_part());
                    int total = searchProductCount(qpos);
                    if (total <= 0) {
                        message4 = "Bad Book part! ";
                    }
                } else {
                    message4 = "Book Part is empty! Bad Book part! ";
                }

                if (pos.getShip_qty() != null && !"".equals(pos.getShip_qty().trim())) {
                    try {
                        Double a = Double.valueOf(pos.getShip_qty());
                        if (pos.getTransaction_code() != null && !"".equals(pos.getTransaction_code().trim())) {
                            if ("S".equals(pos.getTransaction_code().toUpperCase())) {
                                if (a <= 0) {
                                    message5 = "Bad quantity!";
                                }
                            }
                            // 当为R时，必须QTY必须小于0 ,大于0提示
                            else if ("R".equals(pos.getTransaction_code().toUpperCase())) {
                                if (a >= 0) {
                                    message5 = "Bad quantity!";
                                }
                            }
                        }
                        // 如果是数字，//5 Invalid quantity
                    } catch (Exception e) {
                        message5 = "Invalid quantity! ";// 如果抛出异常，返回False
                    }
                } else {
                    message5 = "Ship Qty is empty! Bad quantity! Invalid quantity!";
                }

                if (pos.getShip_date() != null && !"".equals(pos.getShip_date().trim())) {
                    // 6 Invalid ship date 判断是否是合法的日期格式
                    try {
                        String stri = pos.getShip_date();
                        Format f = new SimpleDateFormat("yyyyMMdd");
                        Date d = (Date) f.parseObject(stri);

                        String tmp = f.format(d);
                        System.out.println(stri + ":" + tmp.equals(stri));
                        boolean flag = tmp.equals(stri);
                        if (flag == false) {
                            message6 = "Invalid ship date!";
                        }
                    } catch (Exception e) {
                        message6 = "Invalid ship date!";
                    }

                    // 6 Invalid ship date
                    // 获取当前日期
                    try {
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String DateStr1 = sdf.format(date);
                        String DateStr2 = pos.getShip_date();
                        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        Date dateTime1 = dateFormat.parse(DateStr1);
                        Date dateTime2 = dateFormat.parse(DateStr2);
                        int b = dateTime1.compareTo(dateTime2);
                        if (b < 0) {
                            message6 = "Bad ship date! ";
                        }
                    } catch (Exception e) {
                        message6 = "Bad ship date! ";
                    }
                } else {
                    message6 = "Ship Date is empty! Invalid ship date! Bad ship date!";
                }

                if (pos.getOppreg_nbr() != null && !"".equals(pos.getOppreg_nbr().trim())) {
                    try {
                        Pos qpos = new Pos();
                        qpos.setOppreg_nbr(pos.getOppreg_nbr());
                        qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                        int total = searchDRCount(qpos);
                        if (total < 0) {
                            message30 = "Bad oppreg nbr! ";
                        }
                    } catch (Exception e) {
                        message30 = "Bad oppreg nbr! ";
                    }
                }

                if (pos.getDisti_invoice_nbr() != null && !"".equals(pos.getDisti_invoice_nbr().trim())) {
                    // 11 Duplicate invoice number
                    Pos qpos = new Pos();
                    if (null == pos.getDisti_invoice_item_number() || "".equals(pos.getDisti_invoice_item_number()))
                        qpos.setMark(pos.getDisti_invoice_nbr());
                    else
                        qpos.setMark(pos.getDisti_invoice_nbr() + pos.getDisti_invoice_item_number());
                    qpos.setType("1");
                    qpos.setStatus("('3')");
                    qpos.setId(pos.getId());

                    if (pos.getTransaction_code() != null && !"".equals(pos.getTransaction_code().trim())
                            && "S".equals(pos.getTransaction_code().toUpperCase())) {
                        // 获得shipdate
                        String shipYear = "";
                        try {
                            shipYear = pos.getShip_date().substring(0, 4);
                        } catch (Exception e) {
                            e.printStackTrace();
                            message11 = "Invoive Nbr Disti is empty! Duplicate invoice number! ";
                        }
                        String distiname = pos.getDisti_name();
                        String distiAlias = "";
                        String part = pos.getBook_part();
                        String qty = pos.getShip_qty();
                        Disti_branch disti = new Disti_branch();
                        disti.setDisti_name(distiname);
                        Disti_branch alias = getDistAlias(disti);
                        if (alias != null && StringUtils.isNotEmpty(alias.getDisti_alias())) {
                            distiAlias = alias.getDisti_alias();
                            qpos.setDisti_alias(distiAlias);
                        }
                        List<Pos> li = searchPosListForPosCheckInvoice(qpos);

                        if (li.size() > 0) {
                            // 年份相同reject
                            String ss = "";
                            for (Pos p : li) {
                                ss = p.getShip_date() + "," + ss;
                            }
                            if (ss.contains(shipYear)) {
                                message11 = "Duplicate invoice number! ";
                            } else {
                                if (!distiname.equals(li.get(0).getDisti_name()) &&
                                        ((StringUtils.isNotEmpty(distiAlias) && !distiAlias.equals(li.get(0).getDisti_name())))) {
                                    message11 = "invoice_pending";
                                } else {
                                    if (part.equals(li.get(0).getBook_part()) && li.get(0).getShip_qty().equals(qty)) {
                                        message11 = "invoice_pending";
                                    }
                                }
                            }
                        }
                    }
                } else {
                    message11 = "Invoive Nbr Disti is empty! Duplicate invoice number! ";
                }

                if (pos.getDisti_resale() != null && !"".equals(pos.getDisti_resale().trim())) {
                    message21 = "DISTI RESALE must be empty! ";
                }

                if (pos.getDisti_resale_denom() != null && !"".equals(pos.getDisti_resale_denom().trim())) {
                    // 22 Bad number format
                    try {
                        String str = pos.getDisti_resale_denom();
                        Double.valueOf(str);
                    } catch (Exception e) {
                        message22 = "Bad number format: ";
                    }
                } else {
                    message22 = "Disti Resale Denom is empty! Bad number format! ";
                }

                if (pos.getDisti_resale_currency() != null && !"".equals(pos.getDisti_resale_currency().trim())) {
                    if (!"USD".equals(pos.getDisti_resale_currency())
                            && !"EUR".equals(pos.getDisti_resale_currency())) {
                        message23 = " Invalid currency! ";
                    }
                } else {
                    message23 = "Disti Resale Currency is empty! Invalid currency!";
                }

                if (pos.getDisti_resale_exchange_rate() != null
                        && !"".equals(pos.getDisti_resale_exchange_rate().trim())) {
                    message24 = "DISTI RESALE EXCHANGE RATE must be empty";
                }

                if (pos.getPurchasing_customer_name() == null || "".equals(pos.getPurchasing_customer_name().trim())) {
                    message25 = "Purchasing Customer Name is empty! Customer not found!";
                }

                if (pos.getPurchasing_cust_city() == null || "".equals(pos.getPurchasing_cust_city().trim())) {
                    message26 = "Purchsing Cust City is empty! ";
                }

                if (pos.getPurchasing_cust_country() != null && !"".equals(pos.getPurchasing_cust_country().trim())) {
                    if ("US".equals(pos.getPurchasing_cust_country())
                            || "CA".equals(pos.getPurchasing_cust_country())) {
                        if (pos.getPurchasing_cust_zip() == null || "".equals(pos.getPurchasing_cust_zip().trim())) {
                            message27 = "Purchasing Cust Zip is empty! ";
                        }
                        if (pos.getPurchasing_cust_state() == null
                                || "".equals(pos.getPurchasing_cust_state().trim())) {
                            message28 = "Purchasing Cust State is empty! ";
                        }
                    }
                } else {
                    message29 = "Purchasing Cust Country is empty! ";
                }

                String mark = message0 + message1 + message2 + message3 + message4 + message5 + message6 + message11
                        + message15 + message21 + message22 + message23 + message24 + message25 + message26 + message27
                        + message28 + message29 + message30;

                pos.setM_12nc_PC("");
                pos.setM_12nc_EC("");

                if ("".equals(mark) || mark == null) {
                    if ("".equals(message25) && "".equals(message26)) {
                        Pos qposs = new Pos();
                        qposs.setPurchasing_customer_name(
                                pos.getPurchasing_customer_name().toUpperCase().replaceAll("  ", " "));
                        qposs.setPurchasing_cust_city(pos.getPurchasing_cust_city().toUpperCase());

                        int total = searchEndCustomerCount(qposs);
                        if (total <= 0) {
                            message25 = "Customer not found! ";
                            pos.setM_12nc_PC("PC");
                            pos.setStatus("1");
                        }
                    }
                    try {
                        Pos qposs = new Pos();
                        qposs.setEndcust_name(pos.getEndcust_name().toUpperCase().replaceAll("  ", " "));
                        qposs.setEndcust_city(pos.getEndcust_city().toUpperCase());
                        int total = searchEndCustomerCount(qposs);
                        if (total <= 0) {
                            message31 = "EndCustomer not found! ";
                            pos.setM_12nc_EC("EC");
                            pos.setStatus("1");
                        }
                    } catch (Exception e) {
                        message31 = "EndCustomer not found! ";
                    }
                }

                reMessage = message0 + message1 + message2 + message3 + message4 + message5 + message6 + message11
                        + message15 + message21 + message22 + message23 + message24 + message25 + message26 + message27
                        + message28 + message29 + message30 + message31;

                if (!"".equals(reMessage)) {
                    reMessage = reMessage.substring(0, reMessage.length() - 1);
                }

                if (!"".equals(reMessage)) {
                    if ("invoice_pendin".equals(reMessage)) {// 只有发票penging
                        String m = pos.getM_12nc_PC() + pos.getM_12nc_EC();
                        pos.setM_12nc(m);
                        pos.setStatus("1"); // pending
                        pos.setTips("Duplicate invoice number!Disti name difference or part and qty same   ");
                        pos.setId(pos.getId());
                        pos.setType("1");
                        updatePosTips(pos);
                    } else {
                        String m = pos.getM_12nc_PC() + pos.getM_12nc_EC();
                        pos.setM_12nc(m);
                        if (!"".equals(pos.getM_12nc())) {
                            pos.setStatus("1");
                        } else {
                            pos.setStatus("0");
                        }
                        pos.setTips(reMessage);
                        pos.setId(pos.getId());
                        pos.setType("1");
                        updatePosTips(pos);
                    }
                } else {
                    pos.setStatus("3");
                    pos.setTips("Success!");
                    pos.setId(pos.getId());
                    pos.setType("1");
                    updatePosTips(pos);
                }
            }
            pos1.setFile_id(poList.get(0).getFile_id());
            pos1.setType("1");
            int aa = searchPosDetailListCount(pos1);
            int bb = searchPosDetailListCountForError(pos1);
            pos1.setStatus_num(bb + "/" + aa);
            updatePos(pos1);
        }
    }

    // claimCheckEDI
    @Override
    public void claimCheckEDI(Pos pos) throws Exception {
        String reMessage = "";
        String M1J = "";
        String M1F = "";
        String M1K = "";
        String M2A = "";
        String M2B = "";
        String M2C = "";
        String M2D = "";
        String M2E = "";
        String M2F = "";
        String M2G = "";
        String M2H = "";
        String M2I = "";
        String M2J = "";
        String M2K = "";
        String M2L = "";
        String M2M = "";
        String M2N = "";
        String M2O = "";
        String M2P = "";
        String M2Q = "";
        String M2R = "";
        String M2S = "";
        String M2T = "";
        String M2U = "";
        String M2V = "";
        String M2W = "";
        String M2X = "";

        String C3A = "";
        String C3C = "";
        String C3G = "";
        String C3K = "";
        String C3M = "";
        String C3O = "";
        String C4B = "";
        String C4C = "";
        String C4E = "";
        String C4Q = "";
        String C4V = "";
        String C4T = "";
        String C5E = "";
        String C5F = "";
        String C5G = "";
        String C5J = "";
        String C5O = "";
        String C5Q = "";
        String C5V = "";
        String C5T = "";
        String C6E = "";
        String C6F = "";
        String C6G1 = "";
        String C6G2 = "";
        String C6H = "";
        String C6K = "";
        String C6L = "";
        String C6M = "";
        String C6Q = "";
        String C6V = "";
        String C6T = "";

        try {
            if ("9".equals(pos.getStatus()) && "2".equals(pos.getData_from())) {
                Pos p = getClaimDistiName(pos);
                if (p != null) {
                    pos.setDisti_name(p.getDisti_name());
                }

            }
        } catch (Exception e) {
            pos.setDisti_name(pos.getDisti_name());
        }

        if (pos.getEndcust_name() == null || "".equals(pos.getEndcust_name())) {
            Pos fapiao = new Pos();
            fapiao.setDisti_invoice_nbr(pos.getDisti_invoice_nbr());
            fapiao.setDisti_invoice_item_number(pos.getDisti_invoice_item_number());
            fapiao.setType("1");
            fapiao.setStatus("3");
            List<Pos> lifapiao = posDao.searchPosListForPosCheckInvoice(fapiao);
            if (lifapiao.size() > 0) {
                Pos pp = lifapiao.get(0);
                pos.setEndcust_name(pp.getEndcust_name());
                pos.setEndcust_city(pp.getEndcust_city());
                pos.setEndcust_loc(pp.getEndcust_loc());
                pos.setEndcust_zip(pp.getEndcust_zip());
                pos.setEndcust_state(pp.getEndcust_state());
                pos.setEndcust_country(pp.getEndcust_country());
                posDao.updateClaimEcInfo(pos);
            }
        }

        if (null != pos.getTransaction_code() && !"".equals(pos.getTransaction_code().trim())) {
            M2A = "";
        } else {
            M2A = "2A,";
        }

        if (null != pos.getDisti_name() && !"".equals(pos.getDisti_name().trim())) {
            M2B = "";
        } else {
            M2B = "2B,";
        }

        if (null != pos.getDisti_branch() && !"".equals(pos.getDisti_branch().trim())) {
            M2C = "";
        } else {
            M2C = "2C,";
        }

        if (null != pos.getDisti_city() && !"".equals(pos.getDisti_city().trim())) {
            M2D = "";
        } else {
            M2D = "2D,";
        }

        if (null != pos.getBook_part() && !"".equals(pos.getBook_part().trim())) {
            M2E = "";
        } else {
            M2E = "2E,";
        }

        if (null != pos.getShip_qty() && !"".equals(pos.getShip_qty().trim())) {
            M2F = "";
        } else {
            M2F = "2F,";
        }

        if (null != pos.getShip_date() && !"".equals(pos.getShip_date().trim())) {
            String stri = pos.getShip_date();
            Format f = new SimpleDateFormat("yyyyMMdd");
            try {
                Date d = (Date) f.parseObject(stri);
                String tmp = f.format(d);
                System.out.println(stri + ":" + tmp.equals(stri));
                boolean flag = tmp.equals(stri);
                if (flag == false) {
                    M2G = "2G,";
                }
            } catch (Exception e) {
                M2G = "2G,";
            }
        } else {
            M2G = "2G,";
        }

        if (null != pos.getDebit_number() && !"".equals(pos.getDebit_number().trim())) {
            M2H = "";
        } else {
            M2H = "2H,";
        }

        if (null != pos.getDisti_claimnbr() && !"".equals(pos.getDisti_claimnbr().trim())) {
            M2I = "";
        } else {
            M2I = "2I,";
        }

        if (null != pos.getDisti_invoice_nbr() && !"".equals(pos.getDisti_invoice_nbr().trim())) {
            M2J = "";
        } else {
            M2J = "2J,";
        }

        if (null != pos.getCost_denom() && pos.getCost_denom() != "0" && !"".equals(pos.getCost_denom().trim())) {
            M2K = "";
        } else {
            M2K = "2K,";
        }

        if (null != pos.getDisti_cost_currency() && !"".equals(pos.getDisti_cost_currency().trim())) {
            M2L = "";
        } else {
            M2L = "2L,";
        }

        if (null != pos.getDbc_denom() && pos.getDbc_denom() != "0" && !"".equals(pos.getDbc_denom().trim())) {
            M2M = "";
        } else {
            M2M = "2M,";
        }

        if (null != pos.getDbc_currency_code() && !"".equals(pos.getDbc_currency_code().trim())) {
            M2N = "";
        } else {
            M2N = "2N,";
        }

        if (null != pos.getDisti_resale_denom() && pos.getDisti_resale_denom() != "0"
                && !"".equals(pos.getDisti_resale_denom().trim())) {
            M2O = "";
        } else {
            M2O = "2O,";
        }

        if (null != pos.getDisti_resale_currency() && !"".equals(pos.getDisti_resale_currency().trim())) {
            M2P = "";
        } else {
            M2P = "2P,";
        }

        if (null != pos.getPurchasing_customer_name() && !"".equals(pos.getPurchasing_customer_name().trim())) {
            M2Q = "";
        } else {
            M2Q = "2Q,";
        }

        if (null != pos.getPurchasing_cust_city() && !"".equals(pos.getPurchasing_cust_city().trim())) {
            M2R = "";
        } else {
            M2R = "2R,";
        }

        if (null != pos.getPurchasing_cust_country() && !"".equals(pos.getPurchasing_cust_country().trim())) {

            if ("CA".equals(pos.getPurchasing_cust_country()) || "ca".equals(pos.getPurchasing_cust_country())
                    || "US".equals(pos.getPurchasing_cust_country()) || "us".equals(pos.getPurchasing_cust_country())) {
                M2R = "";
            }

            M2S = "";
        } else {
            M2S = "2S,";
        }

        if (null != pos.getEndcust_name() && !"".equals(pos.getEndcust_name().trim())) {
            M2T = "";
        } else {
            M2T = "2T,";
        }

        if (null != pos.getEndcust_city() && !"".equals(pos.getEndcust_city().trim())) {
            M2U = "";
        } else {
            M2U = "2U,";
        }

        if (null != pos.getEndcust_loc() && !"".equals(pos.getEndcust_loc().trim())) {
            M2V = "";
        } else {
            M2V = "2V,";
        }

        if (null != pos.getEndcust_country() && !"".equals(pos.getEndcust_country().trim())) {

            if ("CA".equals(pos.getEndcust_country()) || "ca".equals(pos.getEndcust_country())
                    || "US".equals(pos.getEndcust_country()) || "us".equals(pos.getEndcust_country())) {
                M2V = "";
            }

            M2W = "";
        } else {
            M2W = "2W,";
        }

        if (null != pos.getDisti_accounting_nbr() && !"".equals(pos.getDisti_accounting_nbr().trim())) {
            M2X = "";
        } else {
            M2X = "2X,";
        }

        Pos qpos = new Pos();
        if (null == pos.getDisti_invoice_item_number() || "".equals(pos.getDisti_invoice_item_number()))
            qpos.setMark(pos.getDisti_invoice_nbr());
        else
            qpos.setMark(pos.getDisti_invoice_nbr() + pos.getDisti_invoice_item_number());
        qpos.setType("2");
        qpos.setStatus("('3','4')");
        qpos.setId(pos.getId());
        int total = searchPosListCountForAll(qpos);
        if (total > 0) {
            M1J = "1J,";
        }

        try {
            if (Double.valueOf(pos.getShip_qty()) <= 0) {
                M1F = "1F,";
            }
        } catch (Exception e) {
            M1F = "1F,";
        }

        if (pos.getDisti_cost() != null && pos.getDisti_bookcost() != null) {
            try {
                if (Double.valueOf(pos.getDisti_cost()) >= Double.valueOf(pos.getDisti_bookcost())) {
                    M1K = "1K,";
                }
            } catch (Exception e) {
                M1K = "1k,";
            }
        }
        // xcfeng 20180205 end

        // 1 BAD_TRANS_CODE
        if ("".equals(M2A)) {
            if (!"C".equals(pos.getTransaction_code()) && !"c".equals(pos.getTransaction_code())) {
                C3A = "3A,";
            }
        }

        // 4 BAD_DISTI_ACCOUNT
        if ("".equals(M2C) && "".equals(M2X)) {
            try {
                String aa = pos.getDisti_accounting_nbr();
                String bb = pos.getDisti_branch();
                if (!aa.equals(bb)) {
                    C3C = "3C,";
                }

            } catch (Exception e) {
                C3C = "3C,";
            }

        }

        // 2 BAD_CR_N_DAYS_AFTER_SHIP
        if ("".equals(M2G)) {

            DateFormat fmtDateTime = new SimpleDateFormat("yyyyMMdd");
            String str1 = pos.getShip_date();
            Date shipdate = fmtDateTime.parse(str1.toString());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(shipdate);// cal1 = ship_date

            Date date2 = new Date();
            String str2 = fmtDateTime.format(date2);
            Date today = fmtDateTime.parse(str2.toString());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(today);
            int day1 = cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);

            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            int time;

            if (year1 != year2) {
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                        timeDistance += 366;
                    } else {
                        timeDistance += 365;
                    }
                }

                time = timeDistance + (day2 - day1);
            } else {
                time = day2 - day1;
            }
            System.out.println(time);
            if (time > 90) {
                C3G = "3G,";
            }

        }

        if ("".equals(M2K)) {
            try {
                Double e = Double.valueOf(pos.getCost_denom());
                int num1 = (e + "").length() - (e + "").indexOf(".") - 1;
                if (num1 > 4) {
                    C3K = "3K,";
                }
            } catch (Exception e) {
                C3K = "3K,";
            }
        }
        if ("".equals(M2M)) {
            try {
                Double g = Double.valueOf(pos.getDbc_denom());
                int num3 = (g + "").length() - (g + "").indexOf(".") - 1;
                if (num3 > 4) {
                    C3M = "3M,";
                }
            } catch (Exception e) {
                C3M = "3M,";
            }
        }
        if ("".equals(M2O)) {
            try {
                Double h = Double.valueOf(pos.getDisti_resale_denom());
                int num4 = (h + "").length() - (h + "").indexOf(".") - 1;
                if (num4 > 4) {
                    C3O = "3O,";
                }
            } catch (Exception e) {
                C3O = "3O,";
            }
        }

        // 与主数据比较 12 - 16
        // 12 BAD_DISTI_CUST toUpperCase
        if ("".equals(M2B)) {
            try {
                qpos = new Pos();
                qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                total = searchCustomerCount(qpos);
                if (total <= 0) {
                    C4B = "4B,";
                }
            } catch (Exception e) {
                C4B = "4B,";
            }
        }

        // 13 BAD_DISTRIBUTOR_BRANCH
        if ("".equals(M2C) && "".equals(M2B)) {
            try {
                qpos = new Pos();
                qpos.setDisti_branch(pos.getDisti_branch());
                qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                total = searchRelationshipCount(qpos);
                if (total <= 0) {
                    C4C = "4C,";
                }
            } catch (Exception e) {
                C4C = "4C,";
            }

        }

        // 16 BAD_PART
        if ("".equals(M2E)) {
            try {
                qpos = new Pos();
                qpos.setBook_part(pos.getBook_part().toUpperCase());
                total = searchProductCount(qpos);
                if (total <= 0) {
                    C4E = "4E,";
                }
            } catch (Exception e) {
                C4E = "4E,";
            }
        }

        // 14 BAD_CUSTOMER toUpperCase
        if ("".equals(M2Q) && "".equals(M2R)) {
            try {
                qpos = new Pos();
                qpos.setPurchasing_customer_name(pos.getPurchasing_customer_name().toUpperCase());
                qpos.setPurchasing_cust_city(pos.getPurchasing_cust_city().toUpperCase());
                total = searchEndCustomerCount(qpos);
                if (total <= 0) {
                    C4Q = "4Q,";
                }
            } catch (Exception e) {
                C4Q = "4Q,";
            }
        }

        // 15 BAD_END_CUSTOMER toUpperCase
        if ("".equals(M2T) && "".equals(M2U)) {
            try {
                qpos = new Pos();
                qpos.setEndcust_name(pos.getEndcust_name().toUpperCase());
                qpos.setEndcust_city(pos.getEndcust_city().toUpperCase());
                total = searchEndCustomerCount(qpos);
                if (total <= 0) {
                    C4T = "4T,";
                }
            } catch (Exception e) {
                C4T = "4T,";
            }
        }

        // 6 - 11 与POS比较
        if ("".equals(M2J)) {
            qpos = new Pos();
            if (null == pos.getDisti_invoice_item_number() || "".equals(pos.getDisti_invoice_item_number()))
                qpos.setMark(pos.getDisti_invoice_nbr());
            else
                qpos.setMark(pos.getDisti_invoice_nbr() + pos.getDisti_invoice_item_number());
            qpos.setType("1");
            qpos.setStatus("3"); // appoved

            List<Pos> poList = searchPosListByPos(qpos);
            if (null != poList && poList.size() > 0) { // 7
                                                       // SHIP_DATE_NOT_MATCH
                if ("".equals(M2G)) {
                    if (!pos.getShip_date().equals(poList.get(0).getShip_date())) {
                        C5G = "5G,";
                    }
                }

                if ("".equals(M2F)) {
                    try {
                        double aaa = Double.valueOf(pos.getShip_qty()) * 1;
                        double bbb = Double.valueOf(poList.get(0).getShip_qty()) * 1;
                        if (aaa != bbb) { // 8 SHIP_QUANTITY_NOT_MATCH
                            C5F = "5F,";
                        }
                    } catch (Exception e) {
                        C5F = "5F,";
                    }
                }

                if ("".equals(M2Q)) {
                    try {
                        if (!pos.getPurchasing_customer_name().trim().toUpperCase().replaceAll("  ", " ").equals(poList
                                .get(0).getPurchasing_customer_name().trim().toUpperCase().replaceAll("  ", " "))) {
                            C5Q = "5Q1,";
                        } else {
                            if (!pos.getPurchasing_cust_city().trim().toUpperCase()
                                    .equals(poList.get(0).getPurchasing_cust_city().trim().toUpperCase())) {
                                C5Q = "5Q2,";
                            }
                        }
                    } catch (Exception e) {
                        C5Q = "5Q,";
                    }
                }

                if ("".equals(M2O)) {
                    try {
                        double aa = Double.valueOf(pos.getDisti_resale_denom()) * 1;
                        double bb = Double.valueOf(poList.get(0).getDisti_resale_denom()) * 1;
                        if (aa != bb) { // RESALE_PRICE_NOT_MATCH
                            C5O = "5O,";
                        }
                    } catch (Exception e) {
                        C5O = "5O,";
                    }
                }

                if ("".equals(M2T)) {
                    try {
                        if (!pos.getEndcust_name().trim().toUpperCase().replaceAll("  ", " ")
                                .equals(poList.get(0).getEndcust_name().trim().toUpperCase().replaceAll("  ", " "))) {
                            C5T = "5T1,";
                        } else {
                            if (!pos.getEndcust_city().trim().toUpperCase()
                                    .equals(poList.get(0).getEndcust_city().trim().toUpperCase())) {
                                C5T = "5T2,";
                            }
                        }
                    } catch (Exception e) {
                        C5T = "5T,";
                    }

                }

                if ("".equals(M2E)) {
                    if (!pos.getBook_part().equals(poList.get(0).getBook_part())) {
                        C5E = "5E,";
                    }
                }

            } else {// 6 BAD_CR_WITHOUT_POS
                C5J = "5J,";
            }
        }

        // 与Quote比较 17 - 27
        Disti_branch disti = new Disti_branch();
        disti.setDisti_name(pos.getDisti_name());
        Disti_branch alias = getDistAlias(disti);
        
        if (alias != null && StringUtils.isNotEmpty(alias.getAlias())) {
            pos.setDisti_alias(alias.getAlias().toUpperCase());
        }
        // 17 BAD_DEBIT_NUM

        if ("".equals(M2B) && "".equals(M2H)) {
            try {
                qpos = new Pos();
                qpos.setDebit_number(pos.getDebit_number());
                qpos.setDisti_name(pos.getDisti_name().toUpperCase());
                qpos.setDisti_alias(pos.getDisti_alias());
                total = getQuoteDetailCount(qpos);
                if (total <= 0) {
                    C6H = "6H,";
                }
            } catch (Exception e) {
                C6H = "6H,";
            }
        }

        // 18 Bad_Debit_Channel

        if ("".equals(M2B) && "".equals(M2E) && "".equals(M2H)) {
            qpos = new Pos();
            qpos.setDebit_number(pos.getDebit_number());
            qpos.setDisti_name(pos.getDisti_name().toUpperCase());
            qpos.setDisti_alias(pos.getDisti_alias());
            qpos.setBook_part(pos.getBook_part().toUpperCase());
            List<QuoteDetail> quList = searchQuoteList(qpos);
            if (null != quList && quList.size() > 0) {
                if (quList.get(0).getIsAgree() != 1) {
                    C6E = "6E,";
                } else {
                    if ("".equals(M2L)) {
                        if (!pos.getDisti_cost_currency().equals(quList.get(0).getCurrency_code())) {
                            C6L = "6L,";
                        }
                    }

                    if ("".equals(M2Q)) {
                        try {
                            Pos qposs = new Pos();
                            qposs.setPurchasing_customer_name((pos.getPurchasing_customer_name()).toUpperCase());
                            qposs.setPurchasing_cust_city((pos.getPurchasing_cust_city()).toUpperCase());
                            qposs.setPurchasing_customer_id(quList.get(0).getPurchaseCustomer_id());
                            List<ECAlias> ecList = new ArrayList<ECAlias>();
                            ecList = searchEcList(qposs);
                            if (null != ecList && ecList.size() > 0) {
                                for (ECAlias ea : ecList) {
                                    if (!((quList.get(0).getPurchaseCustomer_name()).trim().toUpperCase())
                                            .equals(ea.getEc_name().trim().toUpperCase())) {
                                        C6Q = "6Q,";
                                    }
                                }
                            } else {
                                C6Q = "6Q,";
                            }
                        } catch (Exception e) {
                            C6Q = "6Q,";
                        }
                    }

                    if ("".equals(M2T)) {
                        try {
                            Pos qposs = new Pos();
                            qposs.setEndcust_name((pos.getEndcust_name()).toUpperCase());
                            qposs.setEndcust_city((pos.getEndcust_city()).toUpperCase());

                            List<ECAlias> ecList = new ArrayList<ECAlias>();
                            ecList = searchEcMasterName(qposs);
                            if (null != ecList && ecList.size() > 0) {
                                for (ECAlias ea : ecList) {
                                    if (!((quList.get(0).getEndCustomer_name()).trim().toUpperCase())
                                            .equals(ea.getEc_name().trim().toUpperCase())) {
                                        C6T = "6T,";
                                    }
                                }
                            } else {
                                C6T = "6T,";
                            }
                        } catch (Exception e) {
                            C6T = "6T,";
                        }
                    }

                    if ("".equals(C5G)) {
                        try {
                            DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                            Date date = fmt.parse(pos.getShip_date());
                            try {
                                boolean flag = false;
                                Date startDate = DateUtil.getDateTime(
                                        DateUtil.date(quList.get(0).getStart_date(), "yyyyMMdd"), "yyyyMMdd");
                                if (startDate.getTime() > date.getTime()) {
                                    flag = true;
                                }
                                if (flag == true && !date.equals(quList.get(0).getStart_date())) {
                                    C6G1 = "6G1,";
                                }
                            } catch (Exception e) {
                                C6G1 = "6G1,";
                            }
                            try {
                                boolean flag1 = quList.get(0).getEnd_date().after(date);
                                if (flag1 == false && !date.equals(quList.get(0).getEnd_date())) {
                                    C6G2 = "6G2,";
                                }
                            } catch (Exception e) {
                                C6G2 = "6G2,";
                            }
                        } catch (Exception e) {
                            C6G1 = "6G1,";
                        }
                    }

                    if ("".equals(C3K)) {
                        try {
                            if (Double.valueOf(pos.getCost_denom()) != quList.get(0).getSuggest_cost()) {
                                C6K = "6K,";
                            }
                        } catch (Exception e) {
                            C6K = "6K,";
                        }
                    }
                }
            } else {
                C6E = "6E,";
            }
            // 25 BAD_DISTI_BOOK_COST
            if ("".equals(C4C) && "".equals(C5E) && "".equals(C3M) && "".equals(M2N)) {
                try {
                    qpos = new Pos();
                    qpos.setDbc_denom(pos.getDbc_denom());
                    qpos.setDisti_branch(pos.getDisti_branch());
                    qpos.setBook_part(pos.getBook_part().toUpperCase());
                    // 获取当前日期
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String DateStr1 = sdf.format(date);
                    qpos.setShip_date(DateStr1);
                    qpos.setDbc_currency_code(pos.getDbc_currency_code());
                    try {
                        double minPrice = getMinPrice(qpos);
                        if (Double.valueOf(qpos.getDbc_denom()) != minPrice) {
                            C6M = "6M,";
                        }
                    } catch (Exception e2) {
                        C6M = "6M,";
                    }
                } catch (Exception e) {
                    C6M = "6M,";
                }
            }

            // 26 BAD_QUANTITY
            if ("".equals(M2F)) {
                try {
                    qpos = new Pos();
                    pos.setShip_qty(String.valueOf((Double.valueOf(pos.getShip_qty())) * 1));
                    double ccc = Double.valueOf(pos.getShip_qty()) * 1;
                    double passQty = getPassedQty(pos);
                    if (ccc > (quList.get(0).getQty() - passQty)) {
                        C6F = "6F,";
                    }
                } catch (Exception e1) {
                    C6F = "6F,";
                }
            }
        }

        reMessage = M1J + M1F + M1K + M2A + M2B + M2C + M2D + M2E + M2F + M2G + M2H + M2I + M2J + M2K + M2L + M2M + M2N
                + M2O + M2P + M2Q + M2R + M2S + M2T + M2U + M2V + M2W + M2X + C3A + C3C + C3G + C3K + C3M + C3O + C4B
                + C4C + C4E + C4Q + C4V + C4T + C5E + C5F + C5G + C5J + C5O + C5Q + C5V + C5T + C6E + C6F + C6G1 + C6G2
                + C6H + C6K + C6L + C6M + C6Q + C6V + C6T;
        if (!"".equals(reMessage)) {
            reMessage = reMessage.substring(0, reMessage.length() - 1);
        }
        pos.setM_12nc("");
        if ("".equals(reMessage)) {// approve
            pos.setStatus("3");
            pos.setTips("Success!");
            pos.setId(pos.getId());
            updatePosTips(pos);
        } else {
            if ("4Q,6Q".equals(reMessage) || "4Q,5J,6Q".equals(reMessage)) {
                pos.setM_12nc("PC");
                pos.setStatus("1");
            } else if ("4T,6T".equals(reMessage) || "4T,5J,6T".equals(reMessage)) {
                pos.setM_12nc("EC");
                pos.setStatus("1");
            } else if ("4Q,4T,6Q,6T".equals(reMessage) || "4Q,4T,5J,6Q,6T".equals(reMessage)) {
                pos.setM_12nc("PCEC");
                pos.setStatus("1");
            } else if ("6Q".equals(reMessage) || "6T".equals(reMessage) || "6Q,6T".equals(reMessage)) {
                pos.setM_12nc("PCEC");
                pos.setStatus("1");
            } else if ("5J".equals(reMessage)) { // 只有5J 才pedding
                // pos.setM_12nc("PCEC");
                pos.setStatus("1");
            } else {
                pos.setStatus("0");
            }

            pos.setTips(reMessage);
            pos.setId(pos.getId());
            updatePosTips(pos);

        }
    }

    @Override
    public void claimCheckEDI() throws Exception {
        Pos pos1 = new Pos();
        pos1.setType("2");
        int num = searchPosFileIdCount(pos1);
        if (num >= 1) {
            List<Integer> idList = searchPosFileId(pos1);
            for (Integer fileId : idList) {
                pos1.setFile_id(fileId);
                int n = searchPosDetailListCountById(pos1);
                if (n >= 1) {
                    List<Pos> poList = searchPosListForEDI(pos1);
                    for (Pos pos : poList) {
                        if ("9".equals(pos.getStatus())) {
                            this.claimCheckEDI(pos);
                        }
                    }
                }
                int aa = searchPosDetailListCount(pos1);
                int bb = searchPosDetailListCountForError(pos1);
                pos1.setStatus_num(bb + "/" + aa);
                updatePos(pos1);
            }

        }
    }

    @Override
    public int approvePos(Pos pos) {
        try {
            return posDao.approvePos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int rejectPos(Pos pos) {
        try {
            return posDao.rejectPos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) throws ParseException {
        String str1 = "20160831";
        DateFormat fmtDateTime = new SimpleDateFormat("yyyyMMdd");
        Date date1 = fmtDateTime.parse(str1.toString());

        Date dd = DateUtil.addDays(new Date(), -90);
        System.out.println(date1);
        System.out.println(DateUtil.addDays(new Date(), -90));
        System.out.println(date1.getTime());
        System.out.println(dd.getTime());
        if (date1.getTime() < dd.getTime()) {
            System.out.println("----------");
        }
    }

    @Override
    public int updatePCEC(Pos pos) {
        try {
            return posDao.updatePCEC(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Pos getPosById(Pos pos) {
        try {
            return posDao.getPosById(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pos getPosByfileId(Pos pos) {
        try {
            return posDao.getPosByfileId(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pos searchPosByIdForOneCheck(Pos pos) {
        try {
            return posDao.searchPosByIdForOneCheck(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pos> getClaimErrorCodeDetail(Pos pos) {
        List<Pos> li = new ArrayList<Pos>();
        Pos claim = getPosById(pos);
        //// Claim Data
        Pos p1 = new Pos();
        p1.setDisti_name("Claim Data");
        p1.setPurchasing_customer_name(claim.getPurchasing_customer_name());
        p1.setPurchasing_cust_city(claim.getPurchasing_cust_city());
        p1.setPurchasing_cust_country(claim.getPurchasing_cust_country());
        p1.setEndcust_name(claim.getEndcust_name());
        p1.setEndcust_city(claim.getEndcust_city());
        p1.setEndcust_country(claim.getEndcust_country());
        li.add(p1);

        // POS Data
        Pos ppos = new Pos();
        if (null == claim.getDisti_invoice_item_number() || "".equals(claim.getDisti_invoice_item_number()))
            ppos.setMark(claim.getDisti_invoice_nbr());
        else
            ppos.setMark(claim.getDisti_invoice_nbr() + claim.getDisti_invoice_item_number());
        ppos.setType("1");
        ppos.setStatus("3"); // appoved
        Pos p2 = new Pos();
        p2.setDisti_name("Pos Data");
        if (ppos.getMark() == null || "".equals(ppos.getMark())) {
            p2.setPurchasing_customer_name("");
            p2.setPurchasing_cust_city("");

            p2.setEndcust_name("");
            p2.setEndcust_city("");
            p2.setEndcust_country("");
            p2.setPurchasing_cust_country("");
        } else {
            List<Pos> poList = searchPosListByPos(ppos);
            if (poList.size() > 0) {
                p2.setPurchasing_customer_name(poList.get(0).getPurchasing_customer_name());
                p2.setPurchasing_cust_city(poList.get(0).getPurchasing_cust_city());
                p2.setEndcust_name(poList.get(0).getEndcust_name());
                p2.setEndcust_city(poList.get(0).getEndcust_city());
                p2.setEndcust_country(poList.get(0).getEndcust_country());
                p2.setPurchasing_cust_country(poList.get(0).getPurchasing_cust_country());
            } else {
                p2.setPurchasing_customer_name("");
                p2.setPurchasing_cust_city("");
                p2.setEndcust_name("");
                p2.setEndcust_city("");
                p2.setEndcust_country("");
                p2.setPurchasing_cust_country("");
            }
        }
        li.add(p2);

        // Qutoe信息
        Pos qpos = new Pos();
        qpos.setDebit_number(claim.getDebit_number());
        qpos.setDisti_name(claim.getDisti_name().toUpperCase());
        qpos.setBook_part(claim.getBook_part().toUpperCase());
        List<QuoteDetail> quList = searchQuoteList(qpos);

        // CMD
        Pos qposs = new Pos();
        Pos p4 = new Pos();
        p4.setDisti_name("Claim(CMD)");
        if (claim.getPurchasing_customer_name() == null || "".equals(claim.getPurchasing_customer_name())) {
            p4.setPurchasing_customer_name("");
            p4.setPurchasing_cust_city("");
            p4.setPurchasing_cust_country("");
        } else {
            qposs.setEndcust_name((claim.getPurchasing_customer_name()).toUpperCase());
            qposs.setEndcust_city((claim.getPurchasing_cust_city()).toUpperCase());
            List<ECAlias> ecList = new ArrayList<ECAlias>();
            ecList = searchEcMasterName(qposs);
            if (ecList.size() > 0) {
                p4.setPurchasing_customer_name("(" + ecList.get(0).getEc_id() + ")" + ecList.get(0).getEc_name());
                p4.setPurchasing_cust_city(ecList.get(0).getEc_city());
                p4.setPurchasing_cust_country(ecList.get(0).getCountry());
            } else {
                p4.setPurchasing_customer_name("");
                p4.setPurchasing_cust_city("");
                p4.setPurchasing_cust_country("");
            }
        }

        Pos qposs1 = new Pos();
        if (claim.getEndcust_name() == null || "".equals(claim.getEndcust_city())) {
            p4.setEndcust_name("");
            p4.setEndcust_city("");
            p4.setEndcust_country("");
        } else {
            qposs1.setEndcust_name((claim.getEndcust_name()).toUpperCase());
            qposs1.setEndcust_city((claim.getEndcust_city()).toUpperCase());
            List<ECAlias> ecList1 = new ArrayList<ECAlias>();
            ecList1 = searchEcMasterName(qposs1); //
            if (ecList1.size() > 0) {
                p4.setEndcust_name("(" + ecList1.get(0).getEc_id() + ")" + ecList1.get(0).getEc_name());
                p4.setEndcust_city(ecList1.get(0).getEc_city());
                p4.setEndcust_country(ecList1.get(0).getCountry());
            } else {
                p4.setEndcust_name("");
                p4.setEndcust_city("");
                p4.setEndcust_country("");
            }
        }
        li.add(p4);

        // CMD 通过 quote的Ec_id 和Pc 的ec_id获得对应CMD信息
        Pos quoteposs = new Pos();
        Pos p5 = new Pos();
        p5.setDisti_name("Quote(CMD)");
        if (quList == null || quList.size() == 0 ||
                claim.getPurchasing_customer_name() == null || "".equals(claim.getPurchasing_customer_name())
                || quList.get(0).getPurchaseCustomer_id() == null
                || "".equals(quList.get(0).getPurchaseCustomer_id())) {
            p5.setPurchasing_customer_name("");
            p5.setPurchasing_cust_city("");
            p5.setPurchasing_cust_country("");
        } else {
            quoteposs.setEndcust_id(quList.get(0).getPurchaseCustomer_id());
            List<EndCustomer> ecList = new ArrayList<EndCustomer>();
            ecList = searchEcMasterNameByEcId(quoteposs);
            if (ecList.size() > 0) {
                p5.setPurchasing_customer_name(
                        "(" + quList.get(0).getPurchaseCustomer_id() + ")" + ecList.get(0).getEnd_customer_name());
                p5.setPurchasing_cust_city(ecList.get(0).getCity());
                p5.setPurchasing_cust_country(ecList.get(0).getCountry());
            } else {
                p5.setPurchasing_customer_name("");
                p5.setPurchasing_cust_city("");
                p5.setPurchasing_cust_country("");
            }
        }
        Pos quoteposs1 = new Pos();
        if (quList == null || quList.size() == 0 ||
                claim.getEndcust_name() == null || "".equals(claim.getEndcust_city())
                || quList.get(0).getEndCustomer_id() == null || "".equals(quList.get(0).getEndCustomer_id())) {
            p5.setEndcust_name("");
            p5.setEndcust_city("");
            p5.setEndcust_country("");
        } else {
            quoteposs1.setEndcust_id(quList.get(0).getEndCustomer_id());
            List<EndCustomer> ecList1 = new ArrayList<EndCustomer>();
            ecList1 = searchEcMasterNameByEcId(quoteposs1); //
            if (ecList1.size() > 0) {
                p5.setEndcust_name(
                        "(" + quList.get(0).getEndCustomer_id() + ")" + ecList1.get(0).getEnd_customer_name());
                p5.setEndcust_city(ecList1.get(0).getCity());
                p5.setEndcust_country(ecList1.get(0).getCountry());
            } else {
                p5.setEndcust_name("");
                p5.setEndcust_city("");
                p5.setEndcust_country("");
            }
        }
        li.add(p5);

        return li;
    }

    // 20171020 xcfeng
    @Override
    public List<Pos> searchPosDetailList6q6t(Pos pos) {
        try {
            return posDao.searchPosDetailList6q6t(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int searchPosDetailListCount6q6t(Pos pos) {
        try {
            return posDao.searchPosDetailListCount6q6t(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public List<Pos> getPosByfileIds(Pos pos) {
        try {
            return posDao.getPosByfileIds(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Disti_branch getDistAlias(Disti_branch disti) {
        List<Disti_branch> list = posDao.getDistiAliasList(disti);
        
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
}
