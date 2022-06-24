package com.jingtong.platform.account.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jingtong.platform.account.dao.IAccountDao;
import com.jingtong.platform.account.pojo.BNumberDetail;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.CostType;
import com.jingtong.platform.account.pojo.ExpensesDetail;
import com.jingtong.platform.account.pojo.ExpensesTotal;
import com.jingtong.platform.account.pojo.PayeeInfo;
import com.jingtong.platform.account.pojo.Region;
import com.jingtong.platform.account.pojo.SingleDetail;
import com.jingtong.platform.account.pojo.SingleTotal;
import com.jingtong.platform.account.pojo.UserLocked;
import com.jingtong.platform.account.pojo.WorkPlanTotal;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;

@SuppressWarnings("rawtypes")
public class AccountDaoImpl extends BaseDaoImpl implements IAccountDao {

	public Long saveSingleTotal(SingleTotal singleTotal) {
		return (Long) getSqlMapClientTemplate().insert(
				"account.saveEntity_singleTotal", singleTotal);
	}

	
	public void saveSingleDetail(final List<SingleDetail> detailList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (SingleDetail detail : detailList) {
					executor.insert("account.saveEntity_singleDetail", detail);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/**
	 * 创建日常费用总单
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public Long saveExpensesTotal(ExpensesTotal expensesTotal) {
		return (Long) getSqlMapClientTemplate().insert(
				"account.saveExpensesTotal", expensesTotal);

	}

	/***
	 * 创建日常费用明细单
	 * 
	 * @param detailList
	 */
	public void saveExpensesDetail(final List<ExpensesDetail> detailList) {

		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (ExpensesDetail detail : detailList) {
					executor.insert("account.saveExpensesDetail", detail);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/***
	 * 查询日常费用总单列表
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public int searchExpensesCount(ExpensesTotal expensesTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchExpensesCount", expensesTotal);
	}

	/***
	 * 查询日常费用总单列表
	 * 
	 * @param expensesTotal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpensesTotal> searchExpensesList(ExpensesTotal expensesTotal) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchExpensesList", expensesTotal);
	}

	/***
	 * 查询日常费用明细列表
	 * 
	 * @param expensesDetail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExpensesDetail> searchExpensesDetailList(
			ExpensesDetail expensesDetail) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchExpensesDetailList", expensesDetail);
	}

	/***
	 * 获取单个日常费用总单
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public ExpensesTotal getExpenses(ExpensesTotal expensesTotal) {
		return (ExpensesTotal) getSqlMapClientTemplate().queryForObject(
				"account.getExpenses", expensesTotal);

	}

	/**
	 * 判断user是否有某一角色
	 */
	 
	public int getUserRoles(AllUsers user) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getUserRoles", user);
	}

	 
	public SingleTotal searchSingleTotalByPlanId(Long planId) {
		return (SingleTotal) getSqlMapClientTemplate().queryForObject(
				"account.searchSingleTotalByPlanId", planId);
	}

	 
	@SuppressWarnings("unchecked")
	public List<SingleDetail> searchSingleDetailByPlanId(Long planId) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchSingleDetailByPlanId", planId);
	}

	/**
	 * 付款
	 */
	 
	public void playMoney(String ids) {
		this.getSqlMapClientTemplate().update("account.playMoney", ids);
	}

	/**
	 * 导出到Excel的报销单列表
	 */
	 
	@SuppressWarnings("unchecked")
	public List<SingleTotal> searchTraReimburListToExcel(SingleTotal singleTotal) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchTraReimburListToExcel", singleTotal);
	}

	 
	public int searchTraReimburCount(SingleTotal singleTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchTraReimburCount", singleTotal);
	}

	 
	@SuppressWarnings("unchecked")
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchTraReimburList", singleTotal);
	}

	 
	public int searchSingleDetailCount(SingleDetail singleDetail) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchSingleDetailCount", singleDetail);
	}

	 
	@SuppressWarnings("unchecked")
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchSingleDetailList", singleDetail);
	}

	 
	public int searchWorkPlanCount(WorkPlanTotal workPlan) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchWorkPlanCount", workPlan);
	}

	 
	@SuppressWarnings("unchecked")
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan) {
		return getSqlMapClientTemplate().queryForList("account.searchWorkPlan",
				workPlan);
	}

	 
	public int searchCostTypeCount(CmsTbDict dict) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.searchCostTypeCount", dict);
	}

	 
	@SuppressWarnings("unchecked")
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict) {
		return getSqlMapClientTemplate().queryForList(
				"account.searchCostTypeList", dict);
	}

	 
	public String getStationIdByDetailId(Long eventDetailId) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"account.getStationIdByDetailId", eventDetailId);
	}

	 
	public void updateSingleTotal(SingleTotal singleTotal) {
		getSqlMapClientTemplate().update("account.updateSingleTotal",
				singleTotal);
	}

	 
	public void updateSingleDetail(final List<SingleDetail> detailList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (SingleDetail singleDetail : detailList) {
					executor.update("account.updateSingleDetail", singleDetail);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	 
	public void updateCostCenter(SingleTotal singleTotal) {
		getSqlMapClientTemplate().update("account.updateCostCenter",
				singleTotal);

	}

	 
	public int getPayeeInfoCount(PayeeInfo payeeInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getPayeeInfoCount", payeeInfo);
	}

	 
	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList(
				"account.getPayeeInfoList", payeeInfo);
	}

	 
	public void addPayeeInfo(PayeeInfo payeeInfo) {
		getSqlMapClientTemplate().insert("account.addPayeeInfo", payeeInfo);
	}

	 
	public void removePayeeInfo(final String ids, final String modifier) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				String[] id = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id", Long.parseLong(id[i]));
					map.put("modifier", modifier);
					executor.update("account.removePayeeInfo", map);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	 
	public PayeeInfo getPayeeInfoById(Long id) {
		return (PayeeInfo) getSqlMapClientTemplate().queryForObject(
				"account.getPayeeInfoById", id);
	}

	 
	public void modifyPayeeInfo(PayeeInfo payeeInfo) {
		getSqlMapClientTemplate().update("account.modifyPayeeInfo", payeeInfo);
	}

	 
	public int getPayeeCount(PayeeInfo payeeInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getPayeeCount", payeeInfo);
	}

	 
	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList("account.getPayeeList",
				payeeInfo);
	}

	 
	public int getPayeeInfoCountByName(String userName) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getPayeeInfoCountByName", userName);
	}

	 
	public PayeeInfo getDefaultPayee(String userName) {
		return (PayeeInfo) getSqlMapClientTemplate().queryForObject(
				"account.getDefaultPayee", userName);
	}

	 
	public int getPayAccountCount(PayeeInfo payeeInfo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getPayAccountCount", payeeInfo);
	}

	 
	@SuppressWarnings("unchecked")
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo) {
		return getSqlMapClientTemplate().queryForList(
				"account.getPayAccountList", payeeInfo);
	}

	 
	public int getHisEventCount(ProcessEventTotal eventTotal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getHisEventCount", eventTotal);
	};

	 
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal) {
		return getSqlMapClientTemplate().queryForList(
				"account.getHisEventList", eventTotal);
	}

	 
	@SuppressWarnings("unchecked")
	public List<SingleDetail> getSingleDetailList(String transactionId) {
		return getSqlMapClientTemplate().queryForList(
				"account.getSingleDetailList", transactionId);
	}

	 
	public void updateFinancialDocNum(String transaction_id,
			String financial_doc_num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("transaction_id", transaction_id);
		map.put("financial_doc_num", financial_doc_num);
		getSqlMapClientTemplate().update("account.updateFinancialDocNum", map);
	};

	 
	public int countSingleTotal(String transaction_id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.countSingleTotal", transaction_id);
	};

	 
	public void batchUpdateFinancialDocNum(
			final List<SingleTotal> singleTotalList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (SingleTotal singleTotal : singleTotalList) {
					executor.update("account.batchUpdateFinancialDocNum",
							singleTotal);
				}
				executor.executeBatch();
				return null;
			}
		});
	};

	 
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getAuditorListByEventId(Long transaction_id) {
		return getSqlMapClientTemplate().queryForList(
				"account.getAuditorListByEventId", transaction_id);
	}

	 
	public int getReimburDetailCount(SingleDetail singleDetail) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getReimburDetailCount", singleDetail);
	}

	 
	@SuppressWarnings("unchecked")
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail) {
		return getSqlMapClientTemplate().queryForList(
				"account.getReimburDetailList", singleDetail);
	};

	 
	@SuppressWarnings("unchecked")
	public List<SingleDetail> getReimburDetailListNoPage(
			SingleDetail singleDetail) {
		return getSqlMapClientTemplate().queryForList(
				"account.getReimburDetailListNoPage", singleDetail);
	};

	/***
	 * 获取职能费用类型
	 * 
	 * @param costType
	 * @return
	 */
	 
	@SuppressWarnings("unchecked")
	public List<CostType> getCostTypeListByCostTypeParentId(CostType costType) {
		return getSqlMapClientTemplate().queryForList(
				"account.getCostTypeListByCostTypeParentId", costType);
	}

	/**
	 * 查费用编号总数
	 */
	 
	public int getBudgetNumberTotal(BudgetNumber budgetNumber) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"account.getBudgetNumberTotal", budgetNumber);
	}

	/**
	 * 查费用编号明细
	 */
	 
	@SuppressWarnings("unchecked")
	public List<BudgetNumber> getBudgetNumberList(BudgetNumber budgetNumber) {
		return getSqlMapClientTemplate().queryForList(
				"account.getBudgetNumberList", budgetNumber);
	}

	/***
	 * 创建费用编号明细内容
	 */
	 
	public void creatBudgetDetail(final List<BNumberDetail> bNumberDetails,
			final String type) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			 
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				if ("2".equals(type)) { // 核销的时候需要把之前的删除，新的数据插入
					for (BNumberDetail detail : bNumberDetails) {
						executor.update("account.updateBudgetDetail", detail);
						executor.insert("account.creatBudgetDetail", detail);
					}
				} else if ("3".equals(type)) { // 拒绝的时候，直接禁用费用使用 返还费用
					for (BNumberDetail detail : bNumberDetails) {
						executor.insert("account.updateBudgetDetail", detail);
					}
				} else { // 普通提报的时候
					for (BNumberDetail detail : bNumberDetails) {
						executor.insert("account.creatBudgetDetail", detail);
					}
				}

				executor.executeBatch();
				return null;
			}
		});
	}

	/***
	 * 获取当然报销总单
	 * 
	 * @param singleTotal
	 * @return
	 */
	 
	public SingleTotal getSingleTotal(SingleTotal singleTotal) {
		return (SingleTotal) getSqlMapClientTemplate().queryForObject(
				"account.getSingleTotal", singleTotal);
	}

	 
	public void modifyAuditExpenseForm(SingleTotal singleTotal) {
		getSqlMapClientTemplate().update("account.modifyAuditExpenseForm",
				singleTotal);
	}

	 
	public ProEventTotal getEventTotalById(Long eventId) {
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"account.getEventTotalById", eventId);
	}

	 
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail) {
		return getSqlMapClientTemplate().queryForList(
				"account.getEventDetailList", eventDetail);
	}

	 
	@SuppressWarnings("unchecked")
	public List<Region> searchRegion(Region region) {
		return this.getSqlMapClientTemplate().queryForList(
				"account.searchRegion", region);
	}


	public int searchLoginTimes(String UserName) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject(
				"account.searchlogintimes", UserName);
	}
	
	public void addLoginTimes(String UserName) {
		this.getSqlMapClientTemplate().insert("account.addlogintimes", UserName);
	}
	
	public void deleteLoginTimes(String UserName) {
		this.getSqlMapClientTemplate().delete("account.deletelogintimes", UserName);
	}


}
