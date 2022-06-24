









$(document).ready(function() {
	if(checkIsKunnr()){
		search1();
	}
	//loadGrid();
	//$('#hideFrame').bind('load', promgtMsg); 
});

function checkIsKunnr(){
	var kunnr=$('#hdKunnr').val();
	if(kunnr.length>5){//是客户
		$('#table2').css('display','block'); 
		$('#kunnr').combogrid('setValue',kunnr);
		//search1();
		return true;
	}else{
		$('#table1').css('display','block'); 
		return false;
	}
}

$('#datagrid').treegrid({
	method: 'get',
	idField: 'id',
	treeField:'productName', 
	//loadMsg : '数据远程载入中,请等待...',
	columns:[[ 
		{field:'productName',width:350,title:'产品名称',rowspan:2},
		{field:'matnr',width:80,title:'产品编码',rowspan:2}, 
		{field:'kbetr',width:100,title:'单价',hidden:true,rowspan:2},
		{field:'spart',width:100,title:'品类',hidden:true,rowspan:2}, 
		{field:'mvgr1',width:100,title:'系列',hidden:true,rowspan:2}, 
		{field:'preps',title:$("#hdMonth1").val()+'<br>月末库存',width:70,align:'right',rowspan:2},
		{field:'tpiwh',title:$("#hdMonth2").val()+'<br>月入仓',width:70,align:'right',rowspan:2},
		{field:'tpowh',title:$("#hdMonth2").val()+'<br>月出仓',width:70,align:'right',rowspan:2},
		{title:$("#hdMonth2").val()+'月末库存(箱)',width:80,align:'right',colspan:2}
	],[{field:'finps',title:'合计',width:80,align:'right',editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!="") {return 'background-color:#FFF7bb';}}},
	   {field:'finps_pre',title:'其中隔年货',width:80,align:'right',editor:'numberbox',styler: function(value,row,index){if(row.matnr!=null&&row.matnr!=""){return 'background-color:#FFF7bb';}}}
	   ]],
	//border:false,
	fit:true,
	rownumbers: true,
	//fitColumns:true,
	animate: true,
	collapsible: true,
	//showFooter:true,
	toolbar: [{
		iconCls: "icon-save",
		text: "保存",
		handler:function(){
			saveKunnrStock(1);
		}
	},"-",{
		iconCls: "icon-ok",
		text: "保存并进行需求上报",
		handler:function(){
			saveKunnrStock(2);
		}
	},"-",{
		iconCls: "icon-add",
		text: "添加产品",
		handler : function() {
			createKunnrProduct();
		}
	},"-",{
		iconCls: "icon-excel",
		text: "Excel导入",
		handler:function(){
			importKunnrProduct();
		}
	}],
	
	onClickCell: function (value, row) {
		editValue(value,row);
	},
	onAfterEdit:function(row,changes){
		changeValue(row,changes);
	}
});


function loadGrid() {
	_loadwaiting();
//	var data;
//	$.ajax({
//		type : "get",
//		async : false,
//		url : appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//		data : {kunnr:$('#kunnr').combogrid('getValue')},
//		loadMsg : '数据远程载入中,请等待...',
//		success : function(obj) {
//			data=obj;
//			$('#datagrid').treegrid({
//				idField: 'id',
//				treeField:'productName', 
//				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
//				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
//				data:eval('('+data+')'),
//				//loadMsg : '数据远程载入中,请等待...'
//			});
//		}
//	});
	//alert(data);
	setTimeout(test,500);//500毫秒后开始复制工作
	
	//_removeloading();
}
function test(){
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getKunnrProduct.jspa?ran='+Math.random(),
		data : {kunnr:$('#kunnr').combogrid('getValue')},
		loadMsg : '数据远程载入中,请等待...',
		success : function(obj) {
			$('#datagrid').treegrid({
				idField: 'id',
				treeField:'productName', 
				//url:appUrl+ '/inOutStockAction!getKunnrProduct.jspa',
				//queryParams: {kunnr:$('#kunnr').combogrid('getValue')},
				data:eval('('+obj+')')
				//loadMsg : '数据远程载入中,请等待...'
			});
		}
	});
	_removeloading();
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
							onSelect : function(r) {
								search1();
				  			}
						}); 
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
								onSelect : function(r) {
									search1();
					  			}
							}); 
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

function endEditing(){
	if (editingId == undefined){return true;}
	var row2 = $('#datagrid').treegrid('getSelected');
	if(row2){
		$('#datagrid').treegrid('endEdit', editingId);
	}
}

//var editIndex = undefined;
//function onClickRow(index){
//	if (editIndex != index){
//		if (endEditing()){
//			$('#datagrid').datagrid('selectRow', index).datagrid('beginEdit', index);
//			editIndex = index;
//		} else {
//			$('#datagrid').datagrid('selectRow', editIndex);
//		}
//	}
//}
//function endEditing(){
//	if (editIndex == undefined){return true;}
//	if ($('#datagrid').datagrid('validateRow', editIndex)){
//		$('#datagrid').datagrid('endEdit', editIndex);
//		editIndex = undefined;
//		return true;
//	} else {
//		return false;
//	}
//}



function changeValue(row,changes){
//	var finps=parseFloat(row.finps);
//	var finps_this=parseFloat(row.finps_this);
//	var tpiwh=parseFloat(row.tpiwh);
//	var preps=parseFloat(row.preps);
//	
//	var finps_pre=finps-finps_this;
//	row.finps_pre=finps_pre;
//	var tpowh=preps+tpiwh-finps;
//	row.tpowh=tpowh;
	$('#datagrid').treegrid('refresh',row.id);  
}



function search1(){
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
}

function createKunnrProduct(){
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var kunnr=$("#kunnr").combogrid("getValue");
		if(kunnr==""){
			alert("请先选择客户");
		}else{
			var productIdList="0";
			var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
			for(var s=0;s<rootRows.length;s++){
				var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
				$.each(rows, function(index, item){
					productIdList+=","+item.matnr;
				}); 
			}
			initWindows('产品添加', '/inOutStockAction!addKunnrProduct.jspa?productIdList='+productIdList,800,500);
		}
	}else{
		$.messager.alert('提示：', '不在有效的日期范围内，有效的提报日期为'+$("#hdBeref").val()+'至'+$("#hdEnref").val()+'！', 'info');
		return;
	}
	//initWindows('产品添加', '/inOutStockAction!addKunnrProduct.jspa',800,600);
}
function importKunnrProduct(){//导入excel
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var kunnr=$("#kunnr").combogrid("getValue");
		if(kunnr==""){
			alert("请先选择客户");
		}else{
			initWindows('导入产品库存', '/inOutStockAction!importKunnrStock.jspa?kunnr='+kunnr,500,300);
		}
	}
	else{
		$.messager.alert('提示：', '不在有效的日期范围内，有效的提报日期为'+$("#hdBeref").val()+'至'+$("#hdEnref").val()+'！', 'info');
		return;
	}
}


function addProduct(names){
	var newProduct=new Array();
	var names2=new Array();
	names2=names;
	if(names2.length>0){
		for(var i=0;i<names2.length;i++){
			var mvgr1=names2[i].split("#")[0];
			var matnr=names2[i].split("#")[1];
			var maktx=names2[i].split("#")[2];
			var mvgr1_txt=names2[i].split("#")[3];
			var kbetr=names2[i].split("#")[4];
			var spart=names2[i].split("#")[5];
			
			
			var rows=$('#datagrid').treegrid('getChildren',mvgr1);
			if(rows.length==0){//没根节点
				//添加根节点
				$('#datagrid').treegrid('append',{
					data: [{
						productName: mvgr1_txt,
						id: mvgr1
					}]
				});
				$('#datagrid').treegrid('append',{
					parent: mvgr1,  
					data: [{
						id:matnr,
						productName: maktx,
						matnr: matnr,
						preps:0,
						tpiwh:0,
						tpowh:0,
						finps_pre:0,
						finps_this:0,
						finps:0,
						kbetr:kbetr,
						mvgr1:mvgr1,
						spart:spart
					}]
				});
				newProduct.push(matnr);
			}else{//有根节点
				var count=0;
				$.each(rows, function(index, item){
					if(item.matnr==matnr){
						count++;
					}
				}); 
				if(count==0){
					$('#datagrid').treegrid('append',{
						parent: mvgr1,  
						data: [{
							id:matnr,
							productName: maktx,
							matnr: matnr,
							preps:0,
							tpiwh:0,
							tpowh:0,
							finps_pre:0,
							finps_this:0,
							finps:0,
							kbetr:kbetr,
							mvgr1:mvgr1,
							spart:spart
						}]
					});
					newProduct.push(matnr);
				}
			}
		}
	}
	closeWindows();
	
	$('#datagrid').treegrid({
		rowStyler: function(row){
			for(var i=0;i<newProduct.length;i++){
				if (row.matnr==newProduct[i]){
					return 'background-color:#6293BB;';
				}
			}
		}
	});

}

function saveKunnrStock(type){
	endEditing();
	var postStr="";//用来存储list的数据，最后post出去
	var inTime=$("#hdInTime").val();//判断是否在日期内
	if(inTime=="in"){
		var rootRows=$('#datagrid').treegrid('getRoots'); //获取根节点信息
		if(rootRows.length==0){
			$.messager.alert('提示：', '没有数据!', 'info');
			return;
		}
		for(var s=0;s<rootRows.length;s++){
			var rows=$('#datagrid').treegrid('getChildren',rootRows[s].id);//获取子节点信息
			$.each(rows, function(index, item){
				postStr+=item.matnr+"@@";
				postStr+=item.preps+"@@";
				postStr+=item.tpiwh+"@@";
				postStr+=item.tpowh+"@@";
				postStr+=item.finps_pre+"@@";
				postStr+=item.finps+"@@";
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
					url : appUrl + "/inOutStockAction!saveKunnrStock.jspa",
					data : {
						stockListStr:postStr,
						kunnr:$('#hdKunnr').val(),
						vkgrp:$('#hdVkgrp').val(),
						vkbur:$('#hdVkbur').val(),
						bzirk:$('#hdBzirk').val(),
						datid:$('#hdDatid').val(),
						bedat:$('#hdBedat').val(),
						endat:$('#hdEndat').val(),
						month:$('#hdMonth').val()
					},
					success : function(obj) {
						if(obj=="1"){
							if(type==2){
								$.messager.alert('', '操作成功!', 'info',function(){window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#hdBzirk').val()+"&vkbur="+$('#hdVkbur').val()+"&vkgrp="+$('#hdVkgrp').val()+"&kunnr="+$('#hdKunnr').val();});
							}else{
								$.messager.alert('', '操作成功!', 'info');
							}
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('提示：', '客户所属片区已审核，不能提交！', 'info');
			}
		}
		else{
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
					url : appUrl + "/inOutStockAction!saveKunnrStock.jspa",
					data : {
						stockListStr:postStr,
						kunnr:$('#kunnr').combogrid('getValue'),
						vkgrp:$('#vkgrp').combogrid('getValue'),
						vkbur:$('#vkbur').combogrid('getValue'),
						bzirk:$('#bzirk').combogrid('getValue'),
						datid:$('#hdDatid').val(),
						bedat:$('#hdBedat').val(),
						endat:$('#hdEndat').val(),
						month:$('#hdMonth').val()
					},
					success : function(obj) {
						if(obj=="1"){
							//$.messager.alert('提示：', '操作成功!', 'info');
							alert('操作成功');
							if(type==2){
								window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#bzirk').combogrid('getValue')+"&vkbur="+$('#vkbur').combogrid('getValue')+"&vkgrp="+$('#vkgrp').combogrid('getValue')+"&kunnr="+$('#kunnr').combogrid('getValue');
							}
						}else{
							$.messager.alert('提示：', '操作失败!', 'info');
						}
					}
				});
			}else{
				$.messager.alert('提示：', '客户所属片区已审核，不能提交！', 'info');
				return;
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
    $("<div class=\"datagrid-mask-msg\"></div>").html("加载中，请等待...").appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2});  
}  
function _removeloading(){//隐藏等待信息  
    var wrap = $("#cc");  
    wrap.find("div.datagrid-mask-msg").remove();  
    wrap.find("div.datagrid-mask").remove();  
} 


function importSuccess(){
	if(confirm('导入成功，请问现在需要进行需求上报吗？')){
		if(checkIsKunnr()){
			window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#hdBzirk').val()+"&vkbur="+$('#hdVkbur').val()+"&vkgrp="+$('#hdVkgrp').val()+"&kunnr="+$('#hdKunnr').val();
		}else{
			window.location.href=appUrl + "/inOutStockAction!kunnrPlan.jspa?bzirk="+$('#bzirk').combogrid('getValue')+"&vkbur="+$('#vkbur').combogrid('getValue')+"&vkgrp="+$('#vkgrp').combogrid('getValue')+"&kunnr="+$('#kunnr').combogrid('getValue');
		}
	}
}


