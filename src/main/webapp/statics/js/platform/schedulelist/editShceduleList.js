$(document).ready(function() {
	$('#hideFrameDetail').bind('load', promgtMsg);
});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrameDetail");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
 		//$.messager.alert('提示', successResult, 'info', function() {
  			close();
 			window.parent.loadGrid();
 		//});
	}
}

function close() {
	window.parent.closeMaterial();
}

function submit() {
			$.messager.confirm('Confirm', '是否提交?', function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl + "/schedulelistAction!editShceduleList.jspa";
					form.target = "hideFrameDetail";
					form.submit();
				}});
}
