package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.Device;

/**
 * 
 * 工具信息Service
 *
 */
public interface DeviceService {

	/**
	 * 查询工具信息列表
	 * 
	 * @return 工具信息列表
	 */
	List<Device> list(Device cond);

	/**
	 * 删除工具信息
	 * 
	 * @param id 工具信息Id
	 */
	void delete(Integer id);
	
	/**
	 * 删除工具信息
	 * 
	 * @param id 工具基本信息Id
	 */
	void deleteByBasicId(Integer basicId);
	

	/**
	 * 登录工具信息
	 * 
	 * @param device 工具信息
	 * @return 0: 登录成功, 1: 工具信息名重复
	 */
	void add(Device device);

	/**
	 * 查询工具信息
	 * 
	 * @param id 工具信息Id
	 * @return 工具信息
	 */
	Device select(Integer id);
	

	/**
	 * 修改工具信息
	 * 
	 * @param device 工具信息
	 */
	void edit(Device device);
	
	/**
	 * 修改工具使用信息
	 * 
	 * @param info 工具使用信息
	 */
	void updateUsingInfo(Device info);
}
