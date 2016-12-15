package com.luysoft.wlfedu.dao;

import com.luysoft.wlfedu.dto.DeviceType;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DeviceTypeMapper {

	/**
	 * 新增物资编码
	 * 
	 * @param record 物资编码
	 * @param userId 系统用户Id
	 */
	void insert(@Param("record")DeviceType record);

	/**
	 * 删除物资编码
	 * 
	 * @param code 物资编码
	 */
	void delete(String code);

	/**
	 * 修改物资编码
	 * 
	 * @param record 物资编码
	 * @param userId 系统用户Id
	 */
	void update(@Param("record")DeviceType record);

	/**
	 * 查询物资编码
	 * 
	 * @param 物资编码
	 * @return 物资编码
	 */
	DeviceType select(@Param("code")String code);

	/**
	 * 查询物资编码列表
	 * 
	 * @return 物资编码列表
	 */
	List<DeviceType> selectAll(@Param("cond")DeviceType cond,@Param("limit")Integer limit);
}