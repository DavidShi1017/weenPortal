$(document).ready(function() {
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);
});

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
                            states : $('#state').val()
                        },
                        loadMsg : 'Loading...',
                        singleSelect : true,
                        nowrap : true,
                        pagination : true,
                        rownumbers : true,
                        fitColumns : false,
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
                                    field : 'state',
                                    title : 'Status',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.state;
                                        if (flag == '9') {
                                            return "<font color='red'>Deleted</font>";
                                        } else if (flag == '0') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '1') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '2') {
                                            return "<font color='gray'>Rejected</font>";
                                        } else if (flag == '3') {
                                            return "<font color='red'>Expired</font>";
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
                                    width : 250,
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
                                }, ] ],
                        columns : [ [
                                {
                                    field : 'ec_contact',
                                    title : 'EC Contact',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'sale_office',
                                    title : 'Region',
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
                                },
                                {
                                    field : 'dr_typeStr',
                                    title : 'DR Type',
                                    width : 150,
                                    align : 'center',
                                },
                                {
                                    field : 'drtype_def',
                                    title : 'DR Type Definition',
                                    width : 250,
                                    align : 'center',
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
                                    title : 'EC tel number',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'cus_remark',
                                    title : 'Disti Remarks',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'remark',
                                    title : 'Internal Comment',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'weencomments',
                                    title : 'Ween Comment',
                                    width : 100,
                                    align : 'center',
                                }, {
                                    field : 'isCheck',
                                    title : 'isCheck',
                                    width : 200,
                                    align : 'center',
                                    hidden : true
                                } ] ],
                        toolbar : [ "-", {
                            text : 'View',
                            iconCls : 'icon-view',
                            handler : function() {
                                view();
                            }
                        }, "-", {
                            text : 'Outport',
                            iconCls : 'icon-outport',
                            handler : function() {
                                downLoad();
                            }
                        } ],
                        onDblClickRow : function(rowIndex, rowData) {
                            initMaintAccount(true, '1000', '550', '',
                                    '/designRegAction!toViewApproveDR.jspa?id='
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
    queryParams.states = $('#state').val();
    $("#datagrid").datagrid('load');
}

function downLoad() {
    $.messager.confirm('Confirm', 'Confirmed about  OutPort?', function(r) {
        if (r) {
            var form = window.document.forms[0];
            form.action = appUrl + '/designRegAction!downloadExcelData.jspa';
            form.target = "hideFrame";
            form.submit();
        }
    });
}

function view() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item��', 'warning');
        return;
    } else {
        initMaintAccount(true, '1000', '550', '',
                '/designRegAction!toViewApproveDR.jspa?id=' + rows[0].main_id);
    }
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