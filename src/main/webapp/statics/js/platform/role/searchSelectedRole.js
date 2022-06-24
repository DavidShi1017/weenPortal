$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});
function loadGrid() {
	var url1;
	if($('#whichPage').val()=='funLog'){
		url1 ='/customerAction!getRoleListJosn.jspa?roleType=other';
	}else{
		url1 ='/roleAction!getSelectedRoleJsonListJosn.jspa';
	}
	$('#roleIds').combogrid({
		panelWidth : 500,
		panelHeight : 365,
		idField : 'roleId',
		textField : 'roleId',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : true,
		url : appUrl + url1,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'roleId',
			title : 'Role ID',
			width : 170
		}, {
			field : 'roleName',
			title : 'Role Name',
			width : 145
		}, {
			field : 'descn',
			title : 'Description',
			width : 90
		} ] ],
		toolbar : '#toolbar'
	});
	var q = $('#roleIds').combogrid("grid").datagrid();
/*	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
	$('#datagridRole').datagrid({
		iconCls : 'icon-list',
		title : '',
		url : appUrl + '/roleAction!getSelectedRoleJsonList.jspa?stationId='+stationId,
		loadMsg : 'Loading...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		remoteSort : true,
		height : height,
		width : 800,
		columns : [ [ {
			field : 'id',
			checkbox : true
		}/*
			 * ,{ field : 'id', checkbox : true }
			 */
		, {
			field : 'roleId',
			title : 'Role ID',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'roleName',
			title : 'Role Name',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'descn',
			title : 'Description',
			width : setColumnWidth(0.4),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : 'Delete',
			iconCls : 'icon-remove',
			handler : function() {
				deleteRole();
			}
		}, "-" ]
	});

	// 分页控件
/*	var p = $('#datagridRole').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
}
function searcher(val) {
	val = encodeURIComponent(val);
	$('#roleIds').combogrid({
						url : appUrl
								+ '/roleAction!getSelectedRoleJsonListJosn.jspa?roleId='
								+ val
					});
	$('#roleIds').combogrid("grid").datagrid('reload');

}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		$('#roleIds').combobox('setText', '');
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}

function search() {
	var queryParams = $('#datagridRole').datagrid('options').queryParams;
//	queryParams.stationId = $("#stationId").val();
	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.roleName = encodeURIComponent($("#roleName").val());
	$("#datagridRole").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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

function deleteRole() {
	$.messager.confirm('Confirm', 'Confirm Delete Role?', function(r) {
		if (r) {
			var rows = $('#datagridRole').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', ' Please Select Data!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$("#sids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteSelectedRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function selectRole() {
	var rows = $('#roleIds').combogrid("grid").datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('Tips', "Please Select Save", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = "roleAction!selectRole.jspa";
	form.target = "hideFrame";
	form.submit();
}