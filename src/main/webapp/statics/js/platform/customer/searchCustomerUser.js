$(document).ready(function() {
	loadGrid("");
	$('#hideFrame').bind('load', promgtMsg);
 });
 

function loadGrid(authentication_type) {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						fit:true,
						striped : true,
						url : appUrl
						+ '/customerAction!getUserInfoList.jspa?ran='
						+ Math.random()+'&customer_code='+$('#customer_code').val(),
						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : false,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},{
									field : 'userId',
									title : 'User Id',
									width : 120,
									align : 'center',
									hidden:true,
									styler : function(value, record, index) {
										return "background:pink";
									}
								},{
									field : 'loginId',
									title : 'Login Id',
									width : 180,
									align : 'left' 
								},
								{
									field : 'userName',
									title : 'Customer Name',
									width : 150,
									align : 'left',
									sortable:true
								},
								{
									field : 'email',
									title : 'Email',
									width : 120,
 									sortable:true
								} ,
								{
									field : 'mobile',
									title : 'Mobilephone',
									width : 120,
 									sortable:true,
 									hidden:true
								} ,
								{
									field : 'userState',
									title : 'State',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var state = row.userState;
										if (state == 'Y') {
											return "<font color='green'>Active</font>";
										} else if (state == 'D') {
											return "<font color='red'>Disabled</font>";
										}
									},
									sortable:true
								},{
									field : 'create_user',
									title : 'Register User',
									width : 130,
									align : 'center',
 								},{
									field : 'create_date',
									title : 'Register Time',
									width : 130,
									align : 'center',
									formatter : function(date){
										if(date!=undefined){
											return utcToDate(date);											
										}else{
											return "";
										}
									}
 								},{
									field : 'modify_user',
									title : 'Update User',
									width : 130,
									align : 'center',
 								},{
									field : 'modify_date',
									title : 'Update Time',
									width : 130,
									align : 'center',
									formatter : function(date){
										if(date!=undefined){
											return utcToDate(date);											
										}else{
											return "";
										}
									}
 								}  ] ], 
 						toolbar : [  "-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								createCustomerUser();
							}
 						}
 						,"-", {
							text : 'Edit',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						
						}
 						,"-", {
							text : 'Disable',
							iconCls : 'icon-remove',
							handler : function() {
								del();
							}
						
						}, {
							text : 'Active',
							iconCls : 'icon-ok',
							handler : function() {
								startup();
							}
						
						}
						,"-", {
							text : 'Reset',
							iconCls : 'icon-edit',
							handler : function() {
								rePassWd();
							}						
						}
					,"-", {
							text : 'Role',
							iconCls : 'icon-edit',
							handler : function() {
								getByRoleStation();
							}
						
						}
//					,"-", {
//						text : 'Funloc',
//						iconCls : 'icon-list',
//						handler : function() {
//							funloc();
//						}
//					
//					}
						]

					});
  }


/**
 * ??????????????????????????????????????????
 */
function funloc() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('info', 'Please select the data item??????');
		return;
	}
	openWindow(false,'Bind User', '/customerAction!toSearchBindUser.jspa?userId='+rows[0].userId+'&loginId='+rows[0].loginId+'&userName='+rows[0].userName, 790, 420,0,0);
}


/**
 * ??????????????????????????????????????????
 */
function createCustomerUser() {
	openWindow(false,'Register User', '/customerAction!toCreateCustomerUser.jspa?customer_code='+$('#customer_code').val(), 790, 420,0,0);
}


/**
 * ????????????????
 * 
 * */
function getByRoleStation() {
	var rows = $('#datagrid').datagrid('getSelections');
    if(rows.length==0){
		$.messager.alert('Tips', "Please select the data item??????", 'warning');
		return;
    }
    openWindow(true,'Role assignment', '/roleAction!searchSelectedRole.jspa?stationId='
			+ (rows[0].userId)+'&kunnrSign=N', 850, 450,0,0);
}


function rePassWd(){
	
	var rows = $('#datagrid').datagrid('getSelections');
    if(rows.length==0){
		$.messager.alert('Tips', "Please select the data item??????", 'warning');
		return;
     }
    $.messager.confirm('Confirm', 'Confirmed about reset the password?', function(r) {
		if (r) {
		 	var form = window.document.forms[0];
		 	form.action = appUrl + "/allUserAction!resetPWdToKunnr.jspa?userId=" + rows[0].loginId;  
			form.submit();
		}
	}); 	
}

function del(){
	
	
	var rows = $('#datagrid').datagrid('getSelections');
    if(rows.length==0){
		$.messager.alert('Tips', "Please select the data item!", 'warning');
		return;
     }
    $.messager.confirm('Confirm', 'Confirmed about Disable?', function(r) {
		if (r) {
		 	var form = window.document.forms[0];
		 	form.action = appUrl + "/allUserAction!deleteUserByEmpId.jspa?ids=" + rows[0].userId+"&logins="+rows[0].loginId;  
			form.submit();
		}
	}); 	
	
	
}



function startup(){
	
	
	var rows = $('#datagrid').datagrid('getSelections');
    if(rows.length==0){
		$.messager.alert('Tips', "Please select the data item??????", 'warning');
		return;
     }
    $.messager.confirm("Tips", "Confirmed about enable??????", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!startup.jspa?userId=' + rows[0].userId+'&loginId4AD='+rows[0].loginId;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});	
	
	
}
 



function edit(){
	var ids="";
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item??????', 'warning');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('Tips', 'Multi-selection is not supported!', 'warning');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].userState == 'N') {
			$.messager.alert('Tips', 'Disabled user cannot be modified,please enable it firstly!', 'warning');
			return;
		}
		ids = rows[i].userId;
	} 
 
	openWindow(false,'Update User Info', '/allUserAction!toUpdateUserInfo.jspa?ids='+ids, 750, 400,0,0);

}

   
//????????????????????????????????
function openWindow(ffit,title, url, WWidth, WHeight,l,t) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						maximizable : true,
						collapsible : true,
						draggable : true,
						fit:ffit,
						left : l,
						top: t
					});

	$win.window('open');

}
// ????????????????
function closeMaintWindow() {
	$("#hiddenWin").window('close');
	
}

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
 	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
 	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}
   
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // ?????????????????????textarea????????????????????????????????
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // ???????????????readOnly????????????disable??????????????????????????????
	if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};



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
	week["Mon"] = "??";
	week["Tue"] = "??????";
	week["Wed"] = "??????";
	week["Thu"] = "??????";
	week["Fri"] = "??????";
	week["Sat"] = "??????";
	week["Sun"] = "??????";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}