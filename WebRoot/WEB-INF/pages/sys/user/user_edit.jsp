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
						<h3 class="box-title">${operation }角色</h3>
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
						<form action="userController/addUser" method="post" id="userForm" class="form">
							<div class="form-group">
							    <label for="userName">用户名:</label>
							    <input type="text" name="userName" value="${user.userName }" class="form-control" id="" placeholder="你的名字">
							    <input type="hidden" value="${user.id }" name="id"/>
						 	</div>
						 	<div class="form-group">
							    <label for="status">是否可用:</label> <br/>
							    <input type="radio" name="status" value="1">可用
							    <input type="radio" name="status" value="0">不可用
							    <input type="hidden" value="${user.status }" id="status" />
						 	</div>	
						 	<div class="form-group">
							    <label for="role">用户角色:</label> <br/>
							    <c:forEach items="${user_role_list }" var="user_role">
							    	<input type="radio" name="roleId" value="${user_role.id }" checked>${user_role.roleName }
							    </c:forEach>
						    	<c:forEach items="${role_list }" var="role">
							    	<input type="radio" name="roleId" value="${role.id }" >${role.roleName }
						    	</c:forEach>
						 	</div>
						 	<div class="form-group">
							    <label for="column">审核权限:</label> <br/>
							    <c:forEach items="${user_col_list }" var="user_col" varStatus="i">
									<c:if test="${user_col.pid == '1' }">
										<input type="checkbox" name="columnId" value="${user_col.id }" checked/>${user_col.columnName }
									</c:if>
								</c:forEach>
							    <c:forEach items="${col_list }" var="column" varStatus="i">
									<c:if test="${column.pid == '1' }">
										<input type="checkbox" name="columnId" value="${column.id }" />${column.columnName }
									</c:if>
								</c:forEach>
						 	</div>	
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
  </body>
  <script type="text/javascript" src="js/system/user/user_add.js"></script>
</html>
