package com.qinfei.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.qinfei.entity.User;

public interface UserMapper {

	@Select("select id,username,password from cms_user "
			+ "where username=#{value} limit 1")
	User findUserByNamme(String username);

	@Insert("insert into cms_user(username,password,locked,create_time,score,role)"
			+ " values(#{username},#{password},0,now(),0,0)")
	int add(@Valid User user);

	@Select("select * from cms_user where username=#{username} and password=#{password} limit 1")
	User findByPwd(User user);

}
