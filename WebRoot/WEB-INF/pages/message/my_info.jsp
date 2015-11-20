<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mdear.www.vo.Messagevo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<link href="plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />

<head>
<base href="<%=basePath%>">

<title>个人消息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#abc {
	cursor: pointer;
}
</style>
</head>
<body>
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">个人消息</h3>
						<a class="fa fa-repeat" style="float: right" onclick="backpage()"
							id="abc"></a>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>消息内容</th>
									<td>消息状态</td>
									<th>时间</th>
									<th>是否已读</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${message_list }" var="message">
									<tr>
										<td>${message.id }</td>
										<td>${message.message}</td>
										<td><c:forEach items="${code_list }" var="user">
												<c:if test="${message.status==user.dmz}">${user.dmsm }</c:if>
											</c:forEach></td>
										<td>${message.shijian}</td>
										<td>
										<c:if test="${message.messageStatus==1}">已读</c:if>
										<c:if test="${message.messageStatus==0}">未读</c:if>
										</td>
										<td>
											<div class="btn-group">
												<button class="btn btn-default" type="button"
													onclick="seeMsg()">查看详情</button>
												<button class="btn btn-default" type="button"
													onclick="preview('${message.newsid}')">预览</button>
												<div class="btn-group">
													<button class="btn btn-default dropdown-toggle"
														data-toggle="dropdown" type="button" aria-expanded="false">
														处理 <span class="caret"></span>
													</button>
													<ul class="dropdown-menu">
														<li>
														<a onclick="edit('${message.newsid}')">编辑</a>
														</li>
														<li>
														<c:if test="${message.status=='3' }">
														<a href="javascript:;;" onclick="publish('${message.newsid}',2)">退回</a>
														</c:if>
														</li>
														<li>
														<c:if test="${message.status=='1'||	message.status=='2' }">
														<a href="javascript:;;" onclick="publish('${message.newsid}',3)">发布</a>
														</c:if>
														</li>
													</ul>
												</div>
											</div>
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
<script src="plugins/datatables/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/news/news_list.js"></script>
<script type="text/javascript" src="js/news/news.js"></script>
<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
