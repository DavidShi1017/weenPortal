package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DeliveryDetail extends SearchInfo{

	private static final long serialVersionUID = 3494761267120341239L;
	
	

	private long id;
	private String distDocument;//销售和分销凭证号 
	private long distDocumentItem;//销售和分销凭证的项目号
	private long deliveryPlanNum;//交货计划行号
	private String deliveryPlanDate;//计划行日期 
	private double updateQty;//以销售单位计的修正数量 
	private double netPrice;//净价
	private double totalPrice;//行项目总价
	private double taxPrice;//含税单价
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDistDocument() {
		return distDocument;
	}
	public void setDistDocument(String distDocument) {
		this.distDocument = distDocument;
	}
	public long getDistDocumentItem() {
		return distDocumentItem;
	}
	public void setDistDocumentItem(long distDocumentItem) {
		this.distDocumentItem = distDocumentItem;
	}
	public long getDeliveryPlanNum() {
		return deliveryPlanNum;
	}
	public void setDeliveryPlanNum(long deliveryPlanNum) {
		this.deliveryPlanNum = deliveryPlanNum;
	}
	public String getDeliveryPlanDate() {
		return deliveryPlanDate;
	}
	public void setDeliveryPlanDate(String deliveryPlanDate) {
		this.deliveryPlanDate = deliveryPlanDate;
	}
	public double getUpdateQty() {
		return updateQty;
	}
	public void setUpdateQty(double updateQty) {
		this.updateQty = updateQty;
	}
	public double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}
	
	
	
	
	
}