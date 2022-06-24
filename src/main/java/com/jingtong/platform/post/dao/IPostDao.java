package com.jingtong.platform.post.dao;

import com.jingtong.platform.post.pojo.EmpPost;
import java.util.List;

public interface IPostDao {
  Long createEmpPost(EmpPost paramEmpPost);
  
  int getPostNameCount(EmpPost paramEmpPost);
  
  int getEmpPostCount(EmpPost paramEmpPost);
  
  List<EmpPost> getEmpPostList(EmpPost paramEmpPost);
  
  int deleteEmpPostById(EmpPost paramEmpPost);
  
  int getEmpPostCount(String paramString);
  
  List<EmpPost> getEmpPostList(String paramString);
}
