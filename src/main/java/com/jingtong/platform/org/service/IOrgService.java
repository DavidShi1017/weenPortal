package com.jingtong.platform.org.service;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import java.util.List;

public interface IOrgService {
  List<Borg> getOrgListByUserId(String paramString);
  
  List<Borg> getOrgTreeListByOrgId(String paramString);
  
  List<Borg> getOrgTreeListByPorgId(String paramString);
  
  Borg getOrgByOrgId(String paramString);
  
  int getCompanyListCount(Borg paramBorg);
  
  List<Borg> getCompanyList(Borg paramBorg);
  
  List<SapTOrgUnit> getSapTOrgUnitListByPId(String paramString);
  
  BooleanResult createOrg(Borg paramBorg);
  
  BooleanResult NewOU(String paramString1, String paramString2, String paramString3);
  
  BooleanResult NewGroup(String paramString1, String paramString2, String paramString3);
  
  BooleanResult updateBorgWithADInfo(Borg paramBorg);
  
  BooleanResult updateBorg(Borg paramBorg);
  
  BooleanResult dropBorg(Borg paramBorg);
  
  BooleanResult deleteBorg(Borg paramBorg);
  
  BooleanResult reOUNameInAD(String paramString1, String paramString2, String paramString3);
  
  @Deprecated
  String getFnAllChildStrOrg(String paramString);
  
  Borg getOrgByUserId(String paramString);
  
  Borg getOrgByLoginId(String paramString);
  
  Borg getFnUserOrg(String paramString);
  
  List<Borg> getAllParentOrgs(Long paramLong);
  
  int getOrgCount(Borg paramBorg);
  
  List<Borg> getOrgList(Borg paramBorg);
  
  String getorgnamestr(Long paramLong1, Long paramLong2);
  
  String getorgname(Long paramLong);
  
  Long getporgid(Long paramLong);
  
  Long getorgid();
  
  List<Borg> getOrgTreeListByUserId(String paramString);
}
