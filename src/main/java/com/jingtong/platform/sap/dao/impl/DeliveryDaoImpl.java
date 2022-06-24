package com.jingtong.platform.sap.dao.impl;

  import java.util.HashMap;
import java.util.List;
import java.util.Map;

   import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.sap.dao.ShopDeliveryDao;
import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.ShopDelivery;

@SuppressWarnings("rawtypes")
public class DeliveryDaoImpl extends BaseDaoImpl implements ShopDeliveryDao{

	@Override
	public int getDeliverys(ShopDelivery del) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("delvs.getDeliverys", del);
	}

	@Override
	public int updateDelivery(ShopDelivery del) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("delvs.updateDelivery", del);
	}

	@Override
	public long insertDelivery(ShopDelivery del) {
		// TODO Auto-generated method stub
		return (Long) this.getSqlMapClientTemplate().insert("delvs.insertDelivery", del);
	}
 
	@Override
	public String updateOrderPrc(ShopDelivery del) {
 		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("deliveryId", del.getVbeln()+"");
  		parameter.put("RESULTS", "");
 		this.getSqlMapClientTemplate().queryForList(
				"delvs.updateOrderPrc", parameter);
		 String  i = (String) parameter.get("RESULTS");
		 return i;
 	}

@Override
public void updateinfoPro(Map promap) {
	this.getSqlMapClientTemplate().queryForObject("delvs.updateinfoPro", promap);
	
}

	@Override
	public List<Accrual> getCreditByOrder() {
		return   (List<Accrual>) this.getSqlMapClientTemplate().queryForList("delvs.getCreditByOrder");
	}
	
	@Override
	public List<Accrual> getOnlineCredit() {
		return   (List<Accrual>) this.getSqlMapClientTemplate().queryForList("delvs.getOnlineCredit");
	}

	@Override
	public long insetAccrual(Accrual acc) {
		return (Long) this.getSqlMapClientTemplate().insert("delvs.insetAccrual",acc);
	}
	
	@Override
	public long checkExistAccrual(Accrual acc) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("delvs.checkExistAccrual",acc);
	}
	

	@Override
	public int updateAccrual() {
		return this.getSqlMapClientTemplate().update("delvs.updateAccrual");
 	}
 
 
}
