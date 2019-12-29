package com.ward.savefood.fridge.controller;

import java.util.List;
import java.util.Map;

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
import com.ward.savefood.fridge.service.FridgeService;
import com.ward.savefood.member.model.LoginUserRequest;

@RestController
@RequestMapping("/api/fridge")
public class FridgeRestController extends GeneralController {
	
	@Autowired
	private FridgeService fridgeService;
	
}
