package com.jingtong.platform.leveldict.service.impl;

import java.util.List;

import com.jingtong.platform.leveldict.dao.ILevelDictDao;
import com.jingtong.platform.leveldict.dao.impl.LevelDictDao;
import com.jingtong.platform.leveldict.pojo.LevelDict;
import com.jingtong.platform.leveldict.service.ILevelDictService;

public class LevelDictService implements ILevelDictService {
	
	private ILevelDictDao levelDictDao;

	public ILevelDictDao getLevelDictDao() {
		return levelDictDao;
	}

	public void setLevelDictDao(ILevelDictDao levelDictDao) {
		this.levelDictDao = levelDictDao;
	}

	@Override
	public Boolean SaveLevelDict(LevelDict levelDict) {
		return this.levelDictDao.SaveLevelDict(levelDict);
	}

	@Override
	public Boolean UpdateLevelDict(LevelDict levelDict) {
		return this.levelDictDao.UpdateLevelDict(levelDict);
	}

	@Override
	public List<LevelDict> GetAllData() {
		return this.levelDictDao.GetAllData();
	}

	@Override
	public List<LevelDict> SearchById(int id) {
		return this.levelDictDao.SearchById(id);
	}

	@Override
	public List<LevelDict> SearchParameters(LevelDict levelDict) {
		return this.levelDictDao.SearchParameters(levelDict);
	}

}
