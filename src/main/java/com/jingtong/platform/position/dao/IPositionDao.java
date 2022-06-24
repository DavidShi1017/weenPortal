package com.jingtong.platform.position.dao;

import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.position.pojo.Bposition;
import java.util.List;

public interface IPositionDao {
  int getPositionCount(Bposition paramBposition);
  
  List<Bposition> getPositionList(Bposition paramBposition);
  
  List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType paramCmsTbDictType);
  
  List<CmsTbDict> getCmsTbDictJoinTypeList(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictList(CmsTbDict paramCmsTbDict);
  
  List<Bposition> getStaffamountPositionList(Long paramLong);
  
  List<Bposition> getAllStaffamountPosition(Long paramLong);
  
  Long createPosition(Bposition paramBposition);
  
  int updatePosition(Bposition paramBposition);
  
  int getPostCustNum(Long paramLong);
  
  int getDeviceNum(Long paramLong);
  
  int updateCustPos(Long paramLong);
  
  Bposition getPositionObj(Bposition paramBposition);
  
  List<Bposition> getManagementList(Long paramLong);
  
  List<Bposition> getAssignedPosList(Long paramLong);
  
  List<Bposition> getUnassignedPosList(Long paramLong);
  
  List<Bposition> getStaffamountPositionPosList(Bposition paramBposition);
  
  List<Bposition> getPositionsByEmpId(Long paramLong);
  
  List<Bposition> getPosition(Bposition paramBposition);
  
  int updatePosition4User(Bposition paramBposition);
  
  int updatePosition4Position(Bposition paramBposition);
  
  Bposition getPositionByUseState(Bposition paramBposition);
  
  List<Bposition> getPositionListByUseState(Bposition paramBposition);
}
