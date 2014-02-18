package com.fcloud.weservice.messagelog.event;

import org.omg.CORBA.REBIND;
import org.springframework.context.ApplicationEvent;

import com.fcloud.wemessage.messageType.ReqBaseMessage;

public class WeUserLogMessageEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 接受消息基础类
	 */
	private ReqBaseMessage rbMessage;
	
	public WeUserLogMessageEvent(Object source) {
		super(source);
	}
	
	public WeUserLogMessageEvent(Object source,ReqBaseMessage rbMessage) {
		super(source);
		this.rbMessage = rbMessage;
	}
	
	public ReqBaseMessage getRbMessage() {
		return rbMessage;
	}

	public void setRbMessage(ReqBaseMessage rbMessage) {
		this.rbMessage = rbMessage;
	}

	
}
