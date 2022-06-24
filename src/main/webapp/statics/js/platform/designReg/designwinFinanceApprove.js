$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});
if($('#forWho').val()=="Disti"){
	//$('#cus_groupId').disabled=true;
	$('#cus_groupId').attr('disabled','disabled'); 
}
$('#clearV').click(function(){
	$('#start_dateStr').datebox('setValue',"");
	$('#end_dateStr').datebox('setValue',"");
	//setTimeout("search()",100);	
});
function loadGrid() {
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '',
				//height : 370,
				fit:true,
				striped : true,
				url : appUrl
				+ '/designReg/designRegAction!checkDesignRegDetailList.jspa',
				queryParams : {
					states : $('#state').val(),
					saler_design_statusStr: $('#saler_design_statusStr').val(),
					myself: 'finance_approve',
				},
		        loadMsg : 'Loading...',
				singleSelect : true,
				nowrap : true,
				// idField : 'dictTypeId',
				pagination : true,
				rownumbers : true,
				fitColumns : false,
				frozenColumns:[[ {
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
			 			field : 'id',
			 			title : 'ID',
			 			width : 60,
			 			align : 'center',
			 			hidden:true
			 		},
			 		{
			 			field : 'main_id',
			 			title : 'main_id',
			 			width : 150,
			 			align : 'center',
			 			hidden:true
			 		}
			 		,
			 		
			 		{
			 			field : 'saler_design_status',
			 			title : 'Saler Win Status',
			 			width : 100,
			 			align : 'center',	
						formatter : function(value, row, rec) {
							var flag = value;
							if (flag ==0) {
								return "pending";
							} else if (flag ==1){
								return "<font color='green'>Regional sales approved</font>";
							}  else if (flag ==2){
								return "<font color='blue'>Sales head approved</font>";
							}  else if (flag ==3){
								return "<font color='blue'>Sales head reject</font>";
							}  else if (flag ==4){
								return "<font color='blue'>Final approved</font>";
							}  else if (flag ==5){
								return "<font color='blue'>Final reject</font>";
							}  else if (flag ==6){
								return "<font color='blue'>Finance approved</font>";
							}  else if (flag ==7){
								return "<font color='blue'>Finance reject</font>";
							}  else if (flag ==9){
								return "<font color='blue'>Regional sales reject</font>";
							} 
						},
			 		},
			 		{
			 			field : 'drNum',
			 			title : 'DRNum',
			 			width : 80,
			 			align : 'center',				 
			 		},
//			 		{
//			 			field : 'cus_groupId',
//			 			title : 'Customer Group',
//			 			width : 150,
//			 			align : 'center',
//			 		},
			 		{
			 			field : 'customer_id',
			 			title : 'Customer',
			 			width : 100,
			 			align : 'center',
			 			hidden:true
			 		},
			 		{
			 			field : 'cus_groupId',
			 			title : 'Disti',
			 			width : 150,
			 			align : 'left',					 			
			 		},
			 		{
						title : "Disti Branch",
						field : 'disti_branch',
						width : 250,
						align : 'left'
					},
			 		{
			 			field : 'endCus_groupId',
			 			title : 'EC Group',
			 			width : 100,
			 			align : 'center',
			 			hidden:true
			 		} ,
			 		{
			 			field : 'endCus_groupName',
			 			title : 'EC Group',
			 			width : 100,
			 			align : 'center',
			 		} ,
			 		{
			 			field : 'endCus_id',
			 			title : 'End Customer',
			 			width : 100,
			 			align : 'center',	
			 			hidden:true
			 		} ,
			 		{
			 			field : 'endCus_name',
			 			title : 'End Customer',
			 			width : 200,
			 			align : 'left',	
			 		} ,]],
			 		columns : [ [
			 		{
			 			field : 'ec_contact',
			 			title : 'EC Contact', 
			 			width : 100,
			 			align : 'center',	
			 		} ,
			 		{
			 			field : 'material_id',
			 			title : '12NC',
			 			width : 120,
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
			 			field : 'material_name',
			 			title : 'BookPart',
			 			width : 150,
			 			align : 'left',
			 			//hidden:true
			 		},
			 		{
			 			field : 'price',
			 			title : 'Price',
			 			width : 100,
			 			align : 'center',
			 		} ,
			 		{
			 			field : 'usage_amount',
			 			title : 'Annual runrate',
			 			width : 100,
			 			align : 'center',
			 		},
			 		{
			 			field : 'equip_usage',
			 			title : 'Pcs/Set',
			 			width : 100,
			 			align : 'center',	
			 		} ,
			 		{
			 			field : 'value',
			 			title : 'Value',
			 			width : 100,
			 			align : 'center',
			 			formatter : function(value, row, rec) {
							var flag = row.value;
							if (flag == ''||flag==undefined) {
								return "";
							} else{	
								return flag;
							} 
						}
			 		} ,
			 		{
			 			field : 'equip_type',
			 			title : 'Application',
			 			width : 100,
			 			align : 'center',
			 			formatter : function(value, row, rec) {
							var flag = row.equip_type;
							if (flag!=undefined && flag.indexOf("-")>-1) {
								return flag.replace('-',''); 
							}else{
								return flag;
							}
						}
			 		} ,
			 		{
			 			field : 'start_dateStr',
			 			title : 'Registration Date',
			 			width : 100,
			 			align : 'center',				
			 		} ,
			 		{
			 			field : 'end_dateStr',
			 			title : 'Expiry Date', 
			 			width : 100,
			 			align : 'center',	
			 		} ,
			 		{
						field : 'shipPrice',
						title : 'POS amount', 
						width : 100,
						align : 'center',
						hidden:true
					},	
			 		{
			 			field : 'project_name',
			 			title : 'Project',
			 			width : 150,
			 			align : 'center',
			 		},
			 		{
			 			field : 'project_state',
			 			title : 'Project Status', 
			 			width : 100,
			 			align : 'center',
						formatter : function(value, row, rec) {
							var flag = row.project_state;
							if (flag ==0) {
								return "Opportunity";
							} else if (flag ==1){
								return "<font color='green'>Design_in</font>";
							}  else if (flag ==2){
								return "<font color='blue'>Design_win</font>";
							} 
						}
			 		},
//			 		{
//			 			field : 'state',
//			 			title : 'DR Approval', 
//			 			width : 100,
//			 			align : 'center',	
//
//			 		},		
			 		{
			 			field : 'tel',
			 			title : 'EC tel number',
			 			width : 100,
			 			align : 'center',					
			 		} ,
			 		{
			 			field : 'cus_remark',
			 			title : 'Disti Remark',
			 			width : 100,
			 			align : 'center',
			 		} ,
			 		{
			 			field : 'isCheck',
			 			title : 'isCheck',
			 			width : 200,
			 			align : 'center',
			 			hidden:true
			 		},
			 		{
			 			field : 'create_userName',
			 			title : 'Creator',
			 			width : 200,
			 			align : 'center',
			 		},{
			 			field : 'create_time',
			 			title : 'Create Date',
			 			width : 200,
			 			align : 'center',
			 			formatter : function(date){
							if(date==undefined){
								return "";										
							}else{
								return utcToDate(date);	
							}
						}
			 		},{
			 			field : 'design_win',
			 			title : 'Design win',
			 			width : 100,
			 			align : 'center',
			 			formatter : function(date){
							if(date==undefined){
								return "";										
							}else{
								return utcToDate(date);	
							}
						}
			 		},
			 		{
			 			field : 'audit_person',
			 			title : 'DW submitter',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_time',
			 			title : 'DW submitted date',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_person2',
			 			title : 'DW Approval-1(Sales head)',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		
			 		{
			 			field : 'audit_time2',
			 			title : 'DW Approval-1(Sales head) date',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_person3',
			 			title : 'DW Approval-2(MKT)',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_time3',
			 			title : 'DW Approval-2(MKT) date',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_person4',
			 			title : 'DW Approval-3(Finance)',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		,
			 		{
			 			field : 'audit_time4',
			 			title : 'DW Approval-3(Finance) date',
			 			width : 70,
			 			align : 'center',
			 			
			 		}
			 		
			 		]],
			 		toolbar : [
						"-",	
						{text : 'Approve',
						iconCls : 'icon-ok',
						handler : function() {
							approve(6);
						}
					}, "-", {
						text : 'Reject',
						iconCls : 'icon-cancel',
						handler : function() {
							approve(7);
						}
					}
//					,"-", {
//						text : 'Output',
//						iconCls : 'icon-outport',
//						handler : function() {
//							downLoad();
//						}
//					}
					 ],
				 onDblClickRow:function(rowIndex,rowData){
						initMaintAccount(true,'1000','550','Detail Information', '/designRegAction!toViewDesignReg.jspa?id='+rowData.main_id);
				},
				rowStyler: function(index,row){
				var rows = $('#datagrid').datagrid('getRows');
				var drNum=rows[0].drNum;
				var flag=0;
				for(var i=0;i<rows.length;i++){
					if(rows[i].drNum != drNum){
						if(flag==0){
							flag=1;
						}else if(flag==1){
							flag=0;
						}
					}
					rows[i].flag=flag;
					drNum=rows[i].drNum;
				}
				if (row.flag==1){
					return 'background:#ffffcc;';
				}
			},
			onClickCell: function (rowIndex, field, value) {	
				var rows = $('#datagrid').datagrid('getRows');
				var flag = rows[rowIndex].saler_design_status;
				console.log(flag);
				if(flag==4){	
					$('div.datagrid-toolbar a').eq(0).linkbutton('enable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('enable');
				}else{
					$('div.datagrid-toolbar a').eq(0).linkbutton('disable');
					$('div.datagrid-toolbar a').eq(1).linkbutton('disable');
				}
		     }
			});

}


function approve(mark) {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows==undefined || rows == '') {
		$.messager.alert('info', 'Please select the data item');
		return;
	}
	var ids="";
	var mark_status = 0 ;
	for(var i=0;i<rows.length;i++){
		 ids= rows[i].id +","+ ids;
		 if(rows[i].saler_design_status==4){
			 mark_status++;
		 }
	}
	if(mark_status!=rows.length){
		$.messager.alert('info', 'Only when all business approve status can be clicked!');
		return;
	}
	var url=undefined;
		url="/designRegAction!updateSalerDesignWinStatus.jspa";
	$.messager.confirm('Confirm', 'Confirmed about operate?', function(r) {
		if (r) {
			$.ajax({
				url: appUrl + "/designRegAction!updateSalerDesignWinStatus.jspa",
				data:{
					ids:ids,
					state:mark
					},				
				type:"POST",
				contentType:'application/x-www-form-urlencoded;charset=UTF-8', 
				success: function(ss){			
					console.log(ss);
					var msg =ss.result+"";
		 			if(msg==undefined||msg == null || msg == ''){
		 				$.messager.alert('Tips', "Failed!" , 'warning');
					}else{
						if(msg.lastIndexOf("Failed")>-1){
							$.messager.alert('Tips', "Failed", 'warning');
						}else{
							$.messager.alert('Tips', "Success!", 'info',function(){
								$("#datagrid").datagrid('load');
					 		});				
						}
					}
		 	    },
		 	    error : function(r) {
					$.messager.progress('close');
					$.messager.alert('Tips', "create rebate order data Failure", 'info');
				}
			});  
		}
	});

}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.drNum = $("#drNum").val();
	queryParams.project_name = encodeURIComponent($("#project_name").val());
//	queryParams.customer_id = $("#customer_id").combobox('getValue');
	queryParams.disti_branch = $("#disti_branch").val();
	queryParams.cus_groupId = $("#cus_groupId").val();
	queryParams.endCustomer_name = $("#endCus_name").val();
//	queryParams.state = encodeURIComponent($("#state").val());
	queryParams.create_userName = $('#create_userName').val();
	queryParams.states = $('#state').val();
	queryParams.material_name = $("#material_name").val();
	queryParams.equip_type = $("#equip_type").val();
	queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
	queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
	queryParams.myself = 'finance_approve',
	$("#datagrid").datagrid('load');
}

function downLoad(){
	$.messager.confirm('Confirm', 'Confirmed about  OutPort?', function(r) {
		if(r){
			var form = window.document.forms[0];
			form.action = appUrl + '/designRegAction!downloadExcelData.jspa';
			form.target = "hideFrame";
			form.submit();			
		}
	});
}


function view(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item��', 'warning');
		return;
	} else {
		initMaintAccount(true,'1000','550','Detail Information', '/designRegAction!toViewDesignReg.jspa?id='+rows[0].main_id);
	}	
}

function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item��', 'warning');
		return;
	} else {
		var endCus_groupId = rows[0].endCus_groupId==undefined?"":rows[0].endCus_groupId;
		initMaintAccount(false,'800','300','Check if the same item is already registed by other customer or not', 
				'/designRegAction!toCheck.jspa?id='+rows[0].id
				+'&customer_id='+encodeURIComponent(rows[0].customer_id)
				+'&endCus_id='+encodeURIComponent(rows[0].endCus_id)
				+'&endCus_groupId='+encodeURIComponent(endCus_groupId)
				+'&start_date='+encodeURIComponent(rows[0].start_dateStr)
				+'&end_date='+encodeURIComponent(rows[0].end_dateStr)
				+'&material_id='+encodeURIComponent(rows[0].material_id),100,30);
	}	
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


// �رմ���ҳ��
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
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
	date = date + month[str[1]] + "-" + str[2] ;//+ " " + str[3]
	return date;
}	 