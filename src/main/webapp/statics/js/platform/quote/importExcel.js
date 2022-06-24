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
		$.messager.alert('Tips', "Success!", 'warning');
		var qdList = eval(successResult);
//		for(var i=0,i<odList.length,i++){
//			var od = successResult[i];
//		}
		window.parent.returnQuoteDetail(successResult);
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
			$.messager.alert('Tips', 'Please select file to upload!', 'warning');
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
 		$.messager.progress({
            text:'Waiting'
		});
		var form = window.document.forms[0];
		form.action = appUrl + "/quoteAction!findOrderExcel.jspa?path="+encodeURIComponent($('#uploadFile').val())+'&currency_code='+$('#currency_code').val()+'&office_id='+$('#office_id').val()+'&customer_id='+$('#customer_id').val();
		form.submit();
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