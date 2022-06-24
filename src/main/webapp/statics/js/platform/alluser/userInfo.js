$(document).ready(function() {
	loadGrid(); // 权限点查询
	 search();
	$('#hideFrame').bind('load', promgtMsg);
});

$('#clearV').click(function(){
	$('#orgId').val('');
});

function loadGrid() {

	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '',
						//height : 370,
						pageSize : 20,
						fit:true,
						striped : true,
						url : appUrl
								+ '/allUserAction!getUserInfoList.jspa?ran='
								+ Math.random(),

						loadMsg : 'Loading...',
						singleSelect : true,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : true,
						// frozenColumns : [ [ ] ],
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'dictTypeId',
									title : 'UserID',
									field : 'userId',
									width : 80,
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									title : 'OrgID',
									field : 'orgId',
									width : 80,
									align : 'center',
									hidden : true,
									formatter : function(value, row, rec) {
										var orgId = row.orgId;
										if (orgId == null) {
											return "";
										} else {
											return orgId;
										}
									}
								},
								
								{
									title : 'LoginId',
									field : 'loginId',
									width : $(this).width() * 0.16,
									align : 'left',
									sortable : true
								},
								{
									field : 'userName',
									title : 'Name',
									align : 'center',
									width : $(this).width() * 0.08,
									sortable : true
								},{
									title : 'OrgName',
									field : 'orgName',
									width : 120,
									align : 'center',
									//hidden : true,
									formatter : function(value, row, rec) {
										var orgName = row.orgName;
										if (orgName == null) {
											return "";
										} else {
											return orgName;
										}
									},hidden:true
								},
								{
									field : 'sex',
									title : 'Sex',
									align : 'center',
									width : $(this).width() * 0.07,
									sortable : true,
									formatter : function(value, row, rec) {
										var sex_ch = row.sex;
										if (sex_ch == 'M') {
											return "Male";
										} else if (sex_ch == 'F') {
											return "Female";
										}
									},hidden:true
								},
								{
									field : 'mobile',
									title : 'Mobile',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									field : 'phone',
									title : 'Phone',
									align : 'center',
									width : $(this).width() * 0.13,
									sortable : true,
									hidden:true
								},
								{
									field : 'email',
									title : 'Email',
									align : 'center',
									width : $(this).width() * 0.16,
									sortable : true
								},
								{
									field : 'stations',
									title : 'Option',
									align : 'center',
									width : $(this).width() * 0.08,
									formatter : function(value, row, rec) {
										var id = row.userId;
										var loginId =row.loginId;
										var orgName = row.orgName;
										var orgId = row.orgId;
										var state = row.userState;
										if (orgName == null) {
											orgName = "";
										} else {
											orgName = encodeURIComponent(orgName);
										}
										if (orgId == null) {
											orgId = "";
										} else {
											orgId = encodeURIComponent(orgId);
										}

										if (state == 'Y') {
											return  '<img style="cursor:pointer"  title="修改人员信息"  onclick=edit("'
													+ id
                                                    + '")  src='
                                                    + imgUrl
											        + '/images/actions/action_edit.png align="absMiddle"></img> '
											        +'<img style="cursor:pointer" onclick=forbidden("'
													+ id+'","' + loginId 
													+ '") title="禁用人员账号" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>'
											        +'<img style="cursor:pointer" onclick=getByRoleStation("'
													+ id
													 +'") title="维护角色" src='
													+ imgUrl
													+ '/images/actions/action_roles.png align="absMiddle"></img>';
											   
													
										} else {
											return '<img style="cursor:pointer" onclick=startup("'
											+ id+'","' + loginId 
											+ '") title="启用人员账号" src='
											+ imgUrl
											+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
								}
									}
								} ,
								{
									field : 'userState',
									title : 'Status',
									width : $(this).width() * 0.08,
									align : 'center',
									sortable : true,
									formatter : function(value, row, rec) {
										var state = row.userState;
										if (state == 'Y') {
											return "<font color='green'>Active</font>";
										} else if (state == 'N') {
											return "<font color='red'>Inactive</font>";
										}
									}
								}/*,
								{
									field : 'oper23',
									title : '操作',
									width : $(this).width() * 0.1,
									align : 'center',
									
									formatter : function(value, row, rec) {
										var id = row.userId;
										var state = row.userState;
										var loginId = row.loginId;
										var orgName = row.orgName;
										var orgId = row.orgId;
										if (orgName == null) {
											orgName = "";
										} else {
											orgName = encodeURIComponent(orgName);
										}
										if (orgId == null) {
											orgId = "";
										} else {
											orgId = encodeURIComponent(orgId);
										}
										 
										if (state == 'Y') {
											return  '<img style="cursor:pointer"  title="修改人员信息"  onclick=edit("'
													+ id
                                                    + '")  src='
                                                    + imgUrl
											        + '/images/actions/action_edit.png align="absMiddle"></img> '
											       
											        +'<img style="cursor:pointer" onclick=forbidden("'
													+ id+'","' + loginId 
													+ '") title="禁用人员账号" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>';
													
										} else {
											return '<img style="cursor:pointer" onclick=startup("'
													+ id+'","' + loginId 
													+ '") title="启用人员账号" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}
									}
								}*/] ],
								
						toolbar : [ 
						"-", {
							text : 'Add',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}
//						,"-", {
//							text : 'Delete',
//							iconCls : 'icon-remove',
//							handler : function() {
//								remove();
//							}
//						}
//						, 
//						{
//							text : 'Freeze',
//							iconCls : 'icon-reload',
//							handler : function() {
//								forbidden();
//							}
//						}
//						, "-",
//						{
//							text : '同步',
//							iconCls : 'icon-reload',
//							handler : function() {
//								synchronizationUser();
//							}
//						}
						,"-", {
						text : 'Access',
						iconCls : 'icon-add',
						handler : function() {
							access();
						}
					}]
					});

}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.loginId = $("#loginId").val();
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	var bhxjFlag;
	$("[name='bhxjFlag']").each(function() {
		if (this.checked) {
			bhxjFlag = this.value;
		} else {
			bhxjFlag = "N";
		}
	});
	queryParams.bhxjFlag = encodeURIComponent(bhxjFlag);
	$("#con_list").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 1000;
	var WHeight = 550;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					//
					});

	$win.window('open');
}

//创建窗口对象
function initMaintRole(title, url, WWidth, WHeight,left,top) {
	var url = appUrl + url;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						left : left,
						top : top
					});

	$win.window('open');
}
function initMaintWindowForEdit(title, url) {
	var url = appUrl + url;
	var WWidth = 750;
	var WHeight = 400;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
/**
 * 组织数
 * 
 * @param {}
 *            title
 * @param {}
 *            url
 */
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function initMaintWindowForStation(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

/**
 * 人员创建
 */
function add() {
	initMaintWindow('Register User', '/allUserAction!toCreateUser.jspa');
}

/**
 * 角色维护
 * 
 * */
function getByRoleStation(id) {
	 
	initMaintRole('维护角色', '/roleAction!searchSelectedRole.jspa?stationId='
			+ (id)+'&kunnrSign=N', 850, 450,100,50);
}

/**
 * 查看人员岗位
 * 
 */
function searchStation(id, state, orgId, orgName) {
	if (state == 'N') {
		$.messager.alert('Tip', '禁用状态人员没有岗位，不可查看', 'Tip');
		return;
	}
	initMaintWindowForStation('岗位查看',
			'/allUserAction!searchNStationUser.jspa?userId=' + id + "&orgId="
					+ orgId + "&orgName=" + encodeURIComponent(orgName));
}
/**
 * 修改人员信息
 */
function edit(userId) {
	//var ids = '';
	/*var rows = $('#con_list').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('Tip', '未选中任何人员!', 'Tip');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('Tip', '修改时只能选择一个人员', 'Tip');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].userState == 'N') {
			$.messager.alert('Tip', '未启用人员的信息不能修改，请先启用', 'Tip');
			return;
		}
		ids = rows[i].userId;
	}*/
	initMaintWindowForEdit('UserInfo Edit',
			'/customerAction!toUpdateUserInfo.jspa?ids=' + userId);

}

/**
 * 删除人员信息 删除可以批量
 */
function remove() {
	var ids = '';
	var logins = '';
	var rows = $('#con_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].userId + ",";
		logins += rows[i].loginId + ",";
	}
	if (ids == '') {
		$.messager.alert('Tip', '未选中任何人员!', 'Tip');
		return;
	} else {
		$.messager.confirm('Confirm', 'Sure to delete?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!deleteUserByEmpId.jspa?ids='
					+ ids + "&logins=" + logins;
			form.target = "hideFrame";
			form.submit();
		}
		});
	}

}

/**
 * 禁用 启用人员账号
 * 
 * @param {}
 *            id
 */
function forbidden(id,loginId) {
	$.messager.confirm("Tip", "Confirmed about disable？",
			function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl
			+ '/allUserAction!forbidden.jspa?userId=' + id+'&loginId4AD='+loginId;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}
function startup(id,loginId) {
	$.messager.confirm("Tip", "Confirmed about enable？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!startup.jspa?userId=' + id+'&loginId4AD='+loginId;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}
/**
 * 销售国家对应关系表
 */
function access() {
	var rows = $('#con_list').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item!', 'warning');
		return;
	}else if(rows.length>1) {
		$.messager.alert('Tips', 'Multiselect are not supported!', 'warning');
		return;
  	}
  	initMaintWindow('SaleCountry',
  			'/countryAction!toSearchSaleCountry.jspa?userId='+ rows[0].userId);
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tip', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tip', successResult, 'info');
		search();
	}
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
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
/**
 * 回车键默认时间绑定
 * 
 * @param {}
 *            e
 * @return {Boolean}
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	if(event.keyCode == 8) {
	     // 如果是在textarea内不执行任何操作
	        if(event.srcElement.tagName.toLowerCase() != "input"  && event.srcElement.tagName.toLowerCase() != "textarea" && event.srcElement.tagName.toLowerCase() != "password")
	            event.returnValue = false; 
	        // 如果是readOnly或者disable不执行任何操作
	if(event.srcElement.readOnly == true || event.srcElement.disabled == true) 
	            event.returnValue = false;                             
	}
	return true;
};
$(window).resize(function () { 
          $('#datagrid').datagrid('resize', {
              width: $(".f_main").width()
          });
});


/**
 * 人员同步
 */
function synchronizationUser(){
//	location.href=crmUrl + "/webserviceAction!synchronizationUser.jspa";
	 $.messager.progress({
		 title:'请稍等',
		 msg:'从OA同步数据：',
		 text:'Loading...',
		 value:0
		 });
	$.ajax({
		type: "POST",
        url: crmUrl + '/webserviceAction!synchronizationUser.jspa', //换成你要请求的action, paperId 是url参数
        contentType:'application/x-www-form-urlencoded;charset=UTF-8', 
        method:'post',
        error: function(msg){
        	 if(msg!="" && msg != null){
        		 var json = eval(msg);
        		 if(json != null && json != "" && json[0].result != null && json[0].result=="false"){
        			 alert(json[0].info);
        			 return false;
        		 }else{
        			 alert(msg.text);
        			 return false;
        		 }
        	 }
            $.messager.progress('close');
         },
         success: function(msg){
        	 if(msg!="" && msg != null){
        		 var json = eval(msg);
        		 if(json != null && json != "" && json[0].result != null && json[0].result=="false"){
        			 alert(json[0].info);
        			 return false;
        		 }
        	 }
        	 $.messager.progress('close');
        	 $('#con_list').datagrid('load');
         }
	});
}
