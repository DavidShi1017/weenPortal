package com.jingtong.platform.productGroup.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 产品组
 * @author yw
 *
 */
public class ProductGroup extends SearchInfo{
	private long id;
	private String product_groupId;//产品组编码
	private String code_range;//编码范围
	private int isDeleted;//是否删除
	private int sortId; //排序号
	private int sync_state;//同步状态
	private Date sync_time;//同步时间
	private String sync_userId;//同步用户
	private String sysnc_exception;//同步异常
	private String create_userId;//建立用户
	private Date create_time;//建立时间
	private Date latest_time;//操作时间
	private String latest_userId;//操作人
	private String org_code;//所属组织
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProduct_groupId() {
		return product_groupId;
	}
	public void setProduct_groupId(String product_groupId) {
		this.product_groupId = product_groupId;
	}
	public String getCode_range() {
		return code_range;
	}
	public void setCode_range(String code_range) {
		this.code_range = code_range;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
	public int getSync_state() {
		return sync_state;
	}
	public void setSync_state(int sync_state) {
		this.sync_state = sync_state;
	}
	public Date getSync_time() {
		return sync_time;
	}
	public void setSync_time(Date sync_time) {
		this.sync_time = sync_time;
	}
	public String getSync_userId() {
		return sync_userId;
	}
	public void setSync_userId(String sync_userId) {
		this.sync_userId = sync_userId;
	}
	public String getSysnc_exception() {
		return sysnc_exception;
	}
	public void setSysnc_exception(String sysnc_exception) {
		this.sysnc_exception = sysnc_exception;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(Date latest_time) {
		this.latest_time = latest_time;
	}
	public String getLatest_userId() {
		return latest_userId;
	}
	public void setLatest_userId(String latest_userId) {
		this.latest_userId = latest_userId;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	
	
}
