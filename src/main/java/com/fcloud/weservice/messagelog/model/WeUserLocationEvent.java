package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 用户事件消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_user_location_event")
public class WeUserLocationEvent extends WeUserEventBase {
	@DatabaseField(columnName = "fd_event")
	private String fdEvent = "location";

	@DatabaseField(columnName = "fd_latitude")
	private String fdLatitude;

	@DatabaseField(columnName = "fd_longitude")
	private String fdLongitude;

	@DatabaseField(columnName = "fd_precision")
	private String fdPrecision;

	public String getFdEvent() {
		return fdEvent;
	}

	public void setFdEvent(String fdEvent) {
		this.fdEvent = fdEvent;
	}

	public String getFdLatitude() {
		return fdLatitude;
	}

	public void setFdLatitude(String fdLatitude) {
		this.fdLatitude = fdLatitude;
	}

	public String getFdLongitude() {
		return fdLongitude;
	}

	public void setFdLongitude(String fdLongitude) {
		this.fdLongitude = fdLongitude;
	}

	public String getFdPrecision() {
		return fdPrecision;
	}

	public void setFdPrecision(String fdPrecision) {
		this.fdPrecision = fdPrecision;
	}

}
