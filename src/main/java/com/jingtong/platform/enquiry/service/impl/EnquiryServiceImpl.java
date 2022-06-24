package com.jingtong.platform.enquiry.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.enquiry.dao.IEnquiryDao;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;
import com.jingtong.platform.enquiry.service.IEnquiryService;

public class EnquiryServiceImpl implements IEnquiryService{
	private IEnquiryDao  enquiryDao;
	private TransactionTemplate transactionTemplate;
	@Override
	public int getEnquiryListCount(Enquiry e) {
		try {
			return enquiryDao.getEnquiryListCount(e);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Enquiry> getEnquiryList(Enquiry e) {
		try {
			return enquiryDao.getEnquiryList(e);
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public Enquiry getEnquiryById(Enquiry e) {
		try {
			return enquiryDao.getEnquiryById(e);
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public BooleanResult createEnquiry(final Enquiry e, final List<EnquiryDetail> edList) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				long n;
				long m;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);					 
					n=enquiryDao.createEnquiry(e);
					if (n <= 0) {
							booleanResult.setResult(false);
							booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
							ts.setRollbackOnly();
							return booleanResult;
					}else{
						for(EnquiryDetail ed:edList){
							ed.setEnquiry_id(e.getEnquiry_id());	
							ed.setMain_id(n);
							m=enquiryDao.createEnquiryDetail(ed);
							if (m <= 0) {
								booleanResult.setResult(false);
								booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
								ts.setRollbackOnly();
								return booleanResult;
							}
						}
						booleanResult.setResult(true);
 					}
 					 
				} catch (Exception e1) {
					e1.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	@Override
	public BooleanResult updateEnquiry(final Enquiry e, final List<EnquiryDetail> edList,
			final EnquiryDetail ed) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				long n;
				long m;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);					 
					n=enquiryDao.updateEnquiry(e);
					if (n <= 0) {
							booleanResult.setResult(false);
							booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
							ts.setRollbackOnly();
							return booleanResult;
					}else{
						enquiryDao.deleteEnquiryDetail(ed);
						for(EnquiryDetail ed:edList){
							ed.setEnquiry_id(e.getEnquiry_id());	
							if (ed.getId()==0) {
								ed.setMain_id(e.getId());
								m=enquiryDao.createEnquiryDetail(ed);
							}else {															
								m=enquiryDao.updateEnquiryDetail(ed);
							}
							if (m <= 0) {
								booleanResult.setResult(false);
								booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
								ts.setRollbackOnly();
								return booleanResult;
							}
						}
						booleanResult.setResult(true);
 					}
 					 
				} catch (Exception e1) {
					e1.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("²Ù×÷Ê§°Ü£¡");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	@Override
	public int deleteEnquiry(Enquiry e) {
		try {
			return enquiryDao.deleteEnquiry(e);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail ed) {
		try {
			return enquiryDao.getEnquiryDetailList(ed);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long createEnquiryDetail(EnquiryDetail ed) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEnquiryDetail(EnquiryDetail ed) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEnquiryDetail(EnquiryDetail ed) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IEnquiryDao getEnquiryDao() {
		return enquiryDao;
	}

	public void setEnquiryDao(IEnquiryDao enquiryDao) {
		this.enquiryDao = enquiryDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}
