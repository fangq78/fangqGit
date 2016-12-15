package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.DepotMapper;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.service.DepotService;


@Service
public class DepotServiceImpl implements DepotService {

	@Autowired
	private DepotMapper depotMapper;

	@Override
	public List<Depot> list() {

		// 查询段列表
		return depotMapper.selectAll();
	}

	@Transactional
	@Override
	public void delete(Integer id) {

		// 删除段
		depotMapper.delete(id);
	}

	@Transactional
	@Override
	public int add(Depot depot, Integer userId) {

		// 新增段
		depotMapper.insert(depot, userId);

		return 0;
	}

	@Override
	public Depot select(Integer id) {

		// 查询段
		return depotMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(Depot depot, Integer userId) {

		// 修改段
		depotMapper.update(depot, userId);
	}
}
