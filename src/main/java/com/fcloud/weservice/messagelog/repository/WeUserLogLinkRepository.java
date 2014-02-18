package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.LinkMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogLink;

/**
 * 用户链接消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogLinkRepository extends SimpleRepository<WeUserLogLink>  implements IReceiveUserLogService{

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		LinkMessage message = (LinkMessage)rbMessage;
		WeUserLogLink log = new WeUserLogLink();
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
