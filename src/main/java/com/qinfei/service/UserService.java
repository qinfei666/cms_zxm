package com.qinfei.service;

import javax.validation.Valid;

import com.qinfei.entity.User;

public interface UserService {

	User getUserByUsername(String username);

	int register(@Valid User user);

	User login(User user);

}
