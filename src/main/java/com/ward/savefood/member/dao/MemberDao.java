package com.ward.savefood.member.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ward.savefood.member.model.MemberJoinRequest;

@Repository
public interface MemberDao {
	String selectJoinInfo(MemberJoinRequest memberJoinRequest);
	int joinUser(Map<String, Object> joinUser);
	int creatPassword(Map<String, Object> passwordParam);
	int insertAuth(Map<String, Object> insertAuth);
	int insertTelegram(Map<String, Object> insertTelegram);
	String getAuthKey(String memberId);
	int updateAuth(Map<String, Object> updateAuth);
	Map<String, Object> loginUser(String memberId);
}
