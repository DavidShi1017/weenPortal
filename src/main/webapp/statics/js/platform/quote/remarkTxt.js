$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
//	
//	 var str = $('#oldremark').html();
//	 if(str!='undefined'&&str!=''){
//		 
//		 var count = str.length/40;
//		 var str1 = "";
//		 for(var i=0;i<=count;i++){
//			 var s = str.substring(i*40,(i*1+1)*40);
//			 str1+=s+"<br>";						 
//		 }
//		 $('#oldremark').html(str1);
//	 }
	
});
if($('#fromWho').val()!="Ween"){
	$('.ween_reject').hide();
}

function submit() {	
	  if($.trim($('#remark').val())==""){
		  return;
	  }
	  $.messager.confirm('Confirm', 'Please check the information to determine the submission!', function(r) {
		if(r){	
			var str="";
			if($.trim($('#remark').val())!=""){
				str= $('#userName').val()+": "+$.trim($('#remark').val())+"<br>";
			}
			if($('#id').val()!=""){
				var param ="";
				if($('#forWho').val()=="Ween"){
					param = "&remark="+escape(str);
				}else if($('#forWho').val()=="Disti"){
					param = "&cus_remark="+escape(str);
				}
				var form = window.document.forms[0];
				form.action = appUrl + '/quoteAction!updateRemark.jspa?id='+$('#id').val()+param;
				form.target = "hideFrame";
				form.submit();				
			}
			window.parent.closeMain();
			window.parent.setRemark(str,$('#rowIndex').val(),$('#forWho').val());
		}
	});
}

function cancel() {
	if($('#useFor').val()=="represent"){
		 $.messager.confirm('Confirm', 'Cant represent without remark!Sure to cancel?', function(r) {
			if(r){
				window.parent.closeMain();
			}
		 });
	}else{
		window.parent.closeMain();		
	}
}

//设备类型
$('#reject_reason').combobox({
	url : appUrl
	+ '/designRegAction!getDictOfWeen.jspa?dictTypeId=565',
	valueField : 'itemId',
	textField : 'itemName',
	multiple : false,
	editable : false,
	required : false,
	panelHeight : 150,
	width : 360,
	onSelect : function(r){
		var rmk=$('#remark').val();
		if($.trim($('#remark').val())==""){			
			rmk += r.itemName;
		}else{
			rmk += ";"+r.itemName;
		}
		$('#remark').val(rmk);
 	} 
});
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		if(failResult==undefined || failResult=='undefined'){
			return;
		}
		$.messager.alert('Tips', failResult, 'warning');
 	} else{
 		$.messager.alert('Tips', successResult, 'info',function(){			
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}

$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});


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