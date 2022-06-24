$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
$('#clearV').click(function(){
	$('#currency_code').combobox('setValue','');
	$('#price_region').combobox('setValue','');
	//$('#customer_id').combogrid('clear');
//	$('#start_dateStr').datebox('setValue',"");
//	$('#end_dateStr').datebox('setValue',"");
	setTimeout("search()",100);	
});
function loadGrid() {
	$('#datagrid').datagrid(
		{
			iconCls : 'icon-list',
			title : '',
			//height : 370,
			fit:true,
			striped : true,
			url : appUrl+ '/quote/quoteAction!getOutQuotePrice.jspa',
	        loadMsg : 'Loading...',
			singleSelect : true,
			nowrap : true,
			// idField : 'id',
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
							width : 80,
							hidden : true,
							align : 'center'
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
							align : 'center',
							hidden:true,
						},
						{
							title : "Disti Name",
							field : 'cusGroup_id',
							width : 150,
							align : 'left'
						},
						{
							title : '12NC',
							field : 'material_id',
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
							title : 'BookPart',
							field : 'material_name',
							width : 150,
							align : 'left',
							hidden:true
						},						
						{
							title : "Region",
							field : 'price_region',
							width : 80,
							align : 'center',
						},
					
						{
							title : "Disti Region",
							field : 'disti_region',
							width : 80,
							align : 'center',
							hidden:true
						},				
					{
						title : "Currency",
						field : 'currency_code',
						width : 90,
						align : 'center'
					},
					{
						title : 'Quote Price',
						field : 'suggest_cost',
						width : 90,
						align : 'center',	
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
						title : 'USD Price',
						field : 'suggest_cost_usd',
						width : 90,
						align : 'center',	
						formatter : function(value, row, rec) {
							var flag = row.suggest_cost_usd;
							if (flag == 0||flag == ''||flag==undefined) {
								return flag;
							} else{
								flag=JSON.parse(flag*1);
								return flag.toFixed(4);
							} 
						}
					} ,{
			 			title : 'Debit Start',
			 			field : 'debit_start',
			 			width : 100,
			 			align : 'center',
			 			formatter : function(date){
							if(date==undefined){
								return "";										
							}else{
								return utcToDate(date);	
							}
						},
						hidden:true
			 		},{
			 			title : 'Debit Expire',
			 			field : 'debit_endStr',
			 			width : 100,
			 			align : 'center',
//			 			formatter : function(date){
//							if(date==undefined){
//								return "";										
//							}else{
//								return utcToDate(date);	
//							}
//						}
			 		}
					] ],
			toolbar : ["-", {
						text : 'Output',
						iconCls : 'icon-outport',
						handler : function() {
							downLoad();
						}
					}
			 ],
		});
}

function downLoad(){
	var url = '/quoteAction!downloadQuotePrice.jspa';
	$.messager.confirm('Confirm', 'Confirmed about  OutPort?', function(r) {
		if(r){
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			
		}
	});
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.cusGroup_id = $("#cusGroup_id").val();
	queryParams.currency_code = $('#currency_code').combobox('getValue');
	queryParams.price_region = $("#price_region").combobox('getValue');
	queryParams.material_id = $("#material_id").val();
//	queryParams.material_name = $("#material_name").val();
//	queryParams.quote_id = $("#quote_id").val();
//	queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
//	queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
	$("#datagrid").datagrid('load');
}
//组织
$('#price_region').combobox({
	url : appUrl + '/priceRuleAction!getOrgLists.jspa?state=2',
	valueField : 'orgName',
	textField : 'orgName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});
//货币
$('#currency_code').combobox({
	url : appUrl+'/designRegAction!getDictOfWeen.jspa?dictTypeId=548',
		//	+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});

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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}	  