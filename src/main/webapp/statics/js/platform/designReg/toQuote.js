$(document)
        .ready(
                function() {

                    var itemId = "";
                    if ($('#currency_code').val() == "USD") {
                        itemId = 14209;
                    } else if ($('#currency_code').val() == "EUR") {
                        itemId = 14210;
                    }
                    $
                            .ajax({
                                type : "post",
                                url : appUrl
                                        + '/designRegAction!getDictOfWeen.jspa?dictTypeId=561&itemId='
                                        + itemId,
                                success : function(result) {
                                    $('#total_type').val(result[0].itemValue);
                                }
                            });
                    $
                            .ajax({
                                type : "post",
                                url : appUrl
                                        + '/designRegAction!getDictOfWeen.jspa?dictTypeId=563&itemId='
                                        + 14213,
                                success : function(result) {
                                    $('#rate').val(result[0].itemValue);
                                }
                            });
                    if ($('#quoteDetail') != "") {
                        var quoteDetail = $('#quoteDetail').val();
                        quoteDetail = "[" + quoteDetail + "]";
                        var qdJson = eval(quoteDetail);
                        for (var i = 0; i < qdJson.length; i++) {
                            var qd = qdJson[i];
                            $('#datagrid').datagrid('appendRow', {
                                material_name : qd.material_name,
                                material_id : qd.material_id,
                                drNum : qd.drNum,
                                cost : qd.cost,
                                moq : qd.moq,
                                qty : '',
                                pbMpp : qd.pbMpp,
                                target_resale : 0,
                                target_cost : 0,
                                target_margin : 0,
                                cus_profits_percent : '',
                                suggest_resale : '',
                                suggest_cost : '',
                                profits_percent : '',
                                amount : '',
                                reason : '',
                                competitor : '',
                                start_dateStr : '',
                                end_dateStr : '',
                                product_dateStr : '',
                                cus_remark : '',
                                remark : '',
                            });
                        }
                    }
                    $('#hideFrame').bind('load', promgtMsg);
                });

var currentDate = getNowFormatDate();

function upload() {
    initMaintAccount(false, '500', '300', '�ϴ�',
            '/file/fileAction!toImportFile.jspa', 150, 150);
}

function returnUploadFile(fileStr, fileName) {
    var filePath = fileStr;
    var paths = fileName.split("\\");
    var fileName = paths[paths.length - 1];
    $('#new_file_name').val(fileName);
    $('#old_file_name').val(fileName);
    $('#file_path').val(filePath);
}
function closeMaintWindow() {
    $("#hiddenWin").window('close');
}

function cancel() {
    window.parent.closeMaintWindow();
}

$('#datagrid')
        .datagrid(
                {
                    iconCls : 'icon-list',
                    title : '',
                    loadMsg : 'Loading...',
                    singleSelect : true,
                    nowrap : true,
                    checkbox : true,
                    required : true,
                    rownumbers : true,
                    height : 220,
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
                                title : 'pk',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                title : "Status",
                                field : 'state',
                                width : 100,
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
                                        return "<font color='g'reen>Approved</font>";
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
                                field : 'material_name',
                                title : 'BookPart<br>',
                                width : 150,
                                align : 'center',
                                formatter : function(value, row, rec) {
                                    var flag = row.material_name;
                                    if (flag == 'undefined'
                                            || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag;
                                    }
                                }
                            },
                            {
                                field : 'material_id',
                                title : '12NC',
                                width : 120,
                                align : 'center',
                                formatter : function(value, row, rec) {
                                    var flag = row.material_id;
                                    if (flag == '' || flag == undefined) {
                                        return "";
                                    } else {
                                        var str = $.trim(flag);
                                        str = str.substring(str.length - 12,
                                                str.length);
                                        return str;
                                    }
                                }
                            },
                            {
                                field : 'drNum',
                                title : 'DR Number',
                                width : 100,
                                align : 'center',
                            },
                            {
                                field : 'moq',
                                title : 'MOQ',
                                width : 80,
                                align : 'center',
                            },
                            {
                                field : 'cost',
                                title : 'Cost',
                                width : 80,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'qty',
                                title : 'QTY<br>',
                                width : 80,
                                align : 'center',
                                editor : {
                                    type : 'numberbox'
                                },
                                formatter : function(value, row, rec) {
                                    var flag = row.qty;
                                    if (flag == '' || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag * 1;
                                    }
                                }
                            },
                            {
                                field : 'pbMpp',
                                title : 'PB/MPP',
                                width : 70,
                                align : 'center',
                                formatter : function(value, row, rec) {
                                    var flag = row.pbMpp;
                                    if (flag == 0 || flag == ''
                                            || flag == undefined || flag == 'undefined') {
                                        return "<font color='red'>No Price</font>";
                                    } else {
                                        flag = JSON.parse(flag * 1);
                                        return flag.toFixed(4);

                                    }
                                },
                            },
                            {
                                field : 'target_cost',
                                title : 'Target Cost<br> Ŀ������۸�',
                                width : 80,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 4
                                    }
                                }
                            },
                            {
                                field : 'target_resale',
                                title : 'Target Resale<br>Ŀ�����ۼ۸�',
                                width : 85,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 4
                                    }
                                }
                            },
                            {
                                field : 'target_amount',
                                title : 'Target Amount',
                                width : 80,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 4,
                                        disabled : true
                                    }
                                },
                            },
                            {
                                field : 'target_margin',
                                title : 'Target Margin(%)',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 2,
                                        disabled : true
                                    }
                                },
                                formatter : function(value, row, rec) {
                                    var flag = row.target_margin;
                                    if (flag == '' || flag == undefined
                                            || flag == 'undefined') {
                                        return 0;
                                    } else {
                                        flag = JSON.parse(flag * 1);
                                        return flag.toFixed(2);
                                    }
                                }
                            },
                            {
                                field : 'cus_profits_percent',
                                title : 'Disti Margin',
                                width : 80,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'suggest_resale',
                                title : 'Suggest Resale',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'suggest_cost',
                                title : 'Suggest Cost<br> �۸�',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'profits_percent',
                                title : 'Mfr Margin<br> ',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'amount',
                                title : 'Value<br> ����Ŀ�ܼ�',
                                width : 80,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'start_dateStr',
                                title : 'Start Date',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'datebox',
                                    options : {
                                        onSelect : function(d) {
                                            var editors = $('#datagrid')
                                                    .datagrid('getEditors',
                                                            editIndex);
                                            var endEditor = editors[6];
                                            var td = new Date();
                                            td.setHours(0, 0, 0, 0);
                                            if (d < td) {
                                                $(this).datebox('setValue', '');
                                                endEditor.target.datebox(
                                                        'setValue', '');
                                                $.messager
                                                        .alert(
                                                                'Tips',
                                                                ' Start date cannot be backdated. ',
                                                                'warning');
                                                return;
                                            }
                                            d.setMonth(d.getMonth() + 3);
                                            var endDay = d.format('yyyy-MM-dd');
                                            endEditor.target.datebox(
                                                    'setValue', endDay);
                                        }
                                    }
                                },
                            },
                            {
                                field : 'end_dateStr',
                                title : 'Expire Date',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'datebox',
                                    options : {
                                        onSelect : function(d) {
                                            var editors = $('#datagrid')
                                                    .datagrid('getEditors',
                                                            editIndex);
                                            var td = new Date();
                                            td.setHours(0, 0, 0, 0);
                                            if (d < td) {
                                                $(this).datebox('setValue', '');
                                                $.messager
                                                        .alert(
                                                                'Tips',
                                                                'The Expire Date cannot be earlier than the current date��',
                                                                'warning');
                                                return;
                                            }
                                            var startEditor = editors[5];
                                            var startDate = startEditor.target
                                                    .datebox('getValue');
                                            var sd = new Date(startDate);
                                            // sd.setHours( 0, 0, 0, 0 );
                                            if (sd >= d) {
                                                $(this).datebox('setValue', '');
                                                $.messager
                                                        .alert(
                                                                'Tips',
                                                                'The Expire Date must later than the Start Date��',
                                                                'warning');
                                                return;
                                            }
                                        }
                                    }
                                }
                            },
                            {
                                field : 'product_dateStr',
                                title : 'Start Production',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'datebox',
                                    options : {
                                        onSelect : function(d) {
                                            var editors = $('#datagrid')
                                                    .datagrid('getEditors',
                                                            editIndex);
                                            var td = new Date();
                                            td.setHours(0, 0, 0, 0);
                                            if (d < td) {
                                                $(this).datebox('setValue', '');
                                                $.messager
                                                        .alert(
                                                                'Tips',
                                                                'The Start Production cannot be earlier than the current date��',
                                                                'warning');
                                                return;
                                            }
                                            var startEditor = editors[5];
                                            var startDate = startEditor.target
                                                    .datebox('getValue');
                                            var sd = new Date(startDate);
                                            sd.setHours(0, 0, 0, 0);
                                            if (d < sd) {
                                                $(this).datebox('setValue', '');
                                                $.messager
                                                        .alert(
                                                                'Tips',
                                                                'The Start Production cannot be earlier than the Start Date��',
                                                                'warning');
                                                return;
                                            }
                                        }
                                    }
                                }
                            },
                            {
                                field : 'reason',
                                title : 'Justification<br>',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'combobox',
                                    options : {
                                        url : appUrl
                                                + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=556',
                                        valueField : 'itemName', // �洢�ֶ���
                                        textField : 'itemName', // ��ʾ�ֶ�ֵ
                                        editable : false,
                                        panelHeight : 150,
                                        onSelect : function(value) {
                                        }
                                    }
                                }
                            },
                            {
                                field : 'competitor',
                                title : 'Competitor<br>',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'combobox',
                                    options : {
                                        url : appUrl
                                                + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=557',
                                        valueField : 'itemName', // �洢�ֶ���
                                        textField : 'itemName', // ��ʾ�ֶ�ֵ
                                        editable : false,
                                        panelHeight : 150,
                                        onSelect : function(value) {
                                        }
                                    }
                                }
                            }, {
                                field : 'cus_remark',
                                title : 'Disti Remark',
                                width : 100,
                                align : 'center',
                                editor : 'text'
                            }, {
                                field : 'remark',
                                title : 'Internal Comments',
                                width : 100,
                                align : 'center',
                                hidden : true
                            } ] ],
                    toolbar : [ "-", {
                        text : 'Add',
                        iconCls : 'icon-add',
                        handler : function() {
                            add();
                        }
                    }, "-", {
                        text : 'Delete',
                        iconCls : 'icon-remove',
                        handler : function() {
                            dele();
                        }
                    } ],
                    onClickCell : function(rowIndex, field, value) {
                        beginEditing(rowIndex, field, value);
                        $('#datagrid').datagrid('beginEdit', rowIndex);
                        setEditing(rowIndex);
                    },
                });

function setEditing(rowIndex) {
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var numEditor = editors[0];
    var costEditor = editors[1];
    var reSaleEditor = editors[2];
    var targetAmountEditor = editors[3];
    var marginEditor = editors[4];
    costEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    reSaleEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    targetAmountEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    numEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    function calculate() {
        var num = numEditor.target.val();
        var cost = costEditor.target.val();
        var resale = reSaleEditor.target.val();
        if (resale * 1 < cost * 1) {
            $(reSaleEditor.target).val("");
        }
        var targetProfit = ((resale * 1.00 - cost * 1.00) / resale * 1.00) * 100;
        var tamount = cost * num;
        tamount = tamount.toFixed(4);
        $(targetAmountEditor.target).val(tamount);

        targetProfit = targetProfit.toFixed(2);
        $(marginEditor.target).val(targetProfit);
        if (targetProfit == 'NaN' || targetProfit == '-Infinity') {
            $(marginEditor.target).val("");
        }
    }

    var cusRemarkEditor = editors[10];
    cusRemarkEditor.target.bind({
        'click' : function() {
            initMaintAccount(false, '400', '300', 'Remark',
                    '/quoteAction!toRemarkTxt.jspa?remark='
                            + encodeURIComponent(cusRemarkEditor.target.val())
                            + '&rowIndex=' + rowIndex + '&forWho=Disti', 150,
                    150);
        }
    });
}

function setRemark(remark, rowIndex, forWho) {
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var cusRemarkEditor = editors[10];
    var str = $(cusRemarkEditor.target).val() + remark;
    $(cusRemarkEditor.target).val(str);
}
function closeMain() {
    $("#hiddenWin").window('close');
}
//
var ps = [];
function add() {
    ps = [];
    var rows = $("#datagrid").datagrid("getRows");
    for (var i = 0; i < rows.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
        ps.push(rows[i].material_id);
    }
    initMaintAccount(false, '800', '450', 'Product',
            '/productAction!toSearchProductForUse.jspa?isQuoteItem=Y&currency_code='
                    + $('#currency_code').val() + '&office_id='
                    + $('#salesOrg').val() + '&customer_id='
                    + $('#payerTo').val(), 100, 20);
}

function returnProduct(proJson) {
    var productStr = "[" + proJson + "]";
    var pJson = eval(productStr);
    for (var i = 0; i < pJson.length; i++) {
        var product = pJson[i];
        var sameFlag = false;
        for (var j = 0; j < ps.length; j++) {
            if (product.material_id == ps[i]) {
                sameFlag = true;
                break;
            }
        }
        if (sameFlag) {
            continue;
        }

        $('#datagrid').datagrid('appendRow', {
            material_name : product.material_name,
            material_id : product.material_id,
            drNum : '',
            cost : product.cost,
            moq : product.moq,
            qty : '',
            pbMpp : product.pbPrice,
            target_resale : '',
            target_cost : '',
            target_margin : 0,
            cus_profits_percent : '',
            suggest_resale : '',
            suggest_cost : '',
            profits_percent : '',
            amount : '',
            reason : '',
            competitor : '',
            start_dateStr : '',
            cus_remark : '',
            remark : '',
        });
    }
    $("#hiddenWin").window('close');
}

var delQuote = "0";
function dele() {
    var row = $('#datagrid').datagrid('getSelected');
    if (row.length == 0) {
        $.messager.alert('Tips', 'Please select the data item��', 'warning');
        return;
    }
    $.messager.confirm('Confirm', 'Confirmed about delete?', function(r) {
        if (r) {
            {
                if (row.id != undefined) {
                    delQuote += "," + row.id;
                }
                var rowIndex = $('#datagrid').datagrid('getRowIndex', row);
                $('#datagrid').datagrid('deleteRow', rowIndex);

            }
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
    } else {
        if (failResult == undefined || failResult == 'undefined') {
            return;
        }
        $.messager.alert('Tips', successResult, 'info', function() {
            window.parent.closeMaintWindow();
            // window.parent.search();
        });
    }
}

function submit() {
    if ($('#customer_id').val() == '') {
        $.messager.alert('Tips', "Customeris not completed yet��", 'error');
        return;
    }
    if ($('#project_name').val() == '') {
        $.messager.alert('Tips', "Projectis not completed yet��", 'error');
        return;
    }
    if ($('#purchaseCustomer_id').combobox('getValue') == "") {
        $.messager.alert('Tips', "Purchase Customeris not completed yet��",
                'error');
        return;
    }

    var rows = $("#datagrid").datagrid("getRows");
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
        total += rows[i].qty * rows[i].target_cost;
    }
    $('#total_amount').val(total);
    if ($('#total_type').val() != ""
            && total * 1 < ($('#total_type').val() * 1)) {// �����ֵ����õ��ܿ��ƽ��
        $.messager.alert("error", "Total amount is less than "
                + $('#total_type').val() + $('#currency_code').val() + "��",
                'error');
        return;
    }
    var url = undefined;
    if ($('#id').val() == '0' || $('#id').val() == '') {
        url = "/quoteAction!createQuote.jspa";
    } else {
        url = "/quoteAction!updateQuote.jspa";
    }

    var quoteDetailJson = [];
    for (var i = 0; i < rows.length; i++) {
        row_no = (i * 1 + 1) * 10;
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        if (rows[i].qty == undefined || rows[i].qty == 0 || rows[i].qty == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "QTY is not completed yet��", 'error');
            return;
        } else if ((rows[i].qty) * 1 < (rows[i].moq) * 1) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Part quantity must be greater than MOQ��", 'error');
            return;
        }
        if (rows[i].pbMpp == undefined || rows[i].pbMpp == 0
                || rows[i].pbMpp == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "PB/MPP NO PRICE��Pls Contact Sales OR Del", 'error');
            return;
        }
        if (rows[i].target_resale == undefined || rows[i].target_resale == 0
                || rows[i].target_resale == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Target Resale is not completed yet��", 'error');
            return;
        }
        if (rows[i].target_cost == undefined || rows[i].target_cost == 0
                || rows[i].target_cost == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Target Cost is not completed yet��", 'error');
            return;
        }
        if (rows[i].start_dateStr == undefined || rows[i].start_dateStr == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Start Date can not be blank��", 'error');
            return;
        } else if (rows[i].start_dateStr < currentDate) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Start Date  can not earlier than today��", 'error');
            return;
        }
        if (rows[i].target_cost * 1 > rows[i].target_resale * 1) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Target Cost must less than target resale！", 'error');
            return;
        }
        if (rows[i].target_cost * 1 >= rows[i].pbMpp * 1) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Target Cost must less than PB/MPP！", 'error');
            return;
        }
        if (rows[i].end_dateStr == undefined || rows[i].end_dateStr == "") {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Expire Date can not be blank��", 'error');
            return;
        } else if (rows[i].end_dateStr < rows[i].start_dateStr) {
            $.messager
                    .alert('Tips', "Rows" + (i * 1 + 1)
                            + "Expire Date  can not earlier than Start Date��",
                            'error');
            return;
        }
        if (rows[i].product_dateStr == undefined
                || rows[i].product_dateStr == "") {
        } else if (rows[i].product_dateStr < rows[i].start_dateStr) {
            $.messager.alert('Tips', "Rows" + (i * 1 + 1)
                    + "Start Production can not earlier than Start Date��",
                    'error');
            return;
        }
        var material_name = (rows[i].material_name == undefined || rows[i].material_name == 'undefined') ? ''
                : rows[i].material_name;
        var drNum = (rows[i].drNum == undefined || rows[i].drNum == 'undefined') ? ''
                : rows[i].drNum;
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

        var row = "{" + "id:" + "'" + rows[i].id + "'," + "row_no:" + "'"
                + row_no + "'," + "material_name:" + "'" + material_name + "',"
                + "material_id:" + "'" + rows[i].material_id + "'," + "drNum:"
                + "'" + drNum + "'," + "qty:" + "'" + rows[i].qty
                + "',res_qty:'" + rows[i].qty + "',target_resale:'"
                + rows[i].target_resale + "',target_cost:'"
                + rows[i].target_cost + "',amount:'" + rows[i].amount
                + "',cus_profits_percent:'"
                + encodeURIComponent(rows[i].cus_profits_percent)
                + "',suggest_resale:'" + rows[i].suggest_resale
                + "',suggest_cost:'" + rows[i].suggest_cost
                + "',profits_percent:'" + encodeURIComponent(profits_percent)
                + "',reason:'" + reason + "',competitor:'" + competitor
                + "',start_date:'" + rows[i].start_dateStr + "',end_date:'"
                + rows[i].end_dateStr + "',product_dateStr:'"
                + rows[i].product_dateStr + "',cost:'" + rows[i].cost
                + "',moq:'" + rows[i].moq + "',pbMpp:'" + rows[i].pbMpp
                + "',cus_remark:'" + cus_remark + "',remark:'" + remark + "'}";
        quoteDetailJson.push(row);
    }

    $.messager.confirm('Confirm', 'Confirm to submit?', function(r) {
        if (r) {
            $('#quoteDetailJson').val('[' + quoteDetailJson + ']');
            $('#delQuote').val("(" + delQuote + ")");
            var form = window.document.forms[0];
            form.action = appUrl + url;
            form.target = "hideFrame";
            form.submit();
        }
    });

}

function applyEC() {
    initMaintAccount(false, '850', '400', 'Reg End Customer',
            '/endCustomerAction!toCreateEndCustomer.jspa', 100, 30);
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
var find = false;
var pcFirst = true;

$('#purchaseCustomer_id')
        .combogrid(
                {
                    panelHeight : 365,
                    panelWidth : 500,
                    pagination : true,
                    pageSize : 10,
                    multiple : false,
                    editable : false,
                    method : 'post',
                    singleSelect : true,
                    url : appUrl
                            + '/endCustomer/endCustomerAction!getEndCustomerList.jspa?states=(1)',
                    queryParams : {
                        end_customer_name : encodeURIComponent($(
                                "#endCustomer_name").val()),
                    },
                    idField : 'end_customer_id',
                    textField : 'end_customer_name',
                    columns : [ [ {
                        field : 'end_customer_groupName',
                        title : 'Customer Group',
                        width : 100
                    }, {
                        field : 'end_customer_name',
                        title : 'Customer Name',
                        width : 200
                    }, {
                        field : 'country',
                        title : 'Country',
                        width : 70
                    }, {
                        field : 'city',
                        title : 'City',
                        width : 70
                    } ] ],
                    onSelect : function(index, record) {
                        $('#pcGroup_name').val(record.end_customer_groupName);
                        $('#pcGroup_id').val(record.end_customer_groupId);
                        $('#purchaseCustomer_name').val(
                                record.end_customer_name);
                    },
                    onLoadSuccess : function(data) {
                        if (pcFirst) {
                            $('#purchaseCustomer_id').combogrid('grid')
                                    .datagrid('getPager').pagination("select",
                                            1);
                            pcFirst = false;
                        }
                    },
                    toolbar : '#toolbarPurchaseCustomer',
                });
function searcherPurchaseCustomer(name1) {
    pcFirst = true;
    var queryParams = $('#purchaseCustomer_id').combogrid("grid").datagrid(
            'options').queryParams;
    queryParams.end_customer_name = encodeURIComponent(name1);
    $('#purchaseCustomer_id').combogrid("grid").datagrid('reload');
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

$('#customer_id').combogrid({
    panelHeight : 280,
    panelWidth : 320,
    pagination : true,
    pageSize : 20,
    multiple : false,
    editable : false,
    method : 'post',
    singleSelect : true,
    url : appUrl + '/customer/customerAction!getCustomerList.jspa',
    idField : 'customer_code',
    textField : 'customer_name',
    columns : [ [ {
        field : 'customer_code',
        title : 'Code',
        width : 100
    }, {
        field : 'customer_name',
        title : 'Name',
        width : 200
    } ] ],
});

$.extend($.fn.validatebox.defaults.rules, {
    equalTo : {
        validator : function(value, param) {
            return $(param[0]).val() == value;
        },
        message : '�ֶβ�ƥ��'
    }
});
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
    date = date + month[str[1]] + "-" + str[2] + " " + str[3];
    return date;
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
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1
            + strDate;
    return currentdate;
}
