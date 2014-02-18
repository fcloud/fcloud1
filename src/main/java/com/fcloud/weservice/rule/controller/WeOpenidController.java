package com.fcloud.weservice.rule.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fcloud.core.controller.ActionController;
import com.fcloud.weservice.rule.model.WeOpenid;
import com.fcloud.weservice.rule.repository.WeOpenidRepository;

/**
 * OpenID
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/we/weOpenid")
public class WeOpenidController extends ActionController<WeOpenid,WeOpenidRepository> {

}
