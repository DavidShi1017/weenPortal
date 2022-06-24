package com.jingtong.platform.enquiry.dao;

import java.util.List;

import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;



public interface IEnquiryDao {
	/**
	 * 获取询价单列表数
	 * @param c
	 * @return
	 */
	public int getEnquiryListCount(Enquiry o);
	/**
	 * 获取询价单信息列表
	 * @param c
	 * @return
	 */
	public List<Enquiry> getEnquiryList(Enquiry o);
	/**
	 * 根据ID获取询价单信息
	 * @param c
	 * @return
	 */
	public Enquiry getEnquiryById(Enquiry o);
	/**
	 * 询价单信息新增
	 * @param p
	 * @return
	 */
	public long createEnquiry(Enquiry o);
	/**
	 * 修改询价单信息
	 * @param o
	 * @return
	 */
	public int updateEnquiry(Enquiry o);
	/**
	 * 删除询价单信息(逻辑删除)
	 * @param o
	 * @return
	 */
	public int deleteEnquiry(Enquiry o);
	
	
	

	/**
	 * 获取询价单明细信息列表
	 * @param od
	 * @return
	 */
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail od);

	/**
	 * 询价单明细信息新增
	 * @param od
	 * @return
	 */
	public long createEnquiryDetail(EnquiryDetail od);
	/**
	 * 修改询价单明细信息
	 * @param od
	 * @return
	 */
	public int updateEnquiryDetail(EnquiryDetail od);
	/**
	 * 删除询价单明细信息(物理删除)
	 * @param od
	 * @return
	 */
	public int deleteEnquiryDetail(EnquiryDetail od);

}
