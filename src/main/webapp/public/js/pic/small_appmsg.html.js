/**
 * Created with IntelliJ IDEA.
 * User: zxpfss
 * Date: 13-12-14
 * Time: 下午11:31
 * To change this template use File | Settings | File Templates.
 */
define("public/js/pic/small_appmsg.html.js", [], function (e, t, n) {
    return '<div id="appmsgItem{id}" data-fileId="{file_id}" data-id="{id}" class="appmsg_item js_appmsg_item {if cover}has_thumb{/if}">\n    <img class="js_appmsg_thumb appmsg_thumb" src="{cover}">\n    <i class="appmsg_thumb default">缩略图</i>\n    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">{title || \'标题\'}</a></h4>\n    <div class="appmsg_edit_mask">\n        <a class="icon18_common edit_gray js_edit" data-id="{id}" onclick="return false;" href="javascript:void(0);">编辑</a>\n        <a class="icon18_common del_gray js_del" data-id="{id}" onclick="return false;" href="javascript:void(0);">删除</a>\n    </div>\n</div>\n';
});