$(document).ready(function() {
	if($('#state').html()==0){
		$('#state').html('Pending');
	}else if($('#state').html()==1){
		$('#state').html('Approved');
	}else if($('#state').html()==2){
		$('#state').html('Reject');
	}else if($('#state').html()==3){
		$('#state').html('Freezed');
	}
	
	if($('#fileName').html()==""){
		$('#downImg').hide();
	}else{
		$('#downImg').show();
	}
	
 	$('#hideFrameApp').bind('load', promgtMsg);
	 
});

function downLoadFile(){
	if($('#file_path').val()==""){
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?file_path="+$('#file_path').val();
	form.target = "hideFrame";
	form.submit();	
}
//var datass = [{ "id":"0", "text":"Pending" },{  "id":"1", "text":"Approved" },{  "id":"2", "text":"Reject" }];
//$('#state').combobox({    
//	data:datass,    
//	    valueField:'id',    
//	    textField:'text'   
//	}); 


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

$('#disti_customer_id').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
	idField : 'global_account',
	textField : 'customer_name',
	columns : [[{
				field : 'global_account',
				title : 'Code',
				width : 100
			}, {
				field : 'customer_name',
				title : 'Name',
				width : 120
			}]],
});

$('#customer_type').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=546',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 153,
	onSelect : function(r){
	} 
});
$('#country').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl+ '/country/countryAction!getCountryList.jspa',
	idField : 'country_code',
	textField : 'country_name',
	columns : [[{
				field : 'country_code',
				title : 'Code',
				width : 100
			}, {
				field : 'country_name',
				title : 'Name',
				width : 120
			}]],
});
$('#currency_code').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 153,
	onSelect : function(r){
	} 
});
//ECGroup
$('#end_customer_groupId').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/ecgroup/groupInfoAction!getGroupInfoList.jspa?states=(1)',
	idField : 'ecGroup_id',
	textField : 'ecGroup_name',
	columns : [[{
				field : 'ecGroup_id',
				title : 'Code',
				width : 100
			}, {
				field : 'ecGroup_name',
				title : 'Name',
				width : 120
			}]],
});

function cancel(){
	window.parent.closeMaintWindow();
}

document.onkeydown = function(e) {
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
		if(event.srcElement.tagName.toLowerCase() != "input"
			&& event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password") {
		    event.returnValue = false; 
		}
		if(event.srcElement.readOnly == true || event.srcElement.disabled == true) {
			    event.returnValue = false;                             
		} 
	}
	return true;
};
