var isload=0;//如果是从stock跳过来的，执行完一次后isload为1

$(document).ready(function() {
	//loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
	checkIsKunnr();
	initCombogrid();
});

function checkIsKunnr(){
	var kunnm=$('#hdKunnr_txt').val();
	if(kunnm!=""){//是客户
		$('#table2').css('display','block'); 
		//$('#kunnr').combogrid('setValue',kunnr);
		return true;
	}else{
		$('#table1').css('display','block'); 
		return false;
	}
}



$('#datagrid').treegrid({
	idField: 'id',
	treeField:'productName', 
	columns:[[ 
		{field:'productName',width:450,title:'产品名称'}, 
		{field:'matnr',width:80,title:'产品编码'}, 
		{field:'kbetr',width:100,title:'单价',hidden:true},
		{field:'spart',width:100,title:'品类',hidden:true}, 
		{field:'mvgr1',width:100,title:'系列',hidden:true}, 
		{field:'count1',title:$("#hdMonth1").val()+'<br>月末库存',width:70,align:'right',hidden:true},
		{field:'count2',title:$("#hdMonth2").val()+'<br>月末库存',width:70,align:'right',hidden:true},
		{field:'count4',title:'下月计划(箱)',width:80,align:'right',editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!="") {return 'background-color:#FFF7bb';}}}
	]],
	border:false,
	fit:true,
	rownumbers: true,
	//fitColumns:true,
	showFooter:true,
	toolbar: [{
		iconCls: "icon-ok",
		text: "提交",
		handler:function(){
			saveKunnrStock();
		}
	},"-",{
		iconCls: "icon-excel",
		text: "Excel导入",
		handler:function(){
			importKunnrPlan();
		}
	}],
	
	onClickCell: function (value, row) {
		editValue(value,row);
	}
	
});

function loadGrid() {
	_loadwaiting();
	setTimeout(test,500);//500毫秒后开始复制工作
}
function loadGrid2() {
	isload=0;
	_loadwaiting();
	setTimeout(test,500);//500毫秒后开始复制工作
}
function test(){
	if(checkIsKunnr()){
		$.ajax({
			type : "get",
			async : false,
			url : appUrl+ '/inOutStockAction!getKunnrPlan.jspa?ran='+Math.random(),
			data : {kunnr:$('#hdKunnr').val()},
			loadMsg : '数据远程载入中,请等待...',
			success : function(obj) {
				if(obj!=""){
					$('#datagrid').treegrid({
						idField: 'id',
						treeField:'productName', 
						fit:true,
						data:eval('('+obj+')')
					});
				}else{
					alert('没有数据');
					$('#datagrid').treegrid('loadData',{total:0, rows:[]});
				}
			}
		});
	}else{
		$.ajax({
			type : "get",
			async : false,
			url : appUrl+ '/inOutStockAction!getKunnrPlan.jspa?ran='+Math.random(),
			data : {kunnr:$('#kunnr').combogrid('getValue')},
			loadMsg : '数据远程载入中,请等待...',
			success : function(obj) {
				if(obj!=""){
					$('#datagrid').treegrid({
						idField: 'id',
						treeField:'productName', 
						fit:true,
						data:eval('('+obj+')')
					});
				}else{
					alert('没有数据');
					$('#datagrid').treegrid('loadData',{total:0, rows:[]});
				}
			}
		});
	}
	_removeloading();
}





function initCombogrid(){
	if(!checkIsKunnr()){
	
		var bzirk=$('#hdBzirk').val();
		var vkbur=$('#hdVkbur').val();
		var vkgrp=$('#hdVkgrp').val();
		var kunnr=$('#hdKunnr').val();
		$('#bzirk').combogrid({
			width:120,
			panelHeight : 340,
			panelWidth : 420,
			pagination : false,
			multiple : false,
			method : 'post',
			singleSelect : true,
			url:appUrl+ '/inOutStockAction!getOrgList.jspa?parentId=10000002',
			//queryParams: {parentId:'10000002'},
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
								$('#kunnr').combogrid({
									width:250,
									panelHeight : 340,
									panelWidth : 420,
									pagination : false,
									multiple : false,
									method : 'post',
									singleSelect : true,
									url : appUrl + '/inOutStockAction!getKunnrList.jspa',
									queryParams: {vkgrp:recordVkgrp.orgCode},
									idField : 'kunnr',
									textField : 'kunnm',
									columns : [[{
												field : 'kunnr',
												title : '客户编码',
												width : 100
											}, {
												field : 'kunnm',
												title : '客户名称',
												width : 300
											}]],
									onSelect : function(indexKunnr,recordKunnr) {
										search1();
						  			},
									onLoadSuccess:function(){
										if(kunnr!=""&&kunnr!="${kunnr}"){
											$('#kunnr').combogrid('setValue',kunnr);
											isload=1;
											$('#kunnr').combogrid('disable');
											$('#vkbur').combogrid('disable');
											$('#vkgrp').combogrid('disable');
											$('#bzirk').combogrid('disable');
										}
									}
								}); 
								
				  			},
							onLoadSuccess:function(){
								if(vkgrp!=""&&vkgrp!="${vkgrp}"){
									$('#vkgrp').combogrid('setValue',vkgrp);
									
								}
							}
						}); 
						
		  			},
					onLoadSuccess:function(){
						if(vkbur!=""&&vkbur!="${vkbur}"){
							$('#vkbur').combogrid('setValue',vkbur);
						}
					}
				}); 
				
			},
			onLoadSuccess:function(){
				if(bzirk!=""&&bzirk!="${bzirk}"){
					$('#bzirk').combogrid('setValue',bzirk);
				}
			}
		});
	}else{
		var kunnr=$('#hdKunnr').val();
		
		if(kunnr!=""&&kunnr!="${kunnr}"){
			$('#kunnr').combogrid('setValue',kunnr);
			search1();
			isload=1;
			//$('#kunnr').combogrid('disable');
			//$('#vkbur').combogrid('disable');
			//$('#vkgrp').combogrid('disable');
			//$('#bzirk').combogrid('disable');
			
		}
	}
	
}



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
function endEditing(){
	if (editingId == undefined){return true;}
	var row2 = $('#datagrid').treegrid('getSelected');
	if(row2){
		$('#datagrid').treegrid('endEdit', editingId);
	}
}

function search1(){
	//alert(1);
	if(isload==0){
		loadGrid();
	}
	//$('#hideFrame').bind('load', promgtMsg);
	
}




function saveKunnrStock(){
	endEditing();
	var postStr="";//用来存储list的数据，最后post出去
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
		if(rootRows.length==0){
			alert('没有数据！');
			return;
		}
		for(var s=0;s<rootRows.length;s++){
			var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
			$.each(rows, function(index, item){
				postStr+=item.matnr+"@@";
				postStr+=item.count1+"@@";
				postStr+=item.count2+"@@";
				postStr+=item.count4+"@@";
				postStr+=encodeURIComponent(item.productName)+"@@";
				postStr+=item.kbetr+"@@";
				postStr+=item.spart+"@@";
				postStr+=item.mvgr1+"##";
			}); 
		}
		if(checkIsKunnr()){
			var temp="-1";
			var datid=$('#hdDatid').val();
			$.ajax({
				type : "get",
				async : false,
				url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
				data : {kunnr:$('#hdKunnr').val(),datid:datid},
				success : function(obj) {
					temp=obj;
				}
			});
			if(temp=="0"){
				$.ajax({
					type : "post",
					async : false,
					url : appUrl + "/inOutStockAction!saveKunnrPlan.jspa",
					data : {
						stockListStr:postStr,
						kunnr:$('#hdKunnr').val(),
						datid:$('#hdDatid').val(),
						bedat:$('#hdBedat').val(),
						endat:$('#hdEndat').val(),
						month:$('#hdMonth').val()
					},
					success : function(obj) {
						if(obj=="1"){
							//$.messager.alert('提示：', '操作成功!', 'info');
							alert('操作成功');
							var kunnr=$('#hdKunnr').val();
							if(kunnr!=""&&kunnr!="${kunnr}"){
								location.href=appUrl + "/inOutStockAction!kunnrStock.jspa";
							}else{
								location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa";
							}
							
						}else if("0"){
							$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('提示：', '客户所在片区已审核，不能提交!', 'info');
			}
		}else{
			var temp="-1";
			var datid=$('#hdDatid').val();
			$.ajax({
				type : "get",
				async : false,
				url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
				data : {kunnr:$('#kunnr').combogrid('getValue'),datid:datid},
				success : function(obj) {
					temp=obj;
				}
			});
			if(temp=="0"){
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
							//$.messager.alert('提示：', '操作成功!', 'info');
							alert('操作成功');
							var kunnr=$('#hdKunnr').val();
							if(kunnr!=""&&kunnr!="${kunnr}"){
								location.href=appUrl + "/inOutStockAction!kunnrStock.jspa";
							}else{
								location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa";
							}
							
						}else if("0"){
							$.messager.alert('提示：', '数据已审核，不能再保存', 'info');
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('提示：', '客户所在片区已审核，不能提交!', 'info');
			}
		}
	}else{
		$.messager.alert('提示：', '不在有效的日期范围内，有效的提报日期为'+$("#hdBeref").val()+'至'+$("#hdEnref").val()+'！', 'info');
		return;
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

function importKunnrPlan(){//导入excel
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var kunnr=$("#kunnr").combogrid("getValue");
		var datid=$('#hdDatid').val();
		
		if(kunnr==""){
			alert("请先选择客户");
		}else{
			var temp="-1";
			var datid=$('#hdDatid').val();
			$.ajax({
				type : "get",
				async : false,
				url : appUrl+ '/inOutStockAction!getMlbssAusta.jspa?ran='+Math.random(),
				data : {kunnr:kunnr,datid:datid},
				success : function(obj) {
					temp=obj;
				}
			});
			if(temp=="0"){
				initWindows('导入产品库存', '/inOutStockAction!importKunnrPlan.jspa?kunnr='+kunnr+"&datid="+datid,500,300);
			}else{
				alert("客户所在片区已审核，不能导入");
			}
		}
	}else{
		$.messager.alert('提示：', '不在有效的日期范围内，有效的提报日期为'+$("#hdBeref").val()+'至'+$("#hdEnref").val()+'！', 'info');
		return;
	}
}

function importSuccess(){
	if(confirm('需求导入成功，请问现在需要返回库存上报吗？')){
		location.href=appUrl + "/inOutStockAction!kunnrStock.jspa";
	}
}