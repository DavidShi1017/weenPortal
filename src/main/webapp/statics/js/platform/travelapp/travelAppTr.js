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
						+ '/account/accountAction!getPayeeJsonList.jspa?orgId='+orgId+'&userId='+userId,
				columns : [ [ {
					field : 'payee',
					title : '收款单位',
					width : 400,
					align : 'center'
				} ] ],
				onLoadSuccess : function() {
					if ($('#num').val() > 0) {
						$('#payee').combogrid('setText',$('#defaultPayee').val());
						$('#payAccount').combobox({
											url : appUrl
													+ '/account/accountAction!getPayAccountJsonList.jspa?payee='
													+ encodeURIComponent($(
															'#defaultPayee')
															.val())+'&orgId='+orgId+'&userId='+userId,
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
											.val())+'&orgId='+orgId+'&userId='+userId,
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
												+ encodeURIComponent(r.payee)+'&orgId='+orgId+'&userId='+userId,
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
										+ encodeURIComponent(r.payee)+'&orgId='+orgId+'&userId='+userId,
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
		$.messager.alert('Tips', failResult, 'info', function() {
			window.location.reload();
		});
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
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


/***
 * 提交核销流程
 */
function submit(){
	
	var x = 0;
	var detailStr = "";
	var error = "";
	if($("#totalMoney").val()==0){
		$.messager.alert('Tips', "申请金额为0，请确认", 'warning');
		return;
	}
	/*if(Number($("#totalMoney").val())>Number($("#budgetNumberBalance").val())){
		$.messager.alert('Tips', "申请金额大于预算金额", 'warning');
		return;
	}*/
	detailStr += "[";
	for(var k=0;k<$("#size").val();k++){
		if($("#invoiceAmount_"+k).val() == ""){
			error = "第"+Number(k+1)+"行核销金额必须输入！";
			break;
		}
		if($("#travelNum_"+k).val() == ""){
			error = "第"+Number(k+1)+"行出差天数必须输入！";
			break;
		}
		x++;
		detailStr += "{" 
			+ "\"travelStartDateSt\" : \"" + $("#travelStartDateSt_" + k).datebox("getValue")+ "\"," 
			+ "\"travelEndDateSt\" : \""+ $("#travelEndDateSt_" + k).datebox("getValue") + "\","
			+ "\"travelNum\" : \"" + $("#travelNum_" + k).val() + "\","
			+ "\"travleDtId\" : \"" + $("#travleDtId_" + k).val()+ "\","
			+ "\"travelPlace\" : \"" + $("#travelPlace_" + k).val()+ "\","
			 + "\"invoiceAmount\" : \""+ $("#invoiceAmount_" + k).val() + "\","
			+ "\"meno\" : \"" + $("#costMemo_" + k).val()
			+ "\"" + "},";
	}
	if(error != ""){
		$.messager.alert('Tips', error, 'warning');
		return;
	}

	$("#pay_ee").val($("#payee").combogrid("getText"));
	$("#pay_account").val($("#payAccount").combobox("getText"));
	//$("#pay_bank").val($("#payBank").combobox("getText"));
	
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	
	
	
	$.messager.confirm('Confirm', '确定提交吗', function(r) {
		if(r){
			var form = window.document.forms[0];
			form.action = appUrl + "/travelApp/travelAppAction!modifyTravelForm.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}



function print(){
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/travelApp/travelAppAction!print.jspa?travelId='+travelId+'&eventId='+writeEventId+'&writeStatus=1', "printTravel",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
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

function checkIsNumber(k, type) {
	var regAmount = /^\d+(.\d+)?$/;
	if ("amount" == type) {
		if (!regAmount.test($("#invoiceAmount_" + k).val())) {
			$("#invoiceAmount_" + k).val(0);
		}
		countTotalMoney();
	}
}

function countTotalMoney() {
	var total = 0;
	for ( var r = 0; r < $("#size").val(); r++) {
			var value = $("#invoiceAmount_" + r).val();
			if (value.length > 0) {
				total += Number(value);
			}
	}
	$("#totalMoney").val(RoundNumber(total, 2));
	if(Number($("#totalMoney").val())>Number($("#budgetNumberBalance").val())){
		$.messager.alert('Tips', "申请总金额大于预算金额", 'warning');
		$("#totalMoney").val('0');
		for ( var r = 0; r < $("#size").val(); r++) {
			 $("#invoiceAmount_" + r).val('0');
	}
		return;
	}
}

//数字 指定位数后 四舍五入
function RoundNumber(num, pos) {
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);
}

