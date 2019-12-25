package com.ward.savefood.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ward.savefood.admin.service.CustomerService;
import com.ward.savefood.member.dao.MemberDao;
import com.ward.savefood.member.service.MemberService;

@Controller
@RequestMapping("/view/admin/customer")
public class CustomerController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("")
	public String customer(Model model,HttpServletRequest request) throws Exception {
		
		int page = request.getAttribute("page") == null ? 0 : (int)request.getAttribute("page");
		int order = request.getAttribute("order") == null ? 0 :(int)request.getAttribute("order");
		int userRole = memberService.loginUserRole(request);
		boolean isEditable = false;
		if(userRole == 1) {
			isEditable = true;
			model.addAttribute("role", customerService.selectRoleList());
		};
			model.addAttribute("isEditable", isEditable);
			model.addAttribute("maxPage", customerService.selectPager());
			model.addAttribute("status", customerService.selectStatusList());
			model.addAttribute("memberList", customerService.selectMemberList(page, order));
		
		return "admin/customer/index";
	}
}
