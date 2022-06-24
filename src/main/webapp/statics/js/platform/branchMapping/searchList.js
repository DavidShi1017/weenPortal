$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/country/countryAction!getBranchMappingList.jspa',
				        loadMsg : 'Loading...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : "ID",
									field : 'id',
									width : 80,
									hidden : true,
									align : 'center'
								},
								{
									title : "DISTI HQ SOLDTO",
									field : 'disti_hq_soldto',
									width : 65,
									align : 'center',
								},
								{
									title : "Region Level2",
									field : 'region_level2',
									width : 110,
									align : 'center',
								},
								{
									title : "Global Account",
									field : 'global_account',
									width : 150,
									align : 'left'
								},
								{
									title : "Loc Rep Country",
									field : 'loc_rep_country',
									width : 80,
									align : 'center'
								},
								{
									title : "Create time",
									field : 'create_time',
									width : 80,
									align : 'center',
									formatter:function(value,row ,index){
										if (value ==null || value==undefined ||value =='')
											return ''
										return utcToDate(value);
									}
								},
								{
									title : "Update time",
									field : 'update_time',
									width : 80,
									align : 'center',
									formatter:function(value,row ,index){
										if (value ==null || value==undefined ||value =='')
											return ''
										return utcToDate(value);
									}
							
								},

								] ],
						toolbar : [  "-", {
								text : 'Add',
								iconCls : 'icon-add',
								handler : function() {
									add();
								}
							}
							, "-", {
								text : 'Edit',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							},  "-", {
								text : 'Delete',
								iconCls : 'icon-remove',
								handler : function() {
									dele();
								}
							},"-", {
								text : 'Import',
								iconCls : 'icon-up',
								handler : function() {
									importExcel();
								}
							}, "-", {
								text : 'Download template',
								iconCls : 'icon-down',
								handler : function() {
									downloadExcelModel();
								}
							}, 
							"-", {
								text : 'Export Excel',
								iconCls : 'icon-excel',
								handler : function() {
									exportBranchMappingExcel();
								}
							},
							],
						onDblClickRow:function(rowIndex,rowData){
							edit();
//							initMaintAccount(false,'800','200','Detail Information', '/priceRuleAction!toViewPriceRule.jspa?id='+rowData.id,100,100);
						},
						//onLoadSuccess:'fixRownumber'
					});
	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

//�۸�����
/*$('#price_type').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=550',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});*/

function exportBranchMappingExcel(){
	$.messager.confirm('Tips', 'Confirmed about OutPort?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/countryAction!downloadExcelForBranchMapping.jspa';//?disti_hq_soldto='+$("#disti_hq_soldto").val() +'&global_account='+$("#global_account").val()+'&region_level2='+$("#region_level2").val()+'&loc_rep_country='+$("#loc_rep_country").val();
 			form.target = "hideFrame";
			form.submit();
		}
	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.disti_hq_soldto = $("#disti_hq_soldto").val();
	queryParams.region_level2 = $("#region_level2").val();
	queryParams.global_account = $("#global_account").val();
	queryParams.loc_rep_country = $("#loc_rep_country").val();
	$("#datagrid").datagrid('load');
}
function sync(){
	initMaintAccount(false,'400','180','Sync', '/priceRuleAction!toSyncPrice.jspa',100,100);	
}


function add(){
	initMaintAccount(false,'400','350','Add Branch Mapping', '/countryAction!toCreateBranchMapping.jspa',100,100);	
}

function edit(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data you need to operate', 'warning');
		return;
	} else {
		initMaintAccount(false,'400','350','Modify Branch Mapping', '/countryAction!toUpdateBranchMapping.jspa?id='+rows[0].id,100,100);	
	}
}


function importExcel(){
	initMaintAccount(false,'700','350','Import Branch Mapping', '/countryAction!importExcel.jspa',100,100);
}
//ģ������
function downloadExcelModel(){
		$.messager.confirm('Tip', 'Sure to download the template?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/file/fileAction!downloadExcelModel.jspa?excelModel='+encodeURI("branchMapping.xls");
	 			form.target = "hideFrame";
				form.submit();
			}
		});
}
/**
 * ɾ��
 */
function dele() {
	$.messager.confirm('Confirm', 'Sure to delete?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('info', 'No data selected!');
				return;
			}
			var ids ="";
			for(var i = 0;i <rows.length;i++){
				ids = rows[i].id +","+ids;
			}
			var form = window.document.forms[0];
			form.action = appUrl + '/countryAction!deleteBranchMapping.jspa?ids='+ids+'&state=-1';
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function initMaintAccount(ffit,widte,height,title,url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}


// �رմ���ҳ��
function closeMaintWindow() {
	$("#hiddenWin").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function utcToDate(utcCurrTime) {
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
//	date = date + month[str[1]] + "-" + str[2] + " " + str[3];//到时分秒
	date = date + month[str[1]] + "-" + str[2];//年月日
	return date;
}	

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
		if(event.keyCode == 8) {
		     // �������textarea�ڲ�ִ���κβ���
		  if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
		            event.returnValue = false; 
		        // �����readOnly����disable��ִ���κβ���
		  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
		            event.returnValue = false;                             
		}

	return true;
};
$.extend($.fn.datagrid.methods, {  
    fixRownumber : function (jq) {  
        return jq.each(function () {  
            var panel = $(this).datagrid("getPanel");  
            var clone = $(".datagrid-cell-rownumber", panel).last().clone();  
            clone.css({  
                "position" : "absolute",  
                left : -1000  
            }).appendTo("body");  
            var width = clone.width("auto").width();  
            if (width > 25) {  
                //���5������,����һ��߾�  
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 5);  
                $(this).datagrid("resize");  
                //һЩ������  
                clone.remove();  
                clone = null;  
            } else {  
                //��ԭ��Ĭ��״̬  
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");  
            }  
        });  
    }  
});  