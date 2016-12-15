package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.DeviceType;

/**
 * 
 * 物资编码Service
 *
 */
public interface DeviceTypeService {

	/**
	 * 查询物资编码列表
	 * 
	 * @return 物资编码列表
	 */
	List<DeviceType> list(DeviceType cond,Integer limit);

	/**
	 * 删除物资编码
	 * 
	 * @param code 物资编码
	 */
	void delete(String code);

	/**
	 * 登录物资编码
	 * 
	 * @param deviceType 物资编码
	 * @param userId 系统用户Id
	 * @return 0: 登录成功, 1: 物资编码编码重复
	 */
	int add(DeviceType deviceType);

	/**
	 * 查询物资编码
	 * 
	 * @param id 物资编码Id
	 * @return 物资编码
	 */
	DeviceType select(String code);

	/**
	 * 修改物资编码
	 * 
	 * @param depot 物资编码
	 * @param userId 系统用户Id
	 */
	void edit(DeviceType deviceType);
}
