package com.fcloud.weservice.messagelog.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.event.ReportedLocationEvent;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLocationEvent;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLocationEventRepository extends
		SimpleRepository<WeUserLocationEvent> implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		ReportedLocationEvent message = (ReportedLocationEvent) rbMessage;
		WeUserLocationEvent log = new WeUserLocationEvent();
		log.setId(IdGenerator.newId());
		log.setFdCode(message.getFromUserName());
		log.setFdOpenid(message.getToUserName());
		log.setFdCreatetime(new Date());
		log.setFdMsgtype(message.getMsgType());
		log.setFdEvent(message.getEvent());
		log.setFdLatitude(message.getLatitude());
		log.setFdLongitude(message.getLongitude());
		log.setFdPrecision(message.getPrecision());

		save(log);
	}

}
