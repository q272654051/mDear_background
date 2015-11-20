//删除用户
function delUser(id,status){
	$.ajax({
		type: "post",
        url: "userController/deleteUser",
        data: {
        	"id":id,
        	"status":status
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("userController/getUserList?reload=1");
        	}
        },
        error: function(){
        	
        }
	});
}
//转向添加或者修改用户界面
function toAddUser(){
	var width = window.width;
	var height = window.height;
	var url = "userController/getUser";
	$(".content-wrapper").load(url);
}
//修改用户
function editUser(id){
	var width = window.width;
	var height = window.height;
	var url = "userController/getUser?id="+id;
	$(".content-wrapper").load(url);
}
//返回用户界面
function exit(){
	var url = "userController/getUserList?reload=1";
	$(".content-wrapper").load(url);
}
//添加用户
function addUser(){
	$.ajax({
		type: "post",
        url: "userController/addUser",
        data:  $("#userForm").serialize(),
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("userController/getUserList?reload=1");
        	}else{
        		alert(data['msg']);
        	}
        },
        error: function(){
        	
        }
	})
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