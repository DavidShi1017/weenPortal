package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ModeAttribute extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085077756424755887L;

	private long mprAttId;	//	主键	NUMBER
	private String mprModelId;	//	模板ID	VARCHAR2(20)
	private String mprAttCode;	//	模板属性CODE	VARCHAR2(30)
	private String mprAttContent;	//	模板属性名称	VARCHAR2(50)
	private String mprAttDataType;	//	数据类型	VARCHAR2(20)
	private int mprAttDataLength;	//	数据长度	NUMBER
	private String 	mprAttDescript;	//	模板属性描述	VARCHAR2(200)
	private String 	mprAttIsnull;	//	是否可为空	VARCHAR2(2)
 	private String mprAttDataRange;	//	数据范围SQL	VARCHAR2(100)
 	private String mprAttIscumulative;	//	列数据是否需要累加	VARCHAR2(2)
 	private int orderBy;	//	排序字段	NUMBER(10)
 	private String mprAttKey;	//	字段类型(MONEY为计划金额标记)	VARCHAR2(20)
	public long getMprAttId() {
		return mprAttId;
	}
	public void setMprAttId(long mprAttId) {
		this.mprAttId = mprAttId;
	}
	public String getMprModelId() {
		return mprModelId;
	}
	public void setMprModelId(String mprModelId) {
		this.mprModelId = mprModelId;
	}
	public String getMprAttCode() {
		return mprAttCode;
	}
	public void setMprAttCode(String mprAttCode) {
		this.mprAttCode = mprAttCode;
	}
	public String getMprAttContent() {
		return mprAttContent;
	}
	public void setMprAttContent(String mprAttContent) {
		this.mprAttContent = mprAttContent;
	}
	public String getMprAttDataType() {
		return mprAttDataType;
	}
	public void setMprAttDataType(String mprAttDataType) {
		this.mprAttDataType = mprAttDataType;
	}
	public int getMprAttDataLength() {
		return mprAttDataLength;
	}
	public void setMprAttDataLength(int mprAttDataLength) {
		this.mprAttDataLength = mprAttDataLength;
	}
	public String getMprAttDescript() {
		return mprAttDescript;
	}
	public void setMprAttDescript(String mprAttDescript) {
		this.mprAttDescript = mprAttDescript;
	}
	public String getMprAttIsnull() {
		return mprAttIsnull;
	}
	public void setMprAttIsnull(String mprAttIsnull) {
		this.mprAttIsnull = mprAttIsnull;
	}
	public String getMprAttDataRange() {
		return mprAttDataRange;
	}
	public void setMprAttDataRange(String mprAttDataRange) {
		this.mprAttDataRange = mprAttDataRange;
	}
	public String getMprAttIscumulative() {
		return mprAttIscumulative;
	}
	public void setMprAttIscumulative(String mprAttIscumulative) {
		this.mprAttIscumulative = mprAttIscumulative;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getMprAttKey() {
		return mprAttKey;
	}
	public void setMprAttKey(String mprAttKey) {
		this.mprAttKey = mprAttKey;
	}
 	
}
