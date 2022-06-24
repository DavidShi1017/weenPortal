package com.jingtong.platform.account.service;

import java.io.File;
import java.util.List;

import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.CostType;
import com.jingtong.platform.account.pojo.ExpensesTotal;
import com.jingtong.platform.account.pojo.PayeeInfo;
import com.jingtong.platform.account.pojo.Region;
import com.jingtong.platform.account.pojo.SingleDetail;
import com.jingtong.platform.account.pojo.SingleTotal;
import com.jingtong.platform.account.pojo.WorkPlanTotal;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;

public interface IAccountService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String SUCCESS_MESSAGE = "操作成功！";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";;
	
	public boolean getUserRoles(AllUsers user, String roleId);
	
	public StringResult playMoney(String ids);
	
	public StringResult createSingle(SingleTotal singleTotal);
	/***
	 * 创建日常费用申请
	 * @param expensesTotal
	 * @return
	 */
	public StringResult createExpenses(ExpensesTotal  expensesTotal);
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
	public ExpensesTotal getExpensesTotalAndDetail(ExpensesTotal expensesTotal);
	
	public int searchTraReimburCount(SingleTotal singleTotal);
	
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal);
	
	public int searchSingleDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail);
	
	public int searchWorkPlanCount(WorkPlanTotal workPlan);
	
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan);
	
	public int searchCostTypeCount(CmsTbDict dict);
	
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict);
	
	public String getStationIdByDetailId(Long eventDetailId);
	
	public StringResult updateSingle(SingleTotal singleTotal);
	
	public StringResult modifyAuditExpenseForm(SingleTotal singleTotal);

	
	public SingleTotal searchSingelTotalByPlanId(Long planId, String transaction_id);
	
	public File exportTraReimbur(SingleTotal singleTotal);
	
	public int getPayeeInfoCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo);
	
	public StringResult addPayeeInfo(PayeeInfo payeeInfo);
	
	public StringResult removePayeeInfo(String ids, String modifier);
	
	public PayeeInfo getPayeeInfoById(Long id);
	
	public StringResult modifyPayeeInfo(PayeeInfo payeeInfo);
	
	public int getPayeeCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo);
	
	public int getPayeeInfoCountByName(String userName);
	
	public PayeeInfo getDefaultPayee(String userName);
	
	public int getPayAccountCount(PayeeInfo payeeInfo);
	
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo);
	
	public int getHisEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal);
	
	public List<SingleDetail> getSingleDetailList(String transactionId);
	
	public StringResult updateFinancialDocNum(String transaction_id, String financial_doc_num);
	
	public int countSingleTotal(String transaction_id);
	
	public StringResult batchUpdateFinancialDocNum(List<SingleTotal> singleTotalList);
	
	public int getReimburDetailCount(SingleDetail singleDetail);
	
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail);
	
	public File exportReimberDetailList(SingleDetail singleDetail);
	
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
	 * 修改明细
	 * @param details
	 * @return
	 */
	public StringResult updateSingleDetail(List<SingleDetail> details);
	/**
	 * 核销事务处理成功以后调用
	 */
	public BooleanResult activateAccountPlan(SingleTotal singleTotal);
	/**
	 * 申请事务申报时拒绝调用
	 */
	public BooleanResult refuseAccountPlan(SingleTotal singleTotal);

	public ProEventTotal getEventTotalById(Long eventId);
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId);
	

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
