package com.luysoft.wlfedu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luysoft.wlfedu.dto.DeviceBasic;

public interface DeviceBasicMapper {

	/**
	 * 新增工具基本信息
	 * 
	 * @param record 工具基本信息
	 */
	void insert(@Param("record")DeviceBasic record);

	/**
	 * 删除工具基本信息
	 * 
	 * @param id 工具基本信息Id
	 */
	void delete(Integer id);
	
	/**
	 * 删除工具基本信息
	 * 
	 * @param 物资编码
	 */
	void deleteByCode(String code);

	/**
	 * 修改工具基本信息
	 * 
	 * @param record 工具基本信息
	 */
	void update(@Param("record")DeviceBasic record);

	/**
	 * 查询工具基本信息
	 * 
	 * @param id 工具基本信息Id
	 * @return 工具基本信息
	 */
	DeviceBasic select(@Param("id")Integer id);

	/**
	 * 查询工具基本信息列表
	 * 
	 * @return 工具基本信息列表
	 */
	List<DeviceBasic> selectAll(@Param("record") DeviceBasic cond);
}