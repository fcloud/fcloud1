package com.fcloud.weservice.rule.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.model.WeRuleReply;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * 回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeRuleReplyRepository extends SimpleRepository<WeRuleReply> {
	//获取规则
    public WeRuleReply findByPublic(WePublic wePublic,String fdText){
    	WeRuleReply ruleReply = null;
    	try {
        	List<WeRuleReply> replies = null;
        	QueryBuilder queryBuilder = getDao().queryBuilder();
        	queryBuilder.where().eq("fd_wepublic", wePublic.getId()).and().eq("fd_match_type", 2).and().eq("fd_key",fdText);
        	queryBuilder.orderBy("id", true);
        	replies = queryBuilder.query();
        	if(replies != null && !replies.isEmpty()){
        		ruleReply = replies.get(0);
        	}else{
        		queryBuilder.where().reset().eq("fd_wepublic", wePublic.getId()).and().eq("fd_match_type", 1).and().like("fd_key", "%"+fdText+"%");
        		replies = queryBuilder.query();
        		if(replies != null && !replies.isEmpty()){
            		ruleReply = replies.get(0);
            	}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ruleReply;
    }
}
