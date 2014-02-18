package com.fcloud.weservice.messagelog;

import com.fcloud.wemessage.messageType.ReqBaseMessage;

public interface IReceiveUserLogService {
	public void dealLog(ReqBaseMessage rbMessage) throws Exception;
}
