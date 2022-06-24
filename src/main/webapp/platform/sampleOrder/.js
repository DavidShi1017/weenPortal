$(document).ready(function() {
	$('.ec').hide();
	$('#hideFrame').bind('load', promgtMsg);	
});
function cancel() {
	window.parent.closeMaintWindow();
}
//订单明细模块
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
			title : '主键',
			width : 60,
			align : 'center',
			hidden:true
		},
		{
			field : 'po_item',
			title : 'PO Item',
			width : 80,
			align : 'center',
			editor: 'text',
			
		},
		{
			field : 'material_name',
			title : 'BookPart<br>物料名称',
			width : 150,
			align : 'center',
		},
		{
			field : 'material_id',
			title : '12NC<br>物料编码',
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
			title : 'Material Type<br>物料类型',
			width : 130,
			align : 'center',

			
		},

		{
			field : 'material_groupId',
			title : 'Product Group<br>物料组',
			width : 110,
			align : 'center',

		} ,
		{
			field : 'sale_unit',
			title : 'Sale Unit<br>销售单位',
			width : 70,
			align : 'center',	

		} ,
		{
			field : 'limited_QTY',
			title : 'Limited QTY<br>限制数量', 
			width : 90,
			align : 'center',	

		} ,
		{
			field : 'pq',
			title : 'PQ<br/>起订量(每PQ个baseUnit为一个销售单位)', 
			width : 80,
			align : 'center',	
			hidden:true
		} ,
		{
			field : 'order_QTY',
			title : 'Order QTY<br>订单数量',
			width : 90,
			align : 'center',
			editor: {type:'numberbox',options:{precision:2}}
		} ,
		{
			field : 'lead_time',
			title : 'Lead Time(week)<br>生产周期',
			width : 105,
			align : 'center',
			hidden:true		
		} ,
		{
			field : 'delivery_dateStr',
			title : 'Request Date<br>客户期望交期', 
			width : 120,
			align : 'center',	
			editor:'datebox',
		} ,
		{
		field : 'confirm_dateStr',
		title : 'Confirm Delivery Date<br>确认交期',
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
			}],  
			onClickCell: function (rowIndex, field, value) {
			    beginEditing(rowIndex, field, value);
			    $('#datagrid').datagrid('beginEdit', rowIndex);
		     },
}); 


function returnOrderDetail(sodListJson){
	var sodList = eval(sodListJson);
	for(var i=0;i<sodList.length;i++){
		var sod = sodList[i];
		var rows = $('#datagrid').datagrid('getRows');
//		过滤掉重复物料
//		var sameFlag=false;
//		for(var j=0;j<rows.length;j++){
//			if(sod.material_id==rows[j].material_id){
//				sameFlag=true;
//				break;
//			}
//		}
//		if(sameFlag){
//			continue;
//		}
		$('#datagrid').datagrid('appendRow',{
			po_item:sod.po_item,
			material_name:sod.material_name,
			material_id:sod.material_id,
			material_typeId:sod.material_typeId,
			material_groupId:sod.material_groupId,
			sale_unit:sod.sale_unit,
			moq:sod.moq,
			pq:sod.pq,
			order_QTY:sod.order_QTY,
			lead_time:sod.lead_time,
			delivery_dateStr:sod.delivery_dateStr,
			confirm_dateStr:sod.confirm_dateStr,
			price:sod.price,
			remark:sod.remark
		});
	}
	$("#maintWindow").window('close');
}


function returnSampleOrderDetail(sodListJson){
	var sodList = eval(sodListJson);
	for(var i=0;i<sodList.length;i++){
		var sod = sodList[i];
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
			po_item:sod.po_item,
			material_name:sod.material_name,
			material_id:sod.material_id,
			material_typeId:sod.material_typeId,
			material_groupId:sod.material_groupId,
			sale_unit:sod.sale_unit,
			limited_QTY:sod.limited_qty,
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
	initMaintAccount(false,'800','450','Product Info','/productAction!toSearchProductForUse.jspa?isOrderItem=Y&useFor=YPD',100,20);	
}

function returnProduct(proJson){
	var today = getNowFormatDate();
	var productStr="["+proJson+"]";
	var pJson = eval(productStr);
	for(var i=0;i<pJson.length;i++){
		var product = pJson[i];
		var sameFlag=false;
		for(var j=0;j<ps.length;j++){
			if(product.material_id==ps[j]){
				sameFlag=true;
				break;
			}
		}
		if(sameFlag){
			continue;
		}
		$('#datagrid').datagrid('appendRow',{
			po_item:"",
			material_name:product.material_name,
			material_id:product.material_id,
			material_typeId:product.material_type,
			material_groupId:product.material_groupId,
			sale_unit:product.base_unit,
			limited_QTY:product.limited_qty,
			pq:product.numerator,
			order_QTY:'',
			lead_time:product.lead_time,
			delivery_dateStr:today,
			confirm_dateStr:'',
			remark:''
		});
	}
	$("#hiddenWin").window('close');
}

var delOrder="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Error', 'Please select the data you need to operate！', 'warning');
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
	initMaintAccount(false,'700','350','Import OrderDetail', '/sampleOrderAction!importExcel.jspa',100,100);
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
	var alertMsg="";
	if($('#id').val()=='0'||$('#id').val()==''){
		url="/sampleOrderAction!createSampleOrder.jspa";
	}else{
		url="/sampleOrderAction!updateSampleOrder.jspa";
	}
//	if ($('#sale_company').val()=='') {  
//		$.messager.alert('Error', "销售公司未填！", 'error');
//		return;
//	}
	if ($('#branch_id').combobox('getValue')=="") {
		$.messager.alert('Error', "Sold To Is Empty！", 'error');
		return;
	}
	if ($('#ship_to').combobox('getValue')=="") {
		$.messager.alert('Error', "Ship To Is Empty！", 'error');
		return;
	}
	if ($('#payer_to').combobox('getValue')=="") {
		$.messager.alert('Error', "Payer To Is Empty！", 'error');
		return;
	}
	if ($('#billing_to').combobox('getValue')=="") {
		$.messager.alert('Error', "Billing To Is Empty！", 'error');
		return;
	}
//	alert($('#end_customer1').val());
//	if (customer_type=='Disti' && $('#end_customer1').val()=="") {
//		$.messager.alert('Error', "Ship to company’s name Is Empty！", 'error');
//		return;
//	}
	if ($('#contact_tel').val()=="") {
		$.messager.alert('Error', "Ship to phone no./mail address Is Empty！", 'error');
		return;
	}
	if ($('#order_id').val()=='') {  
		//$.messager.alert('Error', "订单号未填！", 'error');
		alertMsg += "Purchase Order Is Empty！";
	}
	var rows = $("#datagrid").datagrid("getRows");	
	if(rows==undefined || rows.length==0){
		$.messager.alert('Error', "Please fill in the order line item！", 'error');
		return;
	}
	
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
//	if ($("#orgCode").val()=='') {
//		$.messager.alert('Error', "采购组织编码未填或格式不正确！", 'error');
//		return;
//	}
	
	var orderDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].material_id==""||rows[i].material_id==undefined) {
			$.messager.alert('Error', "Rows"+(i*1+1)+": 12NC is Empty！", 'error');
			return;
		}
		if ((rows[i].order_QTY==0)||(rows[i].limited_QTY*1.0<rows[i].order_QTY*1.0)) {
			$.messager.alert('Error', "Rows"+(i*1+1)+":Order QTY is 0 or out of limit！", 'error');
			return;
		}
		if(rows[i].delivery_dateStr==undefined||rows[i].delivery_dateStr==""){
			$.messager.alert('Error', "Rows"+(i*1+1)+": Request Date is Empty！", 'error');
			return;
		}
		if(rows[i].delivery_dateStr < getNowFormatDate()){
			$.messager.alert('Error', "Rows"+(i*1+1)+": Request Date can not be earlier than the current date！", 'error');
			return;
		}
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"po_item:"+"'"+rows[i].po_item+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"material_typeId:"+"'"+rows[i].material_typeId+"',"
		+"material_groupId:"+"'"+rows[i].material_groupId+"',sale_unit:'"+rows[i].sale_unit
		+"',limited_QTY:'"+rows[i].limited_QTY+"',order_QTY:'"+rows[i].order_QTY
		+"',lead_time:'"+rows[i].lead_time+"',delivery_date:'"+rows[i].delivery_dateStr
		+"',remark:'"+rows[i].remark+"'}";
		//"',confirm_date:'"+rows[i].confirm_dateStr+
		orderDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', alertMsg+'Sure to submit?', function(r) {
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

//创建窗口对象
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
				width : 65,
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
				width : 280
			},
			{
				field : 'country',
				title : 'city/country',
				width : 80,
				//hidden:true
			}, {
				field : 'currency_code',
				title : '货币代码',
				width : 120,
				hidden:true
			}, {
				field : 'sales',
				title : '销售人员',
				width : 120,
				hidden:true
			}]],
			toolbar : '#toolbarCustomer',
			onSelect : function(index, record){
				$('#SoldToInfo').html(record.customer_code+"/"+record.customer_name+"/"+record.country+"/"+record.address);
				if(record.country=='US'){
					window.open('http://www.digikey.com'); //打开新的页面
					return;
					//window.location.href='http://www.digikey.com';	//本页打开	
				}
				customer_type=record.customer_type;
				if(record.customer_type=='Disti'){
					$('.ec').show();					
				}else{
					$('.ec').hide();
				}
				//送达方
				$('#ship_to').combogrid({
					panelHeight : 280,
					panelWidth : 320,
					width:350,
					pagination : true,
					pageSize:10,
					multiple : false,
					editable : false,
					method : 'post',
					singleSelect : true,
					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=WE',
//					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+$('#branch_id').combobox('getValue')+'&partnerType=WE',
					idField : 'partnerId',
					textField :'nameAddress',
					columns : [[{
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
								title : 'Partner Name',
								width : 250,
							
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
					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+record.customer_code+'&partnerType=RE',
//					url : appUrl + '/customer/customerAction!getCusCompanyList.jspa?customer_code='+$('#branch_id').combobox('getValue')+'&partnerType=RE',
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
								var rows =  $('#billing_to').combogrid("grid").datagrid('getRows');

								if(rows!=undefined && rows.length!=0){
									 $('#billing_to').combogrid("grid").datagrid('selectRow',0);
								}
							
							}
				});
		 	} ,
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
	queryParams.customer_name = encodeURIComponent(name1);
	$('#branch_id').combogrid("grid").datagrid('reload');
} 

var ecParam="";
var ecFirst=true;
//终端客户
$('#end_customer').combogrid({
	panelHeight : 280,
	panelWidth : 400,
	pagination : true,
	pageSize:10,
	width:350,
//	multiple : false,
//	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	textField :'end_customer_name',
	columns : [[
//	            {
//				field : 'end_customer_groupId',
//				title : '客户集团',
//				width : 100
//			},
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
			//toolbar : '#toolbarEndCustomer',
			onChange : function(newValue, oldValue) {
				$('#end_customer1').val(newValue);
				ecFirst=true;
				if(newValue!=undefined && newValue!=null && $.trim(newValue) != ""){
					ecParam = newValue;
				}
				if(ecParam!=undefined && ecParam!=null && $.trim(ecParam) != "" && $.trim(ecParam) != "undefined"){
					$(this).combogrid("grid").datagrid('reload');
				}
//				if (newValue != null) {
//					ecFirst=true;
//					var queryParams = $('#end_customer').combogrid("grid").datagrid('options').queryParams;
//					queryParams.end_customer_name = encodeURIComponent(newValue);
//					$('#end_customer').combogrid("grid").datagrid('reload');
//				}
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
//function searcherEndCustomer(name1) {
//	var queryParams = $('#end_customer').combogrid("grid").datagrid('options').queryParams;
//	queryParams.end_customer_name = encodeURIComponent(name1);
//	$('#end_customer').combogrid("grid").datagrid('reload');
//} 




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
