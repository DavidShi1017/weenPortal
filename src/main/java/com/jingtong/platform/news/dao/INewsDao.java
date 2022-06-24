package com.jingtong.platform.news.dao;

import java.util.List;

 import com.jingtong.platform.news.pojo.NewsDetail;
import com.jingtong.platform.news.pojo.NewsFile;
import com.jingtong.platform.news.pojo.NewsRecord;
import com.jingtong.platform.news.pojo.NewsTotal;

public interface INewsDao {

	/**
	 * 获取oa内网新闻
	 * 
	 * @return
	 */
	public List<NewsDetail> getNewsList();

	/**
	 * 获取新闻总表信息
	 * 
	 * @return
	 */
	public List<NewsTotal> getNewsTotalList();

	/**
	 * 获取新闻明细
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal);

	/**
	 * 获取新闻明细
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public NewsDetail getNewsDetail(NewsDetail NewsDetail);

	/**
	 * 更新新闻点击次数
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int updateNewsDetail(NewsDetail NewsDetail);

	/**
	 * 获取新闻明细附件
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public List<NewsFile> getNewsFileList(NewsFile NewsFile);

	/**
	 * 获取父级栏目
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal);

	/**
	 * 插入新闻总表lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public Long createNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * 获取新闻总表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal);

	/**
	 * 获取新闻总表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal);

	/**
	 * 获取新闻明细表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail);

	/**
	 * 获取新闻明细表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail);

	/**
	 * 删除明细
	 * 
	 * @param NewsDetail
	 * @return
	 */
	public int deleteNewsDetail(NewsDetail NewsDetail);

	/**
	 * 获取新闻明细表条数
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal);

	/**
	 * 获取新闻明细表分页
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal);

	/**
	 * 插入新闻明细表lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsDetail(NewsDetail lanNewsDetail);

	/**
	 * 插入新闻明细表lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	public Long createNewsFile(NewsFile newsFilebean);

	/**
	 * 更新新闻总表
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public int updateNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * 获取单个新闻总表
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal);

	/**
	 * 修改新闻明细附件表
	 * 
	 * @param newsFilebean
	 * @return
	 */
	public int updateNewsFile(NewsFile newsFilebean);
	/**
	 * 查询数据库中是否已经存在为图片显示的数据
	 */
	public int getTotalShowCount();
	

	/**
	 * 记录点击记录
	 */
	public Long addRecord(NewsRecord r);
	
	
	/**
	 * 记录浏览时间
	 */
	public void recordScanTime(NewsRecord r);
	
	/**
	 * 查询记录
	 * @param r
	 * @return
	 */
	public List<NewsRecord> searchScanRecord(NewsRecord r);
	/**
	 * 查看排序码是否占用
	 */
	public int blurNewsCode(NewsTotal newsTotal);
	
	 
}
