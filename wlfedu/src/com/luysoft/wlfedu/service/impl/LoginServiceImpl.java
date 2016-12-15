package com.luysoft.wlfedu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luysoft.wlfedu.dao.LoginHistoryMapper;
import com.luysoft.wlfedu.dto.LoginHistory;
import com.luysoft.wlfedu.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginHistoryMapper loginHistoryMapper;

	@Transactional
	@Override
	public void history(Integer userId, String ip) {
		// 新增学员登录记录
		LoginHistory histroy = new LoginHistory();
		histroy.setUserId(userId);
		histroy.setIp(ip);
		loginHistoryMapper.insert(histroy);
	}

}
