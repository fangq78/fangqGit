package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.TeamUserMapper;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.dto.TeamUser;
import com.luysoft.wlfedu.service.TeamUserService;


@Service
public class TeamUserServiceImpl implements TeamUserService {

	@Autowired
	private TeamUserMapper teamUserMapper;

	@Override
	public List<TeamUser> list(TeamUser conf) {
		return teamUserMapper.list(conf);
	}

	@Transactional
	@Override
	public void delete(Integer id, Integer userId) {
		teamUserMapper.delete(id, userId);
	}

	@Transactional
	@Override
	public int add(TeamUser user, Integer userId) {
		
		// 新增系统用户
		teamUserMapper.insert(user, userId);

		return 0;
	}

	@Override
	public TeamUser select(Integer id) {
		return teamUserMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(TeamUser user, Integer userId) {
		teamUserMapper.update(user, userId);
	}

	@Transactional
	@Override
	public void password(Integer id, String password, Integer userId) {
		teamUserMapper.updatePassword(id, password, userId);
	}
	
	@Override
	public int hasDuplicateName(String name) {
		TeamUser user = teamUserMapper.selectByName(name);
		return user==null?0:1;
	}

	@Override
	public void deleteByDepotId(Integer depotId) {
		teamUserMapper.deleteByDepotId(depotId);
		
	}

	@Override
	public void deleteByWorkshopId(Integer workshopId) {
		teamUserMapper.deleteByWorkshopId(workshopId);
		
	}

	@Override
	public void deleteByTeamId(Integer teamId) {
		teamUserMapper.deleteByTeamId(teamId);
		
	}

}
