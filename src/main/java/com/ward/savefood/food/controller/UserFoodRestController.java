package com.ward.savefood.food.controller;

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
import com.ward.savefood.food.model.SelectFoodRequest;
import com.ward.savefood.food.service.UserFoodService;
import com.ward.savefood.member.service.MemberService;
import com.ward.savefood.admin.model.UpdateCustomerRequest;
import com.ward.savefood.admin.service.CategoryService;
import com.ward.savefood.admin.service.CustomerService;;

@RestController
@RequestMapping("/api/food")
public class UserFoodRestController extends GeneralController {
	
	@Autowired
	private UserFoodService foodService;
	
	@PostMapping("/list")
	public ResponseEntity<?> getFoodList(@Valid @RequestBody SelectFoodRequest foodRequest, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("userFoodRestController getFoodList : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.getFoodList(foodRequest);
	}
	
}
