package com.jingtong.platform.enquiry.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;
import com.jingtong.platform.enquiry.service.IEnquiryService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.JsonUtil;

public class EnquiryAction extends BaseAction{
	private IEnquiryService enquiryService;
	private Enquiry e;
	private List<Enquiry> eList;
	private EnquiryDetail ed;
	private List<EnquiryDetail> edList;
	private String enquiryDetailJson;
	private String id;
	private String delEnquiryDetail;
	private String enquiry_id;
	private int total;
	private String ids;	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * 跳转到查询页面
	 * @return
	 */
	public String toSearchEnquiry(){
		return "toSearchEnquiry";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	public String toViewEnquiry(){
		e=new Enquiry();
		e.setId(Long.valueOf(id));
		e = enquiryService.getEnquiryById(e);
		return "toViewEnquiry";
	}
	/**
	 * 跳转到新增页面
	 * @return
	 */
	public String toCreateEnquiry(){
		return "toCreateEnquiry";
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdateEnquiry(){
		e=new Enquiry();
		e.setId(Long.valueOf(id));
		e = enquiryService.getEnquiryById(e);
		return "toCreateEnquiry";
	}
	
	

	/**
	 * 查询订单信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "eList", include = {"id","enquiry_id","type_id","customer_id","cusGroup_id","ecGroup_id","isDelivery","currency_code",
			  "ship_to","payer_to","billing_to","saler","sale_company","endCustomer_id",
			  "project_name","contact_info","state","remark","start_date","latest_expire",
			  "create_time","create_userId","start_dateStr","latest_expireStr",
			  "latest_time","latest_userId","org_code"},
			    total = "total")
	public String getEnquiryList(){
		
 		e = new Enquiry();
		e.setStart(getStart());
		e.setEnd(getEnd());
		e.setSort("create_time");
		e.setDir("desc");
		e.setEnquiry_id(enquiry_id);	
		total = enquiryService.getEnquiryListCount(e);
		if(total > 0){
			eList = enquiryService.getEnquiryList(e);
		}
		return JSON;
	}

	
	/**
	 * 查询订单明细信息
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "edList", include = { "id","enquiry_id","row_no","material_id","drNum","qty",
			  "target_resale","target_cost","amount","reason","competitor",
			  "start_date","start_dateStr","cus_remark",
			  "remark" }
			    )
	public String getEnquiryDetailList(){
		ed = new EnquiryDetail();
		ed.setEnquiry_id(enquiry_id);
		edList = enquiryService.getEnquiryDetailList(ed);
		return JSON;
	}
	
	
	/**
	 * 创建订单信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public String createEnquiry() throws UnsupportedEncodingException{
		this.setSuccessMessage("");
		this.setFailMessage("");
		
		enquiryDetailJson = java.net.URLDecoder.decode(this.enquiryDetailJson,"utf-8");	
		edList = JsonUtil.getDTOList(enquiryDetailJson, EnquiryDetail.class);	
		e.setCreate_userId(this.getUser().getUserId());
		BooleanResult bool= enquiryService.createEnquiry(e, edList);
		if(bool.getResult()){
			this.setSuccessMessage("操作成功!");
		}else{
			this.setFailMessage(bool.getCode());
		}		
 		return RESULT_MESSAGE;
	}

	
	
	/**
	 * 修改订单信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public String updateEnquiry() throws UnsupportedEncodingException{
		this.setSuccessMessage("");
		this.setFailMessage("");
		
		enquiryDetailJson = java.net.URLDecoder.decode(this.enquiryDetailJson,"utf-8");	
		edList = JsonUtil.getDTOList(enquiryDetailJson, EnquiryDetail.class);	
		ed = new EnquiryDetail();
		ed.setIds(delEnquiryDetail);
		e.setLatest_userId(this.getUser().getUserId());
		BooleanResult bool= enquiryService.updateEnquiry(e, edList, ed);
		if(bool.getResult()){
			this.setSuccessMessage("操作成功!");
		}else{
			this.setFailMessage(bool.getCode());
		}		
 		return RESULT_MESSAGE;
	}

	
	public String deleteEnquiry(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		e=new Enquiry();
		e.setId(Long.valueOf(id));
		int i=enquiryService.deleteEnquiry(e);
    	if(i>0){
    		this.setSuccessMessage("操作成功!");
    	}else{
    		this.setFailMessage("操作失败!");
    	}
    	return RESULT_MESSAGE;
	}
	
	public IEnquiryService getEnquiryService() {
		return enquiryService;
	}

	public void setEnquiryService(IEnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}

	public Enquiry getE() {
		return e;
	}

	public void setE(Enquiry e) {
		this.e = e;
	}

	public List<Enquiry> geteList() {
		return eList;
	}

	public void seteList(List<Enquiry> eList) {
		this.eList = eList;
	}

	public EnquiryDetail getEd() {
		return ed;
	}

	public void setEd(EnquiryDetail ed) {
		this.ed = ed;
	}

	public List<EnquiryDetail> getEdList() {
		return edList;
	}

	public void setEdList(List<EnquiryDetail> edList) {
		this.edList = edList;
	}

	public String getEnquiryDetailJson() {
		return enquiryDetailJson;
	}

	public void setEnquiryDetailJson(String enquiryDetailJson) {
		this.enquiryDetailJson = enquiryDetailJson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelEnquiryDetail() {
		return delEnquiryDetail;
	}

	public void setDelEnquiryDetail(String delEnquiryDetail) {
		this.delEnquiryDetail = delEnquiryDetail;
	}

	public String getEnquiry_id() {
		return enquiry_id;
	}

	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
