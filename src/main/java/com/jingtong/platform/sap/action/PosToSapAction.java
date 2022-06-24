package com.jingtong.platform.sap.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.JsonUtil;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.pojo.PosToSap;
import com.jingtong.platform.sap.service.PosToSapService;
import com.jingtong.platform.sap.service.SAPService;

public class PosToSapAction extends BaseAction {

	private PosToSapService posToSapService;
	private long id;
	private String type_id;
	private String ship_to;
	private String branch_id;
	private String payer_to;
	private String sale_to;
	private String currency_code;
	private String billing_to;
	private String order_id;

	private SAPService sapService;
	private String repaymentDetailJson;
	private List<Pos> posList;
	private double rebateTotal;

	public PosToSapService getPosToSapService() {
		return posToSapService;
	}

	public void setPosToSapService(PosToSapService posToSapService) {
		this.posToSapService = posToSapService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public double getRebateTotal() {
		return rebateTotal;
	}

	public void setRebateTotal(double rebateTotal) {
		this.rebateTotal = rebateTotal;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getShip_to() {
		return ship_to;
	}

	public void setShip_to(String ship_to) {
		this.ship_to = ship_to;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getPayer_to() {
		return payer_to;
	}

	public void setPayer_to(String payer_to) {
		this.payer_to = payer_to;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getBilling_to() {
		return billing_to;
	}

	public void setBilling_to(String billing_to) {
		this.billing_to = billing_to;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getRepaymentDetailJson() {
		return repaymentDetailJson;
	}

	public void setRepaymentDetailJson(String repaymentDetailJson) {
		this.repaymentDetailJson = repaymentDetailJson;
	}

	public List<Pos> getPosList() {
		return posList;
	}

	public void setPosList(List<Pos> posList) {
		this.posList = posList;
	}

	private static final long serialVersionUID = -3599908969412005618L;
	
	private StringResult stringResult;
	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}
/**
 * 查询所有，一行作为一单  B
 * @return
 * @throws Exception 
 */
		@PermissionSearch
		@JsonResult(field = "stringResult", include = { "result"})
		public String getPosToSAPB() throws Exception {
			stringResult=new StringResult();		
			String message = "";

			try {
				repaymentDetailJson = java.net.URLDecoder.decode(
						this.repaymentDetailJson, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			posList = JsonUtil.getDTOList(repaymentDetailJson, Pos.class);

			//合并订单xcfeng 20170527 思路 先把相同合并，然后再取一次获得对应的明细
			List<Pos> posListNew = new ArrayList<Pos>();
			List<Pos> detailPosList = new ArrayList<Pos>();
			for (int i=0 ;i <posList.size() ; i++){
				Pos pos = posList.get(i); 
				//如果pos的payer to 和 disti claimnr相同就合并为一个订单
				Pos newpos  = new Pos();
				newpos.setDisti_branch(pos.getDisti_branch());
				newpos.setDisti_claimnbr(pos.getDisti_claimnbr());
				newpos.setDisti_cost_currency(pos.getDisti_cost_currency());
				newpos.setDebit_number(pos.getDebit_number());
				posListNew.add(newpos);			
			}
			
			removeDuplicate(posListNew);
			
			for (Pos newPos : posListNew){
				newPos.setDetailPosList(getDetailList(newPos ,posList));
			}
			
			String ss ="";
			try {
				//获得lock标准(用这个表BASIS_TB_CUS_POS_MN的type字段作为锁定标志)
				Pos pos = new Pos();
				pos.setId(1L);
				pos.setType("1");
				System.out.println("查询锁定表");
				List<Pos> lipos = posToSapService.getLockmark(pos);
				if (lipos.size() > 0){
					ss ="Create an order repeatedly. Please wait a moment and then recreate the order...........";
				}else{//更新lock标志
					Pos pos1 = new Pos();
					pos1.setId(1L);
					pos1.setType("1");
					System.out.println("更新锁定表为1");
					posToSapService.updateLockMark(pos1);
					try {
						ss =  posToSapService.posToSapB(posListNew);
					} catch (Exception e) {
						e.printStackTrace();
						//释放lock标志
						Pos pos3 = new Pos();
						pos3.setId(1L);
						pos3.setType("0");
					    posToSapService.updateLockMark(pos3);
					}
					Pos pos2 = new Pos();
					pos2.setId(1L);
					pos2.setType("0");
					System.out.println("更新锁定表为0");
					posToSapService.updateLockMark(pos2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			message = message +ss;
		
			//释放lock标志
			stringResult.setResult(message);	
			return JSON;
		}	
	
	
/**
 * 查询所有，相同Disti作为一单
 * @return
 */
	@PermissionSearch
	@JsonResult(field = "stringResult", include = { "result"})
	public String getPosToSAP() {
		stringResult=new StringResult();		
		String message = "";
		
		try {
			repaymentDetailJson = java.net.URLDecoder.decode(
					this.repaymentDetailJson, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		posList = JsonUtil.getDTOList(repaymentDetailJson, Pos.class);
		message = message + posToSapService.posToSap(posList);
		// if(message ="成功"){
		// for(int i=0;i<posList.size();i++){
		// //状态+可用数量+凭证号
		//
		//
		// //询价单+物料----》更新这条数据的可用量
		//
		// }getDisti_claimnbr
		// }
		//
		stringResult.setResult(message);	
		return JSON;
	}
	public   void  removeDuplicate(List<Pos> list)  {
		   for  ( int  i  =   0 ; i  <  list.size() - 1 ; i ++ )  {
			    for  ( int  j  =  list.size() - 1 ; j  >  i; j -- )  {
			      if  (list.get(j).getDisti_branch().equals(list.get(i).getDisti_branch()) && 
			    		  list.get(j).getDisti_claimnbr().equals(list.get(i).getDisti_claimnbr())   )  {
			           list.remove(j);
			      } 
			    } 
			  } 
			  System.out.println(list);
	}
	public List<Pos> getDetailList(Pos pos,List<Pos> li){
		List<Pos> lidetail = new ArrayList<Pos>();
		for (Pos p :li){
			if (p.getDisti_branch().equals(pos.getDisti_branch()) && p.getDisti_claimnbr().equals(pos.getDisti_claimnbr())){
				lidetail.add(p);
			}
		}
		return lidetail ;
	}

	/**
	 * 单个Disti查询，同步  Claim Order   A
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stringResult", include = { "result"})
	public String getPosToSAPA() {

		stringResult=new StringResult();		
		String message = "";
		PosToSap pos = new PosToSap();
		pos.setId(id);
		pos.setType_id(type_id);
		pos.setShip_to(ship_to);
		pos.setPayer_to(payer_to);
		pos.setSale_to(sale_to);
		pos.setCurrency_code(currency_code);
		pos.setBilling_to(billing_to);
		pos.setOrder_id(order_id);
		pos.setPrice(rebateTotal);

		try {
			repaymentDetailJson = java.net.URLDecoder.decode(
					this.repaymentDetailJson, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		posList = JsonUtil.getDTOList(repaymentDetailJson, Pos.class);
		message = message + posToSapService.posToSapA(pos, posList);
		stringResult.setResult(message);	
		return JSON;
	}

	public void getPosToSapTest() {
		this.setSuccessMessage("");
		String message = "";
		PosToSap pos = new PosToSap();
		message = message + posToSapService.posToSapA(pos, posList);
	}

	public String getSale_to() {
		return sale_to;
	}

	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
	}
	

}
