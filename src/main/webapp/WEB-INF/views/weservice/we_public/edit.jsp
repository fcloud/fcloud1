<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://www.fcloud.com/taglib/fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公众号设置</title>

<link href="/public/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="/public/frame/datetimepicker/jquery.datetimepicker.css"
	rel="stylesheet">
<link rel="stylesheet" href="/public/js/city/css.css" />
<link rel="stylesheet" href="/public/js/city/form.css" />


<script src="/public/js/city/kit.js"></script>
<!--[if IE]>
    <script src="/public/js/city/ieFix.js"></script>
    <![endif]-->
<script src="/public/js/city/dom.js"></script>
<script src="/public/js/city/event.js"></script>
<script src="/public/js/city/TreeDict.js"></script>
<script src="/public/js/city/math.js"></script>
<script src="/public/js/city/form.js"></script>
<script src="/public/js/city/combobox.js"></script>
<script src="/public/js/city/suggestselect.js"></script>
<script src="/public/js/city/list.js"></script>
<!--json data-->
<script src="/public/js/city/json-data-city.js" charset="utf-8"></script>

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
	<form:form action="/weservice/we_public${fn:idp(model)}" method="POST"
		commandName="model">
		<form:hidden path="id" />
		<!-- 操作头 -->
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">菜单</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">自定义规则</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><input type="submit" value="提交"
							class="btn btn-info navbar-btn"
							onclick="setArea();"/>
							&nbsp;</li>
						<li><input type="button" value="关闭"
							class="btn btn-default navbar-btn"
							onclick="return _doClick('48257C690025B126.d76951e97f69f82d48257c69002a6cd9/$Body/0.1544', this, null)">
							&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 内容 -->
		<!-- title -->
	  <div class="page-header">
	    <h1>公众号配置</h1>
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
					<tr valign="top">
						<td width="150">公众号名称:</td>
						<td width="350">
							<form:input path="fdName" cssClass="form-control"/>
						</td>
						<td width="150">二维码:</td>
						<td width="350">
							<form:input path="fdCode" cssClass="form-control" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">微信号:</td>
						<td width="350">
							<form:input path="fdPublic" cssClass="form-control" />
						</td>
						<td width="150">类型:</td>
						<%--<td><form:input path="fdPtype"/></td>--%>
						<td width="350">
							<form:select path="fdPtype">
								<form:option value="1">订阅号</form:option>
							</form:select>
						</td>
					</tr>
					<tr valign="top">
						<td width="150">接口URL:</td>
						<td width="850" colspan="3">
							<form:input path="fdIntUrl" readonly="true" cssClass="form-control" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">Token:</td>
						<td width="850" colspan="3">
							<form:input path="fdIntToken" readonly="true" cssClass="form-control" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">AppId:</td>
						<td width="350">
							<form:input path="fdAppId" cssClass="form-control" />
						</td>
						<td width="150">AppSecret:</td>
						<td width="350">
							<form:input path="fdAppSecret" cssClass="form-control" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">地区:</td>
						<td width="850" colspan="3">
							<ul>
								<li><span> 省份 </span> <select
									class="kitjs-form-suggestselect"></select> <span> 市/县 </span> <select
									class="kitjs-form-suggestselect"></select> <span> 区 </span> <select
									class="kitjs-form-suggestselect"></select></li>
							</ul> <form:hidden path="fdArea" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">登录邮箱:</td>
						<td width="850" colspan="3">
							<form:input path="fdEmail" cssClass="form-control" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">头像:</td>
						<td width="850" colspan="3">
							<form:input path="fdPic" />
						</td>
					</tr>
					<tr valign="top">
						<td width="150">功能介绍:</td>
						<td width="850" colspan="3">
							<form:textarea path="fdInfo" cssClass="form-control" />
						</td>
					</tr>
				</table>
		    </div>
		  </div>
	  </div>
	</form:form>
<!-- 结束 -->
		<script>
			var a = $kit
					.els8cls($kit.ui.Form.ComboBox.Select.defaultConfig.transformCls), a1 = [];
			for ( var i = 0; i < a.length; i++) {
				a1.push(a[i])
			}
			a = a1;
			var b1 = new $kit.ui.Form.ComboBox.Select({
				el : a[0],
				data : (function() {
					var provTreeDict = new TreeDict();
					for ( var prov in chinaJSON) {
						provTreeDict.ad(prov, prov);
					}
					return provTreeDict;
				})()
			});
			b1.transform();
			b1.ev({
				ev : 'change',
				fn : function() {
					var prov = chinaJSON[b1.inputEl.value];
					if (prov == null) {
						return;
					}
					var cityTree = new TreeDict();
					for ( var city in prov) {
						cityTree.ad(city, city);
					}
					b2.inputEl.value = '';
					b2.formEl.value = '';
					b2.config.data = cityTree;
					b2.list.buildList(cityTree.search(''));
				}
			});
			var b2 = new $kit.ui.Form.ComboBox.Select({
				el : a[1],
				data : undefined
			});
			b2.transform();
			b2.ev({
				ev : 'change',
				fn : function() {
					var city = chinaJSON[b1.inputEl.value][b2.inputEl.value];
					if (city == null) {
						return;
					}
					var districtTree = new TreeDict();
					for ( var district in city) {
						districtTree.ad(district, city[district]);
					}
					b3.inputEl.value = '';
					b3.formEl.value = '';
					b3.config.data = districtTree;
					b3.list.buildList(districtTree.search(''));
				}
			});
			var b3 = new $kit.ui.Form.ComboBox.Select({
				el : a[2],
				data : undefined
			});
			b3.transform();
			var fdArea = document.getElementById("fdArea").value;
			if (fdArea != null && fdArea.length > 0) {
				var areas = fdArea.split(":");
				b1.inputEl.value = areas[0];
				b2.inputEl.value = areas[1];
				b3.inputEl.value = areas[2];
			}

			function setArea() {
				if (b1.inputEl.value != null && b1.inputEl.value.length > 0) {
					var area = b1.inputEl.value + ":" + b2.inputEl.value + ":"
							+ b3.inputEl.value;
					document.getElementById("fdArea").value = area;
				}
			}
		</script>
		<!-- JS库  -->
	<script src="public/js/jquery.min.js"></script>
	<script src="public/js/bootstrap/bootstrap.min.js"></script>
	<script src="public/frame/datetimepicker/jquery.datetimepicker.js"></script>
</body>
</html>