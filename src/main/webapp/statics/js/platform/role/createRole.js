$(document).ready(function() {
	// 下拉框赋值
	$('#target').combobox('setValue', targetValue);
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
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search();
});
		
	}else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
	}
}

function submit() {
	var p = $("#roleId").validatebox('isValid');
	var n = $("#roleName").validatebox('isValid');
	var t = $("#ownFlag").combobox('isValid');
	var q = $("#roleType").combobox('isValid');
	if (!(p && n && t && q)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/roleAction!createRole.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintRole();
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
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
