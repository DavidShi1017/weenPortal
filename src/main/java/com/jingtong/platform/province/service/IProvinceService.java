package com.jingtong.platform.province.service;

import java.util.List;

import com.jingtong.platform.province.pojo.Province;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public interface IProvinceService {
public List<Province> searchProvince(Province g);
	
	public long saveProvince(Province c);
	
	public int updateProvince(Province c);
	
	public int deleteProvinceById(Province c);
	
	public Province getProvinceById(Province c);
	
	
	public int getProvinceListCount(Province c);
	public int getCountByCode(Province c);
}
