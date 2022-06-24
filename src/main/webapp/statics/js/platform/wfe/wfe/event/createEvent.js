$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	addUploadButton(editor);
});

function loadGrid(){
/*	$('#stationId').combobox({
		url : appUrl + '/wfe/processChooseAction!getStations.jspa',
		valueField : 'stationId',
		textField : 'stationName',
		onLoadSuccess : function() {
			$('#stationId').combobox("setText", '--请选择--');
		},
		editable : false,
		onSelect : function(re) {
			choiceOld(re.stationId);
		}
	});
*/
	selectModelCombox();
	$("#nextinfolist").hide();
}

function addUploadButton(editor){     
	CKEDITOR.on('dialogDefinition', function(ev){         
		var dialogName = ev.data.name;         
		var dialogDefinition = ev.data.definition;         
		if ( dialogName == 'image' ){             
			var infoTab = dialogDefinition.getContents( 'info' );             
			infoTab.add({                 
				type : 'button',                 
				id : 'upload_image',                 
				align : 'center',                 
				label : '上传',                 
				onClick : function( evt ){
					var thisDialog = this.getDialog();                     
					var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');                     
					var txtUrlId = txtUrlObj.getInputElement().$.id;                     
					addUploadImage(txtUrlId);                 
				}             
			}, 'browse'); 
		}
	});
}

function addUploadImage(theURLElementId){
	//这是处理文件/图片上传的页面URL ;scroll:yes;status:yes
	var uploadUrl = appUrl + '/file/fileAction!uploadImagPrepare.jspa?Rnd='+Math.random();	
	//在upload结束后通过js代码window.returnValue=...可以将图片url返回给imgUrl变量
	var imgUrl = window.showModalDialog(uploadUrl, null, "dialogWidth=400px;dialogHeight=200px");
	
	var urlObj = document.getElementById(theURLElementId);     
	urlObj.value = imgUrl;     
	urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片 
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
	}else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

/**
 * 一级下来列表联动
 * 
 * @param {}
 *            id
 */
/*function choiceOld(stationId) {
	var dataHtml = "<input id='curStationId' name='curStationId' readonly />"
			+ "<div id='modelReturn' name='modelReturn'></div>";
	document.getElementById("roleReturn").innerHTML = dataHtml;
	bindSecondCombox('curStationId');
}
*/
/**
 * 绑定二级下拉列表
 *//*
function bindSecondCombox(docId) {
	$('#' + docId).combobox({
		url : appUrl + '/station!getCompanyJsonList.jspa',
		valueField : 'orgId',
		textField : 'orgName',
		onLoadSuccess : function() {
			$('#' + docId).combobox("setText", '--请选择--');
		},
		editable : false,
		onSelect : function(re) {
			choiceNew(re.orgId);
		}
	});
}*/

/**
 * 二级下拉列表联动
 * 
 * @param {}
 *            id
 *//*
function choiceNew(orgId) {
	var dataHtml = "<input id='modelId' name='key' readonly/>";
	document.getElementById("modelReturn").innerHTML = dataHtml;
	bingThirdCombox('modelId');
}
*/
/**
 * 绑定三级下拉列表
 * 
 * @param {}
 *            id
 */
function selectModelCombox() {
	$('#modelId').combobox(
			{
				url : appUrl + '/wfe/processChooseAction!getModels.jspa?from=',
				valueField : 'key',
				textField : 'name',
				onLoadSuccess : function() {
					$('#modelId').combobox("setText", '--请选择--');
				},
				editable : false,
				onSelect : function(re) {
					modifyModel(re.key);
					if ("semiautomatic" == re.key) {
						initMaintEvent('半自动化流程绘制', '/wfe/eventAction!toSemiAutomatic.jspa', 750,450);
					}
				}
			});
}

function modifyModel(key) {
	$('#addContent').html(
					"<A class=\"icon_but\" href=\"javascript:addContent("
							+ "'"
							+ key
							+ "'"
							+ ",0"
							+ ",0)\">"
							+ "<img height=16 width=16 src=\""
							+ imgUrl
							+ "/images/actions/action_edit.png\" align=absMiddle border=0>添加内容</A>");
	if ("any" == key.substr(0,3)) {
		$("#nextinfolist").show();
	} else {
		$("#nextinfolist").hide();
	}
	flag = false;
}

/**
 * 添加内容
 * 
 * @param {}
 *            key
 * @param {}
 *            planAttId
 */
function addContent(key, planAttId, tempFielName) {
	if("fix_travel" == key) {
		initMaintEvent('内容创建',
			'/wfe/eventAction!toBusinessTripApply.jspa?key=' + key
					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	} else {
		initMaintEvent('内容创建',
			'/wfe/eventAction!updateEventContentPrepare.jspa?key=' + key
					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	}
}

/**
 * 选择联系人
 */
function createProEventReader() {
	initMaintEvent('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

function searchLinkMan() {
	initMaintEvent('常用联系人', '/wfe/eventAction!searchLinkMan.jspa', 340, 430);
}

/**
 * 保存联系人
 * 
 * @param {}
 *            x
 */
function saveUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[1]);
	$("#nextOrgId").val(x[2]);
	closeMaintEvent();
}

// 创建窗口对象
function initMaintEvent(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintEvent")
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
						draggable : true
					});
	$win.window('open');
}

/**
 * 关闭弹出窗口
 */
function closeMaintEvent() {
	$("#maintEvent").window('close');
}

/**
 * 内容填写完成之后
 */
function closeContent(planAttId) {
	var key = $('#modelId').combobox('getValue');
	var tempFielName = $("#xmlTemp_FileName").val();
	flag = true;
	$('#addContent').html(
					"<A class=\"icon_but\" href=\"javascript:addContent("
							+ "'"
							+ key
							+ "'"
							+ ","
							+ planAttId
							+ ",'"
							+ tempFielName
							+ "')\">"
							+ "<img height=16 width=16 src=\""
							+ imgUrl
							+ "/images/actions/action_edit.png\" align=absMiddle border=0>编辑内容</A>");
	closeMaintEvent();
}

function submit() {
	var n = $("#eventTitle").validatebox('isValid');
	if (!n) {
		return;
	}
	if (!flag) {
		$.messager.alert('Tips', '请填写内容', 'warning');
		return;
	}
	$("#memo").val(editor.document.getBody().getText()); // 取得纯文本
	var key = $('#modelId').combobox('getValue');
	if ("any" == key.substr(0,3)) {
		var nextuser = $("#nextUserId").val();
		if (nextuser == '' || nextuser.length == 0) {
			$.messager.alert('Tips', '请选择处理人', 'warning');
			return;
		}
	}
	var fix_key = key.substr(0,3);
	if ("fix" == fix_key) {
		$.messager.progress();
		$.ajax({
			type : "post",
			url : appUrl + "/wfe/eventAction!selectNexUser.jspa?time="+new Date() + "&projectId=" + $("#projectId").val(),
			data : {
				key : key,
				userId   : $("#curUserId").val(),
				modelKey : $("#modelKey").val(),
				modelValues:$("#modelValues").val()
			},
			success : function(userUtil) {
						$.messager.progress('close');
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
						    			form.action = appUrl + "/wfe/eventAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId;
						    			form.submit();
						    	   }},{
						    		   text:'取消',
						    		   handler:function(){ 
						    			   	var form = window.document.forms[0];
						    				form.action = appUrl + "/wfe/eventAction!cancelNextUser.jspa?eventId="+userUtil.processInstanceId;
						    				form.target = "hideFrame";
						    				form.submit();
								    	} 
						    	   } 
						    	   ],

						       width: document.documentElement.clientWidth * 0.50,
						       height: document.documentElement.clientHeight * 0.40
						   });
					},
				error: function (XMLHttpRequest, textStatus, errorThrown){
				  	$.messager.alert('Tips', textStatus, 'error');
			  	}
				});
		
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/wfe/eventAction!createEvent.jspa";
		form.submit();
	}
}

function reset() {
	$.messager.alert('Tips', "取消", 'error');
}

