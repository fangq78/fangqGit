package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.DeviceUsingDetailMapper;
import com.luysoft.wlfedu.dto.DeviceUsingDetail;
import com.luysoft.wlfedu.service.DeviceUsingDetailService;

@Service
public class DeviceUsingDetailServiceImp implements DeviceUsingDetailService {

	@Autowired
	private DeviceUsingDetailMapper deviceUsingDetailMapper;
	
	@Transactional
	@Override
	public void insert(DeviceUsingDetail record) {
		deviceUsingDetailMapper.insert(record);

	}

	@Override
	public List<DeviceUsingDetail> search(DeviceUsingDetail cond) {
		return deviceUsingDetailMapper.search(cond);
	}

}
