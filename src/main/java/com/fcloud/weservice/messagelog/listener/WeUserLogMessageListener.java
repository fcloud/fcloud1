package com.fcloud.weservice.messagelog.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationListener;

import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.event.WeUserLogMessageEvent;

public class WeUserLogMessageListener implements
		ApplicationListener<WeUserLogMessageEvent> {

	private Map<String, IReceiveUserLogService> services = new HashMap<String, IReceiveUserLogService>();

	@Override
	public void onApplicationEvent(WeUserLogMessageEvent event) {
		ReqBaseMessage rbMessage = event.getRbMessage();
		if(rbMessage != null){
			IReceiveUserLogService logService = services.get(rbMessage.getMsgType());
			if(logService != null){
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
