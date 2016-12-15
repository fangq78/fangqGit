package com.luysoft.wlfedu.dto;

import java.util.Date;

/**
 * 系统用户
 */
public class TeamUser {

	/** id */
	private Integer id;

	/** 用户名 */
	private String name;
	
	/** 用户名 */
	private String realName;

	/** 登录密码（MD5） */
	private String password;

	/** 段id */
	private Integer depotId;
	
	/** 段名称 */
	private String depotName;
	
	/** 车间id */
	private Integer workshopId;
	
	/** 车间名称 */
	private String workshopName;
	
	/** 班组id */
	private Integer teamId;
	
	/** 班组名称 */
	private String teamName;

	/** 联系电话 */
	private String mobile;

	/** 是否删除（0: 正常  1:删除） */
	private Integer deleted;

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
	 * 取得用户名。
	 *
	 * @return 用户名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置用户名。
	 *
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 取得登录密码（MD5）。
	 *
	 * @return 登录密码（MD5）
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置登录密码（MD5）。
	 *
	 * @param password 登录密码（MD5）
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 取得联系电话。
	 *
	 * @return 联系电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置联系电话。
	 *
	 * @param mobile 联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * 取得是否删除（0: 正常  1:删除）。
	 *
	 * @return 是否删除（0: 正常  1:删除）
	 */
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 * 设置是否删除（0: 正常  1:删除）。
	 *
	 * @param deleted 是否删除（0: 正常  1:删除）
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	
	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}

	public Integer getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(Integer workshopId) {
		this.workshopId = workshopId;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}