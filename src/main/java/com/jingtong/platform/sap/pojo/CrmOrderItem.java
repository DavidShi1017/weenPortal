package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class CrmOrderItem extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1547949356182026496L;
	private String mandt;//客户端 
	private String id;//ZCRM_SO_ID
	private String posnr;//项目号
	private String matnr;//物料号 
	private String kwmeng;//以销售单位表示的累计订单数量
	private String vrkme;//销售单位 
	private String charg;//批号
	private String lgort;//库存地点
	private String werks;//工厂
	private String father;//父项目号
	private String pstyv;//项目类型
	private String price;
	private double outPrice;
	private String waerk;//货币
	private String creditId;
	private String text;
	private String promotionId;
	private String promoDetailId;
	private String orderType;
	private String[] orderTypes;
 	private String sapOrderId;
 	private String sapRowNumber;
	private String rowType;//产品类型
	private String batch;
 	private String creditRange;
	private String receivable_id;
	private String kunnr;
	
	
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getSapRowNumber() {
		return sapRowNumber;
	}
	public void setSapRowNumber(String sapRowNumber) {
		this.sapRowNumber = sapRowNumber;
	}
	public String getCreditRange() {
		return creditRange;
	}
	public void setCreditRange(String creditRange) {
		this.creditRange = creditRange;
	}
	public String getReceivable_id() {
		return receivable_id;
	}
	public void setReceivable_id(String receivable_id) {
		this.receivable_id = receivable_id;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public String getSapOrderId() {
		return sapOrderId;
	}
	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}
	public double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}
	public String[] getOrderTypes() {
		return orderTypes;
	}
	public void setOrderTypes(String[] orderTypes) {
		this.orderTypes = orderTypes;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getPstyv() {
		return pstyv;
	}
	public void setPstyv(String pstyv) {
		this.pstyv = pstyv;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWaerk() {
		return waerk;
	}
	public void setWaerk(String waerk) {
		this.waerk = waerk;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromoDetailId() {
		return promoDetailId;
	}
	public void setPromoDetailId(String promoDetailId) {
		this.promoDetailId = promoDetailId;
	}
	public String getMandt() {
		return mandt;
	}
	public void setMandt(String mandt) {
		this.mandt = mandt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getKwmeng() {
		return kwmeng;
	}
	public void setKwmeng(String kwmeng) {
		this.kwmeng = kwmeng;
	}
	public String getVrkme() {
		return vrkme;
	}
	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}
	public String getCharg() {
		return charg;
	}
	public void setCharg(String charg) {
		this.charg = charg;
	}
	public String getLgort() {
		return lgort;
	}
	public void setLgort(String lgort) {
		this.lgort = lgort;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
}
