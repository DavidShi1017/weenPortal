$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function getVkburColumns1(){//组合datagrid的标题
	var strTitle="";
	strTitle+="{field:'kbetr',title:'单价<br/>(元/箱)',width:60,align:'center',rowspan:2,formatter: function(value,row,index){return rightView(value);}},";
	var vkburTxts= new Array(); 
	vkburTxts=$("#hdVkburTxtListStr").val().split(",");
	for(var i=0;i<vkburTxts.length;i++){
		strTitle+="{title:'"+vkburTxts[i]+"',align:'center',colspan:4},";
	}
	strTitle+="{title:'市场部审核提报',align:'center',colspan:5}";
	return strTitle;
}
function getVkburColumns2(){//组合datagrid的标题
	var strTitle="";
	var vkburs= new Array(); 
	vkburs=$("#hdVkburListStr").val().split(",");
	for(var i=0;i<vkburs.length;i++){
		strTitle+="{field:'target"+vkburs[i]+"',title:'下月基本<br/>目标(万元)',align:'right',width:65},";
		strTitle+="{field:'count"+vkburs[i]+"',title:'下月需货<br>数量(箱)',align:'right',width:65},";
		strTitle+="{field:'price"+vkburs[i]+"',title:'下月需货<br>金额(万元)',align:'right',width:65},";
		strTitle+="{field:'diff"+vkburs[i]+"',title:'差异金额<br>(万元)',align:'right',width:65},";
	}
	strTitle+="{field:'totalCount',title:'市场部需货数量<br>汇总(箱)',align:'right',width:75},";
	strTitle+="{field:'totalPrice',title:'市场部需货金额<br>汇总(万元)',align:'right',width:75},";
	strTitle+="{field:'bzirkTarget',title:'下月基本<br>目标(万元)',align:'right',width:70}";;
	return strTitle;
}

function rightView(v){
	if(v!=null){
		return "<span style=\"float:right\">"+v+"</span>";
	}
}

function getVkburColumns3(){
	var columns="";
	columns="[["+getVkburColumns1()+"],["+getVkburColumns2()+"]]";
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
		url:appUrl+ '/inOutStockAction!getBzirkAuditListJson.jspa?ran='+Math.random(),
		data : {bzirk:$('#hdBzirk').val(),datid:$('#hdDatid').val()},
		loadMsg : '数据远程载入中,请等待...',
		success : function(obj2) {
			var obj = eval('(' + getVkburColumns3() + ')');
			$('#datagrid').treegrid({  
			    fit:true,
			    method: 'get',
				idField: 'id',
				treeField:'productName', 
				rownumbers:true,
				showFooter:true,
				data:eval('('+obj2+')'),
				frozenColumns:[[
					{field:'productName',title:'产品名称',width:300}
				]],
				columns:obj,
				toolbar: [{
					iconCls: "icon-ok",
					text: "上一步",
					handler:function(){
						history.go(-1);
					}
				},"-",{
					iconCls: "icon-save",
					text: "审核",
					handler : function() {
						saveBzirkAudit();
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

function saveBzirkAudit(){
	var temp="-1";
	var datid=$('#hdDatid').val();
	var bzirk=$("#hdBzirk").val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
		data : {bzirk:bzirk,datid:datid},
		success : function(obj) {
			temp=obj;
		}
	});
	if(temp>2){
		alert('该大区已审核，不能重复提交');
	}else{
		location.href=appUrl+"/inOutStockAction!bzirkAuditDiff.jspa?bzirk="+$("#hdBzirk").val()+"&datid="+$("#hdDatid").val();
	}
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
