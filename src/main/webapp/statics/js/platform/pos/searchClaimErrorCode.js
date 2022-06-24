$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						height : 330,
						width : 890,
//						fit:true,
						striped : true,
						url : appUrl + '/posAction!getClaimErrorCodeDetail.jspa?id='+ $('#id').val(),
						loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
//						pagination : true,
						rownumbers : true,
						fitColumns : false,
// 						pageSize:20,
 						frozenColumns: [[    
                            { title: '', field: 'disti_name', width: 80, align : 'left',}    
                        ]], 
 						columns : [
 							
 							 [{"title":"Purchase Customer","colspan":3},  
 						         {"title":"End Customer","colspan":3}],
 						         [
 									{
 										title : "Name",
 										field : 'purchasing_customer_name',
 										width : 180,
 										align : 'center',
 										"rowspan":1
 									},{
 										title : "City",
 										field : 'purchasing_cust_city',
 										width : 120,
 										align : 'center',
 										"rowspan":1
 									},{
 										title : "Country",
 										field : 'purchasing_cust_country',
 										width : 80,
 										align : 'center',
 										"rowspan":1
 									},{
 										title : "Name",
 										field : 'endcust_name',
 										width : 180,
 										align : 'center',
 										"rowspan":1
 									},{
 										title : "City",
 										field : 'endcust_city',
 										width : 120,
 										align : 'center', 
 										"rowspan":1
 									},{
 										title : "Country",
 										field : 'endcust_country',
 										width : 80,
 										align : 'center', 
 										"rowspan":1
 									}] ],
					
					});
 
}


function cancel() {
	window.parent.closeMain();		
}

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