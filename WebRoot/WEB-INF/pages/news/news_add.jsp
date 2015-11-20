<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.mdear.www.vo.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>新增资讯</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link rel="stylesheet" href="js/kindeditor-4.1.10/themes/default/default.css" /> 
<style type="text/css">
	#abc{cursor: pointer;}
	</style>
</head>

<body>
	<form role="form" id="newsForm" action="#" method="post">
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-lg-6">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">新增资讯</h3>
							<a class="fa fa-repeat" style="float: right" onclick="backpage()" id="abc"></a>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<div class="box-body">
							<div class="form-group">
								<label>标题</label>
								<input type="text" name="title" value="${news.title }" class="form-control" placeholder="Enter ..." />
								<input type="hidden" name="status" id="status" value="" />
								<input type="hidden" name="id" id="id" value="${news.id }" />
							</div>
							<div class="form-group">
								<label>封面图片</label> 
								<input type="text" id="url1" value="" name="imageurl" /> 
								<input type="button" id="image1" value="选择图片" />（网络图片 + 本地上传）
								<img alt="" src="${news.imageurl }" id="fmimg" name="fmimg" title="" width="200" height="200" />
							</div>

							<div class="form-group">
								<label>新闻类型</label>
								<div class="input-group">
									<select class="form-control" style="width:300px;" name="type">
										<c:forEach items="${col_list }" var="column"  varStatus="i">
											<c:if test="${column.id == news.type}">
												<option value="${column.id }" selected>${column.columnName }</option>
											</c:if>
											<c:if test="${column.id != news.type}">
												<option value="${column.id }" >${column.columnName }</option>
											</c:if>
										</c:forEach>
									</select>
									<select class="form-control" style="width:300px;" name="smalltype">
									</select>
									<input type="hidden" value="${news.smalltype }" id="smalltypehid"/>
								</div>
							</div>
							<div class="form-group">
								<label>城市</label>
								<div class="input-group">
									<select class="form-control" style="width:300px;" name="province">
										<c:forEach items="${city_list }" var="citys" varStatus="i">
											<c:if test="${citys.upid == '0' && citys.id == news.province}">
												<option value="${citys.id }" selected>${citys.name }</option>
											</c:if>
											<c:if test="${citys.upid == '0' && citys.id != news.province}">
												<option value="${citys.id }">${citys.name }</option>
											</c:if>
										</c:forEach>
									</select> 
									<select class="form-control" style="width:300px;" name="city">
									</select>
									<input type="hidden" value="${news.city }" id="cityhid" />
								</div>
							</div>
							<div class="form-group">
								<label>关键字</label>
								<textarea class="form-control" name="keyword" rows="3" placeholder="Enter ...">${news.keyword }</textarea>
							</div>
							<div class="form-group">
								<label>来源</label> 
								<input type="text" name="lyName" disabled class="form-control" value="云房数据市场研究部" placeholder="云房数据市场研究部" />
							</div>
							<div class="form-group">
								<label>摘要</label>
								<textarea class="form-control" rows="3" name="zhaiyao" placeholder="云房数据市场研究部">${news.zhaiyao }</textarea>
							</div>
							<div class="form-group">
								<label>审核人</label>
								<select class="form-control" name="shenheid"></select>
								<input type="hidden" value="${news.shenheid }" id="shenheidhid"/>
							</div>
							<div class="form-group">
								<label>发布平台</label>
								<c:forEach items="${plateformId_list }" var="plateform">
									<input type="checkbox" value="${plateform.dmz }" name="plateformId" />${plateform.dmsm }
								</c:forEach>
								<input type="hidden" value="${news.plateformId }" />
							</div>
						</div>
					</div>
					<!-- end .box-parimary -->
				</div>
				<div class="col-md-6">
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">
								正文 <small>some advise</small>
							</h3>
							<!-- tools box -->
							<div class="pull-right box-tools">
								<button type="button" onclick="addNews(1)"
									class="btn btn-primary">保存</button>
								<button type="button" onclick="addNews(2)"
									class="btn btn-primary">保存并提交</button>
							</div>
							<!-- /end .tools -->
						</div>
						<!-- /end .box-header -->
						<div class="box-body pad">
							<script id="editor" type="text/plain" style="width:100%;height:666px;"></script>
							<div style="display:none;">
								<textarea rows="0" cols="0" id="cont" name="cont">${news.cont }</textarea>
							</div>
						</div>
						<div class="box-footer"></div>
					</div>
					<!-- /.box box-info-->
				</div>
			</div>
		</section>
	</form>
</body>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="js/news/news_add.js"></script>
	<!-- 返回首页 -->
<script type="text/javascript" src="js/base.js"></script>

</html>
