package com.jingtong.platform.station.service;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.station.pojo.Station;
import com.jingtong.platform.station.pojo.StationOrg;
import com.jingtong.platform.station.pojo.StationRole;
import java.util.List;

public interface IStationService {
  int getStationJsonListCount(Station paramStation);
  
  int getStaffStationCount(Station paramStation);
  
  int getStaffStationAmount(Station paramStation);
  
  List<Station> getStationJsonList(Station paramStation);
  
  Station getStationEntity(String paramString);
  
  int updateStationEntity(Station paramStation);
  
  List<Station> getStationUser(String paramString);
  
  List<Borg> getCompanyJsonList();
  
  int getStationUserCount(String paramString);
  
  List<StationRole> getStationRole(String paramString);
  
  List<CmsTbDict> getCustTypeList();
  
  int getStationRoleCount(String paramString);
  
  int deleteStationEntity(String paramString);
  
  String createStationEntity(Station paramStation);
  
  int searchStationUserCount(Station paramStation);
  
  List<Station> searchStationUser(Station paramStation);
  
  int deleteStationUser(Station paramStation);
  
  List<Station> searchUser(Station paramStation);
  
  int searchUserCount(Station paramStation);
  
  Long insertStationUser(Station paramStation);
  
  List<Station> getStationByEmpId(Long paramLong);
  
  int getStationJsonListCountForSelect(Station paramStation);
  
  List<Station> getStationJsonListForSelect(Station paramStation);
  
  BooleanResult updateStationUser(AllUsers paramAllUsers);
  
  String getStationCountByEmpId(AllUsers paramAllUsers);
  
  int searchStationUserMoreCount(Station paramStation);
  
  List<Station> searchStationUserMore(Station paramStation);
  
  int deleteStationUserById(Station paramStation);
  
  Long createStationOrg(StationOrg paramStationOrg);
  
  List<StationOrg> getStationOrgList(StationOrg paramStationOrg);
  
  int updateStationOrg(StationOrg paramStationOrg);
}
