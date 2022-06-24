$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/quote/quoteAction!getQuoteLogList.jspa',
						queryParams: {
							quote_id:$("#quote_id").val(),
							material_id:$("#material_id").val(),
						},		
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
					     columns : [[
					                 {
					 		field : 'ck',
					 		align : 'center',
					 		checkbox : false,
					 		hidden:true
					 	},{
				 			field : 'type',
				 			title : 'Opt Type',
				 			width : 100,
				 			align : 'center',
				 		},{
				 			field : 'qty',
				 			title : 'Qty',
				 			width : 80,
				 			align : 'center',
				 		},{
				 			field : 'target_cost',
				 			title : 'Target Cost', 
				 			width : 70,
				 			align : 'center',	
				 			formatter : function(value, row, rec) {
								var flag = row.target_cost;
								if (flag == 0||flag == ''||flag==undefined) {
									return flag;
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
								} 
							},
				 		} ,
				 		{
				 			field : 'target_resale',
				 			title : 'Target Resale',
				 			width : 70,
				 			align : 'center',	
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
							field : 'suggest_cost',
							title : 'Suggested Cost', 
							width : 70,
							align : 'center',	
							editor: {type:'numberbox',options:{precision:4}},
							formatter : function(value, row, rec) {
								var flag = row.suggest_cost;
								if (flag == 0||flag == ''||flag==undefined) {
									return flag;
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
								} 
							},hidden:($('#forWho').val()=='JXS')
						} ,
						{
							field : 'suggest_resale',
							title : 'Suggested Resale', 
							width : 70,
							align : 'center',	
							editor: {type:'numberbox',options:{precision:4}},
							formatter : function(value, row, rec) {
								var flag = row.suggest_resale;
								if (flag == 0||flag == ''||flag==undefined) {
									return flag;
								} else{
									flag=JSON.parse(flag*1);
									return flag.toFixed(4);
								} 
							},hidden:($('#forWho').val()=='JXS')
						},	
//						{
//							field : 'cus_remark',
//							title : 'Comments',
//							width : 100,
//							align : 'center',											
//						},	
						{
							field : 'remark',
							title : 'Internal Comments',
							width : 100,
							align : 'center',
							hidden:($('#forWho').val()=='JXS')				
						},{
				 			field : 'latest_userId',
				 			title : 'Modified by',
				 			width : 80,
				 			align : 'center',
				 		},{
				 			field : 'latest_time',
				 			title : 'Modified Date',
				 			width : 120,
				 			align : 'center',
				 			formatter : function(date){
								if(date==undefined){
									return "";										
								}else{
									return utcToDate(date);	
								}
							}
				 		}]],
					});
	// 分页控件
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}	  