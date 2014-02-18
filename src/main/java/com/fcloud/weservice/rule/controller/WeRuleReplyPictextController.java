package com.fcloud.weservice.rule.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fcloud.core.controller.ActionController;
import com.fcloud.core.model.Page;
import com.fcloud.core.model.Pageable;
import com.fcloud.sys.att.model.SysAtt;
import com.fcloud.sys.att.repository.SysAttRepository;
import com.fcloud.util.StringUtil;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReply;
import com.fcloud.weservice.rule.model.WeRuleReplyPictext;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyRepository;
import com.j256.ormlite.stmt.QueryBuilder;

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
	@Value("#{fcloud['fcloudhost']}")
    private String fcloudhost;
	@Resource
	private WeRuleReplyRepository weRuleReplyRepository;

	@Resource
	private SysAttRepository sysAttRepository;

	@RequestMapping("/add")
	@Transactional
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) {
		WeRuleReplyPictext model = null;
		String type = request.getParameter("type");
		try {
			String pathUrl = fcloudhost;
    		if(request.getLocalPort() != 80){
    			pathUrl = StringUtil.linkString(pathUrl, ":", String.valueOf(request.getLocalPort()));
    		}
    		pathUrl = StringUtil.linkString(pathUrl, "/", request.getContextPath());
    		pathUrl = pathUrl+"/sys/att/showPic?";
			if ("1".equals(type)) {
				String fdRuleId = request.getParameter("ruleId");
				if (!StringUtils.isEmpty(fdRuleId)) {
					WeRuleReply ruleReply = weRuleReplyRepository
							.findOne(fdRuleId);
					model = getRepository().findOne(ruleReply.getFdMaterial());
					if (model != null) {
						JSONObject infos = setPicJson(model, pathUrl);
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
			} else {
				String fdId = request.getParameter("fdId");
				if (!StringUtils.isEmpty(fdId)) {
					model = getRepository().findOne(fdId);
					if (model != null) {
						JSONObject infos = setPicJson(model, pathUrl);
						request.setAttribute("infos", infos);
					} else {
						model = createModel();
						request.setAttribute("infos", new JSONObject());
					}
				} else {
					model = createModel();
					request.setAttribute("infos", new JSONObject());
				}
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
		System.out.println(picitems);
		WePublic wePublic = (WePublic) request.getSession().getAttribute(
				"wePublic");
		try {
			WeRuleReplyPictext ruleReplyText = getRepository().findOne(fdId);
			if (ruleReplyText == null) {
				ruleReplyText = createModel();
				ruleReplyText.setId(fdId);
			}
			ruleReplyText.setFdWepublic(wePublic);
			// 设置内容
			setInfo(picitems, ruleReplyText);
			getRepository().save(ruleReplyText);
			String ruleId = request.getParameter("ruleReplyId");
			WeRuleReply ruleReply = weRuleReplyRepository.findOne(ruleId);
			if (ruleReply != null) {
				ruleReply.setFdMaterial(ruleReplyText.getId());
				weRuleReplyRepository.save(ruleReply);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
				SysAtt sysAtt = sysAttRepository.findOne(ruleReplyText
						.getAttId());
				if (sysAtt != null) {
					ruleReplyText.setFdPic(sysAtt.getPicUrl());
				} else {
					ruleReplyText.setAttId("");
				}
			}
			if (picitemsJson.containsKey("sourceurl0")) {
				ruleReplyText.setFdUrl(picitemsJson.getString("sourceurl0"));
			}
			// TODO 缺少附件
		}
	}

	@RequestMapping("/getPictextList")
	@Transactional
	public ModelAndView getPictextList(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			WePublic wePublic = (WePublic) request.getSession().getAttribute(
					"wePublic");
			List<WeRuleReplyPictext> list = getRepository().getDao()
					.queryBuilder().where().eq("fd_wepublic", wePublic.getId())
					.query();
			String opinions = null;
			for (WeRuleReplyPictext weRuleReplyPictext : list) {
				String opinion = "<option value=\""
						+ weRuleReplyPictext.getId() + "\">"
						+ weRuleReplyPictext.getFdTitle() + "</option>";
				opinions = StringUtil.linkString(opinions, "<br/>", opinion);
			}
			out = response.getWriter();
			out.print(opinions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	private JSONObject setPicJson(WeRuleReplyPictext model, String pathUrl) {
		JSONArray multiitem = new JSONArray();
		JSONObject multiobj = new JSONObject();
		multiobj.element("seq", 0);
		multiobj.element("title", model.getFdTitle());
		multiobj.element("digest", model.getFdSummary());
		multiobj.element("show_cover_pic", 1);
		multiobj.element("author", model.getFdTags());
		multiobj.element("content", model.getFdText());
		if (StringUtil.isNotNull(model.getAttId())
				&& StringUtil.isNotNull(model.getFdPic())) {
			multiobj.element("file_id", model.getAttId());
			multiobj.element("cover",
					pathUrl +"file_id="+ model.getAttId());
		}

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

	@Override
	public ModelAndView index(Pageable page, WebRequest request) {
		WePublic wePublic = (WePublic) request.getAttribute("wePublic",
				RequestAttributes.SCOPE_SESSION);
		QueryBuilder queryBuilder = getRepository().getDao().queryBuilder();
		Page<WeRuleReplyPictext> models = null;
		try {
			queryBuilder.where().eq("fd_wepublic", wePublic.getId());
			models = getRepository().readPageByBuilder(queryBuilder, page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return render("index", models);
	}

	@Override
	public ModelAndView delete(String id, WebRequest request) {
		WeRuleReplyPictext weRuleReplyPictext = getRepository().findOne(id);
		getRepository().delete(weRuleReplyPictext);
		return render("/public/success");
	}
	
}
