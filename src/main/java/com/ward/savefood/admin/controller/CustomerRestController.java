package com.ward.savefood.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ward.savefood.common.controller.GeneralController;
import com.ward.savefood.member.service.MemberService;
import com.ward.savefood.admin.model.UpdateCustomerRequest;
import com.ward.savefood.admin.service.CustomerService;;

@RestController
@RequestMapping("/api/admin/customer")
public class CustomerRestController extends GeneralController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody List<UpdateCustomerRequest> customerRequest, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("CustomerRestController updateCustomer : "+ customerRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		boolean isEditable = false;
		int memberRole = memberService.loginUserRole(request);
		if(memberRole == 1) {
			isEditable = true;
		}
		
		return customerService.updateCustomer(customerRequest, isEditable);
	}
	
}
