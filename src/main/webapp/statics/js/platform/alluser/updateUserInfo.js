$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	isSelectedOrg();
	loadGrid();
	$('#updatePwd').dialog('close');
});

//代理商客户
var pcFirst = true;
$('#trafficExpense').combogrid({
	panelHeight : 280,
	panelWidth : 400,
	width:250,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
queryParams:{
		search  : encodeURIComponent($("#trafficExpense").val()),
	},
	idField : 'customer_code',
	textField : 'customer_code',
	columns : [[{
				field : 'customer_code',
				title : 'Code',
				width : 80,
				formatter : function(value, row, rec) {
					var str=(row.customer_code).replace( /^0*/, '');
					return str;
				}
			}, {
				field : 'customer_name',
				title : 'CustomerName',
				width : 220
			},
			{
				field : 'country',
				title : 'city/country',
				width : 100,
				//hidden:true
			}]],
			toolbar : '#toolbarCustomer',
	onSelect : function(index, record) {
		//$('#empPostId').val(record.customer_name);
	},
			onLoadSuccess: function(data) {
				if(pcFirst){					
					$('#trafficExpense').combogrid('grid').datagrid('getPager').pagination("select", 1);
					pcFirst=false;
				}
			},
});
function searcherCustomer(name1) {
pcFirst = true;
	var queryParams = $('#trafficExpense').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#trafficExpense').combogrid("grid").datagrid('reload');
} 


$('#dept').combobox({
	url : appUrl+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=558',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 173,
	onSelect : function(r){
	} 
});

function loadGrid() {
	$('#updatePwd').dialog({
		title:"密码修改",
		modal : true,
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				updatePassWord();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updatePwd').dialog('close');
			}
		}]
	});

	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});

	$('#sex').combobox('setValue', $('#sexValue').val());
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#roleIds')
			.combogrid(
					{
						url : appUrl
								+ '/allUserAction!getSelectedStationsJSON.jspa?orgId='
								+ $('#orgId').val()+'&stationId='
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
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				window.parent.closeOrgTree();
				window.parent.search();
			}
		});
	}else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
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

	var form = window.document.forms[0];

	form.action = appUrl + "/customerAction!updateCustomerUser.jspa?userId=" + $('#userId').val();
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
function initMaintWindow4Post(title, url) {
	var url = appUrl + url;
	var WWidth =600;
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
						closed : true,///
						closable : true,//
						left: $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}

function selectOrgTree() {
	/*if ($('#roleIds').val() != "") {
		$.messager.alert('Tips', "该人员现组织下有岗位存在，请先解除岗位关系再更换组织", 'info');
		return;
	}*/
	initMaintWindowForOrg('Select Org', '/orgAction!orgTreePage.jspa');
}

function selectOrgTree4Post(){
	initMaintWindow4Post('选择职务', '/orgAction!orgTreePage4Post.jspa');
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
function isSelectedOrg() {
	if (document.getElementById('orgName').value == '') {
		$('#roleIds').combo({
			disabled : true
		});
	} else {
		$('#roleIds')
				.combogrid(
						{
							url : appUrl
									+ '/allUserAction!getSelectedStationsJSON.jspa?orgId='
									+ $('#orgId').val()
						});
		$('#roleIds').combogrid("grid").datagrid('reload');
		$('#roleIds').combo({
			disabled : false
		});
	}
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
//	isSelectedOrg();
}
function testId(val) {
	if (val == "") {
		$.messager.alert('Tips', "Login Id is not allowed to blank!", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!isLoginIdExist.jspa?loginId4Check="
			+ val;
	form.submit();
}
function resetPwd(val) {
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!resetPWd.jspa?userId=" + val;
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