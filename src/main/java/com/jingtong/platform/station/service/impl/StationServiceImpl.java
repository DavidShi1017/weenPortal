package com.jingtong.platform.station.service.impl;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.station.dao.IStationDao;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.pojo.StationOrg;
import com.jingtong.platform.station.pojo.StationRole;
import com.jingtong.platform.station.service.IStationService;
import java.util.List;
import org.apache.log4j.Logger;

public class StationServiceImpl implements IStationService {
  private static final Logger logger = Logger.getLogger(StationServiceImpl.class);
  
  private IStationDao stationDao;
  
  public List<Station> getStationJsonList(Station s) {
    try {
      return this.stationDao.getStationJsonList(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return null;
    } 
  }
  
  public List<Borg> getCompanyJsonList() {
    try {
      return this.stationDao.getCompanyJsonList();
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(getClass()), e);
      return null;
    } 
  }
  
  public List<CmsTbDict> getCustTypeList() {
    try {
      return this.stationDao.getCustTypeList();
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(getClass()), e);
      return null;
    } 
  }
  
  public int getStationJsonListCount(Station s) {
    try {
      return this.stationDao.getStationJsonListCount(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return 0;
    } 
  }
  
  public int getStationJsonListCountForSelect(Station station) {
    try {
      return this.stationDao.getStationJsonListCountForSelect(station);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(station), e);
      return 0;
    } 
  }
  
  public List<Station> getStationJsonListForSelect(Station station) {
    try {
      return this.stationDao.getStationJsonListForSelect(station);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(station), e);
      return null;
    } 
  }
  
  public Station getStationEntity(String stationId) {
    try {
      return this.stationDao.getStationEntity(stationId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(stationId), e);
      return null;
    } 
  }
  
  public int updateStationEntity(Station s) {
    try {
      return this.stationDao.updateStationEntity(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return 0;
    } 
  }
  
  public List<Station> getStationUser(String stationId) {
    try {
      return this.stationDao.getStationUser(stationId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(stationId), e);
      return null;
    } 
  }
  
  public List<StationRole> getStationRole(String stationId) {
    try {
      return this.stationDao.getStationRole(stationId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(stationId), e);
      return null;
    } 
  }
  
  public int deleteStationEntity(String stationId) {
    try {
      return this.stationDao.deleteStationEntity(stationId);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(stationId), e);
      return 0;
    } 
  }
  
  public String createStationEntity(Station s) {
    try {
      return this.stationDao.createStationEntity(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return null;
    } 
  }
  
  public int searchStationUserCount(Station s) {
    try {
      return this.stationDao.searchStationUserCount(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return 0;
    } 
  }
  
  public List<Station> searchStationUser(Station s) {
    try {
      return this.stationDao.searchStationUser(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return null;
    } 
  }
  
  public int deleteStationUser(Station s) {
    try {
      return this.stationDao.deleteStationUser(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return 0;
    } 
  }
  
  public List<Station> searchUser(Station s) {
    try {
      return this.stationDao.searchUser(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(getClass()), e);
      return null;
    } 
  }
  
  public int searchUserCount(Station s) {
    try {
      return this.stationDao.searchUserCount(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(getClass()), e);
      return 0;
    } 
  }
  
  public Long insertStationUser(Station s) {
    try {
      return this.stationDao.insertStationUser(s);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(s), e);
      return Long.valueOf(0L);
    } 
  }
  
  public int getStationUserCount(String stationId) {
    try {
      return this.stationDao.getStationUserCount(stationId);
    } catch (Exception e) {
      logger.error(stationId, e);
      return 0;
    } 
  }
  
  public int getStaffStationCount(Station s) {
    try {
      return this.stationDao.getStaffStationCount(s);
    } catch (Exception e) {
      logger.error(s.getStationId(), e);
      return 0;
    } 
  }
  
  public int getStaffStationAmount(Station s) {
    try {
      return this.stationDao.getStaffStationAmount(s);
    } catch (Exception e) {
      logger.error(s.getStationId(), e);
      return 0;
    } 
  }
  
  public int getStationRoleCount(String stationId) {
    try {
      return this.stationDao.getStationRoleCount(stationId);
    } catch (Exception e) {
      logger.error(stationId, e);
      return 0;
    } 
  }
  
  public List<Station> getStationByEmpId(Long empId) {
    try {
      return this.stationDao.getStationByEmpId(empId);
    } catch (Exception e) {
      logger.error(empId, e);
      return null;
    } 
  }
  
  public IStationDao getStationDao() {
    return this.stationDao;
  }
  
  public void setStationDao(IStationDao stationDao) {
    this.stationDao = stationDao;
  }
  
  public BooleanResult updateStationUser(AllUsers allUsers) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      int n = this.stationDao.updateStationUser(allUsers);
      if (n == 1)
        booleanResult.setResult(true); 
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return booleanResult;
  }
  
  public String getStationCountByEmpId(AllUsers allUsers) {
    try {
      int n = this.stationDao.getStationCountByEmpId(allUsers);
      if (n == 0)
        return "unexist"; 
      return "exist";
    } catch (Exception e) {
      logger.error(allUsers, e);
      return null;
    } 
  }
  
  public int searchStationUserMoreCount(Station station) {
    try {
      return this.stationDao.searchStationUserMoreCount(station);
    } catch (Exception e) {
      logger.error(station, e);
      return 0;
    } 
  }
  
  public List<Station> searchStationUserMore(Station station) {
    try {
      return this.stationDao.searchStationUserMore(station);
    } catch (Exception e) {
      logger.error(station, e);
      return null;
    } 
  }
  
  public int deleteStationUserById(Station station) {
    try {
      return this.stationDao.deleteStationUserById(station);
    } catch (Exception e) {
      logger.error(station, e);
      return 0;
    } 
  }
  
  public Long createStationOrg(StationOrg s) {
    try {
      int result = this.stationDao.getStationOrgCount(s);
      if (result > 0)
        return Long.valueOf(this.stationDao.updateStationOrg(s)); 
      return this.stationDao.createStationOrg(s);
    } catch (Exception e) {
      logger.error(s, e);
      return Long.valueOf(0L);
    } 
  }
  
  public List<StationOrg> getStationOrgList(StationOrg s) {
    try {
      return this.stationDao.getStationOrgList(s);
    } catch (Exception e) {
      logger.error(s, e);
      return null;
    } 
  }
  
  public int updateStationOrg(StationOrg s) {
    try {
      return this.stationDao.updateStationOrg(s);
    } catch (Exception e) {
      logger.error(s, e);
      return 0;
    } 
  }
}
