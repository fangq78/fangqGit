package com.luysoft.wlfedu.service;

/**
 * 
 * 学员登录Service
 *
 */
public interface LoginService {

	/**
	 * 新增学员登录记录
	 * 
	 * @param userId 学员Id
	 * @param ip 客户端Ip
	 */
	public void history(Integer userId, String ip);
}
