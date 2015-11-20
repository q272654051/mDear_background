function folder(id){
	var str = '#menu_'+id;
	var menu = $(str);
	menu.css('display','block');
}
$(function() {
	//初始化表格
	$('#data_table').DataTable({
		"paging" : true,  //分页
		"lengthChange" : false,  //每夜显示条数可变
		"searching" : false,  //在结果集上搜索
		"ordering" : false,  //排序图标
		"info" : false, //
		"autoWidth" : false  //自适应宽度
	});
});