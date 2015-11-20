var col_url = "columnController/getChildNodes";
$(function(){
	var type_id = $("select[name='type']").val();
	cascade(type_id,col_url,type_cascade_success);
	$("select[name='type']").change(function(){
		var type_id = this.value;
		$("select[name='smalltype']").empty();
		cascade(type_id,col_url,type_cascade_success);
	});
});
//级联
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
function type_cascade_success(data){
	for(var i = 0; i < data.length; i++){
		$("select[name='smalltype']").prepend("<option value="+data[i].id+">"+data[i].columnName+"</option>");
	}
}

