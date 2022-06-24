$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});
function downLoad(){
	var form = window.document.forms[0];
	form.action = appUrl + '/file/fileAction!downLoadFile.jspa?fileId=0';
	form.target = "hideFrame";
	form.submit();
}
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl+ '/endCustomer/endCustomerAction!pendingData.jspa?end_customer_id='+$('#end_customer_id').val(),
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						//pagination : true,
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
									width : 80,
									hidden : true,
									align : 'center'
								},
								{
									title : "Quote ID/DR NUM",//��ϵ��
									field : 'quote_id',
									width : 200,
									align : 'left',
								},
								{
									title : "create time",//�绰
									field : 'create_time',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.create_time;
										var state=row.state;
										if (state == '3') {
											return "<font color='gray'>"+flag+"</font>";
											
										}  else{
											return flag;
										}
									}
								}
								] ],
//						toolbar : [  "-", {
//							text : 'View',
//							iconCls : 'icon-view ',
//							handler : function() {
//								check();
//							}
//						},
//						 "-", {
//							text : 'User',
//							iconCls : 'icon-group',
//							handler : function() {
//								searchUser();
//							}
//						},
//						 "-", {
//							text : 'Sync',
//							iconCls : 'icon-reload ',
//							handler : function() {
//								sync();
//							}
//						},
//						"-" ],
					onDblClickRow:function(rowIndex,rowData){
						initMaintAccount(false,860,500,'Detail Information', '/customerAction!toViewCustomer.jspa?id='+rowData.id,50,0);
					},
					});
	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 20,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.customer_name = encodeURIComponent($("#customer_name").val());
	queryParams.search = encodeURIComponent($("#customer_code").val());
	queryParams.org_code = encodeURIComponent($("#orgId").val());
	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}
/*
 * �û�����
 */
function searchUser(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false,'800','450','User', '/customerAction!toSearchCustomerUser.jspa?customer_code='+rows[0].customer_code);	
	}
	}
function sync(){
	initMaintAccount(false,'450','400','Sync', '/customerAction!toSyncCustomer.jspa',100,100);	
}
// �������ڶ���
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
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
						closed : true,///
						closable : true,//
 						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true,//
						fit:true
					});

	$win.window('open');
}
 
function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false,860,500,'Detail Information', '/customerAction!toViewCustomer.jspa?id='+rows[0].id,50,0);
	}	
}
/**
 * ɾ��
 */
function deleteCustomer() {
	$.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tip', 'Please select the data item!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push({
					'id' : rows[i].id,
				});
			}
			$("#ids").val($.toJSON(ids));
			var form = window.document.forms[0];
			form.action = appUrl + '/boformAction!deleteReportParameter.jspa';
			form.target = "hideFrame";
			form.submit();

		}
	});

}
// �رմ���ҳ��
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

//�����̿ͻ�
//$('#customer_code').combogrid({
//	panelHeight : 280,
//	panelWidth : 360,
//	pagination : true,
//	pageSize:10,
//	multiple : false,
//	editable : false,
//	method : 'post',
//	singleSelect : true,
//	url : appUrl + '/customer/customerAction!getCustomerList.jspa?customer_code='+$('#customer_code').val(),
//	idField : 'customer_code',
//	textField : 'customer_name',
//	columns : [[{
//				field : 'customer_code',
//				title : 'Code',
//				width : 70,
//				formatter : function(value, row, rec) {
//					var flag = row.customer_code;
//					if (flag == ''||flag==undefined) {
//						return "";
//					} else{
//						var str = $.trim(flag);
//						str = str.substring(str.length-6, str.length);
//						return str;
//					} 
//				}
//			}, {
//				field : 'customer_name',
//				title : 'Customer Name',
//				width : 250
//			}]],
//			toolbar : '#toolbarCustomer',
//			onSelect : function(index, record){
//		 	},
//});
//function searcherCustomer(name1) {
//	var queryParams = $('#customer_code').combogrid("grid").datagrid('options').queryParams;
//	queryParams.search = encodeURIComponent(name1);
//	$('#customer_code').combogrid("grid").datagrid('reload');
//} 

//��֯
function selectOrgTree(){
	initMaintWindowForOrg('Org Tree', '/orgAction!orgTreePage.jspa');
}

function closeOrgTree() {
	$("#hiddenWin").window('close');
}
function returnValue(selectedId, selectedName){
	document.getElementById('orgId').value =selectedId;
	document.getElementById('orgName').value= selectedName;
}
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
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
						closed : true,///
						closable : true,//
						left: ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
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

