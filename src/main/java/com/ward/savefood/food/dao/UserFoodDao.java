package com.ward.savefood.food.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodDao {
	int insertUserFood(Map<String, Object> insertFood);
	int updateSavefood(Map<String, Object> updateFood);
	int deleteSavefood(Map<String, Object> deleteFood);
	Map<String, Object> getExpiDate(Map<String, Object> selectSavefood);
	Map<String, Object> selectFood(Map<String, Object> selectFood);
	Map<String, Object> getSavefood(Map<String, Object> selectSavefood);
	ArrayList<Map<String, Object>> searchFoodList(Map<String, Object> selectFood);
	ArrayList<Map<String, Object>> getFoodList(Map<String, Object> selectFood);
	ArrayList<Map<String, Object>> getAllFoodList();
}
