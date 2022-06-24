$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '',
		fit : true,
		striped : true,
		url : appUrl + '/designReg/designRegAction!checkDesignReg.jspa',
		queryParams : {
			id : $("#id").val(),
			customer_id : $("#customer_id").val(),
			endCus_id : $("#endCus_id").val(),
			endCus_groupId : encodeURIComponent($("#endCus_groupId").val()),
			material_id : $("#material_id").val(),
		},
		loadMsg : 'Loading...',
		singleSelect : true,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		fitColumns : false,
		columns : [ [ {
			field : 'ck',
			align : 'center',
			checkbox : true
		}, {
			field : 'id',
			title : 'pk',
			width : 60,
			align : 'center',
			hidden : true
		}, {
			field : 'main_id',
			title : 'main_id',
			width : 150,
			align : 'center',
			hidden : true
		}, {
			field : 'Status',
			title : 'state',
			width : 100,
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
					return "<font color='gray'>Rejected</font>";
				} else if (flag == 3) {
					return "<font color='red'>Expired</font>";
				}
			}
		}, {
			field : 'material_id',
			title : '12NC',
			width : 100,
			align : 'left',
			formatter : function(value, row, rec) {
				var flag = row.material_id;
				if (flag == '' || flag == undefined) {
					return "";
				} else {
					var str = $.trim(flag);
					str = str.substring(str.length - 12, str.length);
					return str;
				}
			}
		}, {
			field : 'material_name',
			title : 'BookPart',
			width : 150,
			align : 'center',
		}, {
			field : 'drNum',
			title : 'DR number',
			width : 100,
			align : 'center',
		}, {
			field : 'endCus_groupName',
			title : 'EC Group',
			width : 100,
			align : 'center',
		}, {
			field : 'endCus_name',
			title : 'End Customer',
			width : 100,
			align : 'center',
		}, {
			field : 'customer_id',
			title : 'Customer',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			field : 'cus_groupId',
			title : 'Disti',
			width : 100,
			align : 'left',
		}, {
			title : "Disti Branch",
			field : 'disti_branch',
			width : 250,
			align : 'left'
		}, {
			field : 'start_dateStr',
			title : 'Registration Date',
			width : 100,
			align : 'center',
		}, {
			field : 'end_dateStr',
			title : 'Expiry Date',
			width : 100,
			align : 'center',
		}, {
			field : 'project_name',
			title : 'Project',
			width : 150,
			align : 'center',
			hidden : true
		} ] ],
	});

}

function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if (failResult == undefined || failResult == 'undefined') {
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
	if (event.keyCode == 8) {
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
			event.returnValue = false;
	}
	return true;
};