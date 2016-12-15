package com.luysoft.wlfedu.dao;

import com.luysoft.wlfedu.dto.ManagerUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ManagerUserMapper {
	/**
	 * 删除车间管理员
	 * 
	 * @param id 车间管理员id
	 * @param userId 用户id
	 */
	void delete(@Param("id")Integer id, @Param("userId")Integer userId);
	
	/**
	 * 删除车间管理员
	 * 
	 * @param depotId 段ID
	 */
	void deleteByDepotId(Integer depotId);
	
	/**
	 * 删除车间管理员
	 * 
	 * @param workshopId 车间ID
	 */
	void deleteByWorkshopId(Integer workshopId);

	/**
	 * 新增车间管理员
	 * 
	 * @param record 追加车间管理员
	 * @param userId 用户id
	 */
	void insert(@Param("record")ManagerUser record, @Param("userId")Integer userId);

	/**
	 * 根据用户名，登录密码查询系统用户信息
	 * 
	 * @param record 系统用户
	 * @return 系统用户
	 */
	ManagerUser selectByNamePwd(@Param("record")ManagerUser record);
	
	/**
	 * 根据用户名查询系统用户信息
	 * 
	 * @param record 系统用户
	 * @return 系统用户
	 */
	ManagerUser selectByName(@Param("name")String name);

	/**
	 * 根据管理员id 查询车间管理员
	 * 
	 * @param id 管理员id
	 * @return 车间管理员信息
	 */
	ManagerUser select(@Param("id")Integer id);

	/**
	 * 修正车间管理员情报
	 * 
	 * @param record 车间管理员情报
	 * @param userId 用户id
	 */
	void update(@Param("record")ManagerUser record, @Param("userId")Integer userId);

	/**
	 * 修改系统用户密码
	 * 
	 * @param id 管理员id
	 * @param password 用户密码
	 * @param userId 用户id
	 */
	void updatePassword(@Param("id")Integer id, @Param("password")String password, @Param("userId")Integer userId);
	
	/**
	 * 根据用户名，用户类型查询系统用户信息
	 * 
	 * @return 系统用户
	 */
	List<ManagerUser> list(@Param("record")ManagerUser cond);
}