$(document).ready(function() {
	loadGrid();
	//事务状态（0为未处理，1为处理中，2为处理完成，3为已拒绝，4表示取消）
	var data=[{'text':'未处理','value':'0'},{'text':'处理中','value':'1'},{'text':'已完成','value':'2'},{'text':'已拒绝','value':'3'},{'text':'已取消','value':'4'}];
	$('#status').combobox(
			{
				data:data,
				valueField : 'value',
				textField : 'text',
				onLoadSuccess : function() {
				},
				editable : false
			});
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/eventAction!getEventJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								/*{
									field : 'ck',
									checkbox : true
								},*/
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'initator',
									title : '提出者id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'empName',
									title : '提出者',
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.10),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "未处理";
										}
										if (value == 1) {
											return "处理中";
										}
										if (value == 2) {
											return "已完成";
										}
										if (value == 3) {
											return "已拒绝";
										}
										if (value == 4) {
											return "已取消";
										}
									}
								},
								{
									field : 'curUserName',
									title : '当前处理人',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'creatdate',
									title : '提出时间',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.19),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '';
										/*if (row.backStatus == "1") {
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>查看审批意见 </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > 流程 </a>|'
													+ '<a href=javascript:searchProEventReader("'
													+ row.eventId
													+ '") > 授权查看</a>|'
													+ '<a href= javascript:backProEvent("'
													+ row.eventId
													+ '","'
													+ row.currentDetailid
													+ '","'
													+ row.curStaId
													+ '","'
													+ row.modelId
													+ '")>取回 </a>';
										} else {*/
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>查看审批意见 </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > 流程 </a>|'
													+ '<a href=javascript:searchProEventReader("'
													+ row.eventId
													+ '") > 授权查看</a>';
									/*	}*/
										return strReturn;
									}
								} ] ],
						onLoadSuccess : function(data) {
							// 禁用全选功能
							// $(".datagrid-header-check")[0].disabled=true;
							// 初始化置灰
							selectedFile($(this), data.rows);

							// 点击行不选中checkbox
							// function bindRowsEvent(){
							// var panel = $('#datagrid').datagrid('getPanel');
							// var rows = panel.find('tr[datagrid-row-index]');
							// rows.unbind('click').bind('click',function(e){
							// return false;
							// });
							// rows.find('div.datagrid-cell-check
							// input[type=checkbox]').unbind().bind('click',
							// function(e){
							// var index =
							// $(this).parent().parent().parent().attr('datagrid-row-index');
							// if ($(this).attr('checked')){
							// $('#datagrid').datagrid('selectRow', index);
							// } else {
							// $('#datagrid').datagrid('unselectRow', index);
							// }
							// e.stopPropagation();
							// });
							// }
							// setTimeout(function(){
							// bindRowsEvent();
							// }, 10);
							$(".datagrid-header-check input")
									.unbind('click')
									.bind(
											'click',
											function(e) {
												var checkArr = new Array();
												for ( var i = 0; i < data.rows.length; i++) {
													if (0 == data.rows[i].status
															|| 1 == data.rows[i].status) {
														checkArr.push(i);
													}
												}
												var checkedAll = true;
												for ( var i = 0; i < checkArr.length; i++) {
													var chk = $(".datagrid-row[datagrid-row-index="
															+ checkArr[i]
															+ "] input[type='checkbox']")[0].checked;
													if (!chk) {
														checkedAll = false;
														break;
													}
												}
												if (checkedAll) {
													for ( var i = 0; i < data.rows.length; i++) {
														$('#datagrid')
																.datagrid(
																		'unselectRow',
																		i);
													}
												} else {
													for ( var i = 0; i < data.rows.length; i++) {
														if (0 == data.rows[i].status
																|| 1 == data.rows[i].status) {
															$('#datagrid')
																	.datagrid(
																			'selectRow',
																			i);
														}
													}
													$(
															".datagrid-header-check input")
															.attr('checked',
																	true);
												}
											});
						},
						onCheck : function(rowIndex, rowData) {
							if (rowData['status'] == 2
									|| rowData['status'] == 3
									|| rowData['status'] == 4) {
								$('#datagrid')
										.datagrid('unselectRow', rowIndex);
								$('#datagrid').datagrid('unCheckRow', rowIndex);
							}
						}
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * 初始化置灰的选项
 */
function selectedFile(grid, rows) {
	for ( var j = 0; j < rows.length; j++) {
		if (2 == rows[j]['status'] || 3 == rows[j]['status']
				|| 4 == rows[j]['status']) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	$("#datagrid").datagrid('load');
}

// 创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {

	var url = appUrl + url;
	var $win = $("#" + id)
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

//创建窗口对象
function initMaintEvent(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
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
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}

function searchProEventReader(eventId) {
	initMaintEvent(true,'700','400','事务查阅人管理','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function cancelEvent(eventId) {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	$.messager.confirm('Confirm', '确认批量取消事务?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].eventId);
			}
			var form = window.document.forms[0];
			form.action = "eventAction!cancelEvent.jspa?eventIds=" + ids;
			form.target = "hideFrame";
			form.submit();
		}
		;
	});
}

function backProEvent(eventId, currentDetailid, curStaId, modelId) {
	$.messager.confirm('Confirm', '确认取回该事务事务?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = "eventAction!backProEvent.jspa?eventIds=" + eventId
					+ "&toDoDetail=" + currentDetailid + "&curStaId="
					+ curStaId + "&modelId=" + modelId;
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}