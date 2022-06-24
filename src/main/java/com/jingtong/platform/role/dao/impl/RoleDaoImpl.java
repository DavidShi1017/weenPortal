package com.jingtong.platform.role.dao.impl;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.role.dao.IRoleDao;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.station.pojo.Station;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

public class RoleDaoImpl extends BaseDaoImpl implements IRoleDao {
  public int getRoleCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getRoleCount", role)).intValue();
  }
  
  public int getRoleCount1(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getRoleCount1", role)).intValue();
  }
  
  public int getConRole(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getConRole", role)).intValue();
  }
  
  public List<Role> getRoleList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getRoleList", role);
  }
  
  public int getRole1Count(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getRole1Count", role)).intValue();
  }
  
  public List<Role> getRole1List(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getRole1List", role);
  }
  
  public String createRole(Role role) {
    return (String)getSqlMapClientTemplate().insert("role.createRole", 
        role);
  }
  
  public int updateRole(Role role) {
    return getSqlMapClientTemplate().update("role.updateRole", role);
  }
  
  public int deleteRole(Role role) {
    return getSqlMapClientTemplate().delete("role.deleteRole", role);
  }
  
  public int getRole4ConpointCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getRole4ConpointCount", role)).intValue();
  }
  
  public List<Role> getRole4ConpointList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getRole4ConpointList", role);
  }
  
  public int getRole4MenuCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getRole4MenuCount", role)).intValue();
  }
  
  public List<Role> getRole4MenuList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getRole4MenuList", role);
  }
  
  public Role getRoleByRoleId(String roleId) {
    return (Role)getSqlMapClientTemplate().queryForObject(
        "role.getRoleByRoleId", roleId);
  }
  
  public int getSelectedRole4StationCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getSelectedRole4StationCount", role)).intValue();
  }
  
  public List<Role> getSelectedRole4StationList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getSelectedRole4StationList", role);
  }
  
  public List<Role> getSelectedRole4Station(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getSelectedRole4Station", role);
  }
  
  public String selectRole4Station(final Role role) {
    return (String)getSqlMapClientTemplate().execute(
        new SqlMapClientCallback() {
          StringBuilder sb = new StringBuilder();
          
          public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
            executor.startBatch();
            Role r = new Role();
            byte b;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = role.getCodes()).length, b = 0; b < i; ) {
              String s = arrayOfString[b];
              if (StringUtils.isNotEmpty(s)) {
                r.setRoleId(s);
                r.setStationId(role.getStationId());
                if (this.sb.length() != 0)
                  this.sb.append(","); 
                this.sb.append(executor.insert(
                      "role.selectRole4User", r));
              } 
              b++;
            } 
            executor.executeBatch();
            return this.sb.toString();
          }
        });
  }
  
  public int deleteSelectedRole4Station(Role role) {
    return getSqlMapClientTemplate().delete(
        "role.deleteSelectedRole4User", role);
  }
  
  public int getSelectedRole4PositionTypeCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getSelectedRole4PositionTypeCount", role)).intValue();
  }
  
  public List<Role> getSelectedRole4PositionTypeList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getSelectedRole4PositionTypeList", role);
  }
  
  public List<Role> getSelectedRole4PositionType(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getSelectedRole4PositionType", role);
  }
  
  public String selectRole4PositionType(final Role role) {
    return (String)getSqlMapClientTemplate().execute(
        new SqlMapClientCallback() {
          StringBuilder sb = new StringBuilder();
          
          public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
            executor.startBatch();
            Role r = new Role();
            byte b;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = role.getCodes()).length, b = 0; b < i; ) {
              String s = arrayOfString[b];
              if (StringUtils.isNotEmpty(s)) {
                r.setRoleId(s);
                r.setPositionTypeId(role.getPositionTypeId());
                if (this.sb.length() != 0)
                  this.sb.append(","); 
                this.sb.append(executor.insert(
                      "role.selectRole4PositionType", r));
              } 
              b++;
            } 
            executor.executeBatch();
            return this.sb.toString();
          }
        });
  }
  
  public int deleteSelectedRole4PositionType(Role role) {
    return getSqlMapClientTemplate().delete(
        "role.deleteSelectedRole4PositionType", role);
  }
  
  public List<Station> getPositionType4RoleList(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getPositionType4RoleList", role);
  }
  
  public int getPositionType4RoleCount(Role role) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "role.getPositionType4RoleCount", role)).intValue();
  }
  
  public List<Role> getUserByRole(Role role) {
    return getSqlMapClientTemplate().queryForList(
        "role.getUserByRole", role);
  }
}
