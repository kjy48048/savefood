package com.ward.savefood.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ward.savefood.admin.service.FoodService;
import com.ward.savefood.member.service.MemberService;

@Controller
@RequestMapping("/view/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private FoodService foodService;
	
	@GetMapping("")
	public String admin(Model model, HttpSession session) throws Exception {
////		if(session.getAttribute("loginInfo") != null) {
//		
			model.addAttribute("role", memberService.getMemberCount());
			model.addAttribute("food", foodService.getAllFoodList());
			return "admin/index";
//		}
//		
//		return "redirect:/view/member/login";
	}
}
