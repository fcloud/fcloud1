package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.ImageMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogImage;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogImageRepository extends SimpleRepository<WeUserLogImage>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		ImageMessage message = (ImageMessage) rbMessage;
		List<WeUserLogImage> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogImage log = new WeUserLogImage();
			log.setId(IdGenerator.newId());
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdMsgid(String.valueOf(message.getMsgId()));
			log.setFdMsgid(message.getMediaId());
			save(log);
		}

	}

}
