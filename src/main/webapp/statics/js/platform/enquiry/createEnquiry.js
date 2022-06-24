$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);	
});

function cancel() {
	window.parent.closeMaintWindow();
}
//订单明细模块
$('#datagrid').datagrid({   
	iconCls : 'icon-list',
	title : '订单明细',
	url : appUrl +  '/enquiryAction!getEnquiryDetailList.jspa?enquiry_id='+$('#enquiry_id').val(),
	loadMsg : '数据远程载入中,请等待...',
	singleSelect : true,
	nowrap : true,
	checkbox : true,
 	required : true,
	rownumbers : true,
	height:200,
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
			field : 'material_name',
			title : 'BookPart物料名称',
			width : 150,
			align : 'center',
			editor: 'text',
			hidden:true
		},
		{
			field : 'material_id',
			title : '12NC物料编码',
			width : 150,
			align : 'center',
			editor: 'text'
		},
		{
			field : 'drNum',
			title : 'DR Number项目注册的编码',
			width : 60,
			align : 'center',
			editor: 'text'
			
		},

		{
			field : 'qty',
			title : 'QTY订购数量',
			width : 100,
			align : 'center',
			editor: {type:'numberbox',options:{precision:2}}
		} ,
		{
			field : 'target_resale',
			title : 'Target Resale目标销售价格',
			width : 100,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'target_cost',
			title : 'Target Cost 目标进货价格', 
			width : 100,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'amount',
			title : 'Value 行项目总价',
			width : 60,
			align : 'center',
			editor: {type:'numberbox',options:{precision:4}}					
		} ,
		{
			field : 'reason',
			title : 'Justification申请原因', 
			width : 60,
			align : 'center',	
			editor:'text'
		} ,
		{
			field : 'competitor',
			title : 'Competitor竞争对手',
			width : 150,
			align : 'center',
			editor:'text'
		},
		{
			field : 'start_dateStr',
			title : 'Start of Production生产启动日期',
			width : 150,
			align : 'center',
			editor:'datebox',
		},		
		{
			field : 'cus_remark',
			title : 'Cus Remark客户意见',
			width : 100,
			align : 'center',
			editor:'text'						
		}]],
			toolbar : [ "-", {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			},"-", {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					dele();
				}
			}], 
			onClickCell: function (rowIndex, field, value) {
			    beginEditing(rowIndex, field, value);
			    $('#datagrid').datagrid('beginEdit', rowIndex);
		     },
}); 


function add(){	
	$('#datagrid').datagrid('appendRow',{
		material_name:'',
		material_id:'',
		drNum:'',
		qty:'',
		target_resale:'',
		target_cost:'',
		amount:'',
		reason:'',
		competitor:'',
		start_dateStr:'',
		cus_remark:'',
     });
}

var delEnquiry="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Tips', '未选中数据！', 'warning');
		return;
	}
	 $.messager.confirm('Confirm', '确定删除选中数据?', function(r) {
		 if(r){
			 {				
				 if(row.id!=undefined){
					 delEnquiry+=","+row.id;
				}				 
				 var rowIndex = $('#datagrid').datagrid('getRowIndex', row);
				 $('#datagrid').datagrid('deleteRow', rowIndex); 
				
			 }		 
		 }
	 });
}




function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
 		$.messager.alert('Tips', successResult, 'info',function(){			
 			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}



function submit() {
	var rows = $("#datagrid").datagrid("getRows");			
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);        
	} 	
//	if ($("#orgCode").val()=='') {
//		$.messager.alert('Tips', "采购组织编码未填或格式不正确！", 'error');
//		return;
//	}
	var url=undefined;
	if($('#id').val()=='0'||$('#id').val()==''){
		 url="/enquiryAction!createEnquiry.jspa";
	}else{
		url="/enquiryAction!updateEnquiry.jspa";
	}
	
	var enquiryDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
//		if(rows[i].delivery_date_str==undefined||rows[i].delivery_date_str==""){
//			$.messager.alert('Tips', "交货日期未填或者格式不正确！", 'error');
//			return;
//		}
//		if (rows[i].num*rows[i].price!=rows[i].amount) {
//			$.messager.alert('Tips', "采购订单类型未填或者格式不正确！", 'error');
//			return;
//		}
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',start_date:'"+rows[i].start_dateStr+"',cus_remark:'"+rows[i].cus_remark+"'}";
		
		enquiryDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', '请核对信息,确定提交?', function(r) {
		if(r){						
			$('#enquiryDetailJson').val("["+enquiryDetailJson+"]");
			$('#delEnquiry').val("("+delEnquiry+")");	 
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


$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});
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