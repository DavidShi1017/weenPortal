package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class OldProduct extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436018351398266646L;
	private long id;
	private String kunnr;
	private String materialnumber;
	private long oldNum ;//数量
	private long lastnum ;//剩余数量
	private Date begindate;
	private Date enddate;
	private String createuser;
	private Date createdate;
	private String modifyuser;
	private Date modifydate;
	private String status="1";//用于删除标示 3:删除
	private float price;//价格
	 
 
  
	
	
	private String kunnrName;
	private String materialname;
	private String beginDateStr;
	private String endDateStr;
	private String ids;
	private String year;
	
	
	 
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getBeginDateStr() {
		return beginDateStr;
	}
	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMaterialnumber() {
		return materialnumber;
	}
	public void setMaterialnumber(String materialnumber) {
		this.materialnumber = materialnumber;
	}
	 
	 
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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
  
	public long getOldNum() {
		return oldNum;
	}
	public void setOldNum(long oldNum) {
		this.oldNum = oldNum;
	}
	public long getLastnum() {
		return lastnum;
	}
	public void setLastnum(long lastnum) {
		this.lastnum = lastnum;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
 
}
