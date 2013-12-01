package com.fcloud.weservice.controller;

import com.fcloud.core.controller.ActionController;
import com.fcloud.core.model.Entity;
import com.fcloud.weservice.model.WeRuleReplyPictextson;
import com.fcloud.weservice.repository.WeRuleReplyPictextsonRepository;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
