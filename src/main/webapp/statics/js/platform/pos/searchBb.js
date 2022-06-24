$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});


function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						//url : appUrl+ '/posAction!getPosListForBb.jspa?ran='+ Math.random(),

						loadMsg : 'Load...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
 						pageSize:20,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},{
									title : "ID",
									field : 'id',
									width : 130,
									hidden : true,
									align : 'center'
								},{
									title : "Status",
									field : 'status',
									width : 60,
									formatter : function(value, row, rec) {
										var flag = row.status;
										if (flag == '0') {
											return "<font color='red'>Reject</font>";
										}else if (flag == '3') {
											return "<font color='green'>Approved</font>";
										}else if (flag == '1') {
											return "<font color='blue'>Pending</font>";
										}else if (flag == '9') {
											return "<font color='blue'>Transfer</font>";
										}else{
											return flag;
										} 
									},
									align : 'center',
									
								},{
									title : "TRANSACTION CODE",
									field : 'transaction_code',
									width : 130,
									align : 'center'
								},{
									title : "SHIP_DATE",
									field : 'ship_date',
									width : 80,
									align : 'center'
								},{
									title : "FISCPER [YYYYMMM]",
									field : 'ship_date1',
									width : 120,
									align : 'center'
								},{
									title : "FISCQTR",
									field : 'ship_date2',
									width : 80,
									align : 'center',
								},{
									title : "FISCYR",
									field : 'ship_date3',
									width : 80,
									align : 'center',
								},{
									title : "12NC",
									field : 'material_id',
									width : 110,
									align : 'center',
								},{
									title : "Commercial Type",
									field : 'material_exp',
									width : 110,
									align : 'left',
								}
//								,{
//									title : "Package Code",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "MAG",
//									field : '',
//									width : 100,
//									align : 'left',
//								}
//								,{
//									title : "Product Status",
//									field : 'material_state',
//									width : 100,
//									align : 'center',
//								}
								,
								{
									title : "BOOK PART",
									field : 'book_part',
									width : 150,
									align : 'left',
								},
//								{
//									title : "4TG",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Business Line",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Business Unit",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "DWL",
//									field : '',
//									width : 100,
//									align : 'left',
//								},
								{
									title : "SHIP_QTY",
									field : 'ship_qty',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,0,1);
									},
								},{
									title : "DEBIT_NUM",
									field : 'debit_number',
									width : 80,
									align : 'center',
								},{
									title : "DISTI_INVOICE_NBR",
									field : 'disti_invoice_nbr',
									width : 100,
									align : 'center',
								},{
									title : "DISTI_INVOICE_ITEM_NUMBER",
									field : 'disti_invoice_item_number',
									width : 100,
									align : 'center',
								},
//								{
//									title : "FINAL_QUOTED_COST_USD",
//									field : 'cost_denom_USD',
//									width : 100,
//									align : 'left',
//								},{
//									title : "FINAL_QUOTED_COST_EUR",
//									field : 'cost_denom_EUR',
//									width : 100,
//									align : 'left',
//								},
								{
									title : "DISTI NAME",
									field : 'disti_name',
									width : 120,
									align : 'left',
								},
//								{
//									title : "DISTI_PRICING_REGION",
//									field : 'office_id',
//									width : 100,
//									align : 'left',
//								},
								{
									title : "DISTI HQ SOLDTO",
									field : 'disti_branch',
									width : 150,
									align : 'left',
								},{
									title : "DISTI BRANCH SOLDTO",
									field : 'disti_branch',
									width : 150,
									align : 'left',
								},{
									title : "PC_NAME",
									field : 'purchasing_customer_name',
									width : 170,
									align : 'left',
								},{
									title : "PC_LOCATION",
									field : 'purchasing_cust_city',
									width : 120,
									align : 'left',
								}
//								,{
//									title : "PC_CORP_ID",
//									field : 'purchasing_cust_city',
//									width : 100,
//									align : 'left',
//								}
								,{
									title : "PC_FREGION",
									field : 'org_name_pc',
									width : 120,
									align : 'left',
								},{
									title : "PC_COUNTRY",
									field : 'purchasing_cust_country',
									width : 120,
									align : 'left',
								},{
									title : "PC_STATE",
									field : 'purchasing_cust_state',
									width : 120,
									align : 'left',
								},{
									title : "PC_ZIPCODE",
									field : 'purchasing_cust_zip',
									width : 120,
									align : 'left',
								}
//								,{
//									title : "EC_CORP_ID",
//									field : 'endcust_city',
//									width : 100,
//									align : 'left',
//								}
								,{
									title : "EC_COUNTRY",
									field : 'endcust_country',
									width : 100,
									align : 'center',
								},{
									title : "EC_FREGION",
									field : 'org_name_ec',
									width : 120,
									align : 'left',
								},{
									title : "EC_NAME",
									field : 'endcust_name',
									width : 150,
									align : 'left',
								},{
									title : "EC_LOCATION CODE",
									field : 'endcust_loc',
									width : 130,
									align : 'center'
								},{
									title : "EC_STATE",
									field : 'endcust_state',
									width : 120,
									align : 'left',
								},{
									title : "EC_ZIPCODE",
									field : 'endcust_zip',
									width : 100,
									align : 'left',
								}
//									,{
//									title : "Buy Price USD",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Buy Price EUR",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Resale Value USD",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Resale Value EUR",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "FINAL_QUOTED_RESALE_USD",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "FINAL_QUOTED_RESALE_EUR",
//									field : '',
//									width : 100,
//									align : 'left',
//								}
								,{
									title : "DISTI_RESALE_CURRENCY_CODE",
									field : 'disti_resale_currency',
									width : 100,
									align : 'center',
								},{
									title : "DISTI_RESALE_USD",
									field : 'cost_denom_USD',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,4,1);
									}
								},{
									title : "DISTI_RESALE_USD_TOTAL",
									field : 'cost_denom_USD_total',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,2,1);
									}
								},{
									title : "DISTI_RESALE_EUR",
									field : 'cost_denom_EUR',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,4,1);
									}
								},{
									title : "DISTI_RESALE_EUR_TOTAL",
									field : 'cost_denom_EUR_total',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,2,1);
									}
								}
//									,{
//									title : "ASP EUR pp",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "ASP USD pp",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Global Account",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Region Level 2",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "POS_DISTI_BRANCH_CODE",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Consolidated Account",
//									field : '',
//									width : 100,
//									align : 'left',
//								},{
//									title : "Local Rep Country",
//									field : '',
//									width : 100,
//									align : 'left',
//								}
//								,{
//									title : "Sync Time",
//									field : 'created_time',
//									width : 100,
//									align : 'center',
//									formatter : function(date){
//										return utcToDate(date);
//									}
//								}
								] ]
					,
						toolbar : [  "-", {
							text : 'Outport',
							iconCls : 'icon-excel',
							handler : function() {
								downloadExcelModel();
							}
						}]
					});
 
}

function search() {
	//loadGrid();
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_name = $("#disti_name").val();
	queryParams.disti_branch = $("#disti_branch").val();
	queryParams.debit_number = $("#debit_number").val();
	queryParams.book_part = $("#book_part").val();
	queryParams.purchasing_customer_name = $("#purchasing_customer_name").val();
	queryParams.endcust_name = $("#endcust_name").val();
	queryParams.start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	queryParams.end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
//	var opts = grid.datagrid('options');
//	opts.pageNumber = 1;
	
	$('#datagrid').datagrid({
		url : appUrl+ '/posAction!getPosListForBb.jspa',
		queryParams : queryParams,
		pageNumber : 1
	});
	

	//$("#datagrid").datagrid('load');
}

function check() {
	var form = window.document.forms[0];
	form.action = appUrl + '/posAction!checkEDIForAll.jspa?'
	form.target = "hideFrame";
	form.submit();
}

$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");

});


function importExcel(){
	initMaintAccount(false,'700','400','Import', '/posAction!importExcel.jspa',100,100);
}

//模板下载
function downloadExcelModel(){
	var start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	var end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/posAction!downloadExcelModel.jspa?start_timeString='+start_timeString +"&end_timeString="+end_timeString;
 			form.target = "hideFrame";
			form.submit();
		}
	});
}
  
 
 

function initMaintAccount(ffit,widte,height,title,url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}


// 关闭创建页面
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	//	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}