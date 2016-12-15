package com.luysoft.wlfedu.dto;

import java.util.Date;

/**
 * 学员登录记录
 */
public class LoginHistory {

	/** id */
	private Integer id;

	/** 学员Id */
	private Integer userId;

	/** 登录时间 */
	private Date loginTime;

	/** 客户端IP */
	private String ip;


	/**
	 * 取得id。
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置id。
	 *
	 * @param id id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 取得学员Id。
	 *
	 * @return 学员Id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置学员Id。
	 *
	 * @param userId 学员Id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 取得登录时间。
	 *
	 * @return 登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * 设置登录时间。
	 *
	 * @param loginTime 登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * 取得客户端IP。
	 *
	 * @return 客户端IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置客户端IP。
	 *
	 * @param ip 客户端IP
	 */
	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

}