package com.ward.savefood.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/member")
public class MemberController {

	@GetMapping("/login")
	public String loginForm(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			return "redirect:home";
		}
		
		return "member/login";
	}
	
	@GetMapping("/home")
	public String home(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			return "index";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/join")
	public String joinForm(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			return "redirect:home";
		}
		
		return "member/join";
	}
}
