package com.jingtong.platform.sap.service;

import java.util.HashMap;
import java.util.List;

import com.jingtong.platform.sap.pojo.SAPReport;

public interface SAPReportService {

 public List<SAPReport> getFinalReport(SAPReport sapReport);
 
 public HashMap<String, SAPReport> getPromotionSale(SAPReport sapReport);
 
 public List<SAPReport> getPgroupRules(String promoId);
 
 public List<SAPReport> getMvgs();//获取系列
 
 public List<SAPReport> getPromoDetails(String promoId);
 
 public SAPReport getPromotion(String promoId);
 
 public List<SAPReport> getpgRulesByPromoId(String promoId);
 
 public SAPReport getDisplayCost(String promoId);
 public List<SAPReport> getKunnrByProm(String promoId);
}
