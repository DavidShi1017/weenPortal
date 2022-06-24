package com.jingtong.platform.logisticsScheduling.dao.impl;

import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.logisticsScheduling.dao.ScheduleListDao;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleList;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleSmtor;

public class ScheduleListDaoImpl extends BaseDaoImpl implements ScheduleListDao {

	@Override
	public List<ScheduleSmtor> getScheduleSmtor(ScheduleSmtor ss) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("scheduleList.getScheduleSmtor", ss);
	}

	@Override
	public int getScheduleSmtorTotal(ScheduleSmtor ss) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("scheduleList.getScheduleSmtorTotal",ss);
	}

	@Override
	public String querySchedule(Map map) {
		this.getSqlMapClientTemplate().queryForObject("scheduleList.querySchedule", map);
		return null;
	}

	@Override
	public int viewshcedulelistTotal(ScheduleList sl) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("scheduleList.viewshcedulelistTotal",sl);
	}

	@Override
	public List<ScheduleList> viewshcedulelist(ScheduleList sl) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("scheduleList.viewshcedulelist", sl);
	}

	@Override
	public String downlevel(Long id) {
		// TODO Auto-generated method stub
		ScheduleSmtor ss=(ScheduleSmtor) this.getSqlMapClientTemplate().queryForObject("scheduleList.getScheduleSmtorByid", id);
		this.getSqlMapClientTemplate().update("scheduleList.downlevel01", ss);
		this.getSqlMapClientTemplate().update("scheduleList.downlevel02", ss);
		return null;
	}

	@Override
	public String uplevel(Long id) {
		ScheduleSmtor ss=(ScheduleSmtor) this.getSqlMapClientTemplate().queryForObject("scheduleList.getScheduleSmtorByid", id);
		this.getSqlMapClientTemplate().update("scheduleList.uplevel01", ss);
		this.getSqlMapClientTemplate().update("scheduleList.uplevel02", ss);
		return null;
	}

	@Override
	public int islock(ScheduleSmtor ss) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("scheduleList.islock", ss);
	}

	@Override
	public int unlock(ScheduleSmtor ss) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().update("scheduleList.unlock", ss);
	}

	@Override
	public ScheduleList getShceduleListByid(Long ids) {
		// TODO Auto-generated method stub
		ScheduleList sl=(ScheduleList)this.getSqlMapClientTemplate().queryForObject("scheduleList.getShceduleListByid", ids);
		return sl;
	}

	@Override
	public void updateShceduleList(ScheduleList newscheduleList) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("scheduleList.updateschedulel", newscheduleList);
	}

	@Override
	public int viewshcedulelistLock(ScheduleList shecduleLocok) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("scheduleList.viewshcedulelistLock",shecduleLocok);
	}


}
