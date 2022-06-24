package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.QuoteToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;
import com.jingtong.platform.sap.service.QuoteToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class QuoteToSapServiceImpl extends BasicService implements QuoteToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private QuoteToSapDao quoteToSapDao;
    private TransactionTemplate transactionTemplate;

    public QuoteToSapDao getQuoteToSapDao() {
        return quoteToSapDao;
    }

    public void setQuoteToSapDao(QuoteToSapDao quoteToSapDao) {
        this.quoteToSapDao = quoteToSapDao;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public QuoteToSapDao getQuoteDao() {
        return quoteToSapDao;
    }

    public void setQuoteDao(QuoteToSapDao quoteToSapDao) {
        this.quoteToSapDao = quoteToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String quoteToSap(QuoteToSap quote, List<QuoteDetail> quoteDetail, Quote q) {
        // 调用sap接口获取报价单
        String resultMessage = "";

        try {
            // 创建连接
            this.connect("SAP");
            dest = this.getDest("SAP");

            // 链接接口
            JCoFunction function = dest.getRepository().getFunction("ZSDFU009");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("报价单传入SAP/ZSDFU009");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("失败！");
                log.setLogInfo("连接SAP失败!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("连接SAP失败!");
            }

            if (quote != null) {
                // 传入信息（头）
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// 获取结构

                head.setValue("ID", quote.getId());
                head.setValue("QUOTE_ID", quote.getQuoteId());
                head.setValue("TYPE_ID", quote.getQuoteType());
                head.setValue("CUSGROUP_ID", quote.getCustomerGroup());
                head.setValue("CUSTOMER_ID", quote.getCustomer());
                head.setValue("ECGROUP_ID", quote.getEcGroup());
                head.setValue("EC_ID", quote.getEndCustomer());
                head.setValue("TOTAL_AMOUNT", quote.getTotalAmountv());
                head.setValue("CURRENCY_CODE", quote.getCurrency());
                head.setValue("PROJECT_NAME", quote.getProject());
                head.setValue("ISDELIVERY", quote.getAssembly());
                head.setValue("START_DATE", quote.getStartTime());
                head.setValue("LATEST_EXPIRE", quote.getEndTime());
                head.setValue("STATE", quote.getState());
                head.setValue("ENQUIRY_MAINID", quote.getEnquiryMainId());
                head.setValue("SYNC_STATE", quote.getSyncState());
                head.setValue("SYNC_TIME", quote.getSyncTime());
                head.setValue("SYNC_USERID", quote.getSyncUserId());
                head.setValue("SYSNC_EXCEPTION", quote.getSysncException());
                head.setValue("CREATE_TIME", quote.getCreateTime());
                head.setValue("CREATE_USERID", quote.getCreateUserId());
                head.setValue("LATEST_TIME", quote.getLatestTime());
                head.setValue("LATEST_USERID", quote.getLatestUserId());
                head.setValue("ORG_CODE", quote.getOrgCode());

            }
            if (quoteDetail != null) {
                // 传入信息（明细）
                JCoTable item = function.getTableParameterList().getTable("T_ABLE");// 获取表传值
                for (QuoteDetail it : quoteDetail) {
                    item.appendRow(); // 放入表内
                    item.setValue("ID", "11");
                    item.setValue("QUOTE_ID", it.getQuoteId());
                    item.setValue("ROW_NO", it.getRowNo());
                    item.setValue("MATERIAL_ID", it.getMaterialId());
                    item.setValue("DRNUM", it.getDrId());
                    item.setValue("QYT", it.getQty());
                    item.setValue("TARGET_RESALE", it.getTargetResale());
                    item.setValue("TARGET_COST", it.getTargetCost());

                    item.setValue("CUS_PROFITS_PERCENT", it.getMfrMargin());
                    item.setValue("AMOUNT", it.getAmount());
                    item.setValue("SUGGEST_RESALE", it.getSuggestResale());
                    item.setValue("SUGGEST_COST", it.getSuggestCost());
                    item.setValue("PROFITS_PERCENT", it.getCusProfitsPercent());
                    item.setValue("REASON", it.getJustification());
                    item.setValue("COMPETITOR", it.getCompetitor());
                    item.setValue("START_DATE", it.getStartOfProduction());
                    item.setValue("CUS_REMARK", it.getCusRemarks());
                    item.setValue("REMARK", it.getWeenRemarks());
                    item.setValue("STATE", it.getStatus());
                    item.setValue("MAIN_ID", it.getMainId());
                    item.setValue("ENQUIRY_DETAIL_ID", it.getEnquiryDetailId());
                    item.setValue("LATEST_TIME", it.getLatestTime());
                    item.setValue("LATEST_USERID", it.getLatestUserId());

                }
            }
            function.execute(dest);
            // 获取接口结果输出表

            JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
            resultMessage = resultTbl.getString("ZMESG");
            quoteToSapDao.updateQuoteState(q);
            resultMessage = "Success!";

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("报价单传入SAP/ZSDFU009");
            log.setOperateUser("SAP");
            log.setOperateTime(new Date());
            log.setLogDesc("失败！");
            log.setLogInfo(e.getMessage());
            try {
                this.logInfoDao.addLogInfo(log);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return resultMessage + "<br/>";
    }

    @Override
    public List<QuoteDetail> getQuoteDetail(QuoteToSap quote) {
        try {
            return quoteToSapDao.getQuoteDetail(quote);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuoteToSap> getQuoteTotal(QuoteToSap quote) {
        try {
            return quoteToSapDao.getQuoteTotal(quote);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
