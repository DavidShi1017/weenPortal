$(document).ready(function() {
			loadGrid();
 			$('#hideFrame').bind('load', promgtMsg);
		});
function cancel() {
	window.parent.closeMaintWindow();
}
function loadGrid() {
	$('#datagrid').datagrid({   
		iconCls : 'icon-list',
		title : '订单明细',
		url : appUrl +  '/enquiryAction!getEnquiryDetailList.jspa?enquiry_id='+$('#enquiry_id').val(),
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		nowrap : true,
		checkbox : true,
	 	required : true,
		rownumbers : true,
		height:200,
		fitColumns:false,
		striped : true,
	     columns : [[{
	 		field : 'ck',
	 		align : 'center',
	 		checkbox : true
	 	},{
	 			field : 'id',
	 			title : '主键',
	 			width : 60,
	 			align : 'center',
	 			hidden:true
	 		},
	 		{
	 			field : 'material_name',
	 			title : 'BookPart物料名称',
	 			width : 150,
	 			align : 'center',
	 			hidden:true
	 		},
	 		{
	 			field : 'material_id',
	 			title : '12NC物料编码',
	 			width : 150,
	 			align : 'center',
	 		},
	 		{
	 			field : 'drNum',
	 			title : 'DR Number项目注册的编码',
	 			width : 60,
	 			align : 'center',
	 			
	 		},

	 		{
	 			field : 'qty',
	 			title : 'QTY订购数量',
	 			width : 100,
	 			align : 'center',
	 		} ,
	 		{
	 			field : 'target_resale',
	 			title : 'Target Resale目标销售价格',
	 			width : 100,
	 			align : 'center',	
	 		} ,
	 		{
	 			field : 'target_cost',
	 			title : 'Target Cost 目标进货价格', 
	 			width : 100,
	 			align : 'center',	
	 		} ,
	 		{
	 			field : 'amount',
	 			title : 'Value 行项目总价',
	 			width : 60,
	 			align : 'center',				
	 		} ,
	 		{
	 			field : 'reason',
	 			title : 'Justification申请原因', 
	 			width : 60,
	 			align : 'center',	

	 		} ,
	 		{
	 			field : 'competitor',
	 			title : 'Competitor竞争对手',
	 			width : 150,
	 			align : 'center',
	 		},
	 		{
	 			field : 'start_date',
	 			title : 'Start of Production生产启动日期',
	 			width : 150,
	 			align : 'center',
				formatter : function(date){
					return utcToDate(date);
				}
	 		},		
	 		{
	 			field : 'cus_remark',
	 			title : 'Cus Remark客户意见',
	 			width : 100,
	 			align : 'center',				
	 		}]],
	}); 
 
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	
	$("#datagrid").datagrid('load');
}

function closeMain() {
	$("#hiddenWin").window('close');
}

//function dele(){
//	
//	var ids = '(';
//	var rows = $('#priceinfo_list').datagrid('getSelections');
//	for ( var i = 0; i < rows.length; i++) {
//		if(i==rows.length-1){
//			ids += rows[i].id+')';
//		}else{
//			ids += rows[i].id+',';
//		}
//	}
// 	if (ids == '') {
//		$.messager.alert('Tips', '未选中数据！', 'warning');
//		return;
//	} else {
//		var form = window.document.forms[0];
//		form.action = appUrl + '/priceInfoAction!updatePriceInfoState.jspa?ids='+ ids+'&state=5';
//		form.target = "hideFrame";
//		form.submit();
//	}	
//}

//货币
$('#currency_code').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId=548',
	idField : 'itemValue',
	textField : 'itemName',
	columns : [[{
				field : 'itemValue',
				title : '货币代码',
				width : 100
			}, {
				field : 'itemName',
				title : '货币名称',
				width : 120
			}]],
});
//$('#currency_code').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeValue=currency',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	required : true,
//	panelHeight : 120,
//	width : 153,
//	onSelect : function(r){
// 	} 
//});
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
 