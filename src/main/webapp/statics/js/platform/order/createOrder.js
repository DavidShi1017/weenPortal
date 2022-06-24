$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);	
});

//订单明细模块
$('#datagrid').datagrid({   
	iconCls : 'icon-list',
	title : '',
	url : appUrl +  '/orderAction!getOrderDetailList.jspa?main_id='+$('#id').val(),
	loadMsg : 'Loading...',
	singleSelect : true,
//	nowrap : true,
	checkbox : true,
 	required : true,
	rownumbers : true,
	height:280,
	fitColumns:false,
	striped : true,
     columns : [[{
		field : 'ck',
		align : 'center',
		checkbox : true
	},{
			field : 'id',
			title : '主键',
			width : 100,	
			align : 'center',
			hidden:true
		},
		{
			field : 'po_item',
			title : 'PO Item',
			width : 80,
			align : 'center',
			editor:'text',
		},
		{
			field : 'material_name',
			title : 'BookPart',
			width : 150,
			align : 'center',
		},
		{
			field : 'material_id',
			title : '12NC<br/>物料编码',
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
			title : 'Material Type<br/>物料类型',
			width : 100,
			align : 'center',
			
		},

		{
			field : 'material_groupId',
			title : 'Product Group<br/>物料组',
			width : 100,
			align : 'center',
		} ,
		{
			field : 'sale_unit',
			title : 'Sale Unit<br/>销售单位',
			width : 60,
			align : 'center',	
		} ,
		{
			field : 'moq',
			title : 'MOQ<br/>起订量', 
			width : 80,
			align : 'center',	
		} ,
		{
			field : 'pq',
			title : 'PQ<br/>(每PQ个baseUnit为一个销售单位)', 
			width : 80,
			align : 'center',	
		} ,
		{
			field : 'order_QTY',
			title : 'Order QTY<br/>订单数量',
			width : 80,
			align : 'center',
			editor: {type:'numberbox'}
		} ,
		{
			field : 'lead_time',
			title : 'Lead Time(week)<br/>生产周期',
			width : 105,
			align : 'center',				
		} ,
		{
			field : 'delivery_dateStr',
			title : 'Request Date<br/>客户期望交期', 
			width : 120,
			align : 'center',	
			editor:'datebox',
		} ,
		{
		field : 'confirm_dateStr',
		title : 'Confirm Delivery Date<br/>确认交期',
		width : 150,
		align : 'center',
		 editor:{  
			    type:'datebox'   
		 } ,

		hidden:true
		},
		
		{
			field : 'pbMpp',
			title : 'Pb/Mpp',
			width : 120,
			align : 'right',
			hidden:true
			},
		{
		field : 'price',
		title : 'Price<br/>单价',
		width : 120,
		align : 'right',
		editor: {type:'numberbox',options:{precision:4}},
		hidden:true
		},		
		{
			field : 'remark',
			title : 'Cus Remark',
			width : 100,
			align : 'center',
			editor:'text',
			hidden:true
						
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
			}, "-", {
				text : 'Import',
				iconCls : 'icon-up',
				handler : function() {
					importExcel();
				}
			}, "-", {
				text : 'Outport',
				iconCls : 'icon-excel',
				handler : function() {
					downloadExcelModel();//导出模板
				}
			}
			/*, "-", {
				text : 'Download',
				iconCls : 'icon-download',
				handler : function() {
					downloadExcelData();//导出Excel数据
				}
			}*/
			],  
			onClickCell: function (rowIndex, field, value) {
			    beginEditing(rowIndex, field, value);
			    $('#datagrid').datagrid('beginEdit', rowIndex);
		     },
}); 


function importExcel(){
	initMaintAccount(false,'700','350','Import OrderDetail', '/orderAction!importExcel.jspa?currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#branch_id').val(),100,100);
	
}
//模板下载
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
function downloadExcelData(){
	var rows = $("#datagrid").datagrid("getRows");	
	if(rows==undefined ||rows.length==0){//行项目未填
		$.messager.alert('Error', "There is no data！", 'error');
		return;
	}
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
	var orderDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"po_item:"+"'"+rows[i].po_item+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"material_typeId:"+"'"+rows[i].material_typeId+"',"
		+"material_groupId:"+"'"+rows[i].material_groupId+"',sale_unit:'"+rows[i].sale_unit
		+"',moq:'"+rows[i].moq+"',price:'"+rows[i].price+"',order_QTY:'"+rows[i].order_QTY
		+"',lead_time:'"+rows[i].lead_time+"',delivery_date:'"+rows[i].delivery_dateStr
		+"',remark:'"+rows[i].remark+"',pq:'"+rows[i].pq+"'}";
		//"',confirm_date:'"+rows[i].confirm_dateStr+
		orderDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', 'Confirmed about  download?', function(r) {
		if(r){						
			$('#orderDetailJson').val("["+orderDetailJson+"]"); 
			var form = window.document.forms[0];
			form.action = appUrl + "/orderAction!downloadExcelData.jspa";
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
}
function returnOrderDetail(odListJson){
	var odList = eval(odListJson);
	for(var i=0;i<odList.length;i++){
		var od = odList[i];
		var rows = $('#datagrid').datagrid('getRows');
//		过滤掉重复物料
//		var sameFlag=false;
//		for(var j=0;j<rows.length;j++){
//			if(od.material_id==rows[j].material_id){
//				sameFlag=true;
//				break;
//			}
//		}
//		if(sameFlag){
//			continue;
//		}
		$('#datagrid').datagrid('appendRow',{
			po_item:od.po_item,
			material_name:od.material_name,
			material_id:od.material_id,
			material_typeId:od.material_typeId,
			material_groupId:od.material_groupId,
			sale_unit:od.sale_unit,
			moq:od.moq,
			pq:od.pq,
			order_QTY:od.order_QTY,
			lead_time:od.lead_time,
			delivery_dateStr:od.delivery_dateStr,
			confirm_dateStr:od.confirm_dateStr,
			pbMpp:od.pbMpp,
			price:od.price,
			remark:od.remark
		});
	}
	$("#maintWindow").window('close');
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
	initMaintAccount(false,'800','500','Product Info','/productAction!toSearchProductForUse.jspa?isOrderItem=Y&currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#branch_id').val(),100,20);
	//
}

function returnProduct(proJson){
	var productStr="["+proJson+"]";
	var pJson = eval(productStr);
	for(var i=0;i<pJson.length;i++){
		var product = pJson[i];
		//计算期望送达日期：deliveryDate = today+LeadTime*7;
		var today = getNowFormatDate();
	    var dateTemp = today.split("-");  
	    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
	    var days= parseInt((product.lead_time)*7);
	    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
	    var rDate = new Date(millSeconds);  
	    var year = rDate.getFullYear();  
	    var month = rDate.getMonth() + 1;  
	    if (month < 10) month = "0" + month;  
	    var date = rDate.getDate();  
	    if (date < 10) date = "0" + date;  
	   today = year + "-" + month + "-" + date; 
		    
//		    
//		    
//		var date = new Date();
//		var year = date.getFullYear();
//    	var month = date.getMonth()+1;
//    	var day = date.getDate();
//    	day = day+parseInt((product.lead_time)*7);
//    	if(day>=30){
//    		
//    	}
//    	month=month+day/30;
//    	if(month>12){
//    		month=month*1-12;
//    		year=year*1+1;
//    	}
//        if (month*1 >= 1 && month*1 <= 9) {
//            month = "0" + month;
//        }
//        if (day*1 >= 0 && day*1 <= 9) {
//        	day = "0" + day;
//        }
//        var today = year+"-"+month+"-"+day;   	   	
    	
		var sameFlag=false;
		for(var j=0;j<ps.length;j++){
			if(product.material_id==ps[j]){
				sameFlag=true;
				break;
			}
		}
		if(sameFlag){
//			$.messager.confirm('Confirm', product.material_id+' aleady exist,continue?', function(r) {
//				if(r){
//					$('#datagrid').datagrid('appendRow',{
//						material_name:product.material_name,
//						material_id:product.material_id,
//						material_typeId:product.material_type,
//						material_groupId:product.material_groupId,
//						sale_unit:product.sale_unit,
//						moq:product.moq,
//						order_QTY:'',
//						lead_time:product.lead_time,
//						delivery_dateStr:'',
//						confirm_dateStr:'',
//						price:'',
//						remark:''
//					});
//				}
//			});
			alert( product.material_id+' aleady exist');
			//continue;
		}
		$('#datagrid').datagrid('appendRow',{
			po_item:'',
			material_name:product.material_name,
			material_id:product.material_id,
			material_typeId:product.material_type,
			material_groupId:product.material_groupId,
			sale_unit:product.base_unit,
			moq:product.moq,
			pq:product.numerator,
			order_QTY:'',
			lead_time:product.lead_time,
			delivery_dateStr:today,
			confirm_dateStr:'',
			price:'0',
			remark:''
		});
	}
	$("#maintWindow").window('close');
}
var delOrder="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Error', 'Please select the data you need to operate！', 'warning');
		return;
	}
	 $.messager.confirm('Confirm', 'Confirmed about  Delete?', function(r) {
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







function submit() {
	var alertMsg="";
	if ($('#order_id').val()=='') {  
		//$.messager.alert('Error', "订单号未填！", 'error');
		alertMsg += "Purchase Order is not completed yet！";
	}
	if ($('#branch_id').combobox('getValue')=="") {//客户
		$.messager.alert('Error', "Sold To is not completed yet！", 'error');
		return;
	}
	if ($('#ship_to').combobox('getValue')=="") {//送达方
		$.messager.alert('Error', "Ship To is not completed yet！", 'error');
		return;
	}
	if ($('#payer_to').combobox('getValue')=="") {//付款方
		$.messager.alert('Error', "Payer To is not completed yet！", 'error');
		return;
	}
	if ($('#billing_to').combobox('getValue')=="") {//开票方
		$.messager.alert('Error', "Billing To is not completed yet！", 'error');
		return;
	}
	if ($('#currency_code').val()=="") {//货币
		$.messager.alert('Error', "Currency is not completed yet！", 'error');
		return;
	}
	var rows = $("#datagrid").datagrid("getRows");	
	if(rows==undefined ||rows.length==0){//行项目未填
		$.messager.alert('Error', "Please fill in the order line item！", 'error');
		return;
	}
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
	var url=undefined;
	if($('#id').val()=='0'||$('#id').val()==''){
		 url="/orderAction!createOrder.jspa";
	}else{
		url="/orderAction!updateOrder.jspa";
	}
	
	var orderDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].material_id==""||rows[i].material_id==undefined) {
			$.messager.alert('Error', "Rows"+(i*1+1)+": 12NC is not completed yet！", 'error');
			return;
		}
		if ((rows[i].order_QTY==0)||(rows[i].moq*1.0>rows[i].order_QTY*1.0)) {//订购数量不足
			$.messager.alert('Error', "Rows"+(i*1+1)+": Order Qty is not enough！", 'error');
			return;
		}
		if((rows[i].order_QTY*1.00)%(rows[i].pq*1.00)!=0){//订购数量须为PQ整数倍（不可为0）
			$.messager.alert('Error', "Rows"+(i*1+1)+": The order quantity  must be integral multiple of PQ ！", 'error');
			return;
		}
		if(rows[i].delivery_dateStr==undefined||rows[i].delivery_dateStr==""){//不可为空
			$.messager.alert('Error', "Rows"+(i*1+1)+": Request Date is not completed yet！", 'error');
			return;
		}
		if(rows[i].delivery_dateStr < getNowFormatDate()){//不可早于当前日期
			$.messager.alert('Error', "Rows"+(i*1+1)+": Request Date can not be earlier than the current Date！", 'error');
			return;
		}
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"po_item:"+"'"+rows[i].po_item+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"material_typeId:"+"'"+rows[i].material_typeId+"',"
		+"material_groupId:"+"'"+rows[i].material_groupId+"',sale_unit:'"+rows[i].sale_unit
		+"',moq:'"+rows[i].moq+"',price:'"+rows[i].price+"',order_QTY:'"+rows[i].order_QTY
		+"',lead_time:'"+rows[i].lead_time+"',delivery_date:'"+rows[i].delivery_dateStr
		+"',remark:'"+rows[i].remark+"',pq:'"+rows[i].pq+"'}";
		//"',confirm_date:'"+rows[i].confirm_dateStr+
		orderDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', alertMsg+'Confirmed about  submit?', function(r) {
		if(r){						
			$('#orderDetailJson').val("["+orderDetailJson+"]");
			$('#delOrder').val('('+delOrder+')');	 
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
	
 
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
//		if(successResult=="Order Save Success! Confirmed about  Confirm?"){
//			$.messager.confirm('Confirm', successResult, function(r) {
//				if(r){
//					var form = window.document.forms[0];
//					form.action = appUrl + 'mpleOrderToSapAction!getSampleOrderToSAP.jspa?id='+$('#id').val();
//					form.target = "hideFrame";
//					form.submit();
////				window.parent.closeMaintWindow();
////				window.parent.search();
//				}
//			});			
//		}else{		
	 		$.messager.alert('Tips', successResult, 'info',function(){			
	 			window.parent.closeMaintWindow();
				window.parent.search();
			});			
		}
//	}
}





//代理商客户
$('#branch_id').combogrid({
	panelHeight : 280,
	panelWidth : 450,
	width:450,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa?loginId='+$('#loginId').val()+'&multiDisti=Y',
	idField : 'customer_code',
	textField : 'customer_name',
	columns : [[{
				field : 'customer_code',
				title : 'Code',
				width : 70,
				formatter : function(value, row, rec) {
					var flag = row.customer_code;
					if (flag == ''||flag==undefined) {
						return "";
					} else{
						var str = $.trim(flag);
						str = str.substring(str.length-6, str.length);
						return str;
					} 
				}
			}, {
				field : 'customer_name',
				title : 'Name',
				width : 290
			},
			{
				field : 'country',
				title : 'city/country',
				width : 80,
				//hidden:true
			},{
				field : 'currency_code',
				title : 'Currency',
				width : 120,
				hidden:true
			}, {
				field : 'sales',
				title : 'Sale',
				width : 120,
				hidden:true
			}]],
			toolbar : '#toolbarCustomer',
			onSelect : function(index, record){
				$('#currency_code').val(record.currency_code);
				$('#salesOrg').val(record.sale_office);
				$('#SoldToInfo').html(record.customer_code+"/"+record.customer_name+"/"+record.country+"/"+record.address);
				//送达方
				$('#ship_to').combogrid({
					panelHeight : 280,
					panelWidth : 350,
					width:350,
					pagination : true,
					pageSize:10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
//					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+''+'&partnerType=WE',
					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=WE',
					idField : 'partnerId',
					textField :'nameAddress',
					columns : [[{
						field : 'customer_code',
						title : 'Disti',
						width : 80,
						hidden:true
					},
					{
						field : 'partnerId',
						title : 'Code',
						width : 70,
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
					}, {
						field : 'partnerName',
						title : 'PartnerName',
						width : 250
					}, {
						field : 'address',
						title : 'Address',
						width : 200,
					}]],
					onSelect : function(index, record){
						$('#ShipToInfo').html(record.partnerId+"/"+record.partnerName+"/"+record.address);
					},
					
					onLoadSuccess:function(){
						var rows =  $('#ship_to').combogrid("grid").datagrid('getRows');

							if(rows!=undefined && rows.length!=0){
								 $('#ship_to').combogrid("grid").datagrid('selectRow',0);
							}
					}
				});
				//付款方
				$('#payer_to').combogrid({
					panelHeight : 280,
					panelWidth : 320,
					width:350,
					pagination : true,
					pageSize:10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=RG',
//					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+$('#branch_id').combobox('getValue')+'&partnerType=RG',
					idField : 'partnerId',
					textField :'partnerName',
					columns : [[{
								field : 'partnerId',
								title : 'Code',
								width : 100,
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
							}, {
								field : 'partnerName',
								title : 'Partner Name',
								width : 200
							}]],
							onLoadSuccess:function(){
								var rows =  $('#payer_to').combogrid("grid").datagrid('getRows');

								if(rows!=undefined && rows.length!=0){
									 $('#payer_to').combogrid("grid").datagrid('selectRow',0);
								}
							}
				});
				//开票方
				$('#billing_to').combogrid({
					panelHeight : 280,
					panelWidth : 320,
					width:350,
					pagination : true,
					pageSize:10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
//					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+''+'&partnerType=RE',
					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=RE',
					idField : 'partnerId',
					textField :'partnerName',
					columns : [[{
								field : 'partnerId',
								title : 'Code',
								width : 100,
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
							}, {
								field : 'partnerName',
								title : 'PartnerName',
								width : 200
							}]],
							onLoadSuccess:function(){
								var rows =  $('#billing_to').combogrid("grid").datagrid('getRows');

  								if(rows!=undefined && rows.length!=0){
  									 $('#billing_to').combogrid("grid").datagrid('selectRow',0);
  								}
								
							}
				});
		 	},
		 	onChange : function(newValue, oldValue) {
		 		if(oldValue!=undefined && oldValue!=""){
		 			$('#ship_to').combogrid('setValue','');
		 			$('#payer_to').combogrid('setValue','');
		 			$('#billing_to').combogrid('setValue','');		 			
		 		}
		 	}
});
function searcherCustomer(name1) {
	var queryParams = $('#branch_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#branch_id').combogrid("grid").datagrid('reload');
} 
//订单类型
//$('#type_id').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=549&itemId=14161',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	panelHeight : 120,
//	width : 173,
//	onSelect : function(r){
// 	} 
//});
//货币
//$('#currency_code').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	panelHeight : 120,
//	width : 173,
//	onSelect : function(r){
// 	} 
//});

//创建窗口对象
function initMaintAccount(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
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