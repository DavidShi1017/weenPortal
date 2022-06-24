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
						height : height,
						striped : true,
						url : appUrl
								+ '/endCustomer/endCustomerAction!getEndCustomerList.jspa',
						loadMsg : 'loading...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
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
									title : "Status",
									field : 'state',
									width : 80,
									align : 'center'
								},
								{
									title : "Customer Group",
									field : 'disti_groupId',
									width : 110,
									align : 'center'
								},
								{
									title : "Customer",
									field : 'disti_customer_id',
									width : 120,
									align : 'left'
								},
								{
									title : "EC Group",
									field : 'end_customer_groupId',
									width : 100,
									align : 'left',
									hidden:true
								},
								{
									title : "EC Group",
									field : 'end_customer_groupName',
									width : 100,
									align : 'left'
								},
								{
									title : "End Customer",
									field : 'end_customer_name',
									width : 100,
									align : 'left'
								},
								{
									title : "EC Type",
									field : 'customer_type',
									width : 70,
									align : 'left'
								},
								{
									title : "EC Property",
									field : 'customer_property',
									width : 80,
									align : 'left',
								},
								{
									title : "Country",
									field : 'country',
									width : 60,
									align : 'center'
								},
								{
									title : "Currency",
									field : 'currency_code',
									width : 60,
									align : 'center',
								},
								{
									title : "Address",
									field : 'address',
									width : 90,
									align : 'left'
								},
								{
									title : "State",
									field : 'province',
									width : 90,
									align : 'left'
								},
								{
									title : "Contact",
									field : 'contact_name',
									width : 90,
									align : 'left',
								},
								{
									title : "Tel Number",
									field : 'tel',
									width : 90,
									align : 'left',
								},
								{
									title : "Create",
									field : 'create_time',
									width : 90,
									align : 'left',
								}] ],
						toolbar : [  "-", {
							text : 'Check',
							iconCls : 'icon-view ',
							handler : function() {
								check();
							}
						},  "-", {
							text : 'Approved',
							iconCls : 'icon-view ',
							handler : function() {
								approved();
							}
						},  "-", {
							text : 'Reject',
							iconCls : 'icon-view ',
							handler : function() {
								reject();
							}
						}, "-" ]
					});

}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_customer_id = encodeURIComponent($("#disti_customer_id").val());
	queryParams.end_customer_name = encodeURIComponent($("#end_customer_name").val());
	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/endCustomerAction!toViewEndCustomer.jspa?id='+rows[0].id,0,0);
	}	
}
function approved(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Audit Opinion', '/endCustomerAction!toAuditOpinion.jspa?id='+rows[0].id+'&state=1',0,0);
	}	
}
function reject(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Audit Opinion', '/endCustomerAction!toAuditOpinion.jspa?id='+rows[0].id+'&state=2',0,0);
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
	$("#maintWindow").window('close');
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
		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
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