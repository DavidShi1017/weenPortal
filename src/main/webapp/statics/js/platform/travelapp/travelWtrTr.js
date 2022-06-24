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
		$.messager.alert('Tips', failResult, 'warning');
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
		if($("#modfiy_" + k).val()=='false'){
			detailStr += "{" 
				+ "\"travelStartDateSt\" : \"" + $("#travelStartDateSt_" + k).val()+ "\"," 
				+ "\"travelEndDateSt\" : \""+ $("#travelEndDateSt_" + k).val() + "\","
				+ "\"travelNum\" : \"" + $("#travelNum_" + k).val() + "\","
				+ "\"travleDtId\" : \"" + $("#travleDtId_" + k).val()+ "\","
				+ "\"travelPlace\" : \"" + $("#travelPlace_" + k).val()+ "\","
				 + "\"invoiceAmount\" : \""+ $("#invoiceAmount_" + k).val() + "\","
				+ "\"auditMoney\" : \""+$("#auditMoney_"+k).val()+ "\","
				+ "\"meno\" : \"" + $("#costMemo_" + k).val()
				+ "\"" + "},";
		}
		else{
		detailStr += "{" 
			+ "\"travelStartDateSt\" : \"" + $("#travelStartDateSt_" + k).datebox("getValue")+ "\"," 
			+ "\"travelEndDateSt\" : \""+ $("#travelEndDateSt_" + k).datebox("getValue") + "\","
			+ "\"travelNum\" : \"" + $("#travelNum_" + k).val() + "\","
			+ "\"travleDtId\" : \"" + $("#travleDtId_" + k).val()+ "\","
			+ "\"travelPlace\" : \"" + $("#travelPlace_" + k).val()+ "\","
			 + "\"invoiceAmount\" : \""+ $("#invoiceAmount_" + k).val() + "\","
			+ "\"auditMoney\" : \""+$("#auditMoney_"+k).val()+ "\","
			+ "\"meno\" : \"" + $("#costMemo_" + k).val()
			+ "\"" + "},";
		}
	}
	if(error != ""){
		$.messager.alert('Tips', error, 'warning');
		return;
	}
	if($("#modfiy").val()=='y'){
		$("#pay_ee").val($("#payee").combogrid("getText"));
		$("#pay_account").val($("#payAccount").combobox("getText"));
		$("#pay_bank").val($("#payBank").combobox("getText"));
	}

	
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	
	$.messager.confirm('Confirm', '确定提交吗', function(r) {
		if(r){
			var form = window.document.forms[0];
			form.action = appUrl + "/travelApp/travelAppAction!modifyTravelTrForm.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function checkIsNumber(k, type){
	var regAmount = /^\d+(.\d+)?$/;
	if("auditMoney" == type){
		if(!regAmount.test($("#auditMoney_"+k).val())){
			$("#auditMoney_"+k).val('');
		}
		if($("#auditMoney_"+k).val()>$("#invoiceAmount_"+k).val()){
			$.messager.alert('Tips', "明细销金额大于明细申请金额", 'warning');
			$("#auditMoney_"+k).val('0');
			total=0;
		}
		countAuditMoney(k);
	}
}
function countAuditMoney(k){
	var total = 0;
	var check = false;
	for(var r=0;r<$("#size").val();r++){
			var value = $("#auditMoney_"+r).val();
			if(value != '') {
				check = true;	
				total += Number(value);
			}
	}
	
	if(RoundNumber(total, 2)>totalMoney){
		$.messager.alert('Tips', "总核销金额大于申请金额", 'warning');
		$("#auditMoney_"+k).val($("#auditMoney1_"+k).val());
		return;
	}
	if(check) {
		$("#auditMoney").val(RoundNumber(total, 2));
		changePayEe();
	} else {
		$("#auditMoney").val('0');
	}
	
}
function RoundNumber(num, pos)  {  
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);  
} 

function print(){
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/travelApp/travelAppAction!print.jspa?travelId='+travelId+'&eventId='+writeEventId+'&writeStatus=1', "printTravel",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}


function changePayEe(){
	var idx=$("input[name='travelTotal.payType']:checked").val();
	if(idx==1){
		$('#payCash').val(Number($('#auditMoney').val())-Number($('#chouQian').val()));
		$('#dkMoney').val(Number(0));
	}else if(idx==2){
		$('#dkMoney').val(Number($('#auditMoney').val())-Number($('#chouQian').val()));
		$('#payCash').val(Number(0));
	}else{
		$('#dkMoney').val(0);
		$('#payCash').val(0);
		$('#chouQian').val(0);
	}
}

function changeChouQian(){
	var idx=$("input[name='travelTotal.payType']:checked").val();
	if(Number($('#chouQian').val())>Number($('#auditMoney').val())){
		$.messager.alert('Tips', "抽欠金额大于核销金额，无法修改", 'warning');
		$('#chouQian$').val(Number($('#auditMoney').val())-Number($('#payCash').val()));
		return;
	}
	if(idx==1){
		$('#payCash').val(Number($('#auditMoney').val())-Number($('#chouQian').val()));
		$('#dkMoney').val(Number(0));
	}else if(idx==2){
		$('#dkMoney').val(Number($('#auditMoney').val())-Number($('#chouQian').val()));
		$('#payCash').val(Number(0));
	}else{
		$('#dkMoney').val(Number($('#auditMoney').val())-Number($('#chouQian').val())-Number($('#payCash').val()));
	}
}

function changepayCash(){
	var idx=$("input[name='travelTotal.payType']:checked").val();
	if(Number($('#payCash').val())>Number($('#auditMoney').val())){
		$.messager.alert('Tips', "付现金额大于核销金额，无法修改", 'warning');
		$('#payCash').val(Number($('#auditMoney').val())-Number($('#chouQian').val()));
		return;
	}
	if(idx==1){
		$('#chouQian').val(Number($('#auditMoney').val())-Number($('#payCash').val()));
		$('#dkMoney').val(Number(0));
	}else if(idx==2){
		$('#payCash').val(0);
		$.messager.alert('Tips', "支付类型为银行，无法修改付现金额", 'warning');
	}else{
		$('#dkMoney').val(Number($('#auditMoney').val())-Number($('#chouQian').val())-Number($('#payCash').val()));
	    if(Number($('#dkMoney').val())<0){
			$.messager.alert('Tips', "付现金额加抽欠金额加打卡金额大于核销金额，请确认", 'warning');
			$('#dkMoney').val($('#auditMoney').val());
			$('#payCash').val(0);
			$('#chouQian').val(0);
	    }
	}
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



