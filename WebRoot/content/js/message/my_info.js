//处理未读消息
function unmessage(newsid){
	$.ajax({
		type: "post",
        url: "messageController/updateStatus",
        data: {
        	"newsid":newsid,
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("messageController/getMessage");
        	}else{
        		alert("无未读消息");
        	}
        },
	});
}

//显示未读消息数
$(function() {
	$.ajax({
				type : "post",
				url : "messageController/unMessage",
				data : $("#userForm").serialize(),
				dataType : "json",
				async : false,
				success : function(data) {
					if (data['success'] == 'true') {
						var list = data['data'];
						$("#messagecount").html("您有" + list.length + "条消息未读！");
						$("#unmessagecount").html(list.length);
						var text = $("#newsid");
						if(data['sex']=='0'){
							for (var i = 0; i < list.length; i++) {
								text.append("<a onclick='unmessage("
										+ "\""
										+ list[i].newsid
										+ "\""
										+ ")'><div class='pull-left' ><img src='dist/img/boy.jpg' class='img-circle' alt='User Image' /></div><h4>"
										+ list[i].newsid + "</h4>" + "<p>"
										+ list[i].message + "</p></a>");
							}
						} else{
							for (var i = 0; i < list.length; i++) {
								text.append("<a onclick='unmessage("
										+ "\""
										+ list[i].newsid
										+ "\""
										+ ")'><div class='pull-left' ><img src='dist/img/gile.jpg' class='img-circle' alt='User Image' /></div><h4>"
										+ list[i].newsid + "</h4>" + "<p>"
										+ list[i].message + "</p></a>");
							}
						}
					}
				},
				error : function(data) {
				}
			});
});