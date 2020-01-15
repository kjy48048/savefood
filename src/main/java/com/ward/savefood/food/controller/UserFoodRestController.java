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
import com.ward.savefood.food.model.DeleteUserFoodRequest;
import com.ward.savefood.food.model.InsertUserFoodRequest;
import com.ward.savefood.food.model.SelectFoodRequest;
import com.ward.savefood.food.model.SelectSavefoodRequest;
import com.ward.savefood.food.model.UpdateUserFoodRequest;
import com.ward.savefood.food.service.UserFoodService;
import com.ward.savefood.fridge.service.FridgeService;

@RestController
@RequestMapping("/api/food")
public class UserFoodRestController extends GeneralController {
	
	@Autowired
	private UserFoodService foodService;
	@Autowired
	private FridgeService fridgeService;
	
	@PostMapping("/list")
	public ResponseEntity<?> getFoodList(@Valid @RequestBody SelectFoodRequest foodRequest, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("userFoodRestController getFoodList : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.getFoodList(foodRequest);
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> searchFoodList(@Valid @RequestBody SelectFoodRequest foodRequest, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("userFoodRestController searchFoodList : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return foodService.searchFoodList(foodRequest);
	}
	
	@PostMapping("/info")
	public ResponseEntity<?> getFoodInfo(@Valid @RequestBody SelectFoodRequest foodRequest, BindingResult bindingResult, HttpServletRequest request) {
		logger.info("userFoodRestController getFoodInfo : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.getFood(foodRequest);
	}
	
	@PostMapping("/auto/reg")
	public ResponseEntity<?> insertFoodAuto(@Valid @RequestBody InsertUserFoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController insertFoodAuto : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.insertFoodAuto(foodRequest);
	}

	@PostMapping("/reg")
	public ResponseEntity<?> insertFood(@Valid @RequestBody InsertUserFoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController insertFood : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.insertFood(foodRequest);
	}
	

	@PostMapping("/savefood")
	public ResponseEntity<?> getSavefood(@Valid @RequestBody SelectSavefoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController selectSavefood : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.getSavefood(foodRequest);
	}
	
	@PostMapping("/savefood/update")
	public ResponseEntity<?> updateSavefood(@Valid @RequestBody UpdateUserFoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController updateSavefood : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.updateSavefood(foodRequest);
	}
	
	@PostMapping("/savefood/delete")
	public ResponseEntity<?> deleteSavefood(@Valid @RequestBody DeleteUserFoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController deleteSavefoodBach : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.deleteSavefood(foodRequest);
	}
	
	@PostMapping("/savefood/batchDelete")
	public ResponseEntity<?> deleteSavefoodBatch(@Valid @RequestBody List<DeleteUserFoodRequest> foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController deleteSavefoodBach : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		for(int i = 0; i < foodRequest.size(); i++) {
			String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
			foodRequest.get(i).setMemberSeq(memberSeq);
		}

		return foodService.deleteSavefoodBatch(foodRequest);
	}
	
	@PostMapping("/savefood/expidate")
	public ResponseEntity<?> getExpiDate(@Valid @RequestBody SelectSavefoodRequest foodRequest, BindingResult bindingResult, HttpSession session) {
		logger.info("userFoodRestController getExpiDate : "+ foodRequest.toString());

		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String memberSeq = fridgeService.getMemberSeq((String)session.getAttribute("loginInfo"));
		foodRequest.setMemberSeq(memberSeq);

		return foodService.getExpiDate(foodRequest);
	}
}
