package com.jingtong.platform.sap.pojo;


import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class LoadPoint extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8447562619598131241L;

	private long pointId;
	private String vstel;//装运点编号
	private String vtext;//装运点名称
	private String state="1";
	private Date createDate;
	private String createUser;
	private Date modifyDate;
	private String modifyUser;
	private String pointIds;
	public long getPointId() {
		return pointId;
	}
	public void setPointId(long pointId) {
		this.pointId = pointId;
	}
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	public String getVtext() {
		return vtext;
	}
	public void setVtext(String vtext) {
		this.vtext = vtext;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getPointIds() {
		return pointIds;
	}
	public void setPointIds(String pointIds) {
		this.pointIds = pointIds;
	}
	
}
