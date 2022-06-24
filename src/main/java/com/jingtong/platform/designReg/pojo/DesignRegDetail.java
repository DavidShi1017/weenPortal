package com.jingtong.platform.designReg.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DesignRegDetail extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 明细表字段
    private Long id;
    private int row_no;
    private String drNum;
    private String material_id;
    private String material_name;
    private double price;
    private double value;
    private double equip_usage;
    private int project_state;
    private int primaryProState;// 原始projectState（仅用于状态修改时判断）
    private String equip_type;
    private Date start_date;
    private Date end_date;
    private Date product_date;
    private Date design_win;
    private String design_winStr;
    private String product_dateStr;
    private String start_dateStr;
    private String end_dateStr;
    private String project_name;
    private String cus_remark;
    private String remark;
    private String weencomments;
    
    private int state;
    private long main_id;
    private String create_userId;
    private Date create_time;
    private String latest_userId;
    private Date latest_time;
    private String latest_deptId;
    private String ids;
    private String states;
    private String isCheck;
    private String salesOrg;// 审批销售对应的组织
    private String shipPrice;

    // 关联主表字段
    private String cus_groupId;
    private String customer_id;
    private String customer_name;
    private String endCus_groupId;
    private String endCus_groupName;
    private String endCus_id;
    private String endCus_name;
    private String ec_city;
    private String ec_contact;
    private String usage_amount;
    private String tel;
    private String disti_branch;
    private String cost;
    private String moq;
    private String pbMpp;
    private String create_userName;
    private String auditorId;// 审批人ID
    private String disti_alias;
    private String disti_branch_alias;

    private Long saler_design_status;

    private String audit_person;
    private String audit_time;
    private String audit_person2;
    private String audit_time2;
    private String audit_person3;
    private String audit_time3;
    private String audit_person4;
    private String audit_time4;
    private String estimated_share;
    private String dr_type;
    private String sale_office;

    private String saler_design_statusStr;

    private String mp_scheduleStr;

    private String drtype_def;
    private String dw_cal;
    private String dr_typeStr;
    private double dw_value;
    private String inputRemark;
    
    private String customerTypeName;
    private String segmentName;
    private String applicationName;
    
    public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
    
    public String getWeencomments() {
    	
    	return weencomments;
    }
    
    public void setWeencomments(String _weencomments) {    	
    	this.weencomments = _weencomments;
    }

    public String getMp_scheduleStr() {
        return mp_scheduleStr;
    }

    public void setMp_scheduleStr(String mp_scheduleStr) {
        this.mp_scheduleStr = mp_scheduleStr;
    }

    public String getSaler_design_statusStr() {
        return saler_design_statusStr;
    }

    public void setSaler_design_statusStr(String saler_design_statusStr) {
        this.saler_design_statusStr = saler_design_statusStr;
    }

    public String getSale_office() {
        return sale_office;
    }

    public void setSale_office(String sale_office) {
        this.sale_office = sale_office;
    }

    public String getEstimated_share() {
        return estimated_share;
    }

    public void setEstimated_share(String estimated_share) {
        this.estimated_share = estimated_share;
    }

    public String getDr_type() {
        return dr_type;
    }

    public void setDr_type(String dr_type) {
        this.dr_type = dr_type;
    }

    public String getAudit_person2() {
        return audit_person2;
    }

    public void setAudit_person2(String audit_person2) {
        this.audit_person2 = audit_person2;
    }

    public String getAudit_time2() {
        return audit_time2;
    }

    public void setAudit_time2(String audit_time2) {
        this.audit_time2 = audit_time2;
    }

    public String getAudit_person3() {
        return audit_person3;
    }

    public void setAudit_person3(String audit_person3) {
        this.audit_person3 = audit_person3;
    }

    public String getAudit_time3() {
        return audit_time3;
    }

    public void setAudit_time3(String audit_time3) {
        this.audit_time3 = audit_time3;
    }

    public String getAudit_person4() {
        return audit_person4;
    }

    public void setAudit_person4(String audit_person4) {
        this.audit_person4 = audit_person4;
    }

    public String getAudit_time4() {
        return audit_time4;
    }

    public void setAudit_time4(String audit_time4) {
        this.audit_time4 = audit_time4;
    }

    public String getAudit_person() {
        return audit_person;
    }

    public void setAudit_person(String audit_person) {
        this.audit_person = audit_person;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public Long getSaler_design_status() {
        return saler_design_status;
    }

    public void setSaler_design_status(Long saler_design_status) {
        this.saler_design_status = saler_design_status;
    }

    public String getDesign_winStr() {
        return design_winStr;
    }

    public void setDesign_winStr(String design_winStr) {
        this.design_winStr = design_winStr;
    }

    public Date getDesign_win() {
        return design_win;
    }

    public void setDesign_win(Date design_win) {
        this.design_win = design_win;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getCreate_userName() {
        return create_userName;
    }

    public void setCreate_userName(String create_userName) {
        this.create_userName = create_userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getPbMpp() {
        return pbMpp;
    }

    public void setPbMpp(String pbMpp) {
        this.pbMpp = pbMpp;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public int getPrimaryProState() {
        return primaryProState;
    }

    public void setPrimaryProState(int primaryProState) {
        this.primaryProState = primaryProState;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public String getDrNum() {
        return drNum;
    }

    public void setDrNum(String drNum) {
        this.drNum = drNum;
    }

    public String getProduct_dateStr() {
        return product_dateStr;
    }

    public void setProduct_dateStr(String product_dateStr) {
        this.product_dateStr = product_dateStr;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEquip_usage() {
        return equip_usage;
    }

    public void setEquip_usage(double equip_usage) {
        this.equip_usage = equip_usage;
    }

    public int getProject_state() {
        return project_state;
    }

    public void setProject_state(int project_state) {
        this.project_state = project_state;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCus_remark() {
        return cus_remark;
    }

    public void setCus_remark(String cus_remark) {
        this.cus_remark = cus_remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getMain_id() {
        return main_id;
    }

    public void setMain_id(long main_id) {
        this.main_id = main_id;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
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

    public String getLatest_userId() {
        return latest_userId;
    }

    public void setLatest_userId(String latest_userId) {
        this.latest_userId = latest_userId;
    }

    public Date getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(Date latest_time) {
        this.latest_time = latest_time;
    }

    public String getLatest_deptId() {
        return latest_deptId;
    }

    public void setLatest_deptId(String latest_deptId) {
        this.latest_deptId = latest_deptId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getEquip_type() {
        return equip_type;
    }

    public void setEquip_type(String equip_type) {
        this.equip_type = equip_type;
    }

    public String getCus_groupId() {
        return cus_groupId;
    }

    public void setCus_groupId(String cus_groupId) {
        this.cus_groupId = cus_groupId;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEndCus_groupId() {
        return endCus_groupId;
    }

    public void setEndCus_groupId(String endCus_groupId) {
        this.endCus_groupId = endCus_groupId;
    }

    public String getEndCus_id() {
        return endCus_id;
    }

    public void setEndCus_id(String endCus_id) {
        this.endCus_id = endCus_id;
    }

    public String getEc_contact() {
        return ec_contact;
    }

    public void setEc_contact(String ec_contact) {
        this.ec_contact = ec_contact;
    }

    public String getUsage_amount() {
        return usage_amount;
    }

    public void setUsage_amount(String usage_amount) {
        this.usage_amount = usage_amount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEndCus_groupName() {
        return endCus_groupName;
    }

    public void setEndCus_groupName(String endCus_groupName) {
        this.endCus_groupName = endCus_groupName;
    }

    public String getEndCus_name() {
        return endCus_name;
    }

    public void setEndCus_name(String endCus_name) {
        this.endCus_name = endCus_name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getEc_city() {
        return ec_city;
    }

    public void setEc_city(String ec_city) {
        this.ec_city = ec_city;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(String shipPrice) {
        this.shipPrice = shipPrice;
    }

    public String getDrtype_def() {
        return drtype_def;
    }

    public void setDrtype_def(String drtype_def) {
        this.drtype_def = drtype_def;
    }

    public String getDw_cal() {
        return dw_cal;
    }

    public void setDw_cal(String dw_cal) {
        this.dw_cal = dw_cal;
    }

    public String getDr_typeStr() {
        return dr_typeStr;
    }

    public void setDr_typeStr(String dr_typeStr) {
        this.dr_typeStr = dr_typeStr;
    }

    public double getDw_value() {
        return dw_value;
    }

    public void setDw_value(double dw_value) {
        this.dw_value = dw_value;
    }

    public String getInputRemark() {
        return inputRemark;
    }

    public void setInputRemark(String inputRemark) {
        this.inputRemark = inputRemark;
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
