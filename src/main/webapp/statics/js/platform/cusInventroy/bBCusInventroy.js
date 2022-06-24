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
						+ '/cusInventroy/cusInventroyAction!getCusInventroyListForB.jspa',
//						+ '/cusInventroy/cusInventroyAction!getCusInventroyList.jspa',
						loadMsg : 'Loading...',
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,

						columns : [ [ {
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
							title : "STATUS",
							field : 'status',
							width : 60,
							align : 'left',
							formatter : function(value, row, rec) {
								var flag = row.status;
								if (flag == '0') {
									return "<font color='red'>Reject</font>";
								}else if (flag == '3') {
									return "<font color='green'>Approved</font>";
								}else if (flag == '9') {
									return "<font color='blue'>Transfer</font>";
								}else{
									return flag;
								} 
							},
						},{
							title : "Disti Name", //Consolidated Account Description
							field : 'cus_name',
							width : 200,
							align : 'center'
						},{
							title : "SOLD_TO",
							field : 'st_party',
							width : 60,
							align : 'center'
						},{
							title : "BOOK PART",
							field : 'part_name',
							width : 130,
							align : 'left',
						},{
							title : "MATERIAL",
							field : 'material_id',
							width : 120,
							align : 'center',
						},{
							title : "INVENTORY DATE",
							field : 'sender_date',
							width : 110,
							align : 'center',
						},{
							title : "INVENTORY QUANTITY",
							field : 'quantity',
							width : 130,
							align : 'center',
							formatter : function(value, row, rec) {
								return formatNumber(value,0,1);
							}
						},
						{
							title : "INV VALUE PB CURR",
							field : 'currency',
							width : 110,
							align : 'center',
						},{
							title : "INV VALUE PB",
							field : 'price',
							width : 110,
							align : 'center'							,
							formatter : function(value, row, rec) {
								return formatNumber(value,4,1);
							}
						},{
							title : "INV VALUE PB CURR TOTAL",
							field : 'price_total',
							width : 110,
							align : 'center'	,
							formatter : function(value, row, rec) {
								return formatNumber(value,4,1);
							}
						}
//						, {
//							title : "INV VALUE PB_USD",
//							field : 'price_USD',
//							width : 110,
//							align : 'center',
//						}, {
//							title : "INV VALUE PB_USD TOTAL",
//							field : 'price_USD_total',
//							width : 110,
//							align : 'center',
//						},{
//							title : "UNIT COST PB CURR",
//							field : 'price1',
//							width : 110,
//							align : 'center',
//						},{
//							title : "UNIT COST PB CURR TOTAL",
//							field : 'price1_total',
//							width : 110,
//							align : 'center',
//						},{
//							title : "UNIT COST SENT",
//							field : 'price2',
//							width : 110,
//							align : 'center',
//						},{
//							title : "UNIT COST SENT TOTAL",
//							field : 'price2_total',
//							width : 110,
//							align : 'center',
//						}
						,{
							title : "UNIT OF MEASURE",
							field : 'unit',
							width : 110,
							align : 'center',
						},{
							title : "PRICEBOOK CURRENCY",
							field : 'currency',
							width : 110,
							align : 'center',
						},{
							title : "Price Unit_SENT",
							field : 'price_basis',
							width : 110,
							align : 'center',
						}
						 ] ],
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
	queryParams.part_name = encodeURIComponent($("#part_name").val());
	queryParams.cus_name = encodeURIComponent($("#cus_name").val());
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	
	$("#datagrid").datagrid('load');
}


$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");

});


//数据下载
function downloadExcelModel(){
	var start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	var end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
	$.messager.confirm('tip', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/cusInventroyAction!downloadExcelModelForBb.jspa?start_timeString='+start_timeString +"&end_timeString="+end_timeString;
 			form.target = "hideFrame";
			form.submit();
		}
	});
}

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

// 审核
function approved() {
	$.messager.confirm('Confirm', 'Sure to approve?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', '未选中任何信息!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/groupInfoAction!auditGroup.jspa?id='
					+ rows[0].id + '&state=1';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function reject() {
	$.messager.confirm('Confirm', 'Sure to reject?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', '未选中任何信息!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/groupInfoAction!auditGroup.jspa?id='
					+ rows[0].id + '&state=2';
			form.target = "hideFrame";
			form.submit();
		}
	});

}