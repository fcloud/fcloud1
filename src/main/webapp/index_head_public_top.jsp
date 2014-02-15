<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="navbar navbar-fixed-top navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">fcloud</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul id="navs" class="nav navbar-nav">
					<li class="active"><a href="/index/thePublicManage">公众号管理</a></li>
					<li><a href="/index/backgroundManage">后台管理</a></li>
					
				</ul>
			    <div class="navbar-form navbar-right">
					<div class="btn-group">
						<button type="button" id="userno" class="btn btn-info btn-sm">${SessionUser.username}</button>
						<button type="button" class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown">
					    <span class="caret"></span>
					    <span class="sr-only">菜单</span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">编辑</a></li>
							<li><a href="#">注销</a></li>
						</ul>
					</div>
					，你好，
					当前公众号：
					<div id="div_public" class="btn-group">
						<button type="button" id="wepublicno" class="btn btn-info btn-sm"></button>
						<button type="button" class="btn btn-info btn-sm dropdown-toggle" data-toggle="dropdown">
					    <span class="caret"></span>
					    <span class="sr-only">菜单</span>
						</button>
						<ul id="wepublicnos" class="dropdown-menu" aria-labelledby="aa">
							
						</ul>
					</div>
			    </div>

			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</div>
</body>
<script src="/public/js/jquery.min.js"></script>
<script>
$(function(){
	var curPublicId = '${wePublic.id}';
	var curPublicName = '${wePublic.fdName}';
	if(curPublicId){
		$("#wepublicno").val(curPublicId);
		$("#wepublicno").text(curPublicName);
    }
    $.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/weservice/we_public/getWePublic",
        dataType: "json",
        success: function(data) {
            $.each(data,
                    function(i, item) {
	                	if(curPublicId){
		                	$("#wepublicnos").append("<li value=" + item.publicId + " ><a href='#'>" + item.publicName + "</a></li>");
	                    }else{
                			if(i == 0){
                				$("#wepublicno").val(item.publicId);
                				$("#wepublicno").text(item.publicName);
                			}else{
                                $("#wepublicnos").append("<li value=" + item.publicId + " ><a href='#'>" + item.publicName + "</a></li>");
                			}
	                    }
            });
            initLiEvent();
            $("#wepublicnos").append("<li class='divider'></li>");
            $("#wepublicnos").append("<li><a href='#'>编辑</a></li>");
            $("#wepublicnos").append("<li><a href='#'>新增</a></li>");
            if(data.length>0){
                //为第一个option赋值&input
                //$("#wepublic").val(data[0].publicId);
                //$("#_wpublicId option:first").attr("selected",true);
            	if(!curPublicId){
            		setWepublicToSession();
                }
                
            }
        }
    })
});

function initLiEvent(){
	var options=$("#div_public").find("ul.dropdown-menu > li");
	options.on("click",function(){
        var opt=$(this);
        var text=opt.find("a").text();
        var val=opt.attr("value");
        $("#wepublicno").val(val);
        $("#wepublicno").text(text);
        setWepublicToSession();
    });
}


function setWepublicToSession(){
    var wepublicid = $("#wepublicno").val();
    $.ajax({
        type: "GET",
        url: "<%=request.getContextPath()%>/weservice/we_public/setPublicInSession",
        data:{wepublicid:wepublicid},
        dataType: "json",
        timeStamp: new Date().getTime(),
        cache: false,
        success: function(data) {
            showResult(data.result);
        }
    });
}
function showResult(responseText){
    if(responseText!='success'){
        alert("当前公众账号保存session失败.");
    }
    parent.location.reload();
}
</script>
</html>