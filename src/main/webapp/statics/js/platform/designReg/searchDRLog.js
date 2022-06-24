$(document).ready(function() {
	loadGrid(); 
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						fit:true,
						striped : true,
						url : appUrl
						+ '/designReg/designRegAction!getDesignRegDetailLogList.jspa',
						queryParams: {
							id : $("#id").val()
						},		
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						fitColumns : false,
					    columns : [[
					                 {
					 		field : 'id',
					 		align : 'center',
					 		checkbox : false,
					 		hidden:true
					 	},{
				 			field : 'type',
				 			title : 'DR Progress',
				 			width : 120,
				 			align : 'center',
							formatter : function(value, row, rec) {
								var flag = row.type;
								if (flag == 0) {
									return "<font color='black'>DR Create</font>";
								} else if (flag == 1) {
									return "<font color='black'>DR Change</font>";
								} else if (flag == 2) {
									return "<font color='green'>Sales Approve</font>";
								} else if (flag == 3) {
									return "<font color='red'>Sales Reject</font>";
								} else if (flag == 10) {
									return "<font color='black'>DW Submit</font>";
								} else if (flag == 11) {
									return "<font color='green'>DW Approve-1</font>";
								} else if (flag == 12) {
									return "<font color='red'>DW Reject-1</font>";
								} else if (flag == 13) {
									return "<font color='green'>DW Approve-2</font>";
								} else if (flag == 14) {
									return "<font color='red'>DW Reject-2</font>";
								} else if (flag == 15) {
									return "<font color='green'>DW Approve-3</font>";
								} else if (flag == 16) {
									return "<font color='red'>DW Reject-3</font>";
								} else if (flag == 17) {
									return "<font color='black'>POS Turn Win</font>";
								} 
							},
				 		},{
				 			field : 'mp_schedule',
				 			title : 'MP Schedule ',
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
				 			field : 'usage_amount',
				 			title : 'Annual Runrate', 
				 			width : 100,
				 			align : 'center',	
				 		} ,
				 		{
				 			field : 'price',
				 			title : 'Price',
				 			width : 60,
				 			align : 'center',	
				 		} ,						
						{
							field : 'equip_usage',
							title : 'Pcs/Set', 
							width : 60,
							align : 'center',	
						} ,
						{
                            field : 'total_qty',
                            title : 'Total Qty',
                            width : 60,
                            align : 'center',
                            editor : {
                                type : 'numberbox',
                                options : {
                                    disabled : true
                                }
                            },
                            formatter : function(value, row, rec) {
                                var flag = row.equip_usage;
                                var runrate = row.usage_amount;
                                if (flag == '' || flag == undefined
                                        || runrate == ''
                                        || runrate == undefined) {
                                    return "";
                                } else {
                                    return (flag * runrate);
                                }
                            }
						},
						{
							field : 'dr_typeStr',
							title : 'DR Type',
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
                                if (flag == 0) {
                                    return "Opportunity";
                                } else if (flag == 1) {
                                    return "Design In";
                                } else if (flag == 2) {
                                    return "Design Win";
                                } else if (flag == 9) {
                                    return "DW Reject";
                                }
                            }			
						},
						{
				 			field : 'remark',
				 			title : 'Internal Comment',
				 			width : 150,
				 			align : 'center',
						    formatter : function(value, row, rec) {
								var flag = row.remark;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else{	
									return flag;
								} 
							}
				 		},
				 		{
				 			field : 'weencomments',
				 			title : 'Ween Comment',
				 			width : 150,
				 			align : 'center',
						    formatter : function(value, row, rec) {
								var flag = row.weencomments;
								if (flag == 'undefined'||flag==undefined) {
									return "";
								} else{	
									return flag;
								} 
							}
				 		},
				 		{
				 			field : 'create_userName',
				 			title : 'Modified By',
				 			width : 150,
				 			align : 'center',
				 		},
				 		{
							field : 'createTime',
							title : 'Create Date',
							width : 180,
							align : 'center',
				 		}
				 		]],
					});
}

// 关闭创建页面
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

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
