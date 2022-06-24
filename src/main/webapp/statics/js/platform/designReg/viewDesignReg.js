$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	if ($('#equip_type').val().indexOf("-") > -1) {
		$('#equip_type').val($('#equip_type').val().replace("-", ""));
	}
});
function cancel() {
	window.parent.closeMaintWindow();
}
function downLoadFile() {
	if ($('#file_path').val() == "") {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?file_path="
			+ $('#file_path').val();
	form.target = "hideFrame";
	form.submit();
}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl
								+ '/designRegAction!getDesignRegDetailList.jspa?main_id='
								+ $('#id').val(),
						loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						checkbox : true,
						required : true,
						rownumbers : true,
						height : 280,
						fitColumns : false,
						striped : true,
						onLoadSuccess : function() {
							var paycode = $('#customer_id').val();
							if (paycode.substr(0, 1) == '7') {
								$('div.datagrid-toolbar a').eq(0).show();
							} else {
								$('div.datagrid-toolbar a').eq(0).hide();
							}
							var forWho = $('#forWho').val();
							if (forWho == "Disti") {
								$('div.datagrid-toolbar a').eq(1).hide();
							} else {
								$('div.datagrid-toolbar a').eq(1).show();
							}
						},
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									field : 'id',
									title : 'PK',
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
										var flag = row.state;
										if (flag == 0) {
											return "Pending";
										} else if (flag == 1) {
											return "<font color='green'>Approved</font>";
										} else if (flag == 2) {
											return "<font color='red'>Reject</font>";
										} else if (flag == 3) {
											return "<font color='red'>Expired</font>";
										}
									},
								},
								{
									field : 'material_id',
									title : '12NC',
									width : 100,
									align : 'center',
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
									field : 'material_name',
									title : 'BookPart<br>NoAOC{3F',
									width : 150,
									align : 'left',
								},
								{
									field : 'drNum',
									title : 'DR number',
									width : 60,
									align : 'center',
									hidden : true
								},

								{
									field : 'price',
									title : 'Price',
									width : 100,
									align : 'center',
								},
								{
									field : 'equip_usage',
									title : 'Pcs/Set',
									width : 100,
									align : 'center',
								},
								{
									field : 'usage_amount',
									title : 'Usage Amount',
									width : 50,
									align : 'center',
									hidden : true
								},
								{
									field : 'total_qty',
									title : 'Total Qty',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.equip_usage;
										var runrate = row.usage_amount;
										// var runrate = $('#usage_amount')
										// .numberbox('getValue') * 1;
										return (flag * runrate);
									}
								},
								{
									field : 'value',
									title : 'Value',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.value;
										if (flag == '' || flag == undefined) {
											return "";
										} else {
											return flag * 1;// Math.floor(flag);
										}
									}
								},
								{
									field : 'start_dateStr',
									title : 'Registration Date',
									width : 100,
									align : 'center',
								},
								{
									field : 'end_dateStr',
									title : 'Expiry Date',
									width : 100,
									align : 'center',
								},
								{
									field : 'shipPrice',
									title : 'POS amount',
									width : 100,
									align : 'center',
								},
								{
									field : 'dr_typeStr',
									title : 'DR Type',
									width : 150,
									align : 'center',
								},
								{
									field : 'drtype_def',
									title : 'DR Type Definition',
									width : 250,
									align : 'center',
								},	
								{
									field : 'project_state',
									title : 'Project Status',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.project_state;
										if (flag == 0) {
											return "Opportunity";
										} else if (flag == 1) {
											return "Design_in";
										} else if (flag == 2) {
											return "Design_win";
                                        } else if (flag == 9) {
                                            return "DW_Reject";
                                        }
									}
								},
								{
									field : 'cus_remark',
									title : 'Disti Remark',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.cus_remark;
										if (flag == 'undefined'
												|| flag == undefined) {
											return "";
										} else {
											return flag;
										}
									}
								}, 
                                {
                                    field : 'remark',
                                    title : 'Internal Comment',
                                    width : 150,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    },
                                    hidden : ($('#forWho').val()=='Disti')
                                }
								,{
                                    field : 'weencomments',
                                    title : 'Ween Comments',
                                    width : 150,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.weencomments;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                }
								,
                                {
									field : 'cost',
									title : 'Cost',
									width : 100,
									align : 'center',
									hidden : true
								}, {
									field : 'moq',
									title : 'Moq',
									width : 100,
									align : 'center',
									hidden : true
								}, {
									field : 'pbMpp',
									title : 'pbMpp',
									width : 100,
									align : 'center',
									hidden : true
								} ] ],
						toolbar : [ "-", {
							text : 'Quote',
							iconCls : 'icon-add',
							handler : function() {
								quote();
							}
						}, "-", {
							text : 'DR Log',
							iconCls : 'icon-list',
							handler : function() {
								viewDrLog();
							}
						} ],
						onSelect : function(rowIndex, rowDate) {
						},
					});

}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if (failResult == undefined || failResult == 'undefined') {
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;

	$("#datagrid").datagrid('load');
}

function closeMain() {
	$("#hiddenWin").window('close');
}
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}
var quoteDetail = [];

function quote() {
	$.messager
			.confirm(
					'Confirm',
					'Only DR approved items can be used in quotation!',
					function(r) {
						if (r) {
							quoteDetail = [];
							var rows = $('#datagrid').datagrid('getSelections');
							if (rows.length == 0) {
								$.messager.alert('Tips',
										'Please select the data item!',
										'warning');
								return;
							}

							var rejectflag = 0;
							for (var i = 0; i < rows.length; i++) {
								if (rows[i].state == 3) {
									$.messager
											.alert(
													'Tips',
													'DR already expired and not editable!',
													'warning');
									return;
								}
								if (rows[i].state != 1) {
									rejectflag++;
									continue;
								}
								var row = "{" + "id:" + "'" + rows[i].id + "',"
										+ "material_name:" + "'"
										+ rows[i].material_name + "',"
										+ "material_id:" + "'"
										+ rows[i].material_id + "'," + "drNum:"
										+ "'" + rows[i].drNum + "',"
										+ "main_id:" + "'" + rows[i].main_id
										+ "'," + "price:" + "'" + rows[i].price
										+ "',equip_usage:'"
										+ rows[i].equip_usage
										+ "',project_state:'"
										+ rows[i].project_state
										+ "',equip_type:'" + rows[i].equip_type
										+ "',start_date:'"
										+ rows[i].start_dateStr
										+ "',end_date:'" + rows[i].end_dateStr
										+ "',cost:'" + rows[i].cost + "',moq:'"
										+ rows[i].moq + "',pbMpp:'"
										+ rows[i].pbMpp + "',project_name:'"
										+ rows[i].project_name
										+ "',cus_remark:'" + rows[i].cus_remark
										+ "'}";
								quoteDetail.push(row);
							}
							if (quoteDetail.length == 0) {
								$.messager
										.alert(
												'Tips',
												'Only DR approved items can be used in quotation!',
												'warning');
								return;
							}
							$('#quoteDetail').val(quoteDetail);
							initMaintAccount(
									true,
									'1000',
									'550',
									'Create Quote',
									'/designRegAction!toQuote.jspa'
											+ '?disti_branch='
											+ encodeURIComponent($.trim($(
													'#disti_branch').html()))
											+ '&cusGroup_id='
											+ encodeURIComponent($.trim($(
													'#cus_groupId').html()))
											+ '&ecGroup_id='
											+ encodeURIComponent($(
													'#endCus_groupId').val())
											+ '&ecGroup_name='
											+ encodeURIComponent($(
													'#endCus_groupName').val())

											+ '&customer_id='
											+ encodeURIComponent($(
													'#customer_id').val())
											+ '&endCustomer_id='
											+ encodeURIComponent($('#endCus_id')
													.val())
											+ '&endCustomer_name='
											+ encodeURIComponent($(
													'#endCus_name').val())
											+ '&start_date='
											+ encodeURIComponent($(
													'#start_date').val())
											+ '&latest_expire='
											+ encodeURIComponent($('#end_date')
													.val())
											+ '&project_name='
											+ encodeURIComponent($(
													'#project_name').val())
											+ '&quoteDetail='
											+ encodeURIComponent($(
													'#quoteDetail').val()), 0,
									0);
						}
					});
}

// 44=(40?Z6TOs
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
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (event.keyCode == 8) {
		// Hg9{JGTZtextareaDZ2;V4PPHN:N2YWw
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		// Hg9{JGreadOnly;rU_disable2;V4PPHN:N2YWw
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
			event.returnValue = false;
	}
	return true;
};

function viewDrLog() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item !', 'warning');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('Tips', 'Multiselect are not supported !', 'warning');
		return;
	} else {
		initMaintAccount(false, '1150', '350', 'DR Log',
				'/designRegAction!toSearchDrLog.jspa?id=' + rows[0].id, 20, 20);
	}
}