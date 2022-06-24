package com.jingtong.platform.allUser.service;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import java.util.List;

public interface IAllUserService {
  List<AllUsers> searchAllUsers(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpListByOrgId(String paramString);
  
  int searchAllUsersCount(AllUsers paramAllUsers);
  
  StringResult deleteUserByEmpId(AllUsers paramAllUsers);
  
  AllUsers getAllUserByPassport(String paramString);
  
  BooleanResult updateAllUser(AllUsers paramAllUsers, String paramString);
  
  String getAllUserByLoginId(String paramString);
  
  int getEmpCount(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpList(AllUsers paramAllUsers);
  
  List<AllUsers> getEmpList4Code(AllUsers paramAllUsers);
  
  BooleanResult createUser(AllUsers paramAllUsers);
  
  AllUsers getAllUsersByUserId(String paramString);
  
  String getEmpNameByUserId(String paramString);
  
  StringResult forbidden(AllUsers paramAllUsers);
  
  int updateAllUserOrg(AllUsers paramAllUsers);
  
  AllUsers getAllUser(String paramString);
  
  BooleanResult updateUserInfo(AllUsers paramAllUsers);
  
  StringResult updatePwd(AllUsers paramAllUsers);
  
  AllUsers getKunnrUsersByUserId(String paramString);
  
  StringResult updatePwdToKunnr(AllUsers paramAllUsers);
}
