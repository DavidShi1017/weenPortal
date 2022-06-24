$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/userCustAction!getUserCustList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						//height : height,
						selectOnCheck: true,
						fit:true,
						queryParams: {
							empCode : encodeURI($("#empCode").val()),
							customerNumber :  $("#customerNumber").val(),
							empName:encodeURI($("#empName").val())
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : '编码',
									width : 100,
									align : 'center',
									sortable : true
								},
								
								{
									field : 'empCode',
									title : '员工编号',
									width : 100,
									align : 'center',
									sortable : true
								},{
									field : 'empName',
									title : '员工名称',
									width : 100,
									align : 'center',
									sortable : true
								},{
									field : 'customerNumber',
									title : '门店编码',
									width : 100,
									align : 'center', 
									sortable:true
							   },{
									field : 'customerName',
									title : '门店名称',
									width : 220,
									align : 'left', 
									sortable:true
							   },{
									field : 'beginDate',
									title : '有效开始时间',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
										  return value;
										//return utcToDate(value);
										}else{
										return "";
										}
									}
								},{
									field : 'endDate',
									title : '有效结束时间',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
											return value;
										//return utcToDate(value);
										}else{
										return "";
										}
									}
								},
								{
									field : 'operation',
									title : '操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, index) {
										var id = encodeURIComponent(row.id);
										return '<img style="cursor:pointer" onclick=deleteUserCust('
												+ id
												+ ') title="删除信息" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img> &nbsp;&nbsp;<img style="cursor:pointer" onclick=createUserCust('
												+ id
												+ ') title="管理信息" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												;
									}
								} 
								 ] ],
						toolbar : [ "-", {
											text : '新增',
											iconCls : 'icon-add',
											handler : function() {
												createUserCust(0);
											}
									}, "-",{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												delUserCust();
											}
									}] 
					});
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



function initMaterial(title, url,WWidth,WHeight) {
	var url = appUrl + url;
//	var WWidth = 850;
//	var WHeight = 450;
	var $win = $("#werkPlan")
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

function closeMaterial(){
	// 关闭创建页面
$("#werkPlan").window('close');
}
function createUserCust(id) {
	initMaterial('创建产品组信息', '/userCustAction!editUserCust.jspa?id='+id,650,400);
	
}
 
 
function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.empCode = encodeURI($("#empCode").val());
	queryParams.customerNumber =  $("#customerNumber").val();
	queryParams.empName = encodeURI($("#empName").val());
	$("#datagrid").datagrid('load');
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
	//date = str[5] + "-";
	//date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	//date = str[0];//显示到日期
	date = utcCurrTime;//显示到时分秒
	return date;
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

function delProductGroup(id){
	  this.deleteProductGroup(id);
}
function delUserCust(){
	 var ids="";
	//获取所选行
	 var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
	 if(!(rows.length>0)){
		 $.messager.alert('提示：', '请选择要删除的行记录!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].id;
		 }else{
			 ids =ids+","+rows[i].id;
		 }
	 }
	this.deleteUserCust(ids);
}
function deleteUserCust(ids){
	$.messager.confirm('Confirm', '确认删除 ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/userCustAction!deleteUserCust.jspa?",
					data : {
						ids : ids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							$.messager.alert('提示：', '操作成功!', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
		}
	});
}