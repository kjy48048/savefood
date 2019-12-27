package com.ward.savefood.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.ward.savefood.common.sender.Telegram;

public class Interceptor extends HandlerInterceptorAdapter {
	private final int SERVER_ERROR = 500;
	
	@Autowired
	private Telegram telegram;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		int status = response.getStatus();
		if(status >= SERVER_ERROR) {
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("statusCode", status);
			error.put("requestURI", request.getRequestURI());
			error.put("requestMethod", request.getMethod());
			if(ex != null) {
				error.put("error", ex.getMessage());
			}
			
			//telegram.sendError(new Gson().toJson(error));
		}
	}
}
