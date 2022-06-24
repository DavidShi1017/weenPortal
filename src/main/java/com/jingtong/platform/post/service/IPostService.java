package com.jingtong.platform.post.service;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.post.pojo.EmpPost;
import java.util.List;

public interface IPostService {
  BooleanResult createEmpPost(EmpPost paramEmpPost);
  
  int getPostNameCount(EmpPost paramEmpPost);
  
  int getEmpPostCount(EmpPost paramEmpPost);
  
  List<EmpPost> getEmpPostList(EmpPost paramEmpPost);
  
  BooleanResult deleteEmpPostById(EmpPost paramEmpPost);
  
  int getEmpPostCount(String paramString);
  
  List<EmpPost> getEmpPostList(String paramString);
}
