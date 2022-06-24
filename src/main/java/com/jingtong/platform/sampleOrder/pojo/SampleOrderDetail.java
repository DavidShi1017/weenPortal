package com.jingtong.platform.sampleOrder.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 样品订单明细
 * 
 * @author yw
 *
 */
public class SampleOrderDetail extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
    private String order_id;// 订单编号
    private int row_no;// 订单行号
    private String po_item;
    private String material_id;// 物料编号(12NC)
    private String material_name;// 物料编号(12NC)
    private String material_typeId;// 物料类型(Material Type)
    private String material_groupId;// 物料组(Material Group)
    private String sale_unit;// 销售单位(Sale Unit)
    private double limited_QTY;// 最大订购数量(Limited QTY)
    private double order_QTY;// 订单数量(Order QTY)
    private double lead_time;// 生产周期(Lead Time)
    private Date delivery_date;// 交货日期(Delivery Date)
    private String delivery_dateStr;
    private Date confirm_date;// 确认交货日期(Delivery Date)
    private String confirm_dateStr;
    private long main_id;// 订单主表ID
    private String remark;// 备注
    private String ween_remark;// 备注
    private String create_userId;//
    private Date create_time;
    private Date latest_time;// 操作时间
    private String latest_userId;// 操作人
    private String org_code;// 所属组织
    private String ids;
    private double pq;
    private String pbMpp;// PB/MPP
    private String stock_status;
    private String status;
    private String expressage_info;
    private String ship_date;
    private String invoice;
    private String sap_order_id;

    public String getWeen_remark() {
        return ween_remark;
    }

    public void setWeen_remark(String ween_remark) {
        this.ween_remark = ween_remark;
    }

    public String getSap_order_id() {
        return sap_order_id;
    }

    public void setSap_order_id(String sap_order_id) {
        this.sap_order_id = sap_order_id;
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

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPbMpp() {
        return pbMpp;
    }

    public void setPbMpp(String pbMpp) {
        this.pbMpp = pbMpp;
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

    public double getPq() {
        return pq;
    }

    public void setPq(double pq) {
        this.pq = pq;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
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

    public String getPo_item() {
        return po_item;
    }

    public void setPo_item(String po_item) {
        this.po_item = po_item;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

}
