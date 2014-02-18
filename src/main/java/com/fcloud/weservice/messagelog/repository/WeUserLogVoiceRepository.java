package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.VoiceMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogVideo;
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
		
		List<WeUserLogVoice> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogVoice log = new WeUserLogVoice();
			log.setId(IdGenerator.newId());
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

}
