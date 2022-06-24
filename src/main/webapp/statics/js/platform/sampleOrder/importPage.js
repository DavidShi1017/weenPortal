$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
	}else {
		$.messager.alert('Tip', failResult, 'warning');
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function importData(){
 		var epath = $('#upload').val();
 		if(epath==""){
			$.messager.alert('Error', 'Please select file to upload!', 'warning');
			return;
		}
 		$('#uploadFileName').val(epath);
		/*if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
			$.messager.alert('Tips', '03���ϰ汾Excel�����ݲ�֧��!', 'warning');
			return;
		}
		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
			$.messager.alert('Tips', '�����ļ����ͱ���Ϊexcel!', 'warning');
			return;
		}*/
 		$.messager.progress({
 			text:'Waiting'
		});
		var form = window.document.forms[0];
		form.action = appUrl + "/sampleOrderAction!importPageHandler.jspa?path="+$('#upload').val();
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