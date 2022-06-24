package com.jingtong.platform.org.pojo;

import java.io.Serializable;

public class SapTOrgUnit implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long parentId;
  
  private Long nodeid;
  
  private String porgUnit;
  
  private String ptxtsh;
  
  private String orgUnit;
  
  private String txtsh;
  
  private String langu;
  
  private String DateTO;
  
  private String DateFrom;
  
  public String getOrgUnit() {
    return this.orgUnit;
  }
  
  public void setOrgUnit(String orgUnit) {
    this.orgUnit = orgUnit;
  }
  
  public String getLangu() {
    return this.langu;
  }
  
  public void setLangu(String langu) {
    this.langu = langu;
  }
  
  public String getDateTO() {
    return this.DateTO;
  }
  
  public void setDateTO(String dateTO) {
    this.DateTO = dateTO;
  }
  
  public String getDateFrom() {
    return this.DateFrom;
  }
  
  public void setDateFrom(String dateFrom) {
    this.DateFrom = dateFrom;
  }
  
  public String getTxtsh() {
    return this.txtsh;
  }
  
  public void setTxtsh(String txtsh) {
    this.txtsh = txtsh;
  }
  
  public String getPorgUnit() {
    return this.porgUnit;
  }
  
  public void setPorgUnit(String porgUnit) {
    this.porgUnit = porgUnit;
  }
  
  public String getPtxtsh() {
    return this.ptxtsh;
  }
  
  public void setPtxtsh(String ptxtsh) {
    this.ptxtsh = ptxtsh;
  }
  
  public Long getNodeid() {
    return this.nodeid;
  }
  
  public void setNodeid(Long nodeid) {
    this.nodeid = nodeid;
  }
  
  public Long getParentId() {
    return this.parentId;
  }
  
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }
}
