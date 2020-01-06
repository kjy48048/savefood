package com.ward.savefood.fridge.service;


import java.util.ArrayList;
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

import com.ward.savefood.food.service.UserFoodService;
import com.ward.savefood.fridge.dao.FridgeDao;
import com.ward.savefood.fridge.model.InsertSaveplaceRequest;
import com.ward.savefood.fridge.model.UpdateSaveplaceRequest;

@Service
public class FridgeService {
	
	@Autowired
	private FridgeDao fridgeDao;
	
	@Autowired
	private UserFoodService userFoodService;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

	// get memberSeq
	public String getMemberSeq(String memberId) {
		String memberSeq = fridgeDao.getMemberSeq(memberId);
		return memberSeq;
	}

	// get fridgeList
	public ArrayList<Map<String, Object>> getFridgeList(String memberSeq) {
		ArrayList<Map<String, Object>> fridgeList = fridgeDao.getFridgeList(memberSeq);
		return fridgeList;
	}

	// get saveplaceList - fridge/index.jsp �뿉�꽌 �솢�슜
	public ArrayList<Map<String, Object>> getSaveplaceList(ArrayList<Map<String, Object>> fridge) {
		
		Map<String, Object> selectFridge = new HashMap<>();
		selectFridge.put("fridge", fridge);
		
		return fridgeDao.getSaveplace(selectFridge);
	}

	public ArrayList<Map<String, Object>> getSavefoodList(ArrayList<Map<String, Object>> saveplace) {
		Map<String, Object> selectSavefoods = new HashMap<>();
		selectSavefoods.put("saveplace", saveplace);
		
		ArrayList<Map<String, Object>> savefoodList1 = fridgeDao.getSavefoods(selectSavefoods);

		ArrayList<Map<String, Object>> savefoodList2 = userFoodService.calculateFoodRisk(savefoodList1);
		
		ArrayList<Map<String, Object>> dashboardList = new ArrayList<>();
		long danger_expi_date = 9999999;
		long normal_expi_date = 9999999;
		long safe_expi_date = 9999999;
		for(int i = 0; i<savefoodList2.size(); i++) {
			if(i == 0 || savefoodList2.get(i).get("fridge_seq") != savefoodList2.get(i-1).get("fridge_seq")) {
				Map<String, Object> dashboard = new HashMap<>();
				dashboard.put("fridge_seq", savefoodList2.get(i).get("fridge_seq"));
				dashboard.put("danger_cnt", 0);
				dashboard.put("danger_food_name", "");
				dashboard.put("normal_cnt", 0);
				dashboard.put("normal_food_name", "");
				dashboard.put("safe_cnt", 0);
				dashboard.put("safe_food_name", "");
				dashboardList.add(dashboard);
			}
			int index = dashboardList.size()-1;
			if((int)savefoodList2.get(i).get("savefood_risk") == 0) {
				dashboardList.get(index).put("danger_cnt", (int)dashboardList.get(index).get("danger_cnt")+1);
				if(danger_expi_date > (long)savefoodList2.get(i).get("savefood_remain_day")) {
					dashboardList.get(index).put("danger_food_name", savefoodList2.get(i).get("savefood_name"));
					danger_expi_date = (long)savefoodList2.get(i).get("savefood_remain_day");
				}
			}
			else if((int)savefoodList2.get(i).get("savefood_risk") == 1) {
				dashboardList.get(index).put("normal_cnt", (int)dashboardList.get(index).get("normal_cnt")+1);
				if(normal_expi_date > (long)savefoodList2.get(i).get("savefood_remain_day")) {
					dashboardList.get(index).put("normal_food_name", savefoodList2.get(i).get("savefood_name"));
					normal_expi_date = (long)savefoodList2.get(i).get("savefood_remain_day");
				}
			}
			else {
				dashboardList.get(index).put("safe_cnt", (int)dashboardList.get(index).get("safe_cnt")+1);
				if(safe_expi_date > (long)savefoodList2.get(i).get("savefood_remain_day")) {
					dashboardList.get(index).put("safe_food_name", savefoodList2.get(i).get("savefood_name"));
					safe_expi_date = (long)savefoodList2.get(i).get("savefood_remain_day");
				}
			}
			
		}
		
		return dashboardList;
	}
	
	// get storage
	public List<Map<String, Object>> getStorage() {
		
		try {
			List<Map<String, Object>> map = fridgeDao.getStorage();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// get saveplaceList
	public ArrayList<Map<String, Object>> getSaveplaceList(String fridgeSeq) {	
		try {	

			ArrayList<Map<String, Object>> selectResult = fridgeDao.getSaveplaceList(fridgeSeq);
			
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}	
	
	
	// insert saveplace
	public ResponseEntity<?> insertSaveplace(InsertSaveplaceRequest insertSaveplaceRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String saveplaceName = insertSaveplaceRequest.getSaveplace();
			int storage = insertSaveplaceRequest.getStorage();
			int fridgeSeq = insertSaveplaceRequest.getFridgeSeq();
			
			if(StringUtils.isEmpty(saveplaceName) || storage == 0 || fridgeSeq == 0) {
				return new ResponseEntity<>("input saveplace info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> insertSaveplace = new HashMap<String, Object>();
			insertSaveplace.put("saveplace", saveplaceName);
			insertSaveplace.put("storage", storage);
			insertSaveplace.put("fridgeSeq", fridgeSeq);
			int insertResult = fridgeDao.insertSaveplace(insertSaveplace);
			
			if(insertResult > 0) {
				transactionManager.commit(status);
				Map<String, Object> saveplace = new HashMap<String, Object>();
				saveplace.put("saveplace_seq", insertSaveplace.get("saveplaceSeq"));
				return new ResponseEntity<>(saveplace, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to regist savepalce", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// update saveplace
	public ResponseEntity<?> updateSaveplace(UpdateSaveplaceRequest updateSaveplaceRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {	
			String saveplaceName = updateSaveplaceRequest.getSaveplaceName();
			int saveplaceSeq = updateSaveplaceRequest.getSaveplaceSeq();
			
			if(StringUtils.isEmpty(saveplaceName) || saveplaceSeq == 0) {
				return new ResponseEntity<>("no saveplace info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> saveplace = new HashMap<String, Object>();
			saveplace.put("saveplaceSeq", updateSaveplaceRequest.getSaveplaceSeq());
			saveplace.put("saveplaceName", updateSaveplaceRequest.getSaveplaceName());
			int updateResult = fridgeDao.updateSaveplace(saveplace);				
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(saveplace, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update saveplace", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	// delete saveplace
	public ResponseEntity<?> deleteSaveplace(UpdateSaveplaceRequest updateSaveplaceRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {	
			
			if(updateSaveplaceRequest == null) {
				return new ResponseEntity<>("no saveplace info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> saveplace = new HashMap<String, Object>();
			saveplace.put("saveplaceSeq", updateSaveplaceRequest.getSaveplaceSeq());
			int deleteResult = fridgeDao.deleteSaveplace(saveplace);				
			
			if(deleteResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(deleteResult + "delete complete", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to delete saveplace", HttpStatus.INTERNAL_SERVER_ERROR);
	}		
}
