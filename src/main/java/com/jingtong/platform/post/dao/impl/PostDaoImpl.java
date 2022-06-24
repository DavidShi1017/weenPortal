package com.jingtong.platform.post.dao.impl;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.post.dao.IPostDao;
import com.jingtong.platform.post.pojo.EmpPost;
import java.util.List;

public class PostDaoImpl extends BaseDaoImpl implements IPostDao {
  public Long createEmpPost(EmpPost empPost) {
    return (Long)getSqlMapClientTemplate().insert(
        "post.createEmpPost", empPost);
  }
  
  public int getPostNameCount(EmpPost empPost) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "post.getPostNameCount", empPost)).intValue();
  }
  
  public int getEmpPostCount(EmpPost empPost) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "post.getEmpPostCount", empPost)).intValue();
  }
  
  public List<EmpPost> getEmpPostList(EmpPost empPost) {
    return getSqlMapClientTemplate().queryForList(
        "post.getEmpPostList", empPost);
  }
  
  public int deleteEmpPostById(EmpPost empPost) {
    return getSqlMapClientTemplate().update(
        "post.deleteEmpPostById", empPost);
  }
  
  public int getEmpPostCount(String orgId4Post) {
    return ((Integer)getSqlMapClientTemplate().queryForObject(
        "post.getEmpPostCount4User", orgId4Post)).intValue();
  }
  
  public List<EmpPost> getEmpPostList(String orgId4Post) {
    return getSqlMapClientTemplate().queryForList(
        "post.getEmpPostList4User", orgId4Post);
  }
}
