package com.luysoft.wlfedu.dto;

import java.util.Date;

/**
 * 物资编码
 */
public class DeviceType {

	/** 物资编码 */
	private String code;

	/** 名称 */
	private String name;
	
	/** 说明 */
	private String comment;
	

	/**
	 * 取得物资编码。
	 *
	 * @return 物资编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置物资编码。
	 *
	 * @param 物资编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	

	/**
	 * 取得名称。
	 *
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称。
	 *
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}