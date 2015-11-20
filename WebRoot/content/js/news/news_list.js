$(function() {
		//$("#data_table").DataTable();
		//初始化表格
		$('#data_table').DataTable({
			"paging" : true,  //分页
			"lengthChange" : true,  //每夜显示条数可变
			"searching" : true,  //在结果集上搜索
			"ordering" : true,  //排序图标
			"info" : true, //
			"autoWidth" : false  //自适应宽度
		});
		
		//日期选择控件
		$('#daterange-btn').daterangepicker(
              {
                ranges: {
                  'Today': [moment(), moment()],
                  'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                  'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                  'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                  'This Month': [moment().startOf('month'), moment().endOf('month')],
                  'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                startDate: moment().subtract(-1, 'days'),
                endDate: moment()
              },
              function (start, end) {
            	  $('#reportrange span').html(start.format('YYYY/MM/DD') + ' - ' + end.format('YYYY/MM/DD'));
              }
        );
});
//编辑新闻
function edit(id){
	var url = "newsController/toAdd?id="+id;
	$(".content-wrapper").load(url);
}
//删除新闻
function del(id){
	$.ajax({
		type: "post",
        url: "newsController/delNews",
        data: {
        	"id":id
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsListByUser");
        	}
        },
        error: function(){
        	
        }
	});
}
//转向预览页面
function preview(id){
	var url = "newsController/previewNews?id="+id;
	window.open(url,'newwindow');
}
//发布资讯
function publish(id,status){
	var tuihuiyuanyin=$(".tuihuiyuanyin").val();
	$.ajax({
		type: "post",
        url: "newsController/reviewNews",
        data: {
        	"id":id,
        	"status":status,
        	"tuihuiyuanyin":tuihuiyuanyin
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$('#myModal').modal('hide');
        		setTimeout('reload_url("newsController/getPublishedListByUser")',1000);
        	}
        },
        error: function(){
        	
        }
	});
}
//批量删除资讯
function delall(){
	var ids_arr = new Array();
	var i = 0;
	var url = "newsController/delNewsByList";
	$("input[name='ckevery']").each(function(){
		if($(this).prop("checked")){
			ids_arr[i] = this.value;
			i++;
		}
	});
	
	$.ajax({
		type: "post",
        url: url,
        data: {
        	"ids":ids_arr
        },
        dataType: "json",
        traditional : true,
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsList");
        	}
        },
        error: function(){
        	
        }
	});
}
//批量发布 收回资讯
//isPass 是否通过
function publishall(isPass){
	var ids_arr = new Array();
	var i = 0;
	var url = "newsController/reviewNewsList";
	$("input[name='ckevery']").each(function(){
		if($(this).prop("checked")){
			ids_arr[i] = this.value;
			i++;
		}
	});
	
	$.ajax({
		type: "post",
        url: url,
        data: {
        	"ids":ids_arr,
        	"isPass" : isPass
        },
        dataType: "json",
        traditional : true,
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsList");
        	}
        },
        error: function(){
        	
        }
	});
}
//提交资讯
function commitNews(id){
	$.ajax({
		type: "post",
        url: "newsController/saveOrUpdate",
        data: {
        	"id":id,
        	"status":2
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsList");
        	}
        },
        error: function(){
        	
        }
	});
	
}

//批量提交资讯
function commitall(){
	var ids_arr = new Array();
	var i = 0;
	var url = "newsController/updateAll";
	$("input[name='ckevery']").each(function(){
		if($(this).prop("checked")){
			ids_arr[i] = this.value;
			i++;
		}
	});
	
	$.ajax({
		type: "post",
        url: url,
        data: {
        	"status":2,
        	"ids":ids_arr
        },
        dataType: "json",
        traditional : true,
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsList");
        	}
        },
        error: function(){
        	
        }
	});
}

//批量设置为轮播
function setScrolling(){
	var ids_arr = new Array();
	var i = 0;
	var url = "newsController/setScrolling";
	$("input[name='ckevery']").each(function(){
		if($(this).prop("checked")){
			ids_arr[i] = this.value;
			i++;
		}
	});
	
	$.ajax({
		type: "post",
        url: url,
        data: {
        	"ids":ids_arr
        },
        dataType: "json",
        traditional : true,
        success: function(data){
        	if(data['success']){
        		$(".content-wrapper").load("newsController/getNewsList");
        	}else{
        		alert(data['msg']);
        	}
        },
        error: function(){
        	
        }
	});
}
//单条设置轮播，最大不超过3
function setCarousel(id){
	$.ajax({
		type:"post",
		url:"newsController/setScrolling",
		data:{
			"id":id
		},
		dataType:"json",
		success:function(data){
			if(data['success']){
				$(".content-wrapper").load("newsController/getPublishedListByUser");
				alert(data['msg']);
			}else{
				alert(data['msg']);
			}
		},
		error: function(){
			alert(data['msg']);
		}
	});
}

//取消轮播
function unCarousel(id){
	$.ajax({
		type:"post",
		url:"newsController/cancelScrolling",
		data:{
			"id":id
		},
		dataType:"json",
		success:function(data){
			if(data['success']){
				$(".content-wrapper").load("newsController/getPublishedListByUser");
				alert(data['msg']);
			}else{
				alert(data['msg']);
			}
		},
		error: function(){
			alert("系统错误");
		}
	});
}

//条件查询 我的发布
function findwodefabu(){
	var title=$("#title").val();//标题
	var zhuanxierenid=$("#zhuanxierenid").val();//撰写人
	var qishishijian=$("#qishishijian").html();//时间段
	var startTime=qishishijian.split('-')[0];//开始时间
	var endTime=qishishijian.split('-')[1];//截至时间
	$(".content-wrapper").load("newsController/getPublishedListByUser",{"title":title,"zhuanxierenid":zhuanxierenid,"startTime":startTime,"endTime":endTime});
	}
//条件查询 我的资讯
function findwodezixun(){
	var title=$("#title").val();//标题
	alert(title);
	var status=$("#status").val();//状态
	var type=$("#type").val();//类型
	var qishishijian=$("#qishishijian").html();//时间段
	var startTime=qishishijian.split('-')[0];//开始时间
	var endTime=qishishijian.split('-')[1];//截至时间
	$(".content-wrapper").load("newsController/getNewsListByUser",{"title":title,"status":status,"type":type,"startTime":startTime,"endTime":endTime});
}
//条件查询 资讯中心
function findzixunzhongxin(){
	var title=$("#title").val();//标题
	var status=$("#status").val();//状态
	var type=$("#type").val();//类型
	var zhuanxierenid=$("#zhuanxierenid").val();//撰写人
	var qishishijian=$("#qishishijian").html();//时间段
	var startTime=qishishijian.split('-')[0];//开始时间
	var endTime=qishishijian.split('-')[1];//截至时间
	$(".content-wrapper").load("newsController/getNewsList",{"title":title,"status":status,"type":type,"zhuanxierenid":zhuanxierenid,"startTime":startTime,"endTime":endTime});
}
//条件查询 审核中心
function findshenhezhongxin(){
	var title=$("#title").val();//标题
	alert(title);
	var zhuanxierenid=$("#zhuanxierenid").val();//撰写人
	alert(zhuanxierenid);
	var qishishijian=$("#qishishijian").html();//时间段
	var startTime=qishishijian.split('-')[0];//开始时间
	var endTime=qishishijian.split('-')[1];//截至时间
	$(".content-wrapper").load("newsController/getAuditCentre",{"title":title,"zhuanxierenid":zhuanxierenid,"startTime":startTime,"endTime":endTime});
}

//查看详情
function seeMsg() {
	var url = "newsController/getNewsListByUser";
	$(".content-wrapper").load(url);
}

//处理按钮
function shouTr() {
	$('#myModal').modal('show');
	var showTr = $("#dataTable");
	showTr.html("");
}