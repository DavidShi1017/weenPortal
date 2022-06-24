package com.jingtong.platform.allUser.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class UserChange extends SearchInfo {
  private static final long serialVersionUID = 6601838572166939379L;
  
  private Long Id;
  
  private Long empId;
  
  private String norGid;
  
  private String yorGid;
  
  private String empCode;
  
  private String empName;
  
  private String changeType;
  
  private Long opId;
  
  private String oploGid;
  
  private String opName;
  
  private String createTime;
  
  private String sourcs;
  
  private String[] norGids;
  
  private String beginDate;
  
  private String endDate;
  
  private String userId;
  
  private String yorgName;
  
  private String norgName;
  
  private String orgId;
  
  public Long getId() {
    return this.Id;
  }
  
  public void setId(Long id) {
    this.Id = id;
  }
  
  public Long getEmpId() {
    return this.empId;
  }
  
  public void setEmpId(Long empId) {
    this.empId = empId;
  }
  
  public String getEmpCode() {
    return this.empCode;
  }
  
  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }
  
  public String getEmpName() {
    return this.empName;
  }
  
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  
  public String getChangeType() {
    return this.changeType;
  }
  
  public void setChangeType(String changeType) {
    this.changeType = changeType;
  }
  
  public Long getOpId() {
    return this.opId;
  }
  
  public void setOpId(Long opId) {
    this.opId = opId;
  }
  
  public String getOploGid() {
    return this.oploGid;
  }
  
  public void setOploGid(String oploGid) {
    this.oploGid = oploGid;
  }
  
  public String getOpName() {
    return this.opName;
  }
  
  public void setOpName(String opName) {
    this.opName = opName;
  }
  
  public String getCreateTime() {
    return this.createTime;
  }
  
  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
  
  public String getSourcs() {
    return this.sourcs;
  }
  
  public void setSourcs(String sourcs) {
    this.sourcs = sourcs;
  }
  
  public String[] getNorGids() {
    return this.norGids;
  }
  
  public void setNorGids(String[] norGids) {
    this.norGids = norGids;
  }
  
  public String getBeginDate() {
    return this.beginDate;
  }
  
  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }
  
  public String getEndDate() {
    return this.endDate;
  }
  
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getYorgName() {
    return this.yorgName;
  }
  
  public void setYorgName(String yorgName) {
    this.yorgName = yorgName;
  }
  
  public String getNorgName() {
    return this.norgName;
  }
  
  public void setNorgName(String norgName) {
    this.norgName = norgName;
  }
  
  public String getOrgId() {
    return this.orgId;
  }
  
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  
  public String getNorGid() {
    return this.norGid;
  }
  
  public void setNorGid(String norGid) {
    this.norGid = norGid;
  }
  
  public String getYorGid() {
    return this.yorGid;
  }
  
  public void setYorGid(String yorGid) {
    this.yorGid = yorGid;
  }
}
