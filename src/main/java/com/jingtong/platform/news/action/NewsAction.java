package com.jingtong.platform.news.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.FileUtil;
 import com.jingtong.platform.news.pojo.NewsDetail;
import com.jingtong.platform.news.pojo.NewsFile;
import com.jingtong.platform.news.pojo.NewsRecord;
import com.jingtong.platform.news.pojo.NewsTotal;
import com.jingtong.platform.news.service.INewsService;

public class NewsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5009095660964669519L;
	private static final Log logger = LogFactory.getLog(NewsAction.class);
	private List<NewsTotal> lanNewsTotalList = new ArrayList<NewsTotal>();
	private List<NewsDetail> lanNewsDetailList = new ArrayList<NewsDetail>();
	private INewsService newsService;
	private int total;
	private List<NewsTotal> newsTypelist;
	private String detailId;
	private String upload_sign;
	private NewsDetail lanNewsDelailbean;
	private String total_ids;
	private String oaNewsFilePath;
	private String node;
	private String actionName;
	private String newsId;
	private String totalParentId;
	private @Decode
	String totalName;
	private String totalUploadSign;
	private String totalShow;
	private String totalSign;
	private String totalCode;
	private @Decode
	String total_Name;
	private Date create_date;
	private @Decode
	String delail_title;
	private String delail_ids;
	private String total_id;
	private String delailTitle;
	private File[] upload;
	private String[] uploadFileName;
	private @Decode
	String delail_content;
	private String css_flag;
	private String totalId;
	private String filename;
	private String totalParentTotal;
	private String[] fileId;
	private List<NewsFile> lanNewsFileList;
	private @Decode
	boolean totalShow_flag;
	private Long recordId;
	private List<NewsTotal> statList;

	String userName;
	String userId;
	String istrue;
 
	
	/**
	 * 首页公告
	 * 
	 * @return
	 */
	@PermissionSearch
	public String newsIndex() {
		lanNewsTotalList = new ArrayList<NewsTotal>();
		List<NewsDetail> detailList = newsService.getNewsList();
		Map<Long, NewsTotal> map = new HashMap<Long, NewsTotal>();
		for (NewsDetail d : detailList) {
			NewsFile lanNewsFile = new NewsFile();
			lanNewsFile.setDetail_id(d.getDelail_id());
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList.size() > 0) {
				NewsFile NewsFile = lanNewsFileList.get(0);
				d.setNews_file_url(oaNewsFilePath + "/"
						+ NewsFile.getNews_file_url());
			}
			if (map.containsKey(d.getTotal_id())) {
				map.get(d.getTotal_id()).getNewsdet_list().add(d);
			} else {
				NewsTotal total = new NewsTotal();
				total.setTotal_id(d.getTotal_id());
				total.setTotal_name(d.getTotal_name());
				total.setTotal_sign(d.getTotal_sign());
				total.setTotal_show(d.getTotal_show());
				total.setNewsdet_list(new ArrayList<NewsDetail>());
				total.getNewsdet_list().add(d);
				map.put(d.getTotal_id(), total);
				lanNewsTotalList.add(total);
			}
		}
		map = null;
		return "newsIndex";
	}

	
	/**
	 * 获取单个新闻
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getOneNews() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		String conent = null;

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// 获取单个明细
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			lanNewsDelailbean.setClicks_ratio(lanNewsDelailbean
					.getClicks_ratio() + 1);

			// 修改点击次数
			newsService.updateNewsDetail(lanNewsDelailbean);

			// 点击日志
			NewsRecord record = new NewsRecord();
			record.setDetailId(Long.parseLong(detailId));
			record.setUserName(this.getUser().getUserName());
			record.setStartTime(new Date().getTime());
			recordId = newsService.addRecord(record);

			// 获取内容
			if (StringUtils.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaNewsFilePath + "/"
							+ lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}
				if (StringUtils.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);
				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// 获取附件列表
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaNewsFilePath + "/"
							+ NewsFile.getNews_file_url());
				}
				upload_sign = "Y";
			} else {
				upload_sign = "N";
			}
		}
		return "oneNews";
	}

	/**
	 * 记录浏览时间
	 * 
	 * @return
	 */
	@JsonResult(field = "recordId")
	public String recordScanTime() {
		NewsRecord record = new NewsRecord();
		record.setId(recordId);
		record.setEndTime(new Date().getTime());
		newsService.recordScanTime(record);
		return JSON;
	}

	@PermissionSearch
	public String searchScanRecord() {

		return "scan";

	}

	/**
	 * 跳转到更多明细列表
	 * 
	 * @return
	 */
	@PermissionSearch
	public String getSearchNews() {
		return "getSearchNews";
	}

	/**
	 * 跳转明细列表
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsDetailList", include = { "delail_id",
			"delail_title", "detail_date", "delail_operator", "clicks_ratio",
			"css_flag" }, total = "total")
	@PermissionSearch
	public String searchNewsD() {
		NewsTotal NewsTotal = new NewsTotal();
		NewsTotal = this.getSearchInfo(NewsTotal);
		NewsTotal.setStart(getStart());
		NewsTotal.setEnd(getEnd());
		if (StringUtils.isNotEmpty(total_id)) {
			NewsTotal.setTotal_id(Long.valueOf(total_id.replace(" ", "")));
		}
		if (StringUtils.isNotEmpty(total_Name)) {
			NewsTotal.setTotal_name(total_Name);
		}
		total = newsService.getNewsDetailMoreListCount(NewsTotal);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailMoreList(NewsTotal);
		}
		return JSON;
	}

	/**
	 * 跳转到新闻栏目查询及维护
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsTotalList", include = { "total_id",
			"total_parent_id", "total_name", "total_title", "total_date",
			"total_code", "total_show", "total_sign", "total_upload_sign" }, total = "total")
	@PermissionSearch
	public String searchNewsTotalList() {
		NewsTotal newsTotal = new NewsTotal();
		newsTotal = this.getSearchInfo(newsTotal);

		if (StringUtils.isNotEmpty(total_Name)) {
			newsTotal.setTotal_name(total_Name);
		}
		newsTotal.setStart(getStart());
		newsTotal.setEnd(getEnd());
		total = newsService.getNewsTotalJsonCount(newsTotal);
		if (total > 0) {
			lanNewsTotalList = newsService.getNewsTotalJsonList(newsTotal);
		}

		return JSON;
	}

	@JsonResult(field = "lanNewsTotalList", include = { "total_id",
			"total_parent_id", "total_name", "total_title", "total_date",
			"total_code", "total_show", "total_sign", "total_upload_sign" })
	@PermissionSearch
	public String searchNewsTotalListbox() {
		NewsTotal newsTotal = new NewsTotal();
		newsTotal = this.getSearchInfo(newsTotal);

		if (StringUtils.isNotEmpty(total_Name)) {
			newsTotal.setTotal_name(total_Name);
		}
		total = newsService.getNewsTotalJsonCount(newsTotal);
		newsTotal.setStart(getStart());
		newsTotal.setEnd(total);
		List<NewsTotal> newsTotals = new ArrayList<NewsTotal>();
		lanNewsTotalList = new ArrayList<NewsTotal>();
		if (total > 0) {
			newsTotals = newsService.getNewsTotalJsonList(newsTotal);
			for (NewsTotal s : newsTotals) {
				s.setTotal_title(s.getTotal_id() + "," + s.getTotal_show());
				lanNewsTotalList.add(s);
			}
		}

		return JSON;
	}

	/**
	 * 跳转到新闻版块创建
	 * 
	 * @return
	 */
	@PermissionSearch
	public String newsTot_add() {
		int i = newsService.getTotalShowCount();
		if (i == 0) {
			totalShow_flag = true;
		} else {
			totalShow_flag = false;
		}
		return "newsTotAdd";
	}

	/**
	 * 创建总单
	 * 
	 * @return
	 */
	public String createNewsTotal() {
		String result = blurNewsCode();
		if(!result.equals("")){
			this.setFailMessage(result);
			return RESULT_MESSAGE;
		}
		NewsTotal lanNewsTotalbean = new NewsTotal();
		lanNewsTotalbean.setTotal_parent_id(Long.valueOf(0));
		lanNewsTotalbean.setTotal_name(totalName);
		if(totalShow!=null){
			lanNewsTotalbean.setTotal_show(totalShow);
		}else{
			lanNewsTotalbean.setTotal_show("N");
		}
		lanNewsTotalbean.setTotal_flag("Y");
		lanNewsTotalbean.setTotal_upload_sign(totalUploadSign);
			lanNewsTotalbean.setTotal_sign(totalSign);
			lanNewsTotalbean.setTotal_code(totalCode);
		Long totalId = newsService.createNewsTotal(lanNewsTotalbean);
		if (totalId == 0l) {
			this.setFailMessage("创建失败!");
		} else {
			this.setSuccessMessage("创建成功!");
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 只能提示排序码是否占用
	 */
	@PermissionSearch
	public String blurNewsCode(){
		NewsTotal lanNewsTotalbean = new NewsTotal();
		String result="";
		if (StringUtils.isNotEmpty(totalCode)
				&& StringUtils.isNotEmpty(totalCode.trim())) {
			try{
				lanNewsTotalbean.setTotal_code(totalCode);
				int i = newsService.blurNewsCode(lanNewsTotalbean);
				if(i!=0){
					result = "此排序码已经被占用！";
				}
			}catch(Exception e){
				logger.error(e);
			}
		}
		return result;
	}
	/**
	 * 删除新闻总单
	 * 
	 * @return
	 */
	public String deleteNewsTotal() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isNotEmpty(total_ids)) {
			String[] ls = total_ids.split(",");
			for (int i = 0; i < ls.length; i++) {
				NewsDetail det = new NewsDetail();
				det.setTotal_id(Long.valueOf(ls[i]));
				det.setStart(0);
				det.setEnd(10);
				List<NewsDetail> lists = newsService.getNewsDetailJsonList(det);
				if (lists.size()>0) {
					this.setFailMessage("删除失败,栏目为Id:" + ls[i]
							+ "下面有明细信息  请先删除明细信息再删除栏目信息……");
					return RESULT_MESSAGE;
				}
			}
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_ids(ls);
			NewsTotal.setTotal_flag("N");
			if (newsService.updateNewsTotal(NewsTotal) > 0) {
			} else {
				this.setFailMessage("删除失败");
			}
			this.setSuccessMessage("删除成功！ 删除个数:" + ls.length);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 保存新闻栏目明细
	 * 
	 * @return
	 */
	public String createNewsdet() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		lanNewsDelailbean = new NewsDetail();
		AllUsers user = this.getUser();
		NewsFile newsFilebean = new NewsFile();
		String imageFileName = "";

		if (StringUtils.isNotEmpty(delail_content)) {
			File savedir = new File(oaNewsFilePath);
			// 如果目录不存在，则新建
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			String name = DateUtil.getNowDateminStr() + ".txt";
			FileUtil.saveFile(oaNewsFilePath + "/" + name, delail_content,
					false);
			lanNewsDelailbean.setDelail_content(name);
		}

		lanNewsDelailbean.setDelail_title(delailTitle);
		lanNewsDelailbean.setClicks_ratio(0l);
		lanNewsDelailbean.setCss_flag(css_flag);
		lanNewsDelailbean.setDelail_operator(user.getUserName());
		lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
		lanNewsDelailbean.setOrg_name("1");

		Long detailId = newsService.createNewsDetail(lanNewsDelailbean);

		if (0l == detailId) {
			this.setFailMessage("创建新闻明细失败！");
			return RESULT_MESSAGE;
		}

		if (upload != null && upload.length > 0) {
			for (int i = 0; i < upload.length; i++) {
				if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
					imageFileName = UUID.randomUUID()
							+ FileUtil.getFileExtention(uploadFileName[i]);
					File savedir = new File(oaNewsFilePath);
					// 如果目录不存在，则新建
					if (!savedir.exists()) {
						savedir.mkdirs();
					}
					File imageFile = new File(oaNewsFilePath + "/"
							+ imageFileName);
					FileUtil.saveAsFile(upload[i], imageFile);

					newsFilebean.setDetail_id(detailId);
					newsFilebean.setNews_file_name(uploadFileName[i]);
					newsFilebean.setNews_file_url(imageFileName);

					Long news_file_id = newsService
							.createNewsFile(newsFilebean);
					if (0l == news_file_id) {
						this.setFailMessage("创建新闻附件失败！");
						return RESULT_MESSAGE;
					}
				}
			}
		}
		this.setSuccessMessage("创建成功！");

		// 清空cache
		/* removeCache(); */

		return RESULT_MESSAGE;
	}

	/**
	 * 跳转到新闻栏目查询及维护
	 * 
	 * @return
	 */

	@JsonResult(field = "lanNewsDetailList", include = { "delail_id",
			"total_id", "delail_title", "delail_operator", "detail_date",
			"clicks_ratio", "total_name" }, total = "total")
	@PermissionSearch
	public String searchNewsDetailList() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		NewsDetail newsDetail = new NewsDetail();
		/* newsDetail = this.getSearchInfo(newsDetail); */

		// 所属栏目名称
		if (StringUtils.isNotEmpty(total_Name)) {
			newsDetail.setTotal_name(total_Name);
		}

		// 标题
		if (StringUtils.isNotEmpty(delail_title)) {
			newsDetail.setDelail_title(delail_title);
		}
		newsDetail.setStart(getStart());
		newsDetail.setEnd(getEnd());
		total = newsService.getNewsDetailJsonCount(newsDetail);
		if (total > 0) {
			lanNewsDetailList = newsService.getNewsDetailJsonList(newsDetail);
		} else {
			lanNewsDetailList = null;
		}

		return JSON;
	}

	/**
	 * 栏目明细删除
	 * 
	 * @return
	 */
	public String deleteNewsDetail() {
		NewsDetail newsDetail = new NewsDetail();
		if (StringUtils.isNotEmpty(delail_ids)) {
			String[] ls = delail_ids.split(",");
			String[] codes = new String[ls.length];
			for (int i = 0; i < ls.length; i++) {
				if (!ls[i].trim().equals("")) {
					codes[i] = ls[i];
				}
			}
			newsDetail.setCodes(codes);
			if (newsService.deleteNewsDetail(newsDetail) > 0) {
				this.setSuccessMessage("操作成功");
				// 清空cache
				/* removeCache(); */
			} else {
				this.setFailMessage("操作失败");
			}
		} else {
			this.setFailMessage("操作失败");
		}

		return RESULT_MESSAGE;
	}

	/*
	 * private void removeCache() { try {
	 * memcachedCacheService.remove(IMemcachedCacheService.CACHE_KEY_NEWS); }
	 * catch (Exception e) {
	 * 
	 * } }
	 */
	/**
	 * 跳转到栏目明细创建
	 * 
	 * @return
	 */
	@PermissionSearch
	public String newsDetail_add() {
		return "newsDetail_add";
	}

	/**
	 * 跳转到栏目明细创建及维护
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchNewsDetail() {
		return "searchNewsDetail";
	}

	/**
	 * 跳转到栏目查询及维护
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchNewsTotal() {
		return "searchNewsTotal";
	}
     
	
	
	/**
	 * 跳转到修改新闻栏目明细页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateNewsDetailPre() {
		lanNewsDelailbean = new NewsDetail();
		NewsFile lanNewsFile = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String conent = "";

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			lanNewsFile.setDetail_id(Long.valueOf(detailId));

			// 获取单个明细
			lanNewsDelailbean = newsService.getNewsDetail(lanNewsDelailbean);
			filename = lanNewsDelailbean.getDelail_content();
			totalShow = lanNewsDelailbean.getTotal_show();
			delailTitle = lanNewsDelailbean.getDelail_title();
			NewsTotal newsTotal = new NewsTotal();
			newsTotal.setTotal_id(lanNewsDelailbean.getTotal_id());
			newsTotal = newsService.getNewsTotal(newsTotal);
			totalParentTotal = newsTotal.getTotal_id() + ","
					+ newsTotal.getTotal_show();

			// 获取内容
			if (StringUtils.isNotEmpty(lanNewsDelailbean.getDelail_content())) {
				try {
					conent = FileUtil.readFile(oaNewsFilePath + "/"
							+ lanNewsDelailbean.getDelail_content());
				} catch (Exception e) {
					logger.error(e);
				}

				if (StringUtils.isNotEmpty(conent)) {
					lanNewsDelailbean.setDelail_content(conent);

				} else {
					lanNewsDelailbean.setDelail_content("  ");
				}
			}

			// 获取附件列表
			lanNewsFileList = newsService.getNewsFileList(lanNewsFile);
			if (lanNewsFileList != null && lanNewsFileList.size() > 0) {
				for (NewsFile NewsFile : lanNewsFileList) {
					NewsFile.setNews_file_url(oaNewsFilePath + "/"
							+ NewsFile.getNews_file_url());
				}
			}
		}

		return "updateNewsDetailPre";
	}

	/**
	 * 修改新闻栏目明细
	 * 
	 * @return
	 */
	public String updateNewsDetail() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		String result = blurNewsCode();
		if(!result.equals("")){
			this.setFailMessage(result);
			return RESULT_MESSAGE;
		}
		lanNewsDelailbean = new NewsDetail();
		NewsFile newsFilebean = new NewsFile();
		lanNewsFileList = new ArrayList<NewsFile>();
		String imageFileName = "";

		if (StringUtils.isNotEmpty(detailId)) {
			lanNewsDelailbean.setDelail_id(Long.valueOf(detailId));
			// 标题
			lanNewsDelailbean.setDelail_title(delailTitle);
			//lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
			lanNewsDelailbean.setTotal_id(Long.valueOf(totalParentId));
			// 是否标红
			lanNewsDelailbean.setCss_flag(css_flag);
			// 获取内容
			if (StringUtils.isNotEmpty(delail_content)
					&& StringUtils.isNotEmpty(delail_content)) {
				File savedir = new File(oaNewsFilePath);
				// 如果目录不存在，则新建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				if(!FileUtil.saveFile(oaNewsFilePath + "/" + filename,
						delail_content, false)){
					this.setFailMessage("修改明细失败");
					return RESULT_MESSAGE;
				}
			}

			if (newsService.updateNewsDetail(lanNewsDelailbean) <= 0) {// 修改新闻明细
				this.setFailMessage("修改明细失败");
				return RESULT_MESSAGE;
			}
			newsFilebean.setNews_del_flag("N");
			newsFilebean.setDetail_id(Long.valueOf(detailId));
			newsFilebean.setCodes(fileId);

			if (newsService.updateNewsFile(newsFilebean) >= 0) { // 删除附件
				if (upload != null && upload.length > 0) { // 附件处理
					for (int i = 0; i < upload.length; i++) {
						if (uploadFileName[i] != null
								&& uploadFileName[i].length() > 0) {
							imageFileName = UUID.randomUUID()
									+ FileUtil
											.getFileExtention(uploadFileName[i]);
							File savedir = new File(oaNewsFilePath);
							// 如果目录不存在，则新建
							if (!savedir.exists()) {
								savedir.mkdirs();
							}
							File imageFile = new File(oaNewsFilePath + "/"
									+ imageFileName);
							FileUtil.saveAsFile(upload[i], imageFile);

							newsFilebean.setNews_file_name(uploadFileName[i]);
							newsFilebean.setNews_file_url(imageFileName);
							Long news_file_id = newsService
									.createNewsFile(newsFilebean);
							if (0l == news_file_id) {
								this.setFailMessage("创建新闻附件失败！");
								return RESULT_MESSAGE;
							}
						}
					}
				}
			}
		}
		this.setSuccessMessage("修改成功！");

		// 清空cache

		return RESULT_MESSAGE;
	}

	/**
	 * 修改栏目信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateNewsTotalPre() {
		if (StringUtils.isNotEmpty(totalId)) {
			NewsTotal NewsTotal = new NewsTotal();
			NewsTotal.setTotal_id(Long.valueOf(totalId));
			NewsTotal = newsService.getNewsTotal(NewsTotal);
			int i = newsService.getTotalShowCount();
			if (i == 0) {
				totalShow_flag = true;
			} else {
				if (NewsTotal.getTotal_show().equals("Y")) {
					totalShow_flag = true;
				} else {
					totalShow_flag = false;
				}
			}

			if (NewsTotal != null) {
				// 获取栏目名称
				totalName = NewsTotal.getTotal_name();
				totalUploadSign = NewsTotal.getTotal_upload_sign();
				totalShow = NewsTotal.getTotal_show();
				totalSign = NewsTotal.getTotal_sign();
				totalCode = NewsTotal.getTotal_code();

			}
		}
		return "updateNewsTotalPre";
	}

	/**
	 * 修改新闻总单
	 * 
	 * @return
	 */
	public String updateNewsTotal() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		NewsTotal NewsTotal = new NewsTotal();
		String[] ls = totalId.toString().split(",");
		NewsTotal.setTotal_ids(ls);
		NewsTotal.setTotal_code(totalCode);
		NewsTotal.setTotal_name(totalName);
		if(totalShow!=null){
			NewsTotal.setTotal_show(totalShow);
		}else{
			NewsTotal.setTotal_show("N");
		}
		NewsTotal.setTotal_sign(totalSign);
		NewsTotal.setTotal_upload_sign(totalUploadSign);

		if (newsService.updateNewsTotal(NewsTotal) > 0) {
			this.setSuccessMessage("修改成功！");
		} else {
			this.setFailMessage("修改失败");
		}

		return RESULT_MESSAGE;
	}

	public List<NewsTotal> getLanNewsTotalList() {
		return lanNewsTotalList;
	}

	public void setLanNewsTotalList(List<NewsTotal> lanNewsTotalList) {
		this.lanNewsTotalList = lanNewsTotalList;
	}

	public List<NewsDetail> getLanNewsDetailList() {
		return lanNewsDetailList;
	}

	public void setLanNewsDetailList(List<NewsDetail> lanNewsDetailList) {
		this.lanNewsDetailList = lanNewsDetailList;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsTotal> getNewsTypelist() {
		return newsTypelist;
	}

	public void setNewsTypelist(List<NewsTotal> newsTypelist) {
		this.newsTypelist = newsTypelist;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getUpload_sign() {
		return upload_sign;
	}

	public void setUpload_sign(String upload_sign) {
		this.upload_sign = upload_sign;
	}

	public NewsDetail getLanNewsDelailbean() {
		return lanNewsDelailbean;
	}

	public void setLanNewsDelailbean(NewsDetail lanNewsDelailbean) {
		this.lanNewsDelailbean = lanNewsDelailbean;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTotalParentId() {
		return totalParentId;
	}

	public void setTotalParentId(String totalParentId) {
		this.totalParentId = totalParentId;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

	public String getTotalUploadSign() {
		return totalUploadSign;
	}

	public void setTotalUploadSign(String totalUploadSign) {
		this.totalUploadSign = totalUploadSign;
	}

	public String getTotalShow() {
		return totalShow;
	}

	public void setTotalShow(String totalShow) {
		this.totalShow = totalShow;
	}

	public String getTotalSign() {
		return totalSign;
	}

	public void setTotalSign(String totalSign) {
		this.totalSign = totalSign;
	}

	public String getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}

	public String getTotal_Name() {
		return total_Name;
	}

	public void setTotal_Name(String total_Name) {
		this.total_Name = total_Name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getDelail_title() {
		return delail_title;
	}

	public void setDelail_title(String delail_title) {
		this.delail_title = delail_title;
	}

	public String getDelail_ids() {
		return delail_ids;
	}

	public void setDelail_ids(String delail_ids) {
		this.delail_ids = delail_ids;
	}

	public String getTotal_id() {
		return total_id;
	}

	public void setTotal_id(String total_id) {
		this.total_id = total_id;
	}

	public String getDelailTitle() {
		return delailTitle;
	}

	public void setDelailTitle(String delailTitle) {
		this.delailTitle = delailTitle;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDelail_content() {
		return delail_content;
	}

	public void setDelail_content(String delail_content) {
		this.delail_content = delail_content;
	}

	public String getCss_flag() {
		return css_flag;
	}

	public void setCss_flag(String css_flag) {
		this.css_flag = css_flag;
	}

	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String[] getFileId() {
		return fileId;
	}

	public void setFileId(String[] fileId) {
		this.fileId = fileId;
	}

	public String getOaNewsFilePath() {
		return oaNewsFilePath;
	}

	public void setOaNewsFilePath(String oaNewsFilePath) {
		this.oaNewsFilePath = oaNewsFilePath;
	}

	public List<NewsFile> getLanNewsFileList() {
		return lanNewsFileList;
	}

	public void setLanNewsFileList(List<NewsFile> lanNewsFileList) {
		this.lanNewsFileList = lanNewsFileList;
	}

	public String getTotal_ids() {
		return total_ids;
	}

	public void setTotal_ids(String total_ids) {
		this.total_ids = total_ids;
	}

	public boolean isTotalShow_flag() {
		return totalShow_flag;
	}

	public void setTotalShow_flag(boolean totalShow_flag) {
		this.totalShow_flag = totalShow_flag;
	}

	public String getTotalParentTotal() {
		return totalParentTotal;
	}

	public void setTotalParentTotal(String totalParentTotal) {
		this.totalParentTotal = totalParentTotal;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}


	public List<NewsTotal> getStatList() {
		return statList;
	}


	public void setStatList(List<NewsTotal> statList) {
		this.statList = statList;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	 


	public String getIstrue() {
		return istrue;
	}


	public void setIstrue(String istrue) {
		this.istrue = istrue;
	}


	 
	
}
