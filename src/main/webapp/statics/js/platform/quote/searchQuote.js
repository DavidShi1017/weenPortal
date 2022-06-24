$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
if($('#forWho').val()=="Disti"){
	$('#cusGroup_id').attr('disabled','disabled'); 
}
$('#clearV').click(function(){
	//$('#customer_id').combogrid('clear');
	$('#endCustomer_id').combogrid('clear');
	$('#purchaseCustomer_id').combogrid('clear');
	$('#start_dateStr').datebox('setValue',"");
	$('#end_dateStr').datebox('setValue',"");
	setTimeout("search()",100);	
});
function loadGrid() {
	var noCMM = false;
	var isDisti = false;
	if($('#roleId').val()=='JXS'){
		isDisti = true;
	}
	if($('#roleId').val()=='JXS'||$('#roleId').val()=="HK10_H_Sale_Mgmt"){
		noCMM = true;
	}
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '',
				//height : 370,
				fit:true,
				striped : true,
				url : appUrl+ '/quote/quoteAction!getOutPortQuoteList.jspa',
				queryParams : {
					states : $('#state').val(),
					isAgrees : $('#isAgree').val(),
					myself: $('#myself').val(),
				},
		        loadMsg : 'Loading...',
				singleSelect : true,
				nowrap : true,
				// idField : 'id',
				pagination : true,
				rownumbers : true,
				fitColumns : false,
				frozenColumns:[[
				                {
				                	field : 'ck',
				                	align : 'center',
				                	checkbox : true
				                },
				                {
									title : "Agreement",
									field : 'isAgree',
									width : 70,
									align : 'center',
									formatter : function(value, row, rec) {
										/**
										 * 状态
										 * 0处理中，1确认
										 */
										var flag = row.isAgree;
										if (flag == '0') {
											return "";
										}else if (flag == '1') {
											return "<font color='green'>Agree</font>";
										}  else if (flag == '2') {
											return "<font color='gray'>Expired</font>";
										}  
									},
									//hidden:true,
								},
				                {
				                	title : "Quote status",
				                	field : 'state',
				                	width : 90,
				                	align : 'center',
				                	formatter : function(value, row, rec) {
				                		var flag = row.state;
				                		if (flag == '9') {
				                			return "<font color='red'>Deleted</font>";
				                		}else if (flag == '0') {
				                			return "<font color='black'>Pending</font>";
				                		}else if (flag == '1') {
				                			return "<font color='black'>Pending</font>";
				                		}else if (flag == '2') {
				                			return "<font color='black'>Pending</font>";
				                		}else if (flag == '3') {
				                			return "<font color='green'>Approved</font>";
				                		}else if (flag == '4') {
				                			return "<font color='green'>Approved</font>";
				                		}else if (flag == '5') {
				                			return "<font color='green'>Approved</font>";
				                		}else if (flag == '6') {
				                			return "<font color='red'>Reject</font>";
				                		}else if (flag == '7') {
				                			return "<font color='red'>Reject</font>";
				                		}else if (flag == '8') {
				                			return "<font color='red'>Reject</font>";
				                		} else{
				                			return flag;
				                		} 
				                	}
				                },
						
					]],
				columns : [ [
							{
								title : "ID",
								field : 'id',
								width : 80,
								hidden : true,
								align : 'center'
							},
							{
								field : 'flag',
								title : 'flag<br>同一个单号标记',
								width : 100,						
								align : 'center',
								hidden:true
							},
							{
								title : "OrderID",
								field : 'main_id',
								width : 80,
								hidden : true,
								align : 'center'
							},
							{
								title : "Quote number",
								field : 'quote_id',
								width : 90,
								align : 'center',
							},{
								field : 'debit_num',
								title : 'Debit Num',
								width : 120,
								align : 'center',
							},
							
							{
								field : 'material_id',
								title : '12NC<br>物料编码',
								width : 110,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.material_id;
									if (flag == ''||flag==undefined) {
										return "";
									} else{
										var str = $.trim(flag);
										str = str.substring(str.length-12, str.length);
										return str;
									} 
								}
							},
							{
								field : 'material_name',
								title : 'BookPart<br>物料名称',
								width : 150,
								align : 'left',
								//hidden:true
							},
							{
								field : 'isDRItem',
								title : 'DR Indicator',
								width : 80,
								align : 'center',
								hidden:true
							},
							{
								field : 'drNum',
								title : 'DR Number<br>项目注册的编码',
								width : 80,
								align : 'center',
							
							},
							{
								field : 'qty',
								title : 'QTY<br>订购数量',
								width : 80,
								align : 'center',
								formatter : function(value, row, rec) {
									var flag = row.qty;
									if (flag == ''||flag==undefined) {
										return "";
									} else{	
										return formatNumber(flag*1,0,1);
									} 
								}
							} ,
							{
					 			field : 'res_qty',
					 			title : 'Remain QTY',
					 			width : 85,
					 			align : 'center',
					 			formatter : function(value, row, rec) {
									var flag = row.res_qty;
									if (flag == ''||flag==undefined) {
										return "";
									} else{	
										return formatNumber(flag*1,0,1);
									} 
								}//,hidden:true
					 		} ,
							{
								field : 'target_cost',
								title : 'Target Cost <br>目标进货价格', 
								width : 80,
								align : 'center',	
								formatter : function(value, row, rec) {
									var flag = row.target_cost;
									if (flag == 0||flag == ''||flag==undefined) {
										return flag;
									} else{
										flag=JSON.parse(flag*1);
										return flag.toFixed(4);
									} 
								}
							} ,
							{
								field : 'target_resale',
								title : 'Target Resale<br>目标销售价格',
								width : 95,
								align : 'center',	
								formatter : function(value, row, rec) {
									var flag = row.target_resale;
									if (flag == 0||flag == ''||flag==undefined||row.target_resale==0) {
										return flag;
									} else{
										flag=JSON.parse(flag*1);
										return flag.toFixed(4);
									} 
								}
							} ,
//							{
//								field : 'target_amount',
//								title : 'Target Amount',
//								width : 80,
//								align : 'center',
//							} ,
							{
								field : 'target_margin',
								title : 'Target Margin', 
								width : 95,
								align : 'center',	
								formatter : function(value, row, rec) {
									var flag = row.target_margin;
									if (flag == ''||flag==undefined||row.target_resale==0) {
										return "";
									} else{
										flag=JSON.parse(flag*1);
										flag= flag.toFixed(2)+"%";
										return flag;
									} 
								}
							} ,
							{
								field : 'sale_price',
								title : 'Regional Min <br>销售最低价价格', 
								width : 95,
								align : 'center',	
								formatter : function(value, row, rec) {
									var flag = row.sale_price;
									if (flag == 0||flag == ''||flag==undefined) {
										return "<font color='red'>No Price</font>";
									} else{
										flag=JSON.parse(flag*1);
										return flag.toFixed(4);
										
									} 
								},
								hidden:isDisti
							} ,
							{
								field : 'stop_price',
								title : 'CMM<br>停止价格', 
								width : 80,
								align : 'center',	
								formatter : function(value, row, rec) {
									var flag = row.stop_price;
									if (flag == 0||flag == ''||flag==undefined) {
										return "<font color='red'>No Price</font>";
									} else{
										flag=JSON.parse(flag*1);
										return flag.toFixed(4);
										
									} 
								},
								hidden:noCMM
								
							} ,
							{
								title : "Price Region",
								field : 'price_region',
								width : 80,
								align : 'center',
								hidden:isDisti
							},
							{
								title : "Disti",
								field : 'cusGroup_id',
								width : 150,
								align : 'left'
							},
							{
								title : "Disti Branch",
								field : 'disti_branch',
								width : 250,
								align : 'left'
							},
							{
								title : "Disti Region",
								field : 'disti_region',
								width : 80,
								align : 'center',
								hidden:isDisti
							},
//							{
//								title : "Customer Group",
//								field : 'cusGroup_id',
//								width : 110,
//								align : 'center',
//								hidden:true
//							},
							
							{
								title : "PC Group",
								field : 'pcGroup_id',
								width : 110,
								align : 'center',
								hidden:true
							},
							{
								title : "PC Group",
								field : 'pcGroup_name',
								width : 110,
								align : 'center',
								hidden:true
							},
							{
								title : "Purchasing Customer id",
								field : 'purchaseCustomer_id',
								width : 150,
								align : 'center'
							},
							{
								title : "Purchasing Customer",
								field : 'purchaseCustomer_name',
								width : 150,
								align : 'center'
							},
							{
								title : "PC Region",
								field : 'pc_region',
								width : 80,
								align : 'center',
								hidden:isDisti
							},
							{
								title : "PC LOCATION",
								field : 'pc_city',
								width : 90,
								align : 'center'
							},
							{
								title : "EC Group",
								field : 'ecGroup_name',
								width : 100,
								align : 'center',
								hidden:true
							},
							{
								title : "End Customer id",
								field : 'endCustomer_id',
								width : 150,
								align : 'center'
							},
							{
								title : "End Customer",
								field : 'endCustomer_name',
								width : 150,
								align : 'left',
							},
							{
								title : "EC Region",
								field : 'ec_region',
								width : 80,
								align : 'center',
								hidden:isDisti
							},
							{
								title : "EC LOCATION",
								field : 'ec_city',
								width : 90,
								align : 'center'
							},
						{
							title : "Project",
							field : 'project_name',
							width : 90,
							align : 'center'
						},
						{
							title : "Currency",
							field : 'currency_code',
							width : 90,
							align : 'center'
						},
						{
							field : 'pbMpp',
							title : 'PB/MPP', 
							width : 80,
							align : 'center',	
							formatter : function(value, row, rec) {
								var flag = row.pbMpp;
								if (flag == 0||flag == ''||flag==undefined) {
									return "<font color='red'>No Price</font>";
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
									
								} 
							},
							hidden:isDisti
						} ,
						
						{
							field : 'suggest_cost',
							title : 'Final Quoted Cost',
							width : 120,
							align : 'center',	
						//	editor: {type:'numberbox',options:{precision:4}},
							formatter : function(value, row, rec) {
								var flag = row.suggest_cost;
								if (flag == 0||flag == ''||flag==undefined) {
									return flag;
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
								} 
							}
						} ,
						{
							field : 'suggest_resale',
							title : 'Final Quoted Resale <br>审批销售价', 
							width : 140,
							align : 'center',	
							//editor: {type:'numberbox',options:{precision:4}},
							formatter : function(value, row, rec) {
								var flag = row.suggest_resale;
								if (flag == 0||flag == ''||flag==undefined) {
									return flag;
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
								} 
							}
						} ,
						{
							field : 'cus_profits_percent',
							title : 'Final Quoted Disti Margin', 
							width : 170,
							align : 'center',	
							//editor: {type:'text',options:{disabled:true}},
							formatter : function(value, row, rec) {
								var flag = row.cus_profits_percent;
								if (flag == ''||flag==undefined||flag=='undefined') {
									return "";
								} else{
							
									return flag+"%";
								} 
							}
						} ,
						{
							field : 'profits_percent',
							title : 'Mfr Margin <br>瑞能利润', 
							width : 90,
							align : 'center',								
							editor: {type:'text',options:{disabled:true}},
							formatter : function(value, row, rec) {
								var flag = row.profits_percent;
								if (flag == ''||flag==undefined||flag=='undefined') {
									return "";
								} else{
							
									return flag+"%";
								}  
							},
							//hidden:isDisti,
							hidden : true,
						} ,					
						{
							field : 'amount',
							title : 'Final Quoted Amount<br>行项目总价',
							width : 140,
							align : 'center',
							editor: {type:'numberbox',options:{precision:4}},
							formatter : function(value, row, rec) {
									return formatNumber(value*1,0,1);
							}//,hidden:true
							//editor: {type:'numberbox',options:{precision:4,disabled:true}},
						} ,
						{
							field : 'cost',
							title : 'Cost <br>物料成本',
							width : 80,
							align : 'center',
							hidden:true
						} ,{
							field : 'create_time',
							title : 'Create Date',
							width : 120,
							align : 'center',
							formatter : function(date){
								return utcToDate(date);
							}
						},
						{
							field : 'start_dateStr',
							title : 'Effective Date<br>生效日期',
							width : 120,
							align : 'center',
						},
						{
							field : 'end_dateStr',
							title : 'Expire Date<br>截止日期',
							width : 120,
							align : 'center',
						},
						{
							field : 'remark',
							title : 'Internal Comments<br>审批意见',
							width : 150,
							align : 'left',	
							formatter : function(value, row, rec) {
								var flag = row.remark;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else if(flag.length >= 30) {
									  flag = flag.substring(0,24);
									  flag+= "...";
									  return flag;
								}else{	
									return flag;
								}  
							},
							hidden:isDisti
						},
						{
				 			field : 'data_from',
				 			title : 'Data Source',
				 			width : 100,
				 			align : 'center',
				 			formatter : function(value, row, rec) {
								var flag = row.data_from;
								if (flag == '2') {
									return "<font color='blue'>EDI</font>";
								} else {
									return "<font color='black'>NonEDI</font>";
								}  
							},
							hidden:true
				 		},
				 		{
				 			field : 'debit_startStr',
				 			title : 'Debit Start',
				 			width : 100,
				 			align : 'center',
//				 			editor:{type:'datebox',options:{
//								onSelect:function(d){//d即选中时间
//									var editors = $('#datagrid').datagrid('getEditors', editIndex);
//									// 绑定EDITOR，并赋值
//									var endEditor = editors[1];					
//									var td = new Date();//当前时间
//									td.setHours( 0, 0, 0, 0 );	
//									if(d < td){
//										$(this).datebox('setValue','');
//										endEditor.target.datebox('setValue','');
//										$.messager.alert('Tips', 'The Debit Start cannot be earlier than the current date！', 'warning');
//										return;
//									}
////									d.setMonth(d.getMonth()+3);
////							    	var endDay=  d.format('yyyy-MM-dd');
////									endEditor.target.datebox('setValue',endDay);
//								}
//							}},
				 		},{
				 			field : 'debit_endStr',
				 			title : 'Debit End',
				 			width : 100,
				 			align : 'center',
//							editor:{type:'datebox',options:{//1
//								onSelect:function(d){
//									//alert($(this).datebox('getValue'));
//									var editors = $('#datagrid').datagrid('getEditors', editIndex);
//									var td = new Date();
//									td.setHours( 0, 0, 0, 0 );	
//									if(d < td){
//										$(this).datebox('setValue','');
//										$.messager.alert('Tips', 'The Debit End cannot be earlier than the current date！', 'warning');
//										return;
//									}
//									var dStartEditor = editors[0];
//									var dStartDate = dStartEditor.target.datebox('getValue');
//									var sd = new Date(dStartDate);
//									if( sd>= d){
//										$(this).datebox('setValue','');
//										$.messager.alert('Tips', 'The Debit End must later than the Debit Start！', 'warning');
//										return;
//									}
//								}
//							}}
				 		},
				 		{
							field : 'cus_remark',
							title : 'Comments<br>客户意见',
							width : 150,
							align : 'left',	
							formatter : function(value, row, rec) {
								var flag = row.cus_remark;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else if(flag.length >= 30) {
									  flag = flag.substring(0,24);
									  flag+= "...";
									  return flag;
								}else{	
									return flag;
								}  
							}
						},	
						{
				 			field : 'Represent',
				 			title : 'Represent',
				 			width : 100,
				 			align : 'center',
				 			formatter : function(value, row, rec) {
								var flag = row.isRepresent;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else{	
									if (flag=='Represent' || flag=='represent' )
										return "Y";
									else
										return flag;
								} 
							}
				 		},
						{
							field : 'competitor',
							title : 'Competitor<br>竞争对手',
							width : 150,
							align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.competitor;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else{	
									return flag;
								} 
							}
						},
						{
							field : 'reason',
							title : 'Justification<br>申请原因', 
							width : 100,
							align : 'center',	
							formatter : function(value, row, rec) {
								var flag = row.reason;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else{	
									return flag;
								} 
							}
						} ,
						
						{
				 			field : 'product_dateStr',
				 			title : 'Start of Production<br>生产启动日期',
				 			width : 120,
				 			align : 'center',
				 		},
							
						
						] ],
						toolbar : [  "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						} ,  "-", {
							text : 'View',
							iconCls : 'icon-view',
							handler : function() {
								check();
							}
						}
						,  "-", {
							text : 'Copy',
							iconCls : 'icon-copy',
							handler : function() {
								copy();
							}
						}
						],

							onDblClickRow:function(rowIndex,rowData){
								initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rowData.main_id+'&loginRole='+$('#loginRole').val());
							},
//							onSelect : function(rowIndex, rowData) {
//								var state = rowData.state;
//								 if(state!=1){
//									$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
//									$('div.datagrid-toolbar a').eq(3).linkbutton('enable');
//								}else{
//									$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
//									$('div.datagrid-toolbar a').eq(3).linkbutton('disable');
//								}
//							},
							onLoadSuccess:function(data){
								var loginRole = $('#loginRole').val().split("*");
								var canEdit = false;
								for(var i=0;i<loginRole.length;i++){
									var role = loginRole[i];
									if(role=="JXS"){//只有Disti
										canEdit=true;break;
									}
								}
								if(canEdit){
									$('div.datagrid-toolbar a').eq(0).show();
//									$('div.datagrid-toolbar a').eq(2).show();
//									$('div.datagrid-toolbar a').eq(3).show();									
								}else{
									$('div.datagrid-toolbar a').eq(0).hide();
//									$('div.datagrid-toolbar a').eq(2).hide();
//									$('div.datagrid-toolbar a').eq(3).hide();	
								}
							}
					});
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.cusGroup_id = $("#cusGroup_id").val();
	queryParams.debit_num = $("#debit_num").val();
	queryParams.quote_id = $("#quote_id").val();
	//queryParams.customer_id = $("#customer_id").combobox('getValue');
	queryParams.disti_branch = $("#disti_branch").val();
	queryParams.endCustomer_name = $("#endCustomer_name").val();
	queryParams.purchaseCustomer_name = $("#purchaseCustomer_name").val();
	queryParams.project_name = $("#project_name").val();
	queryParams.material_id = $("#material_id").val();
	queryParams.material_name = $("#material_name").val();
	queryParams.states = $('#state').val();
	queryParams.isAgrees = $('#isAgree').val();
	queryParams.create_userName = $('#create_userName').val();
	queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
	queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
//	queryParams.myself = $('#myself').val();
	$("#datagrid").datagrid('load');
}
function add(){
		initMaintAccount(true,'1000','550','Create Quote', '/quoteAction!toCreateQuote.jspa',0,0);	
}
function copy(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Copy Quote', '/quoteAction!toCopyCreate.jspa?id='+rows[0].main_id,0,0);	
	}

}
function sync(){
	function sync(){
//		var rows = $('#datagrid').datagrid('getSelections');
//	  	if (rows.length == 0) {
//			$.messager.alert('Tips', 'Please select the data item！', 'warning');
//			return;
//		} else {
			var form = window.document.forms[0];
//			form.action = appUrl + '/quoteToSapAction!getQuoteToSAP.jspa?id='+rows[0].id;
			form.action = appUrl + '/quoteToSapAction!getQuoteToSAP.jspa?';
			form.target = "hideFrame";
			form.submit();
			
		}
	//}

}

function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Edit Quote', '/quoteAction!toUpdateQuote.jspa?id='+rows[0].main_id,0,0);	
	}
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rows[0].main_id+'&loginRole='+$('#loginRole').val());
	}	
}

/**
 * 删除
 */
function dele() {
	$.messager.confirm('Confirm', 'Confirmed about  delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'Please select the data item！');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/quoteAction!deleteQuote.jspa?id='+rows[0].main_id;
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function agree() {
	$.messager.confirm('Confirm', 'Confirmed about  Agree?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'Please select the data item！');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/quoteAction!auditQuote.jspa?id='+rows[0].main_id+'&state=1';
			form.target = "hideFrame";
			form.submit();
		}
	});

}


function downLoad(){
//	var form = window.document.forms[0];
//	form.action = appUrl + '/quoteAction!downloadExcelModel.jspa';
//	form.target = "hideFrame";
//	form.submit();
	initMaintAccount(false,'600','450','OutPort Quote', '/quoteAction!toOutPort.jspa',100,20);	
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


// 关闭创建页面
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
	     // 如果是在textarea内不执行任何操作
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // 如果是readOnly或者disable不执行任何操作
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}	  