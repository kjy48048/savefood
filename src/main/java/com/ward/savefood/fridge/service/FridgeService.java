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

import com.ward.savefood.fridge.dao.FridgeDao;
import com.ward.savefood.fridge.model.InsertSaveplaceRequest;
import com.ward.savefood.fridge.model.UpdateSaveplaceRequest;

@Service
public class FridgeService {
	
	@Autowired
	private FridgeDao fridgeDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

	public String getMemberSeq(String memberId) {
		String memberSeq = fridgeDao.getMemberSeq(memberId);
		return memberSeq;
	}

	public ArrayList<Map<String, Object>> getFridgeList(String memberSeq) {
		ArrayList<Map<String, Object>> fridgeList = fridgeDao.getFridgeList(memberSeq);
		return fridgeList;
	}

	public ArrayList<Map<String, Object>> getSaveplaceList(int[] fridgeSeqList) {
		ArrayList<Map<String, Object>> saveplaceList = fridgeDao.getSaveplace(fridgeSeqList);
		return saveplaceList;
	}

	public ArrayList<Map<String, Object>> getSavefoodList(int[] saveplaceSeqList) {
		ArrayList<Map<String, Object>> savefoodList = fridgeDao.getSavefoodList(saveplaceSeqList);
		return savefoodList;
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
