$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});
//---------------------------------------------------------------
//---------------------------------------------------------------
//---------------------------------------------------------------
//---------------------------------------------------------------
//---------------------------------------------------------------
//---------------------------------------------------------------
var datass=[];
if($('#pcec').val()=="PCEC"){
	datass= [{ "id":"PC", "text":"PC" },{  "id":"EC", "text":"EC" }];
}else if($('#pcec').val()=="PC"){
	datass= [{ "id":"PC", "text":"PC" }];
}else if($('#pcec').val()=="EC"){
	datass= [{  "id":"EC", "text":"EC" }];
}
function applyEC(){
	var name ='';
	var city =''
	var zip =''
//		alert($('#PCorEC').combobox('getValue'))
	if ($('#PCorEC').combobox('getValue')=='EC'){
		name = escape($('#e_name').val());
		city = escape($('#e_city').val());
		zip = escape($('#e_zip').val());
	}else{
		name = escape($('#p_name').val());
		city = escape($('#p_city').val());
		zip = escape($('#p_zip').val());
	}
	initMaintAccount(false,'850','400','Reg End Customer', '/endCustomerAction!toCreateEndCustomer.jspa?end_name='+name+'&end_city='+city+'&end_zip='+zip);	
}

function closeMaintWindow(){
	$("#hiddenWin").window('close');
}

$('#PCorEC').combobox({    
	data:datass,    
    valueField:'id',    
    textField:'text',
    panelHeight : 'auto',
    editable:false,
    onSelect : function(record) {
    	if(record.id=="PC"){
    		$('#ec_alias_name').val($('#p_name').val());
    		$('#alias_city').val($('#p_city').val());
    		
    	}else if(record.id=="EC"){
    		$('#ec_alias_name').val($('#e_name').val());
    		$('#alias_city').val($('#e_city').val());
    		
    	}
	},
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


function submit() {
 	var id =$('#id').val();
// 	if ($("#ec_id").combobox('getValue')=="") {
// 		$.messager.alert('Tips', "EC Code isEmpty!", 'error');  
// 		return;
// 	}
 	if ($("#ec_name").combobox('getValue')=="") {
 		$.messager.alert('Tips', "Customer!Name isEmpty!", 'error');  
 		return;
 	}
// 	if ($("#ec_city").val()=="") {
// 		$.messager.alert('Tips', "EC!City isEmpty!", 'error');  
// 		return;
// 	}
 	if ($("ec_alias_name").val()=="") {
 		$.messager.alert('Tips', "Alias Name isEmpty!", 'error');  
 		return;
 	}
// 	if ($("#alias_city").val()=="") {
//		$.messager.alert('Tips', "Alias City isEmpty!", 'error');  
//		return;
//	}
 	var form = window.document.forms[0];
 	form.action = appUrl + "/endCustomerAction!addECAlias.jspa";
	form.target = "hideFrame";
	form.submit();
}

//PC�ͻ�
var ecFirst = true;
$('#ec_name').combogrid({
	panelHeight : 280,
	panelWidth : 470,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	idField : 'end_customer_name',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_groupName',
				title : 'EC Group',
				width : 100
			}, {
				field : 'end_customer_name',
				title : 'EC Name',
				width : 200
			}, {
				field : 'country',
				title : 'Country',
				width : 70
			}, {
				field : 'city',
				title : 'City',
				width : 100
			}]],
			onSelect : function(index, record) {
				$('#ec_id').val(record.end_customer_id);
				$('#ec_group').val(record.end_customer_groupId);
				$('#ec_groupName').val(record.end_customer_groupName);
				$('#ec_city').val(record.city);
			},
			onLoadSuccess: function(data) {
				if(ecFirst){					
					$('#ec_name').combogrid('grid').datagrid('getPager').pagination("select", 1);
					ecFirst=false;
				}
			},
			toolbar : '#toolbarPurchaseCustomer',
});
function searcherPurchaseCustomer(name1) {
	ecFirst = true;
	var queryParams = $('#ec_name').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#ec_name').combogrid("grid").datagrid('reload');
} 
function close() {
	window.parent.closeMaintWindow();
}

function cancel() {
	window.parent.closeMaintWindow();
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*!!�ĳ!�ֶ!!*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ!'
    }
});

//!!!�ڶ!�
function initMaintAccount(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');
}

