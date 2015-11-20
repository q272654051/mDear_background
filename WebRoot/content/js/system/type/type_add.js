$(function (){
	var user_status = $('#status').val();
	$('input[name=status]').each(function(){
		if($(this).val() == user_status){
			$(this).attr('checked','true');
		}
	});
	
	var iGroup = $('#inlineGroup');
	var aInline = iGroup.find('.inline');
	
	aInline.each(function(i,elm){
		if((i+1)%3==0){ $(elm).addClass('inline-br'); }
		var elm = $(elm);
		var oSpan = elm.find('span');
		var oNextDiv,oCkbox,bChecked,oNextInput,sVal,elmSpan,oBtn;
		
		oSpan.click(function(){
			elmSpan = $(this);
			var oParent = $(this).parent();
			oParent.siblings().find('.btn-group').attr('class','btn-group btn-group-xs hidden');
			oParent.siblings().find('span').show();
			
			oNextDiv = oParent.find('.btn-group');
			oNextInput = oNextDiv.find('input[type="text"]');
			sVal = elmSpan.html();
			oNextInput.val( sVal );
			oNextDiv.css('display','inline-block').attr('class','btn-group btn-group-xs');
			elmSpan.hide();
		});
		
		oBtn = elm.find('button.btn-save');
		oBtn.click(function(e){
			elmSpan.show().html( oNextInput.val() );
			oNextDiv.hide();
			return false;
		});
	});
	
	$(document).on('keydown',function(e){
		var keyCode = e.keyCode;
		if(keyCode==13){
			return false;
		}
	})
});

//新增新闻类型二级类别
function AddSecondType(){
	var oStype = $('#secondType');
	if(oStype.attr('class')!='form-group'){
		oStype.attr('class','form-group');
	}
	
	var noData = $('#noData');
	noData.attr('class','hidden');
}

//返回新闻类型界面
function exit(){
	var url = "columnController/getList?reload=1";
	$(".content-wrapper").load(url);
}

//添加/保存新闻类型
function addType(){
	$.ajax({
		type: "post",
        url: "columnController/addType",
        data:  $("#typeForm").serialize(),
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("columnController/getList?reload=1");
        	}else{
        		alert(data['msg']);
        	}
        },
        error: function(){
        	
        }
	})
}

//修改类型二级菜单是否可用
function changeCheckBox(obj,id,columnName,pid){
	var status = 0;
	var flag = $(obj).prop('checked');
	if(flag==true){
		status = 1;
	}else if(flag==false){
		status = 0;
	}
	$.ajax({
		type: "post",
        url: "columnController/updateType",
		data: { 
			"id":id,
			"pid":pid,
			"columnName":columnName,
            "status":status
	    },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		//暂无操作;
        	}else{
        		//暂无操作;
        	}
        },
        error: function(){
        	//暂无操作;
        }
	})
}

//修改类型(二级)
function updateSecondType(obj,id,pid,status){
	var columnName = $(obj).siblings('input[type="text"]').val();
	$.ajax({
		type: "post",
        url: "columnController/updateType",
		data: { 
			"id":id,
			"pid":pid,
			"columnName":columnName,
            "status":status
	    },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		//暂无操作;
        	}else{
        		//暂无操作;
        	}
        },
        error: function(){
        	//暂无操作;
        }
	})
}
