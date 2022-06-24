package com.jingtong.platform.position.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class BpositionType extends SearchInfo {
  private static final long serialVersionUID = -5908899502929436071L;
  
  private Long positionTypeId;
  
  private String positionTypeName;
  
  private String staffType;
  
  private Integer grade;
  
  private Date modifyDate;
  
  private Long companyId;
  
  private String status;
  
  private Long positionLevel;
  
  private String positionProperty;
  
  private String companyName;
  
  private Long dictTypeId;
  
  private String position_type_name;
  
  private String roleId;
  
  private String positionTypeLevel;
  
  public Long getPositionTypeId() {
    return this.positionTypeId;
  }
  
  public void setPositionTypeId(Long positionTypeId) {
    this.positionTypeId = positionTypeId;
  }
  
  public String getPositionTypeName() {
    return this.positionTypeName;
  }
  
  public void setPositionTypeName(String positionTypeName) {
    this.positionTypeName = positionTypeName;
  }
  
  public String getStaffType() {
    return this.staffType;
  }
  
  public void setStaffType(String staffType) {
    this.staffType = staffType;
  }
  
  public Integer getGrade() {
    return this.grade;
  }
  
  public void setGrade(Integer grade) {
    this.grade = grade;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public Long getCompanyId() {
    return this.companyId;
  }
  
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public Long getPositionLevel() {
    return this.positionLevel;
  }
  
  public void setPositionLevel(Long positionLevel) {
    this.positionLevel = positionLevel;
  }
  
  public String getPositionProperty() {
    return this.positionProperty;
  }
  
  public void setPositionProperty(String positionProperty) {
    this.positionProperty = positionProperty;
  }
  
  public String getCompanyName() {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  
  public Long getDictTypeId() {
    return this.dictTypeId;
  }
  
  public void setDictTypeId(Long dictTypeId) {
    this.dictTypeId = dictTypeId;
  }
  
  public String getPosition_type_name() {
    return this.position_type_name;
  }
  
  public void setPosition_type_name(String position_type_name) {
    this.position_type_name = position_type_name;
  }
  
  public String getRoleId() {
    return this.roleId;
  }
  
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  
  public String getPositionTypeLevel() {
    return this.positionTypeLevel;
  }
  
  public void setPositionTypeLevel(String positionTypeLevel) {
    this.positionTypeLevel = positionTypeLevel;
  }
}
