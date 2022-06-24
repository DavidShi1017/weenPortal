package com.jingtong.platform.logisticsScheduling.service;

import java.util.List;

import com.jingtong.platform.logisticsScheduling.pojo.LoadCapacity;


public interface LoadCapacityService {

	int getLoadCapacityListTotal(LoadCapacity lc);

	List<LoadCapacity> getLoadCapacityList(LoadCapacity lc);

	int updateLoadCapacity(LoadCapacity loadCapacity);

	int saveLoadCapacity(LoadCapacity loadCapacity);

	LoadCapacity getLoadCapacity(Long ids);

	int deleteLoadCapacity(Long id);
	

}
