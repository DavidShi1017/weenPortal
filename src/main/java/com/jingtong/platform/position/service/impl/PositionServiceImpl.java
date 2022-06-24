package com.jingtong.platform.position.service.impl;

import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.position.dao.IPositionDao;
import com.jingtong.platform.position.pojo.Bposition;
import com.jingtong.platform.position.service.IPositionService;
import java.util.List;

public class PositionServiceImpl implements IPositionService {
  private IPositionDao positionDao;
  
  public int getPositionCount(Bposition position) {
    try {
      return this.positionDao.getPositionCount(position);
    } catch (Exception exception) {
      return 0;
    } 
  }
  
  public List<Bposition> getPositionList(Bposition position) {
    try {
      return this.positionDao.getPositionList(position);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getPosLevelTypeList(Bposition position) {
    try {
      return this.positionDao.getPositionList(position);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType cmsTbDictType) {
    try {
      return this.positionDao.getCmsTbDictTypeList(cmsTbDictType);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict cmsTbDict) {
    try {
      return this.positionDao.getCmsTbDictJoinTypeList(cmsTbDict);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
    try {
      return this.positionDao.getCmsTbDictList(cmsTbDict);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getStaffamountPositionList(Long orgId) {
    try {
      return this.positionDao.getStaffamountPositionList(orgId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getAllStaffamountPosition(Long orgId) {
    try {
      return this.positionDao.getAllStaffamountPosition(orgId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public Boolean updatePosition(Bposition bposition) {
    try {
      int result = 0;
      result = this.positionDao.updatePosition(bposition);
      if (result > 0)
        return Boolean.valueOf(true); 
    } catch (Exception exception) {}
    return Boolean.valueOf(false);
  }
  
  public Bposition getPositionObj(Bposition position) {
    try {
      return this.positionDao.getPositionObj(position);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getStaffamountPositionPosList(Bposition bposition) {
    try {
      return this.positionDao.getStaffamountPositionPosList(bposition);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getManagementList(Long orgId) {
    try {
      return this.positionDao.getManagementList(orgId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getAssignedPosList(Long posId) {
    try {
      return this.positionDao.getAssignedPosList(posId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getUnassignedPosList(Long orgId) {
    try {
      return this.positionDao.getUnassignedPosList(orgId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getPositionsByEmpId(Long empId) {
    try {
      return this.positionDao.getPositionsByEmpId(empId);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public int getDeviceNum(Long posId) {
    try {
      return this.positionDao.getDeviceNum(posId);
    } catch (Exception exception) {
      return 0;
    } 
  }
  
  public List<Bposition> getPosition(Bposition bposition) {
    try {
      return this.positionDao.getPosition(bposition);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public Boolean updatePosition4User(Bposition bposition) {
    try {
      int result = 0;
      result = this.positionDao.updatePosition4User(bposition);
      if (result > 0)
        return Boolean.valueOf(true); 
    } catch (Exception exception) {}
    return Boolean.valueOf(false);
  }
  
  public Boolean updatePosition4Position(Bposition bposition) {
    try {
      int result = 0;
      result = this.positionDao.updatePosition4Position(bposition);
      if (result > 0)
        return Boolean.valueOf(true); 
    } catch (Exception exception) {}
    return Boolean.valueOf(false);
  }
  
  public Bposition getPositionByUseState(Bposition bposition1) {
    try {
      return this.positionDao.getPositionByUseState(bposition1);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public List<Bposition> getPositionListByUseState(Bposition bposition1) {
    try {
      return this.positionDao.getPositionListByUseState(bposition1);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public IPositionDao getPositionDao() {
    return this.positionDao;
  }
  
  public void setPositionDao(IPositionDao positionDao) {
    this.positionDao = positionDao;
  }
  
  public String createPosition(Bposition bposition) {
    return null;
  }
  
  public String deletePosition(Long posId) {
    return null;
  }
  
  public String releasePosition(Bposition position) {
    return null;
  }
}
