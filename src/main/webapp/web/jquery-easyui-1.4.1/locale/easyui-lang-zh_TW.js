if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = '��';
	$.fn.pagination.defaults.afterPageText = '��{pages}�';
	$.fn.pagination.defaults.displayMsg = '�@ʾ{from}��{to},��{total}ӛ�';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '����̎��Ո�Դ�������';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = '�_��';
	$.messager.defaults.cancel = 'ȡ��';
}
$.map(['validatebox','textbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = 'ԓݔ��헞��ݔ�';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'Ոݔ����Ч������]����ַ';
	$.fn.validatebox.defaults.rules.url.message = 'Ոݔ����Ч��URL��ַ';
	$.fn.validatebox.defaults.rules.length.message = 'ݔ������L�ȱ�횽��{0}��{1}֮�g';
	$.fn.validatebox.defaults.rules.remote.message = 'Ո�����˙�λ';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['��','һ','��','��','��','��','��'];
	$.fn.calendar.defaults.months = ['һ��','����','����','����','����','����','����','����','����','ʮ��','ʮһ��','ʮ����'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '����';
	$.fn.datebox.defaults.closeText = '�P�]';
	$.fn.datebox.defaults.okText = '�_��';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
if ($.fn.datetimespinner){
	$.fn.datetimespinner.defaults.selections = [[0,4],[5,7],[8,10],[11,13],[14,16],[17,19]]
}
