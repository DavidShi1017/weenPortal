package com.jingtong.platform.staffAmount.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class StaffAmount extends SearchInfo {
  private static final long serialVersionUID = 8952907243103537613L;
  
  private Long PId;
  
  private String[] PIds;
  
  private Long orgId;
  
  private String stationId;
  
  private Long amount;
  
  private Long amountU;
  
  private Date lastModify;
  
  private String state;
  
  private String orgName;
  
  private String stationName;
  
  private String positionTypeName;
  
  private String[] orgIdarrs;
  
  private String last_Modify;
  
  private String amounts;
  
  private String orgParentName;
  
  private Long companyId;
  
  private String companyName;
  
  public Long getPId() {
    return this.PId;
  }
  
  public void setPId(Long pId) {
    this.PId = pId;
  }
  
  public Long getOrgId() {
    return this.orgId;
  }
  
  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }
  
  public String getStationId() {
    return this.stationId;
  }
  
  public void setStationId(String stationId) {
    this.stationId = stationId;
  }
  
  public Long getAmount() {
    return this.amount;
  }
  
  public void setAmount(Long amount) {
    this.amount = amount;
  }
  
  public Date getLastModify() {
    return this.lastModify;
  }
  
  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getOrgName() {
    return this.orgName;
  }
  
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  public String getStationName() {
    return this.stationName;
  }
  
  public void setStationName(String stationName) {
    this.stationName = stationName;
  }
  
  public String getPositionTypeName() {
    return this.positionTypeName;
  }
  
  public void setPositionTypeName(String positionTypeName) {
    this.positionTypeName = positionTypeName;
  }
  
  public String[] getOrgIdarrs() {
    return this.orgIdarrs;
  }
  
  public void setOrgIdarrs(String[] orgIdarrs) {
    this.orgIdarrs = orgIdarrs;
  }
  
  public String getLast_Modify() {
    return this.last_Modify;
  }
  
  public void setLast_Modify(String last_Modify) {
    this.last_Modify = last_Modify;
  }
  
  public String getAmounts() {
    return this.amounts;
  }
  
  public void setAmounts(String amounts) {
    this.amounts = amounts;
  }
  
  public Long getCompanyId() {
    return this.companyId;
  }
  
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }
  
  public String getCompanyName() {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  
  public String getOrgParentName() {
    return this.orgParentName;
  }
  
  public void setOrgParentName(String orgParentName) {
    this.orgParentName = orgParentName;
  }
  
  public String[] getPIds() {
    return this.PIds;
  }
  
  public void setPIds(String[] pIds) {
    this.PIds = pIds;
  }
  
  public Long getAmountU() {
    return this.amountU;
  }
  
  public void setAmountU(Long amountU) {
    this.amountU = amountU;
  }
  
  public static long getSerialversionuid() {
    return 8952907243103537613L;
  }
}
