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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ward.savefood.common.controller.GeneralController;
import com.ward.savefood.admin.model.InsertCategoryRequest;
import com.ward.savefood.admin.model.InsertFoodRequest;
import com.ward.savefood.admin.model.UpdateCategoryRequest;
import com.ward.savefood.admin.model.UpdateFoodRequest;
import com.ward.savefood.admin.service.CategoryService;
import com.ward.savefood.admin.service.FoodService;;

@RestController
@RequestMapping("/api/admin/food")
public class FoodRestController extends GeneralController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/reg")
	public ResponseEntity<?> insertFood(@Valid @RequestBody InsertFoodRequest foodRequest, BindingResult bindingResult) {
		logger.info("FoodRestController insertFood : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.insertFood(foodRequest);
	}
	
	@PostMapping("/check")
	@ResponseBody
	public ArrayList<Map<String, Object>> selectInsertInfo(@Valid @RequestBody InsertFoodRequest foodRequest) {
		
		return foodService.selectInsertInfo(foodRequest);
	}
	
	@PostMapping("/regImg")
	public ResponseEntity<?> updateFoodImg(MultipartHttpServletRequest request) {
		logger.info("FoodRestController updateFoodImg : "+ request.toString());
		
		String foodSeq = request.getParameter("foodSeq");
		MultipartFile mf = request.getFile("foodImg");
		
		return foodService.updateFoodImg(foodSeq, mf);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateFoodRequest foodRequest, BindingResult bindingResult) {
		logger.info("FoodRestController updateFood : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.updateFood(foodRequest);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteCategory(@Valid @RequestBody List<UpdateFoodRequest> foodRequest, BindingResult bindingResult) {
		logger.info("FoodRestController deleteFood  : "+ foodRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return foodService.deleteFood(foodRequest);
	}
//	
//	@PostMapping("/check")
//	@ResponseBody
//	public ArrayList<Map<String, Object>> selectInsertInfo(@Valid @RequestBody InsertCategoryRequest categoryRequest) {
//		
//		return categoryService.selectInsertInfo(categoryRequest);
//	}
}
