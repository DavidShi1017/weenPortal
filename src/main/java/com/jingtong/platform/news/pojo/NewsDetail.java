package com.jingtong.platform.news.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class NewsDetail extends SearchInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 明细表id
	 */
	private Long delail_id;

	/**
	 * 总表id
	 */
	private Long total_id;

	/**
	 * 标题
	 */
	private String delail_title;

	/**
	 * 内容
	 */
	private String delail_content;

	/**
	 * 发布人
	 */
	private String delail_operator;

	/**
	 * 发布日期
	 */
	private Date detail_date;

	/**
	 * 点击率
	 */
	private Long clicks_ratio;

	/**
	 * 发布组织
	 */
	private String org_name;

	/**
	 * 删除状态（Y：正常，N：删除）
	 */
	private String delail_flag;

	/**
	 * 修改日期
	 */
	private Date last_modify;

	/**
	 * Y为加了css，N为没加
	 */
	private String css_flag;

	private String total_name;

	private String new_flag;

	/**
	 * 是否为跑马灯状态（Y：是，N：否）
	 */
	private String total_sign;

	/**
	 * 图片展示
	 */
	private String total_show;

	/**
	 * 图片地址
	 */
	private String  news_file_url;


	public String getNews_file_url() {
		return news_file_url;
	}

	public void setNews_file_url(String news_file_url) {
		this.news_file_url = news_file_url;
	}


	public String getTotal_show() {
		return total_show;
	}

	public void setTotal_show(String total_show) {
		this.total_show = total_show;
	}

	public Long getDelail_id() {
		return delail_id;
	}

	public void setDelail_id(Long delail_id) {
		this.delail_id = delail_id;
	}

	public Long getTotal_id() {
		return total_id;
	}

	public void setTotal_id(Long total_id) {
		this.total_id = total_id;
	}

	public String getDelail_title() {
		return delail_title;
	}

	public void setDelail_title(String delail_title) {
		this.delail_title = delail_title;
	}

	public String getDelail_content() {
		return delail_content;
	}

	public void setDelail_content(String delail_content) {
		this.delail_content = delail_content;
	}

	public String getDelail_operator() {
		return delail_operator;
	}

	public void setDelail_operator(String delail_operator) {
		this.delail_operator = delail_operator;
	}

	public Date getDetail_date() {
		return detail_date;
	}

	public void setDetail_date(Date detail_date) {
		this.detail_date = detail_date;
	}

	public Long getClicks_ratio() {
		return clicks_ratio;
	}

	public void setClicks_ratio(Long clicks_ratio) {
		this.clicks_ratio = clicks_ratio;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getDelail_flag() {
		return delail_flag;
	}

	public void setDelail_flag(String delail_flag) {
		this.delail_flag = delail_flag;
	}

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public String getCss_flag() {
		return css_flag;
	}

	public void setCss_flag(String css_flag) {
		this.css_flag = css_flag;
	}

	public String getTotal_name() {
		return total_name;
	}

	public void setTotal_name(String total_name) {
		this.total_name = total_name;
	}

	public String getNew_flag() {
		return new_flag;
	}

	public void setNew_flag(String new_flag) {
		this.new_flag = new_flag;
	}

	public String getTotal_sign() {
		return total_sign;
	}

	public void setTotal_sign(String total_sign) {
		this.total_sign = total_sign;
	}

}
