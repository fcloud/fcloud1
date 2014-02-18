package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.LocationMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogLink;
import com.fcloud.weservice.messagelog.model.WeUserLogLocation;

/**
 * 用户地理位置消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogLocationRepository extends
		SimpleRepository<WeUserLogLocation> implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		LocationMessage message = (LocationMessage) rbMessage;
		List<WeUserLogLocation> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogLocation log = new WeUserLogLocation();
			log.setId(IdGenerator.newId());
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdMsgid(String.valueOf(message.getMsgId()));
			log.setFdLabel(message.getLabel());
			log.setFdLocationX(message.getLocation_X());
			log.setFdLocationY(message.getLocation_Y());
			log.setFdScale(Integer.getInteger(message.getScale()));
			save(log);
		}
	}

}
