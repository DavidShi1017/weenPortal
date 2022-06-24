package com.jingtong.platform.customer.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 用戶信息對象
 * 
 * @author mk
 * 
 * createDate 2013-12
 */
public class CustomerUser extends SearchInfo {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userId;//emp_id
	private String loginId;//emp_code
	private String passWd;//emp_psd
	private String userName;//emp_name
	private String email;//emp_email
	private String phone;//emp_phone
	private String busMobilephone;//bus_mobilephone
	
	private String customer_code;
	private String customer_name;
	private String currency_code;//货币编码
	private String sales_org;//销售组织1级
	private String sale_office;//销售办公室2级
	private String district;//区域3级
	private String global_account;//全球账号(即customerGroup)
	private String country;
	
	
	private String userShowName;
	private String userState;
	private String custType;
	private String mobile;
	private String empShortmsgbook;
	private String smsUserTypeId;
	private Long chargeId;
	private String hqSign;//是否总部1是
	private String kunnrSign;   
	private String customerSign;//代理商标记	Y是customer账号
	private String isOffice;
	private Date paswdSignDate;
	private String orgId;
	private String sapOrgId;
	private String orgName;
	private String idCard;
	private String workFax;
	private String homePhone;
	private String startDate;
	private String address;
	private String sex;
	private String haveMail;
	private String remark;
	private String positionTypeName;
	private String[] orgIds;
	private String orgStr;
	private String questionLinkId;
	private String reason;
	private String roleIds;// 岗位ID，角色
	private String stationState;// 岗位状态
	private String ids;// station_user表的ID 主键
	private String[] userIds;
	private String empPostId;// 职务ID
	private String empPostName;// 职务ID
	private String expressly;// 明文密码
	private String stationNames;// 职位名称 多个
    private String isMainStation;//主次岗位区分：Y：主岗位,N次岗位	    
    private String bhxjFlag; //组织向下追溯   
    private String isFirst;   
    private String trafficExpense; //交通费   
    private String mealAllowances; //餐费补贴    
    private String communicationFees; //通讯费   
    private String empUserId;  //员工编号（公司内部编号）    
    private String userAttr;//员工属性：分配不同权限 org,kunnr,cust
    private String deptCode;//部门编号
    private String deptName;//部门名称
    private String purchaseGroupId;//人员对应采购组（yw）
	private String posName;//代替员工属性
	private String posId;
	private String status;    
	private String sales;
	private Date create_date;
	private String create_user;
	private String modify_user;
	private Date modify_date;
	

	public Date getModify_date() {
		return modify_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getGlobal_account() {
		return global_account;
	}

	public void setGlobal_account(String global_account) {
		this.global_account = global_account;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAttr() {
		return userAttr;
	}

	public void setUserAttr(String userAttr) {
		this.userAttr = userAttr;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getStationNames() {
		return stationNames;
	}

	public void setStationNames(String stationNames) {
		this.stationNames = stationNames;
	}

	public String getExpressly() {
		return expressly;
	}

	public void setExpressly(String expressly) {
		this.expressly = expressly;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHqSign() {
		return hqSign;
	}

	public void setHqSign(String hqSign) {
		this.hqSign = hqSign;
	}

	public String getKunnrSign() {
		return kunnrSign;
	}

	public void setKunnrSign(String kunnrSign) {
		this.kunnrSign = kunnrSign;
	}

	public String getIsOffice() {
		return isOffice;
	}

	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}

	public Date getPaswdSignDate() {
		return paswdSignDate;
	}

	public void setPaswdSignDate(Date paswdSignDate) {
		this.paswdSignDate = paswdSignDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getHaveMail() {
		return haveMail;
	}

	public void setHaveMail(String haveMail) {
		this.haveMail = haveMail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPositionTypeName() {
		return positionTypeName;
	}

	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}

	public String getBusMobilephone() {
		return busMobilephone;
	}

	public void setBusMobilephone(String busMobilephone) {
		this.busMobilephone = busMobilephone;
	}

	public String getEmpShortmsgbook() {
		return empShortmsgbook;
	}

	public void setEmpShortmsgbook(String empShortmsgbook) {
		this.empShortmsgbook = empShortmsgbook;
	}

	public String getSmsUserTypeId() {
		return smsUserTypeId;
	}

	public void setSmsUserTypeId(String smsUserTypeId) {
		this.smsUserTypeId = smsUserTypeId;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}
	public String getQuestionLinkId() {
		return questionLinkId;
	}

	public void setQuestionLinkId(String questionLinkId) {
		this.questionLinkId = questionLinkId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getStationState() {
		return stationState;
	}

	public void setStationState(String stationState) {
		this.stationState = stationState;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String getEmpPostId() {
		return empPostId;
	}

	public void setEmpPostId(String empPostId) {
		this.empPostId = empPostId;
	}

	public String getEmpPostName() {
		return empPostName;
	}

	public void setEmpPostName(String empPostName) {
		this.empPostName = empPostName;
	}

	public String getIsMainStation() {
		return isMainStation;
	}

	public void setIsMainStation(String isMainStation) {
		this.isMainStation = isMainStation;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getTrafficExpense() {
		return trafficExpense;
	}

	public void setTrafficExpense(String trafficExpense) {
		this.trafficExpense = trafficExpense;
	}

	public String getMealAllowances() {
		return mealAllowances;
	}

	public void setMealAllowances(String mealAllowances) {
		this.mealAllowances = mealAllowances;
	}

	public String getCommunicationFees() {
		return communicationFees;
	}

	public void setCommunicationFees(String communicationFees) {
		this.communicationFees = communicationFees;
	}

	public String getEmpUserId() {
		return empUserId;
	}

	public void setEmpUserId(String empUserId) {
		this.empUserId = empUserId;
	}
	public String getCustomerSign() {
		return customerSign;
	}

	public void setCustomerSign(String customerSign) {
		this.customerSign = customerSign;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPurchaseGroupId() {
		return purchaseGroupId;
	}

	public void setPurchaseGroupId(String purchaseGroupId) {
		this.purchaseGroupId = purchaseGroupId;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getSales_org() {
		return sales_org;
	}

	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}

	public String getSale_office() {
		return sale_office;
	}

	public void setSale_office(String sale_office) {
		this.sale_office = sale_office;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSapOrgId() {
		return sapOrgId;
	}

	public void setSapOrgId(String sapOrgId) {
		this.sapOrgId = sapOrgId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
