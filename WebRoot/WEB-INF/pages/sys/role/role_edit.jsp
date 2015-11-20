<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    <section class="content" style="width:50%;height:10%;">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">${operation }用户</h3>
						<div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info">请选择</button>
	                      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
	                        <span class="caret"></span>
	                        <span class="sr-only"></span>
	                      </button>
	                      <ul class="dropdown-menu" role="menu" style="min-width:0px;">
	                        <li><a href="javascript:;;" onclick="addUser()">保存</a></li>
	                        <li><a href="javascript:;;" onclick="exit()">退出</a></li>
	                      </ul>
	                    </div>
					</div>
					<div class="box-body">
						<form action="userController/addUser" method="post" id="roleForm">
							<div class="form-group">
							    <label for="roleName">角色名称:</label>
							    <input type="text" name="roleName" value="${role.roleName }" class="form-control" id="" placeholder="角色名称">
							    <input type= "hidden" name="id" value="${role.id }"/>
						 	</div>
						 	<div class="form-group">
							    <label for="roleComment">角色作用:</label>
							    <input type="text" name="roleComment" value="${role.roleComment }" class="form-control" id="" placeholder="角色作用">
						 	</div>
						 	<div class="form-group">
							    <label for="roleComment">菜单权限:</label> <br/>
							    <c:forEach items="${role_menu_list }" var="menu">
							    	<input type="checkbox" name="menu_id" value="${menu.id }" checked/>${menu.menuname }
							    </c:forEach>
							    <c:forEach items="${menu_list }" var="menu">
							    	<input type="checkbox" name="menu_id" value="${menu.id }"/>${menu.menuname }
							    </c:forEach>
						 	</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
  </body>
</html>
