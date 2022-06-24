$(document).ready(function() {
    loadGrid(); // 权限点查询
    $('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
    $('#datagrid')
            .datagrid(
                    {
                        iconCls : 'icon-list',
                        title : '',
                        fit:true,
                        striped : true,
                        url : appUrl+ '/quote/quoteAction!checkQuote.jspa',
                        queryParams: {
                            id:$("#id").val(),
                            endCustomer_name:encodeURIComponent($("#endCustomer_name").val()),
                            purchaseCustomer_name:encodeURIComponent($("#purchaseCustomer_name").val()),
                            pcGroup_id:encodeURIComponent($("#pcGroup_id").val()),
                            ecGroup_id:encodeURIComponent($("#ecGroup_id").val()),                        
                            material_name:encodeURIComponent($("#material_name").val()),
                            start_dateStr:$("#start_dateStr").val(),
                        },        
                        loadMsg : 'Loading...',
                        singleSelect : true,
                        nowrap : true,
                        // idField : 'dictTypeId',
                        pagination : true,
                        rownumbers : true,
                        fitColumns : false,
                        frozenColumns:[[
                                        {
                                            field : 'ck',
                                            align : 'center',
                                            checkbox : true
                                        },
                                        {
                                            title : "Agreement",
                                            field : 'isAgree',
                                            width : 70,
                                            align : 'center',
                                            formatter : function(value, row, rec) {
                                                /**
                                                 * 状态 0处理中，1确认
                                                 */
                                                var flag = row.isAgree;
                                                if (flag == '0') {
                                                    return "";
                                                }else if (flag == '1') {
                                                    return "<font color='green'>Agree</font>";
                                                }  else if (flag == '2') {
                                                    return "<font color='gray'>Expired</font>";
                                                }  
                                            },
                                            // hidden:true,
                                        },
                                        {
                                            title : "Quote Status",
                                            field : 'state',
                                            width : 90,
                                            align : 'center',
                                            formatter : function(value, row, rec) {
                                                var flag = row.state;
                                                if (flag == '9') {
                                                    return "<font color='red'>Deleted</font>";
                                                }else if (flag == '0') {
                                                    return "<font color='black'>Sales Pending</font>";
                                                }else if (flag == '1') {
                                                    return "<font color='black'>Business Pending</font>";
                                                }else if (flag == '2') {
                                                    return "<font color='black'>Director Pending</font>";
                                                }else if (flag == '3') {
                                                    return "<font color='green'>Sales Approved</font>";
                                                }else if (flag == '4') {
                                                    return "<font color='green'>Business Approved</font>";
                                                }else if (flag == '5') {
                                                    return "<font color='green'>Director Approved</font>";
                                                }else if (flag == '6') {
                                                    return "<font color='red'>Sales Reject</font>";
                                                }else if (flag == '7') {
                                                    return "<font color='red'>Business Reject</font>";
                                                }else if (flag == '8') {
                                                    return "<font color='red'>Director Reject</font>";
                                                } else{
                                                    return flag;
                                                } 
                                            }
                                        },
                                
                            ]],
                        columns : [ [
                                    {
                                        title : "ID",
                                        field : 'id',
                                        width : 80,
                                        hidden : true,
                                        align : 'center'
                                    },
                                    {
                                        title : "Quote Num",
                                        field : 'quote_id',
                                        width : 80,
                                        align : 'center',
                                    },
                                    {
                                        field : 'material_name',
                                        title : 'BookPart',
                                        width : 120,
                                        align : 'center',
                                        // hidden:true
                                    },
                                    {
                                        field : 'drNum',
                                        title : 'DR Number',
                                        width : 80,
                                        align : 'center',
                                    
                                    },
                                    {
                                        field : 'qty',
                                        title : 'QTY',
                                        width : 80,
                                        align : 'center',
                                        formatter : function(value, row, rec) {
                                            var flag = row.qty;
                                            if (flag == ''||flag==undefined) {
                                                return "";
                                            } else{    
                                                return Math.floor(flag);
                                            } 
                                        }
                                    } ,
                                    {
                                        field : 'target_cost',
                                        title : 'Target Cost', 
                                        width : 80,
                                        align : 'center',    
                                        formatter : function(value, row, rec) {
                                            var flag = row.target_cost;
                                            if (flag == 0) {
                                                return flag;
                                            } else{
                                                flag=JSON.parse(flag*1);
                                                return flag.toFixed(4);
                                            } 
                                        }
                                    } ,
                                    {
                                        field : 'target_resale',
                                        title : 'Target Resale',
                                        width : 85,
                                        align : 'center',    
                                        formatter : function(value, row, rec) {
                                            var flag = row.target_resale;
                                            if (flag == 0) {
                                                return flag;
                                            } else{
                                                flag=JSON.parse(flag*1);
                                                return flag.toFixed(4);
                                            } 
                                        }
                                    } ,
                                    {
                                        title : "Price Region",
                                        field : 'price_region',
                                        width : 80,
                                        align : 'center',
                                    },
                                    {
                                        title : "Disti",
                                        field : 'cusGroup_id',
                                        width : 250,
                                        align : 'left'
                                    },
                                    {
                                        title : "Disti Branch",
                                        field : 'disti_branch',
                                        width : 250,
                                        align : 'left'
                                    },
                                    {
                                        title : "PC",
                                        field : 'purchaseCustomer_name',
                                        width : 100,
                                        align : 'center'
                                    },
                                    {
                                        title : "PC LOCATION",
                                        field : 'pc_city',
                                        width : 80,
                                        align : 'center'
                                    },
                                    {
                                        title : "End Customer",
                                        field : 'endCustomer_name',
                                        width : 150,
                                        align : 'left',
                                    },
                                    {
                                        title : "EC LOCATION",
                                        field : 'ec_city',
                                        width : 80,
                                        align : 'center'
                                    },
                                {
                                    title : "Project",
                                    field : 'project_name',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    title : "Currency",
                                    field : 'currency_code',
                                    width : 90,
                                    align : 'center'
                                },
                                {
                                    field : 'suggest_cost',
                                    title : 'Suggest Cost', 
                                    width : 80,
                                    align : 'center',    
                                    editor: {type:'numberbox',options:{precision:4}},
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_cost;
                                        if (flag == 0||flag == ''||flag==undefined) {
                                            return flag;
                                        } else{
                                            flag=JSON.parse(flag*1);
                                            return flag.toFixed(4);
                                        } 
                                    }
                                } ,
                                {
                                    field : 'suggest_resale',
                                    title : 'Suggest Resale', 
                                    width : 80,
                                    align : 'center',    
                                    editor: {type:'numberbox',options:{precision:4}},
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_resale;
                                        if (flag == 0||flag == ''||flag==undefined) {
                                            return flag;
                                        } else{
                                            flag=JSON.parse(flag*1);
                                            return flag.toFixed(4);
                                        } 
                                    }
                                } ,
                                {
                                    field : 'cus_profits_percent',
                                    title : 'Disti Margin', 
                                    width : 90,
                                    align : 'center',    
                                    editor: {type:'text',options:{disabled:true}},
                                    formatter : function(value, row, rec) {
                                        var flag = row.cus_profits_percent;
                                        if (flag == ''||flag==undefined||flag=='undefined') {
                                            return "";
                                        } else{
                                    
                                            return flag+"%";
                                        } 
                                    }
                                } ,
                                {
                                    field : 'profits_percent',
                                    title : 'Mfr Margin', 
                                    width : 90,
                                    align : 'center',    
                                    editor: {type:'text',options:{disabled:true}},
                                    formatter : function(value, row, rec) {
                                        var flag = row.profits_percent;
                                        if (flag == ''||flag==undefined||flag=='undefined') {
                                            return "";
                                        } else{
                                    
                                            return flag+"%";
                                        }  
                                    },
                                    
                                } ,                    
                                {
                                    field : 'amount',
                                    title : 'Value',
                                    width : 80,
                                    align : 'center',
                                    editor: {type:'numberbox',options:{precision:4}},
                                } ,
                                {
                                    field : 'remark',
                                    title : 'Internal Comments',
                                    width : 350,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'||flag==undefined) {
                                            return "";
                                        } else{    
                                            return flag;
                                        } 
                                    }
                                }
                                ] ],
                    });
}

// 关闭创建页面
function closeMaintWindow() {
    $("#hiddenWin").window('close');
}

function promgtMsg() {
    var hideFrame = document.getElementById("hideFrame");
    var failResult = hideFrame.contentWindow.failResult;
    var successResult = hideFrame.contentWindow.successResult;
    if (failResult != "") {
        if(failResult==undefined || failResult=='undefined'){
            return;
        }
        $.messager.alert('Tips', failResult, 'warning');
    } else {
        $.messager.alert('Tips', successResult, 'info');
        search();
    }
}

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