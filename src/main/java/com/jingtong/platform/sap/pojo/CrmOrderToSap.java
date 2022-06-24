package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class CrmOrderToSap extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3225945769141484439L;
	private String mandt;//客户端 
	private String id;//CRM ID
	private String auart;//销售凭证类型 
	private String vkorg;//销售组织 
	private String vtweg;//分销渠道
	private String spart;//产品组 
	private String vkbur;//销售部门 
	private String bzirk;//销售地区 
	private String zterm;//付款条件代码
	private String audat;//凭证日期 (接收/发送日期)(要转化成sap识别格式)
	private String bstkd;//客户采购订单编号
	private String kunag;//售达方 
	private String kunnr;//送达方
	private String kunrg;//付款方 
	private String kunre;//收取发票方 
	private String abrvw;//使用标识
	private String pernr;//销售雇员
	private String prsdt;
	private String text;
	private String  orderType;
	private String sapOrderType;
	private String status;
	private String[] ids;//查询条件
	private String augru;//免职原因
	private String vdatu;//交货时间
	 
	
	public String getVdatu() {
		return vdatu;
	}
	public void setVdatu(String vdatu) {
		this.vdatu = vdatu;
	}
	public String getAugru() {
		return augru;
	}
	public void setAugru(String augru) {
		this.augru = augru;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSapOrderType() {
		return sapOrderType;
	}
	public void setSapOrderType(String sapOrderType) {
		this.sapOrderType = sapOrderType;
	}
	 
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getPrsdt() {
		return prsdt;
	}
	public void setPrsdt(String prsdt) {
		this.prsdt = prsdt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMandt() {
		return mandt;
	}
	public void setMandt(String mandt) {
		this.mandt = mandt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuart() {
		return auart;
	}
	public void setAuart(String auart) {
		this.auart = auart;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getVkbur() {
		return vkbur;
	}
	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}
	public String getBzirk() {
		return bzirk;
	}
	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}
	public String getZterm() {
		return zterm;
	}
	public void setZterm(String zterm) {
		this.zterm = zterm;
	}
	public String getAudat() {
		return audat;
	}
	public void setAudat(String audat) {
		this.audat = audat;
	}
	public String getBstkd() {
		return bstkd;
	}
	public void setBstkd(String bstkd) {
		this.bstkd = bstkd;
	}
	public String getKunag() {
		return kunag;
	}
	public void setKunag(String kunag) {
		this.kunag = kunag;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getKunrg() {
		return kunrg;
	}
	public void setKunrg(String kunrg) {
		this.kunrg = kunrg;
	}
	public String getKunre() {
		return kunre;
	}
	public void setKunre(String kunre) {
		this.kunre = kunre;
	}
	public String getAbrvw() {
		return abrvw;
	}
	public void setAbrvw(String abrvw) {
		this.abrvw = abrvw;
	}
	public String getPernr() {
		return pernr;
	}
	public void setPernr(String pernr) {
		this.pernr = pernr;
	}
    
}
