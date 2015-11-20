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
						<h3 class="box-title">${operation}新闻类型</h3>
						<!-- <div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info">请选择</button>
	                      <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
	                        <span class="caret"></span>
	                        <span class="sr-only"></span>
	                      </button>
	                      <ul class="dropdown-menu" role="menu" style="min-width:0px;">
	                        <li><a href="javascript:;;" onclick="addUser()">保存</a></li>
	                        <li><a href="javascript:;;" onclick="exit()">退出</a></li>
	                      </ul>
	                    </div> -->
					</div>
					<div class="box-body">
						<form action="columnController/addType" method="post" id="typeForm" class="form">
							<div class="form-group">
							    <label for="userName">新闻类型名称(一级):</label>
							    <input type="text" name="columnName" value="${colu.columnName }" class="form-control" id="" placeholder="新闻类型名称(一级)">
							    <input type="hidden" value="${colu.id }" name="id"/>
						 	</div>
						 	<div class="form-group" id="inlineGroup">
							    <c:if test="${operation == '修改' }">
							     	<label for="column">下属类型名称(二级):</label>
							    	<button type="button" class="btn btn-info" onclick="AddSecondType()">新增</button><br/>
							    	<c:if test="${col_child_list != '[]' }">
										<c:forEach items="${col_child_list }" var="col_child" varStatus="i">
											<c:if test="${col_child.status == '1' }">
												<div class="inline">
													<input class="chInput" type="checkbox" name="columnId" value="${col_child.id }" checked onclick="changeCheckBox(this,'${col_child.id}','${col_child.columnName}','${col_child.pid}')"/>
													<span>${col_child.columnName}</span>&nbsp;&nbsp;
													<div class="btn-group btn-group-xs hidden"><input class="cgIpt pull-left" type="text" /><button class="btn btn-info btn-save glyphicon glyphicon-ok-circle" onclick="updateSecondType(this,'${col_child.id}','${col_child.pid}','${col_child.status}')">修改</button></div>
												</div>
											</c:if>
											<c:if test="${col_child.status == '0' }">
												<div class="inline">
													<input class="chInput" type="checkbox" name="columnId" value="${col_child.id }" onclick="changeCheckBox(this,'${col_child.id}','${col_child.columnName}','${col_child.pid}')"/>
													<span>${col_child.columnName}</span>&nbsp;&nbsp;
													<div class="btn-group btn-group-xs hidden"><input class="cgIpt pull-left" type="text" /><button class="btn btn-info btn-save glyphicon glyphicon-ok-circle" onclick="updateSecondType(this,'${col_child.id}','${col_child.pid}','${col_child.status}')">修改</button></div>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${col_child_list == '[]' }">
											<label style="font-size:14px;font-weight: normal;padding-left: 10px;" id="noData">暂无</label>
									</c:if>
								</c:if>
						 	</div>
						 	<div class="form-group hidden" id="secondType">
							    <label for="column">新增新闻类型名称(二级):</label> <br/>
								<input type="text" name="columnName" value="" class="form-control" id="" placeholder="新闻类型名称(二级)">
						 	</div>	
						 	<div class="form-group text-right">
							     <button type="button" class="btn btn-info" onclick="addType()">保存</button>
							     <button type="button" class="btn btn-info" onclick="exit()">返回</button>
						 	</div>
						 	
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
  </body>
  <script type="text/javascript" src="js/system/type/type_add.js"></script>
  <style type="text/css">
  .cgIpt{ height: 22px; line-height: 22px; font-size: 12px; }
  </style>
</html>
