package com.luysoft.wlfedu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luysoft.wlfedu.dto.Device;

public interface DeviceMapper {

	/**
	 * 新增工具信息
	 * 
	 * @param record 工具信息
	 */
	void insert(Device record);

	/**
	 * 删除工具信息
	 * 
	 * @param id 工具信息Id
	 */
	void delete(Integer id);
	
	/**
	 * 删除工具信息
	 * 
	 * @param 基本信息Id
	 */
	void deleteByBasicId(Integer basicId);

	/**
	 * 修改工具信息
	 * 
	 * @param record 工具信息
	 */
	void update(@Param("record")Device record);
	
	/**
	 * 修改工具使用信息
	 * 
	 * @param record 工具使用信息
	 */
	void updateUsingInfo(@Param("record")Device record);

	/**
	 * 查询工具信息
	 * 
	 * @param id 工具信息Id
	 * @return 工具信息
	 */
	Device select(@Param("id")Integer id);
	
	/**
	 * 查询工具信息
	 * 
	 * @param id 工具信息Id
	 * @return 工具信息
	 */
	List<Device> list(@Param("record")Device record);

	/**
	 * 查询工具信息列表
	 * 
	 * @return 工具信息列表
	 */
//	List<Device> selectAll(@Param("record") Device cond);
}