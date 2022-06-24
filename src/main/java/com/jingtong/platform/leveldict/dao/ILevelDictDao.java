/**
 * 
 */
package com.jingtong.platform.leveldict.dao;

import java.util.List;
import com.jingtong.platform.leveldict.pojo.*;

/**
 *
 */
public interface ILevelDictDao {
	Boolean SaveLevelDict(LevelDict levelDict);
	Boolean UpdateLevelDict(LevelDict levelDict);
	List<LevelDict> GetAllData();
	List<LevelDict> SearchById(int id);
	List<LevelDict> SearchParameters(LevelDict levelDict);
}
