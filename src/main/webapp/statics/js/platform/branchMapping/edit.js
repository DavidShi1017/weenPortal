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
	} else {
 		$.messager.alert('Tips', successResult, 'info', function() {
 			window.parent.closeMaintWindow();
			window.parent.search();
		 });
	}
}

function submit() {		
	var url=undefined;
	if($('#id').val()=='0'||$('#id').val()==''){
		 url="/countryAction!createBranchMapping.jspa";
	}else{
		 url="/countryAction!updateBranchMapping.jspa";
	}
	
	  $.messager.confirm('Confirm', 'Please check the information to determine the submission!', function(r) {
		if(r){						
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}



function cancel(){
	window.parent.closeMaintWindow();
}
	  
	  function utcToDate(utcCurrTime) {
			utcCurrTime = utcCurrTime + "";
			var date = "";
			var month = new Array();
			month["Jan"] = 1;
			month["Feb"] = 2;
			month["Mar"] = 3;
			month["Apr"] = 4;
			month["May"] = 5;
			month["Jun"] = 6;
			month["Jul"] = 7;
			month["Aug"] = 8;
			month["Sep"] = 9;
			month["Oct"] = 10;
			month["Nov"] = 11;
			month["Dec"] = 12;
			var week = new Array();
			week["Mon"] = "һ";
			week["Tue"] = "��";
			week["Wed"] = "��";
			week["Thu"] = "��";
			week["Fri"] = "��";
			week["Sat"] = "��";
			week["Sun"] = "��";

			str = utcCurrTime.split(" ");
			date = str[5] + "-";
			date = date + month[str[1]] + "-" + str[2] + " " + str[3];
			return date;
		}	  
	  
	  document.onkeydown = function(e) {
			var theEvent = e || window.event;
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
			if(event.keyCode == 8) {
			     // �������textarea�ڲ�ִ���κβ���
			  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
			            event.returnValue = false; 
			        // �����readOnly����disable��ִ���κβ���
			  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
			            event.returnValue = false;                             
			}
			return true;
		};	    