package com.jingtong.platform.kunnr.pojo;

/**
 * 经销商其他详细数据
 * @author xxping
 *
 */
import com.jingtong.platform.base.pojo.SearchInfo;

public class KunnrBusiness extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -147542482064622932L;
	private Long id;
	private String kunnr;
	private String categories;// 品项
	
	private String businessManager;   //业务经理
	private String managerMobile;     //业务经理手机
	private String businessCompetent;   //业务主管
	private String competentMobile;     //业务主管手机
	
	/*
	 * add  20130817
	 * ljwang
	 * start
	 */
	private String businessManagerId;   //业务经理
	private String businessCompetentId;   //业务主管
	private String kunnrLeader;      //经销商负责人
	private String kunnrPhone ;      //经销商联系人电话
	
	//end
	
	private String werks;// 工厂
	private Double lastyearSales;
	private Double theyearSales;
	private String coverArea;
	private String akont;// 统驭科目
	private String zterm;// 付款方式
	private String waers;// 货币
	private String kalks;// 定价过程
	private String versg;// 客戶统计组
	private String vsbed;// 装运条件
	private String podkz;// POD相关
	private String autlf;// 完全交货
	private String kztlf;// 每个项目的交货
	private String antlf;// 最大部分交货
	private String bokre;// 回扣相关
	private String ktgrd;// 账户分配组
	private String taxkd01;// 税分类
	private String titleMedi;
	private String country;
	private String langu;
	private String bzirk;// 省
	private String vkbur;// 部
	private String vkgrp;// 大区
	private String lprio;// 交货优先权
	private String accessFile;// 评估报告
	private String accessFilePath;
	private String customerFile;// 潜在客户
	private String customerFilePath;
	
	private String kukla;
	
	
	
	private String kvgr2;  //区域
	private String kvgr1;  //城市

	public String getAccessFile() {
		return accessFile;
	}

	public void setAccessFile(String accessFile) {
		this.accessFile = accessFile;
	}

	public String getAccessFilePath() {
		return accessFilePath;
	}

	public void setAccessFilePath(String accessFilePath) {
		this.accessFilePath = accessFilePath;
	}

	public String getCustomerFile() {
		return customerFile;
	}

	public void setCustomerFile(String customerFile) {
		this.customerFile = customerFile;
	}

	public String getCustomerFilePath() {
		return customerFilePath;
	}

	public void setCustomerFilePath(String customerFilePath) {
		this.customerFilePath = customerFilePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getBusinessCompetent() {
		return businessCompetent;
	}

	public void setBusinessCompetent(String businessCompetent) {
		this.businessCompetent = businessCompetent;
	}

	public String getCompetentMobile() {
		return competentMobile;
	}

	public void setCompetentMobile(String competentMobile) {
		this.competentMobile = competentMobile;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public Double getLastyearSales() {
		return lastyearSales;
	}

	public void setLastyearSales(Double lastyearSales) {
		this.lastyearSales = lastyearSales;
	}

	public Double getThheyearSales() {
		return theyearSales;
	}

	public void setThheyearSales(Double theyearSales) {
		this.theyearSales = theyearSales;
	}

	public String getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}

	public String getAkont() {
		return akont;
	}

	public void setAkont(String akont) {
		this.akont = akont;
	}

	public String getZterm() {
		return zterm;
	}

	public void setZterm(String zterm) {
		this.zterm = zterm;
	}

	public String getWaers() {
		return waers;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public String getKalks() {
		return kalks;
	}

	public void setKalks(String kalks) {
		this.kalks = kalks;
	}

	public String getVersg() {
		return versg;
	}

	public void setVersg(String versg) {
		this.versg = versg;
	}

	public String getVsbed() {
		return vsbed;
	}

	public void setVsbed(String vsbed) {
		this.vsbed = vsbed;
	}

	public String getPodkz() {
		return podkz;
	}

	public void setPodkz(String podkz) {
		this.podkz = podkz;
	}

	public String getAutlf() {
		return autlf;
	}

	public void setAutlf(String autlf) {
		this.autlf = autlf;
	}

	public String getKztlf() {
		return kztlf;
	}

	public void setKztlf(String kztlf) {
		this.kztlf = kztlf;
	}

	public String getAntlf() {
		return antlf;
	}

	public void setAntlf(String antlf) {
		this.antlf = antlf;
	}

	public String getBokre() {
		return bokre;
	}

	public void setBokre(String bokre) {
		this.bokre = bokre;
	}

	public String getKtgrd() {
		return ktgrd;
	}

	public void setKtgrd(String ktgrd) {
		this.ktgrd = ktgrd;
	}

	public String getTaxkd01() {
		return taxkd01;
	}

	public void setTaxkd01(String taxkd01) {
		this.taxkd01 = taxkd01;
	}

	public String getTitleMedi() {
		return titleMedi;
	}

	public void setTitleMedi(String titleMedi) {
		this.titleMedi = titleMedi;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLangu() {
		return langu;
	}

	public void setLangu(String langu) {
		this.langu = langu;
	}

	public String getBzirk() {
		return bzirk;
	}

	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getVkgrp() {
		return vkgrp;
	}

	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}


	public String getLprio() {
		return lprio;
	}

	public void setLprio(String lprio) {
		this.lprio = lprio;
	}

	public Double getTheyearSales() {
		return theyearSales;
	}

	public void setTheyearSales(Double theyearSales) {
		this.theyearSales = theyearSales;
	}

	public String getBusinessManagerId() {
		return businessManagerId;
	}

	public void setBusinessManagerId(String businessManagerId) {
		this.businessManagerId = businessManagerId;
	}

	public String getBusinessCompetentId() {
		return businessCompetentId;
	}

	public void setBusinessCompetentId(String businessCompetentId) {
		this.businessCompetentId = businessCompetentId;
	}

	public String getKunnrLeader() {
		return kunnrLeader;
	}

	public void setKunnrLeader(String kunnrLeader) {
		this.kunnrLeader = kunnrLeader;
	}

	public String getKunnrPhone() {
		return kunnrPhone;
	}

	public void setKunnrPhone(String kunnrPhone) {
		this.kunnrPhone = kunnrPhone;
	}

	public String getKvgr2() {
		return kvgr2;
	}

	public void setKvgr2(String kvgr2) {
		this.kvgr2 = kvgr2;
	}

	public String getKvgr1() {
		return kvgr1;
	}

	public void setKvgr1(String kvgr1) {
		this.kvgr1 = kvgr1;
	}

	public String getKukla() {
		return kukla;
	}

	public void setKukla(String kukla) {
		this.kukla = kukla;
	}

	
	

}
