package com.jingtong.platform.boform.action;

import java.util.ArrayList;
import java.util.List;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.boform.pojo.QueryParameter;
import com.jingtong.platform.boform.pojo.ReportParameter;
import com.jingtong.platform.boform.service.IBoformService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.org.service.IOrgService;

//import com.alibaba.common.lang.StringUtil;

import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.framework.ISessionMgr;


/**
 * BO报表中心
 * 
 * @author 
 * 
 */
public class BoformAction extends BaseAction {

	private static final Log logger = LogFactory.getLog(BoformAction.class);

	private static final long serialVersionUID = 9170817842048136464L;

	private IBoformService boformService;

	//private ICustService custService;

	//private IMemcachedCacheService memcachedCacheService;

	private int total;

	private List<ReportParameter> reportParameterList;

	private String bid;

	private String pid;

	private ReportParameter reportParameter;

	private String bo3url;

	private String bo3use;

	private String bo3pwd;

	private String bo3dev;

	private String bo3enterp;

	private String bo4url;

	private String bo4use;

	private String bo4pwd;

	private String bo4dev;

	private String bo4enterp;

	private int year;

	private List<Integer> yearList;

	private String custName;

	private String custId = "0";

	private List<QueryParameter> queryParameterList;

	private String custNF;

	private String custYY;

	private String custSP;

	/**
	 * 报表类型：水晶易表
	 */
	private String reportType;

	private IOrgService orgService;

	/**
	 * 区别bo3/bo4
	 */
	private String boType;

	/**
	 * bo报表跳转地址
	 */
	private String url;

	@Decode
	private String tableName;

	@Decode
	private String zdid;

	@Decode
	private String txt;

	@Decode
	private String zdtxt;

	@Decode
	private String d;
	@Decode
	private String search;

	public static final String BO_TYPE_BO3 = "BO3";
	public static final String BO_TYPE_IREPORT="ireport";
	@PermissionSearch
	public String searchReportParameter() {
		return "searchReportParameter";
	}

	@PermissionSearch
	@JsonResult(field = "reportParameterList", include = { "pid", "bid",
			"tableName", "zdid", "zdtxt", "memo", "amount", "txt", "che", "d",
			"nickname", "checkWay" }, total = "total")
	public String getReportParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r.setStart(this.getStart());
		r.setEnd(this.getEnd());

		try {
			if (StringUtils.isNotEmpty(bid) && StringUtils.isNotEmpty(bid.trim())) {
				r.setBid(Long.parseLong(bid.trim()));
			}
		} catch (Exception e) {
			logger.error("bid:" + bid, e);
		}

		total = boformService.getReportParameterCount(r);
		if (total != 0) {
			reportParameterList = boformService.getReportParameterList(r);
		}

		return JSON;
	}

	/**
	 * validate
	 * 
	 * @param reportParameter
	 * @return
	 */
	private boolean validate(ReportParameter reportParameter) {

		if (reportParameter == null) {
			return false;
		}

		return true;
	}

	/**
	 * validate
	 * 
	 * @param reportParameterList
	 * @return
	 */
	private boolean validate(List<ReportParameter> reportParameterList) {

		if (reportParameterList == null) {
			return false;
		}

		return true;
	}

	@PermissionSearch
	public String createReportParameterPrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * 创建ReportParameter
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createReportParameter() {
	
		if (!validate(reportParameterList)) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		StringResult result = boformService
				.createBatchReportParameter(reportParameterList);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		// removeCache
		if (IBoformService.SUCCESS.equals(result.getCode())) {
			String[] bids = new String[reportParameterList.size()];
			int i = 0;
			for (ReportParameter reportParameter : reportParameterList) {
				bids[i++] = reportParameter.getBid().toString();
			}
			removeCache(bids);
			this.setSuccessMessage("操作成功！");
		}

		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String updateReportParameterPrepare() {

		if (StringUtils.isNotEmpty(pid) && StringUtils.isNotEmpty(pid.trim())) {
			try {
				reportParameter = boformService.getReportParameterByPid(Long
						.parseLong(pid.trim()));
			} catch (Exception e) {
				logger.error(pid, e);
				reportParameter = new ReportParameter();
			}
		}

		return UPDATE_PREPARE;
	}

	/**
	 * 修改ReportParameter
	 * 
	 * @return
	 */
	public String updateReportParameter() {

		if (!validate(reportParameter)) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		StringResult result = boformService
				.updateReportParameter(reportParameter);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		// removeCache
		if (IBoformService.SUCCESS.equals(result.getCode())) {
			String[] bids = new String[1];
			bids[0] = reportParameter.getBid().toString();
			removeCache(bids);
			this.setSuccessMessage("操作成功！");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除ReportParameter
	 * 
	 * @return
	 */
	public String deleteReportParameter() {

		String[] l = new String[reportParameterList.size()];
		String[] bids = new String[reportParameterList.size()];

		int i = 0;
		ReportParameter reportParameter = new ReportParameter();

		try {
			for (ReportParameter r : reportParameterList) {
				if (r.getPid() != null) {
					l[i] = r.getPid().toString();
					bids[i] = r.getBid().toString();
					i++;
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(reportParameterList), e);
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		// 无有效的BO报表参数id
		if (i == 0) {
			this.setFailMessage(IBoformService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		reportParameter.setCodes(l);
		StringResult result = boformService
				.deleteReportParameter(reportParameter);

		if (IBoformService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("已成功删除" + result.getResult() + "个BO报表参数！");

			// removeCache
			removeCache(bids);
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 跳转bo报表查看页面
	 * 
	 * @return
	 */
	@PermissionSearch
	@SuppressWarnings("unchecked")
	public String showBoRptPrepare() {
		if (StringUtils.isNotEmpty(bid) && StringUtils.isNotEmpty(bid.trim())) {
			try {

				List<ReportParameter> o = null;
				reportParameterList = (o == null || o.size() == 0) ? boformService
						.getReportParametersByBid(Long.parseLong(bid.trim()))
						: o;

				if (reportParameterList != null
						&& reportParameterList.size() != 0) {
					total = reportParameterList.size();
				}
			} catch (Exception e) {
				logger.error(bid, e);
			}
		}

		// 参数选择年份
		year = DateUtil.getYear();
		yearList = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			yearList.add(year - 3 + i);
		}

		return "showBoRptPrepare";
	}

	/**
	 * 打开bo报表
	 * 
	 * @return
	 */
	@PermissionSearch
	public String showBoRpt() {
		if(boType.equals(BO_TYPE_IREPORT)){
			StringBuffer n =new StringBuffer("/basisPlatform/IreportAction!toReport.jspa?");
			n.append("bid="+bid);
			String s = null;
			String nickname = null;
			StringBuffer parameterUrl=new StringBuffer();
			for (ReportParameter r : reportParameterList) {
				s = r.getMemo();
				nickname = r.getNickname();
				parameterUrl.append(nickname+":"+s+",");
			}
			n.append("&&parameters="+"'"+parameterUrl+"'");
			url = n.toString();
	        System.out.println("ireportURl"+url);
			return "showBoRpt";
		}else {
			StringBuffer n = new StringBuffer(BO_TYPE_BO3.equals(boType) ? bo3url
					: bo4url);
			n.append("?sWindow=same&iDocID=").append(bid).append("&token=")
					.append(sso(boType));

			boolean f1 = true;
			boolean f2 = true;
			String s = null;
			String nickname = null;
			int amount;

			for (ReportParameter r : reportParameterList) {
				s = r.getMemo();

				// 20 多值一页显示n条记录
				// 修改 amount = 2 多值
				amount = r.getAmount() == 20 ? 2 : r.getAmount();
				nickname = r.getNickname();

				if ("1".equals(reportType) && f1) {
					n.append("&lsS").append(nickname).append("=");
					f1 = false;
				}

				if (StringUtils.isNotEmpty(s)) {
					if ("1".equals(reportType)) {
						if (f2) {
							f2 = false;
						} else {
							n.append(",");
						}
						n.append(s);
					} else {
						if (amount == 7 || amount == 1 || amount == 3
								|| amount == 5 || amount == 6) {
							n.append("&lsS");
						} else if (amount == 2 || amount == 4 || amount == 0) {    //手动填、组织树、多选
							n.append("&lsM");
						}
						nickname=nickname.replace(" ", "%20");
						s=s.replace(" ", "%20");
						n.append(nickname).append("=").append(s);
					}
				}
			}

			url = n.toString();
	        System.out.println(url);
			return "showBoRpt";
		}
	
	}

	/**
	 * BO报表 特殊查询条件(物料、工厂...)
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchQueryParameter() {
		// 根据pid查询 tableName, zdid, txt, zdtxt, d
		if (StringUtils.isNotEmpty(pid) && StringUtils.isNotEmpty(pid.trim())) {
			try {
				reportParameter = boformService.getReportParameterByPid(Long
						.parseLong(pid.trim()));

				// 20 多值一页显示n条记录
				// 修改 amount = 2 多值
				if (reportParameter != null
						&& reportParameter.getAmount() == 20) {
					this.setRows(10);
				}
			} catch (Exception e) {
				logger.error(pid, e);
			}
		}

		return "searchQueryParameter";
	}

	@PermissionSearch
	@JsonResult(field = "queryParameterList", include = { "id", "text" }, total = "total")
	public String getQueryParameterJsonList() {
		ReportParameter r = new ReportParameter();
		r.setStart(this.getStart());
		r.setEnd(this.getEnd());

		if (StringUtils.isNotEmpty(tableName)
				&& StringUtils.isNotEmpty(tableName.trim())) {
			r.setTableName(tableName.trim());
		}

		if (StringUtils.isNotEmpty(txt) && StringUtils.isNotEmpty(txt.trim())) {
			try {
				r.setTxt(Integer.parseInt(txt.trim()));
			} catch (Exception e) {
				logger.error(txt, e);
			}
		}

		if (StringUtils.isNotEmpty(zdid) && StringUtils.isNotEmpty(zdid.trim())) {
			r.setZdid(zdid.trim());
		}

		if (StringUtils.isNotEmpty(zdtxt) && StringUtils.isNotEmpty(zdtxt.trim())) {
			r.setZdtxt(zdtxt.trim());
		}

		if (StringUtils.isNotEmpty(d) && StringUtils.isNotEmpty(d.trim())) {
			d = d.trim();
			r.setD(d);
		}
		if (StringUtils.isNotEmpty(search) && StringUtils.isNotEmpty(search.trim())) {
			search = search.trim();
			r.setSearch(search);
		}

		total = boformService.getQueryParameterCount(r);
		if (total != 0) {
			queryParameterList = boformService.getQueryParameterList(r);
		}

		return JSON;
	}

	/**
	 * 单点登录BO
	 * 
	 * @param boType
	 * @return
	 */
	@PermissionSearch
	private String sso(String boType) {
		String token = (String) this.getSession().getAttribute(
				BO_TYPE_BO3.equals(boType) ? "LogonToken" : "Bo4LogonToken");

		if (StringUtils.isEmpty(token)) {
			try {
				ISessionMgr sessionMgr = CrystalEnterprise.getSessionMgr();
				IEnterpriseSession enterpriseSession = sessionMgr.logon(
						BO_TYPE_BO3.equals(boType) ? bo3use : bo4use,
						BO_TYPE_BO3.equals(boType) ? bo3pwd : bo4pwd,
						BO_TYPE_BO3.equals(boType) ? bo3dev : bo4dev,
						BO_TYPE_BO3.equals(boType) ? bo3enterp : bo4enterp);
				// 单点登录
				this.getSession().setAttribute(
						BO_TYPE_BO3.equals(boType) ? "EnterpriseSession"
								: "Bo4EnterpriseSession", enterpriseSession);

				token = enterpriseSession.getLogonTokenMgr().getDefaultToken();

				this.getSession().setAttribute(
						BO_TYPE_BO3.equals(boType) ? "LogonToken"
								: "Bo4LogonToken", token);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return token;
	}

	/**
	 * when create update then removeCache
	 * 
	 * @param bids
	 */
	private void removeCache(String[] bids) {
		try {
			/*for (String id : bids) {
				memcachedCacheService
						.remove(IMemcachedCacheService.CACHE_KEY_BO_PARAMETER
								+ id);
			}*/
		} catch (Exception e) {
		}
	}

	public IBoformService getBoformService() {
		return boformService;
	}

	public void setBoformService(IBoformService boformService) {
		this.boformService = boformService;
	}

	/*public ICustService getCustService() {
		return custService;
	}

	public void setCustService(ICustService custService) {
		this.custService = custService;
	}

	public IMemcachedCacheService getMemcachedCacheService() {
		return memcachedCacheService;
	}

	public void setMemcachedCacheService(
			IMemcachedCacheService memcachedCacheService) {
		this.memcachedCacheService = memcachedCacheService;
	}*/

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ReportParameter> getReportParameterList() {
		return reportParameterList;
	}

	public void setReportParameterList(List<ReportParameter> reportParameterList) {
		this.reportParameterList = reportParameterList;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public ReportParameter getReportParameter() {
		return reportParameter;
	}

	public void setReportParameter(ReportParameter reportParameter) {
		this.reportParameter = reportParameter;
	}

	public String getBo3url() {
		return bo3url;
	}

	public void setBo3url(String bo3url) {
		this.bo3url = bo3url;
	}

	public String getBo3use() {
		return bo3use;
	}

	public void setBo3use(String bo3use) {
		this.bo3use = bo3use;
	}

	public String getBo3pwd() {
		return bo3pwd;
	}

	public void setBo3pwd(String bo3pwd) {
		this.bo3pwd = bo3pwd;
	}

	public String getBo3dev() {
		return bo3dev;
	}

	public void setBo3dev(String bo3dev) {
		this.bo3dev = bo3dev;
	}

	public String getBo3enterp() {
		return bo3enterp;
	}

	public void setBo3enterp(String bo3enterp) {
		this.bo3enterp = bo3enterp;
	}

	public String getBo4url() {
		return bo4url;
	}

	public void setBo4url(String bo4url) {
		this.bo4url = bo4url;
	}

	public String getBo4use() {
		return bo4use;
	}

	public void setBo4use(String bo4use) {
		this.bo4use = bo4use;
	}

	public String getBo4pwd() {
		return bo4pwd;
	}

	public void setBo4pwd(String bo4pwd) {
		this.bo4pwd = bo4pwd;
	}

	public String getBo4dev() {
		return bo4dev;
	}

	public void setBo4dev(String bo4dev) {
		this.bo4dev = bo4dev;
	}

	public String getBo4enterp() {
		return bo4enterp;
	}

	public void setBo4enterp(String bo4enterp) {
		this.bo4enterp = bo4enterp;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Integer> getYearList() {
		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public List<QueryParameter> getQueryParameterList() {
		return queryParameterList;
	}

	public void setQueryParameterList(List<QueryParameter> queryParameterList) {
		this.queryParameterList = queryParameterList;
	}

	public String getCustNF() {
		return custNF;
	}

	public void setCustNF(String custNF) {
		this.custNF = custNF;
	}

	public String getCustYY() {
		return custYY;
	}

	public void setCustYY(String custYY) {
		this.custYY = custYY;
	}

	public String getCustSP() {
		return custSP;
	}

	public void setCustSP(String custSP) {
		this.custSP = custSP;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getZdtxt() {
		return zdtxt;
	}

	public void setZdtxt(String zdtxt) {
		this.zdtxt = zdtxt;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	public static String getBoTypeBo3() {
		return BO_TYPE_BO3;
	}





}
