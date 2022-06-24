package com.jingtong.platform.pos.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

/***
 * POS模板
 * 
 * @author mk
 * @createDate 2016-5-24
 */
public class Pos extends SearchInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String transaction_code;
    private String disti_num;
    private String disti_name;
    private String disti_name1;
    private String disti_branch;
    private String disti_city;
    private String book_part;
    private String book_part1;
    private String book_part2;
    private String ship_date;
    private String startShip_date;
    private String endShip_date;

    private Date quote_sdate;
    private Date quote_edate;
    private String ship_qty;// 分销数量
    private Double quote_totalqty;// quote申请剩余总量 res_qty
    private String rebate_qty;// 可返利的数量
    private String debit_number;// 报价单号
    private String disti_claimnbr;// 客户上传的DISTICLAIMNBR(Country+DIS+YYMM+Serial NO#)
    private String sapClaimNbr;// sap返回的代项凭证号
    private String oppreg_nbr;
    private String cpn;
    private String disti_invoice_nbr;// 发票号
    private String disti_invoice_item_number;// 发票行号
    private String disti_cost;// 报价单审批通过价
    private String disti_bookcost;// BP
    private String cost_denom;// 进货成本（美元） （客户上传）
    private String dbc_denom;// MPP （客户上传，检查通过） 价差=dbc_denom - cost_denom //
    private String disti_cost_currency;
    private String disti_cost_exchangeRate;
    private String dbc_currency_code;
    private String dbc_exchange_rate;
    private String disti_resale;
    private String disti_resale_denom;
    private String disti_resale_currency;
    private String disti_resale_exchange_rate;
    private String purchasing_customer_id;
    private String purchasing_customer_name;
    private String purchasing_cust_country;
    private String purchasing_cust_state;
    private String purchasing_cust_city;
    private String purchasing_cust_zip;
    private String endcust_id;
    private String endcust_name;
    private String endcust_name1;
    private String endcust_name2;
    private String endcust_country;
    private String endcust_state;
    private String endcust_city;
    private String endcust_loc;
    private String endcust_zip;
    private String status;// 状态：0删除 1上传 2检查失败 3过检查通过 4已返利
    private String data_from;
    private Date created_time;
    private Date created_time_start;
    private Date created_time_end;
    private String created_timeStr;
    private String created_user;
    private Date update_time;
    private String update_user;
    private String tips;
    private String file_url;
    private long file_id;
    private String fileIds;

    private String status_num;
    private String type;
    private String disti_accounting_nbr;
    private Long file_id_str;
    private String ship_dateStart;
    private String ship_dateEnd;

    private String pc_ec_name;
    private String error_code;
    private String invoice_number;

    private Long claim_deal_mark;

    private String rejecttoapprove_remark;

    private double sumShipQty;

    private String disti_alias;

    public String getRejecttoapprove_remark() {
        return rejecttoapprove_remark;
    }

    public void setRejecttoapprove_remark(String rejecttoapprove_remark) {
        this.rejecttoapprove_remark = rejecttoapprove_remark;
    }

    public Long getClaim_deal_mark() {
        return claim_deal_mark;
    }

    public void setClaim_deal_mark(Long claim_deal_mark) {
        this.claim_deal_mark = claim_deal_mark;
    }

    public String getPc_ec_name() {
        return pc_ec_name;
    }

    public void setPc_ec_name(String pc_ec_name) {
        this.pc_ec_name = pc_ec_name;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public Date getCreated_time_start() {
        return created_time_start;
    }

    public void setCreated_time_start(Date created_time_start) {
        this.created_time_start = created_time_start;
    }

    public Date getCreated_time_end() {
        return created_time_end;
    }

    public void setCreated_time_end(Date created_time_end) {
        this.created_time_end = created_time_end;
    }

    public String getPurchasing_customer_id() {
        return purchasing_customer_id;
    }

    public void setPurchasing_customer_id(String purchasing_customer_id) {
        this.purchasing_customer_id = purchasing_customer_id;
    }

    public String getEndcust_id() {
        return endcust_id;
    }

    public void setEndcust_id(String endcust_id) {
        this.endcust_id = endcust_id;
    }

    public String getShip_dateStart() {
        return ship_dateStart;
    }

    public void setShip_dateStart(String ship_dateStart) {
        this.ship_dateStart = ship_dateStart;
    }

    public String getShip_dateEnd() {
        return ship_dateEnd;
    }

    public void setShip_dateEnd(String ship_dateEnd) {
        this.ship_dateEnd = ship_dateEnd;
    }

    public Long getFile_id_str() {
        return file_id_str;
    }

    public void setFile_id_str(Long file_id_str) {
        this.file_id_str = file_id_str;
    }

    ////////
    private String flag;
    private double remainQty;// 核销数量
    private double buyPriceUsd;// 下单价格
    private double rebatePrice;// 价差
    private double rebateTotal;// 返利总价

    private Date start_time;
    private Date end_time;

    private String mark;// 发票号+行号

    private double res_qty;// 新增临时变量，用于：创建订单后存储quote剩余数量
    private String row_no;
    private double quoteQty;
    private String rebateOver;//
    private String ship_to;
    private String payer_to;
    private String billing_to;
    private String sale_to;
    private String sold_to;
    private String p_name;
    private String p_city;
    private String e_name;
    private String e_city;

    // PCEC匹配判断
    private String m_12nc_PC;
    private String m_12nc_EC;
    private String m_12nc;
    private String pcec;

    // 报表
    private String ship_date1;
    private String ship_date2;
    private String ship_date3;
    private String material_id;
    private String material_exp;
    private int material_state;
    private String material_stateStr;
    private String office_id;
    private String cost_denom_USD;
    private String cost_denom_EUR;
    private String cost_denom_USD_total;
    private String cost_denom_EUR_total;
    private String org_name_pc;
    private String org_name_ec;

    // Claim报表
    private String disti_resale_denom_total;
    private String cost_denom_total;
    private String dbc_denom_total;
    private String debitsOrRedits;
    private String file_ids;
    private Long qty;
    private String amount;

    private String cmd_pc_name;
    private String cmd_pc_city;
    private String cmd_pc_country;
    private String quote_pc_name;
    private String quote_pc_city;
    private String quote_pc_country;
    private String cmd_ec_name;
    private String cmd_ec_city;
    private String cmd_ec_country;
    private String quote_ec_name;
    private String quote_ec_city;
    private String quote_ec_country;

    public String getCmd_pc_name() {
        return cmd_pc_name;
    }

    public void setCmd_pc_name(String cmd_pc_name) {
        this.cmd_pc_name = cmd_pc_name;
    }

    public String getCmd_pc_city() {
        return cmd_pc_city;
    }

    public void setCmd_pc_city(String cmd_pc_city) {
        this.cmd_pc_city = cmd_pc_city;
    }

    public String getCmd_pc_country() {
        return cmd_pc_country;
    }

    public void setCmd_pc_country(String cmd_pc_country) {
        this.cmd_pc_country = cmd_pc_country;
    }

    public String getQuote_pc_name() {
        return quote_pc_name;
    }

    public void setQuote_pc_name(String quote_pc_name) {
        this.quote_pc_name = quote_pc_name;
    }

    public String getQuote_pc_city() {
        return quote_pc_city;
    }

    public void setQuote_pc_city(String quote_pc_city) {
        this.quote_pc_city = quote_pc_city;
    }

    public String getQuote_pc_country() {
        return quote_pc_country;
    }

    public void setQuote_pc_country(String quote_pc_country) {
        this.quote_pc_country = quote_pc_country;
    }

    public String getCmd_ec_name() {
        return cmd_ec_name;
    }

    public void setCmd_ec_name(String cmd_ec_name) {
        this.cmd_ec_name = cmd_ec_name;
    }

    public String getCmd_ec_city() {
        return cmd_ec_city;
    }

    public void setCmd_ec_city(String cmd_ec_city) {
        this.cmd_ec_city = cmd_ec_city;
    }

    public String getCmd_ec_country() {
        return cmd_ec_country;
    }

    public void setCmd_ec_country(String cmd_ec_country) {
        this.cmd_ec_country = cmd_ec_country;
    }

    public String getQuote_ec_name() {
        return quote_ec_name;
    }

    public void setQuote_ec_name(String quote_ec_name) {
        this.quote_ec_name = quote_ec_name;
    }

    public String getQuote_ec_city() {
        return quote_ec_city;
    }

    public void setQuote_ec_city(String quote_ec_city) {
        this.quote_ec_city = quote_ec_city;
    }

    public String getQuote_ec_country() {
        return quote_ec_country;
    }

    public void setQuote_ec_country(String quote_ec_country) {
        this.quote_ec_country = quote_ec_country;
    }

    private List<Pos> detailPosList;

    public List<Pos> getDetailPosList() {
        return detailPosList;
    }

    public void setDetailPosList(List<Pos> detailPosList) {
        this.detailPosList = detailPosList;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String statusInfo;

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getFile_ids() {
        return file_ids;
    }

    public void setFile_ids(String file_ids) {
        this.file_ids = file_ids;
    }

    public String getDebitsOrRedits() {
        return debitsOrRedits;
    }

    public void setDebitsOrRedits(String debitsOrRedits) {
        this.debitsOrRedits = debitsOrRedits;
    }

    public String getCreated_timeStr() {
        return created_timeStr;
    }

    public void setCreated_timeStr(String created_timeStr) {
        this.created_timeStr = created_timeStr;
    }

    public String getDisti_resale_denom_total() {
        return disti_resale_denom_total;
    }

    public void setDisti_resale_denom_total(String disti_resale_denom_total) {
        this.disti_resale_denom_total = disti_resale_denom_total;
    }

    public String getCost_denom_total() {
        return cost_denom_total;
    }

    public void setCost_denom_total(String cost_denom_total) {
        this.cost_denom_total = cost_denom_total;
    }

    public String getDbc_denom_total() {
        return dbc_denom_total;
    }

    public void setDbc_denom_total(String dbc_denom_total) {
        this.dbc_denom_total = dbc_denom_total;
    }

    public String getOrg_name_pc() {
        return org_name_pc;
    }

    public void setOrg_name_pc(String org_name_pc) {
        this.org_name_pc = org_name_pc;
    }

    public String getOrg_name_ec() {
        return org_name_ec;
    }

    public void setOrg_name_ec(String org_name_ec) {
        this.org_name_ec = org_name_ec;
    }

    public String getCost_denom_EUR() {
        return cost_denom_EUR;
    }

    public void setCost_denom_EUR(String cost_denom_EUR) {
        this.cost_denom_EUR = cost_denom_EUR;
    }

    public String getCost_denom_USD() {
        return cost_denom_USD;
    }

    public void setCost_denom_USD(String cost_denom_USD) {
        this.cost_denom_USD = cost_denom_USD;
    }

    public String getRebateOver() {
        return rebateOver;
    }

    public void setRebateOver(String rebateOver) {
        this.rebateOver = rebateOver;
    }

    public double getQuoteQty() {
        return quoteQty;
    }

    public void setQuoteQty(double quoteQty) {
        this.quoteQty = quoteQty;
    }

    public String getRow_no() {
        return row_no;
    }

    public void setRow_no(String row_no) {
        this.row_no = row_no;
    }

    public double getRes_qty() {
        return res_qty;
    }

    public void setRes_qty(double res_qty) {
        this.res_qty = res_qty;
    }

    public String getStartShip_date() {
        return startShip_date;
    }

    public void setStartShip_date(String startShip_date) {
        this.startShip_date = startShip_date;
    }

    public String getEndShip_date() {
        return endShip_date;
    }

    public void setEndShip_date(String endShip_date) {
        this.endShip_date = endShip_date;
    }

    public double getRemainQty() {
        return remainQty;
    }

    public void setRemainQty(double remainQty) {
        this.remainQty = remainQty;
    }

    public double getBuyPriceUsd() {
        return buyPriceUsd;
    }

    public void setBuyPriceUsd(double buyPriceUsd) {
        this.buyPriceUsd = buyPriceUsd;
    }

    public double getRebatePrice() {
        return rebatePrice;
    }

    public void setRebatePrice(double rebatePrice) {
        this.rebatePrice = rebatePrice;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_city() {
        return p_city;
    }

    public void setP_city(String p_city) {
        this.p_city = p_city;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_city() {
        return e_city;
    }

    public void setE_city(String e_city) {
        this.e_city = e_city;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public double getRebateTotal() {
        return rebateTotal;
    }

    public void setRebateTotal(double rebateTotal) {
        this.rebateTotal = rebateTotal;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransaction_code() {
        return transaction_code;
    }

    public void setTransaction_code(String transaction_code) {
        this.transaction_code = transaction_code;
    }

    public String getDisti_num() {
        return disti_num;
    }

    public void setDisti_num(String disti_num) {
        this.disti_num = disti_num;
    }

    public String getDisti_name() {
        return disti_name;
    }

    public void setDisti_name(String disti_name) {
        this.disti_name = disti_name;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getDisti_city() {
        return disti_city;
    }

    public void setDisti_city(String disti_city) {
        this.disti_city = disti_city;
    }

    public String getBook_part() {
        return book_part;
    }

    public void setBook_part(String book_part) {
        this.book_part = book_part;
    }

    public String getM_12nc() {
        return m_12nc;
    }

    public void setM_12nc(String m_12nc) {
        this.m_12nc = m_12nc;
    }

    public String getShip_date() {
        return ship_date;
    }

    public void setShip_date(String ship_date) {
        this.ship_date = ship_date;
    }

    public Date getQuote_sdate() {
        return quote_sdate;
    }

    public void setQuote_sdate(Date quote_sdate) {
        this.quote_sdate = quote_sdate;
    }

    public Date getQuote_edate() {
        return quote_edate;
    }

    public void setQuote_edate(Date quote_edate) {
        this.quote_edate = quote_edate;
    }

    public String getShip_qty() {
        return ship_qty;
    }

    public void setShip_qty(String ship_qty) {
        this.ship_qty = ship_qty;
    }

    public Double getQuote_totalqty() {
        return quote_totalqty;
    }

    public void setQuote_totalqty(Double quote_totalqty) {
        this.quote_totalqty = quote_totalqty;
    }

    public String getRebate_qty() {
        return rebate_qty;
    }

    public void setRebate_qty(String rebate_qty) {
        this.rebate_qty = rebate_qty;
    }

    public String getDebit_number() {
        return debit_number;
    }

    public void setDebit_number(String debit_number) {
        this.debit_number = debit_number;
    }

    public String getDisti_claimnbr() {
        return disti_claimnbr;
    }

    public void setDisti_claimnbr(String disti_claimnbr) {
        this.disti_claimnbr = disti_claimnbr;
    }

    public String getOppreg_nbr() {
        return oppreg_nbr;
    }

    public void setOppreg_nbr(String oppreg_nbr) {
        this.oppreg_nbr = oppreg_nbr;
    }

    public String getCpn() {
        return cpn;
    }

    public void setCpn(String cpn) {
        this.cpn = cpn;
    }

    public String getDisti_invoice_nbr() {
        return disti_invoice_nbr;
    }

    public void setDisti_invoice_nbr(String disti_invoice_nbr) {
        this.disti_invoice_nbr = disti_invoice_nbr;
    }

    public String getDisti_invoice_item_number() {
        return disti_invoice_item_number;
    }

    public void setDisti_invoice_item_number(String disti_invoice_item_number) {
        this.disti_invoice_item_number = disti_invoice_item_number;
    }

    public String getDisti_cost() {
        return disti_cost;
    }

    public void setDisti_cost(String disti_cost) {
        this.disti_cost = disti_cost;
    }

    public String getDisti_cost_currency() {
        return disti_cost_currency;
    }

    public void setDisti_cost_currency(String disti_cost_currency) {
        this.disti_cost_currency = disti_cost_currency;
    }

    public String getDbc_currency_code() {
        return dbc_currency_code;
    }

    public void setDbc_currency_code(String dbc_currency_code) {
        this.dbc_currency_code = dbc_currency_code;
    }

    public String getDisti_resale_denom() {
        return disti_resale_denom;
    }

    public void setDisti_resale_denom(String disti_resale_denom) {
        this.disti_resale_denom = disti_resale_denom;
    }

    public String getDisti_resale_currency() {
        return disti_resale_currency;
    }

    public void setDisti_resale_currency(String disti_resale_currency) {
        this.disti_resale_currency = disti_resale_currency;
    }

    public String getCost_denom() {
        return cost_denom;
    }

    public void setCost_denom(String cost_denom) {
        this.cost_denom = cost_denom;
    }

    public String getDisti_cost_exchangeRate() {
        return disti_cost_exchangeRate;
    }

    public void setDisti_cost_exchangeRate(String disti_cost_exchangeRate) {
        this.disti_cost_exchangeRate = disti_cost_exchangeRate;
    }

    public String getDisti_bookcost() {
        return disti_bookcost;
    }

    public void setDisti_bookcost(String disti_bookcost) {
        this.disti_bookcost = disti_bookcost;
    }

    public String getDbc_denom() {
        return dbc_denom;
    }

    public void setDbc_denom(String dbc_denom) {
        this.dbc_denom = dbc_denom;
    }

    public String getDbc_exchange_rate() {
        return dbc_exchange_rate;
    }

    public void setDbc_exchange_rate(String dbc_exchange_rate) {
        this.dbc_exchange_rate = dbc_exchange_rate;
    }

    public String getDisti_resale() {
        return disti_resale;
    }

    public void setDisti_resale(String disti_resale) {
        this.disti_resale = disti_resale;
    }

    public String getDisti_resale_exchange_rate() {
        return disti_resale_exchange_rate;
    }

    public void setDisti_resale_exchange_rate(String disti_resale_exchange_rate) {
        this.disti_resale_exchange_rate = disti_resale_exchange_rate;
    }

    public String getPurchasing_customer_name() {
        return purchasing_customer_name;
    }

    public void setPurchasing_customer_name(String purchasing_customer_name) {
        this.purchasing_customer_name = purchasing_customer_name;
    }

    public String getPurchasing_cust_country() {
        return purchasing_cust_country;
    }

    public void setPurchasing_cust_country(String purchasing_cust_country) {
        this.purchasing_cust_country = purchasing_cust_country;
    }

    public String getPurchasing_cust_state() {
        return purchasing_cust_state;
    }

    public void setPurchasing_cust_state(String purchasing_cust_state) {
        this.purchasing_cust_state = purchasing_cust_state;
    }

    public String getPurchasing_cust_city() {
        return purchasing_cust_city;
    }

    public void setPurchasing_cust_city(String purchasing_cust_city) {
        this.purchasing_cust_city = purchasing_cust_city;
    }

    public String getPurchasing_cust_zip() {
        return purchasing_cust_zip;
    }

    public void setPurchasing_cust_zip(String purchasing_cust_zip) {
        this.purchasing_cust_zip = purchasing_cust_zip;
    }

    public String getEndcust_name() {
        return endcust_name;
    }

    public String getSapClaimNbr() {
        return sapClaimNbr;
    }

    public void setSapClaimNbr(String sapClaimNbr) {
        this.sapClaimNbr = sapClaimNbr;
    }

    public void setEndcust_name(String endcust_name) {
        this.endcust_name = endcust_name;
    }

    public String getEndcust_country() {
        return endcust_country;
    }

    public void setEndcust_country(String endcust_country) {
        this.endcust_country = endcust_country;
    }

    public String getEndcust_state() {
        return endcust_state;
    }

    public void setEndcust_state(String endcust_state) {
        this.endcust_state = endcust_state;
    }

    public String getEndcust_city() {
        return endcust_city;
    }

    public void setEndcust_city(String endcust_city) {
        this.endcust_city = endcust_city;
    }

    public String getEndcust_loc() {
        return endcust_loc;
    }

    public void setEndcust_loc(String endcust_loc) {
        this.endcust_loc = endcust_loc;
    }

    public String getEndcust_zip() {
        return endcust_zip;
    }

    public void setEndcust_zip(String endcust_zip) {
        this.endcust_zip = endcust_zip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getCreated_user() {
        return created_user;
    }

    public void setCreated_user(String created_user) {
        this.created_user = created_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public String getStatus_num() {
        return status_num;
    }

    public void setStatus_num(String status_num) {
        this.status_num = status_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisti_accounting_nbr() {
        return disti_accounting_nbr;
    }

    public void setDisti_accounting_nbr(String disti_accounting_nbr) {
        this.disti_accounting_nbr = disti_accounting_nbr;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getShip_to() {
        return ship_to;
    }

    public void setShip_to(String ship_to) {
        this.ship_to = ship_to;
    }

    public String getPayer_to() {
        return payer_to;
    }

    public void setPayer_to(String payer_to) {
        this.payer_to = payer_to;
    }

    public String getBilling_to() {
        return billing_to;
    }

    public void setBilling_to(String billing_to) {
        this.billing_to = billing_to;
    }

    public String getSale_to() {
        return sale_to;
    }

    public void setSale_to(String sale_to) {
        this.sale_to = sale_to;
    }

    public String getM_12nc_PC() {
        return m_12nc_PC;
    }

    public void setM_12nc_PC(String m_12nc_PC) {
        this.m_12nc_PC = m_12nc_PC;
    }

    public String getM_12nc_EC() {
        return m_12nc_EC;
    }

    public void setM_12nc_EC(String m_12nc_EC) {
        this.m_12nc_EC = m_12nc_EC;
    }

    public String getPcec() {
        return pcec;
    }

    public void setPcec(String pcec) {
        this.pcec = pcec;
    }

    public String getShip_date1() {
        return ship_date1;
    }

    public void setShip_date1(String ship_date1) {
        this.ship_date1 = ship_date1;
    }

    public String getShip_date2() {
        return ship_date2;
    }

    public void setShip_date2(String ship_date2) {
        this.ship_date2 = ship_date2;
    }

    public String getShip_date3() {
        return ship_date3;
    }

    public void setShip_date3(String ship_date3) {
        this.ship_date3 = ship_date3;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_exp() {
        return material_exp;
    }

    public void setMaterial_exp(String material_exp) {
        this.material_exp = material_exp;
    }

    public int getMaterial_state() {
        return material_state;
    }

    public void setMaterial_state(int material_state) {
        this.material_state = material_state;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getDisti_name1() {
        return disti_name1;
    }

    public void setDisti_name1(String disti_name1) {
        this.disti_name1 = disti_name1;
    }

    public String getBook_part1() {
        return book_part1;
    }

    public void setBook_part1(String book_part1) {
        this.book_part1 = book_part1;
    }

    public String getBook_part2() {
        return book_part2;
    }

    public void setBook_part2(String book_part2) {
        this.book_part2 = book_part2;
    }

    public String getEndcust_name1() {
        return endcust_name1;
    }

    public void setEndcust_name1(String endcust_name1) {
        this.endcust_name1 = endcust_name1;
    }

    public String getEndcust_name2() {
        return endcust_name2;
    }

    public void setEndcust_name2(String endcust_name2) {
        this.endcust_name2 = endcust_name2;
    }

    public String getMaterial_stateStr() {
        return material_stateStr;
    }

    public void setMaterial_stateStr(String material_stateStr) {
        this.material_stateStr = material_stateStr;
    }

    public String getCost_denom_USD_total() {
        return cost_denom_USD_total;
    }

    public void setCost_denom_USD_total(String cost_denom_USD_total) {
        this.cost_denom_USD_total = cost_denom_USD_total;
    }

    public String getCost_denom_EUR_total() {
        return cost_denom_EUR_total;
    }

    public void setCost_denom_EUR_total(String cost_denom_EUR_total) {
        this.cost_denom_EUR_total = cost_denom_EUR_total;
    }

    public String getSold_to() {
        return sold_to;
    }

    public void setSold_to(String sold_to) {
        this.sold_to = sold_to;
    }

    public double getSumShipQty() {
        return sumShipQty;
    }

    public void setSumShipQty(double sumShipQty) {
        this.sumShipQty = sumShipQty;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getDisti_alias() {
        return disti_alias;
    }

    public void setDisti_alias(String disti_alias) {
        this.disti_alias = disti_alias;
    }

}
