package com.luysoft.wlfedu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luysoft.wlfedu.dto.Team;

public interface TeamMapper {

	/**
	 * 新增班组
	 * 
	 * @param record 班组
	 * @param userId 系统用户Id
	 */
	void insert(@Param("record")Team record, @Param("userId")Integer userId);

	/**
	 * 删除班组
	 * 
	 * @param id 班组Id
	 */
	void delete(Integer id);
	
	/**
	 * 删除班组
	 * 
	 * @param id 段Id
	 */
	void deleteByDepotId(Integer depotId);

	/**
	 * 删除班组
	 * 
	 * @param id 车间Id
	 */
	void deleteByWorkshopId(Integer workshopId);
	
	/**
	 * 修改班组
	 * 
	 * @param record 班组
	 * @param userId 系统用户Id
	 */
	void update(@Param("record")Team record, @Param("userId")Integer userId);

	/**
	 * 查询班组
	 * 
	 * @param id 班组Id
	 * @return 班组
	 */
	Team select(@Param("id")Integer id);

	/**
	 * 查询班组列表
	 * 
	 * @return 班组列表
	 */
	List<Team> selectAll(@Param("record") Team team);
}