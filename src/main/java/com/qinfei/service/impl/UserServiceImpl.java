package com.qinfei.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinfei.common.CmsUtils;
import com.qinfei.dao.UserMapper;
import com.qinfei.entity.User;
import com.qinfei.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.findUserByNamme(username);
	}

	@Override
	public int register(@Valid User user) {
		// TODO Auto-generated method stub
		String encryPwd = CmsUtils.encry(user.getPassword(),user.getUsername());
		user.setPassword(encryPwd);
		return mapper.add(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		user.setPassword(CmsUtils.encry(user.getPassword(),user.getUsername()));
		
		return mapper.findByPwd(user);
	}
	
}
