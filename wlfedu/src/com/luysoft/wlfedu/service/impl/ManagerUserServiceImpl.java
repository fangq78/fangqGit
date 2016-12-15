package com.luysoft.wlfedu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.ManagerUserMapper;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.service.ManagerUserService;

@Service
public class ManagerUserServiceImpl implements ManagerUserService {

	@Autowired
	private ManagerUserMapper managerUserMapper;

	@Override
	public List<ManagerUser> list(ManagerUser conf) {
		return managerUserMapper.list(conf);
	}

	@Override
	public ManagerUser selectManagerUser(String name, String password) {

		ManagerUser conf = new ManagerUser();
		conf.setName(name);
		conf.setPassword(password);

		return managerUserMapper.selectByNamePwd(conf);
	}

	@Transactional
	@Override
	public void delete(Integer id, Integer userId) {
		managerUserMapper.delete(id, userId);
	}

	@Transactional
	@Override
	public int add(ManagerUser user, Integer userId) {

		// 查询用户名是否存在
		ManagerUser conf = new ManagerUser();
		conf.setName(user.getName());
		
		// 新增系统用户
		managerUserMapper.insert(user, userId);

		return 0;
	}

	@Override
	public ManagerUser select(Integer id) {
		return managerUserMapper.select(id);
	}

	@Transactional
	@Override
	public void edit(ManagerUser user, Integer userId) {
		managerUserMapper.update(user, userId);
	}

	@Transactional
	@Override
	public void password(Integer id, String password, Integer userId) {
		managerUserMapper.updatePassword(id, password, userId);
	}

	@Override
	public int hasDuplicateName(String name) {
		ManagerUser user = managerUserMapper.selectByName(name);
		return user==null?0:1;
	}

	@Override
	public void deleteByDepotId(Integer depotId) {
		managerUserMapper.deleteByDepotId(depotId);
		
	}

	@Override
	public void deleteByWorkshopId(Integer workshopId) {
		managerUserMapper.deleteByWorkshopId(workshopId);
		
	}

}
