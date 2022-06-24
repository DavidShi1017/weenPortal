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
						// height : 370,
						fit : true,
						striped : true,
						url : appUrl
								+ '/cusInventroy/cusInventroyAction!getCusInventroyListById.jspa?file_id='+$("#file_id").html(),
//						+ '/cusInventroy/cusInventroyAction!getCusInventroyList.jspa',
						loadMsg : 'Loading...',
						loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,

						columns : [ [ {
							field : 'ck',
							align : 'center',
							checkbox : true
						}, {
							title : "Tips",
							field : 'tips',
							width : 150,
							align : 'center',
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
								}else if (flag == '9') {
									return "<font color='grey'>Transfer</font>";
								}else if (flag == '1') {
									return "<font color='blue'>Pending</font>";
								}else{
									return flag;
								} 
							},
							align : 'center',
							
						}, {
							title : "ID",
							field : 'id',
							width : 130,
							hidden : true,
							align : 'center'
						}, {
							title : "Frequency",
							field : 'frequency',
							width : 100,
							align : 'center',
						}, {
							title : "Sales Org",
							field : 'sales_org',
							width : 60,
							align : 'center',
						},{
							title : "St Party",
							field : 'st_party',
							width : 60,
							align : 'center'
						}
//						,{
//							title : "By Party",
//							field : 'by_party',
//							width : 100,
//							align : 'center',
//						}
						, {
							title : "Disti Name",
							field : 'cus_name',
							width : 200,
							align : 'center'
						},  {
							title : "Sender Date",
							field : 'sender_date',
							width : 80,
							align : 'center',
						}, {
							title : "Book Part",
							field : 'part_name',
							width : 150,
							align : 'left',
						}, {
							title : "Quantity",
							field : 'quantity',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
									return formatNumber(value,0,1);
								}
						}, {
							title : "Price Basis",
							field : 'price_basis',
							width : 60,
							align : 'center',
						},{
							title : "Currency",
							field : 'currency',
							width : 60,
							align : 'center',
						}, {
							title : "Price",
							field : 'price',
							width : 80,
							align : 'center',
						},{
							title : "Sync Time",
							field : 'synchronization_timestr',
							width : 80,
							align : 'center',
						},{
							title : "Sync User",
							field : 'synchronization_user',
							width : 100,
							align : 'center',
							
						}, {
							title : "Data From",
							field : 'data_from',
							width : 60,
							align : 'center',
							hidden : true,
						},
						
						{
							title : "Message Number",
							field : 'message_number',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Doc Id",
							field : 'doc_id',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Doc Code",
							field : 'doc_code',
							width : 130,
							align : 'center',
							hidden : true,
						}, {
							title : "Doc Identifier",
							field : 'doc_identifier',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Function Code",
							field : 'function_code',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Sender Id",
							field : 'sender_id',
							width : 100,
							align : 'center',
							hidden : true
						}, {
							title : "Recipient Id",
							field : 'recipient_id',
							width : 100,
							align : 'center',
								hidden : true,
						}, {
							title : "Line Item",
							field : 'line_item',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Item Type",
							field : 'item_type',
							width : 100,
							align : 'center',
							hidden : true,
							
						}, {
							title : "Inventroy Method",
							field : 'inventroy_method',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Quantity Type",
							field : 'quantity_type',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Unit Code",
							field : 'unit_code',
							width : 100,
							align : 'center',
							hidden : true
						},  {
							title : "Price Code",
							field : 'price_code',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Price Type",
							field : 'price_type',
							width : 100,
							align : 'center',
							hidden : true,
						}, {
							title : "Update Time",
							field : 'update_timestr',
							width : 100,
							align : 'center',
							hidden : true,
						},{
							title : "Update User",
							field : 'update_user',
							width : 100,
							align : 'center',
							hidden : true,
						} ] ],
						toolbar : ["-", {
							text : 'Outport',
							iconCls : 'icon-excel',
							handler : function() {
								downloadExcelModelForOne();
							}
						},"-", {
							text : 'Approved',
							iconCls : 'icon-view',
							handler : function() {
								approved();
							}
						} ,"-", {
							text : 'Reject',
							iconCls : 'icon-view',
							handler : function() {
								reject();
							}
						} 
					],
						onDblClickRow : function(rowIndex, rowData) {
							initMaintWindow('',
									'/cusInventroyAction!toViewcusInventroy.jspa?id='
											+ rowData.id);
						},
					});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.part_name = encodeURIComponent($("#part_name").val());
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	queryParams.status = $("#status").val();
	$("#datagrid").datagrid('load');
}

function reset() {
	
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	}else{
		var form = window.document.forms[0];
		form.action = appUrl + '/cusInventroyAction!resetCusInventroy.jspa?id='+rows[0].id;
		form.target = "hideFrame";
		form.submit();
	}
}

//function approved() {
//	
//	var rows = $('#datagrid').datagrid('getSelections');
//  	if (rows.length == 0) {
//		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
//		return;
//	}else{
//		var form = window.document.forms[0];
//		form.action = appUrl + '/cusInventroyAction!approvedCusInventroy.jspa?id='+rows[0].id;
//		form.target = "hideFrame";
//		form.submit();
//	}
//}
//
//function reject() {
//	
//	var rows = $('#datagrid').datagrid('getSelections');
//  	if (rows.length == 0) {
//		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
//		return;
//	}else{
//		var form = window.document.forms[0];
//		form.action = appUrl + '/cusInventroyAction!rejectCusInventroy.jspa?id='+rows[0].id;
//		form.target = "hideFrame";
//		form.submit();
//	}
//}

function check() {
	var form = window.document.forms[0];
	form.action = appUrl + '/cusInventroyAction!checkEDI.jspa?'
	form.target = "hideFrame";
	form.submit();
}


function initMaintAccount(ffit, widte, height, title, url, l, t) {
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
						fit : FFit,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');

}

function importExcel(){
	initMaintAccount(false,'700','380','Import InventroyInfo', '/cusInventroyAction!importExcel.jspa',100,100);
}

//模板下载
function downloadExcelModel(){
		$.messager.confirm('Tips', 'Download iventory mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl +'/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("inventory.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}

//数据下载
function downloadExcelModelForOne(){
		$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl +'/cusInventroyAction!downloadExcelModelForOne.jspa?file_id='+$("#file_id").html();
	 			form.target = "hideFrame";
				form.submit();
			}
		});
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

//审核
function approved() {
	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
		if(rows[i].status=='1'){
			mark++;
		}
	}
	if(rows[0].id==null){
		$.messager.alert('info', 'Please select the data you need to operate!');
		return;
		}else if(mark!=rows.length){
			$.messager.alert('info', 'Only when pending can be clicked!');
			return;
		}
	$.messager.confirm('Confirm', 'Sure to approve?', function(r) {
		if (r) {
			
			
			var form = window.document.forms[0];
			form.action = appUrl + '/cusInventroyAction!approvedCusInventroy.jspa?id='
					+ ids.substring(0, ids.length-1);
			form.target = "hideFrame";
			form.submit();
		}
		}
	);
}
	

function reject() {
	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
		if(rows[i].status=='1'){
			mark++;
		}
	}
	if(rows[0].id==null){
		$.messager.alert('info', 'Please select the data you need to operate!');
		return;
		}else if(mark!=rows.length){
			$.messager.alert('info', 'Only when pending can be clicked!');
			return;
		}
	$.messager.confirm('Confirm', 'Sure to reject?', function(r) {
		if (r) {
			
			var file_id = $('#file_id1').val();
			var form = window.document.forms[0];
			form.action = appUrl + '/cusInventroyAction!rejectCusInventroy.jspa?id='
					+ ids.substring(0, ids.length-1)+"&file_id ="+file_id;
			form.target = "hideFrame";
			form.submit();
		}
		}
	);
	}
