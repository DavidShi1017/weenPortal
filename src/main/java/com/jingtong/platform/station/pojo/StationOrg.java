package com.jingtong.platform.station.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.io.Serializable;
import java.util.Date;

public class StationOrg extends SearchInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long stationOrgId;
  
  private String stationId;
  
  private String orgIds;
  
  private String creator;
  
  private String modifier;
  
  private Date modifyDate;
  
  private String orgId;
  
  private String orgName;
  
  public String getStationId() {
    return this.stationId;
  }
  
  public void setStationId(String stationId) {
    this.stationId = stationId;
  }
  
  public Long getStationOrgId() {
    return this.stationOrgId;
  }
  
  public void setStationOrgId(Long stationOrgId) {
    this.stationOrgId = stationOrgId;
  }
  
  public String getOrgIds() {
    return this.orgIds;
  }
  
  public void setOrgIds(String orgIds) {
    this.orgIds = orgIds;
  }
  
  public String getCreator() {
    return this.creator;
  }
  
  public void setCreator(String creator) {
    this.creator = creator;
  }
  
  public String getModifier() {
    return this.modifier;
  }
  
  public void setModifier(String modifier) {
    this.modifier = modifier;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public String getOrgId() {
    return this.orgId;
  }
  
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  
  public String getOrgName() {
    return this.orgName;
  }
  
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
