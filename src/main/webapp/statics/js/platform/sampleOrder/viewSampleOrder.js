$(document).ready(function() {
			loadGrid();
 			$('#hideFrame').bind('load', promgtMsg);
		});
function cancel() {
	window.parent.closeMaintWindow();
}
function loadGrid() {
	$('#datagrid').datagrid({   
		iconCls : 'icon-list',
		title : '',
		url : appUrl +  '/sampleOrderAction!getSampleOrderDetailList.jspa?main_id='+$('#id').val(),
		loadMsg : 'Loading...',
		singleSelect : true,
		nowrap : true,
		checkbox : true,
	 	required : true,
		rownumbers : true,
		height:300,
		fitColumns:false,
		striped : true,
	     columns : [[{
			field : 'ck',
			align : 'center',
			checkbox : true
		},{
				field : 'id',
				title : '����',
				width : 60,
				align : 'center',
				hidden:true
			},
			{
				field : 'state',
				title : 'Status',
				width : 80,
				align : 'center',
				formatter : function(value, row, rec) {	
					if ($('#state').val() == '2') {
						return "Not shipped";
					} else if ($('#state').val() == '1') {
						return "Processing";
					} else if (row.expressage_info != undefined
							&& row.expressage_info != 'undefined' && row.expressage_info != '') {
						return "Shipped";
					} else {
						return "Processing";					
					}
				}
			},
			{
				field : 'row_no',
				title : 'Item ID',
				width : 80,
				align : 'center',
			},
			{
				field : 'material_name',
				title : 'BookPart<br>��������',
				width : 150,
				align : 'center',
				formatter : function(value, row, rec) {
					var flag = row.material_name;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				}
			},
			{
				field : 'material_id',
				title : '12NC<br>���ϱ���',
				width : 100,
				align : 'left',
				formatter : function(value, row, rec) {
					var flag = row.material_id;
					if (flag == ''||flag==undefined) {
						return "";
					} else{
						var str = $.trim(flag);
						str = str.substring(str.length-12, str.length);
						return str;
					} 
				}
			},
			{
				field : 'material_typeId',
				title : 'Material Type<br>��������',
				width : 100,
				align : 'center',
				formatter : function(value, row, rec) {
					var flag = row.material_typeId;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				},
				hidden:true
				
			},

			{
				field : 'material_groupId',
				title : 'Product Group<br>������',
				width : 100,
				align : 'center',
				formatter : function(value, row, rec) {
					var flag = row.material_groupId;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				},
				hidden:true
			} ,
			{
				field : 'sale_unit',
				title : 'Sale Unit<br>���۵�λ',
				width : 70,
				align : 'center',	
				formatter : function(value, row, rec) {
					var flag = row.sale_unit;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				}
			} ,
			{
				field : 'limited_QTY',
				title : 'Limited Qty<br>��������', 
				width : 80,
				align : 'center',	
				formatter : function(value, row, rec) {
					var flag = row.limited_QTY;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				}
			} ,
			{
				field : 'stock_status',
				title : 'Stock Status',
				width : 150,
				align : 'center',
			//	editor:'text',	
				hidden:true
				},
			{
				field : 'order_QTY',
				title : 'Order QTY<br>��������',
				width : 80,
				align : 'center',
			} ,
			{
				field : 'expressage_info',
				title : 'Expressage Info',
				width : 120,
				align : 'center',
			} ,
			{
				field : 'ship_date',
				title : 'Ship Date', 
				width : 120,
				align : 'center',	
/*				formatter : function(date){
					if(date==undefined){
						return "";
					}else{
						return utcToDate(date);
					}
					
				}*/
			} ,
			{
				field : 'invoice',
				title : 'Invoice', 
				width : 120,
				align : 'center',	
			} ,
			{
				field : 'lead_time',
				title : 'Lead Time(week)<br>��������',
				width : 105,
				align : 'center',	
				formatter : function(value, row, rec) {
					var flag = row.lead_time;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				},
				hidden:true		
				
			} ,
			{
				field : 'delivery_dateStr',
				title : 'Request Date<br>�ͻ���������', 
				width : 120,
				align : 'center',	
				hidden:true
			} ,
//			{
//				field : 'order_QTY',
//				title : 'Invoice<br>��������',
//				width : 80,
//				align : 'center',
//			} ,
			{
			field : 'confirm_dateStr',
			title : 'Confirm Delivery Date<br>ȷ�Ͻ���',
			width : 150,
			align : 'center',
			formatter : function(value, row, rec) {
				var flag = row.confirm_dateStr;
				if (flag == undefined||flag=='') {
					return "N/A";
				} else{
					return flag;
				} 
			},hidden:true
			},		
			{
				field : 'remark',
				title : 'Cust Remark<br>�ͻ���ע',
				width : 150,
				align : 'center',
				formatter : function(value, row, rec) {
					var flag = row.remark;
					if (flag == '0'||flag=='undefined') {
						return "";
					} else{
						return flag;
					} 
				}},{
					field : 'ween_remark',
					title : 'Ween Remark<br>���ܱ�ע',
					width : 150,
					align : 'center',
					formatter : function(value, row, rec) {
						var flag = row.ween_remark;
						if (flag == '0'||flag=='undefined') {
							return "";
						} else{
							return flag;
						} 
					},
							
				}]],
	}); 
 
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Error', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	
	$("#datagrid").datagrid('load');
}

function closeMain() {
	$("#hiddenWin").window('close');
}

//function dele(){
//	
//	var ids = '(';
//	var rows = $('#priceinfo_list').datagrid('getSelections');
//	for ( var i = 0; i < rows.length; i++) {
//		if(i==rows.length-1){
//			ids += rows[i].id+')';
//		}else{
//			ids += rows[i].id+',';
//		}
//	}
// 	if (ids == '') {
//		$.messager.alert('Tips', 'δѡ�����ݣ�', 'warning');
//		return;
//	} else {
//		var form = window.document.forms[0];
//		form.action = appUrl + '/priceInfoAction!updatePriceInfoState.jspa?ids='+ ids+'&state=5';
//		form.target = "hideFrame";
//		form.submit();
//	}	
//}

//��������
$('#type_id').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=549',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	required : true,
	panelHeight : 120,
	width : 173,
	onSelect : function(r){
 	} 
});


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
	//date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
 