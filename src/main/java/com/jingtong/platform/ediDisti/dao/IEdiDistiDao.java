package com.jingtong.platform.ediDisti.dao;

import java.util.List;

import com.jingtong.platform.ediDisti.pojo.EdiDisti;

public interface IEdiDistiDao {
	
	
	public List<EdiDisti> getEdiDistiList(EdiDisti ed);
	public int getEdiDistiListCount(EdiDisti ed);
	public long createEdiDisti(EdiDisti ed);
	public long deleteEdiDisti(EdiDisti ed);

}
