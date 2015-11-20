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

<title>日志中心</title>
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

									<td class="td_label md">操作人: <select id="status" name="status">
											<c:forEach items="${code_list }" var="code" varStatus="i">
												<option value="${code.dmz }" class="dmsm">${code.dmsm }</option>
											</c:forEach>
									</select></td>
									<td class="td_label lg">操作类型:<select id="type" name="type">
											<c:forEach items="${col_list }" var="column" varStatus="i">
												<c:if test="${column.pid == '1' }">
													<option value="${column.id }">${column.columnName }</option>
												</c:if>
											</c:forEach>
									</select></td>
									<td class="td_label lg">时间:</td>
									<td class="td_inpt">
										<div class="input-group">
											<button class="btn btn-default pull-right" type="button"
												id="daterange-btn">
												<i class="fa fa-calendar"></i> 选择时间段 <i
													class="fa fa-caret-down"></i>
											</button>
											<input type="hidden" id="startTime" class="startTime">
											<input type="hidden" id="endTime" class="endTime">
											<td id="reportrange"><span class="date_span"
												id="qishishijian"></span></td>
										</div>
									</td>
									<td id="reportrange"><button type="button"
											class="btn btn-info" onclick="findzixunzhongxin()">查询</button></td>
									<td class="td_label"></td>
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
						<h3 class="box-title">日志中心</h3>
						<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>类型</th>
									<th>操作人</th>
									<th>操作详情</th>
									<th>时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list_log }" var="log">
									<tr class="tr_${log.id }">
										<td>${log.id }</td>
										<td>${column_map[log.id] }</td>
										<td>${log.userid }</td>
										<td>${log.params }</td>
										<td>${log.createdate }</td>
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
<script type="text/javascript" charset="utf-8" src="js/news/news.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/news/news_list.js"></script>
		<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
