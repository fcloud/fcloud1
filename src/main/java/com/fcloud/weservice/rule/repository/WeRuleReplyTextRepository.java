package com.fcloud.weservice.rule.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.weservice.rule.model.WeRuleReplyText;

/**
 * 文本回复
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeRuleReplyTextRepository extends SimpleRepository<WeRuleReplyText> {
    public void deleteByRuleId(String ruleId) throws SQLException {
        List<WeRuleReplyText> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
            WeRuleReplyText ruleReplyText = ruleReplyTexts.get(0);
            delete(ruleReplyText);
        }
    }
    
    public WeRuleReplyText findByRuleId(String ruleId) throws SQLException {
    	WeRuleReplyText ruleReplyText = null;
        List<WeRuleReplyText> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
            ruleReplyText = ruleReplyTexts.get(0);
        }
        return ruleReplyText;
    }
}
