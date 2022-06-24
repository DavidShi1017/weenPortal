package com.jingtong.platform.role.service.impl;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.role.dao.IRoleDao;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;
import com.jingtong.platform.station.pojo.Station;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RoleServiceImpl implements IRoleService {
  private static final Log logger = LogFactory.getLog(RoleServiceImpl.class);
  
  private IRoleDao roleDao;
  
  public int getRoleCount(Role role) {
    try {
      return this.roleDao.getRoleCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public int getRoleCount1(Role role) {
    try {
      return this.roleDao.getRoleCount1(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public int getConRole(Role role) {
    try {
      return this.roleDao.getConRole(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public int getRole1Count(Role role) {
    try {
      return this.roleDao.getRole1Count(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Role> getRoleList(Role role) {
    try {
      return this.roleDao.getRoleList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public int getPositionType4RoleCount(Role role) {
    try {
      return this.roleDao.getPositionType4RoleCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Station> getPositionType4RoleList(Role role) {
    try {
      return this.roleDao.getPositionType4RoleList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public List<Role> getRole1List(Role role) {
    try {
      return this.roleDao.getRole1List(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public StringResult createRole(Role role) {
    StringResult result = new StringResult();
    try {
      String id = this.roleDao.createRole(role);
      result.setResult(id);
      result.setCode("success");
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public StringResult updateRole(Role role) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.roleDao.updateRole(role);
      if (c == 1)
        result.setCode("success"); 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public StringResult deleteRole(Role role) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.roleDao.deleteRole(role);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public int getRole4ConpointCount(Role role) {
    try {
      return this.roleDao.getRole4ConpointCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Role> getRole4ConpointList(Role role) {
    try {
      return this.roleDao.getRole4ConpointList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public int getRole4MenuCount(Role role) {
    try {
      return this.roleDao.getRole4MenuCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Role> getRole4MenuList(Role role) {
    try {
      return this.roleDao.getRole4MenuList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public Role getRoleByRoleId(String roleId) {
    try {
      return this.roleDao.getRoleByRoleId(roleId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(roleId), e);
      return null;
    } 
  }
  
  public int getSelectedRole4StationCount(Role role) {
    try {
      return this.roleDao.getSelectedRole4StationCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Role> getSelectedRole4StationList(Role role) {
    try {
      return this.roleDao.getSelectedRole4StationList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public StringResult selectRole4Station(Role role) {
    StringResult result = new StringResult();
    try {
      List<Role> list = this.roleDao.getSelectedRole4Station(role);
      Map<String, Boolean> map = new HashMap<String, Boolean>();
      for (Role r : list)
        map.put(r.getRoleId(), Boolean.valueOf(true)); 
      int i = 0;
      String[] temp = new String[(role.getCodes()).length];
      byte b;
      int j;
      String[] arrayOfString1;
      for (j = (arrayOfString1 = role.getCodes()).length, b = 0; b < j; ) {
        String code = arrayOfString1[b];
        if (map.get(code) == null)
          temp[i++] = code; 
        b++;
      } 
      role.setCodes(temp);
      String ids = this.roleDao.selectRole4Station(role);
      result.setResult(ids);
      result.setCode("success");
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public StringResult deleteSelectedRole4Station(Role role) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.roleDao.deleteSelectedRole4Station(role);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public int getSelectedRole4PositionTypeCount(Role role) {
    try {
      return this.roleDao.getSelectedRole4PositionTypeCount(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return 0;
    } 
  }
  
  public List<Role> getSelectedRole4PositionTypeList(Role role) {
    try {
      return this.roleDao.getSelectedRole4PositionTypeList(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public StringResult selectRole4PositionType(Role role) {
    StringResult result = new StringResult();
    try {
      List<Role> list = this.roleDao.getSelectedRole4PositionType(role);
      Map<String, Boolean> map = new HashMap<String, Boolean>();
      for (Role r : list)
        map.put(r.getRoleId(), Boolean.valueOf(true)); 
      int i = 0;
      String[] temp = new String[(role.getCodes()).length];
      byte b;
      int j;
      String[] arrayOfString1;
      for (j = (arrayOfString1 = role.getCodes()).length, b = 0; b < j; ) {
        String code = arrayOfString1[b];
        if (map.get(code) == null)
          temp[i++] = code; 
        b++;
      } 
      role.setCodes(temp);
      String ids = this.roleDao.selectRole4PositionType(role);
      result.setResult(ids);
      result.setCode("success");
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public StringResult deleteSelectedRole4PositionType(Role role) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.roleDao.deleteSelectedRole4PositionType(role);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
    } 
    return result;
  }
  
  public List<Role> getUserByRole(Role role) {
    try {
      return this.roleDao.getUserByRole(role);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(role), e);
      return null;
    } 
  }
  
  public IRoleDao getRoleDao() {
    return this.roleDao;
  }
  
  public void setRoleDao(IRoleDao roleDao) {
    this.roleDao = roleDao;
  }
}
