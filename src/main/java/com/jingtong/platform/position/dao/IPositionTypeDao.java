package com.jingtong.platform.position.dao;

import com.jingtong.platform.position.pojo.BpositionType;
import java.util.List;

public interface IPositionTypeDao {
  int getPositionTypesCount(BpositionType paramBpositionType);
  
  List<BpositionType> getPositionTypesList(BpositionType paramBpositionType);
  
  List<BpositionType> exportPositionTypesList(BpositionType paramBpositionType);
  
  BpositionType getPositionTypes(BpositionType paramBpositionType);
  
  int updatePositionTypes(BpositionType paramBpositionType);
  
  Long createPositionTypes(BpositionType paramBpositionType);
  
  int getPositionType4RoleCount(BpositionType paramBpositionType);
  
  List<BpositionType> getPositionType4RoleList(BpositionType paramBpositionType);
  
  boolean insertPos(BpositionType paramBpositionType);
}
