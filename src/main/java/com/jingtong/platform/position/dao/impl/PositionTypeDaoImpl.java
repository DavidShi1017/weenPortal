package com.jingtong.platform.position.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.position.dao.IPositionTypeDao;
import com.jingtong.platform.position.pojo.BpositionType;
import java.util.List;

public class PositionTypeDaoImpl extends BaseDaoImpl implements IPositionTypeDao {
  public int getPositionTypesCount(BpositionType positionType) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "positionType.getpositionTypeCount", positionType)).intValue();
  }
  
  public List<BpositionType> getPositionTypesList(BpositionType positionType) {
    return getSqlMapClientTemplate().queryForList(
        "positionType.getpositionTypeList", positionType);
  }
  
  public List<BpositionType> exportPositionTypesList(BpositionType positionType) {
    return getSqlMapClientTemplate().queryForList(
        "positionType.exportpositionTypeList", positionType);
  }
  
  public BpositionType getPositionTypes(BpositionType positionType) {
    return (BpositionType)getSqlMapClientTemplate().queryForObject(
        "positionType.getPositionTypes", positionType);
  }
  
  public int updatePositionTypes(BpositionType positionType) {
    return getSqlMapClientTemplate().update(
        "positionType.updatePositionTypes", positionType);
  }
  
  public Long createPositionTypes(BpositionType positionType) {
    return (Long)getSqlMapClientTemplate().insert(
        "positionType.createPositionTypes", positionType);
  }
  
  public int getPositionType4RoleCount(BpositionType positionType) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "positionType.getPositionType4RoleCount", positionType)).intValue();
  }
  
  public boolean insertPos(BpositionType positionType) {
    Long l = (Long)getSqlMapClientTemplate().insert(
        "positionType.createPositionTypes", positionType);
    if (l != null && l.longValue() != 0L)
      return true; 
    return false;
  }
  
  public List<BpositionType> getPositionType4RoleList(BpositionType positionType) {
    return getSqlMapClientTemplate().queryForList(
        "positionType.getPositionType4RoleList", positionType);
  }
}
