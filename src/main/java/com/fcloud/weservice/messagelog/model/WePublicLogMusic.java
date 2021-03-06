package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 回复音乐消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_public_log_music")
@TableOrder(1000)
public class WePublicLogMusic extends Entity {

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
	 * 音乐链接
	 */
    @DatabaseField(columnName = "fd_musicurl")
	protected String fdMusicurl;

	/**
	 * @return 音乐链接
	 */
	public String getFdMusicurl() {
		return fdMusicurl;
	}

	/**
	 * @param fdMusicurl 音乐链接
	 */
	public void setFdMusicurl(String fdMusicurl) {
		this.fdMusicurl = fdMusicurl;
	}

	/**
	 * 高质量音乐链接
	 */
    @DatabaseField(columnName = "fd_hqmusicurl")
	protected String fdHqmusicurl;

	/**
	 * @return 高质量音乐链接
	 */
	public String getFdHqmusicurl() {
		return fdHqmusicurl;
	}

	/**
	 * @param fdHqmusicurl 高质量音乐链接
	 */
	public void setFdHqmusicurl(String fdHqmusicurl) {
		this.fdHqmusicurl = fdHqmusicurl;
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
