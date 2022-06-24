package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 定时 sap同步库存
 */
public class MaterialInventory extends SearchInfo{

	
	private static final long serialVersionUID = -5029707924363799877L;

	private String materialNumber;//物料编码
	private String warehouseCode;//工厂
	private String locationCode;//库位编码
	private String vstel;//装运点编码
	private float inventory;//库存
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	public float getInventory() {
		return inventory;
	}
	public void setInventory(float inventory) {
		this.inventory = inventory;
	}
	
	
	
}
