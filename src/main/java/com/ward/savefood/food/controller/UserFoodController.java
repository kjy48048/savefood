package com.ward.savefood.food.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ward.savefood.admin.service.CategoryService;
import com.ward.savefood.food.service.UserFoodService;


@Controller
@RequestMapping("/view/food")
public class UserFoodController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserFoodService foodService;
	
	
	@GetMapping("")
	public String index(Model model, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
			ArrayList<Map<String, Object>> categoryList = categoryService.getCategoryList();
			ArrayList<ArrayList<Map<String, Object>>> foodListList = foodService.getAllFoodList();
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("foodListList",foodListList);
			
			return "food/index";
		}
		
		return "redirect:/view/member/login";
	}
}
