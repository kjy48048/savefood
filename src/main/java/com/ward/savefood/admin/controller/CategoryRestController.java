package com.ward.savefood.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ward.savefood.common.controller.GeneralController;
import com.ward.savefood.admin.model.InsertCategoryRequest;
import com.ward.savefood.admin.model.UpdateCategoryRequest;
import com.ward.savefood.admin.service.CategoryService;;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryRestController extends GeneralController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/reg")
	public ResponseEntity<?> insertCategory(@Valid @RequestBody InsertCategoryRequest categoryRequest, BindingResult bindingResult) {
		logger.info("CategoryRestController insertCategory : "+ categoryRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return categoryService.insertCategory(categoryRequest);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest categoryRequest, BindingResult bindingResult) {
		logger.info("CategoryRestController updateCategory : "+ categoryRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return categoryService.updateCategory(categoryRequest);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteCategory(@Valid @RequestBody List<UpdateCategoryRequest> categoryRequest, BindingResult bindingResult) {
		logger.info("CategoryRestController updateCategory : "+ categoryRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return categoryService.deleteCategory(categoryRequest);
	}
	
	@PostMapping("/check")
	@ResponseBody
	public ArrayList<Map<String, Object>> selectInsertInfo(@Valid @RequestBody InsertCategoryRequest categoryRequest) {
		
		return categoryService.selectInsertInfo(categoryRequest);
	}
	
//	@PostMapping("/users")
//	public ResponseEntity<?> joinUser(@Valid @RequestBody MemberJoinRequest memberRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController joinUser : "+memberRequest.toString());
//		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return memberService.joinUser(memberRequest);
//	}
	
//	@PostMapping("/users")
//	public ResponseEntity<?> joinUser(@Valid @RequestBody MemberJoinRequest memberRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController joinUser : "+memberRequest.toString());
//		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return memberService.joinUser(memberRequest);
//	}
//	
//	@PostMapping("/authkey")
//	public ResponseEntity<?> createAuthKey(@Valid @RequestBody CreateAuthKeyRequest createAuthKeyRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController createAuthKey : "+createAuthKeyRequest.toString());
//		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return memberService.createAuthKey(createAuthKeyRequest);
//	}
//	
//	@PostMapping("/auth")
//	public ResponseEntity<?> telegramAuth(@Valid @RequestBody TelegramAuthRequest telegramAuthRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController telegramAuthRequest : "+telegramAuthRequest.toString());
//		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return memberService.telegramAuth(telegramAuthRequest);
//	}
//	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController LoginUserRequest : "+loginUserRequest.toString());
//		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return memberService.loginUser(loginUserRequest, session);
//	}
//	
//	@PostMapping("/logout")
//	public ResponseEntity<?> logoutUser(LogoutUserRequest logoutUserRequest, BindingResult bindingResult) {
//		logger.info("MemberRestController LogoutUserRequest : "+logoutUserRequest.toString());
//		
////		if(bindingResult.hasErrors()) {
////			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////		}
//		
//		return memberService.logoutUser(logoutUserRequest, session);
//	}
}
