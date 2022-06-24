package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 *产品库存
 */
public class ProductStock extends SearchInfo{

	
	private static final long serialVersionUID = 8549713737278677795L;

	private long id;
	private String werks;//工厂编码
	private String lgort;//库位编码
	private String matnr;//物料编码
	private String unit;//单位
	private float stocknum;//库存数量
	private float bigmg;//数量(大单位)
	private String bigme;//销售单位(大单位)
	private String createuser;//创建人
	private Date createdate;//创建日期
	private String modifyuser;//修改人
	private Date modifydate;//修改时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
	public String getLgort() {
		return lgort;
	}
	public void setLgort(String lgort) {
		this.lgort = lgort;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getStocknum() {
		return stocknum;
	}
	public void setStocknum(float stocknum) {
		this.stocknum = stocknum;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public float getBigmg() {
		return bigmg;
	}
	public void setBigmg(float bigmg) {
		this.bigmg = bigmg;
	}
	public String getBigme() {
		return bigme;
	}
	public void setBigme(String bigme) {
		this.bigme = bigme;
	}
	@Override
	public String toString() {
		return "ProductStock [werks=" + werks + ", lgort=" + lgort + ", matnr="
				+ matnr + ", unit=" + unit + ", stocknum=" + stocknum
				+ ", bigmg=" + bigmg + ", bigme=" + bigme + "]";
	}
	
}
