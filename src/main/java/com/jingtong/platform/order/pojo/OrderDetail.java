package com.jingtong.platform.order.pojo;



import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 标准订单明细
 * @author yw
 *
 */
public class OrderDetail extends SearchInfo{
	private long id;
	private String order_id;//订单编号，销售凭证-
	private int row_no;//订单行号，销售凭证项目-
	private String material_id;//物料编号(12NC) -
	private String material_name;
	private String material_typeId;//物料类型(Material Type) 
	private String material_groupId;//物料组(Material Group)
	private String sale_unit;//销售单位(Sale Unit取物料的基本单位) -
	private int pq;	//每PQ个sale_unit为一个销售单位
	private double limited_QTY;//最大订购数量(Limited QTY) 
	private int moq;//起订量
	private int order_QTY;//订单数量(Order QTY) -
	private double lead_time;// 生产周期(Lead Time)
	private Date delivery_date;//客户期望的交货日期(Delivery Date) 
	private String delivery_dateStr; 
	private Date confirm_date;//确认交货日期(Delivery Date) 
	private String confirm_dateStr;
	private double price;// 单价
	private long main_id;// 订单主表ID
	private String remark;//备注（项目文本）-
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//操作时间
	private String latest_userId;//操作人
	private String org_code;//所属组织
	private String ids;
	private String po_item;
	private String pbMpp;//PB/MPP 
	
//表头信息	
	private String type_id;//订单类型（Order Type）销售凭证类型-
	private String branch_id;//代理商名称
	private String currency_code;//货币-
	private String ship_to;//送达方-
	private String payer_to;//付款方-
	private String billing_to;//开票方-
	private String sale_to;//售达方-
	private String saler;//销售人员
	private String sale_company;// 销售公司（Sale Company）
	private String project_name;// 项目名称(Project Name) 
	private String sap_order_id;//分销凭证号-
	private String sale_group;//销售组织-
	private String distri_channel;//分销渠道-
	private String product_group;//产品组-
	private int state;//状态
	private int sync_state;//同步状态
	private Date sync_time;//同步时
	private String customer_name;//
	private String shipToName;//
	private String payerToName;//
	private String billingToName;//
	private Date start_date;
	private Date end_date;
	private String start_dateStr;//
	private String end_dateStr;//
	private String loginId;//登陆账号
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getPbMpp() {
		return pbMpp;
	}
	public void setPbMpp(String pbMpp) {
		this.pbMpp = pbMpp;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getRow_no() {
		return row_no;
	}
	public void setRow_no(int row_no) {
		this.row_no = row_no;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_typeId() {
		return material_typeId;
	}
	public void setMaterial_typeId(String material_typeId) {
		this.material_typeId = material_typeId;
	}
	public String getMaterial_groupId() {
		return material_groupId;
	}
	public void setMaterial_groupId(String material_groupId) {
		this.material_groupId = material_groupId;
	}
	public String getSale_unit() {
		return sale_unit;
	}
	public void setSale_unit(String sale_unit) {
		this.sale_unit = sale_unit;
	}
	public double getLead_time() {
		return lead_time;
	}
	public void setLead_time(double lead_time) {
		this.lead_time = lead_time;
	}
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(Date latest_time) {
		this.latest_time = latest_time;
	}
	public String getLatest_userId() {
		return latest_userId;
	}
	public void setLatest_userId(String latest_userId) {
		this.latest_userId = latest_userId;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public Date getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDelivery_dateStr() {
		return delivery_dateStr;
	}
	public void setDelivery_dateStr(String delivery_dateStr) {
		this.delivery_dateStr = delivery_dateStr;
	}
	public String getConfirm_dateStr() {
		return confirm_dateStr;
	}
	public void setConfirm_dateStr(String confirm_dateStr) {
		this.confirm_dateStr = confirm_dateStr;
	}
	public double getLimited_QTY() {
		return limited_QTY;
	}
	public void setLimited_QTY(double limited_QTY) {
		this.limited_QTY = limited_QTY;
	}
	public int getPq() {
		return pq;
	}
	public void setPq(int pq) {
		this.pq = pq;
	}
	public int getMoq() {
		return moq;
	}
	public void setMoq(int moq) {
		this.moq = moq;
	}
	public int getOrder_QTY() {
		return order_QTY;
	}
	public void setOrder_QTY(int order_QTY) {
		this.order_QTY = order_QTY;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getShip_to() {
		return ship_to;
	}
	public void setShip_to(String ship_to) {
		this.ship_to = ship_to;
	}
	public String getPayer_to() {
		return payer_to;
	}
	public void setPayer_to(String payer_to) {
		this.payer_to = payer_to;
	}
	public String getBilling_to() {
		return billing_to;
	}
	public void setBilling_to(String billing_to) {
		this.billing_to = billing_to;
	}
	public String getSale_to() {
		return sale_to;
	}
	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
	}
	public String getSaler() {
		return saler;
	}
	public void setSaler(String saler) {
		this.saler = saler;
	}
	public String getSale_company() {
		return sale_company;
	}
	public void setSale_company(String sale_company) {
		this.sale_company = sale_company;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getSap_order_id() {
		return sap_order_id;
	}
	public void setSap_order_id(String sap_order_id) {
		this.sap_order_id = sap_order_id;
	}
	public String getSale_group() {
		return sale_group;
	}
	public void setSale_group(String sale_group) {
		this.sale_group = sale_group;
	}
	public String getDistri_channel() {
		return distri_channel;
	}
	public void setDistri_channel(String distri_channel) {
		this.distri_channel = distri_channel;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSync_state() {
		return sync_state;
	}
	public void setSync_state(int sync_state) {
		this.sync_state = sync_state;
	}
	public Date getSync_time() {
		return sync_time;
	}
	public void setSync_time(Date sync_time) {
		this.sync_time = sync_time;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getPayerToName() {
		return payerToName;
	}
	public void setPayerToName(String payerToName) {
		this.payerToName = payerToName;
	}
	public String getBillingToName() {
		return billingToName;
	}
	public void setBillingToName(String billingToName) {
		this.billingToName = billingToName;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStart_dateStr() {
		return start_dateStr;
	}
	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}
	public String getEnd_dateStr() {
		return end_dateStr;
	}
	public void setEnd_dateStr(String end_dateStr) {
		this.end_dateStr = end_dateStr;
	}
	
	
}
