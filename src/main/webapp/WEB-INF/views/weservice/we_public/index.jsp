<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/index/index_list_public_top.jsp"%>

<div class="col-xs-12 col-sm-9">
	<p class="pull-left visible-xs">
		<button type="button" class="btn btn-primary btn-xs"
			data-toggle="offcanvas">菜单</button>
	</p>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#">公众号</a></li>
		</ol>
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>公众号名称</th>
						<th>二维码</th>
						<th>微信号</th>
						<th>类型</th>
						<th>登录邮箱</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="m">
						<tr>
							<td>${m.fdName}</td>
							<td>${m.fdCode}</td>
							<td>${m.fdPublic}</td>
							<td>${m.fdPtype}</td>
							<td>${m.fdEmail}</td>
							<td><a
								href="<%=request.getContextPath()%>/weservice/we_public/create"
								target="_blank">新建</a> <a
								href="<%=request.getContextPath()%>/weservice/we_public/${m.id}/edit"
								target="_blank">编辑</a> <a
								href="<%=request.getContextPath()%>/weservice/we_public/${m.id}?_method=DELETE"
								target="_blank">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/row-->
</div>
<!--/span-->
<%@ include file="/WEB-INF/views/index/index_list_down.jsp"%>