package com.fcloud.weservice.rule.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fcloud.core.controller.ActionController;
import com.fcloud.weservice.rule.model.WeMenu;
import com.fcloud.weservice.rule.repository.WeMenuRepository;

/**
 * 自定义菜单
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/we/weMenu")
public class WeMenuController extends ActionController<WeMenu,WeMenuRepository> {
}
