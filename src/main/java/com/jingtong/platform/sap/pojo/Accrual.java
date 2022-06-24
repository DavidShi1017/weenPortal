package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Accrual extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8078303555528248029L;
	
	private long id;//ID	ID
	private String kunnr;//客户编号	kunnr
	private String kunnrName;
	
	private String creditId;//授信编号	credit_Id
	private String creditRange;///授信范围	creditRange
	private String crmOrderId;//CRM订单号	crm_order_Id
	private String sapOrderId;//SAP订单号	sap_order_Id
	private String deliveryId;//交货单号	delivery_Id
	private Date useDate;//使用日期	use_date
	private double repayMentAmount;//应还款金额	repayment_amount
	private Date repayMentDate;//应还款日	repayment_date
	private int repaymentDays;
	private double repaidAmount;//已还款金额	repaid_amount
	private Date repaidDate;//已还款日期	repaid_date
	private double rate;//利率	rate
	private String createUser;//创建人	CREATEUSER
	private Date createDate;//创建日期	CREATEDATE
	private String modifyUser;//修改人	MODIFYUSER
	private Date modifyDate;//修改日期	MODIFYDATE
	private String status;//状态	status
	
	private String bukrs; //法人公司
	private String gjahr; //会计年度
	private String belnr; //会计凭证号
	private String buzei; //凭证行项目
	
	
 	
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getGjahr() {
		return gjahr;
	}
	public void setGjahr(String gjahr) {
		this.gjahr = gjahr;
	}
	public String getBelnr() {
		return belnr;
	}
	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}
	public String getBuzei() {
		return buzei;
	}
	public void setBuzei(String buzei) {
		this.buzei = buzei;
	}
	public int getRepaymentDays() {
		return repaymentDays;
	}
	public void setRepaymentDays(int repaymentDays) {
		this.repaymentDays = repaymentDays;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getCreditRange() {
		return creditRange;
	}
	public void setCreditRange(String creditRange) {
		this.creditRange = creditRange;
	}
	public String getCrmOrderId() {
		return crmOrderId;
	}
	public void setCrmOrderId(String crmOrderId) {
		this.crmOrderId = crmOrderId;
	}
	public String getSapOrderId() {
		return sapOrderId;
	}
	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public double getRepayMentAmount() {
		return repayMentAmount;
	}
	public void setRepayMentAmount(double repayMentAmount) {
		this.repayMentAmount = repayMentAmount;
	}
	public Date getRepayMentDate() {
		return repayMentDate;
	}
	public void setRepayMentDate(Date repayMentDate) {
		this.repayMentDate = repayMentDate;
	}
	public double getRepaidAmount() {
		return repaidAmount;
	}
	public void setRepaidAmount(double repaidAmount) {
		this.repaidAmount = repaidAmount;
	}
	public Date getRepaidDate() {
		return repaidDate;
	}
	public void setRepaidDate(Date repaidDate) {
		this.repaidDate = repaidDate;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
