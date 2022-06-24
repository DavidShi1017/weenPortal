$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
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
						+ '/customer/customerAction!getDistiBranchList.jspa',
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
									title : "Disti Name",
									field : 'disti_name',
									width : 130,
									align : 'left'
								},{
									title : "Paycode",
									field : 'payer_to',
									width : 130,
									align : 'center'
								},
								{
									title : "Disti Branch",
									field : 'disti_branch',
									width : 130,
									align : 'left'
								}, {
									title : "Branch Code",
									field : 'branch_code',
									width : 130,
									align : 'center'
								},{
									title : "Region",
									field : 'pricing_region',
									width : 130,
									align : 'center'
								},
								{
									title : "Currency",
									field : 'currency',
									width : 130,
									align : 'center'
								},{
									title : "Sold to",
									field : 'sold_to',
									width : 100,
									align : 'center'
								},{
									title : "Ship to",
									field : 'ship_to',
									width : 100,
									align : 'center'
								},{
									title : "Bill to",
									field : 'bill_to',
									width : 100,
									align : 'center'
								}] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
						, "-", {
							text : 'Delete',
							iconCls : 'icon-remove',
							handler : function() {
								dele();
							}
						}
						, "-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}
						],
//						onDblClickRow : function(rowIndex, rowData) {
//							initMaintWindow('',
//									'/provinceAction!toViewProvince.jspa?id='
//											+ rowData.id);
//						},
						onSelect : function(rowIndex, rowData) {

						}
					});
	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_name = $("#disti_name").val();
	queryParams.disti_branch = encodeURIComponent($("#disti_branch").val());
	queryParams.payer_to = $("#payer_to").val();
	$("#datagrid").datagrid('load');
}

function add() {
	initMaintAccount(false, '500','420', 'Create DistiBranch',
			'/customerAction!toCreateDistiBranch.jspa',50,50);
}

function edit() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '500','420', 'Edit DistiBranch',
				'/customerAction!toUpdateDistiBranch.jspa?id=' + rows[0].id,100,100);
	}
}

  

/**
 * ɾ��
 */
function dele() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('info', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!deleteDistiBranch.jspa?id='
					+ rows[0].id;
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

// �رմ���ҳ��
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
	     // �������textarea�ڲ�ִ���κβ���
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // �����readOnly����disable��ִ���κβ���
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
