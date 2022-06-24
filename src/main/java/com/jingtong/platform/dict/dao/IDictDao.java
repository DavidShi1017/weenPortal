package com.jingtong.platform.dict.dao;

import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import java.util.List;

public interface IDictDao {
  int getCmsTbDictCount(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictList(CmsTbDict paramCmsTbDict);
  
  int getDictCount(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getDictList(CmsTbDict paramCmsTbDict);
  
  int getCmsTbDictTypeCount(CmsTbDictType paramCmsTbDictType);
  
  List<CmsTbDictType> getCmsTbDictTypeList(CmsTbDictType paramCmsTbDictType);
  
  Long CreateDictType(CmsTbDictType paramCmsTbDictType);
  
  Long CreateDict(CmsTbDict paramCmsTbDict);
  
  int updateDict(CmsTbDict paramCmsTbDict);
  
  int updateDictType(CmsTbDictType paramCmsTbDictType);
  
  CmsTbDictType getCmsTbDictType(CmsTbDictType paramCmsTbDictType);
  
  CmsTbDict getCmsTbDict(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getCmsTbDictByType(CmsTbDict paramCmsTbDict);
  
  List<CmsTbDict> getByCmsTbDictList(CmsTbDict paramCmsTbDict);
}
