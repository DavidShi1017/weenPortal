package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.dao.QuoteToSapDao;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.Exclude;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;

@SuppressWarnings("rawtypes")
public class QuoteToSapDaoImpl extends BaseDaoImpl implements QuoteToSapDao{

	@Override
	public  List<QuoteToSap> getQuoteTotal(QuoteToSap quote) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getQuoteTotal",quote);
	}

	@Override
	public  List<QuoteDetail> getQuoteDetail(QuoteToSap quote) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getQuoteDetail",quote);
	}

	@Override
	public int updateQuoteDetail(QuoteDetail quoteId) {
		return this.getSqlMapClientTemplate().update("sapReport.updateQuoteDetail",quoteId);
	}



	@Override
	public int updateQuoteState(Quote q) {
		return this.getSqlMapClientTemplate().update("sapReport.updateQuoteState",q);
	}
	
	
	
}
