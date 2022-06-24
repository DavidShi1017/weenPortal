package com.jingtong.platform.sap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.sap.dao.SAPDao;
import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.pojo.Warehouses;

public class SAPDaoImpl extends BaseDaoImpl implements SAPDao{

	@Override
	public List<Warehouses> getWarehouses() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getWarehouses");
	}

	@Override
	public List<Material> getMaterials() {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getMaterials");
	}

	@Override
	public long insertProductStock(ProductStock ps) {
		// TODO Auto-generated method stub
		return (Long) this.getSqlMapClientTemplate().insert("sapReport.insertProductStock", ps);
	}

	@Override
	public int getProductStock(ProductStock ps) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("sapReport.getProductStock", ps);
	}

	@Override
	public long updateProductStock(ProductStock ps) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("sapReport.updateProductStock", ps);
	}

	@Override
	public int getNoClearMoney(NoClearMoney mon) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("sapReport.getNoClearMoney", mon);
	}

	@Override
	public long updateNoClearMoney(NoClearMoney mon) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("sapReport.updateNoClearMoney", mon);
	}

	@Override
	public long insertNoClearMoney(NoClearMoney mon) {
		// TODO Auto-generated method stub
		return (Long) this.getSqlMapClientTemplate().insert("sapReport.insertNoClearMoney",mon);
	}

	@Override
	public List<NoClearMoney> getNoClearMoneyList(Map<String, String> params) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getNoClearMoneyList", params);
	}
	
	@Override
	public List<ReceivePay> getSTReceivePay(ReceivePay receivePay) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSTReceivePay", receivePay);
	}
	
	@Override
	public List<ReceivePay> getSTPayMent(ReceivePay receivePay) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSTPayMent", receivePay);
	}
	
	@Override
	public List<Accrual> getSTAccrual(Accrual acc){
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSTAccrual",acc);
	}
	
	@Override
	public List<ReceivePay> getSTAccPayMent(ReceivePay receivePay) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("sapReport.getSTAccPayMent", receivePay);
	}
	
	//getshopPromotionPrc
		@Override
		public BooleanResult writeOffPayMent(String pid) {
			BooleanResult bool =new BooleanResult();
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("shopcarId", pid+"");
	  		parameter.put("RESULTS", "");
	  		parameter.put("RESULTMESSAGE", "");

			this.getSqlMapClientTemplate().queryForList(
					"shopCar.writeOffPayMent", parameter);
			String i = (String) parameter.get("RESULTS");
			String message=(String) parameter.get("RESULTMESSAGE");
			if("0".equals(i)){
				bool.setResult(true);
			}else{
				bool.setResult(false);
				bool.setCode(message);
			}
			return bool;
		}

	@Override
	public NoClearMoney getReceiveCode(String belnr) throws Exception {
		// TODO Auto-generated method stub
		return (NoClearMoney)this.getSqlMapClientTemplate().queryForObject("sapReport.getReceiveCode", belnr);
	}
	
	@Override
	public long checkExistReceivePay(ReceivePay receivePay){
		return (Long) this.getSqlMapClientTemplate().queryForObject("sapReport.checkExistReceivePay",receivePay);
	}

	@Override
	public long addReceivePay(ReceivePay receivePay) throws Exception {
		// TODO Auto-generated method stub
		return (Long)this.getSqlMapClientTemplate().insert("sapReport.addReceivePay", receivePay);
	}
	
	
	
	

	@Override
	public int updateCreditByReceive(String kunnrId) {
		return this.getSqlMapClientTemplate().update("sapReport.updateCreditByReceive",kunnrId);
	}
	
	
	
	@Override
	public List<String> getClearMoneyList(){
		return this.getSqlMapClientTemplate().queryForList("sapReport.getClearMoneyList");
	}
}
