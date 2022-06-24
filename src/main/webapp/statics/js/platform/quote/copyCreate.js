$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);		
});

function applyEC(){
	initMaintAccount(false,'850','400','Reg End Customer', '/endCustomerAction!toCreateEndCustomer.jspa',100,30);	
}
var currentDate=getNowFormatDate();
//上传附件
function upload(){
	  initMaintAccount(false,'500','300','File upload', '/file/fileAction!toImportFile.jspa',150,150);
}
//上传附件返回信息
function returnUploadFile(fileStr,fileName){ 
		var filePath = fileStr;
		var paths = fileName.split("\\");
	    var fileName = paths[paths.length-1];
		$('#old_file_name').val(fileName);
		$('#file_path').val(filePath);
}

////总金额类型
//$('#total_type').combobox({
//	url : appUrl
//			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=561',
//	valueField : 'itemValue',
//	textField : 'itemName',
//	multiple : false,
//	editable : false,
//	panelHeight : 120,
//	width : 170,
//	onSelect : function(r){
// 	} 
//});

//var datass = [{ "id":"0", "text":"No" },{  "id":"1", "text":"Yes" }];
//$('#assembly_id').combobox({    
//	data:datass,    
//	    valueField:'id',    
//	    textField:'text', 
//	    panelHeight : 'auto',
//	    editable:false
//}); 

function pickedFunc(date){             
	var year = date.split("-")[0];
 	var month = date.split("-")[1]*1+1;
 	var day = date.split("-")[2]*1;
 	month=month*1+3;
 	if(month>12){
 		month=month*1-12;
 		year=year*1+1;
 	}
     if (month*1 >= 1 && month*1 <= 9) {
         month = "0" + month;
     }
     if (day*1 >= 0 && day*1 <= 9) {
     	day = "0" + day;
     }

 	var endday = year+"-"+month+"-"+day;
 	$('#latest_expire').val(endday);
 	//$('#latest_expire').datebox('setValue',endday);  
	 
}  
 
//$('#start_date1').datebox({
//    onSelect: function(date){
//    	var year = date.getFullYear();
//    	var month = date.getMonth()+1;
//    	var day = date.getDate();
//    	month=month*1+3;
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
//
//    	var endday = year+"-"+month+"-"+day;
//    	 
//    	$('#latest_expire').datebox('setValue',endday);      
//    }
//}); 

function cancel() {
	window.parent.closeMaintWindow();
}
//订单明细模块
$('#datagrid').datagrid({   
	iconCls : 'icon-list',
	title : '',
	url : appUrl +  '/quoteAction!getCopyQDList.jspa?quote_id='+$('#quote_id').val()+'&id='+$('#id').val(),
	queryParams : {
		office_id:$('#salesOrg').val(),
		currency_code:$('#currency_code').val(),
		customer_id:$('#payerTo').val(),
	},	
	loadMsg : 'Loading...',
	singleSelect : true,
	nowrap : false,
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
			title : '主键',
			width : 100,
			align : 'center',
			hidden:true
		},
		{
			title : "Quote Status",
			field : 'state',
			width : 80,
			align : 'center',
			formatter : function(value, row, rec) {
				var flag = row.state;
				if (flag == '9') {
					return "<font color='red'>Deleted</font>";
				}else if (flag == '0') {
					return "<font color='black'>Pending</font>";
				}else if (flag == '1') {
					return "<font color='black'>Pending</font>";
				}else if (flag == '2') {
					return "<font color='black'>Pending</font>";
				}else if (flag == '3') {
					return "<font color='green'>Approved</font>";
				}else if (flag == '4') {
					return "<font color='green'>Approved</font>";
				}else if (flag == '5') {
					return "<font color='green'>Approved</font>";
				}else if (flag == '6') {
					return "<font color='red'>Reject</font>";
				}else if (flag == '7') {
					return "<font color='red'>Reject</font>";
				}else if (flag == '8') {
					return "<font color='red'>Reject</font>";
				} else{
					return flag;
				} 
			}
		},
		{
			field : 'material_id',
			title : '12NC',
			width : 120,
			align : 'center',
			hidden: true
		},
		{
			field : 'material_name',
			title : 'BookPart',
			width : 150,
			align : 'left',
		},
		{
			field : 'drNum',
			title : 'DR Num',
			width : 80,
			align : 'center',		
		},
		{
			field : 'moq',
			title : 'MOQ',
			width : 80,
			align : 'center',		
		},
		{
			field : 'cost',
			title : 'Cost',
			width : 80,
			align : 'center',	
			hidden:true
		},
		{
			field : 'qty',
			title : 'QTY',
			width : 70,
			align : 'center',
			editor: {type:'numberbox'},
			formatter : function(value, row, rec) {
				var flag = row.qty;
				if (flag == ''||flag==undefined) {
					return "";
				} else{	
					return flag*1;
				} 
			}
		} ,
		{
			field : 'pbMpp',
			title : 'PB/MPP', 
			width : 70,
			align : 'center',	
			formatter : function(value, row, rec) {
				var flag = row.pbMpp;
				if (flag == 0||flag == ''||flag==undefined) {
					return "<font color='red'>No Price</font>";
				} else{
					flag=JSON.parse(flag*1);
					return flag.toFixed(4);
					
				} 
			},
		} ,
		{
			field : 'target_cost',
			title : 'Target Cost', 
			width : 80,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'target_resale',
			title : 'Target Resale',
			width : 90,
			align : 'center',	
			editor: {type:'numberbox',options:{precision:4}}
		} ,
		{
			field : 'target_amount',
			title : 'Target Amount',
			width : 80,
			align : 'center',
			editor: {type:'numberbox',options:{precision:4,disabled:true}},
		} ,
 		{
			field : 'target_margin',
			title : 'Target Margin(%)', 
			width : 100,
			align : 'center',
			editor: {type:'numberbox',options:{precision:2,disabled:true}},
			formatter : function(value, row, rec) {
				var flag = row.target_margin;
				if (flag == ''||flag==undefined||flag=='undefined') {
					return 0;
				} else{
					flag=JSON.parse(flag*1);
					return flag.toFixed(2);
				} 
			}
		} ,
		{
			field : 'cus_profits_percent',
			title : 'Disti Margin', 
			width : 70,
			align : 'center',	
			hidden: true	
		} ,
		{
			field : 'suggest_resale',
			title : 'Suggested Resale', 
			width : 70,
			align : 'center',	
			hidden:true
		} ,
		{
			field : 'suggest_cost',
			title : 'Suggested Cost', 
			width : 70,
			align : 'center',	
			hidden:true	
		} ,
		{
			field : 'profits_percent',
			title : 'Mfr Margin', 
			width : 70,
			align : 'center',	
			hidden:true
		} ,
		{
			field : 'amount',
			title : 'Value',
			width : 70,
			align : 'center',	
			hidden:true				
		} ,
		{
			field : 'start_dateStr',
			title : 'Start Date',
			width : 100,
			align : 'center',
			editor:{type:'datebox',options:{
				onSelect:function(d){//d即选中时间
					var editors = $('#datagrid').datagrid('getEditors', editIndex);
					// 绑定EDITOR，并赋值
					var endEditor = editors[6];					
					var td = new Date();//当前时间
					td.setHours( 0, 0, 0, 0 );	
					if(d < td){
						$(this).datebox('setValue','');
						endEditor.target.datebox('setValue','');
						$.messager.alert('Tips', ' Start date cannot be backdated. ', 'warning');
						return;
					}
					d.setMonth(d.getMonth()+3);
			    	var endDay=  d.format('yyyy-MM-dd');
					endEditor.target.datebox('setValue',endDay);
				}
			}},
		},		
		{
			field : 'end_dateStr',
			title : 'Expire Date',
			width : 100,
			align : 'center',
			editor:{type:'datebox',options:{
				onSelect:function(d){
					var editors = $('#datagrid').datagrid('getEditors', editIndex);
					var td = new Date();
					td.setHours( 0, 0, 0, 0 );	
					if(d < td){
						$(this).datebox('setValue','');
						$.messager.alert('Tips', 'The Expire Date cannot be earlier than the current date！', 'warning');
						return;
					}
					var startEditor = editors[5];
					var startDate = startEditor.target.datebox('getValue');
					var sd = new Date(startDate);
					if( sd>= d){
						$(this).datebox('setValue','');
						$.messager.alert('Tips', 'The Expire Date must later than the Start Date！', 'warning');
						return;
					}
				}
			}}
		},	
		{
 			field : 'product_dateStr',
 			title : 'Start Production',
 			width : 105,
 			align : 'center',
 			editor:{type:'datebox',options:{
				onSelect:function(d){
					var editors = $('#datagrid').datagrid('getEditors', editIndex);
					var td = new Date();
					td.setHours( 0, 0, 0, 0 );	
					if(d < td){
						$(this).datebox('setValue','');
						$.messager.alert('Tips', 'The Start Production cannot be earlier than the current date！', 'warning');
						return;
					}
					var startEditor = editors[5];
					var startDate = startEditor.target.datebox('getValue');
					var sd = new Date(startDate);
					sd.setHours( 0, 0, 0, 0 );	
					if( d < sd){
						$(this).datebox('setValue','');
						$.messager.alert('Tips', 'The Start Production cannot be earlier than the Start Date！', 'warning');
						return;
					}
				}
			}}
 		},
		{
			field : 'reason',
			title : 'Justification', 
			width : 90,
			align : 'center',	
			editor:{type:'combobox',options:{
				url : appUrl
				+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=556',
					valueField : 'itemName', //存储字段码
					textField : 'itemName', //显示字段值
					editable:false,
					panelHeight:150,
			      onSelect:function(value){
				   }
		       }
		    },
		    formatter : function(value, row, rec) {
				var flag = row.reason;
				if (flag == 'undefined'||flag==undefined) {
					return "";
				} else{	
					return flag;
				} 
			}
		} ,
		{
			field : 'competitor',
			title : 'Competitor',
			width : 90,
			align : 'center',
			editor:{type:'combobox',options:{
				url : appUrl
				+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=557',
					valueField : 'itemName', //存储字段码
					textField : 'itemName', //显示字段值
					editable:false,
					panelHeight:150,
			      onSelect:function(value){
				   }
		       }
		    },
		    formatter : function(value, row, rec) {
				var flag = row.competitor;
				if (flag == 'undefined'||flag==undefined) {
					return "";
				} else{	
					return flag;
				} 
			}
		},
		{
			field : 'cus_remark',
			title : 'Comments',
			width : 100, 
			align : 'center',
			editor:'text',
			formatter : function(value, row, rec) {
				var flag = row.cus_remark;
				if (flag == 'undefined'||flag==undefined) {
					return "";
				} else{	
					return flag;
				} 
			}
		},		
		{
			field : 'remark',
			title : 'Internal Comments',
			width : 100,
			align : 'center',
			hidden:true	,
			formatter : function(value, row, rec) {
				var flag = row.remark;
				if (flag == 'undefined'||flag==undefined) {
					return "";
				} else{	
					return flag;
				} 
			}
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
			, "-", {
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
				var rows = $('#datagrid').datagrid('getRows');
				if((rows[rowIndex].state==0)||(rows[rowIndex].state==1)||(rows[rowIndex].state==2)){									
					beginEditing(rowIndex, field, value);
					//$('#datagrid').datagrid('beginEdit', rowIndex);
					setEditing(rowIndex); 
				}
		     },
				onSelect : function(rowIndex, rowData) {
				},
//				onSelect : function(rowIndex, rowData) {//仅待审状态的可修改
//					var state = rowData.state;
//					 if(state==0||state==1||state==2){
//						$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
//					}else{
//						$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
//					}
//				},
}); 


//计算项目总价=订购数量*目标销售价
//客户利润=(resale*1-cost*1)/resale*1;;
function setEditing(rowIndex){    
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);    
    var numEditor = editors[0];   
    var costEditor = editors[1];    
    var reSaleEditor = editors[2]; 
    var targetAmountEditor = editors[3]; 
    var marginEditor = editors[4]; 
//    var startEditor = editors[5]; 
//    var endEditor = editors[6]; 
//    startEditor.target.bind({'blur':function(){
//    	alert(2);
//    },'mouseleave':function(){
//    	alert(3);
//    }}); 
    costEditor.target.bind({'blur':function(){    
    	calculate();    
    },'mouseleave':function(){
    	calculate();
    }});    
    reSaleEditor.target.bind({'blur':function(){    
        calculate();    
    },'mouseleave':function(){
    	calculate();
    }});  
    
    numEditor.target.bind({'blur':function(){    
        calculate();    
    },'mouseleave':function(){
    	calculate();
    }});      
    targetAmountEditor.target.bind({'blur':function(){    
    	calculate();    
    },'mouseleave':function(){
    	calculate();
    }}); 
    function calculate(){  
    	var num = numEditor.target.val();
    	var cost = costEditor.target.val();
    	var resale = reSaleEditor.target.val();
        if(resale*1<cost*1){
        	  $(reSaleEditor.target).val(""); 
        	  resale=0;
        }
      	var tamount = cost*num;  
      	tamount = tamount.toFixed(4);
    	$(targetAmountEditor.target).val(tamount); 
        var targetProfit = ((resale*1.00-cost*1.00)/resale*1.00)*100;

        targetProfit = targetProfit.toFixed(2);
     	$(marginEditor.target).val(targetProfit); 
        if(targetProfit=='NaN'||targetProfit=='-Infinity'){
        	  $(marginEditor.target).val(""); 
         }
        

//        var total=0*1;
//        var rows = $("#datagrid").datagrid("getRows");
//    	for(var i=0;i<rows.length;i++){
//    		$("#datagrid").datagrid("endEdit",i);    
//    		total += rows[i].qty*rows[i].target_cost;   	
//    	} 
//    	total+=num*cost;
//    	$('#total_amount').val(total);

    }  
    
    
    var cusRemarkEditor = editors[10]; 
    cusRemarkEditor.target.bind({'click':function(){ 
  	  initMaintAccount(false,'400','300','Remark',
  				'/quoteAction!toRemarkTxt.jspa?remark='+encodeURIComponent(cusRemarkEditor.target.val())+'&rowIndex='+rowIndex+'&forWho=Disti'
  				,150,150);
    }});
}    

function setRemark(remark,rowIndex,forWho ){
	  var editors = $('#datagrid').datagrid('getEditors', rowIndex);   
	  var cusRemarkEditor = editors[10]; 
		  var str = $(cusRemarkEditor.target).val()+remark; 
		  $(cusRemarkEditor.target).val(str); 
}
function closeMain() {
	$("#hiddenWin").window('close');
}

//$(".datebox-current").click(function(){alert("*");});

//
//导入excel数据
function importExcel(){
	if ($('#disti_branch').combobox('getValue')=='') {  
		$.messager.alert('Tips', "Please choose Disti Branch first！", 'error');
		return;
	}	
	initMaintAccount(false,'700','350','Import QuoteDetail', '/quoteAction!importExcel.jspa?currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#payerTo').val(),100,100);
}
//模板下载
function downloadExcelModel(){
		$.messager.confirm('Tip', 'Download quote mass upload template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("quoteDetail.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}
//excel添加行项目
function returnQuoteDetail(qdListJson){
	var qdList = eval(qdListJson);
	for(var i=0;i<qdList.length;i++){
		var qd = qdList[i];
		var rows = $('#datagrid').datagrid('getRows');
		//过滤掉重复物料
		var sameFlag=false;
		for(var j=0;j<rows.length;j++){
			if(qd.material_id==rows[j].material_id){
				sameFlag=true;
				break;
			}
		}
		if(sameFlag){
			continue;
		}
		$('#datagrid').datagrid('appendRow',{
			state:0,
			material_name:qd.material_name,
			material_id:qd.material_id,
			drNum:'',
			cost:qd.cost,
			moq:qd.moq,
			qty:qd.qty,
			pbMpp:qd.pbMpp,
			target_resale:qd.target_resale,
			target_cost:qd.target_cost,
			target_amount:qd.target_amount,
			target_margin:qd.target_margin,
			cus_profits_percent:'',
			suggest_resale:'',
			suggest_cost:'',
			profits_percent:'',
			amount:'',
			reason:qd.reason,
			competitor:qd.competitor,
			start_dateStr:qd.start_dateStr,
			end_dateStr:qd.end_dateStr,
			product_dateStr:qd.product_dateStr,
			cus_remark:qd.cus_remark,
			remark:'',
		});
	}
	$("#hiddenWin").window('close');
}


//新增一行
var ps=[];
function add(){	
	if ($('#disti_branch').combobox('getValue')=='') {  
		$.messager.alert('Tips', "Please choose Disti Branch first！", 'error');
		return;
	}	
//	if ($('#customer_id').val()=='') {  
//		$.messager.alert('Tips', "Customeris not completed yet！", 'error');
//		return;
//	}
//	if ($('#purchaseCustomer_id').combobox('getValue')=="") {
//		$.messager.alert('Tips', "Purchase Customeris not completed yet！", 'error');
//		return;
//	}

	ps=[];
	var rows = $("#datagrid").datagrid("getRows");
	for ( var i = 0; i < rows.length; i++) {
		$("#datagrid").datagrid("endEdit", i);
		ps.push(rows[i].material_id);
	}
	initMaintAccount(false,'800','450','Product','/productAction!toSearchProductForUse.jspa?isQuoteItem=Y&currency_code='+$('#currency_code').val()+'&office_id='+$('#salesOrg').val()+'&customer_id='+$('#payerTo').val(),100,20);	
}

function returnProduct(proJson){
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
		state:0,
		material_name:product.material_name,
		material_id:product.material_id,
		drNum:'',
		cost:product.cost,
		moq:product.moq,
		qty:'',
		pbMpp:product.pbPrice,
		target_resale:'',
		target_cost:'',
		target_margin:'',
		cus_profits_percent:'',
		suggest_resale:'',
		suggest_cost:'',
		profits_percent:'',
		amount:'',
		reason:'',
		competitor:'',
		start_dateStr:'',
		end_dateStr:'',
		product_dateStr:'',
		cus_remark:'',
		remark:'',
     });
	}
	$("#hiddenWin").window('close');
}

function closeMaintWindow(){
	$("#hiddenWin").window('close');	
}

var delQuote="0";
function dele(){
	var row = $('#datagrid').datagrid('getSelected');
	if (row.length==0){
		$.messager.alert('Tips', 'Please select the data item！', 'warning');
		return;
	}
	 $.messager.confirm('Confirm', 'Confirmed about  delete?', function(r) {
		 if(r){
			 {				
				 if(row.id!=undefined){
					 delQuote+=","+row.id;
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
	if ($('#disti_branch').combobox('getValue')=='') {  
		$.messager.alert('Tips', "Disti Branch is not completed yet！", 'error');
		return;
	}	
	if ($('#project_name').val()=='') {  
		$.messager.alert('Tips', "Project is not completed yet！", 'error');
		return;
	}
	if ($('#purchaseCustomer_id').combobox('getValue')=="") {
		$.messager.alert('Tips', "Purchase Customeris not completed yet！", 'error');
		return;
	}
	if ($('#endCustomer_id').combobox('getValue')=="") {
		$.messager.alert('Tips', "End Customeris not completed yet！", 'error');
		return;
	}
	


	var url="/quoteAction!createQuote.jspa";	
	var rows = $("#datagrid").datagrid("getRows");
	if(rows==undefined||rows.length==0){
		$.messager.alert('Tips', "Please fill in items info of Quote！", 'error');
		return;
	}
	var total=0;
	for(var i=0;i<rows.length;i++){
		$("#datagrid").datagrid("endEdit",i);    
		total += rows[i].qty*rows[i].target_cost;
	} 	
    $('#total_amount').val(total);
    if($('#total_type').val()!=""&&total*1 < ($('#total_type').val()*1)){//大于字典配置的总控制金额
    	$.messager.alert('error',"Total amount is less than"+$('#total_type').val()+$('#currency_code').val()+"！", 'error');
		return;
    }
    
	var quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){
		row_no = (i*1+1)*10;
		//var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if (rows[i].qty==undefined||rows[i].qty==0||rows[i].qty=="") {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"QTY is not completed yet！", 'error');
			return;
		}else if ((rows[i].qty)*1 < (rows[i].moq)*1) {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Part quantity must be greater than MOQ!", 'error');
			return;
		}
		if (rows[i].pbMpp==undefined||rows[i].pbMpp==0||rows[i].pbMpp=="") {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"PB/MPP NO PRICE，Pls Contact Sales OR Del！", 'error');
			return;
		}
		if (rows[i].target_resale==undefined||rows[i].target_resale==0||rows[i].target_resale=="") {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Target Resale is not completed yet！", 'error');
			return;
		}
		if (rows[i].target_cost==undefined||rows[i].target_cost==0||rows[i].target_cost=="") {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Target Cost is not completed yet！", 'error');
			return;
		}
//		if (rows[i].target_cost*1<rows[i].pbMpp*1) {
//			$.messager.alert('Tips', "Rows"+(i*1+1)+"Target Cost must greater than PB/MPP！", 'error');
//			return;
//		}
		if (rows[i].target_cost*1 > rows[i].target_resale*1) {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Target Cost must less than target resale！", 'error');
			return;
		}
		if (rows[i].target_cost*1 >= rows[i].pbMpp*1) {
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Target Cost must less than PB/MPP！", 'error');
			return;
		}
		if(rows[i].start_dateStr==undefined||rows[i].start_dateStr==""){
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Start Date is not completed yet！", 'error');
			return;
		}else if(rows[i].start_dateStr<currentDate){
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Start Date  can not earlier than today！", 'error');
			return;
		}
		if(rows[i].end_dateStr==undefined||rows[i].end_dateStr==""){
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Expire Date is not completed yet！", 'error');
			return;
		}else if(rows[i].end_dateStr<rows[i].start_dateStr){
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Expire Date  can not earlier than Start Date！", 'error');
			return;
		}
		if(rows[i].product_dateStr==undefined||rows[i].product_dateStr==""){
//			$.messager.alert('Tips', "Rows"+(i*1+1)+"Start Production is not completed yet！", 'error');
//			return;
		}else if(rows[i].product_dateStr<rows[i].start_dateStr){
			$.messager.alert('Tips', "Rows"+(i*1+1)+"Start Production can not earlier than Start Date！", 'error');
			return;
		}
		var material_name = (rows[i].material_name ==undefined||rows[i].material_name =='undefined')?'':rows[i].material_name; 
		var drNum = (rows[i].drNum ==undefined||rows[i].drNum =='undefined')?'':rows[i].drNum; 
		var reason = (rows[i].reason ==undefined||rows[i].reason =='undefined')?'':rows[i].reason;
		var competitor = (rows[i].competitor ==undefined||rows[i].competitor =='undefined')?'':rows[i].competitor; 
		var cus_remark = (rows[i].cus_remark ==undefined||rows[i].cus_remark =='undefined')?'':rows[i].cus_remark; 
		var remark = (rows[i].remark ==undefined||rows[i].remark =='undefined')?'':rows[i].remark; 
		var profits_percent = (rows[i].profits_percent ==undefined||rows[i].profits_percent =='undefined')?'':rows[i].profits_percent; 
		
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+material_name+"',"
		+"material_id:'"+rows[i].material_id+"',drNum:"+"'"+drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+escape(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+escape(profits_percent)
		+"',reason:'"+reason+"',competitor:'"+competitor
		+"',product_dateStr:'"+rows[i].product_dateStr
		+"',start_date:'"+rows[i].start_dateStr
		+"',end_date:'"+rows[i].end_dateStr
		+"',state:'"+0    //修改时状态置零重新审批
		+"',cost:'"+rows[i].cost  
		+"',moq:'"+rows[i].moq  
		+"',pbMpp:'"+rows[i].pbMpp  
		+"',cus_remark:'"+escape(cus_remark)+"',remark:'"+escape(remark)+"'}";
		//+"',end_date:'"+rows[i].end_dateStr
		quoteDetailJson.push(row);				
	}

	  $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
		if(r){						
			$('#quoteDetailJson').val('['+quoteDetailJson+']');
			$('#delQuote').val("("+delQuote+")");	 
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
	
 
}


var editIndex = undefined;
function beginEditing (rowIndex, field, value) {
//	alert(rowIndex+"--->"+editIndex);
    if (rowIndex != editIndex){
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
             editIndex = rowIndex;
        } 
//        else {
//            $('#datagrid').datagrid('selectRow', editIndex);
//         }
    }else{
    	$('#datagrid').datagrid('endEdit', rowIndex);
    	$('#datagrid').datagrid('beginEdit', rowIndex);
    }
}
function endEditing () {
    if (editIndex == undefined) { return true; }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
         $('#datagrid').datagrid('endEdit', editIndex);//关闭编辑上一行
       //$('#datagrid').datagrid('selectRow', editIndex);
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

var pcFirst = true;
//PC客户
$('#purchaseCustomer_id').combogrid({
	panelHeight : 365,
	panelWidth : 500,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	queryParams:{
		end_customer_name : encodeURIComponent($("#purchaseCustomer_name").val()),
	},
	idField : 'end_customer_id',
	textField :'end_customer_name',
	columns : [[{
				field : 'end_customer_groupName',
				title : 'PC Group',
				width : 100
			}, {
				field : 'end_customer_name',
				title : 'PC Name',
				width : 200
			}, {
				field : 'country',
				title : 'Country',
				width : 70
			}, {
				field : 'city',
				title : 'city',
				width : 70
			}]],
			onSelect : function(index, record) {
				$('#pcGroup_name').val(record.end_customer_groupName);
				$('#pcGroup_id').val(record.end_customer_groupId);
				$('#purchaseCustomer_name').val(record.end_customer_name);
			},
			onLoadSuccess: function(data) {
				if(pcFirst){					
					$('#purchaseCustomer_id').combogrid('grid').datagrid('getPager').pagination("select", 1);
					pcFirst=false;
				}

			},
			toolbar : '#toolbarPurchaseCustomer',
});
function searcherPurchaseCustomer(name1) {
	pcFirst = true;
	var queryParams = $('#purchaseCustomer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#purchaseCustomer_id').combogrid("grid").datagrid('reload');
} 

var ecFirst = true;
//终端客户
$('#endCustomer_id').combogrid({
	panelHeight : 365,
	panelWidth : 500,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
	queryParams:{
		end_customer_name : encodeURIComponent($("#endCustomer_name").val()),
	},
	idField : 'end_customer_id',
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
				title : 'city',
				width : 70
			}]],
			onSelect : function(index, record) {
				$('#ecGroup_name').val(record.end_customer_groupName);
				$('#ecGroup_id').val(record.end_customer_groupId);
				$('#endCustomer_name').val(record.end_customer_name);
			},
			onLoadSuccess: function(data) {
				if(ecFirst){					
					$('#endCustomer_id').combogrid('grid').datagrid('getPager').pagination("select", 1);
					ecFirst=false;
				}
//				var find = false;
//				if($('#endCustomer_id').combobox('getValue')==''){
//					return;
//				}
//				$.each(data.rows, function(index, value) {
//	                if ($('#endCustomer_id').combobox('getValue') == value.end_customer_id) { // value.id与idField属性对应的值
//	                    find = true;// 循环当前请求页面数据，如果取出的值与默认值一致，则设置成找到
//	                }
//	            });
//	            if (!find) {
//	                // combogrid获取下一页页码
//	                var nextPage = $('#endCustomer_id').combogrid('grid').datagrid('options').pageNumber + 1;
//	                $('#endCustomer_id').combogrid('grid').datagrid('getPager').pagination("select", nextPage);
//	            }
			},
			toolbar : '#toolbarEndCustomer',
});
function searcherEndCustomer(name1) {
	ecFirst = true;
	var queryParams = $('#endCustomer_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.end_customer_name = encodeURIComponent(name1);
	$('#endCustomer_id').combogrid("grid").datagrid('reload');
} 



$('#disti_branch').combogrid({
	panelHeight : 280,
	panelWidth : 300,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getDistiBranchList.jspa?disti_name='+ encodeURIComponent($('#cusGroup_id').val()),
	queryParams:{
		disti_branch : encodeURIComponent($("#disti_branch").val()),
	},
	idField : 'disti_branch',
	textField :'disti_branch',
	columns : [[{
				field : 'disti_branch',
				title : 'Disti Branch',
				width : 200
			}, {
				field : 'pricing_region',
				title : 'Pricing Region',
				width : 100
			}, {
				field : 'currency',
				title : 'Currency',
				width : 70
			}]],
			onSelect : function(index, record) {
				$('#salesOrg').val(record.pricing_region);
				$('#currency_code').val(record.currency);
				$('#payerTo').val(record.payer_to);
				$("#datagrid").datagrid('load',{
					office_id:$('#salesOrg').val(),
					currency_code:$('#currency_code').val(),
					customer_id:$('#payerTo').val(),
				});
				var itemId="";
				if($('#currency_code').val()=="USD"){
					itemId = 14209;
				}else if($('#currency_code').val()=="EUR"){
					itemId = 14210;
					$.ajax({
						type : "post",
						url : appUrl+ '/designRegAction!getDictOfWeen.jspa?dictTypeId=563&itemId='+14213,
						success : function(result) {
							$('#rate').val(result[0].itemValue);
						}
					});
				}
				$.ajax({
					type : "post",
					url : appUrl+ '/designRegAction!getDictOfWeen.jspa?dictTypeId=561&itemId='+itemId,
					success : function(result) {
						$('#total_type').val(result[0].itemValue);
					}
				});
		},
			toolbar : '#toolbarDistiBranch',
});
function searcherDistiBranch(name1) {
	var queryParams = $('#disti_branch').combogrid("grid").datagrid('options').queryParams;
	queryParams.disti_branch = encodeURIComponent(name1);
	$('#disti_branch').combogrid("grid").datagrid('reload');
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
	//date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	date = date + month[str[1]] + "-" + str[2];
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