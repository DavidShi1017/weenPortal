$(document).ready(function() {
	loadGrid();
});

function loadGrid(){
	$("#datagrid").datagrid({
		method: 'get',
		async : false,
		url:appUrl+ '/inOutStockAction!getCustmNoJson.jspa?ran='+Math.random(),
		//queryParams: {},
		columns:[[ 
		          	{
									field : 'ck',
									checkbox : true,
								 
								},{field:'ID',title:'ID',width:80,align:'center',hidden:true}, 
					{field:'KUNNR',title:'客户编码',width:80,align:'center'}, 
					{field:'KUNNM',title:'客户描述',width:250}, 
					{field:'CREATEDATE',title:'创建时间',width:130,align:'center'},
					{field:'STATE',title:'状态',width:60,align:'center'}, 
					{field:'MEMO',title:'屏蔽原因',width:400,align:'center'}
				]],
		fit:true,
		rownumbers: true,
		showFooter:true,
		pagination : true,
		pageSize:30,
		singleSelect:true,
		toolbar: [{
			iconCls: "icon-add",
			text: "新增",
			handler:function(){
				createRecord();
			}
		},"-",{
			iconCls: "icon-remove",
			text: "删除",
			handler:function(){
				deleteRecord();
			}
		},"-",{
			iconCls: "icon-edit",
			text: "编辑",
			handler:function(){
				editRecord();
			}
		}]
	});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 30,
		pageList : [ 30, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function createRecord() {
	initWindows('新建无效客户', '/inOutStockAction!custmNoEdit.jspa',600,400);
}
function editRecord(){
	var ids="";
	//获取所选行
	 var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
	 if(!(rows.length>0)){
		 $.messager.alert('提示：', '请选择要编辑的行记录!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].ID;
		 }else{
			 ids =ids+","+rows[i].ID;
		 }
	 }
	 initWindows('修改无效客户', '/inOutStockAction!custmNoEdit.jspa?id='+ids,600,400);
}
function deleteRecord(){
	 var ids="";
	//获取所选行
	 var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
	 if(!(rows.length>0)){
		 $.messager.alert('提示：', '请选择要删除的行记录!', 'info');
		 return false;
	 }
	 for(var i =0;i<rows.length;i++){
		 if(i==0){
			 ids = rows[i].ID;
		 }else{
			 ids =ids+","+rows[i].ID;
		 }
	 }
	 $.messager.confirm('提示', '确认删除 ?', function(r) {
			if(r){
				 $.ajax({
						type : "post",
						async : false,
						url : appUrl+ "/inOutStockAction!deleteCustmNo.jspa",
						data : {
							id : ids
						},
						success : function(obj) {
							loadGrid();
							$.messager.alert('提示：', '操作成功!', 'info');
						}
					});
			}
		});
}

function initWindows(title, url,WWidth,WHeight) {
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
						fit : false,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}
function closeWindows() {
	$("#hiddenWin").window('close');
}
