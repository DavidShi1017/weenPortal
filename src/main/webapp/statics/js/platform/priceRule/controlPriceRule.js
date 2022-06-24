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
						+ '/priceRule/priceRuleAction!getControlPriceRuleList.jspa',
				        loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : "ID",
									field : 'id',
									width : 80,
									hidden : true,
									align : 'center'
								},
								{
									title : "Price Type",
									field : 'price_type',
									width : 90,
									align : 'center',
								},
								{
									title : "12NC",
									field : 'material_id',
									width : 120,
									align : 'center',
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
									title : "Book Part",
									field : 'material_desc',
									width : 150,
									align : 'left'
								},
								{
									title : "Sales Org",
									field : 'org_id',
									width : 110,
									align : 'center'
								},
								{
									title : "Sales Office",
									field : 'office_id',
									width : 110,
									align : 'center'
								},
								{
									title : "PerUnit",
									field : 'perUnit',
									width : 90,
									align : 'center',
								},
								{
									title : "Currency",
									field : 'currency_code',
									width : 100,
									align : 'center'
								},
								{
									title : "Price",
									field : 'sale_price',
									width : 90,
									align : 'center'
								},
								{
									title : "Start Date",
									field : 'start_dateStr',
									width : 100,
									align : 'center'
								},
								{
									title : "End Date",
									field : 'end_dateStr',
									width : 100,
									align : 'center'
								},
								] ],
						toolbar : [  "-", {
							text : 'Add Pricerule',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
//						, "-", {
//							text : 'Delete',
//							iconCls : 'icon-remove',
//							handler : function() {
//								dele();
//							}
//						}
						, "-", {
							text : 'Edit Pricerule',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
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
								downloadExcelModel();
							}
						}, "-", {
							text : 'View',
							iconCls : 'icon-view ',
							handler : function() {
								check();
							}
						}],
						onDblClickRow:function(rowIndex,rowData){
							initMaintAccount(false,'800','200','Detail Information', '/priceRuleAction!toViewPriceRule.jspa?id='+rowData.id,100,100);
						},
					});
}

//价格类型
$('#price_Type').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=550',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.material_id = encodeURIComponent($("#material_id").val());
	queryParams.material_name = encodeURIComponent($("#material_name").val());
	queryParams.price_Type = encodeURIComponent($("#price_Type").val());
	queryParams.latest_time = $("#latest_time").datebox('getValue');
//	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
}

function clearValue(){
	$("#material_id").val('')
	$("#material_name").val('')
	
	$("#latest_time").datebox('setValue','');
}
function sync(){
	initMaintAccount(false,'400','180','Sync', '/priceRuleAction!toSyncPrice.jspa',100,100);	
}
function add(){
	initMaintAccount(false,'800','350','Add Pricerule', '/priceRuleAction!toCreatePriceRule.jspa',100,100);	
}
function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false,'800','350','Modify PriceRule', '/priceRuleAction!toUpdatePriceRule.jspa?id='+rows[0].id,100,100);	
	}
}
function check(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	} else {
		initMaintAccount(false,'800','200','Detail Information', '/priceRuleAction!toViewPriceRule.jspa?id='+rows[0].id,100,100);
	}	
}

function importExcel(){
	initMaintAccount(false,'700','350','Import PriceRule', '/priceRuleAction!importExcel.jspa',100,100);
}
//模板下载
function downloadExcelModel(){
		$.messager.confirm('Tip', 'Confirmed about download?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("priceRule.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}
/**
 * 删除
 */
function dele() {
	$.messager.confirm('Confirm', 'Confirmed about  delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'No data selected!');
				return;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/priceRuleAction!deletePriceRule.jspa?id='+rows[0].id+'&state=-1';
			form.target = "hideFrame";
			form.submit();
		}
	});

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
						height : 300,
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
//	date = date + month[str[1]] + "-" + str[2] + " " + str[3];//到时分秒
	date = date + month[str[1]] + "-" + str[2];//年月日
	return date;
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