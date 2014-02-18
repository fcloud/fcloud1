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
@DatabaseTable(tableName = "we_user_event")
public class WeUserEvent extends WeUserEventBase {
	@DatabaseField(columnName = "fd_event")
	private String fdEvent;

	@DatabaseField(columnName = "fd_eventKey")
	private String fdEventKey;

	public String getFdEvent() {
		return fdEvent;
	}

	public void setFdEvent(String fdEvent) {
		this.fdEvent = fdEvent;
	}

	public String getFdEventKey() {
		return fdEventKey;
	}

	public void setFdEventKey(String fdEventKey) {
		this.fdEventKey = fdEventKey;
	}

}
