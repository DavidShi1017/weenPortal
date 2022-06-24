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
 		var paths = epath.split("\\");
 		var path =paths[paths.length-1];
 		$('#file_name').val(path);
 		if(epath==""){
			$.messager.alert('Tips', 'Please select file to upload!', 'warning');
			return;
		}
		
//		if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
//			$.messager.alert('Tips', '03以上版本Excel导入暂不支持!', 'warning');
//			return;
//		}
//		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
//			$.messager.alert('Tips', '导入文件类型必须为excel!', 'warning');
//			return;
//		}
 		$.messager.progress({
            title:'',
            msg:'Waiting...',
            text:'Waiting...'
    });
		var form = window.document.forms[0];
		form.action = appUrl + "/cusInventroyAction!findOrderExcel.jspa?path="+$('#uploadFile').val()+"&file_url="+$('#file_name').val();
		form.submit();
}