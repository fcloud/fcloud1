<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/index/index_list_public_top.jsp"%>

<div class="col-xs-12 col-sm-9">
	<p class="pull-left visible-xs">
		<button type="button" class="btn btn-primary btn-xs"
			data-toggle="offcanvas">菜单</button>
	</p>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#">文本</a></li>
		</ol>
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>文本内容</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="m">
						<tr>
							<td>${m.fdText}</td>

							<td><a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_text/${m.id}/edit"
								target="_blank">编辑</a> <a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_text/${m.id}?_method=DELETE"
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