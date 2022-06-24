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



function submit() {
//	var form = window.document.forms[0];
//	form.action = appUrl + '/materialsAction!getMaterialFromSAP.jspa?material_id='+$("#material_id").val();
//	form.target = "hideFrame";
//	form.submit();
	var win = $.messager.progress({
		title:'Please waiting',
		msg:'synchronous data...'
		});
	
	$.ajax({
		type : "post",
		url :  appUrl + '/materialsAction!getMaterialFromSAP.jspa',
		data : {
			material_id : $("#material_id").val()
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