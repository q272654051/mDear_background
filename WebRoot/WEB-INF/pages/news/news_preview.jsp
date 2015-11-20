<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资讯预览</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		<div>资讯预览>>
  		</div><br><br>
		<div align="center">
			<div>${news.title }</div>
			
			<div>${news.fabudate }</div>
			
			<div><c:forEach items="${user_list }" var="user">
			<c:if test="${news.shenheid==user.id}">${user.userName }</c:if> 
			</c:forEach>
			</div>
			
			<div>
			<c:forEach items="${user_list }" var="user">
			<c:if test="${news.shenheid==user.id}">${user.userName }</c:if> 
			</c:forEach>
			</div>
			
			<div>${news.cont }</div>
		</div>

  </body>
</html>
