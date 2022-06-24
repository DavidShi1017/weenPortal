package com.jingtong.platform.allUser.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.sql.Date;

public class UserKunnr extends SearchInfo {
  private static final long serialVersionUID = 1000000000000L;
  
  private long id;
  
  private String empCode;
  
  private String kunnr;
  
  private Date beginDate;
  
  private Date endDate;
  
  private Date createDate;
  
  private String createUser;
  
  private Date modifyDate;
  
  private String modifyUser;
  
  private String empName;
  
  private String kunnrName;
  
  public long getId() {
    return this.id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getEmpCode() {
    return this.empCode;
  }
  
  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }
  
  public String getKunnr() {
    return this.kunnr;
  }
  
  public void setKunnr(String kunnr) {
    this.kunnr = kunnr;
  }
  
  public Date getBeginDate() {
    return this.beginDate;
  }
  
  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }
  
  public Date getEndDate() {
    return this.endDate;
  }
  
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  public Date getCreateDate() {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  public String getCreateUser() {
    return this.createUser;
  }
  
  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }
  
  public Date getModifyDate() {
    return this.modifyDate;
  }
  
  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public String getModifyUser() {
    return this.modifyUser;
  }
  
  public void setModifyUser(String modifyUser) {
    this.modifyUser = modifyUser;
  }
  
  public String getEmpName() {
    return this.empName;
  }
  
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  
  public String getKunnrName() {
    return this.kunnrName;
  }
  
  public void setKunnrName(String kunnrName) {
    this.kunnrName = kunnrName;
  }
}
