package com.jingtong.platform.sap.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public interface SAPService {

	/** 《产品库存可用量》
	 * @param punit String	统计单位：ST或CAR
	 * @param pmatnr String	物料编码
	 * @param pwerks String	工厂编码
	 * @param plgort String	库位编码
	 * @return
	 */
	public ProductStock getMaterialInventoryFromSap(String pmatnr,String pwerks,String plgort);
	
	public void getMaterialInventory();
	
	public CuspayAccdoc getCuspayAccdocFromSAP(CuspayAccdoc doc);
	
	public boolean autoGetCusPlusFromSap();
	
	public boolean getCusPlusFromSap(Date begda,Date endda,String kunnr) ;
	
	
	public List<NoClearMoney> getweiqingMoneyFromSAP(String p_bukrs,String p_gjahr);
	
	/**
	 * 获取汇款凭证
	 * @throws Exception
	 */
	public NoClearMoney getReceiveCode(String belnr) throws Exception;
	
	public long checkExistReceivePay(ReceivePay receivePay);
	
	/**
	 * 保存回款凭证
	 * @param receivePay
	 */
	public long addReceivePay(ReceivePay receivePay) throws Exception;
	
	public int updateCreditByReceive(String kunnrId);
	
	public List<String> getClearMoneyList();

	public void getReceivePayFromSap();

	public void getInitialCredit();

	public void getZSVoucher();

	public void getFSVoucher();
}
