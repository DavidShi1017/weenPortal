var noPB = false;
$(document).ready(function() {
	if($("#useFor").val()=="YPD" || $("#useFor").val()=='VAR'){
		noPB = true;
	}
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	var purl;
	if($("#useFor").val()=="YPD" && $("#isOrderItem").val()=="Y"){
		purl=  '/product/productAction!getProductList.jspa';
	}else if($("#useFor").val()!="YPD"&&$("#isOrderItem").val()=="Y"){
		purl=  '/product/productAction!getDRProductList.jspa?number='+Math.random()+'&currency_code='+$('#currency_code').val()+'&office_id='+$('#office_id').val()+'&customer_id='+$('#customer_id').val();		
	}else if($("#isDRItem").val()=="Y"){
		purl= '/product/productAction!getDRProductList.jspa?number='+Math.random()+'&currency_code='+$('#currency_code').val()+'&office_id='+$('#office_id').val()+'&customer_id='+$('#customer_id').val();		
	}else if($("#isQuoteItem").val()=="Y"){
		purl= '/product/productAction!getQuoteProductList.jspa?number='+Math.random()+'&currency_code='+$('#currency_code').val()+'&office_id='+$('#office_id').val()+'&customer_id='+$('#customer_id').val();		
	}
	
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl+purl,
						
						queryParams:{
							material_id : encodeURIComponent($("#material_id").val()),
							material_name : encodeURIComponent($("#material_name").val()),
							isOrderItem : $("#isOrderItem").val(),
							isQuoteItem : $("#isQuoteItem").val(),
							isDRItem : $("#isDRItem").val(),
							useFor : $("#useFor").val(),
							state : encodeURIComponent($("#state").val()),
						},
				        loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						 idField : 'id',
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
									title : "Status",
									field : 'state',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var flag = row.state;
										if (flag == '0') {
											return "<font color='black'>New</font>";
										} else{
											return flag;
										} 
									},
									hidden:true
								},
								{
									title : "12NC",
									field : 'material_id',
									width : 150,
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
									title : "BOOK Part",
									field : 'material_name',
									width : 150,
									align : 'left'
								},
								{
									title : "Material Type",
									field : 'material_type',
									width : 150,
									align : 'left'
								},
								{
									title : "Product Group",
									field : 'material_groupId',
									width : 150,
									align : 'left'
								},
								{
									title : "Lead Time(week)",
									field : 'lead_time',
									width : 90,
									align : 'left',
									formatter : function(value, row, rec) {
										var flag = row.lead_time;
										if (flag == '0') {
											return "";
										} else{
											return flag;
										} 
									},
									hidden:true
								},
								{
									title : "Base Unit",
									field : 'base_unit',
									width : 80,
									align : 'left',
									hidden:true
								},
								{
									title : "PQ",
									field : 'pq',
									width : 80,
									align : 'center',
									hidden:true
								},
								{
									title : "Numerator",
									field : 'numerator',
									width : 100,
									align : 'center',
									hidden:true
								},
								{
									title : "Denominator",
									field : 'denominator',
									width : 100,
									align : 'center',
									hidden:true
								}
//								,
//								{
//									title : "Order Item",
//									field : 'isOrderItem',
//									width : 90,
//									align : 'left'
//								},
//								{
//									title : "Quote Item",
//									field : 'isQuoteItem',
//									width : 90,
//									align : 'left'
//								},
//								{
//									title : "DR Item",
//									field : 'isDRItem',
//									width : 90,
//									align : 'left',
//								}
								,
								{
									title : "Limited QTY",
									field : 'limited_qty',
									width : 80,
									align : 'left',
									formatter : function(value, row, rec) {
										var flag = row.limited_qty;
										if (flag == '0') {
											return "";
										} else{
											return flag;
										} 
									},
									hidden:true
								},
								{
									title : "MOQ(pc)",
									field : 'moq',
									width : 80,
									align : 'left',
									formatter : function(value, row, rec) {
										var flag = row.moq;
										if (flag == '0') {
											return "";
										} else{
											return flag;
										} 
									},
									hidden:true
								},
								{
									title : "WeEn Cost",
									field : 'cost',
									width : 80,
									align : 'left',
									hidden:true
								},
								{
									title : "PB Price",
									field : 'pbPrice',
									width : 80,
									align : 'left',
									hidden:noPB
								},
								{
									title : "PerUnit",
									field : 'perUnit',
									width : 100,
									align : 'center',
									hidden:true
								},
								{
									title : "For Customer",
									field : 'customer_group',
									width : 80,
									align : 'left',
									hidden:true
								},
								] ],
						toolbar : [  "-", {
							text : 'Select',
							iconCls : 'icon-ok',
							handler : function() {
								selectProduct();
							}
						} ]
					});

}

var productJson=[];
function selectProduct(){
	var rows = $('#datagrid').datagrid('getSelections');

	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item', 'warning');
		return; 
	}
	 $.messager.confirm('Confirm', 'Confirmed to add selected items?', function(r) {
		if(r){	
			for(var i=0;i<rows.length;i++){
				var material_id = rows[i].material_id ==undefined?'':rows[i].material_id; 
				var material_name = rows[i].material_name ==undefined?'':rows[i].material_name;
				var material_groupId = rows[i].material_groupId ==undefined?'':rows[i].material_groupId; 
				var material_type = rows[i].material_type ==undefined?'':rows[i].material_type;
				var lead_time = rows[i].lead_time ==undefined?'':rows[i].lead_time; 
				var base_unit = rows[i].base_unit ==undefined?'':rows[i].base_unit;
				var perUnit = rows[i].perUnit ==undefined?'0':rows[i].perUnit; 
				var sale_unit = rows[i].sale_unit ==undefined?'':rows[i].sale_unit; 
				var numerator = rows[i].numerator ==undefined?'':rows[i].numerator;
				var denominator = rows[i].denominator ==undefined?'':rows[i].denominator; 
				var limited_qty = rows[i].limited_qty ==undefined?'':rows[i].limited_qty;
				var moq = rows[i].moq ==undefined?'':rows[i].moq; 
				var cost = rows[i].cost ==undefined?'':rows[i].cost;
				var customer_group = rows[i].customer_group ==undefined?'':rows[i].customer_group;
				var pbprice = rows[i].pbPrice;
				if  ($("#useFor").val()=='VAR') pbprice='0.0000';
				
				var row = "{" + "material_id:'" +material_id
				+"',material_name:'"+material_name+ "',"	
				+ "material_groupId:'" + material_groupId + "',"
				+ "material_type:" + "'" + material_type + "',"
				+ "lead_time:" + "'" + lead_time 
				+ "',base_unit:'"+ base_unit 
				+ "',sale_unit:'"+ sale_unit 
				+ "',numerator:'" + numerator 
				+ "',denominator:'" + denominator 
				+ "',limited_qty:'"+ limited_qty 
				+ "',pbPrice:'"+ pbprice
				+ "',perUnit:'"+ perUnit 
				+ "',moq:'"+ moq+ "',cost:'"+ cost+
				"',customer_group:'"+ customer_group+"'}";
				productJson.push(row);
			}
			window.parent.returnProduct(productJson);
		}
	 });
	}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.material_id = encodeURIComponent($("#material_id").val());
	queryParams.material_name = encodeURIComponent($("#material_name").val());
	queryParams.isOrderItem = $("#isOrderItem").val();
	queryParams.isQuoteItem = $("#isQuoteItem").val();
	queryParams.isDRItem = $("#isDRItem").val();
	queryParams.useFor=$("#useFor").val(),
	queryParams.state = encodeURIComponent($("#state").val());
	$("#datagrid").datagrid('load');
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