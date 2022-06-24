package com.jingtong.platform.sampleOrder.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 样品订单主表
 * @author yw
 *
 */
public class SampleOrderAndDetail extends SearchInfo{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
	private String order_id;//订单编号(Purchase Order)
	private String po_item;
	private String type_id;//订单类型（Order Type）
	private String branch_id;//代理商（Channel Branch）
	private String ship_to;//货运方（Ship To）
	private String payer_to;//付款方（Payer To）
	private String billing_to;//开票方(Billing To) 
	private String sale_to;//售达方-
	private String saler;//销售人员(Sales) 
	private String sale_company;// 销售公司（Sale Company）
	private String company;// 客户名称公司（Company）
	private String end_customer;//终端客户（End Customer）
	private String project_name;// 项目名称(Project Name) 
	private String contact_info;//终端客户联系信息(EC ContactInfo)
	private String sap_order_id;//分销凭证号-
	private String sale_group;//销售组织-HK01
	private String distri_channel;//分销渠道-00
	private String product_group;//产品组-00
	private int state;//状态
	private String remark;//备注
	private int sync_state;//同步状态
	private Date sync_time;//同步时间
	private String sync_userId;//同步用户
	private String sysnc_exception;//同步异常
	private String create_userId;//
	private Date create_time;
	private String create_timeString;
	private Date latest_time;//操作时间
	private String latest_userId;//操作人
	private String org_code;//所属组织
	private String zip;//
	private String loginId;//
	
	private String customer_name;//
	private String shipToName;//
	private String payerToName;//
	private String shipToAddress;//送达方-
	private String billingToName;//
	private String contact_name;//终端客户联系信息(EC ContactInfo)
	private String contact_tel;//终端客户联系信息(EC ContactInfo)
	private Date start_date;
	private Date end_date;
	private String start_dateStr;//
	private String end_dateStr;//
	private String cus_country;//
	private String country;//
	private String city;//
	private String street;//
	private String application_desc;//
	
	private long detail_id;
	private int row_no;//订单行号
	private String material_id;//物料编号(12NC) 
	private String material_name;//物料编号(12NC) 
	private String material_typeId;//物料类型(Material Type) 
	private String material_groupId;//物料组(Material Group)
	private String sale_unit;//销售单位(Sale Unit) 
	private double limited_QTY;//最大订购数量(Limited QTY) 
	private double order_QTY;//订单数量(Order QTY) 
	private double lead_time;// 生产周期(Lead Time)
	private Date delivery_date;//交货日期(Delivery Date) 
	private String delivery_dateStr;
	private Date confirm_date;//确认交货日期(Delivery Date) 
	private String confirm_dateStr;
	private long main_id;// 订单主表ID
	private String ids;
	private double pq;
	private String pbMpp;//PB/MPP 
	private String stock_status;
	private String status;
	private String expressage_info;
	private String ship_date;
	private String invoice;
	private String detail_remark;
	private String ween_remark ;
	private String detail_state ;
	
	   // 送达区域
    private String ship_to_region;
    // 申请人
    private String applicant_name;
    // 申请公司
    private String applicant_company;
    // 管理人
    private String account_manager;
    // 审查备注
    private String comments;
    
    private String orderState;

	public String getCreate_timeString() {
		return create_timeString;
	}
	public void setCreate_timeString(String create_timeString) {
		this.create_timeString = create_timeString;
	}
	public String getDetail_state() {
		return detail_state;
	}
	public void setDetail_state(String detail_state) {
		this.detail_state = detail_state;
	}
	public String getWeen_remark() {
		return ween_remark;
	}
	public void setWeen_remark(String ween_remark) {
		this.ween_remark = ween_remark;
	}
	public String getDetail_remark() {
		return detail_remark;
	}
	public void setDetail_remark(String detail_remark) {
		this.detail_remark = detail_remark;
	}
	public long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(long detail_id) {
		this.detail_id = detail_id;
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
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
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
	public double getLimited_QTY() {
		return limited_QTY;
	}
	public void setLimited_QTY(double limited_QTY) {
		this.limited_QTY = limited_QTY;
	}
	public double getOrder_QTY() {
		return order_QTY;
	}
	public void setOrder_QTY(double order_QTY) {
		this.order_QTY = order_QTY;
	}
	public double getLead_time() {
		return lead_time;
	}
	public void setLead_time(double lead_time) {
		this.lead_time = lead_time;
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
	public Date getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getConfirm_dateStr() {
		return confirm_dateStr;
	}
	public void setConfirm_dateStr(String confirm_dateStr) {
		this.confirm_dateStr = confirm_dateStr;
	}
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public double getPq() {
		return pq;
	}
	public void setPq(double pq) {
		this.pq = pq;
	}
	public String getPbMpp() {
		return pbMpp;
	}
	public void setPbMpp(String pbMpp) {
		this.pbMpp = pbMpp;
	}
	public String getStock_status() {
		return stock_status;
	}
	public void setStock_status(String stock_status) {
		this.stock_status = stock_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpressage_info() {
		return expressage_info;
	}
	public void setExpressage_info(String expressage_info) {
		this.expressage_info = expressage_info;
	}
	public String getShip_date() {
		return ship_date;
	}
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApplication_desc() {
		return application_desc;
	}
	public void setApplication_desc(String application_desc) {
		this.application_desc = application_desc;
	}
	public long getId() {
		return id;
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
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
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
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
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
	public String getEnd_customer() {
		return end_customer;
	}
	public void setEnd_customer(String end_customer) {
		this.end_customer = end_customer;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getContact_info() {
		return contact_info;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getSync_userId() {
		return sync_userId;
	}
	public void setSync_userId(String sync_userId) {
		this.sync_userId = sync_userId;
	}
	public String getSysnc_exception() {
		return sysnc_exception;
	}
	public void setSysnc_exception(String sysnc_exception) {
		this.sysnc_exception = sysnc_exception;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getSale_to() {
		return sale_to;
	}
	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
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
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_tel() {
		return contact_tel;
	}
	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
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
	public String getCus_country() {
		return cus_country;
	}
	public void setCus_country(String cus_country) {
		this.cus_country = cus_country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
    public String getShip_to_region() {
        return ship_to_region;
    }
    public void setShip_to_region(String ship_to_region) {
        this.ship_to_region = ship_to_region;
    }
    public String getApplicant_name() {
        return applicant_name;
    }
    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }
    public String getApplicant_company() {
        return applicant_company;
    }
    public void setApplicant_company(String applicant_company) {
        this.applicant_company = applicant_company;
    }
    public String getAccount_manager() {
        return account_manager;
    }
    public void setAccount_manager(String account_manager) {
        this.account_manager = account_manager;
    }
    
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getOrderState() {
        return orderState;
    }
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

}
