$(document) .ready( function() {
	empCodeBox();	
	customerCostBox();
	//$('#empCode').combogrid('setValue', $('#empCodes').val());
	$('#customerNumber').combogrid('setValue',$('#customerNumbers').val());
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
	var c = $("#customerNumber").combobox('isValid');
	var b = $("#beginDate").datebox('isValid');
	var d = $("#endDate").datebox('isValid');
	var id =  $("#id").val();
	if (!(a && b && c && d)) {
		 $.messager.alert('Tips', "提示 ：请正确填写表单信息 ！", 'warning');
		 return ;
	}
	var cust =$('#customerNumber').combobox('getText');
	var empCode = $('#empCode').combobox('getValue');
	
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
		url : appUrl + "/userCustAction!createUserCust.jspa",
		data : {
			id:$("#id").val(),
			customerNumber:cust,
			empCode:empCode,
			beginDate:$('#beginDate').datebox('getValue'),
			endDate:$('#endDate').datebox('getValue')
		},
		success : function(obj) { 
			var id =$("#id").val();
			if(obj>0){
				$.messager.alert('提示：', '操作成功!', 'info');
				window.parent.searchList();
				window.parent.closeMaterial();
//				if(!(id>0)){
//					$("#id").val(obj);
//				}
			}else if(obj<0){
				$.messager.alert('提示：', '保存数据在系统中已存在，无法保存！', 'info');
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

function empCodeBox(){
	
	$('#empCode').combogrid({
		panelHeight : 280,
		panelWidth : 370,
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
					width : 100
				}, {
					field : 'userName',
					title : '员工名称',
					width : 250
				}]],
		toolbar : '#toolbarEmpCode',
		onSelect : function(index, record) {
			 
		}
	});
	
}
//上级经销商查询框
function searcherEmp(name1) {
	var queryParams = $('#empCode').combogrid("grid").datagrid('options').queryParams;
	queryParams.loginId = encodeURIComponent(name1);
	$('#empCode').combogrid("grid").datagrid('reload');
}

 
function customerCostBox(){
	$('#customerNumber').combogrid({
		panelHeight : 280,
		panelWidth : 370,
		pagination : true,
		multiple : true,
		method : 'post',
		singleSelect : false,
		url : crmUrl + '/customerAction!customerSearch.jspa',
		checkbox:true,
		editable:true,
		idField : 'custNumber',
		textField : 'custNumber',
		columns : [[{
					field : 'ck',
					checkbox : true 
				},{
					field : 'custNumber',
					title : '门店编码',
					width : 100
				}, {
					field : 'custName',
					title : '门店名称',
					width : 250
				}]],
		toolbar : '#toolbarCust',
		onSelect : function(index, record) {
			 
		}
	});
	if($('#id').val()>0){
		$('#customerNumber').combogrid({multiple : false,singleSelect : true});
	}
}
//上级经销商查询框
function searcherCust(name1) {
	var queryParams = $('#customerNumber').combogrid("grid").datagrid('options').queryParams;
	queryParams.custNumber = encodeURIComponent(name1);
	$('#customerNumber').combogrid("grid").datagrid('reload');
}
function close() {
	window.parent.closeMaterial();
}