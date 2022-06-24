package com.jingtong.platform.dict.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class CmsTbDict extends SearchInfo {
  private static final long serialVersionUID = -31231206571094321L;
  
  private Long itemId;
  
  private Long dictTypeId;
  
  private Long parentItemId;
  
  private String itemName;
  
  private String itemDescription;
  
  private String itemValue;
  
  private String remark;
  
  private String itemState;
  
  private Date lastModify;
  
  private Long chargeId;
  
  private Long appobjectLevel;
  
  private Date modifyDate;
  
  private CmsTbDictType cmsTbDictType;
  
  private String tot;
  
  private String xf;
  
  private String ct;
  
  private String ka;
  
  private String dictTypeName;
  
  private String dictTypeValue;
  
  public Long getItemId() {
    return this.itemId;
  }
  
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  
  public Long getDictTypeId() {
    return this.dictTypeId;
  }
  
  public void setDictTypeId(Long dictTypeId) {
    this.dictTypeId = dictTypeId;
  }
  
  public Long getParentItemId() {
    return this.parentItemId;
  }
  
  public void setParentItemId(Long parentItemId) {
    this.parentItemId = parentItemId;
  }
  
  public String getItemName() {
    return this.itemName;
  }
  
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  
  public String getItemDescription() {
    return this.itemDescription;
  }
  
  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }
  
  public String getItemValue() {
    return this.itemValue;
  }
  
  public void setItemValue(String itemValue) {
    this.itemValue = itemValue;
  }
  
  public String getRemark() {
    return this.remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public String getItemState() {
    return this.itemState;
  }
  
  public void setItemState(String itemState) {
    this.itemState = itemState;
  }
  
  public Date getLastModify() {
    return this.lastModify;
  }
  
  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }
  
  public Long getChargeId() {
    return this.chargeId;
  }
  
  public void setChargeId(Long chargeId) {
    this.chargeId = chargeId;
  }
  
  public Long getAppobjectLevel() {
    return this.appobjectLevel;
  }
  
  public void setAppobjectLevel(Long appobjectLevel) {
    this.appobjectLevel = appobjectLevel;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public CmsTbDictType getCmsTbDictType() {
    return this.cmsTbDictType;
  }
  
  public void setCmsTbDictType(CmsTbDictType cmsTbDictType) {
    this.cmsTbDictType = cmsTbDictType;
  }
  
  public String getTot() {
    return this.tot;
  }
  
  public void setTot(String tot) {
    this.tot = tot;
  }
  
  public String getXf() {
    return this.xf;
  }
  
  public void setXf(String xf) {
    this.xf = xf;
  }
  
  public String getCt() {
    return this.ct;
  }
  
  public void setCt(String ct) {
    this.ct = ct;
  }
  
  public String getKa() {
    return this.ka;
  }
  
  public void setKa(String ka) {
    this.ka = ka;
  }
  
  public String getDictTypeName() {
    return this.dictTypeName;
  }
  
  public void setDictTypeName(String dictTypeName) {
    this.dictTypeName = dictTypeName;
  }
  
  public String getDictTypeValue() {
    return this.dictTypeValue;
  }
  
  public void setDictTypeValue(String dictTypeValue) {
    this.dictTypeValue = dictTypeValue;
  }
}
