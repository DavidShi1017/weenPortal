package com.jingtong.platform.allUser.dao;

import com.jingtong.platform.allUser.pojo.AllUsers;
import java.util.List;

public interface IAllUserDao {
  List<AllUsers> searchAllUsers(AllUsers paramAllUsers);
  
  int searchAllUsersCount(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpListByOrgId(String paramString);
  
  int deleteUserByEmpId(AllUsers paramAllUsers);
  
  AllUsers getAllUserByPassport(String paramString);
  
  int updateAllUser(AllUsers paramAllUsers, String paramString);
  
  int getAllUserByLoginId(String paramString);
  
  int getEmpCount(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpList(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpList4Code(AllUsers paramAllUsers);
  
  long createUser(AllUsers paramAllUsers);
  
  AllUsers getAllUsersByUserId(String paramString);
  
  String getEmpNameByUserId(String paramString);
  
  int forbidden(AllUsers paramAllUsers);
  
  int updateAllUserOrg(AllUsers paramAllUsers);
  
  AllUsers getAllUser(String paramString);
  
  int updateUserInfo(AllUsers paramAllUsers);
  
  long updatePwd(AllUsers paramAllUsers);
  
  AllUsers getKunnrUsersByUserId(String paramString);
  
  long updatePwdToKunnr(AllUsers paramAllUsers);
}
