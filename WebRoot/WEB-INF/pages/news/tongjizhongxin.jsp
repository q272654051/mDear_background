<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>统计中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
			<style type="text/css">
	#abc{cursor: pointer;}
	</style>
</head>
<body>
	<div class="box-header">
		<h3 class="box-title">统计中心</h3>
		<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
	</div>
	<div id="main" style="height:400px"></div>
	<div>
	目前为止，‘我的编辑’数据共${list_wodebianji}条<br>
	目前为止，‘我的发布’数据共${list_wodefabu}条<br>
	目前为止，‘本月新增’数据共${list_benyuexinzeng }条<br>
	目前为止，‘本月发布’数据共${list_benyuefabu }条<br>
	目前为止，‘按年统计’数据共${list_bennianxinzeng }条<br>
	</div>
</body>
<script type="text/javascript"
	src="plugins/echarts/build/dist/echarts-all.js"></script>
<script type="text/javascript" src="js/news/tongjizhongxin.js"></script>
	<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>

</html>
