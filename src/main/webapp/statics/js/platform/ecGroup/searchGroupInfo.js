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
						+ '/ecGroup/groupInfoAction!getGroupInfoList.jspa',
						queryParams : {
							states : $('#states').val()
						},
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
									width : 130,
									hidden : true,
									align : 'center'
								},
								{
									title : "Status",
									field : 'state',
									width : 130,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.state;
										if (flag == '9') {
											return "<font color='red'>Deleted</font>";
										} else if (flag == '0') {
											return "<font color='black'>Pending</font>";
										} else if (flag == '1') {
											return "<font color='green'>Approved</font>";
										} else if (flag == '2') {
											return "<font color='grey'>Reject</font>";
										} else {
											return flag;
										}
									}
								}, {
									title : "Group Id",
									field : 'ecGroup_id',
									width : 130,
									align : 'center',
									//hidden:true
								}, {
									title : "Group Name",
									field : 'ecGroup_name',
									width : 130,
									align : 'center'
								}, {
									title : "Create Time",
									field : 'create_timestr',
									width : 130,
									align : 'center',
								}, {
									title : "Create User",
									field : 'create_userId',
									width : 140,
									align : 'center',
								}, {
									title : "Org Code",
									field : 'org_code',
									width : 130,
									align : 'center',
									hidden:true
								}, {
									title : "Modify Time",
									field : 'modify_timestr',
									width : 140,
									align : 'center'
								}, {
									title : "Modify User",
									field : 'modify_userId',
									width : 130,
									align : 'center',
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
//						, "-", {
//							text : 'Delete',
//							iconCls : 'icon-remove',
//							handler : function() {
//								dele();
//							}
//						}
						, "-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}, "-", {
							text : 'View',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}, "-", {
							text : 'Approved',
							iconCls : 'icon-ok',
							handler : function() {
								approved();
							}
						}, "-", {
							text : 'Reject',
							iconCls : 'icon-cancel',
							handler : function() {
								reject();
							}
						} ],
						onDblClickRow : function(rowIndex, rowData) {
							initMaintWindow('',
									'/groupInfoAction!toViewGroupInfo.jspa?id='
											+ rowData.id);
						},
						onSelect : function(rowIndex, rowData) {
							var state = rowData.state;
						
							 if(state=="0"){
					
								$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
								$('div.datagrid-toolbar a').eq(3).linkbutton('enable');
								$('div.datagrid-toolbar a').eq(4).linkbutton('enable');
							 }else if(state='1'){
								    $('div.datagrid-toolbar a').eq(1).linkbutton('disable');
									$('div.datagrid-toolbar a').eq(3).linkbutton('disable');
									$('div.datagrid-toolbar a').eq(4).linkbutton('disable');
							 }
						}
					});
	// 分页控件
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.ecGroup_id = $("#ecGroup_id").val();
	queryParams.ecGroup_name = $("#ecGroup_name").val();
	if ($("#state").val() == "") {
		$("#states").val('(0,1,2)');
	} else if ($("#state").val() == 0) {
		$("#states").val('(0)');
	} else if ($("#state").val() == 1) {
		$("#states").val('(1)');
	} else if ($("#state").val() == 2) {
		$("#states").val('(2)');
	}
	queryParams.states = $("#states").val();
	$("#datagrid").datagrid('load');
}

function add() {
	initMaintAccount(false, '380','240', 'Create Group',
			'/groupInfoAction!toCreateGroup.jspa',100,100);
}

function edit() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(false, '380','240', 'Edit Group',
				'/groupInfoAction!toUpdateGroup.jspa?id=' + rows[0].id,100,100);
	}
}

function check() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(false, '380','240', 'Detail Information',
				'/groupInfoAction!toViewGroup.jspa?id=' + rows[0].id,100,100);
	}
}

/**
 * 删除
 */
function dele() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/groupInfoAction!delCodeType.jspa?id='
					+ rows[0].id;
			form.target = "hideFrame";
			form.submit();
		}
	});

}


// 审核
function approved() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about approve?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/groupInfoAction!auditGroup.jspa?id='
			+ rows[0].id + '&state=1';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function reject() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about reject?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/groupInfoAction!auditGroup.jspa?id='
			+ rows[0].id + '&state=2';
			form.target = "hideFrame";
			form.submit();
		}
	});
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