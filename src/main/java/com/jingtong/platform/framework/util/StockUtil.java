package com.jingtong.platform.framework.util;

import java.util.Calendar;
import java.util.Random;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;

public class StockUtil {
	public static String floatReg = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";
	public static String dateReg="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]"+
			"{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]"+"|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]"+
			"|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1]"+"[0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468]"+
			"[048]|[13579][26])|((0[48]|[2468][048]|"+"[3579][26])00))-02-29)";
	public static String serialNumGenerator(int size){
		Integer val = new Random().nextInt(9999);
		StringBuffer sb=new StringBuffer("");
		sb.append(val);
		if(sb.length() < size){
			int cnt  = size - sb.length();
			for(int i = 0; i < cnt; i++){
				sb.insert(0, "0");
			}
			return sb.toString();
		}else if(sb.length() > size){
			return sb.substring(sb.length() - size, size);
		}else{
			return sb.toString();
		}
	}

	
	
	//去除空行
			public static int delEmptyRow(Sheet sheet){
				//返回去掉空行的记录数
				int nullCellNum;//统计空格数
				int row = sheet.getRows();
				int column = sheet.getColumns();
				int actualRows = row;
				for (int m = 0; m < row; m++) { //统计行中为空的单元格数
				   nullCellNum = 0;
				    for (int n = 0; n < column; n++) {
				        String val = sheet.getCell(n, m).getContents();
				        val = StringUtils.trimToEmpty(val);
				        if (StringUtils.isBlank(val))
				           nullCellNum++;
				    }
				    if (nullCellNum >= column) { //如果nullCellNum大于或等于总的列数
				    	actualRows--;          //行数减一
				   }
				}
				return actualRows;
			}
	public static String createBatchNo(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String batchNo = ""+year+(month<10?("0"+month):month);
		return batchNo;
	}
	public static void main(String[] args) {
		System.out.println(createBatchNo());
	}
}
