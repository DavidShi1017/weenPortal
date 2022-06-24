$(document).ready(function() {
	loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function loadGrid(){
	$("#datagrid").datagrid({
		columns:[[ 
			{field:'mvgr1_txt',title:'系列',width:70,align:'center',rowspan:2},
			{field:'nmdgp',title:'下月需货<br/>量(箱)',width:70,align:'center',rowspan:2},
			{field:'nmdgp_pri',title:'下月需货<br/>金额(万元)',width:70,align:'center',rowspan:2},
			{title:'基本目标',colspan:3},
			{title:'挑战目标',colspan:3},
			{title:'差异原因',rowspan:2,width:200,field:'difftxt',editor:'text'}
		],[
			{field:'jbmb_pri',title:'下月目标<br/>(万元)',align:'right',width:70},
			{field:'jbmb_diff',title:'<a title="差异额=下月需货金额-下月目标金额" style="color:blue;text-decoration:underline;">差异额<br>(万元)</a>',align:'center',width:70},
			{field:'jbmb_cyl',title:'<a title="差异率=差异金额/下月目标金额*100%" style="color:blue;text-decoration:underline;">差异率</a>',align:'center',width:60	},
			{field:'tzmb_pri',title:'下月目标<br/>(万元)',align:'right',width:70},
			{field:'tzmb_diff',title:'<a title="差异额=下月需货金额-下月目标金额"  style="color:blue;text-decoration:underline;">差异额<br>(万元)</a>',align:'center',width:70},
			{field:'tzmb_cyl',title:'<a title="差异率=差异金额/下月目标金额*100%" style="color:blue;text-decoration:underline;">差异率</a>',align:'center',width:60}

		]],
		method: 'get',
		singleSelect: true,
		border:false,
		fit:true,
		rownumbers: true,
		onClickRow: onClickRow,
		url:appUrl+ '/inOutStockAction!getNmdgpDiffDetailJson.jspa?ran='+Math.random(),
		queryParams: {reaid:$('#hdReaid').val()}
	});
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

function searchGrid(){
	loadGrid();
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

function saveVkgrpDiff(){
	endEditing();
	var msg=checkVkgrpDiff();
	if(msg==""){
		var rows=$('#datagrid').datagrid('getRows'); //获取根节点信息
		var diffList = [];
		$.each(rows, function(index, item){
			if(item.mvgr1!=""){
				var obj = {};
				obj.datid=$('#hdDatid').val();
				obj.vkgrp=$('#hdVkgrp').val();
				obj.vkbur="";
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
			url : appUrl + "/inOutStockAction!saveVkgrpDiff.jspa",
			data : {
				diffList:JSON.stringify(diffList),
				reason:encodeURIComponent(reason),
				reaid:$('hdReaid').val(),
				datid:$('#hdDatid').val()
			},
			success : function(obj) {
				if(obj=="1"){
					alert('操作成功！');
					location.href=appUrl+"/inOutStockAction!vkgrpAudit.jspa";
				}else if("0"){
					$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
				}else{
					$.messager.alert('提示：', '操作失败!', 'info');
				}
			}
		});
	}else{
		alert(msg);
	}
}

function checkVkgrpDiff(){
	var a="";
	var rows=$('#datagrid').datagrid('getRows'); //获取根节点信息
	$.each(rows, function(index, item){
		if(item.mvgr1!=""){
			
			var planPri=item.nmdgp_pri;
			var jbmb=item.jbmb_pri;
			var difftxt=item.difftxt;

			var b=0;
			if(jbmb>0){
				b=(planPri-jbmb)/jbmb;
			}
			if(b>0.1||b<-0.1){
				if(difftxt.length==0){
					a+=item.mvgr1_txt+"差异原因未填写,\n";
				}
			}	
		}
	});
	
	if(a.length>0){
		a=a+"请完善系列差异原因";
	}
	var reason=$('#reason').val();
	if(a==""&&reason.length==0){
		a="整体差异原因未填写";
		$('#cc').layout('expand','south'); 
		$('#reason').focus();
	}
	
	return a;
}

(function($){
	 var buttonDir = {north:'down',south:'up',east:'left',west:'right'};
	    $.extend($.fn.layout.paneldefaults,{
	        onBeforeCollapse:function(){
	            /**/
	            var popts = $(this).panel('options');
	            var dir = popts.region;
	            var btnDir = buttonDir[dir];
	            if(!btnDir) return false;
	 
	            setTimeout(function(){
	                var pDiv = $('.layout-button-'+btnDir).closest('.layout-expand').css({
	                    textAlign:'center',lineHeight:'18px',fontWeight:'bold'
	                });
	 
	                if(popts.title){
	                    var vTitle = popts.title;
	                    if(dir == "east" || dir == "west"){
	                        var vTitle = popts.title.split('').join('<br/>');
	                        pDiv.find('.panel-body').html(vTitle);
	                    }else{
	                        $('.layout-button-'+btnDir).closest('.layout-expand').find('.panel-title')
	                        .css({textAlign:'left'})
	                        .html(vTitle)
	                    }
	                     
	                }   
	            },100);
	             
	        }
	    });
	})(jQuery);