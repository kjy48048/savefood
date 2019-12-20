package com.ward.savefood;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			return "index";
		}
		
		return "redirect:/view/member/login";
	}
}
