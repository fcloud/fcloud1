package com.fcloud.weservice.rule.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fcloud.core.controller.ActionController;
import com.fcloud.util.IdGenerator;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReplyDefault;
import com.fcloud.weservice.rule.repository.WeRuleReplyDefaultRepository;

/**
 * 默认回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/weservice/we_rule_reply_default")
public class WeRuleReplyDefaultController extends ActionController<WeRuleReplyDefault,WeRuleReplyDefaultRepository> {

    @Override
    public ModelAndView create(WebRequest request) {
        try{
            WePublic wePublic = (WePublic)request.getAttribute("wePublic",RequestAttributes.SCOPE_SESSION);

            List<WeRuleReplyDefault> defaultList =  getRepository().getDao().queryBuilder().where().eq("fd_wepublic",wePublic.getId()).query();
            //List<WeRuleReplyDefault> defaultList =  getRepository().getDao().queryBuilder().query();
            JSONObject json = new JSONObject();
            for(WeRuleReplyDefault replyDefault : defaultList){
                json.put("default_id"+replyDefault.getFdType(),replyDefault.getId());
                json.put("default_use"+replyDefault.getFdType(),replyDefault.getFdUse());
                json.put("default_text"+replyDefault.getFdType(),replyDefault.getFdRuleJson());
                json.put("default_type"+replyDefault.getFdType(),replyDefault.getFdRuleType());
            }
            request.setAttribute("fdArea",json, RequestAttributes.SCOPE_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
        }
        return render("edit");
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    @Transactional
    public ModelAndView save(HttpServletRequest request , HttpServletResponse response) {
        WePublic wePublic = (WePublic)request.getSession().getAttribute("wePublic");
        String fdArea = request.getParameter("fdArea");
        saveDefault(fdArea,wePublic);
        return render("/public/success");
    }

    private void saveDefault(String fdArea,WePublic wePublic){
        JSONObject areaJson = JSONObject.fromObject(fdArea);
        for(int i = 0; i<2; i++){
            WeRuleReplyDefault weRuleReplyDefault = null;
            if(StringUtils.isEmpty(areaJson.getString("default_id"+(i+1)))){
                weRuleReplyDefault = new WeRuleReplyDefault();
                weRuleReplyDefault.setId(IdGenerator.newId());
                weRuleReplyDefault.setFdWepublic(wePublic);
            }else{
                weRuleReplyDefault = getRepository().findOne(areaJson.getString("default_id"+(i+1)));
                if(weRuleReplyDefault == null){
                    weRuleReplyDefault = new WeRuleReplyDefault();
                    weRuleReplyDefault.setId(IdGenerator.newId());
                }
            };
            weRuleReplyDefault.setFdUse(Integer.valueOf(areaJson.getString("default_use" + (i + 1))));
            weRuleReplyDefault.setFdRuleJson(areaJson.getString("default_text" + (i + 1)));
            weRuleReplyDefault.setFdRuleType(Integer.valueOf(areaJson.getString("default_type" + (i + 1))));
            weRuleReplyDefault.setFdType(i+1);
            getRepository().save(weRuleReplyDefault);
        }
    }
}
