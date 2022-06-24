$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	if($('#userId').val()!=""){
		$("#loginId").attr("readonly", "readonly");
	}
	//loadGrid();
});
function loadGrid() {

	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});
	$(".datebox :text").attr("readonly", "readonly");
	var curr_time = new Date();
	var strDate = curr_time.getFullYear() + "-";
	strDate += curr_time.getMonth() + 1 + "-";
	strDate += curr_time.getDate();
	$("#startDate").datebox("setValue", strDate);
	$('#postButton').linkbutton({
		disabled : true
	});
}
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
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
		document.getElementById('loginId').value = '';
	} else {
		$.messager.alert('Tips', successResult, 'info', function(data) {
			window.parent.closeMaintWindow();
			window.parent.search();
		});

	}
}

function submit() {
	var id =$('#userId').val();
	var a = $("#loginId").validatebox('isValid');
	var b = $("#userName").combobox('getValue')!="";
	if($("#userName").combobox('getValue')==""){
		$('#userName').combobox('textbox').focus();	
	}
	var c = $("#passWd").validatebox('isValid');
	var f = $("#repassWd").validatebox('isValid');
	var d = $("#email").validatebox('isValid');
	var e = $("#busMobilephone").validatebox('isValid');
	if (!(a && b && c && d && e && f)) {
		return;
	}
	$.messager.confirm('Confirm', 'Sure to submit?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			if (id==0) {
				form.action = appUrl + "/allUserAction!creatUser.jspa";// ?roleIds="+idd;
			} else {
				form.action = appUrl + "/allUserAction!updateUserInfo.jspa?userId="+id;// ?roleIds="+idd;
			}
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function cancel() {
	window.parent.closeMaintWindow();
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
	var WWidth = 600;
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
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}

function selectOrgTree() {
	initMaintWindowForOrg('ORG Tree', '/orgAction!orgTreePage.jspa');
}

function selectOrgTree4Post() {
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
		$('#postButton').linkbutton({
			disabled : false
		});
	}
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	isSelectedOrg();
}
function testId(val) {
	if (val == "") {
		document.getElementById("infoDiv").style.display = "";
		$("#infoTxt").val("Login Id is not allowed to blank!");
		document.getElementById('infoTxt').style.color = "red";
		$("#pass").val('0');
		return;
	}
	if($('#userId').val()!=""){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/customerAction!isLoginIdExist.jspa?time=" + new Date(),
		data : {
			loginId4Check : val
		},
		success : function(stringResult) {
			if (stringResult.code == "exist") {
				document.getElementById("infoDiv").style.display = "";
				$("#infoTxt").val(stringResult.result);
				document.getElementById('infoTxt').style.color = "red";
				$("#pass").val('0');
			} else {
				document.getElementById("infoDiv").style.display = "";
				$("#infoTxt").val(stringResult.result);
				document.getElementById('infoTxt').style.color = "blue";
				$("#pass").val('1');
			}

		}
	});
}


$('#userName').combogrid({
	panelHeight : 280,
	panelWidth : 300,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : true,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getDistiNameList.jspa',
	queryParams:{
		disti_name:$('#userName').val()
	},
	idField : 'disti_name',
	textField :'disti_name',
	columns : [[{
				field : 'disti_name',
				title : 'Disti Name',
				width : 200
			}, {
				field : 'payer_to',
				title : 'Payer To',
				width : 100
			}]],
			onSelect : function(index, record) {;
			},
			toolbar : '#toolbarDistiBranch',
});
function searcherDistiBranch(name1) {
	var queryParams = $('#userName').combogrid("grid").datagrid('options').queryParams;
	queryParams.disti_name = encodeURIComponent(name1);
	$('#userName').combogrid("grid").datagrid('reload');
} 





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



$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '字段不匹配'
	}
});
