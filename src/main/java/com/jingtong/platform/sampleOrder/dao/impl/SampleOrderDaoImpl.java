package com.jingtong.platform.sampleOrder.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sampleOrder.dao.ISampleOrderDao;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;

public class SampleOrderDaoImpl  extends BaseDaoImpl<Object> implements ISampleOrderDao{

	@Override
	public int getSampleOrderListCount(SampleOrder o) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sampleOrder.getSampleOrderListCount",o);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<SampleOrder> getSampleOrderList(SampleOrder o) {
		return (List<SampleOrder>) getSqlMapClientTemplate().queryForList("sampleOrder.getSampleOrderList",o);
	}

	@Override
	public SampleOrder getSampleOrderById(SampleOrder o) {
		return (SampleOrder) getSqlMapClientTemplate().queryForObject("sampleOrder.getSampleOrderById",o);
	}

	@Override
	public long createSampleOrder(SampleOrder o) {
		return (Long) getSqlMapClientTemplate().insert("sampleOrder.createSampleOrder",o);
	}

	@Override
	public int updateSampleOrder(SampleOrder o) {
		return (Integer) getSqlMapClientTemplate().update("sampleOrder.updateSampleOrder",o);
	}

	@Override
	public int deleteSampleOrder(SampleOrder o) {
		return (Integer) getSqlMapClientTemplate().delete("sampleOrder.deleteSampleOrder",o);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<SampleOrderDetail> getSampleOrderDetailList(SampleOrderDetail od) {
		return (List<SampleOrderDetail>) getSqlMapClientTemplate().queryForList("sampleOrder.getSampleOrderDetailList",od);
	}

	@Override
	public long createSampleOrderDetail(SampleOrderDetail od) {
		return (Long) getSqlMapClientTemplate().insert("sampleOrder.createSampleOrderDetail",od);
	}

	@Override
	public int updateSampleOrderDetail(SampleOrderDetail od) {
		return (Integer) getSqlMapClientTemplate().update("sampleOrder.updateSampleOrderDetail",od);
	}

	@Override
	public int deleteSampleOrderDetail(SampleOrderDetail od) {
		return (Integer) getSqlMapClientTemplate().delete("sampleOrder.deleteSampleOrderDetail",od);
	}
	
	@Override
	public String getSystemIdPrc() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("incount", 6+"");
  		parameter.put("intype", 2+"");
  		parameter.put("RESULTCODE","");

 		this.getSqlMapClientTemplate().queryForList(
				"sampleOrder.getSystemIdPrc", parameter);
 		String message=(String) parameter.get("RESULTCODE");
		return message;
	}

	@Override
	public int deleteSODofMain(SampleOrderDetail sod) {
		return (Integer) getSqlMapClientTemplate().delete("sampleOrder.deleteSODofMain",sod);
	}
	
	
	@Override
	public int importUpdateCount(SampleOrderDetail sod) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sampleOrder.importUpdateCount",sod);
	}
	
	@Override
	public int importUpdate(SampleOrderDetail sod) {
		return (Integer) getSqlMapClientTemplate().update("sampleOrder.importUpdate",sod);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<SampleOrderAndDetail> getSampleOrderAndDetailList(SampleOrder o) {
		return (List<SampleOrderAndDetail>) getSqlMapClientTemplate().queryForList("sampleOrder.getSampleOrderAndDetailList",o);
	}

	@Override
	public int getSampleOrderAndDetailListCount(SampleOrder o) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sampleOrder.getSampleOrderAndDetailListCount",o);
	}

	@Override
	public int getRoleByLoginId(SampleOrder o) {
		return (Integer) getSqlMapClientTemplate().queryForObject("sampleOrder.getRoleByLoginId",o);
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<Disti_branch> getDistiBranchList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiBranchList", db);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Borg> getOrgList(Borg borg) {
        return (List<Borg>) getSqlMapClientTemplate().queryForList("priceRule.getOrgList",borg);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<AccountManager> getAccountManageList(AccountManager manager) {
        return (List<AccountManager>) getSqlMapClientTemplate().queryForList("sampleOrder.getAccountManagers",manager);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRoleForSampleOrder(String userId) {
        return (List<Role>) getSqlMapClientTemplate().queryForList("sampleOrder.getRoleForSampleOrder", userId);
    }
    
    @Override
    public int auditSampleOrder(SampleOrder so) {
        return (Integer) getSqlMapClientTemplate().update("sampleOrder.auditSampleOrder", so);
    }
    
    public int auditSampleOrderDetail(SampleOrderDetail sod) {
        return (Integer) getSqlMapClientTemplate().update("sampleOrder.updateSampleOrderDetailRemark", sod);
    }
}
