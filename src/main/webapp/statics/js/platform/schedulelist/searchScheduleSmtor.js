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
						url : appUrl + '/schedulelistAction!getScheduleSmtor.jspa',
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
							bolnr : encodeURI($("#bolnr").val())
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
									field : 'bolnr',
									title : '装运单号',
									width : 180,
									align : 'center',
									sortable : true
								},{
									field : 'vstel',
									title : '装运点',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'brgew',
									title : '重量',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'bolnr_level',
									title : '运单优先级',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'location_level',
									title : '地址优先级',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'states',
									title : '状态',
									width : 80,
									align : 'center',
									sortable:true,
									formatter : function(v){
										if(v=="1")
										{
											return "已排程";
										}else 
										{
											return "--"; 
										} 
									}
							   },{
									field : 'is_lock',
									title : '锁定',
									width : 80,
									align : 'center',
									sortable:true,
									formatter : function(v){
										if(v=="1")
										{
											return "已锁定";
										}else 
										{
											return "--"; 
										} 
									}
							   },{
									field : 'operation',
									title : '操作',
									width : 180,
									align : 'center',
									formatter : function(value, row, index) {
										var id =  row.id;
										return '<img style="cursor:pointer" onclick=uplevel('
												+ id
												+ ') title="优先级上" src='
												+ imgUrl
												+ '/images/platform/activitiWebEdit/icons/action.up.png align="absMiddle"></img> &nbsp;&nbsp;'
												+'<img style="cursor:pointer" onclick=downlevel('
												+ id
												+ ') title="优先级下" src='
												+ imgUrl
												+ '/images/platform/activitiWebEdit/icons/action.down.png align="absMiddle"></img>'
												;
									}
								}
								 ] ],
						toolbar : [ "-", {
											text : '同步',
											iconCls : 'icon-reload',
											handler : function() {
												synchroSmtor();
											}
									}, "-",{
											text : '排程',
											iconCls : 'icon-add',
											handler : function() {
												shcedulelist();
											}
									}, "-",{
										text : '查看排程清单',
										iconCls : 'icon-add',
										handler : function() {
											viewshcedulelist();
										}
								}, "-",{
									text : '锁定',
									iconCls : 'icon-add',
									handler : function() {
										islock();
									}
							}, "-",{
								text : '解锁',
								iconCls : 'icon-add',
								handler : function() {
									unlock();
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
	queryParams.bolnr = encodeURI($("#bolnr").val());
	$("#datagrid").datagrid('load');
}



//同步装运单
function synchroSmtor() {
	$.messager.confirm('Confirm', '确认同步?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!synchroSmtor.jspa?",
					data : {
						strids : 0
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

//排程
function shcedulelist(strids){
	$.messager.confirm('Confirm', '确认排程?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!shcedulelist.jspa?",
					data : {
						strids : 0
					},
					success : function(obj) {
						if(obj=="1"){
							loadGrid();
							$.messager.alert('提示：', '操作成功!', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!'+obj, 'info');
						}
					}
				});
		}
	});

}
//查看排程清单
function viewshcedulelist(){
	var url = appUrl + '/schedulelistAction!toviewshcedulelist.jspa?';
	var WWidth = 850;
	var WHeight = 450;
	var $win = $("#werkPlan")
			.window(
					{
						title : "排程清单",
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

//运单优先级向上
function uplevel(strids){
//	$.messager.confirm('Confirm', '确认删除 ?', function(r) {
//		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!uplevel.jspa?",
					data : {
						strids : strids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							//$.messager.alert('提示：', '操作成功!', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
//		}
//	});
}
 
//运单优先级向下
function downlevel(strids){
//	$.messager.confirm('Confirm', '确认删除 ?', function(r) {
//		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/schedulelistAction!downlevel.jspa?",
					data : {
						strids : strids
					},
					success : function(obj) {
						if(obj>0){
							loadGrid();
							//$.messager.alert('提示：', '操作成功!', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
//		}
//	});
}		
//锁定			 
	function islock(){
			var ids="";
			//获取所选行
			var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
			if(!(rows.length>0)){
				$.messager.alert('提示：', '请选择要锁定的行记录!', 'info');
				return false;
			}
			for(var i =0;i<rows.length;i++){
						 if(i==0){
							 ids = rows[i].id;
						 }else{
							 ids =ids+","+rows[i].id;
						 }
			 }
			$.messager.confirm('Confirm', '确认删锁定?', function(r) {
							if(r){
								 $.ajax({
										type : "post",
										async : false,
										url : appUrl+ "/schedulelistAction!islock.jspa?",
										data : {
											strids : ids
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
	
	
	//解锁		 
	function unlock(){
			var ids="";
			//获取所选行
			var rows = $('#datagrid').datagrid("getSelections");	    //获取你选择的所有行	 //循环所选的行
			if(!(rows.length>0)){
				$.messager.alert('提示：', '请选择要锁定的行记录!', 'info');
				return false;
			}
			for(var i =0;i<rows.length;i++){
						 if(i==0){
							 ids = rows[i].id;
						 }else{
							 ids =ids+","+rows[i].id;
						 }
			 }
			$.messager.confirm('Confirm', '确认删锁定?', function(r) {
							if(r){
								 $.ajax({
										type : "post",
										async : false,
										url : appUrl+ "/schedulelistAction!unlock.jspa?",
										data : {
											strids : ids
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
