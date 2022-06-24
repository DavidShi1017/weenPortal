$(document).ready(function() {
	var str =$('#material_id').val();
	str = $('#material_id').val().substring(str.length-12, str.length);
	$('#material_id').val(str);
 	$('#hideFrame').bind('load', promgtMsg);
	 
});

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    /*var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();*/
    
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;

}


//货币
$('#currency_code').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:20,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId=548',
	idField : 'itemValue',
	textField : 'itemName',
	columns : [[{
				field : 'itemValue',
				title : '货币代码',
				width : 100
			}, {
				field : 'itemName',
				title : '货币名称',
				width : 120
			}]],
});
//$('#currency_code').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeValue=currency',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	required : true,
//	panelHeight : 120,
//	width : 153,
//	onSelect : function(r){
// 	} 
//});
// 返回信息
function promgtMsg() {
	 
	var hideFrame = document.getElementById("hideFrameApp");
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
			week["Mon"] = "一";
			week["Tue"] = "二";
			week["Wed"] = "三";
			week["Thu"] = "四";
			week["Fri"] = "五";
			week["Sat"] = "六";
			week["Sun"] = "日";

			str = utcCurrTime.split(" ");
			date = str[5] + "-";
			date = date + month[str[1]] + "-" + str[2] + " " + str[3];
			return date;
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