package com.jingtong.platform.org.service.impl;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.org.dao.IOrgDao;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import com.jingtong.platform.org.service.IOrgService;
import java.util.List;
import org.apache.log4j.Logger;

public class OrgServiceImpl implements IOrgService {
  private Logger logger = Logger.getLogger(IOrgService.class);
  
  private IOrgDao orgDao;
  
  private String webserviceURL;
  
  public List<Borg> getOrgListByUserId(String userId) {
    try {
      return this.orgDao.getOrgListByUserId(userId);
    } catch (Exception e) {
      this.logger.error(userId, e);
      return null;
    } 
  }
  
  public List<Borg> getOrgTreeListByOrgId(String orgId) {
    try {
      return this.orgDao.getOrgTreeListByOrgId(orgId);
    } catch (Exception e) {
      this.logger.error(orgId, e);
      return null;
    } 
  }
  
  public Borg getOrgByOrgId(String orgId) {
    try {
      return this.orgDao.getOrgByOrgId(orgId);
    } catch (Exception e) {
      this.logger.error(orgId, e);
      return null;
    } 
  }
  
  public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
    try {
      return this.orgDao.getOrgTreeListByPorgId(pOrgId);
    } catch (Exception e) {
      this.logger.error(pOrgId, e);
      return null;
    } 
  }
  
  public List<SapTOrgUnit> getSapTOrgUnitListByPId(String PId) {
    try {
      return this.orgDao.getSapTOrgUnitListByPId(PId);
    } catch (Exception e) {
      this.logger.error(PId, e);
      return null;
    } 
  }
  
  public BooleanResult createOrg(Borg borg) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      Long orgId = this.orgDao.createOrg(borg);
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(orgId));
    } catch (Exception e) {
      booleanResult.setResult(false);
      this.logger.error(LogUtil.parserBean(borg), e);
    } 
    return booleanResult;
  }
  
  public BooleanResult NewOU(String orgName, String orgId, String parenOrgtId) {
    BooleanResult booleanResult = new BooleanResult();
    return booleanResult;
  }
  
  public BooleanResult NewGroup(String groupName, String sAMAccountName, String orgId) {
    BooleanResult booleanResult = new BooleanResult();
    return booleanResult;
  }
  
  public BooleanResult updateBorgWithADInfo(Borg borg) {
    BooleanResult booleanResult = new BooleanResult();
    Borg org = new Borg();
    org.setOrgId(borg.getOrgId());
    org.setsAMAccountName(borg.getsAMAccountName());
    org.setAdGroupName(borg.getAdGroupName());
    try {
      this.orgDao.updateBorg(org);
      booleanResult.setResult(true);
    } catch (Exception e) {
      booleanResult.setResult(false);
      this.logger.error("updateBorgWithADInfo", e);
    } 
    return booleanResult;
  }
  
  public BooleanResult updateBorg(Borg borg) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      this.orgDao.updateBorg(borg);
      booleanResult.setResult(true);
    } catch (Exception e) {
      booleanResult.setResult(false);
      this.logger.error("updateBorg", e);
    } 
    return booleanResult;
  }
  
  public BooleanResult dropBorg(Borg borg) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      this.orgDao.dropBorg(borg);
      booleanResult.setResult(true);
    } catch (Exception e) {
      booleanResult.setResult(false);
      this.logger.error("dropBorg", e);
    } 
    return booleanResult;
  }
  
  public BooleanResult deleteBorg(Borg borg) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      this.orgDao.deleteBorg(borg);
      booleanResult.setResult(true);
    } catch (Exception e) {
      booleanResult.setResult(false);
      this.logger.error("dropBorg", e);
    } 
    return booleanResult;
  }
  
  public BooleanResult reOUNameInAD(String orgId, String targetOrgId, String newOrgName) {
    BooleanResult booleanResult = new BooleanResult();
    return booleanResult;
  }
  
  public String getFnAllChildStrOrg(String orgId) {
    try {
      return this.orgDao.getFnAllChildStrOrg(orgId);
    } catch (Exception e) {
      this.logger.error(orgId, e);
      return null;
    } 
  }
  
  public int getCompanyListCount(Borg borg) {
    try {
      return this.orgDao.getCompanyListCount(borg);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(borg), e);
      return 0;
    } 
  }
  
  public List<Borg> getCompanyList(Borg borg) {
    try {
      return this.orgDao.getCompanyList(borg);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(borg), e);
      return null;
    } 
  }
  
  public Borg getOrgByUserId(String userId) {
    try {
      return this.orgDao.getOrgByUserId(userId);
    } catch (Exception e) {
      this.logger.error(userId, e);
      return null;
    } 
  }
  
  public List<Borg> getOrgTreeListByUserId(String userId) {
    try {
      return this.orgDao.getOrgTreeListByUserId(userId);
    } catch (Exception e) {
      this.logger.error(userId, e);
      return null;
    } 
  }
  
  public Borg getOrgByLoginId(String loginId) {
    try {
      return this.orgDao.getOrgByLoginId(loginId);
    } catch (Exception e) {
      this.logger.error(loginId, e);
      return null;
    } 
  }
  
  public Borg getFnUserOrg(String userId) {
    try {
      String orgId = this.orgDao.getFnUserOrg(userId).split(",")[0];
      return this.orgDao.getOrgByOrgId(orgId);
    } catch (Exception e) {
      this.logger.error(userId, e);
      return null;
    } 
  }
  
  public List<Borg> getAllParentOrgs(Long orgId) {
    try {
      return this.orgDao.getAllParentOrgs(orgId);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(orgId), e);
      return null;
    } 
  }
  
  public int getOrgCount(Borg borg) {
    try {
      return this.orgDao.getOrgCount(borg);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(borg), e);
      return 0;
    } 
  }
  
  public List<Borg> getOrgList(Borg borg) {
    try {
      return this.orgDao.getOrgList(borg);
    } catch (Exception e) {
      this.logger.error(LogUtil.parserBean(borg), e);
      return null;
    } 
  }
  
  public IOrgDao getOrgDao() {
    return this.orgDao;
  }
  
  public void setOrgDao(IOrgDao orgDao) {
    this.orgDao = orgDao;
  }
  
  public String getWebserviceURL() {
    return this.webserviceURL;
  }
  
  public void setWebserviceURL(String webserviceURL) {
    this.webserviceURL = webserviceURL;
  }
  
  public String getorgnamestr(Long org_id, Long id) {
    String oustr = "";
    if (!org_id.toString().equals(id.toString())) {
      oustr = String.valueOf(getorgnamestr(getporgid(org_id), id)) + "\\" + 
        getorgname(org_id);
    } else if (org_id.toString() != null && 
      !org_id.toString().equals("")) {
      oustr = getorgname(org_id);
    } else {
      return null;
    } 
    return oustr;
  }
  
  public String getorgname(Long org_id) {
    String orgname = "";
    try {
      orgname = this.orgDao.getorgname(org_id);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return orgname;
  }
  
  public Long getporgid(Long org_id) {
    Long orgId = Long.valueOf(1L);
    try {
      orgId = this.orgDao.getporgid(org_id);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return orgId;
  }
  
  public Long getorgid() {
    return this.orgDao.getorgid();
  }
}
