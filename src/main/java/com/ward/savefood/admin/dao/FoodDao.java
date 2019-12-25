package com.ward.savefood.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {
	int insertFood(Map<String, Object> insertFood);
	int updateFood(Map<String, Object> updateFood);
	int updateFoodImg(Map<String, Object> updateFood);
	int deleteFood(Map<String, Object> deleteFood);
	ArrayList<Map<String, Object>> selectInsertInfo(Map<String, Object> insertFood);
	ArrayList<Map<String, Object>> getFoodList(String categorySeq);
	ArrayList<Map<String, Object>> getAllFoodList();
//	int joinUser(Map<String, Object> joinUser);
//	int creatPassword(Map<String, Object> passwordParam);
//	int insertAuth(Map<String, Object> insertAuth);
//	int insertTelegram(Map<String, Object> insertTelegram);
//	String getAuthKey(String memberId);
//	int updateAuth(Map<String, Object> updateAuth);
//	Map<String, Object> loginUser(String memberId);
}
