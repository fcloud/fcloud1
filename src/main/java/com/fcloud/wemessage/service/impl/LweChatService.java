package com.fcloud.wemessage.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.repository.WePublicRepository;

@Service
public class LweChatService {
	
	private Logger logger = Logger.getLogger(LweChatService.class);

	@Autowired
	private WePublicRepository wePublicRepository;

	public void setWePublicRepository(
			WePublicRepository wePublicRepository) {
		this.wePublicRepository = wePublicRepository;
	}

    /**
     * 处理微信发来的请求
     * @param rbMessage
     * @param pid
     * @return
     */
	public String processRequest(ReqBaseMessage rbMessage,String pid,String rootPath) {
		String respMessage = "";
		try {
			//id是公众号
			WePublic wePublic = wePublicRepository.findOne(pid);
			if(wePublic == null){
				logger.error("===LweChatService error,该id无公众号记录===");
				return null;
			}
			respMessage = wePublicRepository.sendMessage(wePublic, rbMessage,rootPath);
		} catch (Exception e) {
			logger.error(e);
		}
		return respMessage;
	}
	
}
