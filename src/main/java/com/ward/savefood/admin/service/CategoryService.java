package com.ward.savefood.admin.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.ward.savefood.admin.dao.CategoryDao;
import com.ward.savefood.admin.model.InsertCategoryRequest;
import com.ward.savefood.admin.model.UpdateCategoryRequest;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

	public ResponseEntity<?> insertCategory(InsertCategoryRequest insertCategoryRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String categoryName = insertCategoryRequest.getCategoryName();
			if(StringUtils.isEmpty(categoryName)) {
				return new ResponseEntity<>("input cateogry name", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> insertCategory = new HashMap<String, Object>();
			insertCategory.put("categoryName", insertCategoryRequest.getCategoryName());
			int insertResult = categoryDao.insertCategory(insertCategory);
			
			if(insertResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(categoryName + " " + "insert complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to regist category", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> updateCategory(UpdateCategoryRequest updateCategoryRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			String categoryName = updateCategoryRequest.getCategoryName();
			int categorySeq = updateCategoryRequest.getCategorySeq();
			if(StringUtils.isEmpty(categoryName) || categorySeq <= 0) {
				return new ResponseEntity<>("no category info", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> updateCategory = new HashMap<String, Object>();
			updateCategory.put("categorySeq", updateCategoryRequest.getCategorySeq());
			updateCategory.put("categoryName", updateCategoryRequest.getCategoryName());
			int updateResult = categoryDao.updateCategory(updateCategory);
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(categoryName + " " + "update complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update category", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> deleteCategory(List<UpdateCategoryRequest> updateCategoryRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {			
			
			if(updateCategoryRequest.size() == 0) {
				return new ResponseEntity<>("no category info", HttpStatus.NO_CONTENT);
			}
			int updateResult = 0;
			
			for(int i = 0; i < updateCategoryRequest.size(); i++) {
				Map<String, Object> updateCategory = new HashMap<String, Object>();
				updateCategory.put("categorySeq", updateCategoryRequest.get(i).getCategorySeq());
				updateResult += categoryDao.deleteCategory(updateCategory);				
			}
	
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>(updateResult + "delete complete", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to delete category", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ArrayList<Map<String, Object>> selectInsertInfo(InsertCategoryRequest insertCategoryRequest) {	
		try {	
			String categoryName = insertCategoryRequest.getCategoryName();
			
			if(StringUtils.isEmpty(categoryName)) {
				return null;
			}
			
			Map<String, Object> insertCategory = new HashMap<String, Object>();
			insertCategory.put("categoryName", insertCategoryRequest.getCategoryName());
			insertCategory.put("searchText", insertCategoryRequest.getSearchText());
			ArrayList<Map<String, Object>> selectResult = categoryDao.selectInsertInfo(insertCategory);
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Map<String, Object>> getCategoryList() {	
		try {	
			
			ArrayList<Map<String, Object>> selectResult = categoryDao.getCategoryList();
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
//	
	
//	public ResponseEntity<?> createAuthKey(CreateAuthKeyRequest createAuthKeyRequest) {
//		try {
//			String getAuthKey = memberDao.getAuthKey(createAuthKeyRequest.getMemberId());
//			
//			if(!StringUtils.isEmpty(getAuthKey)) {
//				return new ResponseEntity<>("already authKey", HttpStatus.MULTI_STATUS);
//			}
//			
//			String authKey = authKeyUtil.getAuthKey();
//			
//			Map<String, Object> insertAuth = new HashMap<String, Object>();
//			insertAuth.put("memberId", createAuthKeyRequest.getMemberId());
//			insertAuth.put("authKey", authKey);
//			
//			int authResult = memberDao.insertAuth(insertAuth);
//			if(authResult > 0) {
//				return new ResponseEntity<>(authKey, HttpStatus.OK);
//			}
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<>("fail createAuthKey", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	public ResponseEntity<?> telegramAuth(TelegramAuthRequest telegramAuthRequest) {
//		TransactionStatus status = transactionManager.getTransaction(def);
//		
//		try {
//			String getAuthKey = memberDao.getAuthKey(telegramAuthRequest.getMemberId());
//			if(StringUtils.isEmpty(getAuthKey)) {
//				return new ResponseEntity<>("no select authKey", HttpStatus.NO_CONTENT);
//			}
//			
//			if(!getAuthKey.equals(telegramAuthRequest.getAuthKey())) {
//				return new ResponseEntity<>("wrong authKey", HttpStatus.BAD_REQUEST);
//			}
//			
//			long chatId = telegram.authMember(getAuthKey);
//			
//			if(chatId == -2) {
//				return new ResponseEntity<>("fail telegram", HttpStatus.INTERNAL_SERVER_ERROR);
//			} else if(chatId == -1) {
//				return new ResponseEntity<>("no search authKey", HttpStatus.NO_CONTENT);
//			}
//			
//			Map<String, Object> insertTelegram = new HashMap<String, Object>();
//			insertTelegram.put("memberId", telegramAuthRequest.getMemberId());
//			insertTelegram.put("chatId", chatId);
//			
//			int telegramResult = memberDao.insertTelegram(insertTelegram);
//			if(telegramResult > 0) {
//				Map<String, Object> updateAuth = new HashMap<String, Object>();
//				updateAuth.put("memberId", telegramAuthRequest.getMemberId());
//				updateAuth.put("authKey", telegramAuthRequest.getAuthKey());
//				
//				int updateAuthResult = memberDao.updateAuth(updateAuth);
//				if(updateAuthResult > 0) {
//					transactionManager.commit(status);
//					return new ResponseEntity<>("ok", HttpStatus.OK);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		transactionManager.rollback(status);
//		return new ResponseEntity<>("fail telegramAuth", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	public ResponseEntity<?> loginUser(LoginUserRequest loginUserRequest, HttpSession session) {
//		
//		try {
//			Map<String, Object> loginUserResult = memberDao.loginUser(loginUserRequest.getMemberId());
//			if(loginUserResult != null) {
//				if(passwordUtil.checkHash(loginUserRequest.getPassword(), loginUserResult.get("member_password").toString())) {
//					session.setAttribute("loginInfo", loginUserRequest.getMemberId());
//					session.setMaxInactiveInterval(60 * 60);
//					return new ResponseEntity<>("ok", HttpStatus.OK);
//				}
//			}
//			return new ResponseEntity<>("select not user", HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("fail loginUser", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	public ResponseEntity<?> logoutUser(@Valid LogoutUserRequest logoutUserRequest, HttpSession session) {
//		try {
//			String sessionMemberId = (String) session.getAttribute("loginInfo");
//			if(sessionMemberId != null) {
//				session.removeAttribute("loginInfo");
//				return new ResponseEntity<>("ok", HttpStatus.OK);
//			}
//			
//			return new ResponseEntity<>("select not session loginInfo", HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("fail logoutUser", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
