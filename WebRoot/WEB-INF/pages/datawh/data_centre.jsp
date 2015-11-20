<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mdear.www.vo.DataXinjianzhuzhai"%>
<%@ page import="com.mdear.www.vo.Code"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>数据中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<style type="text/css">
	#abc{cursor: pointer;}
	</style>
</head>
<body>
<input type="hidden" value="1" id="data_centre">
	<section class="content">
	<form role="">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
				<div class="box-header with-border">
							<h3 class="box-title">数据中心</h3>
							<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
						</div>
					<div class="box-body">
						<table class="table table-condensed">
							<tr>
								<td>数据类型:</td>
								<td><select id="tableName" onchange="setTableName()" name="tableName">
										<c:forEach items="${code_list}" var="li">
											<option selected="selected" value="${li.dmsm}">${li.dmsm1}</option>
										</c:forEach>
								</select></td>
								<td><button type="button" class="btn btn-primary"
										onclick="getvalue(1,tName)">查询</button></td>
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
				<div class="box-header"></div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="data_table"
						class="table table-bordered table-condensed table-hover">
						<thead>
							<tr id="getrow">
								<!-- 获取列名 -->
							</tr>
						</thead>
						<tbody id="getvalue">
							<!-- 获取值 -->
						</tbody>
					</table>
				</div>
				<div id="pagegpalUl"></div>
			</div>
			本月(<span id="countData1"></span>)数据共<span id="countData"></span>条。
		</div>
	</div>
	</section>
</body>
<script type="text/javascript" src="js/datawh/data_list.js"></script>
<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
