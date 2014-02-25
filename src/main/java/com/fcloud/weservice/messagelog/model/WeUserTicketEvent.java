package com.fcloud.weservice.messagelog.model;

import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 用户事件消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_user_ticket_event")
@TableOrder(1000)
public class WeUserTicketEvent extends WeUserEventBase {
	@DatabaseField(columnName = "fd_event")
	private String fdEvent;

	@DatabaseField(columnName = "fd_eventKey")
	private String fdEventKey;

	@DatabaseField(columnName = "fd_ticket")
	private String fdTicket;

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

	public String getFdTicket() {
		return fdTicket;
	}

	public void setFdTicket(String fdTicket) {
		this.fdTicket = fdTicket;
	}

}
