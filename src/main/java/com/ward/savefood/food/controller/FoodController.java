package com.ward.savefood.food.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/view/food")
public class FoodController {

	@GetMapping("")
	public String index(HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
			return "food/index";
		}
		
		return "redirect:/view/member/login";
	}
}
