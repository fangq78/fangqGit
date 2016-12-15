package com.luysoft.framework.init;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.luysoft.wlfedu.dto.DeviceType;
import com.luysoft.wlfedu.service.DeviceTypeService;

@SuppressWarnings("rawtypes")
@Repository
public class InitDataListener implements ApplicationListener {
	@Autowired
	DeviceTypeService deviceTypeService;
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		DeviceType cond = new DeviceType();
		List<DeviceType>  list = deviceTypeService.list(cond,null);
		for (DeviceType type:list) {
			cacheDataCenter.putDeviceTypeMapData(type);
		}
	}
	

}
