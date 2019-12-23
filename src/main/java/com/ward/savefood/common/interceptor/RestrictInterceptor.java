package com.ward.savefood.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ward.savefood.member.dao.MemberDao;

public class RestrictInterceptor extends HandlerInterceptorAdapter {
	private final int SERVER_ERROR = 500;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("包府磊 其捞瘤 立辟");
		
		try {
			HttpSession session = request.getSession();
			Map<String, Object> loginUserResult = memberDao.loginUser((String)session.getAttribute("loginInfo"));
			if(loginUserResult != null) {
				if((Integer)loginUserResult.get("member_role_id") == 1 || (Integer)loginUserResult.get("member_role_id") == 2) {
					return true;
				}
				else {
					response.sendRedirect("/");
					return false;
				}
			}
			else {
				response.sendRedirect("/view/member/login");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	};
}
