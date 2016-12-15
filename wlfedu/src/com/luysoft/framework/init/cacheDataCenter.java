package com.luysoft.framework.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.luysoft.wlfedu.dto.DeviceType;

public class cacheDataCenter {
	private static Map<String,DeviceType> deviceTypeMap = new ConcurrentHashMap<String,DeviceType>();
	
	public static void putDeviceTypeMapData(DeviceType deviceType) {
		deviceTypeMap.put(deviceType.getCode(), deviceType);
	}
	
	public static DeviceType getDeviceType(String code) {
		return deviceTypeMap.get(code);
	}
	
	public static DeviceType removeDeviceType(String code) {
		return deviceTypeMap.remove(code);
	}
	
	public static List<DeviceType> DeviceTypeToList() {
		List<DeviceType> list = new ArrayList<DeviceType>();
		list.addAll(deviceTypeMap.values());
		return list;
	}
}
