package com.fcloud.wemessage.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.util.MessageConstant;
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
			//判断消息类型
			String mesType = rbMessage.getMsgType();
			//事件消息
			if(MessageConstant.REQ_MESSAGE_TYPE_EVENT.equals(mesType)){
				respMessage = wePublicRepository.sendEventMessage(wePublic, rbMessage,rootPath);	
			}
			//文本消息
			if(MessageConstant.REQ_MESSAGE_TYPE_TEXT.equals(mesType)){
				respMessage = wePublicRepository.sendMessage(wePublic, rbMessage,rootPath);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return respMessage;
	}
	
}
