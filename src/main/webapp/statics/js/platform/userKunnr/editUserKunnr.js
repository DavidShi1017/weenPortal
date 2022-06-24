$(document) .ready( function() {
	empCodeBox();	
	kunnrBox();
	$('#empCode').combogrid('setValue', $('#empCodes').val());
	$('#kunnr').combogrid('setValue',$('#kunnrs').val());
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
	var c = $("#kunnr").combobox('isValid');
	var b = $("#beginDate").datebox('isValid');
	var d = $("#endDate").datebox('isValid');
	if (!(a && b && c && d)) {
		 $.messager.alert('Tips', "提示 ：请正确填写表单信息 ！", 'warning');
		 return;
	}
	var kunnr =$('#kunnr').combobox('getText');
	var empCode = $('#empCode').combobox('getValue');
	var id =  $("#id").val();
	if(id>0){
		var arr = kunnr.split(",");
		if(arr.length>1){
			$.messager.alert('Tips', "提示 ：修改时请选择一个客户 ！", 'warning');
			return;
		}
	}
	 
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/userKunnrAction!createUserKunnr.jspa",
		data : {
			id:$("#id").val(),
			kunnr:kunnr,
			empCode:empCode,
			beginDate:$('#beginDate').datebox('getValue'),
			endDate:$('#endDate').datebox('getValue')
		},
		success : function(obj) {
			var id =$("#id").val();
			if(obj>0){
				$.messager.alert('提示：', '操作成功!', 'info');
				window.parent.loadGrid();
				window.parent.closeMaterial();
			}else if(obj<0){
				$.messager.alert('提示：', '保存数据在系统中已存在，无法保存！', 'info');
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	}); 
}

function close(){
	window.parent.closeMaterial();
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
}


 
function kunnrBox(){
	$('#kunnr').combogrid({
		panelHeight : 280,
		panelWidth : 450,
		pagination : true,
		multiple : true,
		method : 'post',
		singleSelect : false,
		url : crmUrl + '/kunnrAction!kunnrSearch.jspa',
		checkbox:true,
		idField : 'kunnr',
		textField : 'kunnr',
		columns : [[{
					field : 'ck',
					checkbox : true 
				},{
					field : 'kunnr',
					title : '客户编码',
					width : 120
				}, {
					field : 'name1',
					title : '客户名称',
					width : 250
				}]],
		toolbar : '#toolbarKunnr',
		onSelect : function(index, record) {
			 
		}
	});
}

//员工查询
function searcherEmp(name1) {
	var queryParams = $('#empCode').combogrid("grid").datagrid('options').queryParams;
	queryParams.loginId = encodeURIComponent(name1);
	$('#empCode').combogrid("grid").datagrid('reload');
}
//客户查询
function searcherKunnr(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.kunnrId = encodeURIComponent(name1);
	$('#kunnr').combogrid("grid").datagrid('reload');
}
