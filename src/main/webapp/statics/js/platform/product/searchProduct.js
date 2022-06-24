$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '',
				// height : 370,
				fit : true,
				striped : true,
				url : appUrl + '/product/productAction!getProductList.jspa',
				loadMsg : 'Loading...',
				singleSelect : true,
				nowrap : true,
				// idField : 'dictTypeId',
				pagination : true,
				rownumbers : true,
				fitColumns : false,
				columns : [ [ {
					field : 'ck',
					align : 'center',
					checkbox : true
				}, {
					title : "ID",
					field : 'id',
					width : 80,
					hidden : true,
					align : 'center'
				}, {
					title : "Status",
					field : 'state',
					width : 80,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.state;
						if (flag == '1' || flag == undefined) {
							return "<font color='green'>Approved</font>";
						} else {
							return "<font color='red'>Reject</font>";
						}
					}
				}, {
					title : "IsLocked",
					field : 'isLocked',
					width : 70,
					align : 'center',
					hidden : true
				}, {
					title : "12NC",
					field : 'material_id',
					width : 110,
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
					title : "BOOK Part",
					field : 'material_name',
					width : 120,
					align : 'left'
				}, {
					title : "Material Type",
					field : 'material_type',
					width : 150,
					align : 'left'
				}, {
					title : "Product Group",
					field : 'material_groupId',
					width : 100,
					align : 'center'
				}, {
					title : "Lead Time(week)",
					field : 'lead_time',
					width : 90,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.lead_time;
						if (flag == '0') {
							return "";
						} else {
							return flag;
						}
					}
				}, {
					title : "Base Unit",
					field : 'base_unit',
					width : 70,
					align : 'center',
				}, {
					title : "PQ",
					field : 'pq',
					width : 60,
					align : 'center',
				}, {
					title : "Numerator",
					field : 'numerator',
					width : 80,
					align : 'center',
				}, {
					title : "Denominator<br>",
					field : 'denominator',
					width : 80,
					align : 'center',
				}, {
					title : "Order Item",
					field : 'isOrderItem',
					width : 80,
					align : 'center'
				}, {
					title : "Quote Item",
					field : 'isQuoteItem',
					width : 70,
					align : 'center'
				}, {
					title : "DR Item",
					field : 'isDRItem',
					width : 70,
					align : 'center',
				}, {
					title : "Limited QTY",
					field : 'limited_qty',
					width : 80,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.limited_qty;
						if (flag == '0') {
							return "";
						} else {
							return flag;
						}
					}
				}, {
					title : "MOQ(pc)",
					field : 'moq',
					width : 70,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.moq;
						if (flag == '0') {
							return "";
						} else {
							return flag;
						}
					}
				}, {
					title : "WeEn Cost",
					field : 'cost',
					width : 90,
					align : 'center',
				}, {
					title : "RFS Date",
					field : 'rfs_date',
					width : 100,
					align : 'center',
					formatter : function(date) {
						if (date == undefined) {
							return "";
						} else {
							return utcToDate(date);
						}
					},
				},{
					title : "For Customer",
					field : 'customer_group',
					width : 90,
					align : 'center',
					hidden : true
				}, {
					title : "Create Time",
					field : 'create_time',
					width : 100,
					align : 'center',
					formatter : function(date) {
						if (date == undefined) {
							return "";
						} else {
							return utcToDate(date);
						}
					},
					hidden : true
				}, {
					title : "Create UserId",
					field : 'create_userId',
					width : 140,
					align : 'center',
					hidden : true
				}, {
					title : "Org Code",
					field : 'org_code',
					width : 130,
					align : 'center',
					hidden : true
				}, {
					title : "Modify Time",
					field : 'latest_time',
					width : 100,
					align : 'center',
					formatter : function(date) {
						if (date == undefined) {
							return "";
						} else {
							return utcToDate(date);
						}
					}
				}, {
					title : "Modify UserId",
					field : 'latest_userId',
					width : 130,
					align : 'center',
				}, {
					title : "IsDeleted",
					field : 'isdeleted',
					width : 130,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.isdeleted;
						if (flag == 'X') {
							return "<font color='red'>Deteled</font>";
						} else {
							return "<font color='green'>Approved</font>";
						}
					}
				} ] ],
				toolbar : [ "-", {
					text : 'Import',
					iconCls : 'icon-up',
					handler : function() {
						importExcel();
					}
				}, "-", {
					text : 'Export',
					iconCls : 'icon-excel',
					handler : function() {
						downloadExcelModel();
					}
				}, "-", {
					text : 'Sync',
					iconCls : 'icon-reload',
					handler : function() {
						sync();
					}
				} ],
				onDblClickRow : function(rowIndex, rowData) {
					initMaintAccount(false, 820, 330, 'Detail Information',
							'/productAction!toViewProduct.jspa?id='
									+ rowData.id, 100, 50);
				},
			});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.material_id = encodeURIComponent($("#material_id").val());
	queryParams.material_name = encodeURIComponent($("#material_name").val());
	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}

function importExcel() {
	initMaintAccount(false, '700', '380', 'Import ProductInfo',
			'/productAction!importExcel.jspa', 100, 100);
}
// 同步
function sync() {

	initMaintAccount(false, '500', '350', 'Synchronous  ProductInfo',
			'/productAction!toSyncProduct.jspa', 200, 100);
}


// 模板下载
function downloadExcelModel() {
	$.messager
			.confirm(
					'tip',
					'Confirmed about  download the template?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/product/productAction!downloadExcelModel.jspa?excelModel='
									+ encodeURI("product.xls");
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
		// 如果是在textarea内不执行任何操作
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		// 如果是readOnly或者disable不执行任何操作
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
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

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}