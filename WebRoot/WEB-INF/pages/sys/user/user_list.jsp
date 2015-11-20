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
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    <section class="content">
    	<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">用户列表</h3>
						<div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info">请选择</button>
	                      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
	                        <span class="caret"></span>
	                        <span class="sr-only"></span>
	                      </button>
	                      <ul class="dropdown-menu" role="menu" style="min-width:0px;">
	                        <li><a href="javascript:;;" onclick="toAddUser()">新增</a></li>
	                        <li><a href="javascript:;;" onclick="">禁用</a></li>
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
									<th>用户名</th>
									<th>是否可用</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${user_list }" var="user">
									<tr class="tr_${user.id }">
										<td><input type="checkbox" name="ckevery"
											value="${user.id }" /></td>
										<td>${user.id }</td>
										<td>${user.userName }</td>
										<td>${code_map[fn:substring(user.status,0,10)].dmsm }</td>
										<td style="text-align:center;">
											<a href="javascript:;;" onclick="editUser('${user.id}' )">修改</a> | 
											<c:if test="${user.status == 1 }"> 
												<a href="javascript:;;" onclick="delUser('${user.id}','0')">禁用</a> 
											</c:if>
											<c:if test="${user.status != 1 }"> 
												<a href="javascript:;;" onclick="delUser('${user.id}','1')">启用</a> 
											</c:if>
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
  <script type="text/javascript" src="js/system/user/user_list.js"></script>
</html>
