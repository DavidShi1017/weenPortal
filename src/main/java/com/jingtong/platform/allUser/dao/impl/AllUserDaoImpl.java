package com.jingtong.platform.allUser.dao.impl;

import com.jingtong.platform.allUser.dao.IAllUserDao;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import java.util.List;

public class AllUserDaoImpl extends BaseDaoImpl implements IAllUserDao {
  public List<AllUsers> searchAllUsers(AllUsers alluser) {
    return getSqlMapClientTemplate().queryForList("alluser.searchAllUsers", 
        alluser);
  }
  
  public int searchAllUsersCount(AllUsers alluser) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "alluser.searchAllUsersCount", alluser)).intValue();
  }
  
  public List<AllUsers> getEmpListByOrgId(String orgId) {
    Long org_id = Long.valueOf(orgId);
    return getSqlMapClientTemplate().queryForList(
        "alluser.getEmpListByOrgId", org_id);
  }
  
  public int deleteUserByEmpId(AllUsers allUsers) {
    return getSqlMapClientTemplate().delete("alluser.deleteUserByEmpId", 
        allUsers);
  }
  
  public AllUsers getAllUserByPassport(String passport) {
    return (AllUsers)getSqlMapClientTemplate().queryForObject(
        "alluser.getAllUserByPassport", passport);
  }
  
  public int updateAllUser(AllUsers allUsers, String flag) {
    if ("0".equals(flag))
      return getSqlMapClientTemplate().update("alluser.updateAllUser", 
          allUsers); 
    if ("1".equals(flag))
      return getSqlMapClientTemplate().update("alluser.updateFristUser", 
          allUsers); 
    return getSqlMapClientTemplate().update("alluser.updatePwd", 
        allUsers);
  }
  
  public int getAllUserByLoginId(String loginId) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "alluser.getAllUserByLoginId", loginId)).intValue();
  }
  
  public int getEmpCount(AllUsers allUsers) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "alluser.getEmpCount", allUsers)).intValue();
  }
  
  public List<AllUsers> getEmpList(AllUsers alluser) {
    return getSqlMapClientTemplate().queryForList("alluser.getEmpList", 
        alluser);
  }
  
  public List<AllUsers> getEmpList4Code(AllUsers alluser) {
    return getSqlMapClientTemplate().queryForList(
        "alluser.getEmpList4Code", alluser);
  }
  
  public long createUser(AllUsers allUsers) {
    return ((Long)getSqlMapClientTemplate().insert("alluser.createUser", 
        allUsers)).longValue();
  }
  
  public AllUsers getAllUsersByUserId(String ids) {
    return (AllUsers)getSqlMapClientTemplate().queryForObject(
        "alluser.getAllUsersByUserId", ids);
  }
  
  public String getEmpNameByUserId(String userId) {
    return (String)getSqlMapClientTemplate().queryForObject(
        "alluser.getEmpNameByUserId", userId);
  }
  
  public int forbidden(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("alluser.forbidden", allUsers);
  }
  
  public int updateAllUserOrg(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("alluser.updateAllUserOrg", 
        allUsers);
  }
  
  public AllUsers getAllUser(String userId) {
    return (AllUsers)getSqlMapClientTemplate().queryForObject(
        "alluser.getAllUser", userId);
  }
  
  public int updateUserInfo(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("alluser.updateUserInfo", 
        allUsers);
  }
  
  public long updatePwd(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("alluser.updatePwd", allUsers);
  }
  
  public AllUsers getKunnrUsersByUserId(String ids) {
    return (AllUsers)getSqlMapClientTemplate().queryForObject(
        "alluser.getKunnrUsersByUserId", ids);
  }
  
  public long updatePwdToKunnr(AllUsers allUsers) {
    return getSqlMapClientTemplate().update("alluser.updatePwdToKunnr", allUsers);
  }
}
