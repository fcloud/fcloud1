package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.TextMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogLocation;
import com.fcloud.weservice.messagelog.model.WeUserLogText;

/**
 * 用户文本消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogTextRepository extends SimpleRepository<WeUserLogText>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		TextMessage message = (TextMessage) rbMessage;
		
		List<WeUserLogText> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogText log = new WeUserLogText();
			log.setId(IdGenerator.newId());
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdMsgid(String.valueOf(message.getMsgId()));
			log.setFdContent(message.getContent());
			save(log);
		}
	}
}
