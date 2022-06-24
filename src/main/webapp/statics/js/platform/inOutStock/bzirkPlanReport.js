$(document).ready(function() {
	loadGrid();
	gridMix();
});
$('#bzirk').combogrid({
	width:120,
	panelHeight : 340,
	panelWidth : 420,
	pagination : false,
	multiple : false,
	method : 'post',
	singleSelect : true,
	url:appUrl+ '/inOutStockAction!getOrgList2.jspa',
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
			url : appUrl + '/inOutStockAction!getOrgList2.jspa',
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
					}]]
		}); 
	}
});
function loadGrid(){
	var date1=$('#rpmnt1').combobox('getValue');
	var date2=$('#rpmnt2').combobox('getValue');
	var bzirk=$('#bzirk').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	
	$("#datagrid").datagrid({
		frozenColumns:[[
			{field:'BZTXT',title:'大区',width:100,align:'center',rowspan:2},
			{field:'BEZER',title:'市场部',width:100,align:'center',rowspan:2},
			{field:'SPART_TXT',title:'产品品类描述',width:100,align:'center',rowspan:2},
			{field:'MVGR1_TXT',title:'产品系列描述',width:100,align:'center',rowspan:2}
		]],
		columns:gridMix(),
		method: 'get',
		singleSelect: true,
		border:false,
		fit:true,
		rownumbers: true,
		url:appUrl+ '/inOutReportAction!getBzirkPlanReportJson.jspa?ran='+Math.random(),
		queryParams: {date1:date1,date2:date2,bzirk:bzirk,vkbur:vkbur}
		//showFooter:true,
		//pagination : true,
		//pageSize:30
	});
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 30,
//		pageList : [ 30, 50, 100 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
}

function searchGrid(){
	loadGrid();
}


function gridMix(){
	var date1 = $("#rpmnt1").combobox('getValue');
	var date2 = $("#rpmnt2").combobox('getValue');
	var rpyea1 = date1.split("-")[0];
	var rpyea2 = date2.split("-")[0];
	var rpmnt1 = date1.split("-")[1];
	var rpmnt2 = date2.split("-")[1];
	var mnts = (rpyea2-rpyea1)*12+(rpmnt2-rpmnt1)+1;
	var spmonListStr = "";
	if(mnts>0){
		var nextMadd = 0;
		var nextYadd = 0;
		for(var i=0 ; i < mnts ; i++){
			var _spmon = "";
			var spmon = "";
			var m =  parseInt(rpmnt1) + i + nextMadd;
			var y = parseInt(rpyea1) + nextYadd;
			if(m==12){
				nextYadd += 1;
				nextMadd -= 12;
			}
			_spmon = y+"-0"+m;
			spmon = y+"0"+m;
			if(m > 9){
				_spmon = y + "-" +m;
				spmon = ""+y+m;
			}
			spmonListStr += ","+spmon;
		}
	}
//	alert(spmonListStr);
	//开始拼接
	var strTitle = "";
	var spmons= new Array(); 
	spmons = spmonListStr.split(",");
	strTitle += "[[";
	for(var i =1 ; i < spmons.length; i++){
		strTitle += "{title:'"+spmons[i]+"',width:200,align:'center',colspan:2},";
	}
	strTitle += "],[";
	for(var i =1 ; i < spmons.length; i++){
		strTitle += "{field:'STORE_PRI"+spmons[i]+"',title:'上月末库存',width:100,align:'center'},{field:'NMDGP_PRI"+spmons[i]+"',title:'下月需求',width:100,align:'center'},";
	}
	strTitle += "]]";
//	alert(strTitle);
	var obj = eval('(' + strTitle + ')');
	return obj;
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


$('#rpmnt1').combobox({  
    url:appUrl + '/inOutReportAction!getBzirkPlanDate.jspa',
    valueField:'value',  
    textField:'text'  
}); 
$('#rpmnt2').combobox({  
    url:appUrl + '/inOutReportAction!getBzirkPlanDate.jspa',
    valueField:'value',  
    textField:'text'  
}); 


function closeWindows() {
	$("#hiddenWin").window('close');
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

var editIndex = undefined;
function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#datagrid').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#datagrid').datagrid('selectRow', editIndex);
		}
	}
}
function endEditing(){
	if (editIndex == undefined){return true;}
	if ($('#datagrid').datagrid('validateRow', editIndex)){
		$('#datagrid').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function saveVkburDiff(){
	endEditing();
	var rows=$('#datagrid').datagrid('getRows'); //获取根节点信息
	var diffList = [];
	$.each(rows, function(index, item){
		if(item.mvgr1!=""){
			var obj = {};
			obj.datid=$('#hdDatid').val();
			obj.vkgrp="";
			obj.vkbur=$('#hdVkbur').val();
			obj.bzirk="";
			obj.spart=item.spart;
			obj.spart_txt=encodeURIComponent(item.spart_txt);
			obj.mvgr1=item.mvgr1;
			obj.mvgr1_txt=encodeURIComponent(item.mvgr1_txt);
			obj.jbmb_pri=item.jbmb_pri;
			obj.tzmb_pri=item.tzmb_pri;
			obj.difftxt=encodeURIComponent(item.difftxt);
			obj.reaid=$('hdReaid').val();
			obj.nmdgp=item.nmdgp;
			obj.nmdgp_pri=item.nmdgp_pri;
			diffList.push(obj);
		}
	});
	var reason=$('#reason').val();
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveVkburDiff.jspa",
		data : {
			diffList:JSON.stringify(diffList),
			reason:encodeURIComponent(reason),
			reaid:$('hdReaid').val()
		},
		success : function(obj) {
			if(obj=="1"){
				$.messager.alert('提示：', '操作成功!', 'info');
				location.href=appUrl+"/inOutStockAction!vkburAudit.jspa";
			}else if("0"){
				$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	});
}

function clearInput(){
	$('#rpmnt1').combobox('setValue','');
	$('#rpmnt2').combobox('setValue','');
	$('#bzirk').combogrid('setValue','');
	$('#vkbur').combogrid('setValue','');
}

function exportExcel(){
	var date1=$('#rpmnt1').combobox('getValue');
	var date2=$('#rpmnt2').combobox('getValue');
	var bzirk=$('#bzirk').combogrid('getValue');
	var vkbur=$('#vkbur').combogrid('getValue');
	//alert(appUrl + '/inOutReportAction!exportBzirkPlanReport.jspa?bzirk='+bzirk+'&vkbur='+vkbur+"&date1="+date1+"&date2="+date2);
	var form = window.document.forms[0];
	form.action = appUrl + '/inOutReportAction!exportBzirkPlanReport.jspa?bzirk='+bzirk+'&vkbur='+vkbur+"&date1="+date1+"&date2="+date2;
	form.target = "hideFrame";
	form.submit();
}

