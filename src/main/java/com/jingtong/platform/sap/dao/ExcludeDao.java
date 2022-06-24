package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.sap.pojo.Exclude;

public interface ExcludeDao {

	public long saveExclude(Exclude exc);
	public int updateExclude(Exclude exc);
	public int getExcludeCount();
	
	public int getExcludeTotal(Exclude exc);
	
	public List<Exclude> getExcludeList(Exclude exc);
}
