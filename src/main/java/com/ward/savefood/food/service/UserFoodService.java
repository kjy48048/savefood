package com.ward.savefood.food.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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
	
	public ResponseEntity<?> insertFood(InsertUserFoodRequest request) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String memberSeq = request.getMemberSeq();
			int saveplaceSeq = request.getSaveplaceSeq();
			int foodSeq = request.getFoodSeq();
			String savefoodName = request.getSavefoodName();
			String savefoodQuantity = request.getSavefoodQuantity();
			Date savefoodExpiDate = request.getSavefoodExpiDate();
			
			if(StringUtils.isEmpty(memberSeq) || saveplaceSeq == 0 || foodSeq == 0) {
				return new ResponseEntity<>("input food insert info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> selectFoodInfo = foodDao.selectFood(foodSeq);
			Map<String, Object> insertUserFood = new HashMap<String, Object>();
			if(savefoodExpiDate.getTime() == 0) {
				int foodExpiDate = (int)selectFoodInfo.get("food_expi_date");
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.DATE, foodExpiDate);
				insertUserFood.put("savefoodExpiDate", cal.getTime());
			}
			else {
				insertUserFood.put("savefoodExpiDate", savefoodExpiDate);
			}
			
			insertUserFood.put("memberSeq", memberSeq);
			insertUserFood.put("saveplaceSeq", saveplaceSeq);
			insertUserFood.put("foodSeq", foodSeq);
			if(StringUtils.isEmpty(savefoodName)) {
				insertUserFood.put("savefoodName", (String)selectFoodInfo.get("food_name"));
			}
			else {
				insertUserFood.put("savefoodName", savefoodName);
			}
			
			if(StringUtils.isEmpty(savefoodQuantity)) {
				insertUserFood.put("savefoodQuantity", 0);
			}
			else {
				insertUserFood.put("savefoodQuantity", savefoodQuantity);
			}
			
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
	
	public ResponseEntity<?> getFood(SelectFoodRequest foodRequest){
		
		try {			
			int foodSeq = foodRequest.getFoodSeq();		
			Map<String, Object> food = foodDao.selectFood(foodSeq);
			return new ResponseEntity<>(food, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail to select food", HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	public ArrayList<Map<String, Object>> calculateFoodRisk(ArrayList<Map<String, Object>> savefoodList) {	
		try {	
			for(int i = 0 ; i < savefoodList.size(); i++) {
				// 해당 식품에 대한 가이드 정보 get
				Map<String, Object> food = foodDao.selectFood((int)savefoodList.get(i).get("food_seq"));
				int expiDate = (int)food.get("food_expi_date"); // 해당 식품에 지정된 저장 기한 확인
				Date now = new Date();							// 현재날짜
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date userFoodExpiDate = transFormat.parse((String)savefoodList.get(i).get("savefood_expi_date")); // 유저 식품에 저장된 유통기한
				long diff = now.getTime() - userFoodExpiDate.getTime();	// 현재날짜 - 유저식품 유통기한
				int diffDays = (int) diff/(24 * 60 * 60 * 1000); // 밀리초 단위로 계산된 차이를 날짜 단위로 변환
				int danger = expiDate/3;						 // 남은기한이 어플에서 정해둔 기한의 1/3 미만이면 위험
				int normal = (expiDate/3)*2;				     // 1/3 이상 2/3 미만 이면 보통 그 이상은 안전
				if(danger > 14) {								 // 만약 위험한 기간이 2주 초과라면 위험 기간을 2주로 조정
					danger = 14;
				}
				int risk = 0;
				if(diffDays < danger) {
					risk = 0;
				}
				else if(diffDays >= danger || diffDays < normal) {
					risk = 1;
				}
				else {
					risk = 2;
				}
				
				savefoodList.get(i).put("savefood_risk", risk);
			}
			
			return savefoodList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
