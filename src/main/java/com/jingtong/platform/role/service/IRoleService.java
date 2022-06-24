package com.jingtong.platform.role.service;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.station.pojo.Station;
import java.util.List;

public interface IRoleService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  public static final String ERROR_MESSAGE = "error";
  
  public static final String ERROR_INPUT_MESSAGE = "error";
  
  public static final String ERROR_NULL_MESSAGE = "error";
  
  int getRoleCount(Role paramRole);
  
  int getRole1Count(Role paramRole);
  
  int getRoleCount1(Role paramRole);
  
  int getConRole(Role paramRole);
  
  int getPositionType4RoleCount(Role paramRole);
  
  List<Station> getPositionType4RoleList(Role paramRole);
  
  List<Role> getUserByRole(Role paramRole);
  
  List<Role> getRoleList(Role paramRole);
  
  List<Role> getRole1List(Role paramRole);
  
  StringResult createRole(Role paramRole);
  
  StringResult updateRole(Role paramRole);
  
  StringResult deleteRole(Role paramRole);
  
  int getRole4ConpointCount(Role paramRole);
  
  List<Role> getRole4ConpointList(Role paramRole);
  
  int getRole4MenuCount(Role paramRole);
  
  List<Role> getRole4MenuList(Role paramRole);
  
  Role getRoleByRoleId(String paramString);
  
  int getSelectedRole4StationCount(Role paramRole);
  
  List<Role> getSelectedRole4StationList(Role paramRole);
  
  StringResult selectRole4Station(Role paramRole);
  
  StringResult deleteSelectedRole4Station(Role paramRole);
  
  int getSelectedRole4PositionTypeCount(Role paramRole);
  
  List<Role> getSelectedRole4PositionTypeList(Role paramRole);
  
  StringResult selectRole4PositionType(Role paramRole);
  
  StringResult deleteSelectedRole4PositionType(Role paramRole);
}
