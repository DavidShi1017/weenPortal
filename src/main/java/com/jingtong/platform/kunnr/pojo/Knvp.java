package com.jingtong.platform.kunnr.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 开票方、售达方、送达方、付款方主数据
 * @author mk
 *
 */
public class Knvp extends SearchInfo{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3060951584400554978L;
	private Long id;
	private String  kunnr;
	private String  vkorg;
	private String  vtweg;
	private String  spart;
	private String  parvw;
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
 	
  	private String[] parvws;
  	
  	
  	
	public String[] getParvws() {
		return parvws;
	}
	public void setParvws(String[] parvws) {
		this.parvws = parvws;
	}
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
	 
	 
	
}
