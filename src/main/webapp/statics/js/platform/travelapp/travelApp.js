$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

var i = 1;
var expTypeIndex = 1;
var outsideArr = new Array();
var budgetIndex = 1;
var itemid;
function loadGrid() {
	
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

	

	addRow();
}

function searcher_payee(val) {
	val = encodeURIComponent(val);
	$('#payee').combogrid(
					{
						url : appUrl
								+ '/account/accountAction!getPayeeJsonList.jspa?searchStr='
								+ val
					});
	$('#payee').combogrid("grid").datagrid('reload');
}

function searcher_reference(val) {
	val = encodeURIComponent(val);
	$('#reference')
			.combogrid(
					{
						url : appUrl
								+ '/account/accountAction!getHisEventJsonList.jspa?searchStr='
								+ val
					});
	$('#reference').combogrid("grid").datagrid('reload');
}

/**
 * 添加行
 */
function addRow() {
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";

	 
//		"<td style=\"text-align: center\"><input id=\"item_"
//			+ i
//			+ "\" type=\"checkbox\" /></td>"
			

var htmlTr ="<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"travelStartDate_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"travelEndDate_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"5\" class=\"easyui-validatebox\" id=\"travelNum_"
			+ i
			+ "\" type=\"text\" readonly/></td>"
			+ "<td style=\"text-align: center\"><input size=\"8\" id=\"peerPerson_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"regions_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"regionc_"
			+ i
			+ "\" type=\"text\" readonly /></td>"
			+ "<td style=\"text-align: center\"><input size=\"11\" id=\"regionb_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" class=\"easyui-validatebox\" id=\"invoiceAmount_"
			+ i + "\" onblur=\"checkIsNumber(" + i
			+ ", 'amount');\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"35\" class=\"easyui-validatebox\" validType=\"length[0,75]\" id=\"memo_"
			+ i
			+ "\" type=\"text\" /></td>"
			+"</tr>";
	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler();
	getRegionS(i);
	i++;
}

function getRegionS(j) {
	$('#regions_'+j).combobox({
		editable : false,
		multiple : false,
		url : appUrl + '/account/accountAction!searchRegion.jspa?level=1&pid=0',
		textField : 'text',
		valueField : 'id',
		onLoadSuccess : function() {
			$('#regions_' + j).combobox("setText", '请选择');
		},
		onSelect : function(re) {
			getRegionC(re.id,j);
		}
	});
};

function getRegionC(value,j) {
	$('#regionc_'+j).combobox({
		editable : false,
		multiple : false,
		url : appUrl + '/account/accountAction!searchRegion.jspa?level=2&pid='+value,
		textField : 'text',
		valueField : 'id',
		onLoadSuccess : function() {
			$('#regionc_' + j).combobox("setText", '请选择');
		}
	});
};

/**
 * 添加行元素事件
 */
function addHandler() {
	$("#travelStartDate_" + i).datebox({
		required : true,
		editable : false,
		onSelect:function(v){
			var r=$(this).attr('id');
			var ids=r.split('_');
			intravelNum(ids[1]);
		}
	});	
	$("#travelEndDate_" + i).datebox({
		required : true,
		editable : false,
		onSelect:function(v){
			var r=$(this).attr('id');
			var ids=r.split('_');
			intravelNum(ids[1]);
		}
	});
	$("#invoiceAmount_" + i).validatebox({
		required : true
	});
	
	$("#travelNum_" + i).validatebox({
		required : true
	});
	$("#memo_" + i).validatebox({
	});
}
/**
 * 选择费用类型
 * 
 * @param i
 */
function getExpType(i) {
	expTypeIndex = i;
	initMaintWindow('选择费用类型', '/account/accountAction!selectCostType.jspa?type=03',
			400, 460,50,50);
}
/*******************************************************************************
 * 选择组织
 * 
 * @param i
 */
function getOrg() {
	initMaintWindow('选择成本中心', '/orgAction!orgTreePage.jspa?orgIdIn='
			+ $("#orgIdIn").val(), 400, 460,100,50);
}
/*******************************************************************************
 * 改变费用类型或者成本中心时
 */
function changeBudget() {
	$("#budgetNumber" ).val("");
	$("#budgetNumberBalance").val("");
}
/**
 * 删除行
 */
function removeRow() {
	for ( var k = 1; k < i; k++) {
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}
	}
	$('#item_all').attr('checked', false);
	countTotalMoney();
}

/**
 * 选中全部
 * 
 * @param {}
 *            module
 */
function checkAll() {
	for ( var k = 1; k < i; k++) {
		if (!checkInOutsideArr(k)) {
			$('#item_' + k).attr('checked',
					($('#item_all').attr('checked') == 'checked'));
		}
	}
}

function payAccountReload() {
	$.ajax({
				type : 'post',
				url : appUrl
						+ '/account/accountAction!getDefaultPayAccount.jspa?payee='
						+ encodeURIComponent($('#payee').combogrid('getText')),
				success : function(id) {
					$('#payAccount')
							.combobox(
									{
										url : appUrl
												+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
												+ encodeURIComponent($('#payee')
														.combogrid('getText')),
										valueField : 'id',
										textField : 'payAccount',
										editable : false,
										onLoadSuccess : function() {
											$('#payAccount').combobox(
													'setValue', id);
										}
									});
				}
			});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

function createProEventReader() {
	initMaintWindow('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindow('选择组织', '/orgAction!orgTreePage.jspa?orgIdIn='
			+ $("#orgIdIn").val(), 400, 460,50,50);
}
/**
 * 获取费用编号
 * 
 * @param i
 */
function getBudgetNumber() {
	if ($("#costOrgId").val() == "") {
		$.messager.alert('Tips', '请先选择成本中心!', 'warning');
		return;
	}
	var orgId = $("#costOrgId").val();
	var expType = $("#costType").val();
	initMaintWindow('费用编号',
			'/account/accountAction!bNumberSearchPrepare.jspa?orgId=' + orgId
					+ '&expType=' + expType, 800, 380,150,50);
}

function addNewAccount() {
	var payee = $('#payee').combobox('getText');
	if (payee == '') {
		$.messager.alert('Tips', '请先选择收款人!', 'warning');
		return;
	}
	initMaintWindow('添加新账号',
			'/account/accountAction!toAddNewAccount.jspa?payee='
					+ encodeURIComponent(payee), 500, 400,200,100);
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
	$("#costOrgId" ).val(selectedId);
	$("#costOrgName").val(selectedName);
	changeBudget();
}
/**
 * 费用类型的返回值
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnCostTypeValue(selectedId, selectedName) {
	$("#costType").val(selectedId);
	$("#costTypeName").val(selectedName);
	changeBudget();
}
/**
 * 返回预算编号
 * 
 * @param selectedId
 * @param selectedName
 * @param month
 */
function returnBudgetValue(selectedId, selectedName, bndetailId) {
	$("#budgetNumber" ).val(selectedId);
	$("#budgetNumberBalance").val(selectedName);
	$("#bndetailId").val(bndetailId);

}

/**
 * 费用类型页面关闭
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function closeMaintEvent() {
	closeMaintWindow();
}

/**
 * 费用组织页面关闭
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function closeOrgTree() {
	closeMaintWindow();
}

// 创建窗口对象
function initMaintWindow(title, url, WWidth, WHeight,l,t) {
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
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');
}

function closeMaintWindow() {
	$("#maintDiv").window('close');
}

/**
 * 
 * @param {}
 *            k
 * @param {}
 *            type
 */
function checkIsNumber(k, type) {
	var regAmount = /^\d+(.\d+)?$/;
	if ("amount" == type) {
		if (!regAmount.test($("#invoiceAmount_" + k).val())) {
			$("#invoiceAmount_" + k).val(0);
		}
		if ($("#invoiceAmount_" + k).val() * 1 > $("#budgetNumberBalance_" + k)
				.val() * 1) {
			$("#invoiceAmount_" + k).val(0);
			$.messager.alert('Tips', '发票金额不能超过预算编号余额!', 'warning');
			return;
		}
		countTotalMoney();
	}
}

function countTotalMoney() {
	var total = 0;
	for ( var r = 1; r < i; r++) {
		if (!checkInOutsideArr(r)) {
			var value = $("#invoiceAmount_" + r).val();
			if (value.length > 0) {
				total += Number(value);
			}
		}
	}
	$("#totalMoney").val(RoundNumber(total, 2));
}

// 数字 指定位数后 四舍五入
function RoundNumber(num, pos) {
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);
}

/**
 * 校验Boolean类型数组是否全部为真
 * 
 * @param {}
 *            arr
 * @return {}
 */
function checkBooleanArr(arr) {
	var flag = true;
	for ( var t = 0; t < arr.length; t++) {
		if (!arr[t]) {
			flag = false;
			break;
		}
	}
	return flag;
}

/**
 * 检查index是否在在删除行序列之内
 * 
 * @param {}
 *            index
 */
function checkInOutsideArr(index) {
	var flag = false;
	for ( var a = 0; a < outsideArr.length; a++) {
		if (outsideArr[a] == index) {
			flag = true;
			break;
		}
	}
	return flag;
}
function submit() {
	
	var memo=$('#memo').val();
	if(memo.length>100){
		$.messager.alert('Tips', '出差事由字数不能超过100个字', 'warning');
		return;
	}
	
	if($("#title").val()==''){
		$.messager.alert('Tips', "请填写事务标题",'error');
		return;
	}
	
	if($("#payee").combobox('getValue')==''){
		$.messager.alert('Tips', "请选择收款人",'error');
		return;
	}

	if(Number($("#totalMoney").val())<=0){
		$.messager.alert('Tips', "申请金额为0，请确认",'error');
		return;
	}
	
	for ( var r = 1; r < i; r++) {
		if (!checkInOutsideArr(r)) {
			if($("#invoiceAmount_" + r).val()==''){
				$.messager.alert('Tips', "请填写第"+r+"行金额",'error');
				return;
			}
			if(Number($("#invoiceAmount_" + r).val())<=0){
				$.messager.alert('Tips', "申请金额为0，请确认",'error');
				return;
			}
			if($("#travelStartDate_" + r).datebox('getValue')==''){
				$.messager.alert('Tips', "请填写第"+r+"行出差起始日期",'error');
				return;
			}
			if($("#travelEndDate_" + r).datebox('getValue')==''){
				$.messager.alert('Tips', "请填写第"+r+"行出差结束日期",'error');
				return;
			}
			if($("#travelNum_" + r).val()==''){
				$.messager.alert('Tips', "请填写第"+r+"行出差天数",'error');
				return;
			}
			var costmemo=$('#memo_'+r).val();
			if(costmemo.length>75){
				$.messager.alert('Tips', '申请报销详细说明字数不能超过75个字', 'warning');
				return;
			}
			if($("#regions_" + r).combobox('getValue')==''){
				$.messager.alert('Tips', "请选择第"+r+"目的地所在省",'error');
				return;
			}
			if($("#regionc_" + r).combobox('getValue')==''){
				$.messager.alert('Tips', "请选择第"+r+"目的地所在市",'error');
				return;
			}
		}
	}
	
	
	
	$("#pay_ee").val($("#payee").combogrid("getText"));
	$("#pay_account").val($("#payAccount").combobox("getText"));

	var x = 0;
	var detailStr = "";
	
	detailStr += "[";
	
	for ( var k = 1; k < i; k++) {
		if (!checkInOutsideArr(k)) {
			x++;
			var travelPlace= $("#regions_" + k).combobox("getText")+$("#regionc_" + k).combobox("getText")+$("#regionb_" + k).val();
			detailStr += "{" 
					+ "\"travelStartDateSt\" : \"" + $("#travelStartDate_" + k).datebox("getValue")+ "\"," 
					+ "\"travelEndDateSt\" : \""+ $("#travelEndDate_" + k).datebox("getValue") + "\","
					+ "\"travelNum\" : \"" + $("#travelNum_" + k).val() + "\","
					+ "\"peerPerson\" : \"" + $("#peerPerson_" + k).val() + "\","
					+ "\"travelPlace\" : \"" + travelPlace
					+ "\"," + "\"invoiceAmount\" : \""
					+ $("#invoiceAmount_" + k).val() + "\","
					+ "\"meno\" : \"" + $("#memo_" + k).val()
					+ "\"" + "},";
		}
	}
	if (x > 0) {
		detailStr = detailStr.substring(0, detailStr.length - 1);
	}
	detailStr += ']';
	
	$("#detailJsonStr").val(detailStr);
	
	$.messager.confirm('Confirm', '确定提交吗', function(r) {/*
		if(r){
			$.messager.progress();
			$.ajax({
				type : "post",
				url:appUrl + "/account/accountAction!getStartUserRole.jspa?time="+ new Date(),
				success:function(data) {
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
			});
			
		}
	*/
		startTransaction();
	});
}

function  startTransaction(){
	$.ajax({
		type : "post",
		url : appUrl
				+ "/travelApp/travelAppAction!selectNexUser.jspa?time="
				+ new Date(),
		data : {
			userId : $("#curUserId").val(),
			totalMoney : $("#totalMoney").val(),
			flag : $("#flag").val()
		},
		success : function(userUtil) {
			$.messager.progress('close');
			if (userUtil == null || userUtil == "") {
				$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
				return;
			}
			if (userUtil != null && userUtil.processInstanceId != '-2'
					&& userUtil.processInstanceId != undefined) {
				var nextUser1 = "";
				var total = 0;
				$.each(userUtil.result, function(i, v) {
					total = i + 1;
					nextUser1 = v.userId;
				});
				if (total == 1) {
					$("#nextUserId").val(nextUser1);
					var form = window.document.forms[0];
					form.action = appUrl+ "/travelApp/travelAppAction!startWorkflowFix.jspa?eventId="+ userUtil.processInstanceId;
					form.submit();
				} else if (total == 0) {
					$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",'error');
					return;
				} else {
					if (userUtil.processInstanceId == "-1") {
						$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",'error');
						return;
					}
					var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
							+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
							+ "<tr><td class='head' noWrap>处理人</td>"
							+ "<td><select id='nextUserId1' name='nextUserId1'>";
					$.each(userUtil.result, function(i, v) {
						positionHtml += "<option value='" + v.userId
								+ "'>" + v.userName + "----"
								+ v.stationName + "</option>";
					});
					positionHtml += "</select></td></tr></table></div>";
					if ($('#nextUserDialog').length < 1) {
						$(
								'<div/>',
								{
									id : 'nextUserDialog',
									title : '选择下个处理人',
									html : "<div id='nextUser'>"
											+ positionHtml + "</div>"
											+ "</div>"
								}).appendTo('body');
					} else {
						$('#nextUser').html(positionHtml);
					}
					$('#nextUserDialog')
							.dialog(
									{
										modal : true,
										resizable : false,
										dragable : false,
										closable : false,
										open : function() {
											$('#nextUserDialog').css(
													'padding', '0.4em');
											$(
													'#nextUserDialog .ui-accordion-content')
													.css('padding',
															'0.4em')
													.height(
															$(
																	'#nextUserDialog')
																	.height() - 75);
										},
										buttons : [
												{
													text : '确定',
													handler : function() {
														if ($("#nextUserId1").val() == ""|| $("#nextUserId1").val() == null) {
															$.messager.alert('Tips',"没有下个处理人，请维护！",'error');
															return;
														}
														$.messager.progress();
														$("#nextUserId").val($("#nextUserId1").val());
														var form = window.document.forms[0];
														form.action = appUrl
																+ "/travelApp/travelAppAction!startWorkflowFix.jspa?eventId="
																+ userUtil.processInstanceId;
														form.submit();
													}
												},
												{
													text : '取消',
													handler : function() {
														$('#nextUserDialog').dialog('close');
													}
												} ],
										width : document.documentElement.clientWidth * 0.50,
										height : document.documentElement.clientHeight * 0.40
									});
				}
			} else {
				$.messager.alert('Tips', "流程出错！请联系管理员",
				'error');
			}
		},
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
    var sDate1=$("#travelStartDate_" + k).datebox("getValue");   
    var sDate2=$("#travelEndDate_" + k).datebox("getValue"); 
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
		$("#travelEndDate_" + k).datebox("setValue",'');
		$("#travelStartDate_" + k).datebox("setValue",'');
		return;
    }
}

