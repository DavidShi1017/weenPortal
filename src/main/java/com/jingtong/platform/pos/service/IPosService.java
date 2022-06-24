package com.jingtong.platform.pos.service;

import java.util.List;

import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public interface IPosService {

    /***
     * 新增POS数据
     * 
     * @param pos
     * @return
     */
    public long createPosInfo(Pos pos);

    /***
     * 查询数量
     * 
     * @param pos
     * @return
     */
    public int searchPosListCount(Pos pos);

    /***
     * POS列表
     * 
     * @param pos
     * @return
     */
    public List<Pos> searchPosList(Pos pos);

    public Pos getquote(Pos pos);

    public List<Pos> getPosDebitNumber(Pos pos);

    public Pos getquoListBydebitNumBook(Pos pos);

    public double getMinPrice(Pos pos);

    public List<Pos> searchPosListByPos(Pos pos);

    public int searchCustomerCount(Pos pos);

    public int searchDRCount(Pos pos);

    public int searchRelationshipCount(Pos pos);

    public int searchProductCount(Pos pos);

    public int searchDistiBranch(Pos pos);

    public int getQuoteInfoCount(Pos pos);

    public int getQuoteDetailCount(Pos pos);

    public int getReQuoteDetailCount(Pos pos);

    public Pos getDistiName(Pos pos);

    public int searchEndCustomerCount(Pos pos);

    public List<ECAlias> searchEcList(Pos pos);
//	public ECAlias searchEcList(Pos pos);

    public int getDictCount(Pos pos);

    public int updatePos(Pos pos);

    public int updatePosTips(Pos pos);

    public List<QuoteDetail> searchQuoteList(Pos pos);

    // 获取file_id

    public long getFileId();

    // 二级查询页面
    public int searchPosDetailListCountById(Pos pos);

    public List<Pos> searchPosDetailListById(Pos pos);

    public List<Pos> searchPosDetailListByIdForOne(Pos pos);

    // 编辑PCEC
    public int updatePCEC(Pos pos);

    public Pos getPosById(Pos pos);

    // 获取status_num
    public int searchPosDetailListCount(Pos pos);

    public int searchPosDetailListCountForError(Pos pos);

    // 获取file_id(claim)

    public long getClaimFileId();

    public int searchPosListCountForAll(Pos pos);

    public List<Pos> searchPosListForAll(Pos pos);

    // 检查EDI数据
    public int searchPosListCountForEDI(Pos pos);

    public List<Pos> searchPosListForEDI(Pos pos);

    public int searchPosFileIdCount(Pos pos);

    public List<Integer> searchPosFileId(Pos pos);

    // EDI单条检查（根据ID）
    public int searchPosListCountByIds(Pos pos);

    public List<Pos> searchPosListByIds(Pos pos);

    // 重置状态为9
    public int resetPos(Pos pos);

    public int approvePos(Pos pos);

    public int rejectPos(Pos pos);

    public double getPassedQty(Pos pos);
    public List<Pos> getPassedQtys();

    // POS报表
    public int searchPosListCountForBb(Pos pos);

    public List<Pos> searchPosListForBb(Pos pos);

    // POS报表数据下载（不分页）
    public List<Pos> searchPosListForBbAll(Pos pos);

    // Claim报表
    public int searchClaimListCountForBb(Pos pos);

    public List<Pos> searchClaimListForBb(Pos pos);

    // Claim报表数据下载（不分页）
    public List<Pos> searchClaimListForBbAll(Pos pos);

    /**
     * 到处原始数据报表
     * 
     * @param pos
     * @return
     */
    public int searchPosTrackingDetailCount(Pos pos);

    public List<Pos> searchPosTrackingDetail(Pos pos);

    public List<Pos> searchPosTrackingDetailNoPage(Pos pos);

    public void checkEDI(Long id) throws Exception;

    public void claimCheckEDI() throws Exception;

    public void claimCheckEDI(Pos pos) throws Exception;

    public Pos getPosByfileId(Pos pos);

    public Pos searchPosByIdForOneCheck(Pos pos);

    List<Pos> searchPosListForPosCheckInvoice(Pos pos);

    public List<Pos> getClaimErrorCodeDetail(Pos pos);

    public List<Pos> searchPosDetailList6q6t(Pos pos);

    public int searchPosDetailListCount6q6t(Pos pos);
    
    public List<Pos> getPosByfileIds(Pos pos);
}
