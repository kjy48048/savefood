package com.ward.savefood.fridge.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ward.savefood.fridge.service.FridgeService;

@Controller
@RequestMapping("/view/fridge")
public class FridgeController {

	@Autowired
	private FridgeService fridgeService;
	
	@GetMapping("")
	public String index(Model model, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
			return "fridge/index";
		}
		
		return "redirect:/view/member/login";
	}
	
	@GetMapping("/fridge")
	public String fridge(Model model, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
			model.addAttribute("storage", fridgeService.getStorage());
			
			return "fridge/fridge";
		}
		
		return "redirect:/view/member/login";
	}
	
	@GetMapping("/saveplace")
	public String saveplace(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
		
			return "fridge/saveplace";
		}
		
		return "redirect:/view/member/login";
	}
	
//	@GetMapping("/join")
//	public String joinForm(HttpSession session) throws Exception {
//		if(session.getAttribute("loginInfo") != null) {
//			return "redirect:home";
//		}
//		
//		return "member/join";
//	}
}
