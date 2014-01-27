package com.fcloud.weservice.controller;

import com.fcloud.core.controller.ActionController;
import com.fcloud.core.model.Entity;
import com.fcloud.sys.att.model.SysAtt;
import com.fcloud.sys.att.repository.SysAttRepository;
import com.fcloud.util.StringUtil;
import com.fcloud.weservice.model.WeRuleReply;
import com.fcloud.weservice.model.WeRuleReplyPictext;
import com.fcloud.weservice.model.WeRuleReplyText;
import com.fcloud.weservice.repository.WeRuleReplyPictextRepository;
import com.fcloud.weservice.repository.WeRuleReplyRepository;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 单图文回复
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/weservice/we_rule_reply_pictext")
public class WeRuleReplyPictextController extends
		ActionController<WeRuleReplyPictext, WeRuleReplyPictextRepository> {

	@Resource
	private WeRuleReplyRepository weRuleReplyRepository;
	
	@Resource
	private SysAttRepository sysAttRepository;

	@RequestMapping("/add")
	@Transactional
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) {
		WeRuleReplyPictext model = null;
		String fdRuleId = request.getParameter("ruleId");
		try {
			if (!StringUtils.isEmpty(fdRuleId)) {
				WeRuleReply ruleReply = weRuleReplyRepository.findOne(fdRuleId);
				model = getRepository().findOne(ruleReply.getFdMaterial());
				if (model != null) {
					String pathUrl = request.getLocalAddr();
					if(request.getLocalPort() != 80){
						pathUrl = StringUtil.linkString(pathUrl, ":", String.valueOf(request.getLocalPort()));
					}
					pathUrl = StringUtil.linkString(pathUrl, "/", request.getContextPath());
					JSONObject infos = setPicJson(model,pathUrl);
					request.setAttribute("infos", infos);
				} else {
					model = createModel();
					request.setAttribute("infos", new JSONObject());
				}
				request.setAttribute("ruleReplyId", ruleReply.getId());
			} else {
				model = createModel();
				request.setAttribute("infos", new JSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return render("edit", model);
	}

	@RequestMapping("/save")
	@Transactional
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {
		String fdId = request.getParameter("id");
		String picitems = request.getParameter("picitems");
		String ruleId = request.getParameter("ruleReplyId");
		WeRuleReply ruleReply = weRuleReplyRepository.findOne(ruleId);
		if (ruleReply != null) {
			try {
				WeRuleReplyPictext ruleReplyText = getRepository()
						.findOne(fdId);
				if (ruleReplyText == null) {
					ruleReplyText = createModel();
					ruleReplyText.setId(fdId);
				}
				// 设置内容
				setInfo(picitems, ruleReplyText);
				getRepository().save(ruleReplyText);
				ruleReply.setFdMaterial(ruleReplyText.getId());
				weRuleReplyRepository.save(ruleReply);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return render("/public/success");
	}

	private void setInfo(String picitems, WeRuleReplyPictext ruleReplyText) {
		if (!StringUtils.isEmpty(picitems)) {
			JSONObject picitemsJson = JSONObject.fromObject(picitems);
			if (picitemsJson.containsKey("title0")) {
				ruleReplyText.setFdTitle(picitemsJson.getString("title0"));
			}
			if (picitemsJson.containsKey("author0")) {
				ruleReplyText.setFdTags(picitemsJson.getString("author0"));
			}
			if (picitemsJson.containsKey("digest0")) {
				ruleReplyText.setFdSummary(picitemsJson.getString("digest0"));
			}
			if (picitemsJson.containsKey("content0")) {
				ruleReplyText.setFdText(picitemsJson.getString("content0"));
			}
			if (picitemsJson.containsKey("fileid0")) {
				ruleReplyText.setAttId(picitemsJson.getString("fileid0"));
				SysAtt sysAtt = sysAttRepository.findOne(ruleReplyText.getAttId());
				if(sysAtt != null){
					ruleReplyText.setFdPic(sysAtt.getPicUrl());
				}else{
					ruleReplyText.setAttId("");
				}
			}
			if (picitemsJson.containsKey("sourceurl0")) {
				ruleReplyText
						.setFdUrl(picitemsJson.getString("sourceurl0"));
			}
			// TODO 缺少附件
		}
	}

	private JSONObject setPicJson(WeRuleReplyPictext model,String pathUrl) {
		JSONArray multiitem = new JSONArray();
		JSONObject multiobj = new JSONObject();
		multiobj.element("seq", 0);
		multiobj.element("title", model.getFdTitle());
		multiobj.element("digest", model.getFdSummary());
		multiobj.element("show_cover_pic", 1);
		multiobj.element("author", model.getFdTags());
		multiobj.element("content", model.getFdText());
		multiobj.element("file_id", model.getAttId());
		multiobj.element("cover", "http://"+pathUrl+"/upload"+model.getFdPic());
		multiobj.element("source_url", model.getFdUrl());
		multiitem.add(multiobj);
		multiobj.element("multi_item", multiitem);
		JSONArray item = new JSONArray();
		// multiobj.remove("show_cover_pic");
		item.add(multiobj);
		JSONObject infos = new JSONObject();
		infos.element("item", item);
		System.out.println(infos);
		return infos;
	}
}
