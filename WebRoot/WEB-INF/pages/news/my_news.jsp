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

<title>我的资讯</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!-- 选择时间段样式 -->
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
									
									<td class="td_label md">标题:<input type="text" value="" id="title" /></td>
									<td class="td_label md">状态: <select id="status" name="status">
											<c:forEach items="${code_list }" var="code" varStatus="i">
												<option value="${code.dmz }" class="dmsm">${code.dmsm }</option>
											</c:forEach>
									</select></td>
									<td class="td_label lg">资讯类别:<select id="type" name="type">
											<c:forEach items="${col_list }" var="column" varStatus="i">
												<c:if test="${column.pid == '3' }">
													<option value="${column.id }">${column.columnName }</option>
												</c:if>
											</c:forEach>
									</select></td>
									<td class="td_label lg">编辑时间:</td>
									<td class="td_inpt">
										<div class="input-group">
											<button class="btn btn-default pull-right" type="button" id="daterange-btn">
												<i class="fa fa-calendar"></i> 选择时间段 <i class="fa fa-caret-down"></i>
											</button>
											<input type="hidden"  id="startTime" class="startTime" > 
											<input type="hidden" id="endTime" class="endTime">
											<td id="reportrange"><span class="date_span" id="qishishijian"></span></td>
										</div>
									</td>
									<td id="reportrange"><button type="button"
											class="btn btn-info" onclick="findwodezixun()">查询</button></td>
									<td id="reportrange"><button type="button"
											class="btn btn-info" onclick="">批量提交到审核</button></td>
									<td id="reportrange"><button type="button"
											class="btn btn-info" onclick="">批量删除</button>
										</td>
										<td id="reportrange"><button type="button" class="btn btn-info" onclick="edit(0)">新增资讯</button>
										</td>
									<td class="td_label">
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
						<h3 class="box-title">我的资讯</h3>
						<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
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
									<th>状态</th>
									<th>类别</th>
									<th>最后编辑时间</th>
									<th>发布时间</th>
									<th>审核人</th>
									<th>退回原因</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${news_list }" var="news">
									<tr class="tr_${news.id }">
										<td><input type="checkbox" name="ckevery" value="${news.id }" /></td>
										<td>${news.id }</td>
										<td>${news.title }</td>
										<td>${code_map[news.status] }</td>
										<td>${column_map[news.smalltype] }</td>
										<td>${news.editdate }</td>
										<td>${news.fabudate }</td>
										<td>${userId_map[fn:substring(news.shenheid,0,10)].userName }</td>
										<td>${news.tuihuiyuanyin }</td>
										<td style="text-align:center;">
										<a href="javascript:;;" onclick="preview(${news.id })" class="btn btn-info">预览</a>
											<a href="javascript:;;" onclick="edit('${news.id}')" class="btn btn-info">编辑</a>
											<a href="javascript:;;" onclick="del('${news.id}')" class="btn btn-info">删除</a>
										<c:if test="${news.status=='2' }">
											<a href="javascript:;;" onclick="commitNews('${news.id}')" class="btn btn-info">发布</a>
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
</body>
<!-- DATA TABES SCRIPT 数据排序，上下一页-->
<script src="plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news.js"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news_list.js"></script>
	<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
