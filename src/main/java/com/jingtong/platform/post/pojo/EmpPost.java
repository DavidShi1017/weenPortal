package com.jingtong.platform.post.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class EmpPost extends SearchInfo {
  private static final long serialVersionUID = 1L;
  
  private long empPostId;
  
  private String empPostName;
  
  private String orgId;
  
  private String orgName;
  
  private String empId;
  
  private Date createDate;
  
  private Date modifyDate;
  
  private String state;
  
  private String[] orgIds;
  
  private String[] empPostIds;
  
  public long getEmpPostId() {
    return this.empPostId;
  }
  
  public void setEmpPostId(long empPostId) {
    this.empPostId = empPostId;
  }
  
  public String getEmpPostName() {
    return this.empPostName;
  }
  
  public void setEmpPostName(String empPostName) {
    this.empPostName = empPostName;
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
  
  public String getEmpId() {
    return this.empId;
  }
  
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  
  public Date getCreateDate() {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String[] getOrgIds() {
    return this.orgIds;
  }
  
  public void setOrgIds(String[] orgIds) {
    this.orgIds = orgIds;
  }
  
  public String[] getEmpPostIds() {
    return this.empPostIds;
  }
  
  public void setEmpPostIds(String[] empPostIds) {
    this.empPostIds = empPostIds;
  }
}
