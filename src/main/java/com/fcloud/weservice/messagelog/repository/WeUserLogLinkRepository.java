package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.LinkMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogImage;
import com.fcloud.weservice.messagelog.model.WeUserLogLink;

/**
 * 用户链接消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogLinkRepository extends SimpleRepository<WeUserLogLink>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		LinkMessage message = (LinkMessage) rbMessage;

		List<WeUserLogLink> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogLink log = new WeUserLogLink();
			log.setId(IdGenerator.newId());
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdMsgid(String.valueOf(message.getMsgId()));
			log.setFdDescription(message.getDescription());
			log.setFdTitle(message.getTitle());
			log.setFdUrl(message.getUrl());
			save(log);
		}
	}

}
