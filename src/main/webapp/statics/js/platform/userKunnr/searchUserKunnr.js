$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						url : appUrl + '/userKunnrAction!getUserKunnrList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						fit:true,
						height : height,
						selectOnCheck: true,
						queryParams: {
							empCode : encodeURI($("#empCode").val()),
							kunnr :  $("#kunnr").val()
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : '编码',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'empCode',
									title : '员工编号',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'empName',
									title : '员工名称',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'kunnr',
									title : '客户编码',
									width : 80,
									align : 'center', 
									sortable:true
							   },{
									field : 'kunnrName',
									title : '客户名称',
									width : 250,
									align : 'center', 
									sortable:true
							   },{
									field : 'beginDate',
									title : '有效开始时间',
									width :100,
									align : 'center',
									formatter : function(value) {
										if(value !=null){
										  return value;
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
										return '<img style="cursor:pointer" onclick=deleteUserKunnr('
										+ id
										+ ') title="删除信息" src='
										+ imgUrl
										+ '/images/actions/action_del.png align="absMiddle"></img> &nbsp;&nbsp;<img style="cursor:pointer" onclick=editUserKunnr('
										+ id
										+ ') title="修改信息" src='
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
												createUserKunnr(0);
											}
									}, "-",{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												delUserKunnr();
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

//新建客户分配
function createUserKunnr(id) {
	initMaterial('创建客户分配信息', '/userKunnrAction!editUserKunnr.jspa?id='+id,600,400);
}

function editUserKunnr(id){
	initMaterial('修改客户分配信息', '/userKunnrAction!editUserKunnr.jspa?id='+id,600,400);
}
function delUserKunnr(){
	 var userKunnrIds="";
	//获取所选行
	 var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
	 if(!(rows.length>0)){
		 $.messager.alert('提示：', '请选择要删除的行记录!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 userKunnrIds = rows[i].id;
		 }else{
			 userKunnrIds =userKunnrIds+","+rows[i].id;
		 }
	 }
	this.deleteUserKunnr(userKunnrIds);
}
function deleteUserKunnr(userKunnrIds){
	$.messager.confirm('Confirm', '确认删除 ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/userKunnrAction!deleteUserKunnr.jspa",
					data : {
						userKunnrIds : userKunnrIds
					},
					success : function(obj) {
						loadGrid();
						$.messager.alert('提示：', '操作成功!', 'info');
					}
				});
		}
	});
}
 
function initMaterial(title, url,WWidth,WHeight) {
	var url = appUrl + url;
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}
function closeMaterial(){
	// 关闭创建页面
	$("#werkPlan").window('close');
}

function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.empCode = encodeURIComponent($("#empCode").val());
	queryParams.kunnr =  encodeURIComponent($("#kunnr").val());
	$("#datagrid").datagrid('load');
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

