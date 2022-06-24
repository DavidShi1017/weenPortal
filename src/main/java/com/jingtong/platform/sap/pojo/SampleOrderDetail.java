package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class SampleOrderDetail extends SearchInfo{
	
	private static final long serialVersionUID = -3386093021023024761L;
	
	private long id;
	private String salesId;//销售凭证
	private String row_no;//销售凭证项目
	private String material_id;//物料号
	private String order_QTY;//以销售单位表示的累计订单数量
	private String sale_unit;//销售单位 
	private String remark;//项目文本
	private Date delivery_date;//计划日期
	private String delivery_dateStr; 
	private String po_item;
	
	
	private long main_id;// 订单主表ID
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getRow_no() {
		return row_no;
	}
	public void setRow_no(String row_no) {
		this.row_no = row_no;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getOrder_QTY() {
		return order_QTY;
	}
	public void setOrder_QTY(String order_QTY) {
		this.order_QTY = order_QTY;
	}
	public String getSale_unit() {
		return sale_unit;
	}
	public void setSale_unit(String sale_unit) {
		this.sale_unit = sale_unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDelivery_dateStr() {
		return delivery_dateStr;
	}
	public void setDelivery_dateStr(String delivery_dateStr) {
		this.delivery_dateStr = delivery_dateStr;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
	}

	
	
	
}
