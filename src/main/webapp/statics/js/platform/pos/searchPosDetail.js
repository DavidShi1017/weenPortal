$(document).ready(function() {
	loadGrid(); // 权锟睫碉拷锟窖�
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
						+ '/posAction!getPosListById.jspa?ran='
								+ Math.random()+'&file_id='+$("#file_id").val(),

						loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
 						pageSize:20,
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
										return "<font color='grey'>Transfer</font>";
									}else{
										return flag;
									} 									},
								align : 'center',
								
							},{
								title : "PC/EC",
								field : 'm_12nc',
								width : 50,
								align : 'center',
								//hidden:true
							},{
								title : "ID",
								field : 'id',
								width : 130,
								align : 'center',
								hidden:true
							},{
								title : "Error Codes",
								field : 'tips',
								width : 130,
								align : 'left'
							},
							{
								title : "TRANSACTION CODE",
								field : 'transaction_code',
								width : 50,
								align : 'center',
//								formatter : function(value, row, rec) {
//									var flag = row.transaction_code;
//									if (flag == 'S') {
//										return "<font color='red'>S</font>";
//									} else{
//										return flag;
//									} 
//								}
							},
							{
								title : "DISTI",
								field : 'disti_name',
								width : 110,
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
					
 						]],
						columns : [ [
								
								{
									title : "BOOK Part",
									field : 'book_part',
									width : 120,
									align : 'left'
								},
								{
									title : "SHIP QTY",
									field : 'ship_qty',
									width : 80,
									align : 'center',
 										formatter : function(value, row, rec) {
 											return formatNumber(value,0,1);
 										}
								},
								{
									title : "SHIP DATE",
									field : 'ship_date',
									width : 80,
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
									width : 80,
									align : 'center',
								},
								{
									title : "DISTI INVOICENBR",
									field : 'disti_invoice_nbr',
									width : 80,
									align : 'left'
								},
								{
									title : "DISTI INVOICE ITEM NUMBER",
									field : 'disti_invoice_item_number',
									width : 80,
									align : 'left',
								},
								{
									title : "DISTI COST CURRENCY",
									field : 'disti_cost_currency',
									width : 80,
									align : 'left',
								},{
									title : "DISTI RESALE DENOM",
									field : 'disti_resale_denom',
									width : 80,
									align : 'left',
									formatter : function(value, row, rec) {
											return formatNumber(value,4,1);
										}
								},{
									title : "DISTI RESALE CURRENCY",
									field : 'disti_resale_currency',
									width : 80,
									align : 'left',
								},{
									title : "PURCHASING CUSTOMER NAME",
									field : 'purchasing_customer_name',
									width : 80,
									align : 'left',
								},{
									title : "PURCHASING CUST CITY",
									field : 'purchasing_cust_city',
									width : 80,
									align : 'left',
								},{
									title : "PURCHASING CUST ZIP",
									field : 'purchasing_cust_zip',
									width : 80,
									align : 'left',
								},{
									title : "PURCHASING CUST STATE",
									field : 'purchasing_cust_state',
									width : 80,
									align : 'left',
								},{
									title : "PURCHASING CUST COUNTRY",
									field : 'purchasing_cust_country',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST NAME",
									field : 'endcust_name',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST CITY",
									field : 'endcust_city',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST LOC",
									field : 'endcust_loc',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST ZIP",
									field : 'endcust_zip',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST STATE",
									field : 'endcust_state',
									width : 80,
									align : 'left',
								},{
									title : "ENDCUST COUNTRY",
									field : 'endcust_country',
									width : 80,
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
										downloadExcelModelForOne();
									}
								},"-", {
									text : 'Check',
									iconCls : 'icon-add',
									handler : function() {
										check();
									}
								},"-", {
									text : 'Approve',
									iconCls : 'icon-add',
									handler : function() {
										approved();
									}
								},"-", {
									text : 'Reject',
									iconCls : 'icon-add',
									handler : function() {
										reject();
									}
								},"-", {
									text : 'Edit',
									iconCls : 'icon-edit',
									handler : function() {
										edit();
									}
								}],
					onClickCell: function (rowIndex, field, value) {	
						var rows = $('#datagrid').datagrid('getRows');
						var flag = rows[rowIndex].status;
						if(flag==1){	
							$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
						}else{
							$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
						}
				     }

					});
 
}



$('#clearV').click(function(){
	$('#start_time').datebox('setValue',"");
	$('#end_time').datebox('setValue',"");

});


function reset() {
	
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	}else{
		var form = window.document.forms[0];
		form.action = appUrl + '/posAction!resetPos.jspa?id='+rows[0].id;
		form.target = "hideFrame";
		form.submit();
	}
}

//!锟�
function approved() {
	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
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
	$.messager.confirm('Confirm', 'Sure to approve?', function(r) {
		if (r) {
			
			
			var form = window.document.forms[0];
			form.action = appUrl + "/posAction!approvePos.jspa?ids="
					+ ids.substring(0, ids.length-1)+"&status1=3";
			form.target = "hideFrame";
			form.submit();
		}
		}
	);
}


//!锟�
function reject() {
	var mark = 0 ;
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	for(var i=0;i<=rows.length-1;i++){
		ids = rows[i].id+","+ids;
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_name = encodeURIComponent($("#disti_name").val());
	queryParams.file_url = encodeURIComponent($("#file_url").val());
	queryParams.status = $("#status").val();
	queryParams.start_time = $("#start_time").datebox('getValue');
	queryParams.end_time = $("#end_time").datebox('getValue');
	queryParams.book_part = encodeURIComponent($("#book_part").val());	

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
			+'&e_zip='+encodeURIComponent(rows[0].endcust_zip==undefined?"":rows[0].endcust_zip)
			+'&p_name='+encodeURIComponent(rows[0].purchasing_customer_name==undefined?"":rows[0].purchasing_customer_name)
			+'&p_city='+encodeURIComponent(rows[0].purchasing_cust_city==undefined?"":rows[0].purchasing_cust_city)
			+'&p_zip='+encodeURIComponent(rows[0].purchasing_cust_zip==undefined?"":rows[0].purchasing_cust_zip)
			+'&pcec='+rows[0].m_12nc,10,10);
	
}

function edit() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false, '500','440',  'Edit Pos',
				'/posAction!toUpdatePCEC.jspa?id=' + rows[0].id,100,100);
	}
}


function check() {
//	var rows = $('#datagrid').datagrid('getSelections');
//  	if (rows.length == 0) {
//		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
//		return;
//	}  	
  	
  	
	
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate!', 'warning');
		return;
	}else{
		var mark = 0 ;
		var ids="";
		var rows = $('#datagrid').datagrid('getSelections');
		for(var i=0;i<=rows.length-1;i++){
			ids = rows[i].id+","+ids;
			if(rows[i].status=='1'){
				mark++;
			}
		}
		if(rows[0].id==null){
			$.messager.alert('info', 'Please select the data you need to operate!');
			return;
		}else if(mark!=rows.length){
//			$.messager.alert('info', 'Only when pending can be clicked!');
//			return;
		}
		
		
		var form = window.document.forms[0];
		form.action = appUrl + '/posAction!checkEDI.jspa?ids='+ ids.substring(0, ids.length-1);
			form.target = "hideFrame";
		form.submit();
	}
	
	
}

function importExcel(){
	initMaintAccount(false,'700','400','Import', '/posAction!importExcel.jspa',100,100);
}

//模!!!
function downloadExcelModel(){
		$.messager.confirm('Tips', 'Download pos mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("WeEn POS.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}

//!!!!
function downloadExcelModelForOne(){
		$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/posAction!downloadExcelModelForOne.jspa?file_id='+$("#file_id").val();
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


// 锟截闭达拷!页!
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