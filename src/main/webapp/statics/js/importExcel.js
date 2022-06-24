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
		alert(1+"www"+successResult);
		var odList = eval(successResult);
//		for(var i=0,i<odList.length,i++){
//			var od = successResult[i];
//		}
		window.parent.returnOrderDetail(successResult);
//		$.messager.alert('Tips', successResult, 'info', function(){
//			
//			window.parent.closeMaintWindow();
//			window.parent.search();		
//		});
	}
}

function search() {
	$("#datagrid").datagrid('load');
}

function importData(){
 		var epath = $('#uploadFile').val();
 		if(epath==""){
			$.messager.alert('Tips', '导入文件不能为空!', 'warning');
			return;
		}
		
		/*if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
			$.messager.alert('Tips', '03以上版本Excel导入暂不支持!', 'warning');
			return;
		}
		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
			$.messager.alert('Tips', '导入文件类型必须为excel!', 'warning');
			return;
		}*/
		$.messager.progress();
		var form = window.document.forms[0];
		form.action = appUrl + "/orderAction!findOrderExcel.jspa?path="+$('#uploadFile').val();
		form.submit();
}