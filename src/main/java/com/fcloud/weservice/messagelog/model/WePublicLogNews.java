package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 回复图文消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_public_log_news")
@TableOrder(1000)
public class WePublicLogNews extends Entity {

	/**
	 * OpenID
	 */
    @DatabaseField(columnName = "fd_openid")
	protected String fdOpenid;

	/**
	 * @return OpenID
	 */
	public String getFdOpenid() {
		return fdOpenid;
	}

	/**
	 * @param fdOpenid OpenID
	 */
	public void setFdOpenid(String fdOpenid) {
		this.fdOpenid = fdOpenid;
	}

	/**
	 * 公众号二维码
	 */
    @DatabaseField(columnName = "fd_code")
	protected String fdCode;

	/**
	 * @return 公众号二维码
	 */
	public String getFdCode() {
		return fdCode;
	}

	/**
	 * @param fdCode 公众号二维码
	 */
	public void setFdCode(String fdCode) {
		this.fdCode = fdCode;
	}

	/**
	 * 消息创建时间
	 */
    @DatabaseField(columnName = "fd_createtime")
	protected Date fdCreatetime;

	/**
	 * @return 消息创建时间
	 */
	public Date getFdCreatetime() {
		return fdCreatetime;
	}

	/**
	 * @param fdCreatetime 消息创建时间
	 */
	public void setFdCreatetime(Date fdCreatetime) {
		this.fdCreatetime = fdCreatetime;
	}

	/**
	 * 消息类型
	 */
    @DatabaseField(columnName = "fd_msgtype")
	protected String fdMsgtype;

	/**
	 * @return 消息类型
	 */
	public String getFdMsgtype() {
		return fdMsgtype;
	}

	/**
	 * @param fdMsgtype 消息类型
	 */
	public void setFdMsgtype(String fdMsgtype) {
		this.fdMsgtype = fdMsgtype;
	}

	/**
	 * 图文消息个数
	 */
    @DatabaseField(columnName = "fd_articlecount")
	protected Integer fdArticlecount;

	/**
	 * @return 图文消息个数
	 */
	public Integer getFdArticlecount() {
		return fdArticlecount;
	}

	/**
	 * @param fdArticlecount 图文消息个数
	 */
	public void setFdArticlecount(Integer fdArticlecount) {
		this.fdArticlecount = fdArticlecount;
	}

	/**
	 * 源消息id
	 */
    @DatabaseField(columnName = "fd_srcid")
	protected String fdSrcid;

	/**
	 * @return 源消息id
	 */
	public String getFdSrcid() {
		return fdSrcid;
	}

	/**
	 * @param fdSrcid 源消息id
	 */
	public void setFdSrcid(String fdSrcid) {
		this.fdSrcid = fdSrcid;
	}

}
