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
								+ '/cusInventroy/cusInventroyAction!getCusInventroyList.jspa',
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
						}, 
//						{
//							title : "Status",
//							field : 'status',
//							width : 100,
//							formatter : function(value, row, rec) {
//								var flag = row.status;
//								if (flag == '0') {
//									return "<font color='red'>Reject</font>";
//								}else if (flag == '3') {
//									return "<font color='green'>Approved</font>";
//								}else if (flag == '1') {
//									return "<font color='blue'>non-checked</font>";
//								}else{
//									return flag;
//								} 
//							},
//							align : 'center',
//							
//						}, 
						{
							title : "ID",
							field : 'id',
							width : 130,
							hidden : true,
							align : 'center'
						},{
							title : "Status",
							field : 'status_num',
							width : 80,
							align : 'center'
						},{
							title : "Disti Name",
							field : 'cus_name',
							width : 250,
							align : 'left'
						},{
							title : "File Id",
							field : 'file_id',
							width : 70,
							align : 'center'
						},{
							title : "File Name",
							field : 'file_url',
							width : 250,
							align : 'left',
						},{
							title : "Frequency",
							field : 'frequency',
							width : 80,
							align : 'center',
						},{
							title : "Qty",
							field : 'qty',
							width : 100,
							align : 'center',
							formatter : function(value, row, rec) {
								return formatNumber(value,0,1);
							}
						},{
							title : "Amount",
							field : 'amount',
							width : 150,
							align : 'center',
							formatter : function(value, row, rec) {
								return formatNumber(value,4,1);
							}
						},{
							title : "Created Time",
							field : 'synchronization_timestr',
							width : 90,
							align : 'center',
						},{
							title : "Created User",
							field : 'synchronization_user',
							width : 90,
							align : 'center',
							
						}, {
							title : "Data From",
							field : 'data_from',
							width : 70,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.data_from;
								if (flag == '1') {
									return "<font>Portal</font>";
								}else if (flag == '2') {
									return "<font>EDI</font>";
								}else{
									return flag;
								} 
							},
						}] ],
						toolbar : [ "-", {
							text : 'Import',
							iconCls : 'icon-excel',
							handler : function() {
								importExcel();
							}
						},   "-", {
							text : 'Download',
							iconCls : 'icon-excel',
							handler : function() {
								downloadExcelModel();
							}
						}
						,"-", {
							text : 'Check',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						} 
						,"-", {
							text : 'Weekly',
							iconCls : 'icon-view',
							handler : function() {
								weeklyMonthly('Weekly');
							}
						} 
						,"-", {
							text : 'Monthly',
							iconCls : 'icon-view',
							handler : function() {
								weeklyMonthly('Monthly');
							}
						} 
					],
						onDblClickRow : function(rowIndex, rowData) {
							initMaintAccount(true,1000,500,'Detail',
									'/cusInventroyAction!toSearchCusInventroyDetail.jspa?file_id='+rowData.file_id+"&file_url="+encodeURIComponent(rowData.file_url),0,0);
						},
					});
}

function search() {
	
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.cus_name = encodeURIComponent($("#cus_name").val());
	queryParams.file_url = encodeURIComponent($("#file_url").val());
	
	queryParams.file_id_str = encodeURIComponent($("#file_id").val());
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	queryParams.status = $("#status").val();
	$("#datagrid").datagrid('load');
}


function weeklyMonthly(mark){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows[0].file_id==null || rows[0].file_id==undefined){
		$.messager.alert('info', 'Please select the data you need to operate');
		return;
	}
	for(var i=0;i<=rows.length-1;i++){
		if(mark=="Weekly" && rows[i].frequency=='Monthly'){
			$.messager.alert('info', 'monthly can not be updated to  weekly');
			return;
		}
	}
	var file_id_str ="";
	for(var i=0;i<=rows.length-1;i++){
		file_id_str = rows[i].file_id+","+file_id_str;
	}
	
	$.messager.confirm('Confirm', 'Sure to Operation?', function(r) {
		if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/cusInventroyAction!weeklyMonthly.jspa?file_id_str='+file_id_str.substring(0, file_id_str.length-1)+'&frequencyMark='+mark
				form.target = "hideFrame";
				form.submit();
			}
		}
	);
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

//模板下载
function downloadExcelModel(){
	
		$.messager.confirm('Tips', 'Download inventroy mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl +'/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("inventory.xls");
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


$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");
});


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