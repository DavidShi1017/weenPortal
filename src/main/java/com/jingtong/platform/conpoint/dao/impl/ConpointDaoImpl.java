package com.jingtong.platform.conpoint.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.conpoint.dao.IConpointDao;
import com.jingtong.platform.conpoint.pojo.Conpoint;
import java.math.BigDecimal;
import java.util.List;

public class ConpointDaoImpl extends BaseDaoImpl implements IConpointDao {
  public int getConpointCount(Conpoint p) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "conpoint.getConpointCount", p)).intValue();
  }
  
  public List<Conpoint> getConpointList(Conpoint p) {
    return getSqlMapClientTemplate().queryForList(
        "conpoint.getConpointList", p);
  }
  
  public int deleteConpoint(BigDecimal conpointId) {
    return getSqlMapClientTemplate().update("conpoint.deleteConpoint", 
        conpointId);
  }
  
  public Conpoint getConpointMenuPojo(BigDecimal conpointId) {
    return (Conpoint)getSqlMapClientTemplate().queryForObject(
        "conpoint.getConpointMenuPojo", conpointId);
  }
  
  public int modifyConpoint(Conpoint p) {
    return getSqlMapClientTemplate().update("conpoint.modifyConpoint", p);
  }
  
  public Object createConpoint(Conpoint c) {
    return getSqlMapClientTemplate().insert("conpoint.createConpoint", c);
  }
  
  public Conpoint isAut(Conpoint conpoint) {
    return (Conpoint)getSqlMapClientTemplate().queryForObject(
        "conpoint.isAut", conpoint);
  }
  
  public List<Conpoint> getPermissions(Conpoint conpoint) {
    return getSqlMapClientTemplate().queryForList(
        "conpoint.getPermissions", conpoint);
  }
  
  public int getRoleConpointCount(Conpoint conpoint) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "conpoint.getRoleConpointCount", conpoint)).intValue();
  }
  
  public List<Conpoint> getRoleConpointList(Conpoint conpoint) {
    return getSqlMapClientTemplate().queryForList(
        "conpoint.getRoleConpointList", conpoint);
  }
  
  public Long createRoleConpoint(Conpoint conpoint) {
    return (Long)getSqlMapClientTemplate().insert(
        "conpoint.createRoleConpoint", conpoint);
  }
  
  public int updateRoleConpoint(Conpoint conpoint) {
    return getSqlMapClientTemplate().update("conpoint.updateRoleConpoint", 
        conpoint);
  }
  
  public Conpoint getRoleConpointById(Long roleConpointId) {
    return (Conpoint)getSqlMapClientTemplate().queryForObject(
        "conpoint.getRoleConpointById", roleConpointId);
  }
  
  public int deleteConpoints(Conpoint p) {
    return getSqlMapClientTemplate().delete("conpoint.deleteConpoints", p);
  }
  
  public List<Conpoint> getConpointListJson(Conpoint p) {
    return getSqlMapClientTemplate().queryForList(
        "conpoint.getConpointListJson", p);
  }
  
  public int getRolesByConpointId(Conpoint c) {
    return ((Integer)getSqlMapClientTemplate().queryForObject("conpoint.getRolesByConpointId", c)).intValue();
  }
  
  public List<Conpoint> getConpointListIsExit(Conpoint p) {
    return getSqlMapClientTemplate().queryForList(
        "conpoint.getConpointListIsExit", p);
  }
}
