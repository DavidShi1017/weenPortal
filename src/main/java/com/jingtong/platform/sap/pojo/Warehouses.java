package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Warehouses extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2253330082939333627L;
	 
	 private long id;
	 private String vstel;
	 private String locationName;
	 private String warehouseCode;//对应工厂
	 private String warehouseDesc;
	 private String locationCode;//库位
	 private String productsDate;
	 private String productsPlace;
	 private String createUser;
	 private Date createDate;
	 private String modifyUser;
	 private Date modifyDate;
	 private String status;
	 private String delivery;
	 
	 private String vtext;//查询用
	 private String ids;//删除用
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	 
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseDesc() {
		return warehouseDesc;
	}
	public void setWarehouseDesc(String warehouseDesc) {
		this.warehouseDesc = warehouseDesc;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getProductsDate() {
		return productsDate;
	}
	public void setProductsDate(String productsDate) {
		this.productsDate = productsDate;
	}
	public String getProductsPlace() {
		return productsPlace;
	}
	public void setProductsPlace(String productsPlace) {
		this.productsPlace = productsPlace;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVtext() {
		return vtext;
	}
	public void setVtext(String vtext) {
		this.vtext = vtext;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	 
	 
}
