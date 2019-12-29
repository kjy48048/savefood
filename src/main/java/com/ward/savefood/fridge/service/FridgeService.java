package com.ward.savefood.fridge.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ward.savefood.fridge.dao.FridgeDao;
import com.ward.savefood.member.model.LoginUserRequest;

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

	public String getMemberSeq(String memberId) {
		String memberSeq = fridgeDao.getMemberSeq(memberId);
		return memberSeq;
	}

	public ArrayList<Map<String, Object>> getFridgeList(String memberSeq) {
		ArrayList<Map<String, Object>> fridgeList = fridgeDao.getFridgeList(memberSeq);
		return fridgeList;
	}

	public ArrayList<Map<String, Object>> getSaveplaceList(int[] fridgeSeqList) {
		ArrayList<Map<String, Object>> saveplaceList = fridgeDao.getSaveplaceList(fridgeSeqList);
		return saveplaceList;
	}

	public ArrayList<Map<String, Object>> getSavefoodList(int[] saveplaceSeqList) {
		ArrayList<Map<String, Object>> savefoodList = fridgeDao.getSavefoodList(saveplaceSeqList);
		return savefoodList;
	}
}
