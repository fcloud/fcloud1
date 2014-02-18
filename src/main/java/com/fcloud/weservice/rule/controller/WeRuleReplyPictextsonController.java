package com.fcloud.weservice.rule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fcloud.core.controller.ActionController;
import com.fcloud.weservice.rule.model.WeRuleReplyPictextson;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextsonRepository;

/**
 * 子图文
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Controller
@RequestMapping("/we/weRuleReplyPictextson")
public class WeRuleReplyPictextsonController extends ActionController<WeRuleReplyPictextson,WeRuleReplyPictextsonRepository> {

}
