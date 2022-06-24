package com.jingtong.platform.staffAmount.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.menu.action.MenuTreeAjaxAction;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.staffAmount.service.IStaffService;
import com.jingtong.platform.station.pojo.Station;

public class StaffAmountAction extends BaseAction{
	
	private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);
	private static final long serialVersionUID = 1L;
	private IStaffService staffAmountService;
	private IOrgService iOrgService;
	private int total = 0;
	private StaffAmount staffAmount;
	private StringResult stringResult = new StringResult();
	private String PId;
	private String amount;
	private String orgName;
	private String orgId;
	private String org_id;
	private String positionTypeName;
	private String bhxjFlag = "";
	private String uploadFileFileName;
	private File uploadFile;
	private String PIds;
	private String UpdateNum;
	private String stationId;
	private String sort;
	private String order;
	private List<StaffAmount> staffList ;
	private List<Station> statList;
	private String positionId;
	/**
	 * 跳转菜单
	 * @return
	 */
	@PermissionSearch
	public String toSearchStaffPage(){
		return "searchStaff";
	}
	/**
	 * 查询数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@PermissionSearch
	@SuppressWarnings("deprecation")
	@JsonResult(field = "staffList", include = { "PId", "orgId",
			"stationId", "amount", "amountU", "state", "orgName","orgParentName",
			"positionTypeName" }, total = "total")	
	public String getStaffAmountList() throws UnsupportedEncodingException{
		staffList = new ArrayList<StaffAmount>();
		StaffAmount staffAmount = new StaffAmount();
		staffAmount.setStart(getStart());
		staffAmount.setEnd(getEnd());
		staffAmount.setSort(sort);
		if (StringUtils.isNotEmpty(orgId)) {
			if (StringUtils.isNotEmpty(bhxjFlag) && StringUtils.equals(bhxjFlag, "C")) {
				String orgids = iOrgService.getFnAllChildStrOrg(orgId);
				if (StringUtils.isNotEmpty(orgids)) {
					staffAmount.setOrgIdarrs(orgids.split(","));
				}
			} else {
				staffAmount.setOrgId(Long.parseLong(orgId));
			}
		}

		if (StringUtils.isNotEmpty(positionTypeName)) {
			staffAmount.setPositionTypeName(java.net.URLDecoder.decode(positionTypeName,"UTF-8").trim());
		}
		total = staffAmountService.getStaffTotal(staffAmount);
		if(total!=0){
			staffList = staffAmountService.getStaffList(staffAmount);
		}
		return JSON;
	}
	/**
	 * 创建编制
	 * @return
	 */
	@PermissionSearch
	public String staffCreateSave() {
		return "createStaff";
	}
	public String createStaff(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		StaffAmount staffAmount = new StaffAmount();
		staffAmount.setOrgId(Long.parseLong(org_id));
		staffAmount.setStationId(positionId);
		staffAmount.setAmount(Long.parseLong(amount));
		if(staffAmountService.getStaffAmountCount(staffAmount)!=0){
			this.setFailMessage("该组织下该岗位已维护编制!");
		}else{
			StringResult stringResult = staffAmountService.createStaff(staffAmount);
			if (IStaffService.ERROR.equals(stringResult.getCode())) {
				this.setFailMessage(stringResult.getResult());
			} else {
				this.setSuccessMessage("已成功新增编制,编制ID为：" + stringResult.getResult() + "！");
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 删除编制
	 * @return
	 */
	public String deleteStaff() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		String[] l = PIds.split(",");
		StaffAmount staffAmount = new StaffAmount();
		staffAmount.setPIds(l);
		staffAmount.setState("D");
		int amountU =  staffAmountService.getStaffAmountCountU(staffAmount);
		if(amountU>0){
			this.setFailMessage("在删除的编制中有人员占用编制,请先维护好人员编制,再重新删除");
		}else{
			StringResult result = staffAmountService
					.updateStaffAmounts(staffAmount);
			if (IStaffService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("已成功删除" + result.getResult() + "个编制!");
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 修改编制数量
	 * @return
	 */
	public String updateStaff(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		String[] str = UpdateNum.split(",");
		String[] l = str[1].split(",");
		StaffAmount staffAmount = new StaffAmount();
		staffAmount.setPIds(l);
		staffAmount.setAmount(Long.parseLong(str[0]));
		StringResult result = staffAmountService
				.updateStaffAmounts(staffAmount);
		if (IStaffService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("修改成功！");
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 智能搜索
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "statList", include = { "stationId", "stationName" })
	public String blurSearchStaff() {
		Station station = new Station();
		if (StringUtils.isNotEmpty(positionTypeName)
				&& StringUtils.isNotEmpty(positionTypeName.trim())) {
			try {
				positionTypeName = new String(getServletRequest().getParameter("positionTypeName")
						.getBytes("ISO8859-1"), "UTF-8");
				station.setStationName(positionTypeName.trim());
				statList = staffAmountService.blurSearchStaff(station);
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
		}
		return JSON;
	}
	public IStaffService getStaffAmountService() {
		return staffAmountService;
	}
	public void setStaffAmountService(IStaffService staffAmountService) {
		this.staffAmountService = staffAmountService;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public StaffAmount getStaffAmount() {
		return staffAmount;
	}
	public void setStaffAmount(StaffAmount staffAmount) {
		this.staffAmount = staffAmount;
	}
	public String getPId() {
		return PId;
	}
	public void setPId(String pId) {
		PId = pId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPositionTypeName() {
		return positionTypeName;
	}
	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}
	public String getBhxjFlag() {
		return bhxjFlag;
	}
	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public List<StaffAmount> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<StaffAmount> staffList) {
		this.staffList = staffList;
	}
	public static Log getLogger() {
		return logger;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public StringResult getStringResult() {
		return stringResult;
	}
	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}
	public String getPIds() {
		return PIds;
	}
	public void setPIds(String pIds) {
		PIds = pIds;
	}
	public String getUpdateNum() {
		return UpdateNum;
	}
	public void setUpdateNum(String updateNum) {
		UpdateNum = updateNum;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public List<Station> getStatList() {
		return statList;
	}
	public void setStatList(List<Station> statList) {
		this.statList = statList;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public IOrgService getiOrgService() {
		return iOrgService;
	}
	public void setiOrgService(IOrgService iOrgService) {
		this.iOrgService = iOrgService;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
	
}
