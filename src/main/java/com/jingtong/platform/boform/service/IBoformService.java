package com.jingtong.platform.boform.service;

import java.util.List;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.boform.pojo.QueryParameter;
import com.jingtong.platform.boform.pojo.ReportParameter;


/**
 * bo報表中心接口<br>
 * 包括報表參數配置 報表中心免登
 * 
 * @author xujiakun
 * 
 */
public interface IBoformService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getReportParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<ReportParameter> getReportParameterList(
			ReportParameter reportParameter);

	/**
	 * 批量创建
	 * 
	 * @param reportParameterList
	 * @return
	 */
	public StringResult createBatchReportParameter(
			List<ReportParameter> reportParameterList);

	/**
	 * 根据主键查询
	 * 
	 * @param pid
	 * @return
	 */
	public ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 
	 * 修改
	 * 
	 * @param reportParameter
	 * @return
	 */
	public StringResult updateReportParameter(ReportParameter reportParameter);

	/**
	 * 删除
	 * 
	 * @param reportParameter
	 * @return
	 */
	public StringResult deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 根据报表id查询报表参数
	 * 
	 * @param bid
	 * @return
	 */
	public List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 查询smsuser.b_saporg_smsorg
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
	 * 查询smsuser.b_saporg_smsorg 递归查询所有子组织
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryAllChildOrgList(
			ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryParameterCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryParameterList(
			ReportParameter reportParameter);

}
