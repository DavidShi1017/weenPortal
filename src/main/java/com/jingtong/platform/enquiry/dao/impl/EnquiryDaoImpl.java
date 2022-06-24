package com.jingtong.platform.enquiry.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.enquiry.dao.IEnquiryDao;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;


public class EnquiryDaoImpl extends BaseDaoImpl implements IEnquiryDao{

	@Override
	public int getEnquiryListCount(Enquiry o) {
		return (Integer) getSqlMapClientTemplate().queryForObject("enquiry.getEnquiryListCount",o);
	}

	@Override
	public List<Enquiry> getEnquiryList(Enquiry o) {
		return (List<Enquiry>) getSqlMapClientTemplate().queryForList("enquiry.getEnquiryList",o);
	}

	@Override
	public Enquiry getEnquiryById(Enquiry o) {
		return (Enquiry) getSqlMapClientTemplate().queryForObject("enquiry.getEnquiryById",o);
	}

	@Override
	public long createEnquiry(Enquiry o) {
		return (Long) getSqlMapClientTemplate().insert("enquiry.createEnquiry",o);
	}

	@Override
	public int updateEnquiry(Enquiry o) {
		return (Integer) getSqlMapClientTemplate().update("enquiry.updateEnquiry",o);
	}

	@Override
	public int deleteEnquiry(Enquiry o) {
		return (Integer) getSqlMapClientTemplate().delete("enquiry.deleteEnquiry",o);
	}

	@Override
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail od) {
		return (List<EnquiryDetail>) getSqlMapClientTemplate().queryForList("enquiry.getEnquiryDetailList",od);
	}

	@Override
	public long createEnquiryDetail(EnquiryDetail od) {
		return (Long) getSqlMapClientTemplate().insert("enquiry.createEnquiryDetail",od);
	}

	@Override
	public int updateEnquiryDetail(EnquiryDetail od) {
		return (Integer) getSqlMapClientTemplate().update("enquiry.updateEnquiryDetail",od);
	}

	@Override
	public int deleteEnquiryDetail(EnquiryDetail od) {
		return (Integer) getSqlMapClientTemplate().delete("enquiry.deleteEnquiryDetail",od);
	}
	
}
