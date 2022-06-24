$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
$('#clearV').click(function(){
	$('#branch_id').combogrid('clear');
	$('#start_dateStr').datebox('setValue',"");
	$('#end_dateStr').datebox('setValue',"");
	setTimeout("search()",100);
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
						+ '/sampleOrder/sampleOrderAction!getPendingSampleOrderList.jspa',
				        loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
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
									align : 'center',

								},
								{
									title : "managerId",
									field : 'manager_id',
									width : 80,
									hidden : true,
									align : 'center',

								},
								{
									title : "Status",
									field : 'state',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.state;
										if (flag == '9') {
											return "<font color='red'>Deleted</font>";
										} else if (flag == '0') {
											return "<font color='green'>Approved</font>";
										} else if (flag == '1') {
											return "<font color='black'>Pending</font>";
										} else if (flag == '2') {
											return "<font color='red'>Rejected</font>";
										} else{
											return flag;
										} 
									},
								},
								{
									title : "Sync Status",
									field : 'sync_state',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.sync_state;
										if (flag == '0') {
											return "<font color='red'>Pending</font>";
										}else if (flag == '1') {
											return "<font color='green'>Active</font>";
										} 
									},
									hidden:true
								},	
								{
									title : "Purchase Order",
									field : 'order_id',
									width : 110,
									align : 'center',
									hidden:true
								},
								{
									title : "Order Num",
									field : 'sap_order_id',
									width : 110,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.sap_order_id;
										if (flag != undefined) {
										var str=(flag).replace( /^0*/, '');
										return str;
										}else{
											return "";
											}
										}
								},
								{
									title : "Create Date",
									field : 'create_time',
									width : 110,
									align : 'center',
									formatter : function(date){
										return utcToDate(date);
									}
								},
								{
									title : "Order Type",
									field : 'type_id',
									width : 120,
									align : 'left',
									hidden:true
								},
								{
									title : "Sold-To",
									field : 'branch_id',
									width : 250,
									align : 'left',
									hidden:true
								},
								{
									title : "Ship-To Company",
									field : 'company',
									width : 150,
									align : 'left'
								},
								{
									title : "Ship-To Consignee",
									field : 'contact_name',
									width : 150,
									align : 'left'
								},
								{
									title : "Ship-To Phone No",
									field : 'contact_tel',
									width : 150,
									align : 'left'
								},
								{
									title : "Ship-To Country",
									field : 'country',
									width : 150,
									align : 'left',
								},
								{
									title : "Ship-To City",
									field : 'city',
									width : 150,
									align : 'left',
								},
								{
									title : "Ship-To Street",
									field : 'street',
									width : 250,
									align : 'left'
								},
								{
									title : "Saler",
									field : 'saler',
									width : 60,
									align : 'center',
									hidden:true
								},														
								{
									title : "Project Name",
									field : 'project_name',
									width : 150,
									align : 'left',
									hidden:true
								},
								{
									title : "Ship-To Region",
									field : 'ship_to_region',
									width : 80,
									align : 'left'
								},
								{
									title : "Account Manager",
									field : 'account_manager',
									width : 120,
									align : 'left'
								},
								{
									title : "Applicant Name",
									field : 'applicant_name',
									width : 150,
									align : 'left'
								},
								{
									title : "Applicant Company",
									field : 'applicant_company',
									width : 150,
									align : 'left'
								},
								] ],
						toolbar : [ 
						    "-", {
					        	   text : 'Audit',
					        	   iconCls : 'icon-view',
					        	   handler : function() {
					        		   View();
					        	   }
					           },
					        "-", {
					        	   text : 'Output',
					        	   iconCls : 'icon-view',
					        	   handler : function() {
					        		   output();
					        	   }
					           }
						 ],
						onDblClickRow:function(rowIndex,rowData){
							initMaintAccount(true,'1000','550','Detail Information', '/sampleOrderAction!toAuditSampleOrder.jspa?id='+rowData.id);
						},
						onLoadSuccess:function(){
						},
					});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.states = $("#state").val();
	queryParams.company = encodeURIComponent($("#company").val());
	queryParams.contact_name = encodeURIComponent($("#contact_name").val());
	queryParams.start_dateStr = encodeURIComponent($("#start_dateStr").datebox('getValue'));
	queryParams.end_dateStr = encodeURIComponent($("#end_dateStr").datebox('getValue'));
	queryParams.sap_order_id = encodeURIComponent($("#sap_order_id").val());
	$("#datagrid").datagrid('load');
}

function View(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/sampleOrderAction!toAuditSampleOrder.jspa?id='+rows[0].id);
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
		$.messager.alert('Error', failResult, 'warning');
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
	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}

function output(){
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/sampleOrderAction!downloadSampleOrderDetail.jspa?type=2';
 			form.target = "hideFrame";
			form.submit();
		}
	});
}