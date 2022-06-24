package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 报价单接口主表_T_ABLE
 * 
 *
 */
public class QuoteDetail extends SearchInfo{
	private String id;
	private String quoteId;//报价单号
	private String rowNo;//报价单行项目号
	private String materialId;//物料号
	private String drId;//DR编码(DR Number)
	private String qty;//订购数量（QTY） 
	private String targetResale;//目标销售价格（Target Resale） 
	private String targetCost;	//目标进货价格（Target Cost） 
	private String cusProfitsPercent;//客户利润百分比(Disti Margin)
	private String amount;//行项目总价（Value） 
	private String suggestResale;//审批销售价格(Suggest Resale)
	private String suggestCost;// 审批进货价格(Suggest Cost)
	private String mfrMargin;//瑞能利润百分比(Mfr Margin)

	private String justification;//申请原因(Justification)
	private String competitor;//竞争对手(Competitor)
	private String startOfProduction;//投产日期Start of Production
	private String cusRemarks;//客户备注(CusRemarks)
	private String weenRemarks;//审批意见(WeenRemarks)
	private String status;//报价单状态(Status)
	private String mainId;//报价单主表ID
	private String enquiryDetailId;//询价明细表ID
	private String latestTime;//操作时间
	private String latestUserId;//操作用户
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getDrId() {
		return drId;
	}
	public void setDrId(String drId) {
		this.drId = drId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getTargetResale() {
		return targetResale;
	}
	public void setTargetResale(String targetResale) {
		this.targetResale = targetResale;
	}
	public String getTargetCost() {
		return targetCost;
	}
	public void setTargetCost(String targetCost) {
		this.targetCost = targetCost;
	}
	public String getCusProfitsPercent() {
		return cusProfitsPercent;
	}
	public void setCusProfitsPercent(String cusProfitsPercent) {
		this.cusProfitsPercent = cusProfitsPercent;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSuggestResale() {
		return suggestResale;
	}
	public void setSuggestResale(String suggestResale) {
		this.suggestResale = suggestResale;
	}
	public String getSuggestCost() {
		return suggestCost;
	}
	public void setSuggestCost(String suggestCost) {
		this.suggestCost = suggestCost;
	}
	public String getMfrMargin() {
		return mfrMargin;
	}
	public void setMfrMargin(String mfrMargin) {
		this.mfrMargin = mfrMargin;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getStartOfProduction() {
		return startOfProduction;
	}
	public void setStartOfProduction(String startOfProduction) {
		this.startOfProduction = startOfProduction;
	}
	public String getCusRemarks() {
		return cusRemarks;
	}
	public void setCusRemarks(String cusRemarks) {
		this.cusRemarks = cusRemarks;
	}
	public String getWeenRemarks() {
		return weenRemarks;
	}
	public void setWeenRemarks(String weenRemarks) {
		this.weenRemarks = weenRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getEnquiryDetailId() {
		return enquiryDetailId;
	}
	public void setEnquiryDetailId(String enquiryDetailId) {
		this.enquiryDetailId = enquiryDetailId;
	}
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getLatestUserId() {
		return latestUserId;
	}
	public void setLatestUserId(String latestUserId) {
		this.latestUserId = latestUserId;
	}
	
	
	
	
	
}
