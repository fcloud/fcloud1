/** * Created with IntelliJ IDEA. * User: zxpfss * Date: 13-12-25 * Time: 上午12:04 * To change this template use File | Settings | File Templates. */var list_str = '<tr><td  class = "replay_td" ><label id="order" class="frm_label"></label><input type="hidden" id="fd_id"/></td ><td class="replay_td"><input id="isOpen"  value="" type="checkbox"/></td><td class = "replay_td" ><input type="text" class="replay_input_keyword" id="fd_key"/></td ><td class="replay_td"><label><select id="fd_match_type"><option value="1">模糊匹配</option><option value="2">完全匹配</option></select></label></td><td class= "replay_td" ><select id="fd_reply_type"><option value="1">文本</option><option value="2">单图文</option><option value="3">多图文</option></select></td ><td class="replay_td"><a class="icon18_common edit_blue js_edit"  onclick="return false;" href="javascript:void(0);">添加</a>\n</td><td class= "replay_td" ><div class="appmsg_edit_mask">\n        <a class="icon18_common add_blue js_add"  onclick="return false;" href="javascript:void(0);">添加</a>\n        <a class="icon18_common del_gray js_del"  onclick="return false;" href="javascript:void(0);">删除</a>\n    </div></td></tr> ';$(function(){    if (rulereplyitems.length > 0) {        initData(rulereplyitems);    } else {        addData();    }})//新建数据function addData() {//        var tr_length = 0;//        if ($("#replay_table tr").length == 1) {//            tr_length = $("#replay_table tr").length;//        } else {//            var lastTrObj = $("tr:last", $("#replay_table"));//            var lastIndex = $(lastTrObj).attr("id").split("_")[1];//            tr_length = parseInt(lastIndex) + 1;//        }    //var label_index = $("#replay_table tr").length;    //替换后数据    $(list_str).appendTo($("#replay_table"));    var lastTrObj = $("tr:last", $("#replay_table"));    refreshIndex();    $(".js_add", $(lastTrObj)).attr("onClick", "addData();");    $(".js_del", $(lastTrObj)).attr("onClick", "delData($(this));");    $(".js_edit", $(lastTrObj)).attr("onClick", "saveRuleAndSetInfo($(this));");//    $(lastTrObj).on("click", ".js_add", function () {//        addData();//    });//    $(lastTrObj).on("click", ".js_del", function () {//        delData($(lastTrObj));//    });//    $(lastTrObj).on("click", ".js_edit", function () {//        saveRuleAndSetInfo($(lastTrObj));//    })};//初始化数据function initData(rulereplyitems) {    for (var i = 0; i < rulereplyitems.length; i++) {        var rulereply = rulereplyitems[i];        //替换后数据        $(list_str).appendTo($("#replay_table"));        //$(".selector").find("option[text='pxx']").attr("selected",true);        //var lastTrObj = $("#replay_table tr:last");        var lastTrObj = $("#replay_table tr").eq(i + 1);        lastTrObj = setInfo(lastTrObj, rulereply);        refreshIndex();        $(".js_add", $(lastTrObj)).attr("onClick", "addData();");        $(".js_del", $(lastTrObj)).attr("onClick", "delData($(this));");        $(".js_edit", $(lastTrObj)).attr("onClick", "saveRuleAndSetInfo($(this));");//            $(".js_add",$(lastTrObj)).on("click",function () {//                addData();//            });//            $(".js_del",$(lastTrObj)).on("click",function () {//                delData($(lastTrObj));//            });//            $(".js_edit",$(lastTrObj)).on("click", function () {//                saveRuleAndSetInfo($(lastTrObj));//            })    }};function setInfo(curTrObj, curTrInfo) {    $("#fd_id", $(curTrObj)).val(curTrInfo.id);    $("#isOpen", $(curTrObj)).val(curTrInfo.isuse);    if(curTrInfo.isuse == 1){        $("#isOpen", $(curTrObj)).attr("checked",true);    }    $("#fd_key", $(curTrObj)).val(curTrInfo.key);    $("#fd_match_type", $(curTrObj)).val(curTrInfo.matchType);    $("#fd_reply_type", $(curTrObj)).prop("disabled", true).val(curTrInfo.replyType);    return $(curTrObj);};function delData(obj) {    var tr_length = $("#replay_table tr").length;    if (tr_length == 2) {        alert("无法删除，至少保留一条信息");        return;    }    //获取当前a标签所在tr对象    var trObj = $(obj).parent().parent().parent();    var fd_id = $("#fd_id", $(trObj)).attr("value");    delRule(fd_id);    $(trObj).remove();    refreshIndex();};function refreshIndex() {    var i = 0;    $("tr", $("#replay_table")).each(function () {        {            if (i != 0) {                $("#order", $(this)).html(i);            }            i = i + 1;        }    })};function buildRegex(text) {    return new RegExp(text, "g");};function onReplace(list_str, textReplace, text) {    var regex = buildRegex(text);    return list_str.replace(regex, textReplace);};//保存自定义规则并设置信息function saveRuleAndSetInfo(obj) {    //获取当前a标签所在tr对象    var trObj = $(obj).parent().parent();    var fd_id = $("#fd_id", $(trObj)).attr("value");    var isOpen = 0;    if($("#isOpen", $(trObj)).is(":checked")==true){        isOpen = 1;    }    var fd_key = $("#fd_key", $(trObj)).val();    var fd_match_type = $("#fd_match_type", $(trObj)).val();    var fd_reply_type = $("#fd_reply_type", $(trObj)).val();    var data = {fd_id: fd_id, isOpen: isOpen, fd_key: fd_key, fd_match_type: fd_match_type, fd_reply_type: fd_reply_type};    if (fd_id != null && fd_id.length > 0) {        editRule(data, trObj);    } else {        saveRule(data, trObj);    }};function saveRule(data, obj) {    $.ajax({        type: "POST",        url: "/weservice/we_rule_reply/saveByAjax",        data: data,        dataType: "json",        success: function (data) {            $("#fd_id", $(obj)).attr("value", data.fdId);            $("#fd_reply_type", $(obj)).prop("disabled", true);            var fd_reply_type = $("#fd_reply_type", $(obj)).val();            var url = "";            if (fd_reply_type == "1") {                url = "/weservice/we_rule_reply_text/create?ruleId=" + data.fdId;            } else if (fd_reply_type == "2") {                url = "/weservice/we_rule_reply_pictext/add?ruleId=" + data.fdId;            } else {                url = "/weservice/we_rule_reply_pictexts/add?ruleId=" + data.fdId;            }            window.open(url);        },        error: function (data) {        }})};function delRule(fdId) {    $.ajax({        type: "POST",        url: "/weservice/we_rule_reply/deleteByAjax",        data: {fdId:fdId},        dataType: "json",        success: function (data) {        },        error: function (data) {        }})};function editRule(data, obj) {    $.ajax({        type: "POST",        url: "/weservice/we_rule_reply/editByAjax",        data: data,        dataType: "json",        success: function (data) {            $("#fd_id", $(obj)).html(data.fdId);            var fd_reply_type = $("#fd_reply_type", $(obj)).val();            var url = "";            if (fd_reply_type == "1") {                url = "/weservice/we_rule_reply_text/create?ruleId=" + data.fdId;            } else if (fd_reply_type == "2") {                url = "/weservice/we_rule_reply_pictext/add?ruleId=" + data.fdId;            } else {                url = "/weservice/we_rule_reply_pictexts/add?ruleId=" + data.fdId;            }            window.open(url);        },        error: function (data) {        }})};//--------------------------------------------------------------------------------------------------//define("/public/js/list/replay.html.js", ["/public/js/list/replay.list.js", "/public/js/list/replay.opt.js"], function (e, t, n) {//    e("/public/css/qqcss/base1a003d.css");//    var list_str = e("/public/js/list/replay.list.js"), opt = e("/public/js/list/replay.opt.js");//    if(rulereplyitems.length > 0){//        opt.initData(rulereplyitems);//    }else{//        opt.addData();//    }//    //$(list_str).appendTo($("#replay_table"));//    //$("#replay_table").trigger("create");//});//define("/public/js/list/replay.list.js", [], function (e, t, n) {//    return '<tr><td  class = "replay_td" ><label id="order" class="frm_label"></label><input type="hidden" id="fd_id"/></td ><td class="replay_td"><input id="isOpen"  value="" type="checkbox"/></td><td class = "replay_td" ><input type="text" class="replay_input_keyword" id="fd_key"/></td ><td class="replay_td"><label><select id="fd_match_type"><option value="1">模糊匹配</option><option value="2">完全匹配</option></select></label></td><td class= "replay_td" ><select id="fd_reply_type"><option value="1">文本</option><option value="2">单图文</option><option value="3">多图文</option></select></td ><td class="replay_td"><a class="icon18_common edit_blue js_edit"  onclick="return false;" href="javascript:void(0);">添加</a>\n</td><td class= "replay_td" ><div class="appmsg_edit_mask">\n        <a class="icon18_common add_blue js_add"  onclick="return false;" href="javascript:void(0);">添加</a>\n        <a class="icon18_common del_gray js_del"  onclick="return false;" href="javascript:void(0);">删除</a>\n    </div></td></tr> ';//})//define("/public/js/list/replay.opt.js", ["/public/js/list/replay.list.js"], function (e, t, n) {//    var regx = "{replay_id}";//    var list_str = e("/public/js/list/replay.list.js");////    //新建数据//    function addData() {////        var tr_length = 0;////        if ($("#replay_table tr").length == 1) {////            tr_length = $("#replay_table tr").length;////        } else {////            var lastTrObj = $("tr:last", $("#replay_table"));////            var lastIndex = $(lastTrObj).attr("id").split("_")[1];////            tr_length = parseInt(lastIndex) + 1;////        }//        //var label_index = $("#replay_table tr").length;//        //替换后数据//        $(list_str).appendTo($("#replay_table"));//        var lastTrObj = $("tr:last", $("#replay_table"));//        refreshIndex();//        $(lastTrObj).on("click", ".js_add", function () {//            addData();//        });//        $(lastTrObj).on("click", ".js_del", function () {//            delData($(lastTrObj));//        });//        $(lastTrObj).on("click", ".js_edit", function () {//            saveRuleAndSetInfo($(lastTrObj));//        })//    }//    //初始化数据//    function initData(rulereplyitems) {//        for(var i= 0;i<rulereplyitems.length;i++){//            var rulereply = rulereplyitems[i];//            //替换后数据//            $(list_str).appendTo($("#replay_table"));//            //$(".selector").find("option[text='pxx']").attr("selected",true);//            //var lastTrObj = $("#replay_table tr:last");//            var lastTrObj = $("#replay_table tr").eq(i+1);//            lastTrObj = setInfo(lastTrObj,rulereply);//            refreshIndex();//            $(".js_add",$(lastTrObj)).attr("onClick","addData();");//            $(".js_del",$(lastTrObj)).attr("onClick","delData(this);");//            $(".js_edit",$(lastTrObj)).attr("onClick","saveRuleAndSetInfo(this);");////            $(".js_add",$(lastTrObj)).on("click",function () {////                addData();////            });////            $(".js_del",$(lastTrObj)).on("click",function () {////                delData($(lastTrObj));////            });////            $(".js_edit",$(lastTrObj)).on("click", function () {////                saveRuleAndSetInfo($(lastTrObj));////            })//        }//    }////    function setInfo(curTrObj,curTrInfo){//        $("#fd_id", $(curTrObj)).val(curTrInfo.id);//        $("#isOpen", $(curTrObj)).val(curTrInfo.isuse);//        $("#fd_key", $(curTrObj)).val(curTrInfo.key);//        $("#fd_match_type", $(curTrObj)).val(curTrInfo.matchType);//        $("#fd_reply_type", $(curTrObj)).val(curTrInfo.replyType);//        return $(curTrObj);//    }////    function delData(obj) {//        var tr_length = $("#replay_table tr").length;//        if (tr_length == 2) {//            alert("无法删除，至少保留一条信息");//            return;//        }//        var index = $("#order", obj).text();//        obj.remove();//        if (!(tr_length - 1 == index)) {//            refreshIndex(index);//        }//    }////    function refreshIndex() {//        var i=0;//        $("tr", $("#replay_table")).each(function(){//            {//                if(i != 0){//                    $("#order", $(this)).html(i);//                }//                i = i+1;//            }//        })//    }////    function buildRegex(text) {//        return new RegExp(text, "g");//    }////    function onReplace(list_str, textReplace, text) {//        var regex = buildRegex(text);//        return list_str.replace(regex, textReplace);//    }////    //保存自定义规则并设置信息//    function saveRuleAndSetInfo(obj) {//        var fd_id = $("#fd_id", $(obj)).attr("value");//        var isOpen = $("#isOpen", $(obj)).val();//        var fd_key = $("#fd_key", $(obj)).val();//        var fd_match_type = $("#fd_match_type", $(obj)).val();//        var fd_reply_type = $("#fd_reply_type", $(obj)).val();//        var data = {fd_id: fd_id, isOpen: isOpen, fd_key: fd_key, fd_match_type: fd_match_type, fd_reply_type: fd_reply_type};//        if (fd_id != null && fd_id.length > 0) {//            editRule(data, obj);//        } else {//            saveRule(data, obj);//        }//    }////    function saveRule(data, obj) {//        $.ajax({//            type: "POST",//            url: "/weservice/we_rule_reply/saveByAjax",//            data: data,//            dataType: "json",//            success: function (data) {//                $("#fd_id",$(obj)).attr("value",data.fdId);//                var fd_reply_type = $("#fd_reply_type", $(obj)).val();//                var url = "";//                if(fd_reply_type == "1"){//                    url = "/weservice/we_rule_reply_text/create?ruleId="+data.fdId;//                }else if(fd_reply_type == "2"){//                    url = "/weservice/we_rule_reply_pictext/create?ruleId="+data.fdId;//                }else{//                    url = "/weservice/we_rule_reply_pictexts/create?ruleId="+data.fdId;//                }//                window.open(url);//            },//            error: function (data) {////            }})//    }////    function editRule(data, obj) {//        $.ajax({//            type: "POST",//            url: "/weservice/we_rule_reply/editByAjax",//            data: data,//            dataType: "json",//            success: function (data) {//                $("#fd_id",$(obj)).html(data.fdId);//                var fd_reply_type = $("#fd_reply_type", $(obj)).val();//                var url = "";//                if(fd_reply_type == "1"){//                    url = "/weservice/we_rule_reply_text/create?ruleId="+data.fdId;//                }else if(fd_reply_type == "2"){//                    url = "/weservice/we_rule_reply_pictext/create?ruleId="+data.fdId;//                }else{//                    url = "/weservice/we_rule_reply_pictexts/create?ruleId="+data.fdId;//                }//                window.open(url);//            },//            error: function (data) {////            }})//    }////    t . addData = addData;//    t.initData = initData;//    t.delData = delData;//    t.saveRuleAndSetInfo = saveRuleAndSetInfo;//})