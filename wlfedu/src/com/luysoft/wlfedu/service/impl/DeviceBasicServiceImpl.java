package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.DeviceBasicMapper;
import com.luysoft.wlfedu.dto.DeviceBasic;
import com.luysoft.wlfedu.service.DeviceBasicService;

@Service
public class DeviceBasicServiceImpl implements DeviceBasicService {

	@Autowired
	private DeviceBasicMapper deviceBasicMapper;

	@Override
	public List<DeviceBasic> list(DeviceBasic cond) {

		// 查询工具基本信息列表
		return deviceBasicMapper.selectAll(cond);
	}

	@Transactional
	@Override
	public void delete(Integer id) {

		// 删除工具基本信息
		deviceBasicMapper.delete(id);
	}

	@Transactional
	@Override
	public int add(DeviceBasic basic) {

		// 新增工具基本信息
		deviceBasicMapper.insert(basic);

		return 0;
	}

	@Override
	public DeviceBasic select(Integer id) {

		// 查询工具基本信息
		return deviceBasicMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(DeviceBasic basic) {

		// 修改工具基本信息
		deviceBasicMapper.update(basic);
	}

	@Transactional
	@Override
	public void deleteByCode(String code) {
		deviceBasicMapper.deleteByCode(code);
	}
}
