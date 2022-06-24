package com.jingtong.platform.priceRule.dao.impl;



import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.priceRule.dao.IPriceRuleDao;
import com.jingtong.platform.priceRule.pojo.PriceRule;

public class PriceRuleDaoImpl  extends BaseDaoImpl implements IPriceRuleDao {
	@Override
	public int getPriceRuleListCount(PriceRule pr){
		return (Integer) getSqlMapClientTemplate().queryForObject("priceRule.getPriceRuleListCount",pr);
	}

	@Override
	public List<PriceRule> getPriceRuleList(PriceRule pr) {
		return (List<PriceRule>) getSqlMapClientTemplate().queryForList("priceRule.getPriceRuleList",pr);
	}

	@Override
	public PriceRule getPriceRuleById(PriceRule pr) {
		return (PriceRule) getSqlMapClientTemplate().queryForObject("priceRule.getPriceRuleById",pr);
	}

	@Override
	public long createPriceRule(PriceRule pr) {
		return  (Long) getSqlMapClientTemplate().insert("priceRule.createPriceRule",pr);
	}

	@Override
	public int updatePriceRule(PriceRule pr) {
		return (int) getSqlMapClientTemplate().update("priceRule.updatePriceRule",pr);
	}

	@Override
	public int deletePriceRule(PriceRule pr) {
		return (int) getSqlMapClientTemplate().delete("priceRule.deletePriceRule",pr);
	}

	@Override
	public int auditPriceRule(PriceRule pr) {
		return (int) getSqlMapClientTemplate().update("priceRule.auditPriceRule",pr);
	}
	
	@Override
	public int getControlPriceRuleListCount(PriceRule pr){
		return (Integer) getSqlMapClientTemplate().queryForObject("priceRule.getControlPriceRuleListCount",pr);
	}
	
	@Override
	public List<PriceRule> getControlPriceRuleList(PriceRule pr) {
		return (List<PriceRule>) getSqlMapClientTemplate().queryForList("priceRule.getControlPriceRuleList",pr);
	}

	@Override
	public int getOrgListCount(Borg borg) {
		return (Integer)getSqlMapClientTemplate().queryForObject("priceRule.getOrgListCount",borg);
	}

	@Override
	public List<Borg> getOrgList(Borg borg) {
		return (List<Borg>) getSqlMapClientTemplate().queryForList("priceRule.getOrgList",borg);
	}
	
	@Override
	public int getPriceRuleListCountCmRm(PriceRule pr){
		return (Integer) getSqlMapClientTemplate().queryForObject("priceRule.getPriceRuleListCountCmRm",pr);
	}

	@Override
	public List<PriceRule> getPriceRuleListCmRmQm(PriceRule priceRule) {
		return (List<PriceRule>) getSqlMapClientTemplate().queryForList("priceRule.getPriceRuleListCmRmQm",priceRule);
	}
}
