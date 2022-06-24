package com.jingtong.platform.account.service;

import java.util.List;

import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;

public interface ITravelAppService {

	//创建申请单
	public StringResult  saveTravelTotal(TravelTotal travelTotal);
	/**
	 * 查询报销单数量(分页)
	 */
	public int searchTravelCount(TravelTotal travelTotal);
	
	
	/**
	 * 查询报销单(分页)
	 */
	public List<TravelTotal> searchTravel(TravelTotal travelTotal);
	
	
	/**
	 * 查询报销单通过travelId
	 * @param travelId
	 * @param transactionId
	 * @return
	 */
	
	public TravelTotal searchTraveltotalByTravelId(String travelId,String transactionId);
	
	/**
	 * 修改报销单
	 * @param travelTotal
	 * @return
	 */
	public BooleanResult updateTravel(final TravelTotal travelTotal);
	
	/**
	 * 差旅事务申请拒绝
	 * @param travelTotal
	 * @return
	 */
	public BooleanResult refuseTravelTr(TravelTotal travelTotal);
	/**
	 * 核销最后一步修改预算费用
	 */
	public BooleanResult exitTravelTotal(TravelTotal travelTotal);
	
	/**
	 * 获取预算余额
	 */
	public BudgetNumber getBudgetNumber(BudgetNumber budgetNumber);
	
	/**
	 * 回退，修改申请单信息
	 */
	public BooleanResult updateTravelApp(final TravelTotal travelTotal);

}
