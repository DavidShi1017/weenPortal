$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	if(!($('#payCash').validatebox('isValid') && $('#chouQian').validatebox('isValid'))) {
		return;
	}
	var x = 0;
	var detailStr = "";
	var error = "";
	detailStr += "[";
	for(var k=0;k<$("#size").val();k++){
		if($("#auditMoney_"+k).val() == ""){
			error = "第"+Number(k+1)+"行核销金额必须输入！";
			break;
		}
		x++;
	    detailStr += "{"
				+ "\"detail_id\" : \""+$("#detailId_"+k).val()+ "\","
				+ "\"audit_money\" : \""+$("#auditMoney_"+k).val()+ "\","
				+ "},";
	}
	if(error != ""){
		$.messager.alert('Tips', error, 'warning');
		return;
	}
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	
	var form = window.document.forms[0];
	form.action = appUrl + "/account/accountAction!modifyAuditExpenseForm.jspa";
	form.target = "hideFrame";
	form.submit();
	
}

/**
 * 校验输入数字
 * 
 * @param {}
 *            k
 * @param {}
 *            type
 */
function checkIsNumber(k, type){
	var regAmount = /^\d+(.\d+)?$/;
	if("auditMoney" == type){
		if(!regAmount.test($("#auditMoney_"+k).val())){
			$("#auditMoney_"+k).val(0);
		}
		countAuditMoney();
	}
}

function countAuditMoney(){
	var total = 0;
	var check = false;
	for(var r=0;r<$("#size").val();r++){
	/*	if(!checkInOutsideArr(r)){*/
			var value = $("#auditMoney_"+r).val();
			if(value != '') {
				check = true;	
				total += Number(value);
			}
			/*if(value.length>0){*/
				
			/*}*/
		/*}	*/
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
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}