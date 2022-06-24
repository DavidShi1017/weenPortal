$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	var p = $("#loginId").val();
	if (!(p)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction1!sendTenderMail.jspa";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		document.getElementById("warn_meg_f").style.display = "block";
	} else if (successResult) {
		document.getElementById("warn_meg_s").style.display = "block";
	}
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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