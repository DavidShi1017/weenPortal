package com.jingtong.platform.position.service.impl;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.position.dao.IPositionTypeDao;
import com.jingtong.platform.position.pojo.BpositionType;
import com.jingtong.platform.position.service.IPositionTypeService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class PositionTypeServiceImpl implements IPositionTypeService {
  private Logger logger;
  
  private IPositionTypeDao positionTypeDao;
  
  private TransactionTemplate transactionTemplate;
  
  public PositionTypeServiceImpl() {
    this
      .logger = Logger.getLogger(PositionTypeServiceImpl.class);
  }
  
  public int getPositionTypesCount(BpositionType positionType) {
    try {
      return this.positionTypeDao.getPositionTypesCount(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return 0;
    } 
  }
  
  public List<BpositionType> getPositionTypesList(BpositionType positionType) {
    try {
      return this.positionTypeDao.getPositionTypesList(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return null;
    } 
  }
  
  public List<BpositionType> exportPositionTypesList(BpositionType positionType) {
    try {
      return this.positionTypeDao.exportPositionTypesList(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return null;
    } 
  }
  
  public BpositionType getPositionTypes(BpositionType positionType) {
    try {
      return this.positionTypeDao.getPositionTypes(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return null;
    } 
  }
  
  public int updatePositionTypes(BpositionType positionType) {
    try {
      return this.positionTypeDao.updatePositionTypes(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return 0;
    } 
  }
  
  public Long createPositionTypes(BpositionType positionType) {
    try {
      return this.positionTypeDao.createPositionTypes(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return Long.valueOf(0L);
    } 
  }
  
  public int getPositionType4RoleCount(BpositionType positionType) {
    try {
      return this.positionTypeDao.getPositionType4RoleCount(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return 0;
    } 
  }
  
  public StringResult insertPos(final List<BpositionType> BList) {
    StringResult result = new StringResult();
    result = (StringResult)this.transactionTemplate
      .execute(new TransactionCallback() {
          public StringResult doInTransaction(TransactionStatus ts) {
            StringResult result = new StringResult();
            result.setCode("success");
            try {
              for (BpositionType positionType : BList)
                PositionTypeServiceImpl.this.positionTypeDao.insertPos(positionType); 
            } catch (Exception e) {
              result.setCode("error");
              ts.setRollbackOnly();
              PositionTypeServiceImpl.this.logger.error("error", e);
            } 
            return result;
          }
        });
    return result;
  }
  
  public List<BpositionType> getPositionType4RoleList(BpositionType positionType) {
    try {
      return this.positionTypeDao.getPositionType4RoleList(positionType);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(positionType), e);
      return null;
    } 
  }
  
  public IPositionTypeDao getPositionTypeDao() {
    return this.positionTypeDao;
  }
  
  public void setPositionTypeDao(IPositionTypeDao positionTypeDao) {
    this.positionTypeDao = positionTypeDao;
  }
  
  public TransactionTemplate getTransactionTemplate() {
    return this.transactionTemplate;
  }
  
  public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
    this.transactionTemplate = transactionTemplate;
  }
}
