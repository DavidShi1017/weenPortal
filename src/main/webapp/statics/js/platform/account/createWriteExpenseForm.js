$(document).ready(function() {
	loadGrid();
	/*for(var i=0; i<$("#size").val(); i++){
		addHandler(i);
	}*/
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid(){
	countAuditMoney();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.search();
			window.parent.closeMainFrame();
		});
	}
}
/***
 * 成功后跳转到待办页面
 */
function successRe(){
	var form = document.getElementById("writeForm");
	form.action = appUrl + "/account/accountAction!searchTraReimburPrepare.jspa";
	form.target = "_self";
	form.submit();
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
	for(var a=0;a<$("#size").val();a++){
		if(outsideArr[a]==index){
			flag = true;
			break;
		}
	}
	return flag;
}
/***
 * 提交核销流程
 */
function submit(){
	var n = $("#title").validatebox('isValid');
	if(!n){
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
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/account/accountAction!selectNexUser.jspa?time="+new Date(),
		data : {
			tmp  : $("#tmp").val(),
			type : "update",
			auditMoney : $("#auditMoney").val(),
			flag : $("#flag").val()
		},
		success : function(userUtil) {
			$.messager.progress('close');
			if(userUtil.processInstanceId == "-1"){
				$.messager.alert('Tips', "没有维护下个处理人！请联系管理员", 'error');
				return;
			}
			if(userUtil != null && userUtil.processInstanceId != '-2'&& userUtil.processInstanceId != undefined){
				var nextUser1 = "";
				var total = 0;
				$.each(userUtil.result, function(i, v) {
					total = i+1;
					nextUser1 = v.userId;
				});
				if(total == 1){
					$("#nextUserId").val(nextUser1);
					var form = window.document.forms[0];
					form.action = appUrl+"/account/accountAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId
					+"&type="+"update";
					form.target = "hideFrame";
					form.submit();
				}else if (total == 0) {
					$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",
					'error');
					return;
				}else{
						var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
							+"<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
							+"<tr><td class='head' noWrap>处理人</td>"
							+"<td><select id='nextUserId1' name='nextUserId1'>";
						$.each(userUtil.result, function(i, v) {
							positionHtml += "<option value='"+ v.userId +"'>"+v.userName+"----"+v.stationName+"</option>";
						});
						positionHtml +="</select></td></tr></table></div>";
						if ($('#nextUserDialog').length<1) {
							$('<div/>', {
							id: 'nextUserDialog',
							title: '选择下个处理人',
							html: "<div id='nextUser'>" 
								+positionHtml+
								 "</div>" +
						          "</div>"
							}).appendTo('body');
						} else {
							$('#nextUser').html(positionHtml);
						}
						$('#nextUserDialog').dialog({
							modal: true,
							resizable: false,
							dragable: false,
							closable:false,
							open: function() {
								$('#nextUserDialog').css('padding', '0.4em');
								$('#nextUserDialog .ui-accordion-content').css('padding', '0.4em').height($('#nextUserDialog').height() - 75);
							},
							buttons:[{ 
								text:'确定',
								handler:function(){ 
									if($("#nextUserId1").val() == "" || $("#nextUserId1").val() == null){
										$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
									    return;
									}
									$("#nextUserId").val($("#nextUserId1").val());
									var form = window.document.forms[0];
									form.action = appUrl+"/account/accountAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId
									+"&type="+"update";
									form.target = "hideFrame";
									form.submit();
								}},{
								text:'取消',
								handler:function(){ 
									$('#nextUserDialog').dialog('close');
								} 
							}],
							width: document.documentElement.clientWidth * 0.50,
					 		height: document.documentElement.clientHeight * 0.40
						});
						}
					}else {
						$.messager.alert('Tips', "流程出错！请联系管理员",
						'error');
					}},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
			});
}

function reset() {
	window.parent.closeMaintEvent();
}

function checkIsNumber(k, type){
	//var regNum = /^\d+$/;
	var regAmount = /^\d+(.\d+)?$/;
	/*if("num" == type){
		if(!regNum.test($("#invoiceNum_"+k).val())){
			$("#invoiceNum_"+k).val(0);
		}
	}
	if("amount" == type){
		if(!regAmount.test($("#invoiceAmount_"+k).val())){
			$("#invoiceAmount_"+k).val(0);
		}
		countTotalMoney();
	}*/
	if("auditMoney" == type){
		if(!regAmount.test($("#auditMoney_"+k).val())){
			$("#auditMoney_"+k).val('');
		}
		countAuditMoney();
	}
}

function countTotalMoney(){
	var total = 0;
	for(var r=0;r<=$("#size").val();r++){
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
