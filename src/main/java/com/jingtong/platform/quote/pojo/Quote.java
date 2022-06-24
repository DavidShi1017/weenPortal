package com.jingtong.platform.quote.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 报价信息主表
 * 
 * @author yw
 *
 */
public class Quote extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String quote_id;// 报价单号-
    private String payer_to;
    private String pricing_region;
    private String type_id;// 报价类型（Quote Type-
    private String channel_id;// 代理商集团名称（Channel）
    private String branch_id;// 代理商名称（Channel Branch）
    private String customer_id;// 客户（代理商）-
    private String customer_name;// 客户（代理商）-
    private String cusGroup_id; // 客户集团-
    private String disti_branch;
    private String endCustomer_id;// 终端客户名称（End Customer，最终使用的客户，不会在售给下一级，非必填）-
    private String endCustomer_name;// 终端客户名称（End Customer）-
    private String ecGroup_id;// 终端客户集团名称（Customer）-
    private String ecGroup_name;// 终端客户集团名称（Customer）-
    private String pcGroup_id;// PC客户集团名称（Customer）-
    private String pcGroup_name;// PC客户集团名称（Customer）-
    private String pc_city;
    private String ec_city;
    private String pc_state;
    private String ec_state;
    private String currency_code;// 报价货币单位（Currency）-
    private String project_name;// 项目名称（Project）
    private String isDelivery;// 是否装配
    private String purchaseCustomer_id;// PC采购客户（直接从disti那采购的客户必填）（数据来源同EC）
    private String purchaseCustomer_name;// PC
    private Date start_date;// 报价开始时间
    private Date latest_expire;// 报价有效截止时间
    private String start_dateStr;
    private String latest_expireStr;
    private String salesOrg;
    private String approver;// 审批级别（1销售，2商务经理，3市场总监）
    /**
     * 状态 0提交销售审批，1提交商务经理审批，2提交市场总监审批， 3销售审批通过，4商务审批通过，5市场总监审批通过，
     * 6销售退回，7商务退回，8市场部总监退回，9删除
     */
    private int state;// 报价单状态
    private long enquiry_mainId;// 询价单主表ID
    private String enquiry_id;// 询价单号
    private int sync_state;// 同步状态（1表已同步、0表未同步）
    private Date sync_time;// 同步时间
    private String sync_userId;// 同步用户
    private String sysnc_exception;// 同步异常
    private String create_userId;//
    private String create_userName;//
    private double total_amount;// 行项目金额汇总
    private String total_type;// 总额类型（USD/EUR）
    private Date create_time;
    private String create_timeStr;
    private Date latest_time;// 操作时间
    private String latest_userId;// 操作人
    private String org_code;// 所属组织
    private String remark;//
    private String states;//
    private String material_name;
    private String file_name;
    private String file_path;
    private String roleId;
    private String cost;
    private double rate;
    private String noPCEC;
    private String edi_pc_country;
    private String edi_pc_province;
    private String edi_pc_city;
    private String edi_pc_zip;
    private String edi_ec_country;
    private String edi_ec_province;
    private String edi_ec_city;
    private String edi_ec_zip;
    private String auditorId;// 审批人ID
    private String debit_num;
    private Long forward_id;// 报价单状态
    
    // 客户类型、segment、application by sst 20210927
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

    public Long getForward_id() {
        return forward_id;
    }

    public void setForward_id(Long forward_id) {
        this.forward_id = forward_id;
    }

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

    public String getEdi_pc_country() {
        return edi_pc_country;
    }

    public void setEdi_pc_country(String edi_pc_country) {
        this.edi_pc_country = edi_pc_country;
    }

    public String getEdi_pc_province() {
        return edi_pc_province;
    }

    public void setEdi_pc_province(String edi_pc_province) {
        this.edi_pc_province = edi_pc_province;
    }

    public String getEdi_pc_city() {
        return edi_pc_city;
    }

    public void setEdi_pc_city(String edi_pc_city) {
        this.edi_pc_city = edi_pc_city;
    }

    public String getEdi_pc_zip() {
        return edi_pc_zip;
    }

    public void setEdi_pc_zip(String edi_pc_zip) {
        this.edi_pc_zip = edi_pc_zip;
    }

    public String getEdi_ec_country() {
        return edi_ec_country;
    }

    public void setEdi_ec_country(String edi_ec_country) {
        this.edi_ec_country = edi_ec_country;
    }

    public String getEdi_ec_province() {
        return edi_ec_province;
    }

    public void setEdi_ec_province(String edi_ec_province) {
        this.edi_ec_province = edi_ec_province;
    }

    public String getEdi_ec_city() {
        return edi_ec_city;
    }

    public void setEdi_ec_city(String edi_ec_city) {
        this.edi_ec_city = edi_ec_city;
    }

    public String getEdi_ec_zip() {
        return edi_ec_zip;
    }

    public void setEdi_ec_zip(String edi_ec_zip) {
        this.edi_ec_zip = edi_ec_zip;
    }

    public String getNoPCEC() {
        return noPCEC;
    }

    public void setNoPCEC(String noPCEC) {
        this.noPCEC = noPCEC;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTotal_type() {
        return total_type;
    }

    public void setTotal_type(String total_type) {
        this.total_type = total_type;
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

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getStart_dateStr() {
        return start_dateStr;
    }

    public void setStart_dateStr(String start_dateStr) {
        this.start_dateStr = start_dateStr;
    }

    public String getLatest_expireStr() {
        return latest_expireStr;
    }

    public void setLatest_expireStr(String latest_expireStr) {
        this.latest_expireStr = latest_expireStr;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
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

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEndCustomer_id() {
        return endCustomer_id;
    }

    public void setEndCustomer_id(String endCustomer_id) {
        this.endCustomer_id = endCustomer_id;
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

    public String getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(String enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public String getCusGroup_id() {
        return cusGroup_id;
    }

    public void setCusGroup_id(String cusGroup_id) {
        this.cusGroup_id = cusGroup_id;
    }

    public String getEcGroup_id() {
        return ecGroup_id;
    }

    public void setEcGroup_id(String ecGroup_id) {
        this.ecGroup_id = ecGroup_id;
    }

    public String getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(String isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getLatest_expire() {
        return latest_expire;
    }

    public void setLatest_expire(Date latest_expire) {
        this.latest_expire = latest_expire;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getEnquiry_mainId() {
        return enquiry_mainId;
    }

    public void setEnquiry_mainId(long enquiry_mainId) {
        this.enquiry_mainId = enquiry_mainId;
    }

    public int getSync_state() {
        return sync_state;
    }

    public void setSync_state(int sync_state) {
        this.sync_state = sync_state;
    }

    public Date getSync_time() {
        return sync_time;
    }

    public void setSync_time(Date sync_time) {
        this.sync_time = sync_time;
    }

    public String getSync_userId() {
        return sync_userId;
    }

    public void setSync_userId(String sync_userId) {
        this.sync_userId = sync_userId;
    }

    public String getSysnc_exception() {
        return sysnc_exception;
    }

    public void setSysnc_exception(String sysnc_exception) {
        this.sysnc_exception = sysnc_exception;
    }

    public String getCreate_userId() {
        return create_userId;
    }

    public void setCreate_userId(String create_userId) {
        this.create_userId = create_userId;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
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

    public String getCreate_timeStr() {
        return create_timeStr;
    }

    public void setCreate_timeStr(String create_timeStr) {
        this.create_timeStr = create_timeStr;
    }

    public String getCreate_userName() {
        return create_userName;
    }

    public void setCreate_userName(String create_userName) {
        this.create_userName = create_userName;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
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

    public String getPc_state() {
        return pc_state;
    }

    public void setPc_state(String pc_state) {
        this.pc_state = pc_state;
    }

    public String getEc_state() {
        return ec_state;
    }

    public void setEc_state(String ec_state) {
        this.ec_state = ec_state;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public String getPricing_region() {
        return pricing_region;
    }

    public void setPricing_region(String pricing_region) {
        this.pricing_region = pricing_region;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getPayer_to() {
        return payer_to;
    }

    public void setPayer_to(String payer_to) {
        this.payer_to = payer_to;
    }

}
