package com.ward.savefood.food.controller;

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
import com.ward.savefood.fridge.service.FridgeService;


@Controller
@RequestMapping("/view/food")
public class UserFoodController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FridgeService fridgeService;
	
	@Autowired
	private UserFoodService foodService;
	
	
	@GetMapping("")
	public String index(Model model, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			String memberSeq = fridgeService.getMemberSeq((String) session.getAttribute("loginInfo"));
			ArrayList<Map<String, Object>> fridgeList = fridgeService.getFridgeList(memberSeq);
			int[] fridgeSeqList = new int[fridgeList.size()];
			
			for(int i = 0; i < fridgeList.size(); i++) {
				fridgeSeqList[i] = (int)fridgeList.get(i).get("fridge_seq");
			}
			
			ArrayList<Map<String, Object>> saveplaceList = fridgeService.getSaveplaceList(fridgeSeqList);
			ArrayList<Map<String, Object>> categoryList = categoryService.getCategoryList();
			ArrayList<ArrayList<Map<String, Object>>> foodListList = foodService.getAllFoodList();
			model.addAttribute("fridgeList", fridgeList);
			model.addAttribute("saveplaceList", saveplaceList);
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("foodListList",foodListList);
			
			return "food/index";
		}
		
		return "redirect:/view/member/login";
	}
}