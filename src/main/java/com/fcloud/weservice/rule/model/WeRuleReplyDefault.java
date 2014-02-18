package com.fcloud.weservice.rule.model;


import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 默认回复规则
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_rule_reply_default")
public class WeRuleReplyDefault extends Entity {

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
	 * 类型
	 */
    @DatabaseField(columnName = "fd_rule_type")
	protected Integer fdRuleType;

	/**
	 * @return 类型
	 */
	public Integer getFdRuleType() {
		return fdRuleType;
	}

	/**
	 * @param fdRuleType 类型
	 */
	public void setFdRuleType(Integer fdRuleType) {
		this.fdRuleType = fdRuleType;
	}

	/**
	 * 回复内容
	 */
    @DatabaseField(columnName = "fd_rule_json")
	protected String fdRuleJson;

	/**
	 * @return 回复内容
	 */
	public String getFdRuleJson() {
		return fdRuleJson;
	}

	/**
	 * @param fdRuleJson 回复内容
	 */
	public void setFdRuleJson(String fdRuleJson) {
		this.fdRuleJson = fdRuleJson;
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

    @DatabaseField(columnName = "fd_type")
    protected Integer fdType;

    public Integer getFdType() {
        return fdType;
    }

    public void setFdType(Integer fdType) {
        this.fdType = fdType;
    }
}
