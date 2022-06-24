$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	$("#status").combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : '0', 'flagText' : '未处理'},
			{'flagValue' : '1', 'flagText' : '处理中'},
			{'flagValue' : '2', 'flagText' : '已完成'}
		],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			if(flag == 'Y'){
				$('#status').combobox("setValue", '2');
			}		
		}
		
	});
	
	loadGrid();
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		search();
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

function loadGrid() {
		$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/travelApp/travelAppAction!searchTravelJson.jspa?searchUserRole=all',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : false ,
						height : height*0.88,
						columns : [ [
								{
									field : 'travelId',
									title : '报销单号',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},{
									field : 'transactionId',
									title : '申请单号',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										return "SQ"+v;
									}
								},{
									field : 'writeEventId',
									title : '核销单号',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										if(v !=undefined){
											return "HX"+v;
										}
									}
								},{
									field : 'title',
									title : '申请事务标题',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'writeEvent',
									title : '核销事务标题',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'payee',
									title : '收款人',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'auditMoney',
									title : '总金额',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'payType',
									title : '支付方式',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										if(v == '1'){
											return "现金";
										}else if(v == '2'){
											return "银行";
										}else if(v == '3'){
											return "其他";
										}
									}
								},{
									field : 'status',
									title : '申请状态',
									width : setColumnWidth(0.05),
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
								},{
									field : 'writeStatus',
									title : '核销状态',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "未处理";
										}else if (value == 1) {
											return "处理中";
										}else if (value == 2) {
											return "已完成";
										}else if (value == 3) {
											return "已拒绝";
										}else if (value == 4) {
											return "已取消";
										}else{
											return "未核销";
										}
									}
								},{
									field : 'createDate',
									title : '提报时间',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.travelId;
										var html = "";
											html = '<img style="cursor:pointer"  onclick=searchTravelDetail("'
													+ rid
													+ '","'
													+ row.transactionId 
													+ '") title="查看明细" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
											if(row.writeStatus!=undefined){		
													html=html
													+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=print("'
													+ rid
													+ '","'
													+ row.writeStatus
													+ '","'
													+ row.writeEventId 
													+ '") title="打印报销单" src='
													+ imgUrl
													+ '/images/actions/action_print.png align="absMiddle"></img>';
									        }
										return html;
									}
								}] ]
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
 * */
function selectedFile(grid, rows){
	for(var j=0;j<rows.length;j++){
		if(2 != rows[j]['status']){
			$(".datagrid-row[datagrid-row-index="+j+"] input[type='checkbox']")[0].disabled=true;
		}
	}
}

function search() {
		var bhxjFlag="";
		$("[name='bhxjFlag']").each(function() {
			if (this.checked) {
				bhxjFlag = this.value;
			} else {
				bhxjFlag = "N";
			}
		});
		var queryParams = $('#datagrid').datagrid('options').queryParams;
		queryParams.writeStatus = $('#status').combobox('getValue');
		var eventId=$("#eventId").val();
		if(isNaN(eventId)){
			eventId=$("#eventId").val().substring(2);
			var ev2=$("#eventId").val().substring(0,2);
			if(ev2!='hx'&&ev2!='HX'){
				$.messager.alert('Tips', "请正确输入您的查询申请事务单号", 'warning');
				return;
			}
		}
		queryParams.writeEventId = encodeURIComponent(eventId);
		queryParams.writeEvent = encodeURIComponent($("#title").val());
		queryParams.startDate =$("#startDate").datebox('getValue');
		queryParams.endDate = $("#endDate").datebox('getValue');
		queryParams.orgId = $("#orgId").val();
		queryParams.bhxjFlag = bhxjFlag;
		$("#datagrid").datagrid('reload');
}



function searchTravelDetail(rid,transactionId){
	initMainFrame(false,'差旅费用明细查看', '/travelApp/travelAppAction!travelDetail.jspa?travelId='+rid+"&eventId="+transactionId, 800, 500,150,80);
}

function print(rid,writeStatus,writeEventId){
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/travelApp/travelAppAction!print.jspa?travelId='+rid+'&eventId='+writeEventId+'&writeStatus='+writeStatus, "printTravel",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}




function closeMainFrame(){
	$("#maintFrame").window('close');
}

//创建窗口对象
function initMainFrame(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintFrame")
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

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function cancel(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function toTravelWta(travelId,transactionId){
	initMainFrame(true,'我要核销', '/travelApp/travelAppAction!travelWta.jspa?travelId='+travelId+"&transactionId="+transactionId+"&flag="+$("#flag").val(), 800, 500,0,0);
}
//创建窗口对象
function initMainFrame(ffit,title,url,widte,height,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintFrame")
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

function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}


function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}



