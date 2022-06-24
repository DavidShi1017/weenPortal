package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.sap.dao.CrmOrderToSAPDao;
import com.jingtong.platform.sap.pojo.Credit;
import com.jingtong.platform.sap.pojo.CreditLog;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.OldProduct;
import com.jingtong.platform.sap.pojo.PaymentDetailRecords;
import com.jingtong.platform.sap.pojo.ReceivePay;

@SuppressWarnings("rawtypes")
public class CrmOrderToSAPDaoImpl extends BaseDaoImpl implements CrmOrderToSAPDao {

	@Override
	public List<CrmOrderToSap> getCrmOrderTotal(CrmOrderToSap order) {
		return this.getSqlMapClientTemplate().queryForList("shopOrder.getCrmOrderTotal",order);
	}

	@Override
	public List<CrmOrderItem> getCrmOrderItems(CrmOrderToSap order) {
		return this.getSqlMapClientTemplate().queryForList("shopOrder.getCrmOrderItems",order);
	}

	@Override
	public int updateOrderDetail(CrmOrderItem orderId) {
		return this.getSqlMapClientTemplate().update("shopOrder.updateOrderDetail",orderId);
	}

	@Override
	public int updateCreditByOrder(Credit credit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProductStock(CrmOrderItem order) {
		return this.getSqlMapClientTemplate().update("shopOrder.updateProductStock",order);
 	}

	@Override
	public List<Credit> getCreditList(Credit credit) {
		return this.getSqlMapClientTemplate().queryForList("shopOrder.getCreditList",credit);
	}

	@Override
	public int updateCredit(Credit credit) {
		return this.getSqlMapClientTemplate().update("shopOrder.updateCredit",credit);

	}

	@Override
	public long addCreditLog(CreditLog credl) {
		return (Long)this.getSqlMapClientTemplate().insert("shopOrder.addCreditLog",credl);
	}

	@Override
	public int updateOrderStatus(CrmOrderToSap order) {
		return (Integer)this.getSqlMapClientTemplate().update("shopOrder.updateOrderStatus",order);

	}

	@Override
	public int updateOldProductStock(OldProduct oldP) {
		return (Integer)this.getSqlMapClientTemplate().update("shopOrder.updateOldProductStock",oldP);
 	}

	@Override
	public List<ReceivePay> getNewReceivePayId(String kunnrId) {
		return  (List<ReceivePay>)this.getSqlMapClientTemplate().queryForList("shopOrder.getNewReceivePayId",kunnrId);
	}

	@Override
	public int updateReceivePay(ReceivePay receive) {
		return (Integer)this.getSqlMapClientTemplate().update("shopOrder.updateReceivePay",receive);
 	}

	@Override
	public long saveRepaymentRecords(PaymentDetailRecords pay) {
		return (Long)this.getSqlMapClientTemplate().insert("shopOrder.saveRepaymentRecords",pay);
 	}

	@Override
	public List<CrmOrderItem> getDeleteCrmOrder(CrmOrderToSap order) {
		return this.getSqlMapClientTemplate().queryForList("shopOrder.getDeleteCrmOrder",order);
 	}

	@Override
	public List<CrmOrderItem> getUseRepaymentList(CrmOrderItem item) {
		return (List<CrmOrderItem>) this.getSqlMapClientTemplate().queryForList("shopOrder.getUseRepaymentList",item);
	}

	@Override
	public List<PaymentDetailRecords> getPayMentList(
			PaymentDetailRecords payment) {
		return (List<PaymentDetailRecords>) this.getSqlMapClientTemplate().queryForList("shopOrder.getPayMentList",payment);
 	}

	@Override
	public String getLimitedNumByOrder(CrmOrderItem item) {
		return (String)this.getSqlMapClientTemplate().queryForObject("shopOrder.getLimitedNumByOrder",item);
	}

	@Override
	public int updateLimitedNumByOrder(CrmOrderItem item) {
		return  this.getSqlMapClientTemplate().update("shopOrder.updateLimitedNumByOrder",item);
 	}

	@Override
	public int updateOrderDetailStatus(CrmOrderItem item) {
		return  this.getSqlMapClientTemplate().update("shopOrder.updateOrderDetailStatus",item);
 	}

}
