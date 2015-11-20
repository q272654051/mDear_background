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

function toAddRole(){
	$(".content-wrapper").load("roleController/getRole");
}

function editRole(id){
	$(".content-wrapper").load("roleController/getRole?id="+id);
}

function exit(){
	var url = "roleController/getRoleList";
	$(".content-wrapper").load(url);
}

function addRole(){
	$.ajax({
		type: "post",
        url: "roleController/addRole",
        data:  $("#roleForm").serialize(),
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("roleController/getRoleList");
        	}else{
        		alert(data['msg']);
        	}
        },
        error: function(){
        	
        }
	})
}
function delRole(id){
	$.ajax({
		type: "post",
        url: "roleController/deleteRole",
        data:  {
        	"id":id
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("roleController/getRoleList");
        	}else{
        		alert(data['msg']);
        	}
        },
        error: function(){
        	
        }
		
	});
}
