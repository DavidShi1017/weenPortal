$(document) .ready( function() {
	//empCodeBox();	
	$('#empCode').combogrid('setValue', $('#empCodes').val());
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		close();
		window.parent.location.reload();
	}
}

function submit() {

	var a = $("#empCode").combobox('isValid');
	var c = true;
	var b = $("#beginDate").datebox('isValid');
	var d = $("#endDate").datebox('isValid');
	if (!(a && b && c && d)) {
		 $.messager.alert('Tips', "提示 ：请正确填写表单信息 ！", 'warning');
		 return ;
	}
	var cust =$('#orgId').val();
	var empCode = $('#empCode').combobox('getValue');
	var id =  $("#id").val();
	if(id>0){
		var arr = cust.split(",");
		if(arr.length>1){
			$.messager.alert('Tips', "提示 ：修改时请选择一个门店 ！", 'warning');
			return ;
		}
	}
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/userOrgAction!createUserOrg.jspa",
		data : {
			id:$("#id").val(),
			orgNumbers:cust,
			emp_code:empCode,
			begin_date:$('#beginDate').datebox('getValue'),
			end_date:$('#endDate').datebox('getValue')
		},
		success : function(obj) {
			var id =$("#id").val();
			if(obj>0){
				$.messager.alert('提示：', '操作成功!', 'info');
				window.parent.searchList();
				window.parent.closeMaterial();
				if(!(id>0)){
					$("#id").val(obj);
				}
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	}); 
}


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};

//function empCodeBox(){
	$('#empCode').combogrid({
		panelHeight : 280,
		panelWidth : 450,
		pagination : true,
		multiple : false,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/allUserAction!getUserInfoList.jspa?bhxjFlag=C&orgId=10000000&ran='+ Math.random(),
		idField : 'loginId',
		textField : 'userName',
		columns : [[{
					field : 'loginId',
					title : '员工编号',
					width : 120
				}, {
					field : 'userName',
					title : '员工名称',
					width : 250
				}]],
		toolbar : '#toolbarEmpCode',
		onSelect : function(index, record) {
			 
		}
	});
//}
//上级经销商查询框
function searcherEmp(name1) {
	var queryParams = $('#empCode').combogrid("grid").datagrid('options').queryParams;
	queryParams.loginId = encodeURIComponent(name1);
	$('#empCode').combogrid("grid").datagrid('reload');
}

 

//上级经销商查询框
function searcherCust(name1) {
	var queryParams = $('#customerNumber').combogrid("grid").datagrid('options').queryParams;
	queryParams.custNumber = encodeURIComponent(name1);
	$('#customerNumber').combogrid("grid").datagrid('reload');
}

/**
 * 打开组织树
 */
function selectOrgTree() {
	openWindow(false,'选择组织', '/customerAction!orgTreePage.jspa?orgId=10000000', 400, 400,100,20);
}

/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	closeWindow();
}


//创建窗口对象
function openWindow(ffit,title, url, WWidth, WHeight,l,t) {
	var url = crmUrl + url;
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
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						fit:ffit,
						left : l,
						top: t
					});

	$win.window('open');

}

//关闭窗口
function closeWindow() {
	$("#hiddenWin").window('close');
}
function close() {
	window.parent.closeMaterial();
}
