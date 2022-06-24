package com.jingtong.platform.logisticsScheduling.service;

import java.util.List;
import java.util.Map;

import com.jingtong.platform.logisticsScheduling.pojo.ScheduleList;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleSmtor;


public interface ScheduleListService {

	public List<ScheduleSmtor> getScheduleSmtor(ScheduleSmtor sl) ;

	public int getScheduleSmtorTotal(ScheduleSmtor sl);

	public String querySchedule(Map map);

	public int viewshcedulelistTotal(ScheduleList sl);

	public List<ScheduleList> viewshcedulelist(ScheduleList sl);

	public String uplevel(Long valueOf);

	public String downlevel(Long valueOf);

	public int islock(ScheduleSmtor ss);

	public int unlock(ScheduleSmtor ss);
	public ScheduleList getShceduleListByid(Long id);

	public void updateShceduleList(ScheduleList newscheduleList);

	public int viewshcedulelistLock(ScheduleList shecduleLocok);

}
