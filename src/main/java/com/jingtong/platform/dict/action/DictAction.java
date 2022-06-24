package com.jingtong.platform.dict.action;

import java.util.ArrayList;
import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.pojo.CmsTbDictType;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;



public class DictAction extends BaseAction {

	private static final long serialVersionUID = 5042752280539471298L;

	private List<CmsTbDict> cmsTbDictList = new ArrayList<CmsTbDict>();
 
	private List<CmsTbDictType> cmsTbDictTypeList = new ArrayList<CmsTbDictType>();

	private IDictService dictService;
	private StringResult stringResult = new StringResult();
	private int total;

	@Decode
	private String dictTypeName;
	@Decode
	private String remark;
	@Decode
	private String dictTypeValue;

	private long dictTypeId;
	private long itemId;

	private CmsTbDict cmsTbDict;

	private CmsTbDictType cmsTbDictType;
	
	private String ids;

	/**
	 * ²éÑ¯CmsTbDict×Öµä
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCmsTbDict() {
		this.dictTypeId=dictTypeId;
		return "searchCmsTbDict";
	}

	/**
	 * CmsTbDict×ÖµäÅäÖÃ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemName",
			"itemValue", "parentItemId", "itemDescription", "remark",
			"lastModify", "dictTypeId" }, total = "total")
	public String getCmsTbDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		//m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeId(dictTypeId);
		total = dictService.getCmsTbDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getCmsTbDictList(m);
		}

		return JSON;
	}

	/**
	 * CmsTbDict×ÖµäÅäÖÃ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = { "itemId", "itemValue",
			"itemState", "remark" }, total = "total")
	public String getDictJsonList() {
		CmsTbDict m = new CmsTbDict();
		//m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		if (!"".equals(dictTypeValue) && dictTypeValue != null) {
			m.setDictTypeValue(dictTypeValue);
		}
		total = dictService.getDictCount(m);
		if (total != 0) {
			cmsTbDictList = dictService.getDictList(m);
		}

		return JSON;
	}

	/**
	 * ²éÑ¯CmsTbDictType×Öµä
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCmsTbDictType() {
		return "searchCmsTbDictType";
	}

	/**
	 * CmsTbDictType×ÖµäÅäÖÃ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cmsTbDictTypeList", include = { "dictTypeId",
			"dictTypeName", "dictTypeValue", "remark", "lastModify" }, total = "total")
	public String getCmsTbDictTypeJsonList() {
		CmsTbDictType m = new CmsTbDictType();
//		m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeName(dictTypeName);
		m.setDictTypeValue(dictTypeValue);
		m.setRemark(remark);
		total = dictService.getCmsTbDictTypeCount(m);
		if (total != 0) {
			cmsTbDictTypeList = dictService.getCmsTbDictTypeList(m);
		}

		return JSON;
	}

	@PermissionSearch
	public String toCreateDictType() {
		return "toCreateDictType";
	}

	@PermissionSearch
	public String toCreateDict() {
		this.dictTypeId=dictTypeId;
		return "toCreateDict";
	}

	public String CreateDictType() {
		this.setSuccessMessage("Success!");

		BooleanResult booleanResult = dictService.createDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String CreateDict() {
		this.setSuccessMessage("Success!");
		BooleanResult booleanResult = dictService.createDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}
	@PermissionSearch
	public String toUpdateDictType() {
		CmsTbDictType searhCmsTbDictType = new CmsTbDictType();
		searhCmsTbDictType.setDictTypeId(dictTypeId);
		cmsTbDictType = dictService.getCmsTbDictType(searhCmsTbDictType);
		return "toUpdateDictType";
	}
	@PermissionSearch
	public String toUpdateDict() {
		CmsTbDict searchCmsTbDict = new CmsTbDict();
		searchCmsTbDict.setItemId(itemId);
		cmsTbDict = dictService.getCmsTbDict(searchCmsTbDict);
		return "toUpdateDict";
	}

	public String UpdateDict() {
		this.setSuccessMessage("Success!");
		BooleanResult booleanResult = dictService.updateDict(cmsTbDict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String UpdateDictType() {
		this.setSuccessMessage("Success!");
		BooleanResult booleanResult = dictService.updateDictType(cmsTbDictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String DeleteDict() {
		this.setSuccessMessage("Success!");
		String[] l = ids.split(",");
		CmsTbDict deleteCmsTbDict = new CmsTbDict();
		deleteCmsTbDict.setCodes(l);
		deleteCmsTbDict.setItemState("D");
		BooleanResult booleanResult = dictService.updateDict(deleteCmsTbDict);
		if (!booleanResult.getResult()) {
			//stringResult.setResult("²Ù×÷Ê§°Ü");
			//stringResult.setCode(booleanResult.getCode());
			this.setFailMessage("Failed!");

		}
		return RESULT_MESSAGE;
	}

	public String DeleteDictType() {
		this.setSuccessMessage("Success!");
		CmsTbDictType deleteCmsTbDictType = new CmsTbDictType();
		deleteCmsTbDictType.setDictTypeId(dictTypeId);
		deleteCmsTbDictType.setDictTypeState("D");
		CmsTbDict dict=new CmsTbDict();
		dict.setDictTypeId(dictTypeId);
		int r=dictService.getCmsTbDictCount(dict);
		if(r>0){
			this.setFailMessage("The type has a dictionary entry, please delete the entry first!");
		}else{
		BooleanResult booleanResult = dictService
				.updateDictType(deleteCmsTbDictType);
		if (!booleanResult.getResult()) {
			/*stringResult.setResult("²Ù×÷Ê§°Ü£¡");
			stringResult.setCode(booleanResult.getCode());*/
			this.setFailMessage("Failed!");

		}}
		return RESULT_MESSAGE;
	}

	
	
	@PermissionSearch
	@JsonResult(field = "cmsTbDictList", include = {  "itemId", "itemValue","itemName",
			"itemState", "remark" })
	public String getByCmsTbDictList() {
		CmsTbDict m = new CmsTbDict();
 		//m.setDictTypeValue(dictTypeValue);
 		m.setDictTypeId(dictTypeId);
 		m.setItemId(Long.valueOf(itemId));
		m.setSort("e.itemValue");
		m.setDir("ASC");
 		cmsTbDictList = dictService.getCmsTbDictByType(m);
 		return JSON;
	}
	
	public List<CmsTbDict> getCmsTbDictList() {
		return cmsTbDictList;
	}

	public void setCmsTbDictList(List<CmsTbDict> cmsTbDictList) {
		this.cmsTbDictList = cmsTbDictList;
	}

	public List<CmsTbDictType> getCmsTbDictTypeList() {
		return cmsTbDictTypeList;
	}

	public void setCmsTbDictTypeList(List<CmsTbDictType> cmsTbDictTypeList) {
		this.cmsTbDictTypeList = cmsTbDictTypeList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}

	public CmsTbDictType getCmsTbDictType() {
		return cmsTbDictType;
	}

	public void setCmsTbDictType(CmsTbDictType cmsTbDictType) {
		this.cmsTbDictType = cmsTbDictType;
	}

	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
