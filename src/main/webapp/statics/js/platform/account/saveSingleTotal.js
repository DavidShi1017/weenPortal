$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	if(!($('#eventId').validatebox('isValid'))) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/account/accountAction!saveSingleTotal.jspa";
	form.submit();
	
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
		});
	}
}