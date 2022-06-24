package com.jingtong.platform.quote.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 报价明细
 * 
 * @author yw
 *
 */
public class QuoteDetail extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    ///////// 明细表字段
    private long id;
    private String quote_id;// 报价单号（Quote ID）-
    private String row_no;// 报价单行号（Quote Detail Row）-
    private String material_id;// 物料编码（12NC）
    private String strMaterialId;// 物料编码（12NC）
    private String material_name;
    private String drNum;// 项目注册的编码(DR Number)-
    private Double qty;// 订购数量（QTY）-
    private double res_qty;// 剩余数量（返利用）
    private double target_resale;// 目标销售价格（Target Resale-
    private Double target_cost;// 目标进货价格（Target Cost）-
    private double amount;// 行项目总价（Value）-
    private String target_margin;
    private String cus_profits_percent;// 客户利润百分比(Disti Margin)
    private String reason;// 申请原因(Justification)-
    private String competitor;// 竞争对手(Competitor)-
    private Date product_date;// 开始生产日期Start of Production-
    private String product_dateStr;
    private Date start_date;// 开始日期
    private String start_dateStr;
    private Date end_date;// 截止日期
    private String end_dateStr;
    private String cus_remark;// 代理商备注(CusRemarks)-
    private double suggest_resale;// 审批销售价格(Suggest Resale) -
    private double suggest_cost;// 审批进货价格(Suggest Cost)-
    private double suggest_cost_usd;// USD价格
    private String profits_percent;// 瑞能利润百分比(Mfr Margin) -
    private String cost;// 物料成本
    private String sale_price;// 销售价格
    private String stop_price;// 停止价格 -
    private String remark;// 审批意见(WeenRemarks) -
    private int state;// 物料审批状态(行项目)-
    private int pState;// 报价单状态(主表)-
    private long main_id;// 报价单主表ID
    private long enquiry_detail_id;// 询价明细表ID
    private Date latest_time;// 操作时间
    private String latest_timeStr;// 操作人
    private String latest_userId;// 操作人
    private String ids;
    private String states;
    private int isAgree;// 是否同意
    private String isAgrees;
    private String salesOrg;
    private String type;
    private String isRepresent;// 是否是disti申述（Y是）
    ////////////// 主表字段
    private String customer_id;// 客户（代理商）

    private String cusGroup_id; // 客户集团
    private String endCustomer_id;// 终端客户名称（End Customer）
    private String ecGroup_id;// 终端客户集团名称（Customer）
    private String currency_code;// 报价货币单位（Currency）
    private String project_name;// 项目名称（Project）
    private String isDelivery;// 是否装配
    private String customer_name;// 客户（代理商）-
    private String endCustomer_name;// 终端客户名称（End Customer）-
    private String ecGroup_name;// 终端客户集团名称（Customer）-
    private String purchaseCustomer_id;// 采购客户（必填）（数据来源同EC）
    private String purchaseCustomer_name;
    private String disti_branch;
    private String pcGroup_id;// PC客户集团名称（Customer）-
    private String pcGroup_name;// PC客户集团名称（Customer）-
    private Date qStart_date;
    private Date qEnd_date;
    private String qStart_dateStr;
    private String qEnd_dateStr;
    private String pbMpp;// PB/MPP
    private String price_region;
    private String disti_region;
    private String ec_region;
    private String pc_regions;
    private String pc_region;
    private String pc_city;
    private String ec_city;
    private String file_name;
    private String file_path;
    private String create_userId;
    private double rate;
    private Date create_time;
    private String create_timeStr;
    private String create_user;
    private String ec_country;
    private String ec_state;
    private String pc_country;
    private String pc_state;
    private String ec_zip_code;
    private String pc_zip_code;
    private Long forward_id;
    private String forwarder;
    private String qm_price;// quote_center 价格
    private String isDRItem;
    private String disti_alias;
    private String disti_branch_alias;
    
    // 客户类型、segment、application by sst 20210928
    private String customertypename;
    private String segmentname;
    private String applicationname;
    private String customertypenamepur;
    private String segmentnamepur;
    private String applicationnamepur;

	public String getCustomertypename() {
		return customertypename;
	}

	public void setCustomertypename(String customertypename) {
		this.customertypename = customertypename;
	}

	public String getSegmentname() {
		return segmentname;
	}

	public void setSegmentname(String segmentname) {
		this.segmentname = segmentname;
	}

	public String getApplicationname() {
		return applicationname;
	}

	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}

	public String getCustomertypenamepur() {
		return customertypenamepur;
	}

	public void setCustomertypenamepur(String customertypenamepur) {
		this.customertypenamepur = customertypenamepur;
	}

	public String getSegmentnamepur() {
		return segmentnamepur;
	}

	public void setSegmentnamepur(String segmentnamepur) {
		this.segmentnamepur = segmentnamepur;
	}

	public String getApplicationnamepur() {
		return applicationnamepur;
	}

	public void setApplicationnamepur(String applicationnamepur) {
		this.applicationnamepur = applicationnamepur;
	}

    public String getIsDRItem() {
        return isDRItem;
    }

    public void setIsDRItem(String isDRItem) {
        this.isDRItem = isDRItem;
    }

    public String getEc_zip_code() {
        return ec_zip_code;
    }

    public void setEc_zip_code(String ec_zip_code) {
        this.ec_zip_code = ec_zip_code;
    }

    public String getPc_zip_code() {
        return pc_zip_code;
    }

    public void setPc_zip_code(String pc_zip_code) {
        this.pc_zip_code = pc_zip_code;
    }

    public String getQm_price() {
        return qm_price;
    }

    public void setQm_price(String qm_price) {
        this.qm_price = qm_price;
    }

    public String getForwarder() {
        return forwarder;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder;
    }

    public Long getForward_id() {
        return forward_id;
    }

    public void setForward_id(Long forward_id) {
        this.forward_id = forward_id;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getEc_country() {
        return ec_country;
    }

    public void setEc_country(String ec_country) {
        this.ec_country = ec_country;
    }

    public String getEc_state() {
        return ec_state;
    }

    public void setEc_state(String ec_state) {
        this.ec_state = ec_state;
    }

    public String getPc_country() {
        return pc_country;
    }

    public void setPc_country(String pc_country) {
        this.pc_country = pc_country;
    }

    public String getPc_state() {
        return pc_state;
    }

    public void setPc_state(String pc_state) {
        this.pc_state = pc_state;
    }

    /*public void setQty(Double qty) {
        this.qty = qty;
    }*/

    public String getCreate_timeStr() {
        return create_timeStr;
    }

    public void setCreate_timeStr(String create_timeStr) {
        this.create_timeStr = create_timeStr;
    }

    private int moq;
    private String create_userName;
    private String data_from;
    private double target_amount;
    private Date debit_start;
    private Date debit_end;
    private String debit_startStr;
    private String debit_endStr;
    private String auditorId;// 审批人ID
    private String debit_num;// 用于返利的编号，在agree时生成

    private String isAgreeStr;
    private String stateStr;

    public String getDebit_num() {
        return debit_num;
    }

    public void setDebit_num(String debit_num) {
        this.debit_num = debit_num;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public Date getDebit_start() {
        return debit_start;
    }

    public void setDebit_start(Date debit_start) {
        this.debit_start = debit_start;
    }

    public Date getDebit_end() {
        return debit_end;
    }

    public void setDebit_end(Date debit_end) {
        this.debit_end = debit_end;
    }

    public String getDebit_startStr() {
        return debit_startStr;
    }

    public void setDebit_startStr(String debit_startStr) {
        this.debit_startStr = debit_startStr;
    }

    public String getDebit_endStr() {
        return debit_endStr;
    }

    public void setDebit_endStr(String debit_endStr) {
        this.debit_endStr = debit_endStr;
    }

    public double getTarget_amount() {
        return target_amount;
    }

    public void setTarget_amount(double target_amount) {
        this.target_amount = target_amount;
    }

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public int getMoq() {
        return moq;
    }

    public void setMoq(int moq) {
        this.moq = moq;
    }

    public String getCreate_userName() {
        return create_userName;
    }

    public void setCreate_userName(String create_userName) {
        this.create_userName = create_userName;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_userId() {
        return create_userId;
    }

    public void setCreate_userId(String create_userId) {
        this.create_userId = create_userId;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getStart_dateStr() {
        return start_dateStr;
    }

    public void setStart_dateStr(String start_dateStr) {
        this.start_dateStr = start_dateStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getStop_price() {
        return stop_price;
    }

    public void setStop_price(String stop_price) {
        this.stop_price = stop_price;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEndCustomer_name() {
        return endCustomer_name;
    }

    public void setEndCustomer_name(String endCustomer_name) {
        this.endCustomer_name = endCustomer_name;
    }

    public String getEcGroup_name() {
        return ecGroup_name;
    }

    public void setEcGroup_name(String ecGroup_name) {
        this.ecGroup_name = ecGroup_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getRow_no() {
        return row_no;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public void setRow_no(String row_no) {
        this.row_no = row_no;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getDrNum() {
        return drNum;
    }

    public void setDrNum(String drNum) {
        this.drNum = drNum;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getTarget_resale() {
        return target_resale;
    }

    public void setTarget_resale(double target_resale) {
        this.target_resale = target_resale;
    }

    public double getTarget_cost() {
        return target_cost;
    }

    public void setTarget_cost(double target_cost) {
        this.target_cost = target_cost;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getCus_remark() {
        return cus_remark;
    }

    public void setCus_remark(String cus_remark) {
        this.cus_remark = cus_remark;
    }

    public double getSuggest_resale() {
        return suggest_resale;
    }

    public void setSuggest_resale(double suggest_resale) {
        this.suggest_resale = suggest_resale;
    }

    public double getSuggest_cost() {
        return suggest_cost;
    }

    public void setSuggest_cost(double suggest_cost) {
        this.suggest_cost = suggest_cost;
    }

    public String getCus_profits_percent() {
        return cus_profits_percent;
    }

    public void setCus_profits_percent(String cus_profits_percent) {
        this.cus_profits_percent = cus_profits_percent;
    }

    public String getProfits_percent() {
        return profits_percent;
    }

    public void setProfits_percent(String profits_percent) {
        this.profits_percent = profits_percent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getMain_id() {
        return main_id;
    }

    public void setMain_id(long main_id) {
        this.main_id = main_id;
    }

    public long getEnquiry_detail_id() {
        return enquiry_detail_id;
    }

    public void setEnquiry_detail_id(long enquiry_detail_id) {
        this.enquiry_detail_id = enquiry_detail_id;
    }

    public Date getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(Date latest_time) {
        this.latest_time = latest_time;
    }

    public String getLatest_userId() {
        return latest_userId;
    }

    public void setLatest_userId(String latest_userId) {
        this.latest_userId = latest_userId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCusGroup_id() {
        return cusGroup_id;
    }

    public void setCusGroup_id(String cusGroup_id) {
        this.cusGroup_id = cusGroup_id;
    }

    public String getEndCustomer_id() {
        return endCustomer_id;
    }

    public void setEndCustomer_id(String endCustomer_id) {
        this.endCustomer_id = endCustomer_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEcGroup_id() {
        return ecGroup_id;
    }

    public void setEcGroup_id(String ecGroup_id) {
        this.ecGroup_id = ecGroup_id;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(String isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getEnd_dateStr() {
        return end_dateStr;
    }

    public void setEnd_dateStr(String end_dateStr) {
        this.end_dateStr = end_dateStr;
    }

    public double getRes_qty() {
        return res_qty;
    }

    public void setRes_qty(double res_qty) {
        this.res_qty = res_qty;
    }

    public String getPurchaseCustomer_id() {
        return purchaseCustomer_id;
    }

    public void setPurchaseCustomer_id(String purchaseCustomer_id) {
        this.purchaseCustomer_id = purchaseCustomer_id;
    }

    public String getPurchaseCustomer_name() {
        return purchaseCustomer_name;
    }

    public void setPurchaseCustomer_name(String purchaseCustomer_name) {
        this.purchaseCustomer_name = purchaseCustomer_name;
    }

    public String getTarget_margin() {
        return target_margin;
    }

    public void setTarget_margin(String target_margin) {
        this.target_margin = target_margin;
    }

    public Date getqStart_date() {
        return qStart_date;
    }

    public void setqStart_date(Date qStart_date) {
        this.qStart_date = qStart_date;
    }

    public Date getqEnd_date() {
        return qEnd_date;
    }

    public void setqEnd_date(Date qEnd_date) {
        this.qEnd_date = qEnd_date;
    }

    public String getqStart_dateStr() {
        return qStart_dateStr;
    }

    public void setqStart_dateStr(String qStart_dateStr) {
        this.qStart_dateStr = qStart_dateStr;
    }

    public String getqEnd_dateStr() {
        return qEnd_dateStr;
    }

    public void setqEnd_dateStr(String qEnd_dateStr) {
        this.qEnd_dateStr = qEnd_dateStr;
    }

    public String getIsRepresent() {
        return isRepresent;
    }

    public void setIsRepresent(String isRepresent) {
        this.isRepresent = isRepresent;
    }

    public String getPcGroup_id() {
        return pcGroup_id;
    }

    public void setPcGroup_id(String pcGroup_id) {
        this.pcGroup_id = pcGroup_id;
    }

    public String getPcGroup_name() {
        return pcGroup_name;
    }

    public void setPcGroup_name(String pcGroup_name) {
        this.pcGroup_name = pcGroup_name;
    }

    public String getPbMpp() {
        return pbMpp;
    }

    public void setPbMpp(String pbMpp) {
        this.pbMpp = pbMpp;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    public String getProduct_dateStr() {
        return product_dateStr;
    }

    public void setProduct_dateStr(String product_dateStr) {
        this.product_dateStr = product_dateStr;
    }

    public int getpState() {
        return pState;
    }

    public void setpState(int pState) {
        this.pState = pState;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public String getIsAgrees() {
        return isAgrees;
    }

    public void setIsAgrees(String isAgrees) {
        this.isAgrees = isAgrees;
    }

    public String getPrice_region() {
        return price_region;
    }

    public void setPrice_region(String price_region) {
        this.price_region = price_region;
    }

    public String getDisti_region() {
        return disti_region;
    }

    public void setDisti_region(String disti_region) {
        this.disti_region = disti_region;
    }

    public String getEc_region() {
        return ec_region;
    }

    public void setEc_region(String ec_region) {
        this.ec_region = ec_region;
    }

    public String getPc_region() {
        return pc_region;
    }

    public void setPc_region(String pc_region) {
        this.pc_region = pc_region;
    }

    public String getPc_city() {
        return pc_city;
    }

    public void setPc_city(String pc_city) {
        this.pc_city = pc_city;
    }

    public String getEc_city() {
        return ec_city;
    }

    public void setEc_city(String ec_city) {
        this.ec_city = ec_city;
    }

    public double getSuggest_cost_usd() {
        return suggest_cost_usd;
    }

    public void setSuggest_cost_usd(double suggest_cost_usd) {
        this.suggest_cost_usd = suggest_cost_usd;
    }

    public String getLatest_timeStr() {
        return latest_timeStr;
    }

    public void setLatest_timeStr(String latest_timeStr) {
        this.latest_timeStr = latest_timeStr;
    }

    public String getIsAgreeStr() {
        return isAgreeStr;
    }

    public void setIsAgreeStr(String isAgreeStr) {
        this.isAgreeStr = isAgreeStr;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getPc_regions() {
        return pc_regions;
    }

    public void setPc_regions(String pc_regions) {
        this.pc_regions = pc_regions;
    }

    /*public void setTarget_cost(Double target_cost) {
        this.target_cost = target_cost;
    }*/

    public String getStrMaterialId() {
        return strMaterialId;
    }

    public void setStrMaterialId(String strMaterialId) {
        this.strMaterialId = strMaterialId;
    }

    public String getDisti_alias() {
        return disti_alias;
    }

    public void setDisti_alias(String disti_alias) {
        this.disti_alias = disti_alias;
    }

    public String getDisti_branch_alias() {
        return disti_branch_alias;
    }

    public void setDisti_branch_alias(String disti_branch_alias) {
        this.disti_branch_alias = disti_branch_alias;
    }

}
