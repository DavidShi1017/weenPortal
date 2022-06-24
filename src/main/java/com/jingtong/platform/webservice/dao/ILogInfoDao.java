package com.jingtong.platform.webservice.dao;

import java.util.List;

import com.jingtong.platform.webservice.pojo.ExceptionLog;

/**
 * 日志处理类
 * @author XIA_QX
 *
 */
public interface ILogInfoDao {
	/**
	 * 新增日志信息
	 * @param elog
	 * @throws Exception
	 */
   public void addLogInfo(ExceptionLog elog)throws Exception;
   
   /**
    * 查询日志信息
    * @param elog
    * @return
    * @throws Exception
    */
   public List<ExceptionLog> searchLogList(ExceptionLog elog)throws Exception;
   
   /**
  	 * 查询日志记录
  	 * @param elog
  	 * @return
  	 * @throws Exception
  	 */
   public Integer searchLogListCount(ExceptionLog elog)throws Exception;
}
