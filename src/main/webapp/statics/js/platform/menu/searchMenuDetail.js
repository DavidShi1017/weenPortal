$(document)
		.ready(
				function() {
					// 下拉框赋值
					$('#pname').combobox('setValue', pname);
					$('#target').combobox('setValue', targetValue);
					$('#hideFrame').bind('load', promgtMsg);
					$('#pname')
							.combobox(
									{
										textField : 'pname',
										valueField : 'pname',
										onChange : function(newValue, oldValue) {
											if (newValue != null) {
												var urlStr = appUrl
														+ "/menuAction!blurSearchMenu.jspa?pname="
														+ encodeURIComponent(newValue);
												$("#pname").combobox("reload",
														urlStr);
											}
										},
										onSelect : function(r) {
											$("#pid").val(r.pid);
										}
									});
				});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (successResult) {
		close();
		window.parent.location.reload();
	} else {
		$.messager.alert('Tips', failResult, 'warning');

	}
}

function submit() {

	var p = $("#pname").combobox('isValid');
	var n = $("#name").validatebox('isValid');
	var t = $("#target").combobox('isValid');
	var o = $("#orderBy").numberspinner('isValid');
	if (!(p && n && o && t)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/menuAction!updateMenu.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintMenu();
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