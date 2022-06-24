var t = n = 0, count;
$(document)
		.ready(
				function() {
					count = $("#banner_list a").length;
					$("#banner_list a:not(:first-child)").hide();
					$("#banner_info").html(
							$("#banner_list a:first-child").find("img").attr(
									'alt'));
					$("#banner_info").click(
							function() {
								window.open($("#banner_list a:first-child")
										.attr('href'), "_blank");
							});
					$("#banner li")
							.click(
									function() {
										var i = $(this).text() - 1;
										n = i;
										if (i >= count)
											return;
										$("#banner_info").html(
												$("#banner_list a").eq(i).find(
														"img").attr('alt'));
										$("#banner_info")
												.unbind()
												.click(
														function() {
															window
																	.open(
																			$(
																					"#banner_list a")
																					.eq(
																							i)
																					.attr(
																							'href'),
																			"_blank");
														});
										$("#banner_list a").filter(":visible")
												.fadeOut(500).parent()
												.children().eq(i).fadeIn(1000);
										document.getElementById("banner").style.background = "";
										$(this).toggleClass("on");
										$(this).siblings().removeAttr("class");
									});
					t = setInterval("showAuto()", 4000);
					$("#banner").hover(function() {
						clearInterval(t);
					}, function() {
						t = setInterval("showAuto()", 4000);
					});
				});

function showAuto() {
	n = n >= (count - 1) ? 0 : ++n;
	$("#banner li").eq(n).trigger('click');
}

function oneNews(detailId, totalShow) {
	var d1 = new Date().getTime();
	var WWidth = 950;
	var WHeight = 650;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	var url = appUrl + "/newsAction!getOneNews.jspa?detailId=" + detailId
			+ "&totalShow=" + totalShow + "&rtime=" + d1;
	window.open(url, "OneNews", "left=" + WLeft + ",top=" + WTop
			+ ",width=" + WWidth + ",height=" + WHeight
			+ ",toolbar=no,menubar=no,scrollbars=yes");
}

function getSearchNews(totalId, totalName, totalShow) {
	var d1 = new Date().getTime();
	var url = appUrl + "/newsAction!getSearchNews.jspa?total_id=" + totalId
			+ "&totalShow=" + totalShow + "&rtime=" + d1;
	var $win = $("#searchMore")
			.window(
					{
						title : totalName + '>>>',
						width : 800,
						height : 400,
						top : ($(window).height() - 380) * 0.5,
						left : ($(window).width() - 800) * 0.5,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
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
			if (text == '首页公告') {
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
			// var currTab = $('#tabs').tabs('getTab', text);
			// currTab.panel('refresh');
		}
	}
}

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