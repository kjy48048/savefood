package com.ward.savefood.food.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ward.savefood.admin.dao.FoodDao;
import com.ward.savefood.admin.model.InsertCategoryRequest;
import com.ward.savefood.admin.model.InsertFoodRequest;
import com.ward.savefood.admin.model.UpdateCategoryRequest;
import com.ward.savefood.admin.model.UpdateFoodRequest;
import com.ward.savefood.food.dao.UserFoodDao;
import com.ward.savefood.food.model.InsertUserFoodRequest;
import com.ward.savefood.food.model.SelectFoodRequest;

@Service
public class UserFoodService {
	
	@Autowired
	private UserFoodDao foodDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
	

	public ResponseEntity<?> insertFoodAuto(InsertUserFoodRequest request) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String memberSeq = request.getMemberSeq();
			int saveplaceSeq = request.getSaveplaceSeq();
			int foodSeq = request.getFoodSeq();
			
			if(StringUtils.isEmpty(memberSeq) || saveplaceSeq == 0 || foodSeq == 0) {
				return new ResponseEntity<>("input food insert info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> selectFoodInfo = foodDao.selectFood(foodSeq);
			Map<String, Object> insertUserFood = new HashMap<String, Object>();
			int foodExpiDate = (int)selectFoodInfo.get("food_expi_date");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, foodExpiDate);
			
			insertUserFood.put("memberSeq", memberSeq);
			insertUserFood.put("saveplaceSeq", saveplaceSeq);
			insertUserFood.put("foodSeq", foodSeq);
			insertUserFood.put("savefoodName", (String)selectFoodInfo.get("food_name"));
			insertUserFood.put("savefoodQuantity", 0);
			insertUserFood.put("savefoodExpiDate", cal.getTime());
			
			int insertResult = foodDao.insertUserFood(insertUserFood);
			
			if(insertResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("insert complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to regist category", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> getFoodList(SelectFoodRequest foodRequest) {	
		try {	

			Map<String, Object> selectFood = new HashMap<>();
			selectFood.put("categorySeq", foodRequest.getCategorySeq());
			ArrayList<Map<String, Object>> selectResult = foodDao.getFoodList(selectFood);
			
			if(selectResult.size() > 0) {
				ArrayList<Map<String, Object>> foodList = new ArrayList<>();
				ArrayList<ArrayList<Map<String, Object>>> foodListList = new ArrayList<>();
				
				for(int i = 0; i<selectResult.size(); i++) {
					if(i == selectResult.size()-1 || selectResult.get(i).get("category_seq") != selectResult.get(i+1).get("category_seq"))
					{
						foodList.add(selectResult.get(i));
						@SuppressWarnings("unchecked")
						ArrayList<Map<String, Object>> temp = (ArrayList<Map<String, Object>>) foodList.clone();
						foodListList.add(temp);
						foodList.clear();
					}
					else {
						foodList.add(selectResult.get(i));
					}
				}
				
				return new ResponseEntity<>(foodListList, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("no select result", HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("fail to select food list", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> searchFoodList(SelectFoodRequest foodRequest) {	
		try {	

			Map<String, Object> selectFood = new HashMap<>();
			selectFood.put("searchText", foodRequest.getSearchText());
			ArrayList<Map<String, Object>> selectResult = foodDao.searchFoodList(selectFood);

			if(selectResult.size() > 0) {
				ArrayList<Map<String, Object>> foodList = new ArrayList<>();
				ArrayList<ArrayList<Map<String, Object>>> foodListList = new ArrayList<>();

				for(int i = 0; i<selectResult.size(); i++) {
					if(i == selectResult.size()-1 || selectResult.get(i).get("category_seq") != selectResult.get(i+1).get("category_seq"))
					{
						foodList.add(selectResult.get(i));
						@SuppressWarnings("unchecked")
						ArrayList<Map<String, Object>> temp = (ArrayList<Map<String, Object>>) foodList.clone();
						foodListList.add(temp);
						foodList.clear();
					}
					else {
						foodList.add(selectResult.get(i));
					}
				}

				return new ResponseEntity<>(foodListList, HttpStatus.OK);
			}

			return new ResponseEntity<>("no select result", HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("fail to select food list", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ArrayList<ArrayList<Map<String, Object>>> getAllFoodList() {	
		try {	

			ArrayList<Map<String, Object>> selectResult = foodDao.getAllFoodList();
			ArrayList<Map<String, Object>> foodList = new ArrayList<>();
			ArrayList<ArrayList<Map<String, Object>>> foodListList = new ArrayList<>();
			
			for(int i = 0; i<selectResult.size(); i++) {
				if(i == selectResult.size()-1 || selectResult.get(i).get("category_seq") != selectResult.get(i+1).get("category_seq"))
				{
					foodList.add(selectResult.get(i));
					@SuppressWarnings("unchecked")
					ArrayList<Map<String, Object>> temp = (ArrayList<Map<String, Object>>) foodList.clone();
					foodListList.add(temp);
					foodList.clear();
				}
				else {
					foodList.add(selectResult.get(i));
				}
			}
			
			return foodListList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
