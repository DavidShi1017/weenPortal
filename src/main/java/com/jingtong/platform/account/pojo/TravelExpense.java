package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class TravelExpense extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  Long travelExpenseId; //费用ID
	
	private  Date travelExpenseDate; //申请日期
	
	private  String  orgId; //申请部门编号
	
	private  String  orgName; //申请部门名称
	
	private  String   budgetNumber;//预算编号
	
	private String  budgetOrgId; //费用所属部门
	
	private String budgetOrgName; //费用所属部门
	
	private String businessDate; //业务所属期间
	
	private String payType; //付款方式
	
	private String payCompany; //付款方式
	
	private String accountNumber; //账号信息
	
	private BigDecimal  applyMoney; //申请金额
	
	private String comment; //申请内容

	public Long getTravelExpenseId() {
		return travelExpenseId;
	}

	public void setTravelExpenseId(Long travelExpenseId) {
		this.travelExpenseId = travelExpenseId;
	}

	public Date getTravelExpenseDate() {
		return travelExpenseDate;
	}

	public void setTravelExpenseDate(Date travelExpenseDate) {
		this.travelExpenseDate = travelExpenseDate;
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

	public String getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	public String getBudgetOrgId() {
		return budgetOrgId;
	}

	public void setBudgetOrgId(String budgetOrgId) {
		this.budgetOrgId = budgetOrgId;
	}

	public String getBudgetOrgName() {
		return budgetOrgName;
	}

	public void setBudgetOrgName(String budgetOrgName) {
		this.budgetOrgName = budgetOrgName;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
