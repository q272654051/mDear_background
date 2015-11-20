var ue = UE.getEditor('editor');
var city_url = "cityController/getChildNodes";
var lable_url="lableController/getChildNodes";
var type_url = "userColumnController/getChildNodes"
var col_url = "columnController/getChildNodes";
var self_url = "newsController/toAdd";
KindEditor.ready(function (K) {
	 var editor = K.editor({
	 		uploadJson : 'js/kindeditor-4.1.10/jsp/upload_json.jsp',
            fileManagerJson : 'js/kindeditor-4.1.10/jsp/file_manager_json.jsp',
			allowFileManager : true
		});
       K('#image1').click(function () {
           editor.loadPlugin('image', function () {
               editor.plugin.imageDialog({
            	   showRemote: false,
                   imageUrl: K('#url1').val(),
                   clickFn: function (url, title, width, height, border, align) {
                       K('#url1').val(url);
                       K("#fmimg").attr("src",url);
                       editor.hideDialog();
                   }
               });
           });
       });
   });
$(function(){
	ue.addListener("ready", function () {
		var val = $("#cont").text();
		ue.setContent(val);
	});
	var lable_id = $("select[name='lable']").val();
	var city_id=$("select[name='province']").val();
	var type_id = $("select[name='type']").val();
	cascade(lable_id,lable_url,lable_cascade_success);
	cascade(city_id,city_url,city_cascade_success);
	cascade(type_id,type_url,user_cascade_success);
	cascade(type_id,col_url,type_cascade_success);
	
	$("select[name='lable']").change(function(){
		var lable_id = this.value;
		$("select[name='smalllable']").empty();
		cascade(lable_id,lable_url,lable_cascade_success);
	});
	$("select[name='province']").change(function(){
		var city_id = this.value;
		var city_text = $(this).find("option:selected").text();
		$("select[name='city']").empty();
		if(city_text.indexOf('市') != -1){
			$("select[name='city']").prepend("<option value="+city_id+">"+city_text+"</option>");
		}else{
			cascade(city_id,city_url,city_cascade_success);
		}
	});
	$("select[name='type']").change(function(){
		var type_id = this.value;
		$("select[name='shenheid']").empty();
		$("select[name='smalltype']").empty();
		cascade(type_id,type_url,user_cascade_success);
		cascade(type_id,col_url,type_cascade_success);
	});
});
//新增
function addNews(status){
	$("#status").val(status);
	$('#cont').val(ue.getContentTxt());
	$.ajax({
		type: "post",
        url: "newsController/saveOrUpdate",
        data: $("#newsForm").serialize(),
        dataType: "json",
        success: function (data) {
        	if(data['success']){
        		var url="newsController/getNewsListByUser";
        		$(".content-wrapper").load(url);
        	}
        },
        error: function(){
        }
	});
}

//菜单级联
function cascade(id,url,fun){
	$.ajax({
		type: "post",
        url: url,
        data: {
        	id:id
        },
        dataType: "json",
        success: fun,
        error: function(data){
        	
        }
	});
}

//城市级联成功调用
function city_cascade_success(data){
	var city = $('#cityhid').val();
	for(var i = 0; i < data.length; i++){
		if(data[i].id == city){
			$("select[name='city']").prepend("<option value="+data[i].id+" selected>"+data[i].name+"</option>");
		}else{
			$("select[name='city']").prepend("<option value="+data[i].id+">"+data[i].name+"</option>");
		}
	}
}
//标签级联成功调用
function lable_cascade_success(data){
	var smalllable = $('smalllable').val();
	for(var i = 0; i < data.length; i++){
		if(data[i].id == smalllable){
			$("select[name='smalllable']").prepend("<option value="+data[i].id+" selected>"+data[i].lable+"</option>");
		}else{
			$("select[name='smalllable']").prepend("<option value="+data[i].id+">"+data[i].lable+"</option>");
		}
	}
}
//审核人级联成功调用
function user_cascade_success(data){
	var shenheid = $('#shenheidhid').val();
	for(var i = 0; i < data.length; i++){
		if(data[i].id == shenheid){
			$("select[name='shenheid']").prepend("<option value="+data[i].id+" selected>"+data[i].userName+"</option>");
		}else{
			$("select[name='shenheid']").prepend("<option value="+data[i].id+">"+data[i].userName+"</option>");
		}
	}
}
//新闻类型级联成功调用
function type_cascade_success(data){
	var smalltype = $('#smalltypehid').val();
	for(var i = 0; i < data.length; i++){
		if(data[i].id == smalltype){
			$("select[name='smalltype']").prepend("<option value="+data[i].id+" selected>"+data[i].columnName+"</option>");
		}else{
			$("select[name='smalltype']").prepend("<option value="+data[i].id+">"+data[i].columnName+"</option>");
		}
	} 
}
//初始化
function init(){
	
}

