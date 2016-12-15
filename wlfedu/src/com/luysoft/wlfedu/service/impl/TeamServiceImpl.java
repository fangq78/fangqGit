package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.TeamMapper;
import com.luysoft.wlfedu.dto.Team;
import com.luysoft.wlfedu.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamMapper teamMapper;

	@Override
	public List<Team> list(Team cond) {

		// 查询班组列表
		return teamMapper.selectAll(cond);
	}

	@Transactional
	@Override
	public void delete(Integer id) {

		// 删除班组
		teamMapper.delete(id);
	}

	@Transactional
	@Override
	public int add(Team team, Integer userId) {

		// 新增班组
		teamMapper.insert(team, userId);

		return 0;
	}

	@Override
	public Team select(Integer id) {

		// 查询班组
		return teamMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(Team team, Integer userId) {

		// 修改班组
		teamMapper.update(team, userId);
	}

	@Override
	public void deleteByWorkshopId(Integer workshopId) {
		teamMapper.deleteByWorkshopId(workshopId);
		
	}

	@Override
	public void deleteByDepotId(Integer depotId) {
		teamMapper.deleteByDepotId(depotId);
		
	}
}
