$(document).ready(function() {
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

//组织
function selectOrgTree(){
	initMaintWindowForOrg('Org Tree', '/orgAction!orgTreePage.jspa');
}

function closeOrgTree() {
	$("#hiddenWin").window('close');
}
function returnValue(selectedId, selectedName){
	document.getElementById('sold_to').value =selectedId;
	document.getElementById('disti_name').value= selectedName;
}
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,///
						closable : true,//
						left: 20,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}


function submit() {
	if ($("#sold_to").val()=="") {
		$.messager.alert('Tips', "Sold To is empty!", 'error');  
		return;
	}
	if ($("#disti_name").val()=="") {
		$.messager.alert('Tips', "Disti name is empty!", 'error');  
		return;
	}
 	var form = window.document.forms[0];
 	form.action = appUrl + "/ediDistiAction!createEdiDisti.jspa";
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