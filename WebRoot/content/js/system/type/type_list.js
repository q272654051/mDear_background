//删除新闻类型
function delType(id,status){
	$.ajax({
		type: "post",
        url: "columnController/deleteType",
        data: {
        	"id":id,
        	"status":status
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("columnController/getList");
        	}
        },
        error: function(){
        	
        }
	});
}

//转向添加新闻类型界面
function toAddType(){
	var width = window.width;
	var height = window.height;
	var url = "columnController/getType";
	$(".content-wrapper").load(url);
}

//修改新闻类型
function editType(id){
	var width = window.width;
	var height = window.height;
	var url = "columnController/getType?id="+id;
	$(".content-wrapper").load(url);
}

$(function() {
	//初始化表格
	$('#data_table').DataTable({
		"paging" : true,  //分页
		"lengthChange" : false,  //每夜显示条数可变
		"searching" : true,  //在结果集上搜索
		"ordering" : false,  //排序图标
		"info" : true, //
		"autoWidth" : true  //自适应宽度
	});
});