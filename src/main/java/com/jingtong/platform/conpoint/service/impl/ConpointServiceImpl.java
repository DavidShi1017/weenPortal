package com.jingtong.platform.conpoint.service.impl;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.conpoint.dao.IConpointDao;
import com.jingtong.platform.conpoint.pojo.Conpoint;
import com.jingtong.platform.conpoint.service.IConpointService;
import com.jingtong.platform.framework.util.LogUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConpointServiceImpl implements IConpointService {
  private static final Log logger = LogFactory.getLog(ConpointServiceImpl.class);
  
  private IConpointDao conpointDao;
  
  public int getConpointCount(Conpoint p) {
    try {
      return this.conpointDao.getConpointCount(p);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
      return 0;
    } 
  }
  
  public List<Conpoint> getConpointList(Conpoint p) {
    try {
      return this.conpointDao.getConpointList(p);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
      return null;
    } 
  }
  
  public int deleteConpoint(BigDecimal conpointId) {
    try {
      return this.conpointDao.deleteConpoint(conpointId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpointId), e);
      return 0;
    } 
  }
  
  public Conpoint getConpointMenuPojo(BigDecimal conpointId) {
    try {
      return this.conpointDao.getConpointMenuPojo(conpointId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpointId), e);
      return null;
    } 
  }
  
  public int modifyConpoint(Conpoint p) {
    try {
      return this.conpointDao.modifyConpoint(p);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
      return 0;
    } 
  }
  
  public Object createConpoint(Conpoint c) {
    try {
      return this.conpointDao.createConpoint(c);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(c), e);
      return null;
    } 
  }
  
  public String isAut(Conpoint conpoint) {
    try {
      if (this.conpointDao.isAut(conpoint) != null)
        return "true"; 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpoint), e);
      return null;
    } 
    return "false";
  }
  
  public Map<BigDecimal, String> getPermissions(String userId, String[] conpointId) {
    Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();
    try {
      Conpoint conpoint = new Conpoint();
      conpoint.setUserId(userId);
      conpoint.setCodes(conpointId);
      List<Conpoint> list = this.conpointDao.getPermissions(conpoint);
      if (list != null && list.size() > 0)
        for (Conpoint c : list)
          map.put(c.getConpointId(), c.getCloseFlag());  
    } catch (Exception e) {
      logger.error("userId:" + userId + "conpointId:" + conpointId, e);
    } 
    return map;
  }
  
  public int getRoleConpointCount(Conpoint conpoint) {
    try {
      return this.conpointDao.getRoleConpointCount(conpoint);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpoint), e);
      return 0;
    } 
  }
  
  public List<Conpoint> getRoleConpointList(Conpoint conpoint) {
    try {
      return this.conpointDao.getRoleConpointList(conpoint);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpoint), e);
      return null;
    } 
  }
  
  public StringResult createRoleConpoint(Conpoint conpoint) {
    StringResult result = new StringResult();
    try {
      Long id = this.conpointDao.createRoleConpoint(conpoint);
      result.setResult(id.toString());
      result.setCode("success");
    } catch (Exception e) {
      result.setCode("error");
      result.setResult("error");
      logger.error(LogUtil.parserBean(conpoint), e);
    } 
    return result;
  }
  
  public StringResult updateRoleConpoint(Conpoint conpoint) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.conpointDao.updateRoleConpoint(conpoint);
      if (c == 1)
        result.setCode("success"); 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpoint), e);
    } 
    return result;
  }
  
  public Conpoint getRoleConpointById(Long roleConpointId) {
    try {
      return this.conpointDao.getRoleConpointById(roleConpointId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(roleConpointId), e);
      return null;
    } 
  }
  
  public StringResult deleteRoleConpoint(Conpoint conpoint) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.conpointDao.updateRoleConpoint(conpoint);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(conpoint), e);
    } 
    return result;
  }
  
  public IConpointDao getConpointDao() {
    return this.conpointDao;
  }
  
  public void setConpointDao(IConpointDao conpointDao) {
    this.conpointDao = conpointDao;
  }
  
  public StringResult deleteConpoints(Conpoint p) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.conpointDao.deleteConpoints(p);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
    } 
    return result;
  }
  
  public List<Conpoint> getConpointListJson(Conpoint p) {
    try {
      return this.conpointDao.getConpointListJson(p);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
      return null;
    } 
  }
  
  public int getRolesByConpointId(Conpoint c) {
    try {
      return this.conpointDao.getRolesByConpointId(c);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(c), e);
      return 0;
    } 
  }
  
  public List<Conpoint> getConpointListIsExit(Conpoint p) {
    try {
      return this.conpointDao.getConpointListIsExit(p);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(p), e);
      return null;
    } 
  }
}
