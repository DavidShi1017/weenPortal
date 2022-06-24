package com.jingtong.platform.dict.service.impl;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.dao.IDictDao;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.framework.util.LogUtil;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DictServiceImpl implements IDictService {
  private static final Log logger = LogFactory.getLog(DictServiceImpl.class);
  
  private IDictDao dictDao;
  
  public int getCmsTbDictCount(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getCmsTbDictCount(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return 0;
    } 
  }
  
  public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getCmsTbDictList(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return null;
    } 
  }
  
  public int getDictCount(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getDictCount(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return 0;
    } 
  }
  
  public List<CmsTbDict> getDictList(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getDictList(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return null;
    } 
  }
  
  public int getCmsTbDictTypeCount(CmsTbDictType cmsTbDictType) {
    try {
      return this.dictDao.getCmsTbDictTypeCount(cmsTbDictType);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDictType), e);
      return 0;
    } 
  }
  
  public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
    try {
      return this.dictDao.getCmsTbDictTypeList(cmsTbDictType);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDictType), e);
      return null;
    } 
  }
  
  public BooleanResult createDict(CmsTbDict cmsTbDict) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      long itemId = this.dictDao.CreateDict(cmsTbDict).longValue();
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(itemId));
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("Error");
      logger.error(LogUtil.parserBean(cmsTbDict), e);
    } 
    return booleanResult;
  }
  
  public BooleanResult createDictType(CmsTbDictType cmsTbDictType) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      long dictTypeId = this.dictDao.CreateDictType(cmsTbDictType).longValue();
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(dictTypeId));
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("Error");
      logger.error(LogUtil.parserBean(cmsTbDictType), e);
    } 
    return booleanResult;
  }
  
  public BooleanResult updateDict(CmsTbDict cmsTbDict) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      int n = this.dictDao.updateDict(cmsTbDict);
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(n));
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("Error");
      logger.error(LogUtil.parserBean(cmsTbDict), e);
    } 
    return booleanResult;
  }
  
  public BooleanResult updateDictType(CmsTbDictType cmsTbDictType) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      int n = this.dictDao.updateDictType(cmsTbDictType);
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(n));
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("Error");
      logger.error(LogUtil.parserBean(cmsTbDictType), e);
    } 
    return booleanResult;
  }
  
  public CmsTbDict getCmsTbDict(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getCmsTbDict(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return null;
    } 
  }
  
  public CmsTbDictType getCmsTbDictType(CmsTbDictType cmsTbDictType) {
    try {
      return this.dictDao.getCmsTbDictType(cmsTbDictType);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDictType), e);
      return null;
    } 
  }
  
  public List<CmsTbDict> getCmsTbDictByType(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getCmsTbDictByType(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return null;
    } 
  }
  
  public List<CmsTbDict> getByCmsTbDictList(CmsTbDict cmsTbDict) {
    try {
      return this.dictDao.getByCmsTbDictList(cmsTbDict);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(cmsTbDict), e);
      return null;
    } 
  }
  
  public IDictDao getDictDao() {
    return this.dictDao;
  }
  
  public void setDictDao(IDictDao dictDao) {
    this.dictDao = dictDao;
  }
}
