package com.jingtong.platform.ediDisti.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.customer.dao.ICustomerDao;
import com.jingtong.platform.ediDisti.dao.IEdiDistiDao;
import com.jingtong.platform.ediDisti.pojo.EdiDisti;
import com.jingtong.platform.ediDisti.service.IEdiDistiService;

public class EdiDistiServiceImpl implements IEdiDistiService{
	
	private IEdiDistiDao ediDistiDao;
	private TransactionTemplate transactionTemplate;
	
	

	@Override
	public List<EdiDisti> getEdiDistiList(EdiDisti ed) {
		try {
			return ediDistiDao.getEdiDistiList(ed);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getEdiDistiListCount(EdiDisti ed) {
		try {
			return ediDistiDao.getEdiDistiListCount(ed);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long createEdiDisti(EdiDisti ed) {
		try {
			return ediDistiDao.createEdiDisti(ed);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long deleteEdiDisti(EdiDisti ed) {
		try {
			return ediDistiDao.deleteEdiDisti(ed);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public IEdiDistiDao getEdiDistiDao() {
		return ediDistiDao;
	}

	public void setEdiDistiDao(IEdiDistiDao ediDistiDao) {
		this.ediDistiDao = ediDistiDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	
}
