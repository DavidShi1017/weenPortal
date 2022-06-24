var url1;

$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

});

function loadGrid() {
	if (conpointId != '') {
		url1 = appUrl + '/role/roleAction!getRole4ConfigJsonList.jspa'
				+ '?conpointId=' + conpointId;
	} else if (menuId != '') {
		url1 = appUrl + '/role/roleAction!getRole4ConfigJsonList.jspa'
				+ '?menuId=' + menuId;
	}
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '',
		url : url1,
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
			field : 'roleId',
			title : 'Role ID',
			width : setColumnWidth(0.2),
			align : 'center',
			sortable : true
		}, {
			field : 'roleName',
			title : 'Role Name',
			width : setColumnWidth(0.4)
		}, {
			field : 'descn',
			title : 'Description',
			width : setColumnWidth(0.4)
		} ] ]
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.roleName = encodeURIComponent($("#roleName").val());
	$("#datagrid").datagrid('load');

}

function closeMaintRole4add() {
	$("#addMenu").window('close');
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
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		window.search();
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