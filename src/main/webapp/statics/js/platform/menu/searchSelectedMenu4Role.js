$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#menuIds').combogrid({
		panelWidth : 500,
		panelHight : 500,
		idField : 'id',
		textField : 'id',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : true,
		url : appUrl + '/menuAction!getMenuJsonList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'id',
			title : 'Menu ID',
			width : 60
		}, {
			field : 'name',
			title : 'Menu Name',
			width : 100
		}, {
			field : 'pname',
			title : 'Previous Menu',
			width : 100
		}, {
			field : 'target',
			title : 'Type',
			width : 100
		} ] ],
		toolbar : '#toolbar'
	});
/*	var q = $('#menuIds').combogrid();
	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
	
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl
								+ '/menuAction!getSelectedMenu4RoleJsonList.jspa?roleId='
								+ $("#roleId").val(),
						loadMsg : 'Loading...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						remoteSort : true,
						height : height,
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'id',
							title : 'Menu ID',
							width : setColumnWidth(0.15),
							align : 'center',
							sortable : true
						}, {
							field : 'name',
							title : 'Menu Name',
							width : setColumnWidth(0.3)
						}, {
							field : 'roleId',
							title : 'Role ID',
							width : setColumnWidth(0.3)
						}, {
							field : 'target',
							title : 'Type',
							width : setColumnWidth(0.15),
							align : 'center'
						} ] ],
						toolbar : [
						// {
						// text : '新增',
						// iconCls : 'icon-add',
						// handler : function() {
						// addMenu();
						// }
						// }, "-",
						{
							text : 'Delete',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteMenu();
							}
						}, "-" ]
					});

	// 分页控件
/*	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
}

function initAddMenu(title, url) {
	var url = appUrl + url;
	var WWidth = 750;
	var WHeight = 450;
	var $win = $("#addMenu")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#menuIds').combogrid({
		url : appUrl + '/menuAction!getMenuJsonListforRole.jspa?id=' + val
	});
	$('#menuIds').combogrid("grid").datagrid('reload');

}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.id = encodeURIComponent($("#id").val());
	queryParams.name = encodeURIComponent($("#name").val());
	$("#datagrid").datagrid('load');

}
function selectMenu4Role(ids) {
	// alert($('#menuIds').combobox('getValue'));
	var ids = encodeURIComponent($('#menuIds').combobox('getValue'));
	if ($('#menuIds').combobox('getValue') == "") {
		$.messager.alert('Tips', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Sure to add the menu?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = "menuAction!selectMenu4Role.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function addMenu() {
	initAddMenu('Add new menu', '/menu/menuAction!searchSelectedMenu4Add.jspa');
}
function closeMaintRole4add() {
	$("#addMenu").window('close');
}
function deleteMenu() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Sure to delete?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "menuAction!deleteSelectedMenu4Role.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function close() {
	window.parent.closeMaintRole4menu();
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