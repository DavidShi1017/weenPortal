package com.jingtong.platform.priceRule.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.priceRule.dao.IPriceRuleDao;
import com.jingtong.platform.priceRule.pojo.PriceRule;
import com.jingtong.platform.priceRule.service.IPriceRuleService;


public class PriceRuleServiceImpl implements IPriceRuleService{
	private IPriceRuleDao priceRuleDao;
	private TransactionTemplate transactionTemplate;
	@Override
	public int getPriceRuleListCount(PriceRule pr) {
		try {
			return priceRuleDao.getPriceRuleListCount(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PriceRule> getPriceRuleList(PriceRule pr) {
		try {
			return priceRuleDao.getPriceRuleList(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PriceRule getPriceRuleById(PriceRule pr) {
		try {
			return priceRuleDao.getPriceRuleById(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long createPriceRule(PriceRule pr) {
		try {
			return priceRuleDao.createPriceRule(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updatePriceRule(PriceRule pr) {
		try {
			return priceRuleDao.updatePriceRule(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int auditPriceRule(PriceRule pr) {
		try {
			return priceRuleDao.auditPriceRule(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public IPriceRuleDao getPriceRuleDao() {
		return priceRuleDao;
	}

	public void setPriceRuleDao(IPriceRuleDao priceRuleDao) {
		this.priceRuleDao = priceRuleDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public int deletePriceRule(PriceRule pr) {
		try {
			return priceRuleDao.deletePriceRule(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PriceRule> getControlPriceRuleList(PriceRule pr) {
		try {
			return priceRuleDao.getControlPriceRuleList(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getControlPriceRuleListCount(PriceRule pr) {
		try {
			return priceRuleDao.getControlPriceRuleListCount(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	
	}

	@Override
	public int getOrgListCount(Borg borg) {
		try {
			return priceRuleDao.getOrgListCount(borg);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Borg> getOrgList(Borg borg) {
		try {
			return priceRuleDao.getOrgList(borg);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getPriceRuleListCountCmRm(PriceRule pr){
		try {
			return priceRuleDao.getPriceRuleListCountCmRm(pr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PriceRule> getPriceRuleListCmRmQm(PriceRule priceRule) {
		try {
			return priceRuleDao.getPriceRuleListCmRmQm(priceRule);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}


