$(document).ready(function() {
	loadGrid();
	$('#customer_code1').html($('#customer_code1').html().replace( /^0*/, ''));
 	$('#hideFrameApp').bind('load', promgtMsg);
	 
});
 

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/customerAction!getCusCompanyList.jspa?customer_code='+$('#customer_code').val(),
						loadMsg : 'Loading...', 
						singleSelect : true,
						pagination : false,
						nowrap : true,
						height : 230,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : false,
								
								},{
									field : 'customer_code',
									title : 'Customer Code',
									width : 120,
									align : 'center',
									hidden:true
								},{
									field : 'partnerType',
									title : 'Type',
									width : 80,
									align : 'center'
								}
								,
								{
									field : 'partnerId',
									title : 'Code',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.partnerId;
										if (flag == ''||flag==undefined) {
											return "";
										} else{
											var str = $.trim(flag);								
											str = str.substring(str.length-6, str.length);
											return str;
										} 
									}
								},
 								{
									field : 'partnerName',
									title : 'Name',
									width : 250,
									align : 'center'
								}, 
								{
									field : 'address',
									title : 'Address',
									width : 250,
									align : 'center'
 								},
								{
									field : 'contact',
									title : 'Contact Name',
									width : 100,
									align : 'center'
 								}  ] ],
								onClickCell: function (rowIndex, field, value) {
								    beginEditing(rowIndex, field, value);
								},
						 
						onBeforeEdit:function(index,row){
					        row.editing = true;
					        $('#datagrid').datagrid('refreshRow', index);
					    },
					    onAfterEdit:function(index,row){
					        row.editing = false;
					        $('#datagrid').datagrid('refreshRow', index);
					    },
					    onCancelEdit:function(index,row){
					        row.editing = false;
					        $('#datagrid').datagrid('refreshRow', index);
					    }

					});

}
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
 			window.parent.closeWindow();
			window.parent.search();
		 });
	}
}





var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
 
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
          /*   var ed = $('#datagrid').datagrid('getEditor', { index: rowIndex, field: 'borrowtype_code' });
           
            $(ed.target).focus().bind('blur', function () {
                endEditing();
            }); */
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
         }
    }
}
  function endEditing () {
    if (editIndex == undefined) { return true }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
 }
  
  
  function beginEditing1 (rowIndex, field, value) {
	  
	    if (rowIndex != editIndex) {
	        if (endEditing1()) {
	            $('#datagridBank').datagrid('beginEdit', rowIndex);
	             editIndex = rowIndex;
	          /*   var ed = $('#datagrid').datagrid('getEditor', { index: rowIndex, field: 'borrowtype_code' });
	           
	            $(ed.target).focus().bind('blur', function () {
	                endEditing();
	            }); */
	        } else {
	            $('#datagridBank').datagrid('selectRow', editIndex);
	         }
	    }
	}
	  function endEditing1 () {
	    if (editIndex == undefined) { return true }
	    if ($('#datagridBank').datagrid('validateRow', editIndex)) {
	         $('#datagridBank').datagrid('endEdit', editIndex);
	        $('#datagridBank').datagrid('selectRow', editIndex);
	        editIndex = undefined;
	        return true;
	    } else {
	        return false;
	    }
	 }
  
  
	  var editIndex2 = undefined;

	  function beginEditing2 (rowIndex, field, value) {
 		    if (rowIndex != editIndex2) {
		        if (endEditing2()) {
		            $('#datagridEquire').datagrid('beginEdit', rowIndex);
		            editIndex2 = rowIndex;
		          /*   var ed = $('#datagrid').datagrid('getEditor', { index: rowIndex, field: 'borrowtype_code' });
		           
		            $(ed.target).focus().bind('blur', function () {
		                endEditing();
		            }); */
		        } else {
		            $('#datagridEquire').datagrid('selectRow', editIndex2);
		         }
		    }
		}
	  function endEditing2 () {
	    if (editIndex2 == undefined) { return true }
	    if ($('#datagridEquire').datagrid('validateRow', editIndex2)) {
	         $('#datagridEquire').datagrid('endEdit', editIndex2);
	        $('#datagridEquire').datagrid('selectRow', editIndex2);
	        editIndex2 = undefined;
	        return true;
	    } else {
	        return false;
	    }
	 }
	  
	  $('#customer_type').combobox({
  			url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=553',
			valueField : 'itemValue',
			textField : 'itemName',
			multiple : false,
			editable : false,
			required : true,
			panelHeight : 'auto',
			onSelect : function(r){
		 	} 
		 
		});
	  $('#currency_code').combobox({
			url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
			idField : 'itemValue',
			textField : 'itemName',
			multiple : false,
			editable : false,
			required : true,
			panelHeight : 'auto',
			onSelect : function(r){
		 	} 
		});
	  
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