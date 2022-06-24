$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + 'K',
		valueField : 'channelId',
		textField : 'channelName'
	});
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						fit:true,
						striped : true,
						columns : [ [{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.15),
									align : 'center',
									hidden:true,
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = "<a href='#' onclick=viewLicense('"+ kunnr+ "')>证照</a>| ";
		
										var scan = "  <a href='#' onclick=viewInfo('"
												+ kunnr
												+ "')>详情</a>|";
										var edit = status == 1 ? " <a href='#' onclick=editInfo('"
												+ kunnr
												+ "')>修改</a>|"
												: "&nbsp;&nbsp;&nbsp;&nbsp;";
										var close = status == 1 ? "  <a href='#' onclick=closeKunnr('"
												+ kunnr
												+ "') >关户</a>"
												: "&nbsp;&nbsp;&nbsp;&nbsp;";
										return license + scan + edit + close;
									}
								},
								{
									field : 'kunnr',
									title : '经销商代码',
									//width : setColumnWidth(0.08),
									width : 80,
									align : 'center',
									styler : function(value, record, index) {
										return "background:pink";
									}
								},
								{
									field : 'kunnrErp',
									title : 'ERP客户代码',
									//width : setColumnWidth(0.08),
									width : 80,
									hidden:true,
									align : 'center',
									styler : function(value, record, index) {
										return "background:pink";
									}
								},
								{
									field : 'name1',
									title : '经分销商名称',
									//width : setColumnWidth(0.24),
									width : 200,
									align : 'left',
									sortable:true
								},
								{
									field : 'orgId',
									title : '组织ID',
									width : setColumnWidth(0.05),
									hidden:true,
									sortable:true
								},
								{
									field : 'orgName',
									title : '所属组织',
									//width : setColumnWidth(0.08),
									width : 80,
									sortable:true
								},
								{
									field : 'name3',
									title : '法人',
									width : setColumnWidth(0.05),
									hidden:true,
									sortable:true
								},
								{
									field : 'telNumber',
									title : '公司电话',
									//width : setColumnWidth(0.08),
									width : 80,
									sortable:true
								},
								{
									field : 'bukrs',
									title : '公司代码',
									//width : setColumnWidth(0.06),
									sortable:true,
									hidden : true
								},
								{
									field : 'customerType',
									//title : '客户类型',
									title : '经销商类型',
									//width : setColumnWidth(0.1),
									width : 100,
									sortable:true
								},
								{
									field : 'street1',
									title : '公司地址',
									//width : setColumnWidth(0.26),
									width : 250,
									align : 'left',
									sortable:true
								},
								{
									field : 'businessManager',
									title : '业务经理',
									//width : setColumnWidth(0.06),
									align : 'center',
									hidden:true,
									sortable:true
								},
								{
									field : 'businessCompetent',
									title : '业务主管',
									//width : setColumnWidth(0.06),
									align : 'center',
									hidden:true,
									sortable:true
								},
								{
									field : 'kaManager',
									title : '业务主管',
									width : 100,
									align : 'center',
 									sortable:true
								},
								{
									field : 'status',
									title : '状态',
									//width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 1) {
											return '正常客戶';
										} else if (value == 2) {
											return '<font color="red">冻结客戶</font>';
										}else if (value == 3) {
											return '<font color="red">已关户</font>';
										}
									},
									sortable:true
								},
								{
									field : 'isautodemand',
									title : '是否自动分货',
									//width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '是';
										} else if (value == 'N') {
											return '否';
										}else {
											return '否';
										}
									},
									sortable:true
								},
								{
									field : 'maximum',
									title : '最大可通行车型',
									//width : setColumnWidth(0.07),
									align : 'center'
								},
								{
									field : 'billType',
									title : '是否能开增值税发票',
									//width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '可以';
										} else if (value == 'N') {
											return '不可以';
										}else {
											return '';
										}
									},
									sortable:true
								},
								{
									field : 'isMortgage',
									title : '是否抵押',
									//width : setColumnWidth(0.07),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'Y') {
											return '是';
										} else if (value == 'N') {
											return '否';
										}else {
											return '';
										}
									},
									sortable:true
								},{
									field : 'operation',
									title : '操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr; 
										return '<img style="cursor:pointer" onclick=autoDemand('
												+ kunnr
												+ ') title="是否分货" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												+'<img style="cursor:pointer" onclick=getByRoleStation("'
												+ kunnr
												+'") title="维护角色" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>'
												;
									}
								}
								] ],  
						pageSize : 20,		
						toolbar : [ "-", 
						{
							text : '同步',
							iconCls : 'icon-reload',
							handler : function() {
								getSapToKunnr();
							}
						}
//						,"-",{
//							text : '新增',
//							iconCls : 'icon-add',
//							handler : function() {
//								createKunnr();
//							}
//						}
						]
					});

//	 分页控件
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 20,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
	
	//$('div.datagrid-toolbar a').eq(1).hide();
   // $('div.datagrid-toolbar div').eq(1).hide();
    //$('div.datagrid-toolbar a').eq(2).hide();
    //$('div.datagrid-toolbar div').eq(2).hide();
   // $('div.datagrid-toolbar a').eq(3).hide();
   // $('div.datagrid-toolbar div').eq(3).hide();

}

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $("#kunnr").val();
	queryParams.name1 = encodeURIComponent($("#name1").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.businessManager = encodeURIComponent($("#businessManager")
			.val());
	queryParams.businessCompetent = encodeURIComponent($("#businessCompetent")
			.val());
	queryParams.status = $("#status").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	//var width=1000;
	return $(this).width() * percent;
	//return width * percent;
}
/**
 * 角色维护
 * 
 * */
function getByRoleStation(id) {
   	initMaintRole('维护角色','/roleAction!searchSelectedRole.jspa?stationId='
			+ (id)+'&kunnrSign=Y', 850, 450,100,50);
 
}
//创建窗口对象
function initMaintRole(title, url, WWidth, WHeight,left,top) {
	var url = wfeUrl+url;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						left : left,
						top : top
					});

	$win.window('open');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}

function viewLicense(kunnr) {
	openWindow(false,"经销商证照查看",
			"/kunnrAction!kunnrViewLicense.jspa?kunnrId=" + kunnr, 800, 480,100,100);
}

function viewInfo(kunnr) {
	openWindow(true,"经销商信息查看", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=view", 1120, 480,0,0);
}
function createKunnr(){
	openWindow(true,"经销商开户申请", "/kunnrAction!kunnrApplyPre.jspa", 1120, 480,0,0);
}
function editInfo(kunnr) {
	openWindow(true,"经销商信息修改", "/kunnrAction!kunnrViewInfo.jspa?kunnrId=" + kunnr
			+ "&opera=edit", 1120, 480,0,0);
}

function freezeKunnr(kunnr) {
	openWindow(true,"经销商关户申请", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=freeze", 600, 450,0,0);
}

function closeKunnr(kunnr) {
	openWindow(true,"经销商关户申请", "/kunnrAction!kunnrFreezeOrClosePre.jspa?kunnrId="
			+ kunnr + "&freezeOrClose=close", 600, 320,0,0);
}
function autoDemand(kunnr) {
	initMaterial("是否分货","/kunnrAction!kunnrAutoDemand.jspa?kunnrId="
			+ kunnr , 400, 350);
}

function initMaterial(title, url,WWidth,WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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

// 创建窗口对象
function openWindow(ffit,title, url, WWidth, WHeight,l,t) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						draggable : true,
						fit:ffit,
						left : l,
						top: t
					});

	$win.window('open');

}
// 关闭窗口
function closeWindow() {
	$("#hiddenWin").window('close');
}
//盘点模板下载
function downloadExcelModel(){
	$.messager.confirm('Confirm', '确认下载经销商导入模版?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/stock/stockManageAction!downloadExcelModel.jspa?excelModel='+encodeURI("kunnrImport.xlsx");
			form.target = "hideFrame";
			form.submit();
		}
	});
}

// excel导出
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!kunnrExport.jspa';
	form.submit();

}

	/**
	 * 打开组织树
	 */
	function selectOrgTree() {
		openWindow(false,'选择组织', '/customerAction!orgTreePage.jspa?orgId='+$('#orgId').val(), 400, 460,100,50);
	}
	/**
	 * 组织树选组织返回到输入框
	 * 
	 * @param selectedId
	 * @param selectedName
	 */
	function returnValue(selectedId, selectedName) {
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}
	/**
	 * 组织树选择完之后关闭
	 */
	function closeOrgTree() {
		closeWindow();
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
function getSapToKunnr(){
	$.messager.confirm('Confirm', '同步SAP的经销商主数据,预计时间大概需10分钟,确认是否同步?', function(r) {
		if (r) { 
			var form = window.document.forms[0];
			form.action = sapUrl+"/kunnrsAction!getKunnrListFromSAP.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}