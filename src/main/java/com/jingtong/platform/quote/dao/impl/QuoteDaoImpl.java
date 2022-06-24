package com.jingtong.platform.quote.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.quote.dao.IQuoteDao;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public class QuoteDaoImpl extends BaseDaoImpl implements IQuoteDao {
    @Override
    public int getQuoteListCount(Quote o) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getQuoteListCount", o);
    }

    @Override
    public List<Quote> getQuoteList(Quote o) {
        return (List<Quote>) getSqlMapClientTemplate().queryForList("quote.getQuoteList", o);
    }

    @Override
    public Quote getQuoteById(Quote o) {
        return (Quote) getSqlMapClientTemplate().queryForObject("quote.getQuoteById", o);
    }

    @Override
    public QuoteDetail getQuoteDetailById(QuoteDetail qd) {
        return (QuoteDetail) getSqlMapClientTemplate().queryForObject("quote.getQuoteDetailById", qd);
    }

    @Override
    public long createQuote(Quote o) {
        return (Long) getSqlMapClientTemplate().insert("quote.createQuote", o);
    }

    @Override
    public int updateQuote(Quote o) {
        return (Integer) getSqlMapClientTemplate().update("quote.updateQuote", o);
    }

    @Override
    public int setQuoteCode(Quote o) {
        return (Integer) getSqlMapClientTemplate().update("quote.setQuoteCode", o);
    }

    @Override
    public int deleteQuote(Quote o) {
        return (Integer) getSqlMapClientTemplate().delete("quote.deleteQuote", o);
    }

    @Override
    public List<QuoteDetail> getQuoteDetailList(QuoteDetail od) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getQuoteDetailList", od);
    }

    @Override
    public List<QuoteDetail> getQuoteLogList(QuoteDetail od) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getQuoteLogList", od);
    }

    @Override
    public long createQuoteDetail(QuoteDetail od) {
        return (Long) getSqlMapClientTemplate().insert("quote.createQuoteDetail", od);
    }

    @Override
    public long createQuoteLog(QuoteDetail qd) {
        return (Long) getSqlMapClientTemplate().insert("quote.createQuoteLog", qd);
    }

    @Override
    public int updateQuoteDetail(QuoteDetail od) {
        return (Integer) getSqlMapClientTemplate().update("quote.updateQuoteDetail", od);
    }

    @Override
    public int deleteQuoteDetail(QuoteDetail od) {
        return (Integer) getSqlMapClientTemplate().delete("quote.deleteQuoteDetail", od);
    }

    @Override
    public int changeQuoteDetailState(QuoteDetail od) {
        return (Integer) getSqlMapClientTemplate().update("quote.auditQuoteDetail", od);
    }

    @Override
    public String getSystemIdPrc() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("incount", 6 + "");
        parameter.put("intype", 3 + "");
        parameter.put("RESULTCODE", "");

        this.getSqlMapClientTemplate().queryForList("quote.getSystemIdPrc", parameter);
        String message = (String) parameter.get("RESULTCODE");
        return message;
    }

    @Override
    public int getEDIQuoteCount(Quote o) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getEDIQuoteCount", o);
    }

    @Override
    public List<Quote> getEDIQuote(Quote o) {
        return (List<Quote>) getSqlMapClientTemplate().queryForList("quote.getEDIQuote", o);
    }

    @Override
    public int auditQuote(Quote o) {
        return (Integer) getSqlMapClientTemplate().update("quote.auditQuote", o);
    }

    @Override
    public int getAuditQuoteListCount(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getAuditQuoteListCount", qd);
    }

    @Override
    public List<QuoteDetail> getAuditQuoteList(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getAuditQuoteList", qd);
    }

    @Override
    public int getOutPortQuoteListCount(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getOutPortQuoteListCount", qd);
    }

    @Override
    public List<QuoteDetail> getOutPortQuoteList(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getOutPortQuoteList", qd);
    }

    @Override
    public List<QuoteDetail> getAuditQuoteListNoPage(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getAuditQuoteListNoPage", qd);
    }

    @Override
    public List<QuoteDetail> outPutQuote(QuoteDetail e) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.outPutQuote", e);
    }

    @Override
    public List<CustomerUser> getAuditors(CustomerUser cusUser) {
        return (List<CustomerUser>) getSqlMapClientTemplate().queryForList("quote.getAuditors", cusUser);
    }

    @Override
    public String getQuotePCCountryOrg(Quote q) {
        return (String) getSqlMapClientTemplate().queryForObject("quote.getQuotePCCountryOrg", q);
    }

    @Override
    public int agreeQuoteDetail(QuoteDetail qd) {
        return getSqlMapClientTemplate().update("quote.agreeQuoteDetail", qd);
    }

    @Override
    public List<QuoteDetail> checkQuote(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.checkQuote", qd);
    }

    @Override
    public int updateRemark(QuoteDetail qd) {
        return getSqlMapClientTemplate().update("quote.updateRemark", qd);
    }

    @Override
    public int updatePCid(Quote e) {
        return getSqlMapClientTemplate().update("quote.updatePCid", e);
    }

    @Override
    public int updateECid(Quote e) {
        return getSqlMapClientTemplate().update("quote.updateECid", e);
    }

    /**
     * 根据quoteID查找审批销售
     */
    @Override
    public List<CustomerUser> getQuoteAuditSale(Quote o) {
        return (List<CustomerUser>) getSqlMapClientTemplate().queryForList("quote.getQuoteAuditSale", o);
    }

    @Override
    public Double getReginalMin(QuoteDetail qd) {
        Double tDouble = (Double) getSqlMapClientTemplate().queryForObject("quote.getReginalMin", qd);
        ;
        if (tDouble == null) {
            return (double) 0;
        } else {
            return tDouble;
        }

    }

    @Override
    public List<QuoteDetail> getReginalMins() {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getReginalMins");
    }

    @Override
    public Double getCMM(QuoteDetail qd) {
        Double tDouble = (Double) getSqlMapClientTemplate().queryForObject("quote.getCMM", qd);
        if (tDouble == null) {
            return (double) 0;
        } else {
            return tDouble;
        }
    }

    @Override
    public List<QuoteDetail> getCMMs() {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getCMMs");
    }

    @Override
    public int getOutQuotePriceCount(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getOutQuotePriceCount", qd);
    }

    @Override
    public List<QuoteDetail> getOutQuotePrice(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getOutQuotePrice", qd);
    }

    @Override
    public int updateDebitDate(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().update("quote.updateDebitDate", qd);
    }

    @Override
    public int updateQuoteForForward(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().update("quote.updateQuoteForForward", qd);
    }

    @Override
    public int getAuditQuoteListCountForBL(QuoteDetail qd) {
        return (Integer) getSqlMapClientTemplate().queryForObject("quote.getAuditQuoteListCountForBL", qd);
    }

    @Override
    public List<QuoteDetail> getAuditQuoteListForBL(QuoteDetail qd) {
        return (List<QuoteDetail>) getSqlMapClientTemplate().queryForList("quote.getAuditQuoteListForBL", qd);
    }

    @Override
    public List<SaleCountry> searchSaleCountry(SaleCountry c) {
        return getSqlMapClientTemplate().queryForList("quote.searchCountrysForSaler", c);
    }
    
    @Override
    public List<Disti_branch> getDistiAliasList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiAliasByName", db);
    }

    @Override
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiBranchAliasByName", db);
    }
}
