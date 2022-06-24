package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.order.pojo.StarderOrder;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;



public interface QuoteToSapDao {
	
	
	public List<QuoteToSap> getQuoteTotal(QuoteToSap quote);
 	public List<QuoteDetail> getQuoteDetail(QuoteToSap quote);
 	public int updateQuoteDetail(QuoteDetail quoteDetail);
 	public int updateQuoteState(Quote q);

 	
 	
}
