package com.jingtong.platform.priceRule.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.priceRule.pojo.PriceRule;

public interface IPriceRuleDao {
	/**
	 * 获取终端列表数
	 * @param c
	 * @return
	 */
	public int getPriceRuleListCount(PriceRule pr);
	/**
	 * 获取终端客户列表
	 * @param c
	 * @return
	 */
	public List<PriceRule> getPriceRuleList(PriceRule pr);
	/**
	 * 根据ID获取终端客户信息
	 * @param c
	 * @return
	 */
	public PriceRule getPriceRuleById(PriceRule pr);
	/**
	 * 终端客户信息注册（新增）
	 * @param pr
	 * @return
	 */
	public long createPriceRule(PriceRule pr);
	/**
	 * 修改终端客户信息
	 * @param pr
	 * @return
	 */
	public int updatePriceRule(PriceRule pr);
	/**
	 * 删除终端客户(物理删除)
	 * @param pr
	 * @return
	 */
	public int deletePriceRule(PriceRule pr);
	/**
	 * 终端客户审核
	 * @param pr
	 * @return
	 */
	public int auditPriceRule(PriceRule pr);
	
	/**
	 * 获取终端列表数(control)
	 * @param c
	 * @return
	 */
	public int getControlPriceRuleListCount(PriceRule pr);
	/**
	 * 获取终端客户列表(control)
	 * @param c
	 * @return
	 */
	public List<PriceRule> getControlPriceRuleList(PriceRule pr);
	
	
	public int getOrgListCount(Borg borg);
	public List<Borg> getOrgList(Borg borg);
	
	public int getPriceRuleListCountCmRm(PriceRule pr) ;
	public List<PriceRule> getPriceRuleListCmRmQm(PriceRule priceRule);
}
