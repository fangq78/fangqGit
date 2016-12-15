package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.WorkshopMapper;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.service.WorkshopService;



@Service
public class WorkshopServiceImpl implements WorkshopService {

	@Autowired
	private WorkshopMapper workshopMapper;

	@Override
	public List<Workshop> list(Workshop workshop) {

		// 查询车间列表
		return workshopMapper.selectAll(workshop);
	}

	@Transactional
	@Override
	public void delete(Integer id) {

		// 删除车间
		workshopMapper.delete(id);
	}

	@Transactional
	@Override
	public int add(Workshop workshop, Integer userId) {

		// 新增车间
		workshopMapper.insert(workshop, userId);

		return 0;
	}

	@Override
	public Workshop select(Integer id) {

		// 查询车间
		return workshopMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(Workshop workshop, Integer userId) {

		// 修改车间
		workshopMapper.update(workshop, userId);
	}

	@Override
	public void deleteByDepotId(Integer depotId) {
		// 删除车间
		workshopMapper.deleteByDepotId(depotId);
		
	}
}
