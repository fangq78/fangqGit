package com.luysoft.wlfedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.dto.TeamUser;

public interface TeamUserMapper {
	/**
	 * 删除班组管理员
	 * 
	 * @param id 班组管理员id
	 * @param userId 用户id
	 */
	void delete(@Param("id")Integer id, @Param("userId")Integer userId);
	
	
	/**
	 * 删除班组管理员
	 * 
	 * @param 段Id
	 */
	void deleteByDepotId(Integer depotId);
	
	
	/**
	 * 删除班组管理员
	 * 
	 * @param  车间id
	 */
	void deleteByWorkshopId(Integer workshopId);
	
	
	/**
	 * 删除班组管理员
	 * 
	 * @param 班组Id
	 */
	void deleteByTeamId(Integer teamId);
	
	

	/**
	 * 新增班组管理员
	 * 
	 * @param record 追加班组管理员
	 * @param userId 用户id
	 */
	void insert(@Param("record")TeamUser record, @Param("userId")Integer userId);

	/**
	 * 根据管理员id 查询班组管理员
	 * 
	 * @param id 管理员id
	 * @return 班组管理员信息
	 */
	TeamUser select(@Param("id")Integer id);

	/**
	 * 根据用户名查询系统用户信息
	 * 
	 * @param record 系统用户
	 * @return 系统用户
	 */
	TeamUser selectByName(@Param("name")String name);
	
	/**
	 * 修正班组管理员情报
	 * 
	 * @param record 班组管理员情报
	 * @param userId 用户id
	 */
	void update(@Param("record")TeamUser record, @Param("userId")Integer userId);

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
	 * @return 班组用户
	 */
	List<TeamUser> list(@Param("record")TeamUser cond);
}