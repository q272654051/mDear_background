<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.mdear.www.vo.News"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>新闻列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!-- DATA TABLES -->
<link href="plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
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
									<td class="td_label md">标题:</td>
									<td><input type="text" value="" class="" /></td>

									<td class="td_label md">状态:</td>
									<td class="td_inpt">
									<select class="" name="status">
											<c:forEach items="${code_list }" var="code" varStatus="i">
												<option value="${code.dmz }">${code.dmsm }</option>
											</c:forEach>
									</select></td>
									<td class="td_label lg">资讯类别:</td>
									<td class="input-group"><select class="" name="type">
											<c:forEach items="${col_list }" var="column" varStatus="i">
												<c:if test="${column.pid == '1' }">
													<option value="${column.id }">${column.columnName }</option>
												</c:if>
											</c:forEach>
									</select> <select class="" name="smalltype">
									</select></td>
									<td class="td_label lg">编辑时间:</td>
									<td class="td_inpt">
										<div class="input-group">
											<button class="btn btn-default pull-right" type="button"
												id="daterange-btn">
												<i class="fa fa-calendar"></i> 选择时间段 <i
													class="fa fa-caret-down"></i>
											</button>
											<input type="hidden" value="" name="startTime"> <input
												type="hidden" value="" name="endTime">
										</div>
									</td>
									<td id="reportrange"><span class="date_span"></span></td>
									<td class="td_label">
										
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
						<h3 class="box-title">新闻列表</h3>
						<div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info">请选择</button>
	                      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
	                        <span class="caret"></span>
	                        <span class="sr-only"></span>
	                      </button>
	                      <ul class="dropdown-menu" role="menu">
	                        <li><a href="javascript:;;" onclick="">搜索</a></li>
	                        <li><a href="javascript:;;" onclick="delall()">删除</a></li>
	                        <li><a href="javascript:;;" onclick="commitall()">提交</a></li>
	                      </ul>
	                    </div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="1%"><input type="checkbox" name="ckall" onclick="checkall()" /></th>
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
											<a href="javascript:;;" onclick="preview('${news.id}')">预览</a>|
											<a href="javascript:;;" onclick="edit('${news.id}')">编辑</a>| 
											<a href="javascript:;;" onclick="commitNews('${news.id}')">提交</a>| 
											<a href="javascript:;;" onclick="del('${news.id}')">删除</a>
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

<script type="text/javascript" charset="utf-8" src="js/news/news.js"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news_list.js"></script>
<!-- date-range-picker -->
<script src="js/style/moment.min.js" type="text/javascript"></script>
<script src="plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
</html>
