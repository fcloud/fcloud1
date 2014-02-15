<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="index_list_top.jsp"%> 
<body>
	<%@ include file="/index_head_public_top.jsp"%> 
	<!-- /.navbar -->

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="well sidebar-nav">
					<ul class="nav">
						<li>公众号设置</li>
						<li class="active"><a href='<%=request.getContextPath()%>/weservice/we_public'>公众号</a></li>
						<li>规则设置</li>
						<li><a href='<%=request.getContextPath()%>/weservice/we_rule_reply_default/create'>默认规则</a></li>
						<li><a href='<%=request.getContextPath()%>/weservice/we_rule_reply/create'>自定义规则</a></li>
						<li>素材库</li>
						<li><a href='<%=request.getContextPath()%>/weservice/we_rule_reply_text'>文本</a></li>
						<li><a href='<%=request.getContextPath()%>/weservice/we_rule_reply_pictext'>单图文</a></li>
						<li><a href='<%=request.getContextPath()%>/weservice/we_rule_reply_pictexts'>多图文</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->