package com.fcloud.weservice.rule.model;

import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 文本回复
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_rule_reply_text")
public class WeRuleReplyText extends Entity {

	/**
	 * 回复内容
	 */
    @DatabaseField(columnName = "fd_text")
	protected String fdText;

	/**
	 * @return 回复内容
	 */
	public String getFdText() {
		return fdText;
	}

	/**
	 * @param fdText 回复内容
	 */
	public void setFdText(String fdText) {
		this.fdText = fdText;
	}
	
	/**
	 * 相关公众号
	 */
    @DatabaseField(columnName = "fd_wepublic",foreign = true)
	protected WePublic fdWepublic;

	/**
	 * @return 相关公众号
	 */
	public WePublic getFdWepublic() {
		return fdWepublic;
	}

	/**
	 * @param fdWepublic 相关公众号 
	 */
	public void setFdWepublic(WePublic fdWepublic) {
		this.fdWepublic = fdWepublic;
	}

}
