package com.jingtong.platform.position.service;

import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.position.pojo.Bposition;
import java.util.List;

public interface IPositionService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  public static final String ERROR_MESSAGE = "error";
  
  int getPositionCount(Bposition paramBposition);
  
  List<Bposition> getPositionList(Bposition paramBposition);
  
  List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType paramCmsTbDictType);
  
  List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictList(CmsTbDict paramCmsTbDict);
  
  List<Bposition> getStaffamountPositionList(Long paramLong);
  
  List<Bposition> getAllStaffamountPosition(Long paramLong);
  
  String createPosition(Bposition paramBposition);
  
  Boolean updatePosition(Bposition paramBposition);
  
  String deletePosition(Long paramLong);
  
  Bposition getPositionObj(Bposition paramBposition);
  
  String releasePosition(Bposition paramBposition);
  
  List<Bposition> getManagementList(Long paramLong);
  
  List<Bposition> getAssignedPosList(Long paramLong);
  
  List<Bposition> getUnassignedPosList(Long paramLong);
  
  List<Bposition> getStaffamountPositionPosList(Bposition paramBposition);
  
  List<Bposition> getPositionsByEmpId(Long paramLong);
  
  int getDeviceNum(Long paramLong);
  
  List<Bposition> getPosition(Bposition paramBposition);
  
  Boolean updatePosition4User(Bposition paramBposition);
  
  Boolean updatePosition4Position(Bposition paramBposition);
  
  Bposition getPositionByUseState(Bposition paramBposition);
  
  List<Bposition> getPositionListByUseState(Bposition paramBposition);
}
