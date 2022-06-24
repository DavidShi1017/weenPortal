package com.jingtong.platform.station.dao.impl;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.station.dao.IStationDao;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.pojo.StationOrg;
import com.jingtong.platform.station.pojo.StationRole;
import java.util.List;

public class StationDaoImpl extends BaseDaoImpl implements IStationDao {
  public List<Station> getStationJsonList(Station s) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationJsonList", s);
  }
  
  public int getStationJsonListCount(Station s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationJsonListCount", s)).intValue();
  }
  
  public int getStaffStationCount(Station s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStaffStationcount", s)).intValue();
  }
  
  public int getStaffStationAmount(Station s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStaffStationAmount", s)).intValue();
  }
  
  public List<Station> getStationJsonListForSelect(Station station) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationJsonListForSelect", station);
  }
  
  public int getStationJsonListCountForSelect(Station station) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationJsonListCountForSelect", station)).intValue();
  }
  
  public Station getStationEntity(String stationId) {
    return (Station)getSqlMapClientTemplate().queryForObject(
        "station.getStationEntity", stationId);
  }
  
  public List<Borg> getCompanyJsonList() {
    return getSqlMapClientTemplate().queryForList(
        "station.getCompanyJsonList");
  }
  
  public List<CmsTbDict> getCustTypeList() {
    return getSqlMapClientTemplate().queryForList(
        "station.getCustTypeList");
  }
  
  public int updateStationEntity(Station s) {
    return getSqlMapClientTemplate().update(
        "station.updateStationEntity", s);
  }
  
  public List<Station> getStationUser(String stationId) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationUser", stationId);
  }
  
  public List<StationRole> getStationRole(String stationId) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationRole", stationId);
  }
  
  public int deleteStationEntity(String stationId) {
    return getSqlMapClientTemplate().update(
        "station.deleteStationEntity", stationId);
  }
  
  public String createStationEntity(Station s) {
    return (String)getSqlMapClientTemplate().insert(
        "station.createStationEntity", s);
  }
  
  public int searchStationUserCount(Station s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.searchStationUserCount", s)).intValue();
  }
  
  public List<Station> searchStationUser(Station s) {
    return getSqlMapClientTemplate().queryForList(
        "station.searchStationUser", s);
  }
  
  public int deleteStationUser(Station s) {
    return getSqlMapClientTemplate().delete(
        "station.deleteStationUser", s);
  }
  
  public List<Station> searchUser(Station s) {
    return getSqlMapClientTemplate().queryForList(
        "station.searchUser", s);
  }
  
  public int searchUserCount(Station s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.searchUserCount", s)).intValue();
  }
  
  public Long insertStationUser(Station s) {
    return (Long)getSqlMapClientTemplate().insert(
        "station.insertStationUser", s);
  }
  
  public int getStationUserCount(String stationId) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationUserCount", stationId)).intValue();
  }
  
  public int getStationRoleCount(String stationId) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationRoleCount", stationId)).intValue();
  }
  
  public List<Station> getStationByEmpId(Long empId) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationByEmpId", empId);
  }
  
  public int updateStationUser(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("station.updateStationUser", allUsers);
  }
  
  public int getStationCountByEmpId(AllUsers allUsers) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationCountByEmpId", allUsers)).intValue();
  }
  
  public int searchStationUserMoreCount(Station station) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.searchStationUserMoreCount", station)).intValue();
  }
  
  public List<Station> searchStationUserMore(Station station) {
    return getSqlMapClientTemplate().queryForList(
        "station.searchStationUserMore", station);
  }
  
  public int deleteStationUserById(Station station) {
    return getSqlMapClientTemplate().update("station.deleteStationUserById", station);
  }
  
  public Long createStationOrg(StationOrg s) {
    return (Long)getSqlMapClientTemplate().insert("station.createStationOrg", s);
  }
  
  public List<StationOrg> getStationOrgList(StationOrg s) {
    return getSqlMapClientTemplate().queryForList(
        "station.getStationOrgList", s);
  }
  
  public int updateStationOrg(StationOrg s) {
    return getSqlMapClientTemplate().update("station.updateStationOrg", s);
  }
  
  public int getStationOrgCount(StationOrg s) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "station.getStationOrgCount", s)).intValue();
  }
}
