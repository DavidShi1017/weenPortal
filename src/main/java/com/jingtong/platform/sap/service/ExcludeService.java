package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Exclude;

public interface ExcludeService {

	public void getExcludeFromSap();
	
	public int getExcludeTotal(Exclude exc);
	
	public List<Exclude> getExcludeList(Exclude exc);
}
