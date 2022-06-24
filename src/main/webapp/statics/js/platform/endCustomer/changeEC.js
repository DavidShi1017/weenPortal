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
	var newec_id = $('#ec_id').val();
	var oldec_id = $('#oldEc_id').val();
	if (newec_id == oldec_id){
		$.messager.alert("info","Please don't choose yourself change");
		return
	}
 	var id =$('#id').val();
 	var form = window.document.forms[0];
 	form.action = appUrl + "/endCustomerAction!changeEC.jspa";
	form.target = "hideFrame";
	form.submit();
}



var pcFirst = true;

$('#ec_name1').combogrid({
	panelHeight : 370,
	panelWidth : 480,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	queryParams:{
		end_customer_name : encodeURIComponent($("#ec_name1").val()),
	},
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_groupId',
				title : 'Customer Group',
				width : 100
			}, {
				field : 'end_customer_name',
				title : 'Customer Name',
				width : 200
			}, {
				field : 'country',
				title : 'Country',
				width : 70
			}, {
				field : 'city',
				title : 'city',
				width : 80
			}]],
			onSelect : function(index, record) {
				$('#ec_group').val(record.end_customer_groupId);
				$('#ec_id').val(record.end_customer_id);
				$('#ec_name').val(record.end_customer_name);
				$('#ec_city').val(record.city);
			},
			onLoadSuccess: function(data) {
				if(pcFirst){					
					$('#ec_name1').combogrid('grid').datagrid('getPager').pagination("select", 1);
					pcFirst=false;
				}
			},
			toolbar : '#toolbarPurchaseCustomer',
});
var pcFirst = true;
function searcherPurchaseCustomer(name1) {
	pcFirst = true;
	var queryParams = $('#ec_name1').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#ec_name1').combogrid("grid").datagrid('reload');
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
        message:'字段不匹配'
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