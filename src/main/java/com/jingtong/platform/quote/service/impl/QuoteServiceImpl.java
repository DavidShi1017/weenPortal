package com.jingtong.platform.quote.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.framework.util.DataUtil;
import com.jingtong.platform.quote.dao.IQuoteDao;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.quote.service.IQuoteService;

public class QuoteServiceImpl implements IQuoteService {
    private IQuoteDao quoteDao;
    private TransactionTemplate transactionTemplate;

    @Override
    public int getQuoteListCount(Quote e) {
        try {
            return quoteDao.getQuoteListCount(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Quote> getQuoteList(Quote e) {
        try {
            return quoteDao.getQuoteList(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Quote getQuoteById(Quote e) {
        try {
            return quoteDao.getQuoteById(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
    
    @Override
    public QuoteDetail getQuoteDetailById(QuoteDetail qd) {
        try {
            return quoteDao.getQuoteDetailById(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public BooleanResult createQuote(final Quote e, final List<QuoteDetail> edList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n;
                long m;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    n = quoteDao.createQuote(e);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        String quoteCode = DataUtil.frontCompWithZore("Q", n, 6);
                        e.setId(n);
                        e.setQuote_id(quoteCode);
                        quoteDao.setQuoteCode(e);
                        for (QuoteDetail ed : edList) {
                            ed.setMain_id(n);
                            ed.setQuote_id(quoteCode);
                            m = quoteDao.createQuoteDetail(ed);
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                            ed.setRemark(ed.getCus_remark());
                            ed.setType("DistiCreate");
                            quoteDao.createQuoteLog(ed);
                        }
                        booleanResult.setResult(true);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public BooleanResult updateQuote(final Quote e, final List<QuoteDetail> edList, final QuoteDetail ed) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long n;
                long m;
                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    n = quoteDao.updateQuote(e);
                    if (n <= 0) {
                        booleanResult.setResult(false);
                        booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                        ts.setRollbackOnly();
                        return booleanResult;
                    } else {
                        quoteDao.deleteQuoteDetail(ed);
                        for (QuoteDetail ed : edList) {
                            if (ed.getId() == 0) {
                                m = quoteDao.createQuoteDetail(ed);
                                ed.setType("DistiCreate");
                                ed.setRemark(ed.getCus_remark());
                            } else {
                                m = quoteDao.updateQuoteDetail(ed);
                                ed.setType("DistiUpdate");
                                ed.setRemark(ed.getCus_remark());
                            }
                            if (m <= 0) {
                                booleanResult.setResult(false);
                                booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                                ts.setRollbackOnly();
                                return booleanResult;
                            }
                            quoteDao.createQuoteLog(ed);
                        }
                        booleanResult.setResult(true);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("≤Ÿ◊˜ ß∞‹£°");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public int auditQuote(Quote e) {
        try {
            return quoteDao.auditQuote(e);

        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Override
    public BooleanResult auditQuoteDetail(final Quote e, final List<QuoteDetail> edList) {
        BooleanResult booleanResult = new BooleanResult();
        booleanResult = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus ts) {
                BooleanResult booleanResult = new BooleanResult();
                long m;

                booleanResult.setResult(true);
                try {
                    booleanResult.setResult(false);
                    for (QuoteDetail ed : edList) {
                        m = quoteDao.updateQuoteDetail(ed);
                        if (m <= 0) {
                            booleanResult.setResult(false);
                            booleanResult.setCode("Error !");
                            ts.setRollbackOnly();
                            return booleanResult;
                        }
                        quoteDao.createQuoteLog(ed);
                    }
                    booleanResult.setResult(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    booleanResult.setResult(false);
                    booleanResult.setCode("Error !");
                    ts.setRollbackOnly();
                    return booleanResult;
                }
                return booleanResult;
            }
        });
        return booleanResult;
    }

    @Override
    public int deleteQuote(Quote e) {
        try {
            return quoteDao.deleteQuote(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public int changeQuoteDetailState(QuoteDetail e) {
        try {
            int num = quoteDao.changeQuoteDetailState(e);
            QuoteDetail qd = quoteDao.getQuoteDetailById(e);
            qd.setState(e.getState());
            qd.setLatest_userId(e.getLatest_userId());
            qd.setType(e.getType());
            quoteDao.createQuoteLog(qd);
         
            return num;
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> getQuoteDetailList(QuoteDetail ed) {
        try {
            return quoteDao.getQuoteDetailList(ed);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuoteDetail> getQuoteLogList(QuoteDetail ed) {
        try {
            return quoteDao.getQuoteLogList(ed);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long createQuoteDetail(QuoteDetail ed) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateQuoteDetail(QuoteDetail ed) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteQuoteDetail(QuoteDetail ed) {
        // TODO Auto-generated method stub
        return 0;
    }

    public IQuoteDao getQuoteDao() {
        return quoteDao;
    }

    public void setQuoteDao(IQuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String getSystemIdPrc() {
        try {
            return quoteDao.getSystemIdPrc();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int getEDIQuoteCount(Quote o) {
        try {
            return quoteDao.getEDIQuoteCount(o);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Quote> getEDIQuote(Quote o) {
        try {
            return quoteDao.getEDIQuote(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getAuditQuoteListCount(QuoteDetail e) {
        try {
            return quoteDao.getAuditQuoteListCount(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> getAuditQuoteList(QuoteDetail e) {
        try {
            return quoteDao.getAuditQuoteList(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuoteDetail> getAuditQuoteListNoPage(QuoteDetail e) {
        try {
            return quoteDao.getAuditQuoteListNoPage(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int getOutPortQuoteListCount(QuoteDetail e) {
        try {
            return quoteDao.getOutPortQuoteListCount(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> getOutPortQuoteList(QuoteDetail e) {
        try {
            return quoteDao.getOutPortQuoteList(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuoteDetail> outPutQuote(QuoteDetail e) {
        try {
            return quoteDao.outPutQuote(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerUser> getAuditors(CustomerUser cusUser) {
        try {
            return quoteDao.getAuditors(cusUser);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public String getQuotePCCountryOrg(Quote q) {
        try {
            return quoteDao.getQuotePCCountryOrg(q);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int agreeQuoteDetail(QuoteDetail qd) {
        try {
            return quoteDao.agreeQuoteDetail(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public long createQuoteLog(QuoteDetail qd) {
        try {
            return quoteDao.createQuoteLog(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> checkQuote(QuoteDetail qd) {
        try {
            return quoteDao.checkQuote(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateRemark(QuoteDetail qd) {
        try {
            return quoteDao.updateRemark(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updatePCid(Quote e) {
        try {
            return quoteDao.updatePCid(e);

        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateECid(Quote e) {
        try {
            return quoteDao.updateECid(e);

        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /**
     * ≤È’“…Û≈˙œ˙ €
     */
    @Override
    public List<CustomerUser> getQuoteAuditSale(Quote q) {
        try {
            return quoteDao.getQuoteAuditSale(q);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Double getReginalMin(QuoteDetail qd) {
        try {
            return quoteDao.getReginalMin(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return (double) 0;
        }
    }
    
    public List<QuoteDetail> getReginalMins() {
        try {
            return quoteDao.getReginalMins();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Double getCMM(QuoteDetail qd) {
        try {
            return quoteDao.getCMM(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return (double) 0;
        }
    }
    
    public List<QuoteDetail> getCMMs() {
        try {
            return quoteDao.getCMMs();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
    
    @Override
    public int getOutQuotePriceCount(QuoteDetail qd) {
        try {
            return quoteDao.getOutQuotePriceCount(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> getOutQuotePrice(QuoteDetail qd) {
        try {
            return quoteDao.getOutQuotePrice(qd);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateDebitDate(QuoteDetail qd) {
        try {
            return quoteDao.updateDebitDate(qd);

        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateUserForQuoteForward(QuoteDetail qd) {
        try {
            quoteDao.updateQuoteForForward(qd);
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getAuditQuoteListCountForBL(QuoteDetail e) {
        try {
            return quoteDao.getAuditQuoteListCountForBL(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<QuoteDetail> getAuditQuoteListForBL(QuoteDetail e) {
        try {
            return quoteDao.getAuditQuoteListForBL(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<SaleCountry> searchSaleCountry(String userId) {
        try {
            SaleCountry sc = new SaleCountry();
            sc.setUserId(userId);
            return quoteDao.searchSaleCountry(sc);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Disti_branch getDistAlias(Disti_branch disti) {
        List<Disti_branch> list = quoteDao.getDistiAliasList(disti);
        
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public Disti_branch getDistBranchAlias(Disti_branch distibranch) {
        List<Disti_branch> list = quoteDao.getDistiBranchAliasList(distibranch);
        
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
}
