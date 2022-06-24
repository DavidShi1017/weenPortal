package com.jingtong.platform.quote.dao;

import java.util.List;

import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public interface IQuoteDao {
    /**
     * 获取报价单列表数
     * 
     * @param c
     * @return
     */
    public int getQuoteListCount(Quote o);

    /**
     * 获取报价单信息列表
     * 
     * @param c
     * @return
     */
    public List<Quote> getQuoteList(Quote o);

    /**
     * 获取已审核报价单列表数
     * 
     * @param c
     * @return
     */
    public int getEDIQuoteCount(Quote o);

    /**
     * 获取已审核报价单信息列表
     * 
     * @param c
     * @return
     */
    public List<Quote> getEDIQuote(Quote o);

    /**
     * 根据ID获取报价单信息
     * 
     * @param c
     * @return
     */
    public Quote getQuoteById(Quote o);

    /**
     * 报价单信息新增
     * 
     * @param p
     * @return
     */
    public long createQuote(Quote o);

    /**
     * 修改报价单信息
     * 
     * @param o
     * @return
     */
    public int updateQuote(Quote o);

    /**
     * 删除报价单信息(逻辑删除)
     * 
     * @param o
     * @return
     */
    public int deleteQuote(Quote o);

    public int auditQuote(Quote o);

    /**
     * 获取报价单明细信息列表
     * 
     * @param od
     * @return
     */
    public List<QuoteDetail> getQuoteDetailList(QuoteDetail od);

    /**
     * 报价单明细信息新增
     * 
     * @param od
     * @return
     */
    public long createQuoteDetail(QuoteDetail od);

    /**
     * 修改报价单明细信息
     * 
     * @param od
     * @return
     */
    public int updateQuoteDetail(QuoteDetail od);

    /**
     * 删除报价单明细信息(物理删除)
     * 
     * @param od
     * @return
     */
    public int deleteQuoteDetail(QuoteDetail od);

    /**
     * 获取自动生成单号
     */
    public String getSystemIdPrc();

    public int getAuditQuoteListCount(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteList(QuoteDetail qd);

    int changeQuoteDetailState(QuoteDetail od);

    public int setQuoteCode(Quote o);

    public long createQuoteLog(QuoteDetail od);

    public List<QuoteDetail> getQuoteLogList(QuoteDetail ed);

    public List<QuoteDetail> outPutQuote(QuoteDetail e);

    public List<CustomerUser> getAuditors(CustomerUser cusUser);

    public int getOutPortQuoteListCount(QuoteDetail qd);

    public List<QuoteDetail> getOutPortQuoteList(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteListNoPage(QuoteDetail qd);

    public String getQuotePCCountryOrg(Quote q);

    public int agreeQuoteDetail(QuoteDetail qd);

    public List<QuoteDetail> checkQuote(QuoteDetail qd);

    public int updateRemark(QuoteDetail qd);

    public int updatePCid(Quote e);

    public int updateECid(Quote e);

    public List<CustomerUser> getQuoteAuditSale(Quote o);

    public Double getReginalMin(QuoteDetail qd);
    
    public List<QuoteDetail> getReginalMins();

    public Double getCMM(QuoteDetail qd);
    
    public List<QuoteDetail> getCMMs();

    public int getOutQuotePriceCount(QuoteDetail qd);

    public List<QuoteDetail> getOutQuotePrice(QuoteDetail qd);

    public int updateDebitDate(QuoteDetail qd);

    public int updateQuoteForForward(QuoteDetail qd);

    public int getAuditQuoteListCountForBL(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteListForBL(QuoteDetail qd);

    public QuoteDetail getQuoteDetailById(QuoteDetail qd);

    public List<SaleCountry> searchSaleCountry(SaleCountry c);
    
    public List<Disti_branch> getDistiAliasList(Disti_branch db);
    
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db);
}
