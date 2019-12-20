package com.ward.savefood.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
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

@Service
public class FoodService {
	
	@Autowired
	private FoodDao foodDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

	public ResponseEntity<?> insertFood(InsertFoodRequest insertFoodRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String foodName = insertFoodRequest.getFoodName();
			int expiDate = insertFoodRequest.getFoodExpiDate();
			int categorySeq = insertFoodRequest.getCategorySeq();
			
			if(StringUtils.isEmpty(foodName) || (expiDate == 0 || expiDate > 999) || categorySeq == 0) {
				return new ResponseEntity<>("input food info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> insertFood = new HashMap<String, Object>();
			insertFood.put("foodName", foodName);
			insertFood.put("expiDate", expiDate);
			insertFood.put("categorySeq", categorySeq);
			int insertResult = foodDao.insertFood(insertFood);
			
			if(insertResult > 0) {
				transactionManager.commit(status);
				Map<String, Object> food = new HashMap<String, Object>();
				food.put("food_seq", insertFood.get("foodSeq"));
				return new ResponseEntity<>(food, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to regist food", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> updateFood(UpdateFoodRequest updateFoodRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {		
			int foodSeq = updateFoodRequest.getFoodSeq();
			String foodName = updateFoodRequest.getFoodName();
			int expiDate = updateFoodRequest.getFoodExpiDate();
			String foodImg = updateFoodRequest.getFoodImg();
			
			if(foodSeq == 0 || StringUtils.isEmpty(foodName) || (expiDate == 0 || expiDate > 999)) {
				return new ResponseEntity<>("input food info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> updateFood = new HashMap<String, Object>();
			updateFood.put("foodSeq", foodSeq);
			updateFood.put("foodName", foodName);
			updateFood.put("foodExpiDate", expiDate);
			updateFood.put("foodImg", foodImg);
			int updateResult = foodDao.updateFood(updateFood);
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				Map<String, Object> food = new HashMap<String, Object>();
				food.put("food_seq", updateFood.get("foodSeq"));
				return new ResponseEntity<>(food, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update food", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> deleteFood(List<UpdateFoodRequest> updateFoodRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			
			if(updateFoodRequest.size() == 0) {
				return new ResponseEntity<>("no food info", HttpStatus.NO_CONTENT);
			}
			int updateResult = 0;
			
			for(int i = 0; i < updateFoodRequest.size(); i++) {
				Map<String, Object> updateFood = new HashMap<String, Object>();
				updateFood.put("foodSeq", updateFoodRequest.get(i).getFoodSeq());
				updateResult += foodDao.deleteFood(updateFood);				
			}
	
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(updateResult + "delete complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to delete food", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> updateFoodImg(String foodSeq, MultipartFile mf) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {	
			if(StringUtils.isEmpty(foodSeq)) {
				return new ResponseEntity<>("input food info", HttpStatus.NO_CONTENT);
			}
			String savePath = "c:\\savefood\\upload\\food";
			String prefixUrl = "/upload/food/";
			File dir = new File(savePath); 
			if (!dir.isDirectory()) {
				dir.mkdirs(); 
			}
			
			String originFilename = mf.getOriginalFilename();
			String extName
				= originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = mf.getSize();
			if(size > 5000*1000*1000) {
				return new ResponseEntity<>("over data size", HttpStatus.PAYLOAD_TOO_LARGE);
			}
			
			String saveFileName = "";
			
			Calendar calendar = Calendar.getInstance();
			saveFileName += calendar.get(Calendar.YEAR);
			saveFileName += calendar.get(Calendar.MONTH);
			saveFileName += calendar.get(Calendar.DATE);
			saveFileName += calendar.get(Calendar.HOUR);
			saveFileName += calendar.get(Calendar.MINUTE);
			saveFileName += calendar.get(Calendar.SECOND);
			saveFileName += calendar.get(Calendar.MILLISECOND);
			saveFileName += extName;
			
			byte[] data = mf.getBytes();
			FileOutputStream fos = new FileOutputStream(savePath + "\\" + saveFileName);
			fos.write(data);
			fos.close();
			
			String foodImg = prefixUrl+saveFileName;
			
			Map<String, Object> updateFood = new HashMap<String, Object>();
			updateFood.put("foodSeq", foodSeq);
			updateFood.put("foodImg", foodImg);
			int updateResult = foodDao.updateFoodImg(updateFood);
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("update complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update foodImg", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ArrayList<Map<String, Object>> selectInsertInfo(InsertFoodRequest insertFoodRequest) {	
		try {	
			String foodName = insertFoodRequest.getFoodName();
			
			if(StringUtils.isEmpty(foodName)) {
				return null;
			}
			
			Map<String, Object> insertFood = new HashMap<String, Object>();
			insertFood.put("foodName", insertFoodRequest.getFoodName());
			insertFood.put("searchText", insertFoodRequest.getSearchText());
			ArrayList<Map<String, Object>> selectResult = foodDao.selectInsertInfo(insertFood);
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Map<String, Object>> getFoodList(String categorySeq) {	
		try {	

			ArrayList<Map<String, Object>> selectResult = foodDao.getFoodList(categorySeq);
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
