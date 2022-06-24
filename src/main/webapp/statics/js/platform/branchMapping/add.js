$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);	
});
var datass = [{ "id":"0", "text":"New" },{  "id":"1", "text":"Approved" },{  "id":"2", "text":"Reject" }];
$('#state').combobox({    
	data:datass,    
	    valueField:'id',    
	    textField:'text',
	    panelHeight : 'auto',
	}); 
if($('#state').combobox('getValue')==""){
	$('#state').combobox('setValue',0);
}


function selectOrgTree(){
	initMaintWindowForOrg('Org Tree', '/orgAction!orgTreePage.jspa');
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}

function returnValue(selectedId, selectedName){
	document.getElementById('org_id').value =selectedId;
	document.getElementById('org_name').value= selectedName;
}
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 250;
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
						left: ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}


//����
var mFirst = true;
$('#material_name').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/product/productAction!getProductList.jspa',
	queryParams:{
		material_name : $('#material_name').val(),
	},
	idField : 'material_name',
	textField : 'material_name',
	columns : [[{
				field : 'material_id',
				title : '12NC',
				width : 100
			}, {
				field : 'material_name',
				title : 'BookPart',
				width : 210
			}]],
			toolbar : '#toolbarProduct',
			onSelect : function(index, record) {
				$('#material_id').val(record.material_id);
//				$('#material_type').val(record.material_type);
//				$('#basic_type').val(record.material_groupId);
//				$('#material_desc').val(record.material_name);
			},
			onLoadSuccess: function(data) {
				if(mFirst){					
					$('#material_name').combogrid('grid').datagrid('getPager').pagination("select", 1);
					mFirst=false;
				}
			}
		});
		function searcherProduct(name1) {
			mFirst = true;
			var queryParams = $('#material_name').combogrid("grid").datagrid('options').queryParams;
			queryParams.material_name = encodeURIComponent(name1);
			$('#material_name').combogrid("grid").datagrid('reload');
		} 
		
//��֯
$('#office_id').combobox({
	url : appUrl + '/priceRuleAction!getOrgLists.jspa?state=1',
	valueField : 'sapOrgId',
	textField : 'orgName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});
//����
$('#currency_code').combobox({
	url : appUrl
			+ '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
	valueField : 'itemValue',
	textField : 'itemName',
	multiple : false,
	editable : false,
	panelHeight : 120,
	width : 170,
	onSelect : function(r){
 	} 
});
//�۸�����
$('#priceType').combobox({
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
});
//�����̿ͻ�
$('#customer_code').combogrid({
	panelHeight : 280,
	panelWidth : 320,
	pagination : true,
	pageSize:10,
	multiple : false,
	editable : false,
	method : 'post',
	singleSelect : true,
	url : appUrl + '/customer/customerAction!getCustomerList.jspa',
	idField : 'customer_code',
	textField : 'customer_name',
	columns : [[{
				field : 'customer_code',
				title : 'CustomerCode',
				width : 100,
				formatter : function(value, row, rec) {
					var str=(row.customer_code).replace( /^0*/, '');
					return str;
				}
			}, {
				field : 'customer_name',
				title : 'CustomerName',
				width : 200
			}]],
//			onSelect : function(index, record) {
//				$('#customer_name').val(record.customer_name);
//			}
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}



function submit() {		
	
	
	var url=undefined;
	if($('#id').val()=='0'||$('#id').val()==''){
		 url="/countryAction!createBranchMapping.jspa";
	}else{
		url="/countryAction!updateBranchMapping.jspa";
	}
	
	  $.messager.confirm('Confirm', 'Please check the information to determine the submission!', function(r) {
		if(r){						
			var form = window.document.forms[0];
			form.action = appUrl + url;
			form.target = "hideFrame";
			form.submit();			  						
		}
	});
	
 
}

//�������ڶ���
function initMaintAccount(ffit,widte,height,title, url,l,t) {
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
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');
}

function cancel(){
	window.parent.closeMaintWindow();
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}	  
$.extend($.fn.validatebox.defaults.rules, {  
    /*�����ĳ���ֶ����*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'�ֶβ�ƥ��'
    }
});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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