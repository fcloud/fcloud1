package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.VoiceMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogVoice;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogVoiceRepository extends SimpleRepository<WeUserLogVoice>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		VoiceMessage message = (VoiceMessage) rbMessage;
		WeUserLogVoice log = new WeUserLogVoice();
		log.setFdCode(message.getFromUserName());
		log.setFdOpenid(message.getToUserName());
		log.setFdCreatetime(new Date());
		log.setFdMsgtype(message.getMsgType());
		log.setFdMsgid(String.valueOf(message.getMsgId()));
		log.setFdMediaId(message.getMediaId());
		log.setFdFormat(message.getFormat());
		save(log);
	}

}
