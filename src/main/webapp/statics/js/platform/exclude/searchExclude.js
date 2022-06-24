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
						url : appUrl + '/excludeAction!getExcludeList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						striped:true ,
						remoteSort : true,
						selectOnCheck: true,
						fit:true,
						pageSize:20,
						queryParams: {
							matnr :  $("#matnr").val(),
							kunnr :  $("#kunnr").val() 
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
								},{
									field : 'kappl',
									title : '应用程序',
									width : 100,
									align : 'center',
									sortable : true,
									hidden:true
								},{
									field : 'kschl',
									title : '条件类型',
									width : 100,
									align : 'center',
									sortable : true,
									hidden:true
								},{
									field : 'kunnr',
									title : '客户',
									width : 185,
									align : 'center', 
									sortable:true
							   },{
									field : 'matnr',
									title : '物料编码',
									width : 100,
									align : 'left', 
									sortable:true
							   },{
									field : 'datab1',
									title : '有效开始日期',
									width :100,
									align : 'center',
									hidden:true,
									sortable:true
								},{
									field : 'datbi1',
									title : '有效结束日期',
									width :100,
									align : 'center',
									sortable:true,
									hidden:true
								},{
									field : 'dtype',
									title : '类型',
									width :100,
									align : 'center',
									sortable:true,
									hidden:true,
									formatter : function(v){
										if(v=="01"){
											return "客户";
										}else if(v=="02"){
											return "客户组";
										}else if(v=="03"){
											return "县代码";
										}else if(v=="04"){
											return "城市代码";
										}
									}
								}
								 ] ],
						toolbar : [ "-", {
											text : '同步',
											iconCls : 'icon-add',
											handler : function() {
												getExcludeFromSap();
											}
									}, "-" ] 
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
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
	queryParams.matnr =  $("#matnr").val() ;
	queryParams.kunnr =  $("#kunnr").val();
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

function getExcludeFromSap(){
	$.messager.confirm('Confirm', '确认同步 ?', function(r) {
		if(r){
			 $.ajax({
					type : "post",
					async : false,
					url : appUrl+ "/excludeAction!getExcludeFromSap.jspa?",
					success : function(obj) {
							loadGrid();
					}
				});
		}
	});
}
 