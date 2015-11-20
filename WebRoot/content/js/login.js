var url = "loginController/tologin";
function login(){
	var btn = $('#loginbtn');
	$.ajax({
		type: "post",
        url: url,
        data: $("#userForm").serialize(),
        dataType: "json",
        beforeSend:function (){
        	btn.removeClass('btn-primary').addClass('btn-default');
        	btn.attr('disabled','disabled');
        	btn.text('请稍后...');
        },
        success: function(data){
        	if(data['success']){
        		window.location.href='loginController/loginSuccess';
        	}else{
        		alert(data['msg']);
        		btn.removeAttr('disabled');
        		btn.removeClass('btn-default').addClass('btn-primary');
        		btn.text('登录');
        	}
        },
        error: function(data){
        	alert(data['msg']);
    		btn.removeAttr('disabled');
    		btn.removeClass('btn-default').addClass('btn-primary');
    		btn.text('登录');
        }
	});
}
