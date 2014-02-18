package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.event.ConcernsAndCancelEvent;
import com.fcloud.wemessage.messageType.req.event.DimensionalCodeScanEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserEvent;
import com.fcloud.weservice.messagelog.model.WeUserTicketEvent;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserEventRepository extends SimpleRepository<WeUserEvent>
		implements IReceiveUserLogService {

	@Resource
	private WeUserTicketEventRepository userTicketEventRepository;

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		if (rbMessage instanceof ConcernsAndCancelEvent) {
			ConcernsAndCancelEvent message = (ConcernsAndCancelEvent) rbMessage;
			WeUserEvent log = new WeUserEvent();
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdEvent(message.getEvent());
			log.setFdEventKey(message.getEventKey());
			save(log);
		} else {
			userTicketEventRepository.dealLog(rbMessage);
		}
	}

}
