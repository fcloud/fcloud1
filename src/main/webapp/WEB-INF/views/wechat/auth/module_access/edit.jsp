<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://www.fcloud.com/taglib/fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>模块路径编辑</title>
    <link href="/public/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="/public/frame/datetimepicker/jquery.datetimepicker.css"
		rel="stylesheet">
	<style>
		body {
			padding-top: 30px;
		}
		
		.che-rad-inline {
			display: inline-block;
			padding-right: 15px;
			margin-bottom: 0;
			font-weight: normal;
			vertical-align: middle;
			cursor: pointer;
			line-height: 1.6;
		}
		</style>
</head>
<body>

<form:form action="/wechat/auth/module_access${fn:idp(model)}" method="POST" commandName="model">
	<!-- 操作头 -->
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">菜单</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"></a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><input type="submit" value="提交" class="btn btn-info navbar-btn"/>&nbsp;</li>
						<li><input type="button" value="关闭"
							class="btn btn-default navbar-btn"
							onclick="return _doClick('48257C690025B126.d76951e97f69f82d48257c69002a6cd9/$Body/0.1544', this, null)">
							&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- title -->
	  <div class="page-header">
	    <h1>模块路径编辑</h1>
	  </div>
	  <!-- tabs -->
	  <ul class="nav nav-pills nav-tabs" id="thisTab">
	    <li class="active"><a href="#base" data-toggle="tab">基本信息</a></li>
	  </ul>
	  
	  <!-- 信息 -->
	  <div class="tab-content">
	  	<!-- base -->
		  <div class="tab-pane panel panel-info active" id="base">
		    <div class="table-responsive">
		    	<table  class="table table-bordered" border="1">
		    		<tr>
			            <td>名称:</td>
			            <td><form:input path="name" cssClass="form-control" /><form:errors path="name" cssStyle="color: red"/></td>
			        </tr>
			        <tr>
			            <td>访问前缀:</td>
			            <td><form:input path="pathPrefix" cssClass="form-control" /><form:errors path="pathPrefix" cssStyle="color: red"/></td>
			        </tr>
				</table>
		    </div>
		  </div>
	  </div>
    <form:hidden path="id" />
</form:form>
</body>
</html>