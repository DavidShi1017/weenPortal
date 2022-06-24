package com.jingtong.platform.position.service;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.position.pojo.BpositionType;
import java.util.List;

public interface IPositionTypeService {
  public static final String SUCCESS = "success";
  
  public static final String ERROR = "error";
  
  int getPositionTypesCount(BpositionType paramBpositionType);
  
  List<BpositionType> getPositionTypesList(BpositionType paramBpositionType);
  
  List<BpositionType> exportPositionTypesList(BpositionType paramBpositionType);
  
  BpositionType getPositionTypes(BpositionType paramBpositionType);
  
  int updatePositionTypes(BpositionType paramBpositionType);
  
  Long createPositionTypes(BpositionType paramBpositionType);
  
  int getPositionType4RoleCount(BpositionType paramBpositionType);
  
  List<BpositionType> getPositionType4RoleList(BpositionType paramBpositionType);
  
  StringResult insertPos(List<BpositionType> paramList);
}
