$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
		$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/loadCapacityAction!getLoadCapacityList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						fit:true,
						selectOnCheck: true,
						pageSize:20,
						queryParams: {
							vstel : encodeURI($("#vstel").val()),
							hand_team : encodeURI($("#hand_team").val())
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
								 
								},{
									field : 'id',
									title : 'id',
									width : 80,
									align : 'center',
									sortable : true,
									hidden:true
								},
								{
									field : 'vstel',
									title : '装运点',
									width : 80,
									align : 'center',
									sortable : true
								},{
									field : 'hand_team',
									title : '搬运队',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'start_date',
									title : '开始时间',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'end_date',
									title : '结束时间',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'scheduling',
									title : '装卸能力',
									width : 80,
									align : 'center',
									sortable:true 
							   },
								{
									field : 'operation',
									title : '操作',
									width : 180,
									align : 'center',
									formatter : function(value, row, index) {
										var id =  row.id;
										return '<img style="cursor:pointer" onclick=createLoadCapacity('
												+ id
												+ ') title="管理信息" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img> &nbsp;&nbsp;'
												+'<img style="cursor:pointer" onclick=deleteLoadCapacity('
												+ id
												+ ') title="删除信息" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>'
												;
									}
								} 
								 ] ],
						toolbar : [ "-", {
											text : '新增',
											iconCls : 'icon-add',
											handler : function() {
												createLoadCapacity(0);
											}
									}, "-",{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												delLoadCapacity();
											}
									}],
							onDblClickRow: function (rowIndex, rowData) {
 								 
							}
					});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,
		pageList : [ 20, 40, 60 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		searchList();
		return false;
	}
	return true;
};
 
function searchList() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.vstel = encodeURI($("#vstel").val());
	queryParams.hand_team = encodeURI($("#hand_team").val());
	$("#datagrid").datagrid('load');
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


//新增
function createLoadCapacity(id) {
	initMaterial('装卸能力', '/loadCapacityAction!eidtLoadCapacity.jspa?ids='+id,500,350);
}

function delLoadCapacity(){
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
	this.deleteLoadCapacity(ids);
	
}


function deleteLoadCapacity(strids){
	$.messager.confirm('Confirm', '确认删除 ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/loadCapacityAction!deleteLoadCapacity.jspa?",
					data : {
						strids : strids
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

function closeMaterial(){
	// 关闭创建页面
$("#werkPlan").window('close');
}

 