package com.jingtong.platform.wfe.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.framework.util.FileUtil;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.webservice.service.IWebService;
import com.jingtong.platform.wfe.dao.IEventDao;
import com.jingtong.platform.wfe.pojo.BusinessTripApply;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;
import com.jingtong.platform.wfe.service.IEventService;


public class EventServiceImpl implements IEventService {
	
	private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
	
	private IEventDao eventDao;
	private IWebService webService;
	private String appUrl;		//应用工程路径
	private TransactionTemplate transactionTemplate;
	private String wfeUploadPath;
	private String wfeDownloadPath;
	
	public IEventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(IEventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	
	public Long getEvent_XmlTempId(){
		return eventDao.getEvent_XmlTempId();
	}
	
	/**
	 * 创建事务
	 */
	
	public StringResult createEvent(String key, String userId, String title, String nextUser,
			String eventContent, String userList, String memo){
		StringResult result = new StringResult();
		try{
			String processInstanceId = "";
			String md = key.substring(0, 3);
			if("any".equals(md)){			//创建自动流程
				Object[] res = new Object[8];
				res[0] = key;
				res[1] = userId;
				res[2] = title;
				res[3] = nextUser;
				res[4] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[5] = memo;
				res[6] = "";
				res[7] = "";
				processInstanceId = webService.startAnyProcessWorkflow(res);			
			}else if("sem".equals(md)){ 		//创建半自动化流程
				Object[] res = new Object[8];
				res[0] = key;
				res[1] = userId;
				res[2] = title;
				res[3] = userList;
				res[4] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[5] = memo;
				res[6] = "";
				res[7] = "";
				processInstanceId = webService.startSemiAutomaticWorkflow(res);		
			}else{
				Object[] res = new Object[5];
				res[0] = key;
				res[1] = userId;
				res[2] = appUrl+"/wfe/eventAction!searchEventContent.jspa";
				res[3] = title;
				res[4] = memo;
				processInstanceId = webService.startWorkflow(res);
			}
			try{
				Long.valueOf(processInstanceId);		//判断事务是否正常启动	
				result.setCode(IEventService.SUCCESS);
				result.setResult(processInstanceId);
			}catch(Exception e){
				result.setCode(IEventService.ERROR);
				result.setResult("启动失败！");
				return result;
			}
		} catch(Exception e){
			logger.error(e);
			result.setCode(IEventService.ERROR);
			result.setResult(IEventService.ERROR_MESSAGE);
		}
		return result;
	}
	
	
	public String getProEventDetail(String eventId, String userId){
		return eventDao.getProEventDetail(eventId, userId);	
	}

	/**
	 * 查询常用联系人
	 * @param linkMan
	 * @return
	 */
	
	public List<LinkMan> getLinkManList(LinkMan linkMan) {
		try {
			return eventDao.getLinkManList(linkMan);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(linkMan), e);
		}
		return null;
	}

	/**
	 * 创建常用联系人
	 */
	
	public BooleanResult saveOrUpdateLinkMan(LinkMan linkMan) {
		BooleanResult result = new BooleanResult();
		try {
			int n = eventDao.getLinkManCount(linkMan);
			if (n == 0) {
				Long id = eventDao.createLinkMan(linkMan);
				result.setCode(id.toString());
				result.setResult(true);
			} else {
				int c = eventDao.updateLinkMan(linkMan);
				if (c == 1) {
					result.setResult(true);
				}
			}
		} catch (Exception e) {
			result.setResult(false);
			logger.error(LogUtil.parserBean(linkMan), e);
		}
		return result;
	}
	
	/**
	 * 获取ActionId
	 */
	
	public String getWfeActionId(String eventId) {
		String actionId = eventDao.getWfeActionId(eventId);
		return actionId;
	}
	
	
	public ProEventTotal getEventTotalById(Long eventId) {
		try {
			return eventDao.getEventTotalById(eventId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}

		return null;
	}
	
	/**
	 * 根据事务号查询事务总表
	 */
	
	public ProEventTotal getEventTotalById(Long eventId,Long ccId){
		try {
			return eventDao.getEventTotalById(eventId,ccId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}
		return null;
	}
	
	/***
	 * 查询事务明细并排序
	 */
	
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId) {
		try {
			ProEventDetail proEventDetail = new ProEventDetail();
			proEventDetail.setEventId(eventId);
//			proEventDetail.setSort("time");
//			proEventDetail.setDir("asc");

			List<ProEventDetail> temp = eventDao.getEventDetailList(proEventDetail);

			Map<Long, ProEventDetail> eventDetailMap = new LinkedHashMap<Long, ProEventDetail>();
			StringBuffer link = new StringBuffer();
			for (ProEventDetail detail : temp) {
				// 删除link
				link.delete(0, link.length());

				if ( !"".equals(detail.getFilename()) && detail.getFilename() != null) {
					link.append("<a href=\"javascript:downLoad(");
					link.append(detail.getFileId());
					link.append(");\">");
					link.append(detail.getFilename());
					link.append("</a>&nbsp;");
				}

				ProEventDetail ped = eventDetailMap.get(detail.getEventDtlId());
				if (ped == null) {
					detail.setLink(link.toString());
					eventDetailMap.put(detail.getEventDtlId(), detail);
				} else {
					ped.setLink(link.append(ped.getLink()).toString());
				}
			}

			return new ArrayList<ProEventDetail>(eventDetailMap.values());
		} catch (Exception e) {
			logger.error("查询事务：" + eventId + " 出错！", e);
		}
		return null;
	}
	
	
	public boolean processAttachments(final File[] uploadFiles,
			final String[] uploadFileNames, final Long eventDetailId, final String timestamp,final String key) {
		boolean result = true;
		if (uploadFileNames != null && uploadFileNames.length > 0
				&& StringUtils.isNotEmpty(timestamp)) {

			result = (Boolean) transactionTemplate
					.execute(new TransactionCallback() {
						
						public Boolean doInTransaction(TransactionStatus ts) {
							// 重命名
							String newFileName = null;
							boolean saveAsFile = false;

							File savedir = new File(wfeUploadPath + "/"
									+ key +"/"+ DateUtil.datetime("yyyyMM"));
							// 如果目录不存在，则新建
							if (!savedir.exists()) {
								savedir.mkdirs();
							}

							for (int i = 0; i < uploadFiles.length; i++) {
								if (uploadFileNames[i] != null
										&& uploadFileNames[i].length() > 0) {

									newFileName = timestamp
											+ i
											+ FileUtil
													.getFileExtention(uploadFileNames[i]);

									File imageFile = new File(wfeUploadPath
											+ "/"+ key +"/"+ DateUtil.datetime("yyyyMM")
											+ "/" + newFileName);

									saveAsFile = FileUtil.saveAsFile(
											uploadFiles[i], imageFile);

									if (!saveAsFile) {
										logger.error("saveAsFile error: "
												+ imageFile);
										ts.setRollbackOnly();
										return false;
									}

									String filename = uploadFileNames[i];
									BudgetFileTmp ftp = new BudgetFileTmp();
									ftp.setFileName(filename);
									ftp.setEventDtlId(eventDetailId);// 暂时虚拟一个id
									ftp.setFileNameNew(newFileName);
									ftp.setSubFolders(key+"/"+DateUtil
											.datetime("yyyyMM"));

									try {
										eventDao.createBudgetFileTmp(ftp);
									} catch (Exception e) {
										logger.error(e);
										ts.setRollbackOnly();
										return false;
									}

								}
							}

							return true;
						}
					});
		}
		return result;
	
	}
	
	
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		try {
			return eventDao.getStationListByEventId(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public int getTripWayCount(TripWay tripWay) {
		try {
			return eventDao.getTripWayCount(tripWay);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	
	public List<TripWay> getTripWayList(TripWay tripWay) {
		try {
			return eventDao.getTripWayList(tripWay);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public void updateBuglogFile(Long eventId, String state) {
		try {
			eventDao.updateBuglogFile(eventId, state);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	/**
	 * 根据事务模板查询事务总数
	 */
	
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyListCount(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	/**
	 * 根据事务号查询事务
	 */
	
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyList(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	
	public File exportBusinessTripApplyList(List<BusinessTripApply> businessTripApplyList) {

		try{
			// 建立空模板文件保存路径
			File saveDir = new File(wfeDownloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(wfeDownloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			// 写数据到Excel表格中
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			//	设置字体样式
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
			
			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			Label label;
			// 设置第一行行高
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "事务ID", formatHead);
			sheet.addCell(label);
			// 设置列宽
			sheet.setColumnView(0, 20);
			
			label = new Label(1, 0, "事务标题", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 20);
			
			label = new Label(2, 0, "提出者", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);
			
			label = new Label(3, 0, "所属部门", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);
			
			label = new Label(4, 0, "所属项目", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 20);
			
			label = new Label(5, 0, "出差地点", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);
			
			label = new Label(6, 0, "出行方式", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(6, 40);
			
			label = new Label(7, 0, "开始时间", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(7, 20);
			
			label = new Label(8, 0, "结束时间", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(8, 20);
			
			label = new Label(9, 0, "事由", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(9, 20);
	
			
			String temp2="yyyy-MM-dd";
			SimpleDateFormat formatter2 = new SimpleDateFormat (temp2);
			for(int i=0; i<businessTripApplyList.size(); i++){
				label = new Label(0, i+1, businessTripApplyList.get(i).getEventId(), formatTable);
				sheet.addCell(label);
				label = new Label(1, i+1, businessTripApplyList.get(i).getEventTitle(), formatTable);
				sheet.addCell(label);
				label = new Label(2, i+1, businessTripApplyList.get(i).getUserName(), formatTable);
				sheet.addCell(label);
				label = new Label(3, i+1, businessTripApplyList.get(i).getOrgName(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i+1, businessTripApplyList.get(i).getCostCenterName(), formatTable);
				sheet.addCell(label);
				label = new Label(5, i+1, businessTripApplyList.get(i).getPlace(), formatTable);
				sheet.addCell(label);
				label = new Label(6, i+1, businessTripApplyList.get(i).getTripWayName(), formatTable);
				sheet.addCell(label);
				label = new Label(7, i+1, businessTripApplyList.get(i).getBeginDate()==null ? "" :formatter2.format(businessTripApplyList.get(i).getBeginDate()), formatTable);
				sheet.addCell(label);
				label = new Label(8, i+1, businessTripApplyList.get(i).getEndDate()==null ? "" :formatter2.format(businessTripApplyList.get(i).getEndDate()), formatTable);
				sheet.addCell(label);
				label = new Label(9, i+1, businessTripApplyList.get(i).getReason(), formatTable);
				sheet.addCell(label);
			}
			book.write();
			book.close();
			return rootFile;
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	
	}
	/***
	 * 创建抄送人
	 * @param cc
	 * @return
	 */
	
	public StringResult createCc(List<Cc> cc){
		try {
			return eventDao.createCc(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 修改抄送信息
	 * @param businessTripApplyList
	 * @return
	 */
	
	public int  updateCc(Cc cc){
		try {
			return eventDao.updateCc(cc);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	/**
	 * 获取抄送信息列表数量
	 * @param cc
	 * @return
	 */
	//TODO
	public int  getCcListCount(Cc cc){
		try {
			return 0;//eventDao.getCcListCount(cc);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	
	public List<Cc> getCcList(Cc cc){
		try {
			return eventDao.getCcList(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	
	public List<Cc> getCcListByEventId(Cc cc){
		try {
			return eventDao.getCcListByEventId(cc);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	/**根据人员ID查找该人对应的岗位ID**/
	
	public String getStationIdByUserId(String userId) {
		return eventDao.getStationIdByUserId(userId);
	}
	
	//TODO
	public int getCancelEventCount(ProcessEventTotal eventTotal){
		try {
			return 0;//eventDao.getCancelEventCount(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal){
		try{
			return eventDao.getCancelEventList(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public String getWfeUploadPath() {
		return wfeUploadPath;
	}

	public void setWfeUploadPath(String wfeUploadPath) {
		this.wfeUploadPath = wfeUploadPath;
	}

	public String getWfeDownloadPath() {
		return wfeDownloadPath;
	}

	public void setWfeDownloadPath(String wfeDownloadPath) {
		this.wfeDownloadPath = wfeDownloadPath;
	}

	
}
