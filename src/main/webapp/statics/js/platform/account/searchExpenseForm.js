$(document).ready(function() {
	loadGrid();
	for(var i=0; i<$("#size").val(); i++){
		addHandler(i);
	}
	$('#hideFrame1').bind('load', promgtMsg);
});

var i = $("#size").val()-1;
var outsideArr = new Array();
var orgIndex = $("#size").val()-1;
var expTypeIndex = $("#size").val()-1;
var budgetIndex = $("#size").val()-1;

function loadGrid(){
	
}
function addRow() {
	i++;
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#f4f4f4\" >";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#ffffff\" >";	
			
	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
		+ i
		+ "\" type=\"checkbox\" /></td>"
		+ "<td style=\"text-align: center\"><input size=\"10\" id=\"expTypeName_"
		+ i
		+ "\"  readOnly/> </input><input size=\"9\" id=\"expType_"
		+ i
		+ "\" type=\"hidden\" /><input size=\"10\" type=\"button\"  onclick=\"getExpType("+i+");\" value=\"选择\"/></td>"
		
		+ "<td style=\"text-align: center\"><input size=\"9\" id=\"orgName_"
		+ i
		+ "\" type=\"text\" readOnly /><input size=\"9\" id=\"orgId_"
		+ i
		+ "\" type=\"hidden\" /> </input><input size=\"10\" type=\"button\"  onclick=\"getOrg("+i+");\" value=\"选择\"/></td>"
		+ "<td style=\"text-align: center\"><input size=\"14\" id=\"budgetNumber_"
		+ i
		+ "\" type=\"text\"  readOnly/> <input size=\"10\" type=\"button\"  onclick=\"getBudgetNumber("+i+");\" value=\"选择\"/></td>"
		+ "<td style=\"text-align: center\"><input size=\"14\" id=\"budgetNumberBalance_"
		+ i
		+ "\" type=\"text\"  readOnly /></td>"
		+ "<td style=\"text-align: center\"><input size=\"14\" id=\"expDate_"
		+ i
		+ "\" type=\"text\" /></td>"
		+ "<td style=\"text-align: center\"><input size=\"14\" id=\"invoiceNum_"
		+ i
		+ "\" onblur=\"checkIsNumber("+i+", 'num');\" type=\"text\" /></td>"
		+ "<td style=\"text-align: center\"><input size=\"15\" id=\"invoiceAmount_" 
		+ i
		+ "\" onblur=\"checkIsNumber("+i+", 'amount');\" type=\"text\" /></td>"
		+ "<td style=\"text-align: center\"><input size=\"30\" id=\"memo_"
		+ i
		+ "\" type=\"text\"/></td>" + "</tr>";
		
		var htmlData = "";
		if (i % 2 == 1) {
			htmlData = htmlHead_1 + htmlTr;
		} else {
			htmlData = htmlHead_2 + htmlTr;
		}
		$('#myTab').append(htmlData);
		addHandler();	
		i++;
}

function removeRow() {
	for(var k = 0; k <= i;k++){
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}
	}
	
	$('#item_all').attr('checked', false);
	countTotalMoney();
	countAuditMoney();
}

function checkAll() {
	for ( var k = 0; k <= i; k++) {
		if(!checkInOutsideArr(k)){
			$('#item_' + k).attr('checked',
				($('#item_all').attr('checked') == 'checked'));
		}
	}
}

/**
 * 添加行元素事件
 */
function addHandler(i){
	$("#costDate_"+i).datebox({
			required: true
	});

	$("#invoiceNum_"+i).validatebox({   
    	required: true 
	});
	
	$("#invoiceAmount_"+i).validatebox({   
    	required: true
	});
}
/**
 * 选择费用类型
 * @param i
 */
function getExpType(i){
	expTypeIndex = i;
	initMaintEvent('选择费用类型', '/account/accountAction!selectCostType.jspa', 400, 460);
}
/***
 * 选择组织
 * @param i
 */
function getOrg(i) {
	orgIndex = i;
	initMaintEvent('选择费用类型', '/orgAction!orgTreePage.jspa', 400, 460);
}
/***
 * 改变费用类型或者成本中心时
 */
function changeBudget(i){
	$("#budgetNumber_"+i).val("");
	$("#budgetNumberBalance_"+i).val("");
}

/**
 * 获取费用编号
 * @param i
 */
function getBudgetNumber(i){
	budgetIndex = i;
	if($("#orgId_"+i).val() == "" || $("#expType_"+i).val() == ""){
		$.messager.alert('Tips', '请先选择费用类型和成本中心!', 'warning');
		return;
	}
	var orgId = $("#orgId_"+i).val();
	var expType = $("#expType_"+i).val();
	initMaintEvent('费用编号', '/account/accountAction!bNumberSearchPrepare.jspa?orgId=' + orgId+ '&expType=' + expType, 800, 480);
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame1");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

function createProEventReader() {
	initMaintEvent('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

function saveUser(x) {
	$("#traUserId").val(x[0]);
	$("#traUserName").val(x[1]);
	closeMaintEvent();
}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintEvent('选择组织', '/orgAction!orgTreePage.jspa', 400, 360);
}


/**
 * 组织树的返回值接受
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	$("#orgId_"+orgIndex).val(selectedId);
	$("#orgName_"+orgIndex).val(selectedName);
	changeBudget(orgIndex);
}
/**
 * 费用类型的返回值
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnCostTypeValue(selectedId,selectedName){
	$("#expType_"+expTypeIndex).val(selectedId);
	$("#expTypeName_"+expTypeIndex).val(selectedName);
	changeBudget(expTypeIndex);
	
}
/**
 * 返回预算编号
 * @param selectedId
 * @param selectedName
 * @param month
 */
function returnBudgetValue(selectedId,selectedName,month){
	$("#budgetNumber_"+budgetIndex).val(selectedId);
	$("#budgetNumberBalance_"+budgetIndex).val(selectedName);

}
/**
 * 费用类型页面关闭
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function closeMaintEvent(){
	closeMaintWindow();
}

function closeOrgTree() {
	closeMaintEvent();
}

function initMaintEvent(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintDiv")
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
						maximizable : false,
						collapsible : false,
						draggable : false
					});

	$win.window('open');
}

function closeMaintEvent() {
	$("#maintDiv").window('close');
}

function checkBooleanArr(arr){
	var flag = true;
	for(var t=0;t<arr.length;t++){
		if(!arr[t]){
			flag = false;
			break;
		}
	}
	return flag;
}

function checkInOutsideArr(index){
	var flag = false;
	for(var a=0;a<outsideArr.length;a++){
		if(outsideArr[a]==index){
			flag = true;
			break;
		}
	}
	return flag;
}

function submit() {
	if(curStaId=="start"){
		var m = $('#costCenter').combogrid('isValid');
		var dateArr = new Array();
		var numArr = new Array();
		var amountArr = new Array();	
		for(var r=0;r<=i;r++){
			if(!checkInOutsideArr(r)){
				dateArr.push($("#costDate_"+r).datebox('isValid'));
				numArr.push($("#invoiceNum_"+r).validatebox('isValid'));
				amountArr.push($("#invoiceAmount_"+r).validatebox('isValid'));
			}
		}
		if (!(m && checkBooleanArr(dateArr) && checkBooleanArr(numArr) && checkBooleanArr(amountArr))) {
			return;
		}
	}
	
	var detailStr = "";
	detailStr += "[";
	var x = 0;
	for(var j=0; j<=i; j++){
		if(!checkInOutsideArr(j)){
			x++;
			detailStr += "{"
				+ "\"detail_id\" : \"" +$("#detailId_"+j).val()+ "\","
				+ "\"plan_id\" : \"" +$("#planId").val()+ "\","
				+ "\"cost_type\" : \"" +$("#expType_"+j).combobox("getValue")+ "\","
				+ "\"cost_type_content\" : \"" +$("#expType_"+j).combobox("getText")+ "\",";
			if(curStaId=="start"){
				detailStr += "\"cost_date\" : \"" +$("#costDate_"+j).datebox("getValue")+ "\",";
			}else{
				detailStr += "\"cost_date\" : \"" +$("#costDate_"+j).val()+ "\",";
			}
			detailStr += "\"cost_purpose\" : \"" +$("#costPurpose_"+j).val()+ "\","
				+ "\"invoice_num\" : \"" +$("#invoiceNum_"+j).val()+ "\","
				+ "\"invoice_amount\" : \"" +$("#invoiceAmount_"+j).val()+ "\","
				+ "\"audit_money\" : \"" +$("#auditMoney_"+j).val()+ "\","
				+ "\"cost_memo\" : \"" +$("#costMemo_"+j).val()+ "\""
				+ "},";
		}
	}
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	$("#costCenterText").val($('#costCenter').combobox("getText"));
	var form = window.document.forms[0];
		form.action = appUrl + "/account/accountAction!updateExpenseForm.jspa";
		form.target = "hideFrame1";
		form.submit();
}

function reset() {
	window.parent.closeMaintEvent();
}

function checkIsNumber(k, type){
	var regNum = /^\d+$/;
	var regAmount = /^\d+(.\d+)?$/;
	if("num" == type){
		if(!regNum.test($("#invoiceNum_"+k).val())){
			$("#invoiceNum_"+k).val(0);
		}
	}
	if("amount" == type){
		if(!regAmount.test($("#invoiceAmount_"+k).val())){
			$("#invoiceAmount_"+k).val(0);
		}
		countTotalMoney();
	}
	if("auditMoney" == type){
		if(!regAmount.test($("#auditMoney_"+k).val())){
			$("#auditMoney_"+k).val('');
		}
		countAuditMoney();
	}
}

function countTotalMoney(){
	var total = 0;
	for(var r=0;r<=i;r++){
		if(!checkInOutsideArr(r)){
			var value = $("#invoiceAmount_"+r).val();
			if(value.length>0){
				total += Number(value);
			}
		}	
	}
	$("#totalMoney").val(RoundNumber(total, 2));
}

function countAuditMoney(){
	var total = 0;
	var check = false;
	for(var r=0;r<=i;r++){
		if(!checkInOutsideArr(r)){
			var value = $("#auditMoney_"+r).val();
			if(value != '') {
				check = true;	
			}
			if(value.length>0){
				total += Number(value);
			}
		}	
	}
	if(check) {
		$("#auditMoney").val(RoundNumber(total, 2));
	} else {
		$("#auditMoney").val('');
	}
	
}

function RoundNumber(num, pos)  {  
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);  
} 
