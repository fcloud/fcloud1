package com.fcloud.weservice.model;

import com.fcloud.core.model.Entity;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 多图文回复
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_rule_reply_pictexts")
public class WeRuleReplyPictexts extends Entity {

	/**
	 * 标题
	 */
    @DatabaseField(columnName = "fd_title")
	protected String fdTitle;

	/**
	 * @return 标题
	 */
	public String getFdTitle() {
		return fdTitle;
	}

	/**
	 * @param fdTitle 标题
	 */
	public void setFdTitle(String fdTitle) {
		this.fdTitle = fdTitle;
	}

	/**
	 * 封面
	 */
    @DatabaseField(columnName = "fd_pic")
	protected String fdPic;

	/**
	 * @return 封面
	 */
	public String getFdPic() {
		return fdPic;
	}

	/**
	 * @param fdPic 封面
	 */
	public void setFdPic(String fdPic) {
		this.fdPic = fdPic;
	}

	/**
	 * 正文
	 */
    @DatabaseField(columnName = "fd_text")
	protected String fdText;

	/**
	 * @return 正文
	 */
	public String getFdText() {
		return fdText;
	}

	/**
	 * @param fdText 正文
	 */
	public void setFdText(String fdText) {
		this.fdText = fdText;
	}

	/**
	 * 链接
	 */
    @DatabaseField(columnName = "fd_url")
	protected String fdUrl;

	/**
	 * @return 链接
	 */
	public String getFdUrl() {
		return fdUrl;
	}

	/**
	 * @param fdUrl 链接
	 */
	public void setFdUrl(String fdUrl) {
		this.fdUrl = fdUrl;
	}

	/**
	 * 分类
	 */
    @DatabaseField(columnName = "fd_tags")
	protected String fdTags;

	/**
	 * @return 分类
	 */
	public String getFdTags() {
		return fdTags;
	}

	/**
	 * @param fdTags 分类
	 */
	public void setFdTags(String fdTags) {
		this.fdTags = fdTags;
	}

    /**
     * 子图文
     */
    @ForeignCollectionField(orderColumnName="id",orderAscending=true)
    private ForeignCollection<WeRuleReplyPictextson> weRuleReplyPictextsons;

    public ForeignCollection<WeRuleReplyPictextson> getWeRuleReplyPictextsons() {
        return weRuleReplyPictextsons;
    }
}
