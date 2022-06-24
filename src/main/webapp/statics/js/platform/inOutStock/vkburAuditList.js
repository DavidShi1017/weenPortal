$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function getVkgrpColumns1(){//组合datagrid的标题
	var strTitle="";
	//strTitle+="{field:'mat',title:'产品编码',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	strTitle+="{field:'kbetr',title:'单价<br/>(元/箱)',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	strTitle+="{title:'市场部审核提报',align:'center',colspan:5},";
	var vkgrpTxts= new Array(); 
	vkgrpTxts=$("#hdVkgrpTxtListStr").val().split(",");
	for(var i=0;i<vkgrpTxts.length;i++){
		strTitle+="{title:'"+vkgrpTxts[i]+"',align:'center',colspan:4},";
	}
	strTitle+="{title:'没有的',align:'right',width:75,hidden:true}";
	return strTitle;
}
function getVkgrpColumns2(){//组合datagrid的标题
	var strTitle="";
	strTitle+="{field:'vkgrpCount',title:'市场部审核下月<br>需货量',align:'right',width:75,editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!=\"\") {return 'background-color:#FFF7bb';}}},";
	strTitle+="{field:'vkgrpPrice',title:'市场部审核下月<br>需货金额(万元)',align:'right',width:80},";
	strTitle+="{field:'totalCount',title:'片区需货数量<br>汇总(箱)',align:'right',width:75},";
	strTitle+="{field:'totalPrice',title:'片区需货金额<br>汇总(万元)',align:'right',width:75},";
	strTitle+="{field:'vkgrpTarget',title:'下月基本<br>目标(万元)',align:'right',width:70},";
	
	
	var vkgrps= new Array(); 
	vkgrps=$("#hdVkgrpListStr").val().split(",");
	for(var i=0;i<vkgrps.length;i++){
		strTitle+="{field:'target"+vkgrps[i]+"',title:'下月基本<br/>目标(万元)',align:'right',width:65},";
		strTitle+="{field:'count"+vkgrps[i]+"',title:'下月需货<br>数量(箱)',align:'right',width:65},";
		strTitle+="{field:'price"+vkgrps[i]+"',title:'下月需货<br>金额(万元)',align:'right',width:65},";
		strTitle+="{field:'diff"+vkgrps[i]+"',title:'差异金额<br>(万元)',align:'right',width:65},";
	}
	strTitle+="{title:'没有的',align:'right',width:75,hidden:true}";
	return strTitle;
}

function rightView(v){
	if(v!=null){
		return "<span style=\"float:right\">"+v+"</span>";
	}
}

function getVkgrpColumns3(){
	var columns="";
	columns="[["+getVkgrpColumns1()+"],["+getVkgrpColumns2()+"]]";
	return columns;
}


function loadGrid(){
	_loadwaiting();
	setTimeout(test,500);//500毫秒后开始复制工作
}
function test(){
	$.ajax({
		type : "get",
		async : false,
		url:appUrl+ '/inOutStockAction!getVkburAuditListJson.jspa?ran='+Math.random(),
		data : {vkbur:$('#hdVkbur').val(),datid:$('#hdDatid').val()},
		loadMsg : '数据远程载入中,请等待...',
		success : function(obj2) {
			var obj = eval('(' + getVkgrpColumns3() + ')');
			$('#datagrid').treegrid({  
			    fit:true,
			    method: 'get',
				idField: 'id',
				treeField:'productName', 
				rownumbers:true,
				showFooter:true,
				data:eval('('+obj2+')'),
				frozenColumns:[[
					{field:'productName',title:'产品名称',width:300},
					{field:'matnr',title:'产品编码',width:100}
				]],
				columns:obj,
				toolbar: [{
					iconCls: "icon-ok",
					text: "上一步",
					handler:function(){
						//saveVkgrpStock();
					}
				},"-",{
					iconCls: "icon-excel",
					text: "Excel导入",
					handler:function(){
						importVkburAudit();
					}
				},"-",{
					iconCls: "icon-save",
					text: "提交",
					handler : function() {
						saveVkburAudit();
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
		}
	});
	_removeloading();
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

function saveVkburAudit(){
	var temp="-1";
	var datid=$('#hdDatid').val();
	var vkbur=$('#hdVkbur').val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkbur:vkbur,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>2){
		alert('大区已审核，不能提交');
	}else{
		_loadwaiting();
		setTimeout(saveVkburAudit2,500);//500毫秒后开始复制工作
	}
}
function saveVkburAudit2(){
	var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
	var nndgpList = [];
	for(var s=0;s<rootRows.length;s++){
		var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
		$.each(rows, function(index, item){
			var obj = {};
			obj.matnr=item.id;
			obj.maktx=encodeURIComponent(item.productName);
			obj.spart=item.spart;
			obj.mvgr1=item.mvgr1;
			obj.nmdgp=item.vkgrpCount==null?0:item.vkgrpCount;
			obj.nmdgp_pri=item.vkgrpPrice==null?0:item.kbetr*item.vkgrpCount;
			obj.datid=$('#hdDatid').val();
			obj.vkbur=$('#hdVkbur').val();
			nndgpList.push(obj);
		}); 
	}
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveVkburAudit.jspa",
		data : {
			nmdgpList:JSON.stringify(nndgpList)
		},
		success : function(obj) {
			if(obj=="1"){
				$.messager.alert('提示：', '操作成功!', 'info');
				location.href=appUrl+"/inOutStockAction!vkburAuditDiff.jspa?vkbur="+$("#hdVkbur").val()+"&datid="+$("#hdDatid").val();
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


function importVkburAudit(){//导入excel
	var bzirk=$("#hdBzirk").val();
	var datid=$('#hdDatid').val();
	var vkbur=$('#hdVkbur').val();
	
	var temp="-1";
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {vkbur:vkbur,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>2){
		alert('大区已审核，不能导入');
	}else{
		initWindows('片区审核结果导入', '/inOutStockAction!importVkburAudit.jspa?vkbur='+vkbur+'&bzirk='+bzirk+"&datid="+datid,500,300);
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

function importSuccess(){
	if(confirm('导入成功，请问现在需要进行差异性分析填报吗？')){
		location.href=appUrl+"/inOutStockAction!vkburAuditDiff.jspa?vkbur="+$("#hdVkbur").val()+"&datid="+$("#hdDatid").val();
	}
}