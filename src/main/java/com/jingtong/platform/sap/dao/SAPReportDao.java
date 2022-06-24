package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.sap.pojo.SAPReport;

public interface SAPReportDao {
	
	public List<SAPReport> getPgroupRules(String promoId);

	public List<SAPReport> getMvgs();
	
	public List<SAPReport> getPromoDetails(String promoId);
	
	public SAPReport getPromotion(String promoId);
	
	public List<SAPReport> getpgRulesByPromoId(String promoId);
	
	public SAPReport getDisplayCost(String promoId);
	
	public List<SAPReport> getKunnrByProm(String promoId);
}
