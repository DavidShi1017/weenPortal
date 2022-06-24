$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.treegrid(
					{
						iconCls : 'icon-list',
//						title : '菜单树列表',
						url : appUrl
								+ '/menuAjaxAction!getMenuTreeListByAjax2.jspa?node=1&flag=Y',
						loadMsg : 'Loading...',
						singleSelect : false,
						idField : 'id',
						treeField : 'text',
						nowrap : true,
						striped : true,
						animate : true,
						rowStyler : function(row) {
							return 'color:green;';
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'Menu ID',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									field : 'text',
									title : 'Menu Name',
									width : setColumnWidth(0.2),
									formatter : function(value, row, index) {
										var child = row.redirectAttributes;
										return child ? value
												+ '<font color=red>(Subsystem)</font>'
												: value;
									}
								},
								{
									field : 'attributes',
									title : 'Menu Address',
									width : setColumnWidth(0.26),
									formatter : function(value, row, index) {
										return '<a title=' + value + '>'
												+ value + '</a>';
									}
								},
								{
									field : 'redirectAttributes',
									title : 'Subsystem Address',
									width : setColumnWidth(0.26),
									formatter : function(value, row, index) {
										if (value)
											return '<a title=' + value + '>'
													+ value + '</a>';
									}
								},
								{
									field : 'target',
									title : 'Type',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'NA')
											return 'Menu Folder';
										else if (value == '_blank')
											return 'New Window';
										else if (value == 'mainRight')
											return 'Table Display';
									}
								},
								{
									field : 'orderBy',
									title : 'Sequence',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value, row, index) {
										return '<font color="blue" style="font-weight:bold;">'
												+ value + '</font>';
									}
								},
								{
									field : 'price',
									title : 'Operation',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										return '<img style="cursor:pointer" onclick="updateMenu('
												+ id
												+ ')" title="Edit" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ ' <img style="cursor:pointer" onclick="searchRole('
												+ id
												+ ')" " title="Role" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								createMenu();
							}
						}, "-", {
							text : 'Delete',
							iconCls : 'icon-remove',
							handler : function() {
								deleteMenu();
							}
						}, "-" ],
						onBeforeExpand : function(row) {
							$('#datagrid').treegrid('options').url = appUrl
									+ "/menuAjaxAction!getMenuTreeListByAjax2.jspa?node="
									+ row.id + '&flag=Y';
						}
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			self.location.reload();
		});
	}
}
// 创建窗口对象
function initMaintMenu(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintMenu")
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

function createMenu() {
	initMaintMenu('Create Menu', '/menuAction!createMenuPrepare.jspa', 600, 400);
}

function updateMenu(id) {
	initMaintMenu('Edit Menu', '/menuAction!updateMenuPrepare.jspa?id=' + id, 600,
			400);
}

function searchRole(id) {
	initMaintMenu('Role', '/roleAction!searchRole4Config.jspa?menuId=' + id,
			850, 400);
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
			form.action = appUrl + "/menuAction!deleteMenu.jspa";
			form.target = "hideFrame";
			form.submit();

		}
	});
}

// 关闭创建页面
function closeMaintMenu() {
	$("#maintMenu").window('close');
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