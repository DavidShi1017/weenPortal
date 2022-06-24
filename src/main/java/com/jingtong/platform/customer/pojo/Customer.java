package com.jingtong.platform.customer.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 经销商客户信息
 * @author yw
 *
 */
public class Customer extends SearchInfo{
	private long id;
	private String customer_code;//客户编码ERP
	private String customer_name;//客户名称
	private String global_account;//全球账号(即customerGroup)暂取尼森标识的值
	private String sales_org;//销售组织1级
	private String sale_office;//销售办公室3级
	private String district;//区域2级
	private String sales_orgName;//销售组织1级
	private String sale_officeName;//销售办公室3级
	private String districtName;//区域2级
	private String sales;//联系人
	private String country;//国家代码
	private String countryName;
	private String ship_method;//运输方式
	private String customer_type;//客户类型( 01 OEM,02 Disti,03 EMS,04 subcon, 05 Rep ,06 CP)
	private String design_customer;//设计客户
	private String segment;//
	private String currency_code;//货币代码
	private String address;//地址
	private String contact_name;//联系人
	private String tel;//联系人
	private int isAssigned;//是否分配
	private int state;//状态
	private String audit_opinion;//审核意见
	private String remark;//备注
	private int sync_state;//同步状态（1同步，0未同步）
	private Date sync_time;//同步时间
	private String sync_userId;//同步用户
	private String sysnc_exception;	//同步异常
	private Date create_time;//建立时间
	private String create_userId;//建立用户
	private String org_code;//组织代码
	private Date modify_time;//修改时间
	private String modify_userId;//修改用户
	private String states;
	private String nielsenRemark;//尼森标识
	private String search;//查询参数
	private String loginId;//登陆账号
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getGlobal_account() {
		return global_account;
	}
	public void setGlobal_account(String global_account) {
		this.global_account = global_account;
	}
	public String getSales_org() {
		return sales_org;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getShip_method() {
		return ship_method;
	}
	public void setShip_method(String ship_method) {
		this.ship_method = ship_method;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getDesign_customer() {
		return design_customer;
	}
	public void setDesign_customer(String design_customer) {
		this.design_customer = design_customer;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public int getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(int isAssigned) {
		this.isAssigned = isAssigned;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAudit_opinion() {
		return audit_opinion;
	}
	public void setAudit_opinion(String audit_opinion) {
		this.audit_opinion = audit_opinion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Date getCreate_time() {
		return create_time;
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
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getModify_userId() {
		return modify_userId;
	}
	public void setModify_userId(String modify_userId) {
		this.modify_userId = modify_userId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getSales_orgName() {
		return sales_orgName;
	}
	public void setSales_orgName(String sales_orgName) {
		this.sales_orgName = sales_orgName;
	}
	public String getSale_officeName() {
		return sale_officeName;
	}
	public void setSale_officeName(String sale_officeName) {
		this.sale_officeName = sale_officeName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getNielsenRemark() {
		return nielsenRemark;
	}
	public void setNielsenRemark(String nielsenRemark) {
		this.nielsenRemark = nielsenRemark;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
}
