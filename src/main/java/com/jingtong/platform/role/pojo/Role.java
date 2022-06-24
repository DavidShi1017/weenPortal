package com.jingtong.platform.role.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
import java.util.Date;

public class Role extends SearchInfo {
  private static final long serialVersionUID = -5100230087589323253L;
  
  private String roleId;
  
  private String roleName;
  
  private String descn;
  
  private String state;
  
  private Date lastModify;
  
  private String ownFlag;
  
  private String roleType;
  
  private String stationId;
  
  private Long stationRoleId;
  
  private Long conpointId;
  
  private String positionTypeId;
  
  private Long pyRoleId;
  
  private String id;
  
  private Long menuId;
  
  private String emp_code;
  
  private String emp_name;
  
  private String orgName;
  
  private String kunnrSign;
  
  public String getKunnrSign() {
    return this.kunnrSign;
  }
  
  public void setKunnrSign(String kunnrSign) {
    this.kunnrSign = kunnrSign;
  }
  
  public String getEmp_name() {
    return this.emp_name;
  }
  
  public void setEmp_name(String emp_name) {
    this.emp_name = emp_name;
  }
  
  public String getOrgName() {
    return this.orgName;
  }
  
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  public String getEmp_code() {
    return this.emp_code;
  }
  
  public void setEmp_code(String emp_code) {
    this.emp_code = emp_code;
  }
  
  public String getRoleId() {
    return this.roleId;
  }
  
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  
  public String getRoleName() {
    return this.roleName;
  }
  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  public String getDescn() {
    return this.descn;
  }
  
  public void setDescn(String descn) {
    this.descn = descn;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public Date getLastModify() {
    return this.lastModify;
  }
  
  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }
  
  public String getOwnFlag() {
    return this.ownFlag;
  }
  
  public void setOwnFlag(String ownFlag) {
    this.ownFlag = ownFlag;
  }
  
  public String getStationId() {
    return this.stationId;
  }
  
  public void setStationId(String stationId) {
    this.stationId = stationId;
  }
  
  public Long getStationRoleId() {
    return this.stationRoleId;
  }
  
  public void setStationRoleId(Long stationRoleId) {
    this.stationRoleId = stationRoleId;
  }
  
  public Long getConpointId() {
    return this.conpointId;
  }
  
  public void setConpointId(Long conpointId) {
    this.conpointId = conpointId;
  }
  
  public String getPositionTypeId() {
    return this.positionTypeId;
  }
  
  public void setPositionTypeId(String positionTypeId) {
    this.positionTypeId = positionTypeId;
  }
  
  public Long getPyRoleId() {
    return this.pyRoleId;
  }
  
  public void setPyRoleId(Long pyRoleId) {
    this.pyRoleId = pyRoleId;
  }
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public Long getMenuId() {
    return this.menuId;
  }
  
  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }
  
  public String getRoleType() {
    return this.roleType;
  }
  
  public void setRoleType(String roleType) {
    this.roleType = roleType;
  }
}
