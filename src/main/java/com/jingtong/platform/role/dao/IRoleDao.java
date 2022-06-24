package com.jingtong.platform.role.dao;

import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.station.pojo.Station;
import java.util.List;

public interface IRoleDao {
  int getRoleCount(Role paramRole);
  
  int getRoleCount1(Role paramRole);
  
  int getConRole(Role paramRole);
  
  List<Role> getRoleList(Role paramRole);
  
  List<Station> getPositionType4RoleList(Role paramRole);
  
  int getPositionType4RoleCount(Role paramRole);
  
  String createRole(Role paramRole);
  
  int getRole1Count(Role paramRole);
  
  List<Role> getRole1List(Role paramRole);
  
  int updateRole(Role paramRole);
  
  int deleteRole(Role paramRole);
  
  int getRole4ConpointCount(Role paramRole);
  
  List<Role> getRole4ConpointList(Role paramRole);
  
  int getRole4MenuCount(Role paramRole);
  
  List<Role> getRole4MenuList(Role paramRole);
  
  Role getRoleByRoleId(String paramString);
  
  int getSelectedRole4StationCount(Role paramRole);
  
  List<Role> getSelectedRole4StationList(Role paramRole);
  
  List<Role> getSelectedRole4Station(Role paramRole);
  
  String selectRole4Station(Role paramRole);
  
  int deleteSelectedRole4Station(Role paramRole);
  
  int getSelectedRole4PositionTypeCount(Role paramRole);
  
  List<Role> getSelectedRole4PositionTypeList(Role paramRole);
  
  List<Role> getSelectedRole4PositionType(Role paramRole);
  
  String selectRole4PositionType(Role paramRole);
  
  int deleteSelectedRole4PositionType(Role paramRole);
  
  List<Role> getUserByRole(Role paramRole);
}
