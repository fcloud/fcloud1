package com.fcloud.wemessage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fcloud.util.MessageUtils;
import com.fcloud.util.StringUtil;
import com.fcloud.wechat.basic.util.EncryptUtils;
import com.fcloud.wemessage.messageType.ReqBaseMessage;
import com.fcloud.wemessage.service.impl.LweChatService;
import com.fcloud.weservice.messagelog.event.WeUserLogMessageEvent;
import com.fcloud.weservice.rule.model.WePublic;
import com.fcloud.weservice.rule.repository.WePublicRepository;


/**
 * @author:kezm
 * @date :2013-11-8
 */
@Controller
@RequestMapping("/api")
public class CoreController implements ApplicationContextAware{
	
	@Value("#{fcloud['fcloudhost']}")
    private String fcloudhost;
	@Autowired
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;		
	}
	
	@Autowired
	private LweChatService lweChatService;

	public void setLweChatService(LweChatService lweChatService) {
		this.lweChatService = lweChatService;
	}

	@Autowired
	private WePublicRepository wePublicRepository;

	public void setWePublicRepository(WePublicRepository wePublicRepository) {
		this.wePublicRepository = wePublicRepository;
	}

	@RequestMapping(value = { "/message/{id}" }, method = RequestMethod.POST)
	public void doWeChat(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id") String id) throws ServletException, IOException {
		try {
			ReqBaseMessage rbMessage = MessageUtils.tranMessage(request);
			
			// 处理和用户交互信息
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			//通知
			applicationContext.publishEvent(new WeUserLogMessageEvent(this,rbMessage));
			String pathUrl = fcloudhost;
    		if(request.getLocalPort() != 80){
    			pathUrl = StringUtil.linkString(pathUrl, ":", String.valueOf(request.getLocalPort()));
    		}
    		pathUrl = StringUtil.linkString(pathUrl, "/", request.getContextPath());
    		pathUrl = pathUrl+"/upload";
			// 调用核心业务类接收消息、处理消息
			String respMessage = lweChatService.processRequest(rbMessage,id,pathUrl);
			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "/message/{id}" }, method = RequestMethod.GET)
	public void doWeChat(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,
			@PathVariable("id") String id) throws ServletException, IOException {

		
		WePublic wePublic = wePublicRepository.findOne(id);

		String Token = wePublic == null ? "" : wePublic.getFdIntToken();
		//String Token = "090909";
		String[] ArrTmp = { Token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String pwd = EncryptUtils.Encrypt(sb.toString());
		PrintWriter printWriter = response.getWriter();

		if (pwd.equals(signature)) {
			if (!"".equals(echostr) && echostr != null) {
				printWriter.print(echostr);
			}
		}
		printWriter.close();
		printWriter = null;
	}

}
