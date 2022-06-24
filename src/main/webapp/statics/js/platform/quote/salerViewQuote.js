var loginRole;
var canEdit = false;
var aFlag;
var rFlag;
var eFlag;
var approver;
var userId;
$(document).ready(function() {
    if ($('#ec_state').val() == '0') {
        $('#EC_state1').html('<font color="red">(Pending)</font>');
    }
    if ($('#pc_state').val() == '0') {
        $('#PC_state1').html('<font color="red">(Pending)</font>');
    }
    if ($('#ec_state').val() == '2') {
        $('#EC_state1').html('<font color="red">(Reject)</font>');
    }
    if ($('#pc_state').val() == '2') {
        $('#PC_state1').html('<font color="red">(Reject)</font>');
    }
    if ($('#fileName').html() == "") {
        $('#downImg').hide();
    } else {
        $('#downImg').show();
    }
    loadGrid();
    $('#hideFrame').bind('load', promgtMsg);

});
function downLoadFile() {
    if ($('#file_path').val() == "") {
        return;
    }
    var form = window.document.forms[0];
    form.action = appUrl + "/file/fileAction!downLoadFile.jspa?file_path="
            + $('#file_path').val();
    form.target = "hideFrame";
    form.submit();
}
var remarkTxt = "";
function cancel() {
    window.parent.closeMaintWindow();
}
function back() {
    window.parent.search();
    window.parent.closeMaintWindow();
}
$('.panel-tool-close').mousedown(function() {
    window.parent.closeMaintWindow();
});

function loadGrid() {
    approver = $('#approver').val();
    userId = $('#auditorId').val();
    if (approver == '1') {
        eFlag = 1;
        aFlag = 3;
        rFlag = 6;
    } else if (approver == '2') {
        eFlag = 2;
        aFlag = 4;
        rFlag = 7;
    } else if (approver == '3') {
        aFlag = 5;
        rFlag = 8;
    } else if (approver == '9') {
        eFlag = 1;
        aFlag = 3;
        rFlag = 6;
    }
    $('#datagrid')
            .datagrid(
                    {
                        iconCls : 'icon-list',
                        title : '',
                        url : appUrl
                                + '/quoteAction!getAuditQuoteListNoPage.jspa?quote_id='
                                + $('#quote_id').val() + "&approver="
                                + approver + "&userId=" + userId,
                        loadMsg : 'Loading...',
                        singleSelect : false,
                        nowrap : true,
                        pagination : false,
                        checkbox : true,
                        required : true,
                        rownumbers : true,
                        height : 300,
                        fitColumns : false,
                        striped : true,
                        columns : [ [
                                {
                                    field : 'ck',
                                    align : 'center',
                                    checkbox : true
                                },
                                {
                                    field : 'id',
                                    title : '主键',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'quote_id',
                                    title : 'QuoteId',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    title : "Agreement",
                                    field : 'isAgree',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.isAgree;
                                        if (flag == '0') {
                                            return "";
                                        } else if (flag == '1') {
                                            return "<font color='green'>Agree</font>";
                                        } else if (flag == '2') {
                                            return "<font color='gray'>Expired</font>";
                                        }
                                    },
                                    hidden : true,
                                },
                                {
                                    title : "Status",
                                    field : 'state',
                                    width : 80,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.state;
                                        if (flag == '9') {
                                            return "<font color='red'>Deleted</font>";
                                        } else if (flag == '0') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '1') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '2') {
                                            return "<font color='black'>Pending</font>";
                                        } else if (flag == '3') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '4') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '5') {
                                            return "<font color='green'>Approved</font>";
                                        } else if (flag == '6') {
                                            return "<font color='red'>Reject</font>";
                                        } else if (flag == '7') {
                                            return "<font color='red'>Reject</font>";
                                        } else if (flag == '8') {
                                            return "<font color='red'>Reject</font>";
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'debit_num',
                                    title : 'Debit Num',
                                    width : 120,
                                    align : 'center',
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
                                    },
                                    hidden : true
                                },
                                {
                                    field : 'material_name',
                                    title : 'BookPart',
                                    width : 150,
                                    align : 'left',
                                },
                                {
                                    field : 'isDRItem',
                                    title : 'DR Indicator',
                                    width : 80,
                                    align : 'center',
                                },
                                {
                                    field : 'drNum',
                                    title : 'DR Num',
                                    width : 70,
                                    align : 'center',
                                },

                                {
                                    field : 'qty',
                                    title : 'QTY',
                                    width : 85,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.qty;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return formatNumber(flag * 1, 0, 1);
                                        }
                                    }
                                },
                                {
                                    field : 'res_qty',
                                    title : 'Remain QTY',
                                    width : 85,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.res_qty;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            return formatNumber(flag * 1, 0, 1);
                                        }
                                    }
                                },
                                {
                                    field : 'target_cost',
                                    title : 'Target Cost',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_cost;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'target_resale',
                                    title : 'Target Resale',
                                    width : 95,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_resale;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'target_amount',
                                    title : 'Target Amount',
                                    width : 105,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        return formatNumber(value * 1, 0, 1);
                                    }
                                },
                                {
                                    field : 'target_margin',
                                    title : 'Target Margin',
                                    width : 95,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.target_margin;
                                        if (flag == '' || flag == undefined) {
                                            return "";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            flag = flag.toFixed(2) + "%";
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'sale_price',
                                    title : 'Regional Min',
                                    width : 95,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.sale_price;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : !(approver == '1')
                                },
                                {
                                    field : 'stop_price',
                                    title : 'CMM',
                                    width : 70,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.stop_price;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : !(approver == '2') && !(approver == '9')
                                },
                                {
                                    field : 'qm_price',
                                    title : 'Quote Min',
                                    width : 70,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.qm_price;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                    hidden : true
                                },
                                {
                                    field : 'pbMpp',
                                    title : 'PB/MPP',
                                    width : 70,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.pbMpp;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return "<font color='red'>No Price</font>";
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);

                                        }
                                    },
                                },
                                {
                                    title : "Price Region",
                                    field : 'price_region',
                                    width : 85,
                                    align : 'center'
                                },
                                {
                                    field : 'suggest_cost',
                                    title : 'Final Quoted Cost',
                                    width : 125,
                                    align : 'center',
                                    editor : {
                                        type : 'numberbox',
                                        options : {
                                            precision : 4
                                        }
                                    },
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_cost;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'suggest_resale',
                                    title : 'Final Quoted Resale',
                                    width : 140,
                                    align : 'center',
                                    editor : {
                                        type : 'numberbox',
                                        options : {
                                            precision : 4
                                        }
                                    },
                                    formatter : function(value, row, rec) {
                                        var flag = row.suggest_resale;
                                        if (flag == 0 || flag == ''
                                                || flag == undefined) {
                                            return flag;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(4);
                                        }
                                    }
                                },
                                {
                                    field : 'cus_profits_percent',
                                    title : 'Final Quoted Disti Margin(%)',
                                    width : 195,
                                    align : 'center',
                                    editor : {
                                        type : 'numberbox',
                                        options : {
                                            precision : 2,
                                            disabled : true
                                        }
                                    },
                                    formatter : function(value, row, rec) {
                                        if (value == undefined
                                                || value == "undefined")
                                            return "";
                                        else
                                            return value + "%";
                                    }
                                },
                                {
                                    field : 'profits_percent',
                                    title : 'Mfr Margin(%)',
                                    width : 90,
                                    align : 'center',
                                    editor : {
                                        type : 'numberbox',
                                        options : {
                                            precision : 2,
                                            disabled : true
                                        }
                                    },
                                    formatter : function(value, row, rec) {
                                        var flag = row.profits_percent;
                                        if (flag == '' || flag == undefined
                                                || flag == 'undefined') {
                                            return 0;
                                        } else {
                                            flag = JSON.parse(flag * 1);
                                            return flag.toFixed(2);
                                        }
                                    },
                                    hidden : (approver == '1')
                                },
                                {
                                    field : 'amount',
                                    title : 'Final Quoted Amount',
                                    width : 150,
                                    align : 'center',
                                    editor : {
                                        type : 'numberbox',
                                        options : {
                                            precision : 4,
                                            disabled : true
                                        }
                                    },
                                    formatter : function(value, row, rec) {
                                        return formatNumber(value * 1, 0, 1);
                                    }
                                },
                                {
                                    field : 'remark',
                                    title : 'Internal Comments',
                                    width : 100,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else if (flag.length >= 30) {
                                            flag = flag.substring(0, 24);
                                            flag += "...";
                                            return flag;
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'cost',
                                    title : 'Cost',
                                    width : 70,
                                    align : 'center',
                                    hidden : (approver == '1')
                                },
                                {
                                    field : 'reason',
                                    title : 'Justification',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.reason;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'competitor',
                                    title : 'Competitor',
                                    width : 90,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.competitor;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            return flag;
                                        }
                                    }
                                },
                                {
                                    field : 'product_dateStr',
                                    title : 'Start Production',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'start_dateStr',
                                    title : 'Start Date',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'end_dateStr',
                                    title : 'Expire Date',
                                    width : 100,
                                    align : 'center',
                                },
                                {
                                    field : 'cus_remark',
                                    title : 'Comments',
                                    width : 100,
                                    align : 'center',
                                    editor : 'text',
                                    formatter : function(value, row, rec) {
                                        var flag = row.cus_remark;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else if (flag.length >= 30) {
                                            flag = flag.substring(0, 24);
                                            flag += "...";
                                            return flag;
                                        } else {
                                            return flag;
                                        }
                                    },
                                },
                                {
                                    field : 'latest_time',
                                    title : 'Update Time',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(date) {
                                        if (date == undefined) {
                                            return "";
                                        } else {
                                            return utcToDate(date);
                                        }
                                    }
                                },
                                {
                                    field : 'create_userId',
                                    title : 'Creater',
                                    width : 100,
                                    align : 'center',
                                    hidden : true
                                },
                                {
                                    field : 'data_from',
                                    title : 'Data From',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.data_from;
                                        if (flag == '2') {
                                            return "<font color='blue'>EDI</font>";
                                        } else {
                                            return "<font color='black'>NonEDI</font>";
                                        }
                                    },
                                },
                                {
                                    field : 'debit_start',
                                    title : 'Debit Start',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(date) {
                                        if (date == undefined) {
                                            return "";
                                        } else {
                                            return utcToDate(date);
                                        }
                                    }
                                },
                                {
                                    field : 'debit_end',
                                    title : 'Debit End',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(date) {
                                        if (date == undefined) {
                                            return "";
                                        } else {
                                            return utcToDate(date);
                                        }
                                    }
                                },
                                {
                                    field : 'Represent',
                                    title : 'Represent',
                                    width : 100,
                                    align : 'center',
                                    formatter : function(value, row, rec) {
                                        var flag = row.isRepresent;
                                        if (flag == 'undefined'
                                                || flag == undefined) {
                                            return "";
                                        } else {
                                            if (flag == 'Represent'
                                                    || flag == 'represent')
                                                return "Y";
                                            else
                                                return flag;
                                        }
                                    }
                                }, {
                                    field : 'forward_id',
                                    title : 'forward_id',
                                    width : 50,
                                    align : 'center',

                                } ] ],
                        toolbar : [ "-", {
                            text : 'Check',
                            iconCls : 'icon-search',
                            handler : function() {
                                check();
                            }
                        }, "-", {
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
                            text : 'Escalation',
                            iconCls : 'icon-redo',
                            handler : function() {
                                escalation();
                            }
                        }, "-", {
                            text : 'QuoteLog',
                            iconCls : 'icon-list',
                            handler : function() {
                                quoteLog();
                            }
                        }, "-", {
                            text : 'Copy Price',
                            iconCls : 'icon-copy',
                            handler : function() {
                                copy();
                            }
                        }, "-", {
                            text : 'Reset',
                            iconCls : 'icon-redo',
                            handler : function() {
                                reset();
                            }
                        }, "-", {
                            text : 'Forward',
                            iconCls : 'icon-group',
                            handler : function() {
                                forward();
                            }
                        } ],
                        onClickCell : function(rowIndex, field, value) {
                            $('#datagrid').datagrid('refreshRow', rowIndex);
                            var rows = $('#datagrid').datagrid('getRows');
                            if (field == "cus_remark"
                                    && rows[rowIndex].cus_remark != undefined
                                    && rows[rowIndex].cus_remark != "") {
                                var str = rows[rowIndex].cus_remark;
                                $.messager.alert('Remark', str, '');
                            }
                            if (field == "remark"
                                    && rows[rowIndex].remark != undefined
                                    && rows[rowIndex].remark != "") {
                                var str = rows[rowIndex].remark;
                                $.messager.alert('Remark', str, '');
                            }
                            var thisState = rows[rowIndex].state;

                            if ((approver == '9' && thisState == "0")
                                    || (approver == '1' && thisState == "0")
                                    || (approver == '2' && thisState == "1")
                                    || (approver == '3' && (userId == rows[rowIndex].forward_id || thisState == "2"))) {
                                beginEditing(rowIndex, field, value);
                                setEditing(rowIndex);
                            }
                        },
                        onDblClickRow : function(rowIndex, rowData) {// 双击查看日志
                            initMaintAccount(false, '840', '300', 'Quote Log',
                                    '/quoteAction!toSearchQuoteLog.jspa?quote_id='
                                            + rowData.quote_id
                                            + '&material_id='
                                            + rowData.material_id, 20, 20);
                        },
                        onLoadSuccess : function() {
                            if (approver == '3') {
                                $('div.datagrid-toolbar a').eq(3).hide();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(3).show();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            }
                            if (approver == '1') { // Sales
                                $('div.datagrid-toolbar a').eq(4).show();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            } else if (approver == '9') { // Quote Center
                                $('div.datagrid-toolbar a').eq(4).show();
                                $('div.datagrid-toolbar a').eq(7).show();
                            } else if (approver == '2') { // Business
                                $('div.datagrid-toolbar a').eq(4).show();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            } else if (approver == '3') { // Director
                                $('div.datagrid-toolbar a').eq(4).show();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            } else {
                                $('div.datagrid-toolbar a').eq(4).hide();
                                $('div.datagrid-toolbar a').eq(7).hide();
                            }
                        },

                    });

}

// 计算行项目总价=订购数量*审批成本价
// 客户利润=(审批resale*1-审批cost*1)/审批resale*1;;
// 瑞能利润Mfr Margin= (Suggest Cost-物料成本)/ Suggest Cost
function setEditing(rowIndex) {
    var rows = $('#datagrid').datagrid('getRows');
    var regionalMin = rows[rowIndex].sale_price;
    var materialCost = rows[rowIndex].cost;
    var qty = rows[rowIndex].qty;
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var weenCostEditor = editors[0];
    var weenResaleEditor = editors[1];
    var cusProfitEditor = editors[2];
    var weenProfitEditor = editors[3];
    var amountEditor = editors[4];
    var remarkEditor = editors[5];
    var cusRemarkEditor = editors[6];
    var cusRemarkEditor = editors[6];

    weenCostEditor.target.bind({
        'blur' : function() {
            calculate(rowIndex);
        },
        'mouseleave' : function() {
            calculate(rowIndex);
        }
    });
    weenResaleEditor.target.bind({
        'blur' : function() {
            calculate(rowIndex);
        },
        'mouseleave' : function() {
            calculate(rowIndex);
        }
    });
    amountEditor.target.bind({
        'blur' : function() {
            calculate(rowIndex);
        },
        'mouseleave' : function() {
            calculate(rowIndex);
        }
    });
    weenProfitEditor.target.bind({
        'blur' : function() {
            calculate(rowIndex);
        },
        'mouseleave' : function() {
            calculate(rowIndex);
        }
    });
    cusProfitEditor.target.bind({
        'blur' : function() {
            calculate(rowIndex);
        },
        'mouseleave' : function() {
            calculate(rowIndex);
        }
    });

    var rows = $('#datagrid').datagrid('getRows');
    var id = rows[rowIndex].id;

    remarkEditor.target.bind({
        'click' : function() {
            initMaintAccount(false, '400', '300', 'Remark',
                    '/quoteAction!toRemarkTxt.jspa?remark='
                            + encodeURIComponent(remarkEditor.target.val())
                            + '&rowIndex=' + rowIndex
                            + '&fromWho=Ween&forWho=Ween&id=' + id, 150, 150);

        }
    });
    cusRemarkEditor.target.bind({
        'click' : function() {
            initMaintAccount(false, '400', '300', 'Remark',
                    '/quoteAction!toRemarkTxt.jspa?remark='
                            + encodeURIComponent(cusRemarkEditor.target.val())
                            + '&rowIndex=' + rowIndex
                            + '&fromWho=Ween&forWho=Disti&id=' + id, 150, 150);
        }
    });

    function calculate(rowIndex) {
        var rows = $('#datagrid').datagrid('getRows');
        var weenCost = weenCostEditor.target.val();
        var weenResale = weenResaleEditor.target.val();
        var amount = weenCost * qty;
        amount = amount.toFixed(4);
        var weenProfit = ((weenCost - materialCost) * 1.0 / weenCost * 1.0) * 100;
        var cusProfit = ((weenResale * 1.00 - weenCost * 1.00) / weenResale * 1.00) * 100;
        weenProfit = weenProfit.toFixed(2);
        cusProfit = cusProfit.toFixed(2);
        if (weenResale * 1 < weenCost * 1) {
            $(weenResaleEditor.target).val("");
        }
        $(weenProfitEditor.target).val(weenProfit);
        $(cusProfitEditor.target).val(cusProfit);
        $(amountEditor.target).val(amount);

        if (weenProfit == 'NaN' || weenProfit == '-Infinity') {
            $(weenProfitEditor.target).val("");
        }
        if (cusProfit == 'NaN' || cusProfit == '-Infinity') {
            $(cusProfitEditor.target).val("");
        }
    }
}

function setRemark(remark, rowIndex, forWho) {
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var remarkEditor = editors[5];
    var cusRemarkEditor = editors[6];
    if (forWho == "Disti") {

        var str = $(cusRemarkEditor.target).val() + remark;
        $(cusRemarkEditor.target).val(str);
    } else if (forWho == "Ween") {

        var str = $(remarkEditor.target).val() + remark;
        $(remarkEditor.target).val(str);
    }
}

// copy 审批价格
function copy() {
    if ($('#pc_state').val() == '0'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '0')) {
        $.messager.alert('info', 'Please approve EC/PC first!');
        return;
    }
    var rows = $('#datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if ((approver == '9' && rows[i].state == 0)
                || (approver == '1' && rows[i].state == 0)
                || (approver == '2' && (rows[i].state == 0 || rows[i].state == 1))
                || (approver == '3' && (rows[i].state == 0
                        || rows[i].state == 1 || rows[i].state == 2))) {
            var tCost = rows[i].target_cost;
            var tResale = rows[i].target_resale;
            var mCost = rows[i].cost;// 物料成本为空则报错（weenProfit为NAN）
            var qty = rows[i].qty;
            rows[i].suggest_cost = tCost;
            rows[i].suggest_resale = tResale;
            var sResale = rows[i].suggest_resale;
            var sCost = rows[i].suggest_cost;
            var amount = sCost * qty;
            var weenProfit = ((sCost - mCost) * 1.0 / sCost * 1.0) * 100;
            var cusProfit = ((sResale * 1.00 - sCost * 1.00) / sResale * 1.00) * 100;
            amount = amount.toFixed(4);
            weenProfit = weenProfit.toFixed(2);
            cusProfit = cusProfit.toFixed(2);
            rows[i].amount = amount;
            rows[i].cus_profits_percent = cusProfit;
            rows[i].profits_percent = weenProfit;
            var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
            $("#datagrid").datagrid("refreshRow", rowIndex);
        } else {
            continue;
        }
    }
}

// 重复性检查
function check() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item！', 'warning');
        return;
    } else if (rows.length > 1) {
        $.messager.alert('Tips', 'Multiselect are not supported！', 'warning');
        return;
    } else {
        var pn = $('#pc_name').val();
        pn = $.trim(pn.substring(0, pn.lastIndexOf("-")));
        var en = $('#endCustomer_name').val();
        en = $.trim(pn.substring(0, pn.lastIndexOf("-")));
        initMaintAccount(false, '840', '300', 'Check Quote',
                '/quoteAction!toCheck.jspa?id=' + rows[0].id
                        + '&purchaseCustomer_name=' + encodeURIComponent(pn)
                        + '&endCustomer_name=' + encodeURIComponent(en)
                        + '&pcGroup_id='
                        + encodeURIComponent($('#pcGroup_id').val())
                        + '&ecGroup_id='
                        + encodeURIComponent($('#ecGroup_id').val())
                        + '&material_name=' + rows[0].material_name
                        + '&start_dateStr=' + $('#create_timeStr').val()
                        + '&remark=' + rows[0].remark, 20,
                20);
    }
}

// 查看日志
function quoteLog() {
    var rows = $('#datagrid').datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('Tips', 'Please select the data item！', 'warning');
        return;
    } else if (rows.length > 1) {
        $.messager.alert('Tips', 'Multiselect are not supported！', 'warning');
        return;
    } else {
        initMaintAccount(false, '840', '300', 'Quote Log',
                '/quoteAction!toSearchQuoteLog.jspa?quote_id='
                        + rows[0].quote_id + '&material_id='
                        + rows[0].material_id, 20, 20);
    }
}
// 通过
function approve() {
    if ($('#pc_state').val() == '0'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '0')) {
        $.messager.alert('info', 'Please approve EC/PC first!');
        return;
    }
    if ($('#pc_state').val() == '2'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '2')) {
        $.messager.alert('info', 'EC/PC was rejected，Please Reject Quote!');
        return;
    }
    if ($('#pc_state').val() == '3'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '3')) {
        $.messager.alert('info', 'EC/PC was Frozen!');
        return;
    }

    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }
    var rows = $('#datagrid').datagrid('getSelections');
    var canNotAudit = "";
    var quoteDetailJson = [];
    var isOK = true;
    var tips = "";
    for (var i = 0; i < rows.length; i++) {
    	var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        if (approver == '3') {
        	if (rows[i].state == 5) {
                canNotAudit += (rowIndex * 1 + 1) + ",";
                continue;
        	}
            if (userId != rows[i].forward_id && rows[i].state != 2) {
                canNotAudit += (rowIndex * 1 + 1) + ",";
                continue;
            }
        } else if ((approver == '9' && rows[i].state != 0)
                || (approver == '1' && rows[i].state != 0)
                || (approver == '2' && rows[i].state != 1)) {// 只能处理待审批状态的
            canNotAudit += (rowIndex * 1 + 1) + ",";
            continue;
        }
        row_no = (i * 1 + 1) * 10;
        if (((approver == '1') && (rows[i].sale_price * 1 == 0 || rows[i].sale_price == undefined))
                || (approver == '2' && (rows[i].stop_price * 1 == 0 || rows[i].stop_price == undefined))) {
            tips += "Row"
                    + (rowIndex * 1 + 1)
                    + "Please Contact Price Administrator to upload price！<br/>";
            isOK = false;
        }
        if (approver == '9'
                && (rows[i].stop_price * 1 == 0 || rows[i].stop_price == undefined)) {
            tips += "Row"
                    + (rowIndex * 1 + 1)
                    + "Please Contact Price Administrator to upload CMM price！<br/>";
            isOK = false;
        }
        if (approver == 1) {
            // 销售员approve 必须Forward和自己id
            if ($('#auditorId').val() != rows[i].forward_id) {
                tips += "Row" + (rowIndex * 1 + 1)
                        + "You can't approve other people's quote！<br/>";
                isOK = false;
            }
        }
        if (approver == 9) {
            // quote center Forward和自己id
            if (rows[i].forward_id != null
                    && $('#auditorId').val() != rows[i].forward_id) {
                tips += "Row" + (rowIndex * 1 + 1)
                        + "You can't approve other people's quote！<br/>";
                isOK = false;
            }
        }
        if (rows[i].suggest_resale == 0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Resaleis not completed yet！<br/>";
            isOK = false;
        }
        if (rows[i].suggest_cost == 0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Costis not completed yet！<br/>";
            isOK = false;
        }

        if ((approver == '1')
                && (rows[i].suggest_cost * 1.0 < rows[i].sale_price * 1.0)) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Cost must be greater than Regional Min！<br/>";
            isOK = false;
        }
        if ((approver == '1')
                && (rows[i].suggest_cost * 1.0 >= rows[i].pbMpp * 1.0)) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ":PB/MPP  must be greater than Suggest Cost！<br/>";
            isOK = false;
        }
        if ((approver == '9')
                && (rows[i].suggest_cost * 1.0 >= rows[i].pbMpp * 1.0)) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ":PB/MPP  must be greater than Suggest Cost！<br/>";
            isOK = false;
        }
        if ((approver == '2' || approver == '9')
                && (rows[i].suggest_cost * 1.0 < rows[i].stop_price * 1.0)) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Cost must be greater than CMM！<br/>";
            isOK = false;
        }
        if ((approver == '2')
                && (rows[i].suggest_cost * 1.0 >= rows[i].pbMpp * 1.0)) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ":PB/MPP  must be greater than Suggest Cost！<br/>";
            isOK = false;
        }

        var reason = (rows[i].reason == undefined || rows[i].reason == 'undefined') ? ''
                : rows[i].reason;
        var competitor = (rows[i].competitor == undefined || rows[i].competitor == 'undefined') ? ''
                : rows[i].competitor;
        var cus_remark = (rows[i].cus_remark == undefined || rows[i].cus_remark == 'undefined') ? ''
                : rows[i].cus_remark;
        var remark = (rows[i].remark == undefined || rows[i].remark == 'undefined') ? ''
                : rows[i].remark;
        var profits_percent = (rows[i].profits_percent == undefined || rows[i].profits_percent == 'undefined') ? ''
                : rows[i].profits_percent;

        var row = "{" + "id:" + "'" + rows[i].id + "'," + "quote_id:" + "'"
                + rows[i].quote_id + "'," + "row_no:" + "'" + row_no + "',"
                + "material_name:" + "'" + rows[i].material_name + "',"
                + "material_id:" + "'" + rows[i].material_id + "'," + "drNum:"
                + "'" + rows[i].drNum + "'," + "qty:" + "'" + rows[i].qty
                + "',res_qty:'" + rows[i].qty + "',target_resale:'"
                + rows[i].target_resale + "',target_cost:'"
                + rows[i].target_cost + "',amount:'" + rows[i].amount
                + "',cus_profits_percent:'"
                + encodeURIComponent(rows[i].cus_profits_percent)
                + "',suggest_resale:'" + rows[i].suggest_resale
                + "',suggest_cost:'" + rows[i].suggest_cost
                + "',profits_percent:'" + encodeURIComponent(profits_percent)
                + "',reason:'" + reason + "',competitor:'" + competitor
                + "',product_dateStr:'" + rows[i].product_dateStr
                + "',start_date:'" + rows[i].start_dateStr + "',end_date:'"
                + rows[i].end_dateStr + "',create_userId:'"
                + rows[i].create_userId + "',isDelivery:'" + approver
                + "',isRepresent:'" + rows[i].isRepresent + "',cusGroup_id:'"
                + encodeURIComponent($.trim($('#cusGroup_id').html()))
                + "',cus_remark:'" + escape(cus_remark) + "',remark:'"
                + escape(remark) + "',state:'" + aFlag + "'}";

        quoteDetailJson.push(row);
    }
    if (canNotAudit != "") {
        $.messager.alert('Tips', "Rows" + canNotAudit
                + "This item was already approved or need further approval！",
                'error');
        return;
    }
    if (quoteDetailJson.length <= 0) {
        $.messager.alert('Tips', "Please select the data item！", 'error');
        return;
    }
    if (isOK == false) {
        $.messager.alert('Tips', tips, 'error');
        return;
    }
    $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
        if (r) {
            $('#quoteDetailJson').val('[' + quoteDetailJson + ']');
            var form = window.document.forms[0];
            form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
            form.target = "hideFrame";
            form.submit();
        }
    });
}
// 拒绝
function reject() {
    var isOK = true;
    var tips = "";
    var canNotAudit = "";
    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }

    var rows = $("#datagrid").datagrid("getSelections");

    var quoteDetailJson = [];
    for (var i = 0; i < rows.length; i++) {
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        if (approver == '3') {
            if (userId != rows[i].forward_id && rows[i].state != 2) {
                canNotAudit += (rowIndex * 1 + 1) + ",";
                continue;
            }
        } else if ((approver == '9' && rows[i].state != 0)
                || (approver == '1' && rows[i].state != 0)
                || (approver == '2' && rows[i].state != 1)) {
            canNotAudit += (rowIndex * 1 + 1) + ",";
            continue;
        }
        row_no = (i * 1 + 1) * 10;

        if (rows[i].cus_remark == undefined || rows[i].cus_remark == "") {// ||rows[i].cus_remark.lastIndexOf($('#create_userId').val())<=-1
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Remark（Reject Reason）is not completed yet！<br/>";
            isOK = false;
        }

        if (approver == 1) {
            // 销售员approve 必须Forward和自己id
            if ($('#auditorId').val() != rows[i].forward_id) {
                tips += "Row" + (rowIndex * 1 + 1)
                        + "You can't reject other people's quote！<br/>";
                isOK = false;
            }
        }
        if (approver == 9) {
            // quote center Forward和自己id
            if (rows[i].forward_id != null
                    && $('#auditorId').val() != rows[i].forward_id) {
                tips += "Row" + (rowIndex * 1 + 1)
                        + "You can't reject other people's quote！<br/>";
                isOK = false;
            }
        }

        var reason = (rows[i].reason == undefined || rows[i].reason == 'undefined') ? ''
                : rows[i].reason;
        var competitor = (rows[i].competitor == undefined || rows[i].competitor == 'undefined') ? ''
                : rows[i].competitor;
        var cus_remark = (rows[i].cus_remark == undefined || rows[i].cus_remark == 'undefined') ? ''
                : rows[i].cus_remark;
        var remark = (rows[i].remark == undefined || rows[i].remark == 'undefined') ? ''
                : rows[i].remark;
        var profits_percent = (rows[i].profits_percent == undefined || rows[i].profits_percent == 'undefined') ? ''
                : rows[i].profits_percent;

        var row = "{" + "id:" + "'" + rows[i].id + "'," + "quote_id:" + "'"
                + rows[i].quote_id + "'," + "row_no:" + "'" + row_no + "',"
                + "material_name:" + "'" + rows[i].material_name + "',"
                + "material_id:" + "'" + rows[i].material_id + "'," + "drNum:"
                + "'" + rows[i].drNum + "'," + "qty:" + "'" + rows[i].qty
                + "',res_qty:'" + rows[i].qty + "',target_resale:'"
                + rows[i].target_resale + "',target_cost:'"
                + rows[i].target_cost + "',amount:'" + rows[i].amount
                + "',cus_profits_percent:'"
                + encodeURIComponent(rows[i].cus_profits_percent)
                + "',suggest_resale:'" + rows[i].suggest_resale
                + "',suggest_cost:'" + rows[i].suggest_cost
                + "',profits_percent:'" + encodeURIComponent(profits_percent)
                + "',reason:'" + reason + "',competitor:'" + competitor
                + "',product_dateStr:'" + rows[i].product_dateStr
                + "',start_date:'" + rows[i].start_dateStr + "',end_date:'"
                + rows[i].end_dateStr + "',create_userId:'"
                + rows[i].create_userId + "',isRepresent:'"
                + rows[i].isRepresent + "',cusGroup_id:'"
                + encodeURIComponent($('#cusGroup_id').html())
                + "',cus_remark:'" + escape(cus_remark) + "',remark:'"
                + escape(remark) + "',state:'" + rFlag + "'}";

        quoteDetailJson.push(row);
    }
    if (canNotAudit != "") {
        $.messager.alert('Tips', "Rows" + canNotAudit
                + "This item was already approved or need further approval！",
                'error');
        return;
    }
    if (quoteDetailJson.length <= 0) {
        $.messager.alert('Tips', "Please select the data item！", 'error');
        return;
    }
    if (isOK == false) {
        $.messager.alert('Tips', tips, 'error');
        return;
    }
    $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
        if (r) {
            $('#quoteDetailJson').val('[' + quoteDetailJson + ']');
            var form = window.document.forms[0];
            form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
            form.target = "hideFrame";
            form.submit();
        }
    });
}
// 申请上级审批
function escalation() {
    if ($('#pc_state').val() == '0'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '0')) {
        $.messager.alert('info', 'Please approve EC/PC first!');
        return;
    }
    if ($('#pc_state').val() == '2'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '2')) {
        $.messager.alert('info', 'EC/PC was rejected，Please Reject Quote!');
        return;
    }
    if ($('#pc_state').val() == '3'
            || ($('#endCustomer_name').html() != '' && $('#ec_state').val() == '3')) {
        $.messager.alert('info', 'EC/PC was Frozen!');
        return;
    }
    var isOK = true;
    var tips = "";
    var canNotAudit = "";
    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }

    var rows = $("#datagrid").datagrid("getSelections");

    var quoteDetailJson = [];
    for (var i = 0; i < rows.length; i++) {
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        if ((approver == '9' && rows[i].state != 0)
                || (approver == '1' && rows[i].state != 0)
                || (approver == '2' && rows[i].state != 1)
                || (approver == '3' && rows[i].state != 2)) {
            canNotAudit += (rowIndex * 1 + 1) + ",";
            continue;
        }
        if (approver == 1) { // quote center Forward和自己id
            if ($('#auditorId').val() != rows[i].forward_id) {
                $.messager.alert('Tips',
                        "Can't escalation others pepope quote！", 'error');
                return;
            }
        }
        if (approver == 9) { // quote center Forward和自己id
            if (rows[i].forward_id != null
                    && $('#auditorId').val() != rows[i].forward_id) {
                $.messager.alert('Tips',
                        "Can't escalation others pepope quote！", 'error');
                return;
            }
        }
        row_no = (i * 1 + 1) * 10;
        if (rows[i].suggest_resale == 0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Resaleis not completed yet！<br/>";
            isOK = false;
        }
        if (rows[i].suggest_cost == 0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Costis not completed yet！<br/>";
            isOK = false;
        }
        if (rows[i].suggest_cost * 1.0 >= rows[i].pbMpp * 1.0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Cost can't greater than PB/MPP！<br/>";
            isOK = false;
        }
        var reason = (rows[i].reason == undefined || rows[i].reason == 'undefined') ? ''
                : rows[i].reason;
        var competitor = (rows[i].competitor == undefined || rows[i].competitor == 'undefined') ? ''
                : rows[i].competitor;
        var cus_remark = (rows[i].cus_remark == undefined || rows[i].cus_remark == 'undefined') ? ''
                : rows[i].cus_remark;
        var remark = (rows[i].remark == undefined || rows[i].remark == 'undefined') ? ''
                : rows[i].remark;
        var profits_percent = (rows[i].profits_percent == undefined || rows[i].profits_percent == 'undefined') ? ''
                : rows[i].profits_percent;

        var row = "{" + "id:" + "'" + rows[i].id + "'," + "quote_id:" + "'"
                + rows[i].quote_id + "'," + "row_no:" + "'" + row_no + "',"
                + "material_name:" + "'" + rows[i].material_name + "',"
                + "material_id:" + "'" + rows[i].material_id + "'," + "drNum:"
                + "'" + rows[i].drNum + "'," + "qty:" + "'" + rows[i].qty
                + "',res_qty:'" + rows[i].qty + "',target_resale:'"
                + rows[i].target_resale + "',target_cost:'"
                + rows[i].target_cost + "',amount:'" + rows[i].amount
                + "',cus_profits_percent:'"
                + encodeURIComponent(rows[i].cus_profits_percent)
                + "',suggest_resale:'" + rows[i].suggest_resale
                + "',suggest_cost:'" + rows[i].suggest_cost
                + "',profits_percent:'" + encodeURIComponent(profits_percent)
                + "',reason:'" + reason + "',competitor:'" + competitor
                + "',product_dateStr:'" + rows[i].product_dateStr
                + "',start_date:'" + rows[i].start_dateStr + "',end_date:'"
                + rows[i].end_dateStr + "',create_userId:'"
                + rows[i].create_userId + "',isRepresent:'"
                + rows[i].isRepresent + "',cusGroup_id:'"
                + encodeURIComponent($('#cusGroup_id').html())
                + "',cus_remark:'" + escape(cus_remark) + "',remark:'"
                + escape(remark) + "',state:'" + eFlag + "'}";

        quoteDetailJson.push(row);
    }
    if (canNotAudit != "") {
        $.messager.alert('Tips', "Rows" + canNotAudit
                + "This item was already approved or need further approval!",
                'error');
        return;
    }
    if (quoteDetailJson.length <= 0) {
        $.messager.alert('Tips', "Please select the data item！", 'error');
        return;
    }
    if (isOK == false) {
        $.messager.alert('Tips', tips, 'error');
        return;
    }
    $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
        if (r) {
            $('#quoteDetailJson').val('[' + quoteDetailJson + ']');
            var form = window.document.forms[0];
            form.action = appUrl + "/quoteAction!auditQuoteDetail.jspa";
            form.target = "hideFrame";
            form.submit();
        }
    });
}

function forward() {
    var isOK = true;
    var tips = "";
    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }

    var rows = $("#datagrid").datagrid("getSelections");
    for (var i = 0; i < rows.length; i++) {
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        if (approver == 9) {
            if (rows[i].forward_id != null
                    && $('#auditorId').val() != rows[i].forward_id) {
                $.messager.alert('Tips',
                        "You can't forward other people's quote！", 'error');
                return;
            }
            if (rows[i].state != 0 && rows[i].state != 1 && rows[i].state != 2) {
                $.messager.alert('Tips', "Only pending forward！", 'error');
                return;
            }
        }
        if (approver == 1) {
            // quote center Forward和自己id
            if ($('#auditorId').val() != rows[i].forward_id) {
                $.messager.alert('Tips',
                        "You can't forward other people's quote！", 'error');
                return;
            }
            if (rows[i].state != 0 && rows[i].state != 1 && rows[i].state != 2) {
                $.messager.alert('Tips', "Only pending forward！", 'error');
                return;
            }
        }

        if (rows[i].suggest_cost * 1.0 >= rows[i].pbMpp * 1.0) {
            tips += "Row" + (rowIndex * 1 + 1)
                    + ": Suggest Cost can't greater than PB/MPP !<br/>";
            isOK = false;
        }
    }
    if (isOK == false) {
        $.messager.alert('Tips', tips, 'error');
        return;
    }
    initMaintAccount(true, '600', '450', 'Select User For Forward',
            '/quoteAction!toSelectUserForQuoteForward.jspa', 0, 0);
}

function returnUser(loginid) {
    var ids = "";
    var suggestCosts = "";
    var suggestResales = "";
    var cusProfitsPercents = "";
    var profitsPercents = "";
    var amounts = "";

    var rows = $('#datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if ((approver == '9' && (rows[i].state != 1 && rows[i].state != 3 && rows[i].state != 6))) {
            ids = rows[i].id + "," + ids;
            suggestCosts = rows[i].suggest_cost + "," + suggestCosts;
            suggestResales = rows[i].suggest_resale + "," + suggestResales;
            cusProfitsPercents = encodeURIComponent(rows[i].cus_profits_percent)
                    + "," + cusProfitsPercents;
            profitsPercents = encodeURIComponent(rows[i].profits_percent) + ","
                    + profitsPercents;
            amounts = rows[i].amount + "," + amounts;
        }
    }

    $
            .ajax({
                type : "post",
                url : appUrl
                        + '/quoteAction!updateUserForQuoteForward.jspa?ids='
                        + ids + '&quote_id=' + $('#id').val() + '&loginid='
                        + loginid + '&suggestCosts=' + suggestCosts
                        + '&suggestResales=' + suggestResales
                        + '&cusProfitsPercents=' + cusProfitsPercents
                        + '&profitsPercents=' + profitsPercents + '&amounts='
                        + amounts,
                success : function(result) {
                    $.messager.alert('Tips', "success", 'info');
                    $("#hiddenWin").window('close');
                    $("#datagrid").datagrid("reload");
                },
            });
}

function reset() {
    var quoteDetailJson = [];
    var rows1 = $('#datagrid').datagrid('getRows');
    for (var i = 0; i < rows1.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
    }
    var rows = $('#datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if ((approver == '1' && (rows[i].state != 1 && rows[i].state != 3 && rows[i].state != 6))
                || (approver == '2' && (rows[i].state != 2
                        && rows[i].state != 4 && rows[i].state != 7))
                || (approver == '3' && (rows[i].state != 5 && rows[i].state != 8))) {
            $.messager.alert('Tips', "Can only step on the back！", 'error');
            return;
        }
        if (approver == 1) {
            // quote center Forward和自己id
            if ($('#auditorId').val() != rows[i].forward_id) {
                $.messager.alert('Tips', "Can't reset others pepope quote！",
                        'error');
                return;
            }
        }
        if (approver == '9') {
            // 如果是quotecenter自己同意和拒绝，转到peggie名下的quote 可以reset
            if (rows[i].state == 3 || rows[i].state == 6 || rows[i].state == 1) {
                if (rows[i].forward_id == null) {
                    // Center 中心 自己单子可以撤回
                } else {
                    // 已经Forward出去，就是销售的approve和reject esction单子不能撤回
                    $.messager.alert('Tips', "Can only step on the back！",
                            'error');
                    return;
                }
            } else if (rows[i].state == 0) {
                // 如果是pending状态
                // 则quotecenter可以把转出去的单子搞回来
                if (rows[i].forward_id == null) {
                    // Center 中心
                    // 自己单子不可以撤回，也不需要撤回
                    $.messager.alert('Tips', "Can only step on the back！",
                            'error');
                    return;
                }
            } else {
                $.messager.alert('Tips', "Can only step on the back！", 'error');
                return;
            }
        }

        if (rows[i].isAgree == '1') {
            $.messager.alert('Tips', "Can not reset when agree！", 'error');
            return;
        }
        var row = "{" + "id:" + "'" + rows[i].id + "'," + "state:" + "'"
                + rows[i].state + "'}";
        quoteDetailJson.push(row);
    }
    if (quoteDetailJson.length <= 0) {
        $.messager.alert('Tips', "Please select the data item！", 'error');
        return;
    }
    $.messager.confirm('Confirm', 'Confirm to reset?', function(r) {
        if (r) {
            $('#quoteDetailJson').val('[' + quoteDetailJson + ']');
            var form = window.document.forms[0];
            form.action = appUrl + "/quoteAction!resetAudit.jspa?approver="
                    + approver;
            form.target = "hideFrame";
            form.submit();
        }
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
        // 关闭编辑上一行
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
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
        loadGrid();
    }
}

function closeMain() {
    $("#hiddenWin").window('close');
}

document.onkeydown = function(e) {
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        search();
        return false;
    }
    if (event.keyCode == 8) {
        // 如果是在textarea内不执行任何操作
        if (event.srcElement.tagName.toLowerCase() != "input"
                && event.srcElement.tagName.toLowerCase() != "textarea"
                && event.srcElement.tagName.toLowerCase() != "password")
            event.returnValue = false;
        // 如果是readOnly或者disable不执行任何操作
        if (event.srcElement.readOnly == true
                || event.srcElement.disabled == true)
            event.returnValue = false;
    }
    return true;
};

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
