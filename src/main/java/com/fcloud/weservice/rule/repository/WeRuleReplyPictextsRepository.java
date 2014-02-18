package com.fcloud.weservice.rule.repository;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.weservice.rule.model.WeRuleReplyPictexts;
import com.fcloud.weservice.rule.model.WeRuleReplyPictextson;
import com.j256.ormlite.dao.ForeignCollection;

/**
 * 多图文回复
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeRuleReplyPictextsRepository extends SimpleRepository<WeRuleReplyPictexts> {

    @Resource
    private WeRuleReplyPictextsonRepository weRuleReplyPictextsonRepository;

    public void deleteByRuleId(String ruleId) throws SQLException {
        List<WeRuleReplyPictexts> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
        	delete(ruleReplyTexts.get(0));
        }
    }
    
    public WeRuleReplyPictexts findRuleId(String ruleId) throws SQLException {
    	WeRuleReplyPictexts ruleReplyPictexts = null;
        List<WeRuleReplyPictexts> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
            ruleReplyPictexts = ruleReplyTexts.get(0);    
        }
        return ruleReplyPictexts;
    }

	@Override
	public void delete(WeRuleReplyPictexts entity) {
        ForeignCollection<WeRuleReplyPictextson> pictextsons = entity.getWeRuleReplyPictextsons();
        for(WeRuleReplyPictextson pictextson : pictextsons){
            weRuleReplyPictextsonRepository.delete(pictextson);
        }
		super.delete(entity);
	}
    
}
