package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.DeviceBasicMapper;
import com.luysoft.wlfedu.dao.DeviceTypeMapper;
import com.luysoft.wlfedu.dto.DeviceType;
import com.luysoft.wlfedu.service.DeviceTypeService;


@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

	@Autowired
	private DeviceTypeMapper deviceTypeMapper;
	
	@Override
	public List<DeviceType> list(DeviceType cond,Integer limit) {

		// 查询物资编码列表
		return deviceTypeMapper.selectAll(cond,limit);
	}

	@Transactional
	@Override
	public void delete(String code) {
		
		// 删除物资编码
		deviceTypeMapper.delete(code);
	}

	@Transactional
	@Override
	public int add(DeviceType deviceType) {

		// 新增物资编码
		deviceTypeMapper.insert(deviceType);

		return 0;
	}

	@Override
	public DeviceType select(String code) {

		// 查询物资编码
		return deviceTypeMapper.select(code);
	}

	@Transactional
	@Override
	public void edit(DeviceType deviceType) {

		// 修改物资编码
		deviceTypeMapper.update(deviceType);
	}
}
