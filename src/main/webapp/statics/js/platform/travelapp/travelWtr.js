$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid(){
	$('#payee').combogrid(
			{
				panelWidth : 450,
				panelHight : 500,
				idField : 'payee',
				textField : 'payee',
				pagination : true,// 是否分页
				rownumbers : true,// 序号
				collapsible : false,// 是否可折叠的
				fit : true,// 自动大小
				method : 'post',
				editable : false,
				multiple : false,
				url : appUrl
						+ '/account/accountAction!getPayeeJsonList.jspa',
				columns : [ [ {
					field : 'payee',
					title : '收款单位',
					width : 400,
					align : 'center'
				} ] ],
				onLoadSuccess : function() {
					if ($('#num').val() > 0) {
						$('#payee').combogrid('setText',
								$('#defaultPayee').val());
						$('#payAccount').combobox({
											url : appUrl
													+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
													+ encodeURIComponent($(
															'#defaultPayee')
															.val()),
											valueField : 'id',
											textField : 'payAccount',
											editable : false,
											width : 160,
											onLoadSuccess : function() {
												$('#payAccount').combobox('setValue',$('#defaultPayeeId').val());
											},
											onSelect : function(record) {
												var payB=$('#payAccount').combobox('getValue');
												$('#payBank').combobox('setValue',payB);
											}
										});
						$('#payBank').combobox({
							url : appUrl
									+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
									+ encodeURIComponent($(
											'#defaultPayee')
											.val()),
							valueField : 'id',
							textField : 'payBank',
							editable : false,
							width : 160,
							onLoadSuccess : function() {
								$('#payBank').combobox('setValue',$('#defaultPayeeId').val());
							},
							onSelect : function(record) {
								var payB=$('#payBank').combobox('getValue');
								$('#payAccount').combobox('setValue',payB);
							}
						});
					}
				},
				onSelect : function(record) {
					var g = $('#payee').combogrid('grid'); // get				// object
					var r = g.datagrid('getSelected'); // get the
														// selected row
					$('#payAccount').combobox(
									{
										url : appUrl
												+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
												+ encodeURIComponent(r.payee),
										valueField : 'id',
										textField : 'payAccount',
										editable : false,
										width : 160,
										onLoadSuccess : function(row) {
											 var val = $(this).combobox("getData");
											 for (var item in val[0]) {
												 if (item == "id") {
													$('#payAccount').combobox('setValue',val[0].id);
												 }
											 }
										},
										onSelect : function(record) {
											var payB=$('#payAccount').combobox('getValue');
											$('#payBank').combobox('setValue',payB);
										}
									});
					$('#payBank').combobox(
							{
								url : appUrl
										+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
										+ encodeURIComponent(r.payee),
								valueField : 'id',
								textField : 'payBank',
								editable : false,
								width : 160,
								onLoadSuccess : function(row) {
									 var val = $(this).combobox("getData");
									 for (var item in val[0]) {
										 if (item == "id") {
											 $('#payBank').combobox('setValue', val[0].id);
										 }
									 }
								},
								onSelect : function(record) {
									var payB=$('#payBank').combobox('getValue');
									$('#payAccount').combobox('setValue',payB);
								}
					});
					
					}
				});
	
	$('#payee').combobox('setText', payee);
	$('#payAccount').combobox('setText', payAccount);
	$('#payBank').combobox('setText', payBank);
	$('input:radio[name="travelTotal.payType"]')[payType-1].checked = true;
	
	
	for(var k=0;k<$("#size").val();k++){
		$("#travelStartDateSt_" + k).datebox({
			required : true,
			editable : false,
			onSelect:function(v){
				var r=$(this).attr('id');
				var ids=r.split('_');
				intravelNum(ids[1]);
			}
		});	
		$("#travelEndDateSt_" + k).datebox({
			required : true,
			editable : false,
			onSelect:function(row){
				var r=$(this).attr('id');
				var ids=r.split('_');
				intravelNum(ids[1]);
			}
		});	
	}
	
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.progress('close');
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			$.messager.progress('close');
			window.parent.closeMainFrame();
			window.parent.search();
		});
	}
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
	if($("#title").val()==''||$("#title").val()==null){
		$.messager.alert('Tips', "请填写事务标题", 'warning');
		return;
	}	
	var auditMoney=$('#auditMoney').val();
	if(totalMoney-auditMoney<0){
		$.messager.alert('Tips', "核销金额大于申请金额，无法提交", 'warning');
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
			+ "\"travelStartDateSt\" : \"" + $("#travelStartDateSt_" + k).datebox("getValue")+ "\"," 
			+ "\"travelEndDateSt\" : \""+ $("#travelEndDateSt_" + k).datebox("getValue") + "\","
			+ "\"travelNum\" : \"" + $("#travelNum_" + k).val() + "\","
			+ "\"peerPerson\" : \"" + $("#peerPerson_" + k).val() + "\","
			+ "\"travleDtId\" : \"" + $("#travleDtId_" + k).val()+ "\","
			+ "\"travelPlace\" : \"" + $("#travelPlace_" + k).val()+ "\","
			 + "\"invoiceAmount\" : \""+ $("#invoiceAmount_" + k).val() + "\","
			+ "\"auditMoney\" : \""+$("#auditMoney_"+k).val()+ "\","
			+ "\"meno\" : \"" + $("#costMemo_" + k).val()
			+ "\"" + "},";
	}
	if(error != ""){
		$.messager.alert('Tips', error, 'warning');
		return;
	}
	
	$("#pay_ee").val($("#payee").combogrid("getText"));
	$("#pay_account").val($("#payAccount").combobox("getText"));
	$("#pay_bank").val($("#payBank").combobox("getText"));
	
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	
	

	$.messager.confirm('Confirm', '确定提交吗', function(r) {
		if(r){
			$.messager.progress();
			$.post( appUrl + "/account/accountAction!getStartUserRole.jspa?time="+ new Date(),
				function(data) {
				$.messager.progress('close');
					if(data.total==1){
						$.messager.progress();
						startTransaction(data.cmsTbDictUserList[0].itemValue);
					}else{
						userRoleHtml(data);
						$('#userRoleDialog').dialog(
								{
									title : '请选择提交人职务',
									modal : true,
									resizable : false,
									dragable : false,
									closable : false,
									width : 300,
									height : 160,
									content : nextRoles,
									buttons : [
											{
												text : '确定',
												handler : function() {
													$('#userRoleDialog').dialog('close');
													$.messager.progress();
													startTransaction($('#selectUserRole').val());
												}
											}, {
												text : '取消',
												handler : function() {
													$('#userRoleDialog').dialog('close');
												}
											} ]
								});
					}
				}
			);
		}
	});
}

function reset() {
	window.parent.closeMaintEvent();
}

function checkIsNumber(k, type){
	var regAmount = /^\d+(.\d+)?$/;
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
}

function countAuditMoney(){
	var total = 0;
	var check = false;
	for(var r=0;r<$("#size").val();r++){
			var value = $("#auditMoney_"+r).val();
			if(value != '') {
				check = true;	
				total += Number(value);
			}
			if(Number($("#auditMoney_"+r).val())>Number($("#invoiceAmount_"+r).val())){
				$.messager.alert('Tips', "明细销金额大于明细申请金额", 'warning');
				$("#auditMoney_"+r).val('0');
				total=0;
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

function  startTransaction(role){
	$.ajax({
		type : "post",
		url : appUrl + "/travelApp/travelAppAction!selectNexUser.jspa?time="+new Date(),
		data : {
			type : "update",
			auditMoney : $("#auditMoney").val(),
			flag : $("#flag").val(),
			role:role
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
					$.messager.progress();
					$("#nextUserId").val(nextUser1);
					var form = window.document.forms[0];
					form.action = appUrl+"/travelApp/travelAppAction!startWorkflowFix.jspa?eventId="+userUtil.processInstanceId+"&type="+"update";
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
									$.messager.progress();
									if($("#nextUserId1").val() == "" || $("#nextUserId1").val() == null){
										$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
									    return;
									}
									$("#nextUserId").val($("#nextUserId1").val());
									var form = window.document.forms[0];
									form.action = appUrl+"/travelApp/travelAppAction!startWorkflowFix.jspa?eventId="+userUtil.processInstanceId
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

function userRoleHtml(obj) {
	nextRoles = "<table border='0' cellpadding='0' cellspacing='1'>"
			+ "<tr><td class='head' noWrap>提交人职务</td>"
			+ "<td><select id='selectUserRole'>";
	$.each(obj.cmsTbDictUserList, function(i, v) {
		nextRoles += "<option value='" + v.itemValue + "'>" + v.itemName + "</option>";
	});
	nextRoles += "</select></td></tr></table>";
}

function intravelNum(k)
{
    var aDate, oDate1, oDate2, iDays;
    var sDate1=$("#travelStartDateSt_" + k).datebox("getValue");   
    var sDate2=$("#travelEndDateSt_" + k).datebox("getValue"); 
    if(sDate1==''||sDate2==''){
    	return;
    }
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);  
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
    var i=(oDate2 - oDate1) / 1000 / 60 / 60 /24;
    if(i<0)
    {
        i-=1;
    }
    else
    {
        i+=1;
    }
    iDays = i;  
    if(iDays>0){
    	$("#travelNum_" + k).val(iDays);
    }else{
		$.messager.alert('Tips', "出差开始日期大于出差结束日期，请修改", 'warning');
		$("#travelEndDateSt_" + k).datebox("setValue",'');
		$("#travelStartDateSt_" + k).datebox("setValue",'');
		return;
    }
}
