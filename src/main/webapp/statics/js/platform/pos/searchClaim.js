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
						url : appUrl
						+ '/claimAction!getPosList.jspa?ran='
								+ Math.random(),

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
 										field : 'status_num',
 										width : 100,
 										align : 'center'
 									},{
 										title : "Disti Name",
 										field : 'disti_name',
 										width : 300,
 										align : 'left'
 									},{
 										title : "File Id",
 										field : 'file_id',
 										width : 130,
 										align : 'center'
 									},{
 										title : "File Name",
 										field : 'file_url',
 										width : 240,
 										align : 'left',
 									},{
 										title : "Sync Time",
 										field : 'created_time',
 										width : 100,
 										align : 'center',
 										formatter : function(date){
 											return utcToDate(date);
 										}
 									},{
 										title : "Sync User",
 										field : 'created_user',
 										width : 100,
 										align : 'center',
 										
 									},{
 										title : "Data From",
 										field : 'data_from',
 										width : 100,
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
						toolbar : [  "-", {
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
						},   "-", {
							text : 'Outport',
							iconCls : 'icon-excel',
							handler : function() {
								outport();
							}
						}, 
//						"-", {
//							text : 'Check',
//							iconCls : 'icon-excel',
//							handler : function() {
//								check();
//							}
//						}
						], 
						
					onDblClickRow : function(rowIndex, rowData) {
						initMaintAccount(true,1000,500,'Detail',
								'/claimAction!toPosPreDetail.jspa?file_id='+rowData.file_id+"&file_url="+encodeURIComponent(rowData.file_url),0,0);
					},
					});
 
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.branchId = encodeURIComponent($("#disti_name").val());
	queryParams.status = $("#status").val();
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	queryParams.upload_start_timeString = $("#upload_start_time").datebox('getValue');
	queryParams.upload_end_timeString = $("#upload_end_time").datebox('getValue');
	queryParams.file_url = encodeURIComponent($("#file_url").val());
	queryParams.file_id = $("#file_id").val();
	$("#datagrid").datagrid('load');
}

function importExcel(){
	initMaintAccount(false,'700','400','Import', '/claimAction!importExcel.jspa',100,100);
}


$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");
	$('#upload_start_time').datebox('setValue',"");
	$('#upload_end_time').datebox('setValue',"");
});

//数据下载
function outport(){
	var start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	var end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/claimAction!downloadExcelModel.jspa?start_timeString='+start_timeString +'&end_timeString='+end_timeString+'&file_url='+encodeURIComponent($("#file_url").val())+'&branchId='+encodeURIComponent($("#disti_name").val());
 			form.target = "hideFrame";
			form.submit();
		}
	});
}



//模板下载
function downloadExcelModel(){
	var start_timeString =   encodeURIComponent($("#start_time").datebox('getValue'));
	var end_timeString =   encodeURIComponent($("#end_time").datebox('getValue'));
		$.messager.confirm('Tips', 'Download claim mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?start_timeString='+start_timeString +'&end_timeString='+end_timeString+'&excelModel='+encodeURI("Claim.xlsx");
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