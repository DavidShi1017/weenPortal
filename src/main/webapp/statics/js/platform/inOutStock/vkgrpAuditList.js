$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function getKunnrColumns1(){//组合datagrid的标题
	var strTitle="";
	strTitle+="{field:'kbetr',title:'单价<br/>(元/箱)',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	var kunnms= new Array(); 
	kunnms=$("#hdKunnmListStr").val().split(",");
	strTitle+="{title:'片区审核提报',align:'center',colspan:5},";
	for(var i=0;i<kunnms.length;i++){
		strTitle+="{title:'"+kunnms[i]+"',align:'center',colspan:4},";
	}
	strTitle+="{title:'没有的',align:'right',width:75,hidden:true}";
	return strTitle;
}
function getKunnrColumns2(){//组合datagrid的标题
	var strTitle="";
	var kunnrs= new Array(); 
	kunnrs=$("#hdKunnrListStr").val().split(",");
	strTitle+="{field:'vkgrpCount',title:'片区审核下月<br>需货量',align:'right',width:75,editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!=\"\") {return 'background-color:#FFF7bb';}}},";
	strTitle+="{field:'vkgrpPrice',title:'片区审核下月<br>需货金额(万元)',align:'right',width:80},";
	strTitle+="{field:'totalCount',title:'客户需货数量<br>汇总(箱)',align:'right',width:75},";
	strTitle+="{field:'totalPrice',title:'客户需货金额<br>汇总(万元)',align:'right',width:75},";
	strTitle+="{field:'vkgrpTarget',title:'下月基本<br>目标(万元)',align:'right',width:70},";
	
	for(var i=0;i<kunnrs.length;i++){
		strTitle+="{field:'target"+kunnrs[i]+"',title:'下月基本<br/>目标(万元)',align:'right',width:65},";
		strTitle+="{field:'count"+kunnrs[i]+"',title:'下月需货<br>数量(箱)',align:'right',width:65},";
		strTitle+="{field:'price"+kunnrs[i]+"',title:'下月需货<br>金额(万元)',align:'right',width:65},";
		strTitle+="{field:'diff"+kunnrs[i]+"',title:'差异金额<br>(万元)',align:'right',width:65},";
	}
	strTitle+="{title:'没有的',align:'right',width:75,hidden:true}";
	return strTitle;
}

function rightView(v){
	if(v!=null){
		return "<span style=\"float:right\">"+v+"</span>";
	}
}

function getKunnrColumns3(){
	var columns="";
	columns="[["+getKunnrColumns1()+"],["+getKunnrColumns2()+"]]";
	return columns;
}


function loadGrid(){
	_loadwaiting();
	setTimeout(test,500);//500毫秒后开始复制工作
}
function test(){
	var obj = eval('(' + getKunnrColumns3() + ')');
	$.ajax({
		type : "get",
		async : false,
		url:appUrl+ '/inOutStockAction!getVkgrpAuditListJson.jspa?ran='+Math.random(),
		data : {vkgrp:$('#hdVkgrp').val(),datid:$('#hdDatid').val()},
		loadMsg : '数据远程载入中,请等待...',
		success : function(obj2) {
			data=obj;
			$('#datagrid').treegrid({  
				loadMsg : '数据远程载入中,请等待...',
			    fit:true,
			    method: 'get',
				idField: 'id',
				treeField:'productName', 
				rownumbers:true,
				showFooter:true,
				nowrap:true,
				animate:true,
				data:eval('('+obj2+')'),
				frozenColumns:[[
					{field:'productName',title:'产品名称',width:300},
					{field:'matnr',title:'产品编码',width:80}
				]],
				columns:obj,
				toolbar: [{
					iconCls: "icon-add",
					text: "全展开",
					handler:function(){
						$('#datagrid').treegrid('expandAll');
					}
				},"-",{
					iconCls: "icon-remove",
					text: "全收缩",
					handler:function(){
						$('#datagrid').treegrid('collapseAll');
					}
				},"-",{
					iconCls: "icon-ok",
					text: "上一步",
					handler:function(){
						history.go(-1);
					}
				},"-",{
					iconCls: "icon-excel",
					text: "Excel导入",
					handler:function(){
						importVkgrpAudit();
					}
				},"-",{
					iconCls: "icon-save",
					text: "提交",
					handler : function() {
						saveVkgrpAudit();
					}
				}],
				onClickCell: function (value, row) {
					editValue(value,row);
				},
				onAfterEdit:function(row,changes){
					changeValue(row,changes);
				},rowStyler:function(row){     
					if (row.id.length==3){     
						return 'background-color:#FDF9BB;';     
					}else if(row.id.length==7) {
						return 'background-color:#9DDBF4';    
					}     
				}  
			}); 
			
			
			
//			$('#datagrid').treegrid({
//				idField: 'id',
//				treeField:'productName', 
//				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
//				data:eval('('+obj+')'),
//				//loadMsg : '数据远程载入中,请等待...'
//			});
		}
	});
	_removeloading();
	
	
	
	
//	$('#datagrid').treegrid({  
//		queryParams: {vkgrp:$('#hdVkgrp').val(),datid:$('#hdDatid').val()},
//		loadMsg : '数据远程载入中,请等待...',
//	    fit:true,
//	    method: 'get',
//		idField: 'id',
//		treeField:'productName', 
//		rownumbers:true,
//		showFooter:true,
//		nowrap:true,
//		animate:true,
//		url:appUrl+ '/inOutStockAction!getVkgrpAuditListJson.jspa',
//		
//		frozenColumns:[[
//			{field:'productName',title:'产品名称',width:300}
//		]],
//		columns:obj,
//		toolbar: [{
//			iconCls: "icon-ok",
//			text: "上一步",
//			handler:function(){
//				saveKunnrStock();
//			}
//		},"-",{
//			iconCls: "icon-save",
//			text: "保存",
//			handler : function() {
//				saveVkgrpAudit();
//			}
//		}],
//		onClickCell: function (value, row) {
//			editValue(value,row);
//		},
//		onAfterEdit:function(row,changes){
//			changeValue(row,changes);
//		},rowStyler:function(row){     
//			if (row.id.length==3){     
//				return 'background-color:#FDF9BB;';     
//			}else if(row.id.length==7) {
//				return 'background-color:#9DDBF4';    
//			}     
//		}  
//	}); 
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
		  			}
				}); 
  			}
		}); 
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
function changeValue(row,changes){
	var kbetr=parseFloat(row.kbetr);
	var count=parseFloat(row.vkgrpCount);
	if(count>=0){
		row.vkgrpPrice=(kbetr*count/10000).toFixed(2);
	}
	$('#datagrid').treegrid('refresh',row.id); 
	updateValue(row.parentid);
	updateFootValue();
}
function updateValue(rowid){//更新系列汇总的数据
	var row1=$('#datagrid').treegrid('find',rowid);//找出父节点
	var parentId=rowid;
	var row2=$('#datagrid').treegrid('getChildren',parentId);//找出父节点下的所有子节点
	var count=0,price=0;
	$.each(row2, function(index, item){
		if(!isNaN(item.vkgrpPrice)&&item.vkgrpPrice!=""){//是数字的时候执行
			price+=parseFloat(item.vkgrpPrice);
		}
		if(!isNaN(item.vkgrpCount)&&item.vkgrpCount!=""){//是数字的时候执行
			count+=parseFloat(item.vkgrpCount);
		}
	}); 
	row1.vkgrpPrice=parseFloat(price).toFixed(2);
	row1.vkgrpCount=parseFloat(count).toFixed(2);
	$('#datagrid').treegrid('refresh',parentId);  
}
function updateFootValue(){
	var rowFoot=$('#datagrid').treegrid('getFooterRows');
	var rowRoot=$('#datagrid').treegrid('getRoots');
	var count=0,price=0;
	$.each(rowRoot, function(index, item){
		if(!isNaN(item.vkgrpPrice)&&item.vkgrpPrice!=""){//是数字的时候执行
			price+=parseFloat(item.vkgrpPrice);
		}
		if(!isNaN(item.vkgrpCount)&&item.vkgrpCount!=""){//是数字的时候执行
			count+=parseFloat(item.vkgrpCount);
		}
	});
	var obj=[{"productName":"合计：","vkgrpCount":count,"vkgrpPrice":price,"iconCls":"icon-sum"}];
	$('#datagrid').treegrid('reloadFooter',obj);  
}


function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
}

function saveVkgrpAudit(){
	//var postStr="";//用来存储list的数据，最后post出去
	var temp="-1";
	var datid=$('#hdDatid').val();
	var vkgrp=$('#hdVkgrp').val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkgrp:vkgrp,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>1){
		alert('片区所在市场部已审核，不能提交!');
	}else{
		_loadwaiting();
		setTimeout(saveVkgrpAudit2,500);//500毫秒后开始复制工作
	}
}
function saveVkgrpAudit2(){
	var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
	var nndgpList = [];
	for(var s=0;s<rootRows.length;s++){
		var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
		$.each(rows, function(index, item){
			var obj = {};
			obj.matnr=item.id;
			//obj.maktx=item.productName;
			obj.maktx=encodeURIComponent(item.productName);
			obj.spart=item.spart;
			obj.mvgr1=item.mvgr1;
			obj.nmdgp=item.vkgrpCount==null?0:item.vkgrpCount;
			obj.nmdgp_pri=item.vkgrpPrice==null?0:item.vkgrpCount*item.kbetr;
			obj.datid=$('#hdDatid').val();
			obj.vkgrp=$('#hdVkgrp').val();
			nndgpList.push(obj);
		}); 
	}

	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveVkgrpAudit.jspa",
		data : {
			nmdgpList:JSON.stringify(nndgpList)
		},
		success : function(obj) {
			if(obj=="1"){
				alert('操作成功');
				location.href=appUrl+"/inOutStockAction!vkgrpAuditDiff.jspa?vkgrp="+$("#hdVkgrp").val()+"&datid="+$("#hdDatid").val();
			}else if("0"){
				$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	});
	
	_removeloading();
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

function searchSubmit(){
	var vkgrp=$('#vkgrp').combogrid('getValue');
	if(null!=vkgrp&&""!=vkgrp){
		var rows = $("#datagrid").datagrid("getRows");	
		var strMsg="";
		for(var r=0;r<rows.length;r++){
			if(rows[r].state!=1){
				strMsg+=rows[r].kunnr+rows[r].kunnm+",";
			}
		}
		if(strMsg!=""){
			alert("片区下还有以下客户没有提报下月计划："+strMsg+"请完成客户下月需求计划再审核！");
		}else{
			location.href=appUrl+ '/inOutStockAction!vkgrpAuditList.jspa?vkgrp='+vkgrp;
		}
	}else{
		alert('请先选择片区');
	}
}

function _loadwaiting(){//显示等待信息  
    var wrap = $("#cc");  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:wrap.width(),height:wrap.height()}).appendTo(wrap);  
    $("<div class=\"datagrid-mask-msg\"></div>").html("加载中,请等待...").appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2});  
}  
function _removeloading(){//隐藏等待信息  
    var wrap = $("#cc");  
    wrap.find("div.datagrid-mask-msg").remove();  
    wrap.find("div.datagrid-mask").remove();  
} 


function importVkgrpAudit(){//导入excel
	var vkgrp=$("#hdVkgrp").val();
	var datid=$('#hdDatid').val();
	var vkbur=$('#hdVkbur').val();
	
	var temp="-1";
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkgrp:vkgrp,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>1){
		alert('市场部已审核，不能导入！');
	}else{
		initWindows('片区审核结果导入', '/inOutStockAction!importVkgrpAudit.jspa?vkbur='+vkbur+'&vkgrp='+vkgrp+"&datid="+datid,500,300);
	}
}

function importSuccess(){
	if(confirm('导入成功，请问现在需要进行差异性分析填报吗？')){
		location.href=appUrl+"/inOutStockAction!vkgrpAuditDiff.jspa?vkgrp="+$("#hdVkgrp").val()+"&datid="+$("#hdDatid").val();
	}
}
