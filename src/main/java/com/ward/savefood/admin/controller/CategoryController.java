package com.ward.savefood.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ward.savefood.admin.service.CategoryService;
import com.ward.savefood.admin.service.FoodService;

@Controller
@RequestMapping("/view/admin/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FoodService foodService;
	
	@GetMapping("")
	public String category(Model model, HttpSession session) throws Exception {
		
			model.addAttribute("categoryList", categoryService.getCategoryList());
		
			return "admin/category/index";
//		}
//		
//		return "redirect:/view/member/login";
	}
	
	@GetMapping("/food")
	public String food(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		
		String categorySeq = request.getParameter("category");
		model.addAttribute("categorySeq", Integer.parseInt(categorySeq));
		model.addAttribute("foodList", foodService.getFoodList(categorySeq));
//		if(session.getAttribute("loginInfo") != null) {
			return "admin/category/food";
	}
}
