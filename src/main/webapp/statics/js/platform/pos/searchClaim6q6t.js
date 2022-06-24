$(document).ready(function() {
	loadGrid(); // Ȩ�޵!ѯ
	$('#hideFrame').bind('load', promgtMsg);
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
						+ '/claimAction!getPosList6q6t.jspa',

						loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
 						pageSize:20,
						onDblClickRow:function(rowIndex, rowData){
			            	
//			            	 showErrorCodeDetail();
 			            },
 			           onLoadSuccess:function(){
 							var flag = $('#loginRole').val();
// 							alert(flag);
 							if(flag.indexOf("sys_admin") >= 0 || flag.indexOf("HK10_H_Claim_Mgmt") >= 0){	
 								$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
 								$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
 								$('div.datagrid-toolbar a').eq(2).linkbutton('enable');
 								$('div.datagrid-toolbar a').eq(3).linkbutton('enable');
 							}else{
 								$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
 								$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
 								$('div.datagrid-toolbar a').eq(2).linkbutton('disable');
 								$('div.datagrid-toolbar a').eq(3).linkbutton('disable');
 							}
 			            },
 						frozenColumns:[[
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
									}else if (flag == '4') {
											return "<font color='green'>Rebated</font>";
									}else{
											return flag;
									} 									
								},
								align : 'center',
							},	{
								title : "TRANSACTION CODE",
								field : 'transaction_code',
								width : 80,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.transaction_code;
									if (flag == 'S') {
										return "<font color='red'>S</font>";
									}else{
										return flag;
									}
								}
							},{
								title : "PC/EC",
								field : 'm_12nc',
								width : 130,
								align : 'center',
								hidden:true
							},{
								title : "Error Codes",
								field : 'tips',
								width : 80,
								align : 'center'
							},
							{
								title : "File Id",
								field : 'file_id',
								width : 80,
								align : 'center'
							},
//							{
//								title : "approve remark",
//								field : 'rejecttoapprove_remark',
//								width : 180,
//								align : 'center'
//							},
							{
								title : "ID",
								field : 'id',
								width : 80,
								align : 'center'
							},
			             ]],
						columns : [ [
								
								
								{
									title : "DISTI",
									field : 'disti_name',
									width : 150,
									align : 'center'
								},
								{
									title : "DISTIBRANCH",
									field : 'disti_branch',
									width : 150,
									align : 'center'
								},
								{
									title : "DISTIACCT",
									field : 'disti_city',
									width : 110,
									align : 'center'
								},
								{
									title : "BOOK PART",
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
									width : 110,
									align : 'left',
								},
								{
									title : "DISTI CLAIM NBR",
									field : 'disti_claimnbr',
									width : 110,
									align : 'left',
								},
								{
									title : "OPPREG NBR",
									field : 'oppreg_nbr',
									width : 100,
									align : 'center',
									hidden:true,
								},
								{
									title : "CPN",
									field : 'cpn',
									width : 100,
									align : 'center',
									hidden:true,
								},
								{
									title : "DISTI INVOICENBR",
									field : 'disti_invoice_nbr',
									width : 90,
									align : 'left'
								},
								{
									title : "DISTI INVOICE ITEM NUMBER",
									field : 'disti_invoice_item_number',
									width : 90,
									align : 'left',
								},{
									title : "Disti Cost",
									field : 'disti_cost',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
								},{
									title : "Cost Denom",
									field : 'cost_denom',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
								},
								{
									title : "DISTI COST CURRENCY",
									field : 'disti_cost_currency',
									width : 90,
									align : 'left',
								},{
									title : "Disti Cost ExchangeRate",
									field : 'disti_cost_exchangeRate',
									width : 90,
									align : 'left',
								},{
									title : "Disti Bookcost",
									field : 'disti_bookcost',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
									
								},{
									title : "Dbc Denom",
									field : 'dbc_denom',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
								},{
									title : "Dbc Currency Code",
									field : 'dbc_currency_code',
									width : 90,
									align : 'left',
								},{
									title : "Dbc Exchange Rate",
									field : 'dbc_exchange_rate',
									width : 90,
									align : 'left',
								},{
									title : "Disti Resale",
									field : 'disti_resale',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
				
								},{
									title : "DISTI RESALE DENOM",
									field : 'disti_resale_denom',
									width : 90,
									align : 'left',
//									formatter : function(value, row, rec) {
//										return formatNumber(value,4,1);
//									}
								},{
									title : "DISTI RESALE CURRENCY",
									field : 'disti_resale_currency',
									width : 90,
									align : 'left',
								},{
									title : "Disti Resale Exchange Rate",
									field : 'Disti_resale_exchange_rate',
									width : 90,
									align : 'left',
								},
								{
									title : "PURCHASING CUSTOMER NAME",
									field : 'purchasing_customer_name',
									width : 300,
									align : 'left',
								},{
									title : "PURCHASING CUST CITY",
									field : 'purchasing_cust_city',
									width : 90,
									align : 'left',
								},{
									title : "PURCHASING CUST ZIP",
									field : 'purchasing_cust_zip',
									width : 90,
									align : 'left',
								},{
									title : "PURCHASING CUST STATE",
									field : 'purchasing_cust_state',
									width : 90,
									align : 'left',
								},
								{
									title : "PURCHASING CUST COUNTRY",
									field : 'purchasing_cust_country',
									width : 90,
									align : 'left',
								},
								{
									title : "ENDCUST NAME",
									field : 'endcust_name',
									width : 300,
									align : 'left',
								},{
									title : "ENDCUST CITY",
									field : 'endcust_city',
									width : 90,
									align : 'left',
								},{
									title : "ENDCUST LOC",
									field : 'endcust_loc',
									width : 90,
									align : 'left',
								},{
									title : "ENDCUST ZIP",
									field : 'endcust_zip',
									width : 90,
									align : 'left',
								},{
									title : "ENDCUST STATE",
									field : 'endcust_state',
									width : 90,
									align : 'left',
								},
								{
									title : "ENDCUST COUNTRY",
									field : 'endcust_country',
									width : 90,
									align : 'left',
								},
								{
									title : "DISTI ACCOUNTING NBR",
									field : 'disti_accounting_nbr',
									width : 90,
									align : 'left',
								},
								{
									title : "(CMD)PC NAME",
									field : 'cmd_pc_name',
									width : 200,
									align : 'left',
								},{
									title : "(CMD)PC CITY",
									field : 'cmd_pc_city',
									width : 90,
									align : 'left',
								},							
								{
									title : "(CMD)PC COUNTRY",
									field : 'cmd_pc_country',
									width : 90,
									align : 'left',
								},
								{
									title : "(quote)PC NAME",
									field : 'quote_pc_name',
									width : 200,
									align : 'left',
								},{
									title : "(quote)PC CITY",
									field : 'quote_pc_city',
									width : 90,
									align : 'left',
								},							
								{
									title : "(quote)PC COUNTRY",
									field : 'quote_pc_country',
									width : 90,
									align : 'left',
								},
								{
									title : "(CMD)EC NAME",
									field : 'cmd_ec_name',
									width : 200,
									align : 'left',
								},{
									title : "(CMD)EC CITY",
									field : 'cmd_ec_city',
									width : 90,
									align : 'left',
								},							
								{
									title : "(CMD)EC COUNTRY",
									field : 'cmd_ec_country',
									width : 90,
									align : 'left',
								},
								{
									title : "(quote)EC NAME",
									field : 'quote_ec_name',
									width : 200,
									align : 'left',
								},{
									title : "(quote)EC CITY",
									field : 'quote_ec_city',
									width : 90,
									align : 'left',
								},							
								{
									title : "(quote)EC COUNTRY",
									field : 'quote_ec_country',
									width : 90,
									align : 'left',
								},
								] ],
								toolbar : [
										"-", {
											text : 'Outport',
											iconCls : 'icon-excel',
											handler : function() {
												downloadExcelModelForOne();
											}
										},
										"-",
										{
											text : 'Check',
											iconCls : 'icon-edit',
											handler : function() {
												check();
											}
										},
										"-", {
											text : 'Reject',
											iconCls : 'icon-remove',
											handler : function() {
												reject();
											}
										},
										"-", {
											text : 'Approve',
											iconCls : 'icon-ok',
											handler : function() {
												approve();
											}
										},
									],
							});
		}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.material_id = encodeURIComponent($("#material_id").val());
	queryParams.material_name = encodeURIComponent($("#material_name").val());
	queryParams.state = encodeURIComponent($("#state").val());
	queryParams.status = $("#status").val();
	queryParams.pc_ec_name = $("#pc_ec_name").val();
	queryParams.error_code = $("#error_code").val();
	queryParams.debit_number = $("#debit_number").val();
	queryParams.invoice_number = $("#invoice_number").val();
	
	
	$("#datagrid").datagrid('load');
}

function importExcel(){
	initMaintAccount(false,'700','400','Import', '/claimAction!importExcel.jspa',100,100);
}

$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");

});

function reset() {
	
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operated!', 'warning');
		return;
	}else{
		var ids="";
		var rows = $('#datagrid').datagrid('getSelections');
		for(var i=0;i<=rows.length-1;i++){
			if (rows[i].status=='4'){
				$.messager.alert('Tips', 'sorry rebate status can not operated!', 'warning');
				return;
			}
			ids = rows[i].id+","+ids;			
		}
		var form = window.document.forms[0];
		form.action = appUrl + '/posAction!resetClaim.jspa?ids='+ids.substring(0, ids.length-1);
		form.target = "hideFrame";
		form.submit();
	}
}


//5T 6T
function showErrorCodeDetail(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	}else{
		var id="";
		var rows = $('#datagrid').datagrid('getSelections');
		if (rows.length > 1) {
			$.messager.alert('Tips', 'Please select one record to operate!', 'warning');
			return;
		}
		 var a ="";
		for(var i=0;i<=rows.length-1;i++){
			id = rows[i].id
			a = rows[i].tips
		}
		if (a.indexOf('5T1') >= 0 || a.indexOf('5T2') >= 0 || a.indexOf('5Q1') >= 0 || a.indexOf('5Q2') >= 0 || a.indexOf('6Q') >= 0 || a.indexOf('6T') >= 0){
			initMaintAccount(false,'930','450','Claim Error Code--'+a,'/posAction!searchClaimErrorCode.jspa?id='+id,100,100);
		}
		
		
	}
}


function approve() {
	
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	}else{
		var ids="";
		var status ="";
		var rows = $('#datagrid').datagrid('getSelections');
		for(var i=0;i<=rows.length-1;i++){
			
			ids = rows[i].id+","+ids;
			if (rows[i].status=='4'){
				$.messager.alert('Tips', 'sorry rebate status can not operated!', 'warning');
				return;
			}
			if (rows[i].status!="1"){
				$.messager.alert('Tips', 'all must is pending!', 'warning');
				return;
			}
		}
		
		initMaintAccount(false,'400','300','Approve Remark','/posAction!rejectToApproveRemark.jspa?ids='+ids,150,150);
//		var form = window.document.forms[0];
//		form.action = appUrl + '/posAction!approveClaim.jspa?ids='+ids.substring(0, ids.length-1);
//		form.target = "hideFrame";
//		form.submit();
	}
}

//!!!!
function downloadExcelModelForOne(){
		$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl +'/claimAction!downloadExcelForClaim6q6t.jspa?file_id='+$("#file_id").val();
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}


//ģ!!!
function downloadExcelModel(){
		$.messager.confirm('Tips', 'Download claim mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("Claim.xlsx");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}
  

function check() {
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operated!', 'warning');
		return;
	}
  	
  	
  	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
//		alert( rows[i].id);
		if (rows[i].status=='4'){
			$.messager.alert('Tips', 'sorry rebate status can not operated!', 'warning');
			return;
		}
		if(rows[i].status=='1'){
			mark++;
		}
	}
	if(rows[0].id==null){
		$.messager.alert('info', 'Please select the data you need to operated!');
		return;
	}
	
//	else if(mark!=rows.length){
//		$.messager.alert('info', 'Only when pending can be clicked!');
//		return;
//	}
	$.messager.confirm('Confirm', 'Sure to check?', function(r) {
		if (r) {
			
			var form = window.document.forms[0];
			form.action = appUrl + '/claimAction!checkEDIs.jspa?ids='+ ids.substring(0, ids.length-1);
			form.target = "hideFrame";
			form.submit();
		}
	});

  	
	
	
}


function reject() {
	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
		if (rows[i].status=='4'){
			$.messager.alert('Tips', 'sorry rebate status can not operate!', 'warning');
			return;
		}
		if(rows[i].status=='1'){
			mark++;
		}
	}
	if(rows[0].id==null){
		$.messager.alert('info', 'Please select the data you need to operate!');
		return;
	}else if(mark!=rows.length){
		$.messager.alert('info', 'Only when pending can be clicked!');
		return;
	}
	$.messager.confirm('Confirm', 'Sure to reject?', function(r) {
		if (r) {
			
			
			var form = window.document.forms[0];
			form.action = appUrl + "/posAction!approvePos.jspa?ids="
			+ ids.substring(0, ids.length-1)+"&status1=0";
			form.target = "hideFrame";
			form.submit();
		}
		}
	);
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


// �رմ!�ҳ!
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function closeMain() {
	$("#hiddenWin").window('close');
	search();
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