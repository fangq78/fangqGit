package com.luysoft.wlfedu.dao;

import java.util.List;

import com.luysoft.wlfedu.dto.DeviceUsingDetail;

public interface DeviceUsingDetailMapper {

	/**
	 * 新增使用信息
	 * 
	 * @param record 使用信息
	 */
	void insert(DeviceUsingDetail record);
	
	/**
	 * 检索使用信息
	 * 
	 * @param record 使用信息
	 */
	List<DeviceUsingDetail> search(DeviceUsingDetail cond);
	
}
