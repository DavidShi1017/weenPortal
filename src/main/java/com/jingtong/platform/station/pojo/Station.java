package com.jingtong.platform.station.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.io.Serializable;
import java.util.Date;

public class Station extends SearchInfo implements Serializable {
  private static final long serialVersionUID = -7206625149481891989L;
  
  private String stationId;
  
  private String stationName;
  
  private Long compId;
  
  private Integer stationType;
  
  private String status;
  
  private Date modify_date;
  
  private String grade;
  
  private Long orgId;
  
  private String orgName;
  
  private String userId;
  
  private String userCode;
  
  private String userName;
  
  private String custType;
  
  private Long stationUserId;
  
  private Long id;
  
  private String empCode;
  
  private Long oatype;
  
  private String oatypeName;
  
  private String isMainStation;
  
  private String gradeName;
  
  public String getStationId() {
    return this.stationId;
  }
  
  public void setStationId(String stationId) {
    this.stationId = stationId;
  }
  
  public String getStationName() {
    return this.stationName;
  }
  
  public void setStationName(String stationName) {
    this.stationName = stationName;
  }
  
  public Long getCompId() {
    return this.compId;
  }
  
  public void setCompId(Long compId) {
    this.compId = compId;
  }
  
  public Integer getStationType() {
    return this.stationType;
  }
  
  public void setStationType(Integer stationType) {
    this.stationType = stationType;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public Date getModify_date() {
    return this.modify_date;
  }
  
  public void setModify_date(Date modify_date) {
    this.modify_date = modify_date;
  }
  
  public String getGrade() {
    return this.grade;
  }
  
  public void setGrade(String grade) {
    this.grade = grade;
  }
  
  public Long getOrgId() {
    return this.orgId;
  }
  
  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }
  
  public String getOrgName() {
    return this.orgName;
  }
  
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getCustType() {
    return this.custType;
  }
  
  public void setCustType(String custType) {
    this.custType = custType;
  }
  
  public Long getStationUserId() {
    return this.stationUserId;
  }
  
  public void setStationUserId(Long stationUserId) {
    this.stationUserId = stationUserId;
  }
  
  public String getUserCode() {
    return this.userCode;
  }
  
  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getEmpCode() {
    return this.empCode;
  }
  
  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }
  
  public Long getOatype() {
    return this.oatype;
  }
  
  public void setOatype(Long oatype) {
    this.oatype = oatype;
  }
  
  public String getOatypeName() {
    return this.oatypeName;
  }
  
  public void setOatypeName(String oatypeName) {
    this.oatypeName = oatypeName;
  }
  
  public String getIsMainStation() {
    return this.isMainStation;
  }
  
  public void setIsMainStation(String isMainStation) {
    this.isMainStation = isMainStation;
  }
  
  public String getGradeName() {
    return this.gradeName;
  }
  
  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }
}
