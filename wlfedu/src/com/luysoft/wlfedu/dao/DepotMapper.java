package com.luysoft.wlfedu.dao;

import com.luysoft.wlfedu.dto.Depot;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DepotMapper {

	/**
	 * 新增段
	 * 
	 * @param record 段
	 * @param userId 系统用户Id
	 */
	void insert(@Param("record")Depot record, @Param("userId")Integer userId);

	/**
	 * 删除端
	 * 
	 * @param id 段Id
	 */
	void delete(Integer id);

	/**
	 * 修改段
	 * 
	 * @param record 段
	 * @param userId 系统用户Id
	 */
	void update(@Param("record")Depot record, @Param("userId")Integer userId);

	/**
	 * 查询段
	 * 
	 * @param id 段Id
	 * @return 段
	 */
	Depot select(@Param("id")Integer id);

	/**
	 * 查询段列表
	 * 
	 * @return 段列表
	 */
	List<Depot> selectAll();
}