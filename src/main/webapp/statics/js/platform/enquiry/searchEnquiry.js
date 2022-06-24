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
								+ '/enquiry/enquiryAction!getEnquiryList.jspa',
						loadMsg : '数据远程载入中,请等待...',
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
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.state;
										if (flag == '9') {
											return "<font color='red'>Deleted</font>";
										}else if (flag == '0') {
											return "<font color='black'>New</font>";
										} else if (flag == '1') {
											return "<font color='blue'>1</font>";
										} else if (flag == '2') {
											return "<font color='green'>2</font>";
										} else if (flag == '3') {
											return "<font color='gray'>3</font>";
										}
									}
								},
								{
									title : "Purchase Enquiry",
									field : 'enquiry_id',
									width : 110,
									align : 'center'
								},
								{
									title : "Enquiry Type",
									field : 'type_id',
									width : 120,
									align : 'left'
								},
								{
									title : "Customer",
									field : 'customer_id',
									width : 100,
									align : 'left'
								},
								{
									title : "Customer Group",
									field : 'cusGroup_id',
									width : 110,
									align : 'left',
								},
								{
									title : "End Customer",
									field : 'endCustomer_id',
									width : 60,
									align : 'center',
								},
								{
									title : "EC Group",
									field : 'ecGroup_id',
									width : 60,
									align : 'center'
								},
								{
									title : "Currency",
									field : 'currency_code',
									width : 90,
									align : 'left'
								},
								{
									title : "Project",
									field : 'project_name',
									width : 90,
									align : 'left'
								},
								{
									title : "Assembly",
									field : 'isDelivery',
									width : 90,
									align : 'left'
								},
								{
									title : "Start Date",
									field : 'start_date',
									width : 90,
									align : 'left',
								},
								{
									title : "Latest Expire",
									field : 'latest_expire',
									width : 90,
									align : 'left',
								}] ],
						toolbar : [  "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}
						 ,  "-", {
							text : 'Delete',
							iconCls : 'icon-remove',
							handler : function() {
								dele();
							}
						} ,  "-", {
							text : 'Check',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}
						 ],
						onDblClickRow:function(rowIndex,rowData){
							initMaintWindow('订单信息查看','/enquiryAction!toViewEnquiry.jspa?id='+rowData.id);
						},
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.enquiry_id = $("#enquiry_id").val();
	queryParams.customer_id = $("#customer_id").val();
//	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}

function add(){
		initMaintAccount(true,'1000','550','Create Enquiry', '/enquiryAction!toCreateEnquiry.jspa',0,0);	
}

function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', '未选中数据！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Edit Enquiry', '/enquiryAction!toUpdateEnquiry.jspa?id='+rows[0].id,0,0);	
	}
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', '未选中数据！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/enquiryAction!toViewEnquiry.jspa?id='+rows[0].id);
	}	
}

/**
 * 删除
 */
function dele() {
	$.messager.confirm('Confirm', 'Sure to delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', '未选中任何信息!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/enquiryAction!deleteEnquiry.jspa?id='+rows[0].id;
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