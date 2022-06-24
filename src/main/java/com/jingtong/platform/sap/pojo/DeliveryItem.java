package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DeliveryItem extends SearchInfo{

private static final long serialVersionUID = 3494761267120341239L;
	
	private long id;
	private String salesDocument;//销售凭证
	private long salesDocumentItem;//销售凭证项目
	private String materialNumber;//物料号
	private double totalQty;//以销售单位表示的累计订单数量
	private double salesUnit;//销售单位 
	private String txt;//项目文本
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSalesDocument() {
		return salesDocument;
	}
	public void setSalesDocument(String salesDocument) {
		this.salesDocument = salesDocument;
	}
	public long getSalesDocumentItem() {
		return salesDocumentItem;
	}
	public void setSalesDocumentItem(long salesDocumentItem) {
		this.salesDocumentItem = salesDocumentItem;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public double getSalesUnit() {
		return salesUnit;
	}
	public void setSalesUnit(double salesUnit) {
		this.salesUnit = salesUnit;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	
	
	
	
}
