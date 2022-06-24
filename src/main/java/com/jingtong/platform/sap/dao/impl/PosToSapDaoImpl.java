package com.jingtong.platform.sap.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.dao.PosToSapDao;
import com.jingtong.platform.sap.pojo.PosDetail;
import com.jingtong.platform.sap.pojo.PosToSap;

public class PosToSapDaoImpl extends BaseDaoImpl implements PosToSapDao{

	@Override
	public List<Pos> getPosTotal(Pos pos) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getPosTotal",pos);
	}

	@Override
	public int updatePosState(Pos pos) {
		return this.getSqlMapClientTemplate().update("sapReport.updateSapState",pos);
	}

	@Override
	public int updatePosClaimDealMark(Pos pos) {
		return this.getSqlMapClientTemplate().update("sapReport.updatePosClaimDealMark",pos);
	}

	@Override
	public int updateQuoteResQty(Pos pos) {
		return this.getSqlMapClientTemplate().update("sapReport.updateQuoteResQty",pos);
	}
//	@Override
	public List<Pos> getPosShipTo(Pos pos) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getPosShipTo",pos);
	}
	
	public Pos getPosById(Pos pos) {
		return (Pos) this.getSqlMapClientTemplate().queryForObject("sapReport.getPosById",pos);
	}
//
//	@Override
//	public int updatePosDetail(PosDetail posDetail) {
//		return this.getSqlMapClientTemplate().update("sapReport.updatePosDetail",posDetail);
//	}

	@Override
	public List<Pos> getLockmark(Pos pos) {
		return this.getSqlMapClientTemplate().queryForList("sapReport.getLockmark",pos);
	}

	@Override
	public void updateLockMark(Pos pos1) {
		this.getSqlMapClientTemplate().update("sapReport.updateLockMark",pos1);
	}

}
