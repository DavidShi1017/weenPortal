package com.jingtong.platform.dict.service;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import java.util.List;

public interface IDictService {
  int getCmsTbDictCount(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictList(CmsTbDict paramCmsTbDict);
  
  int getDictCount(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getDictList(CmsTbDict paramCmsTbDict);
  
  int getCmsTbDictTypeCount(CmsTbDictType paramCmsTbDictType);
  
  List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType paramCmsTbDictType);
  
  BooleanResult createDictType(CmsTbDictType paramCmsTbDictType);
  
  BooleanResult createDict(CmsTbDict paramCmsTbDict);
  
  BooleanResult updateDict(CmsTbDict paramCmsTbDict);
  
  BooleanResult updateDictType(CmsTbDictType paramCmsTbDictType);
  
  CmsTbDictType getCmsTbDictType(CmsTbDictType paramCmsTbDictType);
  
  CmsTbDict getCmsTbDict(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictByType(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getByCmsTbDictList(CmsTbDict paramCmsTbDict);
}
