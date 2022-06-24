package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.pojo.PosDetail;
import com.jingtong.platform.sap.pojo.PosToSap;



public interface PosToSapService {
	public String posToSap(List<Pos> posList);
	
	
 	public List<Pos> getPosTotal(Pos pos);
 	
 	public List<PosToSap> getPosToSapTotal(PosToSap pos);
 	public List<PosDetail> getPosDetail(PosToSap pos);


	String posToSapA(PosToSap pos, List<Pos> posList);


	String posToSap(PosToSap pos, List<Pos> posList);


	String posToSapB(List<Pos> posList) throws Exception;


	public List<Pos> getLockmark(Pos pos);


	public void updateLockMark(Pos pos1);
}
