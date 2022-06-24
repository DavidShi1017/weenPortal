var loginRole;
var canEdit = false;
var currentDate=getNowFormatDate();
var representReson="";
var haveReson = false;
$(document).ready(function() {
			var loginRole = $('#loginRole').val().split("*");
			
			for(var i=0;i<loginRole.length;i++){
				var role = loginRole[i];
				if(role=="JXS"){//只有Disti可编辑
					canEdit=true;break;
				}
			}
//			if(canEdit==true && $('#state').val()==0){
//				$('#agreeBtn').show();
//			}else{
//				$('#agreeBtn').hide();
//			}
			
			if($('#fileName').html()==""){
				$('#downImg').hide();
			}else{
				$('#downImg').show();
			}
			loadGrid();
 			$('#hideFrame').bind('load', promgtMsg);
		});


function downLoadFile(){
	if($('#file_path').val()==""){
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?file_path="+$('#file_path').val();
	form.target = "hideFrame";
	form.submit();	
}
function cancel() {
	window.parent.closeMaintWindow();
}
function loadGrid() {
	$('#datagrid').datagrid({   
		iconCls : 'icon-list',
		title : '',
		url : appUrl +  '/quoteAction!getQuoteDetailList.jspa?quote_id='+$('#quote_id').val(),
		loadMsg : 'Loading...',
		singleSelect : false,
		nowrap : true,
		checkbox : true,
	 	required : true,
		rownumbers : true,
//		checkOnSelect:false,
//		selectOnCheck:false,
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
					title : "Agreement",
					field : 'isAgree',
					width : 80,
					align : 'center',
					formatter : function(value, row, rec) {
						/**
						 * 状态
						 * 0处理中，1确认
						 */
						var flag = row.isAgree;
						if (flag == '0') {
							return "";
						}else if (flag == '1') {
							return "<font color='green'>Agree</font>";
						}  else if (flag == '2') {
							return "<font color='gray'>Expired</font>";
						} 
					},
				},{
					field : 'debit_num',
					title : 'Debit Num',
					width : 120,
					align : 'center',
				},
				{
					title : "Status",
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
					hidden: true,
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
		 			field : 'material_name',
		 			title : 'BookPart',
		 			width : 150,
		 			align : 'left',	 			
		 		},
		 		{
					field : 'isDRItem',
					title : 'DR Indicator',
					width : 80,
					align : 'center',
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
		 			formatter : function(value, row, rec) {
						return formatNumber(value * 1,0,1);
					}
		 		},
		 		{
		 			field : 'qty',
		 			title : 'QTY',
		 			width : 85,
		 			align : 'center',
		 			editor: {type:'numberbox'},
		 			formatter : function(value, row, rec) {
						var flag = row.qty;
						if (flag == ''||flag==undefined) {
							return "";
						} else{	
							return formatNumber(flag * 1,0,1);
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
		 			field : 'res_qty',
		 			title : 'Remain QTY',
		 			width : 85,
		 			align : 'center',
		 			editor: {type:'numberbox',options:{disabled:true}},
		 			formatter : function(value, row, rec) {
						var flag = row.res_qty;
						if (flag == ''||flag==undefined) {
							return "";
						} else{	
						    return formatNumber(flag * 1,0,1);
						} 
					}//,hidden:true
					
		 		} ,
		 		{
		 			field : 'target_cost',
		 			title : 'Target Cost', 
		 			width : 80,
		 			align : 'center',	
		 			editor: {type:'numberbox',options:{precision:4}},
		 			formatter : function(value, row, rec) {
						var flag = row.target_cost;
						if (flag == 0||flag == ''||flag==undefined) {
							return flag;
						} else{
							flag=JSON.parse(flag*1);
							return flag.toFixed(4);
						} 
					}
		 		} ,
		 		{
		 			field : 'target_resale',
		 			title : 'Target Resale',
		 			width : 95,
		 			align : 'center',	
		 			editor: {type:'numberbox',options:{precision:4}},
		 			formatter : function(value, row, rec) {
						var flag = row.target_resale;
						if (flag == 0||flag == ''||flag==undefined) {
							return flag;
						} else{
							flag=JSON.parse(flag*1);
							return flag.toFixed(4);
						} 
					}
		 		} ,
				{
					field : 'target_amount',
					title : 'Target Amount',
					width : 100,
					align : 'center',
					editor: {type:'numberbox',options:{precision:4,disabled:true}},
					formatter : function(value, row, rec) {
						return formatNumber(value*1,4,1);
					}
				} ,
		 		{
					field : 'target_margin',
					title : 'Target Margin', 
					width : 95,
					align : 'center',
					editor:'text',
					formatter : function(value, row, rec) {
						var flag = row.target_margin;
						if (flag == ''||flag==undefined) {
							return "";
						} else  if(flag.lastIndexOf('%')==-1){
							flag=JSON.parse(flag*1);
							flag= flag.toFixed(2)+"%";
							return flag;
						} else{
							return flag;
						}
					}
				} ,
				
				{
					field : 'suggest_cost',
					title : 'Final Quoted Cost', 
					width : 125,
					align : 'center',	
					formatter : function(value, row, rec) {
						var flag = row.suggest_cost;
						if (flag == ''||flag==undefined) {
							return flag;
						} else{
							flag=JSON.parse(flag*1);
							return flag.toFixed(4);
						} 
					}
					
				} ,
				{
					field : 'suggest_resale',
					title : 'Final Quoted Resale', 
					width : 150,
					align : 'center',	
					formatter : function(value, row, rec) {
						var flag = row.suggest_resale;
						if (flag == ''||flag==undefined) {
							return flag;
						} else{
							flag=JSON.parse(flag*1);
							return flag.toFixed(4);
						} 
					}
				} ,
				{
					field : 'cus_profits_percent',
					title : 'Final Quoted Disti Margin(%)', 
					width : 195,
					align : 'center',	
					formatter:function(value, row, rec) {
						if (value ==undefined || value =="undefined")
							return "";
						else
							return value+"%";
					}
					//hidden:true	
				} ,
//				{
//					field : 'profits_percent',
//					title : 'Mfr Margin<br> 瑞能利润', 
//					width : 100,
//					align : 'center',	
//				} ,
		 		{
		 			field : 'amount',
		 			title : 'Final Quoted Amount',
		 			width : 150,
		 			align : 'center',	
					hidden:true
					
		 		}, {
					field : 'start_dateStr',
					title : 'Start Date',
					width : 100,
					align : 'center',
					editor:{type:'datebox',options:{
						onSelect:function(d){//d即选中时间
							var editors = $('#datagrid').datagrid('getEditors', editIndex);
							// 绑定EDITOR，并赋值
							var endEditor = editors[7];					
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
						},
						onChange:function(dd){
							var d ;
							if (dd == undefined || dd=="" || dd ==null) {
								return;
							}else{
							    d = new Date(dd);
								var editors = $('#datagrid').datagrid('getEditors', editIndex);
								 if (editors == undefined || editors=="" || editors ==null) {
									return;
								 }
								// 绑定EDITOR，并赋值
								var endEditor = editors[7];		
								var endEditor1 = editors[6];				
								var td = new Date();//当前时间
								td.setHours( 0, 0, 0, 0 );	
								if(d < td){
									$(this).datebox('setValue','');
									endEditor.target.datebox('setValue','');
									//$.messager.alert('Tips', ' Start date cannot be backdated. ', 'warning');
									return;
								}
								d.setMonth(d.getMonth()+3);
						    	var endDay=  d.format('yyyy-MM-dd');
								endEditor.target.datebox('setValue',endDay);
							}
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
							var startEditor = editors[6];
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
		 			width : 100,
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
							var startEditor = editors[6];
							var startDate = startEditor.target.datebox('getValue');
							var sd = new Date(startDate);
							sd.setHours( 0, 0, 0, 0 );
							if(d < sd){
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
		 		//	editor:'text',
		 			formatter : function(value, row, rec) {
						var flag = row.cus_remark;
						if (flag == 'undefined'||flag==undefined) {
							return "";
						} else if(flag.length >= 30) {
							  flag = flag.substring(0,24);
							  flag+= "...";
							  return flag;
						}else{	
							return flag;
						}  
					}
		 			
		 		},{
		 			field : 'remark',
		 			title : 'Internal Comments',
		 			width : 300,
		 			align : 'center',
		 			formatter : function(value, row, rec) {
						var flag = row.remark;
						if (flag == 'undefined'||flag==undefined) {
							return "";
						} else if(flag.length >= 30) {
							  flag = flag.substring(0,24);
							  flag+= "...";
							  return flag;
						}else{	
							return flag;
						}  
					}, 	
					hidden:canEdit
		 		}
//		 		,{
//		 			field : 'latest_userId',
//		 			title : 'Updater',
//		 			width : 100,
//		 			align : 'center',
//		 		}
		 		,{
		 			field : 'latest_time',
		 			title : 'Update Time',
		 			width : 100,
		 			align : 'center',
		 			formatter : function(date){
						if(date==undefined){
							return "";										
						}else{
							return utcToDate(date);	
						}
					}
		 		},{
		 			field : 'debit_start',
		 			title : 'Debit Start',
		 			width : 100,
		 			align : 'center',
		 			formatter : function(date){
						if(date==undefined){
							return "";										
						}else{
							return utcToDate(date);	
						}
					},					
		 		},{
		 			field : 'debit_end',
		 			title : 'Debit End',
		 			width : 100,
		 			align : 'center',
		 			formatter : function(date){
						if(date==undefined){
							return "";										
						}else{
							return utcToDate(date);	
						}
					},
		 		},{
		 			field : 'Represent',
		 			title : 'Represent',
		 			width : 100,
		 			align : 'center',
		 			formatter : function(value, row, rec) {
						var flag = row.isRepresent;
						if (flag == 'undefined'||flag==undefined) {
							return "";
						} else{	
							if (flag=='Represent' || flag=='represent' )
								return "Y";
							else
								return flag;
						} 
					}
		 		}]],
		 		toolbar : [ "-", {
					text : 'Represent',
					iconCls : 'icon-redLeft',
					handler : function() {
						edit();
					}
				}, "-", {
					text : 'Agree',
					iconCls : 'icon-ok',
					handler : function() {
						agree();
					}
				}],
				onLoadSuccess:function(){
					if(canEdit==true){									
						$(".datagrid-toolbar").show();
					}else{
						$(".datagrid-toolbar").hide();
					}		
					
					if($('#pc_state').val()=='2' || ($('#endCustomer_name').html()!='' && $('#ec_state').val()=='2')){
						$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					}else if($('#pc_state').val()=='3' || ($('#endCustomer_name').html()!='' && $('#ec_state').val()=='3')){
						$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					}else{
						$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
					}
				},
				onClickCell: function (rowIndex, field, value) {	
					var rows = $('#datagrid').datagrid('getRows');
					if(field=="cus_remark"&&rows[rowIndex].cus_remark!=undefined  && rows[rowIndex].cus_remark!=""){
						 var str = rows[rowIndex].cus_remark;
						 $.messager.alert('Remark',str,'');						
					}
					if(field=="remark"&&rows[rowIndex].remark!=undefined  && rows[rowIndex].remark!=""){
						 var str = rows[rowIndex].remark;
						 $.messager.alert('Remark',str,'');						
					}
					var auditState = rows[rowIndex].state;
					var isAgree = rows[rowIndex].isAgree;
					if(canEdit==true && isAgree==0 && (auditState==3 ||auditState==4 ||auditState==5 ||auditState==6 || auditState==7 || auditState==8)){	
//						$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
//						$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
						beginEditing(rowIndex, field, value);
						//$('#datagrid').datagrid('beginEdit', rowIndex);
						setEditing(rowIndex); 
					}else{
//						$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
//						$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
					}
			     },
			     onSelect:function(rowIndex, rowData){
			    	 if(rowData.isAgree==1||rowData.state==0||rowData.state==1||rowData.state==2){
			    		 $('#datagrid').datagrid('uncheckRow', rowIndex);
			    		 $('#datagrid').datagrid('unselectRow', rowIndex);
			    		 return;
			    	 }
			     },
			     onCheck:function(rowIndex, rowData){
			    	 if(rowData.isAgree==1||rowData.state==0||rowData.state==1||rowData.state==2){
			    		 $('#datagrid').datagrid('uncheckRow', rowIndex);
			    		 return;
			    	 }
			     } 
	
	}); 
 
}

function setRemark(remark,rowIndex,forWho ){//返回remark的值
//	  var editors = $('#datagrid').datagrid('getEditors', rowIndex); 
//	  if(editors!=undefined &&editors!=null&& editors.length!=0){
//		  var cusRemarkEditor = editors[11]; 
//		  var str = $(cusRemarkEditor.target).val()+remark; 
//		  $(cusRemarkEditor.target).val(str); 		  
//	  }else{
		    representReson=remark;
			quoteDetailJson=[];
			var rows = $('#datagrid').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){	
			var reason = (rows[i].reason ==undefined||rows[i].reason =='undefined')?'':rows[i].reason;
			var competitor = (rows[i].competitor ==undefined||rows[i].competitor =='undefined')?'':rows[i].competitor; 
			var cus_remark = (rows[i].cus_remark ==undefined||rows[i].cus_remark =='undefined')?'':rows[i].cus_remark; 
			var remark = (rows[i].remark ==undefined||rows[i].remark =='undefined')?'':rows[i].remark; 
			var profits_percent = (rows[i].profits_percent ==undefined||rows[i].profits_percent =='undefined')?'':rows[i].profits_percent; 
			cus_remark = cus_remark+representReson;
			var row= "{"+"id:"+"'"+rows[i].id+"',"+"quote_id:"+"'"+rows[i].quote_id+"',"+"row_no:"+"'"+rowIndex+"',"+"material_name:"+"'"+rows[i].material_name+"',"
			+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
			+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
			+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
			+"',cus_profits_percent:'"+encodeURIComponent(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
			+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+encodeURIComponent(profits_percent)
			+"',reason:'"+reason+"',competitor:'"+competitor
			+"',product_dateStr:'"+rows[i].product_dateStr
			+"',start_date:'"+rows[i].start_dateStr
			+"',end_date:'"+rows[i].end_dateStr
			+"',isRepresent:'Y'"
			+",cusGroup_id:'"+encodeURIComponent($.trim($('#cusGroup_id').html()))
			+"',cus_remark:'"+cus_remark+"',remark:'"+remark+"',state:'0'}";
			
			quoteDetailJson.push(row);	
			}
		  $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
				if(r){	
					$('#quoteDetailJson').val("["+quoteDetailJson+"]");
					var form = window.document.forms[0];
					form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
					form.target = "hideFrame";
					form.submit();			  						
				}
			});
		  
		  

//	  }  
}

function setEditing(rowIndex){    
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);    
    var numEditor = editors[0]; 
    var resNumEditor = editors[1];
    var costEditor = editors[2];    
    var reSaleEditor = editors[3];   
    var targetAmountEditor = editors[4]; 
    var marginEditor = editors[5];   
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
    resNumEditor.target.bind({'blur':function(){    
        calculate();    
    },'mouseleave':function(){
    	calculate();
    }});  
    targetAmountEditor.target.bind({'blur':function(){    
    	calculate();    
    },'mouseleave':function(){
    	calculate();
    }}); 
    marginEditor.target.bind({'blur':function(){    
        calculate();    
    },'mouseleave':function(){
    	calculate();
    }}
    );   
    function calculate(){  
    	var cost = costEditor.target.val();
    	var qty = numEditor.target.val();
    	resNumEditor.target.val(qty); 
    	var resale = reSaleEditor.target.val();
    	var margin = ((resale*1.00-cost*1.00)/resale*1.00)*100;
    	if(margin=='NaN'||margin=='-Infinity'){
    		$(marginEditor.target).val(""); 
    	}
    	margin = margin.toFixed(2);
    	$(marginEditor.target).val(margin+'%'); 
    	
      	var tamount = cost*qty;  
      	tamount = tamount.toFixed(4);
    	$(targetAmountEditor.target).val(tamount); 
//        if(resale*1<cost*1){
//        	  $(reSaleEditor.target).val(""); 
//        }
    }    
//    var rows = $('#datagrid').datagrid('getRows');
//    var id = rows[rowIndex].id;
    
//    var cusRemarkEditor = editors[11]; 
//    cusRemarkEditor.target.bind({'click':function(){ 
//  	  initMaintAccount(false,'400','300','Remark',
//  				'/quoteAction!toRemarkTxt.jspa?remark='+encodeURIComponent(cusRemarkEditor.target.val())+'&rowIndex='+rowIndex+'&forWho=Disti&id='+id,150,150);
//    }});
}    
//



function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
//		window.parent.closeMaintWindow();
//		window.parent.search();
		$("#datagrid").datagrid('load');
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	
	$("#datagrid").datagrid('load');
}

function closeMain() {
	$("#hiddenWin").window('close');
}
var row_no;
var quoteDetailJson=[];
function edit() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);
	} 	
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows==undefined || rows.length<=0){
		$.messager.alert('info', ' Please select item.', 'warning');
		return;
	}
	if(rows.length>1){
		$.messager.alert('info', 'Multiselect are not supported!', 'warning');
		return;
	}
	quoteDetailJson=[];
	for(var i=0;i<rows.length;i++){	
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if(rows[i].isAgree==1||rows[i].isAgree==2||rows[i].isAgree==3||rows[i].state==0||rows[i].state==1||rows[i].state==2){
			$.messager.alert('info', ' Please select item.', 'warning');
			return;//continue;
		}
		if (rows[i].qty==undefined||rows[i].qty==0||rows[i].qty=="") {
			$.messager.alert('Tips', "Rows"+(rowIndex*1+1)+"QTY is not completed yet！", 'error');
			return;
		}else if ((rows[i].qty)*1 < (rows[i].moq)*1) {
			$.messager.alert('Tips', "Rows"+(rowIndex*1+1)+"QTY shortage！！", 'error');
			return;
		}
		if (rows[i].target_resale==undefined||rows[i].target_resale==0||rows[i].target_resale=="") {
			$.messager.alert('Tips', "Rows"+(rowIndex*1+1)+"Target Resale is not completed yet！", 'error');
			return;
		}
		if (rows[i].target_cost==undefined||rows[i].target_cost==0||rows[i].target_cost=="") {
			$.messager.alert('Tips', "Rows"+(rowIndex*1+1)+"Target Cost is not completed yet！", 'error');
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
			$.messager.alert('Tips', "Rows"+(i*1+1)+": Expire Date is not completed yet！", 'error');
			return;
		}else if(rows[i].end_dateStr<rows[i].start_dateStr){
			$.messager.alert('Tips', "Rows"+(i*1+1)+": Expire Date  can not earlier than Start Date！", 'error');
			return;
		}
		if(rows[i].product_dateStr==undefined||rows[i].product_dateStr==""){
//			$.messager.alert('Tips', "Rows"+(i*1+1)+"Start Production is not completed yet！", 'error');
//			return;
		}else if(rows[i].product_dateStr<rows[i].start_dateStr){
			$.messager.alert('Tips', "Rows"+(i*1+1)+": Start Production can not earlier than Start Date！", 'error');
			return;
		}
//		if (rows[i].cus_remark==undefined ||rows[i].cus_remark=="") {//||rows[i].cus_remark.lastIndexOf($('#create_userId').val())<=-1
//			$.messager.alert('Tips', "Row"+(i*1+1)+": Comments field is required when represent",'error');
//			return;
//		}
	}
	var cus_remark = (rows[0].cus_remark ==undefined||rows[0].cus_remark =='undefined')?'':rows[0].cus_remark; 
	var representIndex = $('#datagrid').datagrid('getRowIndex', rows[0]);
	initMaintAccount(false,'400','300','Remark','/quoteAction!toRemarkTxt.jspa?remark='+encodeURIComponent(cus_remark)+'&rowIndex='+representIndex+'&forWho=Disti&id='+rows[0].id+'&useFor=represent',150,150);
}



function agree() {
	var rows1 = $('#datagrid').datagrid('getRows');
	for(var i=0;i<rows1.length;i++){
		$("#datagrid").datagrid("endEdit",i);		
	} 		
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows==undefined || rows.length<=0){
		$.messager.alert('info', ' Please select item.', 'warning');
		return;
	}
	quoteDetailJson=[];
	var cantAgree="";
	for(var i=0;i<rows.length;i++){
		var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
		if(rows[i].isAgree==2||rows[i].isAgree==1||rows[i].isAgree==3||rows[i].state==0||rows[i].state==1||rows[i].state==2||rows[i].state==6||rows[i].state==7||rows[i].state==8){
			cantAgree+="Row"+((rowIndex*1)+1)+';';
			continue;
		}
		row_no = (i*1+1)*10;
		var start_dateStr;
		var d = new Date();
		d.setHours( 0, 0, 0, 0 );	
		var start_date = new Date(rows[i].start_dateStr);
		start_date.setHours( 0, 0, 0, 0 );	
		if(d>start_date){
			start_dateStr = d.format("yyyy-MM-dd");   
		}else{
			start_dateStr = rows[i].start_dateStr;
		}
		var row= "{"+"id:"+"'"+rows[i].id+"',"+"quote_id:"+"'"+rows[i].quote_id+"',"+"row_no:"+"'"+row_no+"',"+"material_name:"+"'"+rows[i].material_name+"',"
		+"material_id:"+"'"+rows[i].material_id+"',"+"drNum:"+"'"+rows[i].drNum+"',"
		+"qty:"+"'"+rows[i].qty+"',res_qty:'"+rows[i].qty+"',target_resale:'"+rows[i].target_resale
		+"',target_cost:'"+rows[i].target_cost+"',amount:'"+rows[i].amount
		+"',cus_profits_percent:'"+escape(rows[i].cus_profits_percent)+"',suggest_resale:'"+rows[i].suggest_resale
		+"',suggest_cost:'"+rows[i].suggest_cost+"',profits_percent:'"+escape(rows[i].profits_percent)
		+"',reason:'"+rows[i].reason+"',competitor:'"+rows[i].competitor
		+"',product_dateStr:'"+rows[i].product_dateStr
		+"',start_date:'"+start_dateStr
		+"',end_date:'"+rows[i].end_dateStr
		+"',isRepresent:'"+rows[i].isRepresent
		+"',cusGroup_id:'"+escape($.trim($('#cusGroup_id').html()))
		+"',cus_remark:'"+escape(rows[i].cus_remark)+"',remark:'"+escape(rows[i].remark)+"',state:'0'}";
		quoteDetailJson.push(row);		
	} 
	//+"',cus_remark:'"+encodeURIComponent(rows[i].cus_remark)+"',remark:'"+encodeURIComponent(rows[i].remark)
//	if(cantAgree!=""){
//		cantAgree="系统自动过滤掉不满足要求的"+cantAgree;
//	}
	if(quoteDetailJson.length<rows.length){
		$.messager.alert('info', 'Only When quote status turns to approved can SND trigger.', 'warning');
	}	
	if(quoteDetailJson.length<=0){		
		return;
	}
	$.messager.confirm('Confirm', 'Confirm to agree the quote?', function(r) {
		if(r){
			$('#quoteDetailJson').val('['+quoteDetailJson+']');	 
			var form = window.document.forms[0];
			form.action = appUrl + '/quoteAction!agreeQuoteDetail.jspa';
			form.target = "hideFrame";
			//alert(quoteDetailJson);
			form.submit();			
		}
	});

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

Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  

function initMaintAccount(ffit,widte,height,title,url,l,t) {
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