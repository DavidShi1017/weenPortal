package com.jingtong.platform.productLevel.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * 产品层次
 * @author yw
 *
 */
public class ProductLevel extends SearchInfo{
	private long id;
	private int level;//层次
	private String product_pLevel;//父项层次
	private String product_level;//产品层次
	private String product_exp;//产品描述
	private int isDeleted;//是否删除
	private int sortId; //排序号
	private int sync_state;//同步状态
	private Date sync_time;//同步时间
	private String sync_userId;//同步用户
	private String sysnc_exception;//同步异常
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//操作时间
	private String latest_userId;//操作人
	private String org_code;//所属组织
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getProduct_pLevel() {
		return product_pLevel;
	}
	public void setProduct_pLevel(String product_pLevel) {
		this.product_pLevel = product_pLevel;
	}
	public String getProduct_level() {
		return product_level;
	}
	public void setProduct_level(String product_level) {
		this.product_level = product_level;
	}
	public String getProduct_exp() {
		return product_exp;
	}
	public void setProduct_exp(String product_exp) {
		this.product_exp = product_exp;
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
