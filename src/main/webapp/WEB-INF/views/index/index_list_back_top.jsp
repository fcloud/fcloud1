<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="index_list_top.jsp"%> 
<body>
	<%@ include file="/index_head_back_top.jsp"%> 
	<!-- /.navbar -->

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="well sidebar-nav">
					<ul class="nav">
						<li>用户设置</li>
						<li class="active"><a href='<%=request.getContextPath()%>/wechat/user'>用户列表</a></li>
						<li><a href="<%=request.getContextPath()%>/wechat/auth/module_access">模块路径</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->