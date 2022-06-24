
$(document).ready(function() {
	loadGrid(); 
	
});

function loadGrid() {
	
	$('#processdefinition_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : height,
						striped : true,
						url : appUrl + '/allRoleAction!serachRoles.jspa',

						loadMsg : 'loading...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : '���',
									field : 'roleid',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'rolename',
									title : '��������',
									align : 'center',
									width : 200,
									sortable : true
								},{
									field : 'lastmodify',
									title : '����޸�ʱ��',
									align : 'center',
									width : 140,
									sortable : true						
								} ] ]
					});
					
	// ��ҳ�ؼ�
	var p = $('#processdefinition_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}
function onSelectRole(){
	var rows = $('#processdefinition_list').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  no selected rows!');
		return;
	}

	window.parent.settask_id(rows,window.parent.task,window.parent.win);
	
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
    var week = new Array();
    week["Mon"] = "һ";
    week["Tue"] = "��";
    week["Wed"] = "��";
    week["Thu"] = "��";
    week["Fri"] = "��";
    week["Sat"] = "��";
    week["Sun"] = "��";


    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2] + " " + str[3];
    return date;
}
