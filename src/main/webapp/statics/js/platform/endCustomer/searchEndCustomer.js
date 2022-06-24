var disti_groupId;
$(document).ready(function() {
	// loadGrid(); // 权限点查询
	var loginRole = $('#loginRole').val().split("*");
	for (var i = 0; i < loginRole.length; i++) {
		var role = loginRole[i];
		if (role == "JXS") {
			$('#disti_groupId').attr("readonly", "readonly");
			$('.disti').hide();
			break;
		}
	}
	disti_groupId = $('#disti_groupId').val();
	$('#hideFrame').bind('load', promgtMsg);

});

$('#clearV').click(function() {
	$('#disti_groupId').val(disti_groupId);
});

$('#datagrid')
		.datagrid(
				{
					iconCls : 'icon-list',
					title : '',
					// height : 370,
					fit : true,
					striped : true,
					url : appUrl
							+ '/endCustomer/endCustomerAction!searchEndCustomerList.jspa?ecPage=Y',
					queryParams : {
						end_customer_groupName : encodeURIComponent($(
								"#end_customer_groupName").val()),
						disti_groupId : encodeURIComponent($("#disti_groupId")
								.val()),
						end_customer_name : encodeURIComponent($(
								"#end_customer_name").val()),
						states : $("#states").val()
					},
					loadMsg : 'Loading...',
					singleSelect : true,
					nowrap : true,
					pagination : true,
					rownumbers : true,
					fitColumns : false,
					frozenColumns : [ [
							{
								field : 'ck',
								align : 'center',
								checkbox : true
							},
							{
								title : "ID",
								field : 'id',
								width : 80,
								hidden : true,
								align : 'center'
							},
							{
								title : "Status",
								field : 'state',
								width : 80,
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
										return "<font color='red'>Rejected</font>";
									} else if (flag == '3') {
										return "<font color='gray'>Freezed</font>";
									}
								}
							},
							{
								title : "EC Group",
								field : 'end_customer_groupId',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.end_customer_groupId;
									if (row.ecGroupState == 0) {
										flag = flag + "(Pending)";
									}
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								},
								hidden : true
							},
							{
								title : "Customer Group",
								field : 'end_customer_groupName',
								width : 110,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.end_customer_groupName;
									if (row.ecGroupState == 0) {
										flag = flag + "(Pending)";
									}
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "ecGroupState",
								field : 'ecGroupState',
								width : 80,
								align : 'center',
								hidden : true,
							},
							{
								title : "Customer id",
								field : 'end_customer_id',
								width : 150,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.end_customer_id;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								},
							},
							{
								title : "Customer name",
								field : 'end_customer_name',
								width : 200,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.end_customer_name;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							}, ] ],
					columns : [ [
							{
								title : "Customer Type",
								field : 'customer_type',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.customer_type;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Customer Hierarchy",
								field : 'new_hierarchy',
								width : 120,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.new_hierarchy;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Tier",
								field : 'tier',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.tier;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Customer Property",
								field : 'customer_property',
								width : 80,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.customer_property;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								},
								hidden : true
							},
							{
								title : "Currency",
								field : 'currency_code',
								width : 80,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.currency_code;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								},
								hidden : true
							},
							{
								title : "Country",
								field : 'country',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.country;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								},
								hidden : true
							},
							{
								title : "Country",
								field : 'country_name',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.country_name;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "State",
								field : 'province',
								width : 150,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.province;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "City",
								field : 'city',
								width : 200,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.city;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Address",
								field : 'address',
								width : 200,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.address;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Zip",
								field : 'zip',
								width : 80,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.zip;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Contact",
								field : 'contact_name',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.contact_name;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Tel Number",
								field : 'tel',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.tel;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Creator",
								field : 'create_userId',
								width : 100,
								align : 'center',
							},
							{
								title : "Create Time",
								field : 'create_time',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.create_time;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Audit opinion",
								field : 'audit_opinion',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.audit_opinion;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "Disti Customer",
								field : 'disti_groupId',
								width : 250,
								align : 'left',
								formatter : function(value, row, rec) {
									var flag = row.disti_groupId;
									var state = row.state;
									if (state == '3') {
										return "<font color='gray'>" + flag
												+ "</font>";

									} else {
										return flag;
									}
								}
							},
							{
								title : "IsCheck",
								field : 'isCheck',
								width : 100,
								align : 'center',
								hidden : true
							},
							{
								title : "Sync Status",
								field : 'sync_state',
								width : 100,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.sync_state;
									if (flag == '0') {
										return "<font color='red'>未同步</font>";
									} else if (flag == '1') {
										return "<font color='green'>已同步</font>";
									}
								},
								hidden : true
							} ] ],
					toolbar : [ "-", {
						text : 'Reg EC',
						iconCls : 'icon-add',
						handler : function() {
							add();
						}
					}, "-", {
						text : 'Edit',
						iconCls : 'icon-edit',
						handler : function() {
							edit();
						}
					}, "-", {
						text : 'Freeze',
						iconCls : 'icon-remove',
						handler : function() {
							freezeCustomer();
						}
					}, "-", {
						text : 'Unfreeze',
						iconCls : 'icon-remove',
						handler : function() {
							unFreezeCustomer();
						}
					}, "-", {
						text : 'View',
						iconCls : 'icon-view ',
						handler : function() {
							view();
						}
					}, "-", {
						text : 'Alias',
						iconCls : 'icon-org',
						handler : function() {
							alias();
						}
					} ],
					onDblClickRow : function(rowIndex, rowData) {
						initMaintAccount(false, '850', '230',
								'Detail Information',
								'/endCustomerAction!toViewEndCustomer.jspa?id='
										+ rowData.id, 100, 100);
					},
					onSelect : function(rowIndex, rowData) {
						var state = rowData.state;
						var isCheck = rowData.isCheck;
						if (state == "0") {// 待处理
							$('div.datagrid-toolbar a').eq(1).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(2).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(3).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(5).linkbutton(
									'disable');
						} else if (state == "1") {// 通过
							$('div.datagrid-toolbar a').eq(1).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(2).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(3).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(5).linkbutton(
									'enable');
						} else if (state == "2") {// 拒绝
							$('div.datagrid-toolbar a').eq(1).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(2).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(3).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(5).linkbutton(
									'disable');
						} else if (state == "3") {// 冻结
							$('div.datagrid-toolbar a').eq(1).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(2).linkbutton(
									'disable');
							$('div.datagrid-toolbar a').eq(3).linkbutton(
									'enable');
							$('div.datagrid-toolbar a').eq(5).linkbutton(
									'disable');
						}
					},
					onLoadSuccess : function(data) {
						var loginRole = $('#loginRole').val().split("*");
						for (var i = 0; i < loginRole.length; i++) {
							var role = loginRole[i];
							if (role == "JXS") {
								$('div.datagrid-toolbar a').eq(2).hide();
								$('div.datagrid-toolbar a').eq(3).hide();
								$('div.datagrid-toolbar a').eq(5).hide();
								break;
							}
						}
					}
				});

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.country = encodeURIComponent($("#country").val()),
			queryParams.province = encodeURIComponent($("#province").val()),
			queryParams.customer_type = encodeURIComponent($("#customer_type")
					.val()),
			queryParams.end_customer_groupName = encodeURIComponent($(
					"#end_customer_groupName").val()),
			queryParams.disti_groupId = encodeURIComponent($("#disti_groupId")
					.val()),
			queryParams.end_customer_name = encodeURIComponent($(
					"#end_customer_name").val());
	queryParams.states = $("#states").val();
	if(($("#noTranBegin").datebox('getValue')=="" && $("#noTranEnd").datebox('getValue')!="") || ($("#noTranBegin").datebox('getValue')!="" && $("#noTranEnd").datebox('getValue')=="")){
		alert("please select both begin and end date !")
		return false;
	};
	queryParams.noTranBegin = $("#noTranBegin").datebox('getValue');
	queryParams.noTranEnd = $("#noTranEnd").datebox('getValue');
	
	$("#datagrid").datagrid('load');
}
function alias() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	}

	initMaintAccount(
			false,
			'900',
			'500',
			'EC Alias',
			'/endCustomerAction!tosearchECAlias.jspa?ec_id='
					+ rows[0].end_customer_id
					+ '&ec_name='
					+ encodeURIComponent(rows[0].end_customer_name)
					+ '&ec_group='
					+ encodeURIComponent(rows[0].end_customer_groupId == undefined ? ""
							: rows[0].end_customer_groupId)
					+ '&ec_groupName='
					+ encodeURIComponent(rows[0].end_customer_groupName == undefined ? ""
							: rows[0].end_customer_groupName) + '&ec_city='
					+ encodeURIComponent(rows[0].city), 50, 30);
}

function add() {
	initMaintAccount(false, '850', '400', 'Customer Registration',
			'/endCustomerAction!toCreateEndCustomer.jspa', 100, 30);
}
function edit() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '850', '350', 'Modify End Customer',
				'/endCustomerAction!toUpdateEndCustomer.jspa?id=' + rows[0].id,
				100, 30);
	}
}
function view() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '800', '230', 'Detail Information',
				'/endCustomerAction!toViewEndCustomer.jspa?id=' + rows[0].id,
				100, 30);
	}
}

function check() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '800', '450', '检查同一类型或同一集团内该终端客户是否重复注册',
				'/endCustomerAction!toCheckEndCustomer.jspa?id=' + rows[0].id
						+ '&end_customer_groupId='
						+ rows[0].end_customer_groupId + '&end_customer_name='
						+ encodeURIComponent(rows[0].end_customer_name)
						+ '&customer_type=' + rows[0].customer_type, 100, 30);
	}
}

/**
 * 同步
 */
function sync() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl
				+ '/endCustomerToSapAction!getEndCustomerToSAP.jspa?id='
				+ rows[0].id;
		form.target = "hideFrame";
		form.submit();

	}
}

/**
 * 冻结
 */
function freezeCustomer() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('info', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about  freeze?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/endCustomerAction!deleteEndCustomer.jspa?id='
					+ rows[0].id + '&updateState=3';
			form.target = "hideFrame";
			form.submit();
		}
	});
}
/**
 * 解冻
 */
function unFreezeCustomer() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('info', 'Please select the data item!');
		return;
	}
	$.messager.confirm('Confirm', 'Confirmed about  freeze?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/endCustomerAction!deleteEndCustomer.jspa?id='
					+ rows[0].id + '&updateState=0';
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function approved() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '400', '200', 'Audit Opinion',
				'/endCustomerAction!toAuditOpinion.jspa?id=' + rows[0].id
						+ '&updateState=1', 100, 100);
	}
}
function reject() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '400', '200', 'Audit Opinion',
				'/endCustomerAction!toAuditOpinion.jspa?id=' + rows[0].id
						+ '&updateState=2', 100, 100);
	}
}

function searcherCustomer(name1) {
	var queryParams = $('#disti_customer_id').combogrid("grid").datagrid(
			'options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#disti_customer_id').combogrid("grid").datagrid('reload');
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