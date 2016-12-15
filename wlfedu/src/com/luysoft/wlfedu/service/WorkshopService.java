package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.Workshop;


/**
 * 
 * 车间Service
 *
 */
public interface WorkshopService {

	/**
	 * 查询车间列表
	 * 
	 * @return 车间列表
	 */
	List<Workshop> list(Workshop workshop);

	/**
	 * 删除车间
	 * 
	 * @param id 车间Id
	 */
	void delete(Integer id);

	/**
	 * 删除车间
	 * 
	 * @param id 段Id
	 */
	void deleteByDepotId(Integer depotId);
	
	/**
	 * 登录车间
	 * 
	 * @param workshop 车间
	 * @param userId 系统用户Id
	 * @return 0: 登录成功, 1: 车间名重复
	 */
	int add(Workshop workshop, Integer userId);

	/**
	 * 查询车间
	 * 
	 * @param id 车间Id
	 * @return 车间
	 */
	Workshop select(Integer id);

	/**
	 * 修改车间
	 * 
	 * @param channel 车间
	 * @param userId 系统用户Id
	 */
	void edit(Workshop workshop, Integer userId);
}
