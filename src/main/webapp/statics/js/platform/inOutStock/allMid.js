$(document).ready(function() {
	
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
					url : appUrl + '/inOutStockAction!getOrgList2.jspa',
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
									}]]
						}); 
		  			}
				}); 
  			}
		}); 
	}
});

$('#bedat').combobox({  
    url:appUrl + '/inOutStockAction!getAllReportDate2.jspa',
    valueField:'bedatStr',  
    textField:'bedatStr'  
}); 
$('#endat').combobox({  
    url:appUrl + '/inOutStockAction!getAllReportDate2.jspa',
    valueField:'endatStr',  
    textField:'endatStr'  
}); 

$('#spart').combobox({  
    url:appUrl + '/inOutStockAction!getSpartList.jspa',
    valueField:'spart',  
    textField:'spart_txt'  
}); 
$('#mvgr1').combobox({  
    url:appUrl + '/inOutStockAction!getMvgr1List.jspa',
    valueField:'mvgr1',  
    textField:'mvgr1_txt'  
});
$('#dlvnr').combobox({  
    url:appUrl + '/inOutStockAction!getDLVRPList.jspa',
    valueField:'dlvnr',  
    textField:'dlvnm'  
});

function search(){
	var bedat=$('#bedat').combobox('getValue');
	var endat=$('#endat').combobox('getValue');
	
	var radioObj=document.getElementsByName("dateselect");
	var radioValue
	for(var i=0;i<radioObj.length;i++){
        if(radioObj[i].checked){
            radioValue = radioObj[i].value;
            break;
        }
    }
	if(radioValue=="0"){
		if(bedat==""||endat==""){
			alert('请选择采集日期');
			return;
		}
		low = bedat.substring(0,4);
		low += bedat.substring(5,7);
		low += bedat.substring(8);
		high = endat.substring(0,4);
		high += endat.substring(5,7);
		high += endat.substring(8);
		if(high-low<0){
            alert("采集日期应从小到大");
			return;
		}
	}

	
	var obj=document.getElementsByName('collect_type_base'); //选择所有name="'collect_type_base'"的对象，返回数组
	var s=''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
	} 
	if(s.length<1){
		alert('必须选择统计维度');
		return;
	}
	

	document.forms[0].action = "inOutStockAction!allHtmlMid.jspa";
	document.forms[0].method = "post";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
}



