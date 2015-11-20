<%@ page language="java" import="java.util.*,com.mdear.www.vo.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <title></title>
	<meta charset="UTF-8">
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
						<h3 class="box-title">角色列表</h3>
						<div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info">请选择</button>
	                      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
	                        <span class="caret"></span>
	                        <span class="sr-only"></span>
	                      </button>
	                      <ul class="dropdown-menu" role="menu" style="min-width:0px;">
	                        <li><a href="javascript:;;" onclick="toAddRole()">新增</a></li>
	                        <li><a href="javascript:;;" onclick="delChk()">删除</a></li>
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
									<th>角色名</th>
									<th>角色作用</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${role_list }" var="role">
									<tr class="tr_${role.id }">
										<td><input type="checkbox" name="ckevery" value="${role.id }" /></td>
										<td>${role.id }</td>
										<td>${role.roleName }</td>
										<td>${role.roleComment }</td>
										<td style="text-align:center;">
											<a href="javascript:;;" onclick="delRole('${role.id}')">删除</a> |
											<a href="javascript:;;" onclick="editRole('${role.id}')">修改</a> 
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
  <script type="text/javascript" src="js/system/role/role_list.js"></script>
</html>
