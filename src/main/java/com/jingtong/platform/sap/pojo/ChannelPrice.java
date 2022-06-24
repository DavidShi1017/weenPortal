package com.jingtong.platform.sap.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ChannelPrice extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3494761267120341239L;

	private long id;
	private String vkorg;	//销售组织
	private String vtweg;	//分销渠道
	private String matnr;	//物料号
	private Date datab;		//有效开始日期
	private Date datbi;		//有效截止日期
 	private float kbetr;	//价格
 	private String konwa;	//比率单位
 	private float kpein;		//条件定价单位
	private String kmein;	//条件单位
	private String kfrst;	//批准状态
	private String kunnr;	//客户编码
	private String maktx;	//物料描述
	private String channelPriceType;	//0: 渠道价格；1: 客户价格
	private Date createDate;
	private String createUser;
	private Date modifyDate;
	private String modifyUser; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public Date getDatab() {
		return datab;
	}
	public void setDatab(Date datab) {
		this.datab = datab;
	}
	public Date getDatbi() {
		return datbi;
	}
	public void setDatbi(Date datbi) {
		this.datbi = datbi;
	}
	public float getKbetr() {
		return kbetr;
	}
	public void setKbetr(float kbetr) {
		this.kbetr = kbetr;
	}
	 
	public String getKonwa() {
		return konwa;
	}
	public void setKonwa(String konwa) {
		this.konwa = konwa;
	}
	public float getKpein() {
		return kpein;
	}
	public void setKpein(float kpein) {
		this.kpein = kpein;
	}
	public String getKmein() {
		return kmein;
	}
	public void setKmein(String kmein) {
		this.kmein = kmein;
	}
	public String getKfrst() {
		return kfrst;
	}
	public void setKfrst(String kfrst) {
		this.kfrst = kfrst;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMaktx() {
		return maktx;
	}
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	public String getChannelPriceType() {
		return channelPriceType;
	}
	public void setChannelPriceType(String channelPriceType) {
		this.channelPriceType = channelPriceType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
 	
}
