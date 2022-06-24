package com.jingtong.platform.framework.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
public class XssUtils {
	public static String getSafeStringXSS(String s){
	       if (StringUtils.isBlank(s)) {  
	           return s;  
	       }  
	       StringBuilder sb = new StringBuilder(s.length() + 16);  
	       for (int i = 0; i < s.length(); i++) {  
	           char c = s.charAt(i);  
	           switch (c) {  
	           case '<':  
	               //sb.append("&lt;");  
	               break; 
	           case '>':  
	               //sb.append("&gt;");  
	               break; 
	           case ';':  
	               //sb.append("&gt;");  
	               break; 
	          // case '/':  
	               //sb.append("&gt;");  
	              // break; 
	         //  case '\'':  
	               //sb.append("&prime;");// &acute;");  
	             //  break;  
	           case '¡ä':  
	               //sb.append("&prime;");// &acute;");  
	               break;  
	         //  case '\"':  
	               //sb.append("&quot;");  
	              // break;  
	           case '£¢':  
	               //sb.append("&quot;");  
	               break;  
	         //  case '&':  
	              // sb.append("£¦");  
	             //  break;  
	           case '#':  
	               sb.append("££");  
	               break;  
	         //  case '\\':  
	              // sb.append('£¤');  
	              // break; 
	        //   case '=':  
	               //sb.append("=");  
	             //  break;
	           default:  
	               sb.append(c);  
	               break;  
	           }  
	       }  
	       return sb.toString(); 
	   }
	
	public static <T> void getXssSaftBean(Class<?> clz,T bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String classname = clz.getSimpleName();
		Field[] fields = clz.getDeclaredFields();
		for(Field field : fields){
		
			Class<?> type = field.getType();
			if(type.equals(String.class)){
				String fieldname = field.getName();
				String value = BeanUtils.getProperty(bean, fieldname);
				if(StringUtils.isNotBlank(value)){
					BeanUtils.setProperty(bean, fieldname, getSafeStringXSS(value));
				}
			}
			
		}
	}
}
