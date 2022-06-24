package com.jingtong.platform.station.dao;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.pojo.StationOrg;
import com.jingtong.platform.station.pojo.StationRole;
import java.util.List;

public interface IStationDao {
  int getStationJsonListCount(Station paramStation);
  
  List<Station> getStationJsonList(Station paramStation);
  
  Station getStationEntity(String paramString);
  
  int updateStationEntity(Station paramStation);
  
  List<Borg> getCompanyJsonList();
  
  List<Station> getStationUser(String paramString);
  
  List<StationRole> getStationRole(String paramString);
  
  int deleteStationEntity(String paramString);
  
  String createStationEntity(Station paramStation);
  
  int searchStationUserCount(Station paramStation);
  
  int getStaffStationCount(Station paramStation);
  
  List<Station> searchStationUser(Station paramStation);
  
  int getStaffStationAmount(Station paramStation);
  
  int deleteStationUser(Station paramStation);
  
  List<Station> searchUser(Station paramStation);
  
  int searchUserCount(Station paramStation);
  
  Long insertStationUser(Station paramStation);
  
  int getStationUserCount(String paramString);
  
  List<CmsTbDict> getCustTypeList();
  
  int getStationRoleCount(String paramString);
  
  List<Station> getStationByEmpId(Long paramLong);
  
  List<Station> getStationJsonListForSelect(Station paramStation);
  
  int getStationJsonListCountForSelect(Station paramStation);
  
  int updateStationUser(AllUsers paramAllUsers);
  
  int getStationCountByEmpId(AllUsers paramAllUsers);
  
  int searchStationUserMoreCount(Station paramStation);
  
  List<Station> searchStationUserMore(Station paramStation);
  
  int deleteStationUserById(Station paramStation);
  
  Long createStationOrg(StationOrg paramStationOrg);
  
  List<StationOrg> getStationOrgList(StationOrg paramStationOrg);
  
  int updateStationOrg(StationOrg paramStationOrg);
  
  int getStationOrgCount(StationOrg paramStationOrg);
}
