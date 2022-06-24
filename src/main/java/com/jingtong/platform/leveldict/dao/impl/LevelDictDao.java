package com.jingtong.platform.leveldict.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.jingtong.platform.leveldict.dao.ILevelDictDao;
import com.jingtong.platform.leveldict.pojo.LevelDict;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

@SuppressWarnings("rawtypes")
public class LevelDictDao extends BaseDaoImpl implements ILevelDictDao {

	@Override
	public Boolean SaveLevelDict(LevelDict levelDict) {
		Boolean result = false;
		if(levelDict != null)
		{
			Object object = this.getSqlMapClientTemplate().insert("levelDict.saveLevelDict",levelDict);
		    if(object != null)
		    {
		    	int rows = Integer.getInteger(object.toString());
		    	result = rows > 0;
		    }	
		}
		return result;
	}

	@Override
	public Boolean UpdateLevelDict(LevelDict levelDict) {
		boolean result = false;
		if(levelDict != null)
		{
			int rows = this.getSqlMapClientTemplate().update("levelDict.updateLevelDict",levelDict);
			result = rows > 0;
		}
		return result;
	}

	@Override @SuppressWarnings("unchecked")
	public List<LevelDict> GetAllData() {
		List<LevelDict> resultDicts = new ArrayList<LevelDict>();
		resultDicts = this.getSqlMapClientTemplate().queryForList("levelDict.getAll");
		return resultDicts;
	}

	@Override
	public List<LevelDict> SearchById(int id) {
		List<LevelDict> resultDicts = new ArrayList<LevelDict>();
		if(id > -1)
		{
			Object object = this.getSqlMapClientTemplate().queryForObject("levelDict.searchById", id);
			LevelDict levelDict = (LevelDict)object;
			if(levelDict != null)
			{
				resultDicts.add(levelDict);
			}
		}
		return resultDicts;
	}

	
	@Override @SuppressWarnings("unchecked")
	public List<LevelDict> SearchParameters(LevelDict levelDict) {
		List<LevelDict> resultDicts = new ArrayList<LevelDict>();
		if(levelDict != null)
		{
			resultDicts = (List<LevelDict>)this.getSqlMapClientTemplate().queryForList("levelDict.searchByParameters", levelDict);
		}
		return resultDicts;
	}

}
