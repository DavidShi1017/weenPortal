package com.jingtong.platform.conpoint.dao;

import com.jingtong.platform.conpoint.pojo.Conpoint;
import java.math.BigDecimal;
import java.util.List;

public interface IConpointDao {
  int getConpointCount(Conpoint paramConpoint);
  
  List<Conpoint> getConpointList(Conpoint paramConpoint);
  
  List<Conpoint> getConpointListIsExit(Conpoint paramConpoint);
  
  List<Conpoint> getConpointListJson(Conpoint paramConpoint);
  
  int deleteConpoint(BigDecimal paramBigDecimal);
  
  int deleteConpoints(Conpoint paramConpoint);
  
  Conpoint getConpointMenuPojo(BigDecimal paramBigDecimal);
  
  int modifyConpoint(Conpoint paramConpoint);
  
  Object createConpoint(Conpoint paramConpoint);
  
  @Deprecated
  Conpoint isAut(Conpoint paramConpoint);
  
  int getRolesByConpointId(Conpoint paramConpoint);
  
  List<Conpoint> getPermissions(Conpoint paramConpoint);
  
  int getRoleConpointCount(Conpoint paramConpoint);
  
  List<Conpoint> getRoleConpointList(Conpoint paramConpoint);
  
  Long createRoleConpoint(Conpoint paramConpoint);
  
  int updateRoleConpoint(Conpoint paramConpoint);
  
  Conpoint getRoleConpointById(Long paramLong);
}
