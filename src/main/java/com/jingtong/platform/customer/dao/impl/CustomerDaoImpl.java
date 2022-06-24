package com.jingtong.platform.customer.dao.impl;

import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.dao.ICustomerDao;
import com.jingtong.platform.customer.pojo.CusCompany;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.role.pojo.Role;

/** 2018/11/12 Add Get Role Ids */
/** 2018/11/15 Add CMD Data to forward list */
public class CustomerDaoImpl extends BaseDaoImpl<Object> implements ICustomerDao {

	@Override
	public int getCustomerListCount(Customer c) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCustomerListCount", c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerList(Customer c) {
		return (List<Customer>) getSqlMapClientTemplate().queryForList("customer.getCustomerList", c);
	}

	@Override
	public Customer getCustomerById(Customer c) {
		try {
			Customer cus = (Customer) getSqlMapClientTemplate().queryForObject("customer.getCustomerById", c);
			return cus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CusCompany> getCusCompanyList(CusCompany c) {
		return (List<CusCompany>) getSqlMapClientTemplate().queryForList("customer.getCusCompanyList", c);
	}

	@Override
	public long createCustomerUser(CustomerUser c) {
		return (Long) getSqlMapClientTemplate().insert("customer.createCustomerUser", c);
	}

	@Override
	public int updateCustomerUser(CustomerUser c) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateCustomerUser", c);
	}

	@Override
	public int setUpdateUser(CustomerUser c) {
		return (Integer) getSqlMapClientTemplate().update("customer.setUpdateUser", c);
	}

	// 人员信息
	@SuppressWarnings("unchecked")
	public List<CustomerUser> searchCustomerUser(CustomerUser cusUser) {
		return getSqlMapClientTemplate().queryForList("customer.searchCusUser", cusUser);
	}

	public int searchCustomerUserCount(CustomerUser cusUser) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.searchCusUserCount", cusUser);
	}

	@Override
	public int setCusomerSignForUser(CustomerUser cusUser) {
		return (Integer) getSqlMapClientTemplate().update("customer.setCusomerSignForUser", cusUser);
	}

	@Override
	public int setCreateUserForUser(CustomerUser c) {
		return (Integer) getSqlMapClientTemplate().update("customer.setCreateUserForUser", c);
	}

	@Override
	public int setModifyUserForUser(CustomerUser c) {
		return (Integer) getSqlMapClientTemplate().update("customer.setModifyUserForUser", c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerUser> getLoginCusUser(CustomerUser cusUser) {
		return getSqlMapClientTemplate().queryForList("customer.getLoginCusUser", cusUser);
	}

	@Override
	public CustomerUser getUsersByUserId(String ids) {
		return (CustomerUser) getSqlMapClientTemplate().queryForObject("customer.getUserByUserId", ids);
	}

	@Override
	public int unBind(CustomerUser cusUser) {
		return (Integer) getSqlMapClientTemplate().delete("customer.unBind", cusUser);
	}

	@Override
	public long bindDistiToThisUser(CustomerUser cusUser) {
		return (Long) getSqlMapClientTemplate().insert("customer.bindDistiToThisUser", cusUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerUser> getDistiListOfThisUser(CustomerUser cusUser) {
		return getSqlMapClientTemplate().queryForList("customer.getDistiListOfThisUser", cusUser);
	}

	@Override
	public int getCountByCusUser(CustomerUser cu) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCountByCusUser", cu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disti_branch> getDistiBranchList(Disti_branch db) {
		return getSqlMapClientTemplate().queryForList("customer.getDistiBranchList", db);
	}

	@Override
	public int getDistiBranchCount(Disti_branch db) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getDistiBranchCount", db);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disti_branch> getDistiNameList(Disti_branch db) {
		return getSqlMapClientTemplate().queryForList("customer.getDistiNameList", db);
	}

	@Override
	public int getDistiNameCount(Disti_branch db) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getDistiNameCount", db);
	}

	@Override
	public Disti_branch getDistiBranchById(Disti_branch db) {
		return (Disti_branch) getSqlMapClientTemplate().queryForObject("customer.getDistiBranchById", db);
	}

	@Override
	public long createDistiBranch(Disti_branch db) {
		return (Long) getSqlMapClientTemplate().insert("customer.createDistiBranch", db);
	}

	@Override
	public int updateDistiBranch(Disti_branch db) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateDistiBranch", db);
	}

	@Override
	public int deleteDistiBranch(Disti_branch db) {
		return (Integer) getSqlMapClientTemplate().delete("customer.deleteDistiBranch", db);
	}

	@Override
	public int getDBCountByPayer_to(Disti_branch db) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getDBCountByPayer_to", db);
	}

	@Override
	public int getAllUserByLoginId(String s) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.getAllUserByLoginId", s);
	}

	@Override
	public int getRole1Count(Role r) {
		return (Integer) getSqlMapClientTemplate().queryForObject("role.getRole1Count", r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRole1List(Role r) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRole1List", r);
	}

	// 2018/11/12 Add Start
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleIdsByLoginId(AllUsers user) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("role.getRoleIdsByLoginId", user);
	}
	// 2018/11/12 Add End
	
	// 2018/11/15 Add Start
	public int searchAllUsersForForwardCount(AllUsers user) {
		return (Integer) getSqlMapClientTemplate().queryForObject("alluser.searchAllUsersForForwardCount", user);
	}
	public List<AllUsers> searchAllUsersForForward(AllUsers user) {
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList("alluser.searchAllUsersForForward", user);
	}
	// 2018/11/15 Add End
}
