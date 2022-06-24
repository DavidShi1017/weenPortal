$(document).ready(function() {
    if ($('#equip_type').val().indexOf("-") > -1) {
        $('#equip_type').val($('#equip_type').val().replace("-", ""));
    }
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);
});
function cancel() {
    window.parent.closeMaintWindow();
}
function back() {
    window.parent.search();
    window.parent.closeMaintWindow();
}
function loadGrid() {
    $('#datagrid')
            .datagrid(
                    {
                        iconCls : 'icon-list',
                        title : '',
                        url : appUrl
                                + '/designRegAction!getDesignRegDetailList.jspa?main_id='
                                + $('#id').val(),
                        loadMsg : 'Loading...',
                        singleSelect : false,
                        nowrap : true,
                        checkbox : true,
                        required : true,
                        rownumbers : true,
                        height : 250,
                        fitColumns : false,
                        striped : true,
                        onLoadSuccess : function() {
                        	var forWho = $('#forWho').val();
                            if (forWho == "Disti") {
                                $('div.datagrid-toolbar a').eq(3).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(3).show();
                            }
                        },
                        columns : [ [
                                {
                                    field : 'ck',
                                    align : 'center',
                                    checkbox : true
                                },
                                {
                                    field : 'id',
                                    title : 'pk',
                                    width : 60,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'state',
                                    title : 'Status',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.state;
                                        if (flag == 0) {
                                            return "Pending";
                                        } else if (flag == 1) {
                                            return "<font color='green'>Approve</font>";
                                        } else if (flag == 2) {
                                            return "<font color='red'>Reject</font>";
                                        } else if (flag == 3) {
                                            return "<font color='gray'>Expired</font>";
                                        }
                                    },
                                },
                                {
                                    field : 'material_id',
                                    title : '12NC',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.material_id;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            var str = $.trim(flag);
                                            str = str
                                                    .substring(str.length - 12,
                                                            str.length);
                                            return str;
                                        }
                                    }
                                },
                                {
                                    field : 'material_name',
                                    title : 'BookPart',
                                    width : 150,
                                    align : 'left',
                                },
                                {
                                    field : 'drNum',
                                    title : 'DR number',
                                    width : 60,
                                    align : 'center',
                                    hidden : true
                                },

                                {
                                    field : 'price',
                                    title : 'Price',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'equip_usage',
                                    title : 'Pcs/Set',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'usage_amount',
                                    title : 'Usage Amount',
                                    width : 50,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'total_qty',
                                    title : 'Total Qty',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.equip_usage;
                                        var runrate = row.usage_amount;
                                        return (flag * runrate);
                                    }
                                },
                                {
                                    field : 'value',
                                    title : 'Value',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.value;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag * 1;// Math.floor(flag);
                                        }
                                    }
                                },
                                {
                                    field : 'start_dateStr',
                                    title : 'Registration Date',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'end_dateStr',
                                    title : 'Expiry Date',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'shipPrice',
                                    title : 'POS amount',
                                    width : 100,
                                    align : 'center',
                                },
								{
									field : 'dr_typeStr',
									title : 'DR Type',
									width : 100,
									align : 'center',
								},
								{
									field : 'dr_type',
									title : 'dr_type',
									width : 100,
									align : 'center',
									hidden : true,
								},
								{
									field : 'drtype_def',
									title : 'DR Type Definition',
									width : 250,
									align : 'center',
								},
                                {
                                    field : 'project_state',
                                    title : 'Project Status',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.project_state;
                                        if (flag == 0) {
                                            return "Opportunity";
                                        } else if (flag == 1) {
                                            return "Design_in";
                                        } else if (flag == 2) {
                                            return "Design_win";
                                        } else if (flag == 9) {
                                            return "DW_Reject";
                                        }
                                    }
                                },

                                {
                                    field : 'cus_remark',
                                    title : 'Disti Remark',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.cus_remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        }
                                        else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'remark',
                                    title : 'Internal Comment',
                                    width : 150,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                }
                                ,
                                {
                                    field : 'weencomments',
                                    title : 'Ween Comments',
                                    width : 150,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.weencomments;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                }
                                , {
                                    field : 'primaryProState',
                                    title : 'Cus Remarks',
                                    width : 200,
                                    align : 'center',
                                    editor : 'text',
                                    hidden : true,
                                } ] ],
                        toolbar : [ "-", {
                            text : 'Approve',
                            iconCls : 'icon-ok',
                            handler : function() {
                                approve();
                            }
                        }, "-", {
                            text : 'Reject',
                            iconCls : 'icon-cancel',
                            handler : function() {
                                reject();
                            }
                        }, "-", {
                            text : 'Check',
                            iconCls : 'icon-search',
                            handler : function() {
                                check();
                            }
                        } , "-", {
                            text : 'DR Log',
                            iconCls : 'icon-list',
                            handler : function() {
                                viewDrLog();
                            }
                        } ],
                        onClickCell : function(rowIndex, field, value) {
                            $('#datagrid').datagrid('refreshRow', rowIndex);
                            var rows = $('#datagrid').datagrid('getRows');
                            beginEditing(rowIndex, field, value);
                            setEditing(rowIndex);
                        },
                        onSelect : function(rowIndex, rowDate) {
                            var state = rowDate.state;
                            if (state == "0") {
                                $('div.datagrid-toolbar a').eq(0).linkbutton(
                                        'enable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton(
                                        'enable');
                            } else if (state == "1") {
                                $('div.datagrid-toolbar a').eq(0).linkbutton(
                                        'disable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton(
                                        'enable');
                            } else if (state == "2") {
                                $('div.datagrid-toolbar a').eq(0).linkbutton(
                                        'disable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton(
                                        'disable');
                            }
                        },
                    });

}

var editIndex = undefined;
function beginEditing(rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
            editIndex = rowIndex;
        }
    } else {
        $('#datagrid').datagrid('endEdit', rowIndex);
        $('#datagrid').datagrid('beginEdit', rowIndex);
    }
}
function endEditing() {
    if (editIndex == undefined) {
        return true;
    }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
        $('#datagrid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function setEditing(rowIndex) {
}

function approve() {
    endEditing();
    var designRegDetailJson = [];
    if ($('#ec_state').val() != '1') {
        $.messager.alert('info', 'Please approve End Customer first!');
        return;
    }
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows == undefined || rows == '') {
        $.messager.alert('info', 'Please select the data item');
        return;
    }
    var msg = "";
    for (var i = 0; i < rows.length; i++) {
        // DR行项目的value大于$1M
        if (rows[i].value * 1 > 1000000) {
        	msg += "Rows" + (i * 1 + 1) + " Please confirm that Bookpart" + rows[i].material_id + " value has exceeded one million;<BR>";
        }
        // DR 行项目上的 Price 大于$10
        if (rows[i].price * 1 > 10) {
        	msg += "Rows" + (i * 1 + 1) + " Please confirm that Bookpart" + rows[i].material_id + " price has exceeded 10;<BR>";
        }
    }
    var url = undefined;
    url = "/designRegAction!updateDesignReg.jspa?state=1&isUpdate=0";
    $.messager.confirm('Confirm', msg + 'Confirmed about approve?', function(r) {
        if (r) {
            for (var i = 0; i < rows.length; i++) {
                var addCount = (rows[i].project_state * 1 + 1) * 6;
                var d = new Date();
                d.setMonth(d.getMonth() + addCount * 1);
                var endday = d.format('yyyy-MM-dd');
                rows[i].end_dateStr = endday;
                var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
                $("#datagrid").datagrid("refreshRow", rowIndex);

                var row = "{" + "id:" + "'" + rows[i].id + "'," + "row_no:"
                        + "'" + rowIndex + "'," + "material_name:" + "'"
                        + rows[i].material_name + "'," + "material_id:" + "'"
                        + rows[i].material_id + "'," + "drNum:" + "'"
                        + rows[i].drNum + "'," + "main_id:" + "'"
                        + rows[i].main_id + "'," + "price:" + "'"
                        + rows[i].price + "',equip_usage:'"
                        + rows[i].equip_usage + "',value:'" + rows[i].value
                        + "',project_state:'" + rows[i].project_state
                        + "',equip_type:'" + rows[i].equip_type
                        + "',start_date:'" + rows[i].start_dateStr
                        + "',end_date:'" + endday + "',state:'" + 1
                        + "',project_name:'" + rows[i].project_name
                        + "',cus_remark:'" + rows[i].cus_remark
                        + "',remark:'" + escape(escape(rows[i].remark)) 
                        + "',weencomments:'" + escape(escape(rows[i].weencomments)) 
                        + "'}";

                designRegDetailJson.push(row);
            }
            var rows1 = $("#datagrid").datagrid("getRows");
            if (rows1 != undefined && rows1.length != 0) {
                var today = new Date();
                var max = today.format('yyyy-MM-dd');
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].end_dateStr > max) {
                        max = rows[i].end_dateStr;
                    }
                }
                $('#end_date').val(max);
            }
            $('#designRegDetailJson').val("[" + designRegDetailJson + "]");
            var form = window.document.forms[0];
            form.action = appUrl + url;
            form.target = "hideFrame";
            form.submit();
        }
    });

}

function reject() {
    endEditing();
    var ids = [];
    var remarks = [];
    var weencomments_list=[];
    $.messager.confirm('Confirm', 'Confirmed about reject?', function(r) {
        if (r) {
            var rows = $('#datagrid').datagrid('getSelections');
            if (rows == undefined || rows == '') {
                $.messager.alert('info', 'Please select the data item!');
                return;
            }
            for (var i = 0; i < rows.length; i++) {
                var project_state = rows[i].project_state
                if (project_state == 2) {
                    $.messager.alert('Tips', 'DR already turn DW and not editable !', 'error');
                    return;
                }
            	var id = rows[i].id;
                var remark = escape(rows[i].remark);
                remarks.push(remark);
                
                var ween = escape(rows[i].weencomments);
                weencomments_list.push(ween);
                ids.push(id);
            }

            var form = window.document.forms[0];
            form.action = appUrl + '/designRegAction!auditDRD.jspa?ids=' + ids
                    + '&state=2&remarks=' + remarks+'&weencomments='+weencomments_list;
            form.target = "hideFrame";
            form.submit();
        }
    });

}

function promgtMsg() {
    var hideFrame = document.getElementById("hideFrame");
    var failResult = hideFrame.contentWindow.failResult;
    var successResult = hideFrame.contentWindow.successResult;
    if (failResult) {
        if (failResult == undefined || failResult == 'undefined') {
            return;
        }
        $.messager.alert('Tips', failResult, 'warning');
    } else if (successResult) {
        $.messager.alert('Tips', successResult, 'warning');
        $("#datagrid").datagrid('load');
    }
}

function check() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item', 'warning');
        return;
    } else {
        initMaintAccount(
                false,
                '800',
                '300',
                'Check if the same item is already registed by other customer or not',
                '/designRegAction!toCheck.jspa?id=' + rows[0].id
                        + '&customer_id='
                        + encodeURIComponent($('#customer_id').val())
                        + '&endCus_id='
                        + encodeURIComponent($('#endCus_id').val())
                        + '&endCus_groupId='
                        + encodeURIComponent($('#endCus_groupId').val())
                        + '&start_date='
                        + encodeURIComponent(rows[0].start_dateStr)
                        + '&end_date='
                        + encodeURIComponent(rows[0].end_dateStr)
                        + '&material_id='
                        + encodeURIComponent(rows[0].material_id), 100, 30);
    }
}

function search() {
    var queryParams = $('#datagrid').datagrid('options').queryParams;

    $("#datagrid").datagrid('load');
}

function closeMain() {
    $("#hiddenWin").window('close');
}
function closeMaintWindow() {
    $("#hiddenWin").window('close');
}

$('#currency_code').combobox({
    url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=548',
    valueField : 'itemValue',
    textField : 'itemName',
    multiple : false,
    editable : false,
    required : true,
    panelHeight : 120,
    width : 173,
    onSelect : function(r) {

    }
});

function initMaintAccount(ffit, widte, height, title, url, l, t) {
    var urls = appUrl + url;
    var WWidth = widte;
    var WHeight = height;
    var FFit = ffit;
    var $win = $("#hiddenWin")
            .window(
                    {
                        title : title,
                        width : WWidth,
                        height : WHeight,
                        content : '<iframe frameborder="no" width="100%" height="100%" src='
                                + urls + '/>',
                        shadow : true,
                        modal : true,
                        closed : true,
                        closable : true,
                        minimizable : false,
                        maximizable : false,
                        collapsible : false,
                        fit : FFit,
                        draggable : true,
                        left : l,
                        top : t
                    });

    $win.window('open');
}
document.onkeydown = function(e) {
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
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

function viewDrLog(){
	var rows = $('#datagrid').datagrid('getSelections');
  	if (rows.length == 0) {
		$.messager.alert('Tips', 'Please select the data item !', 'warning');
		return;
	} else if (rows.length > 1) {
        $.messager.alert('Tips', 'Multiselect are not supported !', 'warning');
        return;
    } else {
		initMaintAccount(false,'1150','350','DR Log','/designRegAction!toSearchDrLog.jspa?id='+rows[0].id, 10, 10);
	}
}
