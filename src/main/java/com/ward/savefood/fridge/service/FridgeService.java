package com.ward.savefood.fridge.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ward.savefood.fridge.dao.FridgeDao;

@Service
public class FridgeService {
	
	@Autowired
	private FridgeDao fridgeDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
	
	public List<Map<String, Object>> getStorage() {
		
		try {
			List<Map<String, Object>> map = fridgeDao.getStorage();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
