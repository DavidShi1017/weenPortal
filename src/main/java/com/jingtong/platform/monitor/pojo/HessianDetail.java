package com.jingtong.platform.monitor.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class HessianDetail extends SearchInfo {
  private static final long serialVersionUID = 5733916022288466038L;
  
  private int id;
  
  private int totalId;
  
  private String recordDate;
  
  private long count;
  
  private String hessianName;
  
  public String getHessianName() {
    return this.hessianName;
  }
  
  public void setHessianName(String hessianName) {
    this.hessianName = hessianName;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getTotalId() {
    return this.totalId;
  }
  
  public void setTotalId(int totalId) {
    this.totalId = totalId;
  }
  
  public String getRecordDate() {
    return this.recordDate;
  }
  
  public void setRecordDate(String recordDate) {
    this.recordDate = recordDate;
  }
  
  public long getCount() {
    return this.count;
  }
  
  public void setCount(long count) {
    this.count = count;
  }
}
