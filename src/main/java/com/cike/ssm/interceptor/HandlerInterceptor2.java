package com.cike.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor2 implements HandlerInterceptor{
	//在执行handler之前执行
	//用于用户认证校验，权限校验
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerInterceptor2 preHandle");
		return true;
	}
	//在执行handler返回ModelAndView之前执行
	//如果需要向页面提供一些公用的数据或配置一些视图信息，使用此方法从ModelAndView入手
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor2 postHandle");
	}
	//执行handler之后执行此方法
	//系统统一异常处理，进行方法执行性能监控，实现统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor2 afterCompletion");
	}

}
