package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 用户地理位置消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_user_log_location")
public class WeUserLogLocation extends WeUserLogBase {

	/**
	 * 消息类型
	 */
	@DatabaseField(columnName = "fd_msgtype")
	protected String fdMsgtype = "location";

	/**
	 * @return 消息类型
	 */
	public String getFdMsgtype() {
		return fdMsgtype;
	}

	/**
	 * @param fdMsgtype
	 *            消息类型
	 */
	public void setFdMsgtype(String fdMsgtype) {
		this.fdMsgtype = fdMsgtype;
	}

	/**
	 * 地理位置纬度
	 */
	@DatabaseField(columnName = "fd_location_x")
	protected String fdLocationX;

	/**
	 * @return 地理位置纬度
	 */
	public String getFdLocationX() {
		return fdLocationX;
	}

	/**
	 * @param fdLocationX
	 *            地理位置纬度
	 */
	public void setFdLocationX(String fdLocationX) {
		this.fdLocationX = fdLocationX;
	}

	/**
	 * 地理位置经度
	 */
	@DatabaseField(columnName = "fd_location_y")
	protected String fdLocationY;

	/**
	 * @return 地理位置经度
	 */
	public String getFdLocationY() {
		return fdLocationY;
	}

	/**
	 * @param fdLocationY
	 *            地理位置经度
	 */
	public void setFdLocationY(String fdLocationY) {
		this.fdLocationY = fdLocationY;
	}

	/**
	 * 地图缩放大小
	 */
	@DatabaseField(columnName = "fd_scale")
	protected Integer fdScale;

	/**
	 * @return 地图缩放大小
	 */
	public Integer getFdScale() {
		return fdScale;
	}

	/**
	 * @param fdScale
	 *            地图缩放大小
	 */
	public void setFdScale(Integer fdScale) {
		this.fdScale = fdScale;
	}

	/**
	 * 地理位置信息
	 */
	@DatabaseField(columnName = "fd_label")
	protected String fdLabel;

	/**
	 * @return 地理位置信息
	 */
	public String getFdLabel() {
		return fdLabel;
	}

	/**
	 * @param fdLabel
	 *            地理位置信息
	 */
	public void setFdLabel(String fdLabel) {
		this.fdLabel = fdLabel;
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
	 * @param fdMsgid
	 *            消息id
	 */
	public void setFdMsgid(String fdMsgid) {
		this.fdMsgid = fdMsgid;
	}

}
