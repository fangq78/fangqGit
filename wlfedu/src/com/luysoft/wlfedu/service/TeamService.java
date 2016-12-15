package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.Team;



/**
 * 
 * 班组Service
 *
 */
public interface TeamService {

	/**
	 * 查询班组列表
	 * 
	 * @return 班组列表
	 */
	List<Team> list(Team cond);

	/**
	 * 删除班组
	 * 
	 * @param id 班组Id
	 */
	void delete(Integer id);

	/**
	 * 删除班组
	 * 
	 * @param id 车间Id
	 */
	void deleteByWorkshopId(Integer workshopId);
	
	/**
	 * 删除班组
	 * 
	 * @param id 段Id
	 */
	void deleteByDepotId(Integer depotId);
	
	/**
	 * 登录班组
	 * 
	 * @param team 班组
	 * @param userId 系统用户Id
	 * @return 0: 登录成功, 1: 班组名重复
	 */
	int add(Team team, Integer userId);

	/**
	 * 查询班组
	 * 
	 * @param id 班组Id
	 * @return 班组
	 */
	Team select(Integer id);

	/**
	 * 修改班组
	 * 
	 * @param channel 班组
	 * @param userId 系统用户Id
	 */
	void edit(Team team, Integer userId);
}
