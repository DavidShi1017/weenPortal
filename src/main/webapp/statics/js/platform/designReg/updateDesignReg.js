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

var datas;

datas = [ {
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
                    singleSelect : false,
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
                                title : 'PK',
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
                                title : 'DR number',
                                width : 100,
                                align : 'center',
                                hidden : true
                            },
                            {
                                field : 'main_id',
                                title : 'mainid',
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
                                    var flag = row.equip_usage;
                                    var project_state = row.project_state;
                                    var runrate = $('#usage_amount').numberbox(
                                            'getValue') * 1;
                                    if (project_state == 2 || project_state == 'Design_win') {
                                    	runrate = row.usage_amount;
                                    }
                                    if (flag == '' || flag == undefined
                                            || runrate == ''
                                            || runrate == undefined) {
                                        return "";
                                    } else {
                                        return (flag * runrate);
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
                                hidden : ($('#forWho').val() == 'Disti')
                            },
							{
								field : 'dr_typeStr',
								title : 'DR Type',
								width : 100,
								align : 'center',
								hidden : ($('#forWho').val() != 'Disti')
							},
							{
								field : 'drtype_def',
								title : 'DR Type Definition',
								width : 240,
								align : 'center',
								hidden : ($('#forWho').val() != 'Disti')
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
                                        // disabled:$(this).combobox('getValue'),
                                        onSelect : function(rec) {
                                            var editors = $('#datagrid')
                                                    .datagrid('getEditors',
                                                            dateEditIndex);
                                            var startEditor = editors[4];
                                            var endEditor = editors[5];
                                            var pStateEditor = editors[7];
                                            var remarkEditor = editors[8];
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

                                                var d = new Date();
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

                                                var d1 = new Date();
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
                                    } else if (flag == 2) {
                                        return "Design_win";
                                    } else if (flag == 9) {
                                        return "Dw_Reject";
                                    } else if (flag == 'Design_win') {
                                        return "Design_win";
                                    }
                                }
                            },
                            {
                                field : 'cus_remark',
                                title : 'Disti Remark',
                                width : 200,
                                align : 'center',
                                editor : 'text',
                                formatter : function(value, row, rec) {
                                    var flag = row.cus_remark;
                                    if (flag == 'undefined'
                                            || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag;
                                    }
                                }
                            },
                            {
                                field : 'weencomments',
                                title : 'Ween Comments',
                                width : 200,
                                align : 'center',
                                editor : 'text',
                                hidden : true,
                                formatter : function(value, row, rec) {
                                    var flag = row.weencomments;
                                    if (flag == 'undefined'
                                            || flag == undefined) {
                                        return "";
                                    } else {
                                        return flag;
                                    }
                                }
                            }, {
                                field : 'primaryProState',
                                title : 'primaryProState',
                                width : 200,
                                align : 'center',
                                editor : 'text',
                                hidden : true,
                            } ] ],
                    toolbar : [ "-", {
                        text : 'Import',
                        iconCls : 'icon-up',
                        handler : function() {
                            importExcel();
                        }
                    }, ],
                    onLoadSuccess : function() {
                        if ($('#forWho').val() == 'Disti' || $('#isAllDW').val() == 1) {
                            $('div.datagrid-toolbar a').eq(0).hide();
                        }
                        var rows = $("#datagrid").datagrid("getRows");
                        for (var i = 0; i < rows.length; i++) {
                            var ss = rows[i].project_state
                            if (ss == 2) {
                                $('#mp_schedule').attr("disabled", "disabled");
                            }
                        }
                    },
                    onClickCell : function(rowIndex, field, value) {
                        var rows = $("#datagrid").datagrid("getRows");
                        beginEditing(rowIndex, field, value);
                        dateEditIndex = rowIndex;
                        $('#datagrid').datagrid('beginEdit', rowIndex);
                        setEditing(rowIndex, rows[rowIndex].state);
                        var editors = $('#datagrid').datagrid('getEditors',
                                rowIndex);
                        dateEditState = editors[7].target.combobox('getValue');
                    },
                });

function importExcel() {
    var row = $('#datagrid').datagrid('getSelections');
    if (row.length <= 0) {
        $.messager.alert('Tips', 'Please select the data item!', 'warning');
        return;
    }

    for (var i = 0; i < row.length; i++) {
        var state = row[i].state
        var pstate = row[i].project_state
        if (pstate == 2) {
            $.messager.alert('Tips', 'Design Win state can not upload!',
                    'warning');
            return;
        }
        if (state == 3) {
            $.messager.alert('Tips', 'DR already expired and not editable!', 'warning');
            return;
        }
        else if (state != 1) {
            $.messager.alert('Tips', 'All approve state upload!', 'warning');
            return;
        }
    }

    initMaintAccount(false, '700', '400', 'Import',
            '/designRegAction!importExcel.jspa', 100, 100);
}

function returnUploadFile(fileStr, fileName) {
    var filePath = fileStr;
    var paths = fileName.split("\\");
    var fileName = paths[paths.length - 1];
    $('#upload_file_name').val(fileName);
    $('#file_path').val(filePath);
    if (fileName != null && fileName != '') {
        $('#isUpload').val('1');
        var selectedIds = ""; 
        var row = $('#datagrid').datagrid('getSelections');
        if (row.length > 0) {
            for (var i = 0; i < row.length; i++) {
            	if (selectedIds.length > 0) {
            		selectedIds = selectedIds + ',';
            	}
            	selectedIds = selectedIds + row[i].id;
            	
            }
            $('#dwId').val(selectedIds);
        }
    }
}

function setEditing(rowIndex, state) {
	
    var editors = $('#datagrid').datagrid('getEditors', rowIndex);
    
    var priceEditor = editors[0];
    var setsEditor = editors[1];
    var totalqtyEditor = editors[2];
    var valueEditor = editors[3];
    var drTypeEditor = editors[6];
    var projectStateEditor = editors[7];
    var remarkEditor = editors[8];
    
    var weencommentsEditor = editors[9];
    
    if($('#forWho').val() == 'Disti' || $('#isAllDW').val() == 1) {
    	$(weencommentsEditor.target).attr('disabled','disabled');    	
    }
    
    
    
    if ($(projectStateEditor.target).combobox('getValue') == 2
            || $(projectStateEditor.target).combobox('getValue') == 'Design_win') {
        $(projectStateEditor.target).combobox('setValue', 'Design_win');
        $(drTypeEditor.target).combobox('disable');
        $(projectStateEditor.target).combobox('disable');
        $(priceEditor.target).numberbox('disable');
        $(setsEditor.target).numberbox('disable');
        $(valueEditor.target).numberbox('disable');
        $(totalqtyEditor.target).numberbox('disable');
        return;
    } else if (state == 3) {
        $(projectStateEditor.target).combobox('disable');
        $(drTypeEditor.target).combobox('disable');
        $(priceEditor.target).numberbox('disable');
        $(setsEditor.target).numberbox('disable');
    }
    priceEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        },
        'change' : function() {
            updateExpireDate();
        }
    });
    setsEditor.target.bind({
        'blur' : function() {
            calculate();
        },
        'mouseleave' : function() {
            calculate();
        },
        'change' : function() {
            updateExpireDate();
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
        },
        'change' : function() {
            updateExpireDate();
        }
    });
    remarkEditor.target.bind({
        'change' : function() {
            updateExpireDate();
        }
    });
    function updateExpireDate() {
        var endEditor = editors[5];
        var pStateEditor = editors[6];
        var d = new Date();
        d.setDate(d.getDate() + 180);
        var endday = d.format('yyyy-MM-dd');
        $(endEditor.target).datebox('setValue', endday);

        var rows = $("#datagrid").datagrid("getRows");
        if (rows != undefined && rows.length != 0) {
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

    function calculate() {
    	if ($(projectStateEditor.target).combobox('getValue') == 2
                || $(projectStateEditor.target).combobox('getValue') == 'Design_win') {
    	    return;
    	}
    	var runrate = parseInt($('#usage_amount').val());
        var price = priceEditor.target.val();
        var sets = setsEditor.target.val();
        var totalqty = runrate * sets;
        var value = price * sets * runrate;
        value = value.toFixed(4);
        $(valueEditor.target).val(value);
        $(totalqtyEditor.target).val(totalqty);
    }
}

$('#usage_amount').change(function() {
    var value = parseInt($('#usage_amount').val());
    $('#usage_amount').val(value);
    var rows = $("#datagrid").datagrid("getRows");
    if (rows != undefined && rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
        	if (rows[i].project_state == 2 || rows[i].project_state == 'Design_win') {
        		continue;
        	}
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
            if (rows[i].state == 1) {
                var d = new Date();
                d.setDate(d.getDate() + 180);
                var endday = d.format('yyyy-MM-dd');
                rows[i].end_dateStr = endday;
            }
            $("#datagrid").datagrid("refreshRow", i);
        }
    }

    var rows1 = $("#datagrid").datagrid("getRows");
    if (rows1 != undefined && rows1.length != 0) {
        var max = rows1[0].end_dateStr;
        for (var i = 0; i < rows1.length; i++) {
            if (rows1[i].end_dateStr > max) {
                max = rows1[i].end_dateStr;
            }
        }
        $('#end_date').val(max);
    }
});

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

function submit() {
    if ($('#usage_amount').val() == "") {
		        $.messager.alert('error', "Rows " + (i * 1 + 1)
                + " Runrate is not completed yet !", 'error');
        return;
    }

    var msg = "";
    if ($('#usage_amount').val() * 1 < 1000) {
        msg += 'runrate value too small;';
    }

    var url = undefined;
    url = "/designRegAction!updateDesignReg.jspa?isUpdate=1";
    var rows = $("#datagrid").datagrid("getRows");
    if (rows == undefined || rows.length == 0) {
        $.messager.alert('Tips', "Please fill in items info of DR?", 'error');
        return;
    }
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
    	var project_state = (rows[i].project_state == '2' || rows[i].project_state == 'Design_win') ? '2'
                : rows[i].project_state;
    	var state = rows[i].state;
        $("#datagrid").datagrid("endEdit", i);
        if (rows[i].price * 1 <= 0) {
            $.messager.alert('error', "Rows " + (i * 1 + 1)
                    + " Price is not completed yet !", 'error');
            return;
        }
        if (rows[i].equip_usage * 1 <= 0) {
            $.messager.alert('error', "Rows " + (i * 1 + 1)
                    + " Pcs/Set is not completed yet !", 'error');
            return;
        }
        if ($('#forWho').val() != 'Disti' && project_state != 2 && state != 3) {
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
                + $('#total_type').val() + $('#currency_code').val() + "��",
                'error');
        return;
    }
    var designRegDetailJson = [];

    if ($('#upload_file_name').val().length > 0 && $('#isUpload').val() == '1') {
        url = url + "&isTurnWin=1";
        $('#old_file_name').val($('#upload_file_name').val());
    }
    
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].equip_usage * 1 > 10) {
            msg += "Rows" + (i * 1 + 1) + " PCs/Set value too big;";
        }
        var project_state = (rows[i].project_state == '2' || rows[i].project_state == 'Design_win') ? '2'
                : rows[i].project_state;
        var design_win_date = "";

        if ($('#upload_file_name').val().length > 0 && $('#isUpload').val() == '1' && isRowChecked(rows[i])) {
            project_state = 2;
        }

        var cus_remark = (rows[i].cus_remark == undefined || rows[i].cus_remark == 'undefined') ? ''
                : rows[i].cus_remark;
        row_no = (i * 1 + 1) * 10;
        var rowIndex = $('#datagrid').datagrid('getRowIndex', rows[i]);
        var row = "{" + "id:" + "'" + rows[i].id + "'," + "row_no:" + "'"
                + rowIndex + "'," + "material_name:" + "'"
                + rows[i].material_name + "'," + "material_id:" + "'"
                + rows[i].material_id + "'," + "drNum:" + "'" + rows[i].drNum
                + "'," + "main_id:" + "'" + rows[i].main_id + "'," + "price:"
                + "'" + rows[i].price + "',equip_usage:'" + rows[i].equip_usage
                + "',value:'" + rows[i].value  + "',dr_type:'" + rows[i].dr_type  
                + "',project_state:'" + project_state + "',start_date:'" + rows[i].start_dateStr
                + "',latest_userId:'" + rows[i].latest_userId + "',end_date:'"
                + rows[i].end_dateStr + "',state:'" + rows[i].state
                + "',cus_remark:'" + cus_remark + "',weencomments:'" + rows[i].weencomments + "'}";

        designRegDetailJson.push(row);
    }
    $.messager.confirm('Confirm', msg + ' Sure to submit?', function(r) {
        if (r) {
            $('#designRegDetailJson').val("[" + designRegDetailJson + "]");
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

$('#equip_type').combobox({
    url : appUrl + '/dictAction!getByCmsTbDictList.jspa?dictTypeId=555',
    valueField : 'itemId',
    textField : 'itemName',
    multiple : false,
    editable : false,
    required : false,
    panelHeight : 120,
    width : 173,
    onSelect : function(r) {
    }
});

$('#dr_type').combobox({
    url : appUrl + '/designRegAction!getDictOfWeen.jspa?dictTypeId=567',
    valueField : 'itemId',
    textField : 'itemName',
    multiple : false,
    editable : false,
    required : false,
    width : 173,
    onSelect : function(r) {
    }
});

function isRowChecked(row) {
	if ($('#dwId').val().length > 0) {
	    var selectIds = $('#dwId').val().split(",");
	    for (var i = 0; i < selectIds.length; i++) {
	    	if (row.id == selectIds[i]) {
	    		return true;
	    	}
	    }
    }
    return false;
}

function applyEC() {
    initMaintAccount(false, '850', '400', 'Reg End Customer',
            '/endCustomerAction!toCreateEndCustomer.jspa', 100, 30);
}

function closeMaintWindow() {
    $("#hiddenWin").window('close');
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