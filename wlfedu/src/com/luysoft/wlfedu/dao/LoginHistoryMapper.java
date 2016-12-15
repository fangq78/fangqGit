package com.luysoft.wlfedu.dao;

import com.luysoft.wlfedu.dto.LoginHistory;

import org.apache.ibatis.annotations.Param;

public interface LoginHistoryMapper {

	/**
	 * 新增学员登录记录
	 * 
	 * @param record 学员登录记录
	 */
	void insert(@Param("record")LoginHistory record);
}