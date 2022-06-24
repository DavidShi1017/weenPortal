$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/roleAction!getRoleJsonList.jspa?ran='
								+ Math.random(),
						loadMsg : 'Loading...',
						singleSelect : false,
						pagination : true,
						idField : 'roleId',
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									id : 'roleId',
									field : 'roleId',
									title : 'Role Id',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'roleName',
									title : 'Role Name',
									width : setColumnWidth(0.2),
									align : 'center'

								},
								{
									field : 'roleType',
									title : 'Role Type',
									width : setColumnWidth(0.1),
									align : 'center'

								},
								{
									field : 'descn',
									title : 'Description',
									width : setColumnWidth(0.2),
									align : 'center'

								},
								{
									field : 'price',
									title : 'Operation',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.roleId;
										return '<img style="cursor:pointer" onclick=updateRole("'
												+ id
												+ '") title="Edit"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												+ '  <img style="cursor:pointer" onclick=searchSelectedMenu4Role("'
												+ id
												+ '") title="Menu" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>'
												/*+ '  <img style="cursor:pointer" onclick=searchRoleConpoint("'
												+ id
												+ '") title="��ӿ��Ƶ�" src='
												+ imgUrl
												+ '/images/actions/action_add.png align="absMiddle"></img>'*/
												+ '  <img style="cursor:pointer" onclick=searchPositionType4Role("'
												+ id
												+ '") title="User" src='
												+ imgUrl
												+ '/images/actions/action_station.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								createRole();
							}
						}, "-", {
							text : 'Delete',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteRole();
							}
						}, "-" ]
					});

//	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.roleName = encodeURIComponent($("#roleName").val());
	$("#datagrid").datagrid('load');
}

function initMaintRole(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintRole")
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
						draggable : true,
						left : 100,
						top  : 100
					});

	$win.window('open');

}
function closeMaintRole() {
	$("#maintRole").window('close');
}
function closeMaintRole4menu() {
	$("#maintRole4menu").window('close');
}

function createRole() {
	initMaintRole('Create New Role', '/roleAction!createRolePrepare.jspa', 580, 300);
}
function searchSelectedMenu4Role(id) {
	var id = encodeURIComponent(id);
	initMaintRole('Menu For Role', '/menuAction!searchSelectedMenu4Role.jspa?roleId='
			+ id, 800, 400);
}
function updateRole(id) {
	var id = encodeURIComponent(id);
	initMaintRole('Role Edit', '/roleAction!updateRolePrepare.jspa?roleId=' + id,
			500, 300);
}
function searchRoleConpoint(id) {
	var id = encodeURIComponent(id);
	initMaintRole('select role', '/conpoint!searchRoleConpoint.jspa?roleId=' + id,
			900, 400);
}
function searchPositionType4Role(id) {
	var id = encodeURIComponent(id);
	initMaintRole('User For Role ',
			'/roleAction!searchPositionType4Role.jspa?roleId=' + id,
			800, 400);
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
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function deleteRole() {
	$.messager.confirm('Confirm', 'confirm delete data?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', 'please select role!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].roleId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // �������textarea�ڲ�ִ���κβ���
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // �����readOnly����disable��ִ���κβ���
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};
