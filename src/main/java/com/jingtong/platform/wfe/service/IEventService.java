package com.jingtong.platform.wfe.service;

import java.io.File;
import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.BusinessTripApply;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

public interface IEventService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	public static final String NEXT = "next";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";
	
	public Long getEvent_XmlTempId();

	public StringResult createEvent(String key, String userId, String title,String nextUser,
			String eventContent, String userList, String memo);
	
	public String getProEventDetail(String eventId, String userId);
	
	public String  getWfeActionId(String eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId);
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/**
	 * 获取事务明细列表并且排序
	 * 
	 * @param eventId
	 * @return
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId);
	
	/**
	 * 处理附件上传
	 * 
	 * @param uploadFiles
	 * @param uploadFileNames
	 * @param eventDetailId
	 * @param timestamp
	 * @return
	 */
	public boolean processAttachments(File[] uploadFiles,
			String[] uploadFileNames, Long eventDetailId, String timestamp,String key);
	
	/**
	 * 查询常用联系人
	 * 
	 * @param linkMan
	 * @return
	 */
	public List<LinkMan> getLinkManList(LinkMan linkMan);
	
	/**
	 * 创建常用联系人
	 * 
	 * @param linkMan
	 * @return
	 */
	public BooleanResult saveOrUpdateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	
	public File exportBusinessTripApplyList(List<BusinessTripApply> businessTripApplyList);
	
	/***
	 * 创建抄送人
	 * @param cc
	 * @return
	 */
	public StringResult createCc(List<Cc> cc);
	
	/**
	 * 修改抄送信息
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc);
	
	/**
	 * 获取抄送信息列表数量
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc);
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcList(Cc cc);
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcListByEventId(Cc cc);

	public String getStationIdByUserId(String userId);
	
	public int getCancelEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);
}
