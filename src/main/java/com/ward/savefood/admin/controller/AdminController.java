package com.ward.savefood.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/admin")
public class AdminController {

	@GetMapping("")
	public String admin(HttpSession session) throws Exception {
//		if(session.getAttribute("loginInfo") != null) {
			return "admin/index";
//		}
//		
//		return "redirect:/view/member/login";
	}
}
