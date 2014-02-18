package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.event.CustomMenuEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserClickEvent;
import com.fcloud.weservice.messagelog.model.WeUserLogVoice;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserClickEventRepository extends
		SimpleRepository<WeUserClickEvent> implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		CustomMenuEvent message = (CustomMenuEvent) rbMessage;
		WeUserClickEvent log = new WeUserClickEvent();
		log.setId(IdGenerator.newId());
		log.setFdCode(message.getFromUserName());
		log.setFdOpenid(message.getToUserName());
		log.setFdCreatetime(new Date());
		log.setFdMsgtype(message.getMsgType());
		log.setFdEvent(message.getEvent());
		log.setFdEventKey(message.getEventKey());
		save(log);
	}

}
