$(document).ready(function() {
	initTree();
});

function collapseAll() {
	$('#orgTree').tree('collapseAll');
}

function initTree() {
	$('#orgTree')
			.tree(
					{
						animate : true,
						url : appUrl
								+ '/orgTreeAjaxAction!getOrgUserTreeListByAjax.jspa?node=0',
						onBeforeExpand : function(node, param) {
							$('#orgTree').tree('options').url = appUrl
									+ "/orgTreeAjaxAction!getOrgUserTreeListByAjax.jspa?node="
									+ node.id;
						},
						onClick : function(node) {// 单击事件
							$(this).tree('toggle', node.target);
							if (!node.state) {
								$
										.ajax({
											type : "post",
											url : appUrl
													+ "/allUserAction!userInfoInRtx.jspa",
											data : {
												userId : node.id
											},
											success : function(user) {
												$("#userName").val(
														user.userName);
												$("#sex").val(user.sex);
												$("#orgName").val(user.orgName);
												$("#stationNames").val(
														user.stationNames);
												$("#busMobilephone").val(
														user.busMobilephone);
												$("#phone").val(user.phone);
												$("#workFax").val(user.workFax);
												$("#email").val(user.email);
												$("#address").val(user.address);

											}
										});
							}
						}
					});
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