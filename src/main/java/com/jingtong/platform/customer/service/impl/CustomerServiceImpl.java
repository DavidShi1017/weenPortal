package com.jingtong.platform.customer.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.dao.ICustomerDao;
import com.jingtong.platform.customer.pojo.CusCompany;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.customer.service.ICustomerService;
import com.jingtong.platform.role.pojo.Role;

/** 2018/11/15 Add CMD Data to forward list */
public class CustomerServiceImpl implements ICustomerService {
	private ICustomerDao customerDao;
	private TransactionTemplate transactionTemplate;

	@Override
	public int getCustomerListCount(Customer c) {
		try {
			return customerDao.getCustomerListCount(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Customer> getCustomerList(Customer c) {
		try {
			return customerDao.getCustomerList(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer getCustomerById(Customer c) {
		try {
			return customerDao.getCustomerById(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CusCompany> getCusCompanyList(CusCompany c) {
		try {
			return customerDao.getCusCompanyList(c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	
//	@Override
//	public long createCustomerUser(CustomerUser c) {
//		try {
//			return customerDao.createCustomerUser(c);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}

	@Override
	public BooleanResult createUser(CustomerUser cu) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long empId = customerDao.createCustomerUser(cu);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(empId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("人员创建失败");
		}

		return booleanResult;
	}

	@Override
	public BooleanResult updateCustomerUser(CustomerUser cu) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			long empId = customerDao.updateCustomerUser(cu);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(empId));
		} catch (Exception e) {
			e.printStackTrace();
			booleanResult.setResult(false);
			booleanResult.setCode("人员创建失败");
		}

		return booleanResult;
	}

	// 人员信息
	@Override
	public List<CustomerUser> searchCustomerUser(CustomerUser cusUser) {
		try {
			return customerDao.searchCustomerUser(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int searchCustomerUserCount(CustomerUser cusUser) {
		try {
			return customerDao.searchCustomerUserCount(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int setCusomerSignForUser(CustomerUser cusUser) {
		try {
			System.out.println(cusUser.getLoginId());
			int n = customerDao.setCusomerSignForUser(cusUser);
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int setCreateUserForUser(CustomerUser cusUser) {
		try {
			System.out.println(cusUser.getLoginId());
			int n = customerDao.setCreateUserForUser(cusUser);
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int setModifyUserForUser(CustomerUser cusUser) {
		try {
			System.out.println(cusUser.getLoginId());
			int n = customerDao.setModifyUserForUser(cusUser);
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public List<CustomerUser> getLoginCusUser(CustomerUser cusUser) {
		try {
			return customerDao.getLoginCusUser(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public CustomerUser getUsersByUserId(String ids) {
		try {
			return customerDao.getUsersByUserId(ids);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CustomerUser> getDistiListOfThisUser(CustomerUser cusUser) {
		try {
			return customerDao.getDistiListOfThisUser(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public long bindDistiToThisUser(CustomerUser cusUser) {
		try {
			return customerDao.bindDistiToThisUser(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int unBind(CustomerUser cusUser) {
		try {
			return customerDao.unBind(cusUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public int getCountByCusUser(CustomerUser cu) {
		try {
			return customerDao.getCountByCusUser(cu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Disti_branch> getDistiBranchList(Disti_branch db) {
		try {
			return customerDao.getDistiBranchList(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getDistiBranchCount(Disti_branch db) {
		try {
			return customerDao.getDistiBranchCount(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Disti_branch> getDistiNameList(Disti_branch db) {
		try {
			return customerDao.getDistiNameList(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getDistiNameCount(Disti_branch db) {
		try {
			return customerDao.getDistiNameCount(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getDBCountByPayer_to(Disti_branch db) {
		try {
			return customerDao.getDBCountByPayer_to(db);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteDistiBranch(Disti_branch db) {
		try {
			return customerDao.deleteDistiBranch(db);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateDistiBranch(Disti_branch db) {
		try {
			return customerDao.updateDistiBranch(db);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long createDistiBranch(Disti_branch db) {
		try {
			return customerDao.createDistiBranch(db);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Disti_branch getDistiBranchById(Disti_branch db) {
		try {
			return customerDao.getDistiBranchById(db);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAllUserByLoginId(String s) {
		try {
			int n = customerDao.getAllUserByLoginId(s);
			if (n > 0) {
				return "exist";
			} else {
				return "unexist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getRole1Count(Role r) {
		try {
			return customerDao.getRole1Count(r);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Role> getRole1List(Role r) {
		try {
			return customerDao.getRole1List(r);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 2018/11/15 Add Start
	@Override
	public int searchAllUsersForForwardCount(AllUsers allUser) {
		try {
			return customerDao.searchAllUsersForForwardCount(allUser);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<AllUsers> searchAllUsersForForward(AllUsers allUser) {
		try {
			return customerDao.searchAllUsersForForward(allUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// 2018/11/15 Add End
}
