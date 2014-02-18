package com.fcloud.weservice.messagelog.repository;

import java.util.HashMap;
import java.util.Map;

import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.event.ReqBaseEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
public class WeUserEventBaseRepository implements IReceiveUserLogService {
	private Map<String, IReceiveUserLogService> services = new HashMap<String, IReceiveUserLogService>();

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		ReqBaseEvent rbMBaseEvent = (ReqBaseEvent) rbMessage;
		if (rbMBaseEvent != null) {
			String eventType = rbMBaseEvent.getEvent();
			IReceiveUserLogService logService = services.get(eventType);
			if (logService != null) {
				try {
					logService.dealLog(rbMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Map<String, IReceiveUserLogService> getServices() {
		return services;
	}

	public void setServices(Map<String, IReceiveUserLogService> services) {
		this.services = services;
	}

	
}
