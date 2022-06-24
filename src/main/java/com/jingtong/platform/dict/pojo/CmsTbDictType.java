package com.jingtong.platform.dict.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class CmsTbDictType extends SearchInfo {
  private static final long serialVersionUID = 3708257908483171010L;
  
  private Long dictTypeId;
  
  private Long parentDictTypeId;
  
  private String dictTypeName;
  
  private String dictTypeValue;
  
  private String remark;
  
  private String dictTypeState;
  
  private Date lastModify;
  
  private Date modifyDate;
  
  public Long getDictTypeId() {
    return this.dictTypeId;
  }
  
  public void setDictTypeId(Long dictTypeId) {
    this.dictTypeId = dictTypeId;
  }
  
  public Long getParentDictTypeId() {
    return this.parentDictTypeId;
  }
  
  public void setParentDictTypeId(Long parentDictTypeId) {
    this.parentDictTypeId = parentDictTypeId;
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
  
  public String getRemark() {
    return this.remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public String getDictTypeState() {
    return this.dictTypeState;
  }
  
  public void setDictTypeState(String dictTypeState) {
    this.dictTypeState = dictTypeState;
  }
  
  public Date getLastModify() {
    return this.lastModify;
  }
  
  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
}
