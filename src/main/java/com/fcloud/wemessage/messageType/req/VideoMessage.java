package com.fcloud.wemessage.messageType.req;


import com.fcloud.wemessage.messageType.ReqBaseMessage;

/**
 * 视频消息
 * 
 * @author:kezm
 * @date :2013-11-6
 */
public class VideoMessage extends ReqBaseMessage {
	
	// 视频消息媒体id
	private String MediaId;
	
	// 视频消息缩略图的媒体id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}


}
