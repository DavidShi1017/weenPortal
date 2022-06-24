package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 销售政策
 * 
 * @author hfwu
 * 
 */
public class KunnrPolicy extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private String kunnr; // 经销商编号
	private String business; // 年销售规模
	private String stock; // 库存能力
	private String social_relations; // 社会关系
	private String big_customers_build; // 二批及大客户开发人员
	private String terminal_maintenance; // 终端维护人员
	private String delivery; // 配送人员
	private String trucks; // 卡车及大型车辆
	private String minivan;// 面包车及中小型车辆
	private String tricycle; // 三轮车及人力车
	private String distribution_customer; // 二批及分销客户数
	private String supply_terminal; // 二批及分销客户供货终端数
	private String direct_supply; // 直供终端数
	private String system_name1; // 重要系统名称1
	private String stores_number1; // 重要系统门店数1
	private String system_name2; // 重要系统名称2
	private String stores_number2; // 重要系统门店数2
	private String system_name3; // 重要系统名称3
	private String stores_number3; // 重要系统门店数3
	private String system_name4; // 重要系统名称4
	private String stores_number4; // 重要系统门店数4
	private String products; // 一级代理产品
	private String distribution_products; // 二批及分销产品
	private String applicant; // 申请人
	private Date create_date; // 创建日期
	private String modifier; // 修改人
	private Date modify_date;// 修改日期
	private String status; // Y:正常 N：删除

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSocial_relations() {
		return social_relations;
	}

	public void setSocial_relations(String social_relations) {
		this.social_relations = social_relations;
	}

	public String getBig_customers_build() {
		return big_customers_build;
	}

	public void setBig_customers_build(String big_customers_build) {
		this.big_customers_build = big_customers_build;
	}

	public String getTerminal_maintenance() {
		return terminal_maintenance;
	}

	public void setTerminal_maintenance(String terminal_maintenance) {
		this.terminal_maintenance = terminal_maintenance;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getTrucks() {
		return trucks;
	}

	public void setTrucks(String trucks) {
		this.trucks = trucks;
	}

	public String getMinivan() {
		return minivan;
	}

	public void setMinivan(String minivan) {
		this.minivan = minivan;
	}

	public String getTricycle() {
		return tricycle;
	}

	public void setTricycle(String tricycle) {
		this.tricycle = tricycle;
	}

	public String getDistribution_customer() {
		return distribution_customer;
	}

	public void setDistribution_customer(String distribution_customer) {
		this.distribution_customer = distribution_customer;
	}

	public String getSupply_terminal() {
		return supply_terminal;
	}

	public void setSupply_terminal(String supply_terminal) {
		this.supply_terminal = supply_terminal;
	}

	public String getDirect_supply() {
		return direct_supply;
	}

	public void setDirect_supply(String direct_supply) {
		this.direct_supply = direct_supply;
	}

	public String getSystem_name1() {
		return system_name1;
	}

	public void setSystem_name1(String system_name1) {
		this.system_name1 = system_name1;
	}

	public String getStores_number1() {
		return stores_number1;
	}

	public void setStores_number1(String stores_number1) {
		this.stores_number1 = stores_number1;
	}

	public String getSystem_name2() {
		return system_name2;
	}

	public void setSystem_name2(String system_name2) {
		this.system_name2 = system_name2;
	}

	public String getStores_number2() {
		return stores_number2;
	}

	public void setStores_number2(String stores_number2) {
		this.stores_number2 = stores_number2;
	}

	public String getSystem_name3() {
		return system_name3;
	}

	public void setSystem_name3(String system_name3) {
		this.system_name3 = system_name3;
	}

	public String getStores_number3() {
		return stores_number3;
	}

	public void setStores_number3(String stores_number3) {
		this.stores_number3 = stores_number3;
	}

	public String getSystem_name4() {
		return system_name4;
	}

	public void setSystem_name4(String system_name4) {
		this.system_name4 = system_name4;
	}

	public String getStores_number4() {
		return stores_number4;
	}

	public void setStores_number4(String stores_number4) {
		this.stores_number4 = stores_number4;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getDistribution_products() {
		return distribution_products;
	}

	public void setDistribution_products(String distribution_products) {
		this.distribution_products = distribution_products;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
