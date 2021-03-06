package com.fcloud.weservice.messagelog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.util.IdGenerator;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.messageType.req.VideoMessage;
import com.fcloud.weservice.messagelog.IReceiveUserLogService;
import com.fcloud.weservice.messagelog.model.WeUserLogText;
import com.fcloud.weservice.messagelog.model.WeUserLogVideo;

/**
 * 用户图片消息
 * 
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WeUserLogVideoRepository extends SimpleRepository<WeUserLogVideo>
		implements IReceiveUserLogService {

	@Override
	public void dealLog(ReqBaseMessage rbMessage) throws Exception {
		VideoMessage message = (VideoMessage) rbMessage;
		
		List<WeUserLogVideo> logs = getDao().queryBuilder().where()
				.eq("fd_msgid", message.getMsgId()).query();
		if (!(logs != null && !logs.isEmpty())) {
			WeUserLogVideo log = new WeUserLogVideo();
			log.setId(IdGenerator.newId());
			log.setFdCode(message.getFromUserName());
			log.setFdOpenid(message.getToUserName());
			log.setFdCreatetime(new Date());
			log.setFdMsgtype(message.getMsgType());
			log.setFdMsgid(String.valueOf(message.getMsgId()));
			log.setFdMediaId(message.getMediaId());
			log.setFdThumbMediaId(message.getThumbMediaId());
			save(log);
		}
	}

}
