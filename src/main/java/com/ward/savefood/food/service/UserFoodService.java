package com.ward.savefood.food.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
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
import com.ward.savefood.food.model.DeleteUserFoodRequest;
import com.ward.savefood.food.model.InsertUserFoodRequest;
import com.ward.savefood.food.model.SelectFoodRequest;
import com.ward.savefood.food.model.SelectSavefoodRequest;
import com.ward.savefood.food.model.UpdateUserFoodRequest;

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
	
	public ResponseEntity<?> updateSavefood(UpdateUserFoodRequest request) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int savefoodSeq = request.getSavefoodSeq();
			String memberSeq = request.getMemberSeq();
			int saveplaceSeq = request.getSaveplaceSeq();
			String savefoodName = request.getSavefoodName();
			String savefoodQuantity = request.getSavefoodQuantity();
			Date savefoodExpiDate = request.getSavefoodExpiDate();
			
			if(StringUtils.isEmpty(memberSeq)|| savefoodSeq == 0 || saveplaceSeq == 0 || StringUtils.isEmpty(savefoodExpiDate)||StringUtils.isEmpty(savefoodName)) {
				return new ResponseEntity<>("input food update info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> updateUserFood = new HashMap<String, Object>();
			
			updateUserFood.put("savefoodSeq", savefoodSeq);
			updateUserFood.put("savefoodExpiDate", savefoodExpiDate);
			updateUserFood.put("memberSeq", memberSeq);
			updateUserFood.put("saveplaceSeq", saveplaceSeq);
			updateUserFood.put("savefoodName", savefoodName);
			
			if(StringUtils.isEmpty(savefoodQuantity)) {
				updateUserFood.put("savefoodQuantity", 0);
			}
			else {
				updateUserFood.put("savefoodQuantity", savefoodQuantity);
			}
			
			int updateResult = foodDao.updateSavefood(updateUserFood);
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("update complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update user food", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> deleteSavefood(DeleteUserFoodRequest request) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			
			int savefoodSeq = request.getSavefoodSeq();
			String memberSeq = request.getMemberSeq();
			
			if(StringUtils.isEmpty(memberSeq)|| savefoodSeq == 0) {
				return new ResponseEntity<>("input food delete info", HttpStatus.NO_CONTENT);
			}
		
			Map<String, Object> deleteUserFood = new HashMap<String, Object>();
		
			deleteUserFood.put("savefoodSeq", savefoodSeq);
			deleteUserFood.put("memberSeq", memberSeq);
		
			int updateResult = foodDao.deleteSavefood(deleteUserFood);
		
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("delete complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to delete user food", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> deleteSavefoodBatch(List<DeleteUserFoodRequest> request) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int updateResult = 0;
			
			for(DeleteUserFoodRequest savefood : request) {
				int savefoodSeq = savefood.getSavefoodSeq();
				String memberSeq = savefood.getMemberSeq();
				
				if(StringUtils.isEmpty(memberSeq)|| savefoodSeq == 0) {
					return new ResponseEntity<>("input food delete info", HttpStatus.NO_CONTENT);
				}
			
				Map<String, Object> deleteUserFood = new HashMap<String, Object>();
			
				deleteUserFood.put("savefoodSeq", savefoodSeq);
				deleteUserFood.put("memberSeq", memberSeq);
			
				updateResult += foodDao.deleteSavefood(deleteUserFood);
			}
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("delete complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to delete user food", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> getSavefood(SelectSavefoodRequest foodRequest){
		
		try {			
			int savefoodSeq = foodRequest.getSavefoodSeq();
			String memberSeq = foodRequest.getMemberSeq();		
			
			if(StringUtils.isEmpty(memberSeq) || savefoodSeq == 0) {
				return new ResponseEntity<>("input food select info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> selectSavefood = new HashMap<>();
			selectSavefood.put("savefoodSeq", savefoodSeq);
			selectSavefood.put("memberSeq", memberSeq);
			Map<String, Object> savefood = foodDao.getSavefood(selectSavefood);
			return new ResponseEntity<>(savefood, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail to select food", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> getExpiDate(SelectSavefoodRequest foodRequest){
		
		try {			
			int savefoodSeq = foodRequest.getSavefoodSeq();
			int storageCode = foodRequest.getStorageCode();
			String memberSeq = foodRequest.getMemberSeq();		
			
			if(StringUtils.isEmpty(memberSeq) || savefoodSeq == 0) {
				return new ResponseEntity<>("input food select info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> selectSavefood = new HashMap<>();
			selectSavefood.put("savefoodSeq", savefoodSeq);
			selectSavefood.put("memberSeq", memberSeq);
			selectSavefood.put("storageCode", storageCode);
			Map<String, Object> savefood = foodDao.getExpiDate(selectSavefood);
			int remainDay = 0;
			int orgStorageDate = 0;
			int changeStorageDate = 0;
			if((int)savefood.get("saveplace_storage_code") == 1) {
				orgStorageDate = (int)savefood.get("food_expi_date_frozen");
			}
			else if((int)savefood.get("saveplace_storage_code") == 2) {
				orgStorageDate = (int)savefood.get("food_expi_date");
			}
			else if((int)savefood.get("saveplace_storage_code") == 3) {
				orgStorageDate = (int)savefood.get("food_expi_date_room");
			}
			
			if(storageCode == 1) {
				changeStorageDate = (int)savefood.get("food_expi_date_frozen");
			}
			else if(storageCode == 2) {
				changeStorageDate = (int)savefood.get("food_expi_date");
			}
			else if(storageCode == 3) {
				changeStorageDate = (int)savefood.get("food_expi_date_room");
			}
			
			if( orgStorageDate == 0 || changeStorageDate == 0) {
				remainDay = (int)savefood.get("savefood_remain_day");
			}
			else {
				remainDay = (int)savefood.get("savefood_remain_day")*changeStorageDate/orgStorageDate;
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, remainDay);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String expiDate = df.format(cal.getTime());
			
			return new ResponseEntity<>(expiDate, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail to select food", HttpStatus.INTERNAL_SERVER_ERROR);
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
				
				// ?†Ïåî?êÏòô ?†Ïèô?ôÌíà?†Ïèô???†Ïèô?ôÂç†?ôÏòô ?†Ïèô?ôÂç†?±Îì∏???†Ïèô?ôÂç†?ôÏòô get
				Map<String, Object> food = foodDao.selectFood(selectFood);
				int expiDate = (int)food.get("food_expi_date"); // ?†Ïåî?êÏòô ?†Ïèô?ôÌíà?†Ïèô???†Ïèô?ôÂç†?ôÏòô?†Ïèô???†Ïèô?ôÂç†?ôÏòô ?†Ïèô?ôÂç†?ôÏòô ?ïÂç†?ôÏòô
				Date now = new Date();							// ?†Ïèô?ôÂç†?πÎÇ†Ïß?
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date userFoodExpiDate = new Date(((Timestamp)savefoodList.get(i).get("savefood_expi_date")).getTime()); // ?†Ïèô?ôÂç†?ôÏòô ?†Ïèô?ôÌíà?†Ïèô???†Ïèô?ôÂç†?ôÏòô?†ÔøΩ ?†Ïèô?ôÂç†?ôÏòô?†Ïèô?ôÂç†Ôø?
				//long diff = now.getTime() - userFoodExpiDate.getTime();	// ?†Ïèô?ôÂç†?πÎÇ†Ïß?- ?†Ïèô?ôÂç†?ôÏòô?†Ïèô?ôÌíà ?†Ïèô?ôÂç†?ôÏòô?†Ïèô?ôÂç†Ôø?
				long diff = ((Date)savefoodList.get(i).get("savefood_expi_date")).getTime() - now.getTime();
				long diffDays = diff/(24 * 60 * 60 * 1000); // ?†ÏãªÎ™åÏòô?†Ïèô???†Ïèô?ôÂç†?ôÏòô?†Ïèô???†Ïèô?ôÂç†?ôÏòô ?†Ïèô?ôÂç†?±Î™å???†Ïèô?ôÏßú ?†Ïèô?ôÂç†?ôÏòô?†Ïèô???†Ïèô?ôÌôò
				long danger = expiDate/3;						 // ?†Ïèô?ôÂç†?ôÏòô?†Ïèô?ôÂç†?ôÏòô?†Ïèô???†Ïèô?ôÂç†?úÏö∏?ôÂç†?ôÏòô ?†Ïèô?ôÂç†?îÎì∏???†Ïèô?ôÂç†?ôÏòô?†Ïèô??1/3 ?†Ïã±Î™åÏòô?†Ïã±Î™åÏòô ?†Ïèô?ôÂç†?ôÏòô
				long normal = expiDate*2/3;				     // 1/3 ?†Ïã±?ºÏòô 2/3 ?†Ïã±Î™åÏòô ?†Ïã±Î™åÏòô ?†Ïèô?ôÂç†?ôÏòô ?†Ïèô???†Ïã±?ºÏòô?†Ïèô???†Ïèô?ôÂç†?ôÏòô
				if(danger > 14) {								 // ?†Ïèô?ôÂç†?ôÏòô ?†Ïèô?ôÂç†?ôÏòô?†Ïèô???†Ïç©Í∞ÑÂç†?ôÏòô 2?†Ïèô???†Ïã≠Í≥§Ïòô?†Ïèô?ôÂç†Ôø??†Ïèô?ôÂç†?ôÏòô ?†Ïç©Í∞ÑÂç†?ôÏòô 2?†ÏåçÎ§ÑÏòô ?†Ïèô?ôÂç†?ôÏòô
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

