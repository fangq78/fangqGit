package com.luysoft.wlfedu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PathConfig {

	private final String TEMP = "temp";					// 临时
	private final String COURSE = "course";				// 课程
	private final String COMMUNITY = "community";		// 社区院校
	private final String GRAPHIC = "article/graphic";	// 图文消息
	private final String PHOTOS = "article/photos";		// 相册
	private final String ADVERT = "advert";				// 广告
	private final String ITEM = "item";					// 礼品
	private final String UPLOAD = "upload";				// 富文本上传文件
	private final String DEVICE = "device";				// 工具

	private final String BASEURL = "/s/";			// 资源虚拟路径

	// 文件根目录
	private String pathRoot;

	/**
	 * 取得文件根目录。
	 * 
	 * @return 设置文件根目录
	 */
	public String getPathRoot() {
		return pathRoot;
	}

	/**
	 * 设置文件根目录。
	 * 
	 * @param pathRoot 文件根目录
	 */
	@Value("${app.path.root}")
	public void setPathRoot(String pathRoot) {
		String fix  = pathRoot.substring(pathRoot.length() - 1);
		this.pathRoot = "/".equals(fix) ? pathRoot : pathRoot + "/";
	}

	/**
	 * 取得虚拟路径根目录。
	 * 
	 * @return 虚拟路径根目录
	 */
	public String getBaseUrl() {
		return BASEURL;
	}

	/**
	 * 取得临时路径
	 * 
	 * @return 临时路径
	 */
	public String getTempPath() {
		return pathRoot + TEMP + "/";
	}

	/**
	 * 取得课程路径
	 * 
	 * @return 课程路径
	 */
	public String getCoursePath() {
		return pathRoot + COURSE + "/";
	}

	/**
	 * 取得工具路径
	 * 
	 * @return 工具路径
	 */
	public String getDevicePath() {
		return pathRoot + DEVICE + "/";
	}
	
	/**
	 * 取得社区院校路径
	 * 
	 * @return 社区院校路径
	 */
	public String getCommunityPath() {
		return pathRoot + COMMUNITY + "/";
	}

	/**
	 * 取得图文消息路径
	 * 
	 * @return 图文消息路径
	 */
	public String getGraphicPath() {
		return pathRoot + GRAPHIC + "/";
	}

	/**
	 * 取得相册路径
	 * 
	 * @return 相册路径
	 */
	public String getPhotosPath() {
		return pathRoot + PHOTOS + "/";
	}

	/**
	 * 取得广告路径
	 * 
	 * @return 广告路径
	 */
	public String getAdvertPath() {
		return pathRoot + ADVERT + "/";
	}

	/**
	 * 取得礼品路径
	 * 
	 * @return 礼品路径
	 */
	public String getItemPath() {
		return pathRoot + ITEM + "/";
	}

	/**
	 * 取得富文本上传文件路径
	 * 
	 * @return 富文本上传文件路径
	 */
	public String getUploadPath() {
		return pathRoot + UPLOAD + "/";
	}

	/**
	 * 取得临时文件URL
	 * 
	 * @return 临时文件URL
	 */
	public String getTempUrl() {
		return BASEURL + TEMP + "/";
	}

	/**
	 * 取得工具URL
	 * 
	 * @return 工具URL
	 */
	public String getDeviceUrl() {
		return BASEURL + DEVICE + "/";
	}
	
	/**
	 * 取得课程URL
	 * 
	 * @return 课程URL
	 */
	public String getCourseUrl() {
		return BASEURL + COURSE + "/";
	}

	/**
	 * 取得社区院校URL
	 * 
	 * @return 社区院校URL
	 */
	public String getCommunityUrl() {
		return BASEURL + COMMUNITY + "/";
	}

	/**
	 * 取得图文消息URL
	 * 
	 * @return 图文消息URL
	 */
	public String getGraphicUrl() {
		return BASEURL + GRAPHIC + "/";
	}

	/**
	 * 取得相册URL
	 * 
	 * @return 相册URL
	 */
	public String getPhotosUrl() {
		return BASEURL + PHOTOS + "/";
	}

	/**
	 * 取得广告URL
	 * 
	 * @return 广告URL
	 */
	public String getAdvertUrl() {
		return BASEURL + ADVERT + "/";
	}

	/**
	 * 取得礼品URL
	 * 
	 * @return 礼品URL
	 */
	public String getItemUrl() {
		return BASEURL + ITEM + "/";
	}

	/**
	 * 取得富文本上传文件URL
	 * 
	 * @return 富文本上传文件URL
	 */
	public String getUploadUrl() {
		return BASEURL + UPLOAD + "/";
	}
}
