package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.QuoteToSap;
import com.jingtong.platform.sap.pojo.QuoteDetail;


public interface QuoteToSapService {

	
	
	public String quoteToSap(QuoteToSap quote,List<QuoteDetail> quoteDetail,Quote q);
	
	
 	public List<QuoteToSap> getQuoteTotal(QuoteToSap quote);
 	public List<QuoteDetail> getQuoteDetail(QuoteToSap quote);
}
