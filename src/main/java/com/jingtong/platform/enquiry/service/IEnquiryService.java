package com.jingtong.platform.enquiry.service;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;

public interface IEnquiryService {
	/**
	 * 获取订单列表数
	 * @param o
	 * @return
	 */
	public int getEnquiryListCount(Enquiry o);
	/**
	 * 获取订单信息列表
	 * @param o
	 * @return
	 */
	public List<Enquiry> getEnquiryList(Enquiry o);
	/**
	 * 根据ID获取订单信息
	 * @param o
	 * @return
	 */
	public Enquiry getEnquiryById(Enquiry o);
	/**
	 * 订单信息新增
	 * @param o
	 * @return
	 */
	public BooleanResult createEnquiry(Enquiry o,List<EnquiryDetail> odList);
	/**
	 * 修改订单信息
	 * @param o
	 * @return
	 */
	public BooleanResult updateEnquiry(Enquiry o,List<EnquiryDetail> odList,EnquiryDetail od);
	/**
	 * 删除订单信息(逻辑删除)
	 * @param o
	 * @return
	 */
	public int deleteEnquiry(Enquiry o);
	
	
	

	/**
	 * 获取订单明细信息列表
	 * @param od
	 * @return
	 */
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail od);

	/**
	 * 订单明细信息新增
	 * @param od
	 * @return
	 */
	public long createEnquiryDetail(EnquiryDetail od);
	/**
	 * 修改订单明细信息
	 * @param od
	 * @return
	 */
	public int updateEnquiryDetail(EnquiryDetail od);
	/**
	 * 删除订单明细信息(物理删除)
	 * @param od
	 * @return
	 */
	public int deleteEnquiryDetail(EnquiryDetail od);
}
