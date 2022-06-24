package com.jingtong.platform.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleCalculateUtil {
	   private static final int DEF_DIV_SCALE = 10;
	    
	    /**
	     * 两个Double数相�?
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double add(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.add(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相�?
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double sub(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.subtract(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相�?
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double mul(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.multiply(b2).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相�?
	     * @param v1
	     * @param v2
	     * @return Double
	     */
	    public static Double div(Double v1,Double v2){
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	    
	    /**
	     * 两个Double数相除，并保留scale位小�?
	     * @param v1
	     * @param v2
	     * @param scale
	     * @return Double
	     */
	    public static Double div(Double v1,Double v2,int scale){
	        if(scale<0){
	            throw new IllegalArgumentException(
	            "The scale must be a positive integer or zero");
	        }
	        BigDecimal b1 = new BigDecimal(v1.toString());
	        BigDecimal b2 = new BigDecimal(v2.toString());
	        Double dd =  b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	      //  DecimalFormat   fnum  =   new  DecimalFormat("##0.00");    
			//   String   dd=fnum.format(dd);   
	        return dd;
	    }
	    
	    public static void main(String[] args) {
			System.out.println(add(1.0,2.0));
			System.out.println(sub(10.09,1.01));
			System.out.println(mul(10.00,5.00));
			System.out.println(div(10.00,5.00));
			System.out.println(div(10.00,5.0,4));
			//float   a  =   100.2f;   
//			  float  b   =  (float)(Math.round(a*100))/100;
//			  System.out.println(b);
//			  
			  //String sa =  new DecimalFormat("###,###,###.##").format(100.12345  );

			  String   scale  =   "34.4";   
			  DecimalFormat   fnum  =   new  DecimalFormat("##0.0000");    
			   String   dd=fnum.format(scale);      
			   System.out.println(dd);  
		}
}
