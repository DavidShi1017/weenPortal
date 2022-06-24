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
						//url : appUrl						+ '/claimAction!getPosListForB.jspa?ran='								+ Math.random(),
						loadMsg : 'Loading...',
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
 												return "<font color='blue'>Pendding</font>";
 											}else if (flag == '9') {
 												return "<font color='blue'>Transfer</font>";
 											}else if (flag == '4') {
 												return "<font color='green'>Rebated</font>";
 											}else{
 												return flag;
 											} 
 										},
 										align : 'center',
 										
 									},{
 										title : "Fiscal Year/Period",
 										field : 'created_time',
 										width : 120,
 										align : 'center',
 										formatter : function(date){
 											return utcToDate(date);
 										}
 									},{
 										title : "Disti Name",
 										field : 'disti_name',
 										width : 120,
 										align : 'left'
 									},{
 										title : "Payer-to",
 										field : 'disti_branch',
 										width : 100,
 										align : 'center'
 									},{
 										title : "Debit Distributor",
 										field : 'disti_name1',
 										width : 100,
 										align : 'center',
 									},{
 										title : "Debit Number",
 										field : 'debit_number',
 										width : 100,
 										align : 'center',
 									},{
 										title : "Order Date",
 										field : 'update_time',
 										width : 100,
 										align : 'center',
 										formatter : function(date){
											if (date==null) return "";
 											return utcToDate(date);
 										}
 									},{
 										title : "Sap Claim Nbr",
 										field : 'sapClaimNbr',
 										width : 100,
 										align : 'center',
 									},{
 										title : "Book Part",
 										field : 'book_part',
 										width : 130,
 										align : 'left',
 									},{
 										title : "POS Book Part",
 										field : 'book_part1',
 										width : 130,
 										align : 'left',
 									},{
 										title : "Debit Book Part",
 										field : 'book_part2',
 										width : 130,
 										align : 'left',
 									},{
 										title : "Debit Customer",
 										field : 'endcust_name',
 										width : 130,
 										align : 'left',
 									},{
 										title : "Debit End Customerr",
 										field : 'endcust_name1',
 										width : 130,
 										align : 'left',
 									},{
 										title : "End Cust Name",
 										field : 'endcust_name2',
 										width : 130,
 										align : 'left',
 									},{
 										title : "Ship Date",
 										field : 'ship_date',
 										width : 100,
 										align : 'left',
 									},{
 										title : "Currency Code",
 										field : 'disti_cost_currency',
 										width : 100,
 										align : 'left',
 									},{
 										title : "Invoice Number",
 										field : 'disti_invoice_nbr',
 										width : 100,
 										align : 'left',
 									},{
 										title : "Invoice Item Number",
 										field : 'disti_invoice_item_number',
 										width : 100,
 										align : 'left',
 									},{
 										title : "Ship Qty",
 										field : 'ship_qty',
 										width : 100,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,0,1);
// 										}
 									},{
 										title : "Disti Resale",
 										field : 'disti_resale_denom',
 										width : 100,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,4,1);
// 										}
 									},{
 										title : "Disti Resale Total",
 										field : 'disti_resale_denom_total',
 										width : 100,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,2,1);
// 										}
 									},{
 										title : "Disti Resale Currency",
 										field : 'disti_resale_currency',
 										width : 100,
 										align : 'center',
 									},{
 										title : "Cost Denom",
 										field : 'cost_denom',
 										width : 90,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,4,1);
// 										}
 									},{
 										title : "Cost Denom Total",
 										field : 'cost_denom_total',
 										width : 90,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,2,1);
// 										}
 									},{
 										title : "Disti Cost Currency",
 										field : 'disti_cost_currency',
 										width : 100,
 										align : 'center',
 									},{
 										title : "Dbc Denom",
 										field : 'dbc_denom',
 										width : 100,
 										align : 'center',
// 										formatter : function(value, row, rec) {
// 											return formatNumber(value,4,1);
// 										}
 									},{
 										title : "Dbc Denom Total",
 										field : 'dbc_denom_total',
 										width : 100,
 										align : 'center',
 										formatter : function(value, row, rec) {
 											return formatNumber(value,2,1);
 										}
 									},{
 										title : "Dbc Currency Code",
 										field : 'dbc_currency_code',
 										width : 90,
 										align : 'center',
 									}
 									,{
 										title : "Disti Claimnbr",
 										field : 'disti_claimnbr',
 										width : 100,
 										align : 'left',
 									},{
 										title : "Oppreg Nbr",
 										field : 'oppreg_nbr',
 										width : 100,
 										align : 'left',
 									}] ],
						toolbar : [ "-", {
							text : 'Outport',
							iconCls : 'icon-excel',
							handler : function() {
								downloadExcelModel();
							}
						}], 
					});
 
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_name = encodeURIComponent($("#disti_name").val());
	queryParams.disti_branch = encodeURIComponent($("#disti_branch").val());
	queryParams.debit_number = encodeURIComponent($("#debit_number").val());
	queryParams.book_part = encodeURIComponent($("#book_part").val());
	queryParams.purchasing_customer_name = encodeURIComponent($("#purchasing_customer_name").val());
	queryParams.endcust_name = encodeURIComponent($("#endcust_name").val());
	queryParams.sapClaimNbr = encodeURIComponent($("#sapClaimNbr").val());
	queryParams.status = $("#status").val();
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	queryParams.disti_invoice_nbr = $("#disti_invoice_nbr").val();
	
	queryParams.order_start_timeString = $("#order_start_time").datebox('getValue');
	queryParams.order_end_timeString = $("#order_end_time").datebox('getValue');
	queryParams.data_from = $("#data_from").val();
	$('#datagrid').datagrid({
		url : appUrl						+ '/claimAction!getPosListForB.jspa?ran='								+ Math.random(),
	//	url : appUrl+ '/posAction!getPosListForBb.jspa',
		queryParams : queryParams,
		pageNumber : 1
	});
	
	$("#datagrid").datagrid('load');
}

function importExcel(){
	initMaintAccount(false,'700','400','import', '/claimAction!importExcel.jspa',100,100);
}

$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");
	$('#order_start_time').datebox('setValue',"");
	$('#order_end_time').datebox('setValue',"");

});

//数据下载
function downloadExcelModel(){
	var start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	var end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
	var order_start_timeString = $("#order_start_time").datebox('getValue');
	var order_end_timeString = $("#order_end_time").datebox('getValue');
//	alert(order_start_timeString);
	
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/claimAction!downloadExcelModelForBb.jspa?start_timeString='+start_timeString +"&end_timeString="+end_timeString+
			"&order_start_timeString="+order_start_timeString+"&order_end_timeString="+order_end_timeString;
 			form.target = "hideFrame";
			form.submit();
		}
	});
}




  
function check(){
		var form = window.document.forms[0];
		form.action = appUrl + '/claimAction!checkEDI.jspa';
		form.target = "hideFrame";
		form.submit();
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
	console.log('tips promgtMsg');
	if (!!failResult) {
		console.log('tips failresult',failResult);
		$.messager.alert('Tips', failResult, 'warning');
	} else if(!!successResult){
		console.log('tips successResult')
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