package com.ward.savefood.food.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodDao {
	int insertUserFood(Map<String, Object> insertFood);
	int updateUserFood(Map<String, Object> updateFood);
	int deleteUserFood(Map<String, Object> deleteFood);
	ArrayList<Map<String, Object>> getFoodList(Map<String, Object> selectFood);
	ArrayList<Map<String, Object>> getAllFoodList();
}
