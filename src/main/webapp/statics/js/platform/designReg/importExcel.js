$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}else {
		$.messager.alert('Tips', "Success!", 'info', function(){
 			window.parent.returnUploadFile(successResult, $('#upload').val());
			windowclose();
 		});
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function windowclose(){
	window.parent.closeMaintWindow();	
}

function closeMaintFrame(){
	$("#maintFrame").window('close');
}


function importFile(){
  		var epath = $('#upload').val();
 		if(epath==""){
			$.messager.alert('Tips', 'Please select file to upload!', 'warning');
			return;
		}
 		$('#uploadFileName').val(epath);
 		$.messager.progress({
 			text:'Waiting'
		});
 		var form = window.document.forms[0];
		form.action = appUrl + "/file/fileAction!uploadImage.jspa";
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