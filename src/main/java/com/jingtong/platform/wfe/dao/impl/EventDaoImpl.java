package com.jingtong.platform.wfe.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.dao.IEventDao;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.Leave;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

@SuppressWarnings("rawtypes")
public class EventDaoImpl extends BaseDaoImpl implements IEventDao {

	/**
	 * 获取事务指定当前处理人对应的流程Detail(第一条,有可能多条)
	 * @param eventId
	 * @param userId
	 * @return
	 */
	
	public String getProEventDetail(String eventId, String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", Long.parseLong(eventId.trim()));
		params.put("cur_user_id", userId);
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getDeatilByCurUserAndEventID", params);
	}
	
	
	public Long getEvent_XmlTempId() {
		return (Long)getSqlMapClientTemplate().queryForObject("wfe.getEvent_XmlTempID");
	}

	
	public void updateLeave(Leave leave) {
		getSqlMapClientTemplate().update("wfe.updateEntity", leave);
	}

	
	public String getWfeActionId(String eventId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getWfeActionId",eventId);
	}
	/**
	 * 获取流程明细
	 */
	
	public ProEventTotal getEventTotalById(Long eventId) {
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalById", eventId);
	}
	/**
	 * 根据事务号查询事务总表
	 */
	
	public ProEventTotal getEventTotalById(Long eventId,Long ccId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", eventId);
		params.put("cc_id", ccId);
		
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalByIdAndCcId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail) {
		return getSqlMapClientTemplate().queryForList(
				"wfe.getEventDetailList", eventDetail);
	}
	
	
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp) {
		return (Long) getSqlMapClientTemplate().insert(
				"wfe.createBudgetFileTmp", budgetFileTmp);
	}

	/** 联系人个数  */
	
	public int getLinkManCount(LinkMan linkMan) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"wfe.getLinkManCount", linkMan);
	}
	
	/** 选择常用联系人 */
	
	@SuppressWarnings("unchecked")
	public List<LinkMan> getLinkManList(LinkMan linkMan) {
		return getSqlMapClientTemplate().queryForList("wfe.getLinkManList",
				linkMan);
	}
	
	/** 新增联系人  */
	
	public Long createLinkMan(LinkMan linkMan) {
		return (Long) getSqlMapClientTemplate().insert("wfe.createLinkMan",
				linkMan);
	}
	
	/** 修改联系人 */
	
	public int updateLinkMan(LinkMan linkMan) {
		return getSqlMapClientTemplate().update("wfe.updateLinkMan", linkMan);
	}

	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		return getSqlMapClientTemplate().queryForList("wfe.getStationListByEventId", eventId);
	}
	
	
	public void updateBuglogFile(Long eventId, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventId", eventId);
		map.put("state", state);
		getSqlMapClientTemplate().update("wfe.updateBuglogFile", map);
	}
	
	
	public int getTripWayCount(TripWay tripWay) {
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripWayCount", tripWay);
	};
	
	
	@SuppressWarnings("unchecked")
	public List<TripWay> getTripWayList(TripWay tripWay) {
		return getSqlMapClientTemplate().queryForList("wfe.getTripWayList", tripWay);
	};
	/**
	 * 根据事务模板查询事务总数
	 */
	
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripApplyListCount", proEventTotal);
	}
	/**
	 * 根据事务号查询事务列表
	 */
	
	@SuppressWarnings("unchecked")
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getTripApplyList", proEventTotal);
	}
	
	/***
	 * 创建抄送人
	 * @param cc
	 * @return
	 */
	
	public StringResult createCc(final List<Cc> ccList){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            
			public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(Cc cc : ccList) {
                	executor.insert("wfe.createCc", cc);
                }
                executor.executeBatch();
                StringResult stringResult = new StringResult();
                stringResult.setCode("success");
                return stringResult;
            }
        });
		return null;
	}
	
	/**
	 * 修改抄送信息
	 * @param businessTripApplyList
	 * @return
	 */
	
	public int  updateCc(Cc cc){
		return getSqlMapClientTemplate().update("wfe.updateCc", cc);
	}
	
	/**
	 * 获取抄送信息列表数量
	 * @param cc
	 * @return
	 */
	
	public int  getCcListCount(Cc cc){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getCcListCount", cc);
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Cc> getCcList(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcList", cc);
	}
	/***
	 * 获取抄送信息列表
	 * @param cc
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Cc> getCcListByEventId(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcListByEventId", cc);

	}

	/**根据人员ID查找该人对应的岗位ID**/
	
	public String getStationIdByUserId(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getStationIdByUserId", userId);
	}
	
	
	public Integer getCancelEventCount(ProcessEventTotal eventTotal){
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getCancelEventCount", eventTotal);
	}

	
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getCancelEventList", eventTotal);
	}

}
