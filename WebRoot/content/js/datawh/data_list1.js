/*初始化表名*/
var tName = "data_xinjianzhuzhai";    

/*********************改变下拉列表选项时触发，改变表名***********************/
function changeDateList(){
	tName = $("#tableName").val();
	colHerder(tName);
	loadDataList(1);
}

/*---------------------默认显示数据----------------------*/
$(function(){
	loadDataList(1,"data_xinjianzhuzhai");
});

/*-----------------------------------------加载数据列表begin-----------------------------------------------*/
function loadDataList(curPage) {     //给一个默认的表
	$.ajax({
			type : "POST",
			url : "dataXinjianzhuzhaiController/findDataList",
			async : false,
			data : {
				"dmsm" : tName,
				"curPage" : curPage
			},
			dataType : "json",
			success : function(msg) {
				var msgData = msg.result;     //获取数据
				var msgColumn = msg.columnData;    //获取列名
				var data = eval(msgData);
				colHerder(msgColumn);     //显示列表头部列名部分
				
				var listTr = $("#dataList");
				listTr.html("");
				for (var i = 0; i < data.list.length; i++) {
					var subJsp = "<tr><td>" + '<input type="checkbox" id="checkboxid"/></td>';
					for(var j = 0;j < msgColumn.length; j++){
						var ziduan = msgColumn[j].table_pinyin;
						subJsp += "<td>"+data.list[i][ziduan]+"</td>";
					}			
					subJsp += "<td><a onclick=''>编 辑</a>/<a onclick=''>删 除</a></td></tr>";
					listTr.append(subJsp);
				}
				
			
			
//				if (tName == "data_ershouzhuzhai") {
//					for (var i = 0; i < data.list.length; i++) {
//						listTr.append("<tr><td>" + '<input type="checkbox" id="checkboxid"/>'
//								+ "</td><td>"
//								+ data.list[i].shijian
//								+ "</td><td>"
//								+ data.list[i].city
//								+ "</td><td>"
//								+ data.list[i].chengjiaotaoshu
//								+ "</td><td>"
//								+ data.list[i].shichangjunjia
//								+ "</td><td>"
//								+ data.list[i].userid
//								+ "</td><td><a onclick='shouTr("
//								+ "\""
//								+ data.list[i].shijian
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].city
//								+ "\""
//								+ ","
//								+ data.list[i].chengjiaotaoshu
//								+ ","
//								+ data.list[i].shichangjunjia
//								+ ","
//								+ data.list[i].id
//								+ ")'>编 辑</a>/<a onclick='del("
//								+ "\""
//								+ tableName
//								+ "\""
//								+ ","
//								+ data.list[i].id
//								+ ")'>删 除</a></td></tr>"
//						);
//					}
//				} else if (tName == "data_jiagezhishu") {
//					for (var i = 0; i < data.list.length; i++) {
//						listTr.append("<tr><td>" + '<input type="checkbox"/>'
//								+ "</td><td>"
//								+ data.list[i].shijian
//								+ "</td><td>"
//								+ data.list[i].city
//								+ "</td><td>"
//								+ data.list[i].wuyeleixing
//								+ "</td><td>"
//								+ data.list[i].jiagezhishu
//								+ "</td><td>"
//								+ data.list[i].huanbi
//								+ "</td><td>"
//								+ data.list[i].userid
//								+ "</td><td><a onclick='shouTr("
//								+ "\""
//								+ data.list[i].shijian
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].city
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].wuyeleixing
//								+ "\""
//								+ ","
//								+ data.list[i].jiagezhishu
//								+ ","
//								+ data.list[i].huanbi
//								+ ","
//								+ data.list[i].id
//								+ ")'>编 辑</a>/<a onclick='del("
//								+ "\""
//								+ tableName
//								+ "\""
//								+ ","
//								+ data.list[i].id
//								+ ")'>删 除</a></td></tr>"
//						);
//					}
//				} else if (tName == "data_zujinzhishu") {
//					for (var i = 0; i < data.list.length; i++) {
//						listTr.append("<tr><td>" + '<input type="checkbox"/>'
//								+ "</td><td>"
//								+ data.list[i].shijian
//								+ "</td><td>"
//								+ data.list[i].city
//								+ "</td><td>"
//								+ data.list[i].wuyeleixing
//								+ "</td><td>"
//								+ data.list[i].zujinzhishu
//								+ "</td><td>"
//								+ data.list[i].huanbi
//								+ "</td><td>"
//								+ data.list[i].userid
//								+ "</td><td><a onclick='shouTr("
//								+ "\""
//								+ data.list[i].shijian
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].city
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].wuyeleixing
//								+ "\""
//								+ ","
//								+ data.list[i].zujinzhishu
//								+ ","
//								+ data.list[i].huanbi
//								+ ","
//								+ data.list[i].id
//								+ ")'>编 辑</a>/<a onclick='del("
//								+ "\""
//								+ tableName
//								+ "\""
//								+ ","
//								+ data.list[i].id
//								+ ")'>删 除</a></td></tr>"
//						);
//					}
//				} else if (tName == "data_tudizongti") {
//					for (var i = 0; i < data.list.length; i++) {
//						listTr.append("<tr><td>" + '<input type="checkbox"/>'
//								+ "</td><td>"
//								+ data.list[i].shijian
//								+ "</td><td>"
//								+ data.list[i].city
//								+ "</td><td>"
//								+ data.list[i].yongtu
//								+ "</td><td>"
//								+ data.list[i].zongshu
//								+ "</td><td>"
//								+ data.list[i].tudichurangjin
//								+ "</td><td>"
//								+ data.list[i].jiansheyongdimianji
//								+ "</td><td>"
//								+ data.list[i].guihuajianzhumianji
//								+ "</td><td>"
//								+ data.list[i].pingjunyijialv
//								+ "</td><td>"
//								+ data.list[i].userid
//								+ "</td><td><a onclick='shouTr("
//								+ "\""
//								+ data.list[i].shijian
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].city
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].yongtu
//								+ "\""
//								+ ","
//								+ data.list[i].zongshu
//								+ ","
//								+ data.list[i].tudichurangjin
//								+ ","
//								+ data.list[i].jiansheyongdimianji
//								+ ","
//								+ data.list[i].guihuajianzhumianji
//								+ ","
//								+ data.list[i].pingjunyijialv
//								+ ","
//								+ data.list[i].id
//								+ ")'>编 辑</a>/<a onclick='del("
//								+ "\""
//								+ tableName
//								+ "\""
//								+ ","
//								+ data.list[i].id
//								+ ")'>删 除</a></td></tr>"
//						);
//					}
//				} else if (tName == "data_xinjianzhuzhai") {
//					for (var i = 0; i < data.list.length; i++) {
//						listTr.append("<tr><td>" + '<input type="checkbox"/>'
//								+ "</td><td>"
//								+ data.list[i].shijian
//								+ "</td><td>"
//								+ data.list[i].city
//								+ "</td><td>"
//								+ data.list[i].xiaoshoutaoshu
//								+ "</td><td>"
//								+ data.list[i].xiaoshoumianji
//								+ "</td><td>"
//								+ data.list[i].xiaoshoujiage
//								+ "</td><td>"
//								+ data.list[i].userid
//								+ "</td><td><a onclick='shouTr("
//								+ "\""
//								+ data.list[i].shijian
//								+ "\""
//								+ ","
//								+ "\""
//								+ data.list[i].city
//								+ "\""
//								+ ","
//								+ data.list[i].xiaoshoutaoshu
//								+ ","
//								+ data.list[i].xiaoshoumianji
//								+ ","
//								+ data.list[i].xiaoshoujiage
//								+ ","
//								+ data.list[i].id
//								+ ")'>编 辑</a>/<a onclick='del("
//								+ "\""
//								+ tableName
//								+ "\""
//								+ ","
//								+ data.list[i].id
//								+ ")'>删 除</a></td></tr>"
//						);
//					}
//				}
				var pageUL = $("#pagegpalUl"); // 分页位置
				pagerUtil(data.curPage, data.end, data.pageSize,data.start, data.totalPage, data.totalRow, "loadDataList",pageUL); // 调用分页方法						
			},
			error : function(){
				
			}
	});
}
/*--------------------------------------------------------加载数据列表end------------------------------------------------------*/


/*--------------------------------------------------------根据不同的类型表显示不同类型列begin-----------------------------------------------*/
function colHerder(tableName){
	var colTr = $("#colList");     
	colTr.html("");
	var subColumn = '<td><input type="checkbox"  onclick="SelectAll()"/></td>';
	for (var i = 0; i < tableName.length; i++) {
		subColumn += '<td>'+tableName[i].column_name+'</td>';
	}
	subColumn += '<td>操作</td>';
	colTr.html(subColumn);
	
//	if (tableName == "data_ershouzhuzhai") {
//		colTr.append(<td>"
//				+ '时间' + "</td><td>" + '城市' + "</td><td>" + '成交套数'
//				+ "</td><td>" + '市场均价' + "</td><td>" + '上传人'
//				+ "</td><td>操作</td>");
//	} else if (tableName == "data_jiagezhishu") {
//		colTr.append("<td>" + '<input type="checkbox"  onclick="SelectAll()"/>' + "</td><td>"
//				+ '时间' + "</td><td>" + '城市' + "</td><td>" + '物业类型'
//				+ "</td><td>" + '价格指数' + "</td><td>" + '环比'
//				+ "</td><td>" + '上传人' + "</td><td>操作</td>");
//	} else if (tableName == "data_zujinzhishu") {
//		colTr.append("<td>" + '<input type="checkbox"  onclick="SelectAll()"/>' + "</td><td>"
//				+ '时间' + "</td><td>" + '城市' + "</td><td>" + '物业类型'
//				+ "</td><td>" + '租金指数' + "</td><td>" + '环比'
//				+ "</td><td>" + '上传人' + "</td><td>操作</td>");
//	} else if (tableName == "data_tudizongti") {
//		colTr.append("<td>" + '<input type="checkbox"  onclick="SelectAll()"/>' + "</td><td>"
//				+ '时间 ' + "</td><td>" + '城市 ' + "</td><td>" + '用途 '
//				+ "</td><td>" + '总数 ' + "</td><td>" + '土地出让金 '
//				+ "</td><td>" + '建设用地面积' + "</td><td>" + '规划建筑面积'
//				+ "</td><td>" + '平均溢价率' + "<td>" + '上传人'
//				+ "</td><td>操作</td>");
//	} else if (tableName == "data_xinjianzhuzhai") {
//		colTr.append("<td>" + '<input type="checkbox"  onclick="SelectAll()"/>' + "</td><td>"
//				+ '时间 ' + "</td><td>" + '城市 ' + "</td><td>" + '销售套数 '
//				+ "</td><td>" + ' 销售面积 ' + "</td><td>" + '销售价格'
//				+ "</td><td>" + '上传人' + "</td><td>操作</td>");
//	}			
}
/*---------------------------------------------------根据不同的类型表显示不同类型列end--------------------------------------------------*/


/*--------------------------------------------翻页功能begin--------------------------------------------*/
function pagerUtil(curPage, end, pageSize, start, totalPage, totalRow, load, pageUL) {
	pageUL = $(pageUL);
    pageUL.html("");
    pageUL.append("<strong>当前页 " + curPage + " 共 " + totalPage + " 页/共" + totalRow + "条&nbsp;</strong>");
	//如果为首页的话就不显示上一页
	if(curPage > 1){    
		pageUL.append("<a href='javascript:;' onclick='upLoadPage(" + curPage + ","+load+")' class='pageBn'>上一页</a>");
	}
    
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<a href='javascript:;' data-id=" + (load + j) + " id=" + (load + j) + " onclick='" + load + "(" + (j + 1) +")'>" + (j + 1) + "</a>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<a href='javascript:;' data-id=" + (load + j) + " id=" + (load + j) + " onclick='" + load + "(" + (j + 1) +")'>" + (j + 1) + "</a>");
            }
        }
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<a href='javascript:;' data-id=" + (load + j) + " id=" + (load + j) + " onclick='" + load + "(" + (j + 1) +")'>" + (j + 1) + "</a>");
        }
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<a href='javascript:;' data-id=" + (load + j) + " id=" + (load + j) + " onclick='" + load + "(" + j +")'>" + j + "</a>");
        }
    }
	//如果为尾页的话就不显示下一页
	if(curPage < totalPage){
		pageUL.append("<a href='javascript:;' onclick='DownLoadPage(" + curPage + ',' + totalPage + "," + load + ")' class='pageBn'>下一页</a>");
	}
    
    var pageLink = $('.pagination').find('a');
    pageLink.each(function(){
    	var dataId = $(this).attr('data-id');
    });
}

/*---------------------------上一页------------------------------*/
function upLoadPage(curPage,load) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        load(startPage);
    }
}

/*---------------------------下一页------------------------------*/
function DownLoadPage(curPage, totalPage,load) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        load(endPage);
    }
}
/*----------------------------------------翻页功能end-------------------------------------------------------------*/


/*------------------------- 删除数据-----------------------------*/
function del(tableName, id) {
	if (confirm("是否删除该条数据？")) {
		$.ajax({
			type : "post",
			url : "dataXinjianzhuzhaiController/delData",
			data : {
				"dmsm" : tableName,
				"id" : id
			},
			dataType : "json",
			success : function(data) {
				if (data['success']) {
					loaddata(1, tName);
				}
			},
			error : function() {

			}
		});
	}
}

/*-------------------------------获取下拉列表的值---------------------*/
function ch3() {
	var tableName = document.getElementById("tableName"); // $("tableName")
	var index = tableName.selectedIndex;
	var textsel = tableName.options[index].text;
	$.ajax({
		type : "post",
		url : "dataXinjianzhuzhaiController/findDataXinjiazhuzhai",
		data : {
			"textsel" : textsel
		},
		dataType : "json",
		success : function() {
		},
		error : function() {
		}
	});
}
/*------------------------数据导出------------------*/
function dcxcel(tName) {
	$.ajax({
		type : "post",
		url : "dataXinjianzhuzhaiController/dcExcel",
		data : {
			"tName" : tName,
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data['success']) {
				alert("导出成功！");
			}
		},
		error : function() {

		}
	});
}
/*-----------下载-------------*/
function download_data(fileName) {
	var url = 'dataXinjianzhuzhaiController/xiazai';
	var form = $('#downForm');
	$('#filename').val(fileName);
	form.attr('action', url);
	form.submit();
}

/*---------------------导入数据--------------------------*/ 
function drexcel() {
	var url = 'dataXinjianzhuzhaiController/drExcel';
	var form = $('#fileform');
	form.attr('action', url);
	form.submit();
}

// 新增or修改
function shouTr(t1, t2, t3, t4, t5, t6, t7, t8, t9) {
	$('#myModal').modal('show');
	var showTr = $("#dataTable");
	showTr.html("");
	if (tName == "data_ershouzhuzhai") {
		if (t1 == "") {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian">' + "</td></tr><tr><td>"
					+ '城市' + "</td><td>" + '<input type="text" id="city">'
					+ "</td></tr><tr><td>" + '成交套数' + "</td><td>"
					+ '<input type="text" id="chengjiaotaoshu">'
					+ "</td></tr><tr><td>" + '市场均价' + "</td><td>"
					+ '<input type="text" id="shichangjunjia">' + "</td>"
					+ '<td><input type="hidden" id="hid"/></td>' + "</tr>");
		} else {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian" value="'
					+ t1
					+ '">'
					+ "</td></tr><tr><td>"
					+ '城市'
					+ "</td><td>"
					+ '<input type="text" id="city" value="'
					+ t2
					+ '">'
					+ "</td></tr><tr><td>"
					+ '成交套数'
					+ "</td><td>"
					+ '<input type="text" id="chengjiaotaoshu" value="'
					+ t3
					+ '">'
					+ "</td></tr><tr><td>"
					+ '市场均价'
					+ "</td><td>"
					+ '<input type="text" id="shichangjunjia" value="'
					+ t4
					+ '">'
					+ "</td>"
					+ '<td><input type="hidden" id="hid" value="'
					+ t5
					+ '"/></td>' + "</tr>");
		}
	} else if (tName == "data_jiagezhishu") {
		if (t1 == "") {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian">' + "</td></tr><tr><td>"
					+ '城市' + "</td><td>" + '<input type="text" id="city">'
					+ "</td></tr><tr><td>" + '物业类型' + "</td><td>"
					+ '<input type="text" id="wuyeleixing">'
					+ "</td></tr><tr><td>" + '价格指数' + "</td><td>"
					+ '<input type="text" id="jiagezhishu">'
					+ "</td></tr><tr><td>" + '环比' + "</td><td>"
					+ '<input type="text" id="huanbi">' + "</td></tr><tr><td>"
					+ '<input type="hidden" id="hid">' + "</td></tr>");
		} else {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian" value=" '
					+ t1
					+ '">'
					+ "</td></tr><tr><td>"
					+ '城市'
					+ "</td><td>"
					+ '<input type="text" id="city" value="'
					+ t2
					+ '"">'
					+ "</td></tr><tr><td>"
					+ '物业类型'
					+ "</td><td>"
					+ '<input type="text" id="wuyeleixing" value="'
					+ t3
					+ '">'
					+ "</td></tr><tr><td>"
					+ '价格指数'
					+ "</td><td>"
					+ '<input type="text" id="jiagezhishu" value="'
					+ t4
					+ '">'
					+ "</td></tr><tr><td>"
					+ '环比'
					+ "</td><td>"
					+ '<input type="text" id="huanbi" value="'
					+ t5
					+ '">'
					+ "</td></tr><tr><td>"
					+ '<input type="hidden" id="hid" value="'
					+ t6
					+ '">'
					+ "</td></tr>");
		}
	} else if (tName == "data_zujinzhishu") {
		if (t1 == "") {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian">' + "</td></tr><tr><td>"
					+ '城市' + "</td><td>" + '<input type="text" id="city">'
					+ "</td></tr><tr><td>" + '物业类型' + "</td><td>"
					+ '<input type="text" id="wuyeleixing">'
					+ "</td></tr><tr><td>" + '租金指数' + "</td><td>"
					+ '<input type="text" id="zujinzhishu">'
					+ "</td></tr><tr><td>" + '环比' + "</td><td>"
					+ '<input type="text" id="huanbi">' + "</td></tr><tr><td>"
					+ '<input type="hidden" id="hid">' + "</td></tr>");
		} else {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian" value="'
					+ t1
					+ '">'
					+ "</td></tr><tr><td>"
					+ '城市'
					+ "</td><td>"
					+ '<input type="text" id="city" value="'
					+ t2
					+ '">'
					+ "</td></tr><tr><td>"
					+ '物业类型'
					+ "</td><td>"
					+ '<input type="text" id="wuyeleixing" value="'
					+ t3
					+ '">'
					+ "</td></tr><tr><td>"
					+ '租金指数'
					+ "</td><td>"
					+ '<input type="text" id="zujinzhishu" value="'
					+ t4
					+ '">'
					+ "</td></tr><tr><td>"
					+ '环比'
					+ "</td><td>"
					+ '<input type="text" id="huanbi" value="'
					+ t5
					+ '">'
					+ "</td></tr><tr><td>"
					+ '<input type="hidden" id="hid" value="'
					+ t6
					+ '">'
					+ "</td></tr>");
		}
	} else if (tName == "data_tudizongti") {
		if (t1 == "") {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian">' + "</td></tr><tr><td>"
					+ '城市' + "</td><td>" + '<input type="text" id="city">'
					+ "</td></tr><tr><td>" + '用途' + "</td><td>"
					+ '<input type="text" id="yongtu">' + "</td></tr><tr><td>"
					+ '总数' + "</td><td>" + '<input type="text" id="zongshu">'
					+ "</td></tr><tr><td>" + '土地出让金' + "</td><td>"
					+ '<input type="text" id="tudichurangjin">'
					+ "</td></tr><tr><td>" + '建设用地面积' + "</td><td>"
					+ '<input type="text" id="jiansheyongdimianji">'
					+ "</td></tr><tr><td>" + '规划建筑面积' + "</td><td>"
					+ '<input type="text" id="guihuajianzhumianji">'
					+ "</td></tr><tr><td>" + '平均溢价率' + "</td><td>"
					+ '<input type="text" id="pingjunyijialv">'
					+ '<input type="hidden" id="hid">' + "</td></tr>");
		} else {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian" value="'
					+ t1
					+ '">'
					+ "</td></tr><tr><td>"
					+ '城市'
					+ "</td><td>"
					+ '<input type="text" id="city" value="'
					+ t2
					+ '">'
					+ "</td></tr><tr><td>"
					+ '用途'
					+ "</td><td>"
					+ '<input type="text" id="yongtu" value="'
					+ t3
					+ '">'
					+ "</td></tr><tr><td>"
					+ '总数'
					+ "</td><td>"
					+ '<input type="text" id="zongshu" value="'
					+ t4
					+ '">'
					+ "</td></tr><tr><td>"
					+ '土地出让金'
					+ "</td><td>"
					+ '<input type="text" id="tudichurangjin" value="'
					+ t5
					+ '">'
					+ "</td></tr><tr><td>"
					+ '建设用地面积'
					+ "</td><td>"
					+ '<input type="text" id="jiansheyongdimianji" value="'
					+ t6
					+ '">'
					+ "</td></tr><tr><td>"
					+ '规划建筑面积'
					+ "</td><td>"
					+ '<input type="text" id="guihuajianzhumianji" value="'
					+ t7
					+ '">'
					+ "</td></tr><tr><td>"
					+ '平均溢价率'
					+ "</td><td>"
					+ '<input type="text" id="pingjunyijialv" value="'
					+ t8
					+ '">'
					+ '<input type="hidden" id="hid" value="'
					+ t9
					+ '">' + "</td></tr>");
		}
	} else if (tName == "data_xinjianzhuzhai") {
		if (t1 == "") {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian">' + "</td></tr><tr><td>"
					+ '城市' + "</td><td>" + '<input type="text" id="city">'
					+ "</td></tr><tr><td>" + '销售套数' + "</td><td>"
					+ '<input type="text" id="xiaoshoutaoshu">'
					+ "</td></tr><tr><td>" + '销售面积' + "</td><td>"
					+ '<input type="text" id="xiaoshoumianji">'
					+ "</td></tr><tr><td>" + '销售价格' + "</td><td>"
					+ '<input type="text" id="xiaoshoujiage">'
					+ '<input type="hidden" id="hid">' + "</td></tr>");
		} else {
			showTr.html("<tr><td>" + '时间' + "</td><td>"
					+ '<input type="text" id="shijian" value="'
					+ t1
					+ '">'
					+ "</td></tr><tr><td>"
					+ '城市'
					+ "</td><td>"
					+ '<input type="text" id="city" value="'
					+ t2
					+ '">'
					+ "</td></tr><tr><td>"
					+ '销售套数'
					+ "</td><td>"
					+ '<input type="text" id="xiaoshoutaoshu" value="'
					+ t3
					+ '">'
					+ "</td></tr><tr><td>"
					+ '销售面积'
					+ "</td><td>"
					+ '<input type="text" id="xiaoshoumianji" value="'
					+ t4
					+ '">'
					+ "</td></tr><tr><td>"
					+ '销售价格'
					+ "</td><td>"
					+ '<input type="text" id="xiaoshoujiage" value="'
					+ t5
					+ '">'
					+ '<input type="hidden" id="hid" value="'
					+ t6
					+ '">' + "</td></tr>");
		}
	}
}
function saveOrupdate() {
	if (tName == "data_ershouzhuzhai") {
		var shijian = $("#shijian").val();
		var city = $("#city").val();
		var hid = $("#hid").val();
		var chengjiaotaoshu = $("#chengjiaotaoshu").val();
		var shichangjunjia = $("#shichangjunjia").val();
		$.ajax({
			type : "POST", // 用POST方式传输
			dataType : "json", // 数据格式:JSON
			data : {
				tName : tName,
				id : hid,
				shijian : shijian,
				city : city,
				chengjiaotaoshu : chengjiaotaoshu,
				shichangjunjia : shichangjunjia
			},
			url : 'dataXinjianzhuzhaiController/saveorupdateData', // 目标地址
			success : function(data) {
				$('#myModal').modal('hide');
				if("la"==$("#la").val()){
					loaddata(1, tName);
				}else{
					getvalue(1,tName);
				}
			}
		});
	} else if (tName == "data_jiagezhishu") {
		var shijian = $("#shijian").val();
		var city = $("#city").val();
		var hid = $("#hid").val();
		var wuyeleixing = $("#wuyeleixing").val();
		var jiagezhishu = $("#jiagezhishu").val();
		var huanbi = $("#huanbi").val();
		$.ajax({
			type : "POST", // 用POST方式传输
			dataType : "json", // 数据格式:JSON
			data : {
				tName : tName,
				id : hid,
				shijian : shijian,
				city : city,
				wuyeleixing : wuyeleixing,
				jiagezhishu : jiagezhishu,
				huanbi : huanbi
			},
			url : 'dataXinjianzhuzhaiController/saveorupdateData', // 目标地址
			success : function(data) {
				$('#myModal').modal('hide');
				if("la"==$("#la").val()){
					loaddata(1, tName);
				}else{
					getvalue(1,tName);
				}
			}
		});
	} else if (tName == "data_zujinzhishu") {
		var shijian = $("#shijian").val();
		var city = $("#city").val();
		var hid = $("#hid").val();
		var wuyeleixing = $("#wuyeleixing").val();
		var zujinzhishu = $("#zujinzhishu").val();
		var huanbi = $("#huanbi").val();

		$.ajax({
			type : "POST", // 用POST方式传输
			dataType : "json", // 数据格式:JSON
			data : {
				tName : tName,
				id : hid,
				shijian : shijian,
				city : city,
				wuyeleixing : wuyeleixing,
				zujinzhishu : zujinzhishu,
				huanbi : huanbi
			},
			url : 'dataXinjianzhuzhaiController/saveorupdateData', // 目标地址
			success : function(data) {
				$('#myModal').modal('hide');
				if("la"==$("#la").val()){
					loaddata(1, tName);
				}else{
					getvalue(1,tName);
				}
			}
		});
	} else if (tName == "data_tudizongti") {
		var shijian = $("#shijian").val();
		var city = $("#city").val();
		var hid = $("#hid").val();
		var yongtu = $("#yongtu").val();
		var zongshu = $("#zongshu").val();
		var tudichurangjin = $("#tudichurangjin").val();
		var jiansheyongdimianji = $("#jiansheyongdimianji").val();
		var guihuajianzhumianji = $("#guihuajianzhumianji").val();
		var pingjunyijialv = $("#pingjunyijialv").val();

		$.ajax({
			type : "POST", // 用POST方式传输
			dataType : "json", // 数据格式:JSON
			data : {
				tName : tName,
				id : hid,
				shijian : shijian,
				city : city,
				yongtu : yongtu,
				zongshu : zongshu,
				tudichurangjin : tudichurangjin,
				jiansheyongdimianji : jiansheyongdimianji,
				guihuajianzhumianji : guihuajianzhumianji,
				pingjunyijialv : pingjunyijialv
			},
			url : 'dataXinjianzhuzhaiController/saveorupdateData', // 目标地址
			success : function(data) {
				$('#myModal').modal('hide');
				if("la"==$("#la").val()){
					loaddata(1, tName);
				}else{
					getvalue(1,tName);
				}
			}
		});
	} else if (tName == "data_xinjianzhuzhai") {
		var shijian = $("#shijian").val();
		var city = $("#city").val();
		var hid = $("#hid").val();
		var xiaoshoutaoshu = $("#xiaoshoutaoshu").val();
		var xiaoshoumianji = $("#xiaoshoumianji").val();
		var xiaoshoujiage = $("#xiaoshoujiage").val();

		$.ajax({
			type : "POST", // 用POST方式传输
			dataType : "json", // 数据格式:JSON
			data : {
				tName : tName,
				id : hid,
				shijian : shijian,
				city : city,
				xiaoshoutaoshu : xiaoshoutaoshu,
				xiaoshoumianji : xiaoshoumianji,
				xiaoshoujiage : xiaoshoujiage
			},
			url : 'dataXinjianzhuzhaiController/saveorupdateData', // 目标地址
			success : function(data) {
				$('#myModal').modal('hide');
				if("la"==$("#la").val()){
					loaddata(1, tName);
				}else{
					getvalue(1,tName);
				}
			}
		});
	}
}

function folder(id) {
	var str = '#menu_' + id;
	var menu = $(str);
	menu.css('display', 'block');
}

// 全选
function SelectAll() {
	for (var i = 0; i < document.checkboxform.checkboxid.length; i++) {
		var e = document.checkboxform.checkboxid[i];
		e.checked = !e.checked;
	}
}

/*------刷新数据显示列表--------------*/
function freshen(){
	loadDataList(1);
}