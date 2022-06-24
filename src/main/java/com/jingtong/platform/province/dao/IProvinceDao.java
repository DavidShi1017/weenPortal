package com.jingtong.platform.province.dao;

import java.util.List;

import com.jingtong.platform.province.pojo.Province;



/**
 * ¹ú¼Ò
 * @author lenovo
 *
 */
public interface IProvinceDao {
	
	public List<Province> searchProvince(Province c);
	
	public long saveProvince(Province c);

	public int updateProvince(Province c);
	
	public int deleteProvinceById(Province c);

	public Province getProvinceById(Province c);
	
	
	public int getProvinceListCount(Province c);
	public int getCountByCode(Province c);
	
}
