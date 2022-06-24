$(document).ready(function() {
	if($('#state').val()==0){
		$('#state').val('Pendding');
	}else if($('#state').val()==1){
		$('#state').val('Approved');
	}else if($('#state').val()==2){
		$('#state').val('Reject');
	}
 	$('#hideFrame').bind('load', promgtMsg);
});
function cancel() {
	window.parent.closeMaintWindow();
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
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


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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



//代理商客户
$('#customer_id').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
	idField : 'global_account',
	textField : 'customer_name',
	columns : [[{
				field : 'global_account',
				title : 'Code',
				width : 100
			}, {
				field : 'customer_name',
				title : 'Name',
				width : 120
			}]],
});

//终端客户
$('#endCustomer_id').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa',
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_id',
				title : 'Code',
				width : 100
			}, {
				field : 'end_customer_name',
				title : 'Name',
				width : 120
			}]],
});
//货币
$('#currency_code').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeValue=dictTypeId=548',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 153,
	onSelect : function(r){
 	} 
});


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
 