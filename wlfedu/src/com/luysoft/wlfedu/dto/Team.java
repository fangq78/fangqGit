package com.luysoft.wlfedu.dto;

import java.util.Date;

/**
 * 车间
 */
public class Team {

	/** id */
	private Integer id;

	/** 所属段 */
	private Integer depotId;
	
	/** 所属段 */
	private String depotName;
	
	/** 所属车间 */
	private Integer workshopId;
	
	/** 所属车间名 */
	private String workshopName;
	
	/** 名称 */
	private String name;
	
	/** 电话 */
	private String mobile;
	
	/** 创建者Id */
	private Integer creatorId;

	/** 创建时间 */
	private Date createdTime;

	/** 更新者Id */
	private Integer updaterId;

	/** 更新时间 */
	private Date updatedTime;

	/**
	 * 取得id。
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置id。
	 *
	 * @param id id
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * 取得创建者Id。
	 *
	 * @return 创建者Id
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * 设置创建者Id。
	 *
	 * @param creatorId 创建者Id
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 取得创建时间。
	 *
	 * @return 创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * 设置创建时间。
	 *
	 * @param createdTime 创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 取得更新者Id。
	 *
	 * @return 更新者Id
	 */
	public Integer getUpdaterId() {
		return updaterId;
	}

	/**
	 * 设置更新者Id。
	 *
	 * @param updaterId 更新者Id
	 */
	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	/**
	 * 取得更新时间。
	 *
	 * @return 更新时间
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * 设置更新时间。
	 *
	 * @param updatedTime 更新时间
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * 取得电话。
	 *
	 * @return 电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置电话。
	 *
	 * @param mobile 更新电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 取得所属段。
	 *
	 * @return 所属段
	 */
	public Integer getDepotId() {
		return depotId;
	}

	/**
	 * 设置所属段。
	 *
	 * @param depotId 更新所属段
	 */
	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}

	/**
	 * 取得所属段名。
	 *
	 * @return 所属段名
	 */
	public String getDepotName() {
		return depotName;
	}

	/**
	 * 设置所属段名。
	 *
	 * @param depotName 更新所属段名
	 */
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	
	/**
	 * 取得所属车间id。
	 *
	 * @return 所属车间id
	 */
	public Integer getWorkshopId() {
		return workshopId;
	}

	/**
	 * 设置所属车间id。
	 *
	 * @param workshopId 更新所属车间id
	 */
	public void setWorkshopId(Integer workshopId) {
		this.workshopId = workshopId;
	}

	/**
	 * 取得所属车间名。
	 *
	 * @return 所属车间名
	 */
	public String getWorkshopName() {
		return workshopName;
	}

	/**
	 * 设置所属车间名。
	 *
	 * @param workshopName 更新所属车间名
	 */
	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}
}