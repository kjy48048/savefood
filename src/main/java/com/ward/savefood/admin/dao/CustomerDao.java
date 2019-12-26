package com.ward.savefood.admin.dao;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao {
	int updateCustomer(Map<String, Object> updateCustomer);
	ArrayList<Map<String, Object>> selectMemberList(Map<String, Object> selectQuary);
	Map<String, Object> selectPager();
	ArrayList<Map<String, Object>> selectRoleList();
	ArrayList<Map<String, Object>> selectStatusList();
//	int joinUser(Map<String, Object> joinUser);
//	int creatPassword(Map<String, Object> passwordParam);
//	int insertAuth(Map<String, Object> insertAuth);
//	int insertTelegram(Map<String, Object> insertTelegram);
//	String getAuthKey(String memberId);
//	int updateAuth(Map<String, Object> updateAuth);
//	Map<String, Object> loginUser(String memberId);
}
