$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function back() {
    window.parent.search();
    window.parent.closeMaintWindow();
}
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl
								+ '/sampleOrderAction!getSampleOrderDetailList.jspa?main_id='
								+ $('#id').val(),
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						checkbox : true,
						required : true,
						rownumbers : true,
						height : 300,
						fitColumns : false,
						striped : true,
						columns : [ [
								{
									field : 'id',
									title : 'ID',
									width : 60,
									align : 'center',
									hidden : true
								},
								{
									field : 'state',
									title : 'Status',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										if ($('#state').val() == '2') {
											return "Not shipped";
										} else if ($('#state').val() == '1') {
											return "Processing";
										} else if (row.expressage_info != undefined
												&& row.expressage_info != 'undefined'
												&& row.expressage_info != '') {
											return "Shipped";
										} else {

											return "Processing";
										}
									}
								},
								{
									field : 'row_no',
									title : 'Item ID',
									width : 80,
									align : 'center',
								},
								{
									field : 'material_name',
									title : 'BookPart',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.material_name;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									}
								},
								{
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
											str = str
													.substring(str.length - 12,
															str.length);
											return str;
										}
									}
								},
								{
									field : 'material_typeId',
									title : 'Material Type',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.material_typeId;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									},
									hidden : true

								},

								{
									field : 'material_groupId',
									title : 'Product Group',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.material_groupId;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									},
									hidden : true
								},
								{
									field : 'sale_unit',
									title : 'Sale Unit',
									width : 70,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.sale_unit;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									}
								},
								{
									field : 'limited_QTY',
									title : 'Limited Qty',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.limited_QTY;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									}
								},
								{
									field : 'stock_status',
									title : 'Stock Status',
									width : 150,
									align : 'center',
									// editor:'text',
									hidden : true
								},
								{
									field : 'order_QTY',
									title : 'Order QTY',
									width : 80,
									align : 'center',
								},
								{
									field : 'expressage_info',
									title : 'Expressage Info',
									width : 120,
									align : 'center',
								},
								{
									field : 'ship_date',
									title : 'Ship Date',
									width : 120,
									align : 'center',
								},
								{
									field : 'invoice',
									title : 'Invoice',
									width : 120,
									align : 'center',
								},
								{
									field : 'lead_time',
									title : 'Lead Time(week)',
									width : 105,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.lead_time;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									},
									hidden : true

								},
								{
									field : 'delivery_dateStr',
									title : 'Request Date',
									width : 120,
									align : 'center',
									hidden : true
								},
								{
									field : 'confirm_dateStr',
									title : 'Confirm Delivery Date',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.confirm_dateStr;
										if (flag == undefined || flag == '') {
											return "N/A";
										} else {
											return flag;
										}
									},
									hidden : true
								},
								{
									field : 'remark',
									title : 'Cust Remark',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.remark;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									}
								},
								{
									field : 'ween_remark',
									title : 'Ween Remark',
									width : 150,
									align : 'center',
									editor : 'text',
									formatter : function(value, row, rec) {
										var flag = row.ween_remark;
										if (flag == '0' || flag == 'undefined') {
											return "";
										} else {
											return flag;
										}
									},

								} ] ],
								 toolbar : ["-", {
			                            text : 'Approve',
			                            iconCls : 'icon-ok',
			                            handler : function() {
			                            	Approve();
			                            }
			                        }, "-", {
			                            text : 'Reject',
			                            iconCls : 'icon-cancel',
			                            handler : function() {
			                            	Reject();
			                            }
			                        }],
			            onLoadSuccess : function() {

			            	if ($('#state').val() == '2' && $('#forWho').val() == 'Manager') {
                                $('div.datagrid-toolbar a').eq(0).linkbutton('enable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton('disable');
    						}
							else if ($('#state').val() == '1' && $('#forWho').val() == 'Manager') {
                                $('div.datagrid-toolbar a').eq(0).linkbutton('enable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton('enable');
							}
							else {
                                $('div.datagrid-toolbar a').eq(0).linkbutton('disable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton('disable');
							}

			            	var rows = $("#datagrid").datagrid("getRows");	
			            	if(rows == undefined || rows.length == 0){
			            		return;
			            	}
			            	for (var i = 0; i < rows.length; i++) {
			            		$('#datagrid').datagrid('beginEdit', i);
			            	}
			            },
						onClickCell : function(rowIndex, field, value) {
							beginEditing(rowIndex, field, value);
							$('#datagrid').datagrid('beginEdit', rowIndex);
						},
					});

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
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if (failResult == undefined || failResult == 'undefined') {
			return;
		}
		$.messager.alert('Error', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		$("#datagrid").datagrid('load');
	}
}

function closeMain() {
	$("#hiddenWin").window('close');
}

document.onkeydown = function(e) {

	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;

	if (event.keyCode == 8) {
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
			event.returnValue = false;
	}
	if (event.keyCode == 13) {
    	var rows = $("#datagrid").datagrid("getRows");	
    	if(rows == undefined || rows.length == 0){
    		return;
    	}
    	for (var i = 0; i < rows.length; i++) {
    		$('#datagrid').datagrid('endEdit', i);
    	}
		return false;
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

function Approve() {
	var alertMsg = "";
	
	var rows = $("#datagrid").datagrid("getRows");	
	if(rows == undefined || rows.length == 0){
		$.messager.alert('Error', "Please fill in the order line item!", 'error');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		$("#datagrid").datagrid("endEdit", i);
	}

	var orderDetailJson = [];
	for (var i = 0; i < rows.length; i++) {
		row_no = (i * 1 + 1) * 10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		var row = "{" + "id:" + "'" + rows[i].id + "',ween_remark:'"
				+ rows[i].ween_remark + "'}";
		orderDetailJson.push(row);
	}
	$.messager.confirm('Confirm', alertMsg + 'Sure to approve?', function(r) {
		if (r) {
			$('#orderDetailJson').val("[" + orderDetailJson + "]");
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/sampleOrderAction!approveSampleOrder.jspa?id="
					+ $('#id').val();
			form.target = "hideFrame";
			form.submit();
			$('#state').val(0);
		}
	});
}

function Reject() {
	var alertMsg = "";

	var rows = $("#datagrid").datagrid("getRows");	
	if(rows == undefined || rows.length == 0){
		$.messager.alert('Error', "Please fill in the order line item!", 'error');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		$("#datagrid").datagrid("endEdit", i);
	}

	var orderDetailJson = [];
	for (var i = 0; i < rows.length; i++) {
		row_no = (i * 1 + 1) * 10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].ween_remark == "" || rows[i].ween_remark == undefined) {
			$.messager.alert('Error', "Rows" + (i * 1 + 1)
					+ ": Please input reject Ween Remark!", 'error');
			return;
		}

		var row = "{" + "id:" + "'" + rows[i].id + "',ween_remark:'"
				+ rows[i].ween_remark + "'}";
		orderDetailJson.push(row);
	}

	$.messager.confirm('Confirm', alertMsg + 'Sure to reject?', function(r) {
		if (r) {
			$('#orderDetailJson').val("[" + orderDetailJson + "]");
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/sampleOrderAction!rejectSampleOrder.jspa?id="
					+ $('#id').val();;
			form.target = "hideFrame";
			form.submit();
			$('#state').val(2);
		}
	});
}