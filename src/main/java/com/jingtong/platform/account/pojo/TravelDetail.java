package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.framework.util.DateUtil;

public class TravelDetail {
	
	private Long travelId;
	private Long travleDtId;
	
	private String travelNum;//出差天数
	private Date travelStartDate;//出差起始时间
	private Date travelEndDate;//出差截止时间
	private String peerPerson;//同行人
	private String travelPlace;//出差地
	
	private BigDecimal invoiceAmount;//申请金额
	private BigDecimal auditMoney;// 实际金额
	private String meno;//申请报销说明
	private String flag;
	private String travelStartDateSt;//出差起始时间
	private String travelEndDateSt;//出差截止时间
	
	public Long getTravelId() {
		return travelId;
	}
	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}
	public Long getTravleDtId() {
		return travleDtId;
	}
	public void setTravleDtId(Long travleDtId) {
		this.travleDtId = travleDtId;
	}
	public String getTravelNum() {
		return travelNum;
	}
	public void setTravelNum(String travelNum) {
		this.travelNum = travelNum;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public String getPeerPerson() {
		return peerPerson;
	}
	public void setPeerPerson(String peerPerson) {
		this.peerPerson = peerPerson;
	}
	public String getTravelPlace() {
		return travelPlace;
	}
	public void setTravelPlace(String travelPlace) {
		this.travelPlace = travelPlace;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public BigDecimal getAuditMoney() {
		return auditMoney;
	}
	public void setAuditMoney(BigDecimal auditMoney) {
		this.auditMoney = auditMoney;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTravelStartDateSt() {
		return travelStartDateSt;
	}
	public void setTravelStartDateSt(String travelStartDateSt) {
		this.travelStartDateSt = travelStartDateSt;
		this.setTravelStartDate(DateUtil.getDateTime(travelStartDateSt));	
	}
	public String getTravelEndDateSt() {
		return travelEndDateSt;
	}
	public void setTravelEndDateSt(String travelEndDateSt) {
		this.travelEndDateSt = travelEndDateSt;
		this.setTravelEndDate(DateUtil.getDateTime(travelEndDateSt));	
	}

}
