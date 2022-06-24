package com.jingtong.platform.allUser.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.sql.Date;

public class UserCustomer extends SearchInfo {
  private static final long serialVersionUID = 8697596061004031485L;
  
  public long id;
  
  public String empCode;
  
  public String customerNumber;
  
  public Date beginDate;
  
  public Date endDate;
  
  public Date createDate;
  
  public String createUser;
  
  public Date modifyDate;
  
  public String modifyUser;
  
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
  
  public String getCustomerNumber() {
    return this.customerNumber;
  }
  
  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
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
}
