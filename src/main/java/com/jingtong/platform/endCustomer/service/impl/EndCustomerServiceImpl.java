package com.jingtong.platform.endCustomer.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.dao.IEndCustomerDao;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.endCustomer.service.IEndCustomerService;
import com.jingtong.platform.framework.util.DataUtil;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.message.service.IMessageService;

/** 2018/11/06 Add Change alias to EC */
public class EndCustomerServiceImpl implements IEndCustomerService {
	private IEndCustomerDao endCustomerDao;
	private TransactionTemplate transactionTemplate;
	private IMessageService messageService;

	@Override
	public int searchEndCustomerListCount(EndCustomer c) {
		try {
			return endCustomerDao.searchEndCustomerListCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ECAlias> searchECAlias(EndCustomer c) {
		try {
			return endCustomerDao.searchECAlias(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EndCustomer> searchEndCustomerList(EndCustomer c) {
		try {
			return endCustomerDao.searchEndCustomerList(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getEndCustomerListCount(EndCustomer c) {
		try {
			return endCustomerDao.getEndCustomerListCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<EndCustomer> getEndCustomerList(EndCustomer c) {
		try {
			return endCustomerDao.getEndCustomerList(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EndCustomer> checkEndCustomer(EndCustomer c) {
		try {
			return endCustomerDao.checkEndCustomer(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EndCustomer getEndCustomerById(EndCustomer c) {
		try {
			return endCustomerDao.getEndCustomerById(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EndCustomer getEndCustomerByCode(EndCustomer c) {
		try {
			return endCustomerDao.getEndCustomerByCode(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getECIdPrc() {
		try {
			return endCustomerDao.getECIdPrc();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int setECCode(EndCustomer ec) {
		try {
			return endCustomerDao.setECCode(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkEC(EndCustomer ec) {
		try {
			return endCustomerDao.checkEC(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long createEndCustomer(EndCustomer ec) {
		try {
			return endCustomerDao.createEndCustomer(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateEndCustomer(EndCustomer ec) {
		try {
			return endCustomerDao.updateEndCustomer(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int auditEndCustomer(EndCustomer ec) {
		try {
			return endCustomerDao.auditEndCustomer(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public IEndCustomerDao getEndCustomerDao() {
		return endCustomerDao;
	}

	public void setEndCustomerDao(IEndCustomerDao endCustomerDao) {
		this.endCustomerDao = endCustomerDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public int deleteEndCustomer(EndCustomer ec) {
		try {
			return endCustomerDao.deleteEndCustomer(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int setCheck(EndCustomer ec) {
		try {
			return endCustomerDao.setCheck(ec);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// **************************************别名模块***************************************//
	@Override
	public List<ECAlias> getECAliasList(ECAlias ea) {
		try {
			return endCustomerDao.getECAliasList(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getECAliasListCount(ECAlias ea) {
		try {
			return endCustomerDao.getECAliasListCount(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getCountByAliasName(ECAlias ea) {
		try {
			return endCustomerDao.getCountByAliasName(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long createECAlias(ECAlias ea) {
		try {
			return endCustomerDao.createECAlias(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateECAlias(ECAlias ea) {
		try {
			return endCustomerDao.updateECAlias(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteECAliasById(ECAlias ea) {
		try {
			return endCustomerDao.deleteECAliasById(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ECAlias getECAliasById(ECAlias ea) {
		try {
			return endCustomerDao.getECAliasById(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateQuoteECid(ECAlias ea) {
		try {
			return endCustomerDao.updateQuoteECid(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateQuotePCid(ECAlias ea) {
		try {
			return endCustomerDao.updateQuotePCid(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateDRECid(ECAlias ea) {
		try {
			return endCustomerDao.updateDRECid(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int changeEC1(ECAlias ea) {
		try {
			return endCustomerDao.changeEC(ea);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public BooleanResult changeEC(final ECAlias ea, final EndCustomer ec) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				long c = 0;
				long qp = 0;
				long qe = 0;
				long de = 0;
				long dec = 0;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);
					c = endCustomerDao.changeEC(ea);
					if (c <= 0) {
						booleanResult.setResult(false);
						booleanResult.setCode("Failed！");
						ts.setRollbackOnly();
						return booleanResult;
					} else {
						dec = endCustomerDao.deleteEndCustomer(ec);
						if (dec <= 0) {
							booleanResult.setResult(false);
							booleanResult.setCode("Failed！");
							ts.setRollbackOnly();
							return booleanResult;
						}
						qp = endCustomerDao.updateQuoteInfo(ea);
						qe = endCustomerDao.updateQuoteEcInfo(ea);
						de = endCustomerDao.updateDRECid(ea);
						/*
						 * 原change后需更新Quote和DR 后讨论决定不更改历史数据故此处暂注释 qp=endCustomerDao.updateQuotePCid(ea);
						 * qe=endCustomerDao.updateQuoteECid(ea); de=endCustomerDao.updateDRECid(ea);
						 */

						booleanResult.setResult(true);
					}

				} catch (Exception e) {
					e.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("Failed！");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	@Override
	public List<EndCustomer> pendingData(EndCustomer c) {
		try {
			return endCustomerDao.pendingData(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 2018/11/06 Add Start
	@Override
	public BooleanResult changeAlias(final ECAlias ea) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult booleanResult = new BooleanResult();
				String ecCode;
				booleanResult.setResult(true);
				try {
					booleanResult.setResult(false);
					// Get Alias by Id
					ECAlias alaisObj = getECAliasById(ea);
					if (alaisObj == null) {
						booleanResult.setResult(false);
						booleanResult.setCode("Failed！Alais NOT Exist!");
						return booleanResult;
					}

					// Get EC by Name and City
					EndCustomer ecObj = new EndCustomer();
					ecObj.setEnd_customer_name(alaisObj.getEc_alias_name());
					ecObj.setCity(alaisObj.getAlias_city());
					EndCustomer oldEcObj = endCustomerDao.getEndCustomerByNameCity(ecObj);
					// IF Get The EC
					if (oldEcObj != null) {

						if (oldEcObj.getState() == 9) {
							ecCode = oldEcObj.getEnd_customer_id();
							alaisObj.setEc_group(oldEcObj.getEnd_customer_groupId());
							oldEcObj.setState(0);
							oldEcObj.setModify_userId(ea.getModify_userId());
							// Roll back the EC
							endCustomerDao.rollBackEndCustomer(oldEcObj);
						} else {
							booleanResult.setResult(false);
							StringBuilder errMsg = new StringBuilder();
							errMsg.append("Error!");
							errMsg.append("Name:");
							errMsg.append(ecObj.getEnd_customer_name());
							errMsg.append(" City:");
							errMsg.append(ecObj.getCity());
							errMsg.append(" is Exist.");
							booleanResult.setCode(errMsg.toString());
							ts.setRollbackOnly();
							return booleanResult;
						}
					} else {
						// Create new EC
						alaisObj.setEc_group(null);
						long ecId = endCustomerDao.createEndCustomer(ecObj);
						ecCode = DataUtil.frontCompWithZore("E", ecId, 7);
						ecObj.setEnd_customer_id(ecCode);
						ecObj.setId(ecId);
						setECCode(ecObj);
					}
					alaisObj.setEc_city(alaisObj.getAlias_city());
					alaisObj.setEc_name(alaisObj.getEc_alias_name());
					alaisObj.setEc_id(ecCode);
					endCustomerDao.changeAlias(alaisObj);
					StringBuilder content = new StringBuilder();
					content.append("Dear user<br>&nbsp;&nbsp; A new customer ");
					content.append(ecObj.getEnd_customer_name());
					content.append(" needs you to approve, please login in Portal to approve.");
					CustomerUser cusUser = new CustomerUser();
					String role = "CMD";// 申请商务经理审批
					cusUser.setRoleIds(role);
					List<CustomerUser> userList = endCustomerDao.getAuditors(cusUser);
					if (userList != null && userList.size() != 0) {
						for (CustomerUser user : userList) {
							if (user.getEmail() != null) {
								Message ms = new Message();
								ms.setContent(content.toString());
								ms.setType("ECPC Create");
								ms.setSendNumber(user.getEmail());
								messageService.saveMessage(ms);
								System.out.println("AddEmail(ECPC Create) : " + user.getEmail());
							}
						}
					}

					booleanResult.setResult(true);

				} catch (Exception e) {
					e.printStackTrace();
					booleanResult.setResult(false);
					booleanResult.setCode("Failed！");
					ts.setRollbackOnly();
					return booleanResult;
				}
				return booleanResult;
			}
		});
		return booleanResult;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	// 2018/11/06 Add End
	@Override
	public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh) {
	    try {
	        return endCustomerDao.getNewHierarchyList(nh);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
