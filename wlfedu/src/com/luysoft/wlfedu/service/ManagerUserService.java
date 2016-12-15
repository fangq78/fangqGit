package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.ManagerUser;

/**
 * 系统用户
 */
public interface ManagerUserService {
	
	/**
	 * 查询系统用户信息
	 * 
	 * @param name 用户名
	 * @param password 登录密码
	 * @return 系统用户信息
	 */
	ManagerUser selectManagerUser(String name, String password);
	
	/**
	 * 查询系统用户
	 * 
	 * @param id 用户Id
	 * @return 系统用户
	 */
	ManagerUser select(Integer id);
	
	/**
	 * 查询系统用户列表
	 * 
	 * @param cond 查询条件
	 * @return 系统用户列表
	 */
	List<ManagerUser> list(ManagerUser cond);

	/**
	 * 删除系统用户
	 * 
	 * @param id 系统用户Id
	 * @param userId 登录系统用户Id
	 */
	void delete(Integer id, Integer userId);

	/**
	 * 删除系统用户
	 * 
	 * @param depotId 段Id
	 */
	void deleteByDepotId(Integer depotId);
	
	/**
	 * 删除系统用户
	 * 
	 * @param workshopId 车间Id
	 */
	void deleteByWorkshopId(Integer workshopId);
	
	/**
	 * 新增系统用户
	 * 
	 * @param user 系统用户
	 * @param userId 登录系统用户Id
	 * @return 0:成功, 1:用户名重复
	 */
	int add(ManagerUser user, Integer userId);

	/**
	 * 修改系统用户
	 * 
	 * @param user 系统用户
	 * @param userId 登录系统用户Id
	 */
	void edit(ManagerUser user, Integer userId);

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