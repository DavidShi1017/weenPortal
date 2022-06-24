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
						+ '/country/countryAction!searchSaleCountry.jspa?userId='+$('#userId').val(),
				        loadMsg : 'Loading...',
						singleSelect : false,
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
									width : 130,
									hidden : true,
									align : 'center'
								},
								{
									title : "UserId",
									field : 'userId',
									width : 130,
									align : 'center',
									hidden:true
								}, {
									title : "Org",
									field : 'org_name',
									width : 130,
									align : 'center'
								}, {
									title : "Country Code",
									field : 'country_code',
									width : 130,
									align : 'center',
								}, {
									title : "Country",
									field : 'country_name',
									width : 130,
									align : 'center'
								}, {
									title : "State",
									field : 'province_code',
									width : 130,
									align : 'center',
									hidden:true
								}, {
									title : "State",
									field : 'province_name',
									width : 130,
									align : 'center'
								} ] ],
						toolbar : [ "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'Delete',
							iconCls : 'icon-remove',
							handler : function() {
								dele();
							}
						}
						],
						onSelect : function(rowIndex, rowData) {

						},
					});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	$("#datagrid").datagrid('load');
}

function add() {
	initMaintAccount(false, '800','500', 'Create SaleCountry',
			'/countryAction!toSearchCountryProvince.jspa?userId='+$('#userId').val(),40,10);
}
/**
 * 删除
 */
function dele() {
	var saleCountryJson=[];
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows==undefined || rows.length == 0) {
		$.messager.alert('info', 'Please select the data item！');
		return;
	}
	
	for(var i=0;i<rows.length;i++){
		var row= "{"+"id:'"+rows[i].id+"',userId:'"+$('#userId').val()+"',org_code:'"+rows[i].org_code
		+"',country_code:'"+rows[i].country_code+"',country_name:'"+rows[i].country_name
		+"',province_name:'"+rows[i].province_name+"',province_code:'"+rows[i].province_code+"'}";
		saleCountryJson.push(row);				
	}
	  $.messager.confirm('Confirm', 'Confirmed about  submit?', function(r) {
		if(r){						
			$('#saleCountryJson').val("["+saleCountryJson+"]"); 
			var form = window.document.forms[0];
			form.action = appUrl + '/countryAction!delSaleCountry.jspa';
			form.target = "hideFrame";
			form.submit();			  						
		}
	});

}

function initMaintAccount(ffit, widte, height, title, url, l, t) {
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
						fit : FFit,
						draggable : true,
						left : l,
						top : t
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
