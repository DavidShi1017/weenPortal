package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 信用记录
 * 
 * @author cl
 * 
 */
public class CreditLog extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1070411349807923275L;
	private Long creditLogId;// id
	private String custmoerId;// 客户id
	private String kunnr;// 客户编号
	private String name1;// 客户名称
	private Double creditdbekr;// 调整信用
	private Double creditxyjy;// 调整前信用结余
	private Date createTime;//调整时间
	private String revisionId;//调整人id
	private String revisionName;//调整人名称
	private String remark; //备注 
	private String creditlogtype;//类型
	private double beforeInterestRate;//调整前的利率
	private double afterInterestRate;//调整后的利率
	private Date startDate;//调整前的截止日期
	private Date endDate;//调整后的截止日期
	////
	private String creditId;//信用号
	private Double creditUseAmount;//已使用信用
	
	public CreditLog() {
		
	}
	
	



	public String getRemark() {
		return remark;
	}





	public void setRemark(String remark) {
		this.remark = remark;
	}





	public Long getCreditLogId() {
		return creditLogId;
	}
	public void setCreditLogId(Long creditLogId) {
		this.creditLogId = creditLogId;
	}
	public String getCustmoerId() {
		return custmoerId;
	}
	public void setCustmoerId(String custmoerId) {
		this.custmoerId = custmoerId;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}

	public Double getCreditdbekr() {
		return creditdbekr;
	}





	public void setCreditdbekr(Double creditdbekr) {
		this.creditdbekr = creditdbekr;
	}





	public Double getCreditxyjy() {
		return creditxyjy;
	}





	public void setCreditxyjy(Double creditxyjy) {
		this.creditxyjy = creditxyjy;
	}





	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public String getRevisionId() {
		return revisionId;
	}





	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}





	public String getRevisionName() {
		return revisionName;
	}
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}





	public String getCreditlogtype() {
		return creditlogtype;
	}


	public void setCreditlogtype(String creditlogtype) {
		this.creditlogtype = creditlogtype;
	}





	public String getCreditId() {
		return creditId;
	}





	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}





	public Double getCreditUseAmount() {
		return creditUseAmount;
	}





	public void setCreditUseAmount(Double creditUseAmount) {
		this.creditUseAmount = creditUseAmount;
	}





	public double getBeforeInterestRate() {
		return beforeInterestRate;
	}





	public void setBeforeInterestRate(double beforeInterestRate) {
		this.beforeInterestRate = beforeInterestRate;
	}





	public double getAfterInterestRate() {
		return afterInterestRate;
	}





	public void setAfterInterestRate(double afterInterestRate) {
		this.afterInterestRate = afterInterestRate;
	}





	public Date getStartDate() {
		return startDate;
	}





	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}





	public Date getEndDate() {
		return endDate;
	}





	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


	

}
