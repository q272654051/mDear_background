<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.mdear.www.vo.News"%>
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

<title>审核中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!-- DATA TABLES -->
<link href="plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
		<style type="text/css">
	#abc{cursor: pointer;}
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

									<td class="td_label md">撰写人： <select name="zhuanxierenid"
										id="zhuanxierenid">
											<c:forEach items="#{user_list }" var="user">
												<option value="${user.id }">${user.userName }</option>
											</c:forEach>
									</select>
									<td><button type="button" class="btn btn-info"
											onclick="findshenhezhongxin()">查询</button> <span class="date_span"></span></td>
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
						<h3 class="box-title">审核中心</h3>
							<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="1%"><input type="checkbox" name="ckall" onclick="checkall()" /></th>
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
										<td><input type="checkbox" name="ckevery"
											value="${news.id }" /></td>
										<td>${news.id }</td>
										<td>${news.title }</td>
										<td>${col_list[news.type].columnName }</td>
										<td>${news.lyName }</td>
										<td>${news.fabudate }</td>
										<td>${code_map[news.status] }</td>
										<td>${userId_map[fn:substring(news.userid,0,10)].userName }</td>
										<td>${userId_map[fn:substring(news.shenheid,0,10)].userName }</td>
										<td style="text-align:center;"><a href="javascript:;;"
											onclick="preview('${news.id}')">预览</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<!-- DATA TABES SCRIPT 数据排序，上下一页-->
<script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8"
	src="js/news/news_list.js"></script>

	<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
