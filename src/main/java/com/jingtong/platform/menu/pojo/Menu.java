package com.jingtong.platform.menu.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.List;

public class Menu extends SearchInfo {
  private static final long serialVersionUID = -3155216126617302268L;
  
  private Long id;
  
  private String name;
  
  private Long pid;
  
  private String pname;
  
  private String url;
  
  private String target;
  
  private int orderBy;
  
  private String userId;
  
  private String isFirst;
  
  private String isUse;
  
  private String isKuunrMenu;
  
  private String isOfficeMenu;
  
  private String isClientMenu;
  
  private String isCustMenu;
  
  private String redirectUrl;
  
  private String roleId;
  
  private Long roleMenuId;
  
  private List<Long> ids;
  
  private String ssoUser;
  
  private String ssoPwd;
  
  private String flag;
  
  private String validateType;
  
  public String getValidateType() {
    return this.validateType;
  }
  
  public void setValidateType(String validateType) {
    this.validateType = validateType;
  }
  
  public String getFlag() {
    return this.flag;
  }
  
  public void setFlag(String flag) {
    this.flag = flag;
  }
  
  public String getSsoUser() {
    return this.ssoUser;
  }
  
  public void setSsoUser(String ssoUser) {
    this.ssoUser = ssoUser;
  }
  
  public String getSsoPwd() {
    return this.ssoPwd;
  }
  
  public void setSsoPwd(String ssoPwd) {
    this.ssoPwd = ssoPwd;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Long getPid() {
    return this.pid;
  }
  
  public void setPid(Long pid) {
    this.pid = pid;
  }
  
  public String getPname() {
    return this.pname;
  }
  
  public void setPname(String pname) {
    this.pname = pname;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getTarget() {
    return this.target;
  }
  
  public void setTarget(String target) {
    this.target = target;
  }
  
  public int getOrderBy() {
    return this.orderBy;
  }
  
  public void setOrderBy(int orderBy) {
    this.orderBy = orderBy;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getIsKuunrMenu() {
    return this.isKuunrMenu;
  }
  
  public void setIsKuunrMenu(String isKuunrMenu) {
    this.isKuunrMenu = isKuunrMenu;
  }
  
  public String getIsOfficeMenu() {
    return this.isOfficeMenu;
  }
  
  public void setIsOfficeMenu(String isOfficeMenu) {
    this.isOfficeMenu = isOfficeMenu;
  }
  
  public String getIsClientMenu() {
    return this.isClientMenu;
  }
  
  public void setIsClientMenu(String isClientMenu) {
    this.isClientMenu = isClientMenu;
  }
  
  public String getIsCustMenu() {
    return this.isCustMenu;
  }
  
  public void setIsCustMenu(String isCustMenu) {
    this.isCustMenu = isCustMenu;
  }
  
  public String getRedirectUrl() {
    return this.redirectUrl;
  }
  
  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }
  
  public String getRoleId() {
    return this.roleId;
  }
  
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  
  public Long getRoleMenuId() {
    return this.roleMenuId;
  }
  
  public void setRoleMenuId(Long roleMenuId) {
    this.roleMenuId = roleMenuId;
  }
  
  public List<Long> getIds() {
    return this.ids;
  }
  
  public void setIds(List<Long> ids) {
    this.ids = ids;
  }
  
  public String getIsFirst() {
    return this.isFirst;
  }
  
  public void setIsFirst(String isFirst) {
    this.isFirst = isFirst;
  }
  
  public String getIsUse() {
    return this.isUse;
  }
  
  public void setIsUse(String isUse) {
    this.isUse = isUse;
  }
}
