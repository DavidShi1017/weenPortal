package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.pojo.PosDetail;
import com.jingtong.platform.sap.pojo.PosToSap;



public interface PosToSapDao {
	
	public List<Pos> getPosTotal(Pos pos);
// 	public List<PosDetail> getPosDetail(PosToSap posToSap);
// 	public int updatePosDetail(PosDetail posDetail);
 	public int updatePosState(Pos pos);
	public int updateQuoteResQty(Pos p);

	public List<Pos> getPosShipTo(Pos pos);
	public Pos getPosById(Pos pos);
	public int updatePosClaimDealMark(Pos pos);
	public List<Pos> getLockmark(Pos pos);
	public void updateLockMark(Pos pos1);
}
