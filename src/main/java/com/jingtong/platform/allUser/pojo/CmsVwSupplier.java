package com.jingtong.platform.allUser.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class CmsVwSupplier extends SearchInfo {
  private static final long serialVersionUID = -6103728926926948912L;
  
  private String supplierNumber;
  
  private String supplierNameZh;
  
  private String supplierType;
  
  public String getSupplierNumber() {
    return this.supplierNumber;
  }
  
  public void setSupplierNumber(String supplierNumber) {
    this.supplierNumber = supplierNumber;
  }
  
  public String getSupplierNameZh() {
    return this.supplierNameZh;
  }
  
  public void setSupplierNameZh(String supplierNameZh) {
    this.supplierNameZh = supplierNameZh;
  }
  
  public String getSupplierType() {
    return this.supplierType;
  }
  
  public void setSupplierType(String supplierType) {
    this.supplierType = supplierType;
  }
}
