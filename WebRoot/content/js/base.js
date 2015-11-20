//全选
function checkall(){
	if($("input[name=ckall]").is(':checked')){
		$("input[name='ckevery']").each(function(){
			$(this).prop("checked",true);
		})
	}else{
		$("input[name='ckevery']").each(function(){
			$(this).prop("checked",false);
		})
	}
}
//返回首页
function backpage() {
	var url = "loginController/homepage";
	$(".content-wrapper").load(url);
}

