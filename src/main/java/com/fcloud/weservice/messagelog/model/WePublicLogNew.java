package com.fcloud.weservice.messagelog.model;


import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 回复子图文消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_public_log_new")
public class WePublicLogNew extends Entity {

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
	 * 排序号
	 */
    @DatabaseField(columnName = "fd_order")
	protected Integer fdOrder;

	/**
	 * @return 排序号
	 */
	public Integer getFdOrder() {
		return fdOrder;
	}

	/**
	 * @param fdOrder 排序号
	 */
	public void setFdOrder(Integer fdOrder) {
		this.fdOrder = fdOrder;
	}

	/**
	 * 图文消息标题
	 */
    @DatabaseField(columnName = "fd_title")
	protected String fdTitle;

	/**
	 * @return 图文消息标题
	 */
	public String getFdTitle() {
		return fdTitle;
	}

	/**
	 * @param fdTitle 图文消息标题
	 */
	public void setFdTitle(String fdTitle) {
		this.fdTitle = fdTitle;
	}

	/**
	 * 图文消息描述
	 */
    @DatabaseField(columnName = "fd_description")
	protected String fdDescription;

	/**
	 * @return 图文消息描述
	 */
	public String getFdDescription() {
		return fdDescription;
	}

	/**
	 * @param fdDescription 图文消息描述
	 */
	public void setFdDescription(String fdDescription) {
		this.fdDescription = fdDescription;
	}

	/**
	 * 图片链接
	 */
    @DatabaseField(columnName = "fd_picurl")
	protected String fdPicurl;

	/**
	 * @return 图片链接
	 */
	public String getFdPicurl() {
		return fdPicurl;
	}

	/**
	 * @param fdPicurl 图片链接
	 */
	public void setFdPicurl(String fdPicurl) {
		this.fdPicurl = fdPicurl;
	}

	/**
	 * 点击图文消息跳转链接
	 */
    @DatabaseField(columnName = "fd_url")
	protected String fdUrl;

	/**
	 * @return 点击图文消息跳转链接
	 */
	public String getFdUrl() {
		return fdUrl;
	}

	/**
	 * @param fdUrl 点击图文消息跳转链接
	 */
	public void setFdUrl(String fdUrl) {
		this.fdUrl = fdUrl;
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

	/**
	 * 相关图文消息
	 */
    @DatabaseField(columnName = "fd_wepubliclognews")
	protected String fdWepubliclognews;

	/**
	 * @return 相关图文消息
	 */
	public String getFdWepubliclognews() {
		return fdWepubliclognews;
	}

	/**
	 * @param fdWepubliclognews 相关图文消息
	 */
	public void setFdWepubliclognews(String fdWepubliclognews) {
		this.fdWepubliclognews = fdWepubliclognews;
	}

	/**
	 * 相关图文消息
	 */
    @DatabaseField(columnName = "wePublicLogNews",foreign = true)
	protected WePublicLogNews wePublicLogNews;

	/**
	 * @return 相关图文消息
	 */
	public WePublicLogNews getWePublicLogNews() {
		return wePublicLogNews;
	}

	/**
	 * @param wePublicLogNews 相关图文消息
	 */
	public void setWePublicLogNews(WePublicLogNews wePublicLogNews) {
		this.wePublicLogNews = wePublicLogNews;
	}

}
