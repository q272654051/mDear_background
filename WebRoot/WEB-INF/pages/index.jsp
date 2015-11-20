<%@ page language="java"
	import="java.util.*,com.mdear.www.vo.Menu" pageEncoding="UTF-8"%>
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
<style type="text/css">
#abc {
	cursor: pointer;
}
</style>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>MD后台管理系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.4 -->
<link href="content/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link href="font-awesome-4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
<link href="content/js/style/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="content/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<link href="content/dist/css/skins/_all-skins.min.css" rel="stylesheet"
	type="text/css" />
<!-- iCheck -->
<link href="content/plugins/iCheck/flat/blue.css" rel="stylesheet"
	type="text/css" />
<!-- Morris chart -->
<link href="content/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
<!-- jvectormap -->
<link href="content/plugins/jvectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker -->
<link href="content/plugins/datepicker/datepicker3.css" rel="stylesheet"
	type="text/css" />
<!-- Daterange picker -->
<link href="content/plugins/daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<!-- bootstrap wysihtml5 - text editor -->
<link href="content/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />
</head>
<!-- 皮肤可以通过js调用动态加载 -->
<body class="skin-blue sidebar-mini">
	<!--整个页面放在一个容器内  -->
	<div class="wrapper">
		<!-- 头部横条,包括logo,消息,提示,任务,个人头像,人名,整体设置 -->
		<header class="main-header">
			<!-- Logo -->
			<a onclick="backpage()" class="logo" id="abc"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini">后台管理</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>MD后台管理系统</b></span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-envelope-o"></i> <span class="label label-success"
								id="unmessagecount"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="header" id="messagecount"></li>
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li id="newsid">
											<!-- 调用js 循环显示未读消息列表 -->
										</li>
									</ul>
								</li>
								<li class="footer"><a onclick="unmessage(1)">查询全部</a></li>
							</ul></li>
						<li class="dropdown user user-menu">
							<a href="#" class="dropdown-toggle" > 
								<c:if test="${sessionScope.user_info.gender==1 }">
										<img src="content/dist/img/gile.jpg" class="user-image" alt="User Image" />
								</c:if> 
								<c:if test="${sessionScope.user_info.gender==0 }">
										<img src="content/dist/img/boy.jpg" class="user-image" alt="User Image" />
								</c:if> 
								<span class="hidden-xs">${sessionScope.user_info.userName }</span>
							</a>
							<%-- <ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><c:if
										test="${sessionScope.user_info.gender==1 }">
										<img src="dist/img/gile.jpg" class="img-circle" alt="User Image" />
									</c:if> 
									<c:if test="${sessionScope.user_info.gender==0 }">
										<img src="dist/img/boy.jpg" class="img-circle" alt="User Image" />
									</c:if> 
									<!-- <p>  Alexander Pierce - Web Developer <small>Member since Nov. 2012</small> </p> -->
								</li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="col-xs-4 text-center">
										<a onclick="zqzxButton()">发布</a>
									</div>
									<div class="col-xs-4 text-center">
										<a onclick="zqzxButton()">待审</a>
									</div>
									<div class="col-xs-4 text-center">
										<a onclick="zqzxButton()">退回</a>
									</div>
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a onclick="zqzxButton()" class="btn btn-default btn-flat">个人中心</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出</a>
									</div>
								</li>
							</ul> --%>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<!-- <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li> -->
					</ul>
				</div>
			</nav>
		</header>

		<!-- 左边导航 -->
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header" id='jnkc' style="font-size: 15px;color: white"></li>
					<c:forEach items="${roleMenu_list }" var="roleMenu">
						<c:forEach items="${menu_list }" var="menu">
							<c:if test="${roleMenu.menuId == menu.id && menu.pid =='0' }">
								<li class="treeview">
									<a href="javascript:;;"
									url="${menu.url }"> <i class="fa"></i> <span>${menu.menuname }</span>
										<i class="fa fa-angle-left pull-right"></i>
									</a>
									<ul class="treeview-menu">
										<c:forEach items="${menu_list }" var="sub_menu">
											<c:if test="${sub_menu.pid == menu.id }">
												<c:forEach items="${roleMenu_list }" var="sub_roleMenu">
													<c:if test="${sub_roleMenu.menuId == sub_menu.id }">
														<li><a href="javascript:;" url="${sub_menu.url }"><i
																class="fa"></i> ${sub_menu.menuname }</a></li>
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							</c:if>
						</c:forEach>
					</c:forEach>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- 右侧容器 -->
		<div class="content-wrapper"></div>
		<!-- 頁面底部 -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2015-2016 <a
				href="javascript:void(0);">mdear.com</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<!-- jQuery 2.1.4 -->
</body>
<!-- jQuery 2.1.4 -->
<script src="content/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="content/js/style/jquery-ui.min.js" type="text/javascript"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script type="text/javascript">
	$.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.2 JS -->
<script src="content/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- <!-- Morris.js charts 折线图 -->
<script src="js/style/raphael-min.js"></script>
<script src="content/plugins/morris/morris.min.js" type="text/javascript"></script>
<!-- Sparkline 迷你图-->
<script src="content/plugins/sparkline/jquery.sparkline.min.js"
	type="text/javascript"></script>
<!-- DATA TABES SCRIPT 数据排序，上下一页-->
<script src="content/plugins/datatables/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script src="content/plugins/datatables/dataTables.bootstrap.min.js"
	type="text/javascript"></script>
<!-- jvectormap 地图 -->
<script src="content/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
	type="text/javascript"></script>
<script src="content/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
	type="text/javascript"></script>
<!-- jQuery Knob Chart 旋转按钮-->
<script src="content/plugins/knob/jquery.knob.js" type="text/javascript"></script>
<!-- <!-- 日期选择器 -->
<script src="content/js/style/moment.min.js" type="text/javascript"></script>
<script src="content/plugins/daterangepicker/daterangepicker.js"
	type="text/javascript"></script>
<!-- 日期选择器 -->
<script src="content/plugins/datepicker/bootstrap-datepicker.js"
	type="text/javascript"></script>
<!-- Bootstrap WYSIHTML5 -->
<script
	src="content/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
	type="text/javascript"></script>
<!-- Slimscroll -->
<script src="content/plugins/slimScroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<!-- FastClick -->
<script src="content/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="content/dist/js/app.min.js" type="text/javascript"></script>

<!-- user js -->
<script src="content/js/base.js" type="text/javascript"></script>
<!-- 消息未读提醒，循环显示未读消息 -->
<script type="text/javascript">
	$(function() {
		$(".treeview a").each(function() {
			$(this).click(function() {
				var url = $(this).attr("url");
				if (url != '#') {
					$(".content-wrapper").load(url);
				}
			});
		});
		$(".content-wrapper").load("loginController/homepage");
	});
</script>

<script type="text/javascript">
	$("#img").val();
</script>
<!-- 个人消息 -->
<script type="text/javascript" src="content/js/message/my_info.js"></script>
<!-- 获取时间 -->
<script>
	setInterval(
			"jnkc.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
			1000);
</script>
<script type="text/javascript" src="content/js/homepage/homepage.js"></script>
</html>
