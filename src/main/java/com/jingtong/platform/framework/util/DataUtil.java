package com.jingtong.platform.framework.util;

public class DataUtil {
	// 将数字转化为汉字的数组,因为各个实例都要使用所以设为静态
	private static final char[] cnNumbers = { '零', '壹', '贰', '叁', '肆', '伍',
			'陆', '柒', '捌', '玖' };

	// 供分级转化的数组,因为各个实例都要使用所以设为静态
	private static final char[] series = { '元', '拾', '佰', '仟', '万', '拾', '百',
			'仟', '亿' };

	// 供分级转化的数组,因为各个实例都要使用所以设为静态,小数部分
	private static final char[] series1 = { '角', '分' };

	/**
	 * 将字符形式的数字转化为整形数字 因为所有实例都要用到所以用静态修饰
	 * 
	 * @param c
	 * @return
	 */
	private static int getNumber(char c) {
		String str = String.valueOf(c);
		return Integer.parseInt(str);
	}

	/**
	 * 数字转化为中文大写
	 * 
	 * @param number
	 * @return
	 */
	public static String toChineseNumber(String num) {
		String integerPart = "";
		String floatPart = "";

		if (num.contains(".")) {
			// 如果包含小数点
			int dotIndex = num.indexOf(".");
			integerPart = num.substring(0, dotIndex);
			floatPart = num.substring(dotIndex + 1);
		} else {
			integerPart = num;
		}

		// 因为是累加所以用StringBuffer
		StringBuffer sb = new StringBuffer();

		// 整数部分处理
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));
			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}

		// 小数部分处理
//		sb.append(".");
		if (floatPart.length() > 0) {
			// sb.append("点");
			for (int i = 0; i < floatPart.length(); i++) {
				int number = getNumber(floatPart.charAt(i));
				sb.append(cnNumbers[number]);
				sb.append(series1[ i]);
			}
		}

		// 返回拼接好的字符串
		return sb.toString();
	}

	/** 
	  * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回 
	  * @param sourceDate 
	  * @param formatLength 
	  * @return 重组后的数据 
	  */  
	 public static String frontCompWithZore(String prefix,long sourceDate,int formatLength)  
	 {  
	  /* 
	   * prefix  前缀
	   * 0 指前面补充零 
	   * formatLength 字符总长度为 formatLength 
	   * d 代表为正数。 
	   */  
	  String newString = String.format("%0"+formatLength+"d", sourceDate);  
 	  if(!"".equals(prefix)&& null !=prefix)	{
 		 newString=prefix+newString;
	  } 
	  return  newString;  
	 }  
	
	public static void main(String[] args) {
		System.out.println(frontCompWithZore("v",5,7));
		System.out.println(toChineseNumber("123123458.00"));
	}
}
