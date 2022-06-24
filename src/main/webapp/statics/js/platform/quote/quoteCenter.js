$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

$('#clearV').click(function() {
	$('#endCustomer_id').combogrid('clear');
	$('#purchaseCustomer_id').combogrid('clear');
	$('#start_dateStr').datebox('setValue', "");
	$('#end_dateStr').datebox('setValue', "");
	setTimeout("search()", 100);
});

function loadGrid() {
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '',
				fit : true,
				striped : true,
				url : appUrl + '/quote/quoteAction!getAuditQuoteList.jspa',
				queryParams : {
					states : $('#state').val(),
					webFrom : '0' 
				},
				loadMsg : 'Loading...',
				singleSelect : true,
				nowrap : true,
				pagination : true,
				rownumbers : true,
				fitColumns : false,
				frozenColumns : [ [ {
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
					title : "Agreement",
					field : 'isAgree',
					width : 70,
					align : 'center',
					formatter : function(value, row, rec) {
						/**
						 * 状态 0处理中，1确认
						 */
						var flag = row.isAgree;
						if (flag == '0') {
							return "";
						} else if (flag == '1') {
							return "<font color='green'>Agree</font>";
						} else if (flag == '2') {
							return "<font color='gray'>Expired</font>";
						}
					},
				// hidden:true,
				}, {
					title : "Quote status",
					field : 'state',
					width : 90,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.state;
						if (flag == '9') {
							return "<font color='red'>Deleted</font>";
						} else if (flag == '0') {
							return "<font color='black'>Pending</font>";
						} else if (flag == '1') {
							return "<font color='black'>Pending</font>";
						} else if (flag == '2') {
							return "<font color='black'>Pending</font>";
						} else if (flag == '3') {
							return "<font color='green'>Approved</font>";
						} else if (flag == '4') {
							return "<font color='green'>Approved</font>";
						} else if (flag == '5') {
							return "<font color='green'>Approved</font>";
						} else if (flag == '6') {
							return "<font color='red'>Reject</font>";
						} else if (flag == '7') {
							return "<font color='red'>Reject</font>";
						} else if (flag == '8') {
							return "<font color='red'>Reject</font>";
						} else {
							return flag;
						}
					}
				}, ] ],

				columns : [ [
						{
							title : "OrderID",
							field : 'main_id',
							width : 80,
							hidden : true,
							align : 'center'
						},
						{
							field : 'Represent',
							title : 'Represent',
							width : 100,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.isRepresent;
								if (flag == 'undefined' || flag == undefined) {
									return "";
								} else {
									if (flag == 'Represent'
											|| flag == 'represent')
										return "Y";
									else
										return flag;
								}
							}
						},
						{
							title : "Quote Num",
							field : 'quote_id',
							width : 80,
							align : 'center'
						},
						{
							field : 'Forwarder',
							title : 'forwarder',
							width : 100,
							align : 'center',
							hidden : true
						},
						{
							field : 'debit_num',
							title : 'Debit Num',
							width : 120,
							align : 'center',
						},
						{
							field : 'material_id',
							title : '12NC<br>物料编码',
							width : 110,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.material_id;
								if (flag == '' || flag == undefined) {
									return "";
								} else {
									var str = $.trim(flag);
									str = str.substring(str.length - 12,
											str.length);
									return str;
								}
							}
						},
						{
							field : 'material_name',
							title : 'BookPart<br>物料名称',
							width : 130,
							align : 'left',
						},
						{
							field : 'isDRItem',
							title : 'DR Indicator',
							width : 80,
							align : 'center',
						},
						{
							field : 'drNum',
							title : 'DR Number<br>项目注册的编码',
							width : 80,
							align : 'center',

						},
						{
							field : 'qty',
							title : 'QTY<br>订购数量',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.qty;
								if (flag == '' || flag == undefined) {
									return "";
								} else {
									return formatNumber(flag * 1, 0, 1);
								}
							}
						},
						{
							field : 'res_qty',
							title : 'Remain QTY',
							width : 85,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.res_qty;
								if (flag == '' || flag == undefined) {
									return "";
								} else {
									return formatNumber(flag * 1, 0, 1);
								}
							}
						},
						{
							field : 'target_cost',
							title : 'Target Cost <br>目标进货价格',
							width : 85,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.target_cost;
								if (flag == 0) {
									return flag;
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);
								}
							}
						},
						{
							field : 'target_resale',
							title : 'Target Resale<br>目标销售价格',
							width : 95,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.target_resale;
								if (flag == 0) {
									return flag;
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);
								}
							}
						},
						{
							field : 'target_margin',
							title : 'Target Margin',
							width : 95,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.target_margin;
								if (flag == '' || flag == undefined) {
									return "";
								} else {
									flag = JSON.parse(flag * 1);
									flag = flag.toFixed(2) + "%";
									return flag;
								}
							}
						},
						{
							field : 'stop_price',
							title : 'CMM',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.stop_price;
								if (flag == 0 || flag == ''
										|| flag == undefined) {
									return "<font color='red'>No Price</font>";
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);

								}
							},
						},
						{
							field : 'qm_price',
							title : 'Quote Min',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.qm_price;
								if (flag == 0 || flag == ''
										|| flag == undefined) {
									return "<font color='red'>No Price</font>";
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);

								}
							},
							hidden : true
						},
						{
							title : "Price Region",
							field : 'price_region',
							width : 90,
							align : 'center',
						},
						{
							title : "Disti",
							field : 'cusGroup_id',
							width : 150,
							align : 'left'
						},
						{
							title : "Disti Branch",
							field : 'disti_branch',
							width : 250,
							align : 'left'
						},
						{
							title : "PC Group",
							field : 'pcGroup_id',
							width : 110,
							align : 'center',
							hidden : true
						},
						{
							title : "PC Region",
							field : 'pc_region',
							width : 60,
							align : 'center'
						},
						{
							title : "PC Group",
							field : 'pcGroup_name',
							width : 110,
							align : 'center',
							hidden : true
						},
						{
							title : "Purchasing Customer",
							field : 'purchaseCustomer_name',
							width : 150,
							align : 'center'
						},
						{
							title : "EC Group",
							field : 'ecGroup_name',
							width : 100,
							align : 'center',
							hidden : true
						},
						{
							title : "End Customer",
							field : 'endCustomer_name',
							width : 150,
							align : 'left',
						},
						{
							title : "Project",
							field : 'project_name',
							width : 90,
							align : 'center'
						},
						{
							title : "Currency",
							field : 'currency_code',
							width : 90,
							align : 'center'
						},
						{
							field : 'pbMpp',
							title : 'PB/MPP',
							width : 80,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.pbMpp;
								if (flag == 0 || flag == ''
										|| flag == undefined) {
									return "<font color='red'>No Price</font>";
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);

								}
							},
						},
						{
							field : 'suggest_cost',
							title : 'Final Quoted Cost<br>审批进货价格',
							width : 120,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : {
									precision : 4
								}
							},
							formatter : function(value, row, rec) {
								var flag = row.suggest_cost;
								if (flag == 0 || flag == ''
										|| flag == undefined) {
									return flag;
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);
								}
							}
						},
						{
							field : 'suggest_resale',
							title : 'Final Quoted Resale <br>审批销售价',
							width : 140,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : {
									precision : 4
								}
							},
							formatter : function(value, row, rec) {
								var flag = row.suggest_resale;
								if (flag == 0 || flag == ''
										|| flag == undefined) {
									return flag;
								} else {
									flag = JSON.parse(flag * 1);
									return flag.toFixed(4);
								}
							}
						},
						{
							field : 'cus_profits_percent',
							title : 'Final Quoted Disti Margin<br>客户利润',
							width : 170,
							align : 'center',
							editor : 'text',
							formatter : function(value, row, rec) {
								var flag = row.cus_profits_percent;
								if (flag == '' || flag == undefined
										|| flag == 'undefined') {
									return '';
								} else {
									return flag + "%";
								}
							},
						},
						{
							field : 'profits_percent',
							title : 'Mfr Margin <br>瑞能利润',
							width : 90,
							align : 'center',
							editor : 'text',
							formatter : function(value, row, rec) {
								var flag = row.profits_percent;
								if (flag == '' || flag == undefined
										|| flag == 'undefined') {
									return '';
								} else {
									return flag + "%";
								}
							},

						},
						{
							field : 'amount',
							title : 'Final Quoted Amount<br>行项目总价',
							width : 150,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : {
									precision : 4
								}
							},
							formatter : function(value, row, rec) {
								return formatNumber(value * 1, 0, 1);
							}
						}, {
							field : 'cost',
							title : 'Cost <br>物料成本',
							width : 80,
							align : 'center',

						}, {
							field : 'reason',
							title : 'Justification<br>申请原因',
							width : 100,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.reason;
								if (flag == 'undefined' || flag == undefined) {
									return "";
								} else {
									return flag;
								}
							}
						}, {
							field : 'competitor',
							title : 'Competitor<br>竞争对手',
							width : 150,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.competitor;
								if (flag == 'undefined' || flag == undefined) {
									return "";
								} else {
									return flag;
								}
							}
						}, {
							field : 'product_dateStr',
							title : 'Start of Production<br>生产启动日期',
							width : 120,
							align : 'center',
						}, {
							field : 'start_dateStr',
							title : 'Effective Date<br>生效日期',
							width : 120,
							align : 'center',
						}, {
							field : 'end_dateStr',
							title : 'Expire Date<br>截止日期',
							width : 120,
							align : 'center',
						}, {
							field : 'cus_remark',
							title : 'Comments<br>客户意见',
							width : 200,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.cus_remark;
								if (flag == 'undefined' || flag == undefined) {
									return "";
								} else if (flag.length >= 30) {
									flag = flag.substring(0, 24);
									flag += "...";
									return flag;
								} else {
									return flag;
								}
							}
						}, {
							field : 'remark',
							title : 'Internal Comments<br>审批意见',
							width : 200,
							align : 'center',
							editor : 'text',
							formatter : function(value, row, rec) {
								var flag = row.remark;
								if (flag == 'undefined' || flag == undefined) {
									return "";
								} else if (flag.length >= 30) {
									flag = flag.substring(0, 24);
									flag += "...";
									return flag;
								} else {
									return flag;
								}
							}
						}, {
							field : 'data_from',
							title : 'Data Source',
							width : 100,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.data_from;
								if (flag == '2') {
									return "<font color='blue'>EDI</font>";
								} else {
									return "<font color='black'>NonEDI</font>";
								}
							},
						}, {
							field : 'debit_start',
							title : 'Debit Start',
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
							field : 'debit_end',
							title : 'Debit End',
							width : 100,
							align : 'center',
							formatter : function(date) {
								if (date == undefined) {
									return "";
								} else {
									return utcToDate(date);
								}
							}
						} ] ],

				toolbar : [
						"-",
						{
							text : 'Output',
							iconCls : 'icon-outport',
							handler : function() {
								downLoad();
								$('div.datagrid-toolbar a').eq(0).linkbutton(
										'disable');
								setTimeout(function() {
									$('div.datagrid-toolbar a').eq(0)
											.linkbutton('enable');
								}, 200000);
							}
						},

				],

				onDblClickRow : function(rowIndex, rowData) {
					initMaintAccount(true, '1000', '550', '',
							'/quoteAction!toSalerViewQuote.jspa?id='
									+ rowData.main_id + '&approver=9');
				},
				onClickCell : function(rowIndex, field, value) {
					var rows = $('#datagrid').datagrid('getRows');
					if (field == "cus_remark"
							&& rows[rowIndex].cus_remark != undefined
							&& rows[rowIndex].cus_remark != "") {
						var str = rows[rowIndex].cus_remark;
						$.messager.alert('Remark', str, '');
					}
					if (field == "remark" && rows[rowIndex].remark != undefined
							&& rows[rowIndex].remark != "") {
						var str = rows[rowIndex].remark;
						$.messager.alert('Remark', str, '');
					}
				},
				rowStyler : function(index, row) {
					var rows = $('#datagrid').datagrid('getRows');
					var quote_id = rows[0].quote_id;
					var flag = 0;
					for (var i = 0; i < rows.length; i++) {
						if (rows[i].quote_id != quote_id) {
							if (flag == 0) {
								flag = 1;
							} else if (flag == 1) {
								flag = 0;
							}
						}
						rows[i].flag = flag;
						quote_id = rows[i].quote_id;
					}
					if (row.flag == 1) {
						return 'background:#ffffcc;';
					} else {
						return 'background:#ffffff;';
					}
				}
			});
}

function downLoad() {
	var url = '';
	url = '/quoteAction!downloadQuoteList.jspa';
	$.messager.confirm('Confirm', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var exportExcelUrl = url;
			var form = window.document.forms[0];
			form.action = appUrl + exportExcelUrl;
			form.target = "hideFrame";
			form.submit();
		}
	});
}

// 计算行项目总价=订购数量*审批成本价
// 客户利润=(审批resale*1-审批cost*1)/审批resale*1;;
// 瑞能利润Mfr Margin= (Suggest Cost-物料成本)/ Suggest Cost
function setEditing(rowIndex) {
	var rows = $('#datagrid').datagrid('getRows');
	var stop_price = rows[rowIndex].stop_price;
	var materialCost = rows[rowIndex].cost;
	var qty = rows[rowIndex].qty;
	var editors = $('#datagrid').datagrid('getEditors', rowIndex);
	var weenCostEditor = editors[0];
	var weenResaleEditor = editors[1];
	var cusProfitEditor = editors[2];
	var weenProfitEditor = editors[3];
	var amountEditor = editors[4];
	weenCostEditor.target.bind({
		'blur' : function() {
			calculate();
		},
		'mouseleave' : function() {
			calculate();
		}
	});
	weenResaleEditor.target.bind({
		'blur' : function() {
			calculate();
		},
		'mouseleave' : function() {
			calculate();
		}
	});
	amountEditor.target.bind({
		'blur' : function() {
			calculate();
		},
		'mouseleave' : function() {
			calculate();
		}
	});
	weenProfitEditor.target.bind({
		'blur' : function() {
			calculate();
		},
		'mouseleave' : function() {
			calculate();
		}
	});
	cusProfitEditor.target.bind({
		'blur' : function() {
			calculate();
		},
		'mouseleave' : function() {
			calculate();
		}
	});
	function calculate() {
		var weenCost = weenCostEditor.target.val();
		var weenResale = weenResaleEditor.target.val();
		var amount = weenCost * qty;
		var weenProfit = ((weenCost - materialCost) * 1.0 / weenCost * 1.0) * 100;
		var cusProfit = ((weenResale * 1.00 - weenCost * 1.00) / weenResale * 1.00) * 100;
		weenProfit = weenProfit.toFixed(2);
		cusProfit = cusProfit.toFixed(2);
		if (weenResale * 1 < weenCost * 1) {
			$(weenResaleEditor.target).val("");
		}
		$(weenProfitEditor.target).val(weenProfit + '%');
		$(cusProfitEditor.target).val(cusProfit + '%');
		$(amountEditor.target).val(amount);
		if (weenProfit == 'NaN' || weenProfit == '-Infinity') {
			$(weenProfitEditor.target).val("");
		}
		if (cusProfit == 'NaN' || cusProfit == '-Infinity') {
			$(cusProfitEditor.target).val("");
		}
	}
}
//

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.quote_id = $("#quote_id").val();
	queryParams.debit_num = $("#debit_num").val();
	queryParams.cusGroup_id = $("#cusGroup_id").val();
	queryParams.disti_branch = $("#disti_branch").val();
	queryParams.purchaseCustomer_id = $("#purchaseCustomer_id").combobox(
			'getValue');
	queryParams.endCustomer_id = $("#endCustomer_id").combobox('getValue');
	queryParams.project_name = $("#project_name").val();
	queryParams.material_id = $("#material_id").val();
	queryParams.material_name = $("#material_name").val();
	queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
	queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
	queryParams.states = $('#state').val();
	queryParams.webFrom = '0';
	$("#datagrid").datagrid('load');
}

var editIndex = undefined;
function beginEditing(rowIndex, field, value) {
	if (rowIndex != editIndex) {
		if (endEditing()) {
			$('#datagrid').datagrid('beginEdit', rowIndex);
			editIndex = rowIndex;
		} else {
			$('#datagrid').datagrid('selectRow', editIndex);
		}
	}
}

function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	if ($('#datagrid').datagrid('validateRow', editIndex)) {
		$('#datagrid').datagrid('endEdit', editIndex);
		$('#datagrid').datagrid('selectRow', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
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

// PC客户
$('#purchaseCustomer_id')
		.combogrid(
				{
					panelHeight : 280,
					panelWidth : 330,
					pagination : true,
					pageSize : 10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
					url : appUrl
							+ '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
					idField : 'end_customer_id',
					textField : 'end_customer_name',
					columns : [ [ {
						field : 'end_customer_groupId',
						title : 'PC Group',
						width : 100
					}, {
						field : 'end_customer_name',
						title : 'PC Name',
						width : 200
					}, {
						field : 'end_customer_id',
						title : 'PC ID',
						width : 200,
						hidden : true
					} ] ],
					onSelect : function(index, record) {
					},
					toolbar : '#toolbarPurCustomer',
				});
function searcherPurCustomer(name1) {
	var queryParams = $('#purchaseCustomer_id').combogrid("grid").datagrid(
			'options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#purchaseCustomer_id').combogrid("grid").datagrid('reload');
}

// 终端客户
$('#endCustomer_id')
		.combogrid(
				{
					panelHeight : 280,
					panelWidth : 330,
					pagination : true,
					pageSize : 10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
					url : appUrl
							+ '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
					idField : 'end_customer_id',
					textField : 'end_customer_name',
					columns : [ [ {
						field : 'end_customer_groupId',
						title : 'EC Group',
						width : 100
					}, {
						field : 'end_customer_name',
						title : 'EC Name',
						width : 200
					} ] ],
					onSelect : function(index, record) {
					},
					toolbar : '#toolbarEndCustomer',
				});
function searcherEndCustomer(name1) {
	var queryParams = $('#endCustomer_id').combogrid("grid")
			.datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#endCustomer_id').combogrid("grid").datagrid('reload');
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