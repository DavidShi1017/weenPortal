package com.jingtong.platform.logisticsScheduling.service.impl;


import java.util.List;
import java.util.Map;

import com.jingtong.platform.logisticsScheduling.dao.ScheduleListDao;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleList;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleSmtor;
import com.jingtong.platform.logisticsScheduling.service.ScheduleListService;


public class ScheduleListServiceImpl implements ScheduleListService {

	private ScheduleListDao scheduleListDao;


	public ScheduleListDao getScheduleListDao() {
		return scheduleListDao;
	}

	public void setScheduleListDao(ScheduleListDao scheduleListDao) {
		this.scheduleListDao = scheduleListDao;
	}

	@Override
	public List<ScheduleSmtor> getScheduleSmtor(ScheduleSmtor sl) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.getScheduleSmtor(sl);
	}

	@Override
	public int getScheduleSmtorTotal(ScheduleSmtor sl) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.getScheduleSmtorTotal(sl);
	}

	@Override
	public String querySchedule(Map map) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.querySchedule(map);
	}

	@Override
	public int viewshcedulelistTotal(ScheduleList sl) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.viewshcedulelistTotal(sl);
	}

	@Override
	public List<ScheduleList> viewshcedulelist(ScheduleList sl) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.viewshcedulelist(sl);
	}

	@Override
	public String uplevel(Long valueOf) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.uplevel(valueOf);
	}

	@Override
	public String downlevel(Long valueOf) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.downlevel(valueOf);
	}

	@Override
	public int islock(ScheduleSmtor ss) {
		return this.scheduleListDao.islock(ss);
		
	}

	@Override
	public int unlock(ScheduleSmtor ss) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.unlock(ss);
	}

	@Override
	public ScheduleList getShceduleListByid(Long id) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.getShceduleListByid(id);
	}

	@Override
	public void updateShceduleList(ScheduleList newscheduleList) {
		// TODO Auto-generated method stub
		 this.scheduleListDao.updateShceduleList(newscheduleList);
	}

	@Override
	public int viewshcedulelistLock(ScheduleList shecduleLocok) {
		// TODO Auto-generated method stub
		return this.scheduleListDao.viewshcedulelistLock(shecduleLocok);
	}

	
}
