package com.fcloud.weservice.rule.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.fcloud.util.IdGenerator;
import com.fcloud.util.StringUtil;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReply;
import com.fcloud.weservice.rule.model.WeRuleReplyPictexts;
import com.fcloud.weservice.rule.model.WeRuleReplyPictextson;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextsRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextsonRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyRepository;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * 多图文回复
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/weservice/we_rule_reply_pictexts")
public class WeRuleReplyPictextsController extends
		ActionController<WeRuleReplyPictexts, WeRuleReplyPictextsRepository> {
	@Value("#{fcloud['fcloudhost']}")
    private String fcloudhost;
	@Resource
	private WeRuleReplyRepository weRuleReplyRepository;

	@Resource
	private WeRuleReplyPictextsonRepository weRuleReplyPictextsonRepository;

	@Resource
	private SysAttRepository sysAttRepository;

	@RequestMapping("/add")
	@Transactional
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) {
		WeRuleReplyPictexts model = null;
		String type = request.getParameter("type");
		JSONObject infos = new JSONObject();
		try {
			String pathUrl = fcloudhost;
//    		if(request.getLocalPort() != 80){
//    			pathUrl = StringUtil.linkString(pathUrl, ":", String.valueOf(request.getLocalPort()));
//    		}
    		pathUrl = StringUtil.linkString(pathUrl, "/", request.getContextPath());
    		pathUrl = pathUrl+"/sys/att/showPic?";
			if("1".equals(type)){
				String fdRuleId = request.getParameter("ruleId");
				if (!StringUtils.isEmpty(fdRuleId)) {
					WeRuleReply ruleReply = weRuleReplyRepository.findOne(fdRuleId);
					model = getRepository().findOne(ruleReply.getFdMaterial());
					if (model != null) {
						// 获取子图文
						ForeignCollection<WeRuleReplyPictextson> pictextsons = model
								.getWeRuleReplyPictextsons();
						if (pictextsons != null && !pictextsons.isEmpty()) {
							
							infos = setPicJson(pictextsons, pathUrl);
						}
					} else {
						model = createModel();
					}
					request.setAttribute("ruleReplyId", ruleReply.getId());
					request.setAttribute("infos", infos);

				} else {
					model = createModel();
					request.setAttribute("infos", infos);
				}
			}else{
				String fdId = request.getParameter("fdId");
				if (!StringUtils.isEmpty(fdId)) {
					model = getRepository().findOne(fdId);
					if (model != null) {
						// 获取子图文
						ForeignCollection<WeRuleReplyPictextson> pictextsons = model
								.getWeRuleReplyPictextsons();
						if (pictextsons != null && !pictextsons.isEmpty()) {
							
							infos = setPicJson(pictextsons, pathUrl);
						}
					} else {
						model = createModel();
					}
					request.setAttribute("infos", infos);

				} else {
					model = createModel();
					request.setAttribute("infos", infos);
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
		WePublic wePublic = (WePublic) request.getSession().getAttribute(
				"wePublic");
		try {
			WeRuleReplyPictexts ruleReplyText = getRepository().findOne(
					fdId);
			if (ruleReplyText == null) {
				ruleReplyText = createModel();
				ruleReplyText.setId(fdId);
			}
			ruleReplyText.setFdWepublic(wePublic);
			saveFirstPic(picitems, ruleReplyText);
			getRepository().save(ruleReplyText);
			// 设置子图文内容
			saveChildPic(picitems, ruleReplyText);
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

	@RequestMapping("/getPictextsList")
	@Transactional
	public ModelAndView getPictextsList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			PrintWriter out = null;
			try {
				WePublic wePublic = (WePublic) request.getSession()
						.getAttribute("wePublic");
				List<WeRuleReplyPictexts> list = getRepository().getDao()
						.queryBuilder().query();
				String opinions = null;
				for (WeRuleReplyPictexts weRuleReplyPictexts : list) {
					String opinion = "<option value=\""
							+ weRuleReplyPictexts.getId() + "\">"
							+ weRuleReplyPictexts.getFdTitle() + "</option>";
					opinions = StringUtil
							.linkString(opinions, "<br/>", opinion);
				}
				out = response.getWriter();
				out.print(opinions);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	private void saveChildPic(String picitems, WeRuleReplyPictexts ruleReplyText)
			throws SQLException {
		//获取所有附件信息
		if (!StringUtils.isEmpty(picitems)) {
			JSONObject picitemsJson = JSONObject.fromObject(picitems);
			List<String> attIds = new ArrayList<String>();
			for (int i = 0; i < picitemsJson.getInt("count"); i++) {
				if (picitemsJson.containsKey("fileid" + i)) {
					attIds.add(picitemsJson.getString("fileid" + i));
				}
			}
			
			// 删除当前所有信息关联信息
			List<WeRuleReplyPictextson> pictextsonList = weRuleReplyPictextsonRepository
					.getDao().queryBuilder().where()
					.eq("fd_werulereply", ruleReplyText.getId()).query();
			weRuleReplyPictextsonRepository.delete(pictextsonList,attIds);
			if (!StringUtils.isEmpty(picitems)) {
				int count = picitemsJson.getInt("count");
				for (int i = 0; i < count; i++) {
					WeRuleReplyPictextson pictextson = new WeRuleReplyPictextson();
					pictextson.setId(IdGenerator.newId());
					pictextson.setFdWerulereply(ruleReplyText);
					pictextson.setFdOrder(i);
					if (picitemsJson.containsKey("title" + i)) {
						pictextson.setFdTitle(picitemsJson.getString("title" + i));
					}
					if (picitemsJson.containsKey("content" + i)) {
						pictextson.setFdText(picitemsJson.getString("content" + i));
					}
					if (picitemsJson.containsKey("author" + i)) {
						pictextson.setFdTags(picitemsJson.getString("author" + i));
					}
					if (picitemsJson.containsKey("sourceurl" + i)) {
						pictextson
								.setFdUrl(picitemsJson.getString("sourceurl" + i));
					}
					if (picitemsJson.containsKey("fileid" + i)) {
						pictextson.setAttId(picitemsJson.getString("fileid" + i));
						SysAtt sysAtt = sysAttRepository.findOne(pictextson
								.getAttId());
						if (sysAtt != null) {
							pictextson.setFdPic(sysAtt.getPicUrl());
						} else {
							pictextson.setAttId("");
						}

					}
					// TODO 缺少附件
					weRuleReplyPictextsonRepository.save(pictextson);
				}
			}
		}
		
	}

	@Transactional
	private void saveFirstPic(String picitems, WeRuleReplyPictexts ruleReplyText)
			throws SQLException {
		if (!StringUtils.isEmpty(picitems)) {
			JSONObject picitemsJson = JSONObject.fromObject(picitems);
			if (picitemsJson.containsKey("title" + 0)) {
				ruleReplyText.setFdTitle(picitemsJson.getString("title" + 0));
			}
			if (picitemsJson.containsKey("content" + 0)) {
				ruleReplyText.setFdText(picitemsJson.getString("content" + 0));
			}
			if (picitemsJson.containsKey("author" + 0)) {
				ruleReplyText.setFdTags(picitemsJson.getString("author" + 0));
			}
			if (picitemsJson.containsKey("sourceurl" + 0)) {
				ruleReplyText.setFdUrl(picitemsJson.getString("sourceurl" + 0));
			}
		}
	}

	private JSONObject setPicJson(
			ForeignCollection<WeRuleReplyPictextson> pictextsons, String path) {
		JSONArray multiitem = new JSONArray();
		JSONObject firstobj = new JSONObject();
		int i = 0;
		for (WeRuleReplyPictextson pictextson : pictextsons) {
			JSONObject multiobj = new JSONObject();
			multiobj.element("seq", i);
			multiobj.element("title", pictextson.getFdTitle());
			multiobj.element("show_cover_pic", 1);
			multiobj.element("content", pictextson.getFdText());
			multiobj.element("author", pictextson.getFdTags());
			if (!StringUtils.isEmpty(pictextson.getAttId())) {
				multiobj.element("file_id", pictextson.getAttId());
				multiobj.element("cover", path +"file_id="+ pictextson.getAttId());
			}
			multiobj.element("source_url", pictextson.getFdUrl());
			multiitem.add(multiobj);
			if (i == 0) {
				firstobj = JSONObject.fromObject(firstobj);
			}
		}

		firstobj.element("multi_item", multiitem);
		JSONArray item = new JSONArray();
		// multiobj.remove("show_cover_pic");
		item.add(firstobj);
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
		Page<WeRuleReplyPictexts> models = null;
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
		WeRuleReplyPictexts weRuleReplyPictexts = getRepository().findOne(id);
		getRepository().delete(weRuleReplyPictexts);
		return super.delete(id, request);
	}
}
