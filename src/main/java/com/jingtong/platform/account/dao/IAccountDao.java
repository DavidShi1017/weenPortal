package com.jingtong.platform.account.dao;

import java.util.List;

import com.jingtong.platform.account.pojo.BNumberDetail;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.CostType;
import com.jingtong.platform.account.pojo.ExpensesDetail;
import com.jingtong.platform.account.pojo.ExpensesTotal;
import com.jingtong.platform.account.pojo.PayeeInfo;
import com.jingtong.platform.account.pojo.Region;
import com.jingtong.platform.account.pojo.SingleDetail;
import com.jingtong.platform.account.pojo.SingleTotal;
import com.jingtong.platform.account.pojo.WorkPlanTotal;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;

public interface IAccountDao {

	public Long saveSingleTotal(SingleTotal singleTotal);
	
	public void saveSingleDetail(List<SingleDetail> detailList);
	/**
	 * 创建日常费用总单
	 * @param expensesTotal
	 * @return
	 */
	public Long saveExpensesTotal(ExpensesTotal expensesTotal);
	/***
	 * 创建日常费用明细单
	 * @param detailList
	 */
	public void saveExpensesDetail(List<ExpensesDetail> detailList);
	
	/***
	 * 查询日常费用总单列表
	 * @param expensesTotal
	 * @return
	 */
    public int searchExpensesCount(ExpensesTotal expensesTotal);
	/***
	 * 查询日常费用总单列表
	 * @param expensesTotal
	 * @return
	 */
	public List<ExpensesTotal> searchExpensesList(ExpensesTotal expensesTotal);
	/***
	 * 获取单个日常费用总单
	 * @param expensesTotal
	 * @return
	 */
	public ExpensesTotal getExpenses(ExpensesTotal expensesTotal);
	/***
	 * 查询日常费用明细列表
	 * @param expensesDetail
	 * @return
	 */
	public List<ExpensesDetail> searchExpensesDetailList(ExpensesDetail expensesDetail);
	
	public int getUserRoles(AllUsers user);
	
	public void playMoney(String ids);
	
	public List<SingleTotal> searchTraReimburListToExcel(SingleTotal singleTotal);
	
	public int searchTraReimburCount(SingleTotal singleTotal);
	
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal);
	
	public int searchSingleDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail);
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan);
	
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan);
	
	public int searchCostTypeCount(CmsTbDict dict);
	
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict);
	
	public String getStationIdByDetailId(Long eventDetailId);
	
	public void updateSingleTotal(SingleTotal singleTotal);
	
	public void updateSingleDetail(List<SingleDetail> detailList);

	public void updateCostCenter(SingleTotal singleTotal);
	
	public SingleTotal searchSingleTotalByPlanId(Long planId);
	
	public List<SingleDetail> searchSingleDetailByPlanId(Long planId);
	
	public int getPayeeInfoCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo);
	
	public void addPayeeInfo(PayeeInfo payeeInfo);
	
	public void removePayeeInfo(String ids, String modifier);
	
	public PayeeInfo getPayeeInfoById(Long id);
	
	public void modifyPayeeInfo(PayeeInfo payeeInfo);
	
	public int getPayeeCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo);
	
	public int getPayeeInfoCountByName(String userName);
	
	public PayeeInfo getDefaultPayee(String userName);
	
	public int getPayAccountCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo);
	
	public int getHisEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal);
	
	public List<SingleDetail> getSingleDetailList(String transactionId);
	
	public void updateFinancialDocNum(String transaction_id, String financial_doc_num);
	
	public int countSingleTotal(String transaction_id);
	
	public void batchUpdateFinancialDocNum(List<SingleTotal> singleTotalList);
	
	public List<ProEventDetail> getAuditorListByEventId(Long transaction_id);
	
	public int getReimburDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailListNoPage(SingleDetail singleDetail);
	
	/***
	 * 获取职能费用类型
	 * @param costType
	 * @return
	 */
	public List<CostType> getCostTypeListByCostTypeParentId (CostType costType);
	/**
	 * 查费用编号总数
	 */
	public int getBudgetNumberTotal(BudgetNumber budgetNumber);
	/**
	 * 查费用编号明细
	 */
	public List<BudgetNumber> getBudgetNumberList(BudgetNumber budgetNumber);
	/**
	 * 插入明细表
	 */
	public void creatBudgetDetail(List<BNumberDetail> bNumberDetail,String type);
	/***
	 * 获取当然报销总单
	 * @param singleTotal
	 * @return
	 */
	public SingleTotal getSingleTotal(SingleTotal singleTotal);
	public void modifyAuditExpenseForm(SingleTotal singleTotal);
	
	public ProEventTotal getEventTotalById(Long eventId);
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	/**
	 * 级联查行政区域
	 * @param region
	 * @return
	 */
	public List<Region> searchRegion(Region region);
	
	public int searchLoginTimes(String UserName);
	
	public void addLoginTimes(String UserName);
	
	public void deleteLoginTimes(String UserName);

}
