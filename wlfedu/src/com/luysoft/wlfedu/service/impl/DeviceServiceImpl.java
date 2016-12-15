package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.DeviceMapper;
import com.luysoft.wlfedu.dto.Device;
import com.luysoft.wlfedu.service.DeviceService;


@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public List<Device> list(Device cond) {

		// 查询工具信息列表
		return deviceMapper.list(cond);
	}

	@Transactional
	@Override
	public void delete(Integer id) {

		// 删除工具信息
		deviceMapper.delete(id);
	}

	@Transactional
	@Override
	public void add(Device device) {
		// 新增工具信息
		deviceMapper.insert(device);
	}

	@Override
	public Device select(Integer id) {

		// 查询工具信息
		return deviceMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(Device device) {

		// 修改工具信息
		deviceMapper.update(device);
	}
	
	@Transactional
	@Override 
	public void updateUsingInfo(Device info) {
		// 修改工具使用信息
		deviceMapper.updateUsingInfo(info);
	}
	
	@Transactional
	@Override
	public void deleteByBasicId(Integer basicId) {
		deviceMapper.deleteByBasicId(basicId);
	}

}
