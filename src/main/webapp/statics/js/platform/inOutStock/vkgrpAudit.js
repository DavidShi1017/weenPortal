$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});


function loadGrid(){
	var datid=$('#hdDatid').val();
	var bzirk=$('#bzirk').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	var vkgrp=$('#vkgrp').combogrid('getValue');
	
	
	$("#datagrid").datagrid({
		method: 'get',
		url:appUrl+ '/inOutStockAction!getKunnrSubmitListJson.jspa?ran='+Math.random(),
		queryParams: {datid:datid,bzirk:bzirk,vkbur:vkbur,vkgrp:vkgrp},
		columns:[[ 
			{field:'kunnr',title:'客户编码',width:60,align:'center'}, 
			{field:'kunnm',title:'客户描述',width:250}, 
			{field:'kunnr_tel',title:'电话',width:100,align:'center'}, 
			{field:'bzirk_txt',title:'大区',width:100,align:'center'}, 
			{field:'vkbur_txt',title:'市场部',width:100,align:'center'}, 
			{field:'vkgrp_txt',title:'片区',width:80,align:'center'}, 
			{field:'state',title:'库存上报状态',width:80,align:'center',formatter:function(v1){return getStateValue(v1,"stock");}}, 
			{field:'state1',title:'需求上报状态',width:80,align:'center',formatter:function(v2){return getStateValue(v2,"plan");}}, 
			{field:'crdat',title:'上报时间',width:120,align:'center',formatter : function(v3) {
				if (v3!=null && v3!=undefined) {
					return utcToDate(v3.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}}, 
			{field:'austa',title:'片区审核状态',width:80,align:'center',formatter:function(value,row,index){return getVkgrpStateValue(row);}}, 
			{field:'autim',title:'片区审核时间',width:120,align:'center',formatter : function(v5) {
				if (v5!=null && v5!=undefined) {
					return utcToDate(v5.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
				}
			}}
		]],
		border:false,
		fit:true,
		rownumbers: true,
		fitColumns:false,
		showFooter:true,
		toolbar: "#toolbar"
	});
}

function searchGrid(){
	loadGrid();
}

$('#bzirk').combogrid({
	width:120,
	panelHeight : 340,
	panelWidth : 420,
	pagination : false,
	multiple : false,
	method : 'post',
	singleSelect : true,
	url:appUrl+ '/inOutStockAction!getOrgList.jspa',
	queryParams: {parentId:'10000002'},
	idField : 'orgCode',
	textField : 'orgName',
	columns : [[{
				field : 'orgCode',
				title : '大区编码',
				width : 100
			}, {
				field : 'orgName',
				title : '大区名称',
				width : 300
			}]],
	onSelect : function(index, record) {
		$('#vkbur').combogrid({
			width:120,
			panelHeight : 340,
			panelWidth : 420,
			pagination : false,
			multiple : false,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/inOutStockAction!getOrgList.jspa',
			queryParams: {parentId:record.orgCode},
			idField : 'orgCode',
			textField : 'orgName',
			columns : [[{
						field : 'orgCode',
						title : '市场部编码',
						width : 100
					}, {
						field : 'orgName',
						title : '市场部名称',
						width : 300
					}]],
			onSelect : function(indexVkbur,recordVkbur) {
				$('#vkgrp').combogrid({
					width:120,
					panelHeight : 340,
					panelWidth : 420,
					pagination : false,
					multiple : false,
					method : 'post',
					singleSelect : true,
					url : appUrl + '/inOutStockAction!getOrgList.jspa',
					queryParams: {parentId:recordVkbur.orgCode},
					idField : 'orgCode',
					textField : 'orgName',
					columns : [[{
								field : 'orgCode',
								title : '片区编码',
								width : 100
							}, {
								field : 'orgName',
								title : '片区名称',
								width : 300
							}]],
					onSelect : function(indexVkgrp,recordVkgrp) {
						loadGrid();
		  			},
		  			onLoadSuccess:function(){
		  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
		  				if(rows.length==1){
		  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
		  				}
		  			}
				}); 
  			},
  			onLoadSuccess:function(){
  				var rows=$('#vkbur').combogrid('grid').datagrid('getRows');
  				if(rows.length==1){
  					$('#vkbur').combogrid('setValue',rows[0].orgCode);
  					$('#vkgrp').combogrid({
  						width:120,
  						panelHeight : 340,
  						panelWidth : 420,
  						pagination : false,
  						multiple : false,
  						method : 'post',
  						singleSelect : true,
  						url : appUrl + '/inOutStockAction!getOrgList.jspa',
  						queryParams: {parentId:rows[0].orgCode},
  						idField : 'orgCode',
  						textField : 'orgName',
  						columns : [[{
  									field : 'orgCode',
  									title : '片区编码',
  									width : 100
  								}, {
  									field : 'orgName',
  									title : '片区名称',
  									width : 300
  								}]],
  						onSelect : function(indexVkgrp,recordVkgrp) {
  							loadGrid();
  			  			},
  			  			onLoadSuccess:function(){
  			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
  			  				if(rows.length==1){
  			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
  			  				}
  			  			}
  					});
  				}
  			}
		}); 
	},
	onLoadSuccess:function(){
		var rows=$('#bzirk').combogrid('grid').datagrid('getRows');
		if(rows.length==1){
			$('#bzirk').combogrid('setValue',rows[0].orgCode);
			$('#vkbur').combogrid({
				width:120,
				panelHeight : 340,
				panelWidth : 420,
				pagination : false,
				multiple : false,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/inOutStockAction!getOrgList.jspa',
				queryParams: {parentId:rows[0].orgCode},
				idField : 'orgCode',
				textField : 'orgName',
				columns : [[{
							field : 'orgCode',
							title : '市场部编码',
							width : 100
						}, {
							field : 'orgName',
							title : '市场部名称',
							width : 300
						}]],
				onSelect : function(indexVkbur,recordVkbur) {
					$('#vkgrp').combogrid({
						width:120,
						panelHeight : 340,
						panelWidth : 420,
						pagination : false,
						multiple : false,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/inOutStockAction!getOrgList.jspa',
						queryParams: {parentId:recordVkbur.orgCode},
						idField : 'orgCode',
						textField : 'orgName',
						columns : [[{
									field : 'orgCode',
									title : '片区编码',
									width : 100
								}, {
									field : 'orgName',
									title : '片区名称',
									width : 300
								}]],
						onSelect : function(indexVkgrp,recordVkgrp) {
							loadGrid();
			  			},
			  			onLoadSuccess:function(){
			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
			  				if(rows.length==1){
			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
			  				}
			  			}
					}); 
	  			},
	  			onLoadSuccess:function(){
	  				var rows=$('#vkbur').combogrid('grid').datagrid('getRows');
	  				if(rows.length==1){
	  					$('#vkbur').combogrid('setValue',rows[0].orgCode);
	  					$('#vkgrp').combogrid({
	  						width:120,
	  						panelHeight : 340,
	  						panelWidth : 420,
	  						pagination : false,
	  						multiple : false,
	  						method : 'post',
	  						singleSelect : true,
	  						url : appUrl + '/inOutStockAction!getOrgList.jspa',
	  						queryParams: {parentId:rows[0].orgCode},
	  						idField : 'orgCode',
	  						textField : 'orgName',
	  						columns : [[{
	  									field : 'orgCode',
	  									title : '片区编码',
	  									width : 100
	  								}, {
	  									field : 'orgName',
	  									title : '片区名称',
	  									width : 300
	  								}]],
	  						onSelect : function(indexVkgrp,recordVkgrp) {
	  							loadGrid();
	  			  			},
	  			  			onLoadSuccess:function(){
	  			  				var rows=$('#vkgrp').combogrid('grid').datagrid('getRows');
	  			  				if(rows.length==1){
	  			  					$('#vkgrp').combogrid('setValue',rows[0].orgCode);
	  			  				}
	  			  			}
	  					});
	  				}
	  			}
			}); 
		}
	}
});

$('#kunnr').combogrid({
	onChange:function(r) {
		searchList();
	}
});


function setSearchValue(){
	
}
function searchList(){
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}


function initWindows(title, url,WWidth,WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						fit : false,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}
function closeWindows() {
	$("#hiddenWin").window('close');
}


var editingId;
function editValue(value,row){
	if (editingId != undefined){
		$('#datagrid').treegrid('select', editingId);
	}
	var row2 = $('#datagrid').treegrid('getSelected');
	if(row2){
		$('#datagrid').treegrid('endEdit', editingId);
	}
	editingId = row.id;
	if(editingId.length>3){
		$('#datagrid').treegrid('beginEdit', editingId);
	}
}


function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
}

function createKunnrProduct(){
	var kunnr=$("#kunnr").combogrid("getValue");
//	if(kunnr==""){
//		alert("请先选择客户");
//	}else{
//		initWindows('产品添加', '/inOutStockAction!addKunnrProduct.jspa',800,600);
//	}
	initWindows('产品添加', '/inOutStockAction!addKunnrProduct.jspa',800,600);
}


function saveKunnrStock(){
	var postStr="";//用来存储list的数据，最后post出去
	var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
	for(var s=0;s<rootRows.length;s++){
		var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
		$.each(rows, function(index, item){
			postStr+=item.matnr+"@@";
			postStr+=item.count1+"@@";
			postStr+=item.count2+"@@";
			postStr+=item.count3+"@@";
			postStr+=item.count4+"@@";
			postStr+=encodeURIComponent(item.productName)+"@@";
			postStr+=item.kbetr+"@@";
			postStr+=item.spart+"@@";
			postStr+=item.mvgr1+"##";
		}); 
	}
	
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveKunnrPlan.jspa",
		data : {
			stockListStr:postStr,
			kunnr:$('#kunnr').combogrid('getValue'),
			datid:$('#hdDatid').val(),
			bedat:$('#hdBedat').val(),
			endat:$('#hdEndat').val(),
			month:$('#hdMonth').val()
		},
		success : function(obj) {
			if(obj=="1"){
				$.messager.alert('提示：', '操作成功!', 'info');
			}else if("0"){
				$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	});
}

function utcToDate(utcCurrTime, type) {
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
	date = date + month[str[1]] + "-" + str[2];
	if (type == "timestamp") {
		date = date + " " + str[3];
	}
	return date;
}

function getStateValue(value,type){
	if(type=="stock"){
		if(value>=0){
			return "<font color=red>已上报</font>";
		}else{
			return "<font color=black>未上报</font>";
		}
	}
	else if(type=="plan"){
		if(value>=1){
			return "<font color=red>已上报</font>";
		}else{
			return "<font color=black>未上报</font>";
		}
	}else if(type=="vkgrp"){
		if(value>0){
			return "<font color=red>已审核</font>";
		}else{
			return "<font color=black>未审核</font>";
		}
	}
	return "";
}
function getVkgrpStateValue(row){
	var austa=row.austa;
	var autim=row.autim;
	if(austa>0){
		return "<font color=red>已审核</font>";
	}else{
		if(autim!=""&&autim!=null&&autim!=undefined){
			return "<font color=blue>已退回</font>";
		}else{
			return "<font color=black>未审核</font>";
		}
	}
	
	return "";
}
function searchSubmit(){
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var vkgrp=$('#vkgrp').combogrid('getValue');
		var vkbur=$('#vkbur').combogrid('getValue');
		if(null!=vkgrp&&""!=vkgrp){
			var rows = $("#datagrid").datagrid("getRows");	
			if(rows.length==0){
				alert("没有数据，不能提交");
				return;
			}
			var strMsg="";
			for(var r=0;r<rows.length;r++){
				if(rows[r].state!=1){
					strMsg+=rows[r].kunnr+rows[r].kunnm+",";
				}
			}
			if(strMsg!=""){
				alert("片区下还有以下客户没有提报下月计划："+strMsg+"请完成客户下月需求计划再审核！");
			}else{
				location.href=appUrl+ '/inOutStockAction!vkgrpAuditList.jspa?vkgrp='+vkgrp+"&vkbur="+vkbur;
			}
		}else{
			alert('请先选择片区');
		}
	}else{
		$.messager.alert('提示：', '不在有效的日期范围内，有效的提报日期为'+$("#hdBeplt").val()+'至'+$("#hdEnplt").val()+'！', 'info');
		return;
	}
}

function clearInput(){
	$('#vkgrp').combogrid('setValue','');
	$('#vkbur').combogrid('setValue','');
	$('#bzirk').combogrid('setValue','');
}
