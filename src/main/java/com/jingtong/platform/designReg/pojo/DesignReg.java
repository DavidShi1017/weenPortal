package com.jingtong.platform.designReg.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DesignReg extends SearchInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String drNum;
    private String cus_groupId;
    private String customer_id;
    private String customer_name;
    private String endCus_groupId;
    private String endCus_groupName;
    private String endCus_id;
    private String endCus_name;
    private String ec_city;
    private String ec_contact;
    private Date start_date;
    private Date end_date;
    private String start_dateStr;
    private String end_dateStr;
    private String project_name;
    private String usage_amount;
    private String tel;
    private String equip_type;
    private String equip_name;
    private int state;
    private String remark;
    private String create_userId;
    private Date create_time;
    private String latest_userId;
    private Date latest_time;
    private String latest_deptId;
    private String isCheck;
    private Date mp_schedule;// 量产时间
    private String mp_scheduleStr;// 量产时间
    private double total_amount;// 总进货价
    private String total_type;// 总额类型（USD/EUR）
    private String ec_state;// ec状态
    private String disti_branch;
    private String currency_code;
    private String office_id;
    private String create_userName;
    private String auditorId;// 审批人ID
    private String file_name;
    private String file_path;
    private String estimated_share;
    private String dr_type;
    private String forWho;
    private String sale_office;
    private String disti_alias;
    private String disti_branch_alias;
    private int isAllDW;
    
    /*
     * 增加custtype\segment\application字段 by sst 20210922
     */
    private String customerTypeId;
    private String segmentId;
    private String applicationId;

	private String customerTypeName;
    private String segmentName;
    private String applicationName;
    
    public String getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

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

    
   // private String weencomments;
  //  public String getWeencomments() {
    	
  //  	return weencomments;
   // }
    
  //  public void setWeencomments(String _weencomments) {    	
  //  	this.weencomments = _weencomments;
   // }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
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

    public String getEquip_name() {
        return equip_name;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
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

    public String getDisti_branch() {
        return disti_branch;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getTotal_type() {
        return total_type;
    }

    public void setTotal_type(String total_type) {
        this.total_type = total_type;
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

    public String getDrNum() {
        return drNum;
    }

    public void setDrNum(String drNum) {
        this.drNum = drNum;
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

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public String getEquip_type() {
        return equip_type;
    }

    public void setEquip_type(String equip_type) {
        this.equip_type = equip_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getMp_schedule() {
        return mp_schedule;
    }

    public void setMp_schedule(Date mp_schedule) {
        this.mp_schedule = mp_schedule;
    }

    public String getMp_scheduleStr() {
        return mp_scheduleStr;
    }

    public void setMp_scheduleStr(String mp_scheduleStr) {
        this.mp_scheduleStr = mp_scheduleStr;
    }

    public String getEc_city() {
        return ec_city;
    }

    public void setEc_city(String ec_city) {
        this.ec_city = ec_city;
    }

    public String getEc_state() {
        return ec_state;
    }

    public void setEc_state(String ec_state) {
        this.ec_state = ec_state;
    }

    public String getSale_office() {
        return sale_office;
    }

    public void setSale_office(String sale_office) {
        this.sale_office = sale_office;
    }

    public int getIsAllDW() {
        return isAllDW;
    }

    public void setIsAllDW(int isAllDW) {
        this.isAllDW = isAllDW;
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
