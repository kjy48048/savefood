package com.ward.savefood.fridge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface FridgeDao {
	List<Map<String, Object>> getStorage();
	ArrayList<Map<String, Object>> getSaveplaceList(String fridgeSeq);
	int insertSaveplace(Map<String, Object> insertSaveplace);
	int deleteSaveplace(Map<String, Object> deleteSaveplace);
	
}
