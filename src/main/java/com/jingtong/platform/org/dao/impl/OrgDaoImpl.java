package com.jingtong.platform.org.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.org.dao.IOrgDao;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class OrgDaoImpl extends BaseDaoImpl implements IOrgDao {
  public List<Borg> getOrgListByUserId(String userId) {
    String orgIds = (String)getSqlMapClientTemplate().queryForObject(
        "org.fnUserOrgList", userId);
    String[] orgIdarrs = orgIds.split(",");
    Borg borg = new Borg();
    borg.setOrgIdarrs(orgIdarrs);
    if (StringUtils.isNotEmpty(orgIds))
      return getSqlMapClientTemplate().queryForList(
          "org.getOrgListByOrgIds", borg); 
    return null;
  }
  
  public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
    Borg org = new Borg();
    org.setOrgParentId(Long.valueOf(pOrgId));
    return getSqlMapClientTemplate().queryForList(
        "org.getOrgTreeListByOrgId", org);
  }
  
  public List<Borg> getOrgTreeListByOrgId(String OrgId) {
    Borg org = new Borg();
    org.setOrgId(Long.valueOf(OrgId));
    return getSqlMapClientTemplate().queryForList(
        "org.getOrgTreeListByOrgId", org);
  }
  
  public Borg getOrgByOrgId(String orgId) {
    Borg org = new Borg();
    org.setOrgId(Long.valueOf(orgId));
    return (Borg)getSqlMapClientTemplate().queryForObject(
        "org.getOrgByOrgId", org);
  }
  
  public List<Borg> getCompanyList(Borg borg) {
    return getSqlMapClientTemplate().queryForList(
        "org.getCompanyList", borg);
  }
  
  public Borg getCompanyName(Borg borg) {
    return (Borg)getSqlMapClientTemplate().queryForList(
        "org.getCompanyList", borg);
  }
  
  public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PID) {
    SapTOrgUnit sapTOrgUnit = new SapTOrgUnit();
    sapTOrgUnit.setParentId(Long.valueOf(PID));
    return getSqlMapClientTemplate().queryForList(
        "org.getSapTOrgUnitListByPId", sapTOrgUnit);
  }
  
  public Long createOrg(Borg org) {
    return (Long)getSqlMapClientTemplate().insert("org.createOrg", org);
  }
  
  public int updateBorg(Borg borg) {
    return getSqlMapClientTemplate().update("org.updateBorg", borg);
  }
  
  public int dropBorg(Borg borg) {
    return getSqlMapClientTemplate().update("org.dropBorg", borg);
  }
  
  public int deleteBorg(Borg borg) {
    return getSqlMapClientTemplate().update("org.deleteBorg", borg);
  }
  
  public int getCompanyListCount(Borg borg) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "org.getCompanyListCount", borg)).intValue();
  }
  
  public String getFnAllChildStrOrg(String orgId) {
    return (String)getSqlMapClientTemplate().queryForObject(
        "org.fnAllChildStrOrg", orgId);
  }
  
  public Borg getOrgByUserId(String userId) {
    return (Borg)getSqlMapClientTemplate().queryForObject(
        "org.getOrgByUserId", Long.valueOf(userId));
  }
  
  public List<Borg> getOrgTreeListByUserId(String userId) {
    return getSqlMapClientTemplate().queryForList(
        "org.getOrgTreeListByUserId", userId);
  }
  
  public Borg getOrgByLoginId(String loginId) {
    return (Borg)getSqlMapClientTemplate().queryForObject(
        "org.getOrgByLoginId", loginId);
  }
  
  public String getFnUserOrg(String userId) {
    return (String)getSqlMapClientTemplate().queryForObject(
        "org.getFnUserOrg", userId);
  }
  
  public List<Borg> getAllParentOrgs(Long orgId) {
    return getSqlMapClientTemplate().queryForList(
        "org.getAllParentOrgs", orgId);
  }
  
  public int getOrgCount(Borg borg) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "org.getOrgCount", borg)).intValue();
  }
  
  public List<Borg> getOrgList(Borg borg) {
    return getSqlMapClientTemplate().queryForList(
        "org.getOrgListByOrgIds", borg);
  }
  
  public String getorgname(Long org_id) {
    return (String)getSqlMapClientTemplate().queryForObject(
        "org.getorgname", org_id);
  }
  
  public Long getporgid(Long org_id) {
    return (Long)getSqlMapClientTemplate().queryForObject("org.getporgid", 
        org_id);
  }
  
  public Long getorgid() {
    return (Long)getSqlMapClientTemplate().queryForObject("org.getorgid");
  }
}
