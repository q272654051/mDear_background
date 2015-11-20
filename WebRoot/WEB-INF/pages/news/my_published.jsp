<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>我的发布</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!-- DATA TABLES -->
<link href="plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
#abc {
	cursor: pointer;
}
</style>
</head>


<body>
	<section class="content">
		<form>
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body">
							<table class="table table-condensed">
								<tr>
									<td class="td_label md">标题: <input type="text" value="" id="title" /></td>
									<td class="td_label lg">发布时间:</td>
									<td class="td_inpt">
										<div class="input-group">
											<button class="btn btn-default pull-right" type="button" id="daterange-btn">
												<i class="fa fa-calendar"></i> 选择时间段 <i class="fa fa-caret-down"></i>
											</button>
											<input type="hidden" value="" id="startTime" name="startTime">
											<input type="hidden" value="" id="endTime" name="endTime">
											<td id="reportrange"><span class="date_span" id="qishishijian"></span></td>
										</div>
									</td>

									<td class="td_label md">撰写人：
									 <select name="zhuanxierenid" id="zhuanxierenid">
											<c:forEach items="#{user_list }" var="user">
												<option value="${user.id }">${user.userName }</option>
											</c:forEach>
									</select></td>
									<td><button type="button" class="btn btn-info"
											onclick="findwodefabu()">查询</button> <span class="date_span"></span></td>
									<!-- <button type="button" id="search-btn" class="btn btn-primary">
											<i class="fa fa-search"></i>搜索
										</button>
										<button type="button" class="btn btn-primary"
											onclick="delall()">批量删除</button>
										<button type="button" class="btn btn-primary"
											onclick="commitall()">批量提交</button> -->
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">我的发布</h3>
						<a class="fa fa-repeat" style="float: right" onclick="backpage()"
							id="abc"></a>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="1%"><input type="checkbox" name="ckall"
										onclick="checkall()" /></th>
									<th>编号</th>
									<th>标题</th>
									<th>类别</th>
									<th>来源</th>
									<th>发布时间</th>
									<th>状态</th>
									<th>撰写人</th>
									<th>审核人</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${news_list }" var="news">
									<tr class="tr_${news.id }">
										<td>
											<input type="checkbox" name="ckevery" value="${news.id }" />
										</td>
										<td>${news.id }</td>
										<td>${news.title }</td>
										<td>${col_list[news.type].columnName }</td>
										<td>${news.lyName }</td>
										<td>${news.fabudate }</td>
										<td>${code_map[news.status] }</td>
										<td>${userId_map[fn:substring(news.userid,0,10)].userName }</td>
										<td>${userId_map[fn:substring(news.shenheid,0,10)].userName }</td>
										<td style="text-align:center;">
											<a href="javascript:;;" onclick="preview('${news.id}')">预览</a>| 
											<c:if test="${news.iflunbo=='1' }">
											<a href="javascript:;;" onclick="unCarousel('${news.id}')">取消轮播</a>| 
											</c:if>
											<c:if test="${news.iflunbo=='0' }">
											<a href="javascript:;;" onclick="setCarousel('${news.id}')">设置轮播</a>| 
											</c:if>
											<c:if test="${news.status=='3' }">
											<a href="javascript:;;" onclick="aa('${news.id}')">退回</a>
											</c:if>
											<c:if test="${news.status=='5' }">
											<a href="javascript:;;" onclick="publish('${news.id}',3)">发布</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请输入退回的原因?</h4>
				</div>
				<div class="modal-body">
					<form method="post">
						<table id="dataTable" class="table table-condensed">
							<textarea cols="85" rows="5" id="tuihuiyuanyin" class="tuihuiyuanyin"></textarea>
							<input type="hidden" value id="newsid"/>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="tuihui()">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- DATA TABES SCRIPT 数据排序，上下一页-->
<script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news.js"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news_list.js"></script>
<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript">
function aa(newsid){
	$('#myModal').modal('show');
	$('#newsid').val(newsid);
}
function tuihui(){
	var newsid = $('#newsid').val();
	publish(newsid,4);
}
</script>


</html>
