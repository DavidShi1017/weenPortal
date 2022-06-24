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
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}

//�����̿ͻ�
//$('#customer_code').combogrid({
//	panelHeight : 300,
//	panelWidth : 320,
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
//				title : '�ͻ�����',
//				width : 80,
//				formatter : function(value, row, rec) {
//					var flag = row.customer_code;
//					if (flag == ''||flag==undefined) {
//						return "";
//					} else{
//						var str = $.trim(flag);
//						str = str.substring(str.length-6, str.length);
//						return str;
//					} 
//				},
//				hidden:true
//			}, {
//				field : 'customer_name',
//				title : '',
//				width : 300
//			}]],
//			toolbar : '#toolbarCustomer',
//			onSelect : function(index, record){
//		 	}
//});
//function searcherCustomer(name1) {
//	var queryParams = $('#customer_code').combogrid("grid").datagrid('options').queryParams;
//	queryParams.customer_name = encodeURIComponent(name1);
//	$('#customer_code').combogrid("grid").datagrid('reload');
//} 


function submit() {
// 	var form = window.document.forms[0];
// 	form.action = appUrl + "/kunnrsAction!getKunnrListFromSAP.jspa?customer_code="+$('#customer_code').val()+"&sales_org="+$('#sales_org').val();
//	form.target = "hideFrame";
//	form.submit();
	
	var win = $.messager.progress({
		title:'Please waiting',
		msg:'synchronous data...'
		});
	
	$.ajax({
		type : "post",
		url :  appUrl + '/kunnrsAction!getKunnrListFromSAP.jspa',
		data : {
			customer_code : $("#customer_code").val(),
			sales_org: $("#sales_org").val()
		},
		success : function(r) {
			$.messager.progress('close');
			$.messager.alert('Tips', "Synchronous Success", 'info');
		},
		error : function(r) {
			$.messager.progress('close');
			$.messager.alert('Tips', "Synchronous Failure", 'info');
		}
	});
}

function close() {
	window.parent.closeMaintWindow();
}

function cancel() {
	window.parent.closeMaintWindow();
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*�����ĳ���ֶ����*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ��'
    }
});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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