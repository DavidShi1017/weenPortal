package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Delivery extends SearchInfo{
	
	private static final long serialVersionUID = 3494761267120341239L;
	
	private long id;
	private String distDocument;//销售和分销凭证号 
	private String salesOrg;//销售组织
	private String distChannel;//分销渠道
	private String productGroup;//产品组  
	private String salesDocumentType;//销售凭证类型  
	private String shipTo;//送达方  
	private String billTo;//付款方  
	private String collectTo;//收取发票方  
	private String salesTo;//售达方  
	private String currency;//SD 凭证货币 
	private String orderId ;//客户采购订单编号 
	private String headerTxt;//抬头文本
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
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getDistChannel() {
		return distChannel;
	}
	public void setDistChannel(String distChannel) {
		this.distChannel = distChannel;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getSalesDocumentType() {
		return salesDocumentType;
	}
	public void setSalesDocumentType(String salesDocumentType) {
		this.salesDocumentType = salesDocumentType;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getCollectTo() {
		return collectTo;
	}
	public void setCollectTo(String collectTo) {
		this.collectTo = collectTo;
	}
	public String getSalesTo() {
		return salesTo;
	}
	public void setSalesTo(String salesTo) {
		this.salesTo = salesTo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getHeaderTxt() {
		return headerTxt;
	}
	public void setHeaderTxt(String headerTxt) {
		this.headerTxt = headerTxt;
	}


}
