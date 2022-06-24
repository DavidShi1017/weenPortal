package com.jingtong.platform.logisticsScheduling.dao;

import java.util.List;
import java.util.Map;

import com.jingtong.platform.logisticsScheduling.pojo.ScheduleList;
import com.jingtong.platform.logisticsScheduling.pojo.ScheduleSmtor;


public interface ScheduleListDao {

	List<ScheduleSmtor> getScheduleSmtor(ScheduleSmtor sl);

	int getScheduleSmtorTotal(ScheduleSmtor sl);

	String querySchedule(Map ma);

	int viewshcedulelistTotal(ScheduleList sl);

	List<ScheduleList> viewshcedulelist(ScheduleList sl);

	String downlevel(Long valueOf);

	String uplevel(Long valueOf);

	int islock(ScheduleSmtor ss);

	int unlock(ScheduleSmtor ss);

	ScheduleList getShceduleListByid(Long ids);

	void updateShceduleList(ScheduleList newscheduleList);

	int viewshcedulelistLock(ScheduleList shecduleLocok);

}
