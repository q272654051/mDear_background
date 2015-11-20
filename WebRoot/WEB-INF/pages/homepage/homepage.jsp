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

<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<style>
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>
</head>

<body class="skin-blue sidebar-mini">

	<header class="main-header"> </header>
	<!-- 7个标题按钮 -->
	<div align="center">
		<div class="contianer">
			<table>
				<tr>
					<td>
						<!-- 资讯中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-primary">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">资讯中心</h4>
										</div>
										<div class="modal-body">
											<p>资讯中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="zxzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td>
						<!-- 数据中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-info">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">数据中心</h4>
										</div>
										<div class="modal-body">
											<p>数据中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="sjzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td>
						<!-- 统计中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-primary">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">统计中心</h4>
										</div>
										<div class="modal-body">
											<p>统计中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="tjzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
				</tr>

				<tr>
					<td>
						<!-- 审核中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-success">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">审核中心</h4>
										</div>
										<div class="modal-body">
											<p>审核中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="shzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td>
						<!-- 日志中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-danger">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">日志中心</h4>
										</div>
										<div class="modal-body">
											<p>日志中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="rzzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td>
						<!-- 抓取中心按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-warning">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">抓取中心</h4>
										</div>
										<div class="modal-body">
											<p>抓取中心</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="zqzxButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
				</tr>

				<tr>
					<td>
						<!-- 市研云盘按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-info">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">市研云盘</h4>
										</div>
										<div class="modal-body">
											<p>市研云盘</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline"
												onclick="syypButton()">进入</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td>
						<!-- 尽请期待按钮 --> <section class="content">
						<div class="example-modal" style="width:200px; height:100px;">
							<div class="modal modal-warning">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">尽请期待...</h4>
										</div>
										<div class="modal-body">
											<p>尽请期待...</p>
										</div>
										<div class="modal-footer" style="width:200px; height:60px;">
											<button type="button" class="btn btn-outline">尽请期待...</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</section>
					</td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- js 7大按钮的function -->
	<script type="text/javascript" src="js/homepage/homepage.js"></script>
	<!-- js 其他 -->
	<script type="text/javascript" src="js/datawh/data_list.js"></script>
	<script type="text/javascript" src="js/news/news_list.js"></script>
</body>
</html>
