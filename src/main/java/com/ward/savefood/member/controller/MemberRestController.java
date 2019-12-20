package com.ward.savefood.member.controller;

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
import com.ward.savefood.member.model.CreateAuthKeyRequest;
import com.ward.savefood.member.model.LoginUserRequest;
import com.ward.savefood.member.model.LogoutUserRequest;
import com.ward.savefood.member.model.MemberJoinRequest;
import com.ward.savefood.member.model.TelegramAuthRequest;
import com.ward.savefood.member.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberRestController extends GeneralController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/users")
	public ResponseEntity<?> joinUser(@Valid @RequestBody MemberJoinRequest memberRequest, BindingResult bindingResult) {
		logger.info("MemberRestController joinUser : "+memberRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return memberService.joinUser(memberRequest);
	}
	
	@PostMapping("/authkey")
	public ResponseEntity<?> createAuthKey(@Valid @RequestBody CreateAuthKeyRequest createAuthKeyRequest, BindingResult bindingResult) {
		logger.info("MemberRestController createAuthKey : "+createAuthKeyRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return memberService.createAuthKey(createAuthKeyRequest);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> telegramAuth(@Valid @RequestBody TelegramAuthRequest telegramAuthRequest, BindingResult bindingResult) {
		logger.info("MemberRestController telegramAuthRequest : "+telegramAuthRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return memberService.telegramAuth(telegramAuthRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest, BindingResult bindingResult) {
		logger.info("MemberRestController LoginUserRequest : "+loginUserRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return memberService.loginUser(loginUserRequest, session);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(LogoutUserRequest logoutUserRequest, BindingResult bindingResult) {
		logger.info("MemberRestController LogoutUserRequest : "+logoutUserRequest.toString());
		
//		if(bindingResult.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		
		return memberService.logoutUser(logoutUserRequest, session);
	}
}
