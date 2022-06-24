$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function back(){
	window.parent.closeMaintWindow();
	window.parent.search();
};

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						height : height,
						striped : true,
						url : appUrl+ '/endCustomer/endCustomerAction!checkEndCustomer.jspa',
						queryParams: {
							id:$("#id").val(),
							country:encodeURIComponent($("#country").val()),
							ec_alias_name:encodeURIComponent($("#ec_alias_name").val()),
						},
						loadMsg : 'Loading...',
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
									title : "Customer Group",
									field : 'disti_groupId',
									width : 110,
									align : 'center',
									hidden:true
								},
//								{
//									title : "Customer",
//									field : 'disti_customer_name',
//									width : 250,
//									align : 'left',
//								},
								{
									title : "Customer Group",
									field : 'end_customer_groupId',
									width : 150,
									align : 'left',
									hidden:true
								},
								{
									title : "Customer Group",
									field : 'end_customer_groupName',
									width : 150,
									align : 'left',
								},
								{
									title : "Customer",
									field : 'end_customer_name',
									width : 250,
									align : 'left',
									//hidden:true
								},
								{
									title : "Alias Name",
									field : 'ec_alias_name',
									width : 250,
									align : 'left',
								},
								{
									title : "Customer Type",
									field : 'customer_type',
									width : 100,
									align : 'left',
									hidden:true
								},
								{
									title : "Country",
									field : 'country',
									width : 80,
									align : 'left',
								},{
									title : "City",
									field : 'alias_city',
									width : 100,
									align : 'left',
								}] ],
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
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
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