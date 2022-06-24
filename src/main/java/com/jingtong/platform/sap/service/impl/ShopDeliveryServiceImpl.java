package com.jingtong.platform.sap.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.SAPDao;
import com.jingtong.platform.sap.dao.ShopDeliveryDao;
import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.pojo.ShopDelivery;
import com.jingtong.platform.sap.service.ShopDeliveryService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class ShopDeliveryServiceImpl extends BasicService implements ShopDeliveryService {
	private JCoDestination dest;
	private ILogInfoDao logInfoDao;
	private ShopDeliveryDao shopDeliveryDao;
	private SAPDao sapDao;
	 
	 

	public SAPDao getSapDao() {
		return sapDao;
	}

	public void setSapDao(SAPDao sapDao) {
		this.sapDao = sapDao;
	}

	

	public ShopDeliveryDao getShopDeliveryDao() {
		return shopDeliveryDao;
	}

	public void setShopDeliveryDao(ShopDeliveryDao shopDeliveryDao) {
		this.shopDeliveryDao = shopDeliveryDao;
	}

	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}

	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}
	
	@Override
	public boolean autoGetCusDownFromSap() {
		return this.getCusDownFromSap(null, null, null);
	}
	
	

	/**
	 *  
	 * @return
	 */
	@Override
	public boolean getDeliveryFromSap() {
		System.out.println("-----开始调用交货单接口----------");
		boolean errorFlag = false;
		try {
			//创建连接  
			this.connect("SAP");
			dest = JCoDestinationManager.getDestination("SAP");
			dest.ping();
			//链接接口 
			JCoFunction function = 	dest.getRepository().getFunction("ZCRM_DN_GET");//url
			if (function == null){
				ExceptionLog log = new ExceptionLog();
				log.setInterfaceName("交货单同步/ZCRM_DN_GET");
				log.setOperateUser("SAP");
				log.setOperateTime(new Date());
				log.setLogDesc("失败！");
				log.setLogInfo("连接SAP失败!");
				this.logInfoDao.addLogInfo(log);
				errorFlag=true;
				throw new Exception("连接SAP失败!"); 
			}
		     //获取接口传入参数表
	//		if(punit.trim().length()>0){
	//			function.getImportParameterList().setValue("P_UNIT", punit);
	//		}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		    Date date = new Date();
		    Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(date); 
			//获取接口传入参数表
			JCoTable item = function.getTableParameterList().getTable("S_ERDAT");//创建日期
//	         JCoTable item = function.getTableParameterList().getTable("S_WADAT_IST");//发货过账日期
			 item.appendRow(); //放入表内  
			 //传参
			 item.setValue("SIGN", "I");
			 item.setValue("OPTION", "EQ");
			 String dateB = (df.format(new Date()).toString()).replace("-", "");// new Date()为获取当前系统时间
 			// calendar.add(Calendar.DAY_OF_MONTH, -100); 
			 item.setValue("LOW", dateB);//低位
			// calendar.add(Calendar.DAY_OF_MONTH, -3);
			 item.setValue("HIGH", dateB);//高位
	        
		/*	JCoTable svbeln = function.getTableParameterList().getTable("S_VBELN");//交货单号
            //查询一个交行单号，LOW 不能传入空，传入空的话认为查询空
			svbeln.appendRow(); //放入表内
			svbeln.setValue("SIGN","I");
			svbeln.setValue("OPTION","EQ");
			svbeln.setValue("LOW","80433602");
			 */
			function.execute(dest); 
			//获取接口结果输出表
			JCoTable resultTbl = function.getTableParameterList().getTable("T_OUT"); 
			int rows = resultTbl.getNumRows();
			if(rows>0){
				for(int i=0;i<rows;i++){
					resultTbl.setRow(i);
					ShopDelivery del = new ShopDelivery();
					del.setCrmOrderId(resultTbl.getString("ID"));
					del.setSapOderId(resultTbl.getString("VGBEL"));
					del.setVgpos(resultTbl.getString("VGPOS"));//sap行项目号
					del.setKunnr_ag(resultTbl.getString("KUNAG"));// 售达方
					del.setKunnr_we(resultTbl.getString("KUNNR"));// 送达方
					del.setSendAddress(resultTbl.getString("STRAS"));// 送货地址
					del.setLinkMan(resultTbl.getString("FULLNAME"));// 联系人(什么联系人)
					del.setLinkPhone(resultTbl.getString("TELF1"));// 联系电话
					del.setBillId("");// 开票凭证
					del.setPlanToDate(null);// 计划到货日期
//					del.setPlanLoadDate(PropUtil.getStringToDate(resultTbl.getString("TDDAT")));// 计划装运日期
					del.setPlanLoadDate(new SimpleDateFormat("yyyy/MM/dd").parse(resultTbl.getString("TDDAT").replaceAll("-", "/")));// 计划装运日期
					del.setRealSendDate(null);// 实际发货日期
					//CREDIT_ID ---- CRM信用号
					//OUT_MONEY  ---- 交货金额

					del.setCreditId(resultTbl.getString("CREDIT_ID"));// 信用编码
					del.setLoadPointId("VSTEL");// 装运点
					del.setShipAgent("LIFNR");// 货运代理 
					del.setVbeln(resultTbl.getString("VBELN"));// 交货单号
					del.setErdat(resultTbl.getString("ERDAT").replaceAll("-", "/"));
					del.setPosnr(resultTbl.getString("POSNR"));//行号
					del.setMatnr(resultTbl.getString("MATNR"));
					del.setWerks(resultTbl.getString("WERKS"));//工厂
					del.setLgort(resultTbl.getString("LGORT"));//库存地
					//del.setArktx(resultTbl.getString("ARKTX"));//销售订单项目短文本
					del.setBrgew(resultTbl.getString("BRGEW"));
					del.setVolum(resultTbl.getString("VOLUM"));
					del.setPrice(resultTbl.getString("OUT_MONEY"));
					del.setLfimg(resultTbl.getString("LFIMG"));//实际已交货量（大单位）
					del.setVrkme(resultTbl.getString("VRKME"));//销售单位（大单位）
					del.setDele("");
  			        del.setStatus("0");
  			        double lf = Double.parseDouble(del.getLfimg());
  			        if("".equals(del.getCrmOrderId()) || "".equals(del.getCreditId()) || lf==0 ){
  			        	continue;
  			        }
  			        
					int rs = this.shopDeliveryDao.getDeliverys(del);
					if(rs>0){
						 this.shopDeliveryDao.updateDelivery(del);
					}else{
						long deliId =this.shopDeliveryDao.insertDelivery(del);
						 
						
						
						del.setVbeln(deliId+"");
						shopDeliveryDao.updateOrderPrc(del);
 						
					}
				}
				
				List<Accrual> creDelList =new ArrayList<Accrual>();
				creDelList=shopDeliveryDao.getCreditByOrder();
				if(null !=creDelList && creDelList.size()>0){
					for(int j=0;j<creDelList.size();j++){
						Accrual acc =new Accrual();
	 					acc =creDelList.get(j);
	 					if(null==acc.getRepayMentDate()||"".equals(acc.getRepayMentDate())){
 	 						acc.setRepayMentDate(DateUtil.addDays(acc.getUseDate(),acc.getRepaymentDays()));
	 					}
	 					shopDeliveryDao.insetAccrual(acc);
 					}
					shopDeliveryDao.updateAccrual();
					errorFlag = true;
 				}
				
 			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLog log = new ExceptionLog();
			log.setInterfaceName("交货单同步/Z_RFC_GET_SHIPPING_LIST");
			log.setOperateUser("SAP");
			log.setOperateTime(new Date());
			log.setLogDesc("失败！");
			log.setLogInfo(e.getMessage());
			try {
				this.logInfoDao.addLogInfo(log);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			errorFlag=false;
		}
		 return errorFlag;
	}

	
	 
	
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
//        calendar.add(Calendar.DAY_OF_MONTH, -2);  
//        System.out.println("==="+df.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 0);  
        System.out.println("==="+df.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 3);  
        System.out.println("==="+df.format(calendar.getTime()));
        
        ShopDeliveryServiceImpl p = new ShopDeliveryServiceImpl();
        p.getDeliveryFromSap();
	}

	@Override
	public void updateinfoPro(Map promap) {
		this.shopDeliveryDao.updateinfoPro(promap);
	}

	@Override
	public boolean getCusDownFromSap(Date begda, Date endda, String kunnr) {
		// TODO Auto-generated method stub
		return false;
	} 
}
