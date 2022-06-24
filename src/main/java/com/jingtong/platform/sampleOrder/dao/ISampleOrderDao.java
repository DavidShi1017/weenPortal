package com.jingtong.platform.sampleOrder.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;



public interface ISampleOrderDao{
	/**
	 * 获取订单列表数
	 * @param c
	 * @return
	 */
	public int getSampleOrderListCount(SampleOrder o);
	/**
	 * 获取订单信息列表
	 * @param c
	 * @return
	 */
	public List<SampleOrder> getSampleOrderList(SampleOrder o);
	/**
	 * 根据ID获取订单信息
	 * @param c
	 * @return
	 */
	public SampleOrder getSampleOrderById(SampleOrder o);
	/**
	 * 订单信息新增
	 * @param p
	 * @return
	 */
	public long createSampleOrder(SampleOrder o);
	/**
	 * 修改订单信息
	 * @param o
	 * @return
	 */
	public int updateSampleOrder(SampleOrder o);
	/**
	 * 删除订单信息(逻辑删除)
	 * @param o
	 * @return
	 */
	public int deleteSampleOrder(SampleOrder o);
	
	
	

	/**
	 * 获取订单明细信息列表
	 * @param od
	 * @return
	 */
	public List<SampleOrderDetail> getSampleOrderDetailList(SampleOrderDetail od);

	/**
	 * 订单明细信息新增
	 * @param od
	 * @return
	 */
	public long createSampleOrderDetail(SampleOrderDetail od);
	/**
	 * 修改订单明细信息
	 * @param od
	 * @return
	 */
	public int updateSampleOrderDetail(SampleOrderDetail od);
	/**
	 * 删除订单明细信息(物理删除)
	 * @param od
	 * @return
	 */
	public int deleteSampleOrderDetail(SampleOrderDetail od);
	
	
	/**
	 * 获取自动生成单号
	 */
	public String getSystemIdPrc();
	public int deleteSODofMain(SampleOrderDetail sod);
	int importUpdateCount(SampleOrderDetail sod);
	int importUpdate(SampleOrderDetail sod);
	
	public int getSampleOrderAndDetailListCount(SampleOrder o);
	public List<SampleOrderAndDetail> getSampleOrderAndDetailList(SampleOrder o) ;
	public int getRoleByLoginId(SampleOrder o);

	public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public List<Borg> getOrgList(Borg borg);
    
    public List<AccountManager> getAccountManageList(AccountManager manager);
    
    public List<Role> getRoleForSampleOrder(String userId);
    
    int auditSampleOrder(SampleOrder so);
    
    int auditSampleOrderDetail(SampleOrderDetail sod);
}