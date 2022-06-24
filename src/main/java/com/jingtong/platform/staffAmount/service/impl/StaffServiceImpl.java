package com.jingtong.platform.staffAmount.service.impl;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.staffAmount.dao.IStaffDao;
import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.staffAmount.service.IStaffService;
import com.jingtong.platform.station.pojo.Station;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class StaffServiceImpl implements IStaffService {
  private static final Log logger = LogFactory.getLog(StaffServiceImpl.class);
  
  private IStaffDao iStaffDao;
  
  private TransactionTemplate transactionTemplate;
  
  public int getStaffTotal(StaffAmount s) {
    try {
      return this.iStaffDao.getStaffTotal(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return 0;
    } 
  }
  
  public List<StaffAmount> getStaffList(StaffAmount s) {
    try {
      return this.iStaffDao.getStaffList(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return null;
    } 
  }
  
  public StringResult updateStaffAmounts(final StaffAmount staffAmount) {
    StringResult result = new StringResult();
    result = (StringResult)this.transactionTemplate.execute(new TransactionCallback() {
          public StringResult doInTransaction(TransactionStatus ts) {
            StringResult result = new StringResult();
            try {
              int c = StaffServiceImpl.this.iStaffDao.updateStaffAmounts(staffAmount);
              result.setResult(String.valueOf(c));
              result.setCode("success");
            } catch (Exception e) {
              StaffServiceImpl.logger.error(LogUtil.parserBean(staffAmount), e);
            } 
            return result;
          }
        });
    return result;
  }
  
  public IStaffDao getiStaffDao() {
    return this.iStaffDao;
  }
  
  public void setiStaffDao(IStaffDao iStaffDao) {
    this.iStaffDao = iStaffDao;
  }
  
  public TransactionTemplate getTransactionTemplate() {
    return this.transactionTemplate;
  }
  
  public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
    this.transactionTemplate = transactionTemplate;
  }
  
  public int getStaffAmountCount(StaffAmount staffAmount) {
    try {
      return this.iStaffDao.getStaffAmountCount(staffAmount);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(staffAmount), e);
      return 0;
    } 
  }
  
  public StringResult createStaff(final StaffAmount staffAmount) {
    StringResult result = new StringResult();
    result = (StringResult)this.transactionTemplate.execute(new TransactionCallback() {
          public StringResult doInTransaction(TransactionStatus ts) {
            StringResult result = new StringResult();
            try {
              Long id = StaffServiceImpl.this.iStaffDao.createStaff(staffAmount);
              result.setResult(id.toString());
              result.setCode("success");
            } catch (Exception e) {
              result.setCode("error");
              StaffServiceImpl.logger.error(LogUtil.parserBean(staffAmount), e);
            } 
            return result;
          }
        });
    return result;
  }
  
  public List<Station> blurSearchStaff(Station station) {
    try {
      return this.iStaffDao.blurSearchStaff(station);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(station), e);
      return null;
    } 
  }
  
  public int getStaffAmountCountU(StaffAmount staffAmount) {
    try {
      return this.iStaffDao.getStaffAmountCountU(staffAmount);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(staffAmount), e);
      return 0;
    } 
  }
}
