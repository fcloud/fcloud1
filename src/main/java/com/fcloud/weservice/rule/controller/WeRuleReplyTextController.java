package com.fcloud.weservice.rule.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReply;
import com.fcloud.weservice.rule.model.WeRuleReplyText;
import com.fcloud.weservice.rule.repository.WeRuleReplyRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyTextRepository;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * 文本回复
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/weservice/we_rule_reply_text")
public class WeRuleReplyTextController extends
		ActionController<WeRuleReplyText, WeRuleReplyTextRepository> {

	@Resource
	private WeRuleReplyRepository weRuleReplyRepository;

	@Override
	public ModelAndView create(WebRequest request) {
		WeRuleReplyText model = null;
		try {
			String fdRuleId = request.getParameter("ruleId");
			if (!StringUtils.isEmpty(fdRuleId)) {
				WeRuleReply ruleReply = weRuleReplyRepository.findOne(fdRuleId);
				model = getRepository().findOne(ruleReply.getFdMaterial());
				if (model == null) {
					model = createModel();
				}
				request.setAttribute("ruleReplyId", ruleReply.getId(),
						RequestAttributes.SCOPE_REQUEST);

			} else {
				model = createModel();
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
		WeRuleReplyText model = getRepository().findOne(fdId);
		if (model == null) {
			model = createModel();
			model.setId(fdId);
		}
		model.setFdText(request.getParameter("fdText"));
		getRepository().save(model);
		String fdRuleId = request.getParameter("ruleReplyId");
		if (!StringUtils.isEmpty(fdRuleId)) {
			WeRuleReply ruleReply = weRuleReplyRepository.findOne(fdRuleId);
			ruleReply.setFdMaterial(model.getId());
			weRuleReplyRepository.save(ruleReply);
		}
		return render("/public/success");
	}
	
	@Override
	public ModelAndView index(Pageable page, WebRequest request) {
		WePublic wePublic = (WePublic) request.getAttribute("wePublic",
				RequestAttributes.SCOPE_SESSION);
		QueryBuilder queryBuilder = getRepository().getDao().queryBuilder();
		Page<WeRuleReplyText> models = null;
		try {
			queryBuilder.where().eq("fd_wepublic", wePublic.getId());
			models = getRepository().readPageByBuilder(queryBuilder, page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return render("index", models);
	}
}
