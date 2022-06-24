$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function save_dict() {
	var p = $("#itemName").validatebox('isValid');
	var t = $("#itemValue").validatebox('isValid');
	if (!(p && t)) {
		return;
	}
	if ($("#dictTypeId").val() == "") {
		$.messager.alert('Tips', '请先选择字典类型并点击【查看字典条目】', 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/dictAction/dictAction!CreateDict.jspa";
	form.target = "hideFrame";
	form.submit();
}

function cencel() {
	window.parent.closeMaintWindow();
}

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
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
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