package com.jingtong.platform.enquiry.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 询价主表
 * @author yw
 *
 */
public class Enquiry extends SearchInfo{
	private long id;
	private String enquiry_id;//询价单号
	private String type_id;//报价类型（Quote Type
	private String channel_id;//代理商集团名称（Channel）
	private String branch_id;//代理商名称（Channel Branch）
	private String cusGroup_id;//终端客户集团名称（Customer）
	private String customer_id;
	private String endCustomer_id;//终端客户名称（End Customer）
	private String ecGroup_id;
	private String currency_code;//报价货币单位（Currency）
	private String project_name;// 项目名称（Project）
	private String isDelivery;//装配方式（Assembly
	private Date start_date;//报价开始时间
	private Date latest_expire;//报价有效截止时间
	private String start_dateStr;
	private String latest_expireStr;
	private int state;
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//操作时间
	private String latest_userId;//操作人
	private String org_code;//所属组织
	private String remark;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEnquiry_id() {
		return enquiry_id;
	}
	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
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

	
	
	
}
