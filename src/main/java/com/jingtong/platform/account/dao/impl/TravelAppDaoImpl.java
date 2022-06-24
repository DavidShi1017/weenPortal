package com.jingtong.platform.account.dao.impl;

import java.util.List;

import com.jingtong.platform.account.dao.ITravelAppDao;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelDetail;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;

@SuppressWarnings("rawtypes")
public class TravelAppDaoImpl extends BaseDaoImpl implements ITravelAppDao{

	
	public Long createTravelDetail(TravelDetail travelDetail) {
		return (Long) getSqlMapClientTemplate().insert("travelApp.createTravelDetail", travelDetail);
	}

	
	public Long createTravelTotal(TravelTotal travelTotal) {
		return (Long) getSqlMapClientTemplate().insert("travelApp.createTravelTotal", travelTotal);
	}

	
	public int searchTravelCount(TravelTotal travelTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"travelApp.searchTravelListCount", travelTotal);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TravelTotal> searchTravel(TravelTotal travelTotal){
		return getSqlMapClientTemplate().queryForList("travelApp.searchTravelList", travelTotal);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TravelDetail> searchTravelDetail(TravelTotal travelTotal) {
		return getSqlMapClientTemplate().queryForList("travelApp.searchTravelDetail", travelTotal);
	}
	
	
	public void updateTravelTotal(TravelTotal travelTotal) {
		getSqlMapClientTemplate().update("travelApp.updateTravelTotal", travelTotal);
	}
	
	
	public void updateTravelDetail(TravelDetail travelDetail) {
		getSqlMapClientTemplate().update("travelApp.updateTravelDetail", travelDetail);
	}

	
	@SuppressWarnings("unchecked")
	public List<BudgetNumber> getBudgetNumberList(BudgetNumber budgetNumber){
		return getSqlMapClientTemplate().queryForList("travelApp.getBudgetNumberList", budgetNumber);
	}
}

	
