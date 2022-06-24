$(document).ready(function() {
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);
});
if ($('#forWho').val() == "Disti") {
    $('#cus_groupId').attr('disabled', 'disabled');
}
$('#clearV').click(function() {
    $('#start_dateStr').datebox('setValue', "");
    $('#end_dateStr').datebox('setValue', "");
});

var drTypes    = [ {
    label : 'DR-1: New Product * New Customer',
    value : 1
}, {
    label : 'DR-2: New Product * Existing Customer',
    value : 2
}, {
    label : 'DR-3: Existing Product * New Customer',
    value : 3
}, {
    label : 'DR-4: Existing Product * Existing Customer',
    value : 4
} ];

function loadGrid() {
    $('#datagrid')
            .datagrid(
                    {
                        iconCls : 'icon-list',
                        title : '',
                        fit : true,
                        striped : true,
                        url : appUrl
                                + '/designReg/designRegAction!checkDesignRegDetailList.jspa',
                        queryParams : {
                            states : $('#state').val(),
                            saler_design_statusStr : $(
                                    '#saler_design_statusStr').val(),
                            myself : 'business_approve',
                        },
                        loadMsg : 'Loading...',
                        singleSelect : true,
                        nowrap : true,
                        checkbox : true,
                        pagination : true,
                        rownumbers : true,
                        fitColumns : false,
                        required : true,
                        striped : true,
                        onLoadSuccess : function() {
                        	var forWho = $('#forWho').val();
                            if (forWho == "Disti") {
                                $('div.datagrid-toolbar a').eq(2).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(2).show();
                            }
                        },
                        frozenColumns : [ [
                                {
                                    field : 'ck',
                                    align : 'center',
                                    checkbox : true
                                },
                                {
                                    field : 'id',
                                    title : 'ID',
                                    width : 60,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'main_id',
                                    title : 'main_id',
                                    width : 150,
                                    align : 'center',
                                    hidden : true
                                },

                                {
                                    field : 'saler_design_status',
                                    title : 'DW status',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = value;
                                        if (flag == 0) {
                                            return "pending";
                                        } else if (flag == 1) {
                                            return "<font color='green'>Regional Sales approved</font>";
                                        } else if (flag == 2) {
                                            return "<font color='blue'>Sales head approved</font>";
                                        } else if (flag == 3) {
                                            return "<font color='blue'>Sales head reject</font>";
                                        } else if (flag == 4) {
                                            return "<font color='blue'>Final approved</font>";
                                        } else if (flag == 5) {
                                            return "<font color='blue'>Final reject</font>";
                                        } else if (flag == 6) {
                                            return "<font color='blue'>Finance approved</font>";
                                        } else if (flag == 7) {
                                            return "<font color='blue'>Finance reject</font>";
                                        } else if (flag == 9) {
                                            return "<font color='blue'>Regional sales reject</font>";
                                        }
                                    },
                                }, {
                                    field : 'drNum',
                                    title : 'DRNum',
                                    width : 80,
                                    align : 'center',
                                }, {
                                    field : 'customer_id',
                                    title : 'Customer',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                }, {
                                    field : 'cus_groupId',
                                    title : 'Disti',
                                    width : 150,
                                    align : 'left',
                                }, {
                                    title : "Disti Branch",
                                    field : 'disti_branch',
                                    width : 150,
                                    align : 'left'
                                }, {
                                    field : 'endCus_groupId',
                                    title : 'EC Group',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                }, {
                                    field : 'endCus_groupName',
                                    title : 'EC Group',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'endCus_id',
                                    title : 'End Customer',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                }, {
                                    field : 'endCus_name',
                                    title : 'End Customer',
                                    width : 200,
                                    align : 'left',
                                }, 
                                {
                                    field : 'dr_type',
                                    title : 'DR Type',
                                    width : 240,
                                    align : 'center',
                                    editor : {
                                            type : 'combobox',
                                            options : {
                                                valueField : 'value',
                                                textField : 'label',
                                                panelHeight : 'auto',
                                                editable : false,
                                                data : drTypes,
                                                onSelect : function(rec) {
                                                }
                                            }
                                    },
                                    formatter : function(value, row, rec) {
                                            var flag = row.dr_type;
                                            if (flag == 1) {
                                                return "DR-1: New Product * New Customer";
                                            } else if (flag == 2) {
                                                return "DR-2: New Product * Existing Customer";
                                            } else if (flag == 3) {
                                                return "DR-3: Existing Product * New Customer";
                                            } else if (flag == 4) {
                                                return "DR-4: Existing Product * Existing Customer";
                                            }
                                    },
                                    hidden : false
                                },] ],
                        columns : [ [
                        	    {
								    field : 'remark',
								    title : 'Internal Comment',
								    width : 250,
								    align : 'center',
								    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'
                                            || flag == undefined) {
                                            return "";
                                        } else {
                                            return $.trim(flag);
                                        }
                                    },
							    },{
								    field : 'weencomments',
								    title : 'Ween Comment',
								    width : 250,
								    align : 'center',
								    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.weencomments;
                                        if (flag == 'undefined'
                                            || flag == undefined) {
                                            return "";
                                        } else {
                                            return $.trim(flag);
                                        }
                                    },
							    },
                                {
                                    field : 'ec_contact',
                                    title : 'EC Contact',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'material_id',
                                    title : '12NC',
                                    width : 120,
                                    align : 'left',
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
                                    field : 'price',
                                    title : 'Price',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'usage_amount',
                                    title : 'Annual runrate',
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
                                    field : 'value',
                                    title : 'Value',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.value;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'equip_type',
                                    title : 'Application',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.equip_type;
                                        if (flag != undefined
                                                && flag.indexOf("-") > -1) {
                                            return flag.replace('-', '');
                                        } else {
                                            return flag;
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
                                    hidden : true
                                },
                                {
                                    field : 'project_name',
                                    title : 'Project',
                                    width : 150,
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
                                            return "<font color='green'>Design_in</font>";
                                        } else if (flag == 2) {
                                            return "<font color='blue'>Design_win</font>";
                                        } else if (flag == 9) {
                                            return "<font color='red'>DW_Reject</font>";
                                        }
                                    }
                                }, {
                                    field : 'tel',
                                    title : 'EC Tel Number',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'cus_remark',
                                    title : 'Disti Remark',
                                    width : 100,
                                    align : 'center',
                                }, {
									field : 'inputRemark',
									title : 'Remark',
									width : 50,
									align : 'center',
                                    hidden : true
								}, {
                                    field : 'isCheck',
                                    title : 'isCheck',
                                    width : 20,
                                    align : 'center',
                                    hidden : true
                                }, {
                                    field : 'create_userName',
                                    title : 'Creator',
                                    width : 200,
                                    align : 'center',
                                }, {
                                    field : 'create_time',
                                    title : 'Create Date',
                                    width : 200,
                                    align : 'center',
                                    formatter : function(date) {
                                        if (date == undefined) {
                                            return "";
                                        } else {
                                            return utcToDate(date);
                                        }
                                    }
                                }, {
                                    field : 'design_win',
                                    title : 'Design win',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(date) {
                                        if (date == undefined) {
                                            return "";
                                        } else {
                                            return utcToDate(date);
                                        }
                                    }
                                }, {
                                    field : 'audit_person',
                                    title : 'DW submitter',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_time',
                                    title : 'DW submitted date',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_person2',
                                    title : 'DW Approval-1(Sales head)',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_time2',
                                    title : 'DW Approval-1(Sales head) date',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_person3',
                                    title : 'DW Approval-2(MKT)',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_time3',
                                    title : 'DW Approval-2(MKT) date',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_person4',
                                    title : 'DW Approval-3(Finance)',
                                    width : 70,
                                    align : 'center',

                                }, {
                                    field : 'audit_time4',
                                    title : 'DW Approval-3(Finance) date',
                                    width : 70,
                                    align : 'center',

                                } ] ],
                        toolbar : [ "-", {
                            text : 'Approve',
                            iconCls : 'icon-ok',
                            handler : function() {
                                approve(4);
                            }
                        }, "-", {
                            text : 'Reject',
                            iconCls : 'icon-cancel',
                            handler : function() {
                                approve(5);
                            }
                        } ,  "-", {
                            text : 'Change DR Type',
                            iconCls : 'icon-edit',
                            handler : function() {
                                changeDRType();
                            }
                        } , "-", {
                            text : 'DR Log',
                            iconCls : 'icon-list',
                            handler : function() {
                                viewDrLog();
                            }
                        }],
                        onDblClickRow : function(rowIndex, rowData) {
                            initMaintAccount(true, '1000', '550',
                                    'Detail Information',
                                    '/designRegAction!toViewDesignReg.jspa?id='
                                            + rowData.main_id);
                        },
                        rowStyler : function(index, row) {
                            var rows = $('#datagrid').datagrid('getRows');
                            var drNum = rows[0].drNum;
                            var flag = 0;
                            for (var i = 0; i < rows.length; i++) {
                                if (rows[i].drNum != drNum) {
                                    if (flag == 0) {
                                        flag = 1;
                                    } else if (flag == 1) {
                                        flag = 0;
                                    }
                                }
                                rows[i].flag = flag;
                                drNum = rows[i].drNum;
                            }
                            if (row.flag == 1) {
                                return 'background:#ffffcc;';
                            }
                        },
                        onClickCell : function(rowIndex, field, value) {
                            var rows = $('#datagrid').datagrid('getRows');
                            var flag = rows[rowIndex].saler_design_status;
                            if (flag == 2) {
                                $('div.datagrid-toolbar a').eq(0).linkbutton(
                                        'enable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton(
                                        'enable');
                            } else {
                                $('div.datagrid-toolbar a').eq(0).linkbutton(
                                        'disable');
                                $('div.datagrid-toolbar a').eq(1).linkbutton(
                                        'disable');
                            }

                            beginEditing(rowIndex, field, value);
                            setEditing(rowIndex);
                            dateEditIndex = rowIndex;
                            $('#datagrid').datagrid('beginEdit', rowIndex);
                            var editors = $('#datagrid').datagrid('getEditors',
                                    rowIndex);
                            if (field == "remark"
                                && rows[rowIndex].remark != undefined
                                && $.trim(rows[rowIndex].remark) != "") {
                            　　　　　　　　　　　　　　　　　　var str = rows[rowIndex].remark;
                            　　　　　　　　　　　　　　　　　　$.messager.alert('Remark', str, '');
                        　　　　　　　　　　　　　　　　}
                        }
                    });

}

function setEditing(rowIndex) {
	var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var rows = $('#datagrid').datagrid('getRows');
    var id = rows[rowIndex].id;
    var remarkEditor = editors[1];
    remarkEditor.target.bind({
        'click' : function() {
            initMaintAccount(false, '400', '300', 'Remark',
                    '/designRegAction!toRemarkTxt.jspa?remark='
                            + encodeURIComponent(remarkEditor.target.val())
                            + '&rowIndex=' + rowIndex
                            + '&id=' + id, 150, 150);

        }
    });
}

function setRemark(remark, rowIndex) {
	var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var remarkEditor = editors[1];
    var str = $(remarkEditor.target).val() + remark;
    $(remarkEditor.target).val(str);
    var rows = $('#datagrid').datagrid('getRows');
    rows[rowIndex].inputRemark = remark;
}

var editIndex = undefined;
function beginEditing(rowIndex, field, value) {
    if (rowIndex != editIndex) {
        if (endEditing()) {
            $('#datagrid').datagrid('beginEdit', rowIndex);
            editIndex = rowIndex;
        } else {
            $('#datagrid').datagrid('selectRow', editIndex);
        }
    }
}
function endEditing() {
    if (editIndex == undefined) {
        return true;
    }
    if ($('#datagrid').datagrid('validateRow', editIndex)) {
        $('#datagrid').datagrid('endEdit', editIndex);
        $('#datagrid').datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function approve(mark) {
	endEditing();
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows == undefined || rows == '') {
        $.messager.alert('info', 'Please select the data item');
        return;
    }
    var ids = "";
    var mark_status = 0;
    var remarks = "";
    var drTypes = "";
    var weens="";
    for (var i = 0; i < rows.length; i++) {
        ids = rows[i].id + "," + ids;
        if (rows[i].saler_design_status == 2) {
            mark_status++;
        }
        
        var remark = escape(rows[i].inputRemark);
        if (mark == '5') {
        	if (remark == null || remark == undefined || remark == 'undefined' || remark == '') {
                $.messager.alert('info', 'Please input remark');
                return;
            }
        }
        remarks = rows[i].remark + "," + remarks;
        
        drTypes = rows[i].dr_type + "," + drTypes;
        
        var ween = escape(rows[i].weencomments);
        weens = ween + "," + weens;

    }
    if (mark_status != rows.length) {
        $.messager.alert('info',
                'Only when all leader approve status can be clicked!');
        return;
    }
    $.messager
            .confirm(
                    'Confirm',
                    'Confirmed about operate?',
                    function(r) {
                        if (r) {
                            $
                                    .ajax({
                                        url : appUrl
                                                + "/designRegAction!updateSalerDesignWinStatus.jspa",
                                        data : {
                                            ids : ids,
                                            remarks : remarks,
                                            dr_types : drTypes, 
                                            state : mark,
                                            weencomments:weens
                                        },
                                        type : "POST",
                                        contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
                                        success : function(ss) {
                                            var msg = ss.result + "";
                                            if (msg == undefined || msg == null
                                                    || msg == '') {
                                                $.messager.alert('Tips',
                                                        "Failed!", 'warning');
                                            } else {
                                                if (msg.lastIndexOf("Failed") > -1) {
                                                    $.messager
                                                            .alert('Tips',
                                                                    "Failed",
                                                                    'warning');
                                                } else {
                                                    $.messager
                                                            .alert(
                                                                    'Tips',
                                                                    "Success!",
                                                                    'info',
                                                                    function() {
                                                                        $(
                                                                                "#datagrid")
                                                                                .datagrid(
                                                                                        'load');
                                                                    });
                                                }
                                            }
                                        },
                                        error : function(r) {
                                            $.messager.progress('close');
                                            $.messager
                                                    .alert(
                                                            'Tips',
                                                            "create rebate order data Failure",
                                                            'info');
                                        }
                                    });
                        }
                    });

}


function changeDRType() {
    
	endEditing();
	var url = undefined;
    url = "/designRegAction!updateDrType.jspa";
    
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows == undefined || rows == '') {
        $.messager.alert('info', 'Please select the data item');
        return;
    }
    else if (rows.length > 1) {
        $.messager.alert('Tips', 'Multiselect are not supported !', 'warning');
        return;
    } 
    var id = "";
    var dr_Type;
    for (var i = 0; i < rows.length; i++) {
        id = rows[i].id;
        dr_Type = rows[i].dr_type;
    }

    $.messager
            .confirm(
                    'Confirm',
                    'Confirmed about operate?',
                    function(r) {
                        if (r) {
                            $
                                    .ajax({
                                        url : appUrl
                                                + "/designRegAction!updateDrType.jspa",
                                        data : {
                                            ids : id,
                                            drType : dr_Type
                                        },
                                        type : "POST",
                                        contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
                                        success : function(ss) {
                                            var msg = ss.result + "";
                                            if (msg == undefined || msg == null
                                                    || msg == '') {
                                                $.messager.alert('Tips',
                                                        "Failed!", 'warning');
                                            } else {
                                                if (msg.lastIndexOf("Failed") > -1) {
                                                    $.messager
                                                            .alert('Tips',
                                                                    "Failed",
                                                                    'warning');
                                                } else {
                                                    $.messager
                                                            .alert(
                                                                    'Tips',
                                                                    "Success!",
                                                                    'info',
                                                                    function() {
                                                                        $(
                                                                                "#datagrid")
                                                                                .datagrid(
                                                                                        'load');
                                                                    });
                                                }
                                            }
                                        },
                                        error : function(r) {
                                            $.messager.progress('close');
                                            $.messager
                                                    .alert(
                                                            'Tips',
                                                            "change dr type Failure",
                                                            'info');
                                        }
                                    });
                        }
                    });
}

function search() {
    var queryParams = $('#datagrid').datagrid('options').queryParams;
    queryParams.drNum = $("#drNum").val();
    queryParams.project_name = encodeURIComponent($("#project_name").val());
    queryParams.disti_branch = $("#disti_branch").val();
    queryParams.cus_groupId = $("#cus_groupId").val();
    queryParams.endCustomer_name = $("#endCus_name").val();
    queryParams.create_userName = $('#create_userName').val();
    queryParams.states = $('#state').val();
    queryParams.material_name = $("#material_name").val();
    queryParams.equip_type = $("#equip_type").val();
    queryParams.start_dateStr = $("#start_dateStr").datebox('getValue');
    queryParams.end_dateStr = $("#end_dateStr").datebox('getValue');
    queryParams.saler_design_statusStr = $("#saler_design_statusStr").val();
    queryParams.myself = 'business_approve', $("#datagrid").datagrid('load');
}

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

function closeMaintWindow() {
    $("#hiddenWin").window('close');
}

function promgtMsg() {
    var hideFrame = document.getElementById("hideFrame");
    var failResult = hideFrame.contentWindow.failResult;
    var successResult = hideFrame.contentWindow.successResult;
    if (failResult != "") {
        if (failResult == undefined || failResult == 'undefined') {
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

function utcToDate(utcCurrTime) {
    utcCurrTime = utcCurrTime + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 1;
    month["Feb"] = 2;
    month["Mar"] = 3;
    month["Apr"] = 4;
    month["May"] = 5;
    month["Jun"] = 6;
    month["Jul"] = 7;
    month["Aug"] = 8;
    month["Sep"] = 9;
    month["Oct"] = 10;
    month["Nov"] = 11;
    month["Dec"] = 12;
    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2];
    return date;
}

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