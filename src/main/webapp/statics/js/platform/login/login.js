$(document).ready(function() {
	setInterval("changecolor()", 300);
	$('#hideFrame').bind('load', promgtMsg);
	// isScale();
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
var nextcolor = 0;
function changecolor() {
	var colors = [ "red", "red" ];
	document.getElementById("logfail").style.color = colors[nextcolor];
	nextcolor = nextcolor == 0 ? 1 : 0;
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	if (event.keyCode == 8) {
		if (event.srcElement.tagName.toLowerCase() != "input"
				&& event.srcElement.tagName.toLowerCase() != "textarea"
				&& event.srcElement.tagName.toLowerCase() != "password")
			event.returnValue = false;
		if (event.srcElement.readOnly == true
				|| event.srcElement.disabled == true)
			event.returnValue = false;
	}
	return true;
};

$('#passport').blur(function() {
	var str = $.trim($('#passport').val());
	$('#passport').val(str);
});
function submit() {
	var form = window.document.forms[0];
	form.submit();
}

function cancel() {
	$('#passport').val('');
	$('#pwd').val('');
	$('#logfail').val('');
}

function detectZoom () {

	var ratio = 0;
	screen = window.screen;

	ua = navigator.userAgent.toLowerCase();

	if (window.devicePixelRatio !== undefined) {
        ratio = window.devicePixelRatio;
	}
	else if (~ua.indexOf('msie')) { 
	    if (screen.deviceXDPI && screen.logicalXDPI) {
            ratio = screen.deviceXDPI / screen.logicalXDPI;
        }
    }
    else if (window.outerWidth !== undefined && window.innerWidth !== undefined) {
        ratio = window.outerWidth / window.innerWidth;
    }
    if (ratio) {
        ratio = Math.round(ratio * 100);
    }
  	return ratio;
};

$(window).on('resize',function() {
	// isScale();
});

function isScale() {
    var rate = detectZoom();
	if(rate != 100){
    	alert('The current display ratio is not 100%. For browsing experience, please adjust the display ratio to 100%.')
    }
}