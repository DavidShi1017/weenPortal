package com.jingtong.platform.sap.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.sap.dao.ChannelPriceDao;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.ChannelPrice;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.service.ChannelPriceService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class ChannelPriceServiceImpl extends BasicService implements ChannelPriceService{

	private JCoDestination dest;
	private ChannelPriceDao channelPriceDao;
	private ILogInfoDao logInfoDao;
	
	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}

	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}

	public ChannelPriceDao getChannelPriceDao() {
		return channelPriceDao;
	}

	public void setChannelPriceDao(ChannelPriceDao channelPriceDao) {
		this.channelPriceDao = channelPriceDao;
	}

	@Override
	public String getChannelPriceFromSAP() {
		try {
			//创建连接  
			System.out.println("---------kaishi-------------");
				this.connect("SAP");
				dest = JCoDestinationManager.getDestination("SAP");
				dest.ping();
				//链接接口 
				JCoFunction function = 	dest.getRepository().getFunction("Z_RFC_GET_MATERIAL_PRICE");//url
				if (function == null){
					System.out.println("Z_RFC_GET_MATERIAL_PRICE :  链接SAP失败！");
					throw new Exception("连接SAP失败!"); 
				}else{
					System.out.println("Z_RFC_GET_MATERIAL_PRICE :  链接SAP成功！");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				String date = (df.format(new Date()).toString()).replace("-", "");// new Date()为获取当前系统时间
				
				JCoTable item = function.getTableParameterList().getTable("S_DATBI");
			     item.appendRow(); //放入表内  
			     //传参
			     item.setValue("SIGN", "I");
			     item.setValue("OPTION", "GE");
			     item.setValue("LOW", date );
			     
			     JCoTable items = function.getTableParameterList().getTable("S_DATAB");
			     items.appendRow(); //放入表内  
			     //传参
			     items.setValue("SIGN", "I");
			     items.setValue("OPTION", "LE");
			     items.setValue("LOW", date );
			     
//			     JCoTable item2 = function.getTableParameterList().getTable("S_MATNR");
//			     item2.appendRow(); //放入表内  
//			     //传参
//			     item2.setValue("SIGN", "I");
//			     item2.setValue("OPTION", "EQ");
//			     item2.setValue("LOW", "10201007300");
				function.execute(dest); 
				//获取接口结果输出表
				JCoTable resultTbl = function.getTableParameterList().getTable("OT_PRICE"); 
				int rows = resultTbl.getNumRows();
				int k=0;
				if(rows>0){
					int i=0;
					boolean errorFlag = false;
					for(;i<rows;i++){
						resultTbl.setRow(i); 
						ChannelPrice material = new ChannelPrice();
						material.setVkorg(resultTbl.getString("VKORG"));
						material.setVtweg(resultTbl.getString("VTWEG"));
						material.setMatnr((Long.parseLong(resultTbl.getString("MATNR"))+"").trim());
						material.setDatab(PropUtil.getStringToDate(resultTbl.getString("DATAB")));
					    material.setDatbi(PropUtil.getStringToDate(resultTbl.getString("DATBI")));
						material.setKonwa(resultTbl.getString("KONWA"));
						material.setKpein(resultTbl.getFloat("KPEIN"));
//						if("ST".equals(resultTbl.getString("KMEIN"))){
//							material.setKbetr(resultTbl.getFloat("KBETR"));
//							material.setKmein(resultTbl.getString("KMEIN"));
//						}else 
						if("KAR".equals(resultTbl.getString("KMEIN"))){//将kar的转为基本单位
							Material mater = this.channelPriceDao.getMaterial(material.getMatnr());
							if(null!=mater){
								material.setKmein("ST");
								k++;

							}else{
								material.setKbetr(resultTbl.getFloat("KBETR"));
								material.setKmein(resultTbl.getString("KMEIN"));
							}
						}else{//不处理
							material.setKbetr(resultTbl.getFloat("KBETR"));
							material.setKmein(resultTbl.getString("KMEIN"));
						}
						material.setKfrst(resultTbl.getString("KFRST"));
						String kunnr=resultTbl.getString("KUNNR")+"";
						if(kunnr!=null&&kunnr.trim().length()>0){
							material.setKunnr((Long.parseLong(kunnr))+"");
						}else{
							material.setKunnr(resultTbl.getString("KUNNR"));
						}
						material.setMaktx(resultTbl.getString("MAKTX"));
						material.setChannelPriceType("0");
						try {
						int count = this.channelPriceDao.getChannelPriceCount(material);
						if(count==0){
							long id = this.channelPriceDao.creatChannelPrice(material);
						}else if(count==1){
							int s = this.channelPriceDao.updateChannelPrice(material);
						}else{}
						//System.out.println("========="+k);
						} catch (Exception e) {
							ExceptionLog log = new ExceptionLog();
							log.setInterfaceName("产品渠道价格/Z_RFC_GET_MATERIAL_PRICE");
							log.setOperateUser("SAP");
							log.setOperateTime(new Date());
							log.setLogDesc("失败！");
							log.setLogInfo(e.getMessage());
							errorFlag = true;
							this.logInfoDao.addLogInfo(log);
						    continue;
						}
					}
					if(i==rows&&(!errorFlag)){
						ExceptionLog log = new ExceptionLog();
						log.setInterfaceName("产品渠道价格/Z_RFC_GET_MATERIAL_PRICE");
						log.setOperateUser("SAP");
						log.setOperateTime(new Date());
						log.setLogDesc("成功！");
						log.setLogInfo("成功");
						this.logInfoDao.addLogInfo(log);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
public static void main(String[] args) {
		
	ChannelPriceServiceImpl dap = new  ChannelPriceServiceImpl();
		dap.getChannelPriceFromSAP(); 

	}
}
