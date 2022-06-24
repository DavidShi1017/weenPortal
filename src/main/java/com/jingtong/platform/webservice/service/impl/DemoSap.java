package com.jingtong.platform.webservice.service.impl;

import com.jingtong.platform.framework.bo.BasicService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class DemoSap extends BasicService{
	private JCoDestination dest;
	public void testDemo(){
		 //如何将多个同字段的参数值传给sap
		try { 
			//创建连接  
			this.connect("SAP");
			dest = JCoDestinationManager.getDestination("SAP");
			dest.ping();
			JCoFunction function = 	dest.getRepository().getFunction("");//url
			if (function == null){
				throw new Exception("连接SAP失败!"); 
			}
			// 传入的参数
			function.getImportParameterList().setValue("BUDAT",""); //传参数
			function.getImportParameterList().setValue("BUNAM","");//过账人：是上架人(30号下午确定)
			JCoTable item = function.getTableParameterList().getTable("ITEM");
				       item.appendRow(); //放入表内 
						item.setValue("MATNR", "");//物料
						item.setValue("WERKS", "");//工厂 
			function.execute(dest); 
    		String materialDocument = function.getExportParameterList().getValue("MATERIALDOCUMENT").toString();//sap返回信息
    		String matdocumentYear = function.getExportParameterList().getValue("MATDOCUMENTYEAR").toString();//sap返回信息
			JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN"); 
			int rows = resultTbl.getNumRows();
		
		}catch (Exception e1) { 
				e1.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		
	}
	
	
	

}
