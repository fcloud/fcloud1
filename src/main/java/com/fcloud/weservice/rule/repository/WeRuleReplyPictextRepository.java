package com.fcloud.weservice.rule.repository;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.sys.att.repository.SysAttRepository;
import com.fcloud.util.StringUtil;
import com.fcloud.weservice.rule.model.WeRuleReplyPictext;

/**
 * 单图文回复
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeRuleReplyPictextRepository extends SimpleRepository<WeRuleReplyPictext> {

	@Resource
	private SysAttRepository sysAttRepository;
	
    public void deleteByRuleId(String ruleId) throws SQLException {
        List<WeRuleReplyPictext> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
            WeRuleReplyPictext ruleReplyPictext = ruleReplyTexts.get(0);
            delete(ruleReplyPictext);
        }
    }
    
    public WeRuleReplyPictext findByRuleId(String ruleId) throws SQLException {
    	WeRuleReplyPictext ruleReplyPictext = null;
        List<WeRuleReplyPictext> ruleReplyTexts = getDao().queryBuilder().where().eq("fd_werulereply", ruleId).query();
        if (ruleReplyTexts != null && ruleReplyTexts.size() > 0) {
            ruleReplyPictext = ruleReplyTexts.get(0);
        }
        return ruleReplyPictext;
    }

	@Override
	public void delete(WeRuleReplyPictext entity) {
		if(StringUtil.isNotNull(entity.getAttId())){
			try {
				sysAttRepository.deleteByAttId(entity.getAttId());
			} catch (Exception e) {
				e.printStackTrace();
				throw wrapException(e);
			}
		}
		super.delete(entity);
	}
}
