package com.jingtong.platform.menu.pojo;

import java.io.Serializable;

public class Tree4Ajax implements Serializable {
  private static final long serialVersionUID = -8283009769200561139L;
  
  private String id;
  
  private String text;
  
  private String state;
  
  private String target;
  
  private String attributes;
  
  private String redirectAttributes;
  
  private int orderBy;
  
  private String isFirst;
  
  private String iconCls;
  
  public String getIconCls() {
    return this.iconCls;
  }
  
  public void setIconCls(String iconCls) {
    this.iconCls = iconCls;
  }
  
  public String getIsFirst() {
    return this.isFirst;
  }
  
  public void setIsFirst(String isFirst) {
    this.isFirst = isFirst;
  }
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getText() {
    return this.text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getTarget() {
    return this.target;
  }
  
  public void setTarget(String target) {
    this.target = target;
  }
  
  public String getAttributes() {
    return this.attributes;
  }
  
  public void setAttributes(String attributes) {
    this.attributes = attributes;
  }
  
  public String getRedirectAttributes() {
    return this.redirectAttributes;
  }
  
  public void setRedirectAttributes(String redirectAttributes) {
    this.redirectAttributes = redirectAttributes;
  }
  
  public int getOrderBy() {
    return this.orderBy;
  }
  
  public void setOrderBy(int orderBy) {
    this.orderBy = orderBy;
  }
}
