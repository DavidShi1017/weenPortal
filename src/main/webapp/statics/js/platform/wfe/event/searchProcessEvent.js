$(document).ready(function() {
	
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/eventAction!getProcessEventJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.11),
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
									width : setColumnWidth(0.13),
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
									width : setColumnWidth(0.13),
									align : 'center'
									
								},
								{
									field : 'curStaId',
									title : '角色Id',
									hidden : true
									
								},
								{
									field : 'modelId',
									title : '事务模板id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'keys',
									title : 'keys',
									hidden : true
									
								},
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.15),
									align : 'center'
									
								},
								{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.11),
									align : 'center',
									formatter : function(value){
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
									field : 'creatdate',
									title : '提出时间',
									width : setColumnWidth(0.16),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.18),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:processEvent("'
												+ row.eventId + '","'+ row.currentDetailid + '","'
												+ row.keys + '","'
												+ row.modelId +'","'
												+ row.curStaId +'")>处理 </a>|'
												+'<a href=javascript:graphTrace("'
												+ row.eventId + '") > 流程 </a>|'
												+ '<a href=javascript:searchProEventReader("'
												+ row.eventId + '") > 授权查看</a>';
										return strReturn;
									}
								} 
								] ]
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	$("#datagrid").datagrid('load');
}

function searchEventDetail(eventId) {
	var WWidth = 860;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + "/wfe/eventAction!searchEventDetail.jspa?eventId="
			+ eventId, "searchEventDetail", "left=" + WLeft + ",top=20"
			+ ",width=" + WWidth + ",height=" + (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}


//创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {
	
	var url = appUrl + url;
	var $win = $("#"+id)
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

function searchProEventReader(eventId) {
	initMaintEvent(true,'700','400','事务查阅人管理','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
}

function processEvent(eventId, toDoDetail,keys,modelId,curStaId) {
	initMaintEvent(true,800, 480,'事务处理结果',"/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process",0,0);
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
function closeMaintWindow(){
	$("#maintWindow").window("close");
}


