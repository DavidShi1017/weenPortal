$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
	$('#updatePwd').dialog('close');
});
function loadGrid() {
//	$('#startDate').datebox('disable');
//	$('#roleIds').combogrid(
//			{
//				panelWidth : 580,
//				idField : 'stationId',
//				textField : 'stationName',
//				pagination : true,//
//				rownumbers : true,//
//				collapsible : false,//
//				fit : true,//
//				method : 'post',
//				multiple : true,
//				url : appUrl
//						+ '/allUserAction!getSelectedStationsJSON.jspa?orgId='
//						+ $('#orgId').val(),
//				columns : [ [ {
//					field : 'ck',
//					checkbox : true
//				}, {
//					field : 'id',
//					title : 'POSID',
//					width : 100,
//					hidden : true
//				}, {
//					field : 'stationId',
//					title : '岗位ID',
//					width : 100
//				}, {
//					field : 'stationName',
//					title : '岗位名称',
//					width : 150
//				}, {
//					field : 'orgName',
//					title : '公司名',
//					width : 150
//				}, {
//					field : 'userName',
//					title : '用户名',
//					width : 100
//				} ] ],
//				toolbar : '#toolbar'
//
//			});
	$('#updatePwd').dialog({
		title:"Change Password",
		modal : true,
		buttons:[{
			text:'Submit',
			iconCls:'icon-ok',
			handler:function(){
				updatePassWord();
			}
		},{
			text:'Cancel',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updatePwd').dialog('close');
			}
		}]
	});

//	var q = $('#roleIds').combogrid("grid").datagrid();
//	$('#roleIds').combo({
//		multiple : true
//	});
//	$(q).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});
//	$(".datebox :text").attr("readonly", "readonly");
//	$('#roleIds').combogrid('setValues', [ $('#stationNames').val() ]);
//	$('#roleIds').combo({
//		disabled : true
//	});
	$('#sex').combobox('setValue', $('#sexValue').val());
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#roleIds')
			.combogrid(
					{
						url : appUrl
								+ '/allUserAction!getSelectedStationsJSON.jspa?stationId='
								+ val
					});
	$('#roleIds').combogrid("grid").datagrid('reload');

}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$('#updatePwd').dialog('close');
	}
}

function submit() {
	var a = $("#loginId").validatebox('isValid');
	var b = $("#userName").validatebox('isValid');
	var d = $("#email").validatebox('isValid');
	var e = $("#busMobilephone").validatebox('isValid');
	if (!(a && b && d && e)) {
		return;
	}
	//var ids = $('#roleIds').combogrid('grid').datagrid('getSelections');
//	var idd = '';
//	for (i = 0; i <= ids.length - 1; i++) {
//		idd += ids[i].id + ',';
//	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!updateUserInfo.jspa?roleIds=0" 
			+ "&userId=" + $('#userId').val();
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
	window.parent.search();
}

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						left : ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}
function testId(val) {
	if (val == "") {
		$.messager.alert('Tips', "用户ID为空", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!isLoginIdExist.jspa?loginId4Check="
			+ val;
	form.submit();
}
function updatePwd(val) {
	$('#updatePwd').dialog('open');
}
function updatePassWord(){
	var val = $('#userId').val();
	var passWd = $('#passWd').val();
	var repassWd = $('#repassWd').val();
	if(passWd.length<6||passWd!=repassWd){
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!updatePwd.jspa?userId=" + val+"&repassWd="+repassWd;
	form.submit();
}

$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '字段不匹配'
	}
});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if(event.keyCode == 8) {
	     // 如果是在textarea内不执行任何操作
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // 如果是readOnly或者disable不执行任何操作
	  if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	     }
	return true;
};