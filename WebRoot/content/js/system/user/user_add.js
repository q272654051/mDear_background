$(function (){
	var user_status = $('#status').val();
	$('input[name=status]').each(function(){
		if($(this).val() == user_status){
			$(this).attr('checked','true');
		}
	});
});