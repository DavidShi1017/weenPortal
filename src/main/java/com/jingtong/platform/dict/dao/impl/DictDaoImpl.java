package com.jingtong.platform.dict.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.dict.dao.IDictDao;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import java.util.List;

public class DictDaoImpl extends BaseDaoImpl implements IDictDao {
  public int getCmsTbDictCount(CmsTbDict cmsTbDict) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "dict.getCmsTbDictCount", cmsTbDict)).intValue();
  }
  
  public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "dict.getCmsTbDictList", cmsTbDict);
  }
  
  public int getDictCount(CmsTbDict cmsTbDict) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "dict.getDictCount", cmsTbDict)).intValue();
  }
  
  public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "dict.getDictList", cmsTbDict);
  }
  
  public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "dict.getCmsTbDictTypeCount", cmsTbDictType)).intValue();
  }
  
  public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
    return getSqlMapClientTemplate().queryForList(
        "dict.getCmsTbDictTypeList", cmsTbDictType);
  }
  
  public Long CreateDict(CmsTbDict cmsTbDict) {
    return (Long)getSqlMapClientTemplate().insert("dict.createDict", 
        cmsTbDict);
  }
  
  public Long CreateDictType(CmsTbDictType cmsTbDictType) {
    return (Long)getSqlMapClientTemplate().insert("dict.createDictType", 
        cmsTbDictType);
  }
  
  public int updateDict(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().update("dict.updateDict", cmsTbDict);
  }
  
  public int updateDictType(CmsTbDictType cmsTbDictType) {
    return getSqlMapClientTemplate().update("dict.updateDictType", 
        cmsTbDictType);
  }
  
  public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict) {
    return (CmsTbDict)getSqlMapClientTemplate().queryForObject(
        "dict.getCmsTbDict", cmsTbDict);
  }
  
  public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType) {
    return (CmsTbDictType)getSqlMapClientTemplate().queryForObject(
        "dict.getCmsTbDictType", cmsTbDictType);
  }
  
  public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "dict.getCmsTbDictListByType", cmsTbDict);
  }
  
  public List<CmsTbDict> getByCmsTbDictList(CmsTbDict cmsTbDict) {
    return getSqlMapClientTemplate().queryForList(
        "dict.getByCmsTbDictList", cmsTbDict);
  }
}
