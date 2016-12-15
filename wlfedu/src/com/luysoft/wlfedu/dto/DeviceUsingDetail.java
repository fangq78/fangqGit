package com.luysoft.wlfedu.dto;

public class DeviceUsingDetail {

	/** id */
	private Integer id;
	
	/** 设备id */
	private Integer deviceId;
	
	/** 用户id */
	private Integer userId;
	
	/** 用户名 */
	private String userName;
	
	/** 使用标识 */
	private Integer useFlag;
	
	/** 使用时间 */
	private String useDatetime;
	
	/** pad名称 */
	private String padName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public String getUseDatetime() {
		return useDatetime;
	}

	public void setUseDatetime(String useDatetime) {
		this.useDatetime = useDatetime;
	}

	public String getPadName() {
		return padName;
	}

	public void setPadName(String padName) {
		this.padName = padName;
	}
	
	
	
}
