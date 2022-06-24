package com.jingtong.platform.customer.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Disti_branch extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String disti_name;// 客户名称
    private String disti_branch;// 分公司名称
    private String oldDisti_branch;
    private String pricing_region;// 价格区域
    private String payer_to;// 客户编码
    private String currency;//
    private String creater;//
    private String branch_code;//
    private Date create_date;//
    private String sold_to;// 客户编码
    private String bill_to;// 客户编码
    private String ship_to;// 客户编码
    private String alias;
    private String disti_alias;

    public String getSold_to() {
        return sold_to;
    }

    public void setSold_to(String sold_to) {
        this.sold_to = sold_to;
    }

    public String getBill_to() {
        return bill_to;
    }

    public void setBill_to(String bill_to) {
        this.bill_to = bill_to;
    }

    public String getShip_to() {
        return ship_to;
    }

    public void setShip_to(String ship_to) {
        this.ship_to = ship_to;
    }

    public String getOldDisti_branch() {
        return oldDisti_branch;
    }

    public void setOldDisti_branch(String oldDisti_branch) {
        this.oldDisti_branch = oldDisti_branch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getDisti_name() {
        return disti_name;
    }

    public void setDisti_name(String disti_name) {
        this.disti_name = disti_name;
    }

    public String getDisti_branch() {
        return disti_branch;
    }

    public void setDisti_branch(String disti_branch) {
        this.disti_branch = disti_branch;
    }

    public String getPricing_region() {
        return pricing_region;
    }

    public void setPricing_region(String pricing_region) {
        this.pricing_region = pricing_region;
    }

    public String getPayer_to() {
        return payer_to;
    }

    public void setPayer_to(String payer_to) {
        this.payer_to = payer_to;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDisti_alias() {
        return disti_alias;
    }

    public void setDisti_alias(String disti_alias) {
        this.disti_alias = disti_alias;
    }

}
