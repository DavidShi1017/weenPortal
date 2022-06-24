package com.jingtong.platform.sap.dao;

import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.pojo.Warehouses;

public interface SAPDao {

	public List<Warehouses> getWarehouses();
	
	public List<Material> getMaterials();
	
	public int getProductStock(ProductStock ps);
	
	public long insertProductStock(ProductStock ps);
	
	public long updateProductStock(ProductStock ps);
	
	public int getNoClearMoney(NoClearMoney mon);
	
	public long updateNoClearMoney(NoClearMoney mon);
	
	public long insertNoClearMoney(NoClearMoney mon);
	
	public List<NoClearMoney>  getNoClearMoneyList(Map<String,String> params);
	
	public List<ReceivePay> getSTReceivePay(ReceivePay receivePay);
	
	public List<ReceivePay> getSTPayMent(ReceivePay receivePay);
	
	public BooleanResult writeOffPayMent(String pid) ;
	
	/**
	 * 根据凭证号号获取没清凭证
	 * @param belnr
	 * @return
	 * @throws Exception
	 */
	public NoClearMoney getReceiveCode(String belnr) throws Exception;
	
	public long checkExistReceivePay(ReceivePay receivePay);
	
	/**
	 * 保存回款凭证
	 * @param receivePay
	 */
	public long addReceivePay(ReceivePay receivePay)throws Exception;
	
	public List<Accrual> getSTAccrual(Accrual acc);
	
	public List<ReceivePay> getSTAccPayMent(ReceivePay receivePay);
	
	public int updateCreditByReceive(String kunnrId);
	
	public List<String> getClearMoneyList();
}
