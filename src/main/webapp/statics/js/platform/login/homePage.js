var frameHeight;
var currentDir;// 当前目录
var navSize;// 导航个数
$(document).ready(
				function() {
					frameHeight = '100%';
					// welcome
					//$.growlUI('欢迎 ECP!', 'Have a nice day...');
 

					// 加载首页公告tab
 
 					 add('Index', '/newsAction!newsIndex.jspa', '_self');					
			  
					

					// 绑定tabs的右键菜单
					$("#tabs").tabs({
						onContextMenu : function(e, title) {
							e.preventDefault();
							$('#tabsMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							}).data("tabTitle", title);
						}
					});

					// 待办tip
					$.ajax({	type : "post",
								url : appUrl
										+ '/wfe/eventAction!getProcessedCount.jspa',
								success : function(result) {
									var title = '';
									var count = 0;
									if(result.code > 0){
										title += '<a href="#" onclick="openProcessedTab()"><font class=fontStyle>您有'
												+ result.code
												+ '条待办事宜,点击进入!<font></a>';
										count += 1;
									}
									if(result.result > 0){
										if(count > 0){
											title += '<br>';
										}
										title += '<a href="#" onclick="toCcSearchInit()"><font class=fontStyle>您有'
												+ result.result
												+ '条协商事宜,点击进入!<font></a>';
										count += 1;
									}
									if(result.text > 0){
										if(count > 0){
											title += '<br>';
										}
										title += '<a href="#" onclick="toCaSearchInit()"><font class=fontStyle>您有'
												+ result.text
												+ '条事宜被退回,点击进入!<font></a>';
										count += 1;
									}
									if(count > 0){
										$.messager.show({
												title : '待办事宜',
												msg : title,
												width : 230,
												height : 40 + count*40,
												timeout : 0,
												showSpeed : 2000
										});
									}
									 
								}
							});
					setTimeout(
							function() {// 加载2级导航菜单
								$
										.ajax({
											type : "post",
											async : false,
											url : appUrl
													+ '/menuAjaxAction!getMenuTreeListByAjax.jspa?node=0',
											success : function(treeList) {
												var html = '';
												navSize = treeList.length;
												for ( var i = 0; i < navSize; i++) {
													html = '<li id="navigation_'
															+ i
															+ '" class="system"><a href="#" onclick=navigation4MenuTree('
															+ treeList[i].id
															+ ',"'
															+ treeList[i].text
															+ '",'
															+ i
															+ ')><span style="font-weight:bold;color:black;" >'
															+ treeList[i].text
															+ '</span></a></li>';
													$("#navigation").append(
															html);
													if (treeList[i].isFirst
															&& treeList[i].isFirst == 'Y') {
														// async加载tree
														navigation4MenuTree(
																treeList[i].id,
																treeList[i].text,
																i);
													}

												}
												// 隐藏导航事件
												$(function() {
													var d = 300;
													$('#navigation a')
															.each(
																	function() {
																		$(this)
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-80px'
																						},
																						d += 150);
																	});

													$('#navigation > li')
															.hover(
																	function() {
																		$(
																				'a',
																				$(this))
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-2px'
																						},
																						200);
																	},
																	function() {
																		$(
																				'a',
																				$(this))
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-80px'
																						},
																						200);
																	});
												});
											}
										});
							}, 500);

				});

function collapseAll() {
	$('#rootTree').tree('collapseAll');
}
function expandAll() {
	$('#rootTree').tree('expandAll');
}

function add(text, attributes, target) {
	var flag = true;
	var tabs = $("#tabs");
	var allTabs = tabs.tabs("tabs");
	var opt = '';
	var check = attributes.indexOf('http://') == -1 ? false : true;
	var url = check ? attributes : (appUrl + attributes);
	if (target == '_blank') {
		window.open(url, '_blank');

	} else {
		$.each(allTabs, function() {
			opt = $(this).panel("options");
			if (opt.title == text) {
				flag = false;
			}
		});
		// 已存在的 选中并刷新
		if (flag) {
			if (text == '首页') {
				$('#tabs')
						.tabs(
								'add',
								{
									title : text,
									content : '<iframe frameborder="no" width="100%" height="'
											+ (document.all.work.offsetHeight * 1.6)
											+ '" src=' + url + '></iframe>',
									closable : false
								});

			} else {
				$('#tabs')
						.tabs(
								'add',
								{
									title : text,
									content : '<iframe frameborder="no" width="100%" height="'
											+ frameHeight
											+ '" src='
											+ url
											+ '></iframe>',
									closable : true
								});
			}
		} else {
			$('#tabs').tabs('select', text);
			 var currTab = $('#tabs').tabs('getTab', text);
 			 currTab.panel('refresh');
		}
	}
}

$("#tabsMenu").menu({
	onClick : function(item) {
		CloseTab(this, item.name);
	}
});

function selectedTab(title){
	$('#tabs').tabs({
         onSelect: function (title) {
      	  var currTab = $('#tabs').tabs('getTab', title);
			 currTab.panel('refresh');
           }
 });
	
}

function closeOtherTab(title, type){
	var tabs = $("#tabs");
	tabs.tabs("close", title);
}


// 关闭事件的实现
function CloseTab(menu, type) {
	var curTabTitle = $(menu).data("tabTitle");
	var tabs = $("#tabs");
	if (type === "close") {
		tabs.tabs("close", curTabTitle);
		return;
	}
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	$.each(allTabs, function() {
		var opt = $(this).panel("options");
		if (opt.closable && opt.title != curTabTitle && type === "other") {
			closeTabsTitle.push(opt.title);
		} else if (opt.closable && type === "all") {
			closeTabsTitle.push(opt.title);
		}
	});
	for ( var i = 0; i < closeTabsTitle.length; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}

// menutree
function initMenuTree(node) {
	$('#rootTree')
			.tree(
					{
						animate : true,
						url : appUrl
								+ '/menuAjaxAction!getMenuTreeListByAjax.jspa?node='
								+ node,
						onBeforeExpand : function(node, param) {
							$('#rootTree').tree('options').url = appUrl
									+ "/menuAjaxAction!getMenuTreeListByAjax.jspa?node="
									+ node.id;
						},
						onClick : function(node) {// 单击事件
							$(this).tree('toggle', node.target);
							if (!node.state) {
								if (node.attributes.split('#')[1] == '_blank') {
									add(node.text,
											node.attributes.split('#')[0],
											'_blank');
								} else {
									add(node.text,
											node.attributes.split('#')[0],
											'_self');
								}
							} else {
								var arrayNode = $('#rootTree').tree('getRoots');
								var parent = $('#rootTree').tree('getParent',
										node.target);
								for ( var i = 0; i < arrayNode.length; i++) {
									if (!parent
											&& arrayNode[i].target != node.target) {
										$('#rootTree').tree('collapse',
												arrayNode[i].target);
									}
								}
							}
						}
					});
}

function navigation4MenuTree(node, text, id) {
	for ( var j = 0; j < navSize; j++) {
		if (j == id) {
			$("#navigation_" + j).removeClass("system");
			$("#navigation_" + j).addClass("systemSelect");
		} else {
			$("#navigation_" + j).removeClass("systemSelect");
			$("#navigation_" + j).addClass("system");
		}
	}

	initMenuTree(node);
	var work = $("#work").panel("options").title;
	if (text) {
		//$("#work").panel('setTitle', "工作台 【" + text + "】");
		//$("#work").panel('setTitle', "中山榄菊经销商订单管理系统(ECP-CRM)");
	}
	currentDir = $("#work").panel("options").title;
}

function reloadOA() {
	var currTab = $('#tabs').tabs('getSelected');
	currTab.panel('refresh');
}

function openProcessedTab() {
	add('待办事宜', '/wfe/eventAction!toSearchProcessEvent.jspa', '_self');
}

function toCcSearchInit(){
	add('协商事宜', '/wfe/eventAction!toCcSearchInit.jspa', '_self');
}

function toCaSearchInit(){
	add('拒绝事宜', '/wfe/eventAction!toCaSearchInit.jspa', '_self');
}

//document.onkeydown = function(e) {
//	var theEvent = e || window.event;
//	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
//	if (code == 8) {
//		window.location.href = "loginAction!logout.jspa?action=logout";
//		return false;
//	}
//	return true;
//};
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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