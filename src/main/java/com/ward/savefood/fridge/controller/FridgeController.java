package com.ward.savefood.fridge.controller;

import java.util.ArrayList;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	public String briefFridge(Model model, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {

			String memberId = session.getAttribute("loginInfo").toString();
			String memberSeq = fridgeService.getMemberSeq(memberId);
			
			ArrayList<Map<String, Object>> fridge = fridgeService.getFridgeList(memberSeq);
			if(fridge.size() != 0) {
				ArrayList<Map<String, Object>> saveplace = fridgeService.getSaveplaceList(fridge);
				model.addAttribute("savefoodList", fridgeService.getFridgeDashboard(saveplace));
				model.addAttribute("saveplaceList", saveplace);
			}
			model.addAttribute("fridgeList", fridge);

			return "fridge/index";
		}
		
		return "redirect:/view/member/login";
	}
	
	@GetMapping("/fridge")
	public String fridge(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
			String memberId = session.getAttribute("loginInfo").toString();
			String memberSeq = fridgeService.getMemberSeq(memberId);
			
			String fridgeSeq = request.getParameter("fridge");
			ArrayList<Map<String, Object>> fridge = fridgeService.getFridgeList(memberSeq);
			fridge.removeIf(n -> (int)n.get("fridge_seq") != Integer.parseInt(fridgeSeq));
			
			if(fridge.size() > 0) {
				model.addAttribute("storage", fridgeService.getStorage());
				ArrayList<Map<String, Object>> saveplaceList = fridgeService.getSaveplaceList(fridge);
				model.addAttribute("savefoodList", fridgeService.getSavefoods(saveplaceList));
				
				return "fridge/fridge";
			}	
			
			return "fridge";
		}
		
		return "redirect:/view/member/login";
	}
	
//	@GetMapping("/food")
//	public String food(Model model, HttpServletRequest request, HttpSession session) throws Exception {
//		
//		String categorySeq = request.getParameter("category");
//		model.addAttribute("categorySeq", Integer.parseInt(categorySeq));
//		model.addAttribute("foodList", foodService.getFoodList(categorySeq));
////		if(session.getAttribute("loginInfo") != null) {
//			return "admin/category/food";
//	}	
	
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
	
	@GetMapping("/management")
	public String management(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		if(session.getAttribute("loginInfo") != null) {
			
//			String fridgeSeq = request.getParameter("fridge");
//			
//			model.addAttribute("storage", fridgeService.getStorage());
//			model.addAttribute("fridgeSeq", Integer.parseInt(fridgeSeq));
//			model.addAttribute("saveplaceList", fridgeService.getSaveplaceList(fridgeSeq));

			return "fridge/management";
		}
		
		return "redirect:/view/member/login";
	}
}
