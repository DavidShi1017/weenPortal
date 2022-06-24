package com.jingtong.platform.endCustomer.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

/** 2018/11/06 Add Change alias to EC */
public interface IEndCustomerDao {
	/**
	 * 获取终端页面查询列表数
	 * @param c
	 * @return
	 */
	public int searchEndCustomerListCount(EndCustomer ec);
	
	public List<ECAlias> searchECAlias(EndCustomer ec) ;
	/**
	 * 获取终端客户页面查询列表
	 * @param c
	 * @return
	 */
	public List<EndCustomer> searchEndCustomerList(EndCustomer ec);
	/**
	 * 获取终端列表数
	 * @param c
	 * @return
	 */
	public int getEndCustomerListCount(EndCustomer ec);
	/**
	 * 获取终端客户列表
	 * @param c
	 * @return
	 */
	public List<EndCustomer> getEndCustomerList(EndCustomer ec);
	/**
	 * 根据ID获取终端客户信息
	 * @param c
	 * @return
	 */
	public EndCustomer getEndCustomerById(EndCustomer ec);
	public List<EndCustomer> checkEndCustomer(EndCustomer ec);
	
    public long getECIdPrc();
    /**
     * 设置编码
     * @param ec
     * @return
     */
    public int setECCode(EndCustomer ec);
    /**
     * 标记为已check
     * @param ec
     * @return
     */
    public int checkEC(EndCustomer ec);
	/**
	 * 终端客户信息注册（新增）
	 * @param ec
	 * @return
	 */
	public long createEndCustomer(EndCustomer ec);
	/**
	 * 修改终端客户信息
	 * @param ec
	 * @return
	 */
	public int updateEndCustomer(EndCustomer ec);
	/**
	 * 删除终端客户(物理删除)
	 * @param ec
	 * @return
	 */
	public int deleteEndCustomer(EndCustomer ec);
	
	
	public int updateQuoteInfo(ECAlias ea);
	public int updateQuoteEcInfo(ECAlias ea);
	
	
	/**
	 * 终端客户审核
	 * @param ec
	 * @return
	 */
	public int auditEndCustomer(EndCustomer ec);
	
	
	
	
	
	
//**************************************别名模块***************************************//	
	public int setCheck(EndCustomer ec);
	public List<ECAlias> getECAliasList(ECAlias ea);
	public int getECAliasListCount(ECAlias ea);
	public int getCountByAliasName(ECAlias ea);
	public long createECAlias(ECAlias ea);
	public int updateECAlias(ECAlias ea);
	public int deleteECAliasById(ECAlias ea);
	public ECAlias getECAliasById(ECAlias ea);
	public EndCustomer getEndCustomerByCode(EndCustomer c);
	int updateQuoteECid(ECAlias ea);
	int updateQuotePCid(ECAlias ea);
	int updateDRECid(ECAlias ea);
	int changeEC(ECAlias ea);
	
	public List<EndCustomer> pendingData(EndCustomer ec) ;
	
	// 2018/11/06 Add Start
	public EndCustomer getEndCustomerByNameCity(EndCustomer c);
	public int rollBackEndCustomer(EndCustomer c);
	public int changeAlias(ECAlias ea);
	public List<CustomerUser> getAuditors(CustomerUser cusUser);
	// 2018/11/06 Add End
	
	public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh);
}
