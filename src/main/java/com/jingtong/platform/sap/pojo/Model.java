package com.jingtong.platform.sap.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Model extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3418815164483012135L;
	
	private long mprModelId;	//	主键	NUMBER
	private String mprModelName;	//	模版名称	VARCHAR2(50)
	private String mprModelDescript;	//	模版描述	VARCHAR2(200)
	private Date createDate;		//创建时间
	private long createUser;		//创建人
	private Date modifyDate;		//最后修改时间
	private long modifyUser;		//修改人
	private String mprModelFlag;	//	状态	VARCHAR2(2)
	public long getMprModelId() {
		return mprModelId;
	}
	public void setMprModelId(long mprModelId) {
		this.mprModelId = mprModelId;
	}
	public String getMprModelName() {
		return mprModelName;
	}
	public void setMprModelName(String mprModelName) {
		this.mprModelName = mprModelName;
	}
	public String getMprModelDescript() {
		return mprModelDescript;
	}
	public void setMprModelDescript(String mprModelDescript) {
		this.mprModelDescript = mprModelDescript;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getMprModelFlag() {
		return mprModelFlag;
	}
	public void setMprModelFlag(String mprModelFlag) {
		this.mprModelFlag = mprModelFlag;
	}
	 
}
