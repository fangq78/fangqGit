package com.luysoft.wlfedu.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.luysoft.wlfedu.constants.SessionKey;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("preHandle." + request.getRequestURL());

		HttpSession session = request.getSession(true);
		if (null == session.getAttribute(SessionKey.USER)) {

			logger.info("session timeout.");

			// 判断是否为Ajax请求
			String header = request.getHeader("X-Requested-With");
			if(null != header && header.toLowerCase().equals("xmlhttprequest")) {
				
				response.setCharacterEncoding("UTF-8");  
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = null;
				out = response.getWriter();
				JSONObject json = JSONObject.parseObject("{status: false, timeout: true}");
				out.append(json.toJSONString());  
				out.close();
			}
			else
			{
				// 学员登录
				response.sendRedirect(request.getContextPath() + "/login");
			}
			return false;
		}
		return true;
	}
}
