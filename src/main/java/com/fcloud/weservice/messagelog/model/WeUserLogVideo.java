package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 用户图片消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_user_log_video")
@TableOrder(1000)
public class WeUserLogVideo extends WeUserLogBase {

	/**
	 * 消息类型
	 */
    @DatabaseField(columnName = "fd_msgtype")
	protected String fdMsgtype = "video";

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
	 * 图片链接
	 */
    @DatabaseField(columnName = "fd_mediaId")
	protected String fdMediaId ;

	/**
	 * @return 图片链接
	 */
	public String getFdMediaId() {
		return fdMediaId;
	}

	/**
	 * @param fdPicurl 图片链接
	 */
	public void setFdMediaId(String fdMediaId) {
		this.fdMediaId = fdMediaId;
	}
	
	/**
	 * 语音格式
	 */
    @DatabaseField(columnName = "fd_thumbMediaId")
	protected String fdThumbMediaId;

	public String getFdThumbMediaId() {
		return fdThumbMediaId;
	}

	public void setFdThumbMediaId(String fdThumbMediaId) {
		this.fdThumbMediaId = fdThumbMediaId;
	}

	/**
	 * 消息id
	 */
    @DatabaseField(columnName = "fd_msgid")
	protected String fdMsgid;

	/**
	 * @return 消息id
	 */
	public String getFdMsgid() {
		return fdMsgid;
	}

	/**
	 * @param fdMsgid 消息id
	 */
	public void setFdMsgid(String fdMsgid) {
		this.fdMsgid = fdMsgid;
	}

}
