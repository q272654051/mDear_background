<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>数据维护</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<!-- DATA TABLES -->
<link href="plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<link href="css/table.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#abc {
	cursor: pointer;
}
</style>
</head>
<body>
<input type="hidden" value="la" id="la">
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">数据维护</h3>
					<a class="fa fa-repeat" style="float: right" onclick="backpage()"
						id="abc"></a>
				</div>
				<div class="box-body">
					<form id="fileform" action="dataXinjianzhuzhaiController/upload"
						enctype="multipart/form-data" method="post" target="hidiframe">
						<table class="table table-condensed">
							<tr>
								<td>选择数据来源:</td>
								<td><select id="tableName" onchange="changeDateList()"
									name="tableName">
										<c:forEach items="${code_list}" var="li">
											<option selected="selected" value="${li.dmsm}">${li.dmsm1}</option>
										</c:forEach>
								</select></td>
								<td><button type="button" class="btn btn-primary"
										onclick="loaddata(1,tName)">查询</button></td>
							</tr>
							<tr>
								<td><a class="btn btn-primary"
									onclick="download_data(tName)"> <i class="fa fa-download"></i>
										下载
								</a></td>
								<td><input type="file" id="data1" name="filename" /></td>
							</tr>
							<tr>
								<td>
									<button type="button" class="btn btn-primary"
										onclick="drexcel()">导入数据</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<input type="button" value="新增数据" class="btn btn-primary" onclick="shouTr('','','','','','','','','')" /> 
					<input type="button" value="导出数据" class="btn btn-primary" onclick="dcxcel(tName)" />
					<input type="button" value="刷新" class="btn btn-primary" onclick="freshen()" />
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="data_table"
						class="table table-bordered table-condensed table-hover">
						<thead>
							<tr id="colList">
								<!-- 获取列名 -->
							</tr>
						</thead>
						<tbody id="dataList">
							<!-- 获取值 -->
						</tbody>
					</table>
				</div>
				<div id="pagegpalUl"></div>
			</div>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增数据</h4>
				</div>
				<div class="modal-body">
					<form method="post">
						<table id="dataTable" class="table table-condensed">
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="saveOrupdate()">提交</button>
				</div>
			</div>
		</div>
	</div>
	<form action="" id="downForm" target="hidiframe">
		<input type="hidden" value="" name="fileName" id="filename" />
	</form>
	<iframe id="hidiframe" style="display:none" name="hidiframe"></iframe>
</body>
<script type="text/javascript" src="js/datawh/data_list1.js"></script>
<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
