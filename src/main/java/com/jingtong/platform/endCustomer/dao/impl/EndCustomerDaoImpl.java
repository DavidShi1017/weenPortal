package com.jingtong.platform.endCustomer.dao.impl;



import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.dao.IEndCustomerDao;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;


/** 2018/11/06 Add Change alias to EC */
public class EndCustomerDaoImpl  extends BaseDaoImpl implements IEndCustomerDao {
	@Override
	public int searchEndCustomerListCount(EndCustomer ec){
		return (Integer) getSqlMapClientTemplate().queryForObject("endCustomer.searchEndCustomerListCount",ec);
	}
	
	@Override
	public List<EndCustomer> searchEndCustomerList(EndCustomer ec) {
		Object object =   getSqlMapClientTemplate().queryForList("endCustomer.searchEndCustomerList",ec);
		return (List<EndCustomer>) object;
	}
	@Override
	public int getEndCustomerListCount(EndCustomer ec){
		return (Integer) getSqlMapClientTemplate().queryForObject("endCustomer.getEndCustomerListCount",ec);
	}

	@Override
	public List<EndCustomer> getEndCustomerList(EndCustomer ec) {
		return (List<EndCustomer>) getSqlMapClientTemplate().queryForList("endCustomer.getEndCustomerList",ec);
	}
	@Override
	public List<EndCustomer> checkEndCustomer(EndCustomer ec) {
		return (List<EndCustomer>) getSqlMapClientTemplate().queryForList("endCustomer.checkEndCustomer",ec);
	}
	@Override
	public EndCustomer getEndCustomerById(EndCustomer ec) {
		return (EndCustomer) getSqlMapClientTemplate().queryForObject("endCustomer.getEndCustomerById",ec);
	}
	@Override
	public EndCustomer getEndCustomerByCode(EndCustomer ec) {
		return (EndCustomer) getSqlMapClientTemplate().queryForObject("endCustomer.getEndCustomerByCode",ec);
	}

	@Override
	public long createEndCustomer(EndCustomer ec) {
		return  (Long) getSqlMapClientTemplate().insert("endCustomer.createEndCustomer",ec);
	}
	@Override
	public int setECCode(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.setECCode",ec);
	}
	@Override
	public int checkEC(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.checkEC",ec);
	}
	@Override
	public int updateEndCustomer(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateEndCustomer",ec);
	}

	@Override
	public int deleteEndCustomer(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.deleteEndCustomer",ec);
	}
	
	//更新PC
	@Override
	public int updateQuoteInfo(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateQuoteInfo",ea);
	}
	//更新EC
	@Override
	public int updateQuoteEcInfo(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateQuoteEcInfo",ea);
	}
	
	
	
	@Override
	public int auditEndCustomer(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.auditEndCustomer",ec);
	}

	@Override
	public long getECIdPrc() {
		return (long) getSqlMapClientTemplate().update("endCustomer.getECIdPrc");
		
	}
	@Override
	public int setCheck(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.setCheck",ec);
	}
	

	
	
	
//*********************************别名模块*************************************//	

	
	@Override
	public List<ECAlias> getECAliasList(ECAlias ea) {
		return getSqlMapClientTemplate().queryForList("endCustomer.getECAliasList",ea);
	}
	@Override
	public int getECAliasListCount(ECAlias ea) {
		return (Integer)getSqlMapClientTemplate().queryForObject("endCustomer.getECAliasListCount", ea);
	}
	@Override
	public int getCountByAliasName(ECAlias ea) {
		int n= (Integer) getSqlMapClientTemplate().queryForObject("endCustomer.getCountByAliasName", ea);
		return n;
	}
	
	@Override
	public List<ECAlias> searchECAlias(EndCustomer ec) {
		return getSqlMapClientTemplate().queryForList("endCustomer.searchECAlias", ec);
	}
	
	@Override
	public long createECAlias(ECAlias ea) {
		return (Long) getSqlMapClientTemplate().insert("endCustomer.createECAlias", ea);
	}

	@Override
	public int updateECAlias(ECAlias ea) {
		return getSqlMapClientTemplate().update("endCustomer.updateECAlias", ea);
	}
	
	

	@Override
	public int deleteECAliasById(ECAlias ea) {
		return getSqlMapClientTemplate().delete("endCustomer.deleteECAliasById", ea);
	}

	@Override
	public ECAlias getECAliasById(ECAlias ea) {
		return (ECAlias)getSqlMapClientTemplate().queryForObject("endCustomer.getECAliasById", ea);
	}
	
	@Override
	public int changeEC(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.changeEC",ea);
	}
	
	@Override
	public List<EndCustomer> pendingData(EndCustomer ec) {
		return (List<EndCustomer>) getSqlMapClientTemplate().queryForList("endCustomer.pendingData",ec);
	}
	@Override
	public int updateQuoteECid(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateQuoteECid",ea);
	}
	@Override
	public int updateQuotePCid(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateQuotePCid",ea);
	}
	@Override
	public int updateDRECid(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.updateDRECid",ea);
	}
	
	// 2018/11/06 Add Start
	@Override
	public EndCustomer getEndCustomerByNameCity(EndCustomer ec) {
		return (EndCustomer) getSqlMapClientTemplate().queryForObject("endCustomer.getEndCustomerByNameCity",ec);
	}
	
	@Override
	public int rollBackEndCustomer(EndCustomer ec) {
		return (int) getSqlMapClientTemplate().update("endCustomer.rollBackEndCustomer",ec);
	}
	
	@Override
	public int changeAlias(ECAlias ea) {
		return (int) getSqlMapClientTemplate().update("endCustomer.changeAlias",ea);
	}
	
	@Override
	public List<CustomerUser> getAuditors(CustomerUser cusUser) {
		return (List<CustomerUser>)getSqlMapClientTemplate().queryForList("quote.getAuditors",cusUser);
	}
	// 2018/11/06 Add End
	
	@Override
    public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh) {
	    return (List<CmsTbDict>)getSqlMapClientTemplate().queryForList("dict.getNewHierarchyList", nh);
	}
}
