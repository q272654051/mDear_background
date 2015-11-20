<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>统计中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
</head>
<body>
<section class="content">
   <div class="row">
		<div class="box">
			<div class="box-header">
				<a class="fa fa-repeat" style="float: right" onclick="reload()" id="abc"></a>
				<form action="" class="form-inline">
					<div class="form-group">
						<label>时间:</label>
						<select class="form-control" id="cycle">
							<option value="W">周</option>
							<option value="M">月</option>
							<!-- <option value="S">季</option> -->
							<option value="Y">年</option>
						</select>
					</div>
				</form>
			</div>
			<div class="box-body">
				<div id="myEdit" style="height:400px;" ></div>
				<table id="data_table" class="table table-bordered table-hover">
					<thead>
						
					</thead>
					<tbody>
						
					</tbody>
				</table>
				<div id="myPublish" style="height:400px;"></div>
			</div>
		</div>
	</div>
</section>
</body>
<script type="text/javascript" src="plugins/echarts/build/dist/echarts-all.js"></script>
<script type="text/javascript" src="js/statistics/tongjizhongxin.js"></script>

</html>
