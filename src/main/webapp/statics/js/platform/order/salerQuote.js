$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
$('#clearV').click(function(){
	$('#customer_id').combogrid('clear');
	$('#endCustomer_id').combogrid('clear');
});
function loadGrid() {
	$('#datagrid').datagrid(
		{
			iconCls : 'icon-list',
			title : '',
			//height : 370,
			fit:true,
			striped : true,
			url : appUrl
			+ '/quote/quoteAction!getAuditQuoteList.jspa?states=(0,1,2,3,4,5,6,7,8)',
	        loadMsg : 'Loading...',
			singleSelect : true,
			nowrap : true,
			// idField : 'dictTypeId',
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
						title : "Status",
						field : 'state',
						width : 70,
						align : 'center',
						formatter : function(value, row, rec) {
							var flag = row.state;
							if (flag == '9') {
								return "<font color='red'>Deleted</font>";
							}else if (flag == '0') {
								return "<font color='black'>Sales Pending</font>";
							}else if (flag == '1') {
								return "<font color='black'>Business Pending</font>";
							}else if (flag == '2') {
								return "<font color='black'>Director Pending</font>";
							}else if (flag == '3') {
								return "<font color='green'>Sales Approved</font>";
							}else if (flag == '4') {
								return "<font color='green'>Business Approved</font>";
							}else if (flag == '5') {
								return "<font color='green'>Director Approved</font>";
							}else if (flag == '6') {
								return "<font color='red'>Sales Reject</font>";
							}else if (flag == '7') {
								return "<font color='red'>Business Reject</font>";
							}else if (flag == '8') {
								return "<font color='red'>Director Reject</font>";
							} else{
								return flag;
							} 
						}
					},
					{
						title : "OrderID",
						field : 'main_id',
						width : 80,
						hidden : true,
						align : 'center'
					},
					{
						title : "Quote Num",
						field : 'quote_id',
						width : 80,
						align : 'center'
					},
					{
						field : 'material_name',
						title : 'BookPart<br>物料名称',
						width : 120,
						align : 'center',
						//hidden:true
					},
					{
						field : 'material_id',
						title : '12NC<br>物料编码',
						width : 100,
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
					} ,
					{
						field : 'target_cost',
						title : 'Target Cost <br>目标进货价格', 
						width : 80,
						align : 'center',	
					} ,
					{
						field : 'target_resale',
						title : 'Target Resale<br>目标销售价格',
						width : 85,
						align : 'center',	
					} ,
					{
						field : 'sale_price',
						title : 'Regional Min <br>销售最低价价格', 
						width : 80,
						align : 'center',	
						formatter : function(value, row, rec) {
							var flag = row.sale_price;
							if (flag == 0||flag == ''||flag==undefined) {
								return "<font color='red'>No Price</font>";
							} else{
								return flag*1;
							} 
						}
					} ,
				]],
			columns : [ [
						{
							title : "Disti",
							field : 'customer_name',
							width : 250,
							align : 'left'
						},
						{
							title : "Customer Group",
							field : 'cusGroup_id',
							width : 110,
							align : 'center',
							hidden:true
						},
						{
							title : "PC Group",
							field : 'pcGroup_id',
							width : 110,
							align : 'center',
						},
						{
							title : "PC",
							field : 'purchaseCustomer_name',
							width : 100,
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
							title : "End Customer",
							field : 'endCustomer_name',
							width : 150,
							align : 'left',
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
						field : 'suggest_cost',
						title : 'Suggest Cost <br>审批进货价格', 
						width : 80,
						align : 'center',	
						editor: {type:'numberbox',options:{precision:4}},
					} ,
					{
						field : 'suggest_resale',
						title : 'Suggest Resale <br>审批销售价', 
						width : 80,
						align : 'center',	
						editor: {type:'numberbox',options:{precision:4}},
					} ,
					{
						field : 'cus_profits_percent',
						title : 'Disti Margin<br>客户利润', 
						width : 90,
						align : 'center',	
						editor: {type:'text',options:{disabled:true}},
					} ,
					{
						field : 'profits_percent',
						title : 'Mfr Margin <br>瑞能利润', 
						width : 90,
						align : 'center',	
						editor: {type:'text',options:{disabled:true}},
					} ,
					{
						field : 'amount',
						title : 'Value <br>行项目总价',
						width : 80,
						align : 'center',
						editor: {type:'numberbox',options:{precision:4}},
						//editor: {type:'numberbox',options:{precision:4,disabled:true}},
					} ,
					{
						field : 'cost',
						title : 'Cost <br>物料成本',
						width : 80,
						align : 'center',
						hidden:true
					} ,
					{
						field : 'reason',
						title : 'Justification<br>申请原因', 
						width : 100,
						align : 'center',	
					} ,
					{
						field : 'competitor',
						title : 'Competitor<br>竞争对手',
						width : 150,
						align : 'center',
					},
					{
						field : 'start_dateStr',
						title : 'Start of Production<br>生产启动日期',
						width : 150,
						align : 'center',
					},		
					{
						field : 'cus_remark',
						title : 'Cus Remark<br>客户意见',
						width : 100,
						align : 'center',					
					},		
					{
						field : 'remark',
						title : ' Remark<br>审批意见',
						width : 100,
						align : 'center',
						editor:'text',					
					},
					] ],
			toolbar : [
//						           "-", {
//							text : 'Check',
//							iconCls : 'icon-view',
//							handler : function() {
//								check();
//								
//							}
//						} ,
			     "-", {
				text : 'Approve',
				iconCls : 'icon-ok',
				handler : function() {
					approve();
				}
			}, "-", {
				text : 'Reject',
				iconCls : 'icon-cancel',
				handler : function() {
					reject();
				}
			}, "-", {
				text : 'Escalation',
				iconCls : 'icon-up',
				handler : function() {
					escalation();
				}
			}
			 ],
			onClickCell: function (rowIndex, field, value) {
				var rows = $('#datagrid').datagrid('getRows');
				if(rows[rowIndex].state==0){					
					beginEditing(rowIndex, field, value);
					$('#datagrid').datagrid('beginEdit', rowIndex);
					setEditing(rowIndex); 					
				}
		     },
			onDblClickRow:function(rowIndex,rowData){
				initMaintWindow('Quote Detail','/quoteAction!toViewQuote.jspa?id='+rowData.main_id);
			},
			onSelect : function(rowIndex, rowData) {
				var state = rowData.state;
				 if(state=="0"){
					$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
					$('div.datagrid-toolbar a').eq(2).linkbutton('enable');
				}else{
					$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
					$('div.datagrid-toolbar a').eq(2).linkbutton('disable');
				}
			},
			rowStyler: function(index,row){
				var rows = $('#datagrid').datagrid('getRows');
				var quote_id=rows[0].quote_id;
				var flag=0;
				for(var i=0;i<rows.length;i++){
					if(rows[i].quote_id != quote_id){
						if(flag==0){
							flag=1;
						}else if(flag==1){
							flag=0;
						}
					}
					rows[i].flag=flag;
					quote_id=rows[i].quote_id;
				}
				if (row.flag==1){
					return 'background:#ffffcc;';
				}else{
					return 'background:#ffffff;';
				}
			}
		});
}


//计算行项目总价=订购数量*审批成本价
//客户利润=(审批resale*1-审批cost*1)/审批resale*1;;
//瑞能利润Mfr Margin= (Suggest Cost-物料成本)/ Suggest Cost
function setEditing(rowIndex){    
	var rows = $('#datagrid').datagrid('getRows');
	var regionalMin = rows[rowIndex].sale_price;
	var materialCost = rows[rowIndex].cost;
	var qty = rows[rowIndex].qty;
    var editors = $('#datagrid').datagrid('getEditors', rowIndex); 
    var weenCostEditor = editors[0]; 
    var weenResaleEditor = editors[1];   
    var cusProfitEditor = editors[2];
    var weenProfitEditor = editors[3]; 
    var amountEditor = editors[4];    
    weenCostEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }});  
    weenResaleEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    amountEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }});  
    weenProfitEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    cusProfitEditor.target.bind({'blur':function(){    
        calculate(rowIndex);    
    },'mouseleave':function(){
    	calculate(rowIndex);
    }}); 
    function calculate(rowIndex){  
    	var rows = $('#datagrid').datagrid('getRows');
    	var weenCost = weenCostEditor.target.val();
    	var weenResale = weenResaleEditor.target.val();
        var amount = weenCost*qty;  
    	var weenProfit = ((weenCost-materialCost)*1.0/weenCost*1.0)*100;
        var cusProfit = ((weenResale*1.00-weenCost*1.00)/weenResale*1.00)*100;
        weenProfit = weenProfit.toFixed(2);
        cusProfit = cusProfit.toFixed(2);
//        if(weenCost*1.0 < regionalMin*1.0){      	
//        	$(costEditor.target).numberbox('setValue','0'); 
//        }else{
//        	$(weenProfitEditor.target).val(weenProfit+'%');   
//         	$(cusProfitEditor.target).val(cusProfit+'%'); 
//         	$(amountEditor.target).val(amount);          	
//        }  
        if(weenResale*1<weenCost*1){
      	  $(weenResaleEditor.target).val(""); 
        }
    	$(weenProfitEditor.target).val(weenProfit+'%');   
     	$(cusProfitEditor.target).val(cusProfit+'%'); 
     	$(amountEditor.target).val(amount);   
//     	rows[rowIndex].amount=amount;
        if(weenProfit=='NaN'||weenProfit=='-Infinity'){
      	  $(weenProfitEditor.target).val(""); 
        }
        if(cusProfit=='NaN'||cusProfit=='-Infinity'){
        	  $(cusProfitEditor.target).val(""); 
         }
    }  
}    
//

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.quote_id = $("#quote_id").val();
	queryParams.customer_id = $("#customer_id").combobox('getValue');
	queryParams.endCustomer_id = $("#endCustomer_id").combobox('getValue');
	queryParams.project_name = encodeURIComponent($("#project_name").val());
	//queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}


function approve() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);
	} 	
	var rows = $('#datagrid').datagrid('getSelections');
	var quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){
		if(rows[i].state!=0){//只能处理待审批状态的
			continue;
		}
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].suggest_resale==0) {
			$.messager.alert('Tips', "审批销售价未填或者格式不正确！", 'error');
			return;
		}
		if (rows[i].suggest_cost==0) {
			$.messager.alert('Tips', "审批进货价未填或者格式不正确！", 'error');
			return;
		}
	    if(rows[i].suggest_cost*1.0 < rows[i].sale_price*1.0){      	
				$.messager.alert('Tips', "审批进货价不得低于区域最低价！", 'error');
				return;
	    }
	    if(rows[i].sale_price*1==0||rows[i].sale_price==undefined){      	
			$.messager.alert('Tips', "Please Contact Price Administrator to upload price！", 'error');
			return;
	    }
		var remark = rows[i].remark ==undefined?'':rows[i].remark; 
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark+"',remark:'"+remark+"',state:'3'}";
		
		quoteDetailJson.push(row);				
	}
	  $.messager.confirm('Confirm', '请核对信息,确定提交?', function(r) {
		if(r){		
			$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
			var form = window.document.forms[0];
			form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}

function reject() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);
	} 	
	
	var rows = $("#datagrid").datagrid("getSelections");					
	
	var quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){
		if(rows[i].state!=0){
			continue;
		}
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//		if (rows[i].suggest_resale==0) {
//			$.messager.alert('Tips', "审批销售价未填或者格式不正确！", 'error');
//			return;
//		}
//		if (rows[i].suggest_cost==0) {
//			$.messager.alert('Tips', "审批进货价未填或者格式不正确！", 'error');
//			return;
//		}
		var remark = rows[i].remark ==undefined?'':rows[i].remark; 
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark
		+"',remark:'"+remark+"',state:'6'}";
		
		quoteDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', '请核对信息,确定提交?', function(r) {
		if(r){						
			$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
			var form = window.document.forms[0];
			form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}
 function escalation(){
		var rows1 = $('#datagrid').datagrid('getRows');
		for(var i=0;i<rows1.length;i++){
			$("#datagrid").datagrid("endEdit",i);
		} 	
		
		var rows = $("#datagrid").datagrid("getSelections");					
		
		var quoteDetailJson=[];
		for(var i=0;i<rows.length;i++){
			if(rows[i].state!=0){
				continue;
			}
			row_no = (i*1+1)*10;
			var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//			if (rows[i].suggest_resale==0) {
//				$.messager.alert('Tips', "审批销售价未填或者格式不正确！", 'error');
//				return;
//			}
//			if (rows[i].suggest_cost==0) {
//				$.messager.alert('Tips', "审批进货价未填或者格式不正确！", 'error');
//				return;
//			}
			var remark = rows[i].remark ==undefined?'':rows[i].remark; 
			var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
			+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
			+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
			+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
			+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
			+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(rows[i].profits_percent)
			+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
			+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark
			+"',remark:'"+remark+"',state:'1'}";
			
			quoteDetailJson.push(row);				
		}

		  $.messager.confirm('Confirm', '请核对信息,确定提交?', function(r) {
			if(r){						
				$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
				var form = window.document.forms[0];
				form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
				form.target = "hideFrame";
				form.submit();			  						
			}
		});	
 }
var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
         }
    }
}
function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
 }


function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', '未选中数据！', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/quoteAction!toViewQuote.jspa?id='+rows[0].id);
	}	
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
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}



//代理商客户
$('#customer_id').combogrid({
	panelHeight : 280,
	panelWidth : 350,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa?customer_code='+$('#customer_id').val(),
	idField : 'customer_code',
	textField : 'customer_name',
	columns : [[{
				field : 'customer_code',
				title : '客户编码',
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
				title : '客户名称',
				width : 250
			}]],
			toolbar : '#toolbarCustomer',
			onSelect : function(index, record){
		 	},
});
function searcherCustomer(name1) {
	var queryParams = $('#customer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.customer_name = encodeURIComponent(name1);
	$('#customer_id').combogrid("grid").datagrid('reload');
} 
//终端客户
$('#endCustomer_id').combogrid({
	panelHeight : 280,
	panelWidth : 330,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_groupId',
				title : '客户集团',
				width : 100
			}, {
				field : 'end_customer_name',
				title : '客户名称',
				width : 200
			}]],
			toolbar : '#toolbarEndCustomer',
			onSelect : function(index, record) {
			},
});
function searcherEndCustomer(name1) {
	var queryParams = $('#endCustomer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#endCustomer_id').combogrid("grid").datagrid('reload');
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}	  