<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/index/index_list_public_top.jsp"%>

<div class="col-xs-12 col-sm-9">
	<p class="pull-left visible-xs">
		<button type="button" class="btn btn-primary btn-xs"
			data-toggle="offcanvas">菜单</button>
	</p>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#">单图文</a></li>
		</ol>
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>标题</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="m">
						<tr>
							<td>${m.fdTitle}</td>
							<td>
								<a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictext/add?type=2"
								target="_blank">新增</a>
								<a href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictext/add?fdId=${m.id}&type=2"
								target="_blank">编辑</a> <a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictext/${m.id}?_method=DELETE"
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
