package com.jingtong.platform.boform.dao;

import java.util.List;

import com.jingtong.platform.boform.pojo.QueryParameter;
import com.jingtong.platform.boform.pojo.ReportParameter;


public interface IBoformDao {

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
	 * 
	 * @param reportParameterList
	 * @return
	 */
	public String createBatchReportParameter(
			List<ReportParameter> reportParameterList);

	/**
	 * 
	 * @param pid
	 * @return
	 */
	public ReportParameter getReportParameterByPid(Long pid);

	/**
	 * 
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int updateReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int deleteReportParameter(ReportParameter reportParameter);

	/**
	 * 
	 * @param bid
	 * @return
	 */
	public List<ReportParameter> getReportParametersByBid(Long bid);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryOrgCount(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public List<QueryParameter> getQueryOrgList(ReportParameter reportParameter);

	/**
	 * 
	 * @param reportParameter
	 * @return
	 */
	public int getQueryAllChildOrgCount(ReportParameter reportParameter);

	/**
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
