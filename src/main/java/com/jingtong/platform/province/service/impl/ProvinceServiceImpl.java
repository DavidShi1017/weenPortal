package com.jingtong.platform.province.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.province.dao.IProvinceDao;
import com.jingtong.platform.province.pojo.Province;
import com.jingtong.platform.province.service.IProvinceService;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public class ProvinceServiceImpl implements IProvinceService {
	private static final Log logger = LogFactory.getLog(ProvinceServiceImpl.class);
	private IProvinceDao provinceDao;
	
	public static Log getLogger() {
		return logger;
	}

	public IProvinceDao getProvinceDao() {
		return provinceDao;
	}

	public void setProvinceDao(IProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}


	@Override
	public List<Province> searchProvince(Province g) {
		try {
			return provinceDao.searchProvince(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long saveProvince(Province g) {
		try {
			return provinceDao.saveProvince(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateProvince(Province g) {
		try {
			return provinceDao.updateProvince(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteProvinceById(Province g) {
		try {
			return provinceDao.deleteProvinceById(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Province getProvinceById(Province g) {
		try {
			return provinceDao.getProvinceById(g);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getProvinceListCount(Province g) {
		try {
			return provinceDao.getProvinceListCount(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getCountByCode(Province g) {
		try {
			return provinceDao.getCountByCode(g);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
