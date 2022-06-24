package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Categorys extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -103653753705237866L;
	private String categoryNumber; // 物料编码
	private String categoryName; // 物料描述
	private String categoryType; // 类别 1促销品 2广宣品
	private String categoryUnit; // 单位
	private float categoryweight; //重量
	private String categorySku;	//条码
	private float categoryVolume;	//体积
	private Double categoryPrice; // 单价
	private String state; // 状态
	private Date modifyDate; // 修改时间
	private Date createDate;// 创建时间
	private String creater;// 创建人
	private String modifier; // 修改人
	private String vekmeTxt;	//销售单位
	private String categorylevel; //级别
	private String umrez;	//基本计量单位转换分子
	public String getCategoryNumber() {
		return categoryNumber;
	}
	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryUnit() {
		return categoryUnit;
	}
	public void setCategoryUnit(String categoryUnit) {
		this.categoryUnit = categoryUnit;
	}
	public float getCategoryweight() {
		return categoryweight;
	}
	public void setCategoryweight(float categoryweight) {
		this.categoryweight = categoryweight;
	}
	public String getCategorySku() {
		return categorySku;
	}
	public void setCategorySku(String categorySku) {
		this.categorySku = categorySku;
	}
	public float getCategoryVolume() {
		return categoryVolume;
	}
	public void setCategoryVolume(float categoryVolume) {
		this.categoryVolume = categoryVolume;
	}
	public Double getCategoryPrice() {
		return categoryPrice;
	}
	public void setCategoryPrice(Double categoryPrice) {
		this.categoryPrice = categoryPrice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getVekmeTxt() {
		return vekmeTxt;
	}
	public void setVekmeTxt(String vekmeTxt) {
		this.vekmeTxt = vekmeTxt;
	}
	public String getCategorylevel() {
		return categorylevel;
	}
	public void setCategorylevel(String categorylevel) {
		this.categorylevel = categorylevel;
	}
	public String getUmrez() {
		return umrez;
	}
	public void setUmrez(String umrez) {
		this.umrez = umrez;
	}
	
}
