var myEditChart = echarts.init(document.getElementById('myEdit'));
var myPublishChart = echarts.init(document.getElementById('myPublish'))
var url = 'statisticsController/countContre';
$(function(){
	getIncrement();
	getPublish();
	getIncrementTable();
	$('#cycle').on('change',function(){
		getIncrement();
		getPublish();
		getIncrementTable();
	});
})
var editChartOption;
var publishChartOption;
//周/月/年增长
function getIncrement(name){
	var cycle = $('#cycle').val();
	myEditChart.hideLoading();
	myEditChart.clear();
	$.getJSON("statisticsController/getIncrementChart", {"cycle" : cycle,"name" : name}, function(data){
		myEditChart.setOption(data,true);
		editChartOption = data;
	});
}
//周/月/年发布
function getPublish(){
	var cycle = $('#cycle').val();
	myPublishChart.hideLoading();
	myPublishChart.clear();
	$.getJSON("statisticsController/getPublishChart", {"cycle" : cycle}, function(data){
		myPublishChart.setOption(data,true);
		publishChartOption = data;
	});
}


function getIncrementTable(){
	var cycle = $('#cycle').val();
	$.ajax({
		type: "post",
        url: "statisticsController/getIncrementTable",
        data: {"cycle" : cycle},
        dataType: "json",
        success: function (data) {
        	if(data['success']){
        		var table = document.getElementById("data_table");
        		var th_tr = document.createElement("tr");
        		var td_xingming = document.createElement("td");
        		td_xingming.innerHTML = "姓名";
        		th_tr.appendChild(td_xingming);
        		for(var i = 0; i < data.col_list.length; i++){
        			var td = document.createElement("td");
        			td.innerHTML = data.col_list[i].columnName;
        			th_tr.appendChild(td);
        		}
        		table.children[0].innerHTML = "";
        		table.children[0].appendChild(th_tr);
        		for(var i = 0; i < data.count_list.length; i++){
        			var tb_tr = document.createElement("tr");
        			var tb_td =  document.createElement("td");
        		}
        	}
        },
        error: function(){
        }
	});
	
}
function getPublishTable(){
	
}
//重新加载页面
function reload(){
	$(".content-wrapper").load(url);
}


myEditChart.on('click', function (param){
	getIncrement(param.name);
})

                    