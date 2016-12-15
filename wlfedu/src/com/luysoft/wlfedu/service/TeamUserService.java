package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.TeamUser;


/**
 * 系统用户
 */
public interface TeamUserService {
	
	/**
	 * 查询系统用户
	 * 
	 * @param id 用户Id
	 * @return 系统用户
	 */
	TeamUser select(Integer id);
	
	/**
	 * 查询系统用户列表
	 * 
	 * @param cond 查询条件
	 * @return 系统用户列表
	 */
	List<TeamUser> list(TeamUser cond);

	/**
	 * 删除系统用户
	 * 
	 * @param id 系统用户Id
	 * @param userId 登录系统用户Id
	 */
	void delete(Integer id, Integer userId);
	
	/**
	 * 删除班组人员
	 * 
	 * @param  段ID
	 */
	void deleteByDepotId(Integer depotId);
	
	/**
	 * 删除班组人员
	 * 
	 * @param  车间ID
	 */
	void deleteByWorkshopId(Integer workshopId);
	
	/**
	 * 删除班组人员
	 * 
	 * @param  班组ID
	 */
	void deleteByTeamId(Integer teamId);

	/**
	 * 新增系统用户
	 * 
	 * @param user 系统用户
	 * @param userId 登录系统用户Id
	 * @return 0:成功, 1:用户名重复
	 */
	int add(TeamUser user, Integer userId);

	/**
	 * 修改系统用户
	 * 
	 * @param user 系统用户
	 * @param userId 登录系统用户Id
	 */
	void edit(TeamUser user, Integer userId);

	/**
	 * 修改用户密码
	 * 
	 * @param id 系统用户Id
	 * @param password 用户密码
	 * @param userId 登录系统用户Id
	 */
	void password(Integer id, String password, Integer userId);
	
	int hasDuplicateName(String name);
}