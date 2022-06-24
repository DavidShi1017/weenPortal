$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});


//!֯
$('#pricing_region').combobox({
	url : appUrl + '/priceRuleAction!getOrgLists.jspa?state=2',
	valueField : 'sapOrgId',
	textField : 'orgName',
	multiple : false,
	editable : false,
	panelHeight : 'auto',
	width : 170,
	onSelect : function(r){
    	if(r.orgName=="EU"){
    		$('#currency').val("EUR");
    		
    	}else {
    		$('#currency').val("USD");
    	}
 	} 
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


function submit() {
 	var id =$('#id').val(); 	
 	if ($("#disti_name").combobox('getValue')=="") {
 		$.messager.alert('Tips', "Disti Name is not completed yet!", 'error');  
 		return;
 	}
 	if ($("#payer_to").val()=="") {
 		$.messager.alert('Tips', "Paycode is not completed yet!", 'error');  
 		return;
 	}
 	if ($("#disti_branch").val()=="") {
 		$.messager.alert('Tips', "Disti Branch is not completed yet!", 'error');  
 		return;
 	}
 	if ($("#branch_code").val()=="") {
 		$.messager.alert('Tips', "Branch Code is not completed yet!", 'error');  
 		return;
 	}
 	if ($("#pricing_region").combobox('getValue')=="") {
 		$.messager.alert('Tips', "Region is not completed yet!", 'error');  
 		return;
 	}

 	if ($("#currency").val()=="") {
		$.messager.alert('Tips', "Currency Code is not completed yet!", 'error');  
		return;
	}
 	var form = window.document.forms[0];
 	form.action = appUrl + "/customerAction!updateDistiBranch.jspa";
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
}
$('#disti_name').combogrid({
	panelHeight : 280,
	panelWidth : 300,
	pagination : true,
	pageSize:10,
	multiple : false,
	//editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getDistiNameList.jspa',
	queryParams:{
		disti_name : encodeURIComponent($("#disti_name").val()),
	},
	idField : 'disti_name',
	textField :'disti_name',
	columns : [[{
				field : 'disti_name',
				title : 'Disti Name',
				width : 200
			}, {
				field : 'payer_to',
				title : 'Payer To',
				width : 100
			}]],
			onSelect : function(index, record) {
				$('#payer_to').val(record.payer_to);
			},
			toolbar : '#toolbarDistiBranch',
});
function searcherDistiBranch(name1) {
	var queryParams = $('#disti_name').combogrid("grid").datagrid('options').queryParams;
	queryParams.disti_name = encodeURIComponent(name1);
	$('#disti_name').combogrid("grid").datagrid('reload');
} 



function cancel() {
	window.parent.closeMaintWindow();
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*!!�ĳ!�ֶ!!*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ!'
    }
});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
	     // !!!�textarea�ڲ�ִ!�κβ!�
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // !!�readOnly!!disable!ִ!�κβ!�
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};   