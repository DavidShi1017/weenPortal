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
 	var id =$('#id').val();
 	if ($("#ec_name").val()=="") {
 		$.messager.alert('Tips', "Customer Name is not completed yet!", 'error');  
 		return;
 	}
// 	if ($("#ec_city").val()=="") {
// 		$.messager.alert('Tips', "EC!City is not completed yet!", 'error');  
// 		return;
// 	}
 	if ($("ec_alias_name").val()=="") {
 		$.messager.alert('Tips', "Alias Name is not completed yet!", 'error');  
 		return;
 	}
 	if ($("#alias_city").val()=="") {
		$.messager.alert('Tips', "Alias City is not completed yet!", 'error');  
		return;
	}
 	var form = window.document.forms[0];
 	if(id==0){
 		form.action = appUrl + "/endCustomerAction!addECAlias.jspa";
  	}else{
 		form.action = appUrl + "/endCustomerAction!updateECAlias.jspa";
   	}
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
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
        message:'字段不匹配'
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