package com.jingtong.platform.allUser.service.impl;

import com.jingtong.platform.allUser.dao.IAllUserDao;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AllUserServiceImpl implements IAllUserService {
  private static final Log logger = LogFactory.getLog(AllUserServiceImpl.class);
  
  private IAllUserDao allUserDao;
  
  public List<AllUsers> searchAllUsers(AllUsers alluser) {
    try {
      return this.allUserDao.searchAllUsers(alluser);
    } catch (Exception e) {
      logger.error(alluser, e);
      return null;
    } 
  }
  
  public List<AllUsers> getEmpListByOrgId(String orgId) {
    try {
      return this.allUserDao.getEmpListByOrgId(orgId);
    } catch (Exception e) {
      logger.error(e);
      return null;
    } 
  }
  
  public int searchAllUsersCount(AllUsers alluser) {
    try {
      return this.allUserDao.searchAllUsersCount(alluser);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(alluser), e);
      return 0;
    } 
  }
  
  public StringResult deleteUserByEmpId(AllUsers allUsers) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.allUserDao.deleteUserByEmpId(allUsers);
      result.setResult(String.valueOf(c));
      result.setCode("success");
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return result;
  }
  
  public AllUsers getAllUserByPassport(String passport) {
    if (StringUtils.isEmpty(passport))
      return null; 
    try {
      return this.allUserDao.getAllUserByPassport(passport);
    } catch (Exception e) {
      logger.error(passport, e);
      return null;
    } 
  }
  
  public BooleanResult updateAllUser(AllUsers allUsers, String flag) {
    BooleanResult result = new BooleanResult();
    result.setResult(false);
    try {
      int c = this.allUserDao.updateAllUser(allUsers, flag);
      if (c == 1)
        result.setResult(true); 
    } catch (Exception e) {
      result.setCode("error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return result;
  }
  
  public String getAllUserByLoginId(String loginId) {
    try {
      int n = this.allUserDao.getAllUserByLoginId(loginId);
      if (n >= 1)
        return "exist"; 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(loginId), e);
    } 
    return "unexist";
  }
  
  public int getEmpCount(AllUsers allUser) {
    try {
      return this.allUserDao.getEmpCount(allUser);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUser), e);
      return 0;
    } 
  }
  
  public List<AllUsers> getEmpList(AllUsers allUser) {
    try {
      return this.allUserDao.getEmpList(allUser);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUser), e);
      return null;
    } 
  }
  
  public List<AllUsers> getEmpList4Code(AllUsers allUser) {
    try {
      return this.allUserDao.getEmpList4Code(allUser);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUser), e);
      return null;
    } 
  }
  
  public BooleanResult createUser(AllUsers allUsers) {
    BooleanResult booleanResult = new BooleanResult();
    try {
      long empId = this.allUserDao.createUser(allUsers);
      booleanResult.setResult(true);
      booleanResult.setCode(String.valueOf(empId));
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return booleanResult;
  }
  
  public AllUsers getAllUsersByUserId(String ids) {
    try {
      return this.allUserDao.getAllUsersByUserId(ids);
    } catch (Exception e) {
      return null;
    } 
  }
  
  public IAllUserDao getAllUserDao() {
    return this.allUserDao;
  }
  
  public void setAllUserDao(IAllUserDao allUserDao) {
    this.allUserDao = allUserDao;
  }
  
  public String getEmpNameByUserId(String userId) {
    try {
      return this.allUserDao.getEmpNameByUserId(userId);
    } catch (Exception e) {
      return null;
    } 
  }
  
  public StringResult forbidden(AllUsers allUsers) {
    StringResult result = new StringResult();
    result.setCode("error");
    result.setResult("error");
    try {
      int c = this.allUserDao.forbidden(allUsers);
      if (c == 1)
        result.setCode("success"); 
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return result;
  }
  
  public int updateAllUserOrg(AllUsers allUsers) {
    try {
      return this.allUserDao.updateAllUserOrg(allUsers);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(allUsers), e);
      return 0;
    } 
  }
  
  public AllUsers getAllUser(String userId) {
    try {
      return this.allUserDao.getAllUser(userId);
    } catch (Exception e) {
      logger.error(userId, e);
      return null;
    } 
  }
  
  public BooleanResult updateUserInfo(AllUsers allUsers) {
    BooleanResult booleanResult = new BooleanResult();
    booleanResult.setResult(false);
    booleanResult.setCode("error");
    try {
      int n = this.allUserDao.updateUserInfo(allUsers);
      if (n == 1) {
        booleanResult.setResult(true);
        booleanResult.setCode("Success");
      } 
    } catch (Exception e) {
      booleanResult.setResult(false);
      booleanResult.setCode("Error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return booleanResult;
  }
  
  public StringResult updatePwd(AllUsers allUsers) {
    StringResult stringResult = new StringResult();
    try {
      long i = this.allUserDao.updatePwd(allUsers);
      stringResult.setResult(String.valueOf(i));
      stringResult.setCode("success");
    } catch (Exception e) {
      stringResult.setCode("error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return stringResult;
  }
  
  public AllUsers getKunnrUsersByUserId(String ids) {
    try {
      return this.allUserDao.getKunnrUsersByUserId(ids);
    } catch (Exception e) {
      return null;
    } 
  }
  
  public StringResult updatePwdToKunnr(AllUsers allUsers) {
    StringResult stringResult = new StringResult();
    try {
      long i = this.allUserDao.updatePwdToKunnr(allUsers);
      stringResult.setResult(String.valueOf(i));
      stringResult.setCode("success");
    } catch (Exception e) {
      stringResult.setCode("error");
      logger.error(LogUtil.parserBean(allUsers), e);
    } 
    return stringResult;
  }
}
