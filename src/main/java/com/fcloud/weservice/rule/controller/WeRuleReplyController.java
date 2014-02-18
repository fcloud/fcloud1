package com.fcloud.weservice.rule.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fcloud.core.controller.ActionController;
import com.fcloud.util.IdGenerator;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReply;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextsRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyTextRepository;

/**
 * 回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/weservice/we_rule_reply")
public class WeRuleReplyController extends ActionController<WeRuleReply, WeRuleReplyRepository> {

    @Resource
    private WeRuleReplyTextRepository weRuleReplyTextRepository;
    @Resource
    private WeRuleReplyPictextRepository weRuleReplyPictextRepository;
    @Resource
    private WeRuleReplyPictextsRepository weRuleReplyPictextsRepository;

    @Override
    public ModelAndView create(WebRequest request) {
        WePublic wePublic = (WePublic)request.getAttribute("wePublic",RequestAttributes.SCOPE_SESSION);
        try{
            List<WeRuleReply> defaultList =  getRepository().getDao().queryBuilder().where().eq("fd_wepublic",wePublic.getId()).query();
            JSONArray array = new JSONArray();
            for( WeRuleReply ruleReply : defaultList){
                JSONObject json = new JSONObject();
                json.element("id",ruleReply.getId());
                json.element("key",ruleReply.getFdKey());
                json.element("matchType",String.valueOf(ruleReply.getFdMatchType()));
                json.element("replyType",String.valueOf(ruleReply.getFdReplyType()));
                json.element("isuse",ruleReply.getFdUse());
                //TODO 缺少公众号
                array.add(json);
            }
            request.setAttribute("rulereplyitem",array, RequestAttributes.SCOPE_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
        }
        return render("edit");
    }

    @RequestMapping("/saveByAjax")
    @Transactional
    public ModelAndView saveByAjax(HttpServletRequest request, HttpServletResponse response) {
        WePublic wePublic = (WePublic)request.getSession().getAttribute("wePublic");
        WeRuleReply ruleReply = new WeRuleReply();
        ruleReply.setId(IdGenerator.newId());
        ruleReply.setFdKey(request.getParameter("fd_key"));
        ruleReply.setFdUse(Integer.valueOf(request.getParameter("isOpen")));
        ruleReply.setFdMatchType(Integer.valueOf(request.getParameter("fd_match_type")));
        ruleReply.setFdReplyType(Integer.valueOf(request.getParameter("fd_reply_type")));
        ruleReply.setFdWepublic(wePublic);
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            getRepository().save(ruleReply);
            out = response.getWriter();
            JSONObject result = new JSONObject();
            result.element("fdId", ruleReply.getId());
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/editByAjax")
    @Transactional
    public ModelAndView editByAjax(HttpServletRequest request, HttpServletResponse response) {
        WeRuleReply ruleReply = null;
        String fdId = request.getParameter("fd_id");
        ruleReply = getRepository().findOne(fdId);
        ruleReply.setFdKey(request.getParameter("fd_key"));
        ruleReply.setFdUse(Integer.valueOf(request.getParameter("isOpen")));
        ruleReply.setFdMatchType(Integer.valueOf(request.getParameter("fd_match_type")));
        ruleReply.setFdReplyType(Integer.valueOf(request.getParameter("fd_reply_type")));
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            getRepository().save(ruleReply);
            out = response.getWriter();
            JSONObject result = new JSONObject();
            result.element("fdId", ruleReply.getId());
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/deleteByAjax")
    @Transactional
    public ModelAndView deleteByAjax(HttpServletRequest request, HttpServletResponse response) {
        WeRuleReply ruleReply = null;
        String fdId = request.getParameter("fdId");
        ruleReply = getRepository().findOne(fdId);
        if(ruleReply != null){
            try {
                int ruleType = ruleReply.getFdReplyType();
                //删除子
                delChild(ruleType,ruleReply.getId());
                //删除父
                getRepository().delete(ruleReply);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void delChild(int type,String ruleId) throws SQLException {
        if(type == 1){
            weRuleReplyTextRepository.deleteByRuleId(ruleId);
            return;
        }
        if(type == 2){
            weRuleReplyPictextRepository.deleteByRuleId(ruleId);
            return;
        }
        if(type == 3){
            weRuleReplyPictextsRepository.deleteByRuleId(ruleId);
            return;
        }
    }
}
