package com.jingtong.platform.position.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.position.dao.IPositionDao;
import com.jingtong.platform.position.pojo.Bposition;
import java.util.List;

public class PositionDaoImpl extends BaseDaoImpl implements IPositionDao {
  public int getPositionCount(Bposition position) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "position.getPositionCount", position)).intValue();
  }
  
  public List<Bposition> getPositionList(Bposition position) {
    return getSqlMapClientTemplate().queryForList(
        "position.getPositionList", position);
  }
  
  public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
    return getSqlMapClientTemplate().queryForList(
        "position.getCmsTbDictType", cmsTbDictType);
  }
  
  public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "position.getCmsTbDictJoinType", cmsTbDict);
  }
  
  public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "position.getCmsTbDict", cmsTbDict);
  }
  
  public List<Bposition> getStaffamountPositionList(Long orgId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getStaffamountPosition", orgId);
  }
  
  public List<Bposition> getAllStaffamountPosition(Long orgId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getAllStaffamountPosition", orgId);
  }
  
  public Long createPosition(Bposition bposition) {
    return (Long)getSqlMapClientTemplate().insert(
        "position.createPosition", bposition);
  }
  
  public Bposition getPositionObj(Bposition position) {
    return (Bposition)getSqlMapClientTemplate().queryForObject(
        "position.getPositionObj", position);
  }
  
  public int updatePosition(Bposition bposition) {
    return getSqlMapClientTemplate().update("position.updatePosition", 
        bposition);
  }
  
  public int getPostCustNum(Long posId) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "position.getPostCustNum", posId)).intValue();
  }
  
  public int getDeviceNum(Long posId) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "position.getDeviceNum", posId)).intValue();
  }
  
  public int updateCustPos(Long posId) {
    return getSqlMapClientTemplate().update(
        "position.updateCustPos", posId);
  }
  
  public List<Bposition> getManagementList(Long orgId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getManagementList", orgId);
  }
  
  public List<Bposition> getAssignedPosList(Long posId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getAssignedPosList", posId);
  }
  
  public List<Bposition> getUnassignedPosList(Long orgId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getUnassignedPosList", orgId);
  }
  
  public List<Bposition> getStaffamountPositionPosList(Bposition bposition) {
    return getSqlMapClientTemplate().queryForList(
        "position.getStaffamountPositionBystaIdAndOId", bposition);
  }
  
  public List<Bposition> getPositionsByEmpId(Long empId) {
    return getSqlMapClientTemplate().queryForList(
        "position.getPositionsByEmpId", empId);
  }
  
  public List<Bposition> getPosition(Bposition bposition) {
    return getSqlMapClientTemplate().queryForList(
        "position.getPosition", bposition);
  }
  
  public Bposition getPositionByUseState(Bposition bposition1) {
    return (Bposition)getSqlMapClientTemplate().queryForObject("position.getPositionByUseState", 
        bposition1);
  }
  
  public List<Bposition> getPositionListByUseState(Bposition bposition1) {
    return getSqlMapClientTemplate().queryForList("position.getPositionListByUseState", 
        bposition1);
  }
  
  public int updatePosition4User(Bposition bposition) {
    return 0;
  }
  
  public int updatePosition4Position(Bposition bposition) {
    return 0;
  }
}
