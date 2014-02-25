package com.fcloud.weservice.rule.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@TableOrder(1001)
@DatabaseTable(tableName = "we_rule_reply")
public class WeRuleReply extends Entity {

	/**
	 * 是否启用
	 */
    @DatabaseField(columnName = "fd_use")
	protected Integer fdUse;

	/**
	 * @return 是否启用
	 */
	public Integer getFdUse() {
		return fdUse;
	}

	/**
	 * @param fdUse 是否启用
	 */
	public void setFdUse(Integer fdUse) {
		this.fdUse = fdUse;
	}

	/**
	 * 关键词
	 */
    @DatabaseField(columnName = "fd_key")
	protected String fdKey;

	/**
	 * @return 关键词
	 */
	public String getFdKey() {
		return fdKey;
	}

	/**
	 * @param fdKey 关键词
	 */
	public void setFdKey(String fdKey) {
		this.fdKey = fdKey;
	}

	/**
	 * 匹配方式
	 */
    @DatabaseField(columnName = "fd_match_type")
	protected Integer fdMatchType;

	/**
	 * @return 匹配方式
	 */
	public Integer getFdMatchType() {
		return fdMatchType;
	}

	/**
	 * @param fdMatchType 匹配方式
	 */
	public void setFdMatchType(Integer fdMatchType) {
		this.fdMatchType = fdMatchType;
	}

	/**
	 * 回复类型
	 */
    @DatabaseField(columnName = "fd_reply_type")
	protected Integer fdReplyType;

	/**
	 * @return 回复类型
	 */
	public Integer getFdReplyType() {
		return fdReplyType;
	}

	/**
	 * @param fdReplyType 回复类型
	 */
	public void setFdReplyType(Integer fdReplyType) {
		this.fdReplyType = fdReplyType;
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
	
	@DatabaseField(columnName = "fd_material")
	protected String fdMaterial;

	public String getFdMaterial() {
		return fdMaterial;
	}

	public void setFdMaterial(String fdMaterial) {
		this.fdMaterial = fdMaterial;
	}

}
