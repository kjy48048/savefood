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

import com.ward.savefood.admin.model.InsertFoodRequest;
import com.ward.savefood.admin.model.UpdateFoodRequest;
import com.ward.savefood.common.controller.GeneralController;
import com.ward.savefood.fridge.model.InsertSaveplaceRequest;
import com.ward.savefood.fridge.model.UpdateSaveplaceRequest;
import com.ward.savefood.fridge.service.FridgeService;
import com.ward.savefood.member.model.LoginUserRequest;

@RestController 
@RequestMapping("/api/fridge")
public class FridgeRestController extends GeneralController {
	
	@Autowired
	private FridgeService fridgeService;
	
	@PostMapping("/saveplace/reg")
	public ResponseEntity<?> insertSaveplace(@Valid @RequestBody InsertSaveplaceRequest saveplaceRequest, BindingResult bindingResult) {
		logger.info("FridgeRestController insertSaveplace : "+ saveplaceRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return fridgeService.insertSaveplace(saveplaceRequest);
	}
	
	@PostMapping("/saveplace/update")
	public ResponseEntity<?> updateSaveplace(@Valid @RequestBody UpdateSaveplaceRequest saveplaceRequest, BindingResult bindingResult){
		logger.info("FridgeRestController updateSaveplace  : " + saveplaceRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return fridgeService.updateSaveplace(saveplaceRequest);
	}
	
	@PostMapping("/saveplace/delete")
	public ResponseEntity<?> deleteSaveplace(@Valid @RequestBody UpdateSaveplaceRequest saveplaceRequest, BindingResult bindingResult){
		logger.info("FridgeRestController deleteSaveplace  : "+ saveplaceRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return fridgeService.deleteSaveplace(saveplaceRequest);		
	}
}
