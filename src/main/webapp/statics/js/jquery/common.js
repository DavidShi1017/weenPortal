$(document).ready(function() {

});
function clearValue() {
	document.forms[0].reset();
}


/** 
这是用 js 实现数值的千分位及保存小数方法：
        * 将数值四舍五入后格式化. 
        * @param num 数值(Number或者String) 
        * @param cent 要保留的小数位(Number) 
        * @param isThousand 是否需要千分位 0:不需要,1:需要(数值类型); 
        * @return 格式的字符串,如'1,234,567.45' 
        * @type String 
        */  
function formatNumber(num,cent,isThousand) {  
	if (num==null || num ==undefined)
		return 0;
    num = num.toString().replace(/\$|\,/g,'');  
  
        // 检查传入数值为数值类型  
      if(isNaN(num))  
        num = "0";  
  
        // 获取符号(正/负数)  
        sign = (num == (num = Math.abs(num)));  
  
        num = Math.floor(num*Math.pow(10,cent)+0.50000000001);  // 把指定的小数位先转换成整数.多余的小数位四舍五入  
    cents = num%Math.pow(10,cent);              // 求出小数位数值  
    num = Math.floor(num/Math.pow(10,cent)).toString();   // 求出整数位数值  
    cents = cents.toString();               // 把小数位转换成字符串,以便求小数位长度  
  
        // 补足小数位到指定的位数  
    while(cents.length<cent)  
      cents = "0" + cents;  
  
        if(isThousand) {  
          // 对整数部分进行千分位格式化.  
      for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
        num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));  
        }  
  
        if (cent > 0)  
          return (((sign)?'':'-') + num + '.' + cents);  
    else  
      return (((sign)?'':'-') + num);  
}
  
function createShadeDiv() {
	var exist = document.getElementById("shadeDiv") != null;
	var shadeDiv;
	if (exist) {
		shadeDiv = document.getElementById("shadeDiv");
	} else {
		shadeDiv = document.createElement("div");
	}

	var bh = document.body.scrollHeight;
	var dh = document.documentElement.scrollHeight;

	var bw = document.body.scrollWidth;
	var dw = document.documentElement.scrollWidth;
	var sheight = bh > dh ? bh : dh;
	var swidth = bw > dw ? bw : dw;

	if (exist) {
		var iframe = document.getElementById('shadeIframe');
		iframe.width = swidth;
		iframe.height = sheight;
		window.document.body.focus();
		return;
	}
	shadeDiv.id = "shadeDiv";
	shadeDiv.style.position = "absolute";
	shadeDiv.style.left = 0;
	shadeDiv.style.top = 0;
	shadeDiv.style.zIndex = 100;
	shadeDiv.style.filter = "alpha(opacity=30)";
	shadeDiv.style.backgroundColor = "rgb(69,73,78)";
	shadeDiv.style.width = swidth;
	shadeDiv.style.height = sheight;

	var sb = [];
	sb
			.push('<iframe scrolling="no" width="100%" height="100%" frameborder="0"');
	sb.push('id="shadeIframe" name="shadeIframe" ');
	sb
			.push('style="z-index:99;position:absolute; top:0px;left:0px;filter:alpha(opacity=0);"');
	sb.push('></iframe>');

	shadeDiv.innerHTML = sb.join("");
	document.body.appendChild(shadeDiv);

	document.body.focus();
}

function closeShadeDiv() {
	var loadProceedImg = document.getElementById("loadProceedImg");
	if (loadProceedImg != undefined)
		loadProceedImg.removeNode(true);
	var loadProceedDiv = document.getElementById("loadProceedDiv");
	if (loadProceedDiv != undefined) {
		loadProceedDiv.removeNode(true);
		loadProceedDiv.innerHTML = "";
	}
	var deleteIframe = document.getElementById("shadeIframe");
	if (deleteIframe != undefined)
		deleteIframe.removeNode(true);
	var deleteDiv = document.getElementById("shadeDiv");
	if (deleteDiv != undefined) {
		deleteDiv.removeNode(true);
		deleteDiv.innerHTML = "";
	}
}