package com.jingtong.platform.conpoint.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.math.BigDecimal;
import java.util.Date;

public class Conpoint extends SearchInfo {
  private static final long serialVersionUID = -2626999442318321731L;
  
  private BigDecimal conpointId;
  
  private String conpointNum;
  
  private String conpointName;
  
  private BigDecimal menuId;
  
  private String menuName;
  
  private Date lastModify;
  
  private String userId;
  
  private String closeFlag;
  
  private String roleId;
  
  private Long roleConpointId;
  
  public BigDecimal getConpointId() {
    return this.conpointId;
  }
  
  public void setConpointId(BigDecimal conpointId) {
    this.conpointId = conpointId;
  }
  
  public String getConpointNum() {
    return this.conpointNum;
  }
  
  public void setConpointNum(String conpointNum) {
    this.conpointNum = conpointNum;
  }
  
  public String getConpointName() {
    return this.conpointName;
  }
  
  public void setConpointName(String conpointName) {
    this.conpointName = conpointName;
  }
  
  public BigDecimal getMenuId() {
    return this.menuId;
  }
  
  public void setMenuId(BigDecimal menuId) {
    this.menuId = menuId;
  }
  
  public String getMenuName() {
    return this.menuName;
  }
  
  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }
  
  public Date getLastModify() {
    return this.lastModify;
  }
  
  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getCloseFlag() {
    return this.closeFlag;
  }
  
  public void setCloseFlag(String closeFlag) {
    this.closeFlag = closeFlag;
  }
  
  public String getRoleId() {
    return this.roleId;
  }
  
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  
  public Long getRoleConpointId() {
    return this.roleConpointId;
  }
  
  public void setRoleConpointId(Long roleConpointId) {
    this.roleConpointId = roleConpointId;
  }
}
