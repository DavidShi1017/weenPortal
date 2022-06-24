package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Price extends SearchInfo{


	private static final long serialVersionUID = -3386093021023024761L;
	
	private long id;
	private String type;//条件类型名称-
	private String remark;//条件类型名称
	private String salesOrg;//销售组织
	private String materialNumber;//物料号-
	private String customerNumber;//客户编号
	private String price;//价格
	private String ratioUnit;//比率单位（货币）
	private String priceUnit;//条件定价单位（销售单位）
	private String conditionUnit;//条件单位（每）
	private Date startDate;//有效开始时间
	private Date endDate;//有效结束时间
	private String officeId;//
	private Date create_time;
	
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRatioUnit() {
		return ratioUnit;
	}
	public void setRatioUnit(String ratioUnit) {
		this.ratioUnit = ratioUnit;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getConditionUnit() {
		return conditionUnit;
	}
	public void setConditionUnit(String conditionUnit) {
		this.conditionUnit = conditionUnit;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	
	
	
	
	
 
}
