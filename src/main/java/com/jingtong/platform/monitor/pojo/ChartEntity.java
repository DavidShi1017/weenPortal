package com.jingtong.platform.monitor.pojo;

import java.util.List;

public class ChartEntity {
  private String name;
  
  private List<Long> data;
  
  private List<String> att;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<Long> getData() {
    return this.data;
  }
  
  public void setData(List<Long> data) {
    this.data = data;
  }
  
  public List<String> getAtt() {
    return this.att;
  }
  
  public void setAtt(List<String> att) {
    this.att = att;
  }
}
