<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/index/index_list_public_top.jsp"%>
<link href="/public/css/qqcss/base1a003d.css" type="text/css" rel="stylesheet">
<link href="/public/css/qqcss/lib19e425.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="/public/css/wecss/we_list.css" />
<div class="col-xs-12 col-sm-9">
	<p class="pull-left visible-xs">
		<button type="button" class="btn btn-primary btn-xs"
			data-toggle="offcanvas">菜单</button>
	</p>
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#">自定义规则</a></li>
		</ol>
		<div class="table-responsive">
			<table id="replay_table" class="table table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>启用</th>
						<th>关键字</th>
						<th>匹配方式</th>
						<th>回复类型</th>
						<th>回复内容</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="m">
						<tr>
							<td>${m.fdTitle}</td>
							<td>
								<a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictexts/add?type=2"
								target="_blank">新增</a>
								<a href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictexts/add?fdId=${m.id}&type=2"
								target="_blank">编辑</a> <a
								href="<%=request.getContextPath()%>/weservice/we_rule_reply_pictexts/${m.id}?_method=DELETE"
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
<script type="text/javascript">
	var rulereplyitems = ${rulereplyitem};
</script>
<script type="text/javascript" src="/public/js/list/replay.html.js"></script>
<%@ include file="/WEB-INF/views/index/index_list_down.jsp"%>