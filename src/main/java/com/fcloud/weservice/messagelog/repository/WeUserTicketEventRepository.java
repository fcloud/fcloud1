package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.event.DimensionalCodeScanEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserTicketEvent;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserTicketEventRepository extends SimpleRepository<WeUserTicketEvent>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		DimensionalCodeScanEvent message = (DimensionalCodeScanEvent) rbMessage;
		WeUserTicketEvent log = new WeUserTicketEvent();
		log.setFdCode(message.getFromUserName());
		log.setFdOpenid(message.getToUserName());
		log.setFdCreatetime(new Date());
		log.setFdMsgtype(message.getMsgType());
		log.setFdEvent(message.getEvent());
		log.setFdEventKey(message.getEventKey());
		log.setFdTicket(message.getTicket());
		save(log);
	}

}
