/**
 * 
 */
package com.fcloud.wechat.auth.model;

import java.io.Serializable;

/**
 * @author ruben fu
 *
 */
public class SessionUser implements Serializable {

    private static final long serialVersionUID = -8697617245229493021L;
	
	public static final String SESSION_KEY = "SessionUser";
	
	private static final ThreadLocal<SessionUser> users = new ThreadLocal<SessionUser>();

    public static SessionUser get() {
		return users.get();
	}
	
	public static void set(SessionUser user) {
		users.set(user);
	}
	
	public static void remove() {
		users.remove();
	}

	private String username;
	
	private String userid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public boolean isAdmin() {
		return ("admin".equals(username));
	}
}
