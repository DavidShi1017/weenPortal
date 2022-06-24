package com.jingtong.platform.wfe.dao;

import java.util.List;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.Leave;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

public interface IEventDao {
	
	/**
	 * 获取事务指定处理人对应的流程Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * 获取Xml临时文件名
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	public void updateLeave(Leave leave);
	
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
	 * 根据事务号查询明细
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	/**
	 * 创建附件
	 * 
	 * @param budgetFileTmp
	 * @return
	 */
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp);
	
	/**
	 * 获取常用联系人数
	 * 
	 * @param linkMan
	 * @return
	 */
	public int getLinkManCount(LinkMan linkMan);
	
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
	public Long createLinkMan(LinkMan linkMan);
	
	/**
	 * 更新常用联系人
	 * 
	 * @param linkMan
	 */
	public int updateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
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
	
	public Integer getCancelEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);

}
