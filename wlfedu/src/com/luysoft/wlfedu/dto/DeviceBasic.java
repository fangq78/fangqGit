package com.luysoft.wlfedu.dto;

/**
 * 工具基本信息
 */
public class DeviceBasic {

	/** id */
	private Integer id;

	/** 物资编码 */
	private String code;
	
	/** 物资编码名称 */
	private String codeName;
	
	/** 生产厂家 */
	private String factory;
	
	/** 品牌 */
	private String brand;
	
	/** 型号 */
    private String model;
	
    /** 工具数量 */
    private int count;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}