package com.fcloud.weservice.rule.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReplyDefault;

/**
 * 默认回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeRuleReplyDefaultRepository extends SimpleRepository<WeRuleReplyDefault> {
	//获取规则
    public WeRuleReplyDefault findDefaultByPublic(WePublic wePublic,String eventType){
    	WeRuleReplyDefault ruleReplyDefault = null;
    	try {
        	List<WeRuleReplyDefault> replies = getDao().queryBuilder().where().eq("fd_wepublic", wePublic.getId()).and().eq("fd_type",Integer.parseInt(eventType)).query();
        	if(replies != null && !replies.isEmpty()){
        		ruleReplyDefault = replies.get(0);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ruleReplyDefault;
    }
}
