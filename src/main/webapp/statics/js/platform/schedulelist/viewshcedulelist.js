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
						url : appUrl + '/schedulelistAction!viewshcedulelist.jspa',
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
									field : 'team',
									title : '搬运队',
									width : 80,
									align : 'center',
									sortable:true 
							   },{
									field : 'workstart_date',
									title : '开始装卸',
									width : 150,
									align : 'center',
									sortable:true 
							   },{
									field : 'workend_date',
									title : '结束装卸',
									width : 150,
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
										return '<img style="cursor:pointer" onclick=editShceduleList('
												+ id
												+ ') title="管理信息" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img> &nbsp;&nbsp;'
												;
									}
								}
								 ] ],
						
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



function closeMaterial(){
	// 关闭创建页面
$("#werkPlan").window('close');
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


//修改
function editShceduleList(id) {
	initMaterial('装卸结果', '/schedulelistAction!toeditShceduleList.jspa?ids='+id,500,350);
}
 