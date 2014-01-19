package com.fcloud.weservice.controller;

import com.fcloud.core.controller.ActionController;
import com.fcloud.util.IdGenerator;
import com.fcloud.wechat.auth.model.SessionUser;
import com.fcloud.wechat.user.model.User;
import com.fcloud.wechat.user.repository.UserRepository;
import com.fcloud.weservice.model.WePublic;
import com.fcloud.weservice.model.WeRuleReply;
import com.fcloud.weservice.repository.WePublicRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zxpfss
 * Date: 13-11-19
 * Time: 下午11:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/weservice/we_public")
public class WePublicController extends ActionController<WePublic,WePublicRepository> {

    @Resource
    private UserRepository userRepository;

    @Override
    public ModelAndView create(WebRequest request) {
        WePublic model = createModel();
        model.setFdIntToken(IdGenerator.newId());
        return render("edit", model);
    }

    @Override
    public ModelAndView save(@ModelAttribute WePublic model, WebRequest request, BindingResult result) {
        String userId = SessionUser.get().getUserid();
        User user = userRepository.findOne(userId);
        model.setUser(user);
        return super.save(model, request, result);
    }

    @RequestMapping("/getWePublic")
    public ModelAndView getWePublic(HttpServletRequest request, HttpServletResponse response) {
        String userid = SessionUser.get().getUserid();
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            List<WePublic> publicList =  getRepository().getDao().queryBuilder().where().eq("user",userid).query();
            out = response.getWriter();
            JSONArray array = new JSONArray();
            for(WePublic wePublic:publicList){
                JSONObject result = new JSONObject();
                result.put("publicId",wePublic.getId());
                result.put("publicName",wePublic.getFdName());
                array.add(result);
            }
            out.print(array);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/setPublicInSession")
    public ModelAndView setPublicInSession(WebRequest request, HttpServletResponse response) {
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        String wepublicid = request.getParameter("wepublicid");
        try {
            WePublic wePublic = getRepository().findOne(wepublicid);
            JSONObject result = new JSONObject();
            if(wePublic != null){
                request.setAttribute("wePublic",wePublic, RequestAttributes.SCOPE_SESSION);
                result.put("result","success");
            }else{
                result.put("result","error");
            }
            out = response.getWriter();
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return null;
    }
}
