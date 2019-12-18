package com.qinfei.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.qinfei.common.Contantant;
import com.qinfei.entity.User;

/**
 * 拦截器
 * @author 秦飞
 *
 */
public class CmsInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		User loginUser = (User) request.getSession().getAttribute(Contantant.USER_KEY);
		if (loginUser==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		
		
		return true;
	}
}
