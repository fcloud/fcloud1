package com.fcloud.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
/**
 * 导航控制器
 * @author zxpfss
 *
 */
@Controller
@RequestMapping("/index")
public class FcloudIndexController {
	@RequestMapping("/thePublicManage")
    public ModelAndView thePublicManage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView(); 
		view.setViewName("/index/thePublicManage");
		return view;
    }
	
	@RequestMapping("/backgroundManage")
    public ModelAndView backgroundManage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView(); 
		view.setViewName("/index/backgroundManage");
		return view;
    }
}
