$(document).ready(function() {
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);
});
if ($('#forWho').val() == "Disti") {
    $('#cus_groupId').attr('disabled', 'disabled');
}
function loadGrid() {
    $('#datagrid').datagrid(
            {
                iconCls : 'icon-list',
                title : '',
                fit : true,
                striped : true,
                url : appUrl
                        + '/designReg/designRegAction!getDesignRegList.jspa',
                queryParams : {},
                loadMsg : 'Loading...',
                singleSelect : true,
                nowrap : true,
                pagination : true,
                rownumbers : true,
                fitColumns : false,
                frozenColumns : [ [ {
                    field : 'ck',
                    align : 'center',
                    checkbox : true
                }, {
                    field : 'id',
                    title : 'ID',
                    width : 60,
                    align : 'center',
                    hidden : true
                }, {
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
                    title : 'DR number',
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
                    hidden : true
                }, {
                    title : "Disti Branch",
                    field : 'disti_branch',
                    width : 250,
                    align : 'left'
                }, {
                    field : 'endCus_groupId',
                    title : 'EC Group',
                    width : 80,
                    align : 'center',
                    hidden : true
                }, {
                    field : 'endCus_groupName',
                    title : 'EC Group',
                    width : 100,
                    align : 'center',
                    hidden : true
                }, {
                    field : 'endCus_id',
                    title : 'End Customer',
                    width : 200,
                    align : 'center',
                    hidden : true
                }, {
                    field : 'endCus_name',
                    title : 'End Customer',
                    width : 200,
                    align : 'left',
                } ] ],
                columns : [ [ {
                    field : 'ec_contact',
                    title : 'EC Contact',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'sale_office',
                    title : 'Region',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'start_dateStr',
                    title : 'Registration Date',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'end_dateStr',
                    title : 'Expiry Date',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'mp_scheduleStr',
                    title : 'MP Schedule',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'project_name',
                    title : 'Project',
                    width : 150,
                    align : 'left',
                }, {
                    field : 'primaryProState',
                    title : 'Project Status',
                    width : 100,
                    hidden : true,
                    align : 'center',
                    formatter : function(value, row, rec) {
                        var flag = row.primaryProState;
                        if (flag == 0) {
                            return "Opportunity";
                        } else if (flag == 1) {
                            return "<font color='green'>Design_in</font>";
                        } else if (flag == 2) {
                            return "<font color='blue'>Design_win</font>";
                        }
                    }
                }, {
                    field : 'usage_amount',
                    title : 'Annual runrate',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'tel',
                    title : 'EC Tel Number',
                    width : 100,
                    align : 'center',
                }, {
                    field : 'equip_type',
                    title : 'Application',
                    width : 100,
                    align : 'center',
                    formatter : function(value, row, rec) {
                        var flag = row.equip_type;
                        if (flag != undefined && flag.indexOf("-") > -1) {
                            return flag.replace('-', '');
                        } else {
                            return flag;
                        }
                    }
                }
               // ,{
                //    field : 'weencomments',
                //    title : 'Ween Comments',
               //     width : 100,
               //     align : 'center'
              //  }
                , {
                    field : 'create_userName',
                    title : 'Creator',
                    width : 200,
                    align : 'center',
                } ] ],
                toolbar : [ "-", {
                    text : 'Add',
                    iconCls : 'icon-add',
                    handler : function() {
                        add();
                    }
                }, "-", {
                    text : 'Edit',
                    iconCls : 'icon-edit',
                    handler : function() {
                        edit();
                    }
                }
                , "-", {
                    text : 'View',
                    iconCls : 'icon-view',
                    handler : function() {
                        check();
                    }
                }, "-", {
                    text : 'Copy',
                    iconCls : 'icon-copy',
                    handler : function() {
                        copy();
                    }
                } ],
                onDblClickRow : function(rowIndex, rowData) {
                    initMaintAccount(true, '1000', '550', 'Detail Information',
                            '/designRegAction!toViewDesignReg.jspa?id='
                                    + rowData.id);
                },
                onClickCell : function(rowIndex, field, value) {
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
    $("#datagrid").datagrid('load');
}

function add() {
    initMaintAccount(true, '1000', '550', 'Create DesignReg',
            '/designRegAction!toCreateDesignReg.jspa', 0, 0);
}

function copy() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item!', 'warning');
        return;
    } else {
        initMaintAccount(true, '1000', '550', 'Copy DesignReg',
                '/designRegAction!toCopyCreate.jspa?id=' + rows[0].id, 0, 0);
    }
}

function edit() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item!', 'warning');
        return;
    } else {
        initMaintAccount(true, '1000', '550', 'Edit DesignReg',
                '/designRegAction!toUpdateDesignReg.jspa?id=' + rows[0].id, 0,
                0);
    }
}

function check() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item!', 'warning');
        return;
    } else {
        initMaintAccount(true, '1000', '550', 'Detail Information',
                '/designRegAction!toViewDesignReg.jspa?id=' + rows[0].id);
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
