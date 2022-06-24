$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#dict_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						
						height : 363,
						striped : true,
						url : appUrl
								+ '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId='
								+ $('#dictTypeId').val() + '&ran='
								+ Math.random(),

						loadMsg : 'Loading...',
						singleSelect : false,
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
									title : 'ID',
									field : 'itemId',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'itemName',
									title : 'Name',
									align : 'left',
									width : 200,
									sortable : true
								},
								{
									field : 'itemValue',
									title : 'Value',
									width : 100,
									align : 'center',
									sortable : true
								},

								{
									field : 'dictTypeId',
									title : 'Type ID',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},

								{
									field : 'parentItemId',
									title : 'Parent',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},

								{
									field : 'itemDescription',
									title : 'Description',
									width : 180,
									align : 'center',
									//sortable : true
								},
								{
									field : 'remark',
									title : 'Remark',
									width : 150,
									align : 'center',
									//sortable : true
									hidden:true
								},
								{
									field : 'lastModify',
									title : 'Last Modify',
									align : 'center',
									width : 140,
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
									width : 140,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.itemId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="Update" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
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
}

// 创建窗口对象
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 250;
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
						minimizable : false,//
						maximizable : false,//
						collapsible : false,//
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('Add Dict', '/dictAction!toCreateDict.jspa?dictTypeId='
			+ $('#dictTypeId').val());
}

function edit(id) {
	
	initMaintWindow('Update Dict', '/dictAction!toUpdateDict.jspa?itemId=' + id);
	

}
function search() {
	$("#dict_list").datagrid('load');
}

function remove() {
	var ids = [];
	$.messager.confirm('Confirm', 'Confirm about delete?', function(r) {
		if (r) {
			var rows = $('#dict_list').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  Please select the data item!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].itemId);
			}

			var form = window.document.forms[0];
			form.action = appUrl
					+ '/dictAction/dictAction!DeleteDict.jspa?ids=' + ids;
			form.target = "hideFrame";
			form.submit();
		}
	});

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
		$("#dict_list").datagrid('load');
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
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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