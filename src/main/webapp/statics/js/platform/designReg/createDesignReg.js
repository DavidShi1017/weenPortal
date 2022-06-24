$(document).ready(function() {
    $('#hideFrame').bind('load', promgtMsg);
});

var drTypes = [ {
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

var datas = [ {
    label : 'Opportunity',
    value : 0
}, {
    label : 'Design_in',
    value : 1
} ];

function cancel() {
    window.parent.closeMaintWindow();
}

$('#datagrid')
        .datagrid(
                {
                    iconCls : 'icon-list',
                    title : '',
                    url : appUrl
                            + '/designRegAction!getDesignRegDetailList.jspa?main_id='
                            + $('#id').val(),
                    loadMsg : 'Loading...',
                    singleSelect : true,
                    nowrap : true,
                    checkbox : true,
                    required : true,
                    rownumbers : true,
                    height : 240,
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
                                title : 'ID',
                                width : 60,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'material_name',
                                title : 'BookPart',
                                width : 150,
                                align : 'center',

                            },
                            {
                                field : 'material_id',
                                title : '12NC',
                                width : 150,
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
                                hidden : true
                            },
                            {
                                field : 'main_id',
                                title : 'Vw1mid',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'price',
                                title : 'Price',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 4
                                    }
                                }
                            },
                            {
                                field : 'equip_usage',
                                title : 'Pcs/Set',
                                width : 100,
                                align : 'center',
                                editor : 'numberbox'
                            },
                            {
                                field : 'total_qty',
                                title : 'Total Qty',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        disabled : true
                                    }
                                },
                                formatter : function(value, row, rec) {
                                    var flag = row.total_qty;
                                    if (flag == '' || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag * 1;// Math.floor(flag);
                                    }
                                }
                            },
                            {
                                field : 'value',
                                title : 'Value',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'numberbox',
                                    options : {
                                        precision : 4,
                                        disabled : true
                                    }
                                },
                                formatter : function(value, row, rec) {
                                    var flag = row.value;
                                    if (flag == '' || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag * 1;
                                    }
                                }
                            },
                            {
                                field : 'start_dateStr',
                                title : 'Registration Date',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'datebox',
                                    options : {
                                        disabled : true
                                    }
                                }
                            },
                            {
                                field : 'end_dateStr',
                                title : 'Expiry Date',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'datebox',
                                    options : {
                                        disabled : true
                                    }
                                }
                            },
                            {
                                field : 'dr_type',
                                title : 'DR Type',
                                width : 250,
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
                                    } else {
                                    	return "";
                                    }
                                },
                                hidden : ($('#forWho').val()=='Disti')
                            },
                            {
                                field : 'project_state',
                                title : 'Project Status',
                                width : 100,
                                align : 'center',
                                editor : {
                                    type : 'combobox',
                                    options : {
                                        valueField : 'value',
                                        textField : 'label',
                                        panelHeight : 'auto',
                                        editable : false,
                                        data : datas,
                                        onSelect : function(rec) {
                                            var editors = $('#datagrid')
                                                    .datagrid('getEditors',
                                                            dateEditIndex);
                                            var startEditor = editors[4];
                                            var endEditor = editors[5];
                                            var pStateEditor = editors[7];
                                            var primaryProStateEditor = editors[9];
                                            var dateEditState = $(
                                                    primaryProStateEditor.target)
                                                    .val() * 1;
                                            if ((dateEditState > $(this)
                                                    .combobox('getValue'))
                                                    || ((dateEditState * 1 + 1) < $(
                                                            this).combobox(
                                                            'getValue'))) {
                                                
                                                $(this).combobox('setValue',
                                                        dateEditState);
                                                var addCount = ($(this)
                                                        .combobox('getValue') * 1 + 1) * 3;
                                                var startdate = $(
                                                        startEditor.target)
                                                        .datebox('getValue');
                                                var d = new Date(startdate);
                                                d.setMonth(d.getMonth()
                                                        + addCount * 1);
                                                var endday = d
                                                        .format('yyyy-MM-dd');
                                                $(endEditor.target).datebox(
                                                        'setValue', endday);
                                                var rows = $("#datagrid")
                                                        .datagrid("getRows");
                                                if (rows != undefined
                                                        && rows.length != 0) {
                                                    var max = endday;
                                                    for (var i = 0; i < rows.length; i++) {
                                                        if (i == dateEditIndex) {
                                                            continue;
                                                        }
                                                        if (rows[i].end_dateStr > max) {
                                                            max = rows[i].end_dateStr;
                                                        }
                                                    }
                                                    $('#end_date').val(max);
                                                } else {
                                                    $('#end_date').val(endday);
                                                }
                                            } else {
                                                var addCount = ($(this)
                                                        .combobox('getValue') * 1 + 1) * 3;
                                                var startdate = $(
                                                        startEditor.target)
                                                        .datebox('getValue');
                                                var d1 = new Date(startdate);
                                                d1.setMonth(d1.getMonth()
                                                        + addCount * 1);
                                                var endday = d1
                                                        .format('yyyy-MM-dd');
                                                $(endEditor.target).datebox(
                                                        'setValue', endday);
                                                var rows = $("#datagrid")
                                                        .datagrid("getRows");
                                                if (rows != undefined
                                                        && rows.length != 0) {
                                                    var max = endday;
                                                    for (var i = 0; i < rows.length; i++) {
                                                        if (i == dateEditIndex) {
                                                            continue;
                                                        }
                                                        if (rows[i].end_dateStr > max) {
                                                            max = rows[i].end_dateStr;
                                                        }
                                                    }
                                                    $('#end_date').val(max);
                                                } else {
                                                    $('#end_date').val(endday);
                                                }
                                            }

                                        }
                                    }
                                },
                                formatter : function(value, row, rec) {
                                    var flag = row.project_state;
                                    if (flag == 0) {
                                        return "Opportunity";
                                    } else if (flag == 1) {
                                        return "Design_in";
                                    }
                                }
                            },
                            {
                                field : 'state',
                                title : 'Status',
                                width : 100,
                                align : 'center',
                                formatter : function(value, row, rec) {
                                    var flag = row.state;
                                    if (flag == 0) {
                                        return "Pending";
                                    } else if (flag == 1) {
                                        return "<font color='green'>Approve</font>";
                                    } else if (flag == 2) {
                                        return "<font color='red'>Reject</font>";
                                    }
                                },
                                hidden : true
                            }, {
                                field : 'cus_remark',
                                title : 'Disti Remark',
                                width : 200,
                                align : 'center',
                                editor : 'text'
                            }, {
                                field : 'primaryProState',
                                title : 'primaryProState',
                                width : 200,
                                align : 'center',
                                editor : 'text',
                                hidden : true,
                            }, {
                                field : 'cost',
                                title : 'Cost',
                                width : 100,
                                align : 'center',
                                hidden : true
                            }, {
                                field : 'moq',
                                title : 'Moq',
                                width : 100,
                                align : 'center',
                                hidden : true
                            }, {
                                field : 'pbMpp',
                                title : 'pbMpp',
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
                        dateEditIndex = rowIndex;
                        $('#datagrid').datagrid('beginEdit', rowIndex);
                        setEditing(rowIndex);
                        var editors = $('#datagrid').datagrid('getEditors',
                                rowIndex);
                        dateEditState = editors[6].target.combobox('getValue');
                    },
                });

// Value=runrate*Price*pcs set
function setEditing(rowIndex) {
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    var priceEditor = editors[0];
    var setsEditor = editors[1];
    var totalqtyEditor = editors[2];
    var valueEditor = editors[3];
    priceEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    setsEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    totalqtyEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    valueEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        }
    });
    function calculate() {
        var runrate = $('#usage_amount').val();
        var price = priceEditor.target.val();
        var sets = setsEditor.target.val();
        var value = price * sets * runrate;
        var totalqty = runrate * sets;
        value = value.toFixed(4);
        $(valueEditor.target).val(value);
        $(totalqtyEditor.target).val(totalqty);
    }
    ;
}

$('#usage_amount').change(function() {
    var value = $('#usage_amount').val();
    var rows = $("#datagrid").datagrid("getRows");
    if (rows != undefined && rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            $("#datagrid").datagrid("endEdit", i);
            rows[i].price = rows[i].price;
            rows[i].equip_usage = rows[i].equip_usage;
            rows[i].project_state = rows[i].project_state;
            rows[i].cus_remark = rows[i].cus_remark;

            var amount = rows[i].price * rows[i].equip_usage * value;
            amount = amount.toFixed(4);
            var totalqty = value * rows[i].equip_usage;
            rows[i].value = amount;
            totalqty = totalqty.toFixed(4);
            rows[i].total_qty = totalqty;

            $("#datagrid").datagrid("refreshRow", i);
        }
    }
});
//
var ps = [];
function add() {
    if ($('#disti_branch').combobox('getValue') == '') {
        $.messager.alert('Tips', "Please select Disti Branch first.", 'error');
        return;
    }
    ps = [];
    var rows = $("#datagrid").datagrid("getRows");
    for (var i = 0; i < rows.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
        ps.push(rows[i].material_id);
    }
    initMaintAccount(false, '800', '450', 'Product',
            '/productAction!toSearchProductForUse.jspa?isDRItem=Y&currency_code='
                    + $('#currency_code').val() + '&office_id='
                    + $('#office_id').val() + '&customer_id='
                    + $('#payerTo').val(), 100, 20);
}

function returnProduct(proJson) {
    var productStr = "[" + proJson + "]";
    var pJson = eval(productStr);
    for (var i = 0; i < pJson.length; i++) {
        var product = pJson[i];
        var sameFlag = false;
        for (var j = 0; j < ps.length; j++) {
            if (product.material_id == ps[j]) {
                sameFlag = true;
                break;
            }
        }
        if (sameFlag) {
            continue;
        }

        $('#datagrid').datagrid('appendRow', {
            drNum : $('#drNum').val(),
            main_id : $('#id').val(),
            material_name : product.material_name,
            material_id : product.material_id,
            price : product.pbPrice,
            equip_usage : '',
            start_dateStr : $('#start_date').val(),
            end_dateStr : $('#end_date').val(),
            project_state : 0,
            state : 0,
            cus_remark : '',
            primaryProState : 0,
            cost : product.cost,
            moq : product.moq,
            pbMpp : product.pbPrice,
        });
    }
    $("#hiddenWin").window('close');
}

var delDesignReg = "0";
function dele() {
    var row = $('#datagrid').datagrid('getSelected');
    if (row.length == 0) {
        $.messager.alert('Tips', 'Please select the data item!', 'warning');
        return;
    }
    $.messager.confirm('Confirm', 'Confirmed about  delete?', function(r) {
        if (r) {
            {
                if (row.id != undefined) {
                    delDesignReg += "," + row.id;
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
            window.parent.search();
        });
    }
}

$('#disti_branch')
        .combogrid(
                {
                    panelHeight : 365,
                    panelWidth : 330,
                    pagination : true,
                    pageSize : 10,
                    multiple : false,
                    editable : false,
                    method : 'post',
                    singleSelect : true,
                    url : appUrl
                            + '/customer/customerAction!getDistiBranchList.jspa?disti_name='
                            + encodeURIComponent($('#cus_groupId').val()),
                    idField : 'disti_branch',
                    textField : 'disti_branch',
                    columns : [ [ {
                        field : 'disti_branch',
                        title : 'Disti Branch',
                        width : 200
                    }, {
                        field : 'pricing_region',
                        title : 'Pricing Region',
                        width : 100
                    } ] ],
                    onSelect : function(index, record) {
                        ;
                        $('#office_id').val(record.pricing_region);
                        $('#currency_code').val(record.currency);
                        $('#payerTo').val(record.payer_to);

                        var itemId = "";
                        if ($('#currency_code').val() == "USD") {
                            itemId = 14211;
                        } else if ($('#currency_code').val() == "EUR") {
                            itemId = 14212;
                        }
                        $
                                .ajax({
                                    type : "post",
                                    url : appUrl
                                            + '/designRegAction!getDictOfWeen.jspa?dictTypeId=562&itemId='
                                            + itemId,
                                    success : function(result) {
                                        $('#total_type').val(
                                                result[0].itemValue);
                                    }
                                });
                    },
                    toolbar : '#toolbarDistiBranch',
                });
function searcherDistiBranch(name1) {
    var queryParams = $('#disti_branch').combogrid("grid").datagrid('options').queryParams;
    queryParams.disti_branch = encodeURIComponent(name1);
    $('#disti_branch').combogrid("grid").datagrid('reload');
}

function submit() {
    if ($('#disti_branch').combobox('getValue') == '') {
        $.messager.alert('Tips', "Disti Branch is not completed yet!", 'error');
        return;
    }
    if ($('#endCus_id').combobox('getValue') == "") {
        $.messager.alert('Tips', "End Customer is not completed yet!", 'error');
        return;
    }
    if ($('#project_name').val() == '') {
        $.messager.alert('Tips', "Project is not completed yet!", 'error');
        return;
    }
    if ($('#ec_contact').val() == '') {
        $.messager.alert('Tips', "EC Contact is not completed yet!", 'error');
        return;
    }
    if ($('#tel').val() == '') {
        $.messager
                .alert('Tips', "EC Tel Number is not completed yet!", 'error');
        return;
    }
    if ($('#usage_amount').val() == "") {
        $.messager.alert('error', " Runrate is not completed yet!", 'error');
        return;
    }
    if ($('#equip_type').combobox('getValue') == "") {
        $.messager
                .alert('error', " Application is not completed yet!", 'error');
        return;
    }

    var msg = "";
    if ($('#usage_amount').val() * 1 < 1000) {
        msg += 'runrate value too small;';
    }

    var url = undefined;
    if ($('#id').val() == '0' || $('#id').val() == '') {
        url = "/designRegAction!createDesignReg.jspa";
    } else {
        url = "/designRegAction!updateDesignReg.jspa";
    }
    var rows = $("#datagrid").datagrid("getRows");
    if (rows == undefined || rows.length == 0) {
        $.messager.alert('Tips', "Please fill in items info of DR!", 'error');
        return;
    }
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        $("#datagrid").datagrid("endEdit", i);
        if (rows[i].price * 1 <= 0) {
            $.messager.alert('error', "Rows " + (i * 1 + 1)
                    + " Price is not completed yet!", 'error');
            return;
        }
        if (rows[i].equip_usage * 1 <= 0) {
            $.messager.alert('error', "Rows " + (i * 1 + 1)
                    + " Pcs/Set is not completed yet!", 'error');
            return;
        }
        if ($('#forWho').val() != 'Disti') {
        	if (rows[i].dr_type == '' || rows[i].dr_type == undefined || rows[i].dr_type == '0') {
                $.messager.alert('error', "Rows " + (i * 1 + 1)
                        + " Dr Type is not selected yet!", 'error');
                return;
        	}
        }
        total += rows[i].value * 1;
    }
    $('#total_amount').val(total);
    if ($('#total_type').val() != ""
            && total * 1 < ($('#total_type').val() * 1)) {
        $.messager.alert('error', "Total amount is less than "
                + $('#total_type').val() + $('#currency_code').val() + "!",
                'error');
        return;
    }
    var designRegDetailJson = [];

    for (var i = 0; i < rows.length; i++) {
        if (rows[i].equip_usage * 1 > 10) {
            msg += "Rows" + (i * 1 + 1) + " PCs/Set value too big;<BR>";
        }
        // DR行项目的value大于$1M
        if (rows[i].value * 1 > 1000000) {
        	msg += "Rows" + (i * 1 + 1) + " Please confirm that Bookpart" + rows[i].material_id + " value has exceeded one million;<BR>";
        }
        // DR 行项目上的 Price 大于$10
        if (rows[i].price * 1 > 10) {
        	msg += "Rows" + (i * 1 + 1) + " Please confirm that Bookpart" + rows[i].material_id + " price has exceeded 10;<BR>";
        }
        row_no = (i * 1 + 1) * 10;
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        var row = "{" + "id:" + "'" + rows[i].id + "'," + "row_no:" + "'"
                + row_no + "'," + "material_name:" + "'"
                + rows[i].material_name + "'," + "material_id:" + "'"
                + rows[i].material_id + "'," + "drNum:" + "'" + rows[i].drNum
                + "'," + "main_id:" + "'" + rows[i].main_id + "'," + "price:"
                + "'" + rows[i].price + "',equip_usage:'" + rows[i].equip_usage
                + "',value:'" + rows[i].value + "',dr_type:'" + rows[i].dr_type + "',project_state:'"
                + rows[i].project_state + "',equip_type:'" + rows[i].equip_type
                + "',start_date:'" + rows[i].start_dateStr + "',end_date:'"
                + rows[i].end_dateStr + "',cost:'" + rows[i].cost + "',moq:'"
                + rows[i].moq + "',pbMpp:'" + rows[i].pbMpp
                + "',project_name:'" + rows[i].project_name + "',cus_remark:'"
                + rows[i].cus_remark + "'}";

        designRegDetailJson.push(row);
    }
    $.messager.confirm('Confirm', msg + ' Confirm to submit?', function(r) {
        if (r) {
            $('#designRegDetailJson').val("[" + designRegDetailJson + "]");
            $('#delDesignReg').val("(" + delDesignReg + ")");
            var form = window.document.forms[0];
            form.action = appUrl + url;
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

// VU6K?M;'
var ecFirst = true;
$('#endCus_id')
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
                    idField : 'end_customer_id',
                    textField : 'end_customer_name',
                    columns : [ [ {
                        field : 'end_customer_groupName',
                        title : 'Customer Group',
                        width : 100,
                        hidden : true
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
                        width : 100
                    } ] ],
                    onSelect : function(index, record) {
                        $('#endCus_groupName').val(
                                record.end_customer_groupName);
                        $('#endCus_groupId').val(record.end_customer_groupId);
                        $('#endCus_groupName').val(record.city);
                    },
                    onLoadSuccess : function(data) {
                        if (ecFirst) {
                            $('#endCus_id').combogrid('grid').datagrid(
                                    'getPager').pagination("select", 1);
                            ecFirst = false;
                        }
                    },
                    toolbar : '#toolbarEndCustomer',
                });

function searcherEndCustomer(name1) {
    ecFirst = true;
    var queryParams = $('#endCus_id').combogrid("grid").datagrid('options').queryParams;
    queryParams.end_customer_name = encodeURIComponent(name1);
    $('#endCus_id').combogrid("grid").datagrid('reload');
}

$('#equip_type')
        .combobox(
                {
                    url : appUrl
                            + '/designRegAction!getDictOfWeen.jspa?dictTypeId=555',
                    valueField : 'itemId',
                    textField : 'itemName',
                    multiple : false,
                    editable : false,
                    required : false,
                    width : 173,
                    onSelect : function(r) {
                        if (r.itemName.indexOf("-") <= -1) {
                            $(this).combobox('setValue', '');
                            $.messager.alert('Tips',
                                    "Please select item under the category!",
                                    'error');
                            // return;
                        }
                    }
                });

function applyEC() {
    initMaintAccount(false, '850', '400', 'Reg End Customer',
            '/endCustomerAction!toCreateEndCustomer.jspa', 100, 30);
}

function closeMaintWindow() {
    $("#hiddenWin").window('close');
}
// 44=(40?Z6TOs
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

$.extend($.fn.validatebox.defaults.rules, {
    equalTo : {
        validator : function(value, param) {
            return $(param[0]).val() == value;
        },
        message : 'WV6N2;F%Ed'
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