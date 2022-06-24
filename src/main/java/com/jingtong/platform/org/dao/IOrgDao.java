package com.jingtong.platform.org.dao;

import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import java.util.List;

public interface IOrgDao {
  List<Borg> getOrgListByUserId(String paramString);
  
  List<Borg> getOrgTreeListByPorgId(String paramString);
  
  List<Borg> getOrgTreeListByOrgId(String paramString);
  
  Borg getOrgByOrgId(String paramString);
  
  int getCompanyListCount(Borg paramBorg);
  
  List<Borg> getCompanyList(Borg paramBorg);
  
  Borg getCompanyName(Borg paramBorg);
  
  List<SapTOrgUnit> getSapTOrgUnitListByPId(String paramString);
  
  Long createOrg(Borg paramBorg);
  
  int updateBorg(Borg paramBorg);
  
  int dropBorg(Borg paramBorg);
  
  int deleteBorg(Borg paramBorg);
  
  @Deprecated
  String getFnAllChildStrOrg(String paramString);
  
  Borg getOrgByUserId(String paramString);
  
  Borg getOrgByLoginId(String paramString);
  
  String getFnUserOrg(String paramString);
  
  List<Borg> getAllParentOrgs(Long paramLong);
  
  int getOrgCount(Borg paramBorg);
  
  List<Borg> getOrgList(Borg paramBorg);
  
  String getorgname(Long paramLong);
  
  Long getporgid(Long paramLong);
  
  Long getorgid();
  
  List<Borg> getOrgTreeListByUserId(String paramString);
}
