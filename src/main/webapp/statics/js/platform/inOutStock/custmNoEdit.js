$(document).ready(function() {
	loadGrid();
});
function loadGrid(){
	if($('#state').val()=="D"){
		document.getElementById("state").checked=true;
	}
}
function close(){
	window.parent.closeWindows();
}

function changeKunnr(){
	var kunnr=$('#kunnr').val();
	$.ajax({
		type : "get",
		async : false,
		url : appUrl+ '/inOutStockAction!getKunnmByKunnr.jspa',
		data : {kunnr:kunnr},
		loadMsg : '数据远程载入中,请等待...',
		success : function(obj) {
			$('#kunnm').val(obj);
		}
	});
}

function submit() {
	var kunnr =$('#kunnr').val();
	var kunnm=encodeURIComponent($('#kunnm').val());
	var memo=encodeURIComponent($('#memo').val());
	var id = $("#id").val();
	
	var state="";
	if(document.getElementById("state").checked){
		state="D";
	}
		 
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/inOutStockAction!saveCustmNo.jspa",
		data : {
			id:id,
			kunnr:kunnr,
			kunnm:kunnm,
			memo:memo,
			state:state
		},
		success : function(obj) {
			if(obj>0){
				$.messager.alert('提示：', '操作成功!', 'info');
				window.parent.loadGrid();
				window.parent.closeWindows();
			}else{
				$.messager.alert('提示：', '操作失败!', 'info');
			}
		}
	}); 
}
