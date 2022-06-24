package com.jingtong.platform.province.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.province.pojo.Province;
import com.jingtong.platform.province.service.IProvinceService;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;

public class ProvinceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private IProvinceService provinceService;
	private Province p;
	private List<Province> pList;
	private String id;
	private int total;
	private String country_code;
	private String country;
	private String country_name;
	private String province_code;
	private String province_name;
	private String search;
	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	public String tosearchProvince() {
		return "tosearchProvince";
	}

	/**
	 * 跳转到查看页面
	 * 
	 * @return
	 */
	public String toViewProvince() {
		p = new Province();
		p.setId(Long.valueOf(id));
		p = provinceService.getProvinceById(p);
		return "toViewProvince";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	public String toCreateProvince() {
		p = new Province();
		return "toCreateProvince";
	}

	// 跳转到修改页面
	public String toUpdateProvince() {
		p = new Province();
		p.setId(Long.valueOf(id));
		p = provinceService.getProvinceById(p);
		return "toCreateProvince";
	}

	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "pList", include = { "id", "province_code", "country_code", "country_name",
			"province_name" }, total = "total")
	public String getProvinceList() {

		p = new Province();
		p.setStart(getStart());
		p.setEnd(getEnd());
		p.setSort("aa.province_name");
		p.setDir("asc");
		p.setCountry_code(country);
		if (StringUtils.isNotEmpty(province_name)){
			p.setProvince_name(province_name.toLowerCase());			
		}
		if (StringUtils.isNotEmpty(province_code)){
			p.setProvince_code(province_code.toLowerCase());			
		}
		if (StringUtils.isNotEmpty(country_name)){
			p.setCountry_name(country_name.toUpperCase());			
		}
		if (StringUtils.isNotEmpty(country_code)){
			p.setCountry_code(country_code.toUpperCase());			
		}
		if (StringUtils.isNotEmpty(search)){		
			p.setSearch(search.toLowerCase());
		}
		total = provinceService.getProvinceListCount(p);
		if (total > 0) {
			pList = provinceService.searchProvince(p);
		}
		return JSON;
	}

	/**
	 * 添加信息
	 * 
	 * @return
	 */
	public String addProvince() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		Province aa = new Province();
		aa.setProvince_code(p.getProvince_code().toUpperCase());
		Boolean flag = checkProvinceId(aa);
		if (flag) {
			long result = provinceService.saveProvince(p);
			if (result > 0) {
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage("Failed!");
			}
		} else {
			this.setFailMessage("Province aready exist!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 修改信息
	 */
	public String updateProvince() {

		this.setSuccessMessage("");
		this.setFailMessage("");
		Province aa = new Province();
		aa.setProvince_code(p.getProvince_code().toUpperCase());		
		if (aa.getProvince_code().equals(p.getOldProvince_code().toUpperCase())) {
			long result = provinceService.updateProvince(p);
			if (result > 0) {
				this.setSuccessMessage("Success!");
			} else {
				this.setFailMessage("Failed!");
			}
		} else {
			Boolean flag = checkProvinceId(aa);
			if (flag) {
				long result = provinceService.updateProvince(p);
				if (result > 0) {
					this.setSuccessMessage("Success!");
				} else {
					this.setFailMessage("Failed!");
				}
			} else {
				this.setFailMessage("Province aready exist!");
			}
		}

		return RESULT_MESSAGE;
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String delProvince() {

		this.setFailMessage("");
		this.setSuccessMessage("");
		p = new Province();
		p.setId(Long.valueOf(id));
		int i = provinceService.deleteProvinceById(p);
		if (i > 0) {
			this.setSuccessMessage("Success!");
		} else {
			this.setFailMessage("Failed!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 验证该编码名是否被占用
	 * 
	 * @param g
	 * @return
	 */
	private Boolean checkProvinceId(Province p) {
		Boolean b = true;
		int n = provinceService.getCountByCode(p);
		if (n >= 1) {
			b = false;
		}
		return b;

	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public IProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(IProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public Province getP() {
		return p;
	}

	public void setP(Province p) {
		this.p = p;
	}

	public List<Province> getpList() {
		return pList;
	}

	public void setpList(List<Province> pList) {
		this.pList = pList;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
