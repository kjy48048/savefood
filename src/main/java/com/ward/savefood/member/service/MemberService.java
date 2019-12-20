package com.ward.savefood.member.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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

import com.ward.savefood.common.sender.Telegram;
import com.ward.savefood.common.util.AuthKeyUtil;
import com.ward.savefood.common.util.PasswordUtil;
import com.ward.savefood.member.dao.MemberDao;
import com.ward.savefood.member.model.CreateAuthKeyRequest;
import com.ward.savefood.member.model.LoginUserRequest;
import com.ward.savefood.member.model.LogoutUserRequest;
import com.ward.savefood.member.model.MemberJoinRequest;
import com.ward.savefood.member.model.TelegramAuthRequest;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Autowired
	private Telegram telegram;
	
	@Autowired
	private AuthKeyUtil authKeyUtil;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
	
	public ResponseEntity<?> joinUser(MemberJoinRequest memberJoinRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String chatId = memberDao.selectJoinInfo(memberJoinRequest);
			if(StringUtils.isEmpty(chatId)) {
				return new ResponseEntity<>("no search joinInfo", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> joinUser = new HashMap<String, Object>();
			joinUser.put("memberId", memberJoinRequest.getMemberId());
			int joinResult = memberDao.joinUser(joinUser);
			
			if(joinResult > 0 && joinUser.get("memberSeq") != null) {
				Map<String, Object> passwordParam = new HashMap<String, Object>();
				passwordParam.put("memberSeq", joinUser.get("memberSeq"));
				passwordParam.put("password", passwordUtil.hash(memberJoinRequest.getPassword()));
				
				int creatUser = memberDao.creatPassword(passwordParam);
				if(creatUser > 0) {
					transactionManager.commit(status);
					telegram.sendMessage(chatId, "SaveFood 가입을 축하합니다!");
					return new ResponseEntity<>("ok", HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail joinUser", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> createAuthKey(CreateAuthKeyRequest createAuthKeyRequest) {
		try {
			String getAuthKey = memberDao.getAuthKey(createAuthKeyRequest.getMemberId());
			
			if(!StringUtils.isEmpty(getAuthKey)) {
				return new ResponseEntity<>("already authKey", HttpStatus.MULTI_STATUS);
			}
			
			String authKey = authKeyUtil.getAuthKey();
			
			Map<String, Object> insertAuth = new HashMap<String, Object>();
			insertAuth.put("memberId", createAuthKeyRequest.getMemberId());
			insertAuth.put("authKey", authKey);
			
			int authResult = memberDao.insertAuth(insertAuth);
			if(authResult > 0) {
				return new ResponseEntity<>(authKey, HttpStatus.OK);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("fail createAuthKey", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> telegramAuth(TelegramAuthRequest telegramAuthRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String getAuthKey = memberDao.getAuthKey(telegramAuthRequest.getMemberId());
			if(StringUtils.isEmpty(getAuthKey)) {
				return new ResponseEntity<>("no select authKey", HttpStatus.NO_CONTENT);
			}
			
			if(!getAuthKey.equals(telegramAuthRequest.getAuthKey())) {
				return new ResponseEntity<>("wrong authKey", HttpStatus.BAD_REQUEST);
			}
			
			long chatId = telegram.authMember(getAuthKey);
			
			if(chatId == -2) {
				return new ResponseEntity<>("fail telegram", HttpStatus.INTERNAL_SERVER_ERROR);
			} else if(chatId == -1) {
				return new ResponseEntity<>("no search authKey", HttpStatus.NO_CONTENT);
			}
			
			Map<String, Object> insertTelegram = new HashMap<String, Object>();
			insertTelegram.put("memberId", telegramAuthRequest.getMemberId());
			insertTelegram.put("chatId", chatId);
			
			int telegramResult = memberDao.insertTelegram(insertTelegram);
			if(telegramResult > 0) {
				Map<String, Object> updateAuth = new HashMap<String, Object>();
				updateAuth.put("memberId", telegramAuthRequest.getMemberId());
				updateAuth.put("authKey", telegramAuthRequest.getAuthKey());
				
				int updateAuthResult = memberDao.updateAuth(updateAuth);
				if(updateAuthResult > 0) {
					transactionManager.commit(status);
					return new ResponseEntity<>("ok", HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail telegramAuth", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> loginUser(LoginUserRequest loginUserRequest, HttpSession session) {
		
		try {
			Map<String, Object> loginUserResult = memberDao.loginUser(loginUserRequest.getMemberId());
			if(loginUserResult != null) {
				if(passwordUtil.checkHash(loginUserRequest.getPassword(), loginUserResult.get("member_password").toString())) {
					session.setAttribute("loginInfo", loginUserRequest.getMemberId());
					session.setMaxInactiveInterval(60 * 60);
					return new ResponseEntity<>("ok", HttpStatus.OK);
				}
			}
			return new ResponseEntity<>("select not user", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("fail loginUser", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> logoutUser(@Valid LogoutUserRequest logoutUserRequest, HttpSession session) {
		try {
			String sessionMemberId = (String) session.getAttribute("loginInfo");
			if(sessionMemberId != null) {
				session.removeAttribute("loginInfo");
				return new ResponseEntity<>("ok", HttpStatus.OK);
			}
			
			return new ResponseEntity<>("select not session loginInfo", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("fail logoutUser", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
