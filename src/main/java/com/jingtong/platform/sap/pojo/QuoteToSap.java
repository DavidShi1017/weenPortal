package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 报价单接口结构_I_HEADER
 * 
 *
 */
public class QuoteToSap extends SearchInfo{
	private long id;
	private String quoteId; //报价单编号 
	private String quoteType; //报价类型（Quote Type） 
	private String customerGroup; //客户集团（Customer Group） 
	private String customer; //客户名称（Customer） 
	private String ecGroup; //终端客户集团（EC Group） 
	private String endCustomer; //终端客户（End Customer） 
	private String totalAmountv; //总计进货金额（Total Amount） 
	private String currency; //报价货币单位（Currency） 
	private String project; //项目名称（Project） 
	private String assembly; //是否配送（Assembly） 
	private String startTime; //报价开始时间 
	private String endTime; //报价有效截止时间
	private String state; //报价单状态 
	private String enquiryMainId; //询价单主表ID
	private String syncState; //同步状态（1表已同步、0表未同步） 
	private String syncTime; //同步时间
	private String syncUserId; //同步用户
	private String sysncException; //同步异常
	private String createTime; //建立时间
	private String createUserId; //建立用户 
	private String latestTime; //操作时间
	private String latestUserId; //操作用户
	private String orgCode; //所属组织 
	
	
	private String[] ids;//查询条件

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getEcGroup() {
		return ecGroup;
	}
	public void setEcGroup(String ecGroup) {
		this.ecGroup = ecGroup;
	}
	public String getEndCustomer() {
		return endCustomer;
	}
	public void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	public String getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}
	public String getSyncUserId() {
		return syncUserId;
	}
	public void setSyncUserId(String syncUserId) {
		this.syncUserId = syncUserId;
	}
	public String getSysncException() {
		return sysncException;
	}
	public void setSysncException(String sysncException) {
		this.sysncException = sysncException;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getLatestUserId() {
		return latestUserId;
	}
	public void setLatestUserId(String latestUserId) {
		this.latestUserId = latestUserId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getTotalAmountv() {
		return totalAmountv;
	}
	public void setTotalAmountv(String totalAmountv) {
		this.totalAmountv = totalAmountv;
	}
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEnquiryMainId() {
		return enquiryMainId;
	}
	public void setEnquiryMainId(String enquiryMainId) {
		this.enquiryMainId = enquiryMainId;
	}
	public String getSyncState() {
		return syncState;
	}
	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}
	
	
	
}
