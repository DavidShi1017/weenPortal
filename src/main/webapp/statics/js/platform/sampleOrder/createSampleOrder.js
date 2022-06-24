$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);	
});
function cancel() {
	window.parent.closeMaintWindow();
}

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
	height:260,
	fitColumns:false,
	striped : true,
     columns : [[{
		field : 'ck',
		align : 'center',
		checkbox : true
	},{
			field : 'id',
			title : '!!',
			width : 60,
			align : 'center',
			hidden:true
		},
		{
			field : 'row_no',
			title : 'PO Item',
			width : 80,
			align : 'center',
			hidden:true
		},
		{
			field : 'material_name',
			title : 'BookPart',
			width : 150,
			align : 'center',
		},
		{
			field : 'material_id',
			title : '12NC',
			width : 110,
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
			title : 'Material Type<br>!!!!',
			width : 130,
			align : 'center',
			hidden:true,
			
		},

		{
			field : 'material_groupId',
			title : 'Product Group<br>!!!',
			width : 110,
			align : 'center',
			hidden:true,
		} ,
		{
			field : 'sale_unit',
			title : 'Sale Unit<br>!�۵�λ',
			width : 70,
			align : 'center',	
		} ,
		{
			field : 'limited_QTY',
			title : 'Limited QTY<br>!!!!', 
			width : 90,
			align : 'center',	
		} ,	{
			field : 'stock_num',
			title : 'Stock Status',
			width : 150,
			align : 'center',
			},
		{
			field : 'stock_status',
			title : 'Stock Status',
			width : 150,
			align : 'center',
		    hidden:true		
			},
		{
			field : 'pq',
			title : 'PQ<br/>!!(ÿPQ!baseUnitΪһ!!�۵�λ)', 
			width : 80,
			align : 'center',	
			hidden:true
		} ,
		{
			field : 'order_QTY',
			title : 'Order QTY<br>!!!!',
			width : 90,
			align : 'center',
			editor: {type:'numberbox',options:{precision:2}}
		} ,
		{
			field : 'lead_time',
			title : 'Lead Time(week)<br>!!!!',
			width : 105,
			align : 'center',
			hidden:true		
		} ,
		{
			field : 'delivery_dateStr',
			title : 'Request Date<br>�ͻ!!!!�', 
			width : 120,
			align : 'center',	
			editor:'datebox',
			hidden:true
		} ,
		{
		field : 'confirm_dateStr',
		title : 'Confirm Delivery Date<br>ȷ�Ͻ!�',
		width : 150,
		align : 'center',
		editor:'datebox',
		hidden:true
		},		
		{
			field : 'remark',
			title : 'Cus Remark',
			width : 150,
			align : 'center',
			editor:'text',
			}]],
			toolbar : [ "-", {
				text : 'Add',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			},"-", {
				text : 'Delete',
				iconCls : 'icon-remove',
				handler : function() {
					dele();
				}
			}
			],  
			onClickCell: function (rowIndex, field, value) {
			    beginEditing(rowIndex, field, value);
			    $('#datagrid').datagrid('beginEdit', rowIndex);
		     },
}); 

function returnSampleOrderDetail(sodListJson){
	var sodList = eval(sodListJson);
	for(var i=0;i<sodList.length;i++){
		var sod = sodList[i];
		var rows = $('#datagrid').datagrid('getRows');
		var criticalValue=0;
		var stockValue=0;
		var stock_status = 'H';
		$.ajax({
			type : "post",
			url : appUrl+ '/designRegAction!getDictOfWeen.jspa?dictTypeId=564&itemId='+14247,
			success : function(result) {
				criticalValue = result[0].itemValue;
			}
		});
		$.ajax({
			type : "post",
			url :  appUrl + '/orderToSapAction!getSampleStockFromSAP.jspa',
			data : {
				material_id : sod.material_id
			},
			success : function(r) {
				stockValue = r.result;
			}
		});
		if (isNaN(stockValue) == true) {
			alert(stockValue);
		    return;	
		}
		
		if(stockValue*1 >= criticalValue*1){
			stock_status='H';
		} else if(stockValue*1 < criticalValue*1 && stockValue*1>0){
			stock_status='L';
		} else {
			stock_status='N';
		}
		if($('#ween').val()==""){
			stockValue = stock_status;
		}
		$('#datagrid').datagrid('appendRow',{
			po_item:sod.po_item,
			material_name:sod.material_name,
			material_id:sod.material_id,
			material_typeId:sod.material_typeId,
			material_groupId:sod.material_groupId,
			sale_unit:sod.sale_unit,
			limited_QTY:sod.limited_qty,
			stock_num:stockValue,
			stock_status:stock_status,
			pq:sod.pq,
			order_QTY:sod.order_QTY,
			lead_time:sod.lead_time,
			delivery_dateStr:sod.delivery_dateStr,
			confirm_dateStr:sod.confirm_dateStr,
			price:sod.price,
			remark:sod.remark
		});
	}
	$("#hiddenWin").window('close');
}

function stockAjaxFromExcel(product){
	
}
var ps=[];
function add(){	
	ps=[];
	var rows = $("#datagrid").datagrid("getRows");
	if (rows!=undefined){
		for ( var i = 0; i < rows.length; i++) {
			$("#datagrid").datagrid("endEdit", i);
			ps.push(rows[i].material_id);
		}
	}
	initMaintAccount(false,'800','450','Product Info','/productAction!toSearchProductForUse.jspa?isOrderItem=Y&useFor=YPD&currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#branch_id').val(),100,20);	
}

function returnProduct(proJson){
	
	var productStr="["+proJson+"]";
	var pJson = eval(productStr);
	for(var i=0;i<pJson.length;i++){
		var product = pJson[i];
		var ps = $('#datagrid').datagrid('getRows');
		var sameFlag=false;
		for(var j=0;j<ps.length;j++){
			if(product.material_id==ps[j].material_id){
				sameFlag=true;
				break;
			}
		}
		if(sameFlag){
			continue;
		}
// Ajax !!!!!
		stockAjaxFromProduct(product);	
	}
	$("#hiddenWin").window('close');
}
function stockAjaxFromProduct(product){
	var today = getNowFormatDate();
	var criticalValue=0;
	var stockValue=0;
	var stock_status = 'L';
	$.ajax({
		type : "post",
		url : appUrl+ '/designRegAction!getDictOfWeen.jspa?dictTypeId=564&itemId='+14247,
		success : function(result) {
			criticalValue = result[0].itemValue;
			//
			$.ajax({
				type : "post",
				url :  appUrl + '/orderToSapAction!getSampleStockFromSAP.jspa',
				data : {
					material_id : product.material_id
				},
				success : function(r) {
					stockValue = r.result;
					if (isNaN(stockValue) == true) {
						alert(stockValue);
					    return;	
					}
					
					if ( stockValue * 1 > criticalValue*1) {
					    stock_status='H';
					} else if(stockValue*1 <= criticalValue*1 && stockValue*1>0){
					    stock_status='L';
					} else {
					    stock_status='N';
					}
					if($('#ween').val()==""){
						stockValue = stock_status;
					}
					$('#datagrid').datagrid('appendRow',{
						po_item:"",
						material_name:product.material_name,
						material_id:product.material_id,
						material_typeId:product.material_type,
						material_groupId:product.material_groupId,
						sale_unit:product.base_unit,
						limited_QTY:product.limited_qty,
						stock_num:stockValue,
						stock_status:stock_status,
						pq:product.numerator,
						order_QTY:'',
						lead_time:product.lead_time,
						delivery_dateStr:today,
						confirm_dateStr:'',
						remark:''
					});
				}
			});
		}
	});
}

var delOrder="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Error', 'Please select the data you need to operate!', 'warning');
		return;
	}
	 $.messager.confirm('Confirm', 'Sure To Delete?', function(r) {
		 if(r){
			 {				
				 if(row.id!=undefined){
					 delOrder+=","+row.id;
				}				 
				 var rowIndex = $('#datagrid').datagrid('getRowIndex', row);
				 $('#datagrid').datagrid('deleteRow', rowIndex); 
				
			 }		 
		 }
	 });
}

function importExcel(){
	initMaintAccount(false,'700','350','Import OrderDetail', '/sampleOrderAction!importExcel.jspa?currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#branch_id').val(),100,100);
}

function downloadExcelModel(){
		$.messager.confirm('Tip', 'Download the excel templates ?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("orderDetail.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
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
	var url=undefined;
	var isAutomotiveGradeProduct = false;
	var alertMsg="";
	if($('#id').val()=='0'||$('#id').val()==''){
		url="/sampleOrderAction!createSampleOrder.jspa";
	}else{
		url="/sampleOrderAction!updateSampleOrder.jspa";
	}

	if ($('#branch_id').val()=="") {
		$.messager.alert('Error', "Please contact Portal admin to config funloc!", 'error');
		return;
	}
	if ($('#company').val()=="") {
		$.messager.alert('Error', "Ship-To Company not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#company').val())) {
		$.messager.alert('Error', "Ship-To Company : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#country').val()=="") {
		$.messager.alert('Error', "Ship-To Country not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#country').val())) {
		$.messager.alert('Error', "Ship-To Country : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#contact_name').val()=="") {
		$.messager.alert('Error', "Ship-To Consignee not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#contact_name').val())) {
		$.messager.alert('Error', "Ship-To Consignee : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#city').val()=="") {
		$.messager.alert('Error', "Ship-To City not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#city').val())) {
		$.messager.alert('Error', "Ship-To City : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#contact_tel').val()=="") {
		$.messager.alert('Error', "Ship-To Phone No not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#contact_tel').val())) {
		$.messager.alert('Error', "Ship-To Phone : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#zip').val()=="") {
		$.messager.alert('Error', "Ship-To ZIP Code not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#zip').val())) {
		$.messager.alert('Error', "Ship-To ZIP : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#street').val()=="") {
		$.messager.alert('Error', "Ship-To Street not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#street').val())) {
		$.messager.alert('Error', "Ship-To Street : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#end_customer1').val()=="") {
		$.messager.alert('Error', "End Customer not completed yet!", 'error');
		return;
	}
	if ($('#application_desc').val()=="") {
		$.messager.alert('Error', "Application Desc not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#application_desc').val())) {
		$.messager.alert('Error', "Application Desc : No Chinese input allowed!", 'error');
		return;
	}
	if ($('#project_name').val()=="") {
		$.messager.alert('Error', "Customer Project not completed yet!", 'error');
		return;
	} else if (isChineseChar($('#project_name').val())) {
		$.messager.alert('Error', "Customer Project : No Chinese input allowed!", 'error');
		return;
	}
	if (isChineseChar($('#remark').val())) {
		$.messager.alert('Error', "Shipment Remark : No Chinese input allowed!", 'error');
		return;
	}
	
    if ($('#shipToRegion').combobox('getValue') == "") {
	    $.messager.alert('Error', "Ship-To Region not completed yet!", 'error');
		return;
	}
		
	if ($('#forWho').val()=='Disti') {
		if ($('#account_manager').combobox('getValue') == "") {
			$.messager.alert('Error', "Account Manager not completed yet!", 'error');
			return;
		}
	}

	if ($('#applicant_name').val()=="") {
		$.messager.alert('Error', "Applicant Name not completed yet!", 'error');
		return;
	}
		
	if ($('#applicant_company').val()=="") {
		$.messager.alert('Error', "Company Name of Applicant not completed yet!", 'error');
		return;
	}
	
	if ($('#order_id').val()=='') {  
		alertMsg += "Purchase Order not completed yet!";
	}
	
	var rows = $("#datagrid").datagrid("getRows");	
	if(rows == undefined || rows.length == 0){
		$.messager.alert('Error', "Please fill in the order line item!", 'error');
		return;
	}
	
	for(var i = 0; i < rows.length; i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
	
	var orderDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].material_id==""|| rows[i].material_id==undefined) {
			$.messager.alert('Error', "Rows"+(i*1+1)+": 12NC not completed yet!", 'error');
			return;
		}
		if ((rows[i].order_QTY==0)||(rows[i].limited_QTY*1.0<rows[i].order_QTY*1.0)) {
			$.messager.alert('Error', "Rows"+(i*1+1)+":Order QTY is 0 or out of limit!", 'error');
			return;
		}
		
		if (isChineseChar(rows[i].remark)) {
			$.messager.alert('Error', "Rows"+(i*1+1)+" Cus Remark : No Chinese input allowed!", 'error');
			return;
		}

		var materialName = rows[i].material_name;
		if (materialName != "" && materialName != undefined) {
			var length = materialName.length;
			var start = 0;
			if (length > 4) {
				start = length - 4;
			}
			var checkStr = materialName.substring(start, length);
			if(checkStr.indexOf("-A") >= 0) {
				isAutomotiveGradeProduct = true;
			}
		}
		
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"po_item:"+"'"+rows[i].po_item+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"material_typeId:"+"'"+rows[i].material_typeId+"',"
		+"material_groupId:"+"'"+rows[i].material_groupId+"',sale_unit:'"+rows[i].sale_unit
		+"',limited_QTY:'"+rows[i].limited_QTY+"',order_QTY:'"+rows[i].order_QTY
		+"',lead_time:'"+rows[i].lead_time+"',delivery_dateStr:'"+rows[i].delivery_dateStr
		+"',stock_status:'"+rows[i].stock_status
		+"',remark:'"+rows[i].remark+"'}";
		orderDetailJson.push(row);
	}
    if (isAutomotiveGradeProduct) {
    	$.messager.confirm('Confirm', 'Dear customer, please confirm your application need Automotive grade product.', function(confirm) {
    		if (!confirm) {
    			return;
    		}
    		else {
    		    $.messager.confirm('Confirm', alertMsg+'Sure to submit?', function(r) {
    				if (r) {						
    					$('#orderDetailJson').val("["+orderDetailJson+"]");
    					$('#delOrder').val('('+delOrder+')');	 
    					var form = window.document.forms[0];
    					form.action = appUrl + url;
    					form.target = "hideFrame";
    					form.submit();			  						
    				}
    			});
    		}
    	});
    } 
    else {
	    $.messager.confirm('Confirm', alertMsg+'Sure to submit?', function(r) {
			if (r) {						
				$('#orderDetailJson').val("["+orderDetailJson+"]");
				$('#delOrder').val('('+delOrder+')');	 
				var form = window.document.forms[0];
				form.action = appUrl + url;
				form.target = "hideFrame";
				form.submit();			  						
			}
		});
    }
}

var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
         }
    }
}
function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
 }

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
var customer_type = undefined;
var ecParam="";
var ecFirst=true;

$('#end_customer').combogrid({
	panelHeight : 280,
	panelWidth : 400,
	pagination : true,
	pageSize:10,
	width:350,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	idField : 'end_customer_name',
	textField :'end_customer_name',
	columns : [[
			{
				field : 'end_customer_name',
				title : 'EC Name',
				width : 270
			}, {
				field : 'country',
				title : 'Country',
				width : 60
			}, {
				field : 'city',
				title : 'City',
				width : 70
			}]],
			onSelect : function(index, record){
			},
			onChange : function(newValue, oldValue) {
				$('#end_customer1').val(newValue);
				ecFirst=true;
				if(newValue!=undefined && newValue!=null && $.trim(newValue) != ""){
					ecParam = newValue;
				}
				if(ecParam!=undefined && ecParam!=null && $.trim(ecParam) != "" && $.trim(ecParam) != "undefined"){
					$(this).combogrid("grid").datagrid('reload');
				}
			},
			onBeforeLoad: function(param){
				param.end_customer_name = encodeURIComponent($.trim(ecParam));		
			},
			onLoadSuccess: function(data) {
				if(ecFirst){					
					$('#end_customer').combogrid('grid').datagrid('getPager').pagination("select", 1);
					ecFirst=false;
				}
			}
			
});

$.extend($.fn.validatebox.defaults.rules, {  
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ!'
    }
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
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

// check Chinese Input
function isChineseChar(str){   
    var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
    // return reg.test(str);
    return false;
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
	  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};

$('#shipToRegion').combobox({
	url : appUrl + '/sampleOrderAction!getOrgLists.jspa',
	valueField : 'sapOrgId',
	textField : 'orgName',
	multiple : false,
	editable : false,
	panelHeight : 'auto',
	width : 170,
	onSelect : function(r){
		$("#account_manager").combobox("reset");
		var shipToRegion = $('#shipToRegion').combobox('getValue');
		var urlStr = appUrl
		    + "/sampleOrderAction!getAccountManagerList.jspa?shipToRegion="
		    + encodeURI(shipToRegion);
		$("#account_manager").combobox("reload", urlStr);
 	}
});

$('#account_manager').combobox({
	url : appUrl + '/sampleOrderAction!getAccountManagerList.jspa?shipToRegion=' + $('#shipToRegion').combobox('getValue'),
	valueField : 'userId',
	textField : 'userName',
	multiple : false,
	editable : false,
	panelHeight : 'auto',
	width : 320,
	onSelect : function(r) {
		$('#managerId').val(r.userId);
 	} 
});
