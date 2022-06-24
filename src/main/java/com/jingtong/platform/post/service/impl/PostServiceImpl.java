package com.jingtong.platform.post.service.impl;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.post.dao.IPostDao;
import com.jingtong.platform.post.pojo.EmpPost;
import com.jingtong.platform.post.service.IPostService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PostServiceImpl implements IPostService {
  private IPostDao postDao;
  
  private static final Log logger = LogFactory.getLog(PostServiceImpl.class);
  
  public BooleanResult createEmpPost(EmpPost empPost) {
    BooleanResult result = new BooleanResult();
    result.setResult(false);
    try {
      Long c = this.postDao.createEmpPost(empPost);
      if (c != null)
        result.setResult(true); 
    } catch (Exception e) {
      result.setCode("Error");
      logger.error(LogUtil.parserBean(empPost), e);
    } 
    return result;
  }
  
  public int getPostNameCount(EmpPost empPost) {
    try {
      return this.postDao.getPostNameCount(empPost);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(empPost), e);
      return 0;
    } 
  }
  
  public int getEmpPostCount(EmpPost empPost) {
    try {
      return this.postDao.getEmpPostCount(empPost);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(empPost), e);
      return 0;
    } 
  }
  
  public List<EmpPost> getEmpPostList(EmpPost empPost) {
    try {
      return this.postDao.getEmpPostList(empPost);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(empPost), e);
      return null;
    } 
  }
  
  public BooleanResult deleteEmpPostById(EmpPost empPost) {
    BooleanResult result = new BooleanResult();
    result.setResult(false);
    try {
      int c = this.postDao.deleteEmpPostById(empPost);
      if (c != 0) {
        result.setResult(true);
        result.setCode(String.valueOf(c));
      } 
    } catch (Exception e) {
      result.setCode("Error");
      logger.error(LogUtil.parserBean(empPost), e);
    } 
    return result;
  }
  
  public int getEmpPostCount(String orgId4Post) {
    try {
      return this.postDao.getEmpPostCount(orgId4Post);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(orgId4Post), e);
      return 0;
    } 
  }
  
  public List<EmpPost> getEmpPostList(String orgId4Post) {
    try {
      return this.postDao.getEmpPostList(orgId4Post);
    } catch (Exception e) {
      logger.error(LogUtil.parserBean(orgId4Post), e);
      return null;
    } 
  }
  
  public IPostDao getPostDao() {
    return this.postDao;
  }
  
  public void setPostDao(IPostDao postDao) {
    this.postDao = postDao;
  }
}
