<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/index/index_list_back_top.jsp"%>

<div class="col-xs-12 col-sm-9">
	<p class="pull-left visible-xs">
		<button type="button" class="btn btn-primary btn-xs"
			data-toggle="offcanvas">菜单</button>
	</p>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#">用户信息</a></li>
		</ol>
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="m" varStatus="vs">
						<tr>
							<td>${vs.index + 1}</td>
							<td>${m.name}</td>
							<td>${m.email}</td>
							<td>
								<a href="<%=request.getContextPath()%>/wechat/user/create" target="_blank">新增</a>
								<a href="<%=request.getContextPath()%>/wechat/user/${m.id}/edit" target="_blank">编辑</a>
	                			<a href="<%=request.getContextPath()%>/wechat/user/${m.id}/status?_method=DELETE" target="_blank">禁用</a>
	                		</td>
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