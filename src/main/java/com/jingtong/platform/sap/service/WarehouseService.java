package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Warehouses;

public interface WarehouseService {

	public List<Warehouses> queryWarehouseList(Warehouses warehouse);
	
	public void getSapWarehouseLocation() throws Exception;
}
