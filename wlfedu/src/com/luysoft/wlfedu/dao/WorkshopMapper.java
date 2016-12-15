package com.luysoft.wlfedu.dao;

import com.luysoft.wlfedu.dto.Workshop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WorkshopMapper {

	/**
	 * 新增车间
	 * 
	 * @param record 车间
	 * @param userId 系统用户Id
	 */
	void insert(@Param("record")Workshop record, @Param("userId")Integer userId);

	/**
	 * 删除段
	 * 
	 * @param id 车间Id
	 */
	void delete(Integer id);
	
	/**
	 * 删除段
	 * 
	 * @param 段Id
	 */
	void deleteByDepotId(Integer depotId);
	

	/**
	 * 修改车间
	 * 
	 * @param record 车间
	 * @param userId 系统用户Id
	 */
	void update(@Param("record")Workshop record, @Param("userId")Integer userId);

	/**
	 * 查询车间
	 * 
	 * @param id 车间Id
	 * @return 车间
	 */
	Workshop select(@Param("id")Integer id);

	/**
	 * 查询车间列表
	 * 
	 * @return 车间列表
	 */
	List<Workshop> selectAll(@Param("record") Workshop cond);
}