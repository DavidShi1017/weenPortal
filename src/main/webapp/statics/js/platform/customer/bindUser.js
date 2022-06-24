$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}


function submit() {
 	if ($("#loginId").val()=="") {
 		$.messager.alert('Tips', "Login Id is not completed yet！", 'error');  
 		return;
 	}
 	if ($("#userName").val()=="") {
 		$.messager.alert('Tips', "User　Name is not completed yet！", 'error');  
 		return;
 	}
 	if ($("#customer_id").val()=="") {
 		$.messager.alert('Tips', "Customer　Code is not completed yet！", 'error');  
 		return;
 	}
 	if ($("customer_name").val()=="") {
 		$.messager.alert('Tips', "Customer Name is not completed yet！", 'error');  
 		return;
 	}

 	var form = window.document.forms[0];
 	form.action = appUrl + "/customerAction!bindDistiToThisUser.jspa";
	form.target = "hideFrame";
	form.submit();
}

//代理商客户
$('#customer_code').combogrid({
	panelHeight : 280,
	panelWidth : 400,
	width:400,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
	idField : 'customer_code',
	textField : 'customer_code',
	columns : [[{
				field : 'customer_code',
				title : 'Code',
				width : 80,
				formatter : function(value, row, rec) {
					var str=(row.customer_code).replace( /^0*/, '');
					return str;
				}
			}, {
				field : 'customer_name',
				title : 'CustomerName',
				width : 220
			},
			{
				field : 'country',
				title : 'city/country',
				width : 100,
				//hidden:true
			}]],
			toolbar : '#toolbarCustomer',
	onSelect : function(index, record) {
		$('#customer_name').val(record.customer_name);
	}
});
function searcherCustomer(name1) {
	var queryParams = $('#customer_code').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#customer_code').combogrid("grid").datagrid('reload');
} 

function close() {
	window.parent.closeMaintWindow();
}

function cancel() {
	window.parent.closeMaintWindow();
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});
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