$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	if ($('#id').val() != 0 && $('#id').val() != "") {
		$('#end_customer_name').attr({
			readonly : false
		});
	}
	if ($('#editGroupMark').val() == "1") {
		$('#grouplabel').css("display", 'block')
		$('#groupdisplay').css("display", 'block')
	    $('#chlabel').css("display", 'block')
		$('#chDisplay').css("display", 'block')
		$('#tierlabel').css("display", 'block')
		$('#tierDisplay').css("display", 'block')
	} else {
		$('#grouplabel').css("display", 'none')
		$('#groupdisplay').css("display", 'none')
        $('#chlabel').css("display", 'none')
		$('#chDisplay').css("display", 'none')
		$('#tierlabel').css("display", 'none')
		$('#tierDisplay').css("display", 'none')
	}
});

function delECGroup() {
	$('#end_customer_groupId').combogrid('clear');
}
var datass = [ {
	"id" : "0",
	"text" : "Pendding"
}, {
	"id" : "1",
	"text" : "Approved"
}, {
	"id" : "2",
	"text" : "Reject"
} ];
$('#state').combobox({
	data : datass,
	valueField : 'id',
	textField : 'text',
	panelHeight : 'auto',
});

// 上传附件
function upload() {
	initMaintAccount(false, '500', '300', 'File upload',
			'/file/fileAction!toImportFile.jspa', 100, 5);
}
// 上传附件返回信息
function returnUploadFile(fileStr, fileName) {
	var filePath = fileStr;
	var paths = fileName.split("\\");
	var fileName = paths[paths.length - 1];
	$('#old_file_name').val(fileName);
	$('#file_path').val(filePath);
}

var segment = [
	{"itemvalue":"001","itemName":"Industrial"},
	{"itemvalue":"002","itemName":"Automotive"},
	{"itemvalue":"003","itemName":"Consumer"},
	{"itemvalue":"004","itemName":"Renewable Energy"}]

var segmentgroup = [
	{"value":"g1_001","text":"Telecom","group":"Industrial"},
	{"value":"g1_002","text":"Server and Network","group":"Industrial"},
	{"value":"g2_001","text":"OBC and EV-Charging","group":"Automotive"},
	{"value":"g2_002","text":"Body / Traction Control","group":"Automotive"},
	{"value":"g3_001","text":"PC / Notebook","group":"Consumer"},
	{"value":"g3_002","text":"Tablet / E-Reader","group":"Consumer"}
]

// Tier
$('#tier').combobox({
	url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=568',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 173,
	onSelect : function(r) {
	}
});

// 终端客户类型
$('#customer_type').combobox({
	url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=554',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 173,
	onSelect : function(r) {
	}
});

var nhFirst = true;
$('#new_hierarchy').combogrid(
		{
			panelHeight : 280,
			panelWidth : 320,
			pagination : true,
			pageSize : 10,
			multiple : false,
			editable : false,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/endCustomerAction!getNewHierarchyList.jspa',
			queryParams : {
				newHierarchy : $('#new_hierarchy').val()
			},
			idField : 'itemValue',
			textField : 'itemName',
			columns : [ [ {
				title : 'Item Value',
				field : 'itemValue',
				width : 100
			}, {
				title : 'Item Name',
				field : 'itemName',
				width : 120
			} ] ],
			onLoadSuccess : function(data) {
				if (nhFirst) {
					$('#new_hierarchy').combogrid('grid').datagrid('getPager')
							.pagination("select", 1);
					nhFirst = false;
				}

			},
			toolbar : '#toolbarNewHierarchy',
		});
function searcherNewHierarchy(name1) {
	nhFirst = true;
	var queryParams = $('#new_hierarchy').combogrid("grid").datagrid('options').queryParams;
	queryParams.newHierarchy = encodeURIComponent(name1);
	$('#new_hierarchy').combogrid("grid").datagrid('reload');
}

// 国家
var cFirst = true;
$('#country').combogrid(
		{
			panelHeight : 280,
			panelWidth : 320,
			pagination : true,
			pageSize : 10,
			multiple : false,
			editable : false,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/country/countryAction!getCountryList.jspa',
			queryParams : {
				search : $('#country').val()
			},
			idField : 'country_code',
			textField : 'country_name',
			columns : [ [ {
				title : 'Country Code',
				field : 'country_code',
				width : 100
			}, {
				title : 'Country Name',
				field : 'country_name',
				width : 120
			} ] ],
			onSelect : function(index, record) {
				$('#province').combogrid('setValue', '');
				$('#province').combogrid("grid").datagrid('load', {
					country : record.country_code
				});
			},
			onLoadSuccess : function(data) {
				if (cFirst) {
					$('#country').combogrid('grid').datagrid('getPager')
							.pagination("select", 1);
					cFirst = false;
				}

			},
			toolbar : '#toolbarCountry',
		});
function searcherCountry(name1) {
	cFirst = true;
	var queryParams = $('#country').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#country').combogrid("grid").datagrid('reload');
}

function searcherProvince(name1) {
	pcFirst = true;
	var queryParams = $('#province').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#province').combogrid("grid").datagrid('reload');
}

// 省份/洲
setTimeout("loadprovice()", 1500);
function loadprovice() {
	var pcFirst = true;
	$('#province').combogrid(
			{
				panelHeight : 280,
				panelWidth : 320,
				pagination : true,
				pageSize : 10,
				multiple : false,
				editable : false,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/province/provinceAction!getProvinceList.jspa',
				queryParams : {
					search : $('#province').val()
				},
				idField : 'province_name',
				textField : 'province_name',
				columns : [ [ {
					field : 'country_code',
					title : 'Country',
					width : 100
				}, {
					field : 'province_name',
					title : 'State/Province',
					width : 120
				} ] ],
				onLoadSuccess : function(data) {
					if (pcFirst) {
						$('#province').combogrid('grid').datagrid('getPager')
								.pagination("select", 1);
						pcFirst = false;
					}
				},
				toolbar : '#toolbarProvince',

			});
}

// 终端客户集团
var groupFirst = true;
$('#end_customer_groupId').combogrid(
		{
			panelHeight : 265,
			panelWidth : 300,
			pagination : true,
			pageSize : 10,
			multiple : false,
			editable : false,
			method : 'post',
			singleSelect : true,
			url : appUrl
					+ '/ecgroup/groupInfoAction!getGroupList.jspa?states=(1)',
			queryParams : {
				ecGroup_id : $('#end_customer_groupId').val()
			},
			idField : 'ecGroup_id',
			textField : 'ecGroup_name',
			columns : [ [ {
				field : 'ecGroup_id',
				title : 'Group Code',
				width : 100,
				hidden : true
			}, {
				field : 'ecGroup_name',
				title : 'Group Name',
				width : 250
			} ] ],
			toolbar : '#toolbarECGroup',
			onSelect : function(index, record) {

			},
			onLoadSuccess : function(data) {
				if (groupFirst) {
					$('#end_customer_groupId').combogrid('grid').datagrid(
							'getPager').pagination("select", 1);
					groupFirst = false;
				}
			},

		});
function searcherECGroup(name1) {
	groupFirst = true;
	var queryParams = $('#end_customer_groupId').combogrid("grid").datagrid(
			'options').queryParams;
	queryParams.ecGroup_name = encodeURIComponent(name1);
	queryParams.ecGroup_id = encodeURIComponent("");
	$('#end_customer_groupId').combogrid("grid").datagrid('reload');
}

function applyECGroup() {
	initMaintAccount(false, '380', '240', 'Create Group',
			'/groupInfoAction!toCreateGroup.jspa');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if (failResult == undefined || failResult == 'undefined') {
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		if (failResult == undefined || failResult == 'undefined') {
			return;
		}
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}

function submit() {
	// 增加客户类型、segment、application为必填项 by sst 20210929
	if ($("#customer_type").combobox('getValue') == '') {
		$.messager.alert('Error', "CustomerType is mandatory field！", 'error');
		return;
	}
	if ($("#segment").combobox('getValue') == '') {
		$.messager.alert('Error', "Segment is mandatory field！", 'error');
		return;
	}
	if ($("#application").combobox('getValue') == '') {
		$.messager.alert('Error', "Application is mandatory field！", 'error');
		return;
	}
	if ($("#country").combobox('getValue') == '') {
		$.messager.alert('Error', "Country is mandatory field！", 'error');
		return;
	}
	if ($("#country").combobox('getValue') == 'US'
			|| $("#country").combobox('getValue') == 'CA') {
		if ($('#province').combobox('getValue') == '') {
			$.messager.alert('Error', "State/Province is mandatory field！",
					'error');
			return;
		}
		if ($('#zip').val() == '') {
			$.messager.alert('Error', "zip is mandatory field ！", 'error');
			return;
		}
	}
	if ($("#country").combobox('getValue') == 'CN') {
		if ($('#province').combobox('getValue') == '') {
			$.messager.alert('Error', "State/Province is mandatory field！",
					'error');
			return;
		}
	}
	if ($("#end_customer_name").val() == '') {
		$.messager.alert('Error', "Customer Name is mandatory field！", 'error');
		return;
	}
	if ($("#city").val() == '') {
		$.messager.alert('Error', "City is mandatory field！", 'error');
		return;
	}
	if ($("#customer_type").combobox('getValue') == '') {
		$.messager
				.alert('Error', "Customer Type  is mandatory field！", 'error');
		return;
	}
	// 只有瑞能内部人员才有orgCode（内部人员注册EC以下信息不必填），Disti没有
	if ($("#org_code").val() == '') {
		if ($("#address").val() == '') {
			$.messager.alert('Error', "Address  is mandatory field！", 'error');
			return;
		}
		if ($("#contact_name").val() == '') {
			$.messager.alert('Error', "Contact is mandatory field！", 'error');
			return;
		}
		if ($("#tel").val() == '') {
			$.messager
					.alert('Error', "Tel Number is mandatory field！", 'error');
			return;
		}
		if ($("#old_file_name").val() == '') {
			$.messager.alert('Error', "Please Upload attachments！", 'error');
			return;
		}
	}

	var url = undefined;
	if ($('#id').val() == '0' || $('#id').val() == '') {
		url = "/endCustomerAction!createEndCustomer.jspa";
	} else {
		url = "/endCustomerAction!updateEndCustomer.jspa";
	}

	$.messager.confirm('Confirm',
			'Please check the data is correct before submission', function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl + url;
					form.target = "hideFrame";
					form.submit();
				}
			});

}

// 创建窗口对象
function initMaintAccount(ffit, widte, height, title, url, l, t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
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
						fit : FFit,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');
}

function cancel() {
	window.parent.closeMaintWindow();
}
function closeMaintWindow() {
	$("#maintWindow").window('close');
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
	if (event.keyCode == 8) {
		// 如果是在textarea内不执行任何操作
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		// 如果是readOnly或者disable不执行任何操作
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
			event.returnValue = false;
	}
	return true;
};