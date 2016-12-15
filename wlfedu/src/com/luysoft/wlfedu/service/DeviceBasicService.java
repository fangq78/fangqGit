package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.DeviceBasic;



/**
 * 
 * 工具基本信息Service
 *
 */
public interface DeviceBasicService {

	/**
	 * 查询工具基本信息列表
	 * 
	 * @return 工具基本信息列表
	 */
	List<DeviceBasic> list(DeviceBasic cond);

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
	 * 登录工具基本信息
	 * 
	 * @param workshop 工具基本信息
	 * @return 0: 登录成功, 1: 工具基本信息名重复
	 */
	int add(DeviceBasic basic);

	/**
	 * 查询工具基本信息
	 * 
	 * @param id 工具基本信息Id
	 * @return 工具基本信息
	 */
	DeviceBasic select(Integer id);

	/**
	 * 修改工具基本信息
	 * 
	 * @param channel 工具基本信息
	 */
	void edit(DeviceBasic basic);
}
