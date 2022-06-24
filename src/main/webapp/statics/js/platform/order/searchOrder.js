$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});
$('#clearV').click(function(){
	$('#branch_id').combogrid('clear');
	$('#start_dateStr').datebox('setValue',"");
	$('#end_dateStr').datebox('setValue',"");
	setTimeout("search()",100);
});
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/order/orderAction!getOrderList.jspa',
				        loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : "ID",
									field : 'id',
									width : 100,
									hidden : true,
									align : 'center'
								},
								{
									title : "Status",
									field : 'state',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.state;
										if (flag == '9') {
											return "<font color='red'>Deleted</font>";
										}else if (flag == '0') {
											return "<font color='black'>Pending</font>";
										} else{
											return flag;
										} 
									},
									hidden:true
								},
								{
									title : "Sync Status",
									field : 'sync_state',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.sync_state;
										if (flag == '0') {
											return "<font color='red'>Pending</font>";
										}else if (flag == '1') {
											return "<font color='green'>Active</font>";
										} 
									}
								},
								{
									title : "Order Date",
									field : 'create_time',
									width : 110,
									align : 'center',
									formatter : function(date){
										return utcToDate(date);
									}
								},
								{
									title : "Purchase Order",
									field : 'order_id',
									width : 110,
									align : 'left'
								},
								{
									title : "Sap Order Id",
									field : 'sap_order_id',
									width : 110,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.sap_order_id;
										if (flag != undefined) {
										var str=(flag).replace( /^0*/, '');
										return str;
										}else{
											return "";
											}
										}
								},
								{
									title : "Order Type",
									field : 'type_id',
									width : 120,
									align : 'center',
									hidden:true
								},
								{
									title : "Sold To<br>����������",
									field : 'branch_id',
									width : 250,
									align : 'left'
								},
								{
									title : "Currency",
									field : 'currency_code',
									width : 100,
									align : 'center'
								},
								{
									title : "Ship To",
									field : 'ship_to',
									width : 400,
									align : 'left'
								},
								{
									title : "Payer To",
									field : 'payer_to',
									width : 250,
									align : 'left',
								},
								{
									title : "Billing To",
									field : 'billing_to',
									width : 250,
									align : 'left'
								},
								{
									title : "Saler",
									field : 'saler',
									width : 100,
									align : 'center',
									hidden:true
								}] ],
						toolbar : [  "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
						 , "-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}
						 ,  "-", {
							 text : 'Delete',
							 iconCls : 'icon-remove',
							 handler : function() {
								 dele();
							 }
						 }
						 ,  "-", {
							text : 'View',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}, "-", {
							text : 'Sync',
							iconCls : 'icon-reload',
							handler : function() {
								sync();
							}
						},"-", {
						text : 'Output',
						iconCls : 'icon-outport',
						handler : function() {
							downLoad();
						}
					}
						 ],
						onDblClickRow:function(rowIndex,rowData){
							initMaintAccount(true,'1000','550','Detail Information', '/orderAction!toViewOrder.jspa?id='+rowData.id);
						},
						onSelect : function(rowIndex, rowData) {
							var state = rowData.sync_state;
							 if(state=="1"){
								 $('div.datagrid-toolbar a').eq(1).linkbutton('disable');
								 $('div.datagrid-toolbar a').eq(2).linkbutton('disable');
								 $('div.datagrid-toolbar a').eq(4).linkbutton('disable');
							 }else{
								 $('div.datagrid-toolbar a').eq(1).linkbutton('enable');
								 $('div.datagrid-toolbar a').eq(2).linkbutton('enable');
								 $('div.datagrid-toolbar a').eq(4).linkbutton('enable');
							 }
						}
					});

}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.order_id = $("#order_id").val();
	queryParams.branch_id = $("#branch_id").combobox('getValue');
	queryParams.state = encodeURIComponent($("#state").val());
	queryParams.start_dateStr = encodeURIComponent($("#start_dateStr").datebox('getValue'));
	queryParams.end_dateStr = encodeURIComponent($("#end_dateStr").datebox('getValue'));
	queryParams.sap_order_id = encodeURIComponent($("#sap_order_id").val());
	$("#datagrid").datagrid('load');
}

function downLoad(){
	$.messager.confirm('Confirm', 'Confirmed about  OutPort?', function(r) {
		if(r){
			var form = window.document.forms[0];
			form.action = appUrl + '/orderAction!downloadOrderData.jspa';
			form.target = "hideFrame";
			form.submit();			
		}
	});
}
function add(){
	initMaintAccount(true,'1000','550','Create Order', '/orderAction!toCreateOrder.jspa',0,0);	
}

function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Error', 'Please select the data item', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Edit Order', '/orderAction!toUpdateOrder.jspa?id='+rows[0].id,0,0);	
	}
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Error', 'Please select the data item', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/orderAction!toViewOrder.jspa?id='+rows[0].id);
	}	
}

function sync(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Error', 'Please select the data item', 'warning');
		return;
	} else {
//		if (var i = 0;i <rows.length;i++) {
//			if rows[i].
//			$.messager.alert('info', 'Please select the data item!');
//			return;
//		}
		var form = window.document.forms[0];
		form.action = appUrl + '/sampleOrderToSapAction!getSampleOrderToSAP.jspa?id='+rows[0].id;
		form.target = "hideFrame";
		form.submit();
		
	}
}
/**
 * ɾ��
 */
function dele() {
	$.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'Please select the data item!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/orderAction!deleteOrder.jspa?id='+rows[0].id;
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function initMaintAccount(ffit,widte,height,title,url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
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


// �رմ���ҳ��
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Error', failResult, 'warning');
	} else {
		if(successResult==undefined ||successResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}


//�����̿ͻ�
$('#branch_id').combogrid({
	panelHeight : 280,
	panelWidth : 360,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa?loginId='+$('#loginId').val()+'&multiDisti=Y',
	idField : 'customer_code',
	textField : 'customer_name',
	columns : [[{
				field : 'customer_code',
				title : 'Customer Code',
				width : 70,
				formatter : function(value, row, rec) {
					var flag = row.customer_code;
					if (flag == ''||flag==undefined) {
						return "";
					} else{
						var str = $.trim(flag);
						str = str.substring(str.length-6, str.length);
						return str;
					} 
				}
			}, {
				field : 'customer_name',
				title : 'Customer Name',
				width : 250
			}]],
			toolbar : '#toolbarCustomer',
			onSelect : function(index, record){
		 	},
});
function searcherCustomer(name1) {
	var queryParams = $('#branch_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#branch_id').combogrid("grid").datagrid('reload');
} 

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // �������textarea�ڲ�ִ���κβ���
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // �����readOnly����disable��ִ���κβ���
	if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};

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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	//	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}