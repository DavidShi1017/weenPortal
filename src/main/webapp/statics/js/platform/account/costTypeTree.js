var selectedId;
var selectedName;
var selectedLevel;
$(document)
		.ready(
				function() {
					/*$('#costTypeTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							selectedId = node.id;
							selectedName = node.text;
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});

					$("#treeMenu").menu({
						onClick : function(item) {
								select(selectedId, selectedName);
						}
					});*/

					$('#costTypeTree')
							.tree(
									{
										animate : true,
										url : appUrl
												+ '/account/accountAction!getCostTypeTreeListByAjax.jspa?cost_parent_id=0&type='+$("#type").val(),
										onBeforeExpand : function(node, param) {
											$('#costTypeTree').tree('options').url = appUrl
													+ "/account/accountAction!getCostTypeTreeListByAjax.jspa?cost_parent_id="
													+ node.id;
										},
										onClick : function(node) {// 单击事件
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
										},
										onDblClick : function(node) {
											select(node.id, node.text);
										}
									});
				});

function select(selectedId, selectedName) {
	/*if(selectedLevel==$('#level').val()){*/
		window.parent.returnCostTypeValue(selectedId, selectedName);
		window.parent.closeMaintEvent();
/*	}else{
		alert("此处只能选择第"+$('#level').val()+"级费用类型！");
	}*/
	
}