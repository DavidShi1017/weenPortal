$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});
//var datass = [{ "id":"0", "text":"Pendding" },{ "id":"1", "text":"Approved" },{ "id":"2", "text":"Reject" },{ "id":"9", "text":"Deleted" }];
//$('#state').combobox({    
//	data:datass,    
//    valueField:'id',    
//    textField:'text',
//    editable:false,
//    panelHeight:'auto',
//    onLoadSuccess:function(data){
//    	if($('#state').combobox('getValue')==''){
//    		$('#state').combobox('setValue','0');
//    	}
//    }
//}); 


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}


$("#ecGroup_name").blur(function(){
 	$("#ecGroup_id").val($.trim($("#ecGroup_name").val()));
});
function submit() {
 	var id =$('#id').val();
 	if ($("#ecGroup_name").val()=="") {
		$.messager.alert('Tips', "Group Name is not completed yet！", 'error');  
		return;
	}
 	var form = window.document.forms[0];
 	if(id==0){
 		form.action = appUrl + "/groupInfoAction!addCodeType.jspa";
  	}else{
 		form.action = appUrl + "/groupInfoAction!updateCodeType.jspa";
   	}
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
}

function cancel() {
	window.parent.closeMaintWindow();
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});



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