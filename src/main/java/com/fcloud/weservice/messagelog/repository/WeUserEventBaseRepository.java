package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.VoiceMessage;
import com.fcloud.wemessage.messageType.req.event.ReqBaseEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserEvent;
import com.fcloud.weservice.messagelog.model.WeUserLogVoice;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
public class WeUserEventBaseRepository implements IReceiveUserLogService {
	private Map<String, IReceiveUserLogService> service = new HashMap<String, IReceiveUserLogService>();

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		ReqBaseEvent rbMBaseEvent = (ReqBaseEvent) rbMessage;
		if (rbMBaseEvent != null) {
			String eventType = rbMBaseEvent.getEvent();
			IReceiveUserLogService logService = service.get(eventType);
			if (logService != null) {
				try {
					logService.dealLog(rbMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
