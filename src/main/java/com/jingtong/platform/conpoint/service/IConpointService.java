package com.jingtong.platform.conpoint.service;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.conpoint.pojo.Conpoint;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IConpointService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  public static final String ERROR_MESSAGE = "error";
  
  public static final String ERROR_INPUT_MESSAGE = "error";
  
  int getConpointCount(Conpoint paramConpoint);
  
  List<Conpoint> getConpointList(Conpoint paramConpoint);
  
  List<Conpoint> getConpointListIsExit(Conpoint paramConpoint);
  
  List<Conpoint> getConpointListJson(Conpoint paramConpoint);
  
  int deleteConpoint(BigDecimal paramBigDecimal);
  
  StringResult deleteConpoints(Conpoint paramConpoint);
  
  Conpoint getConpointMenuPojo(BigDecimal paramBigDecimal);
  
  int modifyConpoint(Conpoint paramConpoint);
  
  Object createConpoint(Conpoint paramConpoint);
  
  int getRolesByConpointId(Conpoint paramConpoint);
  
  @Deprecated
  String isAut(Conpoint paramConpoint);
  
  Map<BigDecimal, String> getPermissions(String paramString, String[] paramArrayOfString);
  
  int getRoleConpointCount(Conpoint paramConpoint);
  
  List<Conpoint> getRoleConpointList(Conpoint paramConpoint);
  
  StringResult createRoleConpoint(Conpoint paramConpoint);
  
  StringResult updateRoleConpoint(Conpoint paramConpoint);
  
  Conpoint getRoleConpointById(Long paramLong);
  
  StringResult deleteRoleConpoint(Conpoint paramConpoint);
}
