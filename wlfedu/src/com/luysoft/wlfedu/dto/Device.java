package com.luysoft.wlfedu.dto;

import com.luysoft.wlfedu.common.Tools;

/**
 * 工具信息
 */
public class Device {

	private String formatId;
	/** id */
	private Integer id;
	
	/** 基本信息id */
	private Integer basicId;

	/** 工具名称 */
	private String name;
	
	/** 所属段id */
	private Integer depotId;
	
	/** 所属段名称 */
	private String depotName;
	
	/** 所属车间id */
	private Integer workshopId;
	
	/** 所属车间名称 */
	private String workshopName;
	
	/** 所属班组id */
	private Integer teamId;
	
	/** 所属班组名称 */
	private String teamName;
	
	/** 二维码 */
	private String genCode;
	
	/** 二维码图片URL */
	private String genImageUrl;
	
	/** 二维码图片path */
	private String genImagePath;
	
	/** 使用标识
	 * 0:在库，1：借出 */
	private Integer useFlag;
	
	/** 当前使用、归还工具的人 */
	private Integer lastestUserId;
	
	/** 当前使用、归还工具的人 */
	private String lastestUserName;
	
	/** 当前使用、归还工具的时间 */
	private String lastestDateTime;
	
	/** 生产厂家 */
	private String factory;
	
	/** 品牌 */
	private String brand;
	
	/** 型号 */
	private String model;
	
	/** pad名称 */
	private String padName;

	public String getFormatId() {
		formatId= Tools.formatString(id, 8);
		return formatId;
	}
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

	public Integer getBasicId() {
		return basicId;
	}

	public void setBasicId(Integer basicId) {
		this.basicId = basicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenCode() {
		return genCode;
	}

	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}

	public String getGenImageUrl() {
		return genImageUrl;
	}

	public void setGenImageUrl(String genImageUrl) {
		this.genImageUrl = genImageUrl;
	}

	public String getGenImagePath() {
		return genImagePath;
	}

	public void setGenImagePath(String genImagePath) {
		this.genImagePath = genImagePath;
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public Integer getLastestUserId() {
		return lastestUserId;
	}

	public void setLastestUserId(Integer lastestUserId) {
		this.lastestUserId = lastestUserId;
	}

	public String getLastestUserName() {
		return lastestUserName;
	}

	public void setLastestUserName(String lastestUserName) {
		this.lastestUserName = lastestUserName;
	}

	public String getLastestDateTime() {
		if (lastestDateTime!=null) {
			lastestDateTime = lastestDateTime.substring(0, 4)
								+"/"+lastestDateTime.substring(4,6)
								+"/"+lastestDateTime.substring(6,8)
								+ " "+lastestDateTime.substring(8,10)
								+":"+lastestDateTime.substring(10,12)
								+":"+lastestDateTime.substring(12,14);
		}
		return lastestDateTime;
	}

	public void setLastestDateTime(String lastestDateTime) {
		this.lastestDateTime = lastestDateTime;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPadName() {
		return padName;
	}

	public void setPadName(String padName) {
		this.padName = padName;
	}

	
	
}