package com.jingtong.platform.account.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.account.dao.IAccountDao;
import com.jingtong.platform.account.dao.ITravelAppDao;
import com.jingtong.platform.account.pojo.BNumberDetail;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelDetail;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.account.service.ITravelAppService;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.service.IEventService;

public class TravelAppServiceImpl  implements ITravelAppService{
	private static final Logger logger = Logger.getLogger(TravelAppServiceImpl.class);
	private TransactionTemplate transactionTemplate;
	private ITravelAppDao travelAppDao;
	private IAccountDao accountDao;
	
	public StringResult saveTravelTotal(final TravelTotal travelTotal) {
		StringResult result = (StringResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				StringResult result = new StringResult();
				try {
					
						/*List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
						BudgetNumber budgetNumber=new BudgetNumber();
						budgetNumber.setStart(0);
						budgetNumber.setEnd(1);
						budgetNumber.setCostCentId(travelTotal.getCostOrgId());
						budgetNumber.setSubjectId(travelTotal.getCostType());
						budgetNumber.setBudget_number(travelTotal.getBudgetNumber());
						List<BudgetNumber> budgetNumberList =accountDao.getBudgetNumberList(budgetNumber);
						if(budgetNumberList!=null&&budgetNumberList.size()!=0){
							budgetNumber=budgetNumberList.get(0);
							if(budgetNumber.getBudget_money().compareTo(travelTotal.getTotalMoney())==-1){
								result.setCode(IEventService.ERROR);
								result.setResult("插入总单失败，预算金额小于申请金额，请勿重复提交！");
								return result;
							}
						}else{
							result.setCode(IEventService.ERROR);
							result.setResult("预算编号为"+travelTotal.getBudgetNumber()+"的费用科目，无法查询到数据，请联系管理员！");
							return result;
						}*/
						Long businessKey = travelAppDao.createTravelTotal(travelTotal);
						if (businessKey != 0l) {
							for (TravelDetail detail : travelTotal.getTravelDetailList()) {
								detail.setTravelId(businessKey);
								travelAppDao.createTravelDetail(detail);
							}
							
							
							/*if (travelTotal != null) {
									// 扣减预算
									BigDecimal b1 = new BigDecimal(0);
									BNumberDetail bd = new BNumberDetail();
									// 扣减预算
									bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // 费用编号主表ID
									bd.setPlan_id(travelTotal.getTravelId()); // 报销主表ID
									bd.setOp_money1(b1.subtract(travelTotal.getTotalMoney()));// 操作金额
									bd.setOperatorid(travelTotal.getCreateUserId());// 操作人信息
									bd.setOperatorname(travelTotal.getPayee()); // 操作人名称
									bd.setOp_orgid(String.valueOf(travelTotal.getOrgId()));// 组织ID
									bd.setOp_orgname(travelTotal.getOrgName()); // 组织名称
									bd.setReason("差旅费用申请");
									bd.setREASON_NUM(Long.valueOf(11));// 操作原因
									bd.setCHECK_FLAG("Y");
									bd.setOa_id(travelTotal.getTransactionId());
									bd.setBudget_number(travelTotal.getBudgetNumber());// 预算科目编号
									if(StringUtils.isEmpty(bd.getOa_id())){
										result.setCode(IEventService.ERROR);
										result.setResult("插入总单失败,无法生产预算信息，请勿重复提交！");
									}
									bNumberDetails.add(bd);
									accountDao.creatBudgetDetail(bNumberDetails,"1");// 调用接口保存
							}*/
							result.setCode(IEventService.SUCCESS);
						}else{
							ts.setRollbackOnly();
							result.setCode(IEventService.ERROR);
							result.setResult("插入总单失败，请勿重复提交！");
						}
					
							
					} catch (Exception e) {
						logger.error(e);
						ts.setRollbackOnly();
						e.printStackTrace();
						result.setCode(IEventService.ERROR);
						result.setResult(IEventService.ERROR_MESSAGE);
					}
				return result;
				}	
		});
		return result;
	}
	
	/**
	 * 申请事务申报时拒绝调用
	 */
	public BooleanResult refuseTravelTr(TravelTotal travelTotal) {
		//初始化
		BooleanResult booleanResult = new BooleanResult();
		try {
			travelTotal.setStart(0);
			travelTotal.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(travelTotal);
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			if(tlise!=null&&tlise.size()!=0){
				travelTotal = tlise.get(0);
			}else{
				booleanResult.setResult(false);
				booleanResult.setCode("查询报销单失败");
			}
			BNumberDetail bd = new BNumberDetail();
			// 扣减预算
			bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // 费用编号主表ID
			bd.setPlan_id(travelTotal.getTravelId()); // 报销主表ID
			bd.setREASON_NUM(Long.valueOf(11));// 操作原因
			bd.setBudget_number(travelTotal.getBudgetNumber());// 预算科目编号
			/*bd.setOld_oa_id(travelTotal.getTransactionId());
			if(bd.getPlan_id()==null||bd.getOld_oa_id()==null||"".equals(bd.getOld_oa_id())){
				booleanResult.setResult(false);
				booleanResult.setCode("差旅费用申请返还预算,存在字段为空现象，请联系管理员！");
				return booleanResult;
			}*/
			bNumberDetails.add(bd);
			accountDao.creatBudgetDetail(bNumberDetails,"3");// 调用接口保存
			booleanResult.setResult(true);
			booleanResult.setCode("拒绝成功");
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("差旅费用申请返还预算失败，请联系管理员！");
			logger.error(TravelAppServiceImpl.class,e);
		}
		return booleanResult;
	}
	
	public int searchTravelCount(TravelTotal travelTotal) {
		try {
			return travelAppDao.searchTravelCount(travelTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelTotal), e);
		}
		return 0;
	}
	
	
	 
	public List<TravelTotal> searchTravel(TravelTotal travelTotal) {
		try {
			return travelAppDao.searchTravel(travelTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelTotal), e);
		}
		return null;
	}
	
	 
	public TravelTotal searchTraveltotalByTravelId(String travelId,
			String eventId) {
		try {
			TravelTotal travelTotal=new TravelTotal();
			travelTotal.setId(travelId);
			travelTotal.setStart(0);
			travelTotal.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(travelTotal);
			if(tlise!=null&&tlise.size()!=0){
				travelTotal = tlise.get(0);
			}
			List<TravelDetail> detailList = travelAppDao.searchTravelDetail(travelTotal);
			if(travelId != null){
				List<ProEventDetail> eventDetailList = accountDao
						.getAuditorListByEventId(Long.parseLong(eventId));
				StringBuilder sb = new StringBuilder();
				for (ProEventDetail eventDetail : eventDetailList) {
					if (sb.length() == 0) {
						sb.append(eventDetail.getCurUserName());
					} else {
						sb.append("、" + eventDetail.getCurUserName());
					}
				}
				travelTotal.setAuditor(sb.toString());
			}
			
			travelTotal.setTravelDetailList(detailList);
			return travelTotal;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelId), e);
		}
		return null;
	}
	
	
	 
	public BooleanResult updateTravel(final TravelTotal travelTotal) {
		BooleanResult result = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			 
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				try {
					travelAppDao.updateTravelTotal(travelTotal);
					List<TravelDetail> travelDetailList=travelTotal.getTravelDetailList();
					TravelDetail travelDetail=new TravelDetail();
					for (int i = 0; i < travelDetailList.size(); i++) {
						travelDetail=travelDetailList.get(i);
						travelAppDao.updateTravelDetail(travelDetail);
					}
					result.setResult(true);
					result.setCode("修改报销单成功");
				} catch (Exception e) {
					logger.error(e);
					ts.setRollbackOnly();
					e.printStackTrace();
					result.setResult(false);
					result.setCode("修改报销单出错");
				}
				return result;
			}});
		return result;
	}
	
	 
	public BooleanResult exitTravelTotal(TravelTotal travelTotal) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			TravelTotal total=new TravelTotal();
			total.setWriteEventId(travelTotal.getWriteEventId());
			total.setId(Long.toString(travelTotal.getTravelId()));
			total.setStart(0);
			total.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(total);
			travelTotal=tlise.get(0);
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			BigDecimal b1 = new BigDecimal(0);
			BNumberDetail bd = new BNumberDetail();
			bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // 费用编号主表ID
			bd.setPlan_id(travelTotal.getTravelId()); // 报销主表ID
			bd.setOp_money1(b1.subtract(travelTotal.getAuditMoney()));
			bd.setBudget_number(travelTotal.getBudgetNumber());// 预算科目编号
			bd.setOperatorid(travelTotal.getCreateUserId());// 操作人信息
			bd.setOperatorname(travelTotal.getPayee()); // 操作人名称
			bd.setOp_orgid(String.valueOf(travelTotal.getOrgId()));// 组织ID
			bd.setOp_orgname(travelTotal.getOrgName()); // 组织名称
			bd.setREASON_NUM(Long.valueOf(3));// 操作原因
			bd.setReason("差旅费用核销");
			bd.setCHECK_FLAG("Y");
			bd.setOa_id(travelTotal.getWriteEventId());
			/*bd.setOld_oa_id(travelTotal.getTransactionId());
			if(StringUtils.isEmpty(bd.getOa_id())||StringUtils.isEmpty(bd.getOld_oa_id())){
				booleanResult.setResult(false);
				booleanResult.setCode("修改差旅预算失败，请联系管理员");
			}*/
			bNumberDetails.add(bd);
			accountDao.creatBudgetDetail(bNumberDetails,"2");// 调用接口保存
			booleanResult.setResult(true);
			booleanResult.setCode("修改差旅核销费用信息成功！");
		} catch (Exception e) {
			logger.error(TravelAppServiceImpl.class,e);
			booleanResult.setResult(false);
			booleanResult.setCode("修改差旅核销费用信息失败！");
		}
		
		return booleanResult;
	}
	
	 
	public BooleanResult updateTravelApp(final TravelTotal travelTotal){
		BooleanResult result = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			 
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				try {
					travelAppDao.updateTravelTotal(travelTotal);
					List<TravelDetail> travelDetailList=travelTotal.getTravelDetailList();
					TravelDetail travelDetail=new TravelDetail();
					for (int i = 0; i < travelDetailList.size(); i++) {
						travelDetail=travelDetailList.get(i);
						travelAppDao.updateTravelDetail(travelDetail);
					}
					
					List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
					BigDecimal b1 = new BigDecimal(0);
					BNumberDetail bd = new BNumberDetail();
					bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // 费用编号主表ID
					bd.setPlan_id(travelTotal.getTravelId()); // 报销主表ID
					bd.setOp_money1(b1.subtract(travelTotal.getTotalMoney()));
					bd.setBudget_number(travelTotal.getBudgetNumber());// 预算科目编号
					bd.setREASON_NUM(Long.valueOf(11));// 操作原因
					//bd.setOld_oa_id(travelTotal.getTransactionId());
					bNumberDetails.add(bd);
					//accountDao.updateBudgetDetail(bNumberDetails);
					result.setResult(true);
					result.setCode("修改差旅申请费用信息成功！");
					
				} catch (Exception e) {
					logger.error(e);
					ts.setRollbackOnly();
					e.printStackTrace();
					result.setResult(false);
					result.setCode("修改申请单出错,请联系管理员");
				}
				return result;
			}});
		return result;
	}
	
	 
	public BudgetNumber getBudgetNumber(BudgetNumber budgetNumber) {
		List<BudgetNumber> bListudgetNumber=travelAppDao.getBudgetNumberList(budgetNumber);
		if(bListudgetNumber==null){
			return null;
		}else if(bListudgetNumber.size()>1){
			return null;
		}else{
			return  bListudgetNumber.get(0);
		}
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public ITravelAppDao getTravelAppDao() {
		return travelAppDao;
	}
	public void setTravelAppDao(ITravelAppDao travelAppDao) {
		this.travelAppDao = travelAppDao;
	}
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
}
