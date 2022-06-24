$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	}else {
		$.messager.alert('Tips', successResult, 'info', function(){
			//window.parent.returnUploadExsl(successResult, $('#uploadFile').val());
			window.parent.closeMaintWindow();
			window.parent.search();
 		});
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function importData(){
 		var epath = $('#uploadFile').val();
 		if(epath==""){
			$.messager.alert('Tips', 'Please select file to upload!', 'warning');
			return;
		}
		
 		
//		if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
//			$.messager.alert('Tips', '03���ϰ汾Excel�����ݲ�֧��!', 'warning');
//			return;
//		}
//		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
//			$.messager.alert('Tips', '�����ļ����ͱ���Ϊexcel!', 'warning');
//			return;
//		}
 		$.messager.progress({
            text:''
		});
		var form = window.document.forms[0];
		form.action = appUrl + "/priceRuleAction!findOrderExcel.jspa?path="+encodeURIComponent($('#uploadFile').val());
		form.submit();
}

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