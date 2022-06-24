package com.jingtong.platform.sap.pojo;
 
import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * @author Administrator
 * 查询工具类
 */
public class ToolsClass extends SearchInfo{
	private static final long serialVersionUID = -3386093021023024761L;
  
	private String dictTypeValue; 
	private String itemId; 
	
	
	public String getDictTypeValue() {
		return dictTypeValue;
	}
	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
 
 
  
	
}
