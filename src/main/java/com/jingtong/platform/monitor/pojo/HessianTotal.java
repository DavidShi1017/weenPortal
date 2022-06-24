package com.jingtong.platform.monitor.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class HessianTotal extends SearchInfo {
  private static final long serialVersionUID = 5733916022288466038L;
  
  private int id;
  
  private String hessianName;
  
  private String hessianState;
  
  private Date createDate;
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getHessianName() {
    return this.hessianName;
  }
  
  public void setHessianName(String hessianName) {
    this.hessianName = hessianName;
  }
  
  public String getHessianState() {
    return this.hessianState;
  }
  
  public void setHessianState(String hessianState) {
    this.hessianState = hessianState;
  }
  
  public Date getCreateDate() {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
