package com.jingtong.platform.account.dao;

import java.util.List;

import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelDetail;
import com.jingtong.platform.account.pojo.TravelTotal;

public interface ITravelAppDao {

	public Long createTravelDetail(TravelDetail travelDetail);
	
	public Long createTravelTotal(TravelTotal travelTotal);
	
	public int searchTravelCount(TravelTotal travelTotal);
	
	
	public List<TravelTotal> searchTravel(TravelTotal travelTotal);
	
	public List<TravelDetail> searchTravelDetail(TravelTotal travelTotal);
	
	public void updateTravelTotal(TravelTotal travelTotal);
	
	public void updateTravelDetail(TravelDetail travelDetail);
	
	
	/**
	 * 查费用编号明细
	 */
	public List<BudgetNumber> getBudgetNumberList(BudgetNumber budgetNumber);
	
	
	
	

}
