<%@ page language="java" import="java.util.*,com.mdear.www.vo.*"
	pageEncoding="utf-8"%>
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

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/slider.css" rel="stylesheet" type="text/css" />
<link href="plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
	<style type="text/css">
	#abc{cursor: pointer;}
	</style>
</head>

<body>
	<section class="content">
		<form role="">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">关联设置</h3>
							<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
						</div>
						<div class="box-body">
							<table class="table table-bordered" style="height:750px">
								<tr>
									<td width="20%">
										<ul class="">
											<c:forEach items="${user_list }" var="user">
												<li style="cursor: pointer;" onclick="folder('${user.id}')">
													<a href="javascript:;;"><i
														class="fa fa-fw fa-plus-square"></i> <span>${user.userName
															}</span>
												</a>
													<ul class="" id="menu_${user.id }" style="display: none;">
														<c:forEach items="${user_role_list }" var="user_role">
															<c:if test="${user.id == user_role.userId }">
																<c:forEach items="${role_list }" var="role">
																	<c:if test="${user_role.roleId == role.id }">
																		<li><a href="javascript:;" url=""><i
																				class="fa fa-check-square-o"></i> <span
																				style="font-size: 10px;">${role.roleName }</span>
																		</a>
																		</li>
																	</c:if>
																	<c:if test="${user_role.roleId != role.id }">
																		<li><a href="javascript:;" url=""><i
																				class="fa fa-square-o"></i> <span
																				style="font-size: 10px;">${role.roleName }</span>
																		</a>
																		</li>
																	</c:if>
																</c:forEach>
															</c:if>
														</c:forEach>
													</ul></li>
											</c:forEach>

										</ul></td>
									<td width="75%">
									<div class="row">
										<div class="col-xs-12">
											<div class="box">
												<div class="box-header">
													<h3 class="box-title">菜单列表</h3>
													<div class="btn-group" style="float:right">
														<button type="button" class="btn btn-info btn-sm">确&nbsp;&nbsp;定</button>
													</div>
												</div>
												<div class="box-body">
													<table id="data_table" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th width="1%"><input type="checkbox" name="ckall"
																	onclick="checkall()" />
																</th>
																<th>菜单名称</th>
																<th>菜单链接</th>
																<th>是否启用</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${menu_list }" var="menu">
																<tr>
																	<td><input type="checkbox" name="ckevery"
																		value="${role.id }" />
																	</td>
																	<td>${menu.menuname }</td>
																	<td>${menu.url }</td>
																	<td>${code_map[fn:substring(menu.status,0,10)].dmsm }</td>
																	<td>修改</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
												
										
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
<script type="text/javascript" src="js/system/link/link.js"></script>
	<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>
</html>
