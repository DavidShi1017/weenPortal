package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 开票方、售达方、送达方、付款方主数据
 * @author ljf
 *
 */
public class Knvp extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 988966998660765615L;
	//传参属性
	private String skunnr="";
	private String skunn2="";
	private String svkorg="";
	private String svtweg="";
	private String sParvw="";
	private String sdefpa="";
	
	
	//返回属性
	private Long id;
	private String  kunnr;
	private String  vkorg;//销售组织
	private String  vtweg;//分销渠道
	private String  spart;//产品组(品类)
	private String  parvw;//合作伙伴功能  客户类型（PY付款方；SH送达方；SP售达方）
	private String  parza;
	private String  kunn2;
	private String  lifnr;
	private String  pernr;
	private String  parnr;
	private String  knref;
	private String  defpa;
	private String  street1;
	 
	 
	 
 
	private String name1;
  	private String[] kunnrs;
 	
  	
  	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String[] getKunnrs() {
		return kunnrs;
	}
	public void setKunnrs(String[] kunnrs) {
		this.kunnrs = kunnrs;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
 	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
 	public String getSkunnr() {
		return skunnr;
	}
	public void setSkunnr(String skunnr) {
		this.skunnr = skunnr;
	}
	public String getSkunn2() {
		return skunn2;
	}
	public void setSkunn2(String skunn2) {
		this.skunn2 = skunn2;
	}
	public String getSvkorg() {
		return svkorg;
	}
	public void setSvkorg(String svkorg) {
		this.svkorg = svkorg;
	}
	public String getSvtweg() {
		return svtweg;
	}
	public void setSvtweg(String svtweg) {
		this.svtweg = svtweg;
	}
	public String getsParvw() {
		return sParvw;
	}
	public void setsParvw(String sParvw) {
		this.sParvw = sParvw;
	}
	public String getSdefpa() {
		return sdefpa;
	}
	public void setSdefpa(String sdefpa) {
		this.sdefpa = sdefpa;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
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
	public String getParvw() {
		return parvw;
	}
	public void setParvw(String parvw) {
		this.parvw = parvw;
	}
	public String getParza() {
		return parza;
	}
	public void setParza(String parza) {
		this.parza = parza;
	}
	public String getKunn2() {
		return kunn2;
	}
	public void setKunn2(String kunn2) {
		this.kunn2 = kunn2;
	}
	public String getLifnr() {
		return lifnr;
	}
	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}
	public String getPernr() {
		return pernr;
	}
	public void setPernr(String pernr) {
		this.pernr = pernr;
	}
	public String getParnr() {
		return parnr;
	}
	public void setParnr(String parnr) {
		this.parnr = parnr;
	}
	public String getKnref() {
		return knref;
	}
	public void setKnref(String knref) {
		this.knref = knref;
	}
	public String getDefpa() {
		return defpa;
	}
	public void setDefpa(String defpa) {
		this.defpa = defpa;
	}
	@Override
	public String toString() {
		return "Knvp [skunnr=" + skunnr + ", skunn2=" + skunn2 + ", svkorg="
				+ svkorg + ", svtweg=" + svtweg + ", sParvw=" + sParvw
				+ ", sdefpa=" + sdefpa + ", kunnr=" + kunnr + ", vkorg="
				+ vkorg + ", vtweg=" + vtweg + ", spart=" + spart + ", parvw="
				+ parvw + ", parza=" + parza + ", kunn2=" + kunn2 + ", lifnr="
				+ lifnr + ", pernr=" + pernr + ", parnr=" + parnr + ", knref="
				+ knref + ", defpa=" + defpa + ", street1=" + street1 + "]";
	}
	 
	
}
