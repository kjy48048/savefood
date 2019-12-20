package com.ward.savefood.admin.dao;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao {
	int insertCategory(Map<String, Object> insertCategory);
	int updateCategory(Map<String, Object> updateCategory);
	int deleteCategory(Map<String, Object> deleteCateogry);
	ArrayList<Map<String, Object>> selectInsertInfo(Map<String, Object> insertCategory);
	ArrayList<Map<String, Object>> getCategoryList();
//	int joinUser(Map<String, Object> joinUser);
//	int creatPassword(Map<String, Object> passwordParam);
//	int insertAuth(Map<String, Object> insertAuth);
//	int insertTelegram(Map<String, Object> insertTelegram);
//	String getAuthKey(String memberId);
//	int updateAuth(Map<String, Object> updateAuth);
//	Map<String, Object> loginUser(String memberId);
}
