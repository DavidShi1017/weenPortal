$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
//$('#clearV').click(function(){
//	//$('#customer_id').combogrid('clear');
//	$('#endCustomer_id').combogrid('clear');
//	$('#purchaseCustomer_id').combogrid('clear');
//	setTimeout("search()",100);
//});
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/quote/quoteAction!getEDIQuote.jspa',
				        loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : false,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
						columns : [ [
										{
											field : 'ck',
											align : 'center',
											checkbox : true
										},
										{
											title : "ID",
											field : 'id',
											width : 80,
											hidden : true,
											align : 'center'
										},		
										{
											title : "NoPCEC",
											field : 'noPCEC',
											width : 80,
											align : 'center'
										},
										{
											title : "Quote Num",
											field : 'quote_id',
											width : 80,
											align : 'center'
										},
										{
											title : "Quote Type",
											field : 'type_id',
											width : 80,
											align : 'center',
											hidden:true
										},
										{
											title : "Disti",
											field : 'cusGroup_id',
											width : 150,
											align : 'left'
										},
										{
											title : "Disti Branch",
											field : 'disti_branch',
											width : 200,
											align : 'left'
										},
										{
											title : "PC Group",
											field : 'pcGroup_id',
											width : 110,
											align : 'center',
											hidden:true
										},
										{
											title : "PC Group",
											field : 'pcGroup_name',
											width : 110,
											align : 'center',
										},
										{
											title : "Purchasing Customer",
											field : 'purchaseCustomer_name',
											width : 200,
											align : 'left'
										},
										{
											title : "EDI PC Country",
											field : 'edi_pc_country',
											width : 120,
											align : 'left',
										},
										{
											title : "EDI PC Province",
											field : 'edi_pc_province',
											width : 120,
											align : 'center'
										},
										{
											title : "EDI PC City",
											field : 'edi_pc_city',
											width : 120,
											align : 'left'
										},
										{
											title : "EDI PC Zip",
											field : 'edi_pc_zip',
											width : 120,
											align : 'center',
										},
										{
											title : "EC Group",
											field : 'ecGroup_id',
											width : 100,
											align : 'center',
											hidden:true
										},
										{
											title : "EC Group",
											field : 'ecGroup_name',
											width : 100,
											align : 'center',
											//hidden:true
										},
										{
											title : "End Customer",
											field : 'endCustomer_name',
											width : 200,
											align : 'left',
										},
										{
											title : "EDI EC Country",
											field : 'edi_ec_country',
											width : 120,
											align : 'left',
										},
										{
											title : "EDI EC Province",
											field : 'edi_ec_province',
											width : 120,
											align : 'center'
										},
										{
											title : "EDI EC City",
											field : 'edi_ec_city',
											width : 120,
											align : 'left'
										},
										{
											title : "EDI EC Zip",
											field : 'edi_ec_zip',
											width : 120,
											align : 'center',
										},
										{
											title : "Currency",
											field : 'currency_code',
											width : 80,
											align : 'center'
										},
										{
											title : "Project",
											field : 'project_name',
											width : 150,
											align : 'left'
										},
										{
											title : "Assembly",
											field : 'isDelivery',
											width : 90,
											align : 'center',
											hidden:true
										},
//										{
//											title : "Start Date",
//											field : 'start_dateStr',
//											width : 90,
//											align : 'center',									
//										},
//										{
//											title : "Latest Expire",
//											field : 'latest_expireStr',
//											width : 90,
//											align : 'center',
//										},
										{
											title : "Sync Status",
											field : 'sync_state',
											width : 100,
											align : 'center',
											formatter : function(value, row, rec) {
												var flag = row.sync_state;
												if (flag == '0') {
													return "<font color='red'>No Sync</font>";
												}else if (flag == '1') {
													return "<font color='green'>Yes Sync</font>";
												}
											},
											hidden:true
										},										
										{
											title : "Creator",
											field : 'create_userId',
											width : 100,
											align : 'center',																			
										},{
											title : "Create Time",
											field : 'create_time',
											width : 100,
											formatter : function(date) {											
												if (date == undefined || date=="") {
													return "";
												}else  {
													return utcToDate(date);
												}
											},
											align : 'center'
										},
										] ],
						toolbar : [
						           
						           "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								addPCEC();
							}
						} ,  "-", {
							text : 'View',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}  ,  "-", {
							text : 'Alias',
							iconCls : 'icon-add',
							handler : function() {
								addAlias();
							}
						}						
						],
						onSelect : function(rowIndex, rowData) {
							var state = rowData.noPCEC;
							 if(state=undefined || state==""||state=="undefined"){
								$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					
							}else{
								$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
								
							}
						},
						
							onDblClickRow:function(rowIndex,rowData){
								initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rowData.id+'&loginRole='+$('#loginRole').val());
							},
						
					});
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	$("#datagrid").datagrid('load');
}

function addPCEC(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate！', 'warning');
		return;
	} 
	initMaintAccount(false, '800','400', 'Customer Registration',
			'/endCustomerAction!toCreatePCEC.jspa?e_name='+encodeURIComponent(rows[0].endCustomer_name==undefined?"":rows[0].endCustomer_name)
			+'&p_name='+encodeURIComponent(rows[0].purchaseCustomer_name==undefined?"":rows[0].purchaseCustomer_name)
			+'&p_country='+encodeURIComponent(rows[0].edi_pc_country==undefined?"":rows[0].edi_pc_country)
			+'&p_province='+encodeURIComponent(rows[0].edi_pc_province==undefined?"":rows[0].edi_pc_province)
			+'&p_city='+encodeURIComponent(rows[0].edi_pc_city==undefined?"":rows[0].edi_pc_city)
			+'&p_zip='+encodeURIComponent(rows[0].edi_pc_zip==undefined?"":rows[0].edi_pc_zip)
			+'&e_country='+encodeURIComponent(rows[0].edi_ec_country==undefined?"":rows[0].edi_ec_country)
			+'&e_province='+encodeURIComponent(rows[0].edi_ec_province==undefined?"":rows[0].edi_ec_province)
			+'&e_city='+encodeURIComponent(rows[0].edi_ec_city==undefined?"":rows[0].edi_ec_city)
			+'&e_zip='+encodeURIComponent(rows[0].edi_ec_zip==undefined?"":rows[0].edi_ec_zip)
			+'&noPCEC='+rows[0].noPCEC+'&quote_id='+rows[0].quote_id,20,20);
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rows[0].id+'&loginRole='+$('#loginRole').val());
	}	
}


function addAlias(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate！', 'warning');
		return;
	} 
	initMaintAccount(false, '800','400', 'Customer Alias',
			'/posAction!toCreateECAlias.jspa?e_name='+encodeURIComponent(rows[0].endCustomer_name==undefined?"":rows[0].endCustomer_name)
			+'&p_name='+encodeURIComponent(rows[0].purchaseCustomer_name==undefined?"":rows[0].purchaseCustomer_name)
			+'&p_city='+encodeURIComponent(rows[0].edi_pc_city==undefined?"":rows[0].edi_pc_city)
			+'&e_city='+encodeURIComponent(rows[0].edi_ec_city==undefined?"":rows[0].edi_ec_city)
			+'&pcec='+rows[0].noPCEC+'&quote_id='+rows[0].quote_id,20,20);
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rows[0].id+'&loginRole='+$('#loginRole').val());
	}	
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
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

//document.onkeydown = function(e) {
//	var theEvent = e || window.event;
//	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
//	if (code == 13) {
//		search();
//		return false;
//	}
//	var obj=theEvent.srcElement ? theEvent.srcElement : theEvent.target;
//	if(code == 8) {
//	     // 如果是在textarea内不执行任何操作
//	  if(obj.tagName.toLowerCase() != "input"  && obj.tagName.toLowerCase() != "textarea" && obj.tagName.toLowerCase() != "password")
//		  theEvent.returnValue = false; 
//	        // 如果是readOnly或者disable不执行任何操作
//	  if(obj.readOnly == true || obj.disabled == true) 
//		  theEvent.returnValue = false;                             
//	}
//	return true;
//};

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // 如果是在textarea内不执行任何操作
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // 如果是readOnly或者disable不执行任何操作
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
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
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}	  