package com.ward.savefood.food.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
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
			int storageCode = request.getStorageCode();
			int foodSeq = request.getFoodSeq();
			String savefoodName = request.getSavefoodName();
			String savefoodQuantity = request.getSavefoodQuantity();
			Date savefoodExpiDate = request.getSavefoodExpiDate();
			
			if(StringUtils.isEmpty(memberSeq) || saveplaceSeq == 0 || foodSeq == 0) {
				return new ResponseEntity<>("input food insert info", HttpStatus.NO_CONTENT);
			}
			Map<String, Object> selectFood = new HashMap<>();
			selectFood.put("storageCode", storageCode);
			selectFood.put("foodSeq", foodSeq);
			Map<String, Object> selectFoodInfo = foodDao.selectFood(selectFood);
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
			int storageCode = request.getStorageCode();
			int foodSeq = request.getFoodSeq();
			
			if(StringUtils.isEmpty(memberSeq) || saveplaceSeq == 0 || foodSeq == 0) {
				return new ResponseEntity<>("input food insert info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> selectFood = new HashMap<>();
			selectFood.put("storageCode", storageCode);
			selectFood.put("foodSeq", foodSeq);
			Map<String, Object> selectFoodInfo = foodDao.selectFood(selectFood);
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
			int storageCode = foodRequest.getStorageCode();
			int foodSeq = foodRequest.getFoodSeq();		
			
			Map<String, Object> selectFood = new HashMap<>();
			selectFood.put("storageCode", storageCode);
			selectFood.put("foodSeq", foodSeq);
			
			Map<String, Object> food = foodDao.selectFood(selectFood);
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
				int storageCode = (int)savefoodList.get(i).get("saveplace_storage_code");
				int foodSeq = (int)savefoodList.get(i).get("food_seq");	
				
				Map<String, Object> selectFood = new HashMap<>();
				selectFood.put("storageCode", storageCode);
				selectFood.put("foodSeq", foodSeq);
				
				// ?�쌔?�옙 ?�쏙?�품?�쏙???�쏙?�占?�옙 ?�쏙?�占?�듸???�쏙?�占?�옙 get
				Map<String, Object> food = foodDao.selectFood(selectFood);
				int expiDate = (int)food.get("food_expi_date"); // ?�쌔?�옙 ?�쏙?�품?�쏙???�쏙?�占?�옙?�쏙???�쏙?�占?�옙 ?�쏙?�占?�옙 ?�占?�옙
				Date now = new Date();							// ?�쏙?�占?�날�?
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date userFoodExpiDate = new Date(((Timestamp)savefoodList.get(i).get("savefood_expi_date")).getTime()); // ?�쏙?�占?�옙 ?�쏙?�품?�쏙???�쏙?�占?�옙?�� ?�쏙?�占?�옙?�쏙?�占�?
				//long diff = now.getTime() - userFoodExpiDate.getTime();	// ?�쏙?�占?�날�?- ?�쏙?�占?�옙?�쏙?�품 ?�쏙?�占?�옙?�쏙?�占�?
				long diff = ((Date)savefoodList.get(i).get("savefood_expi_date")).getTime() - now.getTime();
				long diffDays = diff/(24 * 60 * 60 * 1000); // ?�싻몌옙?�쏙???�쏙?�占?�옙?�쏙???�쏙?�占?�옙 ?�쏙?�占?�몌???�쏙?�짜 ?�쏙?�占?�옙?�쏙???�쏙?�환
				long danger = expiDate/3;						 // ?�쏙?�占?�옙?�쏙?�占?�옙?�쏙???�쏙?�占?�울?�占?�옙 ?�쏙?�占?�듸???�쏙?�占?�옙?�쏙??1/3 ?�싱몌옙?�싱몌옙 ?�쏙?�占?�옙
				long normal = expiDate*2/3;				     // 1/3 ?�싱?�옙 2/3 ?�싱몌옙 ?�싱몌옙 ?�쏙?�占?�옙 ?�쏙???�싱?�옙?�쏙???�쏙?�占?�옙
				if(danger > 14) {								 // ?�쏙?�占?�옙 ?�쏙?�占?�옙?�쏙???�썩간占?�옙 2?�쏙???�십곤옙?�쏙?�占�??�쏙?�占?�옙 ?�썩간占?�옙 2?�쌍뤄옙 ?�쏙?�占?�옙
					danger = 14;
				}
				int risk = 0;
				if(diffDays < danger) {
					risk = 0;
				}
				else if(diffDays >= danger && diffDays < normal) {
					risk = 1;
				}
				else {
					risk = 2;
				}
				savefoodList.get(i).put("savefood_remain_day", diffDays);
				savefoodList.get(i).put("savefood_risk", risk);
				
			}
			
			return savefoodList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

