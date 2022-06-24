package com.jingtong.platform.cusInventroy.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/** 
 * @author cl 
 * @createDate 2016-6-16
 * 
 */
public class CusInventroy extends SearchInfo{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	 private String message_number;
	 private String doc_id;
	 private String doc_code;
	 private String doc_identifier;
	 private String function_code;
	 private String sender_id;
	 private String recipient_id;
	 private String sender_date;
	 private String by_party;
	 private String st_party;
	 private String line_item;
	 private String part_name;
	 private String item_type;
	 private String inventroy_method;
	 private String quantity_type;
	 private String quantity;
	 private String unit_code;
	 private String currency;
	 private String price_code;
	 private String price;
	 private String price_type;
	 private String price_basis;
	 private Date synchronization_time;
	 private String synchronization_timestr;
	 private String synchronization_user;
	 private Date update_time;
	 private String update_timestr;
	 private String update_user;
	 private String  data_from;
	 private String status;
	 private String cus_name;
	 private String tips;
	 private String sales_org;
	 private String monthly_closing_date;
	 private String material_id;
	 private long file_id;
	 private String file_url;
	 private String status_num;
	
	 private String frequency;
	 private Long qty;
	 private String amount;
	 
	 private String uploadFile;
	 
	 private Date start_time;
	 private Date end_time;
	 
	 private String file_id_str;
	 
	 //±¨±í
	 private String price_USD;
	 private String unit;
	 private String price1;
	 private String price2;
	 private String price_total;
	 private String price_USD_total;
	 private String price1_total;
	 private String price2_total;
	 private String disti_name;
	 
	 
	 
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDisti_name() {
		return disti_name;
	}
	public void setDisti_name(String disti_name) {
		this.disti_name = disti_name;
	}
	public String getPrice1_total() {
		return price1_total;
	}
	public void setPrice1_total(String price1_total) {
		this.price1_total = price1_total;
	}
	public String getPrice2_total() {
		return price2_total;
	}
	public void setPrice2_total(String price2_total) {
		this.price2_total = price2_total;
	}
	public String getPrice_total() {
		return price_total;
	}
	public void setPrice_total(String price_total) {
		this.price_total = price_total;
	}
	public String getPrice_USD_total() {
		return price_USD_total;
	}
	public void setPrice_USD_total(String price_USD_total) {
		this.price_USD_total = price_USD_total;
	}
	public String getFile_id_str() {
		return file_id_str;
	}
	public void setFile_id_str(String file_id_str) {
		this.file_id_str = file_id_str;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage_number() {
		return message_number;
	}
	public void setMessage_number(String message_number) {
		this.message_number = message_number;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_code() {
		return doc_code;
	}
	public void setDoc_code(String doc_code) {
		this.doc_code = doc_code;
	}
	public String getDoc_identifier() {
		return doc_identifier;
	}
	public void setDoc_identifier(String doc_identifier) {
		this.doc_identifier = doc_identifier;
	}
	public String getFunction_code() {
		return function_code;
	}
	public void setFunction_code(String function_code) {
		this.function_code = function_code;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public String getSender_date() {
		return sender_date;
	}
	public void setSender_date(String sender_date) {
		this.sender_date = sender_date;
	}
	public String getBy_party() {
		return by_party;
	}
	public void setBy_party(String by_party) {
		this.by_party = by_party;
	}
	public String getSt_party() {
		return st_party;
	}
	public void setSt_party(String st_party) {
		this.st_party = st_party;
	}
	public String getLine_item() {
		return line_item;
	}
	public void setLine_item(String line_item) {
		this.line_item = line_item;
	}
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getInventroy_method() {
		return inventroy_method;
	}
	public void setInventroy_method(String inventroy_method) {
		this.inventroy_method = inventroy_method;
	}
	public String getQuantity_type() {
		return quantity_type;
	}
	public void setQuantity_type(String quantity_type) {
		this.quantity_type = quantity_type;
	}

	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPrice_code() {
		return price_code;
	}
	public void setPrice_code(String price_code) {
		this.price_code = price_code;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice_type() {
		return price_type;
	}
	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	public String getPrice_basis() {
		return price_basis;
	}
	public void setPrice_basis(String price_basis) {
		this.price_basis = price_basis;
	}
	public Date getSynchronization_time() {
		return synchronization_time;
	}
	public void setSynchronization_time(Date synchronization_time) {
		this.synchronization_time = synchronization_time;
	}
	public String getSynchronization_user() {
		return synchronization_user;
	}
	public void setSynchronization_user(String synchronization_user) {
		this.synchronization_user = synchronization_user;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public String getData_from() {
		return data_from;
	}
	public void setData_from(String data_from) {
		this.data_from = data_from;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSynchronization_timestr() {
		return synchronization_timestr;
	}
	public void setSynchronization_timestr(String synchronization_timestr) {
		this.synchronization_timestr = synchronization_timestr;
	}
	public String getUpdate_timestr() {
		return update_timestr;
	}
	public void setUpdate_timestr(String update_timestr) {
		this.update_timestr = update_timestr;
	}

	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getSales_org() {
		return sales_org;
	}
	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}
	public String getMonthly_closing_date() {
		return monthly_closing_date;
	}
	public void setMonthly_closing_date(String monthly_closing_date) {
		this.monthly_closing_date = monthly_closing_date;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public long getFile_id() {
		return file_id;
	}
	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getStatus_num() {
		return status_num;
	}
	public void setStatus_num(String status_num) {
		this.status_num = status_num;
	}
	public String getPrice_USD() {
		return price_USD;
	}
	public void setPrice_USD(String price_USD) {
		this.price_USD = price_USD;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	
	 
}
