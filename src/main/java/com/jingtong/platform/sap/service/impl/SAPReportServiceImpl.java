package com.jingtong.platform.sap.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.SAPReportDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.SAPReport;
import com.jingtong.platform.sap.service.SAPReportService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class SAPReportServiceImpl extends BasicService  implements SAPReportService {
	private JCoDestination dest;
	private ILogInfoDao logInfoDao;
	private SAPReportDao sapReportDao;
	
	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}

	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}

	public SAPReportDao getSapReportDao() {
		return sapReportDao;
	}

	public void setSapReportDao(SAPReportDao sapReportDao) {
		this.sapReportDao = sapReportDao;
	}

	@Override
	public List<SAPReport> getFinalReport(SAPReport sapReport) {
		List<SAPReport> reports = new ArrayList<SAPReport>();
		 try {
			//创建连接  
				this.connect("SAP");
				dest = JCoDestinationManager.getDestination("SAP");
				dest.ping();
				//链接接口 
				JCoFunction function = 	dest.getRepository().getFunction("Z_RFC_GET_PROMOTION_SALE");//url
				if (function == null){
					ExceptionLog log = new ExceptionLog();
					log.setInterfaceName("促销活动销售订单数据/Z_RFC_GET_PROMOTION_SALE");
					log.setOperateUser("SAP");
					log.setOperateTime(new Date());
					log.setLogDesc("失败！");
					log.setLogInfo("连接SAP失败!");
					this.logInfoDao.addLogInfo(log);
					throw new Exception("连接SAP失败!"); 
				}
				// 传入的参数
				function.getImportParameterList().setValue("P_PROMOTIONID", sapReport.getP_promotionId()); 
				function.getImportParameterList().setValue("P_SPART", sapReport.getP_spart());
				function.getImportParameterList().setValue("P_MVGR1", sapReport.getP_mvgr1());
				function.getImportParameterList().setValue("P_MATNR", sapReport.getP_matnr());
				function.getImportParameterList().setValue("P_KUNNR", sapReport.getP_kunnr());
				function.getImportParameterList().setValue("P_KWMENG",sapReport.getP_kwmeng());
				if(sapReport.getPbegin_Date()!=null && sapReport.getPend_Date()!=null){
					   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					   function.getImportParameterList().setValue("P_DATAB", (df.format(sapReport.getPbegin_Date()).toString()).replace("-", ""));
					   function.getImportParameterList().setValue("P_DATBI",(df.format(sapReport.getPend_Date()).toString()).replace("-", ""));
				}
//				function.getImportParameterList().setValue("P_DATAB", "2015-03-01".replace("-", ""));
//				function.getImportParameterList().setValue("P_DATBI","2015-03-31".replace("-", ""));
				if(sapReport.getS_promotionId().length()>0){
					JCoTable item = function.getTableParameterList().getTable("S_PROMOTIONID");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_promotionId());
				}
			   if(sapReport.getS_spart().length()>0){
			    	 JCoTable item = function.getTableParameterList().getTable("S_SPART");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_spart());
			     }
			   if(sapReport.getS_mvgr1().length()>0){
				   JCoTable item = function.getTableParameterList().getTable("S_MVGR1");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_mvgr1());
			   }
			   if(sapReport.getS_matnr().length()>0){
				   JCoTable item = function.getTableParameterList().getTable("S_MATNR");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_matnr());
			   }
			   if(sapReport.getS_kunnr().length()>0){
				   //多客户循环
				   JCoTable item = function.getTableParameterList().getTable("S_KUNNR");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_kunnr());
			   }
//			   if(sapReport.getPbegin_Date()!=null && sapReport.getPend_Date()!=null){
//				   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//					//String date = (df.format(new Date()).toString()).replace("-", "");// new Date()为获取当前系统时间
//				   JCoTable serdat = function.getTableParameterList().getTable("S_ERDAT");//读取高地位表
//				   serdat.appendRow(); //放入表内  
//				     //传参 
//				   serdat.setValue("SIGN", "I");
//				   serdat.setValue("OPTION", "BT");
//				   serdat.setValue("LOW", (df.format(sapReport.getPbegin_Date()).toString()).replace("-", ""));
//				   serdat.setValue("HIGH",(df.format(sapReport.getPend_Date()).toString()).replace("-", ""));
////				   serdat.setValue("LOW", "2015-03-01".replace("-", ""));
////				   serdat.setValue("HIGH","2015-03-31".replace("-", ""));
//			   }
				function.execute(dest); 
				//获取接口结果输出表
				JCoTable resultTbl = function.getTableParameterList().getTable("OT_PROMOTION"); 
				int rows = resultTbl.getNumRows();
				if(rows>0){
				  for(int i=0;i<rows;i++){
					try {
							resultTbl.setRow(i); 
							SAPReport report = new SAPReport();
							report.setKunnr(resultTbl.getString("KUNNR"));
							report.setPromotionId(resultTbl.getString("PROMOTIONID"));
							report.setSpart(resultTbl.getString("SPART"));
							report.setMvgr1(resultTbl.getString("MVGR1"));
							report.setMatnr(resultTbl.getString("MATNR"));
							report.setKwmeng(resultTbl.getString("KWMENG"));
							report.setVrkme(resultTbl.getString("VRKME"));
							report.setKlmeng(resultTbl.getString("KLMENG"));
							 DecimalFormat df=new DecimalFormat("#.#"); 
							 Double sl = resultTbl.getDouble("NETWR");
//							System.out.println(resultTbl.getDouble("NETWR")+"---"+df.format(sl)+"--====121122-----"+resultTbl.getFloat("NETWR"));
							report.setNetwr(df.format(sl)+"");
							report.setMaktx(resultTbl.getString("MAKTX"));
//							System.out.println(" NETWR: "+df.format(sl)+" SPART: "+resultTbl.getString("SPART")+" MVGR1:"+resultTbl.getString("MVGR1")+" MATNR:"+resultTbl.getString("MATNR")+" KWMENG:"+resultTbl.getString("KWMENG")+"  VRKME:"+resultTbl.getString("VRKME")+" KLMENG:"+resultTbl.getString("KLMENG"));
							reports.add(report);
					}catch (Exception e) {
							e.printStackTrace();
							ExceptionLog log = new ExceptionLog();
							log.setInterfaceName("促销活动销售订单数据/Z_RFC_GET_PROMOTION_SALE");
							log.setOperateUser("SAP");
							log.setOperateTime(new Date());
							log.setLogDesc("失败！");
							log.setLogInfo(e.getMessage());
							this.logInfoDao.addLogInfo(log);
							continue;
						}
					}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}
	
	public static void main(String[] args) {
		SAPReportServiceImpl pl = new SAPReportServiceImpl();
		SAPReport report = new SAPReport(); 
		report.setP_promotionId("");
		report.setP_spart("");
		report.setP_mvgr1("X");
		report.setP_matnr("X");
		report.setP_kunnr("");
		report.setP_kwmeng("X");
		report.setS_promotionId("");
		report.setS_spart("");
		report.setS_mvgr1("");
		report.setS_matnr("");
		report.setS_kunnr("");
		report.setPbegin_Date(new Date());
		report.setPend_Date(new Date());
		pl.getPromotionSale(report);
	}

	@Override
	public List<SAPReport> getPgroupRules(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getPgroupRules(promoId);
	}

	@Override
	public List<SAPReport> getMvgs() {
		// TODO Auto-generated method stub
		return this.sapReportDao.getMvgs();
	}

	@Override
	public List<SAPReport> getPromoDetails(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getPromoDetails(promoId);
	}

	@Override
	public SAPReport getPromotion(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getPromotion(promoId);
	}

	@Override
	public List<SAPReport> getpgRulesByPromoId(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getpgRulesByPromoId(promoId);
	}

	@Override
	public SAPReport getDisplayCost(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getDisplayCost(promoId);
	}

	@Override
	public HashMap<String, SAPReport> getPromotionSale(SAPReport sapReport) {
		HashMap<String, SAPReport> map = new HashMap<String, SAPReport>();
		List<SAPReport> reports = new ArrayList<SAPReport>();
		 try {
			//创建连接  
				this.connect("SAP");
				dest = JCoDestinationManager.getDestination("SAP");
				dest.ping();
				//链接接口 
				JCoFunction function = 	dest.getRepository().getFunction("Z_RFC_GET_PROMOTION_SALE");//url
				if (function == null){
					ExceptionLog log = new ExceptionLog();
					log.setInterfaceName("促销活动销售订单数据/Z_RFC_GET_PROMOTION_SALE");
					log.setOperateUser("SAP");
					log.setOperateTime(new Date());
					log.setLogDesc("失败！");
					log.setLogInfo("连接SAP失败!");
					this.logInfoDao.addLogInfo(log);
					throw new Exception("连接SAP失败!"); 
				}
				// 传入的参数
				function.getImportParameterList().setValue("P_PROMOTIONID", sapReport.getP_promotionId()); 
				function.getImportParameterList().setValue("P_SPART", sapReport.getP_spart());
				function.getImportParameterList().setValue("P_MVGR1", sapReport.getP_mvgr1());
				function.getImportParameterList().setValue("P_MATNR", sapReport.getP_matnr());
				function.getImportParameterList().setValue("P_KUNNR", sapReport.getP_kunnr());
				function.getImportParameterList().setValue("P_KWMENG",sapReport.getP_kwmeng());
				if(sapReport.getPurchasebegindate()!=null && sapReport.getPurchaseenddate()!=null){
					   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					   function.getImportParameterList().setValue("P_DATAB", (df.format(sapReport.getPurchasebegindate()).toString()).replace("-", ""));
					   function.getImportParameterList().setValue("P_DATBI",(df.format(sapReport.getPurchaseenddate()).toString()).replace("-", ""));
					  // System.out.println((df.format(sapReport.getPbegin_Date()).toString()).replace("-", "")+"--------"+(df.format(sapReport.getPend_Date()).toString()).replace("-", ""));
				}
//				function.getImportParameterList().setValue("P_DATAB", "2014-01-01".replace("-", ""));
//				function.getImportParameterList().setValue("P_DATBI","2015-03-31".replace("-", ""));
				if(sapReport.getS_promotionId().length()>0){
					JCoTable item = function.getTableParameterList().getTable("S_PROMOTIONID");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_promotionId());
				}
			   if(sapReport.getS_spart().length()>0){
			    	 JCoTable item = function.getTableParameterList().getTable("S_SPART");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_spart());
			     }
			   if(sapReport.getS_mvgr1().length()>0){
				   JCoTable item = function.getTableParameterList().getTable("S_MVGR1");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_mvgr1());
			   }
			   if(sapReport.getS_matnr().length()>0){
				   JCoTable item = function.getTableParameterList().getTable("S_MATNR");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_matnr());
			   }
			   if(sapReport.getS_kunnr().length()>0){
				   //多客户循环
				   JCoTable item = function.getTableParameterList().getTable("S_KUNNR");//读取高地位表
				     item.appendRow(); //放入表内  
				     //传参 
				     item.setValue("SIGN", "I");
				     item.setValue("OPTION", "EQ");
				     item.setValue("LOW", sapReport.getS_kunnr());
			   }
			   //传入年月的参数 (必填)
			   String dateStr = DateUtil.getMonth(sapReport.getPbegin_Date(), sapReport.getPend_Date());
			   String[] strs = dateStr.split(",");
//			   String[] strs = {"201401,201402,201403,201404,201405,201406,201407,201408,201409,201410,201411,201412,201501","201502","201503"};
			   if(strs.length>0){
				   JCoTable item = function.getTableParameterList().getTable("S_SPMON");//读取高地位表
				   for(int l=0;l<strs.length;l++){
					   item.appendRow(); //放入表内  
					     //传参 
					     item.setValue("SIGN", "I");
					     item.setValue("OPTION", "EQ");
					     item.setValue("LOW",strs[l].replace("-", "").trim());
				   }
			   }
				function.execute(dest); 
				//获取接口结果输出表
				JCoTable resultTbl = function.getTableParameterList().getTable("OT_PROMOTION"); 
				int rows = resultTbl.getNumRows();
				 DecimalFormat df=new DecimalFormat("#.#"); 
				 System.out.println(rows+"======");
				if(rows>0){
				  for(int i=0;i<rows;i++){
					try {
							resultTbl.setRow(i); 
							SAPReport report = new SAPReport();
							report.setKunnr(resultTbl.getString("KUNNR"));
							report.setPromotionId(resultTbl.getString("PROMOTIONID"));
							report.setSpart(resultTbl.getString("SPART"));
							report.setMvgr1(resultTbl.getString("MVGR1"));
							report.setMatnr(Long.parseLong(resultTbl.getString("MATNR"))+"");
							report.setKwmeng(resultTbl.getString("KWMENG"));
							report.setVrkme(resultTbl.getString("VRKME"));
							report.setKlmeng(resultTbl.getString("KLMENG"));
							Double sl = resultTbl.getDouble("NETWR");
							report.setNetwr(df.format(sl/10000)+"");
							report.setMaktx(resultTbl.getString("MAKTX"));
//							System.out.println(report.toString());
							map.put(report.getMatnr().trim(), report);
							reports.add(report);
					}catch (Exception e) {
							e.printStackTrace();
							ExceptionLog log = new ExceptionLog();
							log.setInterfaceName("促销活动销售订单数据/Z_RFC_GET_PROMOTION_SALE");
							log.setOperateUser("SAP");
							log.setOperateTime(new Date());
							log.setLogDesc("失败！");
							log.setLogInfo(e.getMessage());
							this.logInfoDao.addLogInfo(log);
							continue;
						}
					}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  map; 
	}

	@Override
	public List<SAPReport> getKunnrByProm(String promoId) {
		// TODO Auto-generated method stub
		return this.sapReportDao.getKunnrByProm(promoId);
	}
}