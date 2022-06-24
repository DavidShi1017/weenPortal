$(document).ready(function() {
	loadGrid();
});

function getColumns(){//组合datagrid的标题
	var strTitle="";
	var fields=new Array();
	var titles=new Array();
	var widths=new Array();
	fields=$("#hdField").val().split(",");
	titles=$("#hdTitle").val().split(",");
	widths=$("#hdWidth").val().split(",");
	
	for(var i=0;i<fields.length;i++){
		strTitle+="{field:'"+fields[i]+"',title:'"+titles[i]+"',align:'left',width:"+widths[i]+"},";
	}
	strTitle+="{field:'',title:'没有的',align:'left',width:75,hidden:true}";
	return "[["+strTitle+"]]";
}


function loadGrid(){
	var bzirk=$('#hdBzirk').val();
	var vkgrp=$('#hdVkgrp').val();
	var vkbur=$('#hdVkbur').val();
	var kunnr=$('#hdKunnr').val();
	var bedat=$('#hdBedat').val();
	var endat=$('#hdEndat').val();
	var bedatAudit=$('#hdBedatAudit').val();
	var endatAudit=$('#hdEndatAudit').val();
	var spart=$('#hdSpart').val();
	var mvgr1=$('#hdMvgr1').val();
	var dlvnr=$('#hdDlvnr').val();
	var collect_type_base=$('#hdcollect_type_base').val();
	var dateselect=$('#hdDateselect').val();
	
	//$.messager.alert('提示：', getColumns(), 'info');
	//$.message.alert(getColumns());
	var obj = eval('(' + getColumns() + ')');
	//alert(obj);
	
	$("#datagrid").datagrid({
		method: 'get',
		async : false,
		url:appUrl+ '/inOutStockAction!getAllHtmlJson.jspa?ran='+Math.random(),
		queryParams: {bzirk:bzirk,vkgrp:vkgrp,vkbur:vkbur,kunnr:kunnr,bedat:bedat,endat:endat,bedatAudit:bedatAudit,endatAudit:endatAudit,spart:spart,mvgr1:mvgr1,dlvnr:dlvnr,collect_type_base:collect_type_base,dateselect:dateselect},
		columns:obj,
		fit:true,
		rownumbers: true,
		showFooter:true,
		pagination : true,
		pageSize:30,
		toolbar: [{
			iconCls: "icon-excel",
			text: "导出",
			handler:function(){
				exportReport();
			}
		}]
	});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 30,
		pageList : [ 30, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function exportReport(){
	var bzirk=$('#hdBzirk').val();
	var vkgrp=$('#hdVkgrp').val();
	var vkbur=$('#hdVkbur').val();
	var kunnr=$('#hdKunnr').val();
	var bedat=$('#hdBedat').val();
	var endat=$('#hdEndat').val();
	var bedatAudit=$('#hdBedatAudit').val();
	var endatAudit=$('#hdEndatAudit').val();
	var spart=$('#hdSpart').val();
	var mvgr1=$('#hdMvgr1').val();
	var dlvnr=$('#hdDlvnr').val();
	var collect_type_base=$('#hdcollect_type_base').val();
	var dateselect=$('#hdDateselect').val();
	
	var form = window.document.forms[0];
	form.action = appUrl + '/inOutStockAction!exportAllReport.jspa?bzirk='+bzirk+'&vkbur='+vkbur+'&vkgrp='+vkgrp+
		'&kunnr='+kunnr+'&bedat='+bedat+'&endat='+endat+'&bedatAudit='+bedatAudit+'&endatAudit='+endatAudit+
		'&spart='+spart+'&mvgr1='+mvgr1+'&dlvnr='+dlvnr+'&collect_type_base='+collect_type_base+'&dateselect='+dateselect;
	form.target = "hideFrame";
	form.submit();
}

