package com.jingtong.platform.product.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 产品信息
 * 
 * @author lenovo
 *
 */
public class Product extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String material_id; // 物料编码（12NC）-
    private String material_name;// 物料名称（BOOK Part）-
    private String material_exp;// 物料描述（Description）-
    private String material_type;// 物料类型（Material Type）产品组-
    private String material_groupId;// 物料组（Material Group）
    private Double lead_time;// 生产周期（Lead Time）（portal excel导入）
    private String base_unit;// 基本单位（Base Unit）-
    private String sale_unit;// 销售单位（Base Unit Of Sale）废弃-
    private String unit_change;// 单位换算（Unit Change）废弃（换成分子分母）
    private String numerator;// 单位换算之分子（大数）
    private String denominator;// 单位换算之分母
    private String isOrderItem;// 能否下单（Order Item）（portal excel导入）
    private String isQuoteItem;// 能否报价（Quote Item）（portal excel导入）
    private String isDRItem;// 能否抢注（DR Item）（portal excel导入）
    private Double limited_qty;// 样品限制数量（Sample Limited QTY）（portal excel导入）
    private int moq;// 起订量（MOQ）-
    private String cost;// 成本（WeEn Cost）
    private String factory;// 工厂
    private String remark;// 描述
    private String customer_id;// 客户编号
    private int state;// 状态 状态（status）0新建
    private String isDeleted;// 是否删除（逻辑删除）1是0否
    private int sortId; // 排序号
    private int sync_state;// 同步状态（1同步，0未同步）
    private Date sync_time;// 同步时间
    private String sync_userId;// 同步用户
    private String sysnc_exception;// 同步异常
    private String create_userId;// 建立用户
    private Date create_time;// 建立时间
    private Date latest_time;// 操作时间
    private String latest_userId;// 操作人
    private String latest_deptId;// 操作部门
    private String customer_group;// 客户集团(For Customer)
    private String pq; // PQ(WM单位)即销售单位 与base_unit的关系 1PQ=numerator*base_unit/denominator
    private String isLocked; // 物料被锁定
    private String isDownLoad;// 下载模板标识（只下载限制数量为零的）
    private String useFor;// 使用于何处标识，（YPD为样品单）样品单可显示HK11的物料
    private double pbPrice;// 关联priceRule
    private String perUnit;// 关联priceRule
    private String currency_code;// 用于关联priceRule
    private String office_id;// 用于关联priceRule
    private Date rfs_date;
    private String rfs_dateStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsDownLoad() {
        return isDownLoad;
    }

    public void setIsDownLoad(String isDownLoad) {
        this.isDownLoad = isDownLoad;
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

    public String getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(String perUnit) {
        this.perUnit = perUnit;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_exp() {
        return material_exp;
    }

    public void setMaterial_exp(String material_exp) {
        this.material_exp = material_exp;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getMaterial_groupId() {
        return material_groupId;
    }

    public void setMaterial_groupId(String material_groupId) {
        this.material_groupId = material_groupId;
    }

    public double getLead_time() {
        return lead_time;
    }

    public void setLead_time(Double lead_time) {
        this.lead_time = lead_time;
    }

    public String getBase_unit() {
        return base_unit;
    }

    public void setBase_unit(String base_unit) {
        this.base_unit = base_unit;
    }

    public String getSale_unit() {
        return sale_unit;
    }

    public String getCustomer_group() {
        return customer_group;
    }

    public void setCustomer_group(String customer_group) {
        this.customer_group = customer_group;
    }

    public void setSale_unit(String sale_unit) {
        this.sale_unit = sale_unit;
    }

    public String getUnit_change() {
        return unit_change;
    }

    public void setUnit_change(String unit_change) {
        this.unit_change = unit_change;
    }

    public String getIsOrderItem() {
        return isOrderItem;
    }

    public void setIsOrderItem(String isOrderItem) {
        this.isOrderItem = isOrderItem;
    }

    public String getIsQuoteItem() {
        return isQuoteItem;
    }

    public void setIsQuoteItem(String isQuoteItem) {
        this.isQuoteItem = isQuoteItem;
    }

    public String getIsDRItem() {
        return isDRItem;
    }

    public void setIsDRItem(String isDRItem) {
        this.isDRItem = isDRItem;
    }

    public Double getLimited_qty() {
        return limited_qty;
    }

    public void setLimited_qty(Double limited_qty) {
        this.limited_qty = limited_qty;
    }

    public int getMoq() {
        return moq;
    }

    public void setMoq(int moq) {
        this.moq = moq;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
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

    public String getLatest_deptId() {
        return latest_deptId;
    }

    public void setLatest_deptId(String latest_deptId) {
        this.latest_deptId = latest_deptId;
    }

    public String getPq() {
        return pq;
    }

    public void setPq(String pq) {
        this.pq = pq;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public Double getPbPrice() {
        return pbPrice;
    }

    public void setPbPrice(Double pbPrice) {
        this.pbPrice = pbPrice;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public Date getRfs_date() {
        return rfs_date;
    }

    public void setRfs_date(Date rfs_date) {
        this.rfs_date = rfs_date;
    }

    /*public void setPbPrice(double pbPrice) {
        this.pbPrice = pbPrice;
    }*/

    public String getRfs_dateStr() {
        return rfs_dateStr;
    }

    public void setRfs_dateStr(String rfs_dateStr) {
        this.rfs_dateStr = rfs_dateStr;
    }

}
