$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#dict_type_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						height : 520,
						fit:false,
						striped : true,
						url : appUrl
						+ '/dictAction!getCmsTbDictTypeJsonList.jspa?ran='
						+ Math.random(),

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
									id : 'dictTypeId',
									title : 'ID',
									field : 'dictTypeId',
									//width : setColumnWidth(0.1),
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'dictTypeName',
									title : 'Type Name',
									align : 'center',
									//width : setColumnWidth(0.2),
									width : 200,
									sortable : true
								},
								{
									field : 'dictTypeValue',
									title : 'Type Value',
									//width : setColumnWidth(0.2),
									width : 200,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : 'Last Modify',
									align : 'center',
									//width : setColumnWidth(0.1),
									width : 200,
									sortable : true,
									formatter : function(v) {
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}

								},
								{
									field : 'oper',
									title : 'Operate',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.dictTypeId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ '&nbsp;&nbsp;<img style="cursor:pointer" onclick="search_dict('
												+ id
												+ ')" title="查看字典条目" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'Delete',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// 分页控件
	/*var p = $('#dict_type_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});*/
}

function search() {
	var queryParams = $('#dict_type_list').datagrid('options').queryParams;
	queryParams.dictTypeName = encodeURIComponent($("#dictTypeName").val());
	queryParams.dictTypeValue = encodeURIComponent($("#dictTypeValue").val());
	queryParams.remark = encodeURIComponent($("#remark").val());
	$("#dict_type_list").datagrid('load');
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('Create Dict Type', '/dictAction!toCreateDictType.jspa', 600, 200);
}

function edit(id) {

	initMaintWindow('Update Dict Type', '/dictAction!toUpdateDictType.jspa?dictTypeId='
			+ id, 600, 200);

}

function remove() {
	var ids = '';
	var rows = $('#dict_type_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids = rows[i].dictTypeId;
	}
	if (ids == '') {
		$.messager.alert('Tips', 'Please select the data item！', 'Tips');
		return;
	} else {
		$.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
			if (r) {
		var form = window.document.forms[0];
		form.action = appUrl
				+ '/dictAction/dictAction!DeleteDictType.jspa?dictTypeId='
				+ ids;
		form.target = "hideFrame";
		form.submit();
	}});}

}

function search_dict(id) {
	initMaintWindow('字典条目查询',
			'/dictAction/dictAction!searchCmsTbDict.jspa?dictTypeId=' + id,
			900, 400);

}
// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
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
	return true;
};