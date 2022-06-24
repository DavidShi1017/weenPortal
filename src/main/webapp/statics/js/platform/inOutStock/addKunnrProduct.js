$(document).ready(function() {
	//loadGrid();
	$('#hideFrame').bind('load', promgtMsg); 
});
$('#datagrid').datagrid({
	iconCls : 'icon-list',
	
	singleSelect : false,
	pagination : true,
	pageSize:20,
	nowrap : false,
	striped:true ,
	remoteSort : true,
	fit:true,
	selectOnCheck: true,
	rownumbers:true,
	columns : [[
			{
				field : 'ck',
				checkbox : true
			},{
				field : 'spart',
				title : '品类编码',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'mvgr1',
				title : '系列编码',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'kbetr',
				title : '价钱',
				width : 70,
				align : 'center',
				hidden:true
			},{
				field : 'matnr',
				title : '产品编码',
				width : 80,
				align : 'center',
				sortable : true
			},{
				field : 'maktx',
				title : '产品名称',
				width : 400,
				align : 'center', 
				sortable:true
		   },{
				field : 'spart_txt',
				title : '品类',
				width : 70,
				align : 'center',
				sortable : true
			},{
				field : 'mvgr1_txt',
				title : '系列',
				width : 70,
				align : 'center',
				sortable : true
			}
			 ]],
	toolbar : [ "-", {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							createProduct();
						}
				}] 
});

function loadGrid() {
	var txt=$('#productName').val();
	$('#datagrid').datagrid({
				iconCls : 'icon-list',
				url : appUrl + '/inOutStockAction!getAllProductList.jspa',
				queryParams: {maktx:encodeURIComponent(txt),productIdList:$('#hdProductIdList').val()},
				loadMsg : '数据远程载入中,请等待...',
				singleSelect : false,
				pagination : true,
				pageSize:20,
				nowrap : false,
				striped:true ,
				remoteSort : true,
				fit:true,
				selectOnCheck: true,
				rownumbers:true,
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						 
						},{
							field : 'spart',
							title : '品类编码',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'mvgr1',
							title : '系列编码',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'kbetr',
							title : '价钱',
							width : 70,
							align : 'center',
							hidden:true
						},{
							field : 'matnr',
							title : '产品编码',
							width : 80,
							align : 'center',
							sortable : true
						},{
							field : 'maktx',
							title : '产品名称',
							width : 400,
							align : 'center', 
							sortable:true
					   },{
							field : 'spart_txt',
							title : '品类',
							width : 70,
							align : 'center',
							sortable : true
						},{
							field : 'mvgr1_txt',
							title : '系列',
							width : 70,
							align : 'center',
							sortable : true
						}
						 ] ],
				toolbar : [ "-", {
									text : '确定',
									iconCls : 'icon-add',
									handler : function() {
										createProduct();
									}
							}] 
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



function search(){
	loadGrid();
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

function createProduct(){
	var selRows = $('#datagrid').datagrid('getChecked'); 
	var names = [];
	$.each(selRows, function(index, item){
		names.push(item.mvgr1+"#"+item.matnr+"#"+item.maktx+"#"+item.mvgr1_txt+"#"+item.kbetr+"#"+item.spart);
	}); 
	window.parent.addProduct(names);
}



