$(document).ready(function() {
	loadGrid(); // 权锟睫碉拷锟窖�
	$('#hideFrame').bind('load', promgtMsg);
});

$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");
//	setTimeout("search()",100);
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
						+ '/posAction!searchPosTrackingDetail.jspa?type=1',

						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
 						pageSize:20,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},{
									title : "Status",
									field : 'status',
									width : 60,
									formatter : function(value, row, rec) {
										var flag = row.status;
										if (flag == '0') {
											return "<font color='red'>Reject</font>";
										}else if (flag == '3') {
											return "<font color='green'>Approved</font>";
										}else if (flag == '1') {
											return "<font color='blue'>Pending</font>";
										}else if (flag == '9') {
											return "<font color='blue'>Transfer</font>";
										}else{
											return flag;
										} 									},
									align : 'center',
									
								},{
									title : "File ID",
									field : 'file_id',
									width : 90,
									align : 'center',
								}
								,{
									title : "PC/EC",
									field : 'm_12nc',
									width : 130,
									align : 'center',
									hidden:true
								},{
									title : "Error Codes",
									field : 'tips',
									width : 130,
									align : 'center'
								},
								{
									title : "TRANSACTION CODE",
									field : 'transaction_code',
									width : 50,
 									align : 'center',
								},
								{
									title : "DISTI",
									field : 'disti_name',
									width : 180,
									align : 'center'
								},
								{
									title : "DISTIBRANCH",
									field : 'disti_branch',
									width : 120,
									align : 'center'
								},
								{
									title : "DISTIACCT",
									field : 'disti_city',
									width : 90,
									align : 'center'
								},
								{
									title : "BOOK Part",
									field : 'book_part',
									width : 120,
									align : 'left'
								},
								{
									title : "SHIP QTY",
									field : 'ship_qty',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										return formatNumber(value,0,1);
									}
								},
								{
									title : "SHIP DATE",
									field : 'ship_date',
									width : 100,
									align : 'left'
								},
								{
									title : "DEBIT NUMBER",
									field : 'debit_number',
									width : 130,
									align : 'left',
								},
								{
									title : "OPPREG NBR",
									field : 'oppreg_nbr',
									width : 100,
									align : 'center',
								},
								{
									title : "DISTI INVOICENBR",
									field : 'disti_invoice_nbr',
									width : 100,
									align : 'left'
								},
								{
									title : "DISTI INVOICE ITEM NUMBER",
									field : 'disti_invoice_item_number',
									width : 100,
									align : 'left',
								},
								{
									title : "DISTI COST CURRENCY",
									field : 'disti_cost_currency',
									width : 100,
									align : 'left',
								},{
									title : "DISTI RESALE DENOM",
									field : 'disti_resale_denom',
									width : 100,
									align : 'left',
									formatter : function(value, row, rec) {
										return formatNumber(value,4,1);
//										var flag = row.suggest_cost;
//										if (flag == 0||flag == ''||flag==undefined) {
//											return flag;
//										} else{
//											flag=JSON.parse(flag*1);
//											return flag.toFixed(4);
//										} 
									}
									
								},{
									title : "DISTI RESALE CURRENCY",
									field : 'disti_resale_currency',
									width : 100,
									align : 'left',
								},{
									title : "PURCHASING CUSTOMER NAME",
									field : 'purchasing_customer_name',
									width : 200,
									align : 'left',
								},{
									title : "PURCHASING CUST CITY",
									field : 'purchasing_cust_city',
									width : 100,
									align : 'left',
								},{
									title : "PURCHASING CUST ZIP",
									field : 'purchasing_cust_zip',
									width : 200,
									align : 'left',
								},{
									title : "PURCHASING CUST STATE",
									field : 'purchasing_cust_state',
									width : 200,
									align : 'left',
								},{
									title : "PURCHASING CUST COUNTRY",
									field : 'purchasing_cust_country',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST NAME",
									field : 'endcust_name',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST CITY",
									field : 'endcust_city',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST LOC",
									field : 'endcust_loc',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST ZIP",
									field : 'endcust_zip',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST STATE",
									field : 'endcust_state',
									width : 200,
									align : 'left',
								},{
									title : "ENDCUST COUNTRY",
									field : 'endcust_country',
									width : 200,
									align : 'left',
								}] ],
								toolbar : [ "-", {
									text : 'Add',
									iconCls : 'icon-add',
									handler : function() {
										add();
									}
								},   "-", {
									text : 'Outport',
									iconCls : 'icon-excel',
									handler : function() {
										downloadPosTrackingDetail();
									}
								}
//								,"-", {
//									text : 'Check',
//									iconCls : 'icon-add',
//									handler : function() {
//										check();
//									}
//								},
								],
					onClickCell: function (rowIndex, field, value) {	
						var rows = $('#datagrid').datagrid('getRows');
						var flag = rows[rowIndex].status;
						if(flag==1){	
							$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
						}else{
							$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
						}
				     },
		
					});
 
}







function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_name = encodeURIComponent($("#disti_name").val());
	queryParams.book_part = encodeURIComponent($("#book_part").val());	
	queryParams.purchasing_customer_name = encodeURIComponent($("#purchasing_customer_name").val());
	queryParams.endcust_name = encodeURIComponent($("#endcust_name").val());
	queryParams.book_part = encodeURIComponent($("#book_part").val());
	queryParams.file_ids = $("#file_ids").val();
	queryParams.start_timeString = $("#start_time").datebox('getValue');
	queryParams.end_timeString = $("#end_time").datebox('getValue');
	queryParams.status = $("#status").val();
	queryParams.data_from = $("#data_from").val();
	queryParams.disti_invoice_nbr = $("#disti_invoice_nbr").val();
	$("#datagrid").datagrid('load');
}


function add() {
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	} 
	initMaintAccount(false, '1000','500', 'Create ECAlias',
			'/posAction!toCreateECAlias.jspa?e_name='+encodeURIComponent(rows[0].endcust_name==undefined?"":rows[0].endcust_name)
			+'&e_city='+encodeURIComponent(rows[0].endcust_city==undefined?"":rows[0].endcust_city)
			+'&p_name='+encodeURIComponent(rows[0].purchasing_customer_name==undefined?"":rows[0].purchasing_customer_name)
			+'&p_city='+encodeURIComponent(rows[0].purchasing_cust_city==undefined?"":rows[0].purchasing_cust_city)
			+'&pcec='+rows[0].m_12nc,10,10);
	
}


function check() {
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate锟斤拷', 'warning');
		return;
	}else{
		var form = window.document.forms[0];
		form.action = appUrl + '/posAction!checkEDI.jspa?id='+rows[0].id;
			form.target = "hideFrame";
		form.submit();
	}
	
	
}



//锟斤拷锟斤拷锟斤拷锟斤拷
function downloadPosTrackingDetail(){
	var start_timeString = $("#start_time").datebox('getValue');
	var end_timeString = $("#end_time").datebox('getValue');
		$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/posAction!downloadPosTrackingDetail.jspa?start_timeString='+start_timeString +'&end_timeString='+end_timeString+'&type=1';
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


// 锟截闭达拷锟斤拷页锟斤拷
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
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
	return true;
};