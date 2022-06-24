package com.jingtong.platform.designReg.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Dict  extends SearchInfo{

	private long itemId;
	private long dictTypeId;
	private String parentItemId;
	private String itemName;
	private String itemDescription;
	private String itemValue;
	private String remark;
	private String itemState;
	private String lastModify;
	private String chargeId;
	private String appobjectLevel;
	private Date modifyDate;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public long getDictTypeId() {
		return dictTypeId;
	}
	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}
	public String getParentItemId() {
		return parentItemId;
	}
	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemState() {
		return itemState;
	}
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	public String getLastModify() {
		return lastModify;
	}
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getAppobjectLevel() {
		return appobjectLevel;
	}
	public void setAppobjectLevel(String appobjectLevel) {
		this.appobjectLevel = appobjectLevel;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	

	
	
}
