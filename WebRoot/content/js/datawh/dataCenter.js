//----------------------------------------------------------------------------首页数据中心---------------------------------------------------------------
function getrow(tableName){
	$.ajax({
		type : "POST",
		url : "dataXinjianzhuzhaiController/findDataList",
		async : false,
		data : {
			"dmsm" : tableName
		},
		dataType : "json",
		success : function(msg) {
			var listTr = $("#getvalue");
			var colTr = $("#getrow");
			colTr.html("");
			var data = eval(msg);
			var a = null;
			if (tableName == "data_ershouzhuzhai") {
				colTr.append("<td>"
						+ '时间' + "</td><td>" + '城市' + "</td><td>" + '成交套数'
						+ "</td><td>" + '市场均价' + "</td><td>" + '上传人'
						+ "</td>");
			} else if (tableName == "data_jiagezhishu") {
				colTr.append("<td>"
						+ '时间' + "</td><td>" + '城市' + "</td><td>" + '物业类型'
						+ "</td><td>" + '价格指数' + "</td><td>" + '环比'
						+ "</td><td>" + '上传人' + "</td>");
			} else if (tableName == "data_zujinzhishu") {
				colTr.append("<td>"
						+ '时间' + "</td><td>" + '城市' + "</td><td>" + '物业类型'
						+ "</td><td>" + '租金指数' + "</td><td>" + '环比'
						+ "</td><td>" + '上传人' + "</td>");
			} else if (tableName == "data_tudizongti") {
				colTr.append("<td>"
						+ '时间 ' + "</td><td>" + '城市 ' + "</td><td>" + '用途 '
						+ "</td><td>" + '总数 ' + "</td><td>" + '土地出让金 '
						+ "</td><td>" + '建设用地面积' + "</td><td>" + '规划建筑面积'
						+ "</td><td>" + '平均溢价率' + "<td>" + '上传人'
						+ "</td>");
			} else if (tableName == "data_xinjianzhuzhai") {
				colTr.append("<td>"
						+ '时间 ' + "</td><td>" + '城市 ' + "</td><td>" + '销售套数 '
						+ "</td><td>" + ' 销售面积 ' + "</td><td>" + '销售价格'
						+ "</td><td>" + '上传人' + "</td>");
			}
			pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
					data.totalPage, data.totalRow);
		},
		error : function() {
		}
	});
}

function getvalue(curPage, tableName){
	getrow(tableName);
	$.ajax({
		type : "POST",
		url : "dataXinjianzhuzhaiController/findDataList",
		data : {
			"curPage" : curPage,
			"pageSize":5,
			"dmsm" : tableName,
		},
		dataType : "json",
		success : function(msg) {
			var listTr = $("#getvalue");
			var countData=$("#countData");
			$("#countData1").html("");
			var item = $("#tableName option:selected").text();
			listTr.html("");
			countData.html("");
			var data = eval(msg);
			if (tableName == "data_ershouzhuzhai") {
				for (var i = 0; i < data.list.length; i++) {
					listTr.append("<tr><td>"
							+ data.list[i].shijian
							+ "</td><td>"
							+ data.list[i].city
							+ "</td><td>"
							+ data.list[i].chengjiaotaoshu
							+ "</td><td>"
							+ data.list[i].shichangjunjia
							+ "</td><td>"
							+ data.list[i].userid
							+ "</td></tr>");
				}
				pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
						data.totalPage, data.totalRow);
				countData.append(data.totalRow);
				$("#countData1").append(item);
			} else if (tableName == "data_jiagezhishu") {
				for (var i = 0; i < data.list.length; i++) {
					listTr.append("<tr><td>"
							+ data.list[i].shijian
							+ "</td><td>"
							+ data.list[i].city
							+ "</td><td>"
							+ data.list[i].wuyeleixing
							+ "</td><td>"
							+ data.list[i].jiagezhishu
							+ "</td><td>"
							+ data.list[i].huanbi
							+ "</td><td>"
							+ data.list[i].userid
							+ "</td></tr>");
				}
				pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
						data.totalPage, data.totalRow);
				countData.append(data.totalRow);
				$("#countData1").append(item);
			} else if (tableName == "data_zujinzhishu") {
				for (var i = 0; i < data.list.length; i++) {
					listTr.append("<tr><td>"
							+ data.list[i].shijian
							+ "</td><td>"
							+ data.list[i].city
							+ "</td><td>"
							+ data.list[i].wuyeleixing
							+ "</td><td>"
							+ data.list[i].zujinzhishu
							+ "</td><td>"
							+ data.list[i].huanbi
							+ "</td><td>"
							+ data.list[i].userid
							+ "</td></tr>");
				}
				pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
						data.totalPage, data.totalRow);
				countData.append(data.totalRow);
				$("#countData1").append(item);
			} else if (tableName == "data_tudizongti") {
				for (var i = 0; i < data.list.length; i++) {
					listTr.append("<tr><td>"
							+ data.list[i].shijian
							+ "</td><td>"
							+ data.list[i].city
							+ "</td><td>"
							+ data.list[i].yongtu
							+ "</td><td>"
							+ data.list[i].zongshu
							+ "</td><td>"
							+ data.list[i].tudichurangjin
							+ "</td><td>"
							+ data.list[i].jiansheyongdimianji
							+ "</td><td>"
							+ data.list[i].guihuajianzhumianji
							+ "</td><td>"
							+ data.list[i].pingjunyijialv
							+ "</td><td>"
							+ data.list[i].userid
							+ "</td></tr>");
				}
				pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
						data.totalPage, data.totalRow);
				countData.append(data.totalRow);
				$("#countData1").append(item);
			} else if (tableName == "data_xinjianzhuzhai") {
				for (var i = 0; i < data.list.length; i++) {
					listTr.append("<tr><td>"
							+ data.list[i].shijian
							+ "</td><td>"
							+ data.list[i].city
							+ "</td><td>"
							+ data.list[i].xiaoshoutaoshu
							+ "</td><td>"
							+ data.list[i].xiaoshoumianji
							+ "</td><td>"
							+ data.list[i].xiaoshoujiage
							+ "</td><td>"
							+ data.list[i].userid
							+ "</td></tr>");
				}
				pagerUtil_gpal(data.curPage, data.end, data.pageSize, data.start,
						data.totalPage, data.totalRow);
				countData.append(data.totalRow);
				$("#countData1").append(item);
			}
		},
		error : function() {
			alert("系统错误！");
		}
	});
}