package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.service.CrmOrderToSAPService;
import com.jingtong.platform.sap.service.SAPService;
import com.jingtong.platform.sap.service.impl.CrmOrderToSAPServiceImpl;



public class CrmOrderToSAPAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708360048296344402L;

	private CrmOrderToSAPService crmOrderService;
 	private String crmOrderId;//订单号
 	private String ids; 
 	private SAPService sapService;
 	
	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCrmOrderId() {
		return crmOrderId;
	}

	public void setCrmOrderId(String crmOrderId) {
		this.crmOrderId = crmOrderId;
	}

	public CrmOrderToSAPService getCrmOrderService() {
		return crmOrderService;
	}
    
	public void setCrmOrderService(CrmOrderToSAPService crmOrderService) {
		this.crmOrderService = crmOrderService;
	}
    
	public void judgeCrmOrderToSAP(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=GBK");
	    PrintWriter pw = null;
	    String message="0;";
	    int counts=0;
		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		order.setIds(ids.split(","));
		List<CrmOrderItem> resultItem=crmOrderService.getCrmOrderItems(order);
		for(CrmOrderItem ci:resultItem){
			if(ci.getCreditId()==null||ci.getCreditId().equals("")||ci.getWerks()==null||ci.getWerks().equals("") ||ci.getLgort()==null||ci.getLgort().equals("")){
				message+=ci.getMatnr()+"仓库、库位或信用存在空值!是否确认? </br>";
				counts+=1;
				//break; 
			}
			ProductStock ps = this.sapService.getMaterialInventoryFromSap(ci.getMatnr(),ci.getWerks(),ci.getLgort()); 
			if(ps==null || ps.getStocknum()==0){
				message+=ci.getMatnr()+"SAP库存为空是否确认? </br>";
				counts+=1;
				//break; 
		  	}else{
		  		BigDecimal src = (new BigDecimal(ci.getKwmeng().toString())).setScale(4,BigDecimal.ROUND_HALF_UP);
				BigDecimal dst = (new BigDecimal(ps.getStocknum())).setScale(4,BigDecimal.ROUND_HALF_UP);
				int bd=src.compareTo(dst);
				if(bd>0){
					message+=ci.getMatnr()+"SAP库存不足是否确认? </br>";
					counts+=1;
					//break; 
				}
		  	}
		}
		//提取SAP库存
		
		
		if(counts==0){
			message="1;";//没有错误
		}
		try {
		pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   pw.write(message+"");
	   pw.close();
	}
	public void getCrmOrderToSAP(){
		
	   HttpServletResponse response = ServletActionContext.getResponse();
	   response.setContentType("text/html; charset=GBK");
       PrintWriter pw = null;
	
		String message="";
		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		List<CrmOrderToSap> orderList =crmOrderService.getCrmOrderTotal(order);
		order=orderList.get(0); 
		order.setIds(ids.split(","));
  		List<CrmOrderItem> items=crmOrderService.getCrmOrderItems(order);
 		if(null !=items && items.size()>0){
 			if("2".equals(order.getOrderType())){
 				   order.setSapOrderType(null);
 				   items=crmOrderService.getCrmOrderItems(order);
 	  			   if(null !=items &&items.size()>0){
	  				    order.setAuart("ZOR3");
 	 	                order.setSpart("01");

	 	 	 			for(int i=0;i<items.size();i++){
		 	 				if(items.get(i).getOutPrice()==0){
		 	 	 				items.get(i).setPstyv("TANN");
		 	  				}else{
		 	 	 				items.get(i).setPstyv("");
		 	   				}
		 	  			}
	 	  	 	 	    message=message+crmOrderService.crmOderToSap(order, items);
 	  		        }
 	 	 			
 			}else{
 				
 				
 				order.setSapOrderType("1");
 				//TANN,终端客户值为ZNN1,当为免值订单为KLN2
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){
 	  	 			order.setAuart("ZFD2");
 	  				for(int i=0;i<items.size();i++){
 	 					items.get(i).setPstyv("KLN2");
 	 				}
 	 				message=crmOrderService.crmOderToSap(order, items);
 	 	 		}
 	 			
 	 			order.setSapOrderType("0");
 				//TANN,终端客户值为ZNN1,当为免值订单为KLN2
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){

 	  	 			String vtweg =order.getVtweg();
 	 	 	 		if("10".equals(vtweg)){
 	 	 	 			
 	 	  	 			order.setAuart("ZOR1");
 	  	 	 			
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("TANN");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	  			}
 	 	 	 		}else{
 	 	  	 			order.setAuart("ZOR6");
 	 	                order.setSpart("00");
 	  	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("ZNN1");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	 			}
 	 	 	 		}
 	  	 	 	    message=message+crmOrderService.crmOderToSap(order, items) ;
 	  	 		}
 	 			
 	  			
 	  			order.setSapOrderType("2");
 				//洗涤滚动授信
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){
 	  	 			order.setAuart("ZTS1");
 	                order.setSpart("02");
 	  	 			String vtweg =order.getVtweg();
 	 	 	 		if("10".equals(vtweg)){
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("TANN");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	  			}
 	 	 	 		}else{
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("ZNN1");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	 			}
 	 	 	 		}
 	 				message=message+crmOrderService.crmOderToSap(order, items) ;
 	 	 		}
 	 			 
 			}
 			
 			order.setSapOrderType(null);
 			order.setIds(null);
   	  		List<CrmOrderItem> resultItem=crmOrderService.getCrmOrderItems(order);
            if(null !=resultItem && resultItem.size()>0){
            	order.setStatus("5");
            	crmOrderService.updateOrderStatus(order);
            	message="1||"+message;
            }else{
            	order.setStatus("10");
            	crmOrderService.updateOrderStatus(order);
            	message="2||"+message;
            }
  			
 			
 		}else{
 			message="0||没有需要提交的数据，请核实!";
 		}
 		
		
 		
 		
 		
 		
 	  
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   pw.write(message+"");
	   pw.close();
 	    
 	}
	
	public String crmOrderSoDelete(){
		this.setFailMessage("");
		this.setSuccessMessage("");
   		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		order.setSapOrderType(null);//(orderType)
		order.setIds(ids.split(","));
		List<CrmOrderItem> resultItem=crmOrderService.getDeleteCrmOrder(order);
		String msg ="";
		for(CrmOrderItem ci:resultItem){
			msg=msg+crmOrderService.crmOrderSoDelete(ci);
 		}
		this.setSuccessMessage(msg);
  		return RESULT_MESSAGE;
	}
	
	
	
	
	
	public String getCrmOrderToSAPTest(){
		

		CrmOrderToSap order = new CrmOrderToSap();
		/*order.setId("38824");//CRM_ID 38823
		order.setAuart("ZOR1"); //销售凭证类型
		order.setVkorg("2000"); //销售组织
		order.setVtweg("10");   //分销渠道
		order.setSpart("00");   //产品组
		order.setVkbur("1001"); //销售部门 中珠市场部
		order.setBzirk("100001"); //销售地区 华南
		order.setAudat("20150709");  //凭证日期 
		order.setBstkd("HN2014120215"); //客户采购订单号 HN2014120215
		order.setKunag("1000447");  //售达方
		order.setKunnr("1000447");  //送达方 1003886
		order.setKunrg("1000447");  //付款方
		order.setKunre("1000447");  //开票方
		order.setAbrvw("1");  //使用标示
		order.setPernr("10004");   //销售雇员
		order.setPrsdt("20150709");   //定价日期
		order.setText("客户仓库车辆限制高度3m");   //备注

		
		CrmOrderItem item = new CrmOrderItem();
 		item.setPosnr("1"); //项目号
		item.setMatnr("70000000126"); //物料号
		item.setKwmeng("10"); //数量
		item.setVrkme("PC");  //单位
		item.setCharg("");  //批号
		item.setLgort("0004");  //库存地
		item.setWerks("1000");  //工厂
		item.setFather("");  //父项目
		item.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1
		item.setPrice("-12");  //折扣价 当为促销品是值为负数=出货价-常规价
		item.setWaerk("CNY");  //货币 默认传入CNY
		item.setCreditId("2132");  //CRM信用号
		item.setText("需要最新的生产日期");  //CRM行项目文档
		item.setPromotionId("132");  //促销大方案号
		item.setPromoDetailId("232");  //促销子方案号

		CrmOrderItem item2 = new CrmOrderItem();
 		item2.setPosnr("20"); //项目号
		item2.setMatnr("10101047900"); //物料号
		item2.setKwmeng("2"); //数量
		item2.setVrkme("CAR");  //单位
		item2.setCharg("");  //批号
		item2.setLgort("1005");  //库存地
		item2.setWerks("1010");  //工厂
		item2.setFather("");  //父项目
		item2.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1
		item2.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item2.setWaerk("CNY");  //货币 默认传入CNY
		item2.setCreditId("2132");  //CRM信用号
		item2.setText("");  //CRM行项目文档
		item2.setPromotionId("");  //促销大方案号
		item2.setPromoDetailId("");  //促销子方案号
*/

	/*	order.setId("1200");//CRM_ID 38824
		order.setAuart("ZOR1"); //销售凭证类型
		order.setVkorg("2000"); //销售组织
		order.setVtweg("10");   //分销渠道
		order.setSpart("00");   //产品组
		order.setVkbur("1001"); //销售部门 中珠市场部
		order.setBzirk("100001"); //销售地区 华南
		order.setAudat("20150709");  //凭证日期 
		order.setBstkd("HN2014120215"); //客户采购订单号 HN2014120215
		order.setKunag("1000447");  //售达方
		order.setKunnr("1003886");  //送达方 1003886
		order.setKunrg("1000447");  //付款方
		order.setKunre("1000447");  //开票方
		order.setAbrvw("1");  //使用标示
		order.setPernr("10004");   //销售雇员
		order.setPrsdt("20150709");   //定价日期
		order.setText("客户仓库车辆限制高度3m");   //备注

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //项目号
		item.setMatnr("10101000115"); //物料号
		item.setKwmeng("4"); //数量
		item.setVrkme("CAR");  //单位
		item.setCharg("");  //批号
		item.setWerks("1000");  //工厂
		item.setLgort("0021");  //库存地		
		item.setFather("");  //父项目
		item.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1
		item.setPrice("-12");  //折扣价 当为促销品是值为负数=出货价-常规价
		item.setWaerk("CNY");  //货币 默认传入CNY
		item.setCreditId("2132");  //CRM信用号
		item.setText("需要最新的生产日期");  //CRM行项目文档
		item.setPromotionId("132");  //促销大方案号
		item.setPromoDetailId("232");  //促销子方案号

		CrmOrderItem item2 = new CrmOrderItem();
		item2.setPosnr("20"); //项目号
		item2.setMatnr("10101047900"); //物料号
		item2.setKwmeng("2"); //数量
		item2.setVrkme("CAR");  //单位
		item2.setCharg("");  //批号
		item2.setWerks("1000");  //工厂
		item2.setLgort("0021");  //库存地
		item2.setFather("");  //父项目
		item2.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1
		item2.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item2.setWaerk("CNY");  //货币 默认传入CNY
		item2.setCreditId("2132");  //CRM信用号
		item2.setText("");  //CRM行项目文档
		item2.setPromotionId("");  //促销大方案号
		item2.setPromoDetailId("");  //促销子方案号

		CrmOrderItem item3 = new CrmOrderItem();

		item3.setPosnr("30"); //项目号
		item3.setMatnr("70000000295"); //物料号
		item3.setKwmeng("1"); //数量
		item3.setVrkme("PC");  //单位
		item3.setCharg("");  //批号
		item3.setWerks("2000");  //工厂
		item3.setLgort("9050");  //库存地
		item3.setFather("");  //父项目
		item3.setPstyv("TANN");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1
		item3.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item3.setWaerk("CNY");  //货币 默认传入CNY
		item3.setCreditId("");  //CRM信用号
		item3.setText("");  //CRM行项目文档
		item3.setPromotionId("");  //促销大方案号
		item3.setPromoDetailId("");  //促销子方案号
*/
		
	/*	order.setId("20033");//CRM_ID 20033
		order.setAuart("ZFD2"); //销售凭证类型
		order.setVkorg("2000"); //销售组织
		order.setVtweg("10");   //分销渠道
		order.setSpart("00");   //产品组
		order.setVkbur("1001"); //销售部门 中珠市场部
		order.setBzirk("100001"); //销售地区 华南
		order.setAudat("20150710");  //凭证日期 
		order.setBstkd("HN39293"); //客户采购订单号 HN39293
		order.setKunag("1000447");  //售达方
		order.setKunnr("1003886");  //送达方
		order.setKunrg("1000447");  //付款方
		order.setKunre("1000447");  //开票方
		order.setAbrvw("3");  //使用标示  1增票  2普票 3不开票
		order.setPernr("10004");   //销售雇员
		order.setPrsdt("20150710");   //定价日期
		order.setText("免值商品与订单23333一同发货");   //备注

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //项目号
		item.setMatnr("10202016900"); //物料号
		item.setKwmeng("30000"); //数量
		item.setVrkme("PC");  //单位
		item.setCharg("");  //批号
		item.setWerks("2000");  //工厂
		item.setLgort("9010");  //库存地
		item.setFather("");  //父项目
		item.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item.setWaerk("CNY");  //货币 默认传入CNY
		item.setCreditId("");  //CRM信用号
		item.setText("需要最新的生产日期");  //CRM行项目文档
		item.setPromotionId("");  //促销大方案号
		item.setPromoDetailId("");  //促销子方案号

		CrmOrderItem item2 = new CrmOrderItem();

		item2.setPosnr("20"); //项目号
		item2.setMatnr("10201006600"); //物料号
		item2.setKwmeng("1750"); //数量
		item2.setVrkme("PC");  //单位
		item2.setCharg("");  //批号
		item2.setWerks("2000");  //工厂
		item2.setLgort("9010");  //库存地
		item2.setFather("");  //父项目
		item2.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item2.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item2.setWaerk("CNY");  //货币 默认传入CNY
		item2.setCreditId("");  //CRM信用号
		item2.setText("");  //CRM行项目文档
		item2.setPromotionId("");  //促销大方案号
		item2.setPromoDetailId("");  //促销子方案号

		CrmOrderItem item3 = new CrmOrderItem();

		item3.setPosnr("30"); //项目号
		item3.setMatnr("10201009600"); //物料号
		item3.setKwmeng("1750"); //数量
		item3.setVrkme("PC");  //单位
		item3.setCharg("");  //批号
		item3.setWerks("2000");  //工厂
		item3.setLgort("9010");  //库存地
		item3.setFather("");  //父项目
		item3.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item3.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item3.setWaerk("CNY");  //货币 默认传入CNY
		item3.setCreditId("");  //CRM信用号
		item3.setText("");  //CRM行项目文档
		item3.setPromotionId("");  //促销大方案号
		item3.setPromoDetailId("");  //促销子方案号

		CrmOrderItem item4 = new CrmOrderItem();

		item4.setPosnr("40"); //项目号
		item4.setMatnr("10206001000"); //物料号
		item4.setKwmeng("500"); //数量
		item4.setVrkme("PC");  //单位
		item4.setCharg("");  //批号
		item4.setWerks("2000");  //工厂
		item4.setLgort("");  //库存地
		item4.setFather("");  //父项目
		item4.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item4.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item4.setWaerk("CNY");  //货币 默认传入CNY
		item4.setCreditId("");  //CRM信用号
		item4.setText("");  //CRM行项目文档
		item4.setPromotionId("");  //促销大方案号
		item4.setPromoDetailId("");  //促销子方案号
		*/
		
		order.setId("200212");//CRM_ID 200212
		order.setAuart("ZTS1"); //销售凭证类型
		order.setVkorg("2000"); //销售组织
		order.setVtweg("10");   //分销渠道
		order.setSpart("00");   //产品组
		order.setVkbur("1001"); //销售部门 中珠市场部
		order.setBzirk("100001"); //销售地区 华南
		order.setAudat("20150723");  //凭证日期 
		order.setBstkd("HN3424"); //客户采购订单号 HN3424
		order.setKunag("1000447");  //售达方
		order.setKunnr("1003886");  //送达方
		order.setKunrg("1000447");  //付款方
		order.setKunre("1000447");  //开票方
		order.setAbrvw("3");  //使用标示  1增票  2普票 3不开票
		order.setPernr("10004");   //销售雇员
		order.setPrsdt("20150723");   //定价日期
		order.setText("");   //备注

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //项目号
		item.setMatnr("10900000244"); //物料号
		item.setKwmeng("2"); //数量
		item.setVrkme("CAR");  //单位
		item.setCharg("");  //批号
		item.setWerks("2000");  //工厂
		item.setLgort("9012");  //库存地
		item.setFather("");  //父项目
		item.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item.setWaerk("CNY");  //货币 默认传入CNY
		item.setCreditId("");  //CRM信用号
		item.setText("需要最新的生产日期");  //CRM行项目文档
		item.setPromotionId("");  //促销大方案号
		item.setPromoDetailId("");  //促销子方案号
		
		CrmOrderItem item2 = new CrmOrderItem();

		item2.setPosnr("20"); //项目号
		item2.setMatnr("10900000292"); //物料号
		item2.setKwmeng("10"); //数量
		item2.setVrkme("CAR");  //单位
		item2.setCharg("");  //批号
		item2.setWerks("2000");  //工厂
		item2.setLgort("9012");  //库存地
		item2.setFather("");  //父项目
		item2.setPstyv("");  //销售凭证项目类型  当物价价格为零时，流通客户值为TANN,终端客户值为ZNN1,当为免值订单为KLN2
		item2.setPrice("");  //折扣价 当为促销品是值为负数=出货价-常规价
		item2.setWaerk("CNY");  //货币 默认传入CNY
		item2.setCreditId("");  //CRM信用号
		item2.setText("");  //CRM行项目文档
		item2.setPromotionId("");  //促销大方案号
		item2.setPromoDetailId("");  //促销子方案号
		List<CrmOrderItem> items = new ArrayList<CrmOrderItem>();
		items.add(item);
		items.add(item2);
       
        String message=crmOrderService.crmOderToSap(order, items);
		
		return message;
		
	}
	
}
