package com.luysoft.wlfedu.service;

import java.util.List;

import com.luysoft.wlfedu.dto.Depot;

/**
 * 
 * 段Service
 *
 */
public interface DepotService {

	/**
	 * 查询段列表
	 * 
	 * @return 段列表
	 */
	List<Depot> list();

	/**
	 * 删除段
	 * 
	 * @param id 段Id
	 */
	void delete(Integer id);

	/**
	 * 登录段
	 * 
	 * @param depot 段
	 * @param userId 系统用户Id
	 * @return 0: 登录成功, 1: 段编码重复
	 */
	int add(Depot depot, Integer userId);

	/**
	 * 查询段
	 * 
	 * @param id 段Id
	 * @return 段
	 */
	Depot select(Integer id);

	/**
	 * 修改段
	 * 
	 * @param depot 段
	 * @param userId 系统用户Id
	 */
	void edit(Depot depot, Integer userId);
}
