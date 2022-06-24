package com.jingtong.platform.pos.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.ediDisti.pojo.EdiDisti;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.dao.IPosDao;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public class PosDaoImpl extends BaseDaoImpl implements IPosDao {

	@Override
	public long createPosInfo(Pos pos) {
		return (Long) this.getSqlMapClientTemplate().insert("pos.createPosInfo", pos);
	}

	@Override
	public int searchPosListCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCount", pos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pos> searchPosList(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosList", pos);
	}

	@Override
	public Pos getquote(Pos pos) {
		return (Pos) this.getSqlMapClientTemplate().queryForObject("pos.getquote", pos);

	}

	@Override
	public List<Pos> getPosDebitNumber(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.getPosDebitNumber", pos);
	}

	@Override
	public Pos getquoListBydebitNumBook(Pos pos) {
		return (Pos) this.getSqlMapClientTemplate().queryForObject("pos.getquoListBydebitNumBook", pos);
	}

	@Override
	public double getMinPrice(Pos pos) {
		return (Double) this.getSqlMapClientTemplate().queryForObject("pos.getMinPrice", pos);
	}

	@Override
	public List<Pos> searchPosListByPos(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListByPos", pos);

	}

	@Override
	public int searchCustomerCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchCustomerCount", pos);

	}

	@Override
	public int searchRelationshipCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchRelationshipCount", pos);

	}

	@Override
	public int searchProductCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchProductCount", pos);

	}

	@Override
	public int getQuoteInfoCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.getQuoteInfoCount", pos);
	}

	@Override
	public int getQuoteDetailCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.getQuoteDetailCount", pos);
	}

	@Override
	public int getReQuoteDetailCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.getReQuoteDetailCount", pos);

	}

	@Override
	public int searchEndCustomerCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchEndCustomerCount", pos);
	}

	@Override
	public int getDictCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.getDictCount", pos);
	}

	@Override
	public int searchPosDetailListCountById(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCountById", pos);
	}

	@Override
	public List<Pos> searchPosDetailListById(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListById", pos);
	}

	@Override
	public List<Pos> searchPosDetailListByIdForOne(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListByIdForOne", pos);
	}

	@Override
	public long getFileId() {
		return (Long) getSqlMapClientTemplate().queryForObject("pos.getFileId");
	}

	@Override
	public int searchPosDetailListCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosDetailListCount", pos);
	}

	@Override
	public int searchPosDetailListCountForError(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosDetailListCountForError", pos);
	}

	@Override
	public int updatePos(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.updatePos", pos);
	}

	@Override
	public int updateClaimEcInfo(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.updateClaimEcInfo", pos);
	}

	@Override
	public long getClaimFileId() {
		return (Long) getSqlMapClientTemplate().queryForObject("pos.getClaimFileId");
	}

	@Override
	public int searchPosListCountForAll(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCountForAll", pos);
	}

	@Override
	public List<QuoteDetail> searchQuoteList(Pos pos) {
		return (List<QuoteDetail>) this.getSqlMapClientTemplate().queryForList("pos.searchQuoteList", pos);
	}

	@Override
	public double getPassedQty(Pos pos) {
		return (Double) this.getSqlMapClientTemplate().queryForObject("pos.getPassedQty", pos);
	}
	
	@Override
	public List<Pos> getPassedQtys() {
	    return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.getPassedQtys");
	}

	@Override
	public List<Pos> searchPosListForAll(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListForAll", pos);
	}

	@Override
	public List<Pos> searchPosListForPosCheckInvoice(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListForPosCheckInvoice", pos);
	}

	@Override
	public int searchDRCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchDRCount", pos);
	}

	@Override
	public int searchPosListCountForEDI(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCount", pos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pos> searchPosListForEDI(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListForEDI", pos);
	}

	@Override
	public int updatePosTips(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.updatePosTips", pos);
	}

	@Override
	public List<Integer> searchPosFileId(Pos pos) {
		return (List<Integer>) this.getSqlMapClientTemplate().queryForList("pos.searchPosFileId", pos);
	}

	@Override
	public int searchPosFileIdCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosFileIdCount", pos);
	}

//    @Override
//    public ECAlias searchEcList(Pos pos) {
//        return (ECAlias)this.getSqlMapClientTemplate().queryForObject("pos.searchEcList",pos);
//        return (List<ECAlias>)this.getSqlMapClientTemplate().queryForList("pos.searchEcList",pos);
//
//    }

	@Override
	public List<ECAlias> searchEcList(Pos pos) {
		return (List<ECAlias>) this.getSqlMapClientTemplate().queryForList("pos.searchEcList", pos);

	}

	@Override
	public int searchDistiBranch(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchDistiBranch", pos);
	}

	@Override
	public int searchPosListCountByIds(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCountByIds", pos);
	}

	@Override
	public List<Pos> searchPosListByIds(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListByIds", pos);
	}

	@Override
	public int searchPosListCountForBb(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosListCountForBb", pos);
	}

	@Override
	public List<Pos> searchPosListForBb(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListForBb", pos);
	}

	@Override
	public int searchClaimListCountForBb(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchClaimListCountForBb", pos);
	}

	@Override
	public List<Pos> searchClaimListForBb(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchClaimListForBb", pos);
	}

	@Override
	public List<Pos> searchPosListForBbAll(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosListForBbAll", pos);
	}

	@Override
	public List<Pos> searchClaimListForBbAll(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchClaimListForBbAll", pos);
	}

	@Override
	public Pos getDistiName(Pos pos) {
		return (Pos) this.getSqlMapClientTemplate().queryForObject("pos.getDistiName", pos);
	}

	@Override
	public Pos getClaimDistiName(Pos pos) {
		return (Pos) this.getSqlMapClientTemplate().queryForObject("pos.getClaimDistiName", pos);
	}

	@Override
	public List<Pos> searchPosTrackingDetail(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosTrackingDetail", pos);
	}

	@Override
	public int searchPosTrackingDetailCount(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosTrackingDetailCount", pos);
	}

	@Override
	public List<Pos> searchPosTrackingDetailNoPage(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosTrackingDetailNoPage", pos);
	}

	@Override
	public int resetPos(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.resetPos", pos);
	}

	@Override
	public int approvePos(Pos pos) {
		if ("0".equals(pos.getStatus())) {
			return (Integer) getSqlMapClientTemplate().update("pos.approvePos", pos);
		} else {
			return (Integer) getSqlMapClientTemplate().update("pos.approvePosTipsSuccess", pos);
		}
	}

	@Override
	public int rejectPos(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.rejectPos", pos);
	}

	@Override
	public int updatePCEC(Pos pos) {
		return (Integer) getSqlMapClientTemplate().update("pos.updatePCEC", pos);
	}

	@Override
	public Pos getPosById(Pos pos) {
		return (Pos) getSqlMapClientTemplate().queryForObject("pos.getPosById", pos);
	}

	@Override
	public Pos getPosByfileId(Pos pos) {
		return (Pos) getSqlMapClientTemplate().queryForObject("pos.getPosByfileId", pos);
	}

	@Override
	public Pos searchPosByIdForOneCheck(Pos pos) {
		return (Pos) getSqlMapClientTemplate().queryForObject("pos.searchPosByIdForOneCheck", pos);
	}

	@Override
	public List<ECAlias> searchEcMasterName(Pos pos) {
		return (List<ECAlias>) this.getSqlMapClientTemplate().queryForList("pos.searchEcMasterName", pos);
	}

	@Override
	public List<EndCustomer> searchEcMasterNameByEcId(Pos pos) {
		return (List<EndCustomer>) this.getSqlMapClientTemplate().queryForList("pos.searchEcMasterNameByEcId", pos);
	}

	@Override
	public List<Pos> searchPosDetailList6q6t(Pos pos) {
		return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.searchPosDetailList6q6t", pos);
	}

	@Override
	public int searchPosDetailListCount6q6t(Pos pos) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("pos.searchPosDetailListCount6q6t", pos);
	}

	@Override
	public List<Pos> getPosByfileIds(Pos pos) {
	    return (List<Pos>) this.getSqlMapClientTemplate().queryForList("pos.getPosByfileIds", pos);
	}
	
    @Override
    public List<Disti_branch> getDistiAliasList(Disti_branch db) {
        return getSqlMapClientTemplate().queryForList("customer.getDistiAliasByName", db);
    }
}
