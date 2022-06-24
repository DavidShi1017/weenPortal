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
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}

//国家
var cFirst = true;
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
	queryParams:{
		search:$('#country').val()
	},
	idField : 'country_code',
	textField : 'country_name',
	columns : [[{
				field : 'country_code',
				title : 'Country Code',
				width : 100
			}, {
				field : 'country_name',
				title : 'Country Name',
				width : 120
			}]],
			onSelect:function(index, record){
			},
			onLoadSuccess: function(data) {
				if(cFirst){					
					$('#country').combogrid('grid').datagrid('getPager').pagination("select", 1);
					cFirst=false;
				}
			},
			toolbar : '#toolbarCountry',
});
function searcherCountry(name1) {
	cFirst = true;
	var queryParams = $('#country').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#country').combogrid("grid").datagrid('reload');
} 

function submit() {
 	var id =$('#id').val();
 	if ($("#country").combobox('getValue')=="") {
 		$.messager.alert('Tips', "Country Code is not completed yet！", 'error');  
 		return;
 	}
 	if ($("#province_code").val()=="") {
		$.messager.alert('Tips', "Province Code is not completed yet！", 'error');  
		return;
	}
 	if ($("#province_name").val()=="") {
		$.messager.alert('Tips', "Province Name is not completed yet！", 'error');  
		return;
	}
 	var form = window.document.forms[0];
 	if(id==0){
 		form.action = appUrl + "/provinceAction!addProvince.jspa";
  	}else{
 		form.action = appUrl + "/provinceAction!updateProvince.jspa";
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