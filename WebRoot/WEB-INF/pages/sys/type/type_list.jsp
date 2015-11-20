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
						<h3 class="box-title">类型列表</h3>
						<div class="btn-group" style="float:right">
	                      <button type="button" class="btn btn-info" onclick="toAddType()">新增</button>
	                    </div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="data_table" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="1%"><input type="checkbox" name="ckall" onclick="checkall()" /></th>
									<th>序号</th>
									<th>新闻类型名称(一级)</th>
									<th>是否可用</th>
									<th style="text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${col_list }" var="col" varStatus="status">
									<tr class="tr_${col.id }">
										<td><input type="checkbox" name="ckevery" value="${col.id }" /></td>
										<td>${status.index+1}</td>
										<td>${col.columnName }</td>
										<td>${code_map[fn:substring(col.status,0,10)].dmsm }</td>
										<td style="text-align:center;">
											<a href="javascript:;;" onclick="editType('${col.id}' )">修改</a> | 
											<c:if test="${col.status == 1 }"> 
												<a href="javascript:;;" onclick="delType('${col.id}','0')">禁用</a> 
											</c:if>
											<c:if test="${col.status != 1 }"> 
												<a href="javascript:;;" onclick="delType('${col.id}','1')">启用</a> 
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
  <script type="text/javascript" src="js/system/type/type_list.js"></script>
</html>
