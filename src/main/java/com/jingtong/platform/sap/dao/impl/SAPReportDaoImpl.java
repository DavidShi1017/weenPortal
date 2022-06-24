package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.SAPReportDao;
import com.jingtong.platform.sap.pojo.SAPReport;

 
public class SAPReportDaoImpl  extends BaseDaoImpl implements SAPReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SAPReport> getPgroupRules(String promoId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getPgroupRules", promoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SAPReport> getMvgs() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getMvgs");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SAPReport> getPromoDetails(String promoId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getPromoDetails", promoId);
	}

	@Override
	public SAPReport getPromotion(String promoId) {
		// TODO Auto-generated method stub
		return (SAPReport) this.getSqlMapClientTemplate().queryForObject("sapReport.getPromotion", promoId);
	}

	@Override
	public List<SAPReport> getpgRulesByPromoId(String promoId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getpgRulesByPromoId",promoId);
	}

	@Override
	public SAPReport getDisplayCost(String promoId) {
		// TODO Auto-generated method stub
		return   (SAPReport) this.getSqlMapClientTemplate().queryForObject("sapReport.getDisplayCost", promoId);
	}

	@Override
	public List<SAPReport> getKunnrByProm(String promoId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getKunnrByProm", promoId);
	}

}
