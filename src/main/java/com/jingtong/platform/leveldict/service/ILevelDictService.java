package com.jingtong.platform.leveldict.service;

import java.util.List;

import com.jingtong.platform.leveldict.pojo.LevelDict;

public interface ILevelDictService {
	Boolean SaveLevelDict(LevelDict levelDict);
	Boolean UpdateLevelDict(LevelDict levelDict);
	List<LevelDict> GetAllData();
	List<LevelDict> SearchById(int id);
	List<LevelDict> SearchParameters(LevelDict levelDict);
}
